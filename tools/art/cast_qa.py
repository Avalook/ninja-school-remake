"""Automatic QA of an HD cast/monster image against its original sprite.

Metrics (all computed on opaque pixels):
  palette   - dominant colours (k-means k=6 in CIELAB, weighted by pixel count) of original vs HD;
              score = max(orig->HD, HD->orig) weighted mean nearest-cluster distance (dE76).
  aspect    - |ln((w/h)_hd / (w/h)_orig)| of the alpha bounding boxes.
  facing    - horizontal mass offset of the upper 60 % of the silhouette from the bbox centre,
              normalised by width (+ = right). For masters the target is "faces right"; for
              monster frames the sign must match the original frame.
  key_left  - fraction of HD opaque pixels that still look like the chroma key colour.
  coverage  - opaque fraction of the HD bbox (catches broken mattes).

    python3 tools/art/cast_qa.py            # re-score every art/cast/*.png + art/cast/mob/*.png
"""
import json
import sys
from pathlib import Path

import numpy as np
from PIL import Image
from scipy.cluster.vq import kmeans2

sys.path.insert(0, str(Path(__file__).resolve().parent))
import cast_lib as L  # noqa: E402

LIMITS = {
    "master": {"palette": 24.0, "aspect": 0.55, "key_left": 0.03, "coverage": (0.2, 0.95)},
    "mob": {"palette": 22.0, "aspect": 0.30, "key_left": 0.03, "coverage": (0.15, 0.98)},
}


def _lab(rgb):
    c = rgb.astype(np.float64) / 255.0
    c = np.where(c > 0.04045, ((c + 0.055) / 1.055) ** 2.4, c / 12.92)
    m = np.array([[0.4124, 0.3576, 0.1805], [0.2126, 0.7152, 0.0722], [0.0193, 0.1192, 0.9505]])
    xyz = c @ m.T / np.array([0.95047, 1.0, 1.08883])
    f = np.where(xyz > 0.008856, np.cbrt(xyz), 7.787 * xyz + 16 / 116)
    return np.stack([116 * f[:, 1] - 16, 500 * (f[:, 0] - f[:, 1]), 200 * (f[:, 1] - f[:, 2])], 1)


def palette(im, k=6, seed=0):
    a = np.array(im.convert("RGBA"))
    px = a[a[..., 3] >= 128][:, :3]
    if len(px) > 40000:
        px = px[np.random.default_rng(seed).choice(len(px), 40000, replace=False)]
    lab = _lab(px)
    k = min(k, max(1, len(np.unique(px, axis=0))))
    cent, lbl = kmeans2(lab, k, minit="++", seed=seed)
    w = np.bincount(lbl, minlength=k).astype(float)
    ok = w > 0
    return cent[ok], w[ok] / w[ok].sum()


def pal_dist(p1, p2):
    (c1, w1), (c2, w2) = p1, p2
    d = np.sqrt(((c1[:, None] - c2[None]) ** 2).sum(-1))
    return float(max((d.min(1) * w1).sum(), (d.min(0) * w2).sum()))


def facing(im):
    a = np.array(im.convert("RGBA"))[..., 3] >= 128
    ys, xs = np.nonzero(a)
    if not len(xs):
        return 0.0
    y0, y1, x0, x1 = ys.min(), ys.max(), xs.min(), xs.max()
    up = ys <= y0 + 0.6 * (y1 - y0)
    return float((xs[up].mean() - (x0 + x1) / 2) / max(1, x1 - x0 + 1))


def facing_face(im):
    """for character masters: horizontal offset of skin-tone pixels in the head (top 38 % of the
    silhouette) from the head bbox centre, normalised (+ = faces right). Falls back to `facing`
    when the head shows no skin (masked / bucket head / monsters)."""
    a = np.array(im.convert("RGBA")).astype(int)
    op = a[..., 3] >= 128
    ys, xs = np.nonzero(op)
    y0, y1 = ys.min(), ys.max()
    head = op.copy()
    head[int(y0 + 0.38 * (y1 - y0)):] = False
    hy, hx = np.nonzero(head)
    r, g, b = a[..., 0], a[..., 1], a[..., 2]
    skin = head & (r > 150) & (r > g + 15) & (g > b + 10) & (r - b > 45)
    if skin.sum() < 0.03 * head.sum():
        return facing(im)
    sy, sx = np.nonzero(skin)
    return float((sx.mean() - (hx.min() + hx.max()) / 2) / max(1, hx.max() - hx.min() + 1))


def aspect(im):
    bb = im.getbbox()
    return (bb[2] - bb[0]) / max(1, bb[3] - bb[1])


def score(orig, hd, kind="master", bgname=None, mirror_expected=None):
    lim = LIMITS[kind]
    hd = hd.convert("RGBA")
    orig = orig.convert("RGBA")
    r = {}
    r["palette"] = round(pal_dist(palette(orig), palette(hd)), 2)
    r["aspect_orig"] = round(aspect(orig), 3)
    r["aspect_hd"] = round(aspect(hd), 3)
    r["aspect"] = round(abs(np.log(r["aspect_hd"] / r["aspect_orig"])), 3)
    r["facing_orig"] = round(facing(orig), 3)
    r["facing_hd"] = round(facing_face(hd) if kind == "master" else facing(hd), 3)
    a = np.array(hd)
    op = a[..., 3] >= 128
    if bgname:
        # only the rim (<= 4 px from the matte edge) counts: interior key-coloured details
        # (green goggle lenses...) are legitimate
        from scipy import ndimage as ndi
        rim = op & ndi.binary_dilation(~op, iterations=4)
        r["key_left"] = round(float(L.bg_hits(a[..., :3].astype(int), bgname)[rim].mean()), 4) if rim.any() else 0.0
    else:
        r["key_left"] = 0.0
    bb = hd.getbbox()
    r["coverage"] = round(float(op[bb[1]:bb[3], bb[0]:bb[2]].mean()), 3)
    flags = []
    if r["palette"] > lim["palette"]:
        flags.append("palette")
    if r["aspect"] > lim["aspect"]:
        flags.append("aspect")
    if kind == "master":
        if r["facing_hd"] < -0.02:
            flags.append("facing_not_right")
    else:
        fo, fh = r["facing_orig"], r["facing_hd"]
        if abs(fo) > 0.04 and abs(fh) > 0.02 and np.sign(fo) != np.sign(fh):
            flags.append("facing_mismatch")
    if r["key_left"] > lim["key_left"]:
        flags.append("key_left")
    lo, hi = lim["coverage"]
    if not lo <= r["coverage"] <= hi:
        flags.append("coverage")
    r["flags"] = flags
    r["ok"] = not flags
    return r


def main():
    """re-score all outputs listed in art/cast/jobs.json (latest accepted job per output)."""
    jobs = L.load_jobs()["jobs"]
    latest = {}
    for j in jobs:
        if j.get("accepted"):
            latest[j["output"]] = j
    res = {}
    for out, j in sorted(latest.items()):
        p = L.ROOT / out
        if not p.is_file():
            continue
        orig = Image.open(L.ROOT / j["orig_ref"])
        res[j["id"]] = dict(score(orig, Image.open(p), j.get("kind", "master"), j.get("bg")),
                            output=out, tries=j.get("try", 1), seed=j["seed"])
    qa = L.CAST / "qa.json"
    old = json.loads(qa.read_text()) if qa.is_file() else {}
    old.setdefault("items", {}).update(res)
    old["limits"] = {k: {kk: list(vv) if isinstance(vv, tuple) else vv for kk, vv in v.items()}
                     for k, v in LIMITS.items()}
    old["updated"] = L.now()
    qa.write_text(json.dumps(old, indent=1))
    for k, v in res.items():
        print(k, "OK" if v["ok"] else v["flags"], v["palette"], v["aspect"], v["facing_hd"])


if __name__ == "__main__":
    main()
