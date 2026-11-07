# Draws the map objects of sim/world/objects.gd (push blocks / rafts, parked motorbikes), interpolated between
# the positions of the tick before (prev_b / prev_v) and now. Steps the object rules on Game.tick25: this node
# connects in WorldView._ready, the same place in the tick25 order as the old objects.gd. In the game
# (main.gd, world.external_tick) sim/sim.gd step() runs them instead, at that same place in the tick.
extends Node2D

var world                     # view/world_view.gd
var tex_b: Texture2D
var tex_v: Array = []
var alpha := 0.0

func _ready() -> void:
	z_index = 4
	tex_b = load("res://art/obj/b.png")
	for i in 3:
		tex_v.append(load("res://art/obj/v%d.png" % i))
	if not world.external_tick:
		Game.tick25.connect(_tick)

func _tick() -> void:
	if world == null:
		return
	world.sim.objects.tick(Game.mode == "play")

func _process(_d: float) -> void:
	alpha = clampf(Game.acc / 0.04, 0.0, 1.0)
	queue_redraw()

func _draw() -> void:
	var ob = world.sim.objects
	for n in int(S.it):
		var p := Vector2(S.iv[n], S.iw[n])
		if n < ob.prev_b.size():
			p = ob.prev_b[n].lerp(p, alpha)
		draw_texture_rect(tex_b, Rect2(p.x - 12, p.y - 24, 24, 24), false)
	for n in int(S.il):
		if n == int(S.im):
			continue            # the ridden bike is drawn with the player
		var p := Vector2(S.in_[n], S.io[n])
		if n < ob.prev_v.size():
			p = ob.prev_v[n].lerp(p, alpha)
		draw_bike(self, p, int(S.ip[n]), int(S.iq[n]))

func draw_bike(ci: CanvasItem, p: Vector2, dir: int, frame: int) -> void:
	var t: Texture2D = tex_v[clampi(frame, 0, 2)]
	var sz := Vector2(t.get_width(), t.get_height()) / 4.0
	if dir == 1:
		ci.draw_texture_rect(t, Rect2(p.x - sz.x * 0.5, p.y - sz.y, sz.x, sz.y), false)
	else:
		ci.draw_set_transform(Vector2(p.x + sz.x * 0.5, p.y - sz.y), 0.0, Vector2(-1, 1))
		ci.draw_texture_rect(t, Rect2(Vector2.ZERO, sz), false)
		ci.draw_set_transform(Vector2.ZERO)
