# Story view: runs sim/story_sim.gd (the original story rules) and does everything that is not a rule:
# 25 Hz tick hookup (Game.tick25, world.map_loaded), input polling, UI (dialogue boxes, title cards,
# notices), menus (view/story_menus.gd), Game.mode, map warps and the camera override.
#
# Keeps the API of the old scripts/story/story.gd for main.gd, tests and player_sim:
#   attach(world), tick(), on_map_enter(id), interact(), talk(i), on_enemy_killed, on_player_attack,
#   is_cutscene_active(), is_busy(), talk_label(), game_menu(), open_shop(kv), title_menu(),
#   quick_potion(hp), new_game(), continue_game(), play_tutorial(); st / world / player / events /
#   notice_text / quests / cutscene / items forward to the sim.
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

const StorySim := preload("res://sim/story_sim.gd")
const Menus := preload("res://view/story_menus.gd")

var sim
var menus
var ui              # autoload UI (say / title_card / popup / choose [/ notice / hide_title_card])
var game            # autoload Game (mode, goto_map) - optional
var handle_input := false     # true: poll talk / menu / potion actions here (main.gd may do it)
var _menu_open := false

var st:
	get: return sim.st
	set(v): sim.st = v
var world:                     # view/world_view.gd (warps, camera); the sim reads its world_sim
	set(v):
		world = v
		sim.world = v.sim if v != null and "sim" in v else v
var player:
	get: return sim.player
	set(v): sim.player = v
var events: Array:
	get: return sim.events
var notice_text: String:
	get: return sim.notice_text
var verbose: bool:
	get: return sim.verbose
	set(v): sim.verbose = v
var quests:
	get: return sim.quests
var cutscene:
	get: return sim.cutscene
var items:
	get: return sim.items

func _init() -> void:
	sim = StorySim.new()
	sim.on_event = _on_event
	menus = Menus.new(self)

func _ready() -> void:
	var root := get_tree().root
	if st == null:
		st = root.get_node_or_null("S")
	if ui == null:
		ui = root.get_node_or_null("UI")
	if game == null:
		game = root.get_node_or_null("Game")
	if sim.data == null:
		sim.data = root.get_node_or_null("Data")
	if game and game.has_signal("tick25") and not game.tick25.is_connected(tick):
		game.tick25.connect(tick)
	var w = world if world != null else get_tree().get_first_node_in_group("world")
	if w:
		attach(w)

func attach(w) -> void:
	world = w
	player = w.player if "player" in w else null
	if w.has_signal("map_loaded") and not w.map_loaded.is_connected(on_map_enter):
		w.map_loaded.connect(on_map_enter)

# ================================================================= public API
func is_cutscene_active() -> bool:
	return sim.is_cutscene_active()

func is_busy() -> bool:
	return sim.is_cutscene_active() or sim.dialog_open() or _menu_open

func talk_label() -> String:
	return sim.talk_label()

func interact() -> void:
	if is_busy():
		return
	var r: Dictionary = sim.interact()
	if r.has("shop"):
		await menus.shop(int(r["shop"]))     # NPC shop: not counted as _menu_open (as before)

func talk(npc_index: int) -> void:
	var r: Dictionary = sim.quests.talk(npc_index)
	if r.has("shop"):
		await menus.shop(int(r["shop"]))

func on_enemy_killed(type: int, pos := Vector2.ZERO) -> void:
	sim.on_enemy_killed(type, pos)

func on_player_attack(airborne: bool) -> void:
	sim.on_player_attack(airborne)

func on_map_enter(id: int) -> void:
	sim.on_map_enter(id, Engine.get_process_frames())

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
	return sim.items.quick_potion(hp)

func new_game() -> void:
	sim.new_game()

func continue_game() -> bool:
	return sim.continue_game()

# "Chỉ dẫn": original loads map 101 (not exported) and plays group 8 = sequence 16 (classmates'
# Q&A about the controls) then returns to the title (opcode 29). The remake plays its dialogue.
func play_tutorial() -> void:
	var ops: Array = st.az[16]
	var args: Array = st.aA[16]
	for k in ops.size():
		if int(ops[k]) == 4:
			var typed: Array[String] = []
			for l in st.aG[int(args[k])]:
				typed.append(str(l))
			sim.log_event("say %s" % JSON.stringify(typed))
			if ui and ui.has_method("say"):
				await ui.say(typed, Vector2.ZERO)
	return_to_title.emit()

func set_mode(m: String) -> void:
	if game and "mode" in game:
		game.mode = m

# ================================================================= 25 Hz tick
func tick() -> void:
	if st == null:
		return
	# UI.say switches Game.mode to "dialogue": the original keeps running ad()/V()/camera then
	if game and "mode" in game and str(game.mode) not in ["play", "cutscene", "dialogue"]:
		return
	sim.tick()

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

# ================================================================= sim events (handled immediately)
func _on_event(ev: Dictionary) -> void:
	match str(ev["t"]):
		"say":
			_show_dialog(ev)
		"title":
			if ui and ui.has_method("title_card"):
				ui.title_card(ev["text"], ev["seconds"])
		"hide_title":
			if ui and ui.has_method("hide_title_card"):
				ui.hide_title_card()
		"notice":
			var text: String = ev["text"]
			notice_shown.emit(text)
			if ui and text != "":
				if ui.has_method("notice"):
					ui.notice(text)
				elif ui.has_method("popup"):
					ui.popup(text)          # non-blocking toast
		"marker":
			marker_changed.emit(ev["map"], ev["pos"], ev["npc"])
		"scroll":
			scroll_dropped.emit(ev["pos"])
		"weapon":
			weapon_changed.emit(ev["tier"])
		"changed":
			story_state_changed.emit()
			if st and st.has_signal("changed"):
				st.changed.emit()
		"cutscene_started":
			cutscene_started.emit(ev["group"], ev["seq"])
		"cutscene_finished":
			cutscene_finished.emit(ev["seq"])
		"to_title":
			return_to_title.emit()
		"mode":
			set_mode(ev["mode"])
		"warp":
			_warp(int(ev["map"]), ev["spawn"])
		"camera":
			if world and world.has_method("camera_override"):
				world.camera_override(ev["target"], ev["active"])
		"camera_snap":
			if world and world.has_method("camera_snap"):
				world.camera_snap()

# dialogue box: the sim's rules continue (z() / end of a cutscene line) when the box is closed
func _show_dialog(ev: Dictionary) -> void:
	if ui and ui.has_method("say"):
		await ui.say(ev["lines"], ev["anchor"])
	sim.close_dialog(int(ev["id"]))

func _warp(map_id: int, spawn: Vector2) -> void:
	if game and game.has_method("goto_map") and "world" in game and game.world:
		game.goto_map(map_id, spawn)
	elif world and world.has_method("load_map"):
		world.load_map(map_id, spawn)
	else:
		on_map_enter(map_id)
