# WorldSim: the rules of one map of the original game (no Node, no drawing, no await).
# Tile data/flags come from remake/data/maps (original q(int)): collision flags (2 floor, 4/8 walls, 0x40 water,
# 0x800 springy planks + 0x400 "pressed" marker, 0x1000 vines ...), the map exits jr/js/jt/ju and the per-map
# variables of the original. The camera is presentation (view/world_view.gd) but the rules of the original read
# it (monsters wake up on screen, death falls until off screen), so the view writes what it shows into cam_pos /
# cam_shown / view every frame. Things the view must do in the middle of a rule (reset the camera after the
# monsters are set up, the map_loaded signal, FX, popups) go out through on_event at the moment they happen.
extends RefCounted

const Objects := preload("res://sim/world/objects.gd")
const TILE := 24.0

var map_id := -1
var map := {}
var w := 0
var h := 0
var tiles: Array = []
var flags: Array = []
var exits := {}

var data = null               # Data autoload (map json, static_data), given by the view
var player = null             # player view (forwards to sim/player_sim.gd, flushes its events)
var enemies = null            # enemies view (same reason)
var story = null              # story view (player_sim._u() quest-11 counter); null in tests
var objects = Objects.new()   # push blocks / rafts / motorbikes
var on_event: Callable        # view: camera_reset, map_loaded, fx, popup

# what the view shows (written by view/world_view.gd every frame)
var cam_pos := Vector2.ZERO   # eased camera target (no shake)
var cam_shown := Vector2.ZERO # camera.position (with screen shake)
var view := Vector2(480, 270) # visible world size

func _init() -> void:
	objects.world = self

func _emit(ev: Dictionary) -> void:
	if on_event.is_valid():
		on_event.call(ev)

func view_size() -> Vector2:
	return view

# ---------------- map ----------------
func load_map(id: int, spawn: Vector2, facing := 0) -> void:
	map_id = id
	map = data.map(id)
	w = int(map["w"])
	h = int(map["h"])
	tiles = map["tiles"]
	flags = map["flags"].duplicate()
	exits = map["exits"]
	_apply_map_state(map.get("state", {}))
	_emit({"type": "decor", "id": id})
	S.v = id
	S.iP = w
	S.iQ = h
	S.iR = w * 24
	S.iS = h * 24
	if id < 19:
		S.W[id] = true
	if objects:
		objects.setup(id)
	if player:
		player.place(spawn, facing)
	if enemies:
		enemies.setup(map)
	_emit({"type": "camera_reset"})
	_emit({"type": "map_loaded", "id": id})

# per-map variables of the original after loading this map (monsters j*/k*, objects i*, NPCs lx..lA,
# boss g*/h*) - player / quest / progress variables are never taken from the dump
const MAP_VAR := "^(j[A-Za-z]|k[a-p]|i[a-zA-Z]|l[x-zA]|g[Q-Z]|h[b-d])$"
var _re: RegEx

func _apply_map_state(st: Dictionary) -> void:
	if _re == null:
		_re = RegEx.create_from_string(MAP_VAR)
	# keys missing from a map's dump hold their boot value: reset all map variables first
	var merged: Dictionary = {}
	for k in data.static_data:
		if _re.search(k) != null:
			merged[k] = data.static_data[k]
	merged.merge(st, true)
	for k in merged:
		if k in ["iP", "iQ", "iU", "ii"] or _re.search(k) == null:
			continue
		if k in S:
			var v = merged[k]
			S.set(k, v.duplicate(true) if v is Array else (int(v) if v is float else v))

# original S(): springy plank tiles (0x800) dip while stood on (0x400 marker, drawn +1 px)
func crumble_at(x: int, y: int) -> void:
	_mark(x - 24, y, false); _mark(x - 24, y - 3, false)
	_mark(x + 24, y, false); _mark(x + 24, y - 3, false)
	if flags_at(x, y) & 0x800:
		_mark(x, y, true); _mark(x, y - 3, true)

func _mark(x: int, y: int, on: bool) -> void:
	var tx := int(floor(x / TILE)); var ty := int(floor(y / TILE))
	if tx < 0 or ty < 0 or tx >= w or ty >= h:
		return
	var i := ty * w + tx
	flags[i] = (int(flags[i]) | 0x400) if on else (int(flags[i]) & ~0x400)

func flags_at(x: float, y: float) -> int:
	var tx := int(floor(x / TILE))
	var ty := int(floor(y / TILE))
	if tx < 0 or ty < 0 or tx >= w or ty >= h:
		return 0
	return int(flags[ty * w + tx])

func flag_tile(tx: int, ty: int) -> int:
	if tx < 0 or ty < 0 or tx >= w or ty >= h:
		return 0
	return int(flags[ty * w + tx])

func tile_id(tx: int, ty: int) -> int:
	if tx < 0 or ty < 0 or tx >= w or ty >= h:
		return 0
	return int(tiles[ty * w + tx])

func is_floor(x: float, y: float) -> bool:
	return (flags_at(x, y) & 2) == 2

func world_size() -> Vector2:
	return Vector2(w * TILE, h * TILE)

func set_flag_tile(tx: int, ty: int, f: int) -> void:
	if tx >= 0 and ty >= 0 and tx < w and ty < h:
		flags[ty * w + tx] = f

# ---------------- exits (original run() ~L8187) ----------------
# Which exit the player is leaving by: {} (none) or {"target": map, "spawn": Vector2}. The view checks this
# every frame (as before), fades out, then calls take_exit().
func exit_check(player_dead: bool) -> Dictionary:
	if player == null or player_dead:
		return {}
	var p: Vector2 = player.pos
	var target := -1
	var spawn := Vector2.ZERO
	if p.x < 0 and int(exits["jr"]) != -1:
		target = int(exits["jr"])
		spawn = Vector2(int(exits["jv"]) - 1, int(exits["jw"]))
	elif p.x >= w * TILE - 24 and int(exits["js"]) != -1:
		if not S.fc and int(exits["js"]) == 95:
			player.pos.x = w * TILE - 60
			_emit({"type": "popup", "text": "Khu vực phía trước rất nguy hiểm! Chỉ những người có thể ẩn thân có thể vào!"})
			return {}
		if int(exits["jt"]) != -1 and p.y > int(exits["jD"]):
			target = int(exits["jt"])
			spawn = Vector2(int(exits["jz"]), int(exits["jA"]))
		else:
			target = int(exits["js"])
			spawn = Vector2(int(exits["jx"]), int(exits["jy"]))
	elif p.y >= h * TILE - 4 and int(exits["ju"]) != -1:
		target = int(exits["ju"])
		spawn = Vector2(int(exits["jB"]), int(exits["jC"]))
	if target == -1:
		return {}
	return {"target": target, "spawn": spawn}

func take_exit(target: int, spawn: Vector2) -> void:
	S.fh = int(spawn.x)
	S.fi = int(spawn.y)
	load_map(target, spawn)
	S.save_game()       # original autosaves on map transitions (Midlet.f)
