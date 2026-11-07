# Texture cache + J2ME-style image drawing (Graphics.drawImage / drawRegion with anchors).
# HD art for /mob/* lives in res://art/mob/ with the original file names at exactly 4x size and the
# same registration; every other original image is used from res://orig/ at 1x.
extends RefCounted

const HCENTER := 1
const VCENTER := 2
const LEFT := 4
const RIGHT := 8
const TOP := 16
const BOTTOM := 32

static var _cache := {}

# returns {tex, scale(texture px per world px), w, h (original 1x size)} or {} if missing
static func get_image(path: String) -> Dictionary:
	if _cache.has(path):
		return _cache[path]
	var info := {}
	var orig_path := "res://orig" + path
	var orig: Texture2D = load(orig_path) if ResourceLoader.exists(orig_path) else null
	var hd: Texture2D = null
	if path.begins_with("/mob/") and ResourceLoader.exists("res://art" + path):
		hd = load("res://art" + path)
	var tex: Texture2D = hd if hd else orig
	if tex:
		var w := orig.get_width() if orig else int(tex.get_width() / 4)
		var h := orig.get_height() if orig else int(tex.get_height() / 4)
		info = {"tex": tex, "scale": float(tex.get_width()) / float(max(1, w)), "w": w, "h": h}
	_cache[path] = info
	return info

static func size_of(path: String) -> Vector2i:
	var i := get_image(path)
	if i.is_empty():
		return Vector2i.ZERO
	return Vector2i(i["w"], i["h"])

# Draw `path` like the original drawImage/drawRegion(…, transform 2 = mirror when `mirror`, x, y, anchor).
# `src` is an optional 1x source region (drawRegion). `squash` > 0 squashes around the anchor point.
static func draw(ci: CanvasItem, path: String, x: float, y: float, anchor: int, mirror := false,
		color := Color.WHITE, src := Rect2(), squash := 0.0) -> void:
	var i := get_image(path)
	if i.is_empty():
		return
	var region: Rect2 = src if src.size != Vector2.ZERO else Rect2(0, 0, i["w"], i["h"])
	var w := int(region.size.x)
	var h := int(region.size.y)
	# J2ME anchor arithmetic (integer halves)
	var x0 := x
	var y0 := y
	if anchor & HCENTER:
		x0 -= int(w / 2)
	elif anchor & RIGHT:
		x0 -= w
	if anchor & VCENTER:
		y0 -= int(h / 2)
	elif anchor & BOTTOM:
		y0 -= h
	var s: float = i["scale"]
	var src_px := Rect2(region.position * s, region.size * s)
	var xf := Transform2D.IDENTITY
	if squash != 0.0:
		# scale about the anchor point (feet for monsters)
		var sx := 1.0 + 0.5 * squash
		var sy := 1.0 - squash
		xf = Transform2D(0.0, Vector2(sx, sy), 0.0, Vector2(x, y)) * Transform2D(0.0, Vector2(-x, -y))
	if mirror:
		xf = xf * Transform2D(0.0, Vector2(-1, 1), 0.0, Vector2(2.0 * x0 + w, 0))
	ci.draw_set_transform_matrix(xf)
	ci.draw_texture_rect_region(i["tex"], Rect2(x0, y0, w, h), src_px, color)
	ci.draw_set_transform_matrix(Transform2D.IDENTITY)

# Original HP bar (drawRect + 2 fillRects), e.g. monster bar ~L7926
static func hp_bar(ci: CanvasItem, cx: float, top: float, width: int, value: int, max_value: int,
		frame_col: Color, fill_col: Color, empty_col: Color) -> void:
	var filled: int = int(value * width / max(1, max_value))
	ci.draw_rect(Rect2(cx - (width >> 1) - 1, top, width + 2, 4), frame_col, false, 1.0)
	ci.draw_rect(Rect2(cx - (width >> 1), top + 1, filled, 3), fill_col)
	ci.draw_rect(Rect2(cx - (width >> 1) + filled, top + 1, width - filled + 1, 3), empty_col)
