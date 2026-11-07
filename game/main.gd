# Main scene: runs the original logic at its native 25 Hz and presents it at display rate.
extends Node2D

const LOGICAL := Vector2i(480, 320)   # original canvas; in-world view shows the top 480x270 (HUD redrawn as overlay)
const WORLD_H := 270
const TICK := 0.04

var core
var acc := 0.0
var view: Node2D
var renderer
var hd
var overlay
var auto_lines: PackedStringArray = []
var auto_i := 0
var auto_wait := 0
var auto_shots := ""
var held_keys: Dictionary = {}

func _ready() -> void:
	Engine.max_fps = 0
	core = load("res://core/core.gd").new()
	core.boot(LOGICAL.x, LOGICAL.y)
	Input.set_use_accumulated_input(false)
	view = Node2D.new()
	add_child(view)
	renderer = load("res://render/cmd_renderer.gd").new()
	renderer.core = core
	view.add_child(renderer)
	hd = load("res://render/hd_world.gd").new()
	add_child(hd)
	hd.setup(core, renderer)
	overlay = load("res://render/overlay.gd").new()
	overlay.core = core
	overlay.main = self
	add_child(overlay)
	get_viewport().size_changed.connect(_layout)
	_layout()
	Controls.ensure_defaults()
	var args := OS.get_cmdline_user_args()
	for i in args.size():
		if args[i] == "--auto" and i + 1 < args.size():
			auto_lines = FileAccess.get_file_as_string(args[i + 1]).split("\n")
		if args[i] == "--shots" and i + 1 < args.size():
			auto_shots = args[i + 1]
			DirAccess.make_dir_recursive_absolute(auto_shots)

var world_mode := true

func _layout() -> void:
	var vs := get_viewport_rect().size
	var shown := Vector2(LOGICAL.x, WORLD_H if world_mode else LOGICAL.y)
	var s: float = min(vs.x / shown.x, vs.y / shown.y)
	view.scale = Vector2(s, s)
	view.position = ((vs - shown * s) / 2.0).floor()
	renderer.text_scale = s
	RenderingServer.set_default_clear_color(Color.BLACK)

func _unhandled_input(event: InputEvent) -> void:
	if not auto_lines.is_empty():
		return
	for action in Controls.ACTIONS:
		if event.is_action_pressed(action, false, true):
			_press(action, true)
		elif event.is_action_released(action, true):
			_press(action, false)
	if event.is_action_pressed("toggle_fullscreen"):
		var w := get_window()
		w.mode = Window.MODE_WINDOWED if w.mode == Window.MODE_FULLSCREEN else Window.MODE_FULLSCREEN

func _press(action: String, down: bool) -> void:
	var code: int = Controls.code_for(action, core)
	if code == 0:
		return
	if down:
		if held_keys.get(action, false):
			return
		held_keys[action] = code
		core.key_down(code)
	else:
		var c = held_keys.get(action, code)
		held_keys.erase(action)
		core.key_up(int(c))

func _process(delta: float) -> void:
	acc += min(delta, 0.25)
	var stepped := false
	while acc >= TICK:
		acc -= TICK
		if not auto_lines.is_empty():
			_auto_step()
		core.tick()
		stepped = true
		if core.quit_requested:
			get_tree().quit()
			return
	var wm: bool = core.d == 1 or core.d == 7 or core.d == 2
	if wm != world_mode:
		world_mode = wm
		_layout()
	hd.alpha = acc / TICK
	renderer.alpha = acc / TICK
	if stepped:
		renderer.on_tick()
		hd.on_tick()
	renderer.queue_redraw()

func _auto_step() -> void:
	if auto_wait > 0:
		auto_wait -= 1
		return
	while auto_i < auto_lines.size():
		var t := auto_lines[auto_i].strip_edges().split(" ", false)
		auto_i += 1
		if t.is_empty() or t[0].begins_with("#"):
			continue
		match t[0]:
			"wait":
				auto_wait = int(t[1]) / 40
				return
			"press":
				core.key_down(int(t[1]))
			"release":
				core.key_up(int(t[1]))
			"tap":
				core.key_down(int(t[1]))
				auto_lines.insert(auto_i, "release " + t[1])
				auto_lines.insert(auto_i, "wait 80")
				auto_lines.insert(auto_i + 2, "wait 120")
			"shot":
				_capture.call_deferred(t[1])
			"set":
				core.set(t[1], int(t[2]))
			"quit":
				get_tree().quit()
				return
	auto_lines = PackedStringArray()

func _capture(name: String) -> void:
	await RenderingServer.frame_post_draw
	var img := get_viewport().get_texture().get_image()
	img.save_png(auto_shots + "/" + name + ".png")
