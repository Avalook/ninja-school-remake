"""art/cast/mob_preview.png: per frame original (nearest 4x) | current game fallback | new local HD."""
import sys
from pathlib import Path
from PIL import Image, ImageDraw
ROOT = Path(__file__).resolve().parents[2]
names = sorted(p.stem for p in (ROOT / "art/cast/mob").glob("*.png"))
names = [n for n in names if not (len(n) >= 4 and n.startswith("m") and n[-2] == "1")] if "--anim0" in sys.argv else names
T = 200
cols = 3
img = Image.new("RGBA", (T * 3 * cols, (T + 14) * ((len(names) + cols - 1) // cols)), (40, 40, 48, 255))
d = ImageDraw.Draw(img)
for i, n in enumerate(names):
    x0, y0 = (i % cols) * T * 3, (i // cols) * (T + 14)
    d.text((x0 + 3, y0 + 1), n, fill=(230, 230, 230))
    for k, p in enumerate([ROOT / f"game/orig_res/mob/{n}.png", ROOT / f"game/hd_art/mob/{n}.png", ROOT / f"art/cast/mob/{n}.png"]):
        t = Image.new("RGBA", (T - 4, T - 4), (118, 118, 138, 255))
        if p.is_file():
            im = Image.open(p).convert("RGBA")
            s = min((T - 8) / im.width, (T - 8) / im.height)
            im = im.resize((max(1, int(im.width * s)), max(1, int(im.height * s))), Image.NEAREST)
            t.alpha_composite(im, ((t.width - im.width) // 2, t.height - im.height - 2))
        img.paste(t, (x0 + k * T, y0 + 14))
img.convert("RGB").save(ROOT / "art/cast/mob_preview.png")
print(len(names))
