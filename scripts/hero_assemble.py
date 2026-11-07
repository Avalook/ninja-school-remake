"""Post-process Blender hero renders into final frames, the sprite sheet and a preview.

Host python (Pillow + numpy + scipy):
    python3 scripts/hero_assemble.py

Inputs : art/hero/build/raw/bXX.png (RENDER_SCALE x 256 px), art/hero/build/render-report.json
         art/references/ninja1-hero-46-frames-original.png (read-only, preview only)
Outputs: art/hero/frames/b00.png..b45.png (256x256 RGBA, ground contact at (128,240))
         art/hero/hero_sheet.png + hero_sheet.json (8 cols x 6 rows, B row-major)
         art/hero/hero_preview.png (original frame | HD frame, labelled, mid grey)
         art/hero/build/assemble-report.json (anchor/scale checks)
"""

import json
from pathlib import Path

import numpy as np
from PIL import Image, ImageDraw, ImageFont
from scipy import ndimage

ROOT = Path(__file__).resolve().parent.parent
HERO = ROOT / "art/hero"
RAW = HERO / "build/raw"
REF = ROOT / "art/references/ninja1-hero-46-frames-original.png"
CELL, COLS, COUNT = 256, 8, 46
ANCHOR = (128, 240)
OUTLINE = (20, 15, 28)


def downsample(img, scale):
    a = np.asarray(img.convert("RGBA")).astype(np.float32) / 255.0
    rgb, al = a[..., :3], a[..., 3:4]
    pre = np.concatenate([rgb * al, al], axis=2)
    h, w = pre.shape[:2]
    pre = pre.reshape(h // scale, scale, w // scale, scale, 4).mean(axis=(1, 3))
    al = pre[..., 3:4]
    rgb = np.where(al > 1e-4, pre[..., :3] / np.maximum(al, 1e-4), 0)
    return np.concatenate([rgb, al], axis=2)


def add_outline(px):
    """1 px soft dark outline around the silhouette (master art has inked edges)."""
    al = px[..., 3]
    grown = ndimage.grey_dilation(al, footprint=np.array([[0, 1, 0], [1, 1, 1], [0, 1, 0]]))
    ring = np.clip(grown - al, 0, 1) * 0.85
    ink = np.array(OUTLINE, np.float32) / 255.0
    out_a = al + ring * (1 - al)
    rgb = (px[..., :3] * al[..., None] + ink * (ring * (1 - al))[..., None]) / np.maximum(out_a, 1e-4)[..., None]
    return np.concatenate([rgb, out_a[..., None]], axis=2)


def to_image(px):
    return Image.fromarray(np.clip(px * 255 + 0.5, 0, 255).astype(np.uint8), "RGBA")


def ref_cell(ref, b):
    c, r = b % 12, b // 12
    return ref.crop((c * 160 + 20, r * 140 + 24, c * 160 + 140, r * 140 + 136))


def main():
    report = json.loads((HERO / "build/render-report.json").read_text())
    scale = int(report["render_scale"])
    frames_dir = HERO / "frames"
    frames_dir.mkdir(parents=True, exist_ok=True)
    checks = {}
    frames = []
    missing = []
    for b in range(COUNT):
        src = RAW / f"b{b:02d}.png"
        if not src.is_file():
            missing.append(b)
            frames.append(None)
            continue
        canvas = add_outline(downsample(Image.open(src), scale))
        m = int(report.get("margin", 0))
        al = canvas[..., 3]
        ys, xs = np.nonzero(al >= 0.5)
        place = report["frames"][str(b)]["place"]
        grounded = place[0] == "ground" and float(place[1]) == 0
        shift = 0                      # +down, applied to the image
        y_offset = 0                   # engine draw offset that restores intended placement
        if len(ys):
            top, bottom = ys.min() - m, ys.max() - m
            if grounded:
                shift = ANCHOR[1] - bottom          # lock sole row exactly to the anchor row
            else:
                if top < 1:
                    shift = 1 - top                 # keep the frame inside the cell
                elif bottom > CELL - 2:
                    shift = CELL - 2 - bottom
                y_offset = -shift
        y0 = m - shift
        px = canvas[y0:y0 + CELL, m:m + CELL]
        outside = al.copy()
        outside[max(y0, 0):y0 + CELL, m:m + CELL] = 0
        img = to_image(px)
        img.save(frames_dir / f"b{b:02d}.png", optimize=True)
        frames.append(img)
        cal = np.asarray(img)[..., 3]
        cy, cx = np.nonzero(cal >= 128)
        info = {"place": place, "grounded": grounded, "shift_px": int(shift),
                "y_offset": int(y_offset),
                "bbox": [int(cx.min()), int(cy.min()), int(cx.max()), int(cy.max())] if len(cy) else None,
                "overflow_px": int((outside > 0.03).sum())}
        if grounded and len(cy):
            info["sole_row"] = int(cy.max())
        checks[b] = info
    if missing:
        raise SystemExit(f"missing raw renders for B={missing}")

    sheet = Image.new("RGBA", (COLS * CELL, 6 * CELL), (0, 0, 0, 0))
    for b, img in enumerate(frames):
        sheet.alpha_composite(img, ((b % COLS) * CELL, (b // COLS) * CELL))
    sheet.save(HERO / "hero_sheet.png", optimize=True)
    (HERO / "hero_sheet.json").write_text(json.dumps({
        "cell": [CELL, CELL], "anchor": list(ANCHOR), "cols": COLS, "count": COUNT,
        "facing": "right", "source": "art/generated/ninja1-hero-cloud-v1.png",
        # extra keys: per-frame draw offset (px, +down) that restores the original game's
        # vertical placement where a high jump frame had to be shifted to fit the cell
        "frame_y_offset": [checks[b]["y_offset"] for b in range(COUNT)]}, indent=2))

    # preview: original (nearest x2.2 of the 2x sheet) | HD frame
    ref = Image.open(REF).convert("RGBA")
    pc, tile_w, tile_h = 6, 2 * CELL + 24, CELL + 30
    rows = (COUNT + pc - 1) // pc
    prev = Image.new("RGBA", (pc * tile_w + 16, rows * tile_h + 16), (128, 128, 128, 255))
    d = ImageDraw.Draw(prev)
    try:
        font = ImageFont.truetype("/System/Library/Fonts/Supplemental/Arial Bold.ttf", 18)
    except OSError:
        font = ImageFont.load_default()
    for b, img in enumerate(frames):
        x0, y0 = 8 + (b % pc) * tile_w, 8 + (b // pc) * tile_h
        d.text((x0 + 4, y0 + 2), f"B{b:02d}", fill=(255, 240, 80, 255), font=font)
        orig = ref_cell(ref, b)
        orig = orig.resize((int(orig.width * 2.2), int(orig.height * 2.2)), Image.NEAREST)
        # original ground row (2x sheet y=121) aligned to HD ground row 240
        oy = y0 + 26 + ANCHOR[1] - int((121 - 24) * 2.2)
        prev.alpha_composite(orig, (x0, max(oy, y0 + 26)))
        d.rectangle((x0 + CELL + 12, y0 + 26, x0 + 2 * CELL + 12, y0 + 26 + CELL),
                    outline=(110, 110, 110, 255))
        prev.alpha_composite(img, (x0 + CELL + 12, y0 + 26))
        gy = y0 + 26 + ANCHOR[1]
        d.line((x0 + CELL + 12, gy, x0 + 2 * CELL + 12, gy), fill=(90, 90, 90, 255))
    prev.save(HERO / "hero_preview.png", optimize=True)

    sole_rows = sorted({c["sole_row"] for c in checks.values() if "sole_row" in c})
    summary = {"grounded_sole_rows": sole_rows,
               "overflow_frames": {b: c["overflow_px"] for b, c in checks.items() if c["overflow_px"]},
               "frames": checks}
    (HERO / "build/assemble-report.json").write_text(json.dumps(summary, indent=2))
    print("HERO_ASSEMBLE_DONE sole rows", sole_rows, "overflow", summary["overflow_frames"],
          "y_offsets", {b: c["y_offset"] for b, c in checks.items() if c["y_offset"]})


if __name__ == "__main__":
    main()
