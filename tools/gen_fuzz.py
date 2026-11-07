"""Deterministic pseudo-random input script for differential testing (original JAR vs Godot port)."""
import random, sys
seed=int(sys.argv[1]); n=int(sys.argv[2]); keys=sys.argv[3].split(',') if len(sys.argv)>3 else None
r=random.Random(seed)
out=["wait 2500","tap -7","wait 1500","tap -6","wait 3000","tap -5","wait 6000"]
out+=["tap -5 16","wait 800","tap -5 16","wait 2000"]
pre=sys.argv[4] if len(sys.argv)>4 else ''
for line in pre.split(';'):
    if line: out.append(line)
K=keys or ['-3','-4','-1','-5','-5','-5','-2','-7','49','51','48']
for i in range(n):
    k=r.choice(K); dur=r.choice([80,120,200,400,800])
    if r.random()<0.35:
        k2=r.choice(['-3','-4']); out+=["press "+k2,"tap "+k,"wait %d"%dur,"release "+k2]
    else:
        out+=["hold %s %d"%(k,dur),"wait %d"%r.choice([0,40,80,200])]
print('\n'.join(out))
