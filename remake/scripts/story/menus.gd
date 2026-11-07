# Menu logic of the original (drawing is the UI's job; built on UI.choose / UI.popup):
#   in-game menu   as()  a.java L14282 (d=5): kO = use item / world map / save & quit / music
#   use item       aq()  L14174 (d=8): 6 potion slots V[0..5], heal kJ, HP slots 0-2, MP 3-5
#   world map      run() d=6 L10647 + au() L14447 + s(n) popups L14476 + d=7 L10753 (travel cost)
#   shop           ap()  L14009 (d=9), opened by NPC kind 5 (kv 0 = school shop, 1 = village shop)
#   title          run() d=3 L10491: aF = new game / continue / tutorial / more games / quit
extends RefCounted

var story
var music_on := true

func _init(owner) -> void:
	story = owner

func _choose(title: String, options: Array) -> int:
	if story.ui == null:
		return -1
	var opts: Array[String] = []
	for o in options:
		opts.append(str(o))
	return int(await story.ui.choose(title, opts))

func _popup(text: String) -> void:
	if story.ui != null:
		await story.ui.popup(text)

# ---------------------------------------------------------------- in-game menu (as())
func game_menu() -> void:
	var st = story.st
	story.set_mode("menu")
	while true:
		var labels: Array = (st.kO as Array).duplicate()
		labels[3] = "Nhạc: Bật" if music_on else "Nhạc: Tắt"
		var k := await _choose("Menu", labels)
		if k < 0:
			break
		if k == 0:
			await use_item_menu()
		elif k == 1:
			var travelled := await world_map()
			if travelled:
				return
		elif k == 2:
			# kM == 2: only while standing (A == 1); original then saves (a.a()) and exits the app
			if not story.player_standing():
				await _popup("Bạn chỉ có thể lưu và thoát khi nhân vật đang đứng yên.")
				break
			story.save_game()
			story.set_mode("title")
			story.quit_to_title.emit()
			return
		elif k == 3:
			# original: label only (no handler for kM == 3); remake toggles the music
			music_on = not music_on
			story.music_toggled.emit(music_on)
	story.set_mode("play")

# ---------------------------------------------------------------- use item (aq())
func potion_labels() -> Array:
	var st = story.st
	var out := []
	for n in 6:
		out.append("%s  x%d" % [st.kx[0][n], int(st.V[n])])
	return out

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

func use_item_menu() -> void:
	while true:
		var k := await _choose("Sử dụng item", potion_labels())
		if k < 0:
			return
		use_potion(k)

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

func world_map() -> bool:
	var st = story.st
	story.set_mode("map")
	while true:
		var ll := current_location()
		var labels := []
		for n in 19:
			var s: String = st.kP[n]
			if n == ll:
				s += "  (đang ở đây)"
			elif not bool(st.W[n]):
				s += "  (chưa đến)"
			labels.append(s)
		var k := await _choose("Bản đồ", labels)
		if k < 0:
			story.set_mode("menu")
			return false
		var c := travel_check(k)
		match c["result"]:
			"not_visited":
				await _popup("Không thể chuyển. Vì bạn chưa từng đi bộ đến đây!")
			"here":
				await _popup("Không cần phải chuyển vì bạn đang ở vị trí này!")
			"confirm":
				var yes := await _choose("Chuyển đến đây phải trả %d$. Bạn có %d$. Chuyển?" % [c["cost"], st.dg], ["Chuyển", "Không"])
				if yes == 0:
					if travel(k):
						return true
					await _popup("Bạn không có đủ tiền!")
	return false

# ---------------------------------------------------------------- shop (ap())
func shop_labels(kv: int) -> Array:
	var st = story.st
	var out := []
	for n in 6:
		out.append("%s - %d$  (có %d)" % [st.kx[0][n], int(st.kz[0][n]), int(st.V[n])])
	for n in 4:
		var tag := ""
		if st.x == n:
			tag = "  (đang dùng)"
		elif st.M < int(st.kq[n]):
			tag = "  (cấp %d)" % int(st.kq[n])
		out.append("%s - %d$%s" % [st.kx[1][n], int(st.kz[1][n]), tag])
	return out

# Buy option k of shop_labels (0-5 potions = ku 0, 6-9 weapons = ku 1).
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
		story.weapon_changed.emit(w)      # original t(): reload weapon images
		story.state_changed()
		return ""
	return "Không có đủ tiền: %d$. Bạn chỉ có: %d$. Hãy đi tìm thêm." % [int(st.kz[1][w]), st.dg]

# kv 1 (village shop) sold the same items through premium SMS in the original; the remake sells
# them for in-game money with the kv 0 rules (documented deviation).
func shop(kv: int) -> void:
	var st = story.st
	story.set_mode("shop")
	while true:
		var k := await _choose("Tiền: %d$" % st.dg, shop_labels(kv))
		if k < 0:
			break
		var msg := buy(k)
		if msg != "":
			await _popup(msg)
	story.set_mode("play")

# ---------------------------------------------------------------- title (run() d=3)
# Returns the chosen action: "new" | "continue" | "quit" (tutorial / more games loop back).
func title_menu() -> String:
	var st = story.st
	story.set_mode("title")
	while true:
		var k := await _choose("", st.aF)
		match k:
			0:
				story.new_game()
				return "new"
			1:
				if story.continue_game():
					return "continue"
			2:
				await story.play_tutorial()
			3:
				pass      # "Game khác": original opened a WAP link; nothing on PC
			4:
				story.get_tree().quit()
				return "quit"
	return ""
