"""JVM bytecode (javap -c text) -> GDScript basic-block state machine, for a.run().

a.run() is the original game loop; CFR could not structure it. Translating bytecode keeps exact
semantics.  The Java loop `while(aO){ switch(d){...}; repaint(); serviceRepaints(); sleep-until-40ms }`
becomes `func run_tick()` which executes from `r_pc` until the `Canvas.repaint` call (frame end),
saves the resume pc and returns.  Locals become members (r_L<n>) so they persist across ticks.
"""
import re, sys, json

GD_RESERVED = set("""if elif else for while match break continue pass return class class_name extends is in as self signal func static const enum var
breakpoint preload await yield assert void PI TAU INF NAN and or not true false null super get set print str int float bool range len abs min max load
""".split())

def fieldname(cls, name):
    if name in ('do', 'if'): name = name + '_'
    if cls == 'Midlet': return 'MF_' + name
    return ('f_' + name) if name in GD_RESERVED else name

def tcode_desc(d):
    """descriptor of one param -> j2gd sig code"""
    dims = 0
    while d.startswith('['): dims += 1; d = d[1:]
    base = {'I':'i','Z':'z','B':'b','C':'c','J':'j','S':'h'}.get(d[0])
    if base is None:
        cn = d[1:-1]
        base = 's' if cn == 'java/lang/String' else 'o'
    if dims: return base.upper() + 'A'*(dims-1)
    return base

def split_desc(desc):
    m = re.match(r'\((.*)\)(.*)', desc); ps = m.group(1); ret = m.group(2); out = []; i = 0
    while i < len(ps):
        j = i
        while ps[j] == '[': j += 1
        if ps[j] == 'L': j = ps.index(';', j)
        out.append(ps[i:j+1]); i = j+1
    return out, ret

def parse(path):
    lines = open(path, encoding='utf8').read().split('\n')
    ins = []  # (off, op, args, comment)
    i = 0; exc = []
    while i < len(lines):
        l = lines[i]
        m = re.match(r'\s+(\d+): (\w+)\s*(.*)$', l)
        if m:
            off = int(m.group(1)); op = m.group(2); rest = m.group(3)
            comment = ''
            if '//' in rest:
                rest, comment = rest.split('//', 1); comment = comment.strip()
            rest = rest.strip()
            if op in ('tableswitch', 'lookupswitch'):
                table = {}; dflt = None; i += 1
                while '}' not in lines[i]:
                    mm = re.match(r'\s+(-?\w+): (\d+)', lines[i])
                    if mm:
                        if mm.group(1) == 'default': dflt = int(mm.group(2))
                        else: table[int(mm.group(1))] = int(mm.group(2))
                    i += 1
                ins.append((off, op, (table, dflt), comment))
            else:
                ins.append((off, op, rest, comment))
        else:
            mm = re.match(r'\s+(\d+)\s+(\d+)\s+(\d+)\s+(Class|any)', l)
            if mm: exc.append(tuple(int(x) for x in mm.groups()[:3]))
        i += 1
    return ins, exc

BRANCH1 = {'ifeq':'==','ifne':'!=','iflt':'<','ifge':'>=','ifgt':'>','ifle':'<=','ifnull':'==null','ifnonnull':'!=null'}
BRANCH2 = {'if_icmpeq':'==','if_icmpne':'!=','if_icmplt':'<','if_icmpge':'>=','if_icmpgt':'>','if_icmple':'<=','if_acmpeq':'==','if_acmpne':'!='}

class Gen:
    def __init__(self, ins, exc, yield_off, resume_off, method_map):
        self.ins = ins; self.exc = exc; self.yield_off = yield_off; self.resume_off = resume_off
        self.by_off = {x[0]: k for k, x in enumerate(ins)}
        self.mm = method_map
        self.tmp = 0
        self.errors = []

    def leaders(self):
        L = {0, self.resume_off}
        for k, (off, op, a, c) in enumerate(self.ins):
            nxt = self.ins[k+1][0] if k+1 < len(self.ins) else None
            if op in BRANCH1 or op in BRANCH2 or op in ('goto', 'goto_w'):
                L.add(int(a));
                if nxt is not None: L.add(nxt)
            elif op in ('tableswitch', 'lookupswitch'):
                t, d = a; L.update(t.values()); L.add(d)
                if nxt is not None: L.add(nxt)
            elif op in ('return', 'ireturn', 'areturn', 'athrow'):
                if nxt is not None: L.add(nxt)
            elif off == self.yield_off and nxt is not None:
                L.add(nxt)
        for (f, t, h) in self.exc: L.add(h)
        return sorted(L)

    def newtmp(self):
        self.tmp += 1; return 'r_t%d' % self.tmp

    # symbolic stack entries: (expr, type) type in I Z J A S F(boolean-array?) ; arrays typed 'A:[Z' etc
    def gen(self):
        leaders = self.leaders(); lset = set(leaders)
        # stack depth dataflow
        depth = {0: 0, self.resume_off: 0}
        for (f, t, h) in self.exc: depth[h] = 1
        blocks = {}
        # build block ranges
        offs = [x[0] for x in self.ins]
        for bi, lo in enumerate(leaders):
            if lo not in self.by_off: continue
            k = self.by_off[lo]; body = []
            while k < len(self.ins):
                body.append(self.ins[k]); k += 1
                if k < len(self.ins) and self.ins[k][0] in lset: break
            blocks[lo] = body
        # iterate translation in worklist to get entry depths
        out = {}
        work = [0, self.resume_off] + [h for (_, _, h) in self.exc]
        types_at = {0: [], self.resume_off: []}
        for (_, _, h) in self.exc: types_at[h] = ['A']
        seen = set()
        while work:
            lo = work.pop()
            if lo in seen: continue
            seen.add(lo)
            code, succ = self.block(lo, blocks[lo], types_at[lo])
            out[lo] = code
            for (s, st) in succ:
                if s not in types_at: types_at[s] = st
                elif len(types_at[s]) != len(st): self.errors.append('stack mismatch at %d' % s)
                work.append(s)
        return out

    def block(self, lo, body, entry_types):
        st = [('r_s%d' % i, t) for i, t in enumerate(entry_types)]
        code = []
        succ = []
        def flush_side():
            # materialize non-trivial stack entries before side effects
            for i, (e, t) in enumerate(st):
                if not e.startswith('NEW:') and not re.match(r'^(r_s\d+|r_t\d+|-?\d+|true|false|null|"[^"]*")$', e):
                    tm = self.newtmp(); code.append('%s = %s' % (tm, e)); st[i] = (tm, t)
        def push(e, t='I'): st.append((e, t))
        def pop():
            if not st: self.errors.append('stack underflow at block %d' % lo); return ('0', 'I')
            return st.pop()
        def asint(x):
            e, t = x
            if t == 'Z':
                if e == 'true': return '1'
                if e == 'false': return '0'
                return 'int(%s)' % e
            return e
        def asbool(x):
            e, t = x
            if t == 'Z': return e
            if e == '0': return 'false'
            if e == '1': return 'true'
            return '(%s != 0)' % e
        def asstr(x):
            e, t = x
            if t == 'S': return e
            if t == 'C': return 'char(%s)' % e
            return 'str(%s)' % e
        def exit_to(target):
            # assign stack to slot vars for target
            vals = [x for x in st]
            types = [x[1] for x in st]
            assigns = []
            # avoid aliasing: compute into temps when needed
            tmpv = []
            for i, (e, t) in enumerate(vals):
                if e != 'r_s%d' % i:
                    tm = self.newtmp(); code.append('%s = %s' % (tm, e)); tmpv.append((i, tm))
            for i, tm in tmpv: code.append('r_s%d = %s' % (i, tm))
            succ.append((target, types))
            return 'r_pc = %d' % target
        last_off = body[-1][0]
        for idx, (off, op, a, c) in enumerate(body):
            nxt_off = None
            k = self.by_off[off]
            if k + 1 < len(self.ins): nxt_off = self.ins[k+1][0]
            # ---- constants
            if op.startswith('iconst_'):
                v = op[7:]; push('-1' if v == 'm1' else v); continue
            if op in ('bipush', 'sipush'): push(a); continue
            if op == 'aconst_null': push('null', 'A'); continue
            if op in ('ldc', 'ldc_w', 'ldc2_w'):
                if c == 'String': push('""', 'S')
                elif c.startswith('String '):
                    push(json.dumps(c[7:], ensure_ascii=False), 'S')
                elif c.startswith('int '): push(c[4:])
                elif c.startswith('long '): push(c[5:].rstrip('l'), 'J')
                else: self.errors.append('ldc %s' % c); push('0')
                continue
            if op == 'lconst_0': push('0', 'J'); continue
            if op == 'lconst_1': push('1', 'J'); continue
            # ---- locals
            m = re.match(r'([ilaf])load(?:_(\d))?$', op)
            if m:
                n = m.group(2) if m.group(2) is not None else a
                if n == '0' and m.group(1) == 'a': push('self', 'THIS'); continue
                push('r_L%s' % n, {'i':'I','l':'J','a':'A','f':'F'}[m.group(1)]); continue
            m = re.match(r'([ilaf])store(?:_(\d))?$', op)
            if m:
                n = m.group(2) if m.group(2) is not None else a
                x = pop()
                # materialize stack entries referencing this local
                for i, (e, t) in enumerate(st):
                    if re.search(r'\br_L%s\b' % n, e):
                        tm = self.newtmp(); code.append('%s = %s' % (tm, e)); st[i] = (tm, t)
                code.append('r_L%s = %s' % (n, x[0]))
                continue
            if op == 'iinc':
                n, inc = [s.strip() for s in a.split(',')]
                for i, (e, t) in enumerate(st):
                    if re.search(r'\br_L%s\b' % n, e):
                        tm = self.newtmp(); code.append('%s = %s' % (tm, e)); st[i] = (tm, t)
                code.append('r_L%s += %s' % (n, inc)); continue
            # ---- fields
            if op in ('getstatic', 'putstatic'):
                mm = re.match(r'Field (?:(\w+)\.)?(\w+):(\S+)', c)
                cls = mm.group(1) or 'a'; name = mm.group(2); desc = mm.group(3)
                gd = fieldname(cls, name)
                t = 'Z' if desc == 'Z' else ('S' if desc == 'Ljava/lang/String;' else ('J' if desc == 'J' else ('I' if desc in ('I','B','C','S') else 'A:' + desc)))
                if op == 'getstatic': push(gd, t)
                else:
                    x = pop(); flush_side()
                    v = asbool(x) if desc == 'Z' else (asint(x) if desc in ('I','B','C','S') else x[0])
                    code.append('%s = %s' % (gd, v))
                continue
            if op in ('getfield', 'putfield'):
                mm = re.match(r'Field (?:(\w+)\.)?(\w+):(\S+)', c)
                gd = fieldname('a', mm.group(2))
                if op == 'getfield': pop(); push(gd, 'Z' if mm.group(3) == 'Z' else 'I')
                else:
                    x = pop(); pop(); flush_side(); code.append('%s = %s' % (gd, asbool(x) if mm.group(3)=='Z' else x[0]))
                continue
            # ---- arrays
            if op in ('iaload', 'baload', 'caload', 'saload', 'aaload', 'laload'):
                i_ = pop(); arr = pop()
                at = arr[1]
                et = 'I'
                if at.startswith('A:['):
                    inner = at[3:]
                    if inner == 'Z': et = 'Z'
                    elif inner == 'Ljava/lang/String;': et = 'S'
                    elif inner.startswith('['): et = 'A:' + inner
                    elif inner.startswith('L'): et = 'A'
                    elif inner == 'J': et = 'J'
                push('%s[%s]' % (arr[0], asint(i_)), et); continue
            if op in ('iastore', 'bastore', 'castore', 'sastore', 'aastore', 'lastore'):
                v = pop(); i_ = pop(); arr = pop(); flush_side()
                at = arr[1]
                if at == 'A:[Z' or (op == 'bastore' and v[1] == 'Z'): vv = asbool(v)
                elif op == 'bastore': vv = '_hx_byte(%s)' % asint(v)
                elif op == 'castore': vv = '(%s & 65535)' % asint(v)
                elif op == 'aastore': vv = v[0]
                else: vv = asint(v)
                code.append('%s[%s] = %s' % (arr[0], asint(i_), vv)); continue
            if op == 'arraylength':
                x = pop(); push('%s.size()' % x[0]); continue
            if op == 'newarray':
                n = pop(); fill = 'false' if a.strip() == 'boolean' else '0'
                tt = {'boolean':'A:[Z','int':'A:[I','byte':'A:[B','char':'A:[C'}.get(a.strip(), 'A:[I')
                push('_hx_newarr([%s], %s)' % (asint(n), fill), tt); continue
            if op == 'anewarray':
                n = pop(); inner = c.split('class ',1)[1].strip()
                tt = 'A:[Ljava/lang/String;' if inner == 'java/lang/String' else 'A:[' + inner
                push('_hx_newarr([%s], null)' % asint(n), tt); continue
            # ---- stack ops
            if op == 'dup':
                x = st[-1]
                if not x[0].startswith('NEW:') and not re.match(r'^(r_s\d+|r_t\d+|-?\d+|true|false|null|self)$', x[0]):
                    tm = self.newtmp(); code.append('%s = %s' % (tm, x[0])); st[-1] = (tm, x[1]); x = st[-1]
                push(x[0], x[1]); continue
            if op == 'dup2':
                for j in (-1, -2):
                    x = st[j]
                    if not re.match(r'^(r_s\d+|r_t\d+|-?\d+|true|false|null)$', x[0]):
                        tm = self.newtmp(); code.append('%s = %s' % (tm, x[0])); st[j] = (tm, x[1])
                if st[-1][1] == 'J': push(*st[-1])
                else: b_ = st[-2]; a_ = st[-1]; push(*b_); push(*a_)
                continue
            if op in ('dup_x1', 'dup_x2'):
                x = st[-1]
                if not re.match(r'^(r_s\d+|r_t\d+|-?\d+|true|false|null)$', x[0]):
                    tm = self.newtmp(); code.append('%s = %s' % (tm, x[0])); x = (tm, x[1]); st[-1] = x
                depth = 2 if op == 'dup_x1' else 3
                st.insert(len(st) - depth, x); continue
            if op in ('pop', 'pop2'):
                x = pop()
                if op == 'pop2' and x[1] != 'J': pop()
                if '(' in x[0]: code.append(x[0])  # call with result discarded
                continue
            if op == 'swap':
                x = pop(); y = pop(); push(*x); push(*y); continue
            # ---- arithmetic
            ar = {'iadd':'+','isub':'-','imul':'*','iand':'&','ior':'|','ixor':'^','ishl':'<<','ishr':'>>','ladd':'+','lsub':'-','lmul':'*','land':'&','lor':'|'}
            if op in ar:
                b_ = pop(); a_ = pop()
                if op in ('iand','ior','ixor') and a_[1] == 'Z' and b_[1] == 'Z':
                    push('(%s %s %s)' % (a_[0], {'iand':'and','ior':'or','ixor':'!='}[op], b_[0]), 'Z')
                else:
                    push('(%s %s %s)' % (asint(a_), ar[op], asint(b_)), 'J' if op[0]=='l' else 'I')
                continue
            if op in ('idiv','ldiv'): b_ = pop(); a_ = pop(); push('_hx_div(%s, %s)' % (asint(a_), asint(b_))); continue
            if op in ('irem','lrem'): b_ = pop(); a_ = pop(); push('_hx_mod(%s, %s)' % (asint(a_), asint(b_))); continue
            if op == 'iushr': b_ = pop(); a_ = pop(); push('_hx_ushr(%s, %s)' % (asint(a_), asint(b_))); continue
            if op in ('ineg','lneg'): x = pop(); push('(-%s)' % asint(x)); continue
            if op == 'i2b': x = pop(); push('_hx_byte(%s)' % asint(x)); continue
            if op == 'i2c': x = pop(); push('(%s & 65535)' % asint(x), 'C'); continue
            if op == 'i2s': x = pop(); push('_hx_short(%s)' % asint(x)); continue
            if op == 'i2l': x = pop(); push(asint(x), 'J'); continue
            if op == 'l2i': x = pop(); push('_hx_int(%s)' % x[0]); continue
            if op == 'lcmp':
                b_ = pop(); a_ = pop(); push('_hx_lcmp(%s, %s)' % (a_[0], b_[0])); continue
            # ---- branches
            if op in BRANCH1:
                x = pop(); tgt = int(a)
                if op in ('ifnull', 'ifnonnull'): cond = '%s %s' % (x[0], BRANCH1[op])
                elif x[1] == 'Z' and op in ('ifeq', 'ifne'): cond = ('not %s' % x[0]) if op == 'ifeq' else x[0]
                else: cond = '%s %s 0' % (asint(x), BRANCH1[op])
                flush_side()
                # both successors get same stack
                save_code_len = len(code)
                code.append('if %s:' % cond)
                sub = []
                code_backup = code
                code = sub
                r = exit_to(tgt); sub.append(r)
                code = code_backup
                for s_ in sub: code.append('    ' + s_)
                code.append('else:')
                sub = []; code = sub; r = exit_to(nxt_off); sub.append(r); code = code_backup
                for s_ in sub: code.append('    ' + s_)
                code.append('return_continue')
                return code, succ
            if op in BRANCH2:
                b_ = pop(); a_ = pop(); tgt = int(a)
                if op.startswith('if_acmp'): cond = '%s %s %s' % (a_[0], BRANCH2[op], b_[0])
                else: cond = '%s %s %s' % (asint(a_), BRANCH2[op], asint(b_))
                flush_side()
                code_backup = code
                code.append('if %s:' % cond)
                sub = []; code = sub; r = exit_to(tgt); sub.append(r); code = code_backup
                for s_ in sub: code.append('    ' + s_)
                code.append('else:')
                sub = []; code = sub; r = exit_to(nxt_off); sub.append(r); code = code_backup
                for s_ in sub: code.append('    ' + s_)
                code.append('return_continue')
                return code, succ
            if op in ('goto', 'goto_w'):
                code.append(exit_to(int(a))); code.append('return_continue'); return code, succ
            if op in ('tableswitch', 'lookupswitch'):
                x = pop(); table, dflt = a; flush_side()
                sv = self.newtmp(); code.append('%s = %s' % (sv, asint(x)))
                first = True
                code_backup = code
                for key in sorted(table):
                    code.append(('if %s == %d:' if first else 'elif %s == %d:') % (sv, key)); first = False
                    sub = []; code = sub; r = exit_to(table[key]); sub.append(r); code = code_backup
                    for s_ in sub: code.append('    ' + s_)
                code.append('else:')
                sub = []; code = sub; r = exit_to(dflt); sub.append(r); code = code_backup
                for s_ in sub: code.append('    ' + s_)
                code.append('return_continue')
                return code, succ
            if op == 'return':
                code.append('r_pc = -1'); code.append('return_exit'); return code, succ
            if op == 'athrow':
                code.append('push_error("athrow in run at %d")' % off); code.append('r_pc = -1'); code.append('return_exit'); return code, succ
            if op == 'checkcast': continue
            if op == 'instanceof': x = pop(); push('true', 'Z'); continue
            if op == 'new':
                cn = c.split('class ', 1)[1].strip()
                push('NEW:' + cn, 'NEW'); continue
            # ---- invokes
            if op.startswith('invoke'):
                mm = re.match(r'(?:InterfaceMethod|Method) (?:([\w/$]+)\.)?("?<?\w+>?"?):(\S+)', c)
                owner = mm.group(1) or 'a'; name = mm.group(2).strip('"'); desc = mm.group(3)
                ps, ret = split_desc(desc)
                args = [pop() for _ in ps][::-1]
                recv = None
                if op != 'invokestatic': recv = pop()
                flush_side()
                res = None; rt = 'Z' if ret == 'Z' else ('S' if ret == 'Ljava/lang/String;' else ('V' if ret == 'V' else ('J' if ret=='J' else ('I' if ret in ('I','B','C','S') else 'A:' + ret))))
                if owner in ('a', 'Midlet') and not (owner == 'Midlet' and name in ('c',) and op == 'invokevirtual' and False):
                    pref = 'M_' if owner == 'Midlet' else ''
                    if owner == 'Midlet' and name == 'c' and op == 'invokevirtual':
                        gdn = 'M_c_'
                    else:
                        gdn = pref + name + '_' + ''.join(tcode_desc(p) for p in ps)
                    A = []
                    for p, x in zip(ps, args):
                        if p == 'Z': A.append(asbool(x))
                        elif p in ('I','B','C','S','J'): A.append(asint(x))
                        elif p == 'Ljava/lang/String;' and x[1] == 'C': A.append('char(%s)' % x[0])
                        else: A.append(x[0])
                    res = '%s(%s)' % (gdn, ', '.join(A))
                    self.mm.add(gdn)
                elif owner == 'java/lang/String':
                    if name == 'equals': res = '(%s == %s)' % (recv[0], args[0][0])
                    elif name == 'length': res = '%s.length()' % recv[0]
                    elif name == 'valueOf': res = asstr(args[0])
                    else: self.errors.append('String.%s' % name); res = 'null'
                elif owner == 'java/lang/StringBuffer':
                    if name == '<init>':
                        # replace NEW placeholder (dup'ed) on stack
                        init = asstr(args[0]) if args else '""'
                        tm = self.newtmp(); code.append('%s = %s' % (tm, init))
                        for i, (e, t) in enumerate(st):
                            if e == recv[0]: st[i] = (tm, 'SB')
                        continue
                    if name == 'append':
                        p = ps[0]
                        av = args[0]
                        if p == 'C': s = 'char(%s)' % asint(av)
                        elif p in ('I','J'): s = 'str(%s)' % asint(av)
                        elif p == 'Z': s = 'str(%s)' % av[0]
                        else: s = asstr(av)
                        tm = self.newtmp(); code.append('%s = %s + %s' % (tm, recv[0], s)); push(tm, 'SB'); continue
                    if name == 'toString': push(recv[0], 'S'); continue
                elif owner == 'java/lang/System' and name == 'currentTimeMillis': res = '_hx_now()'
                elif owner == 'java/lang/System' and name == 'gc': res = None
                elif owner == 'java/lang/Thread':
                    if name == 'yield' or name == 'sleep': res = None
                    elif name == '<init>':
                        for i, (e, t) in enumerate(st):
                            if e == recv[0]: st[i] = ('r_thread', 'THREAD')
                        continue
                    elif name == 'start': code.append('_hx_start_loader()'); continue
                elif owner == 'java/lang/Exception' or name == 'printStackTrace': res = None
                elif owner.startswith('javax/microedition/lcdui/Canvas') and name == 'repaint':
                    code.append('_hx_frame_end()')
                    code.append('r_pc = %d' % self.resume_off)
                    code.append('return_exit')
                    return code, [(self.resume_off, [])]
                elif name == 'serviceRepaints': res = None
                elif owner.endswith('Image') and name == 'createImage': res = '_hx_img(%s)' % args[0][0]
                elif name == 'notifyDestroyed': code.append('_hx_quit()'); continue
                elif name == 'platformRequest': res = 'false'
                elif owner.endswith('Displayable') and name in ('getWidth','getHeight'): res = 'aW' if name == 'getWidth' else 'aX'
                elif owner.endswith('Image') and name in ('getWidth','getHeight'): res = '_hx_i%s(%s)' % ('w' if name == 'getWidth' else 'h', recv[0])
                else:
                    self.errors.append('invoke %s.%s%s' % (owner, name, desc)); res = '_hx_noop()'
                if rt == 'V':
                    if res: code.append(res)
                else:
                    push(res if res else '0', rt)
                continue
            if op == 'nop': continue
            self.errors.append('op %s at %d' % (op, off))
        # fallthrough to next leader
        if nxt_off is not None:
            code.append(exit_to(nxt_off)); code.append('return_continue')
        return code, succ

def emit_dispatch(blocks, keys, ind):
    out = []
    pad = '\t' * ind
    if len(keys) <= 4:
        first = True
        for k in keys:
            out.append(pad + ('if' if first else 'elif') + ' r_pc == %d:' % k); first = False
            for l in blocks[k]:
                if l == 'return_continue': out.append(pad + '\tcontinue')
                elif l == 'return_exit': out.append(pad + '\treturn')
                else: out.append(pad + '\t' + l.replace('    ', '\t'))
        return out
    mid = len(keys) // 2
    out.append(pad + 'if r_pc < %d:' % keys[mid])
    out += emit_dispatch(blocks, keys[:mid], ind+1)
    out.append(pad + 'else:')
    out += emit_dispatch(blocks, keys[mid:], ind+1)
    return out

if __name__ == '__main__':
    ins, exc = parse(sys.argv[1])
    # frame end: invokevirtual Canvas.repaint ; resume after the frame-wait loop at the loop condition
    yield_off = [o for (o, op, a, c) in ins if 'Canvas.repaint' in c][0]
    resume_off = int(sys.argv[3])
    used = set()
    g = Gen(ins, exc, yield_off, resume_off, used)
    blocks = g.gen()
    keys = sorted(blocks)
    lines = ['# AUTO-GENERATED by tools/transpile/bc2gd.py from javap bytecode of a.run(). Do not edit.',
             'extends "res://core/orig.gd"', '',
             'var r_pc = 0', 'var r_thread = null']
    regs = set(re.findall(r'\br_(?:L|s|t)\d+\b', '\n'.join('\n'.join(v) for v in blocks.values())))
    lines += ['var %s = 0' % r for r in sorted(regs)]
    lines += ['', '# Runs one frame of the original game loop (until repaint()).', 'func run_tick():', '\tif r_pc < 0:', '\t\treturn', '\twhile true:']
    lines += emit_dispatch(blocks, keys, 2)
    lines += ['\t\tpush_error("bad r_pc %d" % r_pc)', '\t\treturn', '']
    open(sys.argv[2], 'w', encoding='utf8').write('\n'.join(lines) + '\n')
    print('blocks', len(blocks), 'errors', len(g.errors))
    for e in g.errors[:50]: print(' ', e)
    json.dump(sorted(used), open(sys.argv[2] + '.calls.json', 'w'))
