#!/bin/bash
# tests/enemies_shots.sh <outdir> : windowed screenshots of maps with monsters, bosses and rival students
# (--fixed-fps 60, scripted keys) to compare the view pixel by pixel before/after a refactor.
R=$(cd "$(dirname "$0")/.."; pwd)
GODOT=${GODOT:-/Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot}
OUT=$1; mkdir -p "$OUT"
# background.gd alternates day/night on every launch (counter in user://remake_cfg.ini): pin it to "day"
# for every run so two runs are comparable, and put the player's file back afterwards
CFG="$HOME/Library/Application Support/Godot/app_userdata/Ninja School 1 Remake/remake_cfg.ini"
[ -f "$CFG" ] && cp "$CFG" "$OUT/.cfg_backup"
trap '[ -f "$OUT/.cfg_backup" ] && mv "$OUT/.cfg_backup" "$CFG"' EXIT
SK="eL=true;eM=true;eN=true;fc=true;I=300;J=300;G=3000;H=3000"
# name | map,x,y | keys | shot times
SC=(
  "map4_boar|4,1250,312|attack@1.0-1.4,attack@1.6-2.0,weapon@2.2-2.3|1.2,1.8,2.6,3.2"
  "map9_classmates|9,230,288|right@0.5-1.0,attack@1.2-1.6,attack@1.8-2.2|1.4,2.0,2.8,3.6"
  "map97_river|97,300,200|attack@1.0-1.5,weapon@1.6-1.7|1.2,2.0,3.0,4.0"
  "map6_monsters|6,5976,240|left@0.5-1.5,attack@1.6-2.2,weapon@2.4-2.5|1.8,2.4,3.0,3.6"
)
for sc in "${SC[@]}"; do
  IFS='|' read -r name map keys shots <<< "$sc"
  d="$OUT/$name"; mkdir -p "$d"
  q=$(echo "$shots" | tr ',' '\n' | tail -1); q=$(echo "$q + 0.5" | bc)
  printf '[launch]\n\ncount=0\n' > "$CFG"
  timeout 120 "$GODOT" --fixed-fps 60 --path "$R" -- --seed=1 --map="$map" --set="$SK" --keys="$keys" --shots="$shots" --out="$d" --quit="$q" > "$d/log.txt" 2>&1 < /dev/null
  echo "$name: $(ls "$d" | grep -c png) shots, script errors $(grep -c 'SCRIPT ERROR' "$d/log.txt")"
done
