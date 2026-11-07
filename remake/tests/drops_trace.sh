#!/bin/bash
# tests/drops_trace.sh record|check : behaviour lock for the loot rules (sim/enemies/drops.gd).
# The 27 player_trace scenarios have loot on the ground but never pick any up; this covers the pickup paths.
#   record  write tests/drops_trace/expected.txt.gz (baseline, made before a refactor)
#   check   run again and diff against the baseline
R=$(cd "$(dirname "$0")/.."; pwd)
GODOT=${GODOT:-/Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot}
MODE=${1:-check}
E=$R/tests/drops_trace; O=${OUT:-/tmp/claude-501/drops_trace}; mkdir -p "$E" "$O"
timeout 120 "$GODOT" --headless --path "$R" --script res://tests/drops_trace.gd -- --out="$O/got.txt" > "$O/log.txt" 2>&1
errs=$(grep -c 'SCRIPT ERROR' "$O/log.txt")
lines=$(wc -l < "$O/got.txt" | tr -d ' ')
picks=$(grep -c 'E=[^ ]' "$O/got.txt")
if [ "$MODE" == "record" ]; then
  gzip -n -9 -c "$O/got.txt" > "$E/expected.txt.gz"
  echo "recorded: $lines ticks, $picks ticks with pickup events, script errors $errs"
  exit 0
fi
d=$(diff <(gzip -dc "$E/expected.txt.gz") "$O/got.txt" | grep -c '^[<>]')
echo "drops_trace: $lines ticks, $picks ticks with pickup events, $d differing lines, script errors $errs"
[ "$d" == "0" ] && [ "$errs" == "0" ]
