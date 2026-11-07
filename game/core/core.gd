# Driver for the original NinjaSchool1 logic: boot sequence, input, fixed 25 Hz ticks.
# Original key codes (J2ME): -1 up, -2 down, -3 left, -4 right, -5 fire/OK,
# 48..57 = digits 0..9, 42 = '*', 35 = '#', -6 left soft, -7 right soft.
extends "res://core/orig_run.gd"

const TICK_MS := 40

var booted := false
var auto_prompts := true   # PC: skip sound/quality prompts (tests set false to mirror the original exactly)
var ticks := 0

func boot(w: int = 480, h: int = 270) -> void:
	logical_w = w
	logical_h = h
	_clinit_a()
	_clinit_Midlet()
	# Midlet(): c = this; a.s(); Display.setCurrent(b)
	MF_c = self
	s_()
	booted = true

func _ctor_host() -> void:
	# a(): constructor of the Canvas (licensing/IMEI parts bypassed, see PC_DEVIATIONS)
	aN = self
	aT = false
	aW = logical_w
	aX = logical_h
	aY = aW / 2
	aZ = aX / 2
	lO = false
	lY = 100
	mb = 10
	c_()
	d = 13
	Y = 0
	lT = aY - (_hx_sw(lU, lR[1]) >> 1)

func key_down(k: int) -> void:
	keyPressed_i(k)

func key_up(k: int) -> void:
	keyReleased_i(k)

var prev_cam := Vector2.ZERO
var cur_cam := Vector2.ZERO
var prev_player := Vector2.ZERO
var cur_player := Vector2.ZERO
var prev_gq: Array = []

func tick() -> void:
	frame_ended = false
	# PC: answer the two startup prompts (sound on, high quality) — see PC_DEVIATIONS
	if auto_prompts and d == 10 and not lo:
		aV[12] = true
	prev_cam = cur_cam
	prev_player = cur_player
	prev_gq = _gq
	# The original keeps aW/aX in sync with the canvas each frame in some states.
	run_tick()
	ticks += 1
	vclock += 40
	# the original repaints once per loop iteration: record this frame's draw commands
	_gq_begin()
	paint_o(null)
	cur_cam = Vector2(fR, fS)
	cur_player = Vector2(fh, fi)
	if (cur_cam - prev_cam).length() > 64:
		prev_cam = cur_cam
	if (cur_player - prev_player).length() > 48:
		prev_player = cur_player
	# Licensing screen (d==13, Y==0) is bypassed: behave like choosing "1. Tiếp tục".
	if d == 13 and Y == 0:
		d = 4
		aj_()

# ---- presentation hooks (logic unchanged: record an event, then run the original routine) ----
var fx_events: Array = []

# a.f(x, y, dir): original hit/slash spark spawn
func f_iii(_n, _n2, _n3):
	if fx_events.size() > 64:
		fx_events.clear()
	fx_events.append(["hit", _n, _n2, _n3])
	super(_n, _n2, _n3)
