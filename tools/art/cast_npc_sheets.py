"""Small idle/talk sheets for the teacher NPCs (+ dog idle/walk) by smooth 2D mesh warps of the
HD master (no cutting -> no seams), at master resolution, then downsampled to the game texture
density of remake/art/cast/npc_<kind>.png (same anchor convention).

  idle 0..3 : breathing - upper body (above the hips line, 58 % of height) stretched upward by
              0 / 0.8 / 1.6 / 0.8 %, weight ramps smoothly from the hips to the shoulders
  talk 0..1 : head nod - rotation about the neck point (+3.0 / -1.5 deg) with a smoothstep weight
              from the neck (0) to the chin (1), plus +0.6 % breathing
  dog       : idle 0..1 breathing on npc_dog, walk 0..1 = Codex npc_dog / npc_dog_walk

Output art/cast/sheets/npc_<kind>.png/.json and dog.png/.json, copied to remake/art/cast/sheets/.
json: {cell, anchor (feet, in cell px), count, frames{clip: [cells]}, fps, facing: right, scale}
"""
import json
import shutil
import sys
from pathlib import Path

import numpy as np
from PIL import Image
from scipy import ndimage as ndi

sys.path.insert(0, str(Path(__file__).resolve().parent))
import cast_lib as L  # noqa: E402
import cast_rig as R  # noqa: E402
import cast_codex as C  # noqa: E402

CODEX = L.ROOT / "art/generated/codex_hd"
NPCS = {0: "npc0_old_master", 1: "npc1_ayumi", 2: "npc2_hiroyaki", 3: "npc3_honda",
        4: "npc4_principal", 5: "npc5_takanashi"}


def smooth(t):
    t = np.clip(t, 0, 1)
    return t * t * (3 - 2 * t)


def warp(a, stretch=0.0, angle=0.0, neck=None, hips=None, top=None, bottom=None):
    """inverse-mapped warp of an RGBA (premultiplied internally) image."""
    h, w = a.shape[:2]
    yy, xx = np.mgrid[0:h, 0:w].astype(np.float32)
    sy, sx = yy.copy(), xx.copy()
    if stretch:
        # destination y above hips moves up by stretch * (hips - y) * weight
        wgt = smooth((hips - yy) / max(1.0, hips - neck[1]))
        sy = sy + stretch * (hips - yy) * wgt
    if angle:
        cx, cy = neck
        wgt = smooth((cy - yy) / max(1.0, 0.18 * (bottom - top)))
        th = -np.radians(angle) * wgt
        dx, dy = sx - cx, sy - cy
        sx = cx + dx * np.cos(th) - dy * np.sin(th)
        sy = cy + dx * np.sin(th) + dy * np.cos(th)
    f = a.astype(np.float32) / 255.0
    pre = np.dstack([f[..., :3] * f[..., 3:4], f[..., 3:4]])
    out = np.stack([ndi.map_coordinates(pre[..., c], [sy, sx], order=1, mode="constant") for c in range(4)], -1)
    al = out[..., 3:4]
    rgb = np.where(al > 1e-4, out[..., :3] / np.maximum(al, 1e-4), 0)
    return (np.dstack([rgb, al]) * 255 + 0.5).clip(0, 255).astype(np.uint8)


def to_game(frames, world_h):
    """common crop over all frames, resize to hero density, return cells + anchor."""
    boxes = [Image.fromarray(f).getbbox() for f in frames]
    x0 = min(b[0] for b in boxes); y0 = min(b[1] for b in boxes)
    x1 = max(b[2] for b in boxes); y1 = max(b[3] for b in boxes)
    base_h = boxes[0][3] - boxes[0][1]
    tex_h = world_h * C.HERO_DENSITY
    s = tex_h / base_h
    cells = []
    for f in frames:
        im = Image.fromarray(f).crop((x0, y0, x1, y1))
        im = im.resize((max(1, round(im.width * s)), max(1, round(im.height * s))), Image.LANCZOS)
        c = Image.new("RGBA", (im.width + 8, im.height + 8), (0, 0, 0, 0))
        c.alpha_composite(im, (4, 4))
        cells.append(c)
    anc = C.feet_anchor(np.array(cells[0]))
    return cells, anc, round(world_h / tex_h, 5)


def save(name, cells, anc, scale, frames, fps, src):
    cw = max(c.width for c in cells); ch = max(c.height for c in cells)
    sheet = Image.new("RGBA", (cw * len(cells), ch), (0, 0, 0, 0))
    for i, c in enumerate(cells):
        sheet.alpha_composite(c, (i * cw, ch - c.height))
    anc = [anc[0], anc[1] + ch - cells[0].height]
    sd = L.CAST / "sheets"
    sd.mkdir(parents=True, exist_ok=True)
    sheet.save(sd / f"{name}.png", optimize=True)
    meta = {"cell": [cw, ch], "anchor": anc, "cols": len(cells), "count": len(cells), "frames": frames,
            "fps": fps, "facing": "right", "scale": scale, "source": src}
    (sd / f"{name}.json").write_text(json.dumps(meta, indent=1))
    rd = L.ROOT / "remake/art/cast/sheets"
    rd.mkdir(parents=True, exist_ok=True)
    shutil.copy(sd / f"{name}.png", rd / f"{name}.png")
    shutil.copy(sd / f"{name}.json", rd / f"{name}.json")


def npc_sheet(kind):
    src = CODEX / f"{NPCS[kind]}.png"
    a = np.array(Image.open(src).convert("RGBA"))
    op = a[..., 3] >= 128
    neck_y, top, bottom = R.neck_row(op)
    xs = np.nonzero(op[neck_y])[0]
    neck = ((xs.min() + xs.max()) / 2.0, float(neck_y))
    hips = top + 0.58 * (bottom - top)
    kw = dict(neck=neck, hips=hips, top=top, bottom=bottom)
    frames = [warp(a, stretch=s, **kw) if s else a for s in (0.0, 0.008, 0.016, 0.008)]
    frames += [warp(a, stretch=0.006, angle=3.0, **kw), warp(a, stretch=0.006, angle=-1.5, **kw)]
    orig_h = L.npc_composite(kind).height
    cells, anc, scale = to_game(frames, orig_h * C.HERO_RATIO)
    save(f"npc_{kind}", cells, anc, scale, {"idle": [0, 1, 2, 3], "talk": [4, 5]},
         {"idle": 3, "talk": 5}, str(src.relative_to(L.ROOT)))


def dog_sheet():
    a = np.array(Image.open(CODEX / "npc_dog.png").convert("RGBA"))
    b = np.array(Image.open(CODEX / "npc_dog_walk.png").convert("RGBA"))
    op = a[..., 3] >= 128
    ys = np.nonzero(op.any(1))[0]
    top, bottom = ys.min(), ys.max()
    kw = dict(neck=(0, top + 0.1 * (bottom - top)), hips=top + 0.7 * (bottom - top), top=top, bottom=bottom)
    # walk frame: rescale so the body height matches the standing frame
    bb, ab = Image.fromarray(b).getbbox(), Image.fromarray(a).getbbox()
    k = (ab[3] - ab[1]) / (bb[3] - bb[1])
    bi = Image.fromarray(b).crop(bb)
    bi = bi.resize((round(bi.width * k), round(bi.height * k)), Image.LANCZOS)
    bc = Image.new("RGBA", (a.shape[1] + 400, a.shape[0]), (0, 0, 0, 0))
    ac = bc.copy()
    ac.alpha_composite(Image.fromarray(a), (200, 0))
    cx = (ab[0] + ab[2]) // 2 + 200
    bc.alpha_composite(bi, (cx - bi.width // 2, ab[3] - bi.height))
    A = np.array(ac)
    frames = [A, warp(A, stretch=0.012, **dict(kw, neck=(0, kw["neck"][1])))]
    frames += [A, np.array(bc)]
    orig_h = L.trim(L.rgba(L.ORIG / "npc/d0.png")).height
    cells, anc, scale = to_game(frames, orig_h * C.HERO_RATIO)
    save("dog", cells, anc, scale, {"idle": [0, 1], "walk": [2, 3]}, {"idle": 2, "walk": 6},
         "art/generated/codex_hd/npc_dog.png + npc_dog_walk.png")


if __name__ == "__main__":
    for k in NPCS:
        npc_sheet(k)
        print("npc", k)
    dog_sheet()
    print("dog")
