# Minimal stand-ins for the lead's World / Player / UI so the story module runs headless
# (tests only). They implement just the contract surface of archive/docs/REMAKE_PLAN.md that story.gd uses.
extends RefCounted

# ------------------------------------------------------------------ UI
class StubUI:
	extends RefCounted
	signal _tick
	var said: Array = []          # every say() call: lines
	var titles: Array = []
	var popups: Array = []
	var notices: Array = []
	var choices: Array = []       # scripted answers for choose(): ints
	var asked: Array = []
	var ticks_per_line := 6       # the "player" confirms each line after N ticks

	func tick() -> void:
		_tick.emit()

	func say(lines: Array[String], _pos: Vector2) -> void:
		said.append(lines)
		for l in lines:
			for k in ticks_per_line:
				await _tick

	func title_card(text: String, _seconds: float) -> void:
		titles.append(text)

	func popup(text) -> void:
		popups.append(str(text))
		await _tick

	func notice(text: String) -> void:
		notices.append(text)

	func choose(title: String, options: Array[String]) -> int:
		asked.append([title, options])
		await _tick
		return int(choices.pop_front()) if not choices.is_empty() else -1

# ------------------------------------------------------------------ Player
# 25 Hz kinematics only: walk 5 px/tick, gravity onto flag-2 floors of the map, simple jump.
class StubPlayer:
	extends RefCounted
	var pos := Vector2.ZERO
	var facing := -1
	var state := "idle"
	var scripted := false
	var script_keys := {"left": false, "right": false, "jump": false, "attack": false, "down": false}
	var user_keys := {"left": false, "right": false, "jump": false, "attack": false, "down": false}
	var vy := 0.0
	var world
	var attacks := 0
	var _prev_attack := false

	func place(p: Vector2, f: int) -> void:
		pos = p
		if f != 0:
			facing = f
		vy = 0.0

	func is_standing() -> bool:
		return state == "idle"

	func _k(name: String) -> bool:
		var keys: Dictionary = script_keys if scripted else user_keys
		return bool(keys.get(name, false))

	func tick() -> void:
		var dx := 0
		if _k("right"):
			dx = 5
			facing = 1
		elif _k("left"):
			dx = -5
			facing = -1
		var a := _k("attack")
		if a and not _prev_attack:
			attacks += 1
		_prev_attack = a
		var was_grounded := _on_floor()
		pos.x += dx
		if was_grounded and dx != 0 and not _on_floor() and world:
			# walk up / down one-tile steps instead of simulating walls
			var row := int(pos.y / 24.0)
			for r in [row - 1, row + 1]:
				if (int(world.flag_tile(int(pos.x / 24.0), r)) & 2) == 2:
					pos.y = r * 24
					break
		var grounded := _on_floor()
		if grounded and _k("jump"):
			vy = -10.0
			grounded = false
		if not grounded:
			var ny := pos.y + vy
			vy = minf(vy + 1.0, 12.0)
			# land on a standable tile top crossed this tick
			if vy > 0:
				var row_from := int(floor(pos.y / 24.0))
				var row_to := int(floor(ny / 24.0))
				for row in range(row_from, row_to + 1):
					var top := float(row * 24)
					if top > pos.y and top <= ny and world and (int(world.flag_tile(int(pos.x / 24.0), row)) & 2) == 2:
						ny = top
						vy = 0.0
						break
			pos.y = ny
			if world and pos.y > world.h * 24:
				pos.y = world.h * 24
		state = "idle" if (_on_floor() and dx == 0) else ("run" if _on_floor() else "fall")

	func _on_floor() -> bool:
		if world == null or pos.y >= world.h * 24:
			return true
		if int(pos.y) % 24 != 0:
			return false
		return (int(world.flag_tile(int(pos.x / 24.0), int(pos.y / 24.0))) & 2) == 2

# ------------------------------------------------------------------ World
class StubWorld:
	extends RefCounted
	signal map_loaded(id: int)
	var map_id := -1
	var map := {}
	var w := 0
	var h := 0
	var flags: Array = []
	var player
	var cam_override := false
	var cam_target := Vector2.ZERO
	var loads: Array = []

	func load_map(id: int, spawn: Vector2, facing := 0) -> void:
		map = JSON.parse_string(FileAccess.get_file_as_string("res://data/maps/map_%d.json" % id))
		map_id = id
		w = int(map["w"])
		h = int(map["h"])
		flags = (map["flags"] as Array).duplicate()
		loads.append(id)
		if player:
			player.place(spawn, facing)
		map_loaded.emit(id)

	func flag_tile(tx: int, ty: int) -> int:
		if tx < 0 or ty < 0 or tx >= w or ty >= h:
			return 0
		return int(flags[ty * w + tx])

	func camera_override(target: Vector2, active: bool) -> void:
		cam_override = active
		cam_target = target
