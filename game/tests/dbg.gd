extends SceneTree
func _init():
	var g = load("res://core/core.gd").new()
	g.boot(480, 320)
	var keys = [[60,-7],[100,-6],[180,-5]]
	for i in 600:
		for k in keys:
			if k[0] == i: g.key_down(k[1])
			if k[0] + 2 == i: g.key_up(k[1])
		if i > 330 and i % 12 == 0: g.key_down(-5)
		if i > 330 and i % 12 == 2: g.key_up(-5)
		g.tick()
	var cnt = {}
	for c in g._gq:
		var k = str(c[0]) + " " + str(c[-1])
		if c[0] == 4: k = c[-1] + " " + c[1]["p"] + " t%d" % c[5] + (" W" if c[11] != 0 else "")
		cnt[k] = cnt.get(k, 0) + 1
	for k in cnt: print(k, " ", cnt[k])
	print("fh ", g.fh, " fi ", g.fi, " d ", g.d, " fV ", g.fV)
	quit()
