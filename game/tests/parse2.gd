extends SceneTree
func _init():
	for p in ["res://main.gd","res://render/cmd_renderer.gd","res://render/hd_world.gd","res://render/overlay.gd","res://render/fx_layer.gd","res://ui/settings.gd","res://ui/controls.gd"]:
		var s = load(p)
		print(p, " ", s != null)
	quit()
