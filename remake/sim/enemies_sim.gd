# EnemiesSim: the rules of every hostile thing of a map — monsters (20 kinds), rival students, the three
# bosses, their projectiles, the shared shuriken system and the loot they drop. RefCounted: no Node, no
# drawing (view/enemies_view.gd draws it and keeps the old EnemyManager API for main / world / player).
#
# Fidelity: all decisions run on the original fixed 25 Hz step (sim/sim.gd step(), the same tick that advances
# S.w) with the original integer math and the original update order of run() (shurikens ~L9341,
# darts O(), seeds P(), monsters al() ~L12112, students aa(), boar, river monster, golem ac(), coins W(),
# potions ab(), scroll). prev_x/prev_y (px/py) keep the previous 25 Hz state for the view to interpolate.
#
# Presentation is not called directly: fx / floating text / popup / hit flash are events
# ({"t": "fx"|"text"|"popup"|"flash", ...}). With `on_event` set (the view) each event is delivered at the
# moment it happens, so FX keep their exact order relative to the player's own FX (fx_layer seeds each FX
# with randf() in call order); without it (headless tests) they collect in `events`.
# `world` is sim/world_sim.gd (flags_at, cam_pos / view_size = the camera given as input, player.hurt =
# sim/player_sim.gd), wired by sim/sim.gd. Loot rules are sim/enemies/drops.gd, drawn by view/drops_view.gd.
#
# Contract (archive/docs/REMAKE_PLAN.md): setup(map), hit(area, damage, dir) -> index | -1, boss_state(),
# spawn_projectile(x, y, vx, vy, strong), signal enemy_killed(type, pos); damages the player through
# world.player.hurt(dmg, dir) where dir is the push direction of the original a(int n, int dir).
# Player state is read from the original variables the player module publishes into S
# (fh fi fp A fz fg; companion gq gr gu).
@warning_ignore_start("integer_division")
extends RefCounted

signal enemy_killed(type: int, pos: Vector2)
signal classmate_killed(kind: int, pos: Vector2)
signal boss_killed(boss: String)          # "boar" | "river" | "golem"
signal player_hit(damage: int, dir: int)  # every attack that reached the player (for tests/FX)

const Enemy := preload("res://sim/enemies/enemy.gd")
const MT := preload("res://sim/enemies/monster_types.gd")
const Drops := preload("res://sim/enemies/drops.gd")
const Shots := preload("res://sim/enemies/projectiles.gd")
const Classmates := preload("res://sim/enemies/classmates.gd")
const Boar := preload("res://sim/enemies/boss_boar.gd")
const River := preload("res://sim/enemies/boss_river.gd")
const Golem := preload("res://sim/enemies/boss_golem.gd")

var world = null              # World node (group "world"); null in headless tests
var test_player = null        # tests: object with hurt(dmg, dir) used when there is no world
var events: Array = []        # presentation events (only when on_event is not set)
var on_event: Callable        # view: receives each presentation event immediately

var map := {}
var map_w := 0
var map_h := 0
var iR := 0                   # map width in px
var iS := 0                   # map height in px
var _flags: Array = []

var enemies: Array = []       # Enemy records (original slots 0..jK-1)
var spawn_x: Array = []       # km[group][i]
var spawn_y: Array = []       # kn[group][i]
var slot_used: Array = []     # ko[group][i]
var respawn_delay := 70       # jL
var never_respawn := false    # T: killed monsters are removed for good (tutorial map)
var sprite_sets: Array = []   # jJ: [group][facing 0 right / 1 left][frame] -> image path or ""

var drops = Drops.new()
var shots = Shots.new()
var classmates = null
var boar = null
var river = null
var golem = null

# per-tick snapshot of the original globals the AI reads
var w := 0
var fh := 0
var fi := 0
var fp := 1
var fl := 11
var fm := 16
var fk := 32
var fR := 0
var fS := 0
var q := 480
var r := 270

var _last_sync := {}

# ============================================================== setup
func setup(m: Dictionary) -> void:
	map = m
	map_w = int(m.get("w", 0))
	map_h = int(m.get("h", 0))
	iR = map_w * 24
	iS = map_h * 24
	_flags = m.get("flags", [])
	var st: Dictionary = {}
	var stat = Data.static_data if Data and Data.static_data is Dictionary else {}
	st.merge(stat)
	st.merge(m.get("state", {}), true)
	enemies.clear()
	var count := int(st.get("jK", 0))
	for n in count:
		var e := Enemy.new()
		e.load_from(st, n)
		enemies.append(e)
	spawn_x = _ints(st.get("km", []))
	spawn_y = _ints(st.get("kn", []))
	slot_used = []
	var ko = st.get("ko")
	for row in (ko if ko is Array else []):
		slot_used.append((row as Array).map(func(v): return bool(v)) if row is Array else [])
	respawn_delay = int(st.get("jL", 70))
	never_respawn = bool(st.get("T", false))
	sprite_sets = []
	var jj = st.get("jJ", [])
	if count > 0 and jj is Array:
		for grp in jj:
			var faces := []
			for face in grp:
				var frames := []
				for img in face:
					frames.append(String(img["p"]) if img is Dictionary else "")
				faces.append(frames)
			sprite_sets.append(faces)
	drops.clear()
	shots.clear()
	classmates = Classmates.new()
	if not classmates.setup(st):
		classmates = null
	boar = Boar.new()
	boar.setup(st)
	river = River.new()
	river.setup(st)
	river.active = river.active and not bool(S.eI)
	golem = Golem.new()
	golem.setup(st)
	golem.active = golem.active and not bool(S.eK)
	_last_sync = {}
	_sync_to_state()
	_event({"t": "setup", "monsters": enemies.size(), "classmates": classmates.count if classmates else 0})

static func _ints(a) -> Array:
	var o := []
	if a is Array:
		for v in a:
			o.append(_ints(v) if v is Array else int(v))
	return o

# ============================================================== the 25 Hz step
func step() -> void:
	step_projectiles()
	step_actors()

# original order inside one tick: projectiles (shuriken loop, O(), P()) run BEFORE the player's own
# movement R(), monsters/bosses/loot AFTER it — the test harness feeds the player position accordingly
func step_projectiles() -> void:
	_read_globals()
	_sync_from_state()
	shots.tick_shurikens(self)
	shots.tick_darts(self)
	shots.tick_seeds(self)

func step_actors() -> void:
	_read_globals()
	_sync_from_state()
	_update_monsters()
	if classmates:
		classmates.tick(self)
	boar.tick(self)
	river.tick(self)
	golem.tick(self)
	drops.tick(self)
	_sync_to_state()

func _read_globals() -> void:
	w = int(S.w)
	fh = int(S.fh)
	fi = int(S.fi)
	fp = int(S.fp)
	fl = int(S.fl)
	fm = int(S.fm)
	fk = int(S.fk)
	if world and world.get("cam_pos") != null:
		fR = int(world.cam_pos.x)
		fS = int(world.cam_pos.y)
		var vs: Vector2 = world.view_size() if world.has_method("view_size") else Vector2(480, 270)
		q = int(vs.x)
		r = int(vs.y)
	else:
		fR = int(S.fR)
		fS = int(S.fS)
		q = int(S.q)
		r = int(S.r)

# Boss variables are mirrored into S under their original names, both ways: story/cutscene code written
# against the original (opcode 28 sets S.hu = 1, the map-4 script waits for S.gV == 22, a cutscene sets
# S.hc …) keeps working. A value changed in S since our last write is adopted before the next step.
const SYNC := {
	"gQ": ["boar", "active"], "gV": ["boar", "state"], "gS": ["boar", "x"], "gT": ["boar", "y"],
	"hc": ["boar", "hp"], "hd": ["boar", "max_hp"],
	"hM": ["river", "active"], "ia": ["river", "state"], "hS": ["river", "x"], "hT": ["river", "y"],
	"hP": ["river", "hp"], "hQ": ["river", "max_hp"],
	"hl": ["golem", "active"], "hu": ["golem", "state"], "hs": ["golem", "x"], "ht": ["golem", "y"],
	"hp": ["golem", "hp"], "hq": ["golem", "max_hp"],
}

func _sync_from_state() -> void:
	for k in SYNC:
		var v = S.get(k)
		if _last_sync.has(k) and v != _last_sync[k]:
			get(SYNC[k][0]).set(SYNC[k][1], v)

func _sync_to_state() -> void:
	for k in SYNC:
		var v = get(SYNC[k][0]).get(SYNC[k][1])
		S.set(k, v)
		_last_sync[k] = S.get(k)

# ============================================================== al(): monsters
func _update_monsters() -> void:
	for e in enemies:
		e.prev_x = e.x
		e.prev_y = e.y
		if e.aggro_cd <= 30:
			e.aggro_cd += 1
		if e.bar_timer > 0:
			e.bar_timer -= 1
		match e.state:
			Enemy.WAITING: _respawn(e)
			Enemy.ACTIVE: MT.active(e, self)
			Enemy.HURT: MT.hurt(e, self)
			Enemy.ATTACK: MT.attack(e, self)
			Enemy.IDLE:
				e.timer += 1
				if e.timer >= 35:
					e.timer = 0
					e.state = Enemy.ACTIVE
				e.frame = 0
				sense_player(e)
			Enemy.DYING: _dying(e)
			Enemy.FALLING:
				e.y += e.timer
				e.timer += 1
				e.frame = 2
				if e.y > spawn_y_of(e):
					if not (e.type in [2, 6, 16, 13]):
						e.y = spawn_y_of(e)
					e.state = Enemy.IDLE

# al() case 5: wait jL ticks, then appear at a random free spawn point of the group (w % count)
func _respawn(e) -> void:
	e.timer += 1
	if e.timer < respawn_delay:
		return
	e.timer = 0
	var pts: Array = spawn_x[e.group]
	var i := w % pts.size()
	if slot_used[e.group][i]:
		e.timer = respawn_delay
		return
	e.x = pts[i]
	e.y = spawn_y[e.group][i]
	slot_used[e.group][i] = true
	e.slot = i
	e.state = Enemy.ACTIVE
	e.hp = e.max_hp
	MT.on_spawn(e, self)
	e.snap()

# al() case 1: knocked out — flies, bounces off floors twice, splashes, leaves the map
func _dying(e) -> void:
	e.timer += 1
	e.y += e.timer
	if w % 2 == 0:
		if e.jump > 1:
			e.jump -= 1
		elif e.jump < -1:
			e.jump += 1
	e.x += e.jump
	e.frame = 2
	if e.y >= iS or e.x < 0 or e.x >= iR:
		e.timer = -300
		e.state = Enemy.REMOVED if never_respawn else Enemy.WAITING
		slot_used[e.group][e.slot] = false
		return
	if (e.aux == 0 or e.aux < 3 and e.timer > 7) and (flags(e.x, e.y) & 2) == 2:
		e.timer = -4 if e.timer > 4 else -e.timer
		e.aux += 1
	if (flags(e.x, e.y) & 64) == 64 and e.y % 24 > 8:
		fx("splash", e.x, e.y / 24 * 24 + 8)

func spawn_y_of(e) -> int:     # kn[kk[n]][ki[n]]
	return spawn_y[e.group][e.slot]

static func _abs(v: int) -> int:
	return v if v > 0 else -v

# J(n) ~L11613: the player walks into the sense box -> attack
func sense_player(e) -> void:
	if e.aggro_cd <= 30:
		return
	if _in_sense_box(e, fh, fi) and not player_dead() and not player_stealth() and not player_dug_in():
		e.aggro_cd = 0
		e.state = Enemy.ATTACK
		e.timer = 0

# K(n) ~L11628: same for the companion (gq, gr; gu == 14 is hidden)
func sense_companion(e) -> void:
	if e.aggro_cd <= 30:
		return
	if int(S.gu) != 14 and _in_sense_box(e, int(S.gq), int(S.gr)):
		e.aggro_cd = 0
		e.state = Enemy.ATTACK
		e.timer = 0
		e.hit_companion = true

func _in_sense_box(e, px: int, py: int) -> bool:
	var left: int = e.x if e.dir > 0 else e.x - e.sense_w
	var right: int = e.x + e.sense_w if e.dir >= 0 else e.x
	return px >= left and px <= right and py >= e.y - e.sense_h and py <= e.y + e.sense_h

# ============================================================== player / companion interface
func player_dead() -> bool:
	return int(S.A) == 6

func player_dug_in() -> bool:
	return int(S.A) == 14

func player_stealth() -> bool:
	return bool(S.fz)

func player_sword_swing() -> bool:   # A == 5 && fg == 0: a sword swing parries darts/shurikens
	return int(S.A) == 5 and int(S.fg) == 0

func hurt_player(dmg: int, d: int) -> void:     # original a(int dmg, int dir)
	player_hit.emit(dmg, d)
	var p = world.player if world and world.get("player") else test_player
	if p and p.has_method("hurt"):
		p.hurt(dmg, d)

# monster melee: kl ? b(ke, kb) (companion) : a(ke, kb) (player)
func strike(e) -> void:
	if e.hit_companion:
		e.hit_companion = false
		hurt_companion(e.attack, e.dir)
	else:
		hurt_player(e.attack, e.dir)

# original b(int dmg, int dir) ~L2891 — companion knockback (writes the original companion vars)
func hurt_companion(dmg: int, d: int) -> void:
	var c = world.get("companion") if world else null
	if c and c.has_method("hurt"):
		c.hurt(dmg, d)
		return
	S.ij = -1
	S.gs = d << 2
	S.gt = -2
	S.gD = 0
	S.gE = 0
	S.gu = 7
	fx("hit", int(S.gq), int(S.gr) - 12, -int(S.gC))
	text("-%d" % dmg, int(S.gq), int(S.gr) - int(S.gx), 0)

# ============================================================== projectiles
func spawn_projectile(x: float, y: float, vx: float, vy: float, strong: bool) -> void:
	shots.spawn_shuriken(int(x), int(y), int(vx), int(vy), strong)

func shoot_dart(x: int, y: int, vx: int, dmg: int, kind: int) -> void:
	shots.spawn_dart(x, y, vx, dmg, kind)

func lob_seed(x: int, y: int, vx: int, vy: int, dmg: int) -> void:
	shots.spawn_seed(x, y, vx, vy, dmg)

# ============================================================== damage
# Player melee, original u() ~L2112 (the hit part). `area` is the player's attack_box(), `dir` = -fp
# (the direction the target is turned to / knocked towards, as in k(n, dmg, -fp)).
func hit(area: Rect2, damage: int, dir: int) -> int:
	var pfp := -dir
	var x0 := int(area.position.x)
	var x1 := int(area.end.x)
	var y0 := int(area.position.y)
	var y1 := int(area.end.y)
	var pfh := x0 + 20 if pfp > 0 else x0 + 40
	var pfi := y1 - 5
	# A(): first monster whose feet are inside the box (any state but waiting)
	var idx := -1
	for e in enemies:
		if e.state != Enemy.WAITING and e.state != Enemy.REMOVED and e.x >= x0 and e.x <= x1 and e.y >= y0 and e.y <= y1:
			idx = e.index
			damage_monster(e, damage, dir)
			fx("hit", e.x, e.y - (e.height >> 1), pfp)
			text("-%d" % damage, e.x, e.y - e.height, 0)
			break
	# boar
	if boar.active and _abs(pfi - boar.y) < 30 and _abs(pfh + (pfp << 4) - boar.x) < 40:
		if not boar.damage(self, damage, -pfp):
			fx("hit", pfh + ((boar.x - pfh) >> 1), pfi - 12, pfp)
			text("-%d" % damage, boar.x, boar.y - boar.h, 0)
	# rival students
	if classmates:
		for i in classmates.count:
			if _abs(pfi - classmates.y[i]) <= 12 and _abs(pfh + (pfp << 4) - classmates.x[i]) < 24:
				classmates.damage(self, i, damage, -pfp)
				fx("hit", classmates.x[i], classmates.y[i] - 15, pfp)
				text("-%d" % damage, classmates.x[i], classmates.y[i] - 28, 0)
				break
	river.melee(self, pfh, pfi, pfp, damage)
	# TODO(companion hf): original u() also lets the sword hit the rival companion dC[0] (d(n, fp))
	golem.melee(self, pfh, pfi, pfp, damage)
	return idx

# original k(int n, int dmg, int dir) ~L11644; returns true if the monster died
func damage_monster(e, dmg: int, d: int) -> bool:
	if not (e.state == Enemy.ACTIVE or e.state == Enemy.IDLE or e.state == Enemy.ATTACK or e.state == Enemy.FALLING):
		return false
	e.hp -= dmg
	on_damaged(e)
	if e.hp <= 0:
		e.hp = 0
		e.timer = -5
		e.jump = -(d << 3)
		e.aux = 0
		e.bar_timer = 0
		e.state = Enemy.DYING
		kill(e)
		return true
	if e.state == Enemy.FALLING:
		e.timer -= 7
		e.dir = d
	else:
		e.timer = 0
		e.dir = d
		e.state = Enemy.HURT
		e.frame = 3
	return false

func on_damaged(e) -> void:      # presentation only: white flash + squash in the view
	flash("enemy", e.index, 0.3)

# original L(n) ~L11672: EXP, loot (w % 20), quest scroll; quest kill counters are the story's job
func kill(e) -> void:
	gain_exp(int(S.aq[e.type]))
	var roll := w % 20
	if roll > 15:
		drops.spawn_item(e.x, e.y, -(e.dir << 3), e.attack << 2, (w + 1) % 2)
	elif roll == 15:
		drops.spawn_coin(e.x, e.y, -(e.dir << 2), -4, (e.coin << 3) + w % 10)
	else:
		var v: int = ((e.type << 2) / 10 + 1) * 10
		drops.spawn_coin(e.x, e.y, -(e.dir << 1), -6, v)
		drops.spawn_coin(e.x, e.y, -(e.dir << 4), -3, v)
	if e.type == int(S.fB) - 100 and w % 10 < int(S.fC):
		drops.spawn_scroll(e.x, e.y)
		fx("burst", e.x, e.y - 28)
	enemy_killed.emit(e.type, Vector2(e.x, e.y))

# ============================================================== queries for other modules
func boss_state() -> Dictionary:
	return {
		"boar": {"active": boar.active, "state": boar.state, "hp": boar.hp, "max_hp": boar.max_hp, "pos": Vector2(boar.x, boar.y)},
		"river": {"active": river.active, "state": river.state, "hp": river.hp, "max_hp": river.max_hp, "pos": Vector2(river.x, river.y)},
		"golem": {"active": golem.active, "state": golem.state, "hp": golem.hp, "max_hp": golem.max_hp, "pos": Vector2(golem.x, golem.y)},
	}

func start_golem() -> void:      # cutscene opcode 28 (hu = 1)
	golem.start()
	_sync_to_state()

func alive_count(type := -1) -> int:
	var n := 0
	for e in enemies:
		if e.state != Enemy.WAITING and e.state != Enemy.REMOVED and e.state != Enemy.DYING and (type < 0 or e.type == type):
			n += 1
	return n

# ============================================================== map / fx helpers
func flags(x: int, y: int) -> int:     # original i(x, y): iU[y / jh * iP + x / jh]
	if world and world.has_method("flags_at"):
		return world.flags_at(x, y)
	if x < 0 or y < 0 or x >= iR or y >= iS:
		return 0
	return int(_flags[y / 24 * map_w + x / 24])

# presentation events
func _event(ev: Dictionary) -> void:
	if on_event.is_valid():
		on_event.call(ev)
	else:
		events.append(ev)

# original u(int n) a.java L2089 (was S.add_exp in scripts/core/state.gd): gain EXP; at most one level per gain,
# N resets to 0 (no carry-over), E += aj[M-2], F += 2, H += al[M-2], HP full, MP refilled (max MP unchanged),
# modal popup (d = 7). (The demo-build lock at level 7, Midlet.a, does not apply to the PC version.)
# Same order of effects as before: floating text, power FX, message, S.changed.
func gain_exp(n: int) -> void:
	S.N += n
	var p := Vector2(S.fh, S.fi - 32)
	if S.N > int(S.ak[clampi(S.M - 1, 0, S.ak.size() - 1)]) and S.M < S.ak.size():
		S.N = 0
		S.M += 1
		S.E += int(S.aj[S.M - 2])
		S.F += 2
		S.H += int(S.al[S.M - 2])
		S.G = S.H
		S.I = S.J
		_event({"t": "text", "s": "LEVEL UP", "p": p, "c": 0, "color": Color(1, 0.85, 0.2)})
		_event({"t": "fx", "kind": "power", "p": Vector2(S.fh, S.fi - 20), "d": 1})
		_event({"t": "message", "s": "Chúc mừng! Bạn đạt được cấp %d\nHP: %d\nSức đánh: %d" % [S.M, S.H, S.E + (S.E >> 2) * (S.x + 1)]})
	else:
		_event({"t": "text", "s": "+%d" % n, "p": p, "c": 0, "color": Color(0.6, 1, 0.6)})
	S.changed.emit()

func fx(kind: String, x: int, y: int, d := 1) -> void:
	_event({"t": "fx", "kind": kind, "p": Vector2(x, y), "d": d})

func text(s: String, x: int, y: int, color_index: int) -> void:
	_event({"t": "text", "s": s, "p": Vector2(x, y), "c": color_index})

func popup(s: String) -> void:
	_event({"t": "popup", "s": s})

# who: "enemy" (i = slot) | "classmate" (i = slot) | "boar" | "river" | "golem"; squash 0 = flash only
func flash(who: String, i := -1, squash := 0.0) -> void:
	_event({"t": "flash", "who": who, "i": i, "squash": squash})
