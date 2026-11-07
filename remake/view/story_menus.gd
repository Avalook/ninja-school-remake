# Story menus (view): the original menus as UI.choose / UI.popup loops. The rules they apply are in
# sim/story/items.gd (potions, world-map travel, shop).
#   in-game menu   as()  a.java L14282 (d=5): kO = use item / world map / save & quit / music
#   use item       aq()  L14174 (d=8)
#   world map      run() d=6 L10647 + au() L14447 + s(n) popups L14476 + d=7 L10753 (travel cost)
#   shop           ap()  L14009 (d=9), opened by NPC kind 5 (kv 0 = school shop, 1 = village shop)
#   title          run() d=3 L10491: aF = new game / continue / tutorial / more games / quit
extends RefCounted

var story            # view/story_view.gd (owner): st, ui, sim, items
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

# original s(n) popups (d = 7) are modal: UI.message when the UI has it, else UI.popup
func _popup(text: String) -> void:
	if story.ui == null:
		return
	if story.ui.has_method("message"):
		await story.ui.message(text)
	else:
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
			if not story.sim.player_standing():
				await _popup("Bạn chỉ có thể lưu và thoát khi nhân vật đang đứng yên.")
				break
			story.sim.save_game()
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

func use_item_menu() -> void:
	while true:
		var k := await _choose("Sử dụng item", potion_labels())
		if k < 0:
			return
		story.items.use_potion(k)

# ---------------------------------------------------------------- world map
func world_map() -> bool:
	var st = story.st
	var items = story.items
	story.set_mode("map")
	while true:
		var ll: int = items.current_location()
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
		var c: Dictionary = items.travel_check(k)
		match c["result"]:
			"not_visited":
				await _popup("Không thể chuyển. Vì bạn chưa từng đi bộ đến đây!")
			"here":
				await _popup("Không cần phải chuyển vì bạn đang ở vị trí này!")
			"confirm":
				var yes := await _choose("Chuyển đến đây phải trả %d$. Bạn có %d$. Chuyển?" % [c["cost"], st.dg], ["Chuyển", "Không"])
				if yes == 0:
					if items.travel(k):
						return true
					await _popup("Bạn không có đủ tiền!")
	return false

# ---------------------------------------------------------------- shop (ap())
func shop_labels(_kv: int) -> Array:
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

# kv 1 (village shop) sold the same items through premium SMS in the original; the remake sells
# them for in-game money with the kv 0 rules (documented deviation).
func shop(kv: int) -> void:
	var st = story.st
	story.set_mode("shop")
	while true:
		var k := await _choose("Tiền: %d$" % st.dg, shop_labels(kv))
		if k < 0:
			break
		var msg: String = story.items.buy(k)
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
