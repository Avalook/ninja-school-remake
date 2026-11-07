# Behaviour of the 20 original monster kinds (static am[] names), ported from a.java al() ~L12112
# and its helpers N..U (~L13761-13923). One function per kind and state:
#   <kind>_spawn   al() case 5 (respawn ranges)      <kind>_active  al() case 2
#   <kind>_hurt    al() case 6 (hit reaction)        <kind>_attack  al() case 4
# All arithmetic is the original integer math; `m` is the EnemyManager (it provides the 25 Hz
# counter m.w, the player position m.fh/m.fi/m.fp, map size, sensing J/K and attack/projectile hooks).
@warning_ignore_start("integer_division")
extends RefCounted

const E := preload("res://scripts/enemies/enemy.gd")

const KIND_NAMES := [
	"one_eyed_ghost", "snail", "demon_bat", "one_legged_demon", "green_toad", "spider", "wasp",
	"scarecrow", "tiger_fish", "wind_ninja", "red_tiger_fish", "one_eyed_ghost_2", "desert_centipede",
	"golden_bat", "rat", "native", "red_wasp", "scorpion", "flower_demon", "death_flower_demon",
]

static func _abs(v: int) -> int:
	return v if v > 0 else -v

static func _cycle2(e) -> void:          # ka = (ka + 1) > 1 ? 0 : ka + 1
	e.frame += 1
	if e.frame > 1:
		e.frame = 0

static func _turn_at_range(e) -> void:   # patrol range check used by most walkers
	if e.x > e.max_x:
		e.dir = -1
	elif e.x < e.min_x:
		e.dir = 1

# =============================== state 5: respawn ranges (al() case 5 inner switch) ===============
static func on_spawn(e, _m) -> void:
	match e.type:
		0: _range(e, -20, 20)
		1: _range(e, -10, 10)
		2, 13: _home(e, 12)                    # flyers: jT/jU = home x/y (+12)
		3:                                     # Java fall-through 3 -> 4: jY ends as -3
			_range(e, -20, 20)
			e.jump = -3
		4:
			_range(e, -20, 20)
			e.jump = -3
		5:                                     # spider: vertical range on y
			e.min_x = e.y - 10
			e.max_x = e.y + 10
			e.timer = 1
		6, 16: _home(e, 0)
		7: e.state = E.IDLE
		8, 9, 11, 12: _range(e, -20, 20)
		10: _range(e, -30, 30)
		14: _range(e, -40, 40)
		15:
			_range(e, -20, 20)
			e.jump = -4
		17: _range(e, -30, 30)

static func _range(e, a: int, b: int) -> void:
	e.min_x = e.x + a
	e.max_x = e.x + b

static func _home(e, off: int) -> void:
	e.min_x = e.x + off
	e.max_x = e.y + off

# =============================== state 2: active ===================================================
static func active(e, m) -> void:
	match e.type:
		0: one_eyed_ghost_active(e, m)
		1, 17: crawl_patrol(e, m)              # U(n): snail, scorpion
		2: demon_bat_active(e, m)
		3, 15: hopper_active(e, m)             # one-legged demon, native
		4: green_toad_active(e, m)
		5: spider_active(e, m)
		6, 16: hover_patrol(e, m)              # P(n): wasp, red wasp
		8: tiger_fish_active(e, m)
		9, 12: slow_patrol(e, m)               # Q(n): wind ninja, desert centipede
		10: red_tiger_fish_active(e, m)
		11: one_eyed_ghost_2_active(e, m)
		13: golden_bat_active(e, m)
		14: rat_active(e, m)
		18, 19: flower_demon_active(e, m)
		# 7 scarecrow: no active behaviour (stands until hit)

static func one_eyed_ghost_active(e, m) -> void:
	if m.w % 4 == 0:
		_cycle2(e)
	e.x += e.dir
	_turn_at_range(e)
	m.sense_player(e)
	if m.w % 9 == 0:
		m.sense_companion(e)

static func crawl_patrol(e, m) -> void:        # U(n) ~L13906
	if m.w % 4 == 0:
		_cycle2(e)
		e.x += e.dir
	_turn_at_range(e)
	m.sense_player(e)
	if m.w % 5 == 0:
		m.sense_companion(e)

static func demon_bat_active(e, m) -> void:
	var w: int = m.w
	e.x += e.vx
	e.y += e.vy
	if e.x - e.min_x > 40:
		e.vx = -(w % 3 + 1)
	if e.x - e.min_x < -40:
		e.vx = w % 3 + 1
	if e.y - e.max_x > 40:
		e.vy = -(w % 3 + 1)
	if e.y - e.max_x < -40:
		e.vy = w % 3 + 1
	if w % 15 == 0:
		e.vx = w % 4 - 1
		e.vy = (w >> 1) % 4 - 1
	if w % 2 == 0:
		_cycle2(e)
		if e.frame == 0:
			e.y -= 3
		else:
			e.y += 2
	m.sense_player(e)

static func hopper_active(e, m) -> void:       # kinds 3 and 15 (identical code)
	e.x += e.timer
	if e.timer > 1:
		e.timer -= 1
	elif e.timer < -1:
		e.timer += 1
	e.y += e.jump
	e.jump += 1
	if e.jump > 4:
		e.jump = -4
		e.timer = e.dir * 3
	if _abs(e.jump) == 3:
		_turn_at_range(e)
		e.frame = 0
	else:
		e.frame = 1
	m.sense_player(e)
	if m.w % 9 == 0:
		m.sense_companion(e)

static func green_toad_active(e, m) -> void:
	e.x += e.aux
	if e.aux > 1:
		e.aux -= 1
	elif e.aux < -1:
		e.aux += 1
	e.y += e.jump
	e.jump += 1
	if e.jump > 3:
		e.jump = -3
		e.aux = e.dir << 2
		e.timer = 0
		e.y = m.spawn_y(e)
		e.state = E.IDLE
	if e.jump == -2:
		_turn_at_range(e)
		e.aux = e.dir * 6
		e.frame = 0
	else:
		e.frame = 1
	m.sense_player(e)
	if m.w % 9 == 0:
		m.sense_companion(e)

static func spider_active(e, m) -> void:
	if m.w % 4 == 0:
		_cycle2(e)
		e.y += e.timer
	if e.y > e.max_x:
		e.timer = -1
	elif e.y < e.min_x:
		e.timer = 1
	m.sense_player(e)

static func hover_patrol(e, m) -> void:        # P(n) ~L13838
	var w: int = m.w
	e.x += e.vx
	e.y += e.vy
	if e.x - e.min_x > 40:
		e.vx = -(w % 2 + 1)
	if e.x - e.min_x < -40:
		e.vx = w % 2 + 1
	if e.y - e.max_x > 40:
		e.vy = -(w % 2 + 1)
	if e.y - e.max_x < -40:
		e.vy = w % 2 + 1
	if w % 15 == 0:
		e.vx = w % 3 - 1
		e.vy = (w >> 1) % 3 - 1
	_cycle2(e)
	m.sense_player(e)
	if w % 5 == 0:
		m.sense_companion(e)

static func tiger_fish_active(e, m) -> void:
	_cycle2(e)
	e.x += m.w % 5 * e.dir
	_turn_at_range(e)
	m.sense_player(e)

static func slow_patrol(e, m) -> void:         # Q(n) ~L13868
	if m.w % 4 == 1:
		_cycle2(e)
		e.x += e.dir << 1
		_turn_at_range(e)
	m.sense_player(e)

static func red_tiger_fish_active(e, m) -> void:
	_cycle2(e)
	e.x += 5 * e.dir
	_turn_at_range(e)
	m.sense_player(e)

static func one_eyed_ghost_2_active(e, m) -> void:
	if m.w % 4 == 0:
		e.frame += 1
		e.timer = 0
		if e.frame > 1:
			e.frame = 0
			e.timer = 1
	e.x += e.timer * e.dir
	_turn_at_range(e)
	m.sense_player(e)
	if m.w % 9 == 0:
		m.sense_companion(e)

static func golden_bat_active(e, m) -> void:
	var w: int = m.w
	e.x += e.vx
	if e.x - e.min_x > 100:
		e.dir = -1
		e.vx = e.dir * (w % 4 + 1)
		e.x += e.dir << 2
	elif e.x - e.min_x < -100:
		e.dir = 1
		e.vx = e.dir * (w % 4 + 1)
		e.x += e.dir << 2
	if e.y - e.max_x > 20:
		e.vy = -(w % 4 + 1)
	elif e.y - e.max_x < -20:
		e.vy = w % 4 + 1
	else:
		e.vy = 0
	e.y += e.vy
	if w % 2 == 0:
		e.frame += 1
		if e.frame > 1:
			e.frame = 0
			e.y -= 12
		else:
			e.y += 12
	m.sense_player(e)

static func rat_active(e, m) -> void:
	_cycle2(e)
	if m.w % 2 == 0:
		e.x += e.dir << 2
	else:
		e.x += e.dir << 1
	_turn_at_range(e)
	m.sense_player(e)
	if m.w % 5 == 0:
		m.sense_companion(e)

static func flower_demon_active(e, m) -> void:  # kinds 18, 19: rooted, always face the player
	if m.w % 4 == 0:
		_cycle2(e)
	e.dir = -1 if e.x > m.fh else 1
	m.sense_player(e)
	if m.w % 9 == 0:
		m.sense_companion(e)

# =============================== state 6: hurt (al() case 6) =======================================
static func hurt(e, m) -> void:
	e.bar_timer = 100
	e.frame = 2
	match e.type:
		0: one_eyed_ghost_hurt(e, m)
		1, 12: _recoil(e, 5)                    # S(n)
		2: demon_bat_hurt(e, m)
		3, 4: _hop_recoil(e, m, 7)
		5: spider_hurt(e, m)
		6, 16: hover_recoil(e, m)               # O(n)
		7: scarecrow_hurt(e, m)
		8, 10: e.state = E.ATTACK               # fish strike back at once
		9: wind_ninja_hurt(e, m, 4)
		11: wind_ninja_hurt(e, m, 0)            # same code, timer reset to 0
		13: golden_bat_hurt(e, m)
		14, 17: _recoil(e, 3)                   # T(n)
		15: _hop_recoil(e, m, 3)
		18: flower_demon_hurt(e, m, false)
		19: flower_demon_hurt(e, m, true)

static func _recoil(e, ticks: int) -> void:     # S(n) (5 ticks) / T(n) (3 ticks)
	e.timer += 1
	e.x -= e.dir
	if e.timer == ticks:
		e.state = E.ACTIVE

static func one_eyed_ghost_hurt(e, _m) -> void:
	if e.aggro_cd < 30:
		e.x -= e.dir << 1
	else:
		e.x -= e.dir
	e.timer += 1
	if e.timer == 1:
		e.y -= 2
		return
	if e.timer != 5:
		return
	e.y += 2
	e.state = E.ACTIVE

static func demon_bat_hurt(e, _m) -> void:
	e.timer += 1
	e.x -= e.vx >> 3
	e.y -= e.vy >> 3
	if e.vx == 0:
		e.vx = 2
	if e.vy == 0:
		e.vy = 2
	if e.timer != 5:
		return
	e.state = E.ACTIVE
	e.timer = 0
	e.vy = 0
	e.vx = 0
	e.aggro_cd = 10

static func _hop_recoil(e, m, ticks: int) -> void:   # kinds 3, 4 (7 ticks) and 15 (3 ticks)
	e.x -= e.dir
	e.frame = 2
	e.timer += 1
	if e.timer != ticks:
		return
	e.y = m.spawn_y(e)
	e.state = E.ACTIVE
	e.jump = -4
	e.timer = e.dir * 3

static func spider_hurt(e, _m) -> void:
	e.timer += 1
	e.y -= 1
	if e.timer == 3:
		e.state = E.ACTIVE
		e.timer = 1
	e.frame = 2

static func hover_recoil(e, _m) -> void:      # O(n) ~L13817
	e.timer += 1
	e.x -= e.vx
	e.y -= e.vy
	if e.vx == 0:
		e.vx = 2
	if e.vy == 0:
		e.vy = 2
	if e.timer == 5:
		e.state = E.ACTIVE
		e.timer = 0
		e.vy = 0
		e.vx = 0
		e.aggro_cd = 0

static func scarecrow_hurt(e, m) -> void:
	e.timer += 1
	e.dir = -m.fp
	e.frame = 0 if e.timer % 4 > 1 else 2
	if e.timer != 8:
		return
	e.state = E.IDLE
	e.dir = 0
	e.vy = 0
	e.vx = 0
	e.aggro_cd = 0

static func wind_ninja_hurt(e, m, timer_after: int) -> void:
	e.x -= e.dir
	e.frame = 2
	e.timer += 1
	if e.timer != 6:
		return
	e.y = m.spawn_y(e)
	e.state = E.ATTACK
	e.timer = timer_after
	e.x += e.dir << 3

static func golden_bat_hurt(e, _m) -> void:
	e.timer += 1
	e.x -= e.vx >> 3
	e.y -= e.vy >> 3
	if e.vx == 0:
		e.vx = 2
	if e.vy == 0:
		e.vy = 2
	if e.timer != 5:
		return
	e.state = E.ACTIVE
	e.timer = 0
	e.dir = -1 if e.x > e.min_x else 1
	e.vx = e.dir << 3
	e.aggro_cd = 0

static func flower_demon_hurt(e, _m, counter_attack: bool) -> void:
	e.timer += 1
	e.x -= e.dir
	if e.timer != 4:
		return
	e.x += e.dir << 2
	if counter_attack:
		e.timer = 0
		e.state = E.ATTACK
	else:
		e.state = E.ACTIVE

# =============================== state 4: attack (al() case 4) =====================================
static func attack(e, m) -> void:
	if m.player_dug_in() or m.player_stealth():   # A == 14 || fz: lose the target (then still run the case)
		e.frame = 0
		e.state = E.ACTIVE
		e.timer = 0
		e.jump = 0
	match e.type:
		0: one_eyed_ghost_attack(e, m)
		1, 5: _touch(e, m, 5 if e.type == 5 else 1)
		2: demon_bat_attack(e, m)
		3, 15: _lunge(e, m, 3)                 # one-legged demon, native
		4: _lunge(e, m, 1)                     # toad: same lunge, back frame 1
		6, 16: swarm_attack(e, m)              # N(n)
		8: tiger_fish_attack(e, m)
		9: wind_ninja_attack(e, m)
		10: red_tiger_fish_attack(e, m)
		11: one_eyed_ghost_2_attack(e, m)
		12, 14: touch_and_back(e, m)           # R(n)
		13: golden_bat_attack(e, m)
		17, 18: spitter_attack(e, m)           # scorpion / flower demon shoot a dart
		19: death_flower_attack(e, m)

static func one_eyed_ghost_attack(e, m) -> void:
	e.timer += 1
	if e.timer == 2:
		m.strike(e)
	if (e.timer >> 2) % 2 == 0:
		e.x += e.dir
		e.frame = 3
	else:
		e.frame = 0
		e.x -= e.dir
	if e.timer != 12:
		return
	e.state = E.IDLE
	e.timer = 0

static func _touch(e, m, kind: int) -> void:
	m.hurt_player(e.attack, -m.fp)
	if kind == 1:          # snail
		e.state = E.IDLE
		e.timer = 0
		e.frame = 2
	else:                  # spider
		e.state = E.ACTIVE
		e.timer = 1

static func demon_bat_attack(e, m) -> void:
	var w: int = m.w
	if e.x < m.fh:
		e.vx = 3 + w % 4
	elif e.x > m.fh:
		e.vx = -(3 + w % 4)
	if e.y < m.fi:
		e.vy = w % 3
	elif e.y > m.fi:
		e.vy = -(w % 3)
	e.x += e.vx
	e.y += e.vy
	if w % 2 == 0:
		_cycle2(e)
	if _abs(e.x - m.fh) < 5 and _abs(e.y - m.fi) < 5:
		e.aggro_cd = 0
		m.hurt_player(e.attack, -m.fp)
		e.timer = 0
		e.state = E.ACTIVE
	if _abs(e.x - m.fh) <= 160 and _abs(e.y - m.fi) <= 160:
		return
	e.state = E.ACTIVE
	e.timer = 0
	e.vy = 0
	e.vx = 0
	e.aggro_cd = 0

static func _lunge(e, m, back_frame: int) -> void:   # kinds 3, 4, 15
	e.timer += 1
	if e.timer == 3:
		m.strike(e)
	if (e.timer >> 2) % 2 == 0:
		e.x += e.dir
		e.frame = 0
	else:
		e.frame = back_frame
		e.x -= e.dir
	if e.timer != 12:
		return
	e.y = m.spawn_y(e)
	e.state = E.IDLE
	e.jump = -4
	e.timer = 0

static func swarm_attack(e, m) -> void:        # N(n) ~L13761
	var w: int = m.w
	if e.x < m.fh:
		e.vx = w % 3 + 3
		e.dir = 1
	elif e.x > m.fh:
		e.vx = -(w % 3) - 3
		e.dir = -1
	if e.y < m.fi:
		e.vy = w % 3 + 3
	elif e.y > m.fi:
		e.vy = -(w % 3) - 3
	e.x += e.vx
	e.y += e.vy
	_cycle2(e)
	if _abs(e.x - m.fh) < 10 and _abs(e.y - m.fi) < 10:
		e.aggro_cd = 0
		m.strike(e)
		e.timer = 0
		e.state = E.ACTIVE
	if _abs(e.x - m.fh) > 160 or _abs(e.y - m.fi) > 160:
		e.state = E.ACTIVE
		e.timer = 0
		e.vy = 0
		e.vx = 0
		e.aggro_cd = 0

static func _near_player_body(e, m) -> bool:   # |jN - fh| < 16 && |jO - (fi - 16)| < 20
	return _abs(e.x - m.fh) < 16 and _abs(e.y - (m.fi - 16)) < 20

static func tiger_fish_attack(e, m) -> void:
	e.timer += 1
	if e.timer <= 10:
		if e.y > m.fi:
			e.y -= 4
		if e.x < m.fh:
			e.x += 2
		elif e.x > m.fh:
			e.x -= 2
		if _near_player_body(e, m):
			m.hurt_player(e.attack, -m.fp)
			e.timer = 10
	if e.timer == 10:
		e.jump = 1
	if e.timer > 10:
		e.y += e.jump
		e.jump += 1
		if e.y > m.spawn_y(e):
			e.y = m.spawn_y(e)
			e.jump = 0
			e.timer = 0
			e.state = E.ACTIVE
	e.frame = 0 if e.timer % 2 == 0 else 1

static func wind_ninja_attack(e, m) -> void:
	e.timer += 1
	if e.timer < 6:
		e.x += e.dir << 2
	elif e.timer == 6:
		if _abs(e.x + (e.dir << 3) - m.fh) < 13 + m.fl:
			m.hurt_player(e.attack, e.dir)
	elif e.timer < 12:
		e.x -= e.dir << 1
	elif e.timer == 12:
		e.y = m.spawn_y(e)
		e.state = E.IDLE
		e.jump = -4
		e.timer = 0
	e.frame = 1
	if e.timer <= 3 or e.timer >= 9 or e.timer % 2 != 0:
		return
	e.frame = 3

static func red_tiger_fish_attack(e, m) -> void:
	e.timer += 1
	e.frame = 3
	if e.timer <= 15:
		e.y -= 7
		if e.x < m.fh:
			e.x += 1
		elif e.x > m.fh:
			e.x -= 1
		if _near_player_body(e, m):
			m.hurt_player(e.attack, -m.fp)
			e.timer = 15
	if e.timer == 15:
		e.jump = 1
	if e.timer <= 15:
		return
	e.frame = 2
	e.y += e.jump
	e.jump += 1
	if _near_player_body(e, m):
		m.hurt_player(e.attack, -m.fp)
	if e.y <= m.spawn_y(e):
		return
	e.y = m.spawn_y(e)
	e.jump = 0
	e.timer = 0
	e.state = E.ACTIVE

static func one_eyed_ghost_2_attack(e, m) -> void:
	e.timer += 1
	if e.timer == 4 and e.y == m.fi:
		m.hurt_player(e.attack, e.dir)
	if e.timer < 8:
		e.x += e.dir << 1
		e.frame = 3
	else:
		e.frame = 0
		e.x -= e.dir << 1
	if e.timer != 12:
		return
	e.state = E.IDLE
	e.timer = 0
	e.aggro_cd = 25

static func touch_and_back(e, m) -> void:      # R(n) ~L13881
	m.hurt_player(e.attack, -m.fp)
	e.state = E.ACTIVE
	e.timer = 0
	e.frame = 2

static func golden_bat_attack(e, m) -> void:
	var w: int = m.w
	if e.x < m.fh:
		e.vx = 4 + w % 6
	elif e.x > m.fh:
		e.vx = -(4 + w % 6)
	if e.y < m.fi:
		e.vy = 2 + w % 4
	elif e.y > m.fi:
		e.vy = -(2 + w % 4)
	e.x += e.vx
	e.y += e.vy
	if w % 2 == 0:
		_cycle2(e)
	if _abs(e.x - m.fh) < 5 and _abs(e.y - m.fi) < 5:
		m.hurt_player(e.attack, -m.fp)
		e.state = E.ACTIVE
		e.timer = 0
		e.dir = -1 if e.x > e.min_x else 1
		e.vx = e.dir * (w % 4 + 1)
		e.aggro_cd = 10
	if _abs(e.x - m.fh) <= 130 and _abs(e.y - m.fi) <= 130:
		return
	e.state = E.ACTIVE
	e.timer = 0
	e.vy = 0
	e.vx = 0
	e.dir = -1 if e.x > e.min_x else 1
	e.vx = e.dir * (w % 4 + 1)
	e.aggro_cd = 0

static func spitter_attack(e, m) -> void:      # kinds 17, 18: b(x+8kb, y-12, 8kb, ke, 2)
	e.timer += 1
	e.frame = 3
	if e.timer == 3:
		m.shoot_dart(e.x + (e.dir << 3), e.y - 12, e.dir << 3, e.attack, 2)
		return
	if e.timer != 6:
		return
	e.state = E.IDLE
	e.timer = 0

static func death_flower_attack(e, m) -> void:  # kind 19: lobs seeds c(x, y-22, vx, vy, ke)
	e.timer += 1
	e.frame = 3
	if e.timer == 3:
		e.jump += 1
		if e.jump == 3:
			e.jump = 0
		if e.jump == 2:
			m.lob_seed(e.x, e.y - 22, e.dir, -10, e.attack)
		elif e.jump == 0:
			m.lob_seed(e.x, e.y - 22, e.dir << 1, -9, e.attack)
		else:
			m.lob_seed(e.x, e.y - 22, 0, -11, e.attack)
		m.fx("puff", e.x, e.y - 20)
		return
	if e.timer != 6:
		return
	e.state = E.IDLE
	e.timer = 0
