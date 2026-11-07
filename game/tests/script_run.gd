# Usage: godot --headless --path . --script res://tests/script_run.gd -- <script.txt> [trace.txt]
# Script: "wait <ms>" (=ticks of 40ms) | "tap k [n]" | "hold k ms" | "press k" | "release k" | "vars a b c" | "set f v" | "call fn args"
extends SceneTree
var g
var trace: FileAccess
func step(n: int) -> void:
	for i in n:
		g.tick()
		if trace:
			trace.store_line("%d d=%d v=%d A=%d B=%d fh=%d fi=%d fR=%d fS=%d fV=%s fX=%d gc=%d" % [g.ticks, g.d, g.v, g.A, g.B, g.fh, g.fi, g.fR, g.fS, str(g.fV), g.fX, g.gc])
func _init():
	var args := OS.get_cmdline_user_args()
	var C = load("res://core/core.gd")
	g = C.new()
	g.boot()
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
			"vars":
				var s := ""
				for k in range(1, t.size()):
					s += "%s=%s " % [t[k], str(g.get(t[k]))]
				print("[vars] ", s)
			"texts": print("[texts] ", g.get("aG"))
	quit()
