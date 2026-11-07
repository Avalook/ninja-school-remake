# Hero animation from the Veo 3 reference clips (keyed + normalised by tools/art/veo_extract.py).
# Chooses a clip segment from the ORIGINAL state (A = state, B = frame index) and plays it at 24 fps
# (time-warped where the original action is shorter), so gameplay timing/hit boxes stay original.
# Returns false from draw() when nothing is mapped -> the runtime bone rig is used instead.
extends RefCounted

# segment: [clip, first, last, mode]  mode: loop | once | pingpong | hold
const SEG := {
	"idle": ["boy_breathing_stance_anima_", 30, 150, "pingpong"],
	"run": ["boy_running_cycle_animation", 40, 56, "loop"],
	"run_start": ["boy_running_animation_refe_", 12, 30, "once"],
	"skid": ["boy_running_animation_refe_", 110, 148, "once"],
	"jump_up": ["boy_jumping_animation", 24, 48, "once"],
	"fall": ["boy_jumping_animation", 60, 92, "once"],
	"land": ["boy_jumping_animation", 100, 126, "once"],
	"flip": ["boy_performing_aerial_some_", 36, 98, "loop"],
	"slash1": ["drawing_katana_and_attacking", 20, 62, "once"],
	"slash2": ["boy_performing_sword_attacks", 14, 50, "once"],
	"throw": ["throwing_steel_shuriken", 22, 58, "once"],
	"air_slash": ["boy_executing_aerial_slash", 22, 70, "once"],
	"hurt": ["boy_gets_hit", 36, 96, "once"],
	"crouch": ["boy_sinks_into_ground", 6, 26, "once"],
	"under": ["boy_sinks_into_ground", 54, 100, "pingpong"],
	"eyes": ["performing_fade_transforma_", 72, 112, "pingpong"],
	"power": ["boy_powers_up_with", 18, 110, "once"],
}
# how long (s) the original action lasts, so one-shot clips are time-warped to fit it
const FIT := {"slash1": 0.36, "slash2": 0.36, "throw": 0.40, "run_start": 0.25, "air_slash": 0.45, "land": 0.35}

var clips := {}
var meta := {}
var cur := ""
var t := 0.0
var last_x := 0.0
var vx_s := 0.0
var last_A := -1
var after := ""     # queued one-shot transition (skid / land)
var enabled := true

func _clip(name: String):
	if clips.has(name):
		return clips[name]
	var p := "res://art/veo/%s.webp" % name
	if not ResourceLoader.exists(p):
		clips[name] = null
		return null
	clips[name] = load(p)
	meta[name] = JSON.parse_string(FileAccess.get_file_as_string("res://art/veo/%s.json" % name))
	return clips[name]

func preload_all() -> void:
	for k in SEG:
		_clip(SEG[k][0])

func ok() -> bool:
	return enabled and _clip("boy_breathing_stance_anima_") != null

# map original state -> segment name ("" = not mapped)
func _want(core, B: int) -> String:
	var A: int = core.A
	if core.fz or B in [25, 26]:
		return "eyes"
	if A == 23:
		return "power"
	if B == 24:
		return "under"
	if B == 22:
		return "crouch"
	if B == 21:
		return "hurt"
	if B in [8, 9, 10, 11]:
		return "flip"
	if B in [13, 14, 35, 36, 37, 38]:
		return "slash1"
	if B in [15, 16, 39, 40, 41, 42]:
		return "slash2"
	if B in [17, 18, 19, 20]:
		return "throw"
	if B >= 27 and B <= 34:
		return "air_slash"
	if B in [7, 43, 44, 45]:
		return "jump_up"
	if B in [12, 23]:
		return "fall"
	if B in [2, 3, 4, 5, 6] and A == 2:
		return "run"
	if B in [0, 1]:
		return "idle"
	return ""

func update(core, B: int, world_x: float, dt: float) -> void:
	if dt <= 0.0:
		return
	var vx := (world_x - last_x) / dt
	if abs(vx) > 600.0:
		vx = 0.0
	last_x = world_x
	vx_s = lerpf(vx_s, vx, 1.0 - exp(-dt / 0.08))
	var A: int = core.A
	var want := _want(core, B)
	# transitions with their own one-shot clips (skid when stopping, landing, run start)
	var trans := ["skid", "land", "run_start"]
	if cur in trans and t < _dur(cur) and want in ["idle", "run"]:
		want = cur
	elif want == "idle" and cur == "run" and abs(vx_s) > 25.0:
		want = "skid"
	elif want == "idle" and cur == "fall":
		want = "land"
	elif want == "run" and cur in ["idle", "skid", "land"]:
		want = "run_start"
	if want != cur:
		cur = want
		t = 0.0
	var rate := 1.0
	if cur == "run":
		rate = clampf(abs(vx_s) / 95.0, 0.55, 1.35)
	t += dt * rate
	last_A = A

func _dur(name: String) -> float:
	var seg: Array = SEG[name]
	return float(FIT.get(name, float(int(seg[2]) - int(seg[1]) + 1) / 24.0))

func frame_index() -> int:
	if cur == "" or not SEG.has(cur):
		return -1
	var seg: Array = SEG[cur]
	var n: int = int(seg[2]) - int(seg[1]) + 1
	var f := t * 24.0
	if FIT.has(cur):
		f = t / float(FIT[cur]) * float(n)
	var i := int(f)
	match str(seg[3]):
		"loop":
			i = i % n
		"pingpong":
			var m := 2 * n - 2
			i = i % max(1, m)
			if i >= n:
				i = m - i
		_:
			i = mini(i, n - 1)
	return int(seg[1]) + i

func draw(ci: CanvasItem, ground: Vector2, facing: int, scale_out: float, mod: Color) -> bool:
	var fi := frame_index()
	if fi < 0:
		return false
	var name: String = SEG[cur][0]
	var tex: Texture2D = _clip(name)
	if tex == null:
		return false
	var m: Dictionary = meta[name]
	var C: int = int(m["cell"][0])
	var cols: int = int(m["cols"])
	fi = mini(fi, int(m["count"]) - 1)
	var src := Rect2((fi % cols) * C, (fi / cols) * C, C, C)
	var anc := Vector2(m["anchor"][0], m["anchor"][1])
	var size := Vector2(C, C) * scale_out
	if facing == 1:
		ci.draw_texture_rect_region(tex, Rect2(ground - anc * scale_out, size), src, mod)
	else:
		ci.draw_set_transform(Vector2(ground.x + anc.x * scale_out, ground.y - anc.y * scale_out), 0.0, Vector2(-1, 1))
		ci.draw_texture_rect_region(tex, Rect2(Vector2.ZERO, size), src, mod)
		ci.draw_set_transform(Vector2.ZERO)
	return true
