# EnemiesView: draws every hostile thing of a map (monsters, the three bosses, rival students, projectiles,
# loot) from the rules in sim/enemies_sim.gd, interpolating between the last two 25 Hz states.
# Keeps the old EnemyManager API for main / world / player_sim / tests (setup, step_projectiles,
# step_actors, hit, spawn_projectile, boss_state, start_golem, enemies, the signals...): each call is
# forwarded to the sim. Presentation events of the sim (fx, floating text, popup, hit flash) arrive through
# sim.on_event at the moment they happen and are turned into world FX / UI here.
# Hit flash and squash timers live here (they were fields of the sim records before the split).
@warning_ignore_start("integer_division")
extends Node2D

signal enemy_killed(type: int, pos: Vector2)
signal classmate_killed(kind: int, pos: Vector2)
signal boss_killed(boss: String)          # "boar" | "river" | "golem"
signal player_hit(damage: int, dir: int)  # every attack that reached the player (for tests/FX)

const Sim := preload("res://sim/enemies_sim.gd")
const Enemy := preload("res://sim/enemies/enemy.gd")
const Boar := preload("res://sim/enemies/boss_boar.gd")
const River := preload("res://sim/enemies/boss_river.gd")
const Golem := preload("res://sim/enemies/boss_golem.gd")
const Classmates := preload("res://sim/enemies/classmates.gd")
const SP := preload("res://view/sprites.gd")
const TICK := 0.04
const TEXT_COLORS := [Color(1, 0.3, 0.3), Color(1, 0.9, 0.3), Color(0.45, 0.75, 1)]
const GOLEM_PARTS := ["/mob/b20.png", "/mob/b21.png", "/mob/b22.png", "/mob/b21.png", "/mob/b23.png", "/cp/f2h0.png"]

var sim = Sim.new()
var autotick := true          # false: the caller drives step() (main, tests)
var world = null:             # World node (group "world"); null in headless tests
	set(v):
		world = v
		sim.world = v
var test_player = null:
	set(v):
		test_player = v
		sim.test_player = v

# forwarded rule state (read by main's trace, tests and other modules)
var enemies: Array:
	get: return sim.enemies
var boar:
	get: return sim.boar
var river:
	get: return sim.river
var golem:
	get: return sim.golem
var classmates:
	get: return sim.classmates
var shots:
	get: return sim.shots
var drops:
	get: return sim.drops

# presentation state
var _time := 0.0
var enemy_flash: Array = []   # per monster slot: white tint seconds
var enemy_squash: Array = []  # per monster slot: squash amount 0..1
var classmate_flash: Array = []
var boar_flash := 0.0
var boar_squash := 0.0
var river_flash := 0.0
var golem_flash := 0.0
var shuriken_spin: Array = [] # per shuriken slot (slots are reused, the spin carries over like before)

func _init() -> void:
	sim.on_event = _on_event
	sim.enemy_killed.connect(func(t, p): enemy_killed.emit(t, p))
	sim.classmate_killed.connect(func(k, p): classmate_killed.emit(k, p))
	sim.boss_killed.connect(func(b): boss_killed.emit(b))
	sim.player_hit.connect(func(d, dir): player_hit.emit(d, dir))
	shuriken_spin.resize(sim.shots.shurikens.size())
	shuriken_spin.fill(0.0)

func _ready() -> void:
	if autotick and get_node_or_null("/root/Game"):
		get_node("/root/Game").tick25.connect(step)
	if world == null:
		var ws := get_tree().get_nodes_in_group("world") if is_inside_tree() else []
		if ws.size() > 0:
			world = ws[0]

func _process(dt: float) -> void:
	_time += dt
	for i in enemy_flash.size():
		enemy_flash[i] = maxf(0.0, enemy_flash[i] - dt)
		enemy_squash[i] = maxf(0.0, enemy_squash[i] - dt * 2.5)
	queue_redraw()

# ============================================================== API (forwarded to the sim)
func setup(m: Dictionary) -> void:
	sim.setup(m)
	var n: int = sim.enemies.size()
	enemy_flash = []; enemy_flash.resize(n); enemy_flash.fill(0.0)
	enemy_squash = []; enemy_squash.resize(n); enemy_squash.fill(0.0)
	classmate_flash = []
	if sim.classmates:
		classmate_flash.resize(sim.classmates.count)
		classmate_flash.fill(0.0)
	boar_flash = 0.0; boar_squash = 0.0; river_flash = 0.0; golem_flash = 0.0

func step() -> void:
	sim.step()

func step_projectiles() -> void:
	sim.step_projectiles()

func step_actors() -> void:
	sim.step_actors()

func hit(area: Rect2, damage: int, dir: int) -> int:
	return sim.hit(area, damage, dir)

func damage_monster(e, dmg: int, d: int) -> bool:
	return sim.damage_monster(e, dmg, d)

func spawn_projectile(x: float, y: float, vx: float, vy: float, strong: bool) -> void:
	sim.spawn_projectile(x, y, vx, vy, strong)

func shoot_dart(x: int, y: int, vx: int, dmg: int, kind: int) -> void:
	sim.shoot_dart(x, y, vx, dmg, kind)

func lob_seed(x: int, y: int, vx: int, vy: int, dmg: int) -> void:
	sim.lob_seed(x, y, vx, vy, dmg)

func boss_state() -> Dictionary:
	return sim.boss_state()

func start_golem() -> void:
	sim.start_golem()

func alive_count(type := -1) -> int:
	return sim.alive_count(type)

func flags(x: int, y: int) -> int:
	return sim.flags(x, y)

# ============================================================== presentation events
func _on_event(ev: Dictionary) -> void:
	match ev["t"]:
		"fx":
			if world and world.has_method("spawn_fx"):
				world.spawn_fx(ev["kind"], ev["p"], ev["d"])
		"text":
			if world and world.has_method("float_text"):
				world.float_text(ev["s"], ev["p"], TEXT_COLORS[clampi(ev["c"], 0, 2)])
		"popup":
			var ui = get_node_or_null("/root/UI")
			if ui and ui.has_method("popup"):
				ui.popup(ev["s"])
		"flash":
			match ev["who"]:
				"enemy":
					enemy_flash[ev["i"]] = 0.08
					enemy_squash[ev["i"]] = ev["squash"]
				"classmate":
					classmate_flash[ev["i"]] = 0.08
				"boar":
					boar_flash = 0.08
					boar_squash = ev["squash"]
				"river":
					river_flash = 0.08
				"golem":
					golem_flash = 0.08

# ============================================================== drawing
func _alpha() -> float:
	var g = get_node_or_null("/root/Game")
	if g == null:
		return 1.0
	return clampf(float(g.acc) / TICK, 0.0, 1.0)

func _draw() -> void:
	var a := _alpha()
	var dt := get_process_delta_time()
	_draw_river(a, dt)
	sim.drops.draw(self, a, _time)
	_draw_boar(a, sim.w, dt)
	_draw_golem(a, dt)
	_draw_monsters(a)
	if sim.classmates:
		_draw_classmates(a, dt)
	_draw_shots(a, dt)

func _draw_monsters(a: float) -> void:
	for e in sim.enemies:
		if not e.visible():
			continue
		var faces: Array = sim.sprite_sets[e.group] if e.group < sim.sprite_sets.size() else []
		if faces.is_empty():
			continue
		var path: String = faces[0 if e.dir >= 0 else 1][e.frame] if e.frame < faces[0].size() else ""
		if path == "":
			continue
		var x := lerpf(e.prev_x, e.x, a)
		var y := lerpf(e.prev_y, e.y, a)
		var col := Color(3, 3, 3) if enemy_flash[e.index] > 0.0 else Color.WHITE
		if e.state == Enemy.DYING:
			col.a = clampf(1.0 - (e.timer + 5) / 30.0, 0.25, 1.0)
		SP.draw(self, path, x, y, 33, false, col, Rect2(), enemy_squash[e.index])
		if e.bar_timer != 0:
			SP.hp_bar(self, x, y - e.height - 7, e.width, e.hp, e.max_hp, Color8(0x00, 0x33, 0x11), Color8(0x00, 0xEE, 0x00), Color8(0x00, 0x44, 0x22))

# boar ~L7851: ey[4] shadow under ey[gX]; ey[3] sweat drop while staggered; images face right
func _draw_boar(alpha: float, wt: int, dt: float) -> void:
	var b = sim.boar
	if not b.active or b.state == Boar.GONE:
		return
	boar_flash = maxf(0.0, boar_flash - dt)
	boar_squash = maxf(0.0, boar_squash - dt * 2.0)
	var px := lerpf(b.prev_x, b.x, alpha)
	var py := lerpf(b.prev_y, b.y, alpha)
	var col := Color(3, 3, 3) if boar_flash > 0.0 else Color.WHITE
	var mirror: bool = b.dir != 1
	var lift := 0
	if b.frame == 0:
		lift = -2
		if b.state == Boar.ROAM and wt % 10 > 5:
			lift = -1
		SP.draw(self, "/mob/b04.png", px, py + 1, 33, mirror)
	SP.draw(self, "/mob/b0%d.png" % b.frame, px, py + lift + 1, 33, mirror, col, Rect2(), boar_squash)
	if b.state == Boar.STAGGER:
		if b.dir == 1:
			SP.draw(self, "/mob/b03.png", px + 10, py - 25 + 1, 0)
		else:
			SP.draw(self, "/mob/b03.png", px - 10, py - 25 + 1, 24, true)
	if b.bar != 0:
		SP.hp_bar(self, px, py - b.h - 7, b.w, b.hp, b.max_hp, Color8(0x33, 0, 0x11), Color8(0xEE, 0, 0), Color8(0x44, 0, 0x22))

# river monster ~L7594: /mob/b10 body, b11 b12 b13 parts
func _draw_river(alpha: float, dt: float) -> void:
	var r = sim.river
	if not r.active:
		return
	river_flash = maxf(0.0, river_flash - dt)
	var col := Color(3, 3, 3) if river_flash > 0.0 else Color.WHITE
	var mirror: bool = r.dir >= 0
	var cur: Array = [r.x, r.y] + r.parts
	var p := []
	for i in 8:
		p.append(lerpf(r.prev[i], cur[i], alpha))
	SP.draw(self, "/mob/b10.png", p[0], p[1], 33, mirror, col)
	SP.draw(self, "/mob/b12.png", p[2], p[3], 33, mirror, col)
	SP.draw(self, "/mob/b13.png", p[4], p[5], 33, mirror, col)
	SP.draw(self, "/mob/b11.png", p[6], p[7], 33, mirror, col)
	if r.bar != 0:
		SP.hp_bar(self, p[0], p[1] - River.H - 7, River.W, r.hp, r.max_hp, Color8(0x33, 0, 0x11), Color8(0xEE, 0, 0), Color8(0x44, 0, 0x22))

# golem ~L7887: 6-part rig at hG/hH
func _draw_golem(alpha: float, dt: float) -> void:
	var g = sim.golem
	if not g.active:
		return
	golem_flash = maxf(0.0, golem_flash - dt)
	var col := Color(3, 3, 3) if golem_flash > 0.0 else Color.WHITE
	var a := 1.0
	if g.state == Golem.DEATH:
		a = clampf(1.0 - g.timer / 50.0, 0.0, 1.0)
	elif g.state == Golem.GONE:
		return
	col.a = a
	for i in 6:
		SP.draw(self, GOLEM_PARTS[i], lerpf(g.prev_x[i], g.draw_x[i], alpha), lerpf(g.prev_y[i], g.draw_y[i], alpha), 33, g.dir <= 0, col)
	if g.bar != 0:
		SP.hp_bar(self, g.x, g.y - g.h - 7, g.w, g.hp, g.max_hp, Color8(0x33, 0, 0x11), Color8(0xEE, 0, 0), Color8(0x44, 0, 0x22))

# rival students ~L7940: body /mob/x<frame>.png + head /mob/y<kind><frame>.png
func _draw_classmates(alpha: float, dt: float) -> void:
	var c = sim.classmates
	for n in c.count:
		if c.state[n] == Classmates.GONE:
			continue
		classmate_flash[n] = maxf(0.0, classmate_flash[n] - dt)
		var col := Color(4, 4, 4) if classmate_flash[n] > 0.0 else Color.WHITE
		var bx := lerpf(c.px[n], c.x[n], alpha)
		var by := lerpf(c.py[n], c.y[n], alpha)
		var hx := lerpf(c.phx[n], c.head_x[n], alpha)
		var hy := lerpf(c.phy[n], c.head_y[n], alpha)
		var k: int = c.kind[n]
		var hf: int = c.head_frame[n]
		var head := "/mob/y%d%d.png" % [k, hf]
		var body := "/mob/x%d.png" % c.body_frame[n]
		if c.dir[n] == -1:
			SP.draw(self, body, bx + int(S.ai[k]), by, 33, false, col)
			SP.draw(self, head, hx + int(S.ag[k][hf]), hy, 33, false, col)
		else:
			SP.draw(self, body, bx - int(S.ai[k]), by, 33, true, col)
			SP.draw(self, head, hx - int(S.ag[k][hf]), hy, 33, true, col)
		if c.bar[n] != 0:
			var filled: int = c.hp[n] * 24 / int(S.af[k])
			draw_rect(Rect2(bx - 13, by - 40, 26, 4), Color8(0x00, 0x33, 0x11), false, 1.0)
			draw_rect(Rect2(bx - 12, by - 39, filled, 3), Color8(0xEE, 0, 0))
			draw_rect(Rect2(bx - 12 + filled, by - 39, 24 - filled + 1, 3), Color8(0x44, 0, 0x22))

# projectiles R() ~L8056-8074
func _draw_shots(alpha: float, dt: float) -> void:
	var pt := 2 if S.eZ else 0
	var sh: Array = sim.shots.shurikens
	for i in sh.size():
		var s = sh[i]
		if s.state != -1:
			var p := Vector2(lerpf(s.px, s.x, alpha), lerpf(s.py, s.y, alpha))
			if s.state == 1 and s.vx != 0:
				shuriken_spin[i] += dt * 30.0 * signf(s.vx)
			var img := SP.get_image("/c/pt%d.png" % (pt + s.frame))
			if img.is_empty():
				continue
			# polish: spin the shuriken around its centre (original swaps 2 frames)
			draw_set_transform(p, shuriken_spin[i], Vector2.ONE)
			var sz := Vector2(img["w"], img["h"])
			draw_texture_rect(img["tex"], Rect2(-Vector2(int(sz.x / 2), int(sz.y / 2)), sz), false)
			draw_set_transform(Vector2.ZERO)
	for s in sim.shots.darts:
		if s.state != -1:
			var p := Vector2(lerpf(s.px, s.x, alpha), lerpf(s.py, s.y, alpha))
			SP.draw(self, "/c/ar%d%d.png" % [s.kind, 0 if s.vx > 0 else 1], p.x, p.y, 3)
	for s in sim.shots.seeds:
		if s.state != 0:
			var p := Vector2(lerpf(s.px, s.x, alpha), lerpf(s.py, s.y, alpha))
			SP.draw(self, "/e/b.png", p.x, p.y, 3)
