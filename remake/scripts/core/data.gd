# Original content as data (exported from the original game, see REMAKE_PLAN.md).
extends Node

var static_data := {}
var maps := {}

func _ready() -> void:
	static_data = JSON.parse_string(FileAccess.get_file_as_string("res://data/static.json"))

func map(id: int) -> Dictionary:
	if not maps.has(id):
		maps[id] = JSON.parse_string(FileAccess.get_file_as_string("res://data/maps/map_%d.json" % id))
	return maps[id]

func t(name: String):
	return static_data.get(name)
