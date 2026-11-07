"""HD tile pass for the original 24px tiles (t/t.png, tb.png, wtf/wts, h0-2): colour-region
repaint at 4x (smooth contours instead of blocky pixels), per tile with edge padding so tile
borders stay seamless, plus a light painterly shading (soft top light, bottom occlusion, grain)."""
import sys
from pathlib import Path
import numpy as np
from PIL import Image
from scipy import ndimage as ndi
ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT / "scripts"))
import monster_post as MP

PAD = 3

def tile_hd(a, sigma):
    h, w = a.shape[:2]
    p = np.pad(a, ((PAD, PAD), (PAD, PAD), (0, 0)), mode="wrap" if False else "edge")
    out = MP.repaint4x(p, sigma)
    out = out[PAD * 4:PAD * 4 + h * 4, PAD * 4:PAD * 4 + w * 4].astype(np.float32)
    # painterly shading: very light vertical gradient + fine grain on opaque pixels
    yy = np.linspace(1.04, 0.95, h * 4)[:, None]
    rng = np.random.default_rng(1234 + int(a.sum()) % 997)
    grain = 1.0 + (ndi.gaussian_filter(rng.standard_normal((h * 4, w * 4)), 1.2) * 0.035)
    out[..., :3] = np.clip(out[..., :3] * yy[..., None] * grain[..., None], 0, 255)
    # keep fully opaque tiles fully opaque at borders (seamless)
    orig_alpha = np.kron(a[..., 3], np.ones((4, 4)))
    out[..., 3] = np.where(orig_alpha >= 255, 255, out[..., 3])
    return out.astype(np.uint8)

def run(path, tile_h, sigma=1.25):
    src = ROOT / "game/orig_res" / path
    a = np.array(Image.open(src).convert("RGBA"))
    a[a[..., 3] < 128] = 0
    H, W = a.shape[:2]
    th = tile_h if tile_h else H
    rows = []
    for y in range(0, H, th):
        rows.append(tile_hd(a[y:y + th], sigma))
    res = np.concatenate(rows, axis=0)
    dst = ROOT / "game/hd_art" / path
    dst.parent.mkdir(parents=True, exist_ok=True)
    Image.fromarray(res, "RGBA").save(dst, optimize=True)
    print("tiles hd", path, res.shape)

if __name__ == "__main__":
    run("t/t.png", 24)
    for f in ["t/tb.png", "t/wtf.png", "t/wts.png", "t/h0.png", "t/h1.png", "t/h2.png"]:
        if (ROOT / "game/orig_res" / f).is_file():
            run(f, 0)
