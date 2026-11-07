# Quests (sim): NPC talk + quest logic of the original, ported 1:1 on the original variables (S.*).
#
#   talk(npc)          run() d==1, "Giao tiếp" switch (a.java L8361-8753, `switch (a.lA[var4_8])`)
#   talk_classmate(i)  run() "Trò chuyện" (L8754)       talk_girl()  "Nói chuyện" (L8758)
#   dialog_end(cb)     z() (L2393): quest accept / reward by callback id iG, then pending marker
#   queue_npc_marker   V(int) (L14570): lK/lL/lM/lN "go talk to NPC n" marker
#   on_enemy_killed    L(n) (L11672) quest part: kill counter fF for quest O, scroll drop fB/fC
#   on_player_attack   u() (L2112) start: quest 11 counts attacks made in the air
#   tick               quest-item (scroll) pickup fK/fI/fJ (L10467), notice timeout cp/cq (L10483),
#                      talk-target selection (R() case 1, L4866)
extends RefCounted

var story

func _init(owner) -> void:
	story = owner

# ---------------------------------------------------------------- talk target (R() case 1)
# Returns {} or {"kind": "npc"|"girl"|"classmate", "index": i, "label": soft-key text}.
# Priority as in the original (the NPC check runs last and overrides the label).
func talk_target() -> Dictionary:
	var st = story.st
	if not story.player_standing():
		return {}
	for n in int(st.lx):
		if _same_floor(int(st.lz[n])) and absi(st.fh - int(st.ly[n])) < 30:
			return {"kind": "npc", "index": n, "label": "Giao tiếp"}
	if st.gp == st.v and st.gu == 1 and _same_floor(st.gr) and absi(st.fh - st.gq) < 30:
		return {"kind": "girl", "index": 0, "label": "Nói chuyện"}
	var n_cm: int = mini(int(st.dx), mini((st.dG as Array).size(), (st.dC as Array).size()))
	for n in n_cm:
		if int(st.dG[n]) == 1 and _same_floor(int(st.dD[n])) and absi(st.fh - int(st.dC[n])) < 30:
			return {"kind": "classmate", "index": n, "label": "Trò chuyện"}
	return {}

func _same_floor(y: int) -> bool:
	# original: fi == lz[n] (integer feet y). The native player moves sub-pixel: allow 2 px.
	return absi(story.st.fi - y) <= 2

# ---------------------------------------------------------------- "Giao tiếp" switch
# Pure-ish port: returns {"lines": Array, "cb": int} or {"shop": kv}; applies the V(n) marker
# side effects exactly where the original calls V(n) before opening the dialogue.
func npc_dialog(i: int) -> Dictionary:
	var st = story.st
	var kind: int = int(st.lA[i])
	match kind:
		0:   # headmaster Okamesama
			if st.fe:
				return _d(st.lF[7], -1)
			if not st.eL:
				queue_npc_marker(2)
				return _d(st.lC[0], -1)
			if st.fc and not st.fd:
				if st.O == 16:
					if st.eK:
						return _d(st.lD[14], 35)
					return _d(st.lE[14], -1)
				if st.O == -1:
					return _d(st.lC[22], 36)
				return _d(st.lG, 1)
			return _d(st.lF[1], -1)
		1:   # Ayumi
			if st.fe:
				return _d(st.lF[5], -1)
			if not st.eL:
				if st.O == 0:
					if st.fF < int(st.lI[0]):
						return _d(st.lE[0], -1)
					# reward marker -> NPC 4 (Takanashi) in map 0 (lK=0 lN=4 lL=1044 lM=240)
					st.lK = 0
					st.lN = 4
					st.lL = 1044
					st.lM = 240
					return _d(st.lD[0], 4)
				if st.O == -1:
					return _d(st.lC[1], 3)
				return _d(st.lG, 1)
			if not st.eU and st.eT:
				if st.M < 10:
					return _d(["Hãy quay lại gặp ta khi con đạt cấp độ 10"], -1)
				if st.O == 7:
					if st.fF < int(st.lI[7]):
						return _d(st.lE[6], -1)
					queue_npc_marker(2)
					return _d(st.lD[6], 19)
				if st.O == -1:
					return _d(st.lC[14], 18)
				return _d(st.lG, 1)
			if st.eX and not st.eY:
				if st.M < 12:
					return _d(["Hãy quay lại gặp ta khi con đạt cấp độ 12"], -1)
				if st.O == 11:
					if st.fF < 20:
						return _d(st.lE[9], -1)
					queue_npc_marker(4)
					return _d(st.lD[9], 27)
				if st.O == -1:
					return _d(st.lC[17], 28)
				return _d(st.lG, 1)
			if not st.fa and st.eZ:
				if st.M < 15:
					return _d(["Hãy quay lại gặp ta khi con đạt cấp độ 15"], -1)
				if st.O == 13:
					if st.fF < int(st.lI[13]):
						return _d(st.lE[11], -1)
					queue_npc_marker(3)
					return _d(st.lD[11], 31)
				if st.O == -1:
					return _d(st.lC[19], 32)
				return _d(st.lG, 1)
			return _d(st.lF[0], -1)
		2:
			if st.fe:
				return _d(st.lF[9], -1)
			if not st.eL:
				queue_npc_marker(1)
				return _d(st.lC[2], -1)
			if st.eO and not st.eP:
				if st.O == 3:
					return _d(st.lC[9], -1)
				if st.O == -1:
					return _d(st.lC[10], 11)
				return _d(st.lG, 1)
			if st.eU and not st.eV:
				if st.O == 8:
					if st.eJ:
						queue_npc_marker(2)
						return _d(st.lD[7], 23)
					return _d(st.lE[7], -1)
				if st.O == -1:
					return _d(st.lC[15], 22)
				return _d(st.lG, 1)
			if st.eV and not st.eW:
				if st.O == 9:
					if st.fF < int(st.lI[9]):
						return _d(st.lE[8], -1)
					queue_npc_marker(3)
					return _d(st.lD[8], 25)
				if st.O == -1:
					return _d(st.lC[16], 24)
				return _d(st.lG, 1)
			return _d(st.lF[3], -1)
		3:
			if st.fe:
				return _d(st.lF[9], -1)
			if not st.eL or not st.eN:
				return _d(st.lC[5], -1)
			if not st.eO:
				if st.O == 2:
					if not st.fD:
						return _d(st.lE[2], -1)
					queue_npc_marker(2)
					return _d(st.lD[2], 10)
				if st.O == -1:
					return _d(st.lC[8], 9)
				return _d(st.lG, 1)
			if not st.eR and st.eQ:
				if st.O == 5:
					if not st.fD:
						return _d(st.lE[4], -1)
					queue_npc_marker(4)
					return _d(st.lD[4], 15)
				if st.O == -1:
					return _d(st.lC[12], 14)
				return _d(st.lG, 1)
			if not st.fb and st.fa:
				if st.M < 16:
					return _d(["Hãy quay lại gặp ta khi con đạt cấp độ 16"], -1)
				if st.O == 14:
					if st.fF < int(st.lI[14]):
						return _d(st.lE[12], -1)
					queue_npc_marker(3)
					return _d(st.lD[12], 33)
				if st.O == -1:
					return _d(st.lC[20], 34)
				return _d(st.lG, 1)
			if not st.fc and st.fb:
				if st.O == 15:
					if not st.fD:
						return _d(st.lE[13], -1)
					queue_npc_marker(0)
					return _d(st.lD[13], 21)
				if st.O == -1:
					return _d(st.lC[21], 20)
				return _d(st.lG, 1)
			return _d(st.lF[4], -1)
		4:   # Takanashi (homeroom teacher)
			if st.fe:
				return _d(st.lF[8], -1)
			if st.v == 99:
				return _d(st.lC[7], -1)
			if not st.eL:
				return _d(st.lC[4], -1)
			if not st.eN:
				if st.O == 1:
					if st.fF < int(st.lI[1]):
						return _d(st.lE[3], -1)
					queue_npc_marker(3)
					return _d(st.lD[1], 6)
				if st.O == -1:
					return _d(st.lC[6], 5)
				return _d(st.lG, 1)
			if st.eP and not st.eQ:
				if st.O == 4:
					if st.fF < int(st.lI[4]):
						return _d(st.lE[3], -1)
					queue_npc_marker(3)
					return _d(st.lD[3], 12)
				if st.O == -1:
					return _d(st.lC[11], 13)
				return _d(st.lG, 1)
			if st.eR and not st.eT:
				if st.M < 8:
					return _d(["Hãy quay lại gặp ta khi con đạt cấp độ 8"], -1)
				if st.O == 6:
					if not st.fD:
						return _d(st.lE[5], -1)
					queue_npc_marker(1)
					return _d(st.lD[5], 17)
				if st.O == -1:
					return _d(st.lC[13], 16)
				return _d(st.lG, 1)
			if not st.eZ and st.eY:
				if st.O == 12:
					if st.fF < int(st.lI[12]):
						return _d(st.lE[10], -1)
					queue_npc_marker(1)
					return _d(st.lD[10], 29)
				if st.O == -1:
					return _d(st.lC[18], 30)
				return _d(st.lG, 1)
			return _d(st.lF[2], -1)
		5:   # helpers: shopkeepers / wardens (depends on map v and index)
			if st.v == 0:
				if i == 5:
					return _d(st.lB[0], -1)
				if i == 6:
					return {"shop": 0}
			if st.v == 2 and i == 0:
				return {"shop": 1}
			if st.v == 1 and i == 0:
				return _d(st.lB[1], -1)
			if st.v == 5 and i == 0:
				return _d(st.lB[2], -1)
			if st.v == 6 and i == 0:
				return _d(st.lB[3], -1)
	return {}

func _d(lines, cb: int) -> Dictionary:
	return {"lines": Array(lines), "cb": cb}

# talk to NPC i (the player pressed "Giao tiếp" next to it): opens the dialogue, or returns
# {"shop": kv} and the view opens the shop menu (ap())
func talk(i: int) -> Dictionary:
	var st = story.st
	if i < 0 or i >= int(st.lx):
		return {}
	story.log_event("talk npc=%d kind=%d map=%d O=%d" % [i, int(st.lA[i]), st.v, st.O])
	var r := npc_dialog(i)
	# L8751: talking to the marked NPC clears the objective marker
	if st.v == st.ge and i == st.gm:
		st.ge = -1
	if r.has("shop"):
		st.kv = int(r["shop"])
		return {"shop": st.kv}
	if r.has("lines"):
		story.dialog(r["lines"], int(r["cb"]), Vector2(int(st.ly[i]), int(st.lz[i]) - 40))
	return {}

# "Trò chuyện" (L8754): classmate faces the player and says its dy lines
func talk_classmate(i: int) -> void:
	var st = story.st
	st.dM[i] = -st.fp
	story.dialog(st.dy[i], 0, Vector2(int(st.dC[i]), int(st.dD[i]) - 44))

# "Nói chuyện" (L8758): the girl companion (gq, gr)
func talk_girl() -> void:
	var st = story.st
	var at := Vector2(st.gq, st.gr - 44)
	if st.gn:
		story.dialog(st.aI[0], 0, at)
	elif st.eX:
		st.gn = true
		story.dialog(st.aI[2], 0, at)
	elif st.eW:
		st.eW = false
		story.dialog(st.aI[3], 26, at)
	else:
		story.dialog(st.aI[1], 0, at)

# ---------------------------------------------------------------- z()
func dialog_end(cb: int) -> void:
	var st = story.st
	match cb:
		3:
			st.O = 0
			st.fF = 0
			story.notice("NV: Giết 10 ốc sên")
		4:
			st.eL = true
			st.eM = true
			st.O = -1
			st.fF = 0
			story.notice("đã học phép khinh công")
		8:
			story.notice("")
		5:
			st.O = 1
			st.fF = 0
			story.notice("NV: đánh ngã 2 bù nhìn")
		6:
			st.eN = true
			st.O = -1
			st.fF = 0
			story.notice("đã học ném phi tiêu")
		9:
			st.O = 2
			st.fB = 103
			st.fC = 2
			st.fD = false
			story.notice("NV: Tìm bí kíp")
		10:
			st.eO = true
			st.O = -1
			st.fB = 0
			st.fC = 0
			st.fD = false
			story.notice("đã học thuật độn thổ")
		11:
			st.O = 3
			st.gp = 3
			st.gq = 2808
			st.gr = 216
			st.gC = -1
			st.gu = 1
			st.gs = 5
		13:
			st.O = 4
			st.fF = 0
			story.notice("NV: giết 20 con ong")
		12:
			st.eQ = true
			st.E = st.E << 1
			st.O = -1
			st.fB = 0
			st.fC = 0
			st.fD = false
			story.notice("sức công nhân đôi!")
		14:
			st.O = 5
			st.fD = false
			st.fF = 0
			st.fC = 10
			story.notice("NV: giết heo rừng")
		15:
			st.O = -1
			st.eR = true
			st.eS = true
			story.notice("đã học chạy nước.")
		16:
			st.O = 6
			st.fD = false
			st.fC = 10
			story.notice("NV: vượt sông")
		17:
			st.O = -1
			st.eT = true
			story.notice("đã học biến hình.")
		18:
			st.O = 7
			st.fF = 0
			story.notice("NV: giết 15 dơi vàng")
		19:
			st.eU = true
			st.dg += 5000
			st.O = -1
			st.fB = 0
			st.fC = 0
			st.fD = false
			story.notice("Nhận được tiền 5000$")
		22:
			st.O = 8
			story.notice("NV: tìm đánh Toniku")
		23:
			st.O = -1
			st.eV = true
			story.notice("Hoàn thành nhiệm vụ.")
		24:
			st.O = 9
			st.fF = 0
			story.notice("NV: giết 20 thổ dân")
		25:
			st.eW = true
			st.O = -1
			st.fB = 0
			st.fC = 0
			st.fD = false
			story.notice("đến gặp cô bé!")
		26:
			st.O = 10
			st.fF = 0
			st.gn = true
			story.notice("NV: dẫn cô bé đi chơi")
		28:
			st.O = 11
			st.fF = 0
			story.notice("NV: 20 hit trên không")
		27:
			st.O = -1
			st.eY = true
			st.fF = 0
			story.notice("Học khinh công cấp 2")
		30:
			st.O = 12
			st.fF = 0
			story.notice("NV: đánh 70 quỷ hoa đỏ")
		29:
			st.eZ = true
			st.F = st.F << 2
			st.O = -1
			st.fF = 0
			story.notice("đã học phi tiêu cấp 2")
		32:
			st.O = 13
			st.fF = 0
			story.notice("NV: Giết 50 bò cạp")
		31:
			st.fa = true
			st.O = -1
			st.fF = 0
			story.notice("đã có thể chạy nhanh")
		34:
			st.O = 14
			st.fF = 0
			story.notice("NV: Giết 30 cá đỏ")
		33:
			st.fb = true
			st.O = -1
			st.fF = 0
			story.notice("đã có thể đứng nước")
		20:
			st.O = 15
			st.fF = 0
			st.fD = false
			story.notice("NV: tìm bí kíp")
		21:
			st.fc = true
			st.O = -1
			st.fF = 0
			st.fD = false
			story.notice("đã có thể ẩn thân")
		36:
			st.O = 16
			st.fF = 0
			st.fD = false
			story.notice("NV: cứu Sakura")
		35:
			st.O = -1
			st.fF = 0
			st.fD = false
	# pending marker from V(n)
	if st.lK >= 0:
		story.set_marker(st.lK, st.lL, st.lM, st.lN)
		st.lK = -1
	story.state_changed()

# V(int) L14570
func queue_npc_marker(n: int) -> void:
	var st = story.st
	st.lK = st.v
	st.lN = n
	st.lL = int(st.ly[n])
	st.lM = int(st.lz[n]) - 44

# ---------------------------------------------------------------- kills / attacks
# L(n) quest part (EXP, coins and potions of L(n) belong to the enemy module).
func on_enemy_killed(type: int, pos: Vector2) -> void:
	var st = story.st
	if st.O != -1 and int(st.lH[st.O]) == type:
		st.fF += 1
		story.notice("%s: %d#%d" % [st.am[int(st.lH[st.O])], st.fF, int(st.lI[st.O])])
	if type == st.fB - 100 and st.w % 10 < st.fC:
		drop_scroll(Vector2(int(pos.x), int(pos.y)))
	story.state_changed()

# u() L2112 (start): quest 11 counts every attack made while airborne (z != 0).
# (Original quirk: the "20 hit" message checks O >= 20 which is never true -> always "Hit: n".)
func on_player_attack(airborne: bool) -> void:
	var st = story.st
	if st.O == 11 and airborne:
		st.fF += 1
		story.notice("Hit: %d" % st.fF)
		story.state_changed()

# e(x, y) L4654: quest scroll appears
func drop_scroll(p: Vector2) -> void:
	var st = story.st
	st.fI = int(p.x)
	st.fJ = int(p.y)
	st.fK = 1
	story.emit({"t": "scroll", "pos": p})

# ---------------------------------------------------------------- per tick
func tick() -> void:
	var st = story.st
	# scroll pickup (L10467)
	if st.fK != 0:
		if st.fK == 1:
			if absi(st.fi - (st.fk >> 1) - (st.fJ - 7)) < 10 and absi(st.fh - st.fI) < 10:
				st.fK = 2
				st.fD = true
				story.notice("đã lấy được bí kíp")
				story.state_changed()
		else:
			st.fJ -= 3
			st.fK += 1
			if st.fK > 40:
				st.fK = 0
	# notice timeout (L10483)
	if st.cp:
		st.cq += 1
		if st.cq >= 150:
			st.cp = false
