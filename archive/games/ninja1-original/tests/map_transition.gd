extends SceneTree

func _initialize() -> void:
    call_deferred("_run")

func _run() -> void:
    var packed = load("res://original_map.tscn")
    var map99 = packed.instantiate()
    root.add_child(map99)
    current_scene = map99
    await process_frame
    map99.player.position.x = map99.world_w - 25.0
    await process_frame
    if current_scene.grid.id != 99:
        push_error("Map99 exited before the source threshold")
        quit(1)
        return
    map99.player.position.x = map99.world_w - 24.0
    await process_frame
    await process_frame
    var arrived = current_scene
    if arrived == null or arrived.grid.id != 4:
        push_error("Source map99 right exit did not load map4")
        quit(1)
        return
    if arrived.player.position.x != 48.0 or arrived.player.position.y < 120.0 or arrived.player.position.y > 132.0 or arrived.player.get("facing_right"):
        push_error("Map4 did not receive source entry position and facing")
        quit(1)
        return
    print("TRANSITION_PASS map99 right edge -> map4 at source entry (48,120), facing left")
    quit(0)
