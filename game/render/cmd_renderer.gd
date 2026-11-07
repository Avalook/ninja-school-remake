# Draws the draw-command list recorded from the original paint() code, at display rate.
#  * World-space commands are interpolated between the previous and current logic tick
#    (commands are matched by tag + image + ordinal), so 25 Hz logic looks smooth at 60+ fps.
#  * `replacers[tag]` (Callable(renderer, cmds:Array)) draws new art in place of every command
#    issued by that original draw routine, keeping the original painter's order (depth).
#  * Images are drawn from the 4x HD set (hd_res) when present, else the original PNG.
extends Node2D

var core
var tex_cache: Dictionary = {}
var replacers: Dictionary = {}
var hidden_tags: Dictionary = {}
var font: Font
var text_scale := 4.0
var alpha := 1.0
var hd_root := "res://hd_res"
var cam := Vector2.ZERO        # interpolated camera (world px)
var cam_delta := Vector2.ZERO  # current tick camera - interpolated camera
var _prev_index: Dictionary = {}
var _runs: Array = []          # [tag, start, end]

func _ready() -> void:
	font = ThemeDB.fallback_font

static func clip_index(c: Array) -> int:
	return 10 if c[0] == 4 else 6

func tex(path: String) -> Texture2D:
	if tex_cache.has(path):
		return tex_cache[path]
	var t: Texture2D = null
	if ResourceLoader.exists("res://hd_art" + path):   # bespoke HD art (exact 4x, same registration)
		t = load("res://hd_art" + path)
	elif hd_root != "" and ResourceLoader.exists(hd_root + path):
		t = load(hd_root + path)
	if t == null:
		t = load("res://orig_res" + path)
	tex_cache[path] = t
	return t

# called once per logic tick (after core.tick)
func on_tick() -> void:
	_prev_index.clear()
	var ord := {}
	for c in core.prev_gq:
		if c[0] != 4:
			continue
		var k := _key(c, ord)
		if c[11] != 0 or c[12] != 0:
			_prev_index[k] = Vector2(c[2], c[3]) + core.prev_cam
	_runs.clear()
	var cur := ""
	var start := 0
	var cmds: Array = core._gq
	for i in cmds.size():
		var tag: String = cmds[i][-1]
		if tag != cur:
			if i > start:
				_runs.append([cur, start, i])
			cur = tag
			start = i
	if cmds.size() > start:
		_runs.append([cur, start, cmds.size()])

func _key(c: Array, ord: Dictionary) -> String:
	var base: String = c[-1] + "|" + c[1]["p"]
	var n: int = ord.get(base, 0)
	ord[base] = n + 1
	return base + "|" + str(n)

func _draw() -> void:
	if core == null:
		return
	cam = core.prev_cam.lerp(core.cur_cam, alpha)
	cam_delta = core.cur_cam - cam
	var cmds: Array = core._gq
	var ord := {}
	for run in _runs:
		var tag: String = run[0]
		if hidden_tags.has(tag):
			continue
		if replacers.has(tag):
			replacers[tag].call(self, cmds.slice(run[1], run[2]))
			continue
		for i in range(run[1], run[2]):
			draw_cmd(cmds[i], ord)

func world_offset(c: Array) -> Vector2:
	var ci := clip_index(c)
	if c[ci + 1] == 0 and c[ci + 2] == 0:
		return Vector2.ZERO
	return cam_delta

const PANEL_FILL := 6562304     # original brown menu/popup panel
const PANEL_BORDER := 12281361  # its gold-brown outline (drawn by the skin instead)

func draw_panel(r: Rect2, accent := Color(0.95, 0.75, 0.3)) -> void:
	var sb := StyleBoxFlat.new()
	sb.bg_color = Color(0.10, 0.07, 0.18, 0.94)
	sb.border_color = accent
	sb.set_border_width_all(1)
	sb.set_corner_radius_all(4)
	sb.shadow_color = Color(0, 0, 0, 0.45)
	sb.shadow_size = 3
	draw_style_box(sb, r)
	draw_rect(Rect2(r.position + Vector2(2, 2), Vector2(r.size.x - 4, r.size.y * 0.35)), Color(1, 1, 1, 0.05))

func draw_cmd(c: Array, ord = null) -> void:
	# UI skin: original brown panels become modern panels; their outline/corner sprites are dropped
	if c[0] == 0 and c[5] == PANEL_FILL and world_offset(c) == Vector2.ZERO:
		draw_panel(Rect2(c[1], c[2], c[3], c[4]))
		return
	if c[0] == 1 and c[5] == PANEL_BORDER:
		return
	if c[0] == 4 and str(c[1]["p"]).begins_with("/u/bd"):
		return
	match c[0]:
		0, 2:
			var off := world_offset(c)
			_rect(Rect2(c[1] + off.x, c[2] + off.y, c[3], c[4]).intersection(Rect2(c[6])), c[5], true)
		1, 3:
			var off := world_offset(c)
			_rect(Rect2(c[1] + off.x, c[2] + off.y, c[3], c[4]), c[5], false)
		4:
			_img(c, ord)
		5, 6:
			_text(c)

func _rect(r: Rect2, col: int, filled: bool) -> void:
	if r.size.x <= 0 or r.size.y <= 0:
		return
	var color := Color.hex((col << 8) | 0xFF)
	if filled:
		draw_rect(r, color, true)
	else:
		draw_rect(Rect2(r.position + Vector2(0.5, 0.5), r.size - Vector2.ONE), color, false, 1.0)

# screen position of an image command after interpolation
func interp_pos(c: Array, ord) -> Vector2:
	var p := Vector2(c[2], c[3])
	if c[11] == 0 and c[12] == 0:
		return p
	var world: Vector2 = Vector2(c[2], c[3]) + core.cur_cam
	if ord != null:
		var k := _key(c, ord)
		if _prev_index.has(k):
			var pw: Vector2 = _prev_index[k]
			if (pw - world).length() < 40.0:
				world = pw.lerp(world, alpha)
	return world - cam

# J2ME Sprite transforms: 0 NONE,1 MIRROR_ROT180,2 MIRROR,3 ROT180,4 MIRROR_ROT270,5 ROT90,6 ROT270,7 MIRROR_ROT90
func _img(c: Array, ord) -> void:
	var img: Dictionary = c[1]
	var t := tex(img["p"])
	if t == null:
		return
	var p := interp_pos(c, ord)
	draw_image_at(t, img, p, c[4], c[5], Rect2(c[6], c[7], c[8], c[9]), Rect2(c[10]), Color.WHITE)

func draw_image_at(t: Texture2D, img: Dictionary, p: Vector2, an: int, tr: int, region: Rect2, clip: Rect2, mod: Color) -> void:
	var sw := region.size.x
	var sh := region.size.y
	var dw := sh if tr >= 4 else sw
	var dh := sw if tr >= 4 else sh
	var x := p.x
	var y := p.y
	if an & 1:
		x -= floorf(dw / 2)
	elif an & 8:
		x -= dw
	if an & 2:
		y -= floorf(dh / 2)
	elif an & 32:
		y -= dh
	var dst := Rect2(x, y, dw, dh)
	var vis := dst.intersection(clip)
	if vis.size.x <= 0 or vis.size.y <= 0:
		return
	var scale := float(t.get_width()) / float(img["w"])
	var src := Rect2(region.position * scale, region.size * scale)
	if vis != dst and tr == 0:
		src = Rect2(src.position + (vis.position - dst.position) * scale, vis.size * scale)
		dst = vis
	match tr:
		0:
			draw_texture_rect_region(t, dst, src, mod)
		2:
			draw_set_transform(Vector2(dst.position.x + dst.size.x, dst.position.y), 0.0, Vector2(-1, 1))
			draw_texture_rect_region(t, Rect2(Vector2.ZERO, dst.size), src, mod)
		1:
			draw_set_transform(Vector2(dst.position.x, dst.position.y + dst.size.y), 0.0, Vector2(1, -1))
			draw_texture_rect_region(t, Rect2(Vector2.ZERO, dst.size), src, mod)
		3:
			draw_set_transform(dst.position + dst.size, PI, Vector2.ONE)
			draw_texture_rect_region(t, Rect2(Vector2.ZERO, dst.size), src, mod)
		5:
			draw_set_transform(Vector2(dst.position.x + dst.size.x, dst.position.y), PI / 2, Vector2.ONE)
			draw_texture_rect_region(t, Rect2(0, 0, sw, sh), src, mod)
		6:
			draw_set_transform(Vector2(dst.position.x, dst.position.y + dst.size.y), -PI / 2, Vector2.ONE)
			draw_texture_rect_region(t, Rect2(0, 0, sw, sh), src, mod)
		4:
			draw_set_transform(dst.position, PI / 2, Vector2(1, -1))
			draw_texture_rect_region(t, Rect2(0, 0, sw, sh), src, mod)
		7:
			draw_set_transform(dst.position + dst.size, PI / 2, Vector2(-1, 1))
			draw_texture_rect_region(t, Rect2(0, 0, sw, sh), src, mod)
	draw_set_transform(Vector2.ZERO)

# original bitmap fonts: fb.png (gold), fr.png (dark, bubbles), fs0..2 (small digits red/yellow/green)
const STYLE_COL := {0: Color(1.0, 0.85, 0.05), 1: Color(0.0, 0.16, 0.0), 10: Color(1.0, 0.48, 0.48), 11: Color(1.0, 0.87, 0.18), 12: Color(0.16, 1.0, 0.32)}

const DIGIT_ACTION := {"0": "weapon", "1": "jump_left", "2": "jump", "3": "jump_right", "4": "move_left", "5": "attack", "6": "move_right", "7": "transform", "8": "down", "9": "stealth"}
var _re_key := RegEx.create_from_string("([Pp]hím)( số)? ([0-9])")

# original tutorial texts name phone keys ("phím 5"); show the player's current PC binding instead
func pc_keys(s: String) -> String:
	if s.find("hím") < 0:
		return s
	var out := s
	for m in _re_key.search_all(s):
		var act: String = DIGIT_ACTION.get(m.get_string(3), "")
		if act == "":
			continue
		var evs := InputMap.action_get_events(act)
		if evs.is_empty() or not (evs[0] is InputEventKey):
			continue
		out = out.replace(m.get_string(0), m.get_string(1) + " " + OS.get_keycode_string(evs[0].physical_keycode))
	return out

func _text(c: Array) -> void:
	var s: String = pc_keys(c[1])
	if s.is_empty():
		return
	var off := world_offset(c)
	var x: float = c[2] + off.x
	var y: float = c[3] + off.y
	var size := 11.0
	var col := Color(1.0, 0.85, 0.05)
	var align := 0
	var target_w := -1.0
	var outline := true
	if c[0] == 5:
		col = Color.hex((int(c[5]) << 8) | 0xFF)
		var an: int = c[4]
		align = 2 if (an & 1) else (1 if (an & 8) else 0)
		outline = false
	else:
		align = c[4]
		var style: int = c[5]
		col = STYLE_COL.get(style, Color.WHITE)
		if style == 0:
			target_w = core.M_a_s(c[1])
			size = 11.5
		elif style == 1:
			target_w = core.M_c_s(c[1])
			size = 11.0
			outline = false
		else:
			target_w = core.M_d_s(s)
			size = 8.5
	if c[0] == 6 and c[5] == 0:
		if s == "*":
			s = "◀"
		elif s == "/":
			s = "▶"
			x += 8
	if c[0] == 6 and y >= core.aX - 15 and c[8] == 0:
		if x < 40:
			s = "[Esc] " + s
		elif x > core.aW - 40:
			s = s + " [E]"
		target_w = -1.0
	draw_text_fit(s, Vector2(x, y), align, target_w, size, col, outline)

func draw_text_fit(s: String, pos: Vector2, align: int, target_w: float, size: float, col: Color, outline: bool, outline_col := Color(0.12, 0.05, 0.0, 0.9)) -> void:
	var fs := int(size * text_scale)
	var w := font.get_string_size(s, HORIZONTAL_ALIGNMENT_LEFT, -1, fs).x / text_scale
	if target_w > 0 and w > target_w * 1.02:
		fs = int(fs * target_w / w)
		w = font.get_string_size(s, HORIZONTAL_ALIGNMENT_LEFT, -1, fs).x / text_scale
	var x := pos.x
	if target_w > 0:
		if align == 1:
			x -= target_w
		elif align == 2:
			x -= target_w / 2.0
		x += (target_w - w) / 2.0
	else:
		if align == 1:
			x -= w
		elif align == 2:
			x -= w / 2.0
	draw_set_transform(Vector2(x, pos.y), 0.0, Vector2.ONE / text_scale)
	var asc := font.get_ascent(fs)
	if outline:
		draw_string_outline(font, Vector2(0, asc), s, HORIZONTAL_ALIGNMENT_LEFT, -1, fs, int(2 * text_scale), outline_col)
	draw_string(font, Vector2(0, asc), s, HORIZONTAL_ALIGNMENT_LEFT, -1, fs, col)
	draw_set_transform(Vector2.ZERO)
