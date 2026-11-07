# HD presentation: replaces original draw routines (by tag) with new art and draws overlays.
# Gameplay state is only READ here; hit boxes / timing stay in the original logic.
extends Node2D

var core
var r           # cmd_renderer
var alpha := 0.0
var hero_tex: Texture2D
var hero_meta := {}
var rig
var use_rig := true
var tween_tex: Texture2D
var tween_meta := {}
var prev_B := 0
var cur_B := 0
var env_cache := {}
var t := 0.0
var particles: Array = []
var fx: Array = []          # transient effects (slash arcs, sparks, dust)
var last_A := -1
var last_B := -1
var prev_hp := -1
var font: Font

# original map id -> background biome (art/env/<biome>)
const HERO_SCALE := 0.21   # 201px rendered height -> ~42 logical px (original sprite ~36px, hitbox 22x32 unchanged)
const BIOME := {
	99: "school", 0: "school", 1: "school", 2: "village", 94: "school",
	3: "sakura", 4: "bamboo", 5: "waterfall", 6: "fields", 7: "river", 97: "river",
	8: "graveyard", 9: "temple", 10: "temple", 11: "temple", 96: "woods", 12: "woods",
	13: "spikes", 14: "cliffs", 15: "valley", 16: "village", 17: "orochi", 18: "orochi", 95: "orochi",
}
const PARTICLE_HINT := {"sakura": "petals", "bamboo": "leaves", "school": "leaves", "waterfall": "mist", "graveyard": "mist",
	"river": "fireflies", "fields": "leaves", "temple": "leaves", "woods": "leaves", "spikes": "leaves", "cliffs": "dust",
	"valley": "mist", "village": "leaves", "orochi": "embers"}

func setup(core_, renderer) -> void:
	core = core_
	r = renderer
	font = ThemeDB.fallback_font
	var p := "res://art/hero/hero_sheet.png"  # Blender cutout rig output (scripts/run_hero_pipeline.py)
	if ResourceLoader.exists(p):
		hero_tex = load(p)
		var mf := FileAccess.open("res://art/hero/hero_sheet.json", FileAccess.READ)
		if mf:
			hero_meta = JSON.parse_string(mf.get_as_text())
	rig = load("res://render/hero_rig.gd").new()
	rig.load_assets()
	if ResourceLoader.exists("res://art/hero/hero_tweens.png"):
		tween_tex = load("res://art/hero/hero_tweens.png")
		tween_meta = JSON.parse_string(FileAccess.get_file_as_string("res://art/hero/hero_tweens.json"))
	r.replacers["a_o"] = Callable(self, "_draw_sky")
	r.replacers["b_o"] = Callable(self, "_draw_player")
	r.replacers["f_o"] = Callable(self, "_draw_dialogue")
	r.hidden_tags["d_o"] = true      # original bottom HUD strip (redrawn as overlay)
	r.hidden_tags["c_o"] = true

func particle_hint() -> String:
	var e = env_layers(biome(), is_night())
	return str(e[1].get("ambient_particles", PARTICLE_HINT.get(biome(), "leaves")))

func biome() -> String:
	return BIOME.get(int(core.v), "school")

func is_night() -> bool:
	return int(core.MF_g) == 1

func env_layers(b: String, night: bool) -> Array:
	var key := b + ("/night" if night else "/day")
	if env_cache.has(key):
		return env_cache[key]
	var out := []
	for i in 4:
		var p := "res://art/env/%s/layer%d.webp" % [key, i]
		if ResourceLoader.exists(p):
			out.append(load(p))
	var meta := {}
	var mp := "res://art/env/%s/meta.json" % key
	if FileAccess.file_exists(mp):
		meta = JSON.parse_string(FileAccess.get_file_as_string(mp))
	env_cache[key] = [out, meta]
	return env_cache[key]

# ---------- background ----------
func _draw_sky(rr, cmds: Array) -> void:
	var e = env_layers(biome(), is_night())
	var layers: Array = e[0]
	if layers.size() < 4:
		for c in cmds:
			rr.draw_cmd(c)
		return
	var meta: Dictionary = e[1]
	var factors: Array = [0.05, 0.15, 0.35, 0.6]
	if meta.has("layers"):
		factors = []
		for L in meta["layers"]:
			factors.append(float(L.get("parallax", 0.3)))
	var vw := 480.0
	var vh := 270.0
	var world_h: float = core.iS
	var cam: Vector2 = rr.cam
	for i in 4:
		var tx: Texture2D = layers[i]
		var h := vh * 1.12
		var w := h * tx.get_width() / tx.get_height()
		var f: float = factors[i] if i < factors.size() else 0.3
		var ox := -fposmod(cam.x * f, w)
		# vertical: follow camera slightly so tall maps reveal sky
		var max_scroll: float = max(1.0, world_h - vh)
		var oy := -(h - vh) * clampf(cam.y / max_scroll, 0.0, 1.0) * (0.3 + f)
		var x := ox
		while x < vw:
			rr.draw_texture_rect(tx, Rect2(x, oy, w, h), false, Color(1, 1, 1, 0.8 if i == 3 else 1.0))
			x += w

# ---------- player ----------
func _draw_player(rr, cmds: Array) -> void:
	if hero_tex == null or hero_meta.is_empty():
		var ord := {}
		for c in cmds:
			rr.draw_cmd(c, ord)
		return
	if use_rig and rig != null and rig.ok and core.d == 1:
		var wpos: Vector2 = core.prev_player.lerp(core.cur_player, alpha)
		var dtr := get_process_delta_time()
		rig.update(core, cur_B, prev_B, alpha, wpos.x, dtr)
		var gp: Vector2 = wpos - rr.cam
		if core.A in [1, 2, 5, 8, 22]:
			rr.draw_set_transform(gp + Vector2(0, -1), 0.0, Vector2(1.0, 0.28))
			rr.draw_circle(Vector2.ZERO, 11.0, Color(0, 0, 0, 0.28))
			rr.draw_set_transform(Vector2.ZERO)
		var m2 := Color.WHITE
		if core.fA:
			m2 = Color(1.25, 0.8, 0.75)
		if core.fz:
			m2.a = 0.6
		rig.draw(rr, gp, 1 if core.fp == 1 else -1, HERO_SCALE, m2, dtr, rr.cam)
		_draw_weapon_fx(rr, cmds)
		return
	var B: int = cur_B
	var cell: Array = hero_meta.get("cell", [256, 256])
	var anc: Array = hero_meta.get("anchor", [128, 240])
	var cols: int = hero_meta.get("cols", 8)
	var pos: Vector2 = core.prev_player.lerp(core.cur_player, alpha) - rr.cam
	if OS.get_cmdline_user_args().has("--debug") and Engine.get_process_frames() % 120 == 0:
		print("hero B=", B, " pos=", pos, " fp=", core.fp, " cell=", cell)
	var scale := HERO_SCALE
	var yoff: Array = hero_meta.get("frame_y_offset", [])
	var y_extra := 0.0
	var src := Rect2((B % cols) * cell[0], (B / cols) * cell[1], cell[0], cell[1])
	var tex: Texture2D = hero_tex
	# Display runs one tick behind (interpolated), so between prev_B and cur_B we can show the
	# Blender in-between poses (t=1/3, 2/3). Logic/hit timing is untouched.
	if prev_B != cur_B and tween_tex != null and alpha >= 1.0 / 6.0 and alpha < 5.0 / 6.0:
		var key := "%d_%d_%d" % [prev_B, cur_B, 1 if alpha < 0.5 else 2]
		var mp: Dictionary = tween_meta.get("map", {})
		if mp.has(key):
			var ti: int = int(mp[key][0])
			var tc: int = int(tween_meta.get("cols", 16))
			tex = tween_tex
			src = Rect2((ti % tc) * cell[0], (ti / tc) * cell[1], cell[0], cell[1])
			y_extra = float(mp[key][1]) * scale
			B = -1
	elif prev_B != cur_B and alpha < 1.0 / 6.0:
		B = prev_B
		src = Rect2((B % cols) * cell[0], (B / cols) * cell[1], cell[0], cell[1])
	var facing: int = core.fp
	# shadow on ground
	if core.A in [1, 2, 5, 8, 22]:
		rr.draw_set_transform(pos + Vector2(0, -1), 0.0, Vector2(1.0, 0.28))
		rr.draw_circle(Vector2.ZERO, 11.0, Color(0, 0, 0, 0.28))
		rr.draw_set_transform(Vector2.ZERO)
	var mod := Color.WHITE
	if core.fA:   # Xich Mao (red power) buff
		mod = Color(1.25, 0.8, 0.75)
	if core.fz:
		mod.a = 0.6
	if B >= 0 and B < yoff.size():
		y_extra = float(yoff[B]) * scale
	pos.y += y_extra
	var dst_size := Vector2(cell[0], cell[1]) * scale
	var origin := pos - Vector2(anc[0], anc[1]) * scale
	if facing == 1:
		rr.draw_texture_rect_region(tex, Rect2(origin, dst_size), src, mod)
	else:
		rr.draw_set_transform(Vector2(pos.x + anc[0] * scale, origin.y), 0.0, Vector2(-1, 1))
		rr.draw_texture_rect_region(tex, Rect2(Vector2.ZERO, dst_size), src, mod)
		rr.draw_set_transform(Vector2.ZERO)
	_draw_weapon_fx(rr, cmds, true)

# original weapon overlay eE[ff]: 0/3/4 = small held blade (the HD hero holds a katana) -> skipped;
# slash arcs (1,2,5,6,9,10) only for the sprite fallback (the runtime rig draws its own trail);
# 7/8 = bamboo breathing tube while diving, always kept
func _draw_weapon_fx(rr, cmds: Array, arcs := false) -> void:
	var ff: int = core.ff
	if arcs and ff in [1, 2, 5, 6, 9, 10]:
		var ord := {}
		for c in cmds:
			if c[0] == 4 and str(c[1]["p"]).begins_with("/c/w"):
				var tx2: Texture2D = rr.tex(c[1]["p"])
				var p2: Vector2 = rr.interp_pos(c, ord)
				rr.draw_image_at(tx2, c[1], p2 + Vector2(-1, -1), c[4], c[5], Rect2(c[6], c[7], c[8], c[9]), Rect2(c[10]), Color(0.6, 0.85, 1.0, 0.45))
				rr.draw_image_at(tx2, c[1], p2, c[4], c[5], Rect2(c[6], c[7], c[8], c[9]), Rect2(c[10]), Color(1.0, 1.0, 1.0, 0.95))
	elif ff in [7, 8]:
		var ord2 := {}
		for c in cmds:
			if c[0] == 4 and str(c[1]["p"]).begins_with("/c/w"):
				rr.draw_cmd(c, ord2)

# dialogue bubble (original f_o): white rounded box + dark outline + text + tail (/u/co.png)
var dialogue_frame := -10

func _draw_dialogue(rr, cmds: Array) -> void:
	dialogue_frame = Engine.get_process_frames()
	var box := Rect2()
	for c in cmds:
		if c[0] == 2:
			box = Rect2(c[1], c[2], c[3], c[4])
	for c in cmds:
		if c[0] == 2:
			var sb := StyleBoxFlat.new()
			sb.bg_color = Color(1.0, 0.98, 0.92, 0.97)
			sb.border_color = Color(0.55, 0.12, 0.1)
			sb.set_border_width_all(1)
			sb.set_corner_radius_all(5)
			sb.shadow_color = Color(0, 0, 0, 0.35)
			sb.shadow_size = 2
			sb.shadow_offset = Vector2(1, 2)
			rr.draw_style_box(sb, box.grow(1))
		elif c[0] == 3:
			pass
		elif c[0] == 4 and str(c[1]["p"]) == "/u/co.png":
			var tip := Vector2(c[2], c[3])
			var pts := PackedVector2Array([tip + Vector2(-4, -1), tip + Vector2(4, -1), tip + Vector2(0, 6)])
			rr.draw_colored_polygon(pts, Color(1.0, 0.98, 0.92))
			rr.draw_polyline(PackedVector2Array([pts[0], pts[2], pts[1]]), Color(0.55, 0.12, 0.1), 1.0)
		elif c[0] == 6 and c[5] == 1:
			rr.draw_text_fit(rr.pc_keys(c[1]), Vector2(c[2], c[3]), 2, rr.core.M_c_s(c[1]), 11.0, Color(0.16, 0.08, 0.05), false)
		else:
			rr.draw_cmd(c)
	if box.size.x > 0 and int(t * 2.0) % 2 == 0:
		rr.draw_text_fit("▼", box.position + Vector2(box.size.x - 9, box.size.y - 9), 0, -1, 7.0, Color(0.6, 0.15, 0.1), false)

var _last_v := -999

func on_tick() -> void:
	if core.v != _last_v:
		_last_v = core.v
		# warm up this map's background layers (both lightings) off the main thread
		for night in [false, true]:
			for i in 4:
				var p := "res://art/env/%s/%s/layer%d.webp" % [biome(), "night" if night else "day", i]
				if ResourceLoader.exists(p):
					ResourceLoader.load_threaded_request(p)
	prev_B = cur_B
	cur_B = core.B
	if core.fz:
		cur_B = 25 if core.w % 20 > 2 else 26

func _process(delta: float) -> void:
	t += delta
