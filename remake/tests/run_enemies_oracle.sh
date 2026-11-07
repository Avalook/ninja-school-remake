#!/bin/bash
# remake/tests/run_enemies_oracle.sh [ticks] [maps...]
# 1) runs the ORIGINAL game (game/, oracle) from the exported state of each map with no input for N ticks
# 2) runs remake/tests/enemies_oracle.gd on the same state and compares monster/boss state per tick
R=$(cd "$(dirname "$0")/../.."; pwd)
GODOT=/Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot
T=${1:-500}; shift
MAPS=${@:-4 1 6 7 12 95 97}
# optional per-map scenario: ACT_<map>="k@313:10:500:1 set@303:hu:1" (remake test syntax, see enemies_oracle.gd)
O=${TMPDIR:-/tmp}/enemies_oracle; mkdir -p $O
EXTRA=jX,jY,jZ,ka,kb,jV,jW,kf,fz,fp,gq,gr,gu,gS,gT,gV,ia,hs,ht,hP,dk,do,di,dj,cT,cX,cQ,cR,cS,cx,cy,cH,cu,y,z,fg,db,cZ,da,bS,bQ,bZ,bX,bY
[ -f "$(dirname "$0")/enemies_scenarios.env" ] && [ -z "$NO_SCENARIOS" ] && set -a && . "$(dirname "$0")/enemies_scenarios.env" && set +a
for M in $MAPS; do
  ACTV=ACT_$M; ACT=${!ACTV}
  printf "wait 12000\nset eH 1\nset fV 0\ncall x $M\nwait 120\nloadstate $R/remake/data/static.json $R/remake/data/maps/map_$M.json\n" > $O/o$M.txt
  # translate scenario actions into oracle script lines (sorted by tick; 303 = right after loadstate)
  CUR=303
  for a in $(echo $ACT | tr ' ' '\n' | sort -t@ -k2 -n); do
    K=${a%%@*}; P=${a#*@}; AT=${P%%:*}; REST=${P#*:}; [ "$REST" = "$P" ] && REST=""
    [ $AT -gt $CUR ] && echo "wait $(((AT-CUR)*40))" >> $O/o$M.txt && CUR=$AT
    [ $K = k ] && echo "call k ${REST//:/ }" >> $O/o$M.txt
    [ $K = set ] && echo "set ${REST//:/ }" >> $O/o$M.txt
    [ $K = u ] && echo "call u" >> $O/o$M.txt
    [ $K = s ] && printf "set fg 1\ncall u\nset fg 0\n" >> $O/o$M.txt
  done
  echo "wait $(((T+303-CUR)*40))" >> $O/o$M.txt
  (cd $R/game && NINJA_RMS=user://enemy_oracle_$M DIFF=1 TRACE_EXTRA=$EXTRA $GODOT --headless --path . --script res://tests/script_run.gd -- $O/o$M.txt $O/o$M.trace > $O/o$M.log 2>&1 < /dev/null)
  grep -c "SCRIPT ERROR" $O/o$M.log | sed "s/^/  oracle script errors (paint only expected): /"
  (cd $R/remake && timeout 300 $GODOT --headless --path . res://tests/enemies_oracle.tscn -- $M $O/o$M.trace $T $ACT 2>&1 | grep -E "MAP|per field|player damage|SCRIPT ERROR|at: " | head -12)
done
