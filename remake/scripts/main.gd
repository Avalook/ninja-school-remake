# Main: title screen -> world. Wires World, Player, EnemyManager (scripts/enemies), Story (scripts/story),
# music (original per-map track MF_t), pause menu and settings.
extends Node2D

var world
var player
var enemies
var story
var npcs
var settings
var music: AudioStreamPlayer
var music_track := -2
var title_bg: Texture2D
var title_hero: Texture2D
var title_t := 0.0
var title_sel := 0
var title_opts := []
var auto := {}

func _ready() -> void:
	RenderingServer.set_default_clear_color(Color.BLACK)
	music = AudioStreamPlayer.new()
	add_child(music)
	settings = preload("res://scripts/ui/settings.gd").new()
	add_child(settings)
	settings.closed.connect(func(): Game.paused = false)
	world = preload("res://scripts/world/world.gd").new()
	add_child(world)
	world.visible = false
	player = preload("res://scripts/player/player.gd").new()
	player.external_tick = true
	player.world = world
	world.player = player
	world.layer_actors.add_child(player)
	npcs = preload("res://scripts/world/npcs.gd").new()
	npcs.world = world
	world.layer_actors.add_child(npcs)
	world.layer_actors.move_child(npcs, 0)
	var em_s = load("res://scripts/enemies/enemy_manager.gd") if ResourceLoader.exists("res://scripts/enemies/enemy_manager.gd") and not OS.get_cmdline_user_args().has("--noenemies") else null
	if em_s != null and em_s.can_instantiate():
		enemies = em_s.new()
		enemies.autotick = false
		enemies.name = "Enemies"
		world.enemies = enemies
		if "world" in enemies:
			enemies.world = world
		world.layer_actors.add_child(enemies)
		if enemies.has_signal("enemy_killed"):
			enemies.enemy_killed.connect(_on_killed)
	var st_s = load("res://scripts/story/story.gd") if ResourceLoader.exists("res://scripts/story/story.gd") and not OS.get_cmdline_user_args().has("--nostory") else null
	if st_s != null and st_s.can_instantiate():
		story = st_s.new()
		story.name = "Story"
		if "world" in story:
			story.world = world
		add_child(story)
		if story.has_signal("return_to_title"):
			story.return_to_title.connect(_back_to_title)
		if story.has_signal("quit_to_title"):
			story.quit_to_title.connect(_back_to_title)
	world.map_loaded.connect(_on_map_loaded)
	Game.tick25.connect(_tick25)
	if ResourceLoader.exists("res://art/env/school/day/layer0.webp"):
		title_bg = load("res://art/env/school/day/layer0.webp")
	if ResourceLoader.exists("res://art/ui/hero_title.png"):
		title_hero = load("res://art/ui/hero_title.png")
	_parse_args()
	_set_music(int(S.MF_t) if "MF_t" in S else 0)
	if auto.has("newgame"):
		new_game()
	elif auto.has("continue"):
		continue_game()
	else:
		_title()

func _parse_args() -> void:
	for a in OS.get_cmdline_user_args():
		var kv := a.trim_prefix("--").split("=", true, 1)
		auto[kv[0]] = kv[1] if kv.size() > 1 else ""
	if auto.has("map"):
		auto["newgame"] = ""

func _title() -> void:
	Game.mode = "title"
	world.visible = false
	UI.show_hud = false
	title_opts = ["Chơi mới", "Tiếp tục", "Cài đặt phím", "Thoát"]
	title_sel = 1 if S.has_save() else 0

func new_game() -> void:
	world.visible = true
	UI.show_hud = true
	Game.mode = "play"
	if auto.has("map"):
		S.reset_state()
		var p: PackedStringArray = str(auto["map"]).split(",")
		world.load_map(int(p[0]), Vector2(int(p[1]) if p.size() > 1 else 48, int(p[2]) if p.size() > 2 else 0), -1)
	elif story:
		story.new_game()          # original: reset, map 99 at (108, 312) + intro cutscene
	else:
		S.reset_state()
		world.load_map(99, Vector2(108, 312), -1)
	UI.fade_in(0.6)

func continue_game() -> void:
	world.visible = true
	UI.show_hud = true
	Game.mode = "play"
	if story:
		if not story.continue_game():
			new_game()
			return
	elif S.load_game():
		world.load_map(int(S.v), Vector2(S.fh, S.fi), S.fp)
	else:
		new_game()
		return
	UI.fade_in(0.6)

# original run() order inside one tick: projectiles, player R(), monsters / bosses / students
func _tick25() -> void:
	if enemies and Game.mode in ["play", "cutscene"]:
		enemies.step_projectiles()
	player.tick()
	if enemies and Game.mode in ["play", "cutscene"]:
		enemies.step_actors()

func _on_map_loaded(id: int) -> void:
	var st: Dictionary = world.map.get("state", {})
	if st.has("MF_t"):
		_set_music(int(st["MF_t"]))

func _on_killed(type: int, p: Vector2) -> void:
	if story and story.has_method("on_enemy_killed"):
		story.on_enemy_killed(type, p)

func _set_music(t: int) -> void:
	if t == music_track:
		return
	music_track = t
	var p := "res://audio/music%d.mp3" % t
	if t >= 0 and ResourceLoader.exists(p):
		var st: AudioStreamMP3 = load(p)
		st.loop = true
		music.stream = st
		music.play()
	else:
		music.stop()

var run_t := 0.0
var shots_done := {}
var ft: Array = []
var cam_hist: Array = []
var hero_hist: Array = []

# test automation: --keys=right@0.5-2.0,jump@1.0-1.2  --shots=1.0,2.5 --out=/dir  --quit=4
func _auto(delta: float) -> void:
	run_t += delta
	if auto.has("keys"):
		for e in str(auto["keys"]).split(","):
			var a := e.split("@")
			var tt := a[1].split("-")
			var act := {"right": "move_right", "left": "move_left"}.get(a[0], a[0])
			var on := run_t >= float(tt[0]) and run_t < float(tt[1])
			if on and not Input.is_action_pressed(act) and not _auto_down.get(act, false):
				_inject(act, true)
			elif not on and _auto_down.get(act, false) and run_t >= float(tt[1]) and run_t - delta < float(tt[1]) + 0.001:
				_inject(act, false)
	if auto.has("shots"):
		for t in str(auto["shots"]).split(","):
			if run_t >= float(t) and not shots_done.has(t):
				shots_done[t] = true
				var img := get_viewport().get_texture().get_image()
				img.save_png("%s/shot_%s.png" % [auto.get("out", "/tmp"), t])
	if auto.has("log") and Engine.get_process_frames() % 30 == 0:
		print("[t=%.2f] map=%d A=%d B=%d pos=%s vel=%s G=%d/%d I=%d mode=%s" % [run_t, world.map_id, S.A, S.B, player.pos, player.vel, S.G, S.H, S.I, Game.mode])
	if auto.has("ftlog"):
		ft.append(delta)
		cam_hist.append(world.camera.position.x)
		hero_hist.append(player.pos.x)
		if run_t > 0.6:
			print("[f] %.4f %.3f %.3f %d %s %.2f %d" % [delta, world.camera.position.x, player.pos.x, S.A, player.anim.cur, Performance.get_monitor(Performance.TIME_PROCESS) * 1000, Performance.get_monitor(Performance.RENDER_TOTAL_DRAW_CALLS_IN_FRAME)])
	if auto.has("tour"):
		_tour_step()
	if auto.has("quit") and auto.has("ftlog") and run_t >= float(auto["quit"]):
		ft.sort()
		var n := ft.size()
		var jit := 0.0
		for i in range(2, cam_hist.size()):
			# screen-space hero position second difference = visible judder
			var a: float = hero_hist[i] - cam_hist[i]
			var b: float = hero_hist[i - 1] - cam_hist[i - 1]
			var c: float = hero_hist[i - 2] - cam_hist[i - 2]
			jit = max(jit, abs(a - 2 * b + c))
		print("[ft] frames=%d median=%.2fms p95=%.2fms p99=%.2fms max=%.2fms hero_screen_jerk_max=%.3fpx" % [n, ft[n / 2] * 1000, ft[int(n * 0.95)] * 1000, ft[int(n * 0.99)] * 1000, ft[n - 1] * 1000, jit])
	if auto.has("quit") and run_t >= float(auto["quit"]):
		get_tree().quit()

var _auto_down := {}
var _tour_i := -1
var _tour_t := 0.0

# --tour=id,x,y;id,x,y;...  visit maps, screenshot each after 1.5 s (for visual / error review)
func _tour_step() -> void:
	var items: PackedStringArray = str(auto["tour"]).split(";")
	if run_t < _tour_t:
		return
	if _tour_i >= 0:
		var img := get_viewport().get_texture().get_image()
		img.save_png("%s/map_%s.png" % [auto.get("out", "/tmp"), items[_tour_i].split(",")[0]])
		print("[tour] map=%d A=%d pos=%s G=%d enemies=%d" % [world.map_id, S.A, player.pos, S.G, enemies.enemies.size() if enemies else -1])
	_tour_i += 1
	if _tour_i >= items.size():
		get_tree().quit()
		return
	var p := items[_tour_i].split(",")
	S.G = S.H
	world.load_map(int(p[0]), Vector2(int(p[1]), int(p[2])), 1)
	_tour_t = run_t + 1.5

func _inject(act: String, on: bool) -> void:
	var e := InputEventAction.new()
	e.action = act
	e.pressed = on
	_auto_down[act] = on
	Input.parse_input_event(e)

func _process(delta: float) -> void:
	if not auto.is_empty():
		_auto(delta)
	title_t += delta
	if Game.mode == "title":
		queue_redraw()
		return
	if Game.mode == "play" and player:
		if story:
			UI.prompt = "" if story.is_busy() else story.talk_label()
		else:
			UI.prompt = "Giao tiếp" if player.near_npc >= 0 else ""
	else:
		UI.prompt = ""

func _unhandled_input(ev: InputEvent) -> void:
	if Game.mode == "title":
		if ev.is_action_pressed("jump"):
			title_sel = (title_sel - 1 + title_opts.size()) % title_opts.size()
		elif ev.is_action_pressed("down"):
			title_sel = (title_sel + 1) % title_opts.size()
		elif ev.is_action_pressed("attack") or ev.is_action_pressed("talk"):
			match title_sel:
				0: await _start(new_game)
				1:
					if S.has_save():
						await _start(continue_game)
				2:
					Game.paused = true
					settings.open_panel()
				3: get_tree().quit()
		return
	if ev is InputEventKey and ev.pressed and not ev.echo and ev.physical_keycode in [KEY_F10, KEY_F1]:
		Game.paused = true
		settings.open_panel()
		return
	if Game.mode != "play":
		return
	if story and story.is_busy():
		return
	if ev.is_action_pressed("talk") and story and S.A == 1:
		get_viewport().set_input_as_handled()
		story.interact()
	elif ev.is_action_pressed("menu"):
		get_viewport().set_input_as_handled()
		_pause_menu()

func _start(f: Callable) -> void:
	await UI.fade_out(0.4)
	f.call()

func _back_to_title() -> void:
	await UI.fade_out(0.4)
	_title()
	UI.fade_in(0.4)

func _pause_menu() -> void:
	Game.paused = true
	var i: int = await UI.choose("Tạm dừng", ["Tiếp tục", "Túi đồ / Bản đồ / Menu game", "Lưu game", "Cài đặt phím", "Về màn hình chính"])
	Game.paused = false
	if i == 1:
		if story:
			await story.game_menu()
		return
	if i >= 2:
		i -= 1
	match i:
		1:
			S.save_game()
			UI.popup("Đã lưu game!")
		2:
			Game.paused = true
			settings.open_panel()
		3:
			S.save_game()
			await UI.fade_out(0.4)
			_title()
			UI.fade_in(0.4)

func _draw() -> void:
	if Game.mode != "title":
		return
	var vs := get_viewport_rect().size
	draw_rect(Rect2(Vector2.ZERO, vs), Color(0.08, 0.1, 0.2))
	if title_bg:
		var hh := vs.y
		var ww := hh * title_bg.get_width() / title_bg.get_height()
		var ox := -fposmod(title_t * 12.0, ww)
		var x := ox
		while x < vs.x:
			draw_texture_rect(title_bg, Rect2(x, 0, ww, hh), false)
			x += ww
	draw_rect(Rect2(Vector2.ZERO, vs), Color(0, 0, 0.05, 0.35))
	var k := vs.y / 1080.0
	if title_hero:
		var h := vs.y * 0.86
		var w := h * title_hero.get_width() / title_hero.get_height()
		draw_texture_rect(title_hero, Rect2(vs.x * 0.62 - w * 0.5, vs.y - h + 6 * sin(title_t * 1.4) * k, w, h), false)
	var f := ThemeDB.fallback_font
	draw_string_outline(f, Vector2(110 * k, 260 * k), "NINJA SCHOOL", HORIZONTAL_ALIGNMENT_LEFT, -1, int(120 * k), int(14 * k), Color(0.1, 0.02, 0.05))
	draw_string(f, Vector2(110 * k, 260 * k), "NINJA SCHOOL", HORIZONTAL_ALIGNMENT_LEFT, -1, int(120 * k), Color(1, 0.86, 0.4))
	draw_string_outline(f, Vector2(116 * k, 340 * k), "Ninja School 1 — bản PC", HORIZONTAL_ALIGNMENT_LEFT, -1, int(48 * k), int(8 * k), Color(0, 0, 0))
	draw_string(f, Vector2(116 * k, 340 * k), "Ninja School 1 — bản PC", HORIZONTAL_ALIGNMENT_LEFT, -1, int(48 * k), Color(1, 1, 1))
	for i in title_opts.size():
		var y := (520 + 86 * i) * k
		var dis: bool = i == 1 and not S.has_save()
		var c := Color(0.5, 0.5, 0.55) if dis else (Color(1, 0.9, 0.5) if i == title_sel else Color(1, 1, 1))
		if i == title_sel:
			draw_rect(Rect2(90 * k, y - 58 * k, 520 * k, 76 * k), Color(0.95, 0.75, 0.3, 0.22))
			draw_rect(Rect2(90 * k, y - 58 * k, 8 * k, 76 * k), Color(0.95, 0.75, 0.3))
		draw_string_outline(f, Vector2(130 * k, y), str(title_opts[i]), HORIZONTAL_ALIGNMENT_LEFT, -1, int(52 * k), int(8 * k), Color(0, 0, 0))
		draw_string(f, Vector2(130 * k, y), str(title_opts[i]), HORIZONTAL_ALIGNMENT_LEFT, -1, int(52 * k), c)
	var help := "W/S chọn · J xác nhận · F10 cài đặt · F11 toàn màn hình"
	draw_string(f, Vector2(110 * k, vs.y - 50 * k), help, HORIZONTAL_ALIGNMENT_LEFT, -1, int(26 * k), Color(1, 1, 1, 0.7))
