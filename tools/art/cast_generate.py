"""Local HD cast masters from the ORIGINAL sprites (no hand prompting per run).

Recipe (found in art/cast/work/t1..t3 tests, see art/cast/README in the report):
  * input  = original sprite composited exactly like the game draws it, integer nearest-neighbour
             upscale to ~80 % of a 1024 canvas, on a flat chroma colour chosen far from the
             sprite's palette (green / magenta / blue)
  * model  = FLUX.2 Klein 4B (4-bit, mflux-generate-flux2-edit), single reference image,
             4 steps, 1024x1024, seed 4211 (+1000 per retry)
             (two references - sprite + hero master - was tested: the model copies the hero's
             head/headband/gi onto the NPC, so the hero style is given in words instead)
  * prompt = fixed style sentence (the hero's look) + the character description from
             art/prompts/PROMPT_PACK_VEO3_CODEX.md part B
  * matte  = border-connected chroma key + enclosed key-colour pockets, despill, 1px feather
  * normalise to 1254x1254, feet at 97 %, then QA (tools/art/cast_qa.py); up to 3 tries.

    python3 tools/art/cast_generate.py                 # everything missing
    python3 tools/art/cast_generate.py --ids npc10,npc00 --force
Every attempt is appended to art/cast/jobs.json.
"""
import argparse
import json
import sys
from pathlib import Path

from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))
import cast_lib as L  # noqa: E402
import cast_qa as Q  # noqa: E402

STYLE = ("Redraw this pixel-art sprite as a high-resolution 2D anime fighting-game character "
         "illustration: crisp cel shading with sharp shadow shapes, clean bold dark outlines, "
         "saturated colours, chibi proportions about 2.8 heads tall. Same character, same colours "
         "and outfit: {desc}. Full body standing, side 3/4 view facing right, plain flat {bg} "
         "background, no shadow on the ground, no text.")
STYLE_MON = ("Redraw this pixel-art sprite as a high-resolution 2D anime game monster illustration: "
             "crisp cel shading with sharp shadow shapes, clean bold dark outlines, saturated colours. "
             "Same creature, same colours, same pose and same proportions as the sprite: {desc}. "
             "Full body, facing right, plain flat {bg} background, no shadow on the ground, no text.")
GI = ("wearing a sleeveless blue ninja gi with gold trim and a gold belt, black wristbands, "
      "baggy blue pants with gold ankle wraps and black boots")

CAST = {
    "npc00": ("npc", 0, "an old bald kung-fu master with a long white beard and moustache, round "
              "red-framed goggles with green lenses on his eyes, purple robe with a white collar, teal "
              "trousers, slightly hunched, holding a small wooden cane, cheerful wise expression"),
    "npc10": ("npc", 1, "a young woman teacher with glossy black hair in a bun with a pink "
              "cherry-blossom hairpin, red patterned kimono with a gold obi, gentle smile, graceful"),
    "npc20": ("npc", 2, "a calm middle-aged samurai teacher with a black topknot, eyes closed "
              "serenely, light blue and white checkered haori over a dark kimono, red sash, "
              "dark hakama trousers, arms folded"),
    "npc30": ("npc", 3, "an elderly bald martial-arts teacher with white hair on the sides, a white "
              "bushy moustache and short white beard, royal-blue gi with dark trim, blue trousers, "
              "stern but kind face, standing upright"),
    "npc40": ("npc", 4, "a strict ninja school principal with short black spiky hair, grey "
              "headband, sharp stern eyes, black ninja robes with a little red detail, commanding pose"),
    "npc50": ("npc", 5, "a tough ninja homeroom teacher with dark hair, silver-grey headband, dark "
              "navy gi with light blue trim, a scar on the cheek, holding a wooden practice sword, "
              "confident grin"),
    "dog": ("dog", 0, "a grey and white wolf-dog (husky-like) with blue eyes, pointed ears and a "
            "fluffy raised tail, side view standing on four legs facing right"),
    "stu0_beanie": ("stu", 0, "a mischievous boy ninja student in a teal-grey knitted beanie hat, red "
                    "eyes, " + GI),
    "stu1_bald": ("stu", 1, "a gentle chubby-cheeked boy ninja student with a shaved bald head, " + GI),
    "stu2_redspike": ("stu", 2, "a hot-headed boy ninja student with bright red spiky hair and red "
                      "eyes, " + GI),
    "stu3_dreads": ("stu", 3, "a cool boy ninja student with black dreadlocks tied up high like a "
                    "mohawk and purple eyes, " + GI),
    "stu4_glasses": ("stu", 4, "a nerdy bald boy ninja student wearing big white-framed glasses, " + GI),
    "stu5_sakura": ("stu", 5, "a cute girl ninja student named Sakura with black hair in a ponytail "
                    "tied with a red ribbon and big blue eyes, " + GI),
    "stu5_sakura_kimono": ("stu", 5, "a cute young girl named Sakura with black hair in a ponytail "
                           "tied with a red ribbon and big blue eyes, wearing a pink kimono with "
                           "cherry-blossom pattern and a red obi, sandals"),
    "stu6_bucket": ("stu", 6, "a mysterious ninja student whose whole head is hidden under a big "
                    "round wooden bucket-shaped woven basket hat with a small mesh window, " + GI),
    "stu7_toniku": ("stu", 7, "Toniku, a sly rebellious boy ninja student with messy wild dark red "
                    "hair, a bruised face and shifty eyes, wearing a torn sleeveless blue ninja gi "
                    "with gold trim, baggy blue pants and black boots"),
    "boss_boar": ("boss", "b00", "a giant demonic wild boar boss, dark reddish-brown bristly fur with "
                  "spiky mane, curved white tusks, angry yellow eyes, low charging stance, facing right"),
    "boss_squid": ("boss", "b10", "a giant blue river squid monster boss, glossy blue body with a "
                   "pointed head, angry white eyes, curling tentacles with cyan highlights"),
    "boss_golem": ("boss", "golem", "a giant wooden golem boss made of carved wooden blocks with a "
                   "purple belt, huge wooden fists and wooden feet, round wooden shoulders"),
}


def orig_ref(cid):
    kind, key, _ = CAST[cid]
    if kind == "npc":
        return L.npc_composite(key)
    if kind == "dog":
        return L.trim(L.rgba(L.ORIG / "npc/d0.png"))
    if kind == "stu":
        return L.student_composite(key)
    if key == "golem":
        return L.golem_composite()
    return L.trim(L.rgba(L.ORIG / f"mob/{key}.png"))


def generate(cid, tries=3, force=False):
    kind, key, desc = CAST[cid]
    out = L.CAST / f"{cid}.png"
    if out.is_file() and not force:
        print("skip", cid)
        return
    L.WORK.mkdir(parents=True, exist_ok=True)
    ref = orig_ref(cid)
    ref_path = L.WORK / f"orig_{cid}.png"
    ref.save(ref_path)
    bgname = L.pick_bg(ref)
    bgrgb, bgtext = L.BGS[bgname]
    inp, s = L.make_input(ref, 1024, bgrgb, fill=0.80 if kind != "boss" else 0.85,
                          feet=0.97 if kind != "boss" else 0.9)
    inp_path = L.WORK / f"in_{cid}.png"
    inp.save(inp_path)
    tmpl = STYLE_MON if kind == "boss" else STYLE
    prompt = tmpl.format(desc=desc, bg=bgtext)
    best = None
    for t in range(1, tries + 1):
        seed = L.BASE_SEED + 1000 * (t - 1)
        raw = L.WORK / f"raw_{cid}_t{t}.png"
        dt, cmd = L.run_edit([inp_path], prompt, raw, seed, log=L.WORK / f"raw_{cid}_t{t}.log")
        hd = L.normalize(L.matte(Image.open(raw), bgname), 1254,
                         fill=0.92 if kind != "boss" else 0.80)
        cand = L.WORK / f"hd_{cid}_t{t}.png"
        hd.save(cand)
        qa = Q.score(ref, hd, "master", bgname)
        rec = {"id": cid, "try": t, "seed": seed, "steps": L.STEPS, "size": [1024, 1024],
               "kind": "master", "bg": bgname, "input": str(inp_path.relative_to(L.ROOT)),
               "input_upscale": s, "orig_ref": str(ref_path.relative_to(L.ROOT)), "prompt": prompt,
               "raw": str(raw.relative_to(L.ROOT)), "candidate": str(cand.relative_to(L.ROOT)),
               "output": str(out.relative_to(L.ROOT)), "seconds": dt, "qa": qa,
               "accepted": False, "time": L.now()}
        print(cid, "try", t, dt, "s", "OK" if qa["ok"] else qa["flags"], qa["palette"])
        rank = (len(qa["flags"]), qa["palette"])
        if best is None or rank < best[0]:
            best = (rank, cand, rec)
        L.log_job(rec)
        if qa["ok"]:
            break
    _, cand, rec = best
    Image.open(cand).save(out)
    acc = dict(rec, accepted=True, note="best of %d tries" % rec["try"])
    L.log_job(acc)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--ids", default="")
    ap.add_argument("--force", action="store_true")
    ap.add_argument("--tries", type=int, default=3)
    a = ap.parse_args()
    ids = [i for i in a.ids.split(",") if i] or list(CAST)
    for cid in ids:
        generate(cid, a.tries, a.force)


if __name__ == "__main__":
    main()
