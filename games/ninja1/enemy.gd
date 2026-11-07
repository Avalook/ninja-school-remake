class_name Enemy
extends CharacterBody2D

# Kẻ địch ninja — bản thử kỹ thuật (giá trị TẠM THỜI).
signal defeated

const SPEED := 90.0
const GRAVITY := 1000.0
const CHASE_RANGE := 220.0
const ATTACK_RANGE := 34.0
const ATTACK_DAMAGE := 12
const ATTACK_COOLDOWN := 1.0

var health: int = 60
var max_health: int = 60
var facing: int = 1
var _cooldown: float = 0.0
var _patrol_dir: int = 1
var _player: Node2D = null

func _ready() -> void:
	add_to_group("enemy")
	var collision := CollisionShape2D.new()
	var shape := RectangleShape2D.new()
	shape.size = Vector2(24, 40)
	collision.shape = shape
	add_child(collision)
	_patrol_dir = 1 if randf() < 0.5 else -1

func set_player(p: Node2D) -> void:
	_player = p

func _physics_process(delta: float) -> void:
	_cooldown = maxf(0.0, _cooldown - delta)
	velocity.y += GRAVITY * delta
	if _player != null and is_instance_valid(_player):
		var dx: float = _player.position.x - position.x
		if absf(dx) < CHASE_RANGE:
			facing = 1 if dx > 0.0 else -1
			velocity.x = facing * SPEED
			if absf(dx) < ATTACK_RANGE and _cooldown <= 0.0:
				_cooldown = ATTACK_COOLDOWN
				if _player.has_method("take_damage"):
					_player.take_damage(ATTACK_DAMAGE)
		else:
			velocity.x = _patrol_dir * SPEED * 0.6
			facing = _patrol_dir
	else:
		velocity.x = _patrol_dir * SPEED * 0.6
		facing = _patrol_dir
	move_and_slide()
	# Đổi hướng khi chạm tường.
	if is_on_wall():
		_patrol_dir = -_patrol_dir
	queue_redraw()

func take_damage(amount: int) -> void:
	if health <= 0:
		return
	health = maxi(0, health - amount)
	if health <= 0:
		defeated.emit()
		queue_free()

func reset() -> void:
	health = max_health
	_cooldown = 0.0
	velocity = Vector2.ZERO

func _draw() -> void:
	var body_color := Color(0.35, 0.16, 0.16)
	var scarf := Color(0.5, 0.4, 0.2)
	var sword := Color(0.7, 0.7, 0.75)
	draw_rect(Rect2(-9, -20, 18, 24), body_color)
	draw_circle(Vector2(0, -26), 7.0, body_color)
	draw_rect(Rect2(-9, -28, 18, 4), scarf)
	var sx := 9.0 * facing
	draw_line(Vector2(sx, -12), Vector2(sx + 16.0 * facing, -18), sword, 3.0)
	# Thanh máu
	var w := 20.0 * (float(health) / float(max_health))
	draw_rect(Rect2(-10, -38, 20, 3), Color(0.2, 0.1, 0.1))
	draw_rect(Rect2(-10, -38, w, 3), Color(0.8, 0.2, 0.2))
