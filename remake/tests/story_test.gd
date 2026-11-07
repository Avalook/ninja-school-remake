# Headless story tests: the remake story module (sim/story_sim.gd + view/story_view.gd) vs the ORIGINAL game as oracle.
# Run: tests/story_run_tests.sh   (or: godot --headless --path . --script res://tests/story_test.gd)
# Oracle logs: tests/story_oracle/expected/*.log, produced by tests/story_oracle/story_oracle.gd running the
# transpiled original (../game) on tests/story_oracle/scripts/*.txt (refresh: story_run_tests.sh --oracle).
#
# Compared: the ordered story events (map enters, trigger groups D(n), sequences C(n), title cards,
# every dialogue box with its exact lines, quest notices) and final flags. Positions are compared
# only where the story itself sets them (stub physics != original physics).
extends SceneTree

const Stubs := preload("res://tests/story_stubs.gd")
const StoryScript := preload("res://view/story_view.gd")
const StateBase := preload("res://scripts/core/state_base.gd")

var st
var ui
var player
var world
var story
var fails := 0
var checks := 0

func _init() -> void:
	test_intro_prologue()
	test_quest0()
	test_ending()
	test_ending_real_path()
	test_kill_counter_and_scroll()
	test_quest11_air_hits()
	test_menus()
	test_npc_matrix()
	print("")
	print("STORY TESTS: %d checks, %d failed" % [checks, fails])
	quit(1 if fails > 0 else 0)

# ------------------------------------------------------------------ environment
func env() -> void:
	if st:
		st.free()
	st = StateBase.new()
	ui = Stubs.StubUI.new()
	world = Stubs.StubWorld.new()
	player = Stubs.StubPlayer.new()
	player.world = world
	world.player = player
	story = StoryScript.new()
	story.st = st
	story.ui = ui
	story.attach(world)

func tick(bot := Callable()) -> void:
	if bot.is_valid():
		bot.call()
	story.tick()
	player.tick()
	ui.tick()

func run_until(cond: Callable, max_ticks: int, bot := Callable()) -> int:
	for k in max_ticks:
		if cond.call():
			return k
		tick(bot)
	return -1

func walk_right_when_free() -> void:
	var free: bool = not story.is_busy()
	player.user_keys["right"] = free
	player.user_keys["left"] = false

func has_event(prefix: String) -> bool:
	for e in story.events:
		if str(e).begins_with(prefix):
			return true
	return false

func check(cond: bool, what: String) -> void:
	checks += 1
	if cond:
		print("  ok   ", what)
	else:
		fails += 1
		print("  FAIL ", what)

func eq(a, b, what: String) -> void:
	check(a == b, "%s: %s == %s" % [what, str(a), str(b)])

# ------------------------------------------------------------------ oracle log handling
func oracle(name: String) -> Dictionary:
	var ev := []
	var fin := {}
	for line in FileAccess.get_file_as_string("res://tests/story_oracle/expected/%s.log" % name).split("\n"):
		if line.begins_with("[final] "):
			for kv in line.substr(8).split(" ", false):
				var p := kv.split("=")
				fin[p[0]] = p[1]
		elif line.begins_with("[story] "):
			var body := line.substr(8)
			body = body.substr(body.find(" ") + 1)      # drop tick
			ev.append(_norm_oracle(body))
	return {"events": ev.filter(func(e): return e != ""), "final": fin}

func _norm_oracle(b: String) -> String:
	var w := b.split(" ")
	match w[0]:
		"warp": return "enter " + w[1]
		"group", "seq": return w[0] + " " + w[1]
		"say": return "say " + b.substr(b.find("lines=") + 6)
		"note": return b
		"title":
			var arr = JSON.parse_string(b.substr(6))
			return "title " + " ".join(PackedStringArray(arr))
	return ""

func remake_events() -> Array:
	var out := []
	for e in story.events:
		var s := str(e)
		var w := s.split(" ")
		match w[0]:
			"enter", "group", "seq": out.append(w[0] + " " + w[1])
			"say", "note", "title": out.append(s)
	return out

func compare_events(name: String, start: String, expected: Array, got: Array) -> void:
	var i0 := expected.find(start)
	var exp := expected.slice(i0) if i0 >= 0 else expected
	var n: int = max(exp.size(), got.size())
	var first_diff := -1
	for k in n:
		var a = exp[k] if k < exp.size() else "<none>"
		var b = got[k] if k < got.size() else "<none>"
		if a != b:
			first_diff = k
			break
	check(first_diff == -1, "%s: %d story events identical to the oracle (from '%s')" % [name, exp.size(), start])
	if first_diff >= 0:
		for k in range(max(0, first_diff - 2), min(n, first_diff + 3)):
			print("       #%d oracle: %s" % [k, (exp[k] if k < exp.size() else "<none>").left(110)])
			print("       #%d remake: %s" % [k, (got[k] if k < got.size() else "<none>").left(110)])

func final_eq(fin: Dictionary, keys: Array, name: String) -> void:
	for k in keys:
		var o = fin.get(k, "?")
		var r = st.get(k)
		var rs := str(r).to_lower() if r is bool else str(int(r))
		eq(rs, str(o).to_lower(), "%s final %s (oracle)" % [name, k])

# ------------------------------------------------------------------ (a) + (b)
func test_intro_prologue() -> void:
	print("[a+b] map 99 intro D(1) -> map 4 prologue D(0) up to the boar trigger")
	env()
	world.load_map(99, Vector2(108, 312))
	eq(Vector2(st.fh, st.fi), Vector2(108, 312), "n(99) places the player")
	check(st.ih and st.ig == 1 and not story.is_cutscene_active(), "n(99) arms group 1 (D(1))")
	tick()
	check(story.is_cutscene_active() and st.fW == 3, "first tick: trigger kind 2 starts sequence 3")
	# 99: seq 3 plays (pans, title, Takanashi's speech); then the player walks off-screen right
	var k := run_until(func(): return world.map_id == 4, 4000, walk_right_when_free)
	check(k > 0, "trigger 4 (fh > fR + q) -> seq 4 -> warp to map 4 (after %d ticks)" % k)
	eq(world.loads, [99, 4], "map loads")
	# map 4: seq 0 at once, seq 1 at fh > 360, seq 2 at fh > 1128 (boar spotted)
	st.gS = 1328
	st.gT = 312
	k = run_until(func(): return has_event("seq_end 2"), 6000, walk_right_when_free)
	check(k > 0, "map 4 sequences 0,1,2 completed")
	var o := oracle("intro_prologue")
	compare_events("intro+prologue", "enter 99", o["events"], remake_events())
	final_eq(o["final"], ["v", "O", "fF", "eL", "eH", "fV", "ig", "ii"], "intro+prologue")
	check(st.eT and st.eS and st.H == 1000 and st.E == 25, "n(4) prologue powers (eT eS H=1000 E=25)")
	eq(st.av[st.ig][st.ii], 5, "next trigger waits for the boar (kind 5, gV == 22)")
	# trigger 5: the enemy module sets gV = 22 -> seq 5 -> warp to map 0 (prologue end, D(2))
	st.gV = 22
	k = run_until(func(): return world.map_id == 0, 600, walk_right_when_free)
	check(k > 0, "boar dead (gV 22) -> seq 5 -> map 0")
	check(has_event("group 2") and not st.eH and st.eL == false and st.M == 1, "n(0) with eH resets the hero and starts D(2)")
	eq(Vector2(st.fh, st.fi), Vector2(864, 336), "n(0) start position")

# ------------------------------------------------------------------ (c)
func test_quest0() -> void:
	print("[c] quest 0: Ayumi (map 0) -> 10 snails -> reward eL")
	env()
	st.eH = true
	world.load_map(0, Vector2(864, 336))
	var k := run_until(func(): return not story.is_busy() and has_event("seq_end 6"), 3000)
	check(k > 0, "D(2) seq 6 (school intro) completed")
	eq([st.ge, st.gm], [0, 2], "V(2) marker after the intro dialogue")
	# talk to Ayumi (NPC 1) like the oracle bot: stand next to her and press talk
	player.place(Vector2(int(st.ly[1]) + 11, int(st.lz[1])), -1)
	tick()
	eq(story.talk_label(), "Giao tiếp", "talk label near Ayumi")
	story.interact()
	run_until(func(): return not story.is_busy(), 400)
	eq(st.O, 0, "quest 0 accepted (z() cb 3)")
	# oracle: walks to map 1, `set fF 10`, walks back to map 0
	world.load_map(1, Vector2(1415, 216))
	st.fF = 10
	world.load_map(0, Vector2(3, 384))
	player.place(Vector2(int(st.ly[1]) + 13, int(st.lz[1])), -1)
	tick()
	story.interact()
	run_until(func(): return not story.is_busy(), 400)
	var o := oracle("q1c")
	compare_events("quest0", "enter 0", o["events"], remake_events())
	final_eq(o["final"], ["v", "O", "fF", "eL", "eM", "eH", "fV"], "quest0")
	eq([st.ge, st.gf, st.gg, st.gm], [0, 1044, 240, 4], "reward marker -> Takanashi (lK/lL/lM/lN)")

# ------------------------------------------------------------------ (d)
func _ending_setup() -> void:
	env()
	st.eH = false
	st.fc = true
	st.H = 500000
	st.G = 500000
	world.load_map(95, Vector2(24, 192))

func test_ending() -> void:
	print("[d] ending: map 95 boss (hu == 22) -> map 94 D(10) -> map 0")
	_ending_setup()
	var k := run_until(func(): return st.hu == 1, 2000)
	check(k > 0, "seq 14 starts the boss fight (opcode 28 hu = 1)")
	run_until(func(): return not story.is_busy(), 400)
	# enemy module stand-in: the boss dies -> hu 22
	st.hu = 22
	k = run_until(func(): return world.map_id == 0, 8000)
	check(k > 0, "trigger 9 -> seq 15 -> map 94 -> seq 18 -> map 0")
	for i in 50:
		tick()
	var o := oracle("end3")
	compare_events("ending", "enter 95", o["events"], remake_events())
	final_eq(o["final"], ["v", "O", "hu", "eK", "gp", "gn", "fe", "fd", "fV", "ig"], "ending")
	# map 94 -> 0 uses the inherited right-exit spawn jx/jy: real play inherits map 18's (24,192)
	# (= map_94.json exits); the oracle script jumped 99 -> 95 directly so it still had 99's (48,120).
	check(has_event("op_warp 0 from=94 fh=24 fi=192"), "warp 94 -> 0 at the real-play spawn (24,192)")

func test_ending_real_path() -> void:
	print("[d2] ending with quest 16 active (real play): m(0) O==16 && fe -> fd, (984,408)")
	_ending_setup()
	st.O = 16
	run_until(func(): return st.hu == 1, 2000)
	run_until(func(): return not story.is_busy(), 400)
	st.hu = 22
	run_until(func(): return world.map_id == 0, 8000)
	check(st.fd and st.O == -1 and st.fx and st.gp == 0 and st.gn, "fd set, quest cleared, girl home")
	eq(Vector2(st.fh, st.fi), Vector2(984, 408), "m(0) final position")
	# afterwards the headmaster thanks with lF[7] (fe)
	var r: Dictionary = story.quests.npc_dialog(0)
	eq(r.get("lines"), Array(st.lF[7]), "headmaster after the ending (fe)")

# ------------------------------------------------------------------ extra rule checks (code-only)
func test_kill_counter_and_scroll() -> void:
	print("[k] L(n) quest counter / quest-item drop / pickup")
	env()
	world.load_map(1, Vector2(600, 216))
	st.O = 0
	st.fF = 0
	for i in 10:
		story.on_enemy_killed(1, Vector2(500, 216))
	story.on_enemy_killed(2, Vector2(500, 216))
	eq(st.fF, 10, "10 snails counted, other types ignored")
	eq(story.notice_text, "ốc sên: 10#10", "kill notice text")
	# quest 2 scroll: type fB-100 = 3, dropped when w % 10 < fC
	st.O = 2
	st.fB = 103
	st.fC = 2
	st.w = 41
	story.on_enemy_killed(3, Vector2(300, 216))
	eq(st.fK, 1, "scroll dropped (w%10=1 < fC=2)")
	player.place(Vector2(300, 216 - 16 + 7 + 0), 1)   # fi - (fk>>1) - (fJ-7) ~ 0
	player.pos = Vector2(300, 216)
	st.fJ = 216 - 16 + 7 + 7      # place scroll at the player's chest height
	tick()
	check(st.fD and st.fK == 2, "scroll picked up -> fD")

func test_quest11_air_hits() -> void:
	print("[u] quest 11 air attacks")
	env()
	st.O = 11
	st.fF = 0
	story.on_player_attack(false)
	story.on_player_attack(true)
	story.on_player_attack(true)
	eq(st.fF, 2, "only airborne attacks count")
	eq(story.notice_text, "Hit: 2", "notice")

func test_menus() -> void:
	print("[m] menus: item use, world map cost/travel, shop rules")
	env()
	world.load_map(0, Vector2(864, 336))
	st.G = 50
	st.H = 200
	st.V[1] = 1
	check(story.items.use_potion(1) and st.G == 200 and st.V[1] == 0, "HP potion: +400 clamped to H, consumed")
	check(not story.items.use_potion(1), "empty slot does nothing")
	st.W[2] = true
	eq(story.items.travel_check(5)["result"], "not_visited", "unvisited location")
	eq(story.items.travel_check(0)["result"], "here", "current location")
	var c: Dictionary = story.items.travel_check(2)
	eq(c.get("cost"), ((50 + 60 + 70) << 1) / 3, "travel cost 2/3 of kQ[0..2]")
	st.dg = 120
	check(not story.items.travel(2), "lr >= dg refused (120 == cost)")
	st.dg = 1000
	check(story.items.travel(2) and world.map_id == 2 and st.dg == 880, "travel pays and warps")
	eq(player.pos, Vector2(int(st.kT[2]) * 24, int(st.kU[2]) * 24), "spawn at kT/kU tile")
	# shop
	st.dg = 1000
	st.V = [99, 0, 0, 0, 0, 0]
	story.items.buy(0)
	eq(st.V[0], 100, "potion bought (99 -> 100: the original refuses only when the sum > 99)")
	story.items.buy(1)
	eq(st.V[1], 0, "HP group already > 99: refused silently")
	eq(story.items.buy(6), "Không cần thiết phải mua vũ khí này vì vũ khí đang sử dụng mạnh hơn!", "own weapon tier 0")
	st.M = 5
	eq(story.items.buy(7), "Bạn phải đạt cấp độ 10 mới có thể mua vũ khí này!", "level gate kq")
	st.M = 10
	st.dg = 12000
	check(story.items.buy(7) == "" and st.x == 1 and st.dg == 0, "weapon tier 1 bought")
	# game menu via UI.choose: 0 = use item -> slot 3 -> back, back
	st.I = 10
	st.J = 150
	st.V[3] = 2
	ui.choices = [0, 3, -1, -1]
	story.game_menu()
	run_until(func(): return not story.is_busy(), 50)
	check(st.I == 110 and st.V[3] == 1, "menu -> use item -> MP potion")

func test_npc_matrix() -> void:
	print("[n] NPC talk switch spot checks")
	env()
	world.load_map(0, Vector2(864, 336))
	# Takanashi (kind 4, index 4) gives quest 1 after eL
	st.eL = true
	st.O = -1
	var r: Dictionary = story.quests.npc_dialog(4)
	eq(r.get("cb"), 5, "Takanashi offers quest 1 (cb 5)")
	story.quests.dialog_end(5)
	eq(st.O, 1, "quest 1 active")
	st.fF = 2
	r = story.quests.npc_dialog(4)
	eq(r.get("cb"), 6, "quest 1 done -> reward cb 6")
	story.quests.dialog_end(6)
	check(st.eN and st.O == -1, "eN (shuriken) learned")
	eq([st.ge, st.gm], [0, 3], "V(3) marker -> NPC 3")
	r = story.quests.npc_dialog(6)
	eq(r.get("shop"), 0, "NPC 6 in map 0 = school shop")
	st.eT = true
	st.eU = false
	st.M = 9
	r = story.quests.npc_dialog(1)
	eq(r.get("lines"), ["Hãy quay lại gặp ta khi con đạt cấp độ 10"], "Ayumi level gate 10")
