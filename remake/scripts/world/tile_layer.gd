# Draws the visible tiles of one layer (original k/l(Graphics) rules) with the 4x HD tile atlas.
extends Node2D

var world
var which := "back"

func _draw() -> void:
	if world == null or world.map_id < 0:
		return
	var cam: Vector2 = world.camera.position
	var vs: Vector2 = world.view_size()
	var x0 := int(floor(cam.x / 24.0)) - 1
	var y0 := int(floor(cam.y / 24.0)) - 1
	var x1 := int(ceil((cam.x + vs.x) / 24.0)) + 1
	var y1 := int(ceil((cam.y + vs.y) / 24.0)) + 1
	var bF := vs.x * 0.5
	var wt: int = S.w
	if which == "back":
		for d in world.decor:
			var t: Texture2D = world.tex_h[d["img"]]
			var ow: Vector2 = Vector2(t.get_width(), t.get_height()) / 4.0   # HD x4 of the original image
			var sx: float = d["x"] - cam.x - bF
			var cx: float = cam.x + bF + sx * 22.0 / 24.0
			draw_texture_rect(t, Rect2(cx - ow.x * 0.5, d["y"] - ow.y, ow.x, ow.y), false)
	for tx in range(max(0, x0), min(world.w, x1)):
		for ty in range(max(0, y0), min(world.h, y1)):
			var f: int = world.flag_tile(tx, ty)
			var id: int = world.tile_id(tx, ty)
			var px := tx * 24.0
			var py := ty * 24.0
			if which == "back":
				if f & 0x10:
					# background-depth tile: slight parallax around the screen centre (22/24)
					var sx := px - cam.x - bF
					_tile(id, Vector2(cam.x + bF + sx * 22.0 / 24.0, py))
				elif not (f & 0x100) and not (f & 0x200):
					if f & 0x20:
						_anim(world.tex_wtf, 4, wt % 4, Vector2(px, py))
					elif (f & 0x40) or (f & 0x1000):
						var up: int = world.flag_tile(tx, ty - 1)
						if up & 0x20:
							_anim(world.tex_wtf, 4, wt % 4, Vector2(px, py))
						elif up & 0x2000:
							_tile(22, Vector2(px, py))     # atlas row 21 (y 504) = earth behind water/vine
					elif f & 0x400:
						_tile(id, Vector2(px, py + 1))
					elif id > 0:
						_tile(id, Vector2(px, py))
			else:
				if f & 0x40:
					_anim(world.tex_wts, 2, (wt % 8) >> 2, Vector2(px, py))
				elif f & 0x100:
					var sx2 := px - cam.x - bF
					var bx := cam.x + bF + sx2 * 23.0 / 24.0
					var tb: Texture2D = world.tex_tb
					var s := tb.get_width() / 32.0
					draw_texture_rect(tb, Rect2(bx - 2 + (tx + ty) % 5, py - 3 + ty % 5, tb.get_width() / s, tb.get_height() / s), false)
				if f & 0x200:
					_tile(id, Vector2(px, py + 2))
				if f & 0x1000:
					_tile(id, Vector2(px, py))
	if which == "front":
		_signs(cam, vs)

func _signs(cam: Vector2, vs: Vector2) -> void:
	var n: int = int(S.jq)
	var f := ThemeDB.fallback_font
	for i in n:
		if i >= S.jm.size():
			break
		var x: float = float(S.jn[i])
		var y: float = float(S.jo[i])
		if x + 80 < cam.x or x > cam.x + vs.x + 80:
			continue
		var t: String = str(S.jm[i])
		draw_string_outline(f, Vector2(x, y - 3), t, HORIZONTAL_ALIGNMENT_LEFT, -1, 9, 3, Color(0.15, 0.08, 0.02))
		draw_string(f, Vector2(x, y - 3), t, HORIZONTAL_ALIGNMENT_LEFT, -1, 9, Color(1, 0.95, 0.75))

func _tile(id: int, p: Vector2) -> void:
	if id <= 0:
		return
	var t: Texture2D = world.tex_tiles
	var s := t.get_width() / 24.0
	draw_texture_rect_region(t, Rect2(p, Vector2(24.05, 24.05)), Rect2(0, (id - 1) * 24 * s, 24 * s, 24 * s))

func _anim(t: Texture2D, frames: int, f: int, p: Vector2) -> void:
	var s := t.get_width() / 24.0
	var fh := t.get_height() / float(frames)
	draw_texture_rect_region(t, Rect2(p, Vector2(24.05, fh / s)), Rect2(0, f * fh, t.get_width(), fh))
