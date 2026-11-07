extends SceneTree
func _init():
	var g = load("res://core/core.gd").new()
	g.boot(480, 320)
	for i in 400:
		if i == 130: g.key_down(-5)
		if i == 132: g.key_up(-5)
		g.tick()
	for c in g._gq:
		if not (c[-1] in ["k_o","a_o","g_o","d_o","l_o","r_o","b_o"]):
			var s = str(c[0]) + " " + c[-1]
			if c[0] <= 3: s += " col=%d rect=%d,%d,%d,%d" % [c[5], c[1], c[2], c[3], c[4]]
			if c[0] == 4: s += " " + c[1]["p"]
			if c[0] >= 5: s += " '" + c[1] + "'"
			print(s)
	quit()
