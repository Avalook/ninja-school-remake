# Toniku golem boss (map 95 while !eK). Original globals hl hs ht hu hv hw hx hy hz hp hq hr hm hn ho and
# the 6-part rig hG/hH (= hs ± (hI + hK), ht + hJ + hL) with poses hA/hB (idle), hC/hD (strike), hE/hF (walk);
# setup k(n) ~L3976, AI ac() ~L6815, hit in u() ~L2202, draw ~L7887.
# hu == 0 waits for the cutscene (opcode 28 sets hu = 1); hu == 22 (after the death burst) ends the fight.
@warning_ignore_start("integer_division")
extends RefCounted

const SP := preload("res://scripts/enemies/sprites.gd")

const WAIT := 0      # hu
const IDLE := 1
const WALK := 2
const PUNCH := 5
const DEATH := 6
const THROW := 8     # throws shurikens
const SHOOT := 23    # fires darts (kind 3, cannot be parried)
const DASH := 24
const GONE := 22
const PART_IMAGES := ["/mob/b20.png", "/mob/b21.png", "/mob/b22.png", "/mob/b21.png", "/mob/b23.png", "/cp/f2h0.png"]

var active := false  # hl
var x := 192         # hs
var y := 264         # ht
var state := WAIT    # hu
var dir := -1        # hv
var min_x := 120     # hw
var max_x := 504     # hx
var timer := 0       # hy
var target_x := 0    # hz
var hp := 90000      # hp
var max_hp := 90000  # hq
var bar := 0         # hr (never cleared after the first hit, as in the original)
var dmg := 500       # hm
var w := 60          # hn
var h := 60          # ho
var part_x := []     # hI
var part_y := []     # hJ
var pose_x := []     # hK
var pose_y := []     # hL
var idle_x := []; var idle_y := []     # hA hB
var strike_x := []; var strike_y := []  # hC hD
var walk_x := []; var walk_y := []     # hE hF
var draw_x := [0, 0, 0, 0, 0, 0]       # hG
var draw_y := [0, 0, 0, 0, 0, 0]       # hH
var prev_x := []; var prev_y := []
var flash := 0.0

static func _arr(v, n := 6) -> Array:
	var o := []
	if v is Array:
		for e in v:
			o.append(_arr(e) if e is Array else int(e))
	else:
		o.resize(n)
		o.fill(0)
	return o

func setup(st: Dictionary) -> void:
	active = bool(st.get("hl", false))
	x = int(st.get("hs", 192)); y = int(st.get("ht", 264)); state = int(st.get("hu", WAIT))
	dir = int(st.get("hv", -1)); min_x = int(st.get("hw", 120)); max_x = int(st.get("hx", 504))
	timer = int(st.get("hy", 0)); target_x = int(st.get("hz", 0)); hp = int(st.get("hp", 90000))
	max_hp = int(st.get("hq", 90000)); bar = int(st.get("hr", 0)); dmg = int(st.get("hm", 500))
	w = int(st.get("hn", 60)); h = int(st.get("ho", 60))
	part_x = _arr(st.get("hI")); part_y = _arr(st.get("hJ"))
	pose_x = _arr(st.get("hK")); pose_y = _arr(st.get("hL"))
	idle_x = _arr(st.get("hA")); idle_y = _arr(st.get("hB"))
	strike_x = _arr(st.get("hC")); strike_y = _arr(st.get("hD"))
	walk_x = _arr(st.get("hE")); walk_y = _arr(st.get("hF"))
	if st.get("hG") is Array:
		draw_x = _arr(st.get("hG")); draw_y = _arr(st.get("hH"))
	prev_x = draw_x.duplicate(); prev_y = draw_y.duplicate()

# story hook: original cutscene opcode 28 (hu = 1)
func start() -> void:
	if state == WAIT:
		state = IDLE

static func _abs(v: int) -> int:
	return v if v > 0 else -v

func _pose(px: Array, py: Array, i: int) -> void:
	pose_x = px[i]
	pose_y = py[i]

func _face_player(m) -> void:
	dir = 1 if x < m.fh else -1

func _clamp_target(m) -> void:
	target_x = clampi(m.fh, min_x, max_x)

func tick(m) -> void:
	prev_x = draw_x.duplicate(); prev_y = draw_y.duplicate()
	if not active:
		return
	var n: int = m.w % 10
	var dx: int = _abs(m.fh - x)
	var dy: int = _abs(m.fi - y)
	match state:
		IDLE:
			_pose(idle_x, idle_y, 0 if n < 7 else 1)
			if dx > 90 and dx < 100 and n < 2:
				_face_player(m); state = SHOOT; timer = 0
			elif (dx > 120 and dx < 150 or m.fi < y and dx > 40) and n < 2:
				_face_player(m); timer = 0; state = THROW
			elif dx < 40 and n == 1:
				_face_player(m); timer = 0; state = PUNCH
			elif dx > 40 and dx < 100 and n < 3:
				_face_player(m); timer = 0; _clamp_target(m); state = DASH
			elif dx > 80 and n == 2:
				timer = 0; state = WALK; _clamp_target(m)
		PUNCH, THROW:
			var throwing := state == THROW
			timer += 1
			if timer < 2:
				_pose(idle_x, idle_y, 0)
			elif timer < 12:
				_pose(strike_x, strike_y, 1)
			elif timer < 14:
				_pose(strike_x, strike_y, 0)
			elif timer == 14:
				timer = 0
				state = IDLE
			if not throwing:
				if timer == 3 and dx < 40 and dy < 10:
					m.hurt_player(dmg, dir)
			elif timer % 5 == 0:
				m.spawn_projectile(x + (dir << 3), y - 20, dir << 3, -n + 2, false)
		WALK:
			timer += 1
			var f: int = timer % 6 >> 1
			_pose(walk_x, walk_y, f)
			x += dir << 2
			if x > target_x and dir > 0 or x < target_x and dir < 0 or x > max_x or x < min_x or timer > 100:
				timer = 0
				state = IDLE
			if dx > 50 and dx < 100 and n == 1:
				_face_player(m); state = SHOOT; timer = 0
		DASH:
			timer += 1
			_pose(walk_x, walk_y, timer % 6 >> 1)
			x += dir << 3
			if x > target_x and dir > 0 or x < target_x and dir < 0 or x > max_x or x < min_x or timer > 100:
				timer = 0
				state = PUNCH
		SHOOT:
			timer += 1
			_pose(strike_x, strike_y, timer % 4 >> 1)
			if timer > 20 and timer % 10 == 0:
				m.shoot_dart(x + (dir << 3), y - 24, dir << 3, dmg, 3)
			if timer == 100:
				_face_player(m); timer = 0; _clamp_target(m); state = DASH
		WAIT:
			_pose(idle_x, idle_y, 0 if n < 7 else 1)
		DEATH:
			timer += 1
			for i in part_y.size():
				part_y[i] += _abs(i - 3) + 2
				part_x[i] += (i - 3) << 1
			if timer >= 50:
				state = GONE
				m.boss_killed.emit("golem")
	for i in 6:
		draw_x[i] = x + part_x[i] + pose_x[i] if dir == 1 else x - part_x[i] - pose_x[i]
		draw_y[i] = y + part_y[i] + pose_y[i]

# hit from the player's sword (u() ~L2202)
func melee(m, fh: int, fi: int, fp: int, n: int) -> bool:
	if not (active and _abs(fi - (y - 30)) < 30 and _abs(fh + (fp << 4) - x) < 30):
		return false
	hp -= n
	flash = 0.08
	if hp <= 0:
		hp = 0
		timer = 0
		bar = 0
		state = DEATH
		for v in [[4, -10], [-4, -17], [-3, -18], [4, -19], [0, -10]]:
			m.drops.spawn_coin(x, y, v[0], v[1], 500)
	else:
		bar = 50
		m.fx("hit", x, y - 47, fp)
		m.text("-%d" % n, x, y - 55, 0)
	return true

func draw(ci: CanvasItem, alpha: float, dt: float) -> void:
	if not active:
		return
	flash = maxf(0.0, flash - dt)
	var col := Color(3, 3, 3) if flash > 0.0 else Color.WHITE
	var a := 1.0
	if state == DEATH:
		a = clampf(1.0 - timer / 50.0, 0.0, 1.0)
	elif state == GONE:
		return
	col.a = a
	for i in 6:
		SP.draw(ci, PART_IMAGES[i], lerpf(prev_x[i], draw_x[i], alpha), lerpf(prev_y[i], draw_y[i], alpha), 33, dir <= 0, col)
	if bar != 0:
		SP.hp_bar(ci, x, y - h - 7, w, hp, max_hp, Color8(0x33, 0, 0x11), Color8(0xEE, 0, 0), Color8(0x44, 0, 0x22))
