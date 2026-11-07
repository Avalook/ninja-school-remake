# Multi-layer parallax background (Blender-rendered biome sets, art/env/<biome>/<day|night>).
extends Node2D

var world
var cache := {}
const BIOME := {
	99: "school", 0: "school", 1: "school", 2: "village", 94: "school",
	3: "sakura", 4: "bamboo", 5: "waterfall", 6: "fields", 7: "river", 97: "river",
	8: "graveyard", 9: "temple", 10: "temple", 11: "temple", 96: "woods", 12: "woods",
	13: "spikes", 14: "cliffs", 15: "valley", 16: "village", 17: "orochi", 18: "orochi", 95: "orochi",
}
var night := false

func _ready() -> void:
	z_index = -10
	# the original alternates day / night on every launch
	var cf := ConfigFile.new()
	cf.load("user://remake_cfg.ini")
	var n: int = cf.get_value("launch", "count", 0)
	cf.set_value("launch", "count", n + 1)
	cf.save("user://remake_cfg.ini")
	night = n % 2 == 1

func layers() -> Array:
	var b: String = BIOME.get(world.map_id, "school")
	var key := b + ("/night" if night else "/day")
	if not cache.has(key):
		var out := []
		var meta := {}
		var mp := "res://art/env/%s/meta.json" % key
		if FileAccess.file_exists(mp):
			meta = JSON.parse_string(FileAccess.get_file_as_string(mp))
		for i in 4:
			var p := "res://art/env/%s/layer%d.webp" % [key, i]
			if ResourceLoader.exists(p):
				var f := 0.05 + 0.15 * i
				if meta.has("layers") and i < meta["layers"].size():
					f = float(meta["layers"][i].get("parallax", f))
				out.append([load(p), f])
		cache[key] = out
	return cache[key]

func _draw() -> void:
	if world == null or world.map_id < 0:
		return
	var cam: Vector2 = world.camera.position
	var vs: Vector2 = world.view_size()
	var ws: Vector2 = world.world_size()
	draw_rect(Rect2(cam - Vector2(4, 4), vs + Vector2(8, 8)), Color(0.55, 0.75, 0.95) if not night else Color(0.05, 0.07, 0.16))
	var i := 0
	for L in layers():
		var t: Texture2D = L[0]
		var f: float = L[1]
		var hh := vs.y * 1.12
		var ww := hh * t.get_width() / t.get_height()
		var ox := -fposmod(cam.x * f, ww)
		var max_scroll: float = max(1.0, ws.y - vs.y)
		var oy := -(hh - vs.y) * clampf(cam.y / max_scroll, 0.0, 1.0) * (0.3 + f)
		var x := ox
		while x < vs.x:
			draw_texture_rect(t, Rect2(cam + Vector2(x, oy), Vector2(ww, hh)), false, Color(1, 1, 1, 0.82 if i == 3 else 1.0))
			x += ww
		i += 1
