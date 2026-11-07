# DropsView: draws the loot of sim/enemies/drops.gd (coins, HP/MP potions, quest scroll), interpolating
# between the previous and the current 25 Hz state. Called from view/enemies_view.gd in its draw order.
extends RefCounted

const SP := preload("res://view/sprites.gd")

# draw: coins ej[dp] region (0, dn*15, 8, 15) anchor 33 (~L7838); potions ez[cQ] region (0, cW*15, 13, 15)
static func draw(ci: CanvasItem, drops, alpha: float, t_sec: float) -> void:
	for d in drops.coins:
		if d.age != 0 and d.age != 301:
			var p := Vector2(lerpf(d.px, d.x, alpha), lerpf(d.py, d.y, alpha))
			SP.draw(ci, "/i/dm%d.png" % d.size, p.x, p.y, 33, false, Color.WHITE, Rect2(0, d.spin * 15, 8, 15))
	for d in drops.items:
		if d.age != 0 and d.age != 301:
			var p := Vector2(lerpf(d.px, d.x, alpha), lerpf(d.py, d.y, alpha))
			var bob := 0.0 if d.vy != 0 else sin(t_sec * 4.0 + d.x) * 0.6
			SP.draw(ci, "/i/pt%d.png" % d.kind, p.x, p.y + bob, 33, false, Color.WHITE, Rect2(0, d.size * 15, 13, 15))
	if drops.scroll_state != 0:
		SP.draw(ci, "/i/bk.png", drops.scroll_x, lerpf(drops.scroll_py, drops.scroll_y, alpha), 33)
