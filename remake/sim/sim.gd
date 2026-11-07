# Sim: the whole rule state of a running game, one object. Owns the four module sims and wires them to each
# other directly (no view in between):
#   world   sim/world_sim.gd   (map, collision flags, exits, objects)
#   player  sim/player_sim.gd
#   enemies sim/enemies_sim.gd (monsters, bosses, rival students, projectiles, loot)
#   story   sim/story_sim.gd   (cutscenes, quests, items)
# Rule variables of the original live in the state autoload S (scripts/core/state.gd), as before.
#
# step(input) runs ONE original 25 Hz tick in the order the game always had (it used to come from the order
# in which nodes connected Game.tick25): S.w, objects, story, projectiles, player R()/timers, monsters/bosses/
# students/loot. frame(dt, input) runs the native locomotion of the player (A = 1..4), which still follows the
# display frame (until the ROADMAP item "100 Hz").
#
# input = {"keys": {left, right, jump, down, attack, jump_left, jump_right, weapon, stealth, transform, hp, mp},
#          "mode": "play"|..., "camera": {"pos": Vector2, "shown": Vector2, "view": Vector2}, "alpha": float}
#   keys    what the player module reads (raw_key); the view builds it where it used to read Input.
#   mode    the game mode (owned by the view, Game.mode). It can change in the middle of a tick (the story
#           asks for "mode", a dialogue box opens), and later steps of the same tick read the new value, so
#           the view also calls set_mode() whenever Game.mode changes; the sim never calls Game.
#   camera  what the view shows; rules of the original read it (monsters wake up on screen, death fall,
#           projectiles leave the screen). The view also pushes it with set_camera() at the moments a rule
#           needs a camera that was just reset (map load, cutscene snap).
#
# Events: every presentation request of every module goes to on_event(ev) at the moment it happens, with
# ev["src"] = "world" | "player" | "enemies" | "story" (player events leave at the end of each player call,
# as the old PlayerView drained them). Without a handler for a source they are collected in `events`.
# Rule events the sim handles itself: story "warp" (load the map), story "mode", world "map_loaded"
# (story.on_map_enter first, then the view), enemies.enemy_killed -> story.on_enemy_killed.
#
# snapshot() / restore(d): all rule state as plain data (int, bool, String, float, Array, Dictionary, Vector2,
# tagged so JSON keeps the types): S, every module sim and every object they own, the player's native
# locomotion state (pos, vel, coyote, buffer, air_jumps, ...), the mode and the camera the rules read.
# Floats and Vector2 carry their exact bits ("#x", hex of var_to_bytes) next to the readable value.
# Not included: anything of the view (smoothed camera springs, FX, open dialogue boxes, music), the clock.
extends RefCounted

const WorldSim := preload("res://sim/world_sim.gd")
const PlayerSim := preload("res://sim/player_sim.gd")
const EnemiesSim := preload("res://sim/enemies_sim.gd")
const StorySim := preload("res://sim/story_sim.gd")
const Objects := preload("res://sim/world/objects.gd")
const Enemy := preload("res://sim/enemies/enemy.gd")
const Shots := preload("res://sim/enemies/projectiles.gd")
const Drops := preload("res://sim/enemies/drops.gd")
const Classmates := preload("res://sim/enemies/classmates.gd")
const Boar := preload("res://sim/enemies/boss_boar.gd")
const River := preload("res://sim/enemies/boss_river.gd")
const Golem := preload("res://sim/enemies/boss_golem.gd")
const Cutscene := preload("res://sim/story/cutscene.gd")
const Quests := preload("res://sim/story/quests.gd")
const Items := preload("res://sim/story/items.gd")

const KEYS := ["left", "right", "jump", "down", "attack", "jump_left", "jump_right", "weapon", "stealth",
	"transform", "hp", "mp"]

var st                         # state (autoload S)
var data                       # content (autoload Data)
var world
var player
var enemies
var story
var mode := "title"
var keys := {}
var alpha := 0.0
var handlers := {}             # src -> Callable(ev) (the views)
var events: Array = []         # events with no handler (headless)
var _load_seq := 0
var has_story := true          # main.gd --nostory / --noenemies (debug): the module is not run at all
var has_enemies := true

# Pass the module sims the views already made (the game), or nothing (headless: fresh ones).
func _init(state, content, w = null, p = null, e = null, s = null) -> void:
	st = state
	data = content
	world = w if w != null else WorldSim.new()
	player = p if p != null else PlayerSim.new()
	enemies = e if e != null else EnemiesSim.new()
	story = s if s != null else StorySim.new()
	for src in ["world", "player", "enemies", "story"]:
		var m = get(src)
		if m.on_event.is_valid():
			handlers[src] = m.on_event      # the view's handler, kept
		m.on_event = _route.bind(src)
	world.data = content
	world.player = player
	world.enemies = enemies
	world.story = story
	player.world = world
	player.raw_key = func(k: String) -> bool: return bool(keys.get(k, false))
	player.is_play = func() -> bool: return mode == "play"
	enemies.world = world
	story.st = state
	story.data = content
	story.world = world
	story.player = player
	enemies.enemy_killed.connect(_on_enemy_killed)

func _on_enemy_killed(type: int, pos: Vector2) -> void:
	if has_story:
		story.on_enemy_killed(type, pos)

# debug runs without a module (main.gd --nostory / --noenemies), as before: nothing calls it
func disable(module: String) -> void:
	if module == "enemies":
		has_enemies = false
		world.enemies = null
	elif module == "story":
		has_story = false
		world.story = null

# ---------------------------------------------------------------- events
func _route(ev: Dictionary, src: String) -> void:
	var t = ev.get("t", ev.get("type", ""))
	if src == "story" and t == "warp":
		world.load_map(int(ev["map"]), ev["spawn"])
		return
	if src == "story" and t == "mode":
		mode = str(ev["mode"])
	if src == "world" and t == "map_loaded" and has_story:
		_load_seq += 1
		story.on_map_enter(int(ev["id"]), _load_seq)
	ev["src"] = src
	if handlers.has(src):
		handlers[src].call(ev)
	else:
		events.append(ev)

# ---------------------------------------------------------------- input
func set_mode(m: String) -> void:
	mode = m

func set_camera(cam: Dictionary) -> void:
	world.cam_pos = cam.get("pos", world.cam_pos)
	world.cam_shown = cam.get("shown", world.cam_shown)
	world.view = cam.get("view", world.view)

func _take(input: Dictionary) -> void:
	if input.has("keys"):
		keys = input["keys"]
	if input.has("mode"):
		mode = str(input["mode"])
	if input.has("camera"):
		set_camera(input["camera"])
	if input.has("alpha"):
		alpha = float(input["alpha"])

# ---------------------------------------------------------------- run
# one original 25 Hz tick; returns the events with no handler collected during it (headless)
func step(input := {}) -> Array:
	_take(input)
	var n0 := events.size()
	st.w = (int(st.w) + 1) % 10000
	world.objects.tick(mode == "play")
	if has_story and mode in ["play", "cutscene", "dialogue"]:
		story.tick()
	if has_enemies and mode in ["play", "cutscene"]:
		enemies.step_projectiles()
	player.tick()
	if has_enemies and mode in ["play", "cutscene"]:
		enemies.step_actors()
	return events.slice(n0)

# one display frame of the native locomotion
func frame(dt: float, input := {}) -> Array:
	_take(input)
	var n0 := events.size()
	player.frame(dt, alpha)
	return events.slice(n0)

func load_map(id: int, spawn: Vector2, facing := 0) -> void:
	world.load_map(id, spawn, facing)

# ---------------------------------------------------------------- snapshot / restore
# owned object kinds (for objects created again on restore, e.g. a map with more monsters)
var _kinds := {}
func _kind_table() -> Dictionary:
	if _kinds.is_empty():
		_kinds = {"Enemy": Enemy, "Shot": Shots.Shot, "Drop": Drops.Drop, "Shots": Shots, "Drops": Drops,
			"Classmates": Classmates, "Boar": Boar, "River": River, "Golem": Golem, "Objects": Objects,
			"Cutscene": Cutscene, "Quests": Quests, "Items": Items}
	return _kinds

func _kind_of(o: Object) -> String:
	var sc = o.get_script()
	for k in _kind_table():
		if _kinds[k] == sc:
			return k
	return ""

# per root: derived data reloaded from Data after restore, or queues that are empty between two calls
const SKIP := {
	"world": ["map", "tiles", "exits", "data", "on_event", "_re"],   # _re: compiled RegEx cache
	"player": ["events", "raw_key", "is_play", "on_event"],
	"enemies": ["map", "_flags", "events", "on_event", "test_player"],
	"story": ["out", "_static", "data", "st", "on_event"],
}

func _is_ref(v) -> bool:
	if not (v is Object):
		return false
	if v == null or v is Node:
		return true
	return v == world or v == player or v == enemies or v == story or v == self

func _script_props(o: Object) -> Array:
	var out := []
	for p in o.get_property_list():
		if p["usage"] & PROPERTY_USAGE_SCRIPT_VARIABLE:
			out.append(p["name"])
	return out

func _enc(v):
	if v == null:
		return null
	match typeof(v):
		TYPE_BOOL, TYPE_INT, TYPE_STRING:
			return v
		TYPE_STRING_NAME:
			return String(v)
		TYPE_FLOAT:            # exact bits (JSON text loses the last bit), plus the value to read
			return {"#f": v, "#x": var_to_bytes(v).hex_encode()}
		TYPE_VECTOR2:
			return {"#v": [v.x, v.y], "#x": var_to_bytes(v).hex_encode()}
		TYPE_ARRAY:
			var a := []
			for x in v:
				a.append(_enc(x))
			return a
		TYPE_DICTIONARY:
			var kv := []
			for k in v:
				kv.append([_enc(k), _enc(v[k])])
			return {"#d": kv}
		TYPE_OBJECT:
			var k := _kind_of(v)
			if k == "":
				push_error("Sim.snapshot: unknown object %s" % v)
				return null
			return {"#o": k, "p": _enc_obj(v, [])}
	return {"#s": var_to_str(v)}

func _enc_obj(o: Object, skip: Array) -> Dictionary:
	var d := {}
	for n in _script_props(o):
		if n in skip:
			continue
		var v = o.get(n)
		if v is Callable or v is Signal or _is_ref(v):
			continue
		d[n] = _enc(v)
	return d

func _dec(v, cur = null):
	if v is Array:
		var a := []
		for i in v.size():
			a.append(_dec(v[i], cur[i] if cur is Array and i < cur.size() else null))
		return a
	if v is Dictionary:
		if v.has("#x"): return bytes_to_var(str(v["#x"]).hex_decode())
		if v.has("#s"): return str_to_var(v["#s"])
		if v.has("#d"):
			var d := {}
			for kv in v["#d"]:
				d[_dec(kv[0])] = _dec(kv[1])
			return d
		if v.has("#o"):
			var o = cur if cur is Object and _kind_of(cur) == v["#o"] else _kind_table()[v["#o"]].new()
			_dec_obj(o, v["p"])
			return o
	if v is float:
		return int(v)          # untagged JSON numbers are ints
	return v

func _dec_obj(o: Object, d: Dictionary) -> void:
	for n in d:
		o.set(n, _dec(d[n], o.get(n)))

func snapshot() -> Dictionary:
	var s := {"S": {}, "mode": mode, "keys": _enc(keys), "alpha": _enc(alpha), "load_seq": _load_seq}
	for n in _script_props(st):
		var v = st.get(n)
		if v is Callable or v is Signal or v is Object:
			continue
		s["S"][n] = _enc(v)
	for src in SKIP:
		s[src] = _enc_obj(get(src), SKIP[src])
	return s

func restore(s: Dictionary) -> void:
	for n in s["S"]:
		st.set(n, _dec(s["S"][n], st.get(n)))
	mode = str(s["mode"])
	keys = _dec(s["keys"])
	alpha = _dec(s["alpha"])
	_load_seq = int(s["load_seq"])
	for src in SKIP:
		_dec_obj(get(src), s[src])
	# derived data shared with Data (same dictionaries as after load_map)
	if int(world.map_id) >= 0:
		world.map = data.map(int(world.map_id))
		world.tiles = world.map["tiles"]
		world.exits = world.map["exits"]
		enemies.map = world.map
		enemies._flags = world.map.get("flags", [])
	else:
		world.map = {}
		enemies.map = {}
		enemies._flags = []
