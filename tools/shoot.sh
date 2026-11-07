#!/bin/bash
# tools/shoot.sh <autoscript> <outdir> [extra args]: run the game windowed with scripted input, save shots
cd "$(dirname "$0")/../game"; rm -rf "$2"; mkdir -p "$2"
/Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot --headless --path . --import > /dev/null 2>&1 < /dev/null
GODOT=/Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot
rm -rf "$HOME/Library/Application Support/Godot/app_userdata/Ninja School 1/shot_rms"; NINJA_RMS=user://shot_rms timeout ${T:-180} $GODOT --path . -- --auto "$1" --shots "$2" ${@:3} > "$2/log.txt" 2>&1 < /dev/null
grep -m5 -A4 'SCRIPT ERROR' "$2/log.txt"; ls "$2" | grep png | tr '\n' ' '
