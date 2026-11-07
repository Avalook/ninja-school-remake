extends SceneTree
func _init():
	var C = load("res://core/core.gd")
	var g = C.new()
	g.boot()
	for i in 200:
		g.tick()
		if i % 20 == 0:
			print("t=%d d=%d Y=%d v=%d fh=%d fi=%d A=%d" % [i, g.d, g.Y, g.v, g.fh, g.fi, g.A])
	quit()
