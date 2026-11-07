from pathlib import Path
import json,sys,time,os
sys.path.insert(0,str(Path.cwd()))
from scripts.local_reverse import check_model,local_request,atomic_json,MODEL
root=Path.cwd();p=root/'analysis/project-state.json'; d=json.loads(p.read_text());raw=(root/'analysis/NinjaSchool1/sources/a.java').read_text().splitlines()
ranges=[(11400,11468),(11565,11590),(9180,9270),(9525,9580),(9650,9680),(9790,9840)]
evidence='\n'.join(f'{i}: {raw[i-1]}' for a,b in ranges for i in range(a,b+1))
(root/'analysis/ninja1-collision-evidence.txt').write_text(evidence)
atomic_json(p,{**d,'updated_at':time.time(),'active_local_job':{'pid':os.getpid(),'kind':'collision_decode','started_at':time.time(),'output':'analysis/ninja1-collision-spec.json'}})
try:
 check_model(MODEL)
 prompt='Decode ONLY original NinjaSchool1 collision-grid semantics from Java below. Return compact JSON {"masks":[{"mask":2,"meaning":"...","evidence_lines":[...],"confidence":"..."}],"coordinate_units":"...","solid_ground_mask":int_or_null,"hazard_mask":int_or_null,"directional_block_masks":{"left":int_or_null,"right":int_or_null},"unknowns":[]}. Explain helper args and boundary coordinates. Carefully distinguish collision solid from decorative/ladder/water/animation bits. Only label meaning when gameplay callsite proves it. `iT` is raw tile byte grid; `iU` is per-cell int flags; `jh` tile size24. Do not guess. JSON only, max900 tokens. /no_think\n'+evidence
 r=local_request('/api/chat',{'model':MODEL,'stream':False,'think':False,'format':'json','keep_alive':'10m','messages':[{'role':'user','content':prompt}],'options':{'num_ctx':32768,'num_predict':1500,'temperature':0}},timeout=300)
 rawout=r['message']['content'];(root/'analysis/ninja1-collision-raw.txt').write_text(rawout);spec=json.loads(rawout)
 if r.get('done_reason')=='length':raise ValueError('Local output truncated')
 atomic_json(root/'analysis/ninja1-collision-spec.json',spec);print('COLLISION_SPEC_SAVED',flush=True)
except Exception as e:print('COLLISION_DECODE_STOPPED',str(e),flush=True)
finally:
 d=json.loads(p.read_text());atomic_json(p,{**d,'active_local_job':None,'updated_at':time.time()})
 import subprocess
 subprocess.run(['ollama','stop',MODEL],capture_output=True,timeout=30)
