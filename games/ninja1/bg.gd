extends Node2D

# Nền vector tạm thời: núi, trăng, mặt đất, nền tảng.
func _draw() -> void:
	# Bầu trời đêm xanh teal
	draw_rect(Rect2(0, 0, 960, 540), Color(0.05, 0.16, 0.18))
	# Trăng
	draw_circle(Vector2(780, 110), 42.0, Color(0.92, 0.9, 0.75))
	draw_circle(Vector2(795, 100), 38.0, Color(0.05, 0.16, 0.18))
	# Núi xa
	draw_colored_polygon(PackedVector2Array([
		Vector2(0, 360), Vector2(180, 220), Vector2(360, 360)
	]), Color(0.08, 0.22, 0.24))
	draw_colored_polygon(PackedVector2Array([
		Vector2(240, 360), Vector2(480, 180), Vector2(720, 360)
	]), Color(0.1, 0.26, 0.28))
	draw_colored_polygon(PackedVector2Array([
		Vector2(560, 360), Vector2(800, 240), Vector2(960, 360)
	]), Color(0.08, 0.22, 0.24))
	# Mặt đất
	draw_rect(Rect2(0, 500, 960, 40), Color(0.12, 0.2, 0.18))
	draw_rect(Rect2(0, 500, 960, 4), Color(0.3, 0.45, 0.35))
	# Nền tảng
	_draw_platform(Vector2(240, 400), Vector2(160, 16))
	_draw_platform(Vector2(560, 340), Vector2(160, 16))
	_draw_platform(Vector2(760, 430), Vector2(120, 16))
	# Cổng
	_draw_gate(Vector2(880, 470))

func _draw_platform(pos: Vector2, size: Vector2) -> void:
	var rect := Rect2(pos - size / 2.0, size)
	draw_rect(rect, Color(0.2, 0.3, 0.28))
	draw_rect(Rect2(rect.position, Vector2(size.x, 3)), Color(0.4, 0.55, 0.45))

func _draw_gate(pos: Vector2) -> void:
	var c := Color(0.85, 0.6, 0.25)
	draw_rect(Rect2(pos.x - 20, pos.y - 80, 8, 80), c)
	draw_rect(Rect2(pos.x + 12, pos.y - 80, 8, 80), c)
	draw_rect(Rect2(pos.x - 24, pos.y - 84, 48, 8), c)
	draw_rect(Rect2(pos.x - 24, pos.y - 60, 48, 4), c)
