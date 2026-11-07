#!/bin/bash
# tools/diff_suite.sh : core differential suite, original JAR (lockstep emulator) vs oracle game/.
# Build the emulator first: tools/build_emu.sh
#   intro   New game -> map99 intro -> map4      game/tests/scripts/d1.txt
#   fuzz1   seeded fuzz (map99/map4)             game/tests/scripts/f1.txt
#   boss95  map95 boss fight                     tools/diff_scripts/boss95.txt
#   ending  boss -> rescue -> map94 D(10) -> map0 game/tests/scripts/end3d.txt
R=$(cd "$(dirname "$0")/.."; pwd)
run() { echo "##### $1 ($2)"; "$R/tools/diff_run.sh" "$R/$2" "$1" 20; }
run intro  game/tests/scripts/d1.txt
run fuzz1  game/tests/scripts/f1.txt
run boss95 tools/diff_scripts/boss95.txt
run ending game/tests/scripts/end3d.txt
