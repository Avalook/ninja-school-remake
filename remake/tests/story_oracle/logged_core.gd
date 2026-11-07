# Oracle instrumentation (run under the ORIGINAL port project ../game, never shipped):
# the transpiled original with story events logged, so remake story tests can compare against it.
extends "res://core/core.gd"

var story_log: Array = []

func _log(s: String) -> void:
	var line := "%d %s" % [ticks, s]
	story_log.append(line)
	print("[story] ", line)

func a_Siii(_stringArray, _n, _n2, _n3):
	_log("say cb=%d lines=%s" % [_n, JSON.stringify(_stringArray)])
	super(_stringArray, _n, _n2, _n3)

func c_s(_string):
	_log("note %s" % [str(_string)])
	super(_string)

func C_i(_n):
	_log("seq %d map=%d fh=%d fi=%d" % [_n, v, fh, fi])
	super(_n)

func D_i(_n):
	_log("group %d map=%d" % [_n, v])
	super(_n)

func x_i(_n):
	_log("warp %d from=%d fh=%d fi=%d" % [_n, v, fh, fi])
	super(_n)

func z_():
	_log("dialog_end cb=%d" % [iG])
	super()
