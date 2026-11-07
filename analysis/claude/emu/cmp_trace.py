"""Compare original-JAR (lockstep emulator) and Godot traces, segment by segment (each in-game map stay)."""
import sys
def load(p):
    out=[]
    for l in open(p):
        parts=l.split(); kv=dict(x.split('=',1) for x in parts[1:] if '=' in x); out.append(kv)
    return out
def segs(tr):
    s=[]; cur=None
    for kv in tr:
        key=(kv.get('d'),kv.get('v'))
        if kv.get('d')=='1':
            if cur is None or cur[0]!=kv.get('v'): cur=[kv.get('v'),[]]; s.append(cur)
            cur[1].append(kv)
        else: cur=None
    return s
a=segs(load(sys.argv[1])); b=segs(load(sys.argv[2])); bad=0; tot=0
norm=lambda v: v.replace('<null>','null').replace('true','1').replace('false','0').replace(' ','')
for i,(sa,sb) in enumerate(zip(a,b)):
    n=min(len(sa[1]),len(sb[1]))
    for k in range(n):
        ka,kb=sa[1][k],sb[1][k]; tot+=1
        diff={x:(ka[x],kb[x]) for x in ka if x in kb and norm(ka[x])!=norm(kb[x])}
        if diff:
            bad+=1
            if bad<=int(sys.argv[3] if len(sys.argv)>3 else 8): print('seg%d map%s frame%d'%(i,sa[0],k), {k2:(v[0][:60],v[1][:60]) for k2,v in diff.items()})
    print('seg%d map%s frames emu=%d godot=%d'%(i,sa[0],len(sa[1]),len(sb[1])))
print('TOTAL compared',tot,'mismatching',bad)
