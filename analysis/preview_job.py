from pathlib import Path
import json,sys,os,time
sys.path.insert(0,str(Path('scripts').resolve()))
from local_reverse import local_request,check_model,MODEL,atomic_json
root=Path.cwd(); state=root/'analysis/project-state.json'; out=root/'analysis/original-map-preview.html'
spec={'static_base_only':{'ids':'1..120','source_x':0,'source_y':'(id-1)*24','width':24,'height':24,'skip':0},'limits':'Raw static tile atlas placement only. Do not implement any animation flags from prior report; they require further verification.'}
d=json.loads(state.read_text()); atomic_json(state,{**d,'active_local_job':{'pid':os.getpid(),'kind':'original_map_preview','started_at':time.time(),'output':str(out)}})
try:
 check_model(MODEL)
 prompt='Write one self-contained HTML canvas diagnostic preview, Return raw HTML, no JSON, no markdown fences, under 1300 tokens. Vietnamese labels. A STATIC original map preview, not playable or HD. Label exactly: Bản xem trước tĩnh từ dữ liệu gốc — chưa chơi được, chưa nâng cấp HD. Load maps/ninja1/map-000.json with columns,rows,cells; load image NinjaSchool1/resources/t/t.png (24x2880). Render correct original tile draw rules below using canvas drawImage. Tile pixels24. Do not invent character, enemies or scenery; include only confirmed tiles on plain light sky. Render entire map at native size in horizontally scrollable container. Pixelated scaling. Include map selector IDs0..18,94,95,96,97,99 and zoom100%,200%. Paths for IDs map-000.json etc padStart(3,0). Show map ID and dimensions, warning animations/runtime changes not restored. Visible error text on fetch/image failure. No external libraries, no remote URLs, no eval, no innerHTML. Atlas source rectangles validated in range. Return concise readable code. Decoded map raw tile IDs are not runtime-transformed; disclose out-of-range tiles count and that dynamic tiles use only base atlas images. Use only confirmed renderer rules.\nSPEC:\n'+json.dumps(spec)
 r=local_request('/api/chat',{'model':MODEL,'stream':False,'think':False,'keep_alive':'10m','messages':[{'role':'user','content':prompt}],'options':{'num_ctx':8192,'num_predict':2300,'temperature':0}},timeout=360)
 html=r['message']['content'].strip(); (root/'analysis/original-map-preview-raw.txt').write_text(html)
 if not isinstance(html,str) or len(html.encode())>24000 or '<canvas' not in html or 'https://' in html or 'http://' in html: raise ValueError('Invalid preview output')
 out.write_text(html); print('PREVIEW_SAVED',out,flush=True)
except Exception as e: print('PREVIEW_FAILED',str(e),flush=True)
finally:
 d=json.loads(state.read_text()); atomic_json(state,{**d,'active_local_job':None})
