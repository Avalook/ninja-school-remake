# Native 120+ Hz player body (modern feel) on top of the original rules.
#
# The original game decides WHAT the player does (its input block reads the key flags aU/aV,
# including keys "pressed" by cutscene scripts) and everything else (monsters, hits, quests,
# cutscenes, exits, NPC talk) keeps running in the original 25 Hz logic. This module decides HOW
# the body moves while the original state is idle/run/jump/fall (A = 1..4):
#   acceleration / deceleration, air control, variable jump height, coyote time, jump buffering,
#   continuous gravity — with the ORIGINAL constants converted to per-second units
#   (run speed P px/tick, jump z0 = -8 / -10 (khinh công) / -7 (double), gravity 1 px/tick²,
#   terminal fall 18 px/tick) and the ORIGINAL tile flags (2 = standable top, 4/8 = wall right/left,
#   0x40 water surface, 0x1000 ladder).
# Any other original state (attack 5, hurt 7, death 6, swim/ladder 11/12, crouch/dig 8, power-up 23 …)
# suspends this module; it re-syncs from the original position when control returns.
extends RefCounted

const TICK_HZ := 25.0
const G := 625.0                 # 1 px/tick² * 25²
const TERMINAL := 450.0          # 18 px/tick * 25
const HALF_W := 11.0             # fl = fj >> 1
const BODY_H := 16.0             # fm = fk >> 1 (wall probe height)
const COYOTE := 0.09
const BUFFER := 0.12

var core
var active := false
var suspended := true
var x := 0.0
var y := 0.0
var vx := 0.0
var vy := 0.0
var grounded := false
var facing := 1
var coyote := 0.0
var buffer := 0.0
var jump_held_prev := false
var air_jumps_used := 0
var pending_jump := 0          # original z0 requested this tick (-8/-10/-7), 0 = none
var run_phase := 0.0
var written := Vector2i.ZERO
var last_v := -1
var landed_t := 0.0

func attach(c) -> void:
	core = c

# ---------- tile flags (original iU) ----------
func flags(px: float, py: float) -> int:
	var tx := int(floor(px / 24.0))
	var ty := int(floor(py / 24.0))
	if tx < 0 or ty < 0 or tx >= int(core.iP) or ty >= int(core.iQ):
		return 0
	return int(core.iU[ty * int(core.iP) + tx])

func _supported(px: float, py: float) -> bool:
	return (flags(px, py) & 2) == 2

func resync() -> void:
	x = float(core.fh)
	y = float(core.fi)
	vx = float(core.y) * TICK_HZ
	vy = float(core.z) * TICK_HZ
	facing = 1 if int(core.fp) == 1 else -1
	grounded = vy >= 0.0 and _supported(x, y) and (core.A in [1, 2] or int(y) % 24 == 0)
	if grounded:
		vy = 0.0
	written = Vector2i(core.fh, core.fi)
	last_v = int(core.v)

# ---------- per display frame ----------
func update(dt: float) -> void:
	if core == null or not active or suspended or dt <= 0.0:
		return
	dt = min(dt, 1.0 / 30.0)
	var left: bool = core.aU[4]
	var right: bool = core.aU[6]
	var jump_held: bool = core.aU[2]
	var dir := (1 if right else 0) - (1 if left else 0)
	if dir != 0:
		facing = dir
	var top_speed := float(core.P) * TICK_HZ
	var target := dir * top_speed
	var accel := 1700.0 if grounded else 1100.0
	var decel := 1900.0 if grounded else 420.0
	if dir != 0 and sign(vx) != 0 and sign(vx) != dir:
		accel *= 1.8        # snappy turnaround
	vx = move_toward(vx, target, (accel if dir != 0 else decel) * dt)

	# jump input edge for buffering (the original only accepts jumps on the ground)
	if jump_held and not jump_held_prev:
		buffer = BUFFER
	jump_held_prev = jump_held
	buffer = max(0.0, buffer - dt)
	coyote = max(0.0, coyote - dt)

	# --- jumps requested by the original input block (or buffered/coyote) ---
	if pending_jump != 0:
		_do_jump(pending_jump)
		pending_jump = 0
	elif buffer > 0.0 and grounded and core.d == 1 and not core.fV:
		_do_jump(-10 if core.eL else -8)

	# --- horizontal move + walls (flag 4 blocks to the right, 8 to the left) ---
	var nx := x + vx * dt
	if vx > 0.0 and (flags(nx + HALF_W, y - BODY_H) & 4) == 4:
		nx = floor((nx + HALF_W) / 24.0) * 24.0 - HALF_W
		vx = 0.0
	elif vx < 0.0 and (flags(nx - HALF_W - 1.0, y - BODY_H) & 8) == 8:
		nx = floor((nx - HALF_W - 1.0) / 24.0) * 24.0 + 24.0 + HALF_W
		vx = 0.0
	x = nx

	# --- vertical ---
	if grounded:
		if not _supported(x, y):
			grounded = false
			coyote = COYOTE
			vy = 0.0
	if not grounded:
		var g := G
		if vy < 0.0 and not jump_held:
			g *= 2.2           # variable jump height: release early = shorter hop
		vy = min(vy + g * dt, TERMINAL)
		var ny := y + vy * dt
		if ny < 0.0:
			ny = 0.0
			vy = 0.0
		if vy > 0.0:
			# one-way floors: land on the first standable tile top crossed this frame
			var t0 := int(ceil(y / 24.0))
			var t1 := int(floor(ny / 24.0))
			for ty in range(t0, t1 + 1):
				var top := float(ty * 24)
				if top >= y and top <= ny and _supported(x, top):
					ny = top
					grounded = true
					landed_t = 0.18 if vy > 300.0 else 0.0
					vy = 0.0
					air_jumps_used = 0
					break
		y = ny
	if grounded:
		run_phase = fposmod(run_phase + abs(vx) * dt / 40.0, 1.0)
	landed_t = max(0.0, landed_t - dt)

func _do_jump(z0: int) -> void:
	var h := 0.0
	for k in range(1, abs(z0) + 1):
		h += k              # original rise height: z0 + (z0-1) + ... + 1 px
	vy = -sqrt(2.0 * G * h)
	if not grounded and coyote <= 0.0:
		air_jumps_used += 1
	grounded = false
	coyote = 0.0
	buffer = 0.0

# ---------- sync with the original tick ----------
# before run_tick(): publish the body to the original state
func pre_tick() -> void:
	if not active or suspended:
		return
	core.fh = int(round(x))
	core.fi = int(round(y)) if not grounded else int(y)
	core.fp = facing
	if grounded or coyote > 0.0:
		core.A = 2 if abs(vx) > 12.0 else 1
		core.z = 0
	else:
		core.A = 3 if vy < 0.0 else 4
		core.z = clampi(int(vy / TICK_HZ), -6, 18)
	core.y = 0
	core.B = anim_B()
	core.nat_A = core.A
	written = Vector2i(core.fh, core.fi)

# inside the original R(): called with the intent the input block produced this tick
func capture_intent() -> void:
	if core.A == 3 and int(core.z) < 0:
		if int(core.z) == -7:
			pending_jump = -7                      # double jump (eM / eY)
		elif grounded or coyote > 0.0:
			pending_jump = int(core.z)              # -8 / -10

# after run_tick(): adopt anything the original did to the player (hits, teleports, map change,
# attacks, swimming …)
func post_tick() -> void:
	if core == null or not active:
		return
	var A: int = core.A
	if int(core.v) != last_v or core.d != 1:
		suspended = true
		last_v = int(core.v)
	if A in [1, 2, 3, 4]:
		if suspended:
			suspended = false
			resync()
			return
		if abs(int(core.fh) - written.x) > 2 or abs(int(core.fi) - written.y) > 2:
			resync()      # moved by a cutscene, door, exit or knock-back
			return
		# water surface / ladder / bottom of the map: the original handles those states
		if (flags(x, y) & 0x40) == 0x40 or (flags(x, y) & 0x1000) == 0x1000 or y + 4.0 >= float(core.iS):
			core.fh = int(round(x))
			core.fi = int(round(y))
			core.A = 4
			core.z = max(1, int(vy / TICK_HZ))
			suspended = true
	else:
		suspended = true
		x = float(core.fh)
		y = float(core.fi)

# animation frame index for the original B (idle 0/1, run 2..6, air 7/23/12)
func anim_B() -> int:
	if grounded:
		if abs(vx) > 12.0:
			return 2 + int(run_phase * 5.0) % 5
		return 0
	if vy < 0.0:
		return 7
	return 12 if vy > 200.0 else 23
