# HD presentation: replaces original draw routines (by tag) with new art and draws overlays.
# Gameplay state is only READ here; hit boxes / timing stay in the original logic.
extends Node2D

var core
var r           # cmd_renderer
var alpha := 0.0
var hero_tex: Texture2D
var hero_meta := {}
var env_cache := {}
var t := 0.0
var particles: Array = []
var fx: Array = []          # transient effects (slash arcs, sparks, dust)
var last_A := -1
var last_B := -1
var prev_hp := -1
var font: Font

# original map id -> background biome (art/env/<biome>)
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
	var p := "res://art/hero/hero_sheet.png"
	if ResourceLoader.exists(p):
		hero_tex = load(p)
		var mf := FileAccess.open("res://art/hero/hero_sheet.json", FileAccess.READ)
		if mf:
			hero_meta = JSON.parse_string(mf.get_as_text())
	r.replacers["a_o"] = Callable(self, "_draw_sky")
	r.replacers["b_o"] = Callable(self, "_draw_player")
	r.hidden_tags["d_o"] = true      # original bottom HUD strip (redrawn as overlay)
	r.hidden_tags["c_o"] = true

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
		var p := "res://art/env/%s/layer%d.png" % [key, i]
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
	var factors: Array = meta.get("parallax", [0.05, 0.15, 0.35, 0.6])
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
			rr.draw_texture_rect(tx, Rect2(x, oy, w, h), false)
			x += w

# ---------- player ----------
func _draw_player(rr, cmds: Array) -> void:
	if hero_tex == null or hero_meta.is_empty():
		var ord := {}
		for c in cmds:
			rr.draw_cmd(c, ord)
		return
	var B: int = core.B
	if core.fz:
		B = 25 if core.w % 20 > 2 else 26
	var cell: Array = hero_meta.get("cell", [256, 256])
	var anc: Array = hero_meta.get("anchor", [128, 240])
	var cols: int = hero_meta.get("cols", 8)
	var pos: Vector2 = core.prev_player.lerp(core.cur_player, alpha) - rr.cam
	var scale := 0.62
	var src := Rect2((B % cols) * cell[0], (B / cols) * cell[1], cell[0], cell[1])
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
	var dst_size := Vector2(cell[0], cell[1]) * scale
	var origin := pos - Vector2(anc[0], anc[1]) * scale
	if facing == 1:
		rr.draw_texture_rect_region(hero_tex, Rect2(origin, dst_size), src, mod)
	else:
		rr.draw_set_transform(Vector2(pos.x + anc[0] * scale, origin.y), 0.0, Vector2(-1, 1))
		rr.draw_texture_rect_region(hero_tex, Rect2(Vector2.ZERO, dst_size), src, mod)
		rr.draw_set_transform(Vector2.ZERO)
	# weapon slash effect images of the original (eE) stay as drawn by the original routine
	var ord := {}
	for c in cmds:
		if c[0] == 4 and str(c[1]["p"]).begins_with("/c/w"):
			rr.draw_cmd(c, ord)

func on_tick() -> void:
	pass

func _process(delta: float) -> void:
	t += delta
