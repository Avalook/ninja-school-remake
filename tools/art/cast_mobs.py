"""Regenerate the monster frames whose HD is still `fallback_upscale` (art/monsters/manifest.json)
with the local FLUX.2 Klein edit model, keeping the exact-4x canvas + registration contract of
scripts/monster_post.py / scripts/run_monster_pipeline.py:

  * canvas = 4 x original size; the generated body is fitted into 4 x the original body bbox
    (inset by the outline width) with mp.fit_render, then mp.outline(1.5 px) like the Blender set;
  * hit frames (frame 2): the shared "hit gag" pixels (goggle eyes, sparks) are masked out of the
    model input with mp.effect_mask and composited back on top as mp.repaint4x, like Blender frames;
  * anim-1 frames that are exact mirrors of anim-0 in the original are mirrored, not regenerated;
  * tiny parts (< 12 px on a side) keep the fallback (nothing for the model to work with).
  * NPC body/leg parts (npc/<i>0, <i>1) are cut from the approved HD master art/cast/npc<i>0.png
    fitted onto the original standing composite (legs at y, body at y - lJ), so the game's
    two-part NPC draw (with its 1 px breathing bob) keeps working.

    python3 tools/art/cast_mobs.py [--only m000,m1100] [--force] [--tries 3]
Writes art/cast/mob/<same file names>, art/cast/npc/<same names>, logs to art/cast/jobs.json.
"""
import argparse
import json
import sys
from pathlib import Path

import numpy as np
from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))
sys.path.insert(0, str(Path(__file__).resolve().parents[2] / "scripts"))
import cast_lib as L  # noqa: E402
import cast_qa as Q  # noqa: E402
import monster_post as mp  # noqa: E402  (read-only reuse of the registration contract)

OUTLINE_PX = 1.5
OUTLINE_RGB = (26, 14, 22)
MAN = L.ROOT / "art/monsters/manifest.json"
OUT = L.CAST / "mob"
OUT_NPC = L.CAST / "npc"

DESC = {
    "m0": "a one-eyed cyclops caveman with orange-tan skin, one big round eye, bald head and a green "
          "leaf loincloth, walking",
    "m11": "a tan one-eyed mud creature crawling half out of the ground, open red mouth",
    "m12": "a red centipede monster with yellow-and-black striped legs, blue belly and black head",
    "m13": "a golden moth monster with white and yellow wings and a brown body",
    "m15": "a tribal warrior with a huge brown leaf-thatch mask covering head and body, big white "
           "eyes, holding a wooden spear with a steel tip, green grass skirt, bare legs",
    "m16": "an orange hornet with dark stripes and pale blue transparent wings, flying",
    "m17": "a golden scorpion with a curled segmented tail and sting, red claws and a blue shell",
    "m18": "a red carnivorous flower monster with toothed petals and green leaf legs, recoiling",
    "m3": "a green-skinned goblin ghoul with dark spiky hair, hunched, leaf loincloth",
    "m9": "a ronin swordsman with a wide straw conical hat hiding his eyes, dark grey robe, red sword",
    "y0": "a black-clad ninja with a hood and mask, only eyes visible, shooting a bow",
    "y1": "a black-clad ninja with a hood and mask throwing shuriken",
    "y2": "a black-clad ninja with a hood and mask slashing with a short sword",
    "y3": "a red-clad ninja with a hood and mask, only eyes visible, lunging low with a long spear",
    "y4": "a red-clad ninja with a hood and mask, only eyes visible, shooting a bow",
    "y5": "a red-clad ninja with a hood and mask throwing shuriken",
    "y6": "a red-clad ninja with a hood and mask slashing with a short sword",
    "x0": "the dark trousers and red belt of a defeated ninja lying on the ground",
    "x1": "the dark trousers and red belt of a defeated ninja lying on the ground",
    "x2": "a defeated dark grey wolf lying flat on the ground",
    "x3": "a defeated dark grey wolf lying flat on the ground",
    "b0": "a giant demonic wild boar, dark reddish-brown bristly fur with a spiky mane, curved white "
          "tusks, angry yellow eye",
    "b03": "the head of a giant dark brown boar knocked out, dazed white eye, tongue out, red drops",
    "b10": "a giant blue river squid monster with a pointed head, angry white eyes and curling tentacles",
    "b13": "a single curling blue tentacle tip",
    "b20": "a giant carved wooden fist",
    "b23": "a giant carved wooden fist",
    "b21": "a big carved wooden foot block",
    "b22": "the round body of a wooden golem made of carved wood with round shoulders and a purple belt",
    "d0": "a grey and white wolf-dog (husky-like) with blue eyes and a fluffy raised tail, standing",
    "d1": "a grey and white wolf-dog (husky-like) with blue eyes and a fluffy raised tail, walking",
}
# boss frames: the approved Codex master is passed as a SECOND reference image (identity + style),
# the original frame (image 1) gives pose/silhouette. Codex files are read-only.
CODEX = L.ROOT / "art/generated/codex_hd"
BOSS_REF = {"b00": "boss_boar", "b01": "boss_boar", "b02": "boss_boar", "b03": "boss_boar_hurt",
            "b10": "boss_squid", "b13": "boss_squid", "b20": "boss_golem_parts",
            "b21": "boss_golem_parts", "b22": "boss_golem_parts", "b23": "boss_golem_parts"}
TPL2 = ("Redraw the pixel-art sprite in image 1 as a high-resolution game sprite of the same "
        "creature as in image 2, in exactly the art style of image 2 (crisp cel shading, clean bold "
        "dark outlines). Keep exactly the pose, silhouette, proportions, facing direction and "
        "colours of image 1: {desc}. Show only this one element. Plain flat {bg} background, no "
        "shadow on the ground, no text.")
TPL = ("Redraw this pixel-art game sprite as a high-resolution 2D anime game sprite illustration: "
       "crisp cel shading with sharp shadow shapes, clean bold dark outlines, saturated colours. "
       "Keep exactly the same pose, silhouette, proportions, facing direction and colours as the "
       "sprite: {desc}. Plain flat {bg} background, no shadow on the ground, no text.")


def desc_for(stem):
    for k in sorted(DESC, key=len, reverse=True):
        if stem.startswith(k):
            return DESC[k]
    return None


def parse_mob(stem):
    if not stem.startswith("m") or len(stem) < 4:
        return None
    return int(stem[1:-2]), int(stem[-2]), int(stem[-1])


def targets():
    man = json.loads(MAN.read_text())
    return [Path(x["hd"]).relative_to("game/hd_art") for x in man["files"]
            if x["method"] == "fallback_upscale"]


def is_mirror(stem):
    info = parse_mob(stem)
    if not info or info[1] != 1:
        return None
    s0 = "m%d0%d" % (info[0], info[2])
    p0 = L.ORIG / f"mob/{s0}.png"
    if not p0.exists():
        return None
    a = mp.load(L.ORIG / f"mob/{stem}.png")
    b = mp.load(p0)
    if a.shape == b.shape and np.array_equal(b[:, ::-1, 3] >= 128, a[..., 3] >= 128) and \
            np.abs(b[:, ::-1, :3].astype(int) - a[..., :3]).max(initial=0) <= 16:
        return s0
    return None


def hit_frame(stem):
    info = parse_mob(stem)
    return (info is not None and info[2] == 2) or stem == "b03"


def gen_frame(stem, tries=3, force=False, reuse=False, consistent=False):
    out = OUT / f"{stem}.png"
    if out.is_file() and not force:
        return "skip"
    orig = mp.load(L.ORIG / f"mob/{stem}.png")
    h, w = orig.shape[:2]
    desc = desc_for(stem)
    if desc is None or min(w, h) < 12 and stem not in ("x0", "x1"):
        return "keep_fallback"
    eff = mp.effect_mask(orig) if hit_frame(stem) else np.zeros((h, w), bool)
    body = orig.copy()
    body[eff] = 0
    bimg = Image.fromarray(body)
    sprite = L.trim(bimg)
    bgname = L.pick_bg(sprite)
    bgrgb, bgtext = L.BGS[bgname]
    inp, s = L.make_input(sprite, 512, bgrgb, fill=0.8, feet=0.9)
    inp_path = L.WORK / f"in_mob_{stem}.png"
    inp.save(inp_path)
    ref_path = L.WORK / f"orig_mob_{stem}.png"
    bimg.save(ref_path)
    prompt = TPL.format(desc=desc, bg=bgtext)
    images = [inp_path]
    if stem in BOSS_REF:
        ref2 = Image.open(CODEX / f"{BOSS_REF[stem]}.png").convert("RGBA")
        c2 = Image.new("RGBA", ref2.size, tuple(bgrgb) + (255,))
        c2.alpha_composite(ref2)
        r2p = L.WORK / f"ref2_{BOSS_REF[stem]}_{bgname}.png"
        c2.convert("RGB").resize((512, 512), Image.LANCZOS).save(r2p)
        images.append(r2p)
        prompt = TPL2.format(desc=desc, bg=bgtext)
    tag = "raw"
    info = parse_mob(stem)
    if consistent and info and info[2] > 0 and (OUT / ("m%d0%d.png" % (info[0], 0))).is_file():
        # identity lock: the accepted HD of frame 0 of the same monster is the 2nd reference
        f0 = Image.open(OUT / ("m%d00.png" % info[0])).convert("RGBA")
        f0 = L.trim(f0)
        f0 = f0.resize((f0.width * 3, f0.height * 3), Image.LANCZOS)
        c2, _ = L.make_input(f0, 512, bgrgb, fill=0.8, feet=0.9)
        r2p = L.WORK / f"ref2_m{info[0]}00_{bgname}.png"
        c2.save(r2p)
        images.append(r2p)
        prompt = TPL2.format(desc=desc, bg=bgtext)
        tag = "rawc"
    b = mp.bbox(body[..., 3] >= 128)
    si = int(round(OUTLINE_PX - 0.5))
    box = [b[0] * 4 + si, b[1] * 4 + si, b[2] * 4 - si, b[3] * 4 - si]
    best = None
    for t in range(1, tries + 1):
        seed = L.BASE_SEED + 1000 * (t - 1)
        raw = L.WORK / f"{tag}_mob_{stem}_t{t}.png"
        if reuse and not raw.is_file():
            break
        if reuse:
            dt = "rematte"  # re-key an existing raw with the current matte code (no generation)
        else:
            dt, _ = L.run_edit(images, prompt, raw, seed, size=(512, 512))
        m = np.array(L.matte(Image.open(raw), bgname))
        hd = mp.fit_render(m, box, (w * 4, h * 4))
        hd = mp.outline(hd, OUTLINE_PX, OUTLINE_RGB)
        if eff.any():
            layer = orig.copy()
            layer[~eff] = 0
            hd = mp.over(hd, mp.repaint4x(layer))
        cand = L.WORK / f"{'hdc' if tag == 'rawc' else 'hd'}_mob_{stem}_t{t}.png"
        Image.fromarray(hd).save(cand)
        # QA against the body-only original (raw aspect: before fit_render squashes it into the box)
        rb = mp.bbox(m[..., 3] > 8)
        qa = Q.score(bimg, Image.fromarray(m[rb[1]:rb[3], rb[0]:rb[2]]), "mob", bgname)
        qa["exact_4x"] = hd.shape[:2] == (h * 4, w * 4)
        rec = {"id": f"mob/{stem}", "refs": [str(Path(i).relative_to(L.ROOT)) for i in images], "try": t, "seed": seed, "steps": L.STEPS, "size": [512, 512],
               "kind": "mob", "bg": bgname, "input": str(inp_path.relative_to(L.ROOT)),
               "orig_ref": str(ref_path.relative_to(L.ROOT)), "prompt": prompt,
               "raw": str(raw.relative_to(L.ROOT)), "candidate": str(cand.relative_to(L.ROOT)),
               "output": str(out.relative_to(L.ROOT)), "seconds": dt, "qa": qa,
               "hit_gag_overlay": bool(eff.any()), "accepted": False, "time": L.now()}
        L.log_job(rec)
        print(stem, "try", t, dt, "s", "OK" if qa["ok"] else qa["flags"], qa["palette"], flush=True)
        rank = (len(qa["flags"]), qa["palette"])
        if best is None or rank < best[0]:
            best = (rank, cand, rec)
        if qa["ok"]:
            break
    if best is None:
        return "no_raw"
    _, cand, rec = best
    OUT.mkdir(parents=True, exist_ok=True)
    Image.open(cand).save(out)
    L.log_job(dict(rec, accepted=True))
    return "generated"


def mirror_frame(stem, src):
    a = np.array(Image.open(OUT / f"{src}.png"))[:, ::-1].copy()
    Image.fromarray(a).save(OUT / f"{stem}.png")
    return "mirror_of_" + src


def npc_parts(i):
    """cut HD body/legs parts for NPC i from its master, registered on the original composite."""
    master = L.CAST / f"npc{i}0.png"
    if not master.is_file():
        return "no_master"
    lj = L.NPC_LJ[i]
    b = mp.load(L.ORIG / f"npc/{i}0.png")
    l = mp.load(L.ORIG / f"npc/{i}1.png")
    comp = np.array(L.npc_composite(i))
    H, W = comp.shape[:2]
    cb = mp.bbox(comp[..., 3] >= 128)
    m = np.array(L.trim(Image.open(master).convert("RGBA")))
    # fit master into 4x composite bbox (keep its aspect; anchor bottom-centre)
    bw, bh = (cb[2] - cb[0]) * 4, (cb[3] - cb[1]) * 4
    s = bh / m.shape[0]
    mw = max(1, round(m.shape[1] * s))
    mi = Image.fromarray(m).resize((mw, bh), Image.LANCZOS)
    big = Image.new("RGBA", (max(W * 4, mw + 8), H * 4), (0, 0, 0, 0))
    cx = (cb[0] + cb[2]) * 2 + (big.width - W * 4) // 2
    big.alpha_composite(mi, (cx - mw // 2, cb[1] * 4))
    big = np.array(big)
    off = (big.shape[1] - W * 4) // 2
    cut = (H - lj) * 4  # rows below this belong to the legs part
    res = {}
    for part, src, top in (("0", b, H - lj - b.shape[0]), ("1", l, H - l.shape[0])):
        ph, pw = src.shape[0] * 4, src.shape[1] * 4
        x0 = (W * 2 - pw // 2) + off - ((W - src.shape[1]) % 2) * 2 * 0
        region = big[top * 4: top * 4 + ph].copy()
        rows = np.arange(top * 4, top * 4 + ph)
        if part == "0":
            region[rows >= cut] = 0
        else:
            region[rows < cut] = 0
        # horizontal: centre the part like the game (anchor HCENTER) and widen if the HD art is wider
        width = max(pw, 4)
        xs = cx - width // 2
        crop = region[:, max(0, xs): xs + width]
        canvas = np.zeros((ph, pw, 4), np.uint8)
        cw = min(pw, crop.shape[1])
        canvas[:, :cw] = crop[:, :cw]
        OUT_NPC.mkdir(parents=True, exist_ok=True)
        Image.fromarray(canvas).save(OUT_NPC / f"{i}{part}.png")
        res[f"{i}{part}"] = [pw, ph]
    return res


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--only", default="")
    ap.add_argument("--force", action="store_true")
    ap.add_argument("--tries", type=int, default=3)
    ap.add_argument("--rematte", action="store_true", help="re-key existing raws, no generation")
    ap.add_argument("--consistent", action="store_true", help="frame 0 HD as 2nd reference for frames 1..3")
    a = ap.parse_args()
    only = set(x for x in a.only.split(",") if x)
    report = {}
    tl = targets()
    mobs = [t.stem for t in tl if t.parts[0] == "mob"]
    mobs += ["d0", "d1"]  # dog frames live in npc/ but are monster-like single images
    first = [s for s in mobs if not is_mirror(s)]
    for stem in first:
        if only and stem not in only:
            continue
        if stem in ("d0", "d1"):
            continue
        report[stem] = gen_frame(stem, a.tries, a.force or a.rematte, a.rematte, a.consistent)
    for stem in mobs:
        src = is_mirror(stem)
        if src and (OUT / f"{src}.png").is_file() and (not only or src in only or stem in only):
            report[stem] = mirror_frame(stem, src)
    if not only or "npc" in only:
        for i in range(6):
            report[f"npc{i}"] = npc_parts(i)
    rp = L.CAST / "mob_report.json"
    old = json.loads(rp.read_text()) if rp.is_file() else {}
    old.update(report)
    rp.write_text(json.dumps(old, indent=1))
    print(json.dumps(report))


if __name__ == "__main__":
    main()
