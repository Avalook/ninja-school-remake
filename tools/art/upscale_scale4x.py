"""Scale4x (AdvMAME/EPX applied twice) for the original pixel art -> 4x crisp HD base.
Keeps silhouettes/colours exact (no invented detail); used for tiles/NPC/monster sprites
until bespoke HD art replaces them. Input dir is read-only."""
import sys, os
import numpy as np
from PIL import Image

def scale2x(a):
    # a: HxWx4 uint8
    p = np.pad(a, ((1,1),(1,1),(0,0)), mode='edge')
    E = p[1:-1,1:-1]; B = p[:-2,1:-1]; H = p[2:,1:-1]; D = p[1:-1,:-2]; F = p[1:-1,2:]
    eq = lambda x, y: np.all(x == y, axis=2)
    c = (~eq(B,H)) & (~eq(D,F))
    E0 = np.where((c & eq(D,B))[...,None], D, E)
    E1 = np.where((c & eq(B,F))[...,None], F, E)
    E2 = np.where((c & eq(D,H))[...,None], D, E)
    E3 = np.where((c & eq(H,F))[...,None], F, E)
    h, w = a.shape[:2]
    out = np.zeros((h*2, w*2, 4), np.uint8)
    out[0::2,0::2] = E0; out[0::2,1::2] = E1; out[1::2,0::2] = E2; out[1::2,1::2] = E3
    return out

def run(src, dst):
    n = 0
    for dp, dn, fn in os.walk(src):
        for f in fn:
            if not f.endswith('.png'): continue
            ip = os.path.join(dp, f); op = os.path.join(dst, os.path.relpath(ip, src))
            os.makedirs(os.path.dirname(op), exist_ok=True)
            a = np.array(Image.open(ip).convert('RGBA'))
            a[a[...,3] == 0] = 0  # normalise transparent pixels so they compare equal
            b = scale2x(scale2x(a))
            Image.fromarray(b, 'RGBA').save(op, optimize=True)
            n += 1
    print('upscaled', n)

if __name__ == '__main__':
    run(sys.argv[1], sys.argv[2])
