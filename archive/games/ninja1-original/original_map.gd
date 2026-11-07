extends Node2D

@export var map_id: int = 99
@export var atlas: Texture2D
@export var camera: Camera2D
@export var tile_count: int = 0
var grid: Dictionary = {}
var terrain_collision: Node2D
var player: CharacterBody2D
var columns: int = 0
var rows: int = 0
var world_w: float = 0.0
var world_h: float = 0.0
var opening_title_panel: PanelContainer
var opening_elapsed: float = 0.0
var opening_title_shown: bool = false

const TILE_SIZE: int = 24
const PAN_SPEED: float = 350.0

func _ready() -> void:
    RenderingServer.set_default_clear_color(Color(0.6, 0.8, 1.0))
    texture_filter = CanvasItem.TEXTURE_FILTER_NEAREST
    atlas = load("res://assets/tiles.png") as Texture2D
    if atlas == null:
        push_error("Atlas load failed")
        get_tree().quit(1)
        return
    if map_id < 0 or map_id > 999:
        push_error("Invalid map ID")
        get_tree().quit(1)
        return
    var map_name: String = "map-" + str(map_id).pad_zeros(3) + ".json"
    var file: FileAccess = FileAccess.open("res://data/" + map_name, FileAccess.READ)
    if file == null:
        push_error("Map file open failed: " + map_name)
        get_tree().quit(1)
        return
    var parsed: Variant = JSON.parse_string(file.get_as_text())
    file.close()
    if typeof(parsed) != TYPE_DICTIONARY:
        push_error("Map JSON parse failed")
        get_tree().quit(1)
        return
    var data: Dictionary = parsed
    columns = int(data.get("columns", 0))
    rows = int(data.get("rows", 0))
    if int(data.get("id", -1)) != map_id or columns <= 0 or rows <= 0 or not data.has("cells"):
        push_error("Map metadata mismatch")
        get_tree().quit(1)
        return
    var raw_cells: Variant = data["cells"]
    if typeof(raw_cells) != TYPE_ARRAY:
        push_error("Cells not array")
        get_tree().quit(1)
        return
    var cells_arr: Array = raw_cells
    if cells_arr.size() != rows:
        push_error("Row count mismatch")
        get_tree().quit(1)
        return
    var valid_grid: Array = []
    tile_count = 0
    for y in range(rows):
        var row_raw: Variant = cells_arr[y]
        if typeof(row_raw) != TYPE_ARRAY:
            push_error("Row not array")
            get_tree().quit(1)
            return
        var row_arr: Array = row_raw
        if row_arr.size() != columns:
            push_error("Col count mismatch")
            get_tree().quit(1)
            return
        var row_ids: Array = []
        for x in range(columns):
            var id_int: int = int(row_arr[x])
            if id_int < 0 or id_int > 120:
                push_error("Invalid tile ID")
                get_tree().quit(1)
                return
            if id_int != 0:
                tile_count += 1
            row_ids.append(id_int)
        valid_grid.append(row_ids)
    grid = {"id": map_id, "columns": columns, "rows": rows, "cells": valid_grid}
    world_w = float(columns * TILE_SIZE)
    world_h = float(rows * TILE_SIZE)
    var start_data: Dictionary = data.get("player_start", {})
    var spawn: Vector2 = Vector2(float(start_data.get("x", TILE_SIZE * 2)), float(start_data.get("y", TILE_SIZE * 2)))
    var initial_facing_right: bool = int(start_data.get("facing", 1)) == 1
    camera = Camera2D.new()
    camera.zoom = Vector2(1.5, 1.5)
    camera.position = Vector2(spawn.x, world_h / 2.0)
    add_child(camera)
    camera.make_current()
    var terrain_script: Script = load("res://terrain_collision.gd") as Script
    if terrain_script == null:
        push_error("Terrain collision script load failed")
        get_tree().quit(1)
        return
    terrain_collision = terrain_script.new() as Node2D
    if terrain_collision == null:
        push_error("Terrain collision component is invalid")
        get_tree().quit(1)
        return
    terrain_collision.set("grid", grid)
    terrain_collision.name = "TerrainCollision"
    add_child(terrain_collision)
    var player_script: Script = load("res://player.gd") as Script
    if player_script == null:
        push_error("Original player script failed to load")
        get_tree().quit(1)
        return
    player = CharacterBody2D.new()
    player.set_script(player_script)
    player.set("facing_right", initial_facing_right)
    player.name = "Player"
    player.position = spawn
    add_child(player)
    var layer: CanvasLayer = CanvasLayer.new()
    add_child(layer)
    var label: Label = Label.new()
    label.position = Vector2(20, 20)
    if map_id == 99:
        label.text = "Ninja School 1 · New Game · Map 99 (70×15)\nNguồn: spawn (108,312), quay trái; nhịp gốc 40ms, vật lý quy đổi từ mã\nA/D hoặc ←/→: đi · W/Space: nhảy · Esc: thoát · chiến đấu/cốt truyện đang khôi phục"
    else:
        label.text = "Ninja School 1 · Map %d · cảnh gốc đang khôi phục\nĐiều khiển thử A/D hoặc ←/→ · W/Space · Esc: thoát" % map_id
    label.add_theme_font_size_override("font_size", 14)
    layer.add_child(label)
    if map_id == 4:
        _create_opening_title()
    queue_redraw()

func _process(_delta: float) -> void:
    if Input.is_action_just_pressed("ui_cancel"):
        get_tree().quit()
        return
    if player == null or camera == null:
        return
    # Source q(99): map99's right exit leads to map4 at (2*24, 5*24), facing left.
    if map_id == 99 and player.position.x >= world_w - TILE_SIZE:
        get_tree().change_scene_to_file("res://map004.tscn")
        return
    if map_id == 4 and not opening_title_shown:
        opening_elapsed += _delta
        if opening_elapsed >= 0.2:
            opening_title_panel.visible = true
            opening_title_shown = true
    var visible_width: float = get_viewport().get_visible_rect().size.x / camera.zoom.x
    if world_w > visible_width:
        camera.position.x = clampf(player.position.x, visible_width / 2.0, world_w - visible_width / 2.0)
    else:
        camera.position.x = world_w / 2.0
    camera.position.y = world_h / 2.0 if world_h <= get_viewport().get_visible_rect().size.y / camera.zoom.y else clampf(player.position.y, get_viewport().get_visible_rect().size.y / camera.zoom.y / 2.0, world_h - get_viewport().get_visible_rect().size.y / camera.zoom.y / 2.0)

func pan_camera(delta: Vector2) -> void:
    if camera == null:
        return
    var vp_size: Vector2 = get_viewport().get_visible_rect().size / camera.zoom
    var target: Vector2 = camera.position + delta
    target.x = clampf(target.x, vp_size.x / 2.0, world_w - vp_size.x / 2.0) if world_w > vp_size.x else world_w / 2.0
    target.y = clampf(target.y, vp_size.y / 2.0, world_h - vp_size.y / 2.0) if world_h > vp_size.y else world_h / 2.0
    camera.position = target

func get_tile_rect(id: int) -> Rect2:
    if id <= 0 or id > 120:
        return Rect2()
    return Rect2(0.0, float(id - 1) * float(TILE_SIZE), float(TILE_SIZE), float(TILE_SIZE))

func _draw() -> void:
    if atlas == null:
        return
    var cells: Array = grid.get("cells", [])
    for y in range(rows):
        var row: Array = cells[y]
        for x in range(columns):
            var id_int: int = int(row[x])
            if id_int == 0:
                continue
            var src: Rect2 = get_tile_rect(id_int)
            var dst: Rect2 = Rect2(float(x) * TILE_SIZE, float(y) * TILE_SIZE, TILE_SIZE, TILE_SIZE)
            draw_texture_rect_region(atlas, dst, src)

func _create_opening_title() -> void:
    var layer := CanvasLayer.new()
    layer.name = "Map4OpeningTitle"
    layer.layer = 2
    add_child(layer)
    opening_title_panel = PanelContainer.new()
    opening_title_panel.name = "TitleCard"
    opening_title_panel.anchor_left = 0.08
    opening_title_panel.anchor_right = 0.92
    opening_title_panel.anchor_top = 0.78
    opening_title_panel.anchor_bottom = 0.94
    opening_title_panel.offset_left = 0.0
    opening_title_panel.offset_right = 0.0
    opening_title_panel.offset_top = 0.0
    opening_title_panel.offset_bottom = 0.0
    var style := StyleBoxFlat.new()
    style.bg_color = Color(0.025, 0.07, 0.075, 0.92)
    style.border_color = Color(0.57, 0.78, 0.69, 0.85)
    style.set_border_width_all(1)
    style.set_corner_radius_all(8)
    style.content_margin_left = 18.0
    style.content_margin_right = 18.0
    style.content_margin_top = 10.0
    style.content_margin_bottom = 10.0
    opening_title_panel.add_theme_stylebox_override("panel", style)
    opening_title_panel.visible = false
    layer.add_child(opening_title_panel)
    var title := Label.new()
    title.text = "2 ngày sau, tại rừng trúc..."
    title.horizontal_alignment = HORIZONTAL_ALIGNMENT_CENTER
    title.vertical_alignment = VERTICAL_ALIGNMENT_CENTER
    title.add_theme_color_override("font_color", Color(0.96, 0.86, 0.56))
    title.add_theme_font_size_override("font_size", 24)
    opening_title_panel.add_child(title)
