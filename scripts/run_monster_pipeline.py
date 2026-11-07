"""One command for the HD monster / NPC sprite set.

    python3 scripts/run_monster_pipeline.py [--skip-blender] [--only m100,m101]

Steps (each bounded by a timeout, status in art/monsters/pipeline-status.json):
  1. blender   scripts/blender_monsters.py -> art/monsters/raw/*.png + art/blender/monsters.blend
  2. post      fit raw renders to the original silhouette bbox (x4), outline, composite the
               original hit-gag layer (goggle eyes / debris), mirror anim-1 frames, fallback
               upscale for everything else -> game/hd_art/{mob,npc}/*.png
  3. verify    exact 4x sizes + bbox registration -> art/monsters/manifest.json
  4. preview   art/monsters/preview.png (original nearest x4 | HD, per monster type)
"""
import argparse
import glob
import json
import os
import shutil
import subprocess
import sys
import time
from datetime import datetime, timezone
from pathlib import Path

import numpy as np
from PIL import Image, ImageDraw

ROOT = Path(__file__).resolve().parent.parent
sys.path.insert(0, str(ROOT / "scripts"))
import monster_post as mp  # noqa: E402

SRC = ROOT / "analysis/NinjaSchool1/resources"
OUT = ROOT / "game/hd_art"
ART = ROOT / "art/monsters"
RAW = ART / "raw"
BLEND = ROOT / "art/blender/monsters.blend"
STATUS = ART / "pipeline-status.json"
BLENDER = "/Applications/Blender.app/Contents/MacOS/Blender"
OUTLINE_PX = 1.5
OUTLINE_RGB = (26, 14, 22)
# hit frames: 3D body + original gag layer; per-type extra gag colours
HIT = {"m102": {}, "m402": {}, "m502": {}, "m202": {}, "m602": {},
       "m1402": {"extra": [(0xc1, 0xc1, 0xc1), (0xe5, 0xe5, 0xe5)],
                 "anchors": [(0xc1, 0xc1, 0xc1)]},
       "m1902": {}}
NAMES = {0: "Ma mot mat", 1: "Oc sen", 2: "Doi quy", 3: "Quy mot chan", 4: "Coc xanh", 5: "Nhen",
         6: "Ong bo ve", 7: "Bu nhin rom", 8: "Ca cop", 9: "Ninja gio", 10: "Ca cop do",
         11: "Ma mot mat+", 12: "Ret sa mac", 13: "Doi vang", 14: "Chuot", 15: "Tho dan",
         16: "Ong do", 17: "Bo cap", 18: "Quy hoa", 19: "Quy tu hoa"}


def now():
    return datetime.now(timezone.utc).isoformat(timespec="seconds")


def write_status(st):
    ART.mkdir(parents=True, exist_ok=True)
    STATUS.write_text(json.dumps(st, indent=2))


def parse_mob(name):
    """m<type><anim><frame> -> (type, anim, frame) or None."""
    if not name.startswith("m") or len(name) < 4:
        return None
    return int(name[1:-2]), int(name[-2]), int(name[-1])


def all_sources():
    fs = sorted(glob.glob(str(SRC / "mob/*.png"))) + sorted(glob.glob(str(SRC / "npc/*.png")))
    return [Path(f) for f in fs]


def check_bbox(orig, hd):
    ob = mp.bbox(orig[..., 3] >= 128)
    hb = mp.bbox(hd[..., 3] >= 128)
    hs = [v / mp.S for v in hb]
    d = {"left": hs[0] - ob[0], "top": hs[1] - ob[1], "right": hs[2] - ob[2], "bottom": hs[3] - ob[3],
         "centre_x": (hs[0] + hs[2]) / 2 - (ob[0] + ob[2]) / 2}
    ok = (abs(d["bottom"]) <= 2 and abs(d["centre_x"]) <= 2
          and all(abs(d[k]) <= 3 for k in ("left", "top", "right")))
    return {"orig": ob, "hd_scaled": [round(v, 2) for v in hs],
            "delta": {k: round(v, 2) for k, v in d.items()}, "ok": bool(ok)}


def build_blender_frame(name, orig):
    raw = np.array(Image.open(RAW / (name + ".png")).convert("RGBA"))
    h, w = orig.shape[:2]
    cfg = HIT.get(name)
    eff = mp.effect_mask(orig, **cfg) if cfg is not None else np.zeros(orig.shape[:2], bool)
    body = (orig[..., 3] >= 128) & ~eff
    b = mp.bbox(body)
    s = int(round(OUTLINE_PX - 0.5))
    box = [b[0] * 4 + s, b[1] * 4 + s, b[2] * 4 - s, b[3] * 4 - s]
    hd = mp.fit_render(raw, box, (w * 4, h * 4))
    hd = mp.outline(hd, OUTLINE_PX, OUTLINE_RGB)
    rb = mp.bbox(raw[..., 3] > 8)
    aspect = ((rb[2] - rb[0]) / (rb[3] - rb[1])) / ((b[2] - b[0]) / (b[3] - b[1]))
    if eff.any():
        layer = orig.copy()
        layer[~eff] = 0
        hd = mp.over(hd, mp.repaint4x(layer))
    return hd, round(float(aspect), 3), bool(eff.any())


def post(only=None):
    OUT.mkdir(parents=True, exist_ok=True)
    entries, built = [], {}
    srcs = all_sources()
    blender_names = {p.stem for p in RAW.glob("*.png")}
    # pass 1: anim-0 / non-mirror frames
    order = sorted(srcs, key=lambda p: (parse_mob(p.stem) or (0, 0, 0))[1])
    for p in order:
        rel = p.relative_to(SRC)
        orig = mp.load(p)
        info = parse_mob(p.stem) if p.parent.name == "mob" else None
        entry = {"source": str(p.relative_to(ROOT)), "hd": str((OUT / rel).relative_to(ROOT))}
        mirror_of = None
        if info and info[1] == 1:
            src0 = p.parent / ("m%d0%d.png" % (info[0], info[2]))
            if src0.exists():
                o0 = mp.load(src0)
                if (o0.shape == orig.shape and np.array_equal(o0[:, ::-1, 3] >= 128, orig[..., 3] >= 128)
                        and np.abs(o0[:, ::-1, :3].astype(int) - orig[..., :3]).max(initial=0) <= 16):
                    mirror_of = src0.stem
        if mirror_of and mirror_of in built:
            hd = built[mirror_of][0][:, ::-1].copy()
            entry["method"] = built[mirror_of][1]
            entry["note"] = "horizontal mirror of %s (original is a mirror, colour diff <= 16)" % mirror_of
        elif p.parent.name == "mob" and p.stem in blender_names:
            hd, aspect, gag = build_blender_frame(p.stem, orig)
            entry["method"] = "blender"
            entry["render_aspect_vs_orig"] = aspect
            if gag:
                entry["note"] = "3D body + original hit-gag layer (goggle eyes/debris) via fallback upscale"
        else:
            hd = mp.repaint4x(orig)
            entry["method"] = "fallback_upscale"
        built[p.stem if p.parent.name == "mob" else "npc/" + p.stem] = (hd, entry["method"])
        dst = OUT / rel
        dst.parent.mkdir(parents=True, exist_ok=True)
        Image.fromarray(hd).save(dst)
        size_ok = hd.shape[1] == orig.shape[1] * 4 and hd.shape[0] == orig.shape[0] * 4
        entry["size"] = {"orig": [orig.shape[1], orig.shape[0]], "hd": [hd.shape[1], hd.shape[0]], "exact_4x": size_ok}
        entry["bbox_check"] = check_bbox(orig, hd)
        entries.append(entry)
    entries.sort(key=lambda e: e["source"])
    return entries


def preview(entries):
    rows = {}
    for e in entries:
        st = Path(e["source"]).stem
        info = parse_mob(st) if "/mob/" in e["source"] else None
        key = ("m%02d" % info[0]) if info else ("npc" if "/npc/" in e["source"] else st[0])
        rows.setdefault(key, []).append(e)
    pad, lab = 6, 14
    tiles = []
    for key in sorted(rows):
        row = []
        for e in rows[key]:
            o = Image.open(ROOT / e["source"]).convert("RGBA")
            o = o.resize((o.width * 4, o.height * 4), Image.NEAREST)
            h = Image.open(ROOT / e["hd"]).convert("RGBA")
            row.append((Path(e["source"]).stem, e["method"], o, h))
        tiles.append((key, row))
    W = max(sum(o.width * 2 + pad * 3 for _, _, o, _ in r) for _, r in tiles) + 120
    H = sum(max(o.height for _, _, o, _ in r) + lab + pad * 2 for _, r in tiles)
    img = Image.new("RGBA", (W, H), (96, 112, 104, 255))
    d = ImageDraw.Draw(img)
    y = 0
    for key, r in tiles:
        rh = max(o.height for _, _, o, _ in r) + lab + pad * 2
        name = NAMES.get(int(key[1:]), "") if key.startswith("m") and key[1:].isdigit() else {"b": "boss", "x": "fx x", "y": "fx y", "npc": "npc"}.get(key, key)
        d.text((4, y + 4), key, fill=(255, 255, 255))
        d.text((4, y + 18), name, fill=(230, 230, 200))
        x = 120
        for st, meth, o, h in r:
            d.rectangle([x - 2, y + lab, x + o.width * 2 + pad + 2, y + rh - 2], fill=(84, 98, 92, 255))
            d.text((x, y + 2), "%s %s" % (st, {"blender": "3D", "fallback_upscale": "fb", "cutout": "cut"}[meth]),
                   fill=(255, 255, 160) if meth == "blender" else (220, 220, 220))
            img.alpha_composite(o, (x, y + lab + pad))
            img.alpha_composite(h, (x + o.width + pad, y + lab + pad))
            x += o.width * 2 + pad * 3
        y += rh
    img.save(ART / "preview.png")


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--skip-blender", action="store_true")
    ap.add_argument("--only", default=None, help="comma list of frames to re-render in Blender")
    ap.add_argument("--timeout", type=int, default=900)
    a = ap.parse_args()
    st = {"started": now(), "state": "running", "steps": {}}
    write_status(st)
    try:
        if not a.skip_blender:
            t = time.time()
            exe = shutil.which("blender") or BLENDER
            cmd = [exe, "--background", "--factory-startup", "--python", str(ROOT / "scripts/blender_monsters.py"),
                   "--", "--out", str(RAW)]
            if a.only:
                cmd += ["--only", a.only]
            else:
                cmd += ["--blend", str(BLEND)]
            r = subprocess.run(cmd, cwd=ROOT, capture_output=True, text=True, timeout=a.timeout)
            ok = r.returncode == 0 and "Traceback" not in r.stdout + r.stderr
            st["steps"]["blender"] = {"ok": ok, "seconds": round(time.time() - t, 1),
                                      "rendered": r.stdout.count("RENDERED"),
                                      "tail": (r.stdout + r.stderr)[-1500:] if not ok else ""}
            write_status(st)
            if not ok:
                raise RuntimeError("blender step failed")
        t = time.time()
        entries = post()
        man = {"generated": now(), "scale": 4, "count": len(entries),
               "methods": {m: sum(e["method"] == m for e in entries) for m in ("blender", "cutout", "fallback_upscale")},
               "all_exact_4x": all(e["size"]["exact_4x"] for e in entries),
               "all_bbox_ok": all(e["bbox_check"]["ok"] for e in entries),
               "files": entries}
        (ART / "manifest.json").write_text(json.dumps(man, indent=1))
        st["steps"]["post"] = {"ok": True, "seconds": round(time.time() - t, 1), "files": len(entries),
                               "methods": man["methods"]}
        bad = [e["source"] for e in entries if not (e["size"]["exact_4x"] and e["bbox_check"]["ok"])]
        st["steps"]["verify"] = {"ok": not bad, "all_exact_4x": man["all_exact_4x"],
                                 "all_bbox_ok": man["all_bbox_ok"], "failures": bad}
        t = time.time()
        preview(entries)
        st["steps"]["preview"] = {"ok": True, "seconds": round(time.time() - t, 1)}
        st["state"] = "passed" if not bad else "failed"
    except Exception as ex:  # noqa: BLE001
        st["state"] = "failed"
        st["error"] = repr(ex)
    st["finished"] = now()
    write_status(st)
    print(json.dumps({k: v for k, v in st.items()}, indent=1)[:3000])
    return 0 if st["state"] == "passed" else 1


if __name__ == "__main__":
    sys.exit(main())
