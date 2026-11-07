"""Showcase video of the HD hero (Blender rig: 46 key frames + 210 in-betweens) performing every
original animation at the original tick timing (25 Hz logic), displayed at 60 fps.
Output: builds/video/hero_showcase.mp4 (1920x1080, H.264). Usage: python3 tools/art/hero_showcase.py"""
import json, subprocess, sys
from pathlib import Path
from PIL import Image, ImageDraw, ImageFont

ROOT = Path(__file__).resolve().parents[2]
HERO = ROOT / "art/hero"
OUT = ROOT / "builds/video/hero_showcase.mp4"
W, H, FPS, TICK = 1920, 1080, 60, 0.04
SCALE = 2.0

key = Image.open(HERO / "hero_sheet.png").convert("RGBA")
meta = json.loads((HERO / "hero_sheet.json").read_text())
tw = Image.open(HERO / "hero_tweens.png").convert("RGBA")
twm = json.loads((HERO / "hero_tweens.json").read_text())
orig = Image.open(ROOT / "art/references/ninja1-hero-46-frames-original.png").convert("RGBA")
CELL = 256

def key_cell(b):
    return key.crop(((b % 8) * CELL, (b // 8) * CELL, (b % 8) * CELL + CELL, (b // 8) * CELL + CELL)), meta["frame_y_offset"][b]

def tween_cell(b1, b2, k):
    v = twm["map"].get("%d_%d_%d" % (b1, b2, k))
    if v is None:
        return None
    i, yo = v
    c = twm["cols"]
    return tw.crop(((i % c) * CELL, (i // c) * CELL, (i % c) * CELL + CELL, (i // c) * CELL + CELL)), yo

def orig_cell(b):
    c, r = b % 12, b // 12
    return orig.crop((c * 160 + 20, r * 140 + 24, c * 160 + 140, r * 140 + 136))

def pose_at(prev_b, cur_b, alpha):
    """same rule as the game renderer: one tick behind, blend prev->cur with in-betweens"""
    if prev_b == cur_b or alpha >= 5 / 6:
        return key_cell(cur_b)
    if alpha < 1 / 6:
        return key_cell(prev_b)
    t = tween_cell(prev_b, cur_b, 1 if alpha < 0.5 else 2)
    return t if t else key_cell(cur_b if alpha >= 0.5 else prev_b)

def seq(frames_ticks, loops=1):
    out = []
    for _ in range(loops):
        for b, n in frames_ticks:
            out += [b] * n
    return out

# (Vietnamese title, tick sequence of B frames) — timings follow the original state machine
SECTIONS = [
    ("Đứng thở (B0-1)", seq([(0, 5), (1, 10)], 3)),
    ("Chạy (B2-6)", seq([(2, 2), (3, 2), (4, 2), (5, 2), (6, 2)], 6)),
    ("Nhảy lên / rơi / tiếp đất (B7, B23, B12)", seq([(0, 4), (7, 10), (23, 8), (12, 6), (0, 8)], 2)),
    ("Khinh công: lộn nhào (B8-11)", seq([(7, 4)]) + seq([(8, 2), (9, 2), (10, 2), (11, 2)], 4) + seq([(23, 6), (0, 6)])),
    ("Chém kiếm combo (B13-16)", seq([(0, 4), (13, 3), (14, 3), (15, 3), (16, 3)], 4) + seq([(0, 6)])),
    ("Phóng shuriken (B17-20)", seq([(0, 4), (17, 3), (18, 3), (19, 3), (20, 3)], 3) + seq([(0, 6)])),
    ("Chém trên không (B27-34)", seq([(7, 4)] + [(b, 2) for b in range(27, 35)] + [(23, 4)], 3)),
    ("Vừa chạy vừa chém (B35-42)", seq([(b, 2) for b in range(35, 43)], 4)),
    ("Trúng đòn / choáng (B21)", seq([(0, 4), (21, 12), (23, 6), (0, 8)], 2)),
    ("Ngồi xuống & độn thổ (B22, B24)", seq([(0, 6), (22, 10), (24, 20), (22, 8), (0, 8)])),
    ("Ẩn thân (B25-26)", seq([(0, 6)]) + seq([(25, 6), (26, 3)], 5) + seq([(0, 6)])),
    ("Nhảy cao biến thể (B43-45)", seq([(0, 4), (43, 6), (44, 6), (45, 8), (23, 6), (0, 6)], 2)),
]

def font(sz, bold=True):
    for p in ["/System/Library/Fonts/Supplemental/Arial Bold.ttf" if bold else "/System/Library/Fonts/Supplemental/Arial.ttf",
              "/System/Library/Fonts/Helvetica.ttc"]:
        try:
            return ImageFont.truetype(p, sz)
        except OSError:
            pass
    return ImageFont.load_default()

def background():
    bg = Image.new("RGBA", (W, H), (30, 30, 40, 255))
    d = ROOT / "art/env/school/day"
    for i in range(4):
        p = d / f"layer{i}.png"
        if p.is_file():
            L = Image.open(p).convert("RGBA").resize((W, H), Image.LANCZOS)
            if i == 3:
                L.putalpha(L.getchannel("A").point(lambda a: int(a * 0.6)))
            bg.alpha_composite(L)
    shade = Image.new("RGBA", (W, H), (0, 0, 0, 70))
    bg.alpha_composite(shade)
    dr = ImageDraw.Draw(bg)
    dr.rectangle((0, 900, W, H), fill=(60, 45, 30, 230))
    dr.rectangle((0, 896, W, 904), fill=(120, 190, 70, 255))
    return bg

def main():
    OUT.parent.mkdir(parents=True, exist_ok=True)
    bg = background()
    ft, fs, fsm = font(56), font(30, False), font(26, False)
    cmd = ["ffmpeg", "-loglevel", "error", "-y", "-f", "rawvideo", "-pix_fmt", "rgba", "-s", f"{W}x{H}", "-r", str(FPS),
           "-i", "-", "-c:v", "libx264", "-pix_fmt", "yuv420p", "-crf", "18", "-preset", "medium", str(OUT)]
    p = subprocess.Popen(cmd, stdin=subprocess.PIPE)
    total = 0
    for si, (title, ticks) in enumerate(SECTIONS):
        nframes = int(len(ticks) * TICK * FPS)
        for f in range(nframes):
            t = f / FPS
            ti = int(t / TICK)
            alpha = (t / TICK) - ti
            cur = ticks[min(ti, len(ticks) - 1)]
            prev = ticks[max(0, min(ti - 1, len(ticks) - 1))]
            img, yoff = pose_at(prev, cur, alpha)
            fr = bg.copy()
            hd = img.resize((int(CELL * SCALE), int(CELL * SCALE)), Image.LANCZOS)
            ax, ay = 1000, 900   # ground contact on screen
            fr.alpha_composite(hd, (int(ax - 128 * SCALE), int(ay - 240 * SCALE + yoff * SCALE)))
            # original pixel frame inset
            oc = orig_cell(cur).resize((240 * 2, 224 * 2), Image.NEAREST)
            box = Image.new("RGBA", (500, 470), (0, 0, 0, 150))
            fr.alpha_composite(box, (60, 330))
            fr.alpha_composite(oc, (70, 340))
            d = ImageDraw.Draw(fr)
            d.text((80, 810), "Khung gốc J2ME  B=%d" % cur, font=fsm, fill=(255, 230, 120, 255))
            d.text((60, 60), "Ninja School 1 — PC Remake: bộ chuyển động nhân vật", font=fs, fill=(255, 255, 255, 255))
            d.text((60, 110), "%d/%d  %s" % (si + 1, len(SECTIONS), title), font=ft, fill=(255, 215, 60, 255))
            d.text((60, 190), "Blender cutout rig · 46 khung chính + 210 khung chuyển tiếp · nhịp gốc 25 Hz hiển thị 60 fps",
                   font=fsm, fill=(220, 220, 230, 255))
            p.stdin.write(fr.tobytes())
            total += 1
    p.stdin.close(); p.wait()
    print("wrote", OUT, total, "frames", round(total / FPS, 1), "s")

if __name__ == "__main__":
    main()
