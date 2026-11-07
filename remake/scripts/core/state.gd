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

# original u(int n) a.java L2089: gain EXP; at most one level per gain, N resets to 0 (no carry-over),
# E += aj[M-2], F += 2, H += al[M-2], HP full, MP refilled (max MP unchanged), modal popup (d = 7).
# (The demo-build lock at level 7, Midlet.a, does not apply to the PC version.)
func add_exp(n: int) -> void:
	N += n
	var w = get_tree().get_first_node_in_group("world") if is_inside_tree() else null
	var p := Vector2(fh, fi - 32)
	if N > int(ak[clampi(M - 1, 0, ak.size() - 1)]) and M < ak.size():
		N = 0
		M += 1
		E += int(aj[M - 2])
		F += 2
		H += int(al[M - 2])
		G = H
		I = J
		if w:
			w.float_text("LEVEL UP", p, Color(1, 0.85, 0.2))
			w.spawn_fx("power", Vector2(fh, fi - 20), 1)
		UI.message("Chúc mừng! Bạn đạt được cấp %d\nHP: %d\nSức đánh: %d" % [M, H, E + (E >> 2) * (x + 1)])
	elif w:
		w.float_text("+%d" % n, p, Color(0.6, 1, 0.6))
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
