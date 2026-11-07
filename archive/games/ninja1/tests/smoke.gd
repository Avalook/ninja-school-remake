extends SceneTree

# Smoke test: kiểm tra vòng lặp chơi thực sự của main.tscn.
var _failures: int = 0

func _init() -> void:
	_run()

func _check(cond: bool, msg: String) -> void:
	if cond:
		print("OK: ", msg)
	else:
		_failures += 1
		print("FAIL: ", msg)

func _run() -> void:
	var packed: PackedScene = load("res://main.tscn")
	if packed == null:
		print("FAIL: không tải được main.tscn")
		_finish()
		return
	var main: Node2D = packed.instantiate()
	root.add_child(main)
	await process_frame
	await process_frame

	# Bắt đầu game thực sự.
	main.start_game()
	await physics_frame

	var player: Node2D = main.get_player()
	_check(player != null, "player tồn tại")
	if player == null:
		_finish()
		return

	# Người chơi đứng trên sàn (y gần 500, không rơi xuyên).
	var y0: float = player.position.y
	for i in 30:
		await physics_frame
	var y1: float = player.position.y
	_check(y1 < 500.0, "player đứng trên sàn (y=%.1f)" % y1)
	_check(y1 >= y0 - 5.0, "player không rơi xuyên sàn")

	# Di chuyển phải.
	var x0: float = player.position.x
	for i in 20:
		Input.action_press("move_right")
		await physics_frame
	Input.action_release("move_right")
	var x1: float = player.position.x
	_check(x1 > x0 + 5.0, "di chuyển phải (dx=%.1f)" % (x1 - x0))

	# Tấn công làm giảm máu kẻ địch gần nhất.
	var enemies: Array = main.get_enemies()
	_check(enemies.size() == 3, "có 3 kẻ địch")
	for enemy in enemies:
		_check(enemy.position.y < 520.0, "quái không rơi khỏi sàn (y=%.1f)" % enemy.position.y)
	var target: Node2D = null
	var best := 1e9
	for e in enemies:
		if is_instance_valid(e):
			var d := absf(e.position.x - player.position.x)
			if d < best:
				best = d
				target = e
	if target != null:
		# Đưa player sát target.
		player.position = Vector2(target.position.x - 20.0, target.position.y)
		await physics_frame
		var hp_before: int = target.health
		player.attack()
		await physics_frame
		var hp_after: int = target.health if is_instance_valid(target) else 0
		_check(hp_after < hp_before, "tấn công làm giảm máu kẻ địch (%d -> %d)" % [hp_before, hp_after])

	# Restart khôi phục máu và số kẻ địch.
	player.take_damage(999)
	await physics_frame
	main.restart_game()
	await physics_frame
	var p2: Node2D = main.get_player()
	_check(p2.health == p2.max_health, "restart khôi phục máu")
	_check(main.get_enemies().size() == 3, "restart khôi phục 3 kẻ địch")
	_check(main.get_node("Overlay").get_child_count() == 0, "restart đóng thông báo thua/thắng")

	var pause_event := InputEventKey.new()
	pause_event.keycode = KEY_ESCAPE
	pause_event.pressed = true
	main._unhandled_input(pause_event)
	var paused_position: Vector2 = p2.position
	var enemies_paused: Array = main.get_enemies()
	var enemy_position: Vector2 = enemies_paused[0].position
	Input.action_press("move_right")
	for i in 15:
		await physics_frame
	Input.action_release("move_right")
	_check(p2.position.distance_to(paused_position) < 0.1, "tạm dừng giữ nguyên người chơi")
	_check(enemies_paused[0].position.distance_to(enemy_position) < 0.1, "tạm dừng giữ nguyên quái")
	main._unhandled_input(pause_event)
	for i in 20:
		await physics_frame
	var jump_start_y: float = p2.position.y
	Input.action_press("jump")
	for i in 3:
		await physics_frame
	Input.action_release("jump")
	for i in 5:
		await physics_frame
	_check(p2.position.y < jump_start_y - 10.0, "tiếp tục sau tạm dừng và nhảy")

	_finish()

func _finish() -> void:
	if _failures == 0:
		print("SMOKE_PASS")
		quit(0)
	else:
		print("SMOKE_FAIL (%d lỗi)" % _failures)
		quit(1)
