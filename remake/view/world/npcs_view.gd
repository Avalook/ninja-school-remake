# NPCs of the current map (original lx / ly / lz / lA, drawn in a.java ~L14698: legs at (ly, lz) and the
# body at lz - lJ[kind] with a small idle bob). HD cast art (art/cast/npc_<kind>.png + .json, anchor at
# the feet) replaces the original two-part sprites when present.
extends Node2D

var world
var orig := {}
var hd := {}
var font: Font

func _ready() -> void:
	z_index = 3
	font = ThemeDB.fallback_font

func _part(kind: int, i: int) -> Texture2D:
	var k := "%d%d" % [kind, i]
	if not orig.has(k):
		var p := "res://orig/npc/%s.png" % k
		orig[k] = load(p) if ResourceLoader.exists(p) else null
	return orig[k]

func _hd(kind: int):
	if not hd.has(kind):
		var p := "res://art/cast/npc_%d.png" % kind
		if ResourceLoader.exists(p):
			var m = JSON.parse_string(FileAccess.get_file_as_string(p.replace(".png", ".json")))
			hd[kind] = [load(p), m]
		else:
			hd[kind] = null
	return hd[kind]

func _process(_d: float) -> void:
	queue_redraw()

func _draw() -> void:
	if world == null or world.map_id < 0:
		return
	_draw_marker()
	var n: int = int(S.lx)
	for i in n:
		if i >= S.ly.size() or i >= S.lz.size() or i >= S.lA.size():
			break
		var x := float(S.ly[i])
		var y := float(S.lz[i])
		var kind: int = int(S.lA[i])
		var face := -1 if world.player and world.player.pos.x < x else 1
		draw_set_transform(Vector2(x, y - 1), 0.0, Vector2(1.0, 0.28))
		draw_circle(Vector2.ZERO, 10.0, Color(0, 0, 0, 0.25))
		draw_set_transform(Vector2.ZERO)
		var h = _hd(kind)
		var t := Time.get_ticks_msec() / 1000.0
		if h != null:
			var tex: Texture2D = h[0]
			var m: Dictionary = h[1] if h[1] is Dictionary else {}
			var s: float = float(m.get("scale", 0.2))
			var anc := Vector2(m.get("anchor", [tex.get_width() / 2, tex.get_height()])[0], m.get("anchor", [0, tex.get_height()])[1])
			var breathe := 1.0 + 0.012 * sin(t * 2.2 + i)
			draw_set_transform(Vector2(x, y), 0.0, Vector2(s * face, s * breathe))
			draw_texture(tex, -anc)
			draw_set_transform(Vector2.ZERO)
		else:
			var legs := _part(kind, 1)
			var body := _part(kind, 0)
			var lj: int = int(S.lJ[kind]) if "lJ" in S and kind < S.lJ.size() else 6
			var bob := 0 if S.w % (15 + kind) > 4 else 1
			if legs:
				draw_texture(legs, Vector2(x - legs.get_width() / 2.0, y - legs.get_height()))
			if body:
				draw_texture(body, Vector2(x - body.get_width() / 2.0, y - lj + bob - body.get_height()))
		if world.player and world.player.near_npc == i:
			var a := 0.6 + 0.4 * sin(t * 6.0)
			draw_string(font, Vector2(x - 20, y - 44 - 2 * sin(t * 4.0)), "!", HORIZONTAL_ALIGNMENT_CENTER, 40, 14, Color(1, 0.9, 0.3, a))

# objective marker (original ge / gf / gg / gm, drawn at gi/gj in paint): a bobbing arrow at the target,
# kept on the screen edge (pointing towards it) while the target is off screen
func _draw_marker() -> void:
	if int(S.ge) != world.map_id or int(S.ge) < 0:
		return
	var t := Time.get_ticks_msec() / 1000.0
	var cam: Vector2 = world.camera.position
	var vs: Vector2 = world.view_size()
	var target := Vector2(float(S.gf), float(S.gg))
	if int(S.gm) >= 0 and int(S.gm) < S.ly.size():
		target = Vector2(float(S.ly[S.gm]), float(S.lz[S.gm]) - 50)
	if target.x >= 20000:
		target.x = world.w * 24.0
	var m := 14.0
	var p := Vector2(clampf(target.x, cam.x + m, cam.x + vs.x - m), clampf(target.y, cam.y + m + 20, cam.y + vs.y - m))
	var dir := Vector2.DOWN
	if target.x > cam.x + vs.x - m:
		dir = Vector2.RIGHT
	elif target.x < cam.x + m:
		dir = Vector2.LEFT
	var bob := dir * (3.0 * sin(t * 6.0))
	var c := Color(1, 0.85, 0.2, 0.95)
	var tip := p + bob + dir * 6.0
	var side := Vector2(-dir.y, dir.x) * 6.0
	var back := p + bob - dir * 4.0
	draw_colored_polygon(PackedVector2Array([tip, back + side, back - side]), c)
	draw_polyline(PackedVector2Array([tip, back + side, back - side, tip]), Color(0.3, 0.1, 0, 0.9), 1.0)
