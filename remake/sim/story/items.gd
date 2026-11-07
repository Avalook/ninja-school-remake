# Items (sim): the rules behind the original menus (the menus themselves are view/story_menus.gd):
#   use item       aq()  a.java L14174 (d=8): 6 potion slots V[0..5], heal kJ, HP slots 0-2, MP 3-5
#   hot keys * / # run() d==1 aV[10]/aV[11] L8315
#   world map      as() kM == 1 L14334 (location ll), s(2) L14491 (cost), d=7 L10753 (travel)
#   shop           ap()  L14009 (d=9): potions ku 0, weapons ku 1
extends RefCounted

var story            # sim/story_sim.gd (owner)

func _init(owner) -> void:
	story = owner

# one selection of aq(): consumes the potion even when HP/MP is already full (original)
func use_potion(slot: int) -> bool:
	var st = story.st
	if int(st.V[slot]) <= 0:
		return false
	st.V[slot] = int(st.V[slot]) - 1
	if slot <= 2:
		if st.G < st.H:
			st.G += int(st.kJ[slot])
		if st.G > st.H:
			st.G = st.H
	else:
		if st.I < st.J:
			st.I += int(st.kJ[slot])
		if st.I > st.J:
			st.I = st.J
	story.state_changed()
	return true

# hot keys * / # (run() d==1 aV[10]/aV[11] L8315): first non-empty slot, only when not full
func quick_potion(hp: bool) -> bool:
	var st = story.st
	if hp:
		if st.G >= st.H:
			return false
		for n in range(0, 3):
			if int(st.V[n]) > 0:
				st.V[n] = int(st.V[n]) - 1
				st.G = mini(st.G + int(st.kJ[n]), st.H)
				story.state_changed()
				return true
	else:
		if st.I >= st.J:
			return false
		for n in range(3, 6):
			if int(st.V[n]) > 0:
				st.V[n] = int(st.V[n]) - 1
				st.I = mini(st.I + int(st.kJ[n]), st.J)
				story.state_changed()
				return true
	return false

# ---------------------------------------------------------------- world map
# current location ll (as() kM == 1, L14334)
func current_location() -> int:
	var v: int = story.st.v
	if v == 99:
		return 0
	if v == 97:
		return 7
	if v == 96:
		return 11
	if v == 95:
		return 18
	return v

# s(2) L14491: cost = 2/3 of the kQ sum between the two locations (inclusive)
func travel_cost(from: int, to: int) -> int:
	var st = story.st
	var s := 0
	for n in range(mini(from, to), maxi(from, to) + 1):
		s += int(st.kQ[n])
	return (s << 1) / 3

# Result of selecting location kZ: "not_visited" | "here" | "confirm" (with cost)
func travel_check(to: int) -> Dictionary:
	var st = story.st
	if not bool(st.W[to]):
		return {"result": "not_visited"}
	var ll := current_location()
	if ll == to:
		return {"result": "here"}
	return {"result": "confirm", "cost": travel_cost(ll, to)}

# d=7 lq==2 confirm: pays and warps to (kT, kU) tiles of the location
func travel(to: int) -> bool:
	var st = story.st
	var cost := travel_cost(current_location(), to)
	if cost >= st.dg:          # original: lr >= dg -> "not enough money" (even when equal)
		return false
	st.dg -= cost
	story.state_changed()
	story.set_mode("play")
	story.warp(to, Vector2(int(st.kT[to]) * 24, int(st.kU[to]) * 24))
	return true

# ---------------------------------------------------------------- shop (ap())
# Buy option k of the shop list (0-5 potions = ku 0, 6-9 weapons = ku 1).
# Returns "" (bought or silently refused, like the original) or a popup message.
func buy(k: int) -> String:
	var st = story.st
	if k < 6:
		var ks := k
		var total: int = int(st.V[0]) + int(st.V[1]) + int(st.V[2]) if ks < 3 else int(st.V[3]) + int(st.V[4]) + int(st.V[5])
		if total > 99:
			return ""
		if st.dg >= int(st.kz[0][ks]):
			st.V[ks] = int(st.V[ks]) + 1
			st.dg -= int(st.kz[0][ks])
			story.state_changed()
		return ""
	var w := k - 6
	if st.x >= w:
		return "Không cần thiết phải mua vũ khí này vì vũ khí đang sử dụng mạnh hơn!"
	if st.M < int(st.kq[w]):
		return "Bạn phải đạt cấp độ %d mới có thể mua vũ khí này!" % int(st.kq[w])
	if st.dg >= int(st.kz[1][w]):
		st.dg -= int(st.kz[1][w])
		st.x = w
		story.emit({"t": "weapon", "tier": w})      # original t(): reload weapon images
		story.state_changed()
		return ""
	return "Không có đủ tiền: %d$. Bạn chỉ có: %d$. Hãy đi tìm thêm." % [int(st.kz[1][w]), st.dg]
