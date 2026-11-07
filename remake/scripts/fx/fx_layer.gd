# World-space effects: hit sparks, slash arcs, dust, death bursts, power aura, floating damage numbers.
extends Node2D

var world
var fx: Array = []
var texts: Array = []
var font: Font

func _ready() -> void:
	z_index = 20
	font = ThemeDB.fallback_font

func spawn(kind: String, p: Vector2, dir := 1) -> void:
	var life: float = {"hit": 0.28, "slash": 0.22, "dust": 0.42, "burst": 0.5, "power": 1.2, "splash": 0.5}.get(kind, 0.35)
	fx.append({"k": kind, "p": p, "d": dir, "t": 0.0, "life": life, "seed": randf() * TAU})

func text(s: String, p: Vector2, c := Color(1, 0.3, 0.3)) -> void:
	texts.append({"s": s, "p": p, "c": c, "t": 0.0})

func _process(delta: float) -> void:
	for f in fx:
		f["t"] += delta
	fx = fx.filter(func(f): return f["t"] < f["life"])
	for t in texts:
		t["t"] += delta
	texts = texts.filter(func(t): return t["t"] < 0.9)
	queue_redraw()

func _draw() -> void:
	for f in fx:
		var k: float = f["t"] / f["life"]
		var p: Vector2 = f["p"]
		match f["k"]:
			"hit":
				draw_circle(p, 3.0 + 10.0 * k, Color(1, 0.95, 0.7, 0.55 * (1.0 - k)))
				for i in 6:
					var a: float = f["seed"] + i * TAU / 6.0
					draw_line(p + Vector2(cos(a), sin(a)) * (2.0 + 9.0 * k), p + Vector2(cos(a), sin(a)) * (5.0 + 16.0 * k), Color(1, 0.85, 0.3, 1.0 - k), 1.2)
			"slash":
				var d: int = f["d"]
				var pts := PackedVector2Array()
				var rad := 16.0 + 6.0 * k
				for i in 11:
					var a := lerpf(-1.4, 1.1, float(i) / 10.0)
					pts.append(p + Vector2(cos(a) * d, sin(a)) * rad - Vector2(d * 6, 0))
				draw_polyline(pts, Color(0.85, 0.97, 1.0, 0.9 * (1.0 - k)), 3.0 * (1.0 - k) + 0.6, true)
			"dust":
				var d: int = f["d"]
				draw_circle(p + Vector2(d * 10.0 * k, -3.0 * k - 1), 2.0 + 4.0 * k, Color(0.92, 0.85, 0.7, 0.5 * (1.0 - k)))
			"burst":
				for i in 10:
					var a: float = f["seed"] + i * TAU / 10.0
					draw_circle(p + Vector2(cos(a), sin(a)) * (4.0 + 22.0 * k), 2.5 * (1.0 - k) + 0.5, Color(1.0, 0.6 + 0.4 * (i % 2), 0.3, 1.0 - k))
				draw_circle(p, 10.0 * (1.0 - k), Color(1, 1, 1, 0.35 * (1.0 - k)))
			"power":
				for i in 3:
					var kk := fposmod(k * 2.0 + i / 3.0, 1.0)
					draw_arc(p, 8.0 + 40.0 * kk, 0, TAU, 32, Color(1, 0.4, 0.2, 0.6 * (1.0 - kk)), 2.0)
			"splash":
				for i in 7:
					var a := -PI * (0.15 + 0.7 * i / 6.0)
					var q := p + Vector2(cos(a) * 14.0 * k, sin(a) * 18.0 * k + 30.0 * k * k)
					draw_circle(q, 1.8 * (1.0 - k) + 0.4, Color(0.75, 0.9, 1.0, 1.0 - k))
	for t in texts:
		var k: float = t["t"] / 0.9
		var c: Color = t["c"]
		c.a = 1.0 - k * k
		var p: Vector2 = t["p"] - Vector2(0, 18.0 * k)
		draw_string_outline(font, p - Vector2(10, 0), t["s"], HORIZONTAL_ALIGNMENT_CENTER, 20, 9, 3, Color(0, 0, 0, c.a))
		draw_string(font, p - Vector2(10, 0), t["s"], HORIZONTAL_ALIGNMENT_CENTER, 20, 9, c)
