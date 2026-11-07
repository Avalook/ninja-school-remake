#!/bin/bash
# tests/story_shots.sh <outdir> [project dir] : windowed screenshots of story scenes (title card, cutscene
# dialogue, NPC dialogue, camera pans) at --fixed-fps 60, to compare the view pixel by pixel before/after a refactor.
# The dialogue "next" arrow blinks on real time (ui.gd Time.get_ticks_msec), so its few pixels may differ.
R=${2:-$(cd "$(dirname "$0")/.."; pwd)}
GODOT=${GODOT:-/Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot}
OUT=$1; mkdir -p "$OUT"
# background.gd alternates day/night on every launch (counter in user://remake_cfg.ini): pin it to "day"
CFG="$HOME/Library/Application Support/Godot/app_userdata/Ninja School 1 Remake/remake_cfg.ini"
[ -f "$CFG" ] && cp "$CFG" "$OUT/.cfg_backup"
trap '[ -f "$OUT/.cfg_backup" ] && mv "$OUT/.cfg_backup" "$CFG"' EXIT
# name | start args | keys | shot times
SC=(
  "intro_m99|--newgame|right@3.0-6.0|1.0,2.5,4.0,6.5,9.0"
  "m0_ayumi|--map=0,1044,408 --set=eH=false;eL=false|talk@1.0-1.1|0.8,1.6,3.0,5.0"
  "m97_cutscene|--map=97,100,216 --set=eL=true;eN=true;I=300;J=300;G=3000;H=3000|right@2.0-4.0|1.0,2.2,3.4,4.6"
)
for sc in "${SC[@]}"; do
  IFS='|' read -r name start keys shots <<< "$sc"
  d="$OUT/$name"; mkdir -p "$d"
  q=$(echo "$shots" | tr ',' '\n' | tail -1); q=$(echo "$q + 0.5" | bc)
  printf '[launch]\n\ncount=0\n' > "$CFG"
  timeout 120 "$GODOT" --fixed-fps 60 --path "$R" -- --seed=1 $start --autoskip --keys="$keys" --shots="$shots" --out="$d" --quit="$q" > "$d/log.txt" 2>&1 < /dev/null
  echo "$name: $(ls "$d" | grep -c png) shots, script errors $(grep -c 'SCRIPT ERROR' "$d/log.txt")"
done
