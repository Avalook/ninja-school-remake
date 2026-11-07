# Player: modern native controller on the ORIGINAL rules (a.java R(), input block in run(), u(), a(n,dir)).
#
# Locomotion (original states A = 1 idle, 2 run, 3 jump, 4 fall) is simulated per display frame with
# continuous physics: acceleration / deceleration, air control, variable jump height, coyote time,
# jump buffering. Constants are the original ones converted to per-second units
# (run P px/tick, jump z0 -8 / -10 khinh công / -7 double, gravity 1 px/tick², terminal 18 px/tick).
# Every other original state (5 attack combo, 6 death, 7 hurt, 8 crouch, 11 swim, 12 sink, 13-15 dig,
# 16/17 under water walk, 23 Xích Mao) is a literal port of R() stepped on the 25 Hz clock, drawn
# interpolated between ticks. The original variables are kept in S (fh, fi, fp, A, B, C, D, y, z, ...)
# so story / enemy modules can read them like the original did.
extends Node2D

const HZ := 25.0
const G := 625.0
const TERMINAL := 450.0
const HALF_W := 11.0      # fl
const BODY_H := 16.0      # fm
const FK := 32            # fk (height)
const COYOTE := 0.09
const BUFFER := 0.12
const NATIVE := [1, 2, 3, 4]

var world
var pos := Vector2.ZERO        # feet (fh, fi), smooth
var vel := Vector2.ZERO        # px/s (native states)
var facing := 1
var state := "idle"
var scripted := false
var script_keys := {}
var weapon: int:
	get: return S.fg

var grounded := false
var coyote := 0.0
var buffer := 0.0
var air_jumps := 0
var prev_tick_pos := Vector2.ZERO
var cur_tick_pos := Vector2.ZERO
var tick_alpha := 0.0
var run_phase := 0.0
var land_t := 0.0
var near_npc := -1
var anim
var _pressed := {}             # edge-triggered keys latched until consumed
var _held_prev := {}
var trail: Array = []

func _ready() -> void:
	Game.tick25.connect(_tick)
	anim = preload("res://scripts/player/hero_anim.gd").new()
	anim.preload_all()
	z_index = 5

# ---------------- input ----------------
func held(k: String) -> bool:
	if scripted:
		return bool(script_keys.get(k, false))
	if Game.mode != "play":
		return false
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

func _poll_edges() -> void:
	for k in ["jump", "attack", "weapon", "stealth", "transform", "hp", "mp", "jump_left", "jump_right", "down"]:
		var h := held(k)
		if h and not _held_prev.get(k, false):
			_pressed[k] = true
			if k == "jump":
				buffer = BUFFER
		_held_prev[k] = h

func take(k: String) -> bool:
	if _pressed.get(k, false):
		_pressed[k] = false
		return true
	return false

# ---------------- placement ----------------
func place(p: Vector2, f := 0) -> void:
	pos = p
	S.fh = int(p.x)
	S.fi = int(p.y)
	S.fs = S.fh
	S.ft = S.fi
	if f != 0:
		facing = f
	S.fp = facing
	vel = Vector2.ZERO
	S.A = 1
	S.y = 0
	S.z = 0
	S.C = 0
	S.D = 0
	S.fq = 0
	grounded = world.is_floor(p.x, p.y)
	if not grounded:
		S.A = 4
		S.z = 1
	prev_tick_pos = p
	cur_tick_pos = p
	_pressed.clear()

func hitbox() -> Rect2:
	return Rect2(pos.x - 11, pos.y - 32, 22, 32)

# original A(): x∈[fh-20, fh+40] facing right (mirrored), y∈[fi-fk-15, fi+5]
func attack_box() -> Rect2:
	var x0 := S.fh - 20 if S.fp > 0 else S.fh - 40
	return Rect2(x0, S.fi - FK - 15, 60, FK + 20)

func is_native() -> bool:
	return int(S.A) in NATIVE

# ---------------- original a(n, dir): player takes damage ----------------
func hurt(n: int, dir: int) -> void:
	if int(S.A) == 6 or S.fq < 0 or S.fz:
		return
	S.ff = -1
	S.iu = -1
	S.G -= n
	if S.G <= 0:
		S.G = 0
		S.z = -7
		S.y = dir << 2
		S.A = 6
		S.im = -1
	elif S.im == -1 and int(S.A) != 23:
		S.y = dir << 2
		S.z = -2
		S.C = 0
		S.D = 0
		S.A = 7
	_enter_tick_state()
	world.spawn_fx("hit", Vector2(S.fh, S.fi - 12), -S.fp)
	world.float_text("-%d" % n, Vector2(S.fh, S.fi - FK), Color(1, 0.35, 0.3))
	world.screen_shake(0.1)
	S.changed.emit()

func _enter_tick_state() -> void:
	S.fh = int(round(pos.x))
	S.fi = int(round(pos.y))
	prev_tick_pos = pos
	cur_tick_pos = Vector2(S.fh, S.fi)
	vel = Vector2.ZERO

# ---------------- per frame ----------------
func _process(dt: float) -> void:
	if world == null or world.map_id < 0:
		return
	_poll_edges()
	if Game.mode == "play" or scripted:
		if is_native():
			_native(min(dt, 1.0 / 30.0))
		else:
			tick_alpha = clampf(Game.acc / 0.04, 0.0, 1.0)
			pos = prev_tick_pos.lerp(cur_tick_pos, tick_alpha)
	state = _state_name()
	anim.update(self, dt)
	queue_redraw()

func _native(dt: float) -> void:
	var dir := (1 if held("right") else 0) - (1 if held("left") else 0)
	if dir != 0:
		facing = dir
	var top := float(S.P) * HZ
	var accel := 1700.0 if grounded else 1100.0
	var decel := 1900.0 if grounded else 420.0
	if dir != 0 and sign(vel.x) != 0 and sign(vel.x) != dir:
		accel *= 1.8
	vel.x = move_toward(vel.x, dir * top, (accel if dir != 0 else decel) * dt)
	buffer = max(0.0, buffer - dt)
	coyote = max(0.0, coyote - dt)

	# ground actions (original input block, state 1/2)
	if grounded or coyote > 0.0:
		if buffer > 0.0:
			_jump(-10 if S.eL else -8)
		elif take("jump_left") or take("jump_right"):
			var d := -1 if held("jump_left") or _held_prev.get("jump_left", false) else 1
			facing = d
			vel.x = d * 4.0 * HZ
			_jump(-10 if S.eL else -8)
		elif grounded and held("down") and abs(vel.x) < 40.0:
			_start_tick_state(8)
			S.C = 0
			S.D = 0
			return
	else:
		# double / triple jump: original A==3, aU[2], I>0, z>-4, (C==0 && eM || C==1 && eY), costs 3 MP
		if take("jump") and S.I > 0 and vel.y > -4.0 * HZ and ((air_jumps == 0 and S.eM) or (air_jumps == 1 and S.eY)):
			air_jumps += 1
			S.I = max(0, S.I - 3)
			world.spawn_fx("dust", pos + Vector2(-8, 0), -1)
			world.spawn_fx("dust", pos + Vector2(8, 0), 1)
			_jump(-7)
			S.changed.emit()
	if take("attack"):
		# ground attack anytime; in the air while rising (A 3) or falling fast (A 4, z > 4)
		if grounded or vel.y < 0.0 or vel.y > 4.0 * HZ:
			_start_attack()
			return
	_common_actions()

	# horizontal + walls (4 = wall on the right, 8 = wall on the left)
	var nx := pos.x + vel.x * dt
	if vel.x > 0.0 and (world.flags_at(nx + HALF_W, pos.y - BODY_H) & 4) == 4:
		nx = floor((nx + HALF_W) / 24.0) * 24.0 - HALF_W
		vel.x = 0.0
	elif vel.x < 0.0 and (world.flags_at(nx - HALF_W - 1.0, pos.y - BODY_H) & 8) == 8:
		nx = floor((nx - HALF_W - 1.0) / 24.0) * 24.0 + 24.0 + HALF_W
		vel.x = 0.0
	pos.x = nx

	if grounded and not world.is_floor(pos.x, pos.y):
		grounded = false
		coyote = COYOTE
		vel.y = 0.0
	if not grounded:
		var g := G
		if vel.y < 0.0 and not held("jump"):
			g *= 2.2
		vel.y = min(vel.y + g * dt, TERMINAL)
		var ny := pos.y + vel.y * dt
		if ny < 0.0:
			ny = 0.0
			vel.y = 0.0
		if vel.y > 0.0:
			var t0 := int(ceil(pos.y / 24.0))
			var t1 := int(floor(ny / 24.0))
			for ty in range(t0, t1 + 1):
				var top_y := float(ty * 24)
				if top_y >= pos.y and top_y <= ny and world.is_floor(pos.x, top_y):
					ny = top_y
					_land()
					break
		pos.y = ny
		var f: int = world.flags_at(pos.x, pos.y)
		if not grounded:
			if pos.y + 4.0 >= world.h * 24.0 and int(world.exits.get("ju", -1)) == -1:
				S.G = 0
				_start_tick_state(6)       # fell into the abyss
				return
			if (f & 0x40) and int(pos.y) % 24 > 8 and vel.y > 0.0:
				_start_tick_state(11)      # water surface -> swim
				S.fi = int(pos.y) / 24 * 24 + 12
				S.y = S.fp << 1
				S.z = int(vel.y / HZ) >> 2
				if S.I == 0 or not S.eS:
					S.A = 12
				_sync_tick_pos()
				return
			if f & 0x1000:
				_start_tick_state(12)
				return
	if grounded:
		run_phase = fposmod(run_phase + abs(vel.x) * dt / 40.0, 1.0)
		if abs(vel.x) > 60.0 and int(S.w) % 6 == 0 and randf() < 0.25:
			world.spawn_fx("dust", pos - Vector2(facing * 6, 0), -facing)
		_crumble()
	land_t = max(0.0, land_t - dt)
	_publish()

func _jump(z0: int) -> void:
	var h := 0.0
	for k in range(1, abs(z0) + 1):
		h += k
	vel.y = -sqrt(2.0 * G * h)
	if grounded:
		world.spawn_fx("dust", pos, 0)
	grounded = false
	coyote = 0.0
	buffer = 0.0
	_pressed["jump"] = false
	near_npc = -1

func _land() -> void:
	var hard := vel.y > 12.0 * HZ
	grounded = true
	air_jumps = 0
	land_t = 0.18 if vel.y > 300.0 else 0.0
	world.spawn_fx("dust", pos + Vector2(-6, 0), -1)
	world.spawn_fx("dust", pos + Vector2(6, 0), 1)
	vel.y = 0.0
	if S.O == 11 and S.fF < 20:
		S.fF = 0           # quest 11: the hit counter resets when touching the ground
	if hard:
		vel.x = 0.0
		_start_tick_state(8)   # original: landing from a high fall -> crouch (A = 8)
		S.C = 0
		S.D = 0

# keys valid in every playable state (original aV[0], aV[9], aV[7], aV[10], aV[11])
func _common_actions() -> void:
	if take("weapon"):
		if S.fg == 0:
			if S.eN:
				S.fg = 1
		else:
			S.fg = 0
		S.changed.emit()
	if take("stealth") and S.fc:
		S.fz = not S.fz
		S.changed.emit()
	if take("transform"):
		if int(S.A) == 1 and S.eT and grounded:
			_start_tick_state(23)
			S.C = 0
	if take("hp") and S.G < S.H:
		for i in 3:
			if S.V[i] > 0:
				S.V[i] -= 1
				S.G = min(S.H, S.G + int(S.kJ[i]))
				world.float_text("+%d" % int(S.kJ[i]), pos - Vector2(0, 40), Color(0.4, 1, 0.4))
				break
		S.changed.emit()
	if take("mp") and S.I < S.J:
		for i in range(3, 6):
			if S.V[i] > 0:
				S.V[i] -= 1
				S.I = min(S.J, S.I + int(S.kJ[i]))
				world.float_text("+%d" % int(S.kJ[i]), pos - Vector2(0, 40), Color(0.4, 0.6, 1))
				break
		S.changed.emit()

# publish the native body into the original variables (for story / enemies / exits)
func _publish() -> void:
	S.fh = int(round(pos.x))
	S.fi = int(pos.y) if grounded else int(round(pos.y))
	S.fp = facing
	if grounded or coyote > 0.0:
		S.A = 2 if abs(vel.x) > 12.0 else 1
		S.z = 0
	else:
		S.A = 3 if vel.y < 0.0 else 4
		S.z = clampi(int(vel.y / HZ), -10, 18)
	S.y = int(vel.x / HZ)

func _start_attack() -> void:
	_start_tick_state(5)
	S.fw = 0
	S.C = 0
	S.fv = 0
	S.fy = false
	S.z = 0 if grounded else clampi(int(vel.y / HZ), -10, 18)
	S.y = 0 if grounded else int(vel.x / HZ)
	near_npc = -1

func _start_tick_state(a: int) -> void:
	_publish()
	S.A = a
	_enter_tick_state()

func _sync_tick_pos() -> void:
	cur_tick_pos = Vector2(S.fh, S.fi)
	prev_tick_pos = pos

# return to native locomotion from a tick state
func _resume_native() -> void:
	pos = Vector2(S.fh, S.fi)
	facing = S.fp
	vel = Vector2(S.y * HZ, S.z * HZ)
	grounded = int(S.A) in [1, 2] and world.is_floor(pos.x, pos.y)
	if int(S.A) in [1, 2]:
		vel.y = 0.0
		if not grounded:
			S.A = 4
	if grounded:
		air_jumps = 0
	elif int(S.A) == 3:
		air_jumps = 0

# ---------------- 25 Hz: tick states + global timers ----------------
func _tick() -> void:
	if world == null or world.map_id < 0 or (Game.mode != "play" and not scripted):
		return
	# MP drain of stealth / Xích Mao (original: every 3 ticks)
	if S.w % 3 == 0:
		if S.fz:
			S.I -= 1
			if S.I <= 0:
				S.I = 0
				S.fz = false
			S.changed.emit()
		if S.fA:
			S.I -= 1
			if S.I <= 0:
				S.I = 0
				S.fA = false
			S.changed.emit()
	if S.fq < 0:
		S.fq += 1
	if is_native():
		_crumble_tick()
		_npc_check()
		return
	prev_tick_pos = Vector2(S.fh, S.fi)
	_step_original()
	if is_native():
		_resume_native()
	cur_tick_pos = Vector2(S.fh, S.fi)
	if is_native():
		prev_tick_pos = cur_tick_pos

func _npc_check() -> void:
	near_npc = -1
	if int(S.A) != 1:
		return
	for n in int(S.lx):
		if S.fi == int(S.lz[n]) and abs(S.fh - int(S.ly[n])) < 30:
			near_npc = n
			return

func g(x: int, y: int, f: int) -> bool:
	return (world.flags_at(x, y) & f) == f

func fl(x: int, y: int) -> int:
	return world.flags_at(x, y)

static func I_(v: int) -> int:
	return int(v / 24) * 24

# original S(): crumbling tiles (0x800 crumble when stood on -> 0x400 marker)
func _crumble() -> void:
	pass

func _crumble_tick() -> void:
	if S.A in [1, 2] and world.has_method("crumble_at"):
		world.crumble_at(S.fh, S.fi + 1)

func _step_original() -> void:
	var A := int(S.A)
	match A:
		5: _st_attack()
		6: _st_dead()
		7: _st_hurt()
		8: _st_crouch()
		11: _st_swim()
		12: _st_sink()
		13:
			S.B = 24
			S.fi += 28
			S.A = 14
		14:
			S.B = 25 if S.w % 20 > 2 else 26
			if held("left"): S.fp = -1
			elif held("right"): S.fp = 1
			elif take("jump"): S.A = 15
			if S.w % 8 == 0:
				S.I -= 1
				if S.I <= 0:
					S.I = 0
					S.A = 15
				S.changed.emit()
		15:
			S.fi -= 10
			S.z = -10
			S.A = 3
		16, 17: _st_underwater(A)
		23: _st_transform()
		_:
			S.A = 4
	facing = S.fp

# ---- A = 5: attack combo (fw = combo stage, C = tick in stage), hit frames call u() ----
func _st_attack() -> void:
	if held("left"):
		if S.fp == 1: S.fp = -1
		else: S.y = -S.P + 2
	elif held("right"):
		if S.fp == -1: S.fp = 1
		else: S.y = S.P - 2
	if take("attack"):
		S.fy = true
	S.C += 1
	if S.fp == 1:
		if fl(S.fh + 11, S.fi - 16) & 4: S.y = 0
	elif fl(S.fh - 11, S.fi - 16) & 8: S.y = 0
	S.fh += S.y
	S.fi += S.z
	if S.fi < 0:
		S.z = 0
		S.fi = 0
	if S.z == 0:
		if S.iu == -1 and (fl(S.fh, S.fi) & 2) != 2:
			S.A = 4
			S.ff = -1
			S.y = (S.P >> 1) * S.fp
			S.D = 0
			S.C = 0
	elif S.z < 0:
		S.z += 1
		if S.z == 0:
			S.z = 1
	else:
		if S.z < 20 and S.C % 5 == 0:
			S.z += 1
		if S.z > 3:
			S.z = 3
		if S.z > 2 and (fl(S.fh, S.fi + 3) & 2) == 2:
			S.z = 0
			S.y = 0
			S.fi = I_(S.fi + 3)
			if S.O == 11 and S.fF < 20:
				S.fF = 0
		if g(S.fh, S.fi, 64) and S.fi % 24 > 8:
			S.A = 11
			S.y = S.fp << 1
			S.z >>= 2
			S.fi = I_(S.fi) + 12
			S.ff = -1
			if S.I == 0 or not S.eS:
				S.A = 12
			return
		if g(S.fh, S.fi, 4096):
			S.A = 12
			S.ff = -1
			return
	if S.y > 0: S.y -= 1
	elif S.y < 0: S.y += 1
	if int(S.A) != 5:
		return
	var C := int(S.C)
	match int(S.fw):
		0:
			if C <= 3: S.B = 13
			elif C == 4: S.B = 14
			elif C <= 6:
				S.B = 15
				if C == 6: _u()
			elif C <= 8:
				S.B = 16
				if C == 8:
					S.fw = 2 if S.fy else 1
					S.fy = false
					S.C = 1
		3:
			if C <= 2: S.B = 15
			elif C <= 4:
				S.B = 16
				if C == 4:
					S.fw = 2 if S.fy else 1
					S.fy = false
					S.C = 1
		1:
			S.B = 15
			if C >= 2: S.fw = 10
		2:
			if C <= 3: S.B = 17
			elif C == 4: S.B = 18
			elif C == 5: S.B = 19
			elif C <= 7:
				S.B = 20
				if C == 6: _u()
				if C == 7:
					if S.fy and S.fv < 2:
						if S.z == 0 and S.fu:
							S.C = 0
							S.fw = 9
						else:
							S.fw = 3
							S.fv += 1
						S.fy = false
					else:
						S.fw = 10
					S.C = 1
		9:
			# launcher: knocks the enemy into the air (original jS = 9, jX = -8) and jumps after it
			if C <= 3: S.B = 13
			elif C == 4: S.B = 14
			elif C <= 6:
				S.B = 15
				S.fu = false
				var n: int = world.enemies.launch(attack_box(), S.fp) if world.enemies and world.enemies.has_method("launch") else -1
				if n != -1:
					S.fu = true
			elif C <= 8: S.B = 16
			else:
				S.B = 16
				if S.fu:
					S.A = 3
					S.y = 0
					S.z = -8
				else:
					S.C = 0
					S.D = 0
					S.fw = 10
		10:
			if C == 1: S.B = 16
			elif C == 2: S.B = 14
			elif C <= 4: S.B = 13
			else:
				S.B = 13
				S.A = 4 if S.z != 0 else 1
				S.fv = 0
				S.D = 0
	if S.fg == 1:
		S.ff = -1
	if int(S.A) == 5:
		if S.z != 0: S.B += 14
		elif S.y != 0: S.B += 22

# original u(): damage roll and the hit (sword) or the shuriken throw
func _u() -> void:
	if S.O == 11 and S.z != 0:
		S.fF += 1
		UI.popup("Thành công 20 hit!" if S.fF >= 20 else "Hit: %d" % S.fF, 0.6)
	var n: int = S.E + S.w % max(1, S.E >> 1)
	n += (n >> 1) * S.x
	if S.fA:
		n <<= 1
	if S.fg == 1:
		if world.enemies and world.enemies.has_method("spawn_projectile"):
			world.enemies.spawn_projectile(S.fh + (S.fp << 3), S.fi - 12, (S.fp << 3) + S.y, S.z << 1, false)
		return
	if S.z > 0 and S.y == 0:
		S.z = -2
	S.fu = false
	var hit := -1
	if world.enemies:
		hit = world.enemies.hit(attack_box(), n, -S.fp)
	world.spawn_fx("slash", Vector2(S.fh + S.fp * 18, S.fi - 20), S.fp)
	if hit != -1:
		S.fu = true
		if S.z > 0:
			S.z = -6
		Game.hitstop(0.05)
		if S.fA:
			world.screen_shake(0.12)
	if world.has_method("push_block"):
		world.push_block(S.fh + (S.fp << 4), S.fi - 16, S.fp)

# ---- A = 6: death -> respawn at the map entry, HP/MP 1/3, half EXP (original) ----
func _st_dead() -> void:
	S.fh += S.y
	if S.y > 1: S.y -= 1
	if S.y < -1: S.y += 1
	S.B = 21
	S.z += 1
	S.fi += S.z
	var vs: Vector2 = world.view_size()
	if S.fi < world.h * 24 and S.fi <= world.camera.position.y + vs.y + FK and not g(S.fh, S.fi - FK, 4096):
		return
	S.fh = S.fs
	S.fi = S.ft
	S.A = 4
	S.G = S.H / 3
	S.I = S.J / 3
	S.z = 0
	S.y = 0
	S.D = 0
	S.C = 0
	S.N >>= 1
	if S.hf:
		S.hj = S.hk
	if S.hl:
		S.hp = S.hq
	S.changed.emit()
	prev_tick_pos = Vector2(S.fh, S.fi)
	UI.popup("Bạn đã bị trọng thương! Mất một số điểm kinh nghiệm!")

# ---- A = 7: hit knock-back for 7 ticks, then 20 ticks of invulnerability (fq) ----
func _st_hurt() -> void:
	if fl(S.fh + 11, S.fi - 16) & 4: S.y = -S.y
	if fl(S.fh - 11, S.fi - 16) & 8: S.y = -S.y
	S.fh += S.y
	S.fi += S.z
	S.B = 21
	if S.y > 1: S.y -= 1
	if S.y < -1: S.y += 1
	S.z += 1
	if S.z > 0: S.z = 0
	S.C += 1
	if S.C >= 7:
		S.A = 4
		S.fq = -20
		S.D = 0
		S.C = 0
		S.z = 0

# ---- A = 8: crouch; holding down 6+ ticks on earth (0x2000) with Độn thổ (eO) and MP -> dig ----
func _st_crouch() -> void:
	S.B = 22
	if S.C < 5:
		S.C += 1
	elif not held("down"):
		S.A = 1
	S.D += 1
	if S.D > 6 and g(S.fh + 11, S.fi + 2, 8192) and g(S.fh - 11, S.fi + 2, 8192) and S.eO and S.I > 0:
		S.A = 13

# ---- A = 11: swimming on the surface (original U()) ----
func _st_swim() -> void:
	if take("jump") or held("jump"):
		S.z = -10 if S.eL else -8
		S.A = 3
		S.C = 0
		return
	if held("left"):
		if S.fp == 1: S.fp = -1
		else: S.y = -S.K + S.fr
	elif held("right"):
		if S.fp == -1: S.fp = 1
		else: S.y = S.K + S.fr
	S.C += 1
	if S.C >= 5:
		S.C = 0
		S.fr = 0
	S.B = S.C + 2
	if S.fp == 1:
		if g(S.fh + 11, S.fi - 1, 4):
			S.y = 0
			S.fh = I_(S.fh + 11) - 11
	elif g(S.fh - 12, S.fi - 1, 8):
		S.y = 0
		S.fh = I_(S.fh - 12) + 24 + 11
	S.fh += S.y
	if S.y > 0: S.y -= 1
	elif S.y < 0: S.y += 1
	elif not S.fb:
		S.A = 12
		S.fr = 0
	else:
		S.A = 3
		S.z = -8
		S.D = 0
		S.C = 0
	if S.w % 8 == 0:
		S.I -= 3
		if S.I <= 0:
			S.I = 0
			S.y = 0
			S.A = 12
		S.changed.emit()

# ---- A = 12: sinking (water body / depth) ----
func _st_sink() -> void:
	if take("jump") and S.eS:
		S.z = -10
		S.A = 3
		S.ff = -1
		S.C = 0
		return
	S.fi += S.z
	if S.z < 20 and S.w % 2 == 0:
		S.z += 1
	S.B = 7
	if S.fi >= world.h * 24:
		S.G = 0
		S.A = 6
		S.ff = -1
		return
	if g(S.fh, S.fi, 2):
		S.fi = I_(S.fi)
		if g(S.fh, S.fi - 2, 64):
			S.A = 1
		elif S.eR:
			S.A = 17
		else:
			S.A = 6
		S.z = 0
		S.y = 0
		S.D = 0
		S.C = 0
		return
	if g(S.fh, S.fi - FK - 5, 4096):
		S.G = 0
		S.A = 6
		S.ff = -1

# ---- A = 16 / 17: walking on the water bottom (eR) ----
func _st_underwater(A: int) -> void:
	if take("jump") or held("jump"):
		S.z = -10
		S.A = 3
		S.ff = -1
		S.C = 0
		return
	if held("left"):
		if S.fp == 1: S.fp = -1
		else:
			S.A = 16
			S.y = -S.L
	elif held("right"):
		if S.fp == -1: S.fp = 1
		else:
			S.A = 16
			S.y = S.L
	if int(S.A) == 17:
		S.C += 1
		if S.C > 30: S.C = 0
		S.B = 0 if S.C % 15 < 5 else 1
		return
	S.C += 1
	if S.C >= 20: S.C = 0
	S.B = (S.C >> 2) + 2
	if S.fp == 1:
		if g(S.fh + 11, S.fi - 16, 4):
			S.y = 0
			S.fh = I_(S.fh + 11) - 11
	elif g(S.fh - 12, S.fi - 16, 8):
		S.y = 0
		S.fh = I_(S.fh - 12) + 24 + 11
	S.fh += S.y
	if S.y > 0: S.y -= 1
	elif S.y < 0: S.y += 1
	else: S.A = 17
	if (fl(S.fh, S.fi) & 2) != 2:
		S.A = 12
		S.y = S.fp << 2
		S.D = 0

# ---- A = 23: Xích Mao transformation (original T(), 30 ticks, toggles fA) ----
func _st_transform() -> void:
	S.C += 1
	if S.C == 1:
		world.spawn_fx("power", Vector2(S.fh, S.fi - 40), 1)
		world.screen_shake(0.25)
	elif S.C >= 30:
		S.A = 1
		S.C = 0
		if S.I == 0:
			return
		S.fA = not S.fA
		S.changed.emit()
	S.B = 0 if S.C % 2 > 0 else 1

# ---------------- animation state name ----------------
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
		23: return "power"
	return "idle"

func _draw() -> void:
	anim.draw(self)
