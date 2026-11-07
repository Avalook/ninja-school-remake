# Wild boar boss (maps 4 and 5). Original globals gQ gS gT gU gV gW gX gY gZ ha hb hc hd he gR;
# setup i(n) ~L3860, AI in run() ~L9772-9870, charge trigger N() ~L4581, damage c(int,int) ~L2932,
# draw ~L7851. gV == 22 (fell off after death) is what the map-4 cutscene D(0) step 3 waits for.
@warning_ignore_start("integer_division")
extends RefCounted

const SP := preload("res://scripts/enemies/sprites.gd")

const ROAM := 1       # gV: pause, then pick a new walk target
const WALK := 2
const CHARGE := 5
const DEATH := 6
const STAGGER := 7
const GONE := 22

var active := false   # gQ
var x := 0            # gS
var y := 0            # gT
var vx := 0           # gU
var state := ROAM     # gV
var dir := -1         # gW
var frame := 0        # gX (ey[0..2])
var w := 55           # gY (ey[0] width)
var h := 35           # gZ
var timer := 0        # ha (also fall speed while dying)
var target_x := 0     # hb (also stagger length after a hit)
var hp := 1200        # hc
var max_hp := 1200    # hd
var bar := 0          # he
var contact_dmg := 30 # gR
var prev_x := 0.0
var prev_y := 0.0
var flash := 0.0
var squash := 0.0

func setup(st: Dictionary) -> void:
	active = bool(st.get("gQ", false))
	x = int(st.get("gS", 0)); y = int(st.get("gT", 0)); vx = int(st.get("gU", 0))
	state = int(st.get("gV", ROAM)); dir = int(st.get("gW", -1)); frame = int(st.get("gX", 0))
	w = int(st.get("gY", 55)); h = int(st.get("gZ", 35)); timer = int(st.get("ha", 0))
	target_x = int(st.get("hb", 0)); hp = int(st.get("hc", 1200)); max_hp = int(st.get("hd", 1200))
	bar = int(st.get("he", 0)); contact_dmg = int(st.get("gR", 30))
	prev_x = x; prev_y = y

static func _abs(v: int) -> int:
	return v if v > 0 else -v

func _enraged() -> bool:
	return hp < max_hp >> 1

# N(): start a charge when the player is level with the boar and within 200 px
func _try_charge(m) -> void:
	if not m.player_stealth() and not m.player_dug_in() and _abs(m.fi - y) < 30 and _abs(m.fh - x) < 200:
		state = CHARGE
		dir = 1 if x < m.fh else -1
		target_x = m.fh + (dir << 5)
		vx = dir * 6
		if _enraged():
			vx = dir << 3

func tick(m) -> void:
	prev_x = x; prev_y = y
	if not active:
		return
	if bar > 0:
		bar -= 1
	var wt: int = m.w
	match state:
		ROAM:
			if timer > 0:
				timer -= 1
			else:
				var d := wt % 80 - 40
				d = d + 20 if d > 0 else d - 20
				target_x = x + d
				dir = 1 if d > 0 else -1
				vx = dir << 2
				if _enraged():
					vx = dir << 3
				state = WALK
				if wt % 5 == 0:
					_try_charge(m)
			frame = 0
			if wt % 10 == 6:
				m.fx("dust", x + dir * 28, y - 5, -dir)    # e(-gW, gS + 28gW, gT - 5)
		WALK:
			if wt % 8 == 0:
				_try_charge(m)
			if not _floor_ahead(m):
				state = ROAM
				timer = 0
				return
			if _abs(x - target_x) < 8:
				state = ROAM
				timer = 20
			if wt % 2 == 0:
				x += vx
				frame = 0
			else:
				frame = 1
		CHARGE:
			if not _floor_ahead(m):
				state = ROAM
				timer = 0
				return
			if _abs(x - target_x) < 4:
				state = ROAM
				timer = 35
			if _abs(m.fi - y) < 18 and _abs(x + (dir << 4) - m.fh) < 10:
				m.hurt_player(contact_dmg, dir)
				state = ROAM
				timer = 45
				if _enraged():
					timer = 18
			x += vx
			frame = wt % 3
		STAGGER:
			x -= dir
			frame = 2
			timer += 1
			if timer == 4:
				state = ROAM
				timer = target_x
				if timer > 0 and _enraged():
					timer = 5
		DEATH:
			y += timer
			timer += 1
			x += vx
			if vx > 1:
				vx -= 1
			if vx < -1:
				vx += 1
			if not (y <= m.fS + m.r + h and y < m.iS + h):
				timer = 0
				state = GONE

func _floor_ahead(m) -> bool:   # g(gS + (gW << 4), gT + 1, 2)
	return (m.flags(x + (dir << 4), y + 1) & 2) == 2

# c(int dmg, int dir) ~L2932: only while roaming/walking; returns true when killed
func damage(m, dmg: int, d: int) -> bool:
	if state != ROAM and state != WALK:
		return false
	hp -= dmg
	flash = 0.08
	squash = 0.25
	if hp <= 0:
		hp = 0
		timer = -5
		vx = -(d << 3)
		bar = 0
		state = DEATH
		for v in [[4, -6], [-5, -7], [-4, -8], [5, -9], [0, -10]]:
			m.drops.spawn_coin(x, y, v[0], v[1], 50)
		if int(S.O) == 5:
			m.drops.spawn_scroll(x, y)
		m.boss_killed.emit("boar")
		return true
	timer = 0
	dir = d
	state = STAGGER
	bar = 10
	frame = 0
	target_x = 0 if dmg <= int(S.F) else 2 + m.w % 5
	return false

# draw ~L7851: ey[4] shadow under ey[gX]; ey[3] sweat drop while staggered; images face right
func draw(ci: CanvasItem, alpha: float, wt: int, dt: float) -> void:
	if not active or state == GONE:
		return
	flash = maxf(0.0, flash - dt)
	squash = maxf(0.0, squash - dt * 2.0)
	var px := lerpf(prev_x, x, alpha)
	var py := lerpf(prev_y, y, alpha)
	var col := Color(3, 3, 3) if flash > 0.0 else Color.WHITE
	var mirror := dir != 1
	var lift := 0
	if frame == 0:
		lift = -2
		if state == ROAM and wt % 10 > 5:
			lift = -1
		SP.draw(ci, "/mob/b04.png", px, py + 1, 33, mirror)
	SP.draw(ci, "/mob/b0%d.png" % frame, px, py + lift + 1, 33, mirror, col, Rect2(), squash)
	if state == STAGGER:
		if dir == 1:
			SP.draw(ci, "/mob/b03.png", px + 10, py - 25 + 1, 0)
		else:
			SP.draw(ci, "/mob/b03.png", px - 10, py - 25 + 1, 24, true)
	if bar != 0:
		SP.hp_bar(ci, px, py - h - 7, w, hp, max_hp, Color8(0x33, 0, 0x11), Color8(0xEE, 0, 0), Color8(0x44, 0, 0x22))
