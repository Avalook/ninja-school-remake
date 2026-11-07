# Cutscene (sim): the original cutscene system, interpreting the ORIGINAL tables at 25 Hz.
#
#   groups    av/aw/ax/ay  (D(n) a.java L4780 starts group n, ad() L6992 evaluates its triggers)
#   sequences az/aA/aB     (C(n) L4660 starts sequence n, V() L5881 interprets it)
#
# aB values are CUMULATIVE ticks from the start of the sequence (gc counts ticks; an opcode runs
# when ++gc >= aB[fX]; blocking opcodes (4 dialogue waits for A==1, 12/14 camera pans) clamp gc
# so the remaining timeline is delayed exactly like in the original).
#
# The native player is driven through player.scripted / player.script_keys (original F(n)/G(n)
# key presses), the camera through the story sim's "camera" event (view: world.camera_override). The original camera
# variables fL/fM (target) and fR/fS (camera top-left, eased 1/4 per tick: run() lbl817 ~L9127)
# are emulated here, because triggers (kind 4: fh > fR + q) and pans (12/14) depend on them.
extends RefCounted

var story            # sim/story_sim.gd (owner): st (state), world, player, emit()

# key code -> script_keys name (original keyPressed codes, see game/core/core.gd header)
const KEY_NAMES := {-1: "jump", -2: "down", -3: "left", -4: "right", -5: "attack",
	48: "weapon", 49: "jump_left", 51: "jump_right", 55: "transform", 57: "stealth"}

var _attack_pulse := false   # original V() clears aU[5]/aV[5] at the end of every tick (L6260)
var _dialog_token := 0

func _init(owner) -> void:
	story = owner

# ---------------------------------------------------------------- D(n) / C(n)
# D(n) a.java L4780: start trigger group n.
func start_group(n: int) -> void:
	var st = story.st
	st.ii = 0
	st.ig = n
	st.ih = true
	story.log_event("group %d map=%d" % [n, st.v])

# C(n) a.java L4660: start sequence n (cutscene mode: fV = true, input only from the script).
func start_sequence(n: int) -> void:
	var st = story.st
	st.gc = 0
	st.fW = n
	st.fX = 0
	st.fV = true
	st.gd = false
	_release_all_keys()            # a.c()/a.d(): clear held/pressed keys
	story.log_event("seq %d map=%d fh=%d fi=%d" % [n, st.v, st.fh, st.fi])
	story.emit({"t": "cutscene_started", "group": st.ig, "seq": n})

func is_active() -> bool:
	return bool(story.st.fV)

# ---------------------------------------------------------------- ad()
# ad() a.java L6992: evaluate the current trigger of the active group (one per tick).
func tick_triggers() -> void:
	var st = story.st
	if not st.ih:
		# L7062: group 10 (the ending) resets to 0 once finished (original: back to the canvas)
		if st.ig == 10:
			st.ig = 0
		return
	var kinds: Array = st.av[st.ig]
	if st.ii >= kinds.size():
		return
	var kind: int = int(kinds[st.ii])
	var seq: int = int(st.aw[st.ig][st.ii])
	var ax: int = int(st.ax[st.ig][st.ii])
	var ay: int = int(st.ay[st.ig][st.ii])
	match kind:
		2:   # immediately
			_fire(seq)
		1:   # player walked past x
			if st.fh > ax:
				_fire(seq)
		4:   # player left the screen on the right (camera fR + view width q)
			if st.fh > st.fR + st.q:
				_fire(seq)
		6:   # player inside [ax-96, ax) on floor ay; NOTE original falls through into case 5
			if st.fh < ax and st.fh > ax - 96 and st.fi == ay:
				_fire(seq)
			if st.gV == 22:
				_fire(int(st.aw[st.ig][st.ii]) if st.ii < st.aw[st.ig].size() else 0)
		5:   # boar (gV) defeated
			if st.gV == 22:
				_fire(seq)
		7:   # map-96 boss ia == 5
			if st.ia == 5:
				_fire(seq)
		8:   # Toniku hp hj < 10
			if st.hj < 10:
				_fire(seq)
		9:   # final boss hu == 22 (dead) -> eK
			if st.hu == 22:
				st.eK = true
				_fire(seq)
		_:   # 3 = end of group: nothing (ih stays true, like the original default: return)
			pass

func _fire(seq: int) -> void:
	start_sequence(seq)
	story.st.ii += 1

# ---------------------------------------------------------------- V()
# V() a.java L5881. Returns true when the tick must stop (map warp / back to title).
func tick_sequence() -> bool:
	var st = story.st
	if _attack_pulse:
		_attack_pulse = false
		_set_key("attack", false)
	if not st.fV:
		return false
	if st.gd:
		return false               # dialogue open: the view closes it (dialog_closed)
	var seq: int = st.fW
	var ops: Array = st.az[seq]
	var args: Array = st.aA[seq]
	var at: Array = st.aB[seq]
	if st.fX >= ops.size():
		st.fV = false
		return false
	st.gc += 1
	if st.gc < int(at[st.fX]):
		return false
	st.gc = int(at[st.fX])
	var op: int = int(ops[st.fX])
	var arg: int = int(args[st.fX])
	match op:
		1:   # F(key): press
			_set_key_code(arg, true)
			st.fX += 1
		2:   # G(key): release
			_set_key_code(arg, false)
			st.fX += 1
		4:   # dialogue aG[arg]; only starts when the player stands (A == 1)
			if not story.player_standing():
				return false
			st.fY = false
			story.emit({"t": "hide_title"})
			var anchor := _speaker_anchor(arg)
			st.fX += 1
			st.gd = true
			_run_dialog(st.aG[arg], anchor)
		5:   # objective marker: map v, x = arg (20000 = "go right"), y = fi - 50
			story.set_marker(st.v, arg, st.fi - 50, -1)
			st.fX += 1
		6:   # clear marker
			st.ge = -1
			st.fX += 1
		3:   # end of sequence
			st.fV = false
			st.fX += 1
			_release_all_keys()
			story.log_event("seq_end %d" % seq)
			story.emit({"t": "cutscene_finished", "seq": seq})
		8:   # unbind camera from the player
			st.fx = false
			st.fX += 1
		9:   # camera jump to aC[arg]
			st.fR = _clamp_x(int(st.aC[arg][0]) - st.bF)
			st.fS = _clamp_y(int(st.aC[arg][1]) - st.bG)
			st.fL = st.fR
			st.fM = st.fS
			story.camera_snap()
			st.fX += 1
		10:  # camera target aC[arg] (eased)
			st.fL = _clamp_x(int(st.aC[arg][0]) - st.bF)
			st.fM = _clamp_y(int(st.aC[arg][1]) - st.bG)
			st.fX += 1
		7:   # bind camera to the player
			st.fx = true
			st.fX += 1
		23:  # camera on classmate dC/dD[arg]
			st.fx = false
			st.fL = _clamp_x(int(st.dC[arg]) - st.bF)
			st.fM = _clamp_y(int(st.dD[arg]) - st.bG)
			st.fX += 1
		27:  # camera on the boss (hs, ht)
			st.fx = false
			st.fL = _clamp_x(st.hs - st.bF)
			st.fM = _clamp_y(st.ht - st.bG)
			st.fX += 1
		31:  # camera on the girl companion (gq, gr)
			st.fx = false
			st.fL = _clamp_x(st.gq - st.bF)
			st.fM = _clamp_y(st.gr - st.bG)
			st.fX += 1
		11:  # camera on the boar (gS, gT)
			st.fL = _clamp_x(st.gS - st.bF)
			st.fM = _clamp_y(st.gT - st.bG)
			st.fX += 1
		12:  # pan down 2 px/tick until below aC[arg].y (blocking)
			st.fS += 2
			st.fM = st.fS
			if st.fS > int(st.aC[arg][1]) - st.bG:
				st.fX += 1
		14:  # pan left 2 px/tick until left of aC[arg].x (blocking)
			st.fR -= 2
			st.fL = st.fR
			if st.fR < int(st.aC[arg][0]) - st.bF:
				st.fX += 1
		15:  # title card aH[arg]
			st.fY = true
			st.fZ = [st.aH[arg]]
			story.show_title_card(st.aH[arg], _title_seconds(seq, st.fX))
			st.fX += 1
		16, 24:  # classmates walk to x = arg (dA/dB, read by the classmate AI)
			st.dA = true
			st.dB = arg
			st.fX += 1
		17:  # warp to map arg at the current map's right-exit spawn (jx, jy)
			st.fs = st.jx
			st.fh = st.jx
			st.ft = st.jy
			st.fi = st.jy
			story.log_event("op_warp %d from=%d fh=%d fi=%d" % [arg, st.v, st.fh, st.fi])
			_release_all_keys()
			story.warp(arg, Vector2(st.jx, st.jy))
			return true
		18:  # V(n): marker "go talk to NPC n" (applied when the next dialogue ends, z())
			story.quests.queue_npc_marker(arg)
			st.fX += 1
		19:  # girl companion starts following
			st.gn = true
			st.fX += 1
		20:  # girl goes home (map 0)
			st.gn = false
			st.gp = 0
			st.gq = 1080
			st.gr = 408
			st.fX += 1
		21:  # quest 3 reward: motorbike licence
			st.O = -1
			st.eP = true
			story.notice("nhận bằng lái môtô")
			story.set_marker(0, int(st.ly[4]), int(st.lz[4]) - 40, 4)
			st.fX += 1
		22:  # river boss dead: open barrier column 24, rows 0..6
			story.clear_barrier(6)
			st.eI = true
			st.fX += 1
		25:
			st.hh = true
			st.fX += 1
		26:  # Toniku beaten: open barrier column 24, rows 0..10
			story.clear_barrier(10)
			st.eJ = true
			st.fX += 1
		28:  # final boss fight starts
			st.hu = 1
			st.fX += 1
		29:  # end of the tutorial sequence: back to the title (K())
			st.fV = false
			_release_all_keys()
			story.log_event("to_title")
			story.emit({"t": "to_title"})
			return true
		30:  # quest 10 done: girl back at school, meet Ayumi
			st.O = -1
			st.eW = false
			st.eX = true
			st.gp = 0
			st.gq = 648
			st.gr = 288
			st.gn = false
			story.notice("gặp cô Ayumi")
			story.set_marker(0, 1032, 368, 4)
			st.fX += 1
		32:  # Sakura rescued: follows the player
			st.gn = true
			st.gu = 2
			st.gs = -5
			st.gC = -1
			st.gN = 8
			st.fX += 1
		33:  # ending: game finished flag
			st.fe = true
			st.gn = true
			st.fX += 1
		_:
			st.fX += 1
	return false

# dialogue of a cutscene (V() case 4 + its end, L5889-5897: M() paging, then iC/gd false, z())
func _run_dialog(lines: Array, anchor: Vector2) -> void:
	_dialog_token += 1
	story.st.iG = -1
	story.st.iC = true
	story.say(lines, anchor, {"kind": "cut", "tok": _dialog_token})

# the view closed the box opened by _run_dialog (stale boxes after abort() are ignored)
func dialog_closed(tok: int) -> void:
	if tok != _dialog_token:
		return
	story.st.iC = false
	story.st.gd = false
	story.quests.dialog_end(-1)     # z() with iG = -1: only applies a pending V(n) marker

# speaker of aG[i]: aE kind 0 player, 1 NPC ly/lz[aD], 2 classmate dC/dD[aD], 3/4 boss/girl gq/gr
func _speaker_anchor(i: int) -> Vector2:
	var st = story.st
	var who: int = int(st.aD[i])
	var p := Vector2(st.fh, st.fi)
	match int(st.aE[i]):
		1:
			p = Vector2(int(st.ly[who]), int(st.lz[who]))
		2:
			p = Vector2(int(st.dC[who]), int(st.dD[who]))
		3, 4:
			p = Vector2(st.gq, st.gr)
	return p - Vector2(0, 40)

# the original title card stays until the next dialogue starts (fY = false in case 4)
func _title_seconds(seq: int, from: int) -> float:
	var st = story.st
	var ops: Array = st.az[seq]
	var at: Array = st.aB[seq]
	for k in range(from + 1, ops.size()):
		if int(ops[k]) in [3, 4, 17, 29]:
			return clampf(float(int(at[k]) - int(at[from])) / 25.0, 2.0, 6.0)
	return 3.0

# ---------------------------------------------------------------- camera (lbl817 ~L9127)
func _clamp_x(x: int) -> int:
	return clampi(x, 0, max(0, story.st.fT))

func _clamp_y(y: int) -> int:
	return clampi(y, 0, max(0, story.st.fU))

# R() ~L4855 (fx: target follows the player) + lbl817 easing (1/4 per tick, 4-bit fraction)
func tick_camera() -> void:
	var st = story.st
	if st.fx:
		st.fL = st.fh - st.bF + st.bI * st.fp
		st.fM = st.fi - st.bH
	if st.fR != st.fL or st.fS != st.fM:
		st.fN = (st.fL - st.fR) << 2
		st.fO = (st.fM - st.fS) << 2
		st.fP &= 15
		st.fQ += st.fO
		st.fS += st.fQ >> 4
		st.fQ &= 15
		st.fP += st.fN
		st.fR += st.fP >> 4
		st.fR = clampi(st.fR, 0, max(0, st.fT))
		st.fS = clampi(st.fS, 0, max(0, st.fU))

func reset_camera() -> void:
	# h() L3185: on map load the camera starts on the player's framing
	var st = story.st
	st.fL = st.fh - st.bF + st.bI * st.fp
	st.fM = st.fi - st.bH
	st.fR = _clamp_x(st.fL)
	st.fS = _clamp_y(st.fM)

# ---------------------------------------------------------------- keys
func _set_key_code(code: int, down: bool) -> void:
	if not KEY_NAMES.has(code):
		return
	var name: String = KEY_NAMES[code]
	_set_key(name, down)
	if name == "attack" and down:
		_attack_pulse = true

func _set_key(name: String, down: bool) -> void:
	var p = story.player
	if p == null:
		return
	if not ("script_keys" in p):
		return
	p.script_keys[name] = down

func _release_all_keys() -> void:
	_attack_pulse = false
	var p = story.player
	if p != null and "script_keys" in p:
		for k in p.script_keys.keys():
			p.script_keys[k] = false

func abort() -> void:
	# used when a new game / title interrupts everything
	_dialog_token += 1
	story.st.fV = false
	story.st.gd = false
	_release_all_keys()
