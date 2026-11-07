"""Use the Codex HD cast (art/generated/codex_hd, READ-ONLY raw supplied data) as the primary source:
  1. automatic QA of every Codex master against its original sprite (tools/art/cast_qa.py metrics)
     merged with the visual verdicts below -> art/cast/qa.json["codex"]
  2. game-ready single textures for the remake:
       remake/art/cast/npc_<kind>.png + .json   (kind = original lA 0..5; read by remake/scripts/world/npcs.gd)
       remake/art/cast/<id>.png + .json         (dog, students, bosses: same format, not wired yet)
     json = {"anchor": [x, y] feet in texture px, "scale": world px per texture px, "facing": "right",
             "world_height": px, "source": ..., "orig_height": px}
     Texture density matches the hero sheet (hero 201 tex px = 42 world px -> 4.786 tex px / world px);
     world height = original standing height * 42/36 (hero: 36 px original -> 42 world px), so the
     relative heights of the original cast are kept.

    python3 tools/art/cast_codex.py
"""
import json
import sys
from pathlib import Path

import numpy as np
from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))
import cast_lib as L  # noqa: E402
import cast_qa as Q  # noqa: E402
import cast_generate as G  # noqa: E402

CODEX = L.ROOT / "art/generated/codex_hd"
REMAKE_CAST = L.ROOT / "remake/art/cast"
HERO_DENSITY = 201.0 / 42.0
HERO_RATIO = 42.0 / 36.0

# id -> (codex file, npc kind or None, visual verdict, note)
ITEMS = {
    "npc00": ("npc0_old_master", 0, "good", "goggles smaller than the sprite; trousers purple not teal"),
    "npc10": ("npc1_ayumi", 1, "good", "faithful: black bun + pink flower, red kimono, gold obi"),
    "npc20": ("npc2_hiroyaki", 2, "good", "topknot, closed eyes, blue/white haori, red sash"),
    "npc30": ("npc3_honda", 3, "good", "bald, white moustache/beard, blue gi; adds a gold belt, no staff"),
    "npc40": ("npc4_principal", 4, "good", "black robes, grey headband, red-lined cloak"),
    "npc50": ("npc5_takanashi", 5, "good", "silver headband, navy gi, wooden sword"),
    "dog": ("npc_dog", None, "good", "husky wolf-dog, blue eyes; standing (d0)"),
    "dog_walk": ("npc_dog_walk", None, "good", "walking pose (d1)"),
    "stu0_beanie": ("stu0_beanie", None, "good", "teal beanie, red eyes, hero gi"),
    "stu1_bald": ("stu1_bald", None, "good", ""),
    "stu2_redspike": ("stu2_redspike", None, "good", ""),
    "stu3_dreads": ("stu3_dreads", None, "good", "dreads tied high, purple eyes"),
    "stu4_glasses": ("stu4_glasses", None, "good", "bald + white glasses"),
    "stu5_sakura": ("stu5_sakura", None, "good", "ponytail + red ribbon, blue eyes"),
    "stu5_sakura_kimono": ("stu5_sakura_kimono", None, "good", "pink kimono alternative"),
    "stu6_bucket": ("stu6_bucket", None, "good", "woven bucket hat with mesh window"),
    "stu7_toniku": ("stu7_toniku", None, "good", "messy red hair, bruised, torn gi"),
    "stu7_toniku_leader": ("stu7_toniku_leader", None, "unreviewed_extra", "bandit-leader variant"),
    "boss_boar": ("boss_boar", None, "good", "dark red-brown boar, white tusks, faces right"),
    "boss_squid": ("boss_squid", None, "good", "blue squid; 8 tentacles vs 4 in the sprite"),
    "boss_golem": ("boss_golem", None, "acceptable", "adds a head/face; original golem is a round "
                   "barrel body with fists and feet (its 'head' is a classmate head f2h0 in boss_golem.gd)"),
    "mob00": ("mob00", None, "reject", "purple ghost from the prompt-pack text; original type 0 is a "
              "one-eyed orange caveman (m000..m003)"),
    "mob01": ("mob01", None, "acceptable", "snail ok but shell orange, sprite shell maroon; type 1 "
              "already has Blender HD"),
    "mob02": ("mob02", None, "reject", "purple bat; original type 2 is a blue/cyan bat; "
              "mob02_sheet has no real alpha (min alpha 170)"),
}


def orig_for(cid):
    if cid == "dog_walk":
        return L.trim(L.rgba(L.ORIG / "npc/d1.png"))
    if cid in G.CAST:
        return G.orig_ref(cid)
    if cid == "stu7_toniku_leader":
        return G.orig_ref("stu7_toniku")
    if cid.startswith("mob0"):
        return L.trim(L.rgba(L.ORIG / f"mob/m{int(cid[3:])}00.png"))
    raise KeyError(cid)


def feet_anchor(a):
    op = a[..., 3] >= 128
    ys, xs = np.nonzero(op)
    bottom = ys.max()
    low = ys >= bottom - max(2, int(0.05 * (bottom - ys.min())))
    return [int(round(xs[low].mean())), int(bottom) + 1]


def export(cid, src, orig_h, name):
    im = L.trim(Image.open(src).convert("RGBA"))
    world_h = orig_h * (1.0 if cid.startswith("boss") else HERO_RATIO)  # bosses keep 1:1 like /mob art
    tex_h = int(round(world_h * HERO_DENSITY))
    s = tex_h / im.height
    im = im.resize((max(1, round(im.width * s)), tex_h), Image.LANCZOS)
    pad = 4
    c = Image.new("RGBA", (im.width + 2 * pad, im.height + 2 * pad), (0, 0, 0, 0))
    c.alpha_composite(im, (pad, pad))
    a = np.array(c)
    anc = feet_anchor(a)
    REMAKE_CAST.mkdir(parents=True, exist_ok=True)
    c.save(REMAKE_CAST / f"{name}.png")
    meta = {"anchor": anc, "scale": round(world_h / tex_h, 5), "facing": "right",
            "world_height": round(world_h, 2), "orig_height": orig_h,
            "source": str(Path(src).relative_to(L.ROOT)), "size": list(c.size)}
    (REMAKE_CAST / f"{name}.json").write_text(json.dumps(meta, indent=1))
    return meta


def main():
    qa = {}
    for cid, (fname, kind, verdict, note) in ITEMS.items():
        src = CODEX / f"{fname}.png"
        if not src.is_file():
            qa[cid] = {"missing": str(src)}
            continue
        orig = orig_for(cid)
        r = Q.score(orig, Image.open(src), "master")
        r.update(source=str(src.relative_to(L.ROOT)), visual=verdict, note=note)
        a = np.array(Image.open(src).convert("RGBA"))[..., 3]
        r["alpha_real"] = bool(a.min() == 0 and (a >= 250).mean() > 0.05)
        if not r["alpha_real"]:
            r["flags"] = r["flags"] + ["no_alpha"]
            r["ok"] = False
        r["use"] = verdict in ("good", "acceptable") and r["alpha_real"]
        qa[cid] = r
        if r["use"] and not cid.startswith("mob"):
            oh = orig.height
            name = f"npc_{kind}" if kind is not None else cid
            r["game_ready"] = export(cid, src, oh, name)
            r["game_ready"]["file"] = f"remake/art/cast/{name}.png"
        print(cid, verdict, "auto-OK" if r["ok"] else r["flags"], r["palette"], r["facing_hd"])
    p = L.CAST / "qa.json"
    d = json.loads(p.read_text()) if p.is_file() else {}
    d["codex"] = qa
    d["updated"] = L.now()
    p.write_text(json.dumps(d, indent=1, default=float))


if __name__ == "__main__":
    main()
