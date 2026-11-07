extends Node2D

# Bản thử kỹ thuật Ninja School 1 — sảnh tập luyện.
# Trạng thái: menu, playing, paused, win, dead.
enum State { MENU, PLAYING, PAUSED, WIN, DEAD }

const ENEMY_COUNT := 3
const GATE_POS := Vector2(880, 470)

var state: int = State.MENU
var _player: Player = null
var _enemies: Array[Enemy] = []
var _enemies_defeated: int = 0
var _hud: CanvasLayer = null
var _menu: CanvasLayer = null
var _overlay: CanvasLayer = null
var _world: Node2D = null

func _ready() -> void:
	randomize()
	_build_world()
	_build_ui()
	_show_menu()

func _build_world() -> void:
	_world = Node2D.new()
	_world.name = "World"
	add_child(_world)

	# Nền (vẽ vector)
	var bg := _make_bg()
	bg.name = "Background"
	_world.add_child(bg)

	# Sàn
	var floor := StaticBody2D.new()
	floor.name = "Floor"
	var floor_shape := RectangleShape2D.new()
	floor_shape.size = Vector2(960, 40)
	var floor_col := CollisionShape2D.new()
	floor_col.shape = floor_shape
	floor.add_child(floor_col)
	floor.position = Vector2(480, 520)
	_world.add_child(floor)

	# Nền tảng
	_add_platform(Vector2(240, 400), Vector2(160, 16))
	_add_platform(Vector2(560, 340), Vector2(160, 16))
	_add_platform(Vector2(760, 430), Vector2(120, 16))

	# Cổng (vùng hoàn thành)
	var gate := Area2D.new()
	gate.name = "Gate"
	var gate_shape := RectangleShape2D.new()
	gate_shape.size = Vector2(40, 80)
	var gate_col := CollisionShape2D.new()
	gate_col.shape = gate_shape
	gate.add_child(gate_col)
	gate.position = GATE_POS
	gate.body_entered.connect(_on_gate_body_entered)
	_world.add_child(gate)

	# Người chơi
	_player = Player.new()
	_player.name = "Player"
	_player.position = Vector2(120, 470)
	_player.died.connect(_on_player_died)
	_world.add_child(_player)

	# Kẻ địch
	_spawn_enemies()

func _add_platform(pos: Vector2, size: Vector2) -> void:
	var body := StaticBody2D.new()
	var shape := RectangleShape2D.new()
	shape.size = size
	var col := CollisionShape2D.new()
	col.shape = shape
	body.add_child(col)
	body.position = pos
	_world.add_child(body)

func _spawn_enemies() -> void:
	var spots := [Vector2(360, 470), Vector2(600, 300), Vector2(760, 400)]
	for i in ENEMY_COUNT:
		var e := Enemy.new()
		e.name = "Enemy%d" % i
		e.position = spots[i % spots.size()]
		e.set_player(_player)
		e.defeated.connect(_on_enemy_defeated)
		_world.add_child(e)
		_enemies.append(e)

func _make_bg() -> Node2D:
	var bg := Node2D.new()
	bg.set_script(load("res://bg.gd"))
	return bg

func _build_ui() -> void:
	_hud = CanvasLayer.new()
	_hud.name = "HUD"
	add_child(_hud)
	_overlay = CanvasLayer.new()
	_overlay.name = "Overlay"
	add_child(_overlay)
	_menu = CanvasLayer.new()
	_menu.name = "Menu"
	add_child(_menu)

func _show_menu() -> void:
	_world.process_mode = Node.PROCESS_MODE_DISABLED
	state = State.MENU
	_clear_layer(_menu)
	_clear_layer(_overlay)
	var title := Label.new()
	title.text = "Ninja School 1 — Bản thử kỹ thuật"
	title.add_theme_font_size_override("font_size", 34)
	title.add_theme_color_override("font_color", Color(0.95, 0.85, 0.5))
	title.position = Vector2(280, 120)
	_menu.add_child(title)

	var sub := Label.new()
	sub.text = "Sảnh tập luyện — bản thử, chưa phải bản đồ gốc"
	sub.add_theme_font_size_override("font_size", 16)
	sub.add_theme_color_override("font_color", Color(0.7, 0.8, 0.8))
	sub.position = Vector2(280, 165)
	_menu.add_child(sub)

	var play := Button.new()
	play.text = "Chơi thử"
	play.position = Vector2(430, 230)
	play.pressed.connect(start_game)
	_menu.add_child(play)

	var keys := Label.new()
	keys.text = "A/D hoặc ←/→: di chuyển\nSpace/W: nhảy\nJ: tấn công\nR: chơi lại\nEsc: tạm dừng\nF11: toàn màn hình"
	keys.add_theme_font_size_override("font_size", 15)
	keys.add_theme_color_override("font_color", Color(0.85, 0.85, 0.85))
	keys.position = Vector2(360, 300)
	_menu.add_child(keys)

func start_game() -> void:
	_clear_layer(_menu)
	_clear_layer(_overlay)
	_reset_play()
	state = State.PLAYING
	_update_hud()

func restart_game() -> void:
	_reset_play()
	state = State.PLAYING
	_update_hud()

func _reset_play() -> void:
	_clear_layer(_overlay)
	_world.process_mode = Node.PROCESS_MODE_INHERIT
	_enemies_defeated = 0
	_player.reset()
	_player.position = Vector2(120, 470)
	_player.velocity = Vector2.ZERO
	for e in _enemies:
		if is_instance_valid(e):
			e.queue_free()
	_enemies.clear()
	_spawn_enemies()

func _on_enemy_defeated() -> void:
	_enemies_defeated += 1
	_update_hud()

func _on_player_died() -> void:
	state = State.DEAD
	_show_overlay("Bạn đã ngã xuống", "Nhấn R để chơi lại")

func _on_gate_body_entered(body: Node) -> void:
	if state != State.PLAYING:
		return
	if body == _player and _enemies_defeated >= ENEMY_COUNT:
		state = State.WIN
		_show_overlay("Hoàn thành sảnh tập!", "Nhấn R để chơi lại")

func _show_overlay(title: String, sub: String) -> void:
	_clear_layer(_overlay)
	var t := Label.new()
	t.text = title
	t.add_theme_font_size_override("font_size", 32)
	t.add_theme_color_override("font_color", Color(0.95, 0.85, 0.5))
	t.position = Vector2(360, 220)
	_overlay.add_child(t)
	var s := Label.new()
	s.text = sub
	s.add_theme_font_size_override("font_size", 18)
	s.add_theme_color_override("font_color", Color(0.8, 0.85, 0.85))
	s.position = Vector2(400, 270)
	_overlay.add_child(s)

func _update_hud() -> void:
	_clear_layer(_hud)
	var hp := Label.new()
	hp.text = "Máu: %d/%d   Kẻ địch: %d/%d" % [_player.health, _player.max_health, _enemies_defeated, ENEMY_COUNT]
	hp.add_theme_font_size_override("font_size", 18)
	hp.add_theme_color_override("font_color", Color(0.95, 0.9, 0.6))
	hp.position = Vector2(16, 12)
	_hud.add_child(hp)

func _clear_layer(layer: CanvasLayer) -> void:
	for c in layer.get_children():
		c.queue_free()

func _unhandled_input(event: InputEvent) -> void:
	if event is InputEventKey and event.pressed:
		match event.keycode:
			KEY_R:
				if state == State.PLAYING or state == State.DEAD or state == State.WIN:
					restart_game()
			KEY_ESCAPE:
				if state == State.PLAYING:
					state = State.PAUSED
					_world.process_mode = Node.PROCESS_MODE_DISABLED
					_show_overlay("Tạm dừng", "Nhấn Esc để tiếp tục")
				elif state == State.PAUSED:
					_world.process_mode = Node.PROCESS_MODE_INHERIT
					_clear_layer(_overlay)
					state = State.PLAYING
			KEY_F11:
				var dw := DisplayServer.window_get_mode()
				DisplayServer.window_set_mode(DisplayServer.WINDOW_MODE_FULLSCREEN if dw != DisplayServer.WINDOW_MODE_FULLSCREEN else DisplayServer.WINDOW_MODE_WINDOWED)

func _physics_process(_delta: float) -> void:
	if state == State.PLAYING:
		_update_hud()

# --- API cho smoke test ---
func get_player() -> Node2D:
	return _player

func get_enemies() -> Array:
	return _enemies
