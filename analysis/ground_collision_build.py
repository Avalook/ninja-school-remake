from pathlib import Path
import json,sys,time,os
sys.path.insert(0,str(Path.cwd()))
from scripts.local_reverse import check_model,local_request,atomic_json,MODEL
root=Path.cwd();state=root/'analysis/project-state.json';game=root/'games/ninja1-original';d=json.loads(state.read_text())
atomic_json(state,{**d,'updated_at':time.time(),'active_local_job':{'pid':os.getpid(),'kind':'map0_ground_collision','started_at':time.time(),'output':'games/ninja1-original/terrain_collision.gd'}})
try:
 check_model(MODEL)
 evidence=(root/'analysis/ninja1-collision-evidence.txt').read_text()
 script=(game/'original_map.gd').read_text()
 prompt='''Implement collision-ground truth on the separate original map project using local source evidence. Return JSON {"file":"terrain_collision.gd","script":"..."}. Add a Godot4 Node2D component only for STATIC GROUND mask2, which original physics call sites verify solid. Raw map is grid Dictionary {columns,rows,cells}, cells[y][x], tile size24. Derive flags from raw tile IDs exactly as Java loader L11409-12: [1,2,3,4,5,6,7,36,37,54,91,92,93,94,73,74,97,98,120] are solid mask2. Add StaticBody2D child with a RectangleShape2D 24x24 positioned at each cell center when ID in that list. Expose `solid_tile_count` and keep the grid unmodified. Use explicit types / no Variant inference warnings, bounds checks, fail visibly if malformed grid. Do NOT implement hazard16, directional masks4/8, ladders, or any other flag yet because their complete behavior is not yet proven. This adds actual terrain floor collider only; not player collision integration. Under90 lines. JSON must be valid and complete. /no_think\nSOURCE EVIDENCE:\n'''+evidence+'\nCURRENT GODOT SCRIPT:\n'+script
 r=local_request('/api/chat',{'model':MODEL,'stream':False,'think':False,'format':'json','keep_alive':'10m','messages':[{'role':'user','content':prompt}],'options':{'num_ctx':32768,'num_predict':2200,'temperature':0}},timeout=300)
 raw=r['message']['content'];(root/'analysis/ninja1-ground-collision-patch.json').write_text(raw);data=json.loads(raw)
 if r.get('done_reason')=='length' or data.get('file')!='terrain_collision.gd':raise ValueError('Incomplete or unexpected component')
 code=data.get('script')
 if not isinstance(code,str) or not code.startswith('extends Node2D') or len(code.encode())>16000:raise ValueError('Invalid GDScript output')
 (game/'terrain_collision.gd').write_text(code+'\n');print('GROUND_COLLIDER_SAVED',flush=True)
except Exception as e:print('GROUND_COLLIDER_STOPPED',str(e),flush=True)
finally:
 d=json.loads(state.read_text());atomic_json(state,{**d,'active_local_job':None,'updated_at':time.time()})
 import subprocess
 subprocess.run(['ollama','stop',MODEL],capture_output=True,timeout=30)
