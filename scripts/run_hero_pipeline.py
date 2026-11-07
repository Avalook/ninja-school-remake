"""One command for the HD hero sprite set: split -> Blender rig/render -> sheet assembly.

    python3 scripts/run_hero_pipeline.py [--frames 0,1,13]   (frames: re-render a subset;
                                                              assembly needs all 46 raws)

Writes art/hero/pipeline-status.json (running / failed / passed, per-step timings).
Each step runs in a bounded subprocess, like scripts/run_blender_stage.py.
"""

import argparse
import json
import shutil
import subprocess
import sys
import time
from datetime import datetime, timezone
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
STATUS = ROOT / "art/hero/pipeline-status.json"


def now():
    return datetime.now(timezone.utc).isoformat(timespec="seconds")


def blender_exe():
    exe = shutil.which("blender")
    if exe:
        return exe
    mac = Path("/Applications/Blender.app/Contents/MacOS/Blender")
    if mac.is_file():
        return str(mac)
    raise SystemExit("Install Blender from blender.org, or add its executable to PATH.")


def write(state):
    STATUS.parent.mkdir(parents=True, exist_ok=True)
    STATUS.write_text(json.dumps(state, indent=2))


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--frames", default="")
    args = ap.parse_args()
    blender = [blender_exe(), "--background", "--factory-startup", "--python-exit-code", "1",
               "--python", str(ROOT / "scripts/blender_hero_rig.py"), "--",
               "--project-root", str(ROOT)]
    if args.frames:
        blender += ["--frames", args.frames]
    pairs_file = ROOT / "art/hero/tween_pairs.json"
    if pairs_file.is_file():   # in-between poses for smooth 60 fps display of 25 Hz frame changes
        pairs = json.loads(pairs_file.read_text())
        blender += ["--tweens", ",".join("%d-%d" % (a, b) for a, b in pairs)]
    steps = [
        ("split", [sys.executable, str(ROOT / "scripts/hero_split_parts.py"), "--debug"], 180),
        ("blender_rig_render", blender, 900),
        ("assemble", [sys.executable, str(ROOT / "scripts/hero_assemble.py")], 180),
        ("assemble_tweens", [sys.executable, str(ROOT / "scripts/hero_tweens_assemble.py")], 300),
    ]
    state = {"status": "running", "started_at": now(), "finished_at": None, "steps": []}
    write(state)
    for name, cmd, timeout in steps:
        step = {"name": name, "status": "running", "started_at": now(), "timeout_s": timeout}
        state["steps"].append(step)
        write(state)
        t0 = time.time()
        try:
            proc = subprocess.run(cmd, cwd=ROOT, timeout=timeout, capture_output=True, text=True)
            err = [l for l in proc.stderr.splitlines()
                   if l.strip() and "DeprecationWarning" not in l and "use_nodes" not in l]
            tail = proc.stdout.strip().splitlines()[-4:] + err[-4:]
            step["log_tail"] = tail
            if proc.returncode != 0:
                raise subprocess.CalledProcessError(proc.returncode, cmd)
            step["status"] = "passed"
        except (OSError, subprocess.SubprocessError) as error:
            step["status"] = "failed"
            step["error"] = str(error)
            step["seconds"] = round(time.time() - t0, 2)
            state.update(status="failed", finished_at=now())
            write(state)
            raise SystemExit(f"hero pipeline failed at step {name}: {error}")
        step["seconds"] = round(time.time() - t0, 2)
        write(state)
    report = ROOT / "art/hero/build/assemble-report.json"
    if report.is_file():
        r = json.loads(report.read_text())
        state["checks"] = {"grounded_sole_rows": r["grounded_sole_rows"],
                           "overflow_frames": r["overflow_frames"]}
    state.update(status="passed", finished_at=now())
    write(state)
    print("hero pipeline passed:", STATUS.relative_to(ROOT))


if __name__ == "__main__":
    main()
