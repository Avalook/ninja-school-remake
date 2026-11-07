"""Side-by-side review sheets: original sprite (nearest) | Codex HD (art/generated/codex_hd) | local mflux HD (art/cast)."""
import sys
from pathlib import Path
from PIL import Image, ImageDraw
sys.path.insert(0, str(Path(__file__).resolve().parent))
import cast_lib as L
import cast_generate as G

CODEX = L.ROOT / "art/generated/codex_hd"
PAIRS = {"npc00": "npc0_old_master", "npc10": "npc1_ayumi", "npc20": "npc2_hiroyaki", "npc30": "npc3_honda",
         "npc40": "npc4_principal", "npc50": "npc5_takanashi", "dog": "npc_dog",
         "stu0_beanie": "stu0_beanie", "stu1_bald": "stu1_bald", "stu2_redspike": "stu2_redspike",
         "stu3_dreads": "stu3_dreads", "stu4_glasses": "stu4_glasses", "stu5_sakura": "stu5_sakura",
         "stu5_sakura_kimono": "stu5_sakura_kimono", "stu6_bucket": "stu6_bucket", "stu7_toniku": "stu7_toniku",
         "boss_boar": "boss_boar", "boss_squid": "boss_squid", "boss_golem": "boss_golem"}


def tile(im, size, bg=(118, 118, 138, 255), nearest=False):
    c = Image.new("RGBA", (size, size), bg)
    if im is None:
        return c
    im = L.trim(im.convert("RGBA"))
    s = min(size * 0.94 / im.width, size * 0.94 / im.height)
    im = im.resize((max(1, int(im.width * s)), max(1, int(im.height * s))), Image.NEAREST if nearest else Image.LANCZOS)
    c.alpha_composite(im, ((size - im.width) // 2, size - 4 - im.height))
    return c


GAME = {"npc00": "npc_0", "npc10": "npc_1", "npc20": "npc_2", "npc30": "npc_3", "npc40": "npc_4",
        "npc50": "npc_5"}


def sheet(ids, out, size=300):
    W = size * 4
    img = Image.new("RGBA", (W * 2, (size + 18) * ((len(ids) + 1) // 2)), (40, 40, 48, 255))
    d = ImageDraw.Draw(img)
    for n, cid in enumerate(ids):
        x0, y0 = (n % 2) * W, (n // 2) * (size + 18)
        cod = CODEX / f"{PAIRS[cid]}.png"
        loc = L.CAST / f"{cid}.png"
        gp = L.ROOT / "remake/art/cast" / f"{GAME.get(cid, cid)}.png"
        sp = L.CAST / "sheets" / f"{GAME.get(cid, cid)}.png"
        game = None
        if sp.is_file():  # first cell of the game sheet
            sj = __import__("json").loads(sp.with_suffix(".json").read_text())
            game = Image.open(sp).crop((0, 0, sj["cell"][0], sj["cell"][1]))
        elif gp.is_file():
            game = Image.open(gp)
        for k, im in enumerate([tile(G.orig_ref(cid), size, nearest=True),
                                tile(Image.open(cod) if cod.is_file() else None, size),
                                tile(Image.open(loc) if loc.is_file() else None, size),
                                tile(game, size, nearest=True)]):
            img.paste(im, (x0 + k * size, y0 + 18))
        d.text((x0 + 4, y0 + 3), f"{cid}: original | codex {PAIRS[cid]} | local mflux | game texture (frame 0, nearest)", fill=(230, 230, 230))
    img.convert("RGB").save(out)


if __name__ == "__main__":
    sheet(list(PAIRS), L.CAST / "preview.png", size=220)
