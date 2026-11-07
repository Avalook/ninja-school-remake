"""Java (CFR output of NinjaSchool1 a.java / Midlet.java) -> GDScript transpiler.

Faithful-logic port: every structured static method becomes a GDScript func on one
state object (`Orig`).  Rendering methods (Graphics param) are skipped; platform
APIs are routed to `_hx_*` helpers implemented in the hand-written host layer.

Conventions
  * class `a` fields keep their names (prefixed with `f_` when they clash with GDScript words)
  * class `Midlet` fields -> `M_<name>`, methods -> `M_<mangled>`
  * methods mangled: name + '_' + signature codes (i=int z=bool s=String I=int[] ...)
  * locals/params -> `_<name>`; all hoisted to function top (Java switch-scope safe)
"""
import sys, re, json
import javalang
from javalang import tree as T

GD_RESERVED = set("""if elif else for while match break continue pass return class class_name extends is in as self signal func static const enum var
breakpoint preload await yield assert void PI TAU INF NAN and or not true false null super get set print str int float bool range len abs min max load
""".split())

class Ctx:
    pass

def tcode(t):
    if t is None: return 'v'
    n = t.name
    d = len(t.dimensions or [])
    base = {'int':'i','boolean':'z','String':'s','byte':'b','char':'c','long':'j','short':'h'}.get(n, 'o')
    return base.upper()*1 + ('A'*(d-1)) if d else base

class Transpiler:
    def __init__(self):
        self.fields = {}      # (cls, name) -> (gdname, typeinfo)
        self.methods = {}     # (cls, name) -> list of (gdname, [param types], ret type, node)
        self.errors = []
        self.skipped_calls = set()
        self.out = []
        self.flagc = 0

    # ---------- type helpers ----------
    @staticmethod
    def ty(t, extra_dims=0):
        if t is None: return ('void', 0)
        return (t.name, len(t.dimensions or []) + extra_dims)

    def fname(self, cls, name):
        if cls == 'a':
            return ('f_'+name) if name in GD_RESERVED else name
        if cls == 'Midlet': return 'MF_'+name
        if cls == 'b': return 'B_'+name
        raise KeyError(cls)

    def collect(self, cls, cu):
        typ = cu.types[0]
        for f in typ.fields:
            for d in f.declarators:
                self.fields[(cls, d.name)] = (self.fname(cls, d.name), self.ty(f.type, len(d.dimensions or [])), d.initializer, f)
        for m in typ.methods:
            sig = ''.join(tcode(p.type) if not p.type.dimensions else (tcode(p.type)) for p in m.parameters)
            gd = ('M_' if cls == 'Midlet' else ('B_' if cls=='b' else '')) + m.name + '_' + sig
            gd = gd.replace('[','').replace(']','')
            ptypes = [self.ty(p.type) for p in m.parameters]
            self.methods.setdefault((cls, m.name), []).append((gd, ptypes, self.ty(m.return_type), m))
        self.ctors = getattr(self, 'ctors', {})
        for c in typ.constructors:
            self.ctors[cls] = c
        self.inits = getattr(self, 'inits', {})
        for b in typ.body:
            if isinstance(b, list):  # static initializer block appears as list? (javalang: Initializer missing) handled below
                pass

    # ---------- emission ----------
    def emit(self, ind, s):
        self.cur.append('    '*ind + s)

    def newflag(self):
        self.flagc += 1
        n = '_j%d' % self.flagc
        self.fn_flags.append(n)
        return n

    def local(self, name):
        n = '_' + name
        self.fn_locals.add(n)
        return n

    # ---------- expressions ----------
    # returns (code, type) ; pre-statements appended to self.pre (list of (code)) — only valid where hoisting is safe
    def expr(self, e, want_bool=False):
        c, t = self._expr(e)
        return c, t

    def is_bool(self, t):
        return t == ('boolean', 0)

    def lit(self, v):
        if v in ('true','false','null'): return v, {'true':('boolean',0),'false':('boolean',0),'null':('null',0)}[v]
        if v.startswith('"'):
            s = v[1:-1]
            s = re.sub(r'\\u([0-9a-fA-F]{4})', lambda m: chr(int(m.group(1),16)), s)
            s = s.replace('\\"','"').replace('\\n','\n').replace('\\t','\t').replace('\\\\','\\')
            return json.dumps(s, ensure_ascii=False), ('String',0)
        if v.startswith("'"):
            s = v[1:-1]
            s = re.sub(r'\\u([0-9a-fA-F]{4})', lambda m: chr(int(m.group(1),16)), s)
            s = {'\\n':'\n','\\t':'\t',"\\'":"'",'\\\\':'\\'}.get(s, s)
            return str(ord(s)), ('char',0)
        vv = v.rstrip('Ll')
        if vv.lower().startswith('0x'): return str(int(vv,16)), ('long' if v[-1] in 'Ll' else 'int',0)
        return vv, ('int',0)

    def lookup_var(self, name, qual=None):
        """returns (gdcode, type) for a simple name or qualified static field"""
        if qual in (None, '', 'this'):
            if name in self.fn_vars and qual is None:
                return self.fn_vars[name]
            key = (self.cls, name)
            if key in self.fields:
                f = self.fields[key]; return f[0], f[1]
            if (('a', name) in self.fields):
                f = self.fields[('a', name)]; return f[0], f[1]
            self.errors.append('unknown name %s in %s' % (name, self.fn_name)); return name, ('?',0)
        if qual in ('a','Midlet','b'):
            if name == 'length' : pass
            key = (qual, name)
            if key in self.fields:
                f = self.fields[key]; return f[0], f[1]
            self.errors.append('unknown field %s.%s' % (qual, name)); return name, ('?',0)
        # qual is a variable: a2.lY (instance), object.length ...
        if qual in self.fn_vars or (self.cls, qual) in self.fields or ('a', qual) in self.fields:
            qc, qt = self.lookup_var(qual)
            if name == 'length': return qc + '.size()', ('int',0)
            if qt[0] in ('a', 'Midlet'):   # instance field through instance ref (a2.lY) -> member
                return self.lookup_var(name, qt[0])
            self.errors.append('field %s on %s' % (name, qual)); return qc+'.'+name, ('?',0)
        if qual == 'Integer' and name == 'MAX_VALUE': return '2147483647', ('int',0)
        self.errors.append('unknown qual %s.%s' % (qual, name)); return qual+'.'+name, ('?',0)

    def apply_selectors(self, code, t, sels):
        for s in sels or []:
            if isinstance(s, T.ArraySelector):
                ic, it = self._expr(s.index)
                code = '%s[%s]' % (code, ic); t = (t[0], max(0, t[1]-1))
            elif isinstance(s, T.MemberReference):
                if s.member == 'length': code = code + '.size()'; t = ('int',0)
                else: self.errors.append('member sel %s' % s.member); code += '.'+s.member
            elif isinstance(s, T.MethodInvocation):
                code, t = self.call_on(code, t, s)
            else:
                self.errors.append('selector %s' % type(s).__name__)
        return code, t

    def call_on(self, code, t, m):
        args = [self._expr(a) for a in m.arguments]
        nm = m.member
        A = [a[0] for a in args]
        if nm == 'getClass': return '_hx_noop()', ('Class',0)
        if t[0] == 'String' and t[1] == 0 or t[0]=='StringBuffer':
            if nm == 'length': return code+'.length()', ('int',0)
            if nm == 'charAt': return '%s.unicode_at(%s)' % (code, A[0]), ('char',0)
            if nm == 'equals': return '(%s == %s)' % (code, A[0]), ('boolean',0)
            if nm == 'substring':
                if len(A) == 1: return '%s.substr(%s)' % (code, A[0]), ('String',0)
                return '%s.substr(%s, (%s) - (%s))' % (code, A[0], A[1], A[0]), ('String',0)
            if nm == 'indexOf':
                if len(A)==1: return '%s.find(%s)' % (code, self.as_str(args[0])), ('int',0)
                return '%s.find(%s, %s)' % (code, self.as_str(args[0]), A[1]), ('int',0)
            if nm == 'lastIndexOf': return '%s.rfind(%s)' % (code, self.as_str(args[0])), ('int',0)
            if nm == 'trim': return code+'.strip_edges()', ('String',0)
            if nm == 'toString': return code, ('String',0)
            if nm == 'endsWith': return '%s.ends_with(%s)' % (code, A[0]), ('boolean',0)
            if nm == 'startsWith': return '%s.begins_with(%s)' % (code, A[0]), ('boolean',0)
            if nm == 'getBytes': return '%s.to_utf8_buffer()' % code, ('byte',1)
            if nm == 'toUpperCase': return code+'.to_upper()', ('String',0)
            if nm == 'toLowerCase': return code+'.to_lower()', ('String',0)
        if t[0] == 'Vector':
            if nm == 'addElement': return '%s.append(%s)' % (code, A[0]), ('void',0)
            if nm == 'size': return '%s.size()' % code, ('int',0)
            if nm == 'elementAt': return '%s[%s]' % (code, A[0]), ('String',0)
        if t[0] == 'Object' and nm == 'equals': return '(%s == %s)' % (code, A[0]), ('boolean',0)
        if t[0] == 'Image':
            if nm == 'getWidth': return '_hx_iw(%s)' % code, ('int',0)
            if nm == 'getHeight': return '_hx_ih(%s)' % code, ('int',0)
        if t[0] == 'Font':
            if nm == 'getHeight': return '_hx_fh(%s)' % code, ('int',0)
            if nm == 'stringWidth': return '_hx_sw(%s, %s)' % (code, A[0]), ('int',0)
            if nm == 'charWidth': return '_hx_sw(%s, char(%s))' % (code, A[0]), ('int',0)
        if t[0] == 'InputStream':
            if nm == 'read': return '_hx_read(%s)' % code, ('int',0)
            if nm == 'close': return '_hx_noop()', ('void',0)
            if nm == 'available': return '_hx_avail(%s)' % code, ('int',0)
        if t[0] == 'RecordStore':
            return '_hx_rs_%s(%s)' % (nm, ', '.join([code]+A)), ({'getNumRecords':('int',0),'getRecord':('byte',1),'addRecord':('int',0)}.get(nm, ('void',0)))
        if t[0] == 'LoaderThread' and nm == 'start': return '_hx_start_loader()', ('void',0)
        if t[0] in ('Player','VolumeControl','Control','Thread','Command','TextBox','Display','Displayable','MessageConnection','TextMessage','Graphics'):
            self.skipped_calls.add('%s.%s' % (t[0], nm))
            if nm == 'getString': return '_hx_textbox()', ('String',0)
            if nm == 'getLabel': return '_hx_cmdlabel(%s)' % code, ('String',0)
            return '_hx_noop()', ('void',0)
        if t[0] in ('Object','?') and nm == 'getClass':
            return '_hx_noop()', ('Class',0)
        if t[0] == 'Class' and nm == 'getResourceAsStream':
            return '_hx_res(%s)' % A[0], ('InputStream',0)
        if t[0] in ('a','Midlet'):
            return self.call_static(t[0], m, args)
        self.errors.append('call %s on %s (%s)' % (nm, code, t)); return '%s.%s(%s)' % (code, nm, ', '.join(A)), ('?',0)

    def as_str(self, a):
        c, t = a
        if t == ('String',0): return c
        if t == ('char',0): return 'char(%s)' % c
        return 'str(%s)' % c

    def resolve(self, cls, name, args):
        cands = self.methods.get((cls, name), [])
        cands = [c for c in cands if len(c[1]) == len(args)]
        if not cands: return None
        if len(cands) == 1: return cands[0]
        def score(c):
            s = 0
            for (pt, (_, at)) in zip(c[1], args):
                if pt == at: s += 2
                elif pt[1]==at[1] and pt[0] in ('int','byte','char','short','long') and at[0] in ('int','byte','char','short','long'): s += 1
                elif at[0] in ('?','null'): s += 0
                else: s -= 5
            return s
        cands.sort(key=score, reverse=True)
        return cands[0]

    def call_static(self, cls, m, args=None):
        if args is None: args = [self._expr(a) for a in m.arguments]
        r = self.resolve(cls, m.member, args)
        if r is None:
            self.errors.append('unresolved %s.%s/%d in %s' % (cls, m.member, len(args), self.fn_name)); return '%s(%s)' % (m.member, ', '.join(a[0] for a in args)), ('?',0)
        gd, pts, rt, node = r
        if gd in self.skip_methods and gd not in self.host_methods:
            self.skipped_calls.add(gd)
            return '_hx_skip(%s)' % json.dumps(gd), rt
        A = []
        for (pt, (c, at)) in zip(pts, args):
            if pt == ('String',0) and at == ('char',0): c = 'char(%s)' % c
            A.append(c)
        return '%s(%s)' % (gd, ', '.join(A)), rt

    def call_ext(self, qual, m):
        args = [self._expr(a) for a in m.arguments]; A = [a[0] for a in args]; nm = m.member
        if qual == 'String' and nm == 'valueOf':
            return self.as_str(args[0]), ('String',0)
        if qual == 'Integer' and nm == 'parseInt': return 'int(%s)' % A[0], ('int',0)
        if qual == 'Integer' and nm == 'toString': return 'str(%s)' % A[0], ('String',0)
        if qual == 'Math' and nm == 'abs': return 'absi(%s)' % A[0], ('int',0)
        if qual == 'Math' and nm in ('max','min'): return '%si(%s, %s)' % (nm, A[0], A[1]), ('int',0)
        if qual == 'System' and nm == 'currentTimeMillis': return '_hx_now()', ('long',0)
        if qual == 'System.out' and nm == 'println': return '_hx_noop()', ('void',0)
        if qual == 'System' and nm == 'gc': return '_hx_noop()', ('void',0)
        if qual == 'System' and nm == 'getProperty': return '_hx_prop(%s)' % A[0], ('String',0)
        if qual == 'System' and nm == 'arraycopy': return '_hx_arraycopy(%s)' % ', '.join(A), ('void',0)
        if qual == 'Thread' and nm in ('sleep','yield'): return '_hx_noop()', ('void',0)
        if qual == 'Image' and nm == 'createImage': return '_hx_img(%s)' % ', '.join(A), ('Image',0)
        if qual == 'Font' and nm == 'getFont': return '_hx_font(%s)' % ', '.join(A), ('Font',0)
        if qual == 'RecordStore': return '_hx_rs_%s(%s)' % (nm, ', '.join(A)), ('RecordStore',0)
        if qual == 'Manager': return '_hx_player(%s)' % ', '.join(A), ('Player',0)
        if qual == 'Display': return '_hx_noop()', ('Display',0)
        if qual == 'LocalDevice': return '_hx_noop()', ('?',0)
        if qual == 'Connector': return '_hx_noop()', ('?',0)
        self.errors.append('ext call %s.%s' % (qual, nm)); return '%s.%s(%s)' % (qual, nm, ', '.join(A)), ('?',0)

    def _expr(self, e):
        """returns (code, type). may append to self.pre for hoisted side effects"""
        if isinstance(e, T.Literal):
            c, t = self.lit(e.value)
            c, t = self.apply_selectors(c, t, e.selectors)
            return self.post(e, c, t)
        if isinstance(e, T.MemberReference):
            if e.qualifier and e.qualifier not in ('a','Midlet','b','this') and e.qualifier not in self.fn_vars and ('a',e.qualifier) not in self.fields and (self.cls, e.qualifier) not in self.fields:
                # e.g. Canvas.UP constants
                consts = {'Canvas.UP':'1','Canvas.DOWN':'6','Canvas.LEFT':'2','Canvas.RIGHT':'5','Canvas.FIRE':'8'}
                k = '%s.%s' % (e.qualifier, e.member)
                if k in consts: return consts[k], ('int',0)
            code, t = self.lookup_var(e.member, e.qualifier if e.qualifier else None)
            code, t = self.apply_selectors(code, t, e.selectors)
            return self.prepost(e, code, t)
        if isinstance(e, T.MethodInvocation):
            q = e.qualifier
            if q in (None, '', 'this'):
                cls = self.cls
                if not self.methods.get((cls, e.member)) and self.methods.get(('a', e.member)): cls = 'a'
                code, t = self.call_static(cls, e)
            elif q in ('a', 'Midlet', 'b'):
                code, t = self.call_static(q, e)
            elif q in self.fn_vars or ('a', q) in self.fields or (self.cls, q) in self.fields:
                qc, qt = self.lookup_var(q)
                code, t = self.call_on(qc, qt, e)
            elif q.startswith('Midlet.') or q.startswith('a.'):
                qq, f = q.split('.', 1)
                qc, qt = self.lookup_var(f, qq)
                code, t = self.call_on(qc, qt, e)
            else:
                code, t = self.call_ext(q, e)
            code, t = self.apply_selectors(code, t, e.selectors)
            return self.prepost(e, code, t)
        if isinstance(e, T.BinaryOperation):
            l = self._expr(e.operandl); op = e.operator
            if op in ('&&', '||'):
                # side effects in rhs must not be hoisted
                save = self.pre; self.pre = []
                r = self._expr(e.operandr)
                if self.pre:
                    rpre = self.pre
                    self.pre = save
                    tmp = self.local('t%d' % self.newtmp())
                    self.pre.append('%s = %s' % (tmp, l[0]))
                    self.pre.append(('if %s%s:' % ('' if op == '&&' else 'not ', tmp), rpre + ['%s = %s' % (tmp, r[0])], []))
                    return tmp, ('boolean',0)
                else:
                    self.pre = save
                return '(%s %s %s)' % (l[0], 'and' if op == '&&' else 'or', r[0]), ('boolean',0)
            r = self._expr(e.operandr)
            if op == '+' and (l[1] == ('String',0) or r[1] == ('String',0)):
                return '(%s + %s)' % (self.as_str(l), self.as_str(r)), ('String',0)
            if op in ('&','|','^') and (self.is_bool(l[1]) or self.is_bool(r[1])):
                gop = {'&':'and','|':'or','^':'!='}[op]
                return '(%s %s %s)' % (l[0], gop, r[0]), ('boolean',0)
            if op == '>>>':
                return '_hx_ushr(%s, %s)' % (l[0], r[0]), ('int',0)
            if op in ('==','!=','<','>','<=','>='):
                return '(%s %s %s)' % (l[0], op, r[0]), ('boolean',0)
            if op == 'instanceof':
                return 'true', ('boolean',0)
            if op in ('/',):
                return '_hx_div(%s, %s)' % (l[0], r[0]), ('int',0)
            if op in ('%',):
                return '_hx_mod(%s, %s)' % (l[0], r[0]), ('int',0)
            rt = ('long',0) if 'long' in (l[1][0], r[1][0]) else ('int',0)
            code = '(%s %s %s)' % (l[0], op, r[0])
            if op in ('*','<<','+','-') and rt == ('int',0):
                pass  # 32-bit overflow ignored (checked: tick counters wrap explicitly)
            return code, rt
        if isinstance(e, T.TernaryExpression):
            c = self._expr(e.condition)
            save = self.pre; self.pre = []
            a = self._expr(e.if_true); pa = self.pre; self.pre = []
            b = self._expr(e.if_false); pb = self.pre; self.pre = save
            if pa or pb:
                # hoist via temp: only safe if this ternary itself is in hoistable position
                tmp = self.local('t%d' % self.newtmp())
                self.pre.append(('if %s:' % c[0], pa + ['%s = %s' % (tmp, a[0])], pb + ['%s = %s' % (tmp, b[0])]))
                return tmp, a[1] if a[1][0] != 'null' else b[1]
            t = a[1] if a[1][0] not in ('null','?') else b[1]
            if t == ('String',0) and (a[1] == ('char',0) or b[1] == ('char',0)):
                a = (self.as_str(a), t); b = (self.as_str(b), t)
            return '(%s if %s else %s)' % (a[0], c[0], b[0]), t
        if isinstance(e, T.Assignment):
            return self.assign(e, as_expr=True)
        if isinstance(e, T.Cast):
            c, t = self._expr(e.expression)
            tn = e.type.name; dims = len(e.type.dimensions or [])
            if dims: return c, (tn, dims)
            if tn == 'byte': return '_hx_byte(%s)' % c, ('byte',0)
            if tn == 'char': return '(%s & 65535)' % c if t != ('char',0) else c, ('char',0)
            if tn == 'short': return '_hx_short(%s)' % c, ('short',0)
            if tn == 'int': return ('_hx_int(%s)' % c if t[0]=='long' else c), ('int',0)
            if tn == 'long': return c, ('long',0)
            return c, (tn, 0)
        if isinstance(e, T.ArrayCreator):
            if e.initializer is not None:
                return self.arrinit(e.initializer, (e.type.name, len(e.dimensions))), (e.type.name, len(e.dimensions))
            dims = [self._expr(d)[0] if d is not None else None for d in e.dimensions]
            base = e.type.name
            fill = {'int':'0','byte':'0','char':'0','short':'0','long':'0','boolean':'false'}.get(base, 'null')
            sized = [d for d in dims if d is not None]
            if len(sized) == len(dims):
                return '_hx_newarr([%s], %s)' % (', '.join(sized), fill), (base, len(dims))
            return '_hx_newarr([%s], null)' % ', '.join(sized), (base, len(dims))
        if isinstance(e, T.ArrayInitializer):
            return self.arrinit(e, ('?',1)), ('?',1)
        if isinstance(e, T.ClassCreator):
            n = e.type.name
            if n == 'StringBuffer':
                return '""', ('StringBuffer',0)
            if n == 'Thread':
                a0 = e.arguments[0] if e.arguments else None
                tt = ('LoaderThread',0) if (isinstance(a0, T.MemberReference) and a0.qualifier == 'Midlet' and a0.member == 'c') else ('Thread',0)
                return self.apply_selectors('_hx_noop()', tt, e.selectors)
            if n == 'String':
                args = [self._expr(a) for a in e.arguments]
                return '_hx_newstring(%s)' % ', '.join(a[0] for a in args), ('String',0)
            if n == 'Vector': return '[]', ('Vector',0)
            if n in ('Command','TextBox'): return '_hx_obj(%s)' % json.dumps(n), (n,0)
            if n in ('a','Midlet'): return '_hx_self()', (n,0)
            self.errors.append('new %s' % n); return '_hx_obj(%s)' % json.dumps(n), (n,0)
        if isinstance(e, T.This):
            code = '_hx_self()'; t = (self.cls, 0)
            if e.selectors:
                # this.method() or this.field
                s0 = e.selectors[0]
                if isinstance(s0, T.MethodInvocation):
                    code, t = self.call_static(self.cls, s0)
                    return self.apply_selectors(code, t, e.selectors[1:])
                if isinstance(s0, T.MemberReference):
                    code, t = self.lookup_var(s0.member, self.cls)
                    code, t = self.apply_selectors(code, t, e.selectors[1:])
                    return self.prepost(e, code, t)
            return code, t
        if isinstance(e, T.LambdaExpression) or isinstance(e, T.MethodReference):
            self.errors.append('lambda'); return 'null', ('?',0)
        if isinstance(e, T.ClassReference):
            return '_hx_noop()', ('Class', 0)
        self.errors.append('expr %s' % type(e).__name__)
        return '???', ('?',0)

    _tmpc = 0
    def newtmp(self):
        Transpiler._tmpc += 1; return Transpiler._tmpc

    def arrinit(self, init, t):
        parts = []
        for x in init.initializers:
            if isinstance(x, T.ArrayInitializer): parts.append(self.arrinit(x, (t[0], t[1]-1)))
            else: parts.append(self._expr(x)[0])
        return '[%s]' % ', '.join(parts)

    def post(self, e, c, t):
        return self.prepost(e, c, t)

    def prepost(self, e, code, t):
        pre = list(getattr(e, 'prefix_operators', None) or [])
        postf = list(getattr(e, 'postfix_operators', None) or [])
        # ++/-- prefix: hoist increment then use value
        for op in postf:
            if op in ('++','--'):
                tmp = self.local('t%d' % self.newtmp())
                self.pre.append('%s = %s' % (tmp, code))
                self.pre.append('%s %s= 1' % (code, op[0]))
                code = tmp
        for op in reversed(pre):
            if op in ('++','--'):
                self.pre.append('%s %s= 1' % (code, op[0]))
            elif op == '!':
                code = '(not %s)' % code; t = ('boolean',0)
            elif op == '-':
                code = '(-%s)' % code
            elif op == '~':
                code = '(~%s)' % code
            elif op == '+':
                pass
        return code, t

    def assign(self, e, as_expr=False):
        lhs = e.expressionl
        lc, lt = self._expr(lhs)
        rc, rt = self._expr(e.value)
        op = e.type
        if op == '=':
            if lt == ('String',0) and rt == ('char',0): rc = 'char(%s)' % rc
            stmt = '%s = %s' % (lc, rc)
        else:
            bop = op[:-1]
            if bop == '+' and lt[0] in ('String','StringBuffer'):
                stmt = '%s += %s' % (lc, self.as_str((rc, rt)))
            elif bop in ('&','|','^') and self.is_bool(lt):
                stmt = '%s = (%s %s %s)' % (lc, lc, {'&':'and','|':'or','^':'!='}[bop], rc)
            elif bop == '>>>':
                stmt = '%s = _hx_ushr(%s, %s)' % (lc, lc, rc)
            elif bop == '/':
                stmt = '%s = _hx_div(%s, %s)' % (lc, lc, rc)
            elif bop == '%':
                stmt = '%s = _hx_mod(%s, %s)' % (lc, lc, rc)
            else:
                stmt = '%s %s %s' % (lc, op, rc)
            if lt[0] == 'byte':
                stmt = '%s = _hx_byte(%s %s (%s))' % (lc, lc, bop, rc) if bop not in ('>>>','/','%') else stmt
        if as_expr:
            self.pre.append(stmt)
            return lc, lt
        return stmt

    # ---------- statements ----------
    def flush_pre(self, ind):
        self._flush(self.pre, ind); self.pre = []

    def _flush(self, items, ind):
        for p in items:
            if isinstance(p, tuple):
                cond, a, b = p
                self.emit(ind, cond)
                if a: self._flush(a, ind+1)
                else: self.emit(ind+1, 'pass')
                if b:
                    self.emit(ind, 'else:'); self._flush(b, ind+1)
            else:
                self.emit(ind, p)

    def cond(self, e, ind):
        """evaluate condition, flush hoisted pre at ind, return code"""
        self.pre = []
        c, t = self._expr(e)
        self.flush_pre(ind)
        return c

    def block(self, stmts, ind):
        n0 = len(self.cur)
        for s in stmts or []:
            self.stmt(s, ind)
        if len(self.cur) == n0:
            self.emit(ind, 'pass')

    def body_list(self, s):
        if s is None: return []
        if isinstance(s, T.BlockStatement) and not s.label: return s.statements or []
        return [s]

    # context stack: entries dict(kind=loop|switch|block, label, gd=bool (is GD loop), cont_update=list[str], id)
    def find_target(self, label, is_continue):
        for i in range(len(self.ctx)-1, -1, -1):
            c = self.ctx[i]
            if label:
                if c['label'] == label: return i
            else:
                if is_continue and c['kind'] == 'loop': return i
                if not is_continue and c['kind'] in ('loop', 'switch'): return i
        self.errors.append('jump target not found %s' % label); return len(self.ctx)-1

    def jump(self, label, is_continue, ind):
        ti = self.find_target(label, is_continue)
        tgt = self.ctx[ti]
        crossed = [c for c in self.ctx[ti+1:] if c['gd']]
        if not crossed:
            if is_continue:
                for u in tgt.get('update', []): self.emit(ind, u)
                if tgt.get('dowhile'):
                    self.emit(ind, 'if not (%s): break' % tgt['dowhile'])
                self.emit(ind, 'continue')
            else:
                if tgt['gd']:
                    self.emit(ind, 'break')
                else:
                    # switch as if-chain: break must jump to end of switch -> should have been made gd
                    self.errors.append('break to non-gd switch in %s' % self.fn_name)
                    self.emit(ind, 'pass # BREAK?')
            return
        f = self.newflag()
        self.emit(ind, '%s = true' % f)
        self.emit(ind, 'break')
        # register pending exit on each crossed gd context (innermost first)
        crossed[-1].setdefault('exits', []).append((f, ti, is_continue))

    def after_gd(self, c, ind):
        """emit checks after a gd loop/wrapper ended, for exits that pass through it"""
        for (f, ti, is_cont) in c.get('exits', []):
            # find next gd context outward between c and target
            ci = self.ctx.index(c) if c in self.ctx else None
            # c already popped: compute based on stored index
            idx = c['index']
            outer_gd = [x for x in self.ctx[ti+1:idx] if x['gd']]
            tgt = self.ctx[ti]
            if outer_gd:
                self.emit(ind, 'if %s: break' % f)
                outer_gd[-1].setdefault('exits', []).append((f, ti, is_cont))
            else:
                if is_cont:
                    self.emit(ind, 'if %s:' % f)
                    self.emit(ind+1, '%s = false' % f)
                    for u in tgt.get('update', []): self.emit(ind+1, u)
                    if tgt.get('dowhile'):
                        self.emit(ind+1, 'if not (%s): break' % tgt['dowhile'])
                    self.emit(ind+1, 'continue')
                else:
                    if tgt['gd']:
                        self.emit(ind, 'if %s:' % f)
                        self.emit(ind+1, 'break')
                        tgt.setdefault('resets', []).append(f)
                    else:
                        self.errors.append('flag break to non-gd target')

    def push(self, **kw):
        kw['index'] = len(self.ctx)
        self.ctx.append(kw); return kw

    def pop_gd(self, c, ind_after):
        self.ctx.pop()
        if c['gd']:
            self.after_gd(c, ind_after)
            for f in c.get('resets', []):
                self.emit(ind_after, '%s = false' % f)

    def stmt(self, s, ind):
        label = getattr(s, 'label', None)
        if isinstance(s, T.LocalVariableDeclaration) or isinstance(s, T.VariableDeclaration):
            for d in s.declarators:
                n = self.local(d.name)
                self.fn_vars[d.name] = (n, self.ty(s.type, len(d.dimensions or [])))
                if d.initializer is not None:
                    self.pre = []
                    if isinstance(d.initializer, T.ArrayInitializer):
                        c = self.arrinit(d.initializer, self.fn_vars[d.name][1])
                    else:
                        c, t = self._expr(d.initializer)
                        if self.fn_vars[d.name][1] == ('String',0) and t == ('char',0): c = 'char(%s)' % c
                    self.flush_pre(ind)
                    self.emit(ind, '%s = %s' % (n, c))
            return
        if isinstance(s, T.StatementExpression):
            e = s.expression
            self.pre = []
            if isinstance(e, T.Assignment) and isinstance(e.expressionl, T.ArrayCreator):
                return  # dead store present in original bytecode (e.g. clinit `(new String[1])[0] = ...`)
            if isinstance(e, T.Assignment):
                st = self.assign(e)
                self.flush_pre(ind); self.emit(ind, st); return
            # pure ++/-- statement
            pre = list(getattr(e, 'prefix_operators', None) or []); postf = list(getattr(e, 'postfix_operators', None) or [])
            if isinstance(e, T.MemberReference) and (('++' in pre or '--' in pre or '++' in postf or '--' in postf)):
                op = '+' if ('++' in pre or '++' in postf) else '-'
                e.prefix_operators = [x for x in pre if x not in ('++','--')]; e.postfix_operators = []
                c, t = self._expr(e)
                self.flush_pre(ind); self.emit(ind, '%s %s= 1' % (c, op)); return
            # StringBuffer append as statement
            if isinstance(e, T.MethodInvocation) and e.member == 'append':
                q = e.qualifier
                qc, qt = self.lookup_var(q) if q in self.fn_vars else (None, None)
                if qc:
                    a = self._expr(e.arguments[0]); self.flush_pre(ind); self.emit(ind, '%s += %s' % (qc, self.as_str(a))); return
            if isinstance(e, T.Cast) and isinstance(e.expression, T.MethodInvocation) and e.expression.member=='append':
                ex = e.expression
                # ((StringBuffer)object).append(x)  qualifier is in cast
                pass
            c, t = self._expr(e)
            self.flush_pre(ind)
            if c not in ('_hx_noop()',) and not c.startswith('_t'):
                self.emit(ind, c)
            return
        if isinstance(s, T.IfStatement):
            c = self.cond(s.condition, ind)
            self.emit(ind, 'if %s:' % c)
            self.block(self.body_list(s.then_statement), ind+1)
            el = s.else_statement
            while el is not None:
                if isinstance(el, T.IfStatement) and not getattr(el, 'label', None):
                    # elif only if condition has no pre
                    self.pre = []
                    save = self.cur; self.cur = []
                    cc, _ = self._expr(el.condition)
                    pre = self.pre; self.cur = save
                    if pre:
                        self.emit(ind, 'else:')
                        self.stmt(el, ind+1)
                        break
                    self.pre = []
                    self.emit(ind, 'elif %s:' % cc)
                    self.block(self.body_list(el.then_statement), ind+1)
                    el = el.else_statement
                else:
                    self.emit(ind, 'else:')
                    self.block(self.body_list(el), ind+1)
                    break
            return
        if isinstance(s, T.WhileStatement):
            self.pre = []
            save = self.cur; self.cur = []
            c, _ = self._expr(s.condition); pre = self.pre; self.cur = save; self.pre = []
            ctx = self.push(kind='loop', label=label, gd=True)
            if not pre and c != 'true':
                self.emit(ind, 'while %s:' % c)
            else:
                self.emit(ind, 'while true:')
                if c != 'true':
                    self.pre = []
                    cc = self.cond(s.condition, ind+1)
                    self.emit(ind+1, 'if not (%s): break' % cc)
            self.block(self.body_list(s.body), ind+1)
            self.pop_gd(ctx, ind)
            return
        if isinstance(s, T.DoStatement):
            ctx = self.push(kind='loop', label=label, gd=True)
            self.pre = []; save = self.cur; self.cur = []
            cc, _ = self._expr(s.condition); pre = self.pre; self.cur = save; self.pre = []
            if pre: self.errors.append('do-while cond side effect')
            ctx['dowhile'] = cc
            self.emit(ind, 'while true:')
            self.block(self.body_list(s.body), ind+1)
            self.emit(ind+1, 'if not (%s): break' % cc)
            self.pop_gd(ctx, ind)
            return
        if isinstance(s, T.ForStatement):
            ctl = s.control
            if isinstance(ctl, T.EnhancedForControl):
                self.errors.append('foreach'); return
            if ctl.init:
                inits = ctl.init if isinstance(ctl.init, list) else [ctl.init]
                for i in inits:
                    if isinstance(i, (T.VariableDeclaration, T.LocalVariableDeclaration)): self.stmt(i, ind)
                    else: self.stmt(T.StatementExpression(expression=i), ind)
            upd = []
            if ctl.update:
                save = self.cur; self.cur = []
                for u in ctl.update: self.stmt(T.StatementExpression(expression=u), 0)
                upd = [x.strip() for x in self.cur]; self.cur = save
            ctx = self.push(kind='loop', label=label, gd=True, update=upd)
            if ctl.condition is not None:
                self.pre = []; save = self.cur; self.cur = []
                c, _ = self._expr(ctl.condition); pre = self.pre; self.cur = save; self.pre = []
                if pre:
                    self.emit(ind, 'while true:'); cc = self.cond(ctl.condition, ind+1); self.emit(ind+1, 'if not (%s): break' % cc)
                else:
                    self.emit(ind, 'while %s:' % c)
            else:
                self.emit(ind, 'while true:')
            self.block(self.body_list(s.body), ind+1)
            for u in upd: self.emit(ind+1, u)
            self.pop_gd(ctx, ind)
            return
        if isinstance(s, T.BlockStatement):
            if label:
                ctx = self.push(kind='block', label=label, gd=True)
                self.emit(ind, 'while true: # %s' % label)
                self.block(s.statements, ind+1)
                self.emit(ind+1, 'break')
                self.pop_gd(ctx, ind)
            else:
                for x in s.statements or []: self.stmt(x, ind)
            return
        if isinstance(s, T.SwitchStatement):
            self.switch(s, ind, label); return
        if isinstance(s, T.BreakStatement):
            self.jump(s.goto, False, ind); return
        if isinstance(s, T.ContinueStatement):
            self.jump(s.goto, True, ind); return
        if isinstance(s, T.ReturnStatement):
            if s.expression is None: self.emit(ind, 'return'); return
            self.pre = []
            c, t = self._expr(s.expression)
            if self.fn_ret == ('String',0) and t == ('char',0): c = 'char(%s)' % c
            self.flush_pre(ind); self.emit(ind, 'return %s' % c); return
        if isinstance(s, T.TryStatement):
            # no exceptions in GDScript: run try body, then finally
            if s.resources: self.errors.append('try-with-resources')
            self.block(s.block, ind)
            if s.finally_block: self.block(s.finally_block, ind)
            return
        if isinstance(s, T.ThrowStatement):
            self.emit(ind, 'push_error("throw in %s")' % self.fn_name); self.emit(ind, 'return' + ('' if self.fn_ret[0]=='void' else ' ' + self.default_ret())); return
        if isinstance(s, T.SynchronizedStatement):
            self.block(s.block, ind); return
        if isinstance(s, T.Statement) and type(s) is T.Statement:
            return
        if isinstance(s, T.AssertStatement): return
        self.errors.append('stmt %s' % type(s).__name__)
        self.emit(ind, 'pass # UNSUPPORTED %s' % type(s).__name__)

    def default_ret(self):
        t = self.fn_ret
        if t[1] > 0 or t[0] not in ('int','boolean','byte','char','long','short'): return 'null'
        return 'false' if t[0]=='boolean' else '0'

    @staticmethod
    def terminates(stmts):
        if not stmts: return False
        last = stmts[-1]
        if isinstance(last, (T.BreakStatement, T.ReturnStatement, T.ContinueStatement, T.ThrowStatement)): return True
        if isinstance(last, T.BlockStatement) and not last.label: return Transpiler.terminates(last.statements)
        if isinstance(last, T.IfStatement) and last.else_statement is not None:
            a = Transpiler.terminates(Transpiler.blist(last.then_statement)); b = Transpiler.terminates(Transpiler.blist(last.else_statement))
            return a and b
        return False

    @staticmethod
    def blist(s):
        if isinstance(s, T.BlockStatement) and not s.label: return s.statements or []
        return [s]

    def contains_break_to(self, stmts, label, depth=0):
        """does stmts contain a break targeting this switch (unlabeled break not nested in loop/switch, or labeled to label)"""
        found = False
        def walk(node, inner):
            nonlocal found
            if isinstance(node, T.BreakStatement):
                if (node.goto is None and not inner) or (label and node.goto == label): found = True
                return
            if isinstance(node, (T.WhileStatement, T.DoStatement, T.ForStatement, T.SwitchStatement)):
                inner2 = True
            else:
                inner2 = inner
            for ch in node.children if hasattr(node, 'children') else []:
                if isinstance(ch, list):
                    for x in ch:
                        if isinstance(x, T.Node): walk(x, inner2)
                elif isinstance(ch, T.Node): walk(ch, inner2)
        for s in stmts: walk(s, False)
        return found

    def switch(self, s, ind, label):
        self.pre = []
        ec, et = self._expr(s.expression)
        self.flush_pre(ind)
        sv = self.local('sw%d' % self.newtmp())
        self.emit(ind, '%s = %s' % (sv, ec))
        cases = s.cases
        # strip trailing breaks, detect fallthrough
        groups = []
        for c in cases:
            labels = c.case  # list of exprs or 'default'
            stmts = list(c.statements or [])
            groups.append([labels, stmts])
        need_wrap = False; fall = False
        proc = []
        for gi, (labels, stmts) in enumerate(groups):
            st = list(stmts)
            # flatten single block
            if len(st) == 1 and isinstance(st[0], T.BlockStatement) and not st[0].label: st = list(st[0].statements or [])
            ends = False
            if st and isinstance(st[-1], T.BreakStatement) and st[-1].goto is None:
                st = st[:-1]; ends = True
            elif self.terminates(st):
                ends = True
            if not st and not ends and gi < len(groups)-1:
                pass  # empty: merges with next (javalang already groups labels; this is a real fallthrough of empty body)
            if not ends and gi < len(groups)-1: fall = True
            if self.contains_break_to(st, label): need_wrap = True
            proc.append((labels, st, ends))
        vals = []
        for labels, st, ends in proc:
            for l in labels:
                if l != 'default' and not (isinstance(l, str)): vals.append(self._expr(l)[0])
        def condfor(labels):
            if not labels: labels = ['default']
            parts = []
            for l in labels:
                if isinstance(l, str) and l == 'default' or l is None:
                    parts.append('not (%s in [%s])' % (sv, ', '.join(vals)))
                else:
                    parts.append('%s == %s' % (sv, self._expr(l)[0]))
            return ' or '.join(parts) if parts else 'false'
        if not need_wrap and not fall:
            ctx = self.push(kind='switch', label=label, gd=False)
            first = True
            default_body = None
            for labels, st, ends in proc:
                if (not labels) or (any((isinstance(l, str) and l == 'default') for l in labels) and len(labels) == 1):
                    default_body = st; continue
                self.emit(ind, ('if %s:' if first else 'elif %s:') % condfor(labels)); first = False
                self.block(st, ind+1)
            if default_body is not None:
                if first:
                    for x in default_body: self.stmt(x, ind)
                else:
                    self.emit(ind, 'else:'); self.block(default_body, ind+1)
            self.ctx.pop()
            return
        # general: wrapper loop with fallthrough flag
        ff = self.local('ft%d' % self.newtmp())
        self.emit(ind, '%s = false' % ff)
        ctx = self.push(kind='switch', label=label, gd=True)
        self.emit(ind, 'while true: # switch')
        for labels, st, ends in proc:
            self.emit(ind+1, 'if %s or %s:' % (ff, condfor(labels)))
            self.emit(ind+2, '%s = true' % ff)
            for x in st: self.stmt(x, ind+2)
            if ends: self.emit(ind+2, 'break')
        self.emit(ind+1, 'break')
        self.pop_gd(ctx, ind)

    # ---------- methods ----------
    def method(self, cls, gd, node, params, ret, body, is_ctor=False):
        self.cls = cls; self.fn_name = gd; self.fn_vars = {}; self.fn_locals = set(); self.fn_flags = []; self.ctx = []; self.pre = []; self.fn_ret = ret
        plist = []
        for p in params:
            n = '_' + p.name
            self.fn_vars[p.name] = (n, self.ty(p.type))
            plist.append(n)
        self.cur = []
        self.block(body, 1)
        bodylines = self.cur
        head = ['func %s(%s):' % (gd, ', '.join(plist))]
        decl = ['    var %s' % v for v in sorted(self.fn_locals - set(plist))] + ['    var %s = false' % f for f in self.fn_flags]
        # functions with return value need trailing default return to satisfy GDScript
        tail = []
        if ret[0] != 'void':
            tail = ['    return ' + self.default_ret()]
        self.out.extend(head + decl + bodylines + tail + [''])

    def run(self, files, skip_graphics=True, skip_names=()):
        cus = {}
        for cls, path in files:
            src = open(path, encoding='utf8').read()
            cu = javalang.parse.parse(src)
            cus[cls] = cu
            self.collect(cls, cu)
        # decide skipped methods: those with Graphics parameters + explicit
        self.skip_methods = set(x for x in skip_names if x.startswith('_ctor_'))
        self.host_methods = set(skip_names)
        for (cls, name), lst in self.methods.items():
            for gd, pts, rt, node in lst:
                if any(p[0] == 'Graphics' for p in pts) or gd in skip_names:
                    self.skip_methods.add(gd)
        # header + fields
        self.out.append('# AUTO-GENERATED by tools/transpile/j2gd.py from CFR sources. Do not edit; edit host/overrides instead.')
        self.out.append('extends "res://core/orig_host.gd"')
        self.out.append('')
        for (cls, name), (gd, t, init, f) in self.fields.items():
            self.out.append('var %s' % gd)
        self.out.append('')
        # clinit: field initializers + static blocks in order, per class
        for cls in [c for c, _ in files]:
            cu = cus[cls]
            typ = cu.types[0]
            self.cls = cls; self.fn_name = cls + '_clinit'; self.fn_vars = {}; self.fn_locals = set(); self.fn_flags=[]; self.ctx=[]; self.pre=[]; self.fn_ret=('void',0)
            self.cur = []
            for member in typ.body:
                if isinstance(member, T.FieldDeclaration):
                    for d in member.declarators:
                        gd = self.fields[(cls, d.name)][0]
                        t = self.fields[(cls, d.name)][1]
                        if d.initializer is not None:
                            self.pre = []
                            if isinstance(d.initializer, T.ArrayInitializer): c = self.arrinit(d.initializer, t)
                            else: c, _ = self._expr(d.initializer)
                            self.flush_pre(1); self.emit(1, '%s = %s' % (gd, c))
                        else:
                            dv = {'int':'0','boolean':'false','byte':'0','char':'0','long':'0','short':'0'}.get(t[0], 'null') if t[1] == 0 else 'null'
                            self.emit(1, '%s = %s' % (gd, dv))
                elif isinstance(member, list):
                    pass
            # static initializer blocks: javalang puts them in typ.body as list of statements? handle BlockStatement with static
            for member in typ.body:
                if isinstance(member, T.BlockStatement) or (isinstance(member, list)):
                    sts = member.statements if isinstance(member, T.BlockStatement) else member
                    for st in sts: self.stmt(st, 1)
            lines = self.cur
            self.out.append('func _clinit_%s():' % cls)
            self.out.extend(['    var %s' % v for v in sorted(self.fn_locals)] + ['    var %s = false' % f for f in self.fn_flags])
            self.out.extend(lines or ['    pass'])
            self.out.append('')
        for (cls, name), lst in self.methods.items():
            for gd, pts, rt, node in lst:
                if gd in self.skip_methods: continue
                if node.body is None: continue
                self.method(cls, gd, node, node.parameters, rt, node.body)
        for cls, c in self.ctors.items():
            if '_ctor_' + cls in self.skip_methods: continue
            self.method(cls, '_ctor_' + cls, c, c.parameters, ('void',0), c.body)
        return '\n'.join(self.out)

if __name__ == '__main__':
    tr = Transpiler()
    files = [('a', sys.argv[1]), ('Midlet', sys.argv[2])]
    out = tr.run(files, skip_names=set(sys.argv[4].split(',')) if len(sys.argv) > 4 else ())
    open(sys.argv[3], 'w', encoding='utf8').write(out + '\n')
    rep = {'errors': tr.errors, 'skipped_calls': sorted(tr.skipped_calls)}
    open(sys.argv[3] + '.report.json', 'w').write(json.dumps(rep, indent=1, ensure_ascii=False))
    from collections import Counter
    print('errors', len(tr.errors))
    for k, v in Counter(re.sub(r' in \S+', '', e) for e in tr.errors).most_common(40): print(v, k)
