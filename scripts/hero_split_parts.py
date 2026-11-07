"""Cut the approved hero style master into layered cutout parts for the Blender rig.

Host python (Pillow + numpy + scipy). Re-runnable:
    python3 scripts/hero_split_parts.py [--debug]

Inputs : art/generated/ninja1-hero-cloud-v1.png (read-only)
Outputs: art/hero/parts/<part>.png  (cropped RGBA, colour-bled under alpha)
         art/hero/parts/parts.json  (crop offsets, pivots, z order, sole points;
                                     all coordinates in SOURCE pixels, y down)
         art/hero/parts/debug_ownership.png (with --debug)

Method
  * Every opaque source pixel is owned by exactly one part (priority order +
    hand-tuned polygons + a colour rule that keeps the gold arm-hole trim on the
    torso).
  * Each part image = its own pixels + "fill": pixels inside its polygon that are
    owned by a part drawn IN FRONT of it (so they are hidden at rest) and joint
    caps (circles clipped to the silhouette of the part that covers the joint).
    Fill colours come from the nearest own pixel (distance-transform inpaint),
    so rotating a limb never opens a transparent gap or shows foreign pixels.
"""

import argparse
import json
from pathlib import Path

import numpy as np
from PIL import Image, ImageDraw, ImageFilter
from scipy import ndimage

ROOT = Path(__file__).resolve().parent.parent
SRC = ROOT / "art/generated/ninja1-hero-cloud-v1.png"
OUT = ROOT / "art/hero/parts"

# z order, back (0) to front. The Blender rig uses the same numbers.
Z = {
    "back_shin": 0, "back_thigh": 1, "front_shin": 2, "front_thigh": 3,
    "back_upper": 4, "back_fore": 5, "torso": 6, "head": 7,
    "front_upper": 8, "front_fore": 9,
}

# Ownership priority (first match wins).
PRIORITY = ["head", "front_fore", "front_upper", "back_fore", "back_upper", "torso",
            "front_thigh", "front_shin", "back_thigh", "back_shin"]

POLY = {
    "head": [(230, 0), (1020, 0), (1020, 470), (800, 470), (770, 515), (725, 548),
             (660, 552), (600, 528), (565, 520), (540, 545), (230, 545)],
    "torso": [(430, 470), (820, 470), (820, 640), (790, 760), (765, 852), (690, 885),
              (655, 899), (560, 866), (500, 830), (470, 760), (430, 640)],
    "back_upper": [(450, 545), (510, 548), (540, 580), (546, 640), (540, 700), (515, 728),
                   (398, 728), (405, 690), (432, 630)],
    "back_fore": [(395, 726), (520, 726), (512, 770), (505, 805), (503, 830), (512, 845),
                  (518, 870), (513, 900), (496, 925), (395, 928)],
    "front_upper": [(740, 640), (760, 655), (795, 700), (800, 744), (733, 744), (736, 700)],
    "front_fore": [(733, 742), (802, 742), (815, 800), (850, 835), (858, 912), (762, 918),
                   (760, 870), (765, 840), (748, 825), (738, 790)],
    "back_thigh": [(415, 800), (612, 800), (628, 900), (618, 960), (605, 1030), (560, 1048),
                   (425, 1048), (410, 990), (425, 900)],
    "back_shin": [(412, 985), (610, 985), (603, 1050), (565, 1068), (558, 1120), (560, 1230),
                  (412, 1230)],
    "front_thigh": [(612, 800), (790, 800), (775, 940), (770, 1005), (705, 1040), (622, 1020),
                    (606, 950)],
    "front_shin": [(612, 1000), (775, 990), (765, 1080), (825, 1140), (830, 1230), (625, 1230),
                   (638, 1078), (618, 1045)],
}

# Joint caps: (cx, cy, r, clip_part) - circle filled with the part's own colours,
# clipped to the (dilated) silhouette of the part that covers that joint.
CAPS = {
    "torso": [(615, 500, 60, "head")],
    "back_upper": [(495, 585, 50, "torso"), (470, 742, 42, "back_fore")],
    "front_upper": [(765, 752, 30, "front_fore")],
    "back_thigh": [(530, 865, 75, "torso")],
    "front_thigh": [(690, 872, 75, "torso")],
    "back_shin": [(505, 1005, 70, "back_thigh")],
    "front_shin": [(695, 1015, 70, "front_thigh")],
}

# Interior erosion (px) for fill colour sources, so fills never copy outline ink.
ERODE = {"back_thigh": 22, "front_thigh": 22, "back_shin": 14, "front_shin": 14,
         "torso": 12, "back_upper": 8, "front_upper": 6}

# Rig pivots in source px. "ground" = point that maps to output (128, 240).
PIVOTS = {
    "ground": (620, 1215),
    "hips": (612, 868),
    "neck": (625, 540),
    "head_top": (625, 40),
    "back_shoulder": (495, 600), "back_elbow": (472, 728), "back_hand": (468, 868),
    "front_shoulder": (757, 655), "front_elbow": (768, 744), "front_hand": (803, 866),
    "back_hip": (530, 890), "back_knee": (505, 1012), "back_ankle": (490, 1120),
    "front_hip": (685, 895), "front_knee": (695, 1025), "front_ankle": (700, 1120),
}

# Points on the boot outlines used by the rig to lock grounded frames to the floor.
SOLE = {
    "back_shin": [(433, 1170), (436, 1214), (480, 1216), (545, 1214), (549, 1185)],
    "front_shin": [(640, 1180), (642, 1214), (720, 1216), (812, 1212), (818, 1180)],
}


def poly_mask(points, shape):
    img = Image.new("L", (shape[1], shape[0]), 0)
    ImageDraw.Draw(img).polygon(points, fill=255)
    return np.array(img) > 0


def circle_mask(cx, cy, r, shape):
    yy, xx = np.ogrid[:shape[0], :shape[1]]
    return (xx - cx) ** 2 + (yy - cy) ** 2 <= r * r


def bleed(rgb, src_mask):
    """Nearest-source colour for every pixel (inpaint by distance transform)."""
    _, (iy, ix) = ndimage.distance_transform_edt(~src_mask, return_indices=True)
    return rgb[iy, ix]


def build(debug=False):
    im = np.array(Image.open(SRC).convert("RGBA"))
    h, w = im.shape[:2]
    rgb = im[..., :3].astype(np.uint8)
    alpha = im[..., 3]
    opaque = alpha > 8

    # --- ownership -------------------------------------------------------------
    owner = np.full((h, w), -1, np.int16)
    polys = {k: poly_mask(v, (h, w)) for k, v in POLY.items()}
    r, g, b = (rgb[..., i].astype(int) for i in range(3))
    gold = ((b < 22) & (r > 80) & (r - b > 70)) | ((b < 55) & (g > 140) & (r > 200))
    for idx, name in enumerate(PRIORITY):
        m = polys[name] & opaque & (owner < 0)
        if name == "back_upper":
            # gold arm-hole trim stays on the torso (covers the shoulder joint)
            m &= ~(gold & (np.arange(h)[:, None] < 700))
        owner[m] = idx
    left = opaque & (owner < 0)
    # pixels outside every polygon: give them to the nearest owned part
    if left.any():
        _, (iy, ix) = ndimage.distance_transform_edt(owner < 0, return_indices=True)
        owner[left] = owner[iy[left], ix[left]]
    own = {name: owner == i for i, name in enumerate(PRIORITY)}

    OUT.mkdir(parents=True, exist_ok=True)
    meta = {"source": str(SRC.relative_to(ROOT)), "source_size": [w, h],
            "coords": "source pixels, origin top-left, y down",
            "z": Z, "pivots": {k: list(v) for k, v in PIVOTS.items()},
            "sole_points": {k: [list(p) for p in v] for k, v in SOLE.items()},
            "parts": {}}

    for name in PRIORITY:
        mine = own[name]
        front = np.zeros((h, w), bool)
        for other in PRIORITY:
            if other != name and Z[other] > Z[name]:
                front |= own[other]
        fill = polys[name] & front
        for cx, cy, rad, clip in CAPS.get(name, []):
            clipm = ndimage.binary_dilation(own[clip], iterations=3)
            fill |= circle_mask(cx, cy, rad, (h, w)) & clipm
        fill &= ~mine
        solid = ndimage.binary_erosion(mine & (alpha > 200), iterations=ERODE.get(name, 8))
        if solid.sum() < 200:
            solid = mine & (alpha > 200)
        col = bleed(rgb, solid)
        a = np.where(mine, alpha, 0).astype(np.float32)
        fa = np.array(Image.fromarray((fill * 255).astype(np.uint8)).filter(
            ImageFilter.GaussianBlur(1.2))).astype(np.float32)
        fa[~ndimage.binary_dilation(fill, iterations=2)] = 0
        a = np.maximum(a, np.where(mine, 0, fa))
        a[fill & ~mine] = np.maximum(a[fill & ~mine], 250)
        out_rgb = np.where(mine[..., None], rgb, col)
        save_part(name, out_rgb, a, meta)

    head_variants(rgb, alpha, own, meta)
    eyes(rgb, alpha, meta)
    (OUT / "parts.json").write_text(json.dumps(meta, indent=2))

    if debug:
        pal = np.array([[230, 60, 60], [60, 200, 60], [60, 60, 230], [230, 200, 40],
                        [200, 60, 200], [40, 200, 200], [250, 140, 40], [140, 80, 30],
                        [120, 120, 250], [250, 250, 250]], np.uint8)
        dbg = np.full((h, w, 3), 40, np.uint8)
        for i in range(len(PRIORITY)):
            dbg[own[PRIORITY[i]]] = pal[i]
        mix = (0.55 * dbg + 0.45 * rgb).astype(np.uint8)
        mix[~opaque] = 40
        Image.fromarray(mix).save(OUT / "debug_ownership.png")
    print("parts written to", OUT)


def save_part(name, rgb, a, meta):
    a8 = np.clip(a, 0, 255).astype(np.uint8)
    ys, xs = np.nonzero(a8 > 0)
    pad = 2
    x0, y0 = max(xs.min() - pad, 0), max(ys.min() - pad, 0)
    x1, y1 = min(xs.max() + pad + 1, a8.shape[1]), min(ys.max() + pad + 1, a8.shape[0])
    crgb, ca = rgb[y0:y1, x0:x1], a8[y0:y1, x0:x1]
    # colour bleed under transparent pixels so bilinear sampling has no dark fringe
    crgb = np.where((ca > 0)[..., None], crgb, bleed(crgb, ca > 0))
    img = np.dstack([crgb, ca])
    Image.fromarray(img.astype(np.uint8), "RGBA").save(OUT / f"{name}.png", optimize=True)
    meta["parts"][name] = {"file": f"{name}.png", "offset": [int(x0), int(y0)],
                           "size": [int(x1 - x0), int(y1 - y0)]}


def head_variants(rgb, alpha, own, meta):
    """head_hurt (frame 21: stunned goggle eyes, open mouth) and head_dig (frame 24)."""
    h, w = alpha.shape
    mine = own["head"]
    base = Image.fromarray(np.dstack([rgb, np.where(mine, alpha, 0)]).astype(np.uint8), "RGBA")
    hurt = base.copy()
    d = ImageDraw.Draw(hurt)
    ink, white = (22, 18, 30, 255), (250, 250, 246, 255)
    # big stunned eyes covering the brows/eyes
    d.ellipse((606, 372, 712, 470), fill=ink)
    d.ellipse((616, 382, 702, 460), fill=white)
    d.ellipse((722, 380, 790, 468), fill=ink)
    d.ellipse((730, 389, 781, 459), fill=white)
    d.ellipse((650, 412, 668, 430), fill=ink)
    d.ellipse((750, 416, 763, 431), fill=ink)
    # open mouth
    d.ellipse((690, 482, 734, 520), fill=ink)
    d.ellipse((697, 489, 727, 514), fill=(150, 30, 40, 255))
    d.ellipse((703, 501, 722, 514), fill=(225, 90, 90, 255))
    # sweat drop
    d.polygon([(812, 360), (800, 392), (824, 392)], fill=ink)
    d.ellipse((798, 380, 826, 408), fill=ink)
    d.polygon([(812, 368), (804, 390), (820, 390)], fill=(170, 220, 255, 255))
    d.ellipse((803, 384, 821, 402), fill=(170, 220, 255, 255))
    arr = np.array(hurt)
    save_part("head_hurt", arr[..., :3], arr[..., 3].astype(np.float32), meta)
    # head_dig: head only, bottom cut flat just under the chin
    arr = np.array(base)
    arr[530:, :, 3] = 0
    save_part("head_dig", arr[..., :3], arr[..., 3].astype(np.float32), meta)


def eyes(rgb, alpha, meta):
    """Eyes-only parts for the stealth frames 25 (open) and 26 (half closed)."""
    h, w = alpha.shape
    src = Image.fromarray(np.dstack([rgb, alpha]).astype(np.uint8), "RGBA")
    specs = [((660, 425), (44, 38)), ((758, 430), (22, 34))]
    for name, half in (("eyes_open", False), ("eyes_half", True)):
        canvas = Image.new("RGBA", (w, h), (0, 0, 0, 0))
        for (cx, cy), (rx, ry) in specs:
            box_o = (cx - rx - 6, cy - ry - 6, cx + rx + 6, cy + ry + 6)
            box_i = (cx - rx, cy - ry, cx + rx, cy + ry)
            outline = Image.new("L", (w, h), 0)
            ImageDraw.Draw(outline).ellipse(box_o, fill=255)
            inner = Image.new("L", (w, h), 0)
            ImageDraw.Draw(inner).ellipse(box_i, fill=255)
            if half:
                lid = cy - int(ry * 0.05)
                ImageDraw.Draw(outline).rectangle((0, 0, w, lid - 7), fill=0)
                ImageDraw.Draw(outline).rectangle((cx - rx - 8, lid - 7, cx + rx + 8, lid + 3), fill=255)
                ImageDraw.Draw(inner).rectangle((0, 0, w, lid + 3), fill=0)
            ink = Image.new("RGBA", (w, h), (22, 18, 30, 255))
            canvas.paste(ink, (0, 0), outline)
            eye = np.array(src).copy()
            er, eg, eb = (eye[..., i].astype(int) for i in range(3))
            skin = (er > 170) & (eg > 110) & (eb < 150) & (er - eb > 60)
            eye[skin] = (248, 248, 244, 255)
            eye[..., 3] = 255
            canvas.paste(Image.fromarray(eye, "RGBA"), (0, 0), inner)
        arr = np.array(canvas)
        save_part(name, arr[..., :3], arr[..., 3].astype(np.float32), meta)


if __name__ == "__main__":
    ap = argparse.ArgumentParser()
    ap.add_argument("--debug", action="store_true")
    build(ap.parse_args().debug)
