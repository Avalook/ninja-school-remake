extends SceneTree
func _init():
	var g = load("res://core/core.gd").new()
	g.boot(480, 320)
	var maps = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,94,95,96,97,99]
	var ent = {}
	for n in maps:
		g.q_i(n)
		var info = {"n": n, "w": g.iP, "h": g.iQ, "left": g.jr, "right": g.js, "up": g.jt, "down": g.ju}
		if g.js != -1: ent[g.js] = [g.jx, g.jy]
		if g.jr != -1: ent[g.jr] = [g.jv, g.jw]
		if g.jt != -1: ent[g.jt] = [g.jz, g.jA]
		if g.ju != -1: ent[g.ju] = [g.jB, g.jC]
		print(JSON.stringify(info))
	print("ENT ", JSON.stringify(ent))
	quit()
