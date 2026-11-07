# Screen-space overlay: HUD (replaces the original bottom strip), prompts, lighting & ambient FX.
# Values come straight from the original state: G/H = HP/maxHP, I/J = MP/maxMP, M = level,
# N = EXP (next level at ak[M-1]), dg = money, V[0..2] / V[3..5] = HP / MP potions, fg = weapon mode.
extends Node2D

var core
var main
var font: Font
var portrait: Texture2D
var t := 0.0
var parts: Array = []
var hp_shown := 1.0
var mp_shown := 1.0
var exp_shown := 0.0
var flash := 0.0
var last_hp := -1
var debug := OS.get_cmdline_user_args().has("--debug")

func _ready() -> void:
	font = ThemeDB.fallback_font
	var p := "res://art/ui/portrait.png"
	if ResourceLoader.exists(p):
		portrait = load(p)

func _process(delta: float) -> void:
	t += delta
	if core == null:
		return
	if core.d == 1 and core.H > 0:
		var hp := float(core.G) / float(max(1, core.H))
		var mp := float(core.I) / float(max(1, core.J))
		var need: int = core.ak[clampi(core.M - 1, 0, core.ak.size() - 1)]
		var ex := float(core.N) / float(max(1, need))
		hp_shown = lerpf(hp_shown, hp, min(1.0, delta * 6.0))
		mp_shown = lerpf(mp_shown, mp, min(1.0, delta * 6.0))
		exp_shown = lerpf(exp_shown, ex, min(1.0, delta * 4.0))
		if last_hp >= 0 and core.G < last_hp:
			flash = 0.35
		last_hp = core.G
	flash = max(0.0, flash - delta)
	_update_particles(delta)
	queue_redraw()

func _update_particles(delta: float) -> void:
	if core.d != 1 or main == null:
		parts.clear()
		return
	var hint: String = main.hd.particle_hint()
	var vs := get_viewport_rect().size
	var want := 26 if hint != "mist" else 0
	while parts.size() < want:
		parts.append(_spawn(hint, vs, true))
	for p in parts:
		p["pos"] += p["vel"] * delta
		p["life"] -= delta
		p["rot"] += p["spin"] * delta
	for i in range(parts.size() - 1, -1, -1):
		var p = parts[i]
		if p["life"] <= 0 or p["pos"].y > vs.y + 40 or p["pos"].x < -60 or p["pos"].x > vs.x + 60:
			parts[i] = _spawn(hint, vs, false)

func _spawn(kind: String, vs: Vector2, anywhere: bool) -> Dictionary:
	var p := {"kind": kind, "rot": randf() * TAU, "spin": randf_range(-2, 2), "life": randf_range(6, 14)}
	match kind:
		"petals":
			p["pos"] = Vector2(randf() * vs.x * 1.2, randf() * vs.y if anywhere else -20.0)
			p["vel"] = Vector2(randf_range(-60, -20), randf_range(30, 70))
			p["col"] = Color(1.0, 0.75, 0.85, 0.85)
			p["size"] = randf_range(5, 9)
		"leaves":
			p["pos"] = Vector2(randf() * vs.x * 1.2, randf() * vs.y if anywhere else -20.0)
			p["vel"] = Vector2(randf_range(-50, -10), randf_range(25, 60))
			p["col"] = [Color(0.95, 0.55, 0.15, 0.8), Color(0.85, 0.3, 0.1, 0.8), Color(0.55, 0.75, 0.25, 0.7)][randi() % 3]
			p["size"] = randf_range(5, 8)
		"fireflies", "embers":
			p["pos"] = Vector2(randf() * vs.x, vs.y * randf_range(0.3, 1.0))
			p["vel"] = Vector2(randf_range(-15, 15), randf_range(-25, -5))
			p["col"] = Color(1.0, 0.9, 0.4, 0.9) if kind == "fireflies" else Color(1.0, 0.35, 0.1, 0.9)
			p["size"] = randf_range(2, 4)
		"dust":
			p["pos"] = Vector2(randf() * vs.x, randf() * vs.y)
			p["vel"] = Vector2(randf_range(-40, -15), randf_range(-5, 5))
			p["col"] = Color(0.9, 0.8, 0.6, 0.35)
			p["size"] = randf_range(2, 3)
		_:
			p["pos"] = Vector2(randf() * vs.x, vs.y * randf_range(0.45, 0.95))
			p["vel"] = Vector2(randf_range(-12, -4), 0)
			p["col"] = Color(1, 1, 1, 0.06)
			p["size"] = randf_range(120, 260)
	return p

func _draw() -> void:
	if core == null:
		return
	var vs := get_viewport_rect().size
	var u: float = vs.y / 1080.0
	if core.d == 1 or core.d == 7:
		_draw_particles()
		if flash > 0:
			draw_rect(Rect2(Vector2.ZERO, vs), Color(0.8, 0.0, 0.0, flash * 0.35))
	if core.d == 1 and not core.fV:
		var dlg: bool = Engine.get_process_frames() - main.hd.dialogue_frame < 3
		modulate.a = 1.0
		if dlg:
			draw_set_transform(Vector2.ZERO, 0, Vector2.ONE)
		_draw_hud(u, vs, 0.25 if dlg else 1.0)
	if not main.world_mode:
		# letterbox outside the original 480x320 canvas
		var vp: Vector2 = main.view.position
		var cw: float = 480.0 * main.view.scale.x
		draw_rect(Rect2(0, 0, vp.x, vs.y), Color.BLACK)
		draw_rect(Rect2(vp.x + cw, 0, vs.x - vp.x - cw, vs.y), Color.BLACK)
		_txt("F10: Cài đặt / đổi phím   ·   F11: Toàn màn hình", Vector2(20 * u, vs.y - 20 * u), 18 * u, Color(1, 1, 1, 0.5))
	if debug:
		_txt("d=%d v=%d A=%d B=%d fh=%d fi=%d fR=%d fS=%d fV=%s fW=%d fX=%d r=%d fU=%d aX=%d" % [core.d, core.v, core.A, core.B, core.fh, core.fi, core.fR, core.fS, str(core.fV), core.fW, core.fX, core.r, core.fU, core.aX], Vector2(20, vs.y - 60 * u), 22 * u, Color(0.6, 1, 1))

func _draw_particles() -> void:
	for p in parts:
		var c: Color = p["col"]
		match p["kind"]:
			"petals", "leaves":
				draw_set_transform(p["pos"], p["rot"], Vector2(1.0, 0.55))
				draw_circle(Vector2.ZERO, p["size"], c)
				draw_set_transform(Vector2.ZERO)
			"fireflies", "embers", "dust":
				var a := 0.5 + 0.5 * sin(t * 3.0 + p["rot"])
				draw_circle(p["pos"], p["size"] * 2.5, Color(c, c.a * 0.15 * a))
				draw_circle(p["pos"], p["size"], Color(c, c.a * a))
			_:
				pass  # mist is part of the Blender background layers

func _bar(pos: Vector2, size: Vector2, v: float, col_a: Color, col_b: Color) -> void:
	draw_rect(Rect2(pos - Vector2(2, 2), size + Vector2(4, 4)), Color(0.05, 0.03, 0.02, 0.85))
	draw_rect(Rect2(pos, size), Color(0.18, 0.1, 0.08, 0.9))
	var w := size.x * clampf(v, 0.0, 1.0)
	draw_rect(Rect2(pos, Vector2(w, size.y)), col_a)
	draw_rect(Rect2(pos, Vector2(w, size.y * 0.4)), col_b)
	draw_rect(Rect2(pos - Vector2(2, 2), size + Vector2(4, 4)), Color(0.95, 0.75, 0.3, 0.9), false, 2.0)

func _txt(s: String, pos: Vector2, size: float, col := Color.WHITE, align := HORIZONTAL_ALIGNMENT_LEFT, width := -1.0) -> void:
	draw_string_outline(font, pos, s, align, width, int(size), int(max(3, size * 0.18)), Color(0.05, 0.02, 0, 0.95))
	draw_string(font, pos, s, align, width, int(size), col)

func _draw_hud(u: float, vs: Vector2, fade := 1.0) -> void:
	if fade < 1.0:
		var s := Color(1, 1, 1, fade)
		self.self_modulate = s
	else:
		self.self_modulate = Color.WHITE
	var o := Vector2(28, 24) * u
	# portrait medallion
	var pr := 64.0 * u
	var pc := o + Vector2(pr, pr)
	draw_circle(pc, pr + 5 * u, Color(0.95, 0.75, 0.3))
	draw_circle(pc, pr, Color(0.12, 0.08, 0.2))
	if portrait:
		draw_texture_rect(portrait, Rect2(pc - Vector2(pr, pr) * 0.92, Vector2(pr, pr) * 1.84), false)
	draw_circle(pc + Vector2(pr * 0.72, pr * 0.72), 22 * u, Color(0.75, 0.1, 0.1))
	_txt(str(core.M), pc + Vector2(pr * 0.72 - 22 * u, pr * 0.72 + 10 * u), 30 * u, Color(1, 0.95, 0.7), HORIZONTAL_ALIGNMENT_CENTER, 44 * u)
	var bx := o.x + pr * 2 + 24 * u
	_bar(Vector2(bx, o.y + 18 * u), Vector2(380, 24) * u, hp_shown, Color(0.85, 0.1, 0.12), Color(1, 0.45, 0.4))
	_txt("HP %d/%d" % [core.G, core.H], Vector2(bx + 10 * u, o.y + 38 * u), 22 * u)
	_bar(Vector2(bx, o.y + 56 * u), Vector2(320, 20) * u, mp_shown, Color(0.12, 0.3, 0.9), Color(0.5, 0.7, 1))
	_txt("MP %d/%d" % [core.I, core.J], Vector2(bx + 10 * u, o.y + 73 * u), 19 * u)
	_bar(Vector2(bx, o.y + 90 * u), Vector2(260, 10) * u, exp_shown, Color(0.2, 0.85, 0.5), Color(0.6, 1, 0.8))
	_txt("Lv %d   EXP %d/%d" % [core.M, core.N, core.ak[clampi(core.M - 1, 0, core.ak.size() - 1)]], Vector2(bx, o.y + 128 * u), 20 * u, Color(0.8, 1, 0.85))
	# right side: money, potions, weapon
	var rx := vs.x - 30 * u
	_txt("%d $" % core.dg, Vector2(rx - 300 * u, o.y + 40 * u), 34 * u, Color(1, 0.85, 0.2), HORIZONTAL_ALIGNMENT_RIGHT, 300 * u)
	var hpp: int = core.V[0] + core.V[1] + core.V[2]
	var mpp: int = core.V[3] + core.V[4] + core.V[5]
	_potion(Vector2(rx - 220 * u, o.y + 82 * u), Color(0.9, 0.15, 0.15), hpp, "Q", u)
	_potion(Vector2(rx - 110 * u, o.y + 82 * u), Color(0.2, 0.4, 1.0), mpp, "R", u)
	var wname := "Kiếm" if core.fg == 0 else "Shuriken"
	_txt("[K] " + wname, Vector2(rx - 300 * u, o.y + 160 * u), 22 * u, Color(0.9, 0.9, 1), HORIZONTAL_ALIGNMENT_RIGHT, 300 * u)
	# context prompt (original right soft-key label, e.g. "Giao tiếp")
	var ol: String = str(core.o).strip_edges()
	if ol != "":
		var s := "[E] " + ol
		var w := font.get_string_size(s, HORIZONTAL_ALIGNMENT_LEFT, -1, int(30 * u)).x
		var bp := Vector2(vs.x - w - 60 * u, vs.y - 80 * u)
		draw_rect(Rect2(bp - Vector2(16, 38) * u, Vector2(w + 32 * u, 54 * u)), Color(0.05, 0.03, 0.1, 0.8))
		draw_rect(Rect2(bp - Vector2(16, 38) * u, Vector2(w + 32 * u, 54 * u)), Color(0.95, 0.75, 0.3), false, 2.0)
		_txt(s, bp, 30 * u, Color(1, 0.95, 0.8))
	_txt("[Esc] Menu", Vector2(30 * u, vs.y - 30 * u), 20 * u, Color(1, 1, 1, 0.6))

func _potion(p: Vector2, col: Color, n: int, key: String, u: float) -> void:
	draw_circle(p, 20 * u, Color(0.05, 0.03, 0.02, 0.8))
	draw_circle(p + Vector2(0, 4) * u, 14 * u, col)
	draw_rect(Rect2(p + Vector2(-5, -20) * u, Vector2(10, 10) * u), Color(0.8, 0.7, 0.5))
	_txt("x%d" % n, p + Vector2(24, 10) * u, 24 * u)
	_txt(key, p + Vector2(-7, -24) * u, 16 * u, Color(1, 1, 1, 0.7))
