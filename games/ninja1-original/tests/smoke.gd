extends SceneTree

class SmokeEnemy extends Node2D:
    var health: int = 200
    var hit_count: int = 0
    var last_knockback: int = 0

    func take_source_hit(damage: int, knockback_direction: int) -> void:
        health -= damage
        hit_count += 1
        last_knockback = knockback_direction

func _initialize() -> void:
    call_deferred("_run")

func _run() -> void:
    var packed = load("res://original_map.tscn")
    assert(packed != null, "Original map scene loads")
    var scene = packed.instantiate()
    root.add_child(scene)
    await process_frame
    await process_frame
    assert(scene.grid.id == 99, "Fresh game begins on original map99")
    assert(scene.grid.columns == 70 and scene.grid.rows == 15, "Original map99 dimensions")
    assert(scene.grid.cells.size() == 15, "Exact map99 row count")
    for row in scene.grid.cells:
        assert(row.size() == 70, "Exact map99 column count")
    assert(scene.grid.cells[0][0] == 39 and scene.grid.cells[13][4] == 2, "Source map99 landmarks and start floor")
    assert(scene.grid.cells[14][69] == 22, "Original map99 final tile")
    assert(scene.player.position == Vector2(108.0, 312.0), "New-game spawn from source")
    assert(not scene.player.get("facing_right"), "New-game facing from source")
    assert(scene.get_tile_rect(-1) == Rect2(), "Negative tile excluded")
    assert(scene.atlas.get_size() == Vector2(24, 2880), "Original atlas dimensions")
    assert(scene.get_tile_rect(1) == Rect2(0, 0, 24, 24), "Tile1 crop")
    assert(scene.get_tile_rect(120) == Rect2(0, 2856, 24, 24), "Tile120 crop")
    assert(scene.get_tile_rect(0) == Rect2() and scene.get_tile_rect(121) == Rect2(), "Invalid tile excluded")
    var expected := 0
    var solid_ids := [1, 2, 3, 4, 5, 6, 7, 36, 37, 54, 91, 92, 93, 94, 73, 74, 97, 98, 120]
    var expected_solid := 0
    for row in scene.grid.cells:
        for id in row:
            if id >= 1 and id <= 120:
                expected += 1
            if id in solid_ids:
                expected_solid += 1
    assert(scene.tile_count == expected, "All map99 cells accounted for")
    assert(scene.terrain_collision.get("solid_tile_count") == expected_solid, "Verified ground collider count")
    assert(scene.terrain_collision.get_child(0).get_child_count() == expected_solid, "One collider per solid cell")
    await physics_frame
    var query := PhysicsRayQueryParameters2D.create(Vector2(108, 300), Vector2(108, 350), 1)
    var hit: Dictionary = scene.get_world_2d().direct_space_state.intersect_ray(query)
    assert(not hit.is_empty(), "Map99 spawn floor collides")
    assert(scene.camera.zoom == Vector2(1.5, 1.5), "World zoom")
    for _step in range(4):
        await physics_frame
    assert(scene.player.is_on_floor(), "New-game player stands on original map99 ground")
    var expected_camera_x := clampf(scene.player.position.x, 1280.0 / 3.0, 1680.0 - 1280.0 / 3.0)
    assert(is_equal_approx(scene.camera.position.x, expected_camera_x), "Camera follows within map bounds")
    assert((scene.player.get_node("Head") as Sprite2D).texture != null, "Original head sprite loaded")
    assert((scene.player.get_node("Legs") as Sprite2D).texture != null, "Original leg sprite loaded")
    assert((scene.player.get_node("Body") as Sprite2D).texture != null, "Original body sprite loaded")
    assert((scene.player.get_node("CollisionShape2D") as CollisionShape2D).shape != null, "Player collider loaded")
    var start_x: float = scene.player.position.x
    Input.action_press("ui_right")
    for _step in range(4):
        await physics_frame
    Input.action_release("ui_right")
    assert(scene.player.position.x > start_x, "Right input moves player")
    var start_y: float = scene.player.position.y
    Input.action_press("ui_accept")
    await physics_frame
    Input.action_release("ui_accept")
    assert(scene.player.position.y < start_y, "Jump input moves player upward")
    var attack_has_j: bool = false
    var attack_has_kp5: bool = false
    for event: InputEvent in InputMap.action_get_events("attack"):
        if event is InputEventKey:
            attack_has_j = attack_has_j or (event as InputEventKey).physical_keycode == KEY_J
            attack_has_kp5 = attack_has_kp5 or (event as InputEventKey).physical_keycode == KEY_KP_5
    assert(attack_has_j and attack_has_kp5, "Attack has J and keypad-5 PC bindings")
    var out_of_range_target := SmokeEnemy.new()
    out_of_range_target.add_to_group("enemies")
    out_of_range_target.position = scene.player.position + Vector2(50.0, -35.0)
    scene.add_child(out_of_range_target)
    var combat_target := SmokeEnemy.new()
    combat_target.add_to_group("enemies")
    combat_target.position = scene.player.position + Vector2(30.0, -35.0)
    scene.add_child(combat_target)
    Input.action_press("attack")
    await physics_frame
    Input.action_release("attack")
    assert(scene.player.get("is_attacking"), "PC attack action starts original attack state")
    for _step in range(3):
        await physics_frame
    assert(scene.player.get("attack_window_open"), "Attack window opens on source attack frames")
    for _step in range(2):
        await physics_frame
    assert(combat_target.health < 200 and combat_target.hit_count == 1, "Source melee hit damages one target once")
    var dealt_damage: int = 200 - combat_target.health
    assert(dealt_damage >= 20 and dealt_damage <= 29, "Source base attack power and variance are applied")
    assert(combat_target.last_knockback == -1, "Melee knockback points away from right-facing player")
    assert(out_of_range_target.health == 200 and out_of_range_target.hit_count == 0, "Melee hitbox excludes targets outside source range")
    for _step in range(2):
        await physics_frame
    assert(not scene.player.get("attack_window_open"), "Attack window closes after active frames")
    for _step in range(7):
        await physics_frame
    assert(not scene.player.get("is_attacking"), "Original basic attack animation returns to idle")
    scene.pan_camera(Vector2(100, 0))
    scene.pan_camera(Vector2(999999, 999999))
    assert(is_equal_approx(scene.camera.position.x, 1680.0 - 1280.0 / 3.0), "Camera right boundary")
    assert(is_equal_approx(scene.camera.position.y, 180.0), "Short map centers vertically")
    scene.pan_camera(Vector2(-999999, -999999))
    assert(is_equal_approx(scene.camera.position.x, 1280.0 / 3.0), "Camera left boundary")
    var map4_packed = load("res://map004.tscn")
    assert(map4_packed != null, "Map4 transition scene exists")
    var map4 = map4_packed.instantiate()
    root.add_child(map4)
    assert(map4.player.position == Vector2(48.0, 120.0), "Map4 uses source map99 exit coordinates")
    await process_frame
    await process_frame
    assert(map4.grid.id == 4 and map4.grid.columns == 80 and map4.grid.rows == 15, "Original map4 data loads at verified dimensions")
    assert(not map4.player.get("facing_right"), "Map4 entry faces source direction")
    assert(map4.opening_title_panel != null, "Map4 opening card is created from source sequence")
    await create_timer(0.25).timeout
    assert(map4.opening_title_shown and map4.opening_title_panel.visible, "Map4 opening title appears after the source five-tick delay")
    assert((map4.opening_title_panel.get_child(0) as Label).text == "2 ngày sau, tại rừng trúc...", "Map4 uses exact source title aH[1]")
    assert(map4.atlas.get_size() == Vector2(24.0, 2880.0), "Original map4 uses the game atlas")
    print("SMOKE_PASS ORIGINAL_MAP99_AND_MAP4: map99 movement/jump/camera/melee and map4 data/entry")
    quit(0)
