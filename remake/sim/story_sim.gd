# StorySim: the ORIGINAL story rules on the original variables (S) at the original 25 Hz. No Node, no UI, no await.
#   map entry      m(n) a.java L4097 (before load), f(n) L3478 (girl companion), n(n) L4132
#   cutscenes      sim/story/cutscene.gd  (D/C L4660-4784, ad() L6992, V() L5881)
#   NPC / quests   sim/story/quests.gd    (talk switch L8361, z() L2393, L(n) L11672, u() L2112)
#   items / travel sim/story/items.gd     (aq() L14174, hot keys L8315, world map d=6/7, ap() L14009)
# The view (view/story_view.gd) owns UI, menus, input, Game.mode and the camera node; the sim asks for a mode
# with the "mode" event.
#
# Effects leave the sim as events through on_event (called immediately, in the original order):
#   {"t": "say", "lines", "anchor", "id"}   dialogue box; the view calls close_dialog(id) when it is closed
#   "title" (text, seconds) | "hide_title" | "notice" (text) | "marker" (map, pos, npc) | "scroll" (pos)
#   "weapon" (tier) | "changed" | "cutscene_started" (group, seq) | "cutscene_finished" (seq) | "to_title"
#   "mode" (m) | "warp" (map, spawn) | "camera" (target, active) | "camera_snap"
# Without on_event they are queued in `out`.
# Reads/writes `world` (sim/world_sim.gd: map json, set_flag_tile) and `player` (sim/player_sim.gd: pos,
# facing, is_standing, place, scripted, script_keys), wired by sim/sim.gd.
extends RefCounted

const Cutscene := preload("res://sim/story/cutscene.gd")
const Quests := preload("res://sim/story/quests.gd")
const Items := preload("res://sim/story/items.gd")

var st              # original state (autoload S, or a state_base instance in tests)
var world           # sim/world_sim.gd (map json, collision flags)
var player          # player (pos, facing, is_standing, place, scripted, script_keys)
var data            # Data autoload (map(id), static_data) - optional

var cutscene
var quests
var items

var on_event := Callable()
var out: Array = []
var notice_text := ""        # c(String): current notice (original cr), shown while S.cp
var events: Array = []        # story log (tests compare it with the oracle)
var verbose := false
var _dialog_open := 0
var _dialogs := {}            # open dialogue id -> {"kind": "talk", "cb"} | {"kind": "cut", "tok"}
var _dialog_seq := 0
var _static := {}
var _last_enter := [-1, -1]

func _init() -> void:
	cutscene = Cutscene.new(self)
	quests = Quests.new(self)
	items = Items.new(self)

func emit(ev: Dictionary) -> void:
	if on_event.is_valid():
		on_event.call(ev)
	else:
		out.append(ev)

# ================================================================= public API
func is_cutscene_active() -> bool:
	return st != null and bool(st.fV)

func dialog_open() -> bool:
	return _dialog_open > 0

func talk_label() -> String:
	return str(quests.talk_target().get("label", ""))

# talk key next to someone: opens a dialogue (event "say") or returns {"shop": kv} for the view's shop menu
func interact() -> Dictionary:
	var t: Dictionary = quests.talk_target()
	match str(t.get("kind", "")):
		"npc":
			return quests.talk(int(t["index"]))
		"girl":
			quests.talk_girl()
		"classmate":
			quests.talk_classmate(int(t["index"]))
	return {}

func on_enemy_killed(type: int, pos := Vector2.ZERO) -> void:
	quests.on_enemy_killed(type, pos)

func on_player_attack(airborne: bool) -> void:
	quests.on_player_attack(airborne)

# ================================================================= 25 Hz tick (run() d==1 order)
func tick() -> void:
	if st == null:
		return
	_sync_player_in()
	cutscene.tick_triggers()          # ad()
	if cutscene.tick_sequence():      # V(): true = warp / title -> rest of the tick skipped
		return
	_apply_player_control()
	quests.tick()
	cutscene.tick_camera()
	_push_camera()

# ================================================================= map entry
# Order of the original loader (Midlet.run L381): m(v) ... f(v) g(v) ... n(v) last.
# frame: the view's process frame, so the signal and a manual call in the same frame run it once.
func on_map_enter(id: int, frame := -1) -> void:
	if st == null:
		return
	if _last_enter[0] == id and _last_enter[1] == frame:
		return                           # already handled this load (signal + manual call)
	_last_enter = [id, frame]
	if world and "player" in world and world.player:
		player = world.player
	_sync_player_in()
	log_event("enter %d fh=%d fi=%d" % [id, st.fh, st.fi])
	var before := Vector2(st.fh, st.fi)
	var before_fp: int = st.fp
	st.v = id
	_load_map_story_state(id)
	_m(id)
	_f(id)
	_g_variants(id)
	_n(id)
	cutscene.reset_camera()
	if Vector2(st.fh, st.fi) != before or st.fp != before_fp:
		_place_player(Vector2(st.fh, st.fi), st.fp)
	_push_camera(true)

# m(n) L4097
func _m(n: int) -> void:
	if n == 94:
		st.dz = true
		st.fh = 24
		st.fi = 288
		st.gp = 94
		st.gq = 12
		st.gr = 288
		st.gC = 1
		st.gn = false
		st.fp = 1
	if n == 0 and st.O == 15 and st.fD:
		st.gp = -1
		st.gn = false
	if n == 0 and st.O == 16 and st.fe:
		st.fd = true
		st.gp = 0
		st.gn = true
		st.fx = true
		st.O = -1
		st.fh = 984
		st.fi = 408
		return
	if n == 95 and not st.eK:
		st.gn = false
		st.gq = 599
		st.gr = 264
		st.gC = -1
		st.gp = 95

# f(n) L3478: the girl companion follows the player into the new map
func _f(n: int) -> void:
	if st.gn:
		st.gp = n
	if st.gp != n:
		return
	st.ij = -1
	if st.gn:
		st.gq = st.fh - st.fp * 10
		st.gr = st.fi
		st.gC = st.fp
		st.gu = 4

# n(n) L4132: map-entry triggers
func _n(n: int) -> void:
	st.ih = false
	st.fV = false
	st.fK = 0
	if n == 15 and st.O == 15:
		quests.drop_scroll(Vector2(216, 432))
	if n == 8 and st.O == 6:
		quests.drop_scroll(Vector2(72, 240))
	if n == 4 and st.eH:
		st.T = true
		st.eL = true
		st.eT = true
		st.eS = true
		st.H = 1000
		st.G = 1000
		st.J = 2000
		st.I = 2000
		st.E = 25
		st.A = 4
		st.hd = 500
		st.hc = 500
		cutscene.start_group(0)
	elif n == 99:
		st.eH = true
		st.dz = true
		st.fh = 108
		st.fi = 312
		st.fp = -1
		cutscene.start_group(1)
	elif n == 94:
		cutscene.start_group(10)
	elif n == 0 and st.eH:
		st.T = false
		st.eL = false
		st.eS = false
		st.eT = false
		st.fA = false
		st.G = 200
		st.H = 200
		st.I = 200
		st.J = 200
		st.hd = 1000
		st.hc = 1000
		st.N = 0
		st.M = 1
		st.E = 20
		st.fh = 864
		st.fi = 336
		for k in 19:
			st.W[k] = false
		st.W[0] = true
		st.fp = -1
		st.dz = false
		st.dA = false
		cutscene.start_group(2)
		st.eH = false
	elif n == 3 and st.O == 3 and not st.gn:
		cutscene.start_group(3)
	elif n == 0 and st.O == 3 and st.gn:
		cutscene.start_group(4)
	elif n == 97 and not st.eI:
		cutscene.start_group(5)
	elif n == 96 and not st.eJ:
		cutscene.start_group(6)
	elif n == 95 and not st.eK:
		cutscene.start_group(7)
	elif n == 101:
		cutscene.start_group(8)
	elif n == 9 and st.O == 10:
		cutscene.start_group(9)
	if n == 97 and st.eI:
		clear_barrier(6)
	if n == 96 and st.eJ:
		clear_barrier(11)

# g(n) L3537: classmate lines/sprites that depend on eT (Toniku turned assassin)
func _g_variants(n: int) -> void:
	if not (st.dy is Array) or not (st.dK is Array):
		return
	var idx := -1
	var lines_t := []
	var lines_f := []
	if n == 0:
		idx = 2
		lines_t = ["Thằng bên kia là sát thủ giả mạo.", "Ta đây mới là ninja sát thủ thứ thiệt đây!"]
		lines_f = ["Chào, tao tên là Tonic, mày là thằng nào?", "Nhìn lão hiệu trưởng kìa, lão có một hũ rượu trông hấp dẫn quá!", "Nếu muốn rủ tao đi nhậu thì ok, còn không thì biến đi.", "À, nếu mày có thấy con chó của tao thì báo tao 1 tiếng nhé."]
	elif n == 1:
		idx = 1
		lines_t = ["Ta đây là một ninja sát thủ!", "Ha ha ha!"]
		lines_f = ["Mày đã học khinh công rồi hả?", "À, mày có thấy con chó của tao chạy đâu đâu không?"]
	elif n == 2:
		idx = 2
		lines_t = ["Ninja sát thủ là ta - Thiên hạ vô địch!"]
		lines_f = ["Mày có thấy con chó của tao ở đâu không?"]
	if idx < 0 or idx >= st.dy.size() or idx >= st.dK.size():
		return
	st.dy[idx] = lines_t if st.eT else lines_f
	st.dK[idx] = 6 if st.eT else 2

# Map-dependent story data from remake/data/maps/map_<id>.json `state` (values after the original
# loaded the map; missing keys = boot value from static.json). Only data, never progress flags.
const MAP_KEYS := ["lx", "ly", "lz", "lA", "dx", "dC", "dD", "dE", "dF", "dG", "dH", "dI", "dJ",
	"dK", "dL", "dM", "dN", "dO", "dP", "dQ", "dy", "jr", "js", "jt", "ju", "jv", "jw", "jx", "jy",
	"jz", "jA", "jB", "jC", "jD", "iR", "iS"]

func _load_map_story_state(id: int) -> void:
	var m := _map_json(id)
	if m.is_empty():
		return
	var state: Dictionary = m.get("state", {})
	var stat := _static_json()
	for k in MAP_KEYS:
		var val = state.get(k, stat.get(k))
		if val == null:
			continue
		st.set(k, val.duplicate(true) if val is Array else (int(val) if val is float else val))
	var exits: Dictionary = m.get("exits", {})
	for k in exits:
		if k in st:
			st.set(k, int(exits[k]))
	var mw := int(m.get("w", 0))
	var mh := int(m.get("h", 0))
	if mw > 0:
		st.iR = mw * 24
		st.iS = mh * 24
		st.fT = (mw - 1) * 24 - st.q
		st.fU = (mh - 1) * 24 - st.r

func _map_json(id: int) -> Dictionary:
	if world and "map" in world and world.map is Dictionary and int(world.map.get("id", -999)) == id:
		return world.map
	if data and data.has_method("map"):
		var d = data.map(id)
		return d if d is Dictionary else {}
	var path := "res://data/maps/map_%d.json" % id
	if not FileAccess.file_exists(path):
		return {}
	var j = JSON.parse_string(FileAccess.get_file_as_string(path))
	return j if j is Dictionary else {}

func _static_json() -> Dictionary:
	if _static.is_empty():
		if data and "static_data" in data and data.static_data is Dictionary and not data.static_data.is_empty():
			_static = data.static_data
		else:
			var j = JSON.parse_string(FileAccess.get_file_as_string("res://data/static.json"))
			_static = j if j is Dictionary else {}
	return _static

# ================================================================= helpers used by the modules
func player_standing() -> bool:
	# original A == 1 (standing still on the ground)
	if player == null:
		return true
	if player.has_method("is_standing"):
		return bool(player.is_standing())
	return str(player.state) == "idle"

func _sync_player_in() -> void:
	if player == null:
		return
	st.fh = int(round(player.pos.x))
	st.fi = int(round(player.pos.y))
	if "facing" in player and int(player.facing) != 0:
		st.fp = 1 if int(player.facing) > 0 else -1

func _place_player(p: Vector2, facing: int) -> void:
	if player == null:
		return
	if player.has_method("place"):
		player.place(p, facing)
	else:
		player.pos = p
		if "facing" in player:
			player.facing = facing

func _apply_player_control() -> void:
	if player == null or not ("scripted" in player):
		return
	player.scripted = bool(st.fV) or _dialog_open > 0

func _push_camera(snap := false) -> void:
	if st.fx:
		emit({"t": "camera", "target": Vector2.ZERO, "active": false})
	else:
		emit({"t": "camera", "target": Vector2(st.fL + st.bF, st.fM + st.bG), "active": true})
	if snap:
		camera_snap()

func camera_snap() -> void:
	emit({"t": "camera_snap"})

func warp(map_id: int, spawn: Vector2) -> void:
	st.fh = int(spawn.x)
	st.fi = int(spawn.y)
	emit({"t": "warp", "map": map_id, "spawn": spawn})

# i(24, row, 0) L11582: clear the collision flags of tile column 24, rows 0..max_row
func clear_barrier(max_row: int) -> void:
	if world == null:
		return
	for r in range(0, max_row + 1):
		if world.has_method("set_flag_tile"):
			world.set_flag_tile(24, r, 0)
		elif "flags" in world and "w" in world:
			var k: int = r * int(world.w) + 24
			if k >= 0 and k < world.flags.size():
				world.flags[k] = 0

# ---------------------------------------------------------------- dialogue boxes
# a(String[], cb, x, y) L4670 + its end in run() (L8246: M() paging then z()): close_dialog runs z().
func dialog(lines: Array, cb: int, anchor: Vector2) -> void:
	_dialog_open += 1
	st.iC = true
	st.iG = cb
	_apply_player_control()
	say(lines, anchor, {"kind": "talk", "cb": cb})

# opens a box (event "say"); `end` says what close_dialog does: talk z(cb) | cut (cutscene token) | none
func say(lines: Array, anchor: Vector2, end := {}) -> int:
	var typed: Array[String] = []
	for l in lines:
		typed.append(str(l))
	log_event("say %s" % JSON.stringify(typed))
	_dialog_seq += 1
	_dialogs[_dialog_seq] = end
	emit({"t": "say", "lines": typed, "anchor": anchor, "id": _dialog_seq})
	return _dialog_seq

# the view closed dialogue box `id` (the point where the original's paging M() returned)
func close_dialog(id: int) -> void:
	var end: Dictionary = _dialogs.get(id, {})
	_dialogs.erase(id)
	match str(end.get("kind", "")):
		"talk":
			st.iC = false
			_dialog_open -= 1
			quests.dialog_end(int(end["cb"]))
			_apply_player_control()
		"cut":
			cutscene.dialog_closed(int(end["tok"]))

func show_title_card(text: String, seconds: float) -> void:
	log_event("title %s" % text)
	emit({"t": "title", "text": text, "seconds": seconds})

# c(String) L4761: short notice line (HUD), 150 ticks
func notice(text: String) -> void:
	notice_text = text
	st.set("cr", text)          # cr was null at boot, so the generated state may not declare it
	st.cq = 0
	st.cp = true
	log_event("note %s" % text)
	emit({"t": "notice", "text": text})

# a(int,int,int,int) L4626: objective marker (map ge, position gf/gg, NPC gm)
func set_marker(map_id: int, x: int, y: int, npc: int) -> void:
	st.ge = map_id
	st.gf = x
	st.gg = y
	st.gm = npc
	emit({"t": "marker", "map": map_id, "pos": Vector2(x, y), "npc": npc})

func set_mode(m: String) -> void:
	emit({"t": "mode", "mode": m})

func state_changed() -> void:
	emit({"t": "changed"})

func save_game() -> void:
	if st.has_method("save_game"):
		st.save_game()

func log_event(s: String) -> void:
	events.append(s)
	if verbose:
		print("[story] ", s)

# ================================================================= title flow
func reset_state() -> void:
	var fresh = load("res://scripts/core/state_base.gd").new()
	for p in fresh.get_property_list():
		if p["usage"] & PROPERTY_USAGE_SCRIPT_VARIABLE:
			var val = fresh.get(p["name"])
			st.set(p["name"], val.duplicate(true) if val is Array or val is Dictionary else val)
	fresh.free()

func new_game() -> void:
	cutscene.abort()
	reset_state()
	set_mode("play")
	warp(99, Vector2(108, 312))

func continue_game() -> bool:
	if not (st.has_method("has_save") and st.has_save()):
		return false
	cutscene.abort()
	if not st.load_game():
		return false
	set_mode("play")
	warp(st.v, Vector2(st.fh, st.fi))
	return true
