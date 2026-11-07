# Hero animation: Veo 3 reference clips (keyed 24 fps atlases, tools/art/veo_extract.py) chosen from the
# player's original state (A) / frame (B); one-shot clips are time-warped to the original action length so
# hit timing stays original. Falls back to the Blender hero sheet (art/hero) for unmapped states.
extends RefCounted

const SCALE := 0.21      # 201 px standing height in the atlases -> ~42 world px
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
var vx_s := 0.0
var sheet: Texture2D
var sheet_meta := {}
var B := 0

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
	sheet = load("res://art/hero/hero_sheet.png")
	sheet_meta = JSON.parse_string(FileAccess.get_file_as_string("res://art/hero/hero_sheet.json"))

func _native_B(p) -> int:
	if p.grounded:
		if abs(p.vel.x) > 12.0:
			return 2 + int(p.run_phase * 5.0) % 5
		return 0
	if p.vel.y < 0.0:
		return 7
	return 12 if p.vel.y > 200.0 else 23

func _want(p) -> String:
	var A: int = S.A
	if S.fz or B in [25, 26]:
		return "eyes"
	if A == 23:
		return "power"
	if B == 24:
		return "under"
	if B == 22:
		return "crouch"
	if B == 21 and A == 7:
		return "hurt"
	if A == 6 or A in [18, 19, 20, 21]:
		return ""
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
	if A == 5 and S.fg == 1:
		return "throw"
	if B in [7, 43, 44, 45]:
		return "jump_up"
	if B in [12, 23]:
		return "fall"
	if B in [2, 3, 4, 5, 6] and A in [2, 16]:
		return "run"
	if B in [0, 1]:
		return "idle"
	return ""

func update(p, dt: float) -> void:
	if dt <= 0.0:
		return
	B = _native_B(p) if p.is_native() else int(S.B)
	if p.is_native() and S.eL and p.vel.y < 0.0 and p.vel.y > -3.5 * 25.0 and not S.fA:
		B = 8     # khinh công somersault at the top of the jump (original z -3..0 -> B 8..11)
	var vx: float = p.vel.x if p.is_native() else float(S.y) * 25.0
	vx_s = lerpf(vx_s, vx, 1.0 - exp(-dt / 0.08))
	var want := _want(p)
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

func draw(ci) -> void:
	var p = ci
	var ground: Vector2 = p.pos
	var facing: int = p.facing
	var mod := Color.WHITE
	if S.fA:
		mod = Color(1.25, 0.8, 0.75)
	if S.fz:
		mod.a = 0.5
	if S.fq < 0 and S.w % 4 < 2 and int(S.A) != 7:
		mod.a *= 0.45          # post-hit invulnerability blink
	if int(S.A) in [1, 2, 5, 8] or p.grounded:
		ci.draw_set_transform(ground + Vector2(0, -1), 0.0, Vector2(1.0, 0.28))
		ci.draw_circle(Vector2.ZERO, 11.0, Color(0, 0, 0, 0.28))
		ci.draw_set_transform(Vector2.ZERO)
	var fi := frame_index()
	if fi >= 0:
		var name: String = SEG[cur][0]
		var tex: Texture2D = _clip(name)
		if tex != null:
			var m: Dictionary = meta[name]
			var C: int = int(m["cell"][0])
			var cols: int = int(m["cols"])
			fi = mini(fi, int(m["count"]) - 1)
			_blit(ci, tex, Rect2((fi % cols) * C, (fi / cols) * C, C, C), Vector2(m["anchor"][0], m["anchor"][1]), ground, facing, SCALE, mod)
			return
	# fallback: Blender hero sheet frame = original B
	var cell: Array = sheet_meta.get("cell", [256, 256])
	var cols2: int = sheet_meta.get("cols", 8)
	var b := clampi(B, 0, int(sheet_meta.get("count", 46)) - 1)
	var rot := 0.0
	if int(S.A) == 6:
		b = 21
	_blit(ci, sheet, Rect2((b % cols2) * cell[0], (b / cols2) * cell[1], cell[0], cell[1]), Vector2(sheet_meta["anchor"][0], sheet_meta["anchor"][1]), ground, facing, SCALE, mod)

func _blit(ci, tex: Texture2D, src: Rect2, anc: Vector2, ground: Vector2, facing: int, s: float, mod: Color) -> void:
	var size := src.size * s
	if facing == 1:
		ci.draw_texture_rect_region(tex, Rect2(ground - anc * s, size), src, mod)
	else:
		ci.draw_set_transform(Vector2(ground.x + anc.x * s, ground.y - anc.y * s), 0.0, Vector2(-1, 1))
		ci.draw_texture_rect_region(tex, Rect2(Vector2.ZERO, size), src, mod)
		ci.draw_set_transform(Vector2.ZERO)
