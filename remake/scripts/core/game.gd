# Global flow: the 25 Hz clock (emits tick25; the tick itself, S.w included, is sim/sim.gd step()), modes,
# map transitions, hitstop.
extends Node

signal tick25
signal mode_changed(mode: String)   # sim/sim.gd follows the mode through this (main.gd connects it)
var mode := "title":         # title | play | dialogue | menu | shop | map | cutscene
	set(v):
		mode = v
		mode_changed.emit(v)
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
		tick25.emit()
	if acc > 0.04:
		acc = 0.0

func goto_map(id: int, spawn: Vector2, facing := 0) -> void:
	if world:
		world.load_map(id, spawn, facing)
