#!/bin/bash
# Regenerate game/core/orig.gd from the raw CFR sources (read-only inputs).
set -e
R=$(cd "$(dirname "$0")/../.."; pwd); P=$R/.venv-tools/bin/python; T=${TMPDIR:-/tmp}/ninja-transpile; mkdir -p $T
$P $R/tools/transpile/prep.py $R/analysis/NinjaSchool1/sources/a.java $T/a.java
$P $R/tools/transpile/prep.py $R/analysis/NinjaSchool1/sources/Midlet.java $T/M.java
SKIP=_ctor_a,f_s,a_sB,M_a_sB,M_b_s,ae_,af_,aA_,aD_,az_,aG_,ay_,aB_,aC_,aF_,aH_,commandAction_oo,M_c_,M_startApp_,M_destroyApp_z,M_pauseApp_,run_,paint_o
$P $R/tools/transpile/j2gd.py $T/a.java $T/M.java $R/game/core/orig.gd $SKIP
# post-patches (documented): Midlet.run waits for loading-anim counter S>=90 on its own thread; host drives loading synchronously.
python3 - "$R/game/core/orig.gd" <<'P'
import sys; p=sys.argv[1]; s=open(p).read()
s=s.replace("    while (S < 90):\n        pass\n","    _hx_loading_done()\n")
open(p,'w').write(s)
P
# a.run(): bytecode -> state machine (CFR could not structure it)
JAVAP=/opt/homebrew/opt/openjdk@21/bin/javap
mkdir -p $T/jar; (cd $T/jar && unzip -oq $R/NinjaSchool1.jar a.class)
$JAVAP -c -p -cp $T/jar a > $T/a.javap
awk '/public final void run\(\);/{p=1} p&&/^$/{exit} p' $T/a.javap > $T/run.javap
$P $R/tools/transpile/bc2gd.py $T/run.javap $R/game/core/orig_run.gd 18400
