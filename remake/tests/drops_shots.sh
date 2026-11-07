#!/bin/bash
# tests/drops_shots.sh <outdir> : windowed screenshots with loot on screen (main.gd --drops: 3 coin sizes,
# HP/MP potion, quest scroll next to the player), to compare the loot view pixel by pixel before/after a refactor.
R=$(cd "$(dirname "$0")/.."; pwd)
GODOT=${GODOT:-/Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot}
OUT=$1; mkdir -p "$OUT"
# background.gd alternates day/night on every launch: pin it to "day", restore the player's file afterwards
CFG="$HOME/Library/Application Support/Godot/app_userdata/Ninja School 1 Remake/remake_cfg.ini"
[ -f "$CFG" ] && cp "$CFG" "$OUT/.cfg_backup"
trap '[ -f "$OUT/.cfg_backup" ] && mv "$OUT/.cfg_backup" "$CFG"' EXIT
SK="eL=true;eM=true;eN=true;fc=true;I=300;J=300;G=3000;H=3000"
# name | map,x,y | keys | shot times   (coins land and spin, potions bob, the scroll floats up when taken)
SC=(
  "map9_loot|9,230,288||0.3,0.8,1.5,2.5"
  "map9_pickup|9,230,288|right@1.0-3.0|1.2,1.6,2.0,2.4,3.0"
  "map1_loot|1,280,216||0.3,0.8,1.5,2.5"
)
for sc in "${SC[@]}"; do
  IFS='|' read -r name map keys shots <<< "$sc"
  d="$OUT/$name"; mkdir -p "$d"
  q=$(echo "$shots" | tr ',' '\n' | tail -1); q=$(echo "$q + 0.5" | bc)
  printf '[launch]\n\ncount=0\n' > "$CFG"
  a=(--seed=1 --map="$map" --set="$SK" --drops --shots="$shots" --out="$d" --quit="$q")
  [ -n "$keys" ] && a+=(--keys="$keys")
  timeout 120 "$GODOT" --fixed-fps 60 --path "$R" -- "${a[@]}" > "$d/log.txt" 2>&1 < /dev/null
  echo "$name: $(ls "$d" | grep -c png) shots, script errors $(grep -c 'SCRIPT ERROR' "$d/log.txt")"
done
