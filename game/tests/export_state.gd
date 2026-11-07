extends SceneTree
# Dumps the original game state after loading each map (all script vars) -> remake/data/raw/map_<n>.json
# plus the static tables right after boot -> remake/data/raw/static.json
func dump(g, path: String) -> void:
	var out := {}
	for p in g.get_property_list():
		if not (p["usage"] & PROPERTY_USAGE_SCRIPT_VARIABLE):
			continue
		var n: String = p["name"]
		if n.begins_with("_") or n.begins_with("r_") or n.begins_with("p_"):
			continue
		var v = g.get(n)
		if v is Object:
			continue
		out[n] = v
	var f := FileAccess.open(path, FileAccess.WRITE)
	f.store_string(JSON.stringify(out))

func _init():
	var root := OS.get_cmdline_user_args()[0]
	var g = load("res://core/core.gd").new()
	g.boot(480, 320)
	for i in 300:
		g.tick()
	dump(g, root + "/static.json")
	for m in [99, 4, 0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 94, 95, 96, 97]:
		g.eH = (m == 99 or m == 4)
		g.fV = false
		g.x_i(m)
		for i in 3:
			g.tick()
		dump(g, root + "/map_%d.json" % m)
		print("dumped ", m)
	quit()
