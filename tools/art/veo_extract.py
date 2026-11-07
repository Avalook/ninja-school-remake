"""Turn the Veo 3 reference clips (art/incoming/veo/*.mp4, green screen) into keyed, normalised
24 fps sprite atlases for the game: game/art/veo/<clip>.webp + <clip>.json.

Per clip: chroma key (green dominance) with spill suppression, floor-line removal, skip the fade-in,
one constant transform per clip so the motion (jumps, slides) is preserved: the standing pose of the
first stable frame is scaled to the hero sheet's standing height and its feet put on the anchor.
Usage: python3 tools/art/veo_extract.py [clip ...]"""
import json, subprocess, sys
from pathlib import Path
import numpy as np
from PIL import Image
from scipy import ndimage as ndi

ROOT = Path(__file__).resolve().parents[2]
SRC = ROOT / "art/incoming/veo"
OUT = ROOT / "game/art/veo"
DBG = ROOT / "art/veo_build"
CELL = 320
ANCHOR = (160, 288)
STAND_H = 201.0          # px, same as the Blender hero sheet (art/hero/hero_sheet.json)
W, H = 1280, 720

REF_FRAME = {"boy_gets_hit": 120}

def frames(path):
    p = subprocess.run(["ffmpeg", "-loglevel", "error", "-i", str(path), "-f", "rawvideo", "-pix_fmt", "rgb24", "-"],
                       capture_output=True, check=True)
    a = np.frombuffer(p.stdout, np.uint8)
    return a.reshape(-1, H, W, 3)

def key(rgb):
    a = rgb.astype(np.float32)
    r, g, b = a[..., 0], a[..., 1], a[..., 2]
    gs = g - np.maximum(r, b)
    alpha = np.clip((34.0 - gs) / 18.0, 0.0, 1.0)
    # very dark pixels in green areas (vignette) are background too
    lum = a.mean(axis=2)
    alpha = np.where((gs > 8) & (lum < 60), 0.0, alpha)
    # spill suppression on kept pixels
    g2 = np.minimum(g, np.maximum(r, b) + 12.0)
    out = np.dstack([r, np.where(alpha > 0, g2, g), b, alpha * 255.0])
    return out

def remove_floor_line(rgba):
    al = rgba[..., 3] > 128
    rows = al.mean(axis=1)
    for y in range(int(H * 0.6), H):
        if rows[y] > 0.55:                      # a row covered across the frame = drawn floor line
            rgba[max(0, y - 2):y + 3, :, 3] = 0
    return rgba

def clean(alpha):
    m = alpha > 40
    lab, n = ndi.label(m)
    if n == 0:
        return alpha
    sizes = ndi.sum(m, lab, range(1, n + 1))
    keep = np.zeros(n + 1, bool)
    keep[1:] = sizes >= max(60, sizes.max() * 0.004)   # drop specks, keep effects/shuriken
    return np.where(keep[lab], alpha, 0)

def first_stable(fr):
    # skip the black fade-in: background corner brightness reaches its steady value
    c = fr[:, 20:60, 20:60].mean(axis=(1, 2, 3))
    target = np.median(c[len(c) // 2:])
    for i, v in enumerate(c):
        if v > target * 0.93:
            return i
    return 0

def process(path):
    name = path.stem
    fr = frames(path)
    s0 = first_stable(fr)
    keyed = []
    for i in range(s0, len(fr)):
        k = remove_floor_line(key(fr[i]))
        k[..., 3] = clean(k[..., 3])
        keyed.append(k)
    # reference standing pose = first stable frame (per-clip override when the clip starts mid-action)
    ref = REF_FRAME.get(name, 0)
    al = keyed[min(ref, len(keyed) - 1)][..., 3] > 128
    ys, xs = np.nonzero(al)
    top, bot = ys.min(), ys.max()
    # horizontal centre from the torso band (ignores sword/headband tails)
    band = al[top + int((bot - top) * 0.35): top + int((bot - top) * 0.75)]
    bx = np.nonzero(band.any(axis=0))[0]
    cx = (bx.min() + bx.max()) / 2.0
    s = STAND_H / float(bot - top)
    out_frames = []
    for k in keyed:
        img = Image.fromarray(np.clip(k, 0, 255).astype(np.uint8), "RGBA")
        img = img.resize((int(W * s), int(H * s)), Image.LANCZOS)
        cell = Image.new("RGBA", (CELL, CELL), (0, 0, 0, 0))
        ox = int(round(ANCHOR[0] - cx * s)); oy = int(round(ANCHOR[1] - bot * s))
        canvas = Image.new("RGBA", (CELL, CELL), (0, 0, 0, 0))
        # paste with clipping (alpha_composite needs in-bounds source)
        sx0, sy0 = max(0, -ox), max(0, -oy)
        crop = img.crop((sx0, sy0, min(img.width, CELL - ox), min(img.height, CELL - oy)))
        canvas.alpha_composite(crop, (ox + sx0, oy + sy0))
        out_frames.append(canvas)
    cols = 16
    rows = (len(out_frames) + cols - 1) // cols
    sheet = Image.new("RGBA", (cols * CELL, rows * CELL), (0, 0, 0, 0))
    for i, f in enumerate(out_frames):
        sheet.alpha_composite(f, ((i % cols) * CELL, (i // cols) * CELL))
    OUT.mkdir(parents=True, exist_ok=True)
    sheet.save(OUT / f"{name}.webp", "WEBP", quality=90, method=4)
    (OUT / f"{name}.json").write_text(json.dumps({"clip": name, "fps": 24, "count": len(out_frames), "cols": cols,
        "cell": [CELL, CELL], "anchor": list(ANCHOR), "skipped_fade_frames": int(s0),
        "scale": round(s, 4), "source": str(path.relative_to(ROOT))}, indent=1))
    DBG.mkdir(parents=True, exist_ok=True)
    strip = Image.new("RGBA", (CELL * 12, CELL), (120, 120, 130, 255))
    for j, i in enumerate(np.linspace(0, len(out_frames) - 1, 12).astype(int)):
        strip.alpha_composite(out_frames[i], (j * CELL, 0))
    strip.convert("RGB").resize((CELL * 6, CELL // 2)).save(DBG / f"{name}_strip.png")
    print(name, "frames", len(out_frames), "fade", s0, "scale", round(s, 3), "stand_px", bot - top)

if __name__ == "__main__":
    names = sys.argv[1:]
    for p in sorted(SRC.glob("*.mp4")):
        if not names or p.stem in names:
            process(p)
