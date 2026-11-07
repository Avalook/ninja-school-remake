#!/usr/bin/env python3
"""Run the whole parallax-background pipeline: every biome x {day, night} -> art/env/.

  python3 scripts/run_env_pipeline.py                 # all biomes, full res, 3 parallel Blender jobs
  python3 scripts/run_env_pipeline.py --biomes school orochi --jobs 1
  python3 scripts/run_env_pipeline.py --preview-only  # rebuild art/env/preview.png from existing PNGs
  python3 scripts/run_env_pipeline.py --no-blend      # skip saving art/blender/env_backgrounds.blend

Each biome runs in its own Blender process (--background --factory-startup) with a timeout.
Status is written live to art/env/pipeline-status.json (running / passed / failed, timings, seam checks).
Requires Pillow for the contact sheet (art/env/preview.png).
"""
import argparse
import json
import os
import shutil
import subprocess
import sys
import time
from concurrent.futures import ThreadPoolExecutor, as_completed
from threading import Lock

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
GEN = os.path.join(ROOT, "scripts", "blender_env_backgrounds.py")
BIOMES = ["school", "sakura", "bamboo", "waterfall", "fields", "river", "graveyard", "temple", "woods",
          "spikes", "cliffs", "valley", "village", "orochi"]
LIGHTS = ["day", "night"]


def find_blender():
    for c in (os.environ.get("BLENDER"), shutil.which("blender"), "/Applications/Blender.app/Contents/MacOS/Blender"):
        if c and os.path.exists(c):
            return c
    sys.exit("Blender not found (set $BLENDER)")


class Status:
    def __init__(self, path, biomes):
        self.path = path
        self.lock = Lock()
        self.data = dict(status="running", started=time.strftime("%Y-%m-%dT%H:%M:%S"), generator="scripts/blender_env_backgrounds.py",
                         biomes={b: dict(status="pending") for b in biomes})
        self.write()

    def set(self, biome, **kw):
        with self.lock:
            self.data["biomes"][biome].update(kw)
            self.write()

    def write(self):
        os.makedirs(os.path.dirname(self.path), exist_ok=True)
        tmp = self.path + ".tmp"
        with open(tmp, "w") as f:
            json.dump(self.data, f, indent=2)
        os.replace(tmp, self.path)


def run_biome(blender, biome, out, samples, scale, timeout, status):
    status.set(biome, status="running")
    log = os.path.join(out, biome, "render.log")
    os.makedirs(os.path.dirname(log), exist_ok=True)
    cmd = [blender, "--background", "--factory-startup", "--python", GEN, "--", "--biome", biome, "--lighting", *LIGHTS,
           "--out", out, "--samples", str(samples), "--scale", str(scale)]
    t = time.time()
    try:
        with open(log, "w") as lf:
            p = subprocess.run(cmd, stdout=lf, stderr=subprocess.STDOUT, timeout=timeout, cwd=ROOT)
        rc = p.returncode
        err = None
    except subprocess.TimeoutExpired:
        rc, err = -1, "timeout after %ds" % timeout
    dt = round(time.time() - t, 1)
    res = {}
    ok = rc == 0
    for li in LIGHTS:
        mp = os.path.join(out, biome, li, "meta.json")
        files_ok = os.path.exists(mp) and all(os.path.exists(os.path.join(out, biome, li, "layer%d.png" % i)) for i in range(4))
        if not files_ok:
            ok = False
            res[li] = dict(ok=False)
            continue
        m = json.load(open(mp))
        seams = [l["seam_check"]["ok"] for l in m["layers"]]
        res[li] = dict(ok=all(seams), seams_ok=seams, render_seconds=sum(l["render_seconds"] for l in m["layers"]))
        ok = ok and all(seams)
    if rc != 0 and err is None:
        err = "blender exit code %d (see %s)" % (rc, os.path.relpath(log, ROOT))
    status.set(biome, status="passed" if ok else "failed", seconds=dt, lightings=res, error=err,
               log=os.path.relpath(log, ROOT))
    return biome, ok, dt


def preview(out, biomes, path):
    from PIL import Image, ImageDraw
    tw, th = 480, 270
    cols = 4  # biome A day, A night, B day, B night
    rows = (len(biomes) + 1) // 2
    pad = 22
    sheet = Image.new("RGB", (tw * cols, (th + pad) * rows), (24, 26, 32))
    d = ImageDraw.Draw(sheet)
    for i, b in enumerate(biomes):
        for j, li in enumerate(LIGHTS):
            dd = os.path.join(out, b, li)
            try:
                ims = [Image.open(os.path.join(dd, "layer%d.png" % k)).convert("RGBA") for k in range(4)]
            except FileNotFoundError:
                continue
            W, H = ims[1].size
            base = ims[0].resize((W, H), Image.BICUBIC)
            for im in ims[1:]:
                base = Image.alpha_composite(base, im)
            t = base.resize((tw, th), Image.LANCZOS).convert("RGB")
            x = ((i % 2) * 2 + j) * tw
            y = (i // 2) * (th + pad)
            sheet.paste(t, (x, y + pad))
            d.text((x + 6, y + 5), "%s / %s" % (b, li), fill=(235, 235, 235))
    sheet.save(path)
    return path


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--biomes", nargs="+", default=BIOMES)
    ap.add_argument("--out", default=os.path.join(ROOT, "art", "env"))
    ap.add_argument("--samples", type=int, default=48)
    ap.add_argument("--scale", type=float, default=1.0)
    ap.add_argument("--jobs", type=int, default=3)
    ap.add_argument("--timeout", type=int, default=600, help="seconds per biome (both lightings)")
    ap.add_argument("--blend", default=os.path.join(ROOT, "art", "blender", "env_backgrounds.blend"))
    ap.add_argument("--no-blend", action="store_true")
    ap.add_argument("--preview-only", action="store_true")
    a = ap.parse_args()
    out = os.path.abspath(a.out)
    if a.preview_only:
        print(preview(out, a.biomes, os.path.join(out, "preview.png")))
        return
    blender = find_blender()
    st = Status(os.path.join(out, "pipeline-status.json"), a.biomes)
    st.data.update(blender=blender, samples=a.samples, scale=a.scale, jobs=a.jobs, timeout=a.timeout)
    t0 = time.time()
    with ThreadPoolExecutor(max_workers=a.jobs) as ex:
        futs = [ex.submit(run_biome, blender, b, out, a.samples, a.scale, a.timeout, st) for b in a.biomes]
        for f in as_completed(futs):
            b, ok, dt = f.result()
            print("[pipeline] %-10s %s %.1fs" % (b, "passed" if ok else "FAILED", dt), flush=True)
    if not a.no_blend:
        tb = time.time()
        cmd = [blender, "--background", "--factory-startup", "--python", GEN, "--", "--biome", *a.biomes, "--lighting", "day",
               "--no-render", "--blend", a.blend]
        try:
            p = subprocess.run(cmd, capture_output=True, text=True, timeout=a.timeout, cwd=ROOT)
            st.data["blend"] = dict(path=os.path.relpath(a.blend, ROOT), ok=p.returncode == 0 and os.path.exists(a.blend),
                                    seconds=round(time.time() - tb, 1),
                                    mb=round(os.path.getsize(a.blend) / 1e6, 2) if os.path.exists(a.blend) else None)
        except subprocess.TimeoutExpired:
            st.data["blend"] = dict(path=os.path.relpath(a.blend, ROOT), ok=False, error="timeout")
    try:
        st.data["preview"] = os.path.relpath(preview(out, a.biomes, os.path.join(out, "preview.png")), ROOT)
    except Exception as e:  # noqa: BLE001 - report, don't hide
        st.data["preview_error"] = repr(e)
    ok = all(v.get("status") == "passed" for v in st.data["biomes"].values())
    if "blend" in st.data:
        ok = ok and st.data["blend"]["ok"]
    st.data.update(status="passed" if ok else "failed", total_seconds=round(time.time() - t0, 1),
                   finished=time.strftime("%Y-%m-%dT%H:%M:%S"))
    st.write()
    print("[pipeline] %s in %.1fs -> %s" % (st.data["status"], time.time() - t0, os.path.relpath(st.path, ROOT)))
    sys.exit(0 if ok else 1)


if __name__ == "__main__":
    main()
