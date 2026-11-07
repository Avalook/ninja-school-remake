extends SceneTree
func _init():
	var g = load("res://core/core.gd").new()
	g.boot(480, 320)
	var keys = [[60,-7],[100,-6],[180,-5]]
	for i in 700:
		for k in keys:
			if k[0] == i: g.key_down(k[1])
			if k[0] + 2 == i: g.key_up(k[1])
		if i > 330 and i < 600 and i % 12 == 0: g.key_down(-5)
		if i > 330 and i < 600 and i % 12 == 2: g.key_up(-5)
		if i == 620: g.key_down(-4)
		g.tick()
	print("fh ", g.fh, " fi ", g.fi, " fR ", g.fR, " fS ", g.fS, " fV ", g.fV, " A ", g.A)
	var n = 0
	for c in g._gq:
		if c[0] == 4 and (c[-1] == "b_o" or (c[-1] == "k_o" and n < 5)):
			if c[-1] == "k_o": n += 1
			print(c[-1], " ", c[1]["p"], " xy ", c[2], ",", c[3], " t ", c[11], ",", c[12], " clip ", c[10])
	quit()
