# Game state: the original variables (state_base, generated) + modern helpers.
extends "res://scripts/core/state_base.gd"

signal changed

# original arrays that are null at boot (allocated per map); keywords get a trailing "_" (in -> in_)
var iv: Array = []
var iw: Array = []
var ix: Array = []
var iy: Array = []
var iz: Array = []
var iA: Array = []
var iB: Array = []
var in_: Array = []
var io: Array = []
var ip: Array = []
var iq: Array = []
var ir: Array = []
var is_: Array = []

func level_need() -> int:
	return int(ak[clampi(M - 1, 0, ak.size() - 1)])

# original u(n): gain EXP (and level up with the original tables)
func add_exp(n: int) -> void:
	N += n
	while N >= level_need() and M < ak.size():
		N -= level_need()
		M += 1
		H += 20
		J += 10
		E += 2
		G = H
		I = J
		UI.popup("Lên cấp %d!" % M)
	changed.emit()

func add_money(n: int) -> void:
	dg += n
	changed.emit()

const SAVE_PATH := "user://remake_save.json"

func save_game() -> void:
	var out := {}
	for p in get_property_list():
		if p["usage"] & PROPERTY_USAGE_SCRIPT_VARIABLE:
			var v = get(p["name"])
			if v is int or v is bool or v is String or v is Array:
				out[p["name"]] = v
	var f := FileAccess.open(SAVE_PATH, FileAccess.WRITE)
	f.store_string(JSON.stringify(out))

func has_save() -> bool:
	return FileAccess.file_exists(SAVE_PATH)

func load_game() -> bool:
	if not has_save():
		return false
	var d = JSON.parse_string(FileAccess.get_file_as_string(SAVE_PATH))
	if typeof(d) != TYPE_DICTIONARY:
		return false
	for k in d:
		var cur = get(k)
		if cur is int:
			set(k, int(d[k]))
		elif cur is bool:
			set(k, bool(d[k]))
		else:
			set(k, d[k])
	changed.emit()
	return true

# back to the boot values of the original (New Game)
func reset_state() -> void:
	var fresh = load("res://scripts/core/state_base.gd").new()
	for p in fresh.get_property_list():
		if p["usage"] & PROPERTY_USAGE_SCRIPT_VARIABLE:
			var v = fresh.get(p["name"])
			set(p["name"], v.duplicate(true) if v is Array else v)
	fresh.free()
	changed.emit()
