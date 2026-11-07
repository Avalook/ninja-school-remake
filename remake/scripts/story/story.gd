# Story: cutscenes, map-entry triggers, NPC talk / quests, rewards, menus (agent B module).
#
# Runs the ORIGINAL story rules on the original variables (autoload S) at the original 25 Hz:
#   map entry      m(n) a.java L4097 (before load), f(n) L3478 (girl companion), n(n) L4132
#   cutscenes      cutscene_player.gd  (D/C L4660-4784, ad() L6992, V() L5881)
#   NPC / quests   npc_quests.gd       (talk switch L8361, z() L2393, L(n) L11672, u() L2112)
#   menus          menus.gd            (as() L14282, aq() L14174, world map d=6, ap() L14009, title d=3)
#
# Integration (see REMAKE_PLAN.md "Story"):
#   story.attach(world)              world: map_loaded(id) signal, player, camera_override(), flags
#   on_map_enter(id)                 called automatically from world.map_loaded (or call it yourself)
#   tick()                           25 Hz; auto-connected to Game.tick25 when Game exists
#   on_enemy_killed(type, pos)       EnemyManager.enemy_killed -> quest counters (O / fF), scroll drop
#   on_player_attack(airborne)       player melee/shuriken swing -> quest 11 (air hits)
#   interact()                       talk key (-7) when standing: NPC / girl / classmate
#   is_cutscene_active(), is_busy()  player input must come from script_keys while active
#   game_menu(), title_menu(), open_shop(kv), quick_potion(hp)
# Enemy module keeps the boss variables the triggers read: S.gV (boar 22 = dead), S.gS/gT (boar pos),
# S.ia (map-96 boss), S.hj (Toniku hp), S.hu (final boss 22 = dead), S.hs/ht, S.gq/gr (girl).
extends Node

signal cutscene_started(group: int, seq: int)
signal cutscene_finished(seq: int)
signal notice_shown(text: String)
signal marker_changed(map_id: int, pos: Vector2, npc: int)
signal scroll_dropped(pos: Vector2)
signal weapon_changed(tier: int)
signal music_toggled(on: bool)
signal return_to_title
signal quit_to_title
signal story_state_changed

const CutscenePlayer := preload("res://scripts/story/cutscene_player.gd")
const NpcQuests := preload("res://scripts/story/npc_quests.gd")
const Menus := preload("res://scripts/story/menus.gd")

var st              # original state (autoload S, or a state_base instance in tests)
var ui              # autoload UI (say / title_card / popup / choose [/ notice / hide_title_card])
var world           # World node
var player          # Player node (pos, facing, state, script_keys, scripted, place())
var game            # autoload Game (mode, goto_map) - optional

var cutscene
var quests
var menus

var handle_input := true      # poll talk / menu / potion actions in _process
var events: Array = []        # story log (tests compare it with the oracle)
var verbose := false
var _dialog_open := 0
var _menu_open := false
var _static := {}
var _last_enter := [-1, -1]

func _init() -> void:
	cutscene = CutscenePlayer.new(self)
	quests = NpcQuests.new(self)
	menus = Menus.new(self)

func _ready() -> void:
	var root := get_tree().root
	if st == null:
		st = root.get_node_or_null("S")
	if ui == null:
		ui = root.get_node_or_null("UI")
	if game == null:
		game = root.get_node_or_null("Game")
	if game and game.has_signal("tick25") and not game.tick25.is_connected(tick):
		game.tick25.connect(tick)
	if world == null:
		var w = get_tree().get_first_node_in_group("world")
		if w:
			attach(w)

func attach(w) -> void:
	world = w
	player = w.player if "player" in w else null
	if w.has_signal("map_loaded") and not w.map_loaded.is_connected(on_map_enter):
		w.map_loaded.connect(on_map_enter)

# ================================================================= public API
func is_cutscene_active() -> bool:
	return st != null and bool(st.fV)

func is_busy() -> bool:
	return is_cutscene_active() or _dialog_open > 0 or _menu_open

func talk_label() -> String:
	return str(quests.talk_target().get("label", ""))

func interact() -> void:
	if is_busy():
		return
	var t: Dictionary = quests.talk_target()
	match str(t.get("kind", "")):
		"npc":
			await talk(int(t["index"]))
		"girl":
			await quests.talk_girl()
		"classmate":
			await quests.talk_classmate(int(t["index"]))

func talk(npc_index: int) -> void:
	await quests.talk(npc_index)

func on_enemy_killed(type: int, pos := Vector2.ZERO) -> void:
	quests.on_enemy_killed(type, pos)

func on_player_attack(airborne: bool) -> void:
	quests.on_player_attack(airborne)

func game_menu() -> void:
	if is_busy():
		return
	_menu_open = true
	await menus.game_menu()
	_menu_open = false

func open_shop(kv: int) -> void:
	_menu_open = true
	await menus.shop(kv)
	_menu_open = false

func title_menu() -> String:
	return await menus.title_menu()

func quick_potion(hp: bool) -> bool:
	return menus.quick_potion(hp)

# ================================================================= 25 Hz tick (run() d==1 order)
func tick() -> void:
	if st == null:
		return
	if game and "mode" in game and str(game.mode) not in ["play", "cutscene"]:
		return
	_sync_player_in()
	cutscene.tick_triggers()          # ad()
	if cutscene.tick_sequence():      # V(): true = warp / title -> rest of the tick skipped
		return
	_apply_player_control()
	quests.tick()
	cutscene.tick_camera()
	_push_camera()

func _process(_dt: float) -> void:
	if not handle_input or st == null or is_busy():
		return
	if game and "mode" in game and str(game.mode) != "play":
		return
	if _just("talk"):
		interact()
	elif _just("menu"):
		game_menu()
	elif _just("hp_potion"):
		quick_potion(true)
	elif _just("mp_potion"):
		quick_potion(false)

func _just(action: String) -> bool:
	return InputMap.has_action(action) and Input.is_action_just_pressed(action)

# ================================================================= map entry
# Order of the original loader (Midlet.run L381): m(v) ... f(v) g(v) ... n(v) last.
func on_map_enter(id: int) -> void:
	if st == null:
		return
	var frame := Engine.get_process_frames()
	if _last_enter[0] == id and _last_enter[1] == frame:
		return                           # already handled this load (signal + manual call)
	_last_enter = [id, frame]
	if world and "player" in world and world.player:
		player = world.player
	_sync_player_in()
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
	log_event("enter %d fh=%d fi=%d" % [id, st.fh, st.fi])

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
	var data = get_tree().root.get_node_or_null("Data") if is_inside_tree() else null
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
		var data = get_tree().root.get_node_or_null("Data") if is_inside_tree() else null
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
	if world == null or not world.has_method("camera_override"):
		return
	if st.fx:
		world.camera_override(Vector2.ZERO, false)
	else:
		world.camera_override(Vector2(st.fL + st.bF, st.fM + st.bG), true)
	if snap:
		camera_snap()

func camera_snap() -> void:
	if world and world.has_method("camera_snap"):
		world.camera_snap()

func warp(map_id: int, spawn: Vector2) -> void:
	st.fh = int(spawn.x)
	st.fi = int(spawn.y)
	if game and game.has_method("goto_map") and "world" in game and game.world:
		game.goto_map(map_id, spawn)
	elif world and world.has_method("load_map"):
		world.load_map(map_id, spawn)
	else:
		on_map_enter(map_id)

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

# a(String[], cb, x, y) L4670 + its end in run() (L8246: M() paging then z())
func dialog(lines: Array, cb: int, anchor: Vector2) -> void:
	_dialog_open += 1
	st.iC = true
	st.iG = cb
	_apply_player_control()
	await say(lines, anchor)
	st.iC = false
	_dialog_open -= 1
	quests.dialog_end(cb)
	_apply_player_control()

func say(lines: Array, anchor: Vector2) -> void:
	var typed: Array[String] = []
	for l in lines:
		typed.append(str(l))
	log_event("say %s" % JSON.stringify(typed))
	if ui and ui.has_method("say"):
		await ui.say(typed, anchor)

func show_title_card(text: String, seconds: float) -> void:
	log_event("title %s" % text)
	if ui and ui.has_method("title_card"):
		ui.title_card(text, seconds)

# c(String) L4761: short notice line (HUD), 150 ticks
func notice(text: String) -> void:
	st.cr = text
	st.cq = 0
	st.cp = true
	log_event("note %s" % text)
	notice_shown.emit(text)
	if ui and ui.has_method("notice") and text != "":
		ui.notice(text)

# a(int,int,int,int) L4626: objective marker (map ge, position gf/gg, NPC gm)
func set_marker(map_id: int, x: int, y: int, npc: int) -> void:
	st.ge = map_id
	st.gf = x
	st.gg = y
	st.gm = npc
	marker_changed.emit(map_id, Vector2(x, y), npc)

func set_mode(m: String) -> void:
	if game and "mode" in game:
		game.mode = m

func state_changed() -> void:
	story_state_changed.emit()
	if st and st.has_signal("changed"):
		st.changed.emit()

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

# "Chỉ dẫn": original loads map 101 (not exported) and plays group 8 = sequence 16 (classmates'
# Q&A about the controls) then returns to the title (opcode 29). The remake plays its dialogue.
func play_tutorial() -> void:
	var ops: Array = st.az[16]
	var args: Array = st.aA[16]
	for k in ops.size():
		if int(ops[k]) == 4:
			await say(st.aG[int(args[k])], Vector2.ZERO)
	return_to_title.emit()
