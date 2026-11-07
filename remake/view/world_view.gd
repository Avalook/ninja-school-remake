# WorldView: draws one map with HD art (sim/world_sim.gd holds the rules). Drawing rules follow the original
# j/k/l(Graphics) layers (back parallax tiles 0x10, main tiles, front bushes 0x100 / tiles 0x200 /
# vines 0x1000, animated water 0x20 / 0x40). Owns the camera (eased follow, look-ahead, shake, story
# override), the FX layer and the fade on map transitions. No game rule lives here.
# Keeps the old World API (load_map, map_id, map, w, h, tiles, flags, exits, flags_at, is_floor, camera_snap,
# camera_override, spawn_fx, ...) for main / story / tests / the other views by forwarding to the sim.
extends Node2D

signal map_loaded(id: int)

const WorldSim := preload("res://sim/world_sim.gd")
const TILE := 24.0
const VIEW := Vector2(480, 270)

var sim = WorldSim.new()
var external_tick := false     # main.gd: sim/sim.gd step() runs the object rules (objects_view does not tick)

var map_id: int:
	get: return sim.map_id
var map: Dictionary:
	get: return sim.map
var w: int:
	get: return sim.w
var h: int:
	get: return sim.h
var tiles: Array:
	get: return sim.tiles
var flags: Array:
	get: return sim.flags
var exits: Dictionary:
	get: return sim.exits
var player = null:
	set(v):
		player = v
		sim.player = v
var enemies = null:
	set(v):
		enemies = v
		sim.enemies = v
var story = null:
	set(v):
		story = v
		sim.story = v
var objects                    # view/world/objects_view.gd (draws blocks / bikes; the rules are sim.objects)
var camera: Camera2D
var cam_pos := Vector2.ZERO     # eased camera target (no shake); the rules get it as input (camera_input)
var cam_shown := Vector2.ZERO   # camera.position (with shake)
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

func _init() -> void:
	sim.on_event = _on_event

func _ready() -> void:
	add_to_group("world")
	Game.world = self
	sim.data = Data
	tex_tiles = load("res://art/tiles/t.png")
	tex_wtf = load("res://art/tiles/wtf.png")
	tex_wts = load("res://art/tiles/wts.png")
	tex_tb = load("res://art/tiles/tb.png")
	layer_bg = preload("res://view/world/background.gd").new()
	layer_bg.world = self
	add_child(layer_bg)
	layer_back = _layer("back")
	layer_actors = Node2D.new()
	layer_actors.name = "Actors"
	add_child(layer_actors)
	objects = preload("res://view/world/objects_view.gd").new()
	objects.world = self
	layer_actors.add_child(objects)
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
	n.set_script(preload("res://view/world/tile_layer.gd"))
	n.world = self
	n.which = which
	n.name = "Tiles_" + which
	add_child(n)
	return n

func _fit_camera() -> void:
	var vs := get_viewport_rect().size
	var z: float = vs.y / VIEW.y
	camera.zoom = Vector2(z, z)
	_push_camera()

func view_size() -> Vector2:
	return get_viewport_rect().size / camera.zoom

func _set_camera(p: Vector2) -> void:
	camera.position = p
	cam_shown = p

# What the rules of the original read of the camera (monsters wake up on screen, death fall, shots leave the
# screen): main.gd / player_view.gd pass it in the input of sim.step() / sim.frame(). _push_camera() hands it
# over right away at the moments a rule needs a camera that was just reset (map load, cutscene snap).
func camera_input() -> Dictionary:
	return {"pos": cam_pos, "shown": cam_shown, "view": view_size()}

func _push_camera() -> void:
	sim.cam_pos = cam_pos
	sim.cam_shown = cam_shown
	sim.view = view_size()

# ---------------- map (rules in the sim) ----------------
func load_map(id: int, spawn: Vector2, facing := 0) -> void:
	_push_camera()
	sim.load_map(id, spawn, facing)

func flags_at(x: float, y: float) -> int:
	return sim.flags_at(x, y)

func flag_tile(tx: int, ty: int) -> int:
	return sim.flag_tile(tx, ty)

func tile_id(tx: int, ty: int) -> int:
	return sim.tile_id(tx, ty)

func is_floor(x: float, y: float) -> bool:
	return sim.is_floor(x, y)

func world_size() -> Vector2:
	return sim.world_size()

func set_flag_tile(tx: int, ty: int, f: int) -> void:
	sim.set_flag_tile(tx, ty, f)

func crumble_at(x: int, y: int) -> void:
	sim.crumble_at(x, y)

# events of the sim, handled at the moment they happen (same order as the old single-node World)
func _on_event(ev: Dictionary) -> void:
	match ev["type"]:
		"decor":
			_decor_setup(ev["id"])
		"camera_reset":
			if player:
				lead = view_size().x / 6.0 * player.facing
			lead_v[0] = 0.0
			cam_vx[0] = 0.0
			cam_vy[0] = 0.0
			cam_pos = _camera_target(true)
			_set_camera(cam_pos)
			_push_camera()
		"map_loaded":
			map_loaded.emit(ev["id"])
		"fx":
			spawn_fx(ev["kind"], ev["p"], ev["d"])
		"popup":
			UI.popup(ev["text"])

# big decor houses (original p(n): only maps 2 and 16), [image, tile x, tile y]; drawn behind the tiles
# with the 22/24 parallax, anchored bottom-centre at (tx*24+12, ty*24)
const DECOR := {2: [[0, 29, 13], [1, 34, 13], [2, 39, 13], [1, 46, 11], [2, 51, 11]],
	16: [[0, 5, 11], [1, 14, 12], [2, 33, 10], [1, 53, 10], [0, 59, 11]]}
var decor: Array = []
var tex_h: Array = []

func _decor_setup(id: int) -> void:
	decor = []
	if tex_h.is_empty():
		for i in 3:
			tex_h.append(load("res://art/tiles/h%d.png" % i))
	for d in DECOR.get(id, []):
		decor.append({"img": d[0], "x": d[1] * 24 + 12, "y": d[2] * 24})

# ---------------- camera ----------------
# original framing (h() ~L3185): target x = px - q/2 + bI*facing, y = py - 3r/4, clamped to
# [0, (w-1)*24 - q] x [0, (h-1)*24 - r]  (q = view width, r = view height, bI = q/6)
func _camera_target(snap := false) -> Vector2:
	var vs := view_size()
	var t := Vector2.ZERO
	if cam_override_active:
		t = cam_override_target - vs * Vector2(0.5, 0.5)
	elif player:
		t = Vector2(player.pos.x - vs.x * 0.5 + lead, player.pos.y - vs.y * 0.75)
	var maxx: float = max(0.0, (w - 1) * TILE - vs.x)
	var maxy: float = max(0.0, (h - 1) * TILE - vs.y)
	return Vector2(clampf(t.x, 0.0, maxx), clampf(t.y, 0.0, maxy))

func camera_snap() -> void:
	cam_pos = _camera_target(true)
	cam_vx[0] = 0.0
	cam_vy[0] = 0.0
	_set_camera(cam_pos)
	_push_camera()

func camera_override(target: Vector2, active: bool) -> void:
	cam_override_active = active
	cam_override_target = target

func _process(delta: float) -> void:
	if map_id < 0:
		return
	# look-ahead in the facing direction (original bI * fp), eased so turning never snaps
	if player:
		var want_lead: float = view_size().x / 6.0 * player.facing
		lead = _damp(lead, want_lead, lead_v, 0.45, delta)
	var t := _camera_target()
	var tau := 0.16 if not cam_override_active else 0.4
	if (t - cam_pos).length() > 600.0:
		cam_pos = t
		cam_v = Vector2.ZERO
	else:
		var nx := _damp(cam_pos.x, t.x, cam_vx, tau, delta)
		var ny := _damp(cam_pos.y, t.y, cam_vy, tau * 1.6, delta)
		cam_pos = Vector2(nx, ny)
	if shake > 0.0:
		shake = max(0.0, shake - delta)
	var sh := Vector2(randf_range(-1, 1), randf_range(-1, 1)) * shake * 20.0
	_set_camera(cam_pos + sh)
	layer_back.queue_redraw()
	layer_front.queue_redraw()
	layer_bg.queue_redraw()
	_check_exits()

var shake := 0.0
var lead := 0.0
var lead_v := [0.0]
var cam_vx := [0.0]
var cam_vy := [0.0]
var cam_v := Vector2.ZERO

# critically damped spring (Unity SmoothDamp): continuous position AND velocity
func _damp(cur: float, target: float, vel: Array, smooth: float, dt: float) -> float:
	var omega: float = 2.0 / maxf(0.0001, smooth)
	var x: float = omega * dt
	var e: float = 1.0 / (1.0 + x + 0.48 * x * x + 0.235 * x * x * x)
	var change := cur - target
	var temp: float = (vel[0] + omega * change) * dt
	vel[0] = (vel[0] - omega * temp) * e
	return target + (change + temp) * e

func screen_shake(amount := 0.12) -> void:
	shake = max(shake, amount)

# ---------------- exits: rule in sim.exit_check / take_exit, fade here ----------------
var _transition := false

func _check_exits() -> void:
	if player == null or _transition:
		return
	var ex: Dictionary = sim.exit_check(player.state in ["dead"])
	if ex.is_empty():
		return
	_transition = true
	await UI.fade_out(0.25)
	_push_camera()
	sim.take_exit(ex["target"], ex["spawn"])
	await UI.fade_in(0.25)
	_transition = false

# ---------------- fx helpers (contract) ----------------
func spawn_fx(kind: String, pos: Vector2, dir := 1) -> void:
	layer_fx.spawn(kind, pos, dir)

func float_text(text: String, pos: Vector2, color := Color(1, 0.3, 0.3)) -> void:
	layer_fx.text(text, pos, color)
