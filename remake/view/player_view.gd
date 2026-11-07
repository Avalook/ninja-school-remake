# PlayerView: the player's Node2D. Draws the hero (view/hero_anim.gd) and the bike, feeds input to the
# rules (sim/player_sim.gd) and turns the sim's events into FX / UI. No game rule lives here.
# Keeps the old player.gd API (pos, vel, facing, grounded, scripted, script_keys, near_npc, state, place(),
# hurt(), tick(), carry(), is_standing(), ...) by forwarding to the sim, so main / story / world / objects /
# enemies / cutscene did not change.
extends Node2D

const PlayerSim := preload("res://sim/player_sim.gd")

var sim = PlayerSim.new()
var state := "idle"            # animation state name (story reads it)
var anim
var external_tick := false     # main drives the order: projectiles -> player -> monsters (original run())

var world:
	get: return sim.world
	set(v): sim.world = v
var pos: Vector2:
	get: return sim.pos
	set(v): sim.pos = v
var vel: Vector2:
	get: return sim.vel
	set(v): sim.vel = v
var facing: int:
	get: return sim.facing
	set(v): sim.facing = v
var grounded: bool:
	get: return sim.grounded
	set(v): sim.grounded = v
var scripted: bool:
	get: return sim.scripted
	set(v): sim.scripted = v
var script_keys: Dictionary:
	get: return sim.script_keys
	set(v): sim.script_keys = v
var near_npc: int:
	get: return sim.near_npc
	set(v): sim.near_npc = v
var run_phase: float:
	get: return sim.run_phase
var land_t: float:
	get: return sim.land_t

func _init() -> void:
	sim.raw_key = _raw
	sim.is_play = func() -> bool: return Game.mode == "play"

func _ready() -> void:
	if not external_tick:
		Game.tick25.connect(tick)
	anim = preload("res://view/hero_anim.gd").new()
	anim.preload_all()
	z_index = 5

func _raw(k: String) -> bool:
	match k:
		"left": return Input.is_action_pressed("move_left")
		"right": return Input.is_action_pressed("move_right")
		"jump": return Input.is_action_pressed("jump")
		"down": return Input.is_action_pressed("down")
		"attack": return Input.is_action_pressed("attack")
		"jump_left": return Input.is_action_pressed("jump_left")
		"jump_right": return Input.is_action_pressed("jump_right")
		"weapon": return Input.is_action_pressed("weapon")
		"stealth": return Input.is_action_pressed("stealth")
		"transform": return Input.is_action_pressed("transform")
		"hp": return Input.is_action_pressed("hp_potion")
		"mp": return Input.is_action_pressed("mp_potion")
	return false

# ---------------- forwarded rule calls (each drains the sim events right away, keeping the old order) ----------------
func place(p: Vector2, f := 0) -> void:
	sim.place(p, f)
	_drain()

func hurt(n: int, dir: int) -> void:
	sim.hurt(n, dir)
	_drain()

func tick() -> void:
	sim.tick()
	_drain()

func carry(d: Vector2, y: float) -> void:
	sim.carry(d, y)
	_drain()

func is_standing() -> bool:
	return sim.is_standing()

func is_native() -> bool:
	return sim.is_native()

func hitbox() -> Rect2:
	return sim.hitbox()

func attack_box() -> Rect2:
	return sim.attack_box()

func _drain() -> void:
	if sim.events.is_empty():
		return
	var evs: Array = sim.events
	sim.events = []
	for e in evs:
		match e["t"]:
			"fx": world.spawn_fx(e["kind"], e["p"], e["dir"])
			"run_dust":
				if randf() < 0.25:
					world.spawn_fx("dust", e["p"], e["dir"])
			"text": world.float_text(e["s"], e["p"], e["c"])
			"shake": world.screen_shake(e["v"])
			"hitstop": Game.hitstop(e["v"])
			"message": UI.message(e["s"])
			"changed": S.changed.emit()

# ---------------- per frame ----------------
func _process(dt: float) -> void:
	if world == null or world.map_id < 0:
		return
	sim.frame(dt, clampf(Game.acc / 0.04, 0.0, 1.0))
	_drain()
	state = _state_name()
	anim.update(self, dt)
	queue_redraw()

func _state_name() -> String:
	match int(S.A):
		1: return "idle"
		2: return "run"
		3: return "jump"
		4: return "fall"
		5: return "attack"
		6: return "dead"
		7: return "hurt"
		8: return "crouch"
		11: return "swim"
		12: return "sink"
		13, 14: return "dig"
		15: return "jump"
		16: return "run"
		17: return "idle"
		18, 19, 20, 21: return "bike"
		23: return "power"
	return "idle"

func _draw() -> void:
	if int(S.im) != -1 and int(S.A) in [18, 19, 20, 21] and world.objects:
		world.objects.draw_bike(self, pos, facing, int(S.iq[S.im]) if S.im < S.iq.size() else 0)
	anim.draw(self)
