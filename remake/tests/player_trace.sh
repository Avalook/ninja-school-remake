#!/bin/bash
# tests/player_trace.sh record|check : behaviour lock for the player (and the monsters it fights).
# Runs fixed scenarios headless at --fixed-fps 60 with seeded key timelines (gen_keys.py) and writes the
# per-tick rule trace of main.gd --trace (player S vars, pos/vel, monster slots).
#   record  write tests/player_trace/expected/<name>.txt.gz (baseline, made before a refactor)
#   check   run again and diff against the baseline; any differing line = behaviour changed
R=$(cd "$(dirname "$0")/.."; pwd)
GODOT=${GODOT:-/Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot}
MODE=${1:-check}
E=$R/tests/player_trace/expected; O=${OUT:-/tmp/claude-501/player_trace}; mkdir -p "$E" "$O"
ALL="left,right,jump,attack,down,jump_left,jump_right,weapon,hp_potion,mp_potion,stealth,transform"
SKILLS="eL=true;eM=true;eY=true;eT=true;eN=true;fc=true;eO=true;eS=true;eR=true;eP=true;I=300;J=300;G=3000;H=3000"
# name | map,x,y | --set | key seed | keys | seconds | fixed opening keys
SCEN=(
  "m4_combat|4,48,120||1|left,right,right,jump,attack,attack,down,jump_right|40"
  "m4_weapons|4,48,120|eN=true;I=200;J=200|2|$ALL|40"
  "m6_skills|6,5976,240|$SKILLS|3|$ALL|45"
  "m5_fall_water|5,456,1872|$SKILLS|4|left,right,jump,attack,down|40"
  "m3_bike|3,60,216|$SKILLS|5|left,right,right,right,jump,attack|40|jump@1.00-1.10"
  "m4_death|4,48,120|G=20;H=20|7|left,right,right,jump,attack,attack|40"
  "m7_water_skills|7,300,100|$SKILLS|8|left,right,jump,down,attack|40"
  "m7_water_plain|7,300,100||9|left,right,jump,attack|30"
  "m1_transform|1,1416,216|eH=false;eT=true;fc=true;I=300;J=300;eL=true|10|transform,transform,stealth,left,right,attack,mp_potion|40"
  "m1_snails|1,1416,216|eL=true|6|left,right,jump,attack,jump_left|40"
  "m9_classmates|9,230,288|$SKILLS|11|left,right,right,jump,attack,attack,attack,weapon|40"
  "m4_boar|4,1250,312|$SKILLS|12|left,right,attack,attack,attack,weapon,jump|40"
  "m97_river|97,300,200|$SKILLS|13|left,right,attack,attack,weapon,jump|40"
  "m95_golem|95,150,264|$SKILLS|14|left,right,attack,attack,weapon,jump|30"
)
fail=0
for sc in "${SCEN[@]}"; do
  [ -n "$ONLY" ] && [[ ! "$sc" =~ $ONLY ]] && continue
  IFS='|' read -r name map set seed keys secs pre <<< "$sc"
  k=$(python3 "$R/tests/player_trace/gen_keys.py" "$seed" "$secs" "$keys" "$pre")
  args=(--map="$map" --keys="$k" --quit="$secs" --trace="$O/$name.txt")
  [ -n "$set" ] && args+=(--set="$set")
  timeout 300 "$GODOT" --headless --fixed-fps 60 --path "$R" -- "${args[@]}" > "$O/$name.log" 2>&1
  errs=$(grep -c 'SCRIPT ERROR' "$O/$name.log")
  states=$(grep -oE ' A=[0-9]+' "$O/$name.txt" | sort | uniq -c | sort -rn | awk '{printf "%s%s ", $2, "x"$1}')
  if [ "$MODE" == "record" ]; then
    gzip -n -9 -c "$O/$name.txt" > "$E/$name.txt.gz"
    echo "recorded $name: $(wc -l < "$O/$name.txt") ticks, script errors $errs, states: $states"
  else
    d=$(diff <(gunzip -c "$E/$name.txt.gz") "$O/$name.txt" | grep -c '^[<>]')
    echo "check $name: $(wc -l < "$O/$name.txt") ticks, differing lines $d, script errors $errs"
    [ "$d" != "0" ] && { diff <(gunzip -c "$E/$name.txt.gz") "$O/$name.txt" | head -4; fail=1; }
  fi
done
exit $fail
