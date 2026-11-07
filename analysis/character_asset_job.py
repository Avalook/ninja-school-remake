from pathlib import Path
import json,sys,os,time
sys.path.insert(0,str(Path('scripts').resolve()))
from local_reverse import local_request,check_model,atomic_json,MODEL
root=Path.cwd(); state=root/'analysis/project-state.json'; out=root/'analysis/ninja1-character-loading.json'
lines=(root/'analysis/NinjaSchool1/sources/a.java').read_text().splitlines()
hits=[i for i,l in enumerate(lines) if '/cp/' in l]
indexes=sorted({j for i in hits for j in range(max(0,i-16),min(len(lines),i+17))})
evidence='\n'.join(f'{i+1}: {lines[i]}' for i in indexes)
(root/'analysis/ninja1-character-loading-evidence.txt').write_text(evidence)
d=json.loads(state.read_text()); atomic_json(state,{**d,'active_local_job':{'pid':os.getpid(),'kind':'original_character_loading','started_at':time.time(),'output':str(out)}})
try:
 check_model(MODEL)
 prompt='Analyze supplied original Java character resource-loading evidence only. Identify image fields for /cp/ch, /cp/cb, /cp/cl (head/body/legs) and any positioning/animation data fields or character draw helper names actually present. Return compact JSON {image_fields:[{symbol,role,resource_pattern,lines}],related_fields:[],draw_helpers:[],unknown:[]}. Max450 tokens. Exact obfuscated symbols, no guesses or full-game claim. /no_think\n'+evidence
 r=local_request('/api/chat',{'model':MODEL,'stream':False,'think':False,'format':'json','keep_alive':'10m','messages':[{'role':'user','content':prompt}],'options':{'num_ctx':16384,'num_predict':1000,'temperature':0}},timeout=300)
 raw=r['message']['content'];(root/'analysis/ninja1-character-loading-raw.txt').write_text(raw);spec=json.loads(raw);atomic_json(out,spec);print('CHARACTER_LOADING_SAVED',flush=True)
except Exception as e: print('CHARACTER_LOADING_FAILED',str(e),flush=True)
finally:
 d=json.loads(state.read_text());atomic_json(state,{**d,'active_local_job':None})
