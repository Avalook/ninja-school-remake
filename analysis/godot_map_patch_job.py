from pathlib import Path
import json,sys,time,os
sys.path.insert(0,str(Path('scripts').resolve()))
from local_reverse import check_model,local_request,atomic_json,MODEL
root=Path.cwd(); state=root/'analysis/project-state.json'; path=root/'games/ninja1-original/original_map.gd'
d=json.loads(state.read_text());atomic_json(state,{**d,'active_local_job':{'pid':os.getpid(),'kind':'original_map_godot_fix','started_at':time.time(),'output':str(path)}})
try:
 check_model(MODEL)
 prompt='''Fix supplied Godot4 script with compact JSON {"patches":[{"find":"exact unique text","replace":"replacement"}]}. No commentary. Under1000 tokens. Fix ALL:1 Viewport.clear_color invalid; use RenderingServer.set_default_clear_color. 2 valid_grid must be Array of row_ids, grid must dictionary {columns:COLS,rows:ROWS,cells:valid_grid}. _draw reads grid.cells[y] not numeric dictionarykeys.3 Add camera child before make_current.4 pan_camera(delta) takes OFFSET: target=camera.position+delta; _process passes dir*PAN_SPEED*delta (not camera.position added). Call pan_camera even with zero dir so clamping follows viewport resize.5 draw_texture_rect_region(atlas,dst,src) correct order. Keep all others. Don't rewrite whole file; unique exact string patches only. /no_think\n'''+path.read_text()
 r=local_request('/api/chat',{'model':MODEL,'stream':False,'think':False,'format':'json','keep_alive':'10m','messages':[{'role':'user','content':prompt}],'options':{'num_ctx':8192,'num_predict':1700,'temperature':0}},timeout=360)
 raw=r['message']['content'];(root/'analysis/original-map-godot-patch.json').write_text(raw);patches=json.loads(raw)['patches'];code=path.read_text()
 if not isinstance(patches,list) or not 1<=len(patches)<=14:raise ValueError('Invalid patch count')
 for patch in patches:
  find,replace=patch['find'],patch['replace']
  if not isinstance(find,str) or not isinstance(replace,str) or not find or code.count(find)!=1:raise ValueError('Patch not unique')
  code=code.replace(find,replace,1)
 if len(code.encode())>18000:raise ValueError('Output too large')
 path.write_text(code);print('ORIGINAL_MAP_PATCH_APPLIED',flush=True)
except Exception as e:print('ORIGINAL_MAP_PATCH_FAILED',str(e),flush=True)
finally:
 d=json.loads(state.read_text());atomic_json(state,{**d,'active_local_job':None})
