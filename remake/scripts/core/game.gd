# Global flow: 25 Hz original clock (S.w), modes, map transitions, hitstop.
extends Node

signal tick25
var mode := "title"          # title | play | dialogue | menu | shop | map | cutscene
var acc := 0.0
var world = null              # current World node
var paused := false
var stop_t := 0.0

func hitstop(t: float) -> void:
	stop_t = max(stop_t, t)

func _process(delta: float) -> void:
	if paused:
		return
	if stop_t > 0.0:
		stop_t -= delta
		return
	acc += delta
	var n := 0
	while acc >= 0.04 and n < 4:
		acc -= 0.04
		n += 1
		S.w = (S.w + 1) % 10000
		tick25.emit()
	if acc > 0.04:
		acc = 0.0

func goto_map(id: int, spawn: Vector2, facing := 0) -> void:
	if world:
		world.load_map(id, spawn, facing)
