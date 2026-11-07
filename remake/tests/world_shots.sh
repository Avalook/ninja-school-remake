#!/bin/bash
# tests/world_shots.sh <outdir> : windowed screenshots of world objects (push blocks map 1, motorbike map 3,
# decor houses map 2, rafts map 97) at --fixed-fps 60, to compare the view pixel by pixel before/after a refactor.
R=$(cd "$(dirname "$0")/.."; pwd)
GODOT=${GODOT:-/Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot}
OUT=$1; mkdir -p "$OUT"
# background.gd alternates day/night on every launch (counter in user://remake_cfg.ini): pin it to "day"
CFG="$HOME/Library/Application Support/Godot/app_userdata/Ninja School 1 Remake/remake_cfg.ini"
[ -f "$CFG" ] && cp "$CFG" "$OUT/.cfg_backup"
trap '[ -f "$OUT/.cfg_backup" ] && mv "$OUT/.cfg_backup" "$CFG"' EXIT
SK="eL=true;eM=true;eN=true;fc=true;I=300;J=300;G=3000;H=3000"
# name | map,x,y | keys | shot times
SC=(
  "map1_blocks|1,280,216|right@0.5-2.0,attack@2.2-2.4|0.8,1.6,2.6,3.4"
  "map3_bike|3,60,216|right@0.5-1.5|0.4,1.2,2.0"
  "map2_houses|2,700,312|right@0.5-2.5|0.6,1.8,3.0"
  "map97_rafts|97,40,168|right@0.5-1.5,attack@1.6-1.8|0.8,2.0,3.2,4.4"
)
for sc in "${SC[@]}"; do
  IFS='|' read -r name map keys shots <<< "$sc"
  d="$OUT/$name"; mkdir -p "$d"
  q=$(echo "$shots" | tr ',' '\n' | tail -1); q=$(echo "$q + 0.5" | bc)
  printf '[launch]\n\ncount=0\n' > "$CFG"
  timeout 120 "$GODOT" --fixed-fps 60 --path "$R" -- --seed=1 --map="$map" --set="$SK" --keys="$keys" --shots="$shots" --out="$d" --quit="$q" > "$d/log.txt" 2>&1 < /dev/null
  echo "$name: $(ls "$d" | grep -c png) shots, script errors $(grep -c 'SCRIPT ERROR' "$d/log.txt")"
done
