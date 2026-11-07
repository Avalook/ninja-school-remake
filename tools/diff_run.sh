#!/bin/bash
# tools/diff_run.sh <script> <name> : run the same input script on the original JAR (lockstep emulator) and the Godot port, compare traces
R=$(cd "$(dirname "$0")/.."; pwd); S=$1; N=$2; O=/tmp/claude-501/diff; mkdir -p $O
(cd $R/analysis/claude/emu && T=600 JOPTS="-Dlockstep=true -Dtrace=1" ./run.sh d_$N < $S > /dev/null; cp d_$N/trace.txt $O/$N.emu.txt)
GODOT=/Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot
(cd $R/game && NINJA_RMS=user://test_rms DIFF=1 NW=240 NH=320 timeout 600 $GODOT --headless --path . --script res://tests/script_run.gd -- $S $O/$N.gd.txt > $O/$N.gd.log 2>&1 < /dev/null)
grep -m3 -A3 'SCRIPT ERROR' $O/$N.gd.log
python3 $R/analysis/claude/emu/cmp_trace.py $O/$N.emu.txt $O/$N.gd.txt ${3:-6}
