# River monster boss (map 97 while !eI). Original globals hM hS hT ia ib ic id ie if hP hQ hN hO hR and
# part offsets bJ..bO; setup j(n) ~L3931, AI run() ~L9872-9990, hit in u() ~L2174, drawn by view/enemies_view.gd
# (~L7594: /mob/b10 body, b11 b12 b13 parts). ia == 5 (sunk after death) is what the story waits for.
@warning_ignore_start("integer_division")
extends RefCounted

const SINKING := 1   # ia
const SWIM := 2
const SLAM := 4      # rises, sweeps the claw, spits seeds and darts
const DEAD := 5
const DIVE := 7      # dives and resurfaces next to the player
const W := 50        # hR hit width
const H := 60        # hO hit height

var active := false  # hM
var x := 0           # hS
var y := 0           # hT
var state := SWIM    # ia
var dir := -1        # ib
var min_x := 0       # ic
var max_x := 0       # id
var timer := 0       # ie
var bar := 0         # if
var hp := 8000       # hP
var max_hp := 8000   # hQ
var dmg := 20        # hN
var off := [-22, 18, -10, -19, -12, -26]   # bJ bK bL bM bN bO
var parts := [0, 0, 0, 0, 0, 0]            # hU hY hX hZ hV hW
var prev := []

func setup(st: Dictionary) -> void:
	active = bool(st.get("hM", false))
	x = int(st.get("hS", 0)); y = int(st.get("hT", 0)); state = int(st.get("ia", SWIM))
	dir = int(st.get("ib", -1)); min_x = int(st.get("ic", 0)); max_x = int(st.get("id", 0))
	timer = int(st.get("ie", 0)); bar = int(st.get("if_", st.get("if", 0)))
	hp = int(st.get("hP", 8000)); max_hp = int(st.get("hQ", 8000)); dmg = int(st.get("hN", 20))
	var names := ["bJ", "bK", "bL", "bM", "bN", "bO"]
	for i in 6:
		off[i] = int(st.get(names[i], off[i]))
	var pn := ["hU", "hY", "hX", "hZ", "hV", "hW"]
	for i in 6:
		parts[i] = int(st.get(pn[i], 0))
	_snap()

func _snap() -> void:
	prev = [x, y] + parts.duplicate()

static func _abs(v: int) -> int:
	return v if v > 0 else -v

func tick(m) -> void:
	_snap()
	if not active:
		return
	var wt: int = m.w
	var t10 := wt % 10
	if bar > 0:
		bar -= 1
	match state:
		SWIM:
			x += (dir << 2) if hp < max_hp >> 1 else (dir << 1)
			if x < min_x:
				dir = 1
			if x > max_x:
				dir = -1
			if timer > 0:
				timer -= 1
			if timer == 0 and _abs(m.fh - (x - 40)) < 30:
				timer = 0
				state = SLAM
				dir = 1 if x < m.fh else -1
			if wt % 122 == 0:
				m.lob_seed(x, y - 45, -1, -12, dmg)
				m.lob_seed(x, y - 50, 0, -15, dmg)
				m.lob_seed(x, y - 45, 1, -12, dmg)
			elif wt % 200 == 0:
				state = DIVE
				timer = 0
		DIVE:
			timer += 1
			if timer == 50:
				state = SWIM
				timer = 1
			elif timer == 30:
				x = m.fh + (m.fp << 6)
				if x > max_x:
					x = max_x - 30
				if x < min_x:
					x = min_x + 30
				dir = 1 if x < m.fh else -1
				_snap()
			elif timer < 10:
				y += 10
			elif timer > 40:
				y -= 10
		SLAM:
			if timer == 110:
				state = SWIM
				timer = 30
			elif timer < 35:
				y -= 1
			elif timer >= 75:
				y += 1
				if t10 == 5:
					m.lob_seed(x, y - 45, -2, -10, dmg)
				elif t10 == 7 and hp < max_hp >> 1:
					m.lob_seed(x, y - 50, 0, -11, dmg)
				elif t10 == 9:
					m.lob_seed(x, y - 45, 2, -10, dmg)
			if timer > 20 and timer < 50 and t10 == 0:
				m.shoot_dart(x + dir * 20, y - 29, dir << 3, dmg, 2)
			if timer > 45 and timer < 50:
				off[2] += dir
			elif timer > 50 and timer < 55:
				off[2] -= dir
			timer += 1
		SINKING:
			y += 2
			off[3] += 1
			off[4] += 1
			off[5] += 1
			timer += 1
			if timer > 50:
				state = DEAD
				m.boss_killed.emit("river")
	# idle bobbing (always)
	if t10 < 4:
		y -= 1
	elif t10 < 8:
		y += 1
	if t10 > 4:
		off[3] -= 1
		off[4] += 1
	else:
		off[3] += 1
		off[4] -= 1
	var s := 1 if dir == -1 else -1
	parts = [x + s * off[0], y + off[3], x + s * off[1], y + off[4], x + s * off[2], y + off[5]]

# hit from the player's sword (u() ~L2174); returns true if it connected
func melee(m, fh: int, fi: int, fp: int, n: int) -> bool:
	if not (active and _abs(fi - (y - 40)) < 30 and _abs(fh + (fp << 4) - x) < 40):
		return false
	hp -= n
	m.flash("river")
	if hp <= 0:
		hp = 0
		timer = 0
		bar = 0
		state = SINKING
		for v in [[4, -10], [-4, -17], [-3, -18], [4, -19], [0, -10]]:
			m.drops.spawn_coin(x, y, v[0], v[1], 600)
	else:
		bar = 50
	m.fx("hit", fh + ((x - fh) >> 1), fi - 12, fp)
	m.text("-%d" % n, x, y - H, 0)
	return true
