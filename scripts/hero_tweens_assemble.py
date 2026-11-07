"""Assemble Blender in-between hero frames (t=1/3, 2/3 between two key poses B1->B2) into
art/hero/hero_tweens.png + .json. Placement = interpolation of the two key frames' cell shifts,
so tweens line up with the 46 key frames (feet anchor (128,240))."""
import json
from pathlib import Path
import numpy as np
from PIL import Image
import hero_assemble as HA

HERO = HA.HERO
def main():
    rep = json.loads((HERO / "build/render-report.json").read_text())
    asm = json.loads((HERO / "build/assemble-report.json").read_text())["frames"]
    scale = int(rep["render_scale"]); m = int(rep.get("margin", 0))
    cells = []; index = {}
    for tw in rep.get("tweens", []):
        b1, b2, k, t = tw["b1"], tw["b2"], tw["k"], tw["t"]
        src = HA.RAW / ("t_%d_%d_%d.png" % (b1, b2, k))
        if not src.is_file():
            continue
        canvas = HA.add_outline(HA.downsample(Image.open(src), scale))
        s1, s2 = asm[str(b1)]["shift_px"], asm[str(b2)]["shift_px"]
        shift = int(round(s1 * (1 - t) + s2 * t))
        yoff = int(round(asm[str(b1)]["y_offset"] * (1 - t) + asm[str(b2)]["y_offset"] * t))
        y0 = m - shift
        px = canvas[max(y0, 0):y0 + HA.CELL, m:m + HA.CELL]
        img = HA.to_image(px)
        if img.height < HA.CELL:
            pad = Image.new("RGBA", (HA.CELL, HA.CELL), (0, 0, 0, 0)); pad.alpha_composite(img, (0, HA.CELL - img.height if y0 < 0 else 0)); img = pad
        index["%d_%d_%d" % (b1, b2, k)] = [len(cells), yoff]
        cells.append(img)
    cols = 16; rows = (len(cells) + cols - 1) // cols
    sheet = Image.new("RGBA", (cols * HA.CELL, rows * HA.CELL), (0, 0, 0, 0))
    for i, img in enumerate(cells):
        sheet.alpha_composite(img, ((i % cols) * HA.CELL, (i // cols) * HA.CELL))
    sheet.save(HERO / "hero_tweens.png", optimize=True)
    (HERO / "hero_tweens.json").write_text(json.dumps({"cell": [HA.CELL, HA.CELL], "cols": cols, "count": len(cells),
        "map": index, "note": "key = b1_b2_k; k=1 at t=1/3, k=2 at t=2/3; value = [cell index, y_offset px]"}, indent=1))
    print("HERO_TWEENS_DONE", len(cells))

if __name__ == "__main__":
    main()
