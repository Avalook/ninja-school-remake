# Headless check of sim/sim.gd snapshot() / restore() (run: tests/sim_snapshot.sh).
# No view: the harness plays the view's part with a fixed display rate of 60 fps (the clock of Game: one
# sim.step() every 0.04 s, sim.frame(1/60) every frame), seeded random keys, a simple follow camera, and
# dialogue boxes closed 10 ticks after they open.
# Per scenario:
#   A  fresh state, run M ticks writing one line per tick (hash of the full snapshot + a few readable fields);
#      every EVERY ticks from N on, keep snapshot() as JSON text (many points, so that most variables are away
#      from their start value at some point: a variable missing from the snapshot then shows up)
#   B  per point: fresh state AND fresh sim objects, restore from the parsed JSON, replay REPLAY ticks with the
#      same input -> the lines must be identical to A
#   C  per point: snapshot right after the restore, as JSON text, must equal the saved text (round trip)
# --skip=<root>.<name> drops one variable from the snapshot (sensitivity check: B must then fail; C fails
# trivially).
extends SceneTree

const DT := 1.0 / 60.0
const VIEW := Vector2(480, 270)
const SKILLS := "eL=true;eM=true;eY=true;eT=true;eN=true;fc=true;eO=true;eS=true;eR=true;eP=true;I=300;J=300;G=3000;H=3000"
const KEYS := ["left", "right", "jump", "down", "attack", "jump_left", "jump_right", "weapon", "stealth",
	"transform", "hp", "mp"]

# name | map,x,y (or "new" = story new game) | --set | key seed | keys used
const SCEN := [
	["m4_boar", "4,1250,312", SKILLS, 1, ["left", "right", "attack", "attack", "weapon", "jump"]],
	["m6_moves", "6,5976,240", SKILLS, 2, KEYS],
	["m9_classmates", "9,230,288", SKILLS, 3, ["left", "right", "right", "jump", "attack", "attack", "weapon"]],
	["m95_golem", "95,150,264", SKILLS, 4, ["left", "right", "attack", "attack", "weapon", "jump"]],
	["m1_blocks", "1,280,216", SKILLS, 5, ["left", "right", "right", "jump", "attack"]],
	["story_intro", "new", "", 6, ["right", "right", "right", "jump", "attack"]],
]
const N := 100        # first snapshot point
const M := 900        # ticks run
const EVERY := 25     # a snapshot point every 25 ticks
const REPLAY := 50    # ticks replayed after each restore

var S
var Data
var Sim                       # loaded at run time: the sims use the autoloads S / Data as globals
var skip := ""

class Harness:
	var sim
	var rng := RandomNumberGenerator.new()
	var keys := {}
	var use: Array = []
	var acc := 0.0
	var ticks := 0
	var close_at := {}        # dialogue id -> tick
	func state() -> Dictionary:
		return {"rng": rng.state, "keys": keys.duplicate(), "acc": acc, "ticks": ticks, "close_at": close_at.duplicate()}
	func set_state(d: Dictionary) -> void:
		rng.state = d["rng"]; keys = d["keys"].duplicate(); acc = d["acc"]; ticks = d["ticks"]; close_at = d["close_at"].duplicate()
	func on_story(ev: Dictionary) -> void:
		if ev.get("t", "") == "say":
			close_at[int(ev["id"])] = ticks + 10
	func camera() -> Dictionary:
		var p: Vector2 = sim.player.pos
		var maxx: float = max(0.0, (sim.world.w - 1) * 24.0 - VIEW.x)
		var maxy: float = max(0.0, (sim.world.h - 1) * 24.0 - VIEW.y)
		var c := Vector2(clampf(p.x - VIEW.x * 0.5, 0.0, maxx), clampf(p.y - VIEW.y * 0.75, 0.0, maxy))
		return {"pos": c, "shown": c, "view": VIEW}
	# one display frame; returns true after each 25 Hz tick (callback with the tick number)
	func run_frame(on_tick: Callable) -> void:
		acc += DT
		while acc >= 0.04:
			acc -= 0.04
			if rng.randi() % 6 == 0:            # change one key now and then
				var k: String = use[rng.randi() % use.size()]
				keys[k] = not bool(keys.get(k, false))
			sim.step({"keys": keys, "camera": camera()})
			ticks += 1
			for id in close_at.keys():
				if close_at[id] <= ticks:
					close_at.erase(id)
					sim.story.close_dialog(int(id))
			on_tick.call(ticks)
		sim.frame(DT, {"keys": keys, "camera": camera(), "alpha": clampf(acc / 0.04, 0.0, 1.0)})

func _initialize() -> void:
	S = root.get_node("S")
	Data = root.get_node("Data")
	Sim = load("res://sim/sim.gd")
	if Data.static_data.is_empty():
		Data._ready()
	for a in OS.get_cmdline_user_args():
		if a.begins_with("--skip="):
			skip = a.substr(7)
	var fail := 0
	for sc in SCEN:
		if OS.get_environment("ONLY") == "" or OS.get_environment("ONLY") == sc[0]:
			fail += _scenario(sc)
	print("SIM SNAPSHOT: %d scenarios, %d failed" % [SCEN.size(), fail])
	quit(1 if fail > 0 else 0)

func _fresh_sim(h: Harness):
	S.reset_state()
	var sim = Sim.new(S, Data)
	sim.handlers["story"] = h.on_story
	h.sim = sim
	return sim

func _js(d) -> String:
	return JSON.stringify(d, "", true, true)

func _line(sim, t: int) -> String:
	var snap: Dictionary = sim.snapshot()
	if OS.get_environment("SNAP_DUMP") != "" and t == int(OS.get_environment("DUMP_TICK")):
		var f := OS.get_environment("SNAP_DUMP") + "/tick_%d_%s.json" % [t, "b" if sim.has_meta("restored") else "a"]
		FileAccess.open(f, FileAccess.WRITE).store_string(_js(snap))
	return "%d %s A=%d fh=%d fi=%d G=%d N=%d mode=%s pos=%s vel=%s coyote=%s" % [t, _js(snap).md5_text(),
		S.A, S.fh, S.fi, S.G, S.N, sim.mode, sim.player.pos, sim.player.vel, sim.player.coyote]

func _scenario(sc: Array) -> int:
	var name: String = sc[0]
	var h := Harness.new()
	h.use = sc[4]
	h.rng.seed = int(sc[3])
	var sim = _fresh_sim(h)
	sim.set_mode("play")
	if sc[1] == "new":
		sim.story.new_game()
	else:
		for kv in str(sc[2]).split(";", false):
			var a := kv.split("=")
			var cur = S.get(a[0])
			S.set(a[0], (a[1] == "true") if cur is bool else int(a[1]))
		var p := str(sc[1]).split(",")
		sim.load_map(int(p[0]), Vector2(int(p[1]), int(p[2])), 1)
	# A: run to M, one line per tick; at every EVERY-th tick from N on (no dialogue box open) keep the snapshot
	# as JSON text and the harness state (lambdas capture locals by value: results go into this dictionary)
	var r := {"lines": {}, "points": []}
	while h.ticks < M:
		h.run_frame(func(t):
			r["lines"][t] = _line(sim, t)
			if t >= N and t % EVERY == 0 and t + REPLAY <= M and h.close_at.is_empty():
				var d: Dictionary = sim.snapshot()
				if skip != "":
					var sp := skip.split(".")
					d[sp[0]].erase(sp[1])
				r["points"].append([t, _js(d), h.state()]))
	if r["points"].is_empty():
		print("FAIL %s: no snapshot point without an open dialogue box" % name)
		return 1
	# B / C for every point: fresh state + fresh sim, restore, same input for REPLAY ticks
	var bad_lines := 0
	var bad_rt := 0
	var first := ""
	var kb := 0
	for pt in r["points"]:
		var t0: int = pt[0]
		var text: String = pt[1]
		kb = max(kb, text.length() / 1024)
		var h2 := Harness.new()
		h2.use = sc[4]
		var sim2 = _fresh_sim(h2)
		sim2.restore(JSON.parse_string(text))
		sim2.set_meta("restored", true)
		h2.set_state(pt[2])
		var again := _js(sim2.snapshot())
		if again != text:
			bad_rt += 1
			if first == "":
				first = "JSON round trip at tick %d differs" % t0
				_print_json_diff(JSON.parse_string(text), JSON.parse_string(again), "")
		var lb := {}
		# the snapshot was taken inside the tick callback of a frame, before that frame called sim.frame()
		sim2.frame(DT, {"keys": h2.keys, "camera": h2.camera(), "alpha": clampf(h2.acc / 0.04, 0.0, 1.0)})
		while h2.ticks < t0 + REPLAY:
			h2.run_frame(func(t): lb[t] = _line(sim2, t))
		for t in range(t0 + 1, t0 + REPLAY + 1):
			if lb.get(t, "") != r["lines"][t]:
				bad_lines += 1
				if first == "":
					first = "tick %d (restored from %d)\n   A %s\n   B %s" % [t, t0, r["lines"][t], lb.get(t, "")]
	var ok := bad_lines == 0 and bad_rt == 0
	print("%s %s: %d snapshot points (ticks %d..%d, up to %d KB JSON), %d ticks replayed each: %d lines differ, %d JSON round trips differ, map %d" % [
		"ok  " if ok else "FAIL", name, r["points"].size(), r["points"][0][0], r["points"][-1][0], kb, REPLAY,
		bad_lines, bad_rt, sim.world.map_id])
	if first != "":
		print("   first: " + first)
	return 0 if ok else 1

func _print_json_diff(a, b, path: String, n := [0]) -> void:
	if n[0] > 8:
		return
	if a is Dictionary and b is Dictionary:
		for k in a:
			if not b.has(k):
				print("   only in saved: %s.%s" % [path, k]); n[0] += 1
			else:
				_print_json_diff(a[k], b[k], path + "." + str(k), n)
		for k in b:
			if not a.has(k):
				print("   only after restore: %s.%s" % [path, k]); n[0] += 1
	elif a is Array and b is Array and a.size() == b.size():
		for i in a.size():
			_print_json_diff(a[i], b[i], "%s[%d]" % [path, i], n)
	elif str(a) != str(b):
		print("   %s: saved %s, after restore %s" % [path, str(a).left(80), str(b).left(80)]); n[0] += 1
