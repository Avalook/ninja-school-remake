# Usage: godot --headless --path . --script res://tests/script_run.gd -- <script.txt> [trace.txt]
# Script: "wait <ms>" (=ticks of 40ms) | "tap k [n]" | "hold k ms" | "press k" | "release k" | "vars a b c" | "set f v" | "call fn args"
extends SceneTree
var g
var trace: FileAccess
const TF = ["d","v","A","B","fh","fi","fR","fS","fV","fX","gc","G","I","N","M","dg","O","fF","w","jN","jO","kc","jS","hu","hP","hS","hT"]
func step(n: int) -> void:
	for i in n:
		g.tick()
		if trace:
			var s := str(g.ticks)
			for fname in TF:
				var val = g.get(fname)
				s += " %s=%s" % [fname, str(val).replace(" ", "")]
			trace.store_line(s)
func _init():
	var args := OS.get_cmdline_user_args()
	# clean save storage for deterministic tests
	var rd := OS.get_environment("NINJA_RMS") if OS.get_environment("NINJA_RMS") != "" else "user://rms"
	var dir := DirAccess.open(rd) if OS.get_environment("KEEP_RMS") == "" else null
	if dir:
		for f in dir.get_files():
			dir.remove(f)
	var C = load("res://core/core.gd")
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
	quit()
