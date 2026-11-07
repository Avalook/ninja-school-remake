class_name Player
extends CharacterBody2D

signal died

const SPEED := 220.0
const JUMP_VELOCITY := -420.0
const GRAVITY := 1000.0
const ATTACK_COOLDOWN := 0.4
const ATTACK_RANGE := 46.0
const ATTACK_DAMAGE := 34

var max_health := 100
var health := 100
var facing := 1
var _cooldown := 0.0
var _slash_timer := 0.0

func _ready() -> void:
	add_to_group("player")
	var col := CollisionShape2D.new()
	var shape := RectangleShape2D.new()
	shape.size = Vector2(24, 40)
	col.shape = shape
	col.position = Vector2(0, 0)
	add_child(col)

func _physics_process(delta: float) -> void:
	_cooldown = maxf(0.0, _cooldown - delta)
	_slash_timer = maxf(0.0, _slash_timer - delta)
	if not is_on_floor():
		velocity.y += GRAVITY * delta
	var dir := Input.get_axis("move_left", "move_right")
	if dir != 0.0:
		velocity.x = dir * SPEED
		facing = 1.0 if dir > 0.0 else -1.0
	else:
		velocity.x = 0.0
	if Input.is_action_just_pressed("jump") and is_on_floor():
		velocity.y = JUMP_VELOCITY
	if Input.is_action_just_pressed("attack") and _cooldown <= 0.0:
		attack()
	move_and_slide()
	queue_redraw()

func attack() -> void:
	if _cooldown > 0.0:
		return
	_cooldown = ATTACK_COOLDOWN
	_slash_timer = 0.18
	# Tìm kẻ địch trong tầm chém theo hướng nhìn.
	for e in get_tree().get_nodes_in_group("enemy"):
		if not is_instance_valid(e):
			continue
		var dx: float = e.position.x - position.x
		if absf(e.position.y - position.y) < 40.0 and absf(dx) < ATTACK_RANGE and signf(dx) == facing:
			e.take_damage(ATTACK_DAMAGE)

func take_damage(amount: int) -> void:
	health -= amount
	if health <= 0:
		health = 0
		died.emit()

func reset() -> void:
	health = max_health
	velocity = Vector2.ZERO
	_cooldown = 0.0
	_slash_timer = 0.0
	facing = 1.0

func _draw() -> void:
	# Bóng ninja tạm thời
	var body_c := Color(0.12, 0.12, 0.16)
	draw_rect(Rect2(-12, -20, 24, 40), body_c)
	# Đầu
	draw_circle(Vector2(0, -26), 10.0, body_c)
	# Khăn
	draw_rect(Rect2(-12 * facing, -30, 24 * facing, 6), Color(0.8, 0.25, 0.2))
	# Mắt
	draw_rect(Rect2(2 * facing, -28, 4, 3), Color(0.9, 0.9, 0.9))
	# Kiếm
	var sword_c := Color(0.75, 0.78, 0.85)
	draw_rect(Rect2(8 * facing, -10, 18 * facing, 3), sword_c)
	# Vệt chém
	if _slash_timer > 0.0:
		var a := _slash_timer / 0.18
		var c := Color(0.95, 0.9, 0.5, a)
		draw_arc(Vector2(0, -5), 34.0, 0.0, TAU, 24, c, 4.0)
