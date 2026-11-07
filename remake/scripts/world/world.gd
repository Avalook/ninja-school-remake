# World: one map of the original game, drawn with HD art and simulated per frame.
# Tile data/flags come from remake/data/maps (original q(int)); drawing rules follow the original
# j/k/l(Graphics) layers (back parallax tiles 0x10, main tiles, front bushes 0x100 / tiles 0x200 /
# vines 0x1000, animated water 0x20 / 0x40).
extends Node2D

signal map_loaded(id: int)

const TILE := 24.0
const VIEW := Vector2(480, 270)

var map_id := -1
var map := {}
var w := 0
var h := 0
var tiles: Array = []
var flags: Array = []
var exits := {}

var player
var enemies
var camera: Camera2D
var cam_pos := Vector2.ZERO
var cam_override_active := false
var cam_override_target := Vector2.ZERO

var tex_tiles: Texture2D
var tex_wtf: Texture2D
var tex_wts: Texture2D
var tex_tb: Texture2D
var layer_back: Node2D
var layer_front: Node2D
var layer_bg: Node2D
var layer_actors: Node2D
var layer_fx: Node2D
var npc_tex := {}
var fx: Array = []
var texts: Array = []

func _ready() -> void:
	add_to_group("world")
	Game.world = self
	tex_tiles = load("res://art/tiles/t.png")
	tex_wtf = load("res://art/tiles/wtf.png")
	tex_wts = load("res://art/tiles/wts.png")
	tex_tb = load("res://art/tiles/tb.png")
	layer_bg = preload("res://scripts/world/background.gd").new()
	layer_bg.world = self
	add_child(layer_bg)
	layer_back = _layer("back")
	layer_actors = Node2D.new()
	layer_actors.name = "Actors"
	add_child(layer_actors)
	layer_front = _layer("front")
	layer_fx = Node2D.new()
	layer_fx.name = "FX"
	layer_fx.set_script(preload("res://scripts/fx/fx_layer.gd"))
	add_child(layer_fx)
	layer_fx.world = self
	camera = Camera2D.new()
	camera.anchor_mode = Camera2D.ANCHOR_MODE_FIXED_TOP_LEFT
	add_child(camera)
	camera.make_current()
	get_viewport().size_changed.connect(_fit_camera)
	_fit_camera()

func _layer(which: String) -> Node2D:
	var n := Node2D.new()
	n.set_script(preload("res://scripts/world/tile_layer.gd"))
	n.world = self
	n.which = which
	n.name = "Tiles_" + which
	add_child(n)
	return n

func _fit_camera() -> void:
	var vs := get_viewport_rect().size
	var z: float = vs.y / VIEW.y
	camera.zoom = Vector2(z, z)

func view_size() -> Vector2:
	return get_viewport_rect().size / camera.zoom

# ---------------- map ----------------
func load_map(id: int, spawn: Vector2, facing := 0) -> void:
	map_id = id
	map = Data.map(id)
	w = int(map["w"])
	h = int(map["h"])
	tiles = map["tiles"]
	flags = map["flags"].duplicate()
	exits = map["exits"]
	_apply_map_state(map.get("state", {}))
	S.v = id
	S.iP = w
	S.iQ = h
	S.iR = w * 24
	S.iS = h * 24
	if id < 19:
		S.W[id] = true
	if player:
		player.place(spawn, facing)
	if enemies:
		enemies.setup(map)
	cam_pos = _camera_target(true)
	camera.position = cam_pos
	map_loaded.emit(id)

# per-map variables of the original after loading this map (monsters j*/k*, objects i*, NPCs lx..lA,
# boss g*/h*) - player / quest / progress variables are never taken from the dump
const MAP_VAR := "^(j[A-Za-z]|k[a-p]|i[a-zA-Z]|l[x-zA]|g[Q-Z]|h[b-d])$"
var _re: RegEx

func _apply_map_state(st: Dictionary) -> void:
	if _re == null:
		_re = RegEx.create_from_string(MAP_VAR)
	for k in st:
		if k in ["iP", "iQ", "iU", "ii"] or _re.search(k) == null:
			continue
		if k in S:
			var v = st[k]
			S.set(k, v.duplicate(true) if v is Array else (int(v) if v is float else v))

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

# ---------------- camera ----------------
# original framing (h() ~L3185): target x = px - q/2 + bI*facing, y = py - 3r/4, clamped to
# [0, (w-1)*24 - q] x [0, (h-1)*24 - r]  (q = view width, r = view height, bI = q/6)
func _camera_target(snap := false) -> Vector2:
	var vs := view_size()
	var t := Vector2.ZERO
	if cam_override_active:
		t = cam_override_target - vs * Vector2(0.5, 0.5)
	elif player:
		t = Vector2(player.pos.x - vs.x * 0.5 + vs.x / 6.0 * player.facing, player.pos.y - vs.y * 0.75)
	var maxx: float = max(0.0, (w - 1) * TILE - vs.x)
	var maxy: float = max(0.0, (h - 1) * TILE - vs.y)
	return Vector2(clampf(t.x, 0.0, maxx), clampf(t.y, 0.0, maxy))

func camera_override(target: Vector2, active: bool) -> void:
	cam_override_active = active
	cam_override_target = target

func _process(delta: float) -> void:
	if map_id < 0:
		return
	var t := _camera_target()
	var tau := 0.14 if not cam_override_active else 0.35
	if (t - cam_pos).length() > 600.0:
		cam_pos = t
	else:
		cam_pos += (t - cam_pos) * (1.0 - exp(-delta / tau))
	if shake > 0.0:
		shake = max(0.0, shake - delta)
	var sh := Vector2(randf_range(-1, 1), randf_range(-1, 1)) * shake * 20.0
	camera.position = cam_pos + sh
	layer_back.queue_redraw()
	layer_front.queue_redraw()
	layer_bg.queue_redraw()
	_check_exits()

var shake := 0.0

func screen_shake(amount := 0.12) -> void:
	shake = max(shake, amount)

# ---------------- exits (original run() ~L8187) ----------------
var _transition := false

func _check_exits() -> void:
	if player == null or _transition or player.state in ["dead"]:
		return
	var p: Vector2 = player.pos
	var target := -1
	var spawn := Vector2.ZERO
	if p.x < 0 and int(exits["jr"]) != -1:
		target = int(exits["jr"])
		spawn = Vector2(int(exits["jv"]) - 1, int(exits["jw"]))
	elif p.x >= w * TILE - 24 and int(exits["js"]) != -1:
		if not S.fc and int(exits["js"]) == 95:
			player.pos.x = w * TILE - 60
			UI.popup("Khu vực phía trước rất nguy hiểm! Chỉ những người có thể ẩn thân có thể vào!")
			return
		if int(exits["jt"]) != -1 and p.y > int(exits["jD"]):
			target = int(exits["jt"])
			spawn = Vector2(int(exits["jz"]), int(exits["jA"]))
		else:
			target = int(exits["js"])
			spawn = Vector2(int(exits["jx"]), int(exits["jy"]))
	elif p.y >= h * TILE - 4 and int(exits["ju"]) != -1:
		target = int(exits["ju"])
		spawn = Vector2(int(exits["jB"]), int(exits["jC"]))
	if target != -1:
		_transition = true
		await UI.fade_out(0.25)
		S.fh = int(spawn.x)
		S.fi = int(spawn.y)
		load_map(target, spawn)
		S.save_game()       # original autosaves on map transitions (Midlet.f)
		await UI.fade_in(0.25)
		_transition = false

# ---------------- fx helpers (contract) ----------------
func spawn_fx(kind: String, pos: Vector2, dir := 1) -> void:
	layer_fx.spawn(kind, pos, dir)

func float_text(text: String, pos: Vector2, color := Color(1, 0.3, 0.3)) -> void:
	layer_fx.text(text, pos, color)
