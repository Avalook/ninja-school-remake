#!/bin/bash
# tests/sim_snapshot.sh [--skip=<root>.<var>] : sim/sim.gd snapshot()/restore() check, headless, fixed 60 fps
# (see tests/sim_snapshot.gd). Exit 0 = every scenario replays identically after a restore from JSON.
R=$(cd "$(dirname "$0")/.."; pwd)
GODOT=${GODOT:-/Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot}
timeout 600 "$GODOT" --headless --path "$R" --script res://tests/sim_snapshot.gd -- "$@" 2>&1 \
  | grep -vE '^(Godot Engine|WARNING: [0-9]+ ObjectDB|   at: |ERROR: [0-9]+ resources)'
exit ${PIPESTATUS[0]}
