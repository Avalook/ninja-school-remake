#!/bin/bash
# Story module verification.
#   tests/story_run_tests.sh            remake story tests against the saved oracle logs
#   tests/story_run_tests.sh --oracle   first re-run the ORIGINAL game (../game, transpiled a.java)
#                                       on tests/story_oracle/scripts/*.txt and refresh tests/story_oracle/expected
set -e
R=$(cd "$(dirname "$0")/.."; pwd)
GODOT=${GODOT:-/Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot}
if [ "$1" == "--oracle" ]; then
  mkdir -p "$R/tests/story_oracle/expected"
  for s in intro_prologue q1c end3; do
    (cd "$R" && NINJA_RMS=user://story_oracle_$s timeout 600 "$GODOT" --headless --path ../game \
      --script "$R/tests/story_oracle/story_oracle.gd" -- "$R/tests/story_oracle/scripts/$s.txt" 2>&1 \
      | grep -E '^\[(story|final|vars|bot)\]' > "$R/tests/story_oracle/expected/$s.log")
    echo "oracle $s: $(grep -c '^\[story\]' "$R/tests/story_oracle/expected/$s.log") story events"
  done
fi
cd "$R" && timeout 600 "$GODOT" --headless --path . --script res://tests/story_test.gd 2>&1 | grep -vE '^(Godot Engine|WARNING: [0-9]+ ObjectDB|   at: |ERROR: [0-9]+ resources)' 
