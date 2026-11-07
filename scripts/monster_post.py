"""2D post step for HD monster/NPC sprites (exact 4x canvases, original registration).

Used by scripts/run_monster_pipeline.py. Pure PIL/numpy/scipy (no Blender).

  * repaint4x(img)         -> algorithmic fallback: Scale4x on the colour-label image, then
                              per-label Gaussian field smoothing (argmax) and a soft alpha edge.
  * fit_render(raw, box)   -> scale a Blender render's alpha bbox into a target HD box.
  * outline(img, px, col)  -> dark outer outline by alpha dilation.
  * effect_layer(orig)     -> the shared "hit gag" pixels of an original frame (goggle eyes,
                              mouth, debris) so they can be composited over a 3D body.
"""
import numpy as np
from PIL import Image
from scipy import ndimage as ndi

S = 4
# colours shared by every mob's hit frame (goggle eyes, ring, nose, mouth, sparks)
# (0xdbdbdb is the wasp's wing colour, deliberately NOT included)
EFFECT_RGB = {(0xd8, 0xd8, 0xd8), (0x98, 0x98, 0xb8), (0x50, 0x50, 0x78),
              (0x80, 0x20, 0x18), (0xc8, 0x38, 0x20)}
ANCHOR_RGB = {(0x98, 0x98, 0xb8), (0x50, 0x50, 0x78), (0x80, 0x20, 0x18), (0xc8, 0x38, 0x20)}


def load(path):
    return np.array(Image.open(path).convert("RGBA"))


def labels_of(a):
    """colour-label image; label 0 = transparent."""
    flat = a.reshape(-1, 4).copy()
    flat[flat[:, 3] < 128] = 0
    cols, inv = np.unique(flat, axis=0, return_inverse=True)
    inv = inv.reshape(a.shape[:2])
    if cols[0, 3] != 0:  # no transparent colour present: shift
        cols = np.vstack([np.zeros((1, 4), cols.dtype), cols])
        inv = inv + 1
    return inv, cols


def scale2x(L):
    p = np.pad(L, 1, mode="edge")
    A, B, C, D = p[:-2, 1:-1], p[1:-1, 2:], p[1:-1, :-2], p[2:, 1:-1]   # up, right, left, down
    E = L
    e0 = np.where((C == A) & (C != D) & (A != B), A, E)
    e1 = np.where((A == B) & (A != C) & (B != D), B, E)
    e2 = np.where((D == C) & (D != B) & (C != A), C, E)
    e3 = np.where((B == D) & (B != A) & (D != C), D, E)
    out = np.empty((L.shape[0] * 2, L.shape[1] * 2), L.dtype)
    out[0::2, 0::2], out[0::2, 1::2], out[1::2, 0::2], out[1::2, 1::2] = e0, e1, e2, e3
    return out


def repaint4x(a, sigma=1.05):
    """fallback HD: exact 4x RGBA, same silhouette (soft 1px edge)."""
    L, cols = labels_of(a)
    L4 = scale2x(scale2x(L))
    best = np.full(L4.shape, -1.0)
    lab = np.zeros(L4.shape, np.int64)
    for k in range(len(cols)):
        f = ndi.gaussian_filter((L4 == k).astype(np.float32), sigma)
        m = f > best
        best[m], lab[m] = f[m], k
    out = cols[lab].astype(np.float32)
    opaque = ndi.gaussian_filter((L4 != 0).astype(np.float32), sigma)
    alpha = np.clip((opaque - 0.5) * 2.2 + 0.5, 0, 1)
    # colour under soft edge pixels: take nearest opaque label colour
    idx = ndi.distance_transform_edt(lab == 0, return_distances=False, return_indices=True)
    rgb = cols[lab[idx[0], idx[1]]][..., :3].astype(np.float32)
    res = np.dstack([rgb, alpha * 255]).clip(0, 255).astype(np.uint8)
    return res


def bbox(alpha_mask):
    ys, xs = np.nonzero(alpha_mask)
    if len(xs) == 0:
        return None
    return [int(xs.min()), int(ys.min()), int(xs.max()) + 1, int(ys.max()) + 1]


def effect_mask(a, extra=(), anchors=()):
    """original-res mask of hit-gag pixels: connected groups of effect colours that contain a
    goggle-ring / mouth colour (or `anchors`), tiny sparks, the dark pupils/outline touching
    them, and small detached debris."""
    rgb = a[..., :3]
    op = a[..., 3] >= 128
    eff = np.zeros(op.shape, bool)
    anc = np.zeros(op.shape, bool)
    for c in set(EFFECT_RGB) | set(extra):
        eff |= op & np.all(rgb == c, axis=-1)
    for c in set(ANCHOR_RGB) | set(anchors):
        anc |= op & np.all(rgb == c, axis=-1)
    keep = np.zeros(op.shape, bool)
    lab, n = ndi.label(eff, np.ones((3, 3)))
    for i in range(1, n + 1):
        m = lab == i
        if (m & anc).any() or m.sum() <= 8:
            keep |= m
    dark = op & (rgb.astype(int).sum(-1) < 120)
    grown = ndi.binary_dilation(keep, np.ones((3, 3)))
    eff2 = keep | (grown & dark)
    eff2 = (ndi.binary_fill_holes(eff2) & op) if keep.any() else eff2
    lab, n = ndi.label(op & ~eff2, np.ones((3, 3)))
    if n:
        sizes = ndi.sum(np.ones_like(lab), lab, range(1, n + 1))
        big = sizes.max()
        for i, s in enumerate(sizes, 1):
            if s < max(6, 0.04 * big):
                eff2 |= lab == i
    return eff2


def body_mask(a, eff=None):
    op = a[..., 3] >= 128
    if eff is None:
        eff = effect_mask(a)
    return op & ~eff


def fit_render(raw, box, size):
    """place raw RGBA render (any size) so its alpha bbox fills `box` (HD px) on a canvas."""
    al = raw[..., 3] > 8
    b = bbox(al)
    crop = Image.fromarray(raw[b[1]:b[3], b[0]:b[2]])
    w, h = box[2] - box[0], box[3] - box[1]
    crop = crop.resize((max(1, w), max(1, h)), Image.LANCZOS)
    canvas = Image.new("RGBA", size, (0, 0, 0, 0))
    canvas.alpha_composite(crop, (box[0], box[1]))
    return np.array(canvas)


def outline(img, px=2, col=(24, 14, 22)):
    a = img[..., 3].astype(np.float32) / 255
    r = int(np.ceil(px))
    yy, xx = np.mgrid[-r:r + 1, -r:r + 1]
    k = (xx ** 2 + yy ** 2) <= px * px + 0.5
    grown = ndi.grey_dilation(a, footprint=k)
    under = np.zeros_like(img)
    under[..., :3] = col
    under[..., 3] = (grown * 255).astype(np.uint8)
    base = Image.fromarray(under)
    base.alpha_composite(Image.fromarray(img))
    return np.array(base)


def over(dst, src):
    d = Image.fromarray(dst)
    d.alpha_composite(Image.fromarray(src))
    return np.array(d)
