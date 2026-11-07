#!/bin/bash
# usage: run.sh outdir < script ; makes sheet.png of shots in order
D=$(cd "$(dirname "$0")"; pwd); OUT=$1; rm -rf "$D/$OUT"; mkdir -p "$D/$OUT"; cat > "$D/$OUT/script.txt"
cd "$D"; timeout ${T:-120} /opt/homebrew/opt/openjdk@21/bin/java -Djava.awt.headless=true -Dmicroedition.platform=Nokia6300 -cp pcls:classes:../../../NinjaSchool1.jar harness.Emu "$OUT/script.txt" "$OUT" 2>&1 | grep -E '^\[vars\]|Exception|err' | head -30
python3 - "$D/$OUT" <<'P'
import sys,os,re
from PIL import Image
d=sys.argv[1]; s=open(d+'/script.txt').read().split('\n')
names=[l.split()[1] for l in s if l.startswith('shot ')]
ims=[Image.open(f'{d}/{n}.png').resize((240,320)) for n in names if os.path.exists(f'{d}/{n}.png')]
if ims:
  cols=min(6,len(ims)); rows=(len(ims)+cols-1)//cols
  c=Image.new('RGB',(cols*244,rows*324),(60,0,60))
  for i,im in enumerate(ims): c.paste(im,((i%cols)*244,(i//cols)*324))
  c.save(d+'/sheet.png')
P
