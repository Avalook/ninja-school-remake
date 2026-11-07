extends SceneTree
func _init():
	var g = load("res://core/core.gd").new()
	g.boot(480, 320)
	for i in 300:
		g.tick()
	g.set("eH", false); g.fh = 48; g.fi = 120; g.x_i(4)
	var t0 = Time.get_ticks_usec(); var worst = 0
	for i in 1000:
		var a = Time.get_ticks_usec()
		if i % 7 == 0: g.key_down(-4)
		if i % 7 == 3: g.key_up(-4)
		if i % 5 == 0: g.key_down(-5)
		if i % 5 == 2: g.key_up(-5)
		g.tick()
		worst = max(worst, Time.get_ticks_usec() - a)
	print("avg tick ms ", (Time.get_ticks_usec() - t0) / 1000.0 / 1000.0, " worst ms ", worst / 1000.0, " cmds ", g._gq.size(), " v ", g.v)
	quit()
