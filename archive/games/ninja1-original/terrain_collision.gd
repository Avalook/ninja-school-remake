extends Node2D

@export var grid: Dictionary = {}

const TILE_SIZE: int = 24
const SOLID_IDS: Array[int] = [1, 2, 3, 4, 5, 6, 7, 36, 37, 54, 91, 92, 93, 94, 73, 74, 97, 98, 120]

var solid_tile_count: int = 0

func _ready() -> void:
	var cols: int = int(grid.get("columns", 0))
	var rows: int = int(grid.get("rows", 0))
	var cells_raw: Variant = grid.get("cells", [])
	if cols <= 0 or rows <= 0 or typeof(cells_raw) != TYPE_ARRAY:
		push_error("Malformed grid")
		get_tree().quit(1)
		return
	var cells: Array = cells_raw
	if cells.size() != rows:
		push_error("Row count mismatch")
		get_tree().quit(1)
		return
	var body: StaticBody2D = StaticBody2D.new()
	add_child(body)
	for y in range(rows):
		var row_raw: Variant = cells[y]
		if typeof(row_raw) != TYPE_ARRAY:
			push_error("Row not array")
			get_tree().quit(1)
			return
		var row: Array = row_raw
		if row.size() != cols:
			push_error("Col count mismatch")
			get_tree().quit(1)
			return
		for x in range(cols):
			var id: int = int(row[x])
			if SOLID_IDS.has(id):
				var shape: RectangleShape2D = RectangleShape2D.new()
				shape.size = Vector2(TILE_SIZE, TILE_SIZE)
				var col: CollisionShape2D = CollisionShape2D.new()
				col.shape = shape
				col.position = Vector2((x + 0.5) * TILE_SIZE, (y + 0.5) * TILE_SIZE)
				body.add_child(col)
				solid_tile_count += 1

