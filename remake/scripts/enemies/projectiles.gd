# All flying projectiles of the original:
#  - shurikens (cY=10 slots db cZ da dc dd de df; run() ~L9341-9480): thrown by the player (u() fg==1),
#    the companion (v(), strong) and the Toniku golem (ac() case 8); hit monsters, classmates, bosses
#    and - when not "strong" (df) - the player.
#  - darts (bP=10 slots bS bQ bR bT bU bV; b(5) ~L4605, O() ~L4801): monsters 17/18, classmates,
#    river monster, golem. kind bV selects /c/ar<kind><dir>.png; kinds < 3 can be parried by a sword swing.
#  - seeds (bW=8 slots bZ bX bY ca cb cc; c(5) ~L4633, P() ~L4825): monster 19 and the river monster.
# Positions are integer per 25 Hz tick (original), drawn interpolated.
@warning_ignore_start("integer_division")
extends RefCounted

const SP := preload("res://scripts/enemies/sprites.gd")
const E := preload("res://scripts/enemies/enemy.gd")

class Shot:
	var state := -1      # db: -1 free, 1 flying, 2..9 stuck in a wall, then drops (1 with dd=0)
	var x := 0
	var y := 0
	var vx := 0
	var vy := 0
	var frame := 0       # dc
	var strong := false  # df
	var kind := 0        # bV (darts)
	var dmg := 0         # bU / cc
	var px := 0.0
	var py := 0.0
	var spin := 0.0

var shurikens: Array = []
var darts: Array = []
var seeds: Array = []

func _init() -> void:
	for i in 10:
		shurikens.append(Shot.new())
		darts.append(Shot.new())
	for i in 8:
		var s := Shot.new()
		s.state = 0          # bZ: 0 = free (note: -1 after a hit keeps it alive, original quirk)
		seeds.append(s)

func clear() -> void:
	for s in shurikens + darts:
		s.state = -1
	for s in seeds:
		s.state = 0

# original a(int x, int y, int vx, int vy, boolean strong) ~L4717
func spawn_shuriken(x: int, y: int, vx: int, vy: int, strong: bool) -> void:
	for s in shurikens:
		if s.state == -1:
			s.state = 1; s.x = x; s.y = y; s.vx = vx; s.vy = vy; s.strong = strong
			s.px = x; s.py = y
			return

# original b(int x, int y, int vx, int dmg, int kind) ~L4605
func spawn_dart(x: int, y: int, vx: int, dmg: int, kind: int) -> void:
	for s in darts:
		if s.state == -1:
			s.state = 1; s.x = x; s.y = y; s.vx = vx; s.dmg = dmg; s.kind = kind
			s.px = x; s.py = y
			return

# original c(int x, int y, int vx, int vy, int dmg) ~L4633
func spawn_seed(x: int, y: int, vx: int, vy: int, dmg: int) -> void:
	for s in seeds:
		if s.state == 0:
			s.state = 1; s.x = x; s.y = y; s.vx = vx; s.vy = vy; s.dmg = dmg
			s.px = x; s.py = y
			return

static func _abs(v: int) -> int:
	return v if v > 0 else -v

func _on_screen(m, x: int, y: int) -> bool:
	return x >= m.fR and x <= m.fR + m.q and y >= m.fS and y <= m.fS + m.r

# ------------------------------------------------------------------ shurikens (run() ~L9341)
func tick_shurikens(m) -> void:
	for s in shurikens:
		s.px = s.x
		s.py = s.y
	for s in shurikens:
		if s.state == 1:
			if _fly_shuriken(m, s):
				return            # original `break` out of the whole loop
		elif s.state > 1:
			s.state += 1
			if s.state >= 10:
				s.state = 1
				s.vy = 12
				s.vx = 0

# returns true when the original code breaks out of the shuriken loop
func _fly_shuriken(m, s) -> bool:
	s.x += s.vx
	s.y += s.vy
	if not _on_screen(m, s.x, s.y):
		s.state = -1
		return false
	var f: int = m.flags(s.x, s.y)
	if s.vx > 0 and (f & 4) == 4:
		s.state = 2
		s.x = s.x / 24 * 24
	elif s.vx < 0 and (f & 8) == 8:
		s.state = 2
		s.x = s.x / 24 * 24 + 24
	s.frame += 1
	if s.frame > 1:
		s.frame = 0
	if s.vx == 0:
		return false
	var dmg: int = (S.F << 1) if s.strong else S.F
	# monsters
	for e in m.enemies:
		if e.state == E.WAITING or e.state == E.DYING or _abs(e.x - s.x) >= e.width or _abs(e.y - (e.height >> 1) - s.y) >= e.height:
			continue
		var silent := false
		if e.state == E.ACTIVE or e.state == E.IDLE or e.state == E.ATTACK:
			if e.type == 9 and s.vx * e.dir < 0:
				# Ninja gió parries: counter-attack and send the shuriken back
				e.state = E.ATTACK
				e.timer = 4
				s.vx = -s.vx
				s.vy = -s.vy
				s.x += s.vx
				s.y += s.vy
				silent = true
			else:
				e.hp -= dmg
				m.on_damaged(e)
				if e.hp <= 0:
					e.hp = 0
					e.timer = -5
					e.jump = s.vx
					e.aux = 0
					e.bar_timer = 0
					e.state = E.DYING
					m.kill(e)
					silent = true
				else:
					e.timer = 0
					e.dir = 1 if s.vx < 0 else -1
					e.state = E.HURT
					e.frame = 3
					s.state = -1
		if not silent:
			m.fx("hit", e.x, e.y - (e.height >> 1), e.dir)
			m.text("-%d" % dmg, e.x, e.y - e.height, 0)
		break
	# classmates (cs)
	if m.classmates:
		var cm = m.classmates
		for i in cm.count:
			if cm.state[i] != 5 and cm.state[i] != 1 and _abs(cm.x[i] - s.x) < 20 and _abs(cm.y[i] - 15 - s.y) < 20:
				if s.strong:
					# original passes cK[shuriken index] as direction (index bug kept: a.cK[var3_2])
					var qdir: int = cm.dir[shurikens.find(s)] if shurikens.find(s) < cm.count else 0
					cm.damage(m, i, S.F << 1, qdir)
					m.fx("hit", cm.x[i], cm.y[i] - 15, cm.dir[i])
					m.text("-%d" % (S.F << 1), cm.x[i], cm.y[i] - 32, 0)
					s.state = -1
					break
				cm.state[i] = 4
				cm.timer[i] = 0
				cm.target[i] = 0
				cm.dir[i] = -1 if s.vx > 0 else 1
				s.vx = -s.vx
				break
	# the player (not for strong / companion shurikens)
	if not s.strong and _abs(m.fh - s.x) < m.fl and _abs(m.fi - m.fm - s.y) < m.fm:
		if m.player_sword_swing():
			s.vx = -s.vx
			s.vy = -3
			return true
		m.hurt_player(S.F, -m.fp)
		s.state = -1
		return true
	# boar
	if m.boar and m.boar.active and _abs(m.boar.x - s.x) < m.boar.w >> 1 and _abs(m.boar.y - (m.boar.h >> 1) - s.y) < m.boar.h >> 1:
		m.boar.damage(m, S.F, -m.boar.dir)
		m.fx("hit", s.x, s.y, m.boar.dir)
		m.text("-%d" % S.F, m.boar.x, m.boar.y - m.boar.h, 0)
		s.state = -1
	# river monster deflects shurikens
	if m.river and m.river.active and _abs(m.river.x - s.x) < m.river.W >> 1 and _abs(m.river.y - (m.river.H >> 1) - s.y) < m.river.H >> 1:
		s.vx = -s.vx
		s.vy = -1
	# TODO(companion hf): original also tests the companion dC[0]/dD[0] here (deflect / d(F, -dM))
	return false

# ------------------------------------------------------------------ darts O() ~L4801
func tick_darts(m) -> void:
	for s in darts:
		s.px = s.x
		s.py = s.y
	for s in darts:
		if s.state != 1:
			continue
		s.x += s.vx
		if s.x < m.fR or s.x > m.fR + m.q or s.y < m.fS or s.y > m.fS + m.r:
			s.state = -1
		elif _abs(m.fh - s.x) < m.fl and _abs(m.fi - m.fm - s.y) < m.fm:
			if m.player_sword_swing() and s.kind < 3:
				s.vx = -s.vx
				s.x += s.vx
				return
			m.hurt_player(s.dmg, 1 if s.x < m.fh else -1)
			s.state = -1
			return

# ------------------------------------------------------------------ seeds P() ~L4825
func tick_seeds(m) -> void:
	for s in seeds:
		s.px = s.x
		s.py = s.y
	for s in seeds:
		if s.state == 0:
			continue
		s.x += s.vx
		s.y += s.vy
		s.vy += 1
		if s.y > m.fS + m.r:
			s.state = 0
		if _abs(m.fh - s.x) < m.fl and _abs(m.fi - m.fm - s.y) < m.fm:
			m.hurt_player(s.dmg, 1 if s.x < m.fh else -1)
			s.state = -1
			return

# ------------------------------------------------------------------ draw (R() ~L8056-8074)
func draw(ci: CanvasItem, alpha: float, dt: float) -> void:
	var pt := 2 if S.eZ else 0
	for s in shurikens:
		if s.state != -1:
			var p := Vector2(lerpf(s.px, s.x, alpha), lerpf(s.py, s.y, alpha))
			if s.state == 1 and s.vx != 0:
				s.spin += dt * 30.0 * signf(s.vx)
			var i := SP.get_image("/c/pt%d.png" % (pt + s.frame))
			if i.is_empty():
				continue
			# polish: spin the shuriken around its centre (original swaps 2 frames)
			ci.draw_set_transform(p, s.spin, Vector2.ONE)
			var sz := Vector2(i["w"], i["h"])
			ci.draw_texture_rect(i["tex"], Rect2(-Vector2(int(sz.x / 2), int(sz.y / 2)), sz), false)
			ci.draw_set_transform(Vector2.ZERO)
	for s in darts:
		if s.state != -1:
			var p := Vector2(lerpf(s.px, s.x, alpha), lerpf(s.py, s.y, alpha))
			SP.draw(ci, "/c/ar%d%d.png" % [s.kind, 0 if s.vx > 0 else 1], p.x, p.y, 3)
	for s in seeds:
		if s.state != 0:
			var p := Vector2(lerpf(s.px, s.x, alpha), lerpf(s.py, s.y, alpha))
			SP.draw(ci, "/e/b.png", p.x, p.y, 3)
