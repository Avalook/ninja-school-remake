from pathlib import Path
import sys,json,time
sys.path.insert(0,str(Path.cwd()))
sys.path.insert(0,str(Path('scripts').resolve()))
from local_game import ProjectTools
from local_reverse import atomic_json
project=Path('games/ninja1-original'); runner=ProjectTools(project,Path('analysis/NinjaSchool1'))
results={}
for name in ['check_project','smoke_test','run_game']:
 try: results[name]=json.loads(runner.godot(name))
 except Exception as error: results[name]={'ok':False,'output':str(error)}
 atomic_json(Path('analysis/ninja1-original-scene-checks.json'),{'updated_at':time.time(),'project':str(project),'checks':results,'playable':False,'new_hd_art':False})
 print(name,results[name]['ok'],results[name].get('output','')[-4500:],flush=True)
 if not results[name]['ok']: break
