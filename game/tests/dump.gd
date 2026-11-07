extends SceneTree
# dumps selected original tables after boot + New Game map load to JSON
func _init():
	var g = load("res://core/core.gd").new()
	g.boot(480, 320)
	var keys = [[60,-7],[100,-6],[180,-5]]
	for i in 260:
		for k in keys:
			if k[0] == i: g.key_down(k[1])
			if k[0] + 2 == i: g.key_up(k[1])
		g.tick()
	var out = {}
	for n in OS.get_cmdline_user_args()[1].split(","):
		out[n] = g.get(n)
	var f = FileAccess.open(OS.get_cmdline_user_args()[0], FileAccess.WRITE)
	f.store_string(JSON.stringify(out))
	quit()
