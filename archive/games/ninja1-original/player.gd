extends CharacterBody2D

const FRAME_DATA: Array = [
    [[0, -10, 32], [1, -7, 7], [1, -11, 15]],
    [[0, -10, 33], [1, -7, 7], [1, -11, 16]],
    [[1, -10, 33], [2, -10, 11], [2, -9, 16]],
    [[1, -10, 32], [3, -11, 9], [3, -11, 16]],
    [[1, -10, 34], [4, -9, 9], [4, -8, 16]],
    [[1, -10, 34], [5, -11, 11], [5, -10, 17]],
    [[1, -10, 33], [6, -9, 9], [6, -8, 16]],
]
const HEAD_PATHS: Array = ["res://assets/cp/ch0.png", "res://assets/cp/ch1.png"]
const LEGS_PATHS: Array = ["", "res://assets/cp/cl1.png", "res://assets/cp/cl2.png", "res://assets/cp/cl3.png", "res://assets/cp/cl4.png", "res://assets/cp/cl5.png", "res://assets/cp/cl6.png"]
const BODY_PATHS: Array = ["", "res://assets/cp/cb1.png", "res://assets/cp/cb2.png", "res://assets/cp/cb3.png", "res://assets/cp/cb4.png", "res://assets/cp/cb5.png", "res://assets/cp/cb6.png"]

# Source: aa=40ms, P=5 px/update, jump z=-8, gravity +1/update, fall cap18.
const TICK_HZ: float = 25.0
const WALK_SPEED: float = 5.0 * TICK_HZ
const HORIZONTAL_DECEL: float = 1.0 * TICK_HZ * TICK_HZ
const GRAVITY: float = 1.0 * TICK_HZ * TICK_HZ
const JUMP_VELOCITY: float = -8.0 * TICK_HZ
const MAX_FALL_SPEED: float = 18.0 * TICK_HZ
# Source's combo-free A=5 path runs 8 + 2 + 5 updates (a.java:5080-5388).
const SOURCE_ATTACK_TOTAL_TICKS: int = 15
const SOURCE_ATTACK_WINDOW_START: int = 4
const SOURCE_ATTACK_WINDOW_END: int = 7
const SOURCE_ATTACK_HIT_TICK: int = 6
# New-game base stats from a.java:n(0), weapon index x defaults to 0.
const SOURCE_STARTING_ATTACK_POWER: int = 20
const SOURCE_PLAYER_HEIGHT: int = 32

signal attack_started
signal attack_window_changed(is_open: bool)
signal melee_hit(target: Node2D, damage: int, knockback_direction: int)

var head: Sprite2D
var legs: Sprite2D
var body: Sprite2D
var facing_right: bool = true
var frame_index: int = 0
var jump_pressed: bool = false
var walk_tick: int = 0
var idle_tick: int = 0
var is_attacking: bool = false
var attack_window_open: bool = false
var attack_queued: bool = false
var attack_tick: int = 0
var attack_button_pressed: bool = false
var source_tick: int = 0
var attack_power: int = SOURCE_STARTING_ATTACK_POWER
var weapon_index: int = 0
var double_damage_enabled: bool = false

func _ready() -> void:
    _register_attack_bindings()
    head = Sprite2D.new()
    head.name = "Head"
    legs = Sprite2D.new()
    legs.name = "Legs"
    body = Sprite2D.new()
    body.name = "Body"
    for sprite in [head, legs, body]:
        sprite.centered = false
        sprite.texture_filter = CanvasItem.TEXTURE_FILTER_NEAREST
        add_child(sprite)
    var shape := RectangleShape2D.new()
    shape.size = Vector2(14.0, 32.0)
    var col := CollisionShape2D.new()
    col.name = "CollisionShape2D"
    col.shape = shape
    col.position = Vector2(0.0, -16.0)
    add_child(col)
    _set_frame(0)

func _physics_process(delta: float) -> void:
    source_tick += 1
    var attack_now: bool = Input.is_action_pressed("attack")
    if attack_now and not attack_button_pressed:
        if is_attacking:
            attack_queued = true
        else:
            _start_attack()
    attack_button_pressed = attack_now
    _advance_attack()

    var left: bool = Input.is_key_pressed(KEY_A) or Input.is_action_pressed("ui_left")
    var right: bool = Input.is_key_pressed(KEY_D) or Input.is_action_pressed("ui_right")
    var input_dir: float = -1.0 if left and not right else (1.0 if right and not left else 0.0)
    var turned_only: bool = false
    if input_dir != 0.0:
        var next_facing_right: bool = input_dir > 0.0
        if facing_right != next_facing_right:
            facing_right = next_facing_right
            velocity.x = 0.0
            turned_only = true
        else:
            velocity.x = input_dir * WALK_SPEED
    else:
        velocity.x = move_toward(velocity.x, 0.0, HORIZONTAL_DECEL * delta)
    if not is_on_floor():
        velocity.y = minf(velocity.y + GRAVITY * delta, MAX_FALL_SPEED)
    elif velocity.y > 0.0:
        velocity.y = 0.0
    var jump_now: bool = Input.is_key_pressed(KEY_W) or Input.is_action_pressed("ui_accept")
    if jump_now and not jump_pressed and is_on_floor():
        velocity.y = JUMP_VELOCITY
    jump_pressed = jump_now
    move_and_slide()
    _update_anim(not turned_only and (input_dir != 0.0 or absf(velocity.x) > 1.0))

func _register_attack_bindings() -> void:
    if not InputMap.has_action("attack"):
        InputMap.add_action("attack")
    for key_code: Key in [KEY_J, KEY_KP_5]:
        var found: bool = false
        for event: InputEvent in InputMap.action_get_events("attack"):
            if event is InputEventKey and (event as InputEventKey).physical_keycode == key_code:
                found = true
                break
        if not found:
            var key_event := InputEventKey.new()
            key_event.physical_keycode = key_code
            InputMap.action_add_event("attack", key_event)

func _start_attack() -> void:
    is_attacking = true
    attack_tick = 0
    attack_queued = false
    attack_started.emit()

func _advance_attack() -> void:
    if not is_attacking:
        return
    attack_tick += 1
    var next_window: bool = attack_tick >= SOURCE_ATTACK_WINDOW_START and attack_tick <= SOURCE_ATTACK_WINDOW_END
    if next_window != attack_window_open:
        attack_window_open = next_window
        attack_window_changed.emit(attack_window_open)
        queue_redraw()
    if attack_tick == SOURCE_ATTACK_HIT_TICK:
        _resolve_source_melee_hit()
    if attack_tick >= SOURCE_ATTACK_TOTAL_TICKS:
        is_attacking = false
        if attack_queued:
            _start_attack()

func _resolve_source_melee_hit() -> void:
    # Mirrors a.java:A() and u(): one target in front, within the source bounds.
    var min_x: float = -20.0 if facing_right else -40.0
    var max_x: float = 40.0 if facing_right else 20.0
    var min_y: float = -float(SOURCE_PLAYER_HEIGHT + 15)
    var max_y: float = 5.0
    var safe_power: int = maxi(2, attack_power)
    # a.java:u(): E + w % (E >> 1), plus weapon index x and fA's double-damage flag.
    var damage: int = safe_power + (source_tick % (safe_power >> 1))
    damage += (damage >> 1) * maxi(0, weapon_index)
    if double_damage_enabled:
        damage <<= 1
    var knockback_direction: int = -1 if facing_right else 1
    for candidate: Node in get_tree().get_nodes_in_group("enemies"):
        if not candidate is Node2D or not candidate.has_method("take_source_hit"):
            continue
        var target := candidate as Node2D
        var relative: Vector2 = to_local(target.global_position)
        if relative.x < min_x or relative.x > max_x or relative.y < min_y or relative.y > max_y:
            continue
        target.call("take_source_hit", damage, knockback_direction)
        melee_hit.emit(target, damage, knockback_direction)
        break

func _draw() -> void:
    if attack_window_open:
        var direction: float = 1.0 if facing_right else -1.0
        draw_line(Vector2(8.0 * direction, -20.0), Vector2(31.0 * direction, -31.0), Color(1.0, 0.9, 0.55, 0.95), 3.0, true)
        draw_line(Vector2(12.0 * direction, -16.0), Vector2(28.0 * direction, -27.0), Color(0.85, 0.96, 1.0, 0.8), 1.5, true)

func _update_anim(moving: bool) -> void:
    if moving:
        idle_tick = 0
        walk_tick = (walk_tick + 1) % 10
        frame_index = (walk_tick >> 1) + 2
    else:
        walk_tick = 0
        idle_tick = (idle_tick + 1) % 31
        frame_index = 0 if idle_tick % 15 < 5 else 1
    _set_frame(frame_index)

func _set_frame(idx: int) -> void:
    idx = clampi(idx, 0, FRAME_DATA.size() - 1)
    frame_index = idx
    var frame: Array = FRAME_DATA[idx]
    _place(head, frame[0], HEAD_PATHS)
    _place(legs, frame[1], LEGS_PATHS)
    _place(body, frame[2], BODY_PATHS)

func _place(sprite: Sprite2D, data: Array, paths: Array) -> void:
    var asset_index: int = int(data[0])
    sprite.texture = load(paths[asset_index]) as Texture2D
    sprite.flip_h = not facing_right
    var x_offset: int = int(data[1])
    var y_offset: int = int(data[2])
    if facing_right:
        sprite.position = Vector2(float(x_offset), float(-y_offset))
    else:
        sprite.position = Vector2(float(-x_offset - sprite.texture.get_width()), float(-y_offset))
