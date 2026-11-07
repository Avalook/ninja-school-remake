# Story oracle: copy of game/tests/script_run.gd driving the ORIGINAL (transpiled) game with story logging.
# Usage (from remake/): $GODOT --headless --path ../game --script $PWD/tests/story_oracle/story_oracle.gd -- <script.txt>
# Prints "[story] <tick> say/note/seq/group/warp/title ..." lines; compared by tests/story_*.gd expectations.
# Script: "wait <ms>" (=ticks of 40ms) | "tap k [n]" | "hold k ms" | "press k" | "release k" | "vars a b c" | "set f v" | "call fn args"
extends SceneTree
var g
var trace: FileAccess
const TF = ["d","v","A","B","fh","fi","fR","fS","fV","fX","gc","G","I","N","M","dg","O","fF","w","jN","jO","kc","jS","hu","hP","hS","hT"]
var last_title := ""
func step(n: int) -> void:
	for i in n:
		g.tick()
		if g.fY and g.fZ != null and str(g.fZ) != last_title:
			last_title = str(g.fZ)
			g._log("title %s" % last_title)
		if trace:
			var s := str(g.ticks)
			for fname in TF:
				var val = g.get(fname)
				s += " %s=%s" % [fname, str(val).replace(" ", "")]
			trace.store_line(s)
var stuck := 0
var lastx := 0
func dismiss() -> void:
	# popups (d==7) and dialogue bubbles block the bot: confirm them like a player would
	var guard := 0
	while (g.d == 7 or (g.d == 1 and g.gd)) and guard < 60:
		var kk := -6 if g.d == 7 else -5
		g.key_down(kk); step(2); g.key_up(kk); step(4)
		guard += 1

func bot_goto(target: Callable, ticks: int, near := 14) -> void:
	for k in ticks:
		dismiss()
		var dx: int = int(target.call()) - g.fh
		if abs(dx) <= near:
			break
		var want := -4 if dx > 0 else -3
		g.key_up(-3 if want == -4 else -4)
		g.key_down(want)
		if g.fh == lastx:
			stuck += 1
		else:
			stuck = 0
		lastx = g.fh
		if stuck > 4:
			g.key_down(-1); step(3); g.key_up(-1)
			stuck = 0
		step(1)
	g.key_up(-3); g.key_up(-4)

func _init():
	var args := OS.get_cmdline_user_args()
	# clean save storage for deterministic tests
	var rd := OS.get_environment("NINJA_RMS") if OS.get_environment("NINJA_RMS") != "" else "user://rms"
	var dir := DirAccess.open(rd) if OS.get_environment("KEEP_RMS") == "" else null
	if dir:
		for f in dir.get_files():
			dir.remove(f)
	var C = load(get_script().resource_path.get_base_dir() + "/logged_core.gd")
	g = C.new()
	if OS.get_environment("DIFF") != "":
		g.auto_prompts = false
		g.virtual_clock = true
	g.boot(int(OS.get_environment("NW")) if OS.get_environment("NW") != "" else 480, int(OS.get_environment("NH")) if OS.get_environment("NH") != "" else 270)
	if args.size() > 1:
		trace = FileAccess.open(args[1], FileAccess.WRITE)
	for line in FileAccess.get_file_as_string(args[0]).split("\n"):
		var t := line.strip_edges().split(" ", false)
		if t.is_empty() or t[0].begins_with("#"):
			continue
		match t[0]:
			"wait": step(int(t[1]) / 40)
			"tap":
				var n := int(t[2]) if t.size() > 2 else 1
				for k in n:
					g.key_down(int(t[1])); step(2); g.key_up(int(t[1])); step(3)
			"hold":
				g.key_down(int(t[1])); step(int(t[2]) / 40); g.key_up(int(t[1]))
			"press": g.key_down(int(t[1]))
			"release": g.key_up(int(t[1]))
			"set": g.set(t[1], int(t[2]))
			"call":
				var a := []
				for k in range(2, t.size()):
					a.append(int(t[k]))
				g.callv(t[1] + "_" + "i".repeat(a.size()), a)
			"vars":
				var s := ""
				for k in range(1, t.size()):
					s += "%s=%s " % [t[k], str(g.get(t[k]))]
				print("[vars] ", s)
			"texts": print("[texts] ", g.get("aG"))
			"bot_npc":
				# walk to NPC t[1] (ly/lz), then press talk (-7) and advance dialogue
				bot_goto(func(): return g.ly[int(t[1])], int(t[2]))
				step(15)
				print("[bot] near npc o=", g.o, " A=", g.A, " fh=", g.fh)
				g.key_down(-7); step(2); g.key_up(-7); step(4)
				for k in 40:
					if g.d != 1:
						break
					g.key_down(-5); step(2); g.key_up(-5); step(6)
			"bot_kill":
				# hunt living monsters of type t[1] for t[2] ticks
				var mt := int(t[1])
				var n := int(t[2])
				var k := 0
				while k < n:
					dismiss()
					var best := -1
					var bd := 99999
					for i in g.jK:
						if g.jM[i] == mt and g.jS[i] != 1 and g.jS[i] != 0 and g.jS[i] != 5:
							var dd: int = abs(g.jN[i] - g.fh) + 2 * abs(g.jO[i] - g.fi)
							if dd < bd:
								bd = dd
								best = i
					if best < 0:
						step(10); k += 10
						continue
					var tx: int = g.jN[best]
					if g.jO[best] < g.fi - 20 and k % 80 < 40:
						g.key_down(-1); step(2); g.key_up(-1)
					bot_goto(func(): return tx, 30, 26)
					if (tx > g.fh) != (g.fp > 0):
						var w := -4 if tx > g.fh else -3
						g.key_down(w); step(1); g.key_up(w)
					g.key_down(-5); step(2); g.key_up(-5); step(4)
					k += 40
			"bot_walk":
				var target_x := int(t[1])
				var v0: int = g.v
				for kk in int(t[2]) / 10:
					bot_goto(func(): return target_x, 10, 4)
					if g.v != v0:
						break
			"bot_boss":
				# simple test bot: walk to the boss (hs,ht), face it and attack; advances dialogue with OK
				for k in int(t[1]):
					var dx: int = g.hs - g.fh
					var want := -4 if dx > 0 else -3
					var other := -3 if want == -4 else -4
					g.key_up(other)
					if abs(dx) > 18:
						g.key_down(want)
					else:
						g.key_up(want)
						if (dx > 0) != (g.fp > 0):
							g.key_down(want); step(1); g.key_up(want)
					if k % 4 == 0:
						g.key_down(-5)
					elif k % 4 == 2:
						g.key_up(-5)
					if k % 16 == 0 and abs(dx) < 40:
						g.key_down(-1)
					if k % 16 == 3:
						g.key_up(-1)
					step(1)
				g.key_up(-3); g.key_up(-4); g.key_up(-5)
	print("[final] v=%d d=%d fh=%d fi=%d O=%d fF=%d eL=%s eM=%s eH=%s fV=%s ig=%d ii=%d hu=%d eK=%s gp=%d gn=%s fe=%s fd=%s" % [g.v, g.d, g.fh, g.fi, g.O, g.fF, g.eL, g.eM, g.eH, g.fV, g.ig, g.ii, g.hu, g.eK, g.gp, g.gn, g.fe, g.fd])
	quit()
