"""Shared helpers for the local HD cast pipeline (FLUX.2 Klein 4B edit via MFLUX, offline).

Pure Pillow + numpy + scipy. Used by tools/art/cast_generate.py, cast_mobs.py, cast_qa.py, cast_rig.py.
"""
import json
import os
import subprocess
import time
from datetime import datetime, timezone
from pathlib import Path

import numpy as np
from PIL import Image
from scipy import ndimage as ndi

ROOT = Path(__file__).resolve().parents[2]
ORIG = ROOT / "game/orig_res"
CAST = ROOT / "art/cast"
WORK = CAST / "work"
JOBS = CAST / "jobs.json"
MFLUX_EDIT = "/Users/quangdang/Downloads/ninja/.tools/imagegen-venv/bin/mflux-generate-flux2-edit"
MODEL = "Runpod/FLUX.2-klein-4B-mflux-4bit"
BASE_MODEL = "flux2-klein-4b"
BASE_SEED = 4211
STEPS = 4

# background colours used for generation; picked per character to be far from its palette
BGS = {
    "green": ((0, 255, 0), "bright chroma-key green (#00FF00)"),
    "magenta": ((255, 0, 255), "bright magenta (#FF00FF)"),
    "blue": ((0, 80, 255), "bright pure blue (#0050FF)"),
}

# NPC body offset above the legs (orig.gd: lJ = [7, 6, 6, 5, 2, 4]; both drawn anchor BOTTOM|HCENTER)
NPC_LJ = [7, 6, 6, 5, 2, 4]


def now():
    return datetime.now(timezone.utc).isoformat(timespec="seconds")


def rgba(path):
    return Image.open(path).convert("RGBA")


# ---------------------------------------------------------------- original references
def npc_composite(i):
    """standing NPC i (0..5) exactly as orig.gd r_o draws it: legs at y, body at y - lJ[i]."""
    lj = NPC_LJ[i]
    b = rgba(ORIG / f"npc/{i}0.png")
    l = rgba(ORIG / f"npc/{i}1.png")
    w = max(b.width, l.width) + 2
    h = lj + b.height
    c = Image.new("RGBA", (w, h), (0, 0, 0, 0))
    cx = w // 2
    c.alpha_composite(l, (cx - l.width // 2, h - l.height))
    c.alpha_composite(b, (cx - b.width // 2, h - lj - b.height))
    return c


def student_composite(f, h=0):
    """classmate standing frame: orig.gd g_o with hero frame 0 = head (-10,-32), legs cl1 (-7,-7),
    body cb1 (-11,-15), anchor TOP|LEFT, draw order head, legs, body."""
    head = rgba(ORIG / f"cp/f{f}h{h}.png") if f is not None else rgba(ORIG / "cp/ch0.png")
    legs = rgba(ORIG / "cp/cl1.png")
    body = rgba(ORIG / "cp/cb1.png")
    ox, oy = 14, 34
    c = Image.new("RGBA", (40, 36), (0, 0, 0, 0))
    c.alpha_composite(head, (ox - 10, oy - 32))
    c.alpha_composite(legs, (ox - 7, oy - 7))
    c.alpha_composite(body, (ox - 11, oy - 15))
    return c.crop(c.getbbox())


def golem_composite():
    """wooden golem assembled from its parts for a full-body reference (approximate layout)."""
    body = rgba(ORIG / "mob/b22.png")
    fist_l = rgba(ORIG / "mob/b20.png")
    fist_r = rgba(ORIG / "mob/b23.png")
    foot = rgba(ORIG / "mob/b21.png")
    c = Image.new("RGBA", (110, 70), (0, 0, 0, 0))
    c.alpha_composite(foot, (30, 52))
    c.alpha_composite(foot, (55, 52))
    c.alpha_composite(body, (33, 12))
    c.alpha_composite(fist_l, (2, 20))
    c.alpha_composite(fist_r, (78, 20))
    return c.crop(c.getbbox())


def trim(im):
    bb = im.getbbox()
    return im.crop(bb) if bb else im


# ---------------------------------------------------------------- background choice / input
def bg_hits(rgb, name):
    r, g, b = [rgb[..., i].astype(int) for i in range(3)]
    if name == "green":
        # includes the darker green of a cast ground shadow (flood fill from the border only)
        return (g > r + 30) & (g > b + 30)
    if name == "magenta":
        return (r > g + 28) & (b > g + 28) & (np.abs(r - b) < 0.45 * np.maximum(r, b) + 10)
    if name == "blue":
        return (b > r + 60) & (b > g + 40)
    raise KeyError(name)


def pick_bg(sprite):
    a = np.array(sprite)
    op = a[..., 3] >= 128
    best = None
    for name in ("green", "magenta", "blue"):
        frac = float(bg_hits(a[..., :3], name)[op].mean()) if op.any() else 0.0
        if best is None or frac < best[1] - 1e-9:
            best = (name, frac)
    return best[0]


def make_input(sprite, size=1024, bg=(255, 255, 255), fill=0.80, feet=0.97):
    """nearest-neighbour integer upscale of the original sprite onto a flat canvas."""
    s = max(1, int(size * fill / max(sprite.size)))
    up = sprite.resize((sprite.width * s, sprite.height * s), Image.NEAREST)
    c = Image.new("RGBA", (size, size), tuple(bg) + (255,))
    y = int(size * feet) - up.height
    y = max(0, min(y, size - up.height))
    c.alpha_composite(up, ((size - up.width) // 2, y))
    return c.convert("RGB"), s


# ---------------------------------------------------------------- generation
def run_edit(images, prompt, out, seed, size=(1024, 1024), steps=STEPS, log=None):
    env = dict(os.environ, HF_HUB_OFFLINE="1")
    cmd = [MFLUX_EDIT, "--model", MODEL, "--base-model", BASE_MODEL,
           "--image-paths", *[str(p) for p in images], "--prompt", prompt,
           "--steps", str(steps), "--seed", str(seed),
           "--width", str(size[0]), "--height", str(size[1]), "--output", str(out)]
    t0 = time.time()
    p = subprocess.run(cmd, env=env, capture_output=True, text=True, timeout=1800)
    dt = round(time.time() - t0, 1)
    if log:
        Path(log).write_text(p.stdout[-3000:] + "\n" + p.stderr[-3000:])
    if p.returncode != 0 or not Path(out).is_file():
        raise RuntimeError(f"mflux failed ({p.returncode}): {p.stderr[-800:]}")
    return dt, cmd


# ---------------------------------------------------------------- matting
def matte(img, bgname, soft=True):
    """chroma key: background = bg-coloured pixels connected to the image border (so the same
    colour inside the character survives), incl. darker cast shadows of that hue; then despill
    and a 1px soft edge."""
    a = np.array(img.convert("RGB")).astype(np.int32)
    hit = bg_hits(a, bgname)
    lab, n = ndi.label(hit)
    border = set(np.unique(np.concatenate([lab[0], lab[-1], lab[:, 0], lab[:, -1]]))) - {0}
    bg = np.isin(lab, list(border))
    if bg.any():
        ref = np.median(a[bg][::7], axis=0)
        # enclosed pockets (between arm and body, under the chin...) that have the key colour itself
        if n:
            means = np.stack([ndi.mean(a[..., i], lab, range(1, n + 1)) for i in range(3)], 1)
            dist = np.sqrt(((means - ref) ** 2).sum(1))
            pocket = np.zeros(n + 1, bool)
            pocket[1:] = dist < 45
            bg |= pocket[lab]
    # cast ground shadow: dark, key-tinted pixels in the lowest 15 % of the figure, reachable from bg
    fgb = np.nonzero((~bg).any(1))[0]
    if len(fgb):
        r, g, b = a[..., 0], a[..., 1], a[..., 2]
        tint = {"green": (g > r + 8) & (g > b + 8), "magenta": (r > g + 8) & (b > g + 8),
                "blue": (b > r + 15) & (b > g + 8)}[bgname]
        cand = tint & (a.sum(-1) < 330)
        cand[: int(fgb[-1] - 0.15 * (fgb[-1] - fgb[0]))] = False
        bg = ndi.binary_propagation(bg, mask=bg | cand)
    fg = ~bg
    lab2, n2 = ndi.label(fg)
    if n2:
        sizes = ndi.sum(fg, lab2, range(1, n2 + 1))
        keep = np.zeros(n2 + 1, bool)
        keep[1:] = sizes >= max(150, 0.002 * sizes.max())
        fg = keep[lab2]
    alpha = fg.astype(np.float32)
    if soft:
        # 1px edge feather: average of 3x3 neighbourhood on the boundary only
        sm = ndi.uniform_filter(alpha, 3)
        edge = fg ^ ndi.binary_erosion(fg)
        alpha = np.where(edge, np.clip(sm * 1.4 - 0.2, 0, 1), alpha)
    rgb = a.astype(np.float32)
    # despill: clamp the key channel(s) on edge-near pixels
    near = ndi.binary_dilation(~fg, iterations=4) & fg
    r, g, b = rgb[..., 0], rgb[..., 1], rgb[..., 2]
    if bgname == "green":
        g2 = np.minimum(g, np.maximum(r, b) + 10)
        rgb[..., 1] = np.where(near, g2, g)
    elif bgname == "magenta":
        cap = g + 10
        rgb[..., 0] = np.where(near & (r > cap) & (b > cap), np.minimum(r, np.maximum(cap, b * 0 + cap)), r)
        rgb[..., 2] = np.where(near & (b > cap) & (r > cap), np.minimum(b, cap), b)
    elif bgname == "blue":
        rgb[..., 2] = np.where(near, np.minimum(b, np.maximum(r, g) + 15), b)
    out = np.dstack([np.clip(rgb, 0, 255), alpha * 255]).astype(np.uint8)
    out[..., :3][alpha == 0] = 0
    return Image.fromarray(out, "RGBA")


def normalize(im, canvas=1254, feet=0.97, fill=0.92):
    """crop to alpha bbox, scale to fit `fill` of the canvas, feet at `feet` of the height."""
    im = trim(im)
    s = min(canvas * fill / im.height, canvas * 0.96 / im.width)
    im = im.resize((max(1, round(im.width * s)), max(1, round(im.height * s))), Image.LANCZOS)
    c = Image.new("RGBA", (canvas, canvas), (0, 0, 0, 0))
    y = int(canvas * feet) - im.height
    c.alpha_composite(im, ((canvas - im.width) // 2, max(0, y)))
    return c


# ---------------------------------------------------------------- job log
def load_jobs():
    if JOBS.is_file():
        return json.loads(JOBS.read_text())
    return {"model": MODEL, "tool": MFLUX_EDIT, "local_only": True, "jobs": []}


def log_job(rec):
    d = load_jobs()
    d["jobs"].append(rec)
    d["updated"] = now()
    JOBS.write_text(json.dumps(d, indent=1))
