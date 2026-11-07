"""art/cast/manifest.json: every cast/monster output, its source, method and format contract."""
import json
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import cast_lib as L  # noqa: E402

FORMATS = {
    "remake/art/cast/npc_<kind>.png+json": "single texture, read by remake/scripts/world/npcs.gd; "
        "anchor = feet in texture px, scale = world px per texture px, facing right (game mirrors)",
    "remake/art/cast/<id>.png+json": "same single-texture format for dog, students, bosses (not wired yet)",
    "remake/art/cast/sheets/<id>.png+json": "horizontal strip of equal cells; json {cell, anchor (feet, "
        "cell px), count, frames{clip:[cell indices]}, fps, facing:right, scale}. Students: idle/talk/walk "
        "from the hero cutout rig (cell 256, anchor 128,240, same scale as hero_sheet); NPCs: idle/talk "
        "mesh-warp frames; dog: idle/walk",
    "art/cast/mob/<orig name>.png": "exact 4x canvas of game/orig_res/mob/<name>.png, body fitted into "
        "4x the original body bbox (scripts/monster_post.py contract) -> copy to remake/art/mob/ and "
        "game/hd_art/mob/ after review",
}


def main():
    jobs = L.load_jobs()["jobs"]
    acc = {}
    for j in jobs:
        if j.get("accepted"):
            acc[j["output"]] = {k: j[k] for k in ("id", "seed", "try", "seconds", "qa", "refs", "prompt")
                                if k in j}
    rc = L.ROOT / "remake/art/cast"
    items = {}
    for p in sorted(rc.rglob("*.png")):
        meta = p.with_suffix(".json")
        items[str(p.relative_to(L.ROOT))] = json.loads(meta.read_text()) if meta.is_file() else {}
    mobs = {}
    rep = L.CAST / "mob_report.json"
    mrep = json.loads(rep.read_text()) if rep.is_file() else {}
    for p in sorted((L.CAST / "mob").glob("*.png")):
        out = str(p.relative_to(L.ROOT))
        mobs[out] = acc.get(out, {"method": mrep.get(p.stem, "unknown")})
        if "qa" in mobs[out]:
            mobs[out]["method"] = "mflux_flux2_klein_edit"
        mobs[out]["status"] = mrep.get(p.stem, "")
    (L.CAST / "manifest.json").write_text(json.dumps({
        "generated": L.now(), "formats": FORMATS, "game_ready": items, "mob": mobs,
        "masters_codex_primary": "art/generated/codex_hd (read-only); QA in art/cast/qa.json[codex]",
        "masters_local": sorted(str(p.relative_to(L.ROOT)) for p in L.CAST.glob("*.png")
                                if p.name not in ("preview.png", "mob_preview.png"))}, indent=1))


if __name__ == "__main__":
    main()
