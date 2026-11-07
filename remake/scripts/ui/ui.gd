# UI autoload: HUD, dialogue bubbles, title cards, popups, choices, shop, fades, frame-time overlay (F3).
# All blocking calls are coroutines: `await UI.say([...])`, `var i = await UI.choose(...)`.
extends CanvasLayer

signal advanced
signal chosen(i: int)

var root: Control
var font: Font
var portrait: Texture2D
var fade_a := 0.0
var _fade_target := 0.0
var _fade_speed := 4.0
# dialogue
var say_lines: Array = []
var say_i := 0
var say_chars := 0.0
var say_speaker := ""
var say_active := false
# title card
var card_text := ""
var card_t := -1.0
# popups (toasts) and modal message
var toasts: Array = []
var modal_text := ""
var modal_active := false
# choice / shop
var choice_title := ""
var choice_opts: Array = []
var choice_i := 0
var choice_active := false
var choice_prev_mode := "play"
# HUD smoothing
var hp_s := 1.0
var mp_s := 1.0
var exp_s := 0.0
var show_hud := false
var show_ft := false
var ft_hist: Array = []
var prompt := ""

func _ready() -> void:
	layer = 50
	process_mode = Node.PROCESS_MODE_ALWAYS
	root = Control.new()
	root.set_anchors_preset(Control.PRESET_FULL_RECT)
	root.mouse_filter = Control.MOUSE_FILTER_IGNORE
	add_child(root)
	root.draw.connect(_draw_ui)
	font = ThemeDB.fallback_font
	if ResourceLoader.exists("res://art/ui/portrait.png"):
		portrait = load("res://art/ui/portrait.png")

func u() -> float:
	return root.size.y / 1080.0

# ---------------- API ----------------
func fade_out(t := 0.3) -> void:
	_fade_target = 1.0
	_fade_speed = 1.0 / max(t, 0.01)
	while fade_a < 0.999:
		await get_tree().process_frame

func fade_in(t := 0.3) -> void:
	_fade_target = 0.0
	_fade_speed = 1.0 / max(t, 0.01)
	while fade_a > 0.001:
		await get_tree().process_frame

# lines: Array of String or [speaker, text]
func say(lines: Array, _speaker_pos := Vector2.ZERO, speaker := "") -> void:
	if lines.is_empty():
		return
	say_lines = lines
	say_i = 0
	say_chars = 0.0
	say_speaker = speaker
	say_active = true
	_open_frame = Engine.get_process_frames()
	var prev := Game.mode
	Game.mode = "dialogue"
	while say_active:
		await advanced
	if Game.mode == "dialogue":
		Game.mode = prev if prev != "dialogue" else "play"

func title_card(text: String, dur := 2.4) -> void:
	card_text = text
	card_t = 0.0
	while card_t >= 0.0 and card_t < dur:
		await get_tree().process_frame
	card_t = -1.0

func popup(text: String, dur := 2.6) -> void:
	toasts.append({"s": text, "t": 0.0, "d": dur})

# modal message that needs OK (original d = 7 popups)
func message(text: String) -> void:
	modal_text = text
	modal_active = true
	_open_frame = Engine.get_process_frames()
	var prev := Game.mode
	Game.mode = "dialogue"
	while modal_active:
		await advanced
	if Game.mode == "dialogue":
		Game.mode = prev if prev != "dialogue" else "play"

func choose(title: String, options: Array) -> int:
	choice_title = title
	choice_opts = options
	choice_i = 0
	choice_active = true
	_open_frame = Engine.get_process_frames()
	choice_prev_mode = Game.mode
	Game.mode = "menu"
	var r: int = await chosen
	return r

func show_shop(kind := 0) -> void:
	# original shop: potions HP (V0..2) / MP (V3..5); names kx, prices from the original tables
	var names: Array = S.kx[0]
	while true:
		var opts := []
		for i in min(6, names.size()):
			opts.append("%s  (có %d)   %d$" % [str(names[i]), int(S.V[i]), _price(i)])
		opts.append("Thoát")
		var i: int = await choose("Cửa hàng — tiền: %d$" % S.dg, opts)
		if i < 0 or i >= opts.size() - 1:
			break
		var p := _price(i)
		if S.dg >= p:
			S.dg -= p
			S.V[i] = int(S.V[i]) + 1
			popup("Mua thành công!")
			S.changed.emit()
		else:
			popup("Bạn không có đủ tiền!")
	Game.mode = "play"

func _price(i: int) -> int:
	if "ky" in S and S.ky is Array and i < S.ky.size():
		return int(S.ky[i])
	return [20, 50, 100, 20, 50, 100][i]

# ---------------- input ----------------
func _unhandled_input(ev: InputEvent) -> void:
	if ev.is_action_pressed("toggle_fullscreen"):
		var full := DisplayServer.window_get_mode() == DisplayServer.WINDOW_MODE_FULLSCREEN
		DisplayServer.window_set_mode(DisplayServer.WINDOW_MODE_WINDOWED if full else DisplayServer.WINDOW_MODE_FULLSCREEN)
		return
	if ev is InputEventKey and ev.pressed and not ev.echo and ev.physical_keycode == KEY_F3:
		show_ft = not show_ft
		return

func _finish_choice(i: int) -> void:
	choice_active = false
	Game.mode = choice_prev_mode if choice_prev_mode != "menu" else "play"
	chosen.emit(i)

# auto-advance support for tests / cutscene scripts
func skip_all() -> void:
	if say_active:
		say_active = false
		advanced.emit()
	if modal_active:
		modal_active = false
		advanced.emit()

func _line_text(l) -> String:
	if l is Array:
		return str(l[1])
	return str(l)

func _line_speaker(l) -> String:
	if l is Array:
		return str(l[0])
	return say_speaker

func _jp(a: String) -> bool:
	return Input.is_action_just_pressed(a)

# dialogue / modal / choice keys are polled (works for real keys and Input.action_press in tests)
var _open_frame := -1

var autoskip := OS.get_cmdline_user_args().has("--autoskip")
var _auto_t := 0.0

func _poll_keys() -> void:
	if Engine.get_process_frames() <= _open_frame:
		return      # the key that opened this box must not also answer it
	if autoskip and (say_active or modal_active):
		_auto_t += get_process_delta_time()
		if _auto_t > 0.5:
			_auto_t = 0.0
			if say_active:
				say_i += 1
				say_chars = 0.0
				if say_i >= say_lines.size():
					say_active = false
			else:
				modal_active = false
			advanced.emit()
		return
	if choice_active:
		if _jp("jump") or _jp("move_left"):
			choice_i = (choice_i - 1 + choice_opts.size()) % choice_opts.size()
		elif _jp("down") or _jp("move_right"):
			choice_i = (choice_i + 1) % choice_opts.size()
		elif _jp("attack") or _jp("talk"):
			_finish_choice(choice_i)
		elif _jp("menu"):
			_finish_choice(-1)
		return
	if modal_active and (_jp("attack") or _jp("talk") or _jp("menu")):
		modal_active = false
		advanced.emit()
		return
	if say_active and (_jp("attack") or _jp("talk") or _jp("jump")):
		var full_len := _line_text(say_lines[say_i]).length()
		if say_chars < full_len:
			say_chars = full_len
		else:
			say_i += 1
			say_chars = 0.0
			if say_i >= say_lines.size():
				say_active = false
			advanced.emit()

func _process(delta: float) -> void:
	_poll_keys()
	fade_a = move_toward(fade_a, _fade_target, _fade_speed * delta)
	if say_active:
		say_chars += delta * 60.0
	if card_t >= 0.0:
		card_t += delta
	for t in toasts:
		t["t"] += delta
	toasts = toasts.filter(func(t): return t["t"] < t["d"])
	if show_hud:
		var hmax: float = max(1, S.H)
		hp_s = lerpf(hp_s, float(S.G) / hmax, 1.0 - exp(-delta / 0.12))
		mp_s = lerpf(mp_s, float(S.I) / max(1, S.J), 1.0 - exp(-delta / 0.12))
		exp_s = lerpf(exp_s, float(S.N) / max(1, S.level_need()), 1.0 - exp(-delta / 0.2))
	ft_hist.append(delta)
	if ft_hist.size() > 240:
		ft_hist.pop_front()
	root.queue_redraw()

# ---------------- drawing ----------------
func _txt(s: String, p: Vector2, size: float, col := Color.WHITE, align := HORIZONTAL_ALIGNMENT_LEFT, width := -1.0, outline := true) -> void:
	if outline:
		root.draw_string_outline(font, p, s, align, width, int(size), int(max(2.0, size / 7.0)), Color(0, 0, 0, col.a * 0.85))
	root.draw_string(font, p, s, align, width, int(size), col)

func _panel(r: Rect2, a := 0.82) -> void:
	root.draw_rect(r, Color(0.05, 0.03, 0.1, a))
	root.draw_rect(r, Color(0.95, 0.75, 0.3, 0.95), false, max(2.0, 3.0 * u()))

func _bar(p: Vector2, size: Vector2, v: float, a: Color, b: Color) -> void:
	root.draw_rect(Rect2(p - Vector2(3, 3), size + Vector2(6, 6)), Color(0, 0, 0, 0.6))
	root.draw_rect(Rect2(p, Vector2(size.x * clampf(v, 0, 1), size.y)), a)
	root.draw_rect(Rect2(p, Vector2(size.x * clampf(v, 0, 1), size.y * 0.4)), b)

func _draw_ui() -> void:
	var vs := root.size
	var k := u()
	if show_hud and Game.mode != "title":
		_draw_hud(vs, k)
	# title card (original title text aH): letterbox + big centred text
	if card_t >= 0.0:
		var a := clampf(min(card_t / 0.4, (2.4 - card_t) / 0.4), 0.0, 1.0)
		root.draw_rect(Rect2(0, vs.y * 0.38, vs.x, vs.y * 0.24), Color(0, 0, 0, 0.55 * a))
		_txt(card_text, Vector2(0, vs.y * 0.52), 64 * k, Color(1, 0.92, 0.7, a), HORIZONTAL_ALIGNMENT_CENTER, vs.x)
	# dialogue
	if say_active and say_i < say_lines.size():
		var l = say_lines[say_i]
		var txt := _line_text(l)
		var r := Rect2(vs.x * 0.12, vs.y * 0.70, vs.x * 0.76, vs.y * 0.24)
		_panel(r)
		var sp := _line_speaker(l)
		var tx := r.position.x + 40 * k
		if sp != "":
			_txt(sp, Vector2(tx, r.position.y + 52 * k), 36 * k, Color(1, 0.8, 0.35))
		var shown := txt.substr(0, int(say_chars))
		root.draw_multiline_string(font, Vector2(tx, r.position.y + (100 if sp != "" else 64) * k), shown, HORIZONTAL_ALIGNMENT_LEFT, r.size.x - 80 * k, int(34 * k), -1, Color(1, 1, 1))
		if say_chars >= txt.length() and int(Time.get_ticks_msec() / 400) % 2 == 0:
			_txt("▼", r.end - Vector2(50, 24) * k, 28 * k, Color(1, 0.85, 0.4))
	# modal
	if modal_active:
		var r2 := Rect2(vs.x * 0.25, vs.y * 0.36, vs.x * 0.5, vs.y * 0.24)
		_panel(r2, 0.92)
		root.draw_multiline_string(font, r2.position + Vector2(40, 70) * k, modal_text, HORIZONTAL_ALIGNMENT_CENTER, r2.size.x - 80 * k, int(34 * k), -1, Color.WHITE)
		_txt("[J] OK", Vector2(r2.position.x, r2.end.y - 26 * k), 28 * k, Color(1, 0.85, 0.4), HORIZONTAL_ALIGNMENT_CENTER, r2.size.x)
	# choice
	if choice_active:
		var h := (110 + 60 * choice_opts.size()) * k
		var r3 := Rect2(vs.x * 0.3, (vs.y - h) * 0.5, vs.x * 0.4, h)
		_panel(r3, 0.94)
		_txt(choice_title, Vector2(r3.position.x, r3.position.y + 60 * k), 38 * k, Color(1, 0.85, 0.4), HORIZONTAL_ALIGNMENT_CENTER, r3.size.x)
		for i in choice_opts.size():
			var y := r3.position.y + (120 + 60 * i) * k
			if i == choice_i:
				root.draw_rect(Rect2(r3.position.x + 20 * k, y - 42 * k, r3.size.x - 40 * k, 54 * k), Color(0.95, 0.75, 0.3, 0.25))
			_txt(str(choice_opts[i]), Vector2(r3.position.x + 50 * k, y), 32 * k, Color.WHITE if i == choice_i else Color(0.8, 0.8, 0.85))
	# toasts
	var ty := vs.y * 0.18
	for t in toasts:
		var a2: float = clampf(min(t["t"] / 0.15, (t["d"] - t["t"]) / 0.4), 0.0, 1.0)
		var w := font.get_string_size(t["s"], HORIZONTAL_ALIGNMENT_LEFT, -1, int(32 * k)).x
		var r4 := Rect2((vs.x - w) * 0.5 - 30 * k, ty - 46 * k, w + 60 * k, 64 * k)
		root.draw_rect(r4, Color(0.05, 0.03, 0.1, 0.8 * a2))
		_txt(t["s"], Vector2(0, ty), 32 * k, Color(1, 0.95, 0.8, a2), HORIZONTAL_ALIGNMENT_CENTER, vs.x)
		ty += 76 * k
	if fade_a > 0.0:
		root.draw_rect(Rect2(Vector2.ZERO, vs), Color(0, 0, 0, fade_a))
	if show_ft:
		_draw_frametime(vs, k)

func _draw_hud(vs: Vector2, k: float) -> void:
	var o := Vector2(28, 24) * k
	var pr := 60.0 * k
	var pc := o + Vector2(pr, pr)
	root.draw_circle(pc, pr + 5 * k, Color(0.95, 0.75, 0.3))
	root.draw_circle(pc, pr, Color(0.12, 0.08, 0.2))
	if portrait:
		root.draw_texture_rect(portrait, Rect2(pc - Vector2(pr, pr) * 0.92, Vector2(pr, pr) * 1.84), false)
	root.draw_circle(pc + Vector2(pr * 0.72, pr * 0.72), 22 * k, Color(0.75, 0.1, 0.1))
	_txt(str(S.M), pc + Vector2(pr * 0.72 - 22 * k, pr * 0.72 + 10 * k), 28 * k, Color(1, 0.95, 0.7), HORIZONTAL_ALIGNMENT_CENTER, 44 * k)
	var bx := o.x + pr * 2 + 24 * k
	_bar(Vector2(bx, o.y + 14 * k), Vector2(380, 24) * k, hp_s, Color(0.85, 0.1, 0.12), Color(1, 0.45, 0.4))
	_txt("HP %d/%d" % [S.G, S.H], Vector2(bx + 10 * k, o.y + 34 * k), 21 * k)
	_bar(Vector2(bx, o.y + 52 * k), Vector2(320, 20) * k, mp_s, Color(0.12, 0.3, 0.9), Color(0.5, 0.7, 1))
	_txt("MP %d/%d" % [S.I, S.J], Vector2(bx + 10 * k, o.y + 69 * k), 18 * k)
	_bar(Vector2(bx, o.y + 86 * k), Vector2(260, 10) * k, exp_s, Color(0.2, 0.85, 0.5), Color(0.6, 1, 0.8))
	_txt("Lv %d   EXP %d/%d" % [S.M, S.N, S.level_need()], Vector2(bx, o.y + 124 * k), 20 * k, Color(0.8, 1, 0.85))
	var rx := vs.x - 30 * k
	_txt("%d $" % S.dg, Vector2(rx - 300 * k, o.y + 40 * k), 34 * k, Color(1, 0.85, 0.2), HORIZONTAL_ALIGNMENT_RIGHT, 300 * k)
	var hpp: int = int(S.V[0]) + int(S.V[1]) + int(S.V[2])
	var mpp: int = int(S.V[3]) + int(S.V[4]) + int(S.V[5])
	_potion(Vector2(rx - 220 * k, o.y + 82 * k), Color(0.9, 0.15, 0.15), hpp, Controls.keys_text("hp_potion"), k)
	_potion(Vector2(rx - 110 * k, o.y + 82 * k), Color(0.2, 0.4, 1.0), mpp, Controls.keys_text("mp_potion"), k)
	var wname := "Kiếm" if S.fg == 0 else "Shuriken"
	_txt("[%s] %s" % [Controls.keys_text("weapon"), wname], Vector2(rx - 300 * k, o.y + 160 * k), 22 * k, Color(0.9, 0.9, 1), HORIZONTAL_ALIGNMENT_RIGHT, 300 * k)
	var st := []
	if S.fz: st.append("Ẩn thân")
	if S.fA: st.append("Xích Mao")
	if st.size() > 0:
		_txt(" · ".join(st), Vector2(rx - 300 * k, o.y + 196 * k), 22 * k, Color(1, 0.6, 0.5), HORIZONTAL_ALIGNMENT_RIGHT, 300 * k)
	if prompt != "" and Game.mode == "play":
		var s := "[%s] %s" % [Controls.keys_text("talk"), prompt]
		var w := font.get_string_size(s, HORIZONTAL_ALIGNMENT_LEFT, -1, int(30 * k)).x
		var bp := Vector2(vs.x - w - 60 * k, vs.y - 80 * k)
		_panel(Rect2(bp - Vector2(16, 38) * k, Vector2(w + 32 * k, 54 * k)))
		_txt(s, bp, 30 * k, Color(1, 0.95, 0.8))
	_txt("[Esc] Menu", Vector2(30 * k, vs.y - 30 * k), 20 * k, Color(1, 1, 1, 0.6))

func _potion(p: Vector2, col: Color, n: int, key: String, k: float) -> void:
	root.draw_circle(p, 20 * k, Color(0.05, 0.03, 0.02, 0.8))
	root.draw_circle(p + Vector2(0, 4) * k, 14 * k, col)
	root.draw_rect(Rect2(p + Vector2(-5, -20) * k, Vector2(10, 10) * k), Color(0.8, 0.7, 0.5))
	_txt("x%d" % n, p + Vector2(24, 10) * k, 24 * k)
	_txt(key, p + Vector2(-7, -24) * k, 16 * k, Color(1, 1, 1, 0.7))

# F3: frame-time graph (target lines 8.3 / 16.7 / 33 ms) + fps
func _draw_frametime(vs: Vector2, k: float) -> void:
	var r := Rect2(vs.x - 520 * k, vs.y - 260 * k, 480 * k, 200 * k)
	root.draw_rect(r, Color(0, 0, 0, 0.6))
	var sc := r.size.y / 0.05
	for ms in [1.0 / 120.0, 1.0 / 60.0, 1.0 / 30.0]:
		var y: float = r.end.y - ms * sc
		root.draw_line(Vector2(r.position.x, y), Vector2(r.end.x, y), Color(1, 1, 1, 0.25), 1.0)
	var n := ft_hist.size()
	var worst := 0.0
	for i in n:
		var d: float = ft_hist[i]
		worst = max(worst, d)
		var x := r.position.x + r.size.x * i / 240.0
		root.draw_line(Vector2(x, r.end.y), Vector2(x, r.end.y - min(d, 0.05) * sc), Color(0.3, 1, 0.4) if d < 0.0175 else Color(1, 0.4, 0.3), max(1.0, 2.0 * k))
	_txt("%d fps   max %.1f ms" % [Engine.get_frames_per_second(), worst * 1000.0], r.position + Vector2(12, 34) * k, 24 * k)
