#!/bin/bash
# tools/build_emu.sh : rebuild the J2ME oracle emulator (analysis/claude/emu) from the repo.
#   classes/  <- javac of the harness (src/harness/Emu.java, harnessR/harness/R.java) + MIDP stubs (src/javax/...)
#   pcls/     <- PATCHED COPIES of a.class, b.class, Midlet.class extracted from NinjaSchool1.jar (patchcls.py).
# The original JAR is only read, never modified. classes/ and pcls/ are gitignored (do not commit .class files).
# Needs JDK 21 (javac/jar) and python3. Override with JAVA_HOME=...
set -euo pipefail
R=$(cd "$(dirname "$0")/.."; pwd)
E=$R/analysis/claude/emu
JAR=$R/NinjaSchool1.jar
JH=${JAVA_HOME:-/opt/homebrew/opt/openjdk@21}
JAVAC=$JH/bin/javac; JARX=$JH/bin/jar
[ -x "$JAVAC" ] || JAVAC=$(command -v javac)
[ -x "$JARX" ] || JARX=$(command -v jar)

cd "$E"
rm -rf classes pcls; mkdir -p classes pcls
SRCS=$(find src harnessR -name '*.java')
"$JAVAC" -nowarn -encoding UTF-8 -d classes -cp "$JAR" $SRCS

TMP=$(mktemp -d); trap 'rm -rf "$TMP"' EXIT
(cd "$TMP" && "$JARX" xf "$JAR" a.class b.class Midlet.class)
python3 patchcls.py "$TMP" a.class b.class Midlet.class

echo "built: $(find classes -name '*.class' | wc -l | tr -d ' ') harness/stub classes, pcls: $(ls pcls | tr '\n' ' ')"
