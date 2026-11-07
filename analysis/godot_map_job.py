from pathlib import Path
import json,sys,time,os
sys.path.insert(0,str(Path('scripts').resolve()))
from local_reverse import check_model,local_request,atomic_json,MODEL
root=Path.cwd(); state=root/'analysis/project-state.json'; game=root/'games/ninja1-original'
d=json.loads(state.read_text()); atomic_json(state,{**d,'active_local_job':{'pid':os.getpid(),'kind':'original_map_godot','started_at':time.time(),'output':str(game/'original_map.gd')}})
try:
 check_model(MODEL)
 prompt='''Write raw Godot4 GDScript for a Node2D original tile-map scene, not a playable game yet. No markdown, no JSON. Under100 lines /1500 tokens. Only one file. Must expose members grid:Dictionary, atlas:Texture2D, camera:Camera2D, tile_count:int and methods get_tile_rect(id:int)->Rect2, pan_camera(delta:Vector2)->void. In _ready, load res://data/map-000.json via FileAccess and JSON.parse_string. Contract columns80 rows19 cells 2D array cells[y][x], tile24px. Copy original atlas at res://assets/tiles.png (24x2880). Validate 2D grid; fail clear with push_error and quit1. Count valid nonzero tile IDs in tile_count. texture_filter=CanvasItem.TEXTURE_FILTER_NEAREST. Set default clear color light blue. _draw draws atlas for each tile IDs1..120: Rect2(0,(id-1)*24,24,24) onto Rect2(x*24,y*24,24,24). get_tile_rect returns empty Rect2 for0/out-of-range. Camera2D camera zoom Vector2(1.5,1.5) at world center initially. Pan via arrows via built-in ui actions _process(delta), speed350worldpx/sec. pan_camera takes absolute position offset and clamps actual camera.position so viewport never shows beyond world if world larger than viewport; if world smaller center that axis. Visible world viewport size /camera.zoom. Camera should be current. Add CanvasLayer + readable Label near(20,20) with Vietnamese text: Ninja School1 — Cảnh gốc đang khôi phục; Map0 · Xem cảnh, chưa có nhân vật/va chạm/HD; Mũi tên: xem cảnh · Esc: thoát. Escape quits. Do not invent collision, character or enemies; no other assets available. Handle resource/JSON failures visibly. Scene root loads this script. Test mode external SceneTree script checks grid dimensions/atlas/get_tile_rect/tile_count/camera. Avoid integer division warnings, unsupported APIs and Variant type inference warnings. Use explicit types for parsed Variants and dictionary values. Output only compilable GDScript. /no_think'''
 response=local_request('/api/chat',{'model':MODEL,'stream':False,'think':False,'keep_alive':'10m','messages':[{'role':'user','content':prompt}],'options':{'num_ctx':8192,'num_predict':2200,'temperature':0}},timeout=360)
 code=response['message']['content'].strip(); (root/'analysis/original-map-godot-raw.txt').write_text(code)
 if code.startswith('```'):code=code.split('\n',1)[1].rsplit('```',1)[0].strip()
 if not code.startswith('extends Node2D') or len(code.encode())>18000:raise ValueError('Invalid code output')
 (game/'original_map.gd').write_text(code+'\n'); print('ORIGINAL_MAP_CODE_SAVED',flush=True)
except Exception as e:print('ORIGINAL_MAP_CODE_FAILED',str(e),flush=True)
finally:
 d=json.loads(state.read_text());atomic_json(state,{**d,'active_local_job':None})
