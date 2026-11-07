extends SceneTree
func _init():
	print("loading host")
	var h = load("res://core/orig_host.gd")
	print("host ", h)
	var o = load("res://core/orig.gd")
	print("orig ", o)
	var r = load("res://core/orig_run.gd")
	print("run ", r)
	quit()
