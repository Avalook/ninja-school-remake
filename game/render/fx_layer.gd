# World-space combat & movement effects drawn above the original scene (logical px, interpolated camera).
# Reads state only: hit events from core.fx_events, player state transitions, monster deaths (jS).
extends Node2D

var core
var r
var fx: Array = []
var last_A := -1
var last_jS: Array = []
var run_t := 0.0

func _spawn(kind: String, p: Vector2, dir := 1, life := 0.35) -> void:
	fx.append({"k": kind, "p": p, "d": dir, "t": 0.0, "life": life, "seed": randf() * TAU})

func on_tick() -> void:
	for e in core.fx_events:
		if e[0] == "hit":
			_spawn("hit", Vector2(e[1], e[2]), e[3], 0.28)
			_spawn("slash", Vector2(e[1], e[2]), e[3], 0.22)
	core.fx_events.clear()
	if core.d != 1:
		return
	var A: int = core.A
	var feet := Vector2(core.fh, core.fi)
	if last_A in [3, 4, 12] and A in [1, 2, 8]:
		_spawn("dust", feet, 0, 0.45)
		_spawn("dust", feet + Vector2(-6, 0), -1, 0.4)
		_spawn("dust", feet + Vector2(6, 0), 1, 0.4)
	if A == 3 and last_A in [1, 2]:
		_spawn("dust", feet, 0, 0.35)
	if A == 2 and core.ticks % 6 == 0:
		_spawn("dust", feet - Vector2(core.fp * 6, 0), -core.fp, 0.35)
	last_A = A
	var jS: Array = core.jS if core.jS != null else []
	for i in min(jS.size(), last_jS.size()):
		if jS[i] == 1 and last_jS[i] != 1:
			_spawn("burst", Vector2(core.jN[i], core.jO[i] - core.jP[i] / 2), 0, 0.5)
	last_jS = jS.duplicate()

func _process(delta: float) -> void:
	for f in fx:
		f["t"] += delta
	fx = fx.filter(func(f): return f["t"] < f["life"])
	queue_redraw()

func _draw() -> void:
	if core == null or r == null or core.d != 1:
		return
	var cam: Vector2 = r.cam
	for f in fx:
		var k: float = f["t"] / f["life"]
		var p: Vector2 = f["p"] - cam
		match f["k"]:
			"hit":
				var rad := 3.0 + 10.0 * k
				draw_circle(p, rad, Color(1, 0.95, 0.7, 0.55 * (1.0 - k)))
				for i in 6:
					var a: float = f["seed"] + i * TAU / 6.0
					var a1 := p + Vector2(cos(a), sin(a)) * (2.0 + 9.0 * k)
					var a2 := p + Vector2(cos(a), sin(a)) * (5.0 + 16.0 * k)
					draw_line(a1, a2, Color(1, 0.85, 0.3, 1.0 - k), 1.2)
			"slash":
				var d: int = f["d"]
				var pts := PackedVector2Array()
				var rad := 14.0 + 6.0 * k
				for i in 9:
					var a := lerpf(-1.3, 1.1, float(i) / 8.0)
					pts.append(p + Vector2(cos(a) * d, sin(a)) * rad - Vector2(d * 6, 0))
				draw_polyline(pts, Color(0.8, 0.95, 1.0, 0.85 * (1.0 - k)), 2.5 * (1.0 - k) + 0.5, true)
			"dust":
				var d: int = f["d"]
				var c := Color(0.92, 0.85, 0.7, 0.5 * (1.0 - k))
				draw_circle(p + Vector2(d * 10.0 * k, -3.0 * k - 1), 2.0 + 4.0 * k, c)
			"burst":
				for i in 10:
					var a: float = f["seed"] + i * TAU / 10.0
					var q := p + Vector2(cos(a), sin(a)) * (4.0 + 22.0 * k)
					draw_circle(q, 2.5 * (1.0 - k) + 0.5, Color(1.0, 0.6 + 0.4 * (i % 2), 0.3, 1.0 - k))
				draw_circle(p, 10.0 * (1.0 - k), Color(1, 1, 1, 0.35 * (1.0 - k)))
