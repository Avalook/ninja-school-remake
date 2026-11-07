# Rival students ("classmates", original cs slots cv..cO / cu ct, set up by h(n) ~L3693 on maps 9-11,
# 16, 17): two-part sprites (body /mob/x<frame>.png + head /mob/y<kind><frame>.png), AI aa() ~L6558,
# sensing w(n)/v(n) ~L2313, damage a(int,int,int) ~L2902.
# Kinds cG: 2, 3, 6 = melee (dash in and strike), 0, 4 = dart throwers, 1, 5 = fast-dart throwers.
# Rules only; drawn by view/enemies_view.gd.
@warning_ignore_start("integer_division")
extends RefCounted

# cH states
const DYING := 1
const WALK := 2
const IDLE := 3
const ATTACK := 4
const GONE := 5       # waiting to come back at the spawn point
const HURT := 6
const DASH := 7

var count := 0                         # cs
var spawn_x := []; var spawn_y := []   # cv cw
var x := []; var y := []               # cx cy  body feet
var head_x := []; var head_y := []     # cz cA
var vx := []; var vy := []             # cB cC
var head_vx := []; var head_vy := []   # cD cE (head flies off separately when knocked out)
var bob := []                          # cF
var kind := []                         # cG
var state := []                        # cH
var timer := []                        # cI
var target := []                       # cJ  walk/dash target x
var dir := []                          # cK
var body_frame := []                   # cL
var head_frame := []                   # cM
var min_x := []; var max_x := []       # cN cO
var hp := []                           # cu
var bar := []                          # ct
var px := []; var py := []; var phx := []; var phy := []

func setup(st: Dictionary) -> bool:
	count = int(st.get("cs", 0))
	if count <= 0:
		return false
	var m := {"cv": "spawn_x", "cw": "spawn_y", "cx": "x", "cy": "y", "cz": "head_x", "cA": "head_y",
		"cB": "vx", "cC": "vy", "cD": "head_vx", "cE": "head_vy", "cF": "bob", "cG": "kind", "cH": "state",
		"cI": "timer", "cJ": "target", "cK": "dir", "cL": "body_frame", "cM": "head_frame", "cN": "min_x",
		"cO": "max_x", "cu": "hp", "ct": "bar"}
	for k in m:
		var src = st.get(k, [])
		var arr := []
		for i in count:
			arr.append(int(src[i]) if src is Array and i < src.size() and src[i] != null else 0)
		set(m[k], arr)
	_snap_all()
	return true

func _snap_all() -> void:
	px = x.duplicate(); py = y.duplicate(); phx = head_x.duplicate(); phy = head_y.duplicate()

static func _abs(v: int) -> int:
	return v if v > 0 else -v

# ---------------------------------------------------------------- w(n): notice the player
func _sense(m, n: int) -> void:
	if m.player_stealth():
		return
	if _abs(x[n] - m.fh) < 100 and _abs(y[n] - m.fi) < 20:
		match kind[n]:
			2, 3, 6:
				target[n] = m.fh - 10 if x[n] < m.fh else m.fh + 10
				dir[n] = 1 if target[n] < m.fh else -1
				state[n] = DASH
				vx[n] = dir[n] << 3
				target[n] = clampi(target[n], min_x[n], max_x[n])
			0, 1, 4, 5:
				dir[n] = 1 if x[n] < m.fh else -1
				timer[n] = 0
				target[n] = 0
				state[n] = ATTACK

# ---------------------------------------------------------------- v(n): melee kinds strike when close
func _close_strike(m, n: int) -> void:
	if _abs(m.fi - y[n]) <= 24 and _abs(x[n] + (dir[n] << 3) - m.fh) < 25 and kind[n] in [6, 2, 3]:
		timer[n] = 0
		target[n] = 0
		state[n] = ATTACK

# ---------------------------------------------------------------- aa()
func tick(m) -> void:
	var w: int = m.w
	for n in count:
		px[n] = x[n]; py[n] = y[n]; phx[n] = head_x[n]; phy[n] = head_y[n]
		if bar[n] > 0:
			bar[n] -= 1
		var keep_head := false
		match state[n]:
			IDLE:
				bob[n] = 1 if (w + n) % 10 > 5 else 0
				timer[n] += 1
				body_frame[n] = 0
				head_frame[n] = 0
				if timer[n] > 50:
					var d: int = (w - n) % (n + 80) - 40
					d = d + 20 if d > 0 else d - 20
					target[n] = x[n] + d
					dir[n] = 1 if d > 0 else -1
					if w % 4 > 1:
						state[n] = WALK
						vx[n] = dir[n] << 1
					else:
						state[n] = DASH
						vx[n] = dir[n] << 2
					target[n] = clampi(target[n], min_x[n], max_x[n])
				if w % 30 == 28:
					_sense(m, n)
					_close_strike(m, n)
			WALK:
				if w % 12 > 7:
					bob[n] = 1
					body_frame[n] = 1
					if w % 12 < 10:
						if _abs(x[n] - target[n]) < 4:
							target[n] = 0
							timer[n] = 0
							state[n] = IDLE
							_update_head(n)
							continue
						x[n] += vx[n]
					_sense(m, n)
				else:
					body_frame[n] = 0
					bob[n] = 0
			DASH:
				if _abs(x[n] - target[n]) < 12:
					target[n] = 0
					timer[n] = 0
					state[n] = IDLE
					_close_strike(m, n)
				else:
					x[n] += vx[n]
					if w % 2 == 1:
						bob[n] = 1
						body_frame[n] = 3
					else:
						body_frame[n] = 2
						bob[n] = 0
			ATTACK:
				timer[n] += 1
				if timer[n] < 3:
					bob[n] = 0; body_frame[n] = 0; head_frame[n] = 0
				elif timer[n] < 14:
					bob[n] = 1; body_frame[n] = 1; head_frame[n] = 1
				else:
					state[n] = IDLE
					timer[n] = 1
				if timer[n] == 3:
					var dmg: int = int(S.ad[kind[n]])
					match kind[n]:
						2, 3, 6:
							if _abs(m.fi - y[n]) < 5 and _abs(m.fh - x[n]) < 30:
								m.hurt_player(dmg, dir[n])
						0, 4:
							m.shoot_dart(x[n] + (dir[n] << 3), y[n] - 15, dir[n] * 12, dmg, 0)
						1, 5:
							m.shoot_dart(x[n] + (dir[n] << 3), y[n] - 15, dir[n] << 3, dmg, 1)
			HURT:
				bob[n] = 1 if (w + n) % 10 > 5 else 0
				body_frame[n] = 0
				head_frame[n] = 0
				timer[n] += 1
				if timer[n] >= 5:
					target[n] = m.fh - 80 if x[n] < m.fh else m.fh + 80
					timer[n] = 0
					dir[n] = -1 if target[n] < m.fh else 1
					state[n] = DASH
					vx[n] = dir[n] << 3
					target[n] = clampi(target[n], min_x[n], max_x[n])
			GONE:
				timer[n] += 1
				if timer[n] > 550 + n * 5:
					x[n] = spawn_x[n]
					y[n] = spawn_y[n]
					px[n] = x[n]; py[n] = y[n]
					timer[n] = 0
					state[n] = IDLE
					hp[n] = int(S.af[kind[n]])
			DYING:
				head_frame[n] = 1
				timer[n] += 1
				x[n] += vx[n]
				y[n] += vy[n]
				head_x[n] += head_vx[n]
				head_y[n] += head_vy[n]
				if timer[n] > 14:
					if vx[n] > 1:
						vx[n] -= 1
					elif vx[n] < -1:
						vx[n] += 1
					vy[n] += 1
				if head_vx[n] > 1:
					head_vx[n] -= 1
				elif head_vx[n] < -1:
					head_vx[n] += 1
				head_vy[n] += 1
				if y[n] <= m.fS + m.r + 32 and y[n] < m.iS:
					keep_head = true
				else:
					timer[n] = 0
					target[n] = 0
					state[n] = GONE
		if not keep_head:
			_update_head(n)

func _update_head(n: int) -> void:
	head_x[n] = x[n]
	head_y[n] = y[n] - 9 + bob[n] + int(S.ah[kind[n]][head_frame[n]])

# ---------------------------------------------------------------- a(int n, int dmg, int dir) ~L2902
func damage(m, n: int, dmg: int, d: int) -> bool:
	hp[n] -= dmg
	if state[n] == DYING or state[n] == GONE:
		return true
	m.flash("classmate", n)
	if hp[n] <= 0:
		hp[n] = 0
		dir[n] = d
		vx[n] = 0
		vy[n] = 0
		head_vx[n] = -(d << 3)
		head_vy[n] = -6
		timer[n] = 0
		bar[n] = 0
		state[n] = DYING
		m.gain_exp(int(S.ae[kind[n]]))
		m.drops.spawn_coin(x[n], y[n], -(dir[n] << 2), -4, int(S.ac[kind[n]]) + m.w % 10)
		if m.w % 20 > 5:
			m.drops.spawn_item(x[n], y[n], -(dir[n] << 3), int(S.ad[kind[n]]) << 2, (m.w + 1) % 2)
		m.classmate_killed.emit(kind[n], Vector2(x[n], y[n]))
		return true
	timer[n] = 0
	dir[n] = d
	bar[n] = 100
	state[n] = HURT
	return false
