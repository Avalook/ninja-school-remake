import sys,re
def load(p):
    out=[]
    for l in open(p):
        parts=l.split(); f=int(parts[0]); kv=dict(x.split('=',1) for x in parts[1:] if '=' in x); out.append((f,kv))
    return out
a=load(sys.argv[1]); b=load(sys.argv[2])
# align on first in-game frame
def first(tr): return next(i for i,(f,kv) in enumerate(tr) if kv.get('d')=='1')
ia,ib=first(a),first(b)
n=min(len(a)-ia,len(b)-ib); bad=0
for k in range(n):
    ka=a[ia+k][1]; kb=b[ib+k][1]
    diff={x:(ka[x],kb.get(x)) for x in ka if x in kb and ka[x]!=kb[x]}
    if diff:
        bad+=1
        if bad<=10: print('frame+%d'%k, diff)
print('compared',n,'frames from in-game start; mismatching frames:',bad)
