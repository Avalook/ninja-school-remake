#!/bin/bash
# tools/diff_suite.sh [filter] : differential suite, original JAR (lockstep emulator) vs oracle game/.
# Build the emulator first: tools/build_emu.sh
#   intro   New game -> map99 intro -> map4      game/tests/scripts/d1.txt
#   fuzz1   seeded fuzz (map99/map4)             game/tests/scripts/f1.txt   (gen_fuzz.py 1 400)
#   fuzz2   seeded fuzz                          tools/diff_scripts/f2.txt   (gen_fuzz.py 2 400)
#   fuzz3   seeded fuzz                          tools/diff_scripts/f3.txt   (gen_fuzz.py 3 400)
#   fmN     teleport to map N + fuzz (22 maps)   game/tests/scripts/fm*.txt
#   boss95  map95 boss fight                     tools/diff_scripts/boss95.txt
#   ending  boss -> rescue -> map94 D(10) -> map0 game/tests/scripts/end3d.txt
# Optional [filter]: only run tests whose name matches this regex (e.g. "^fm", "fuzz").
R=$(cd "$(dirname "$0")/.."; pwd)
F=${1:-.}
run() { [[ $1 =~ $F ]] || return 0; echo "##### $1 ($2)"; "$R/tools/diff_run.sh" "$R/$2" "$1" 20; }
run intro  game/tests/scripts/d1.txt
run fuzz1  game/tests/scripts/f1.txt
run fuzz2  tools/diff_scripts/f2.txt
run fuzz3  tools/diff_scripts/f3.txt
for m in 0 1 2 3 5 6 7 8 9 10 11 12 13 14 15 16 17 18 94 95 96 97; do
  run fm$m game/tests/scripts/fm$m.txt
done
run boss95 tools/diff_scripts/boss95.txt
run ending game/tests/scripts/end3d.txt
