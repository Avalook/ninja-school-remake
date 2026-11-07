extends SceneTree
func _init():
	var h = load("res://render/hd_world.gd").new()
	print(ResourceLoader.exists("res://art/hero/hero_sheet.png"), " ", FileAccess.file_exists("res://art/hero/hero_sheet.json"))
	var t = load("res://art/hero/hero_sheet.png")
	print(t, " ", t.get_size() if t else null)
	quit()
