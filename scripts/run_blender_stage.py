"""Run the Blender art-stage harness with a bounded render process."""

import json
import shutil
import subprocess
from pathlib import Path


def main():
    root = Path(__file__).resolve().parent.parent
    executable = shutil.which("blender")
    if not executable:
        mac = Path("/Applications/Blender.app/Contents/MacOS/Blender")
        if not mac.is_file():
            raise SystemExit("Install Blender from blender.org, or add its executable to PATH.")
        executable = str(mac)
    command = [executable, "--background", "--factory-startup", "--python-exit-code", "1",
               "--python", str(root / "scripts/blender_build_art_stage.py"),
               "--", "--project-root", str(root)]
    output = root / "art/blender"
    output.mkdir(parents=True, exist_ok=True)
    proof = output / "connection-check.json"
    proof.write_text(json.dumps({"status": "running", "render_completed": False}))
    try:
        subprocess.run(command, cwd=root, check=True, timeout=180)
    except (OSError, subprocess.SubprocessError) as error:
        proof.write_text(json.dumps({"status": "failed", "render_completed": False,
                                     "error": str(error)}, indent=2))
        raise


if __name__ == "__main__":
    main()
