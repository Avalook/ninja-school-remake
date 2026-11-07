from pathlib import Path
import json,sys,os,time
sys.path.insert(0,str(Path('scripts').resolve()))
from local_reverse import local_request,check_model,MODEL,atomic_json
root=Path.cwd(); state=root/'analysis/project-state.json'; out=root/'analysis/ninja1-tile-spec.json'
lines=(root/'analysis/NinjaSchool1/sources/a.java').read_text().splitlines()
ranges=[(11085,11133),(11400,11605)]
evidence='\n'.join(f'{n}: {lines[n-1]}' for a,b in ranges for n in range(a,b+1))
(root/'analysis/ninja1-tile-evidence.txt').write_text(evidence)
d=json.loads(state.read_text()); atomic_json(state,{**d,'active_local_job':{'pid':os.getpid(),'kind':'tile_renderer','started_at':time.time(),'output':str(out)}})
try:
 check_model(MODEL)
 prompt='Analyze ONLY supplied original Java tile renderer evidence. Return JSON with tile_image_path, tile_size, draw_rules ONLY (raw id ranges -> source y formula and exclusions), runtime_tile_transforms, uncertainties. Line refs. Max 450 tokens total, compact JSON. Omit collision analysis.\n'+evidence
 r=local_request('/api/chat',{'model':MODEL,'stream':False,'think':False,'format':'json','keep_alive':'10m','messages':[{'role':'user','content':prompt}],'options':{'num_ctx':16384,'num_predict':1000,'temperature':0}},timeout=360)
 (root/'analysis/ninja1-tile-spec-raw.txt').write_text(r['message']['content']); spec=json.loads(r['message']['content']); atomic_json(out,spec); print('TILE_SPEC_SAVED',out,flush=True)
except Exception as e:
 print('TILE_SPEC_FAILED',str(e),flush=True)
finally:
 d=json.loads(state.read_text()); atomic_json(state,{**d,'active_local_job':None})
