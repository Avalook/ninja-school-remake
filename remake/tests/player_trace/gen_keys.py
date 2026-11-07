"""Seeded random key timeline for main.gd --keys (non-overlapping intervals per key)."""
import random, sys
seed, dur = int(sys.argv[1]), float(sys.argv[2])
keys = sys.argv[3].split(',')
pre = sys.argv[4].split(',') if len(sys.argv) > 4 and sys.argv[4] else []   # fixed opening: key@t0-t1
r = random.Random(seed)
free = {k: 1.0 for k in keys}          # next time each key may be pressed again
out = list(pre)
t = 1.0
for e in pre:
    k, tt = e.split('@'); t1 = float(tt.split('-')[1])
    free[k] = max(free.get(k, 0), t1 + 0.05); t = max(t, t1 + 0.1)
while t < dur:
    k = r.choice(keys)
    if free[k] <= t:
        d = r.choice([0.05, 0.1, 0.2, 0.4, 0.8, 1.5])
        out.append("%s@%.2f-%.2f" % (k, t, t + d))
        free[k] = t + d + 0.05
    t += r.choice([0.04, 0.08, 0.12, 0.2, 0.3])
print(",".join(out))
