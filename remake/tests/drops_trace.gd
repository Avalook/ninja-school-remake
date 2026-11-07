# Headless behaviour lock for the loot rules (coins W(), potions ab(), quest scroll fK).
# Run: tests/drops_trace.sh record|check
# A fake owner `m` (the role EnemiesSim plays: player feet fh/fi, height fk, map size iR/iS, tile flags,
# text/popup events) and a seeded schedule of spawns and player moves. Every tick writes all slots,
# the scroll, the rule vars the pickup touches (S.dg G I fD c) and the events of that tick.
# Covers: 3 coin sizes, HP/MP potions (capped at H/J), the scroll, bouncing on floor flag 2,
# leaving the map, expiry after 200 ticks, slot exhaustion (more than 5 spawns at once).
extends SceneTree

const DROPS_PATH := "res://sim/enemies/drops.gd"

class FakeOwner:
	var fh := 0
	var fi := 0
	var fk := 32
	var iR := 960
	var iS := 480
	var ev: Array = []
	func flags(x: int, y: int) -> int:
		if x < 0 or y < 0 or x >= iR or y >= iS:
			return 0
		@warning_ignore("integer_division")
		var ty := y / 24
		@warning_ignore("integer_division")
		var tx := x / 24
		if ty == 15 or (ty == 9 and tx >= 10 and tx < 20) or (ty == 5 and tx >= 30):
			return 2
		return 0
	func text(s: String, x: int, y: int, c: int) -> void:
		ev.append("text:%s@%d,%d,%d" % [s, x, y, c])
	func popup(s: String) -> void:
		ev.append("popup:" + s)

func _initialize() -> void:
	var out_path := ""
	for a in OS.get_cmdline_user_args():
		if a.begins_with("--out="):
			out_path = a.substr(6)
	var S = root.get_node("S")
	var f := FileAccess.open(out_path, FileAccess.WRITE)
	var Drops = load(DROPS_PATH)
	for seed in [1, 2, 3, 4]:
		var rng := RandomNumberGenerator.new()
		rng.seed = seed
		S.dg = 0; S.G = 50; S.H = 200; S.I = 40; S.J = 150; S.fD = false; S.c = false
		var d = Drops.new()
		var m := FakeOwner.new()
		m.fh = 480; m.fi = 360
		var tx := 480
		for t in 1500:
			# schedule: bursts of spawns around the player, sometimes far away / off map
			if rng.randi() % 25 == 0:
				var n := 1 + rng.randi() % 7
				for k in n:
					var x := rng.randi_range(-30, 990)
					var y := rng.randi_range(-10, 470)
					if rng.randi() % 2 == 0:   # near the player (pickup range is 10 px around (fh, fi - fk/2 + 7))
						x = m.fh + rng.randi_range(-40, 40)
						y = m.fi - 9 + rng.randi_range(-30, 12)
					var vx := rng.randi_range(-16, 16)
					match rng.randi() % 4:
						0, 1:
							d.spawn_coin(x, y, vx, rng.randi_range(-8, 0), rng.randi_range(1, 900))
						2:
							d.spawn_item(x, y, vx, rng.randi_range(1, 300), rng.randi() % 2)
						3:
							if rng.randi() % 3 == 0:
								d.spawn_scroll(x, y)
			if rng.randi() % 60 == 0:
				tx = rng.randi_range(0, 959)
			if rng.randi() % 40 == 0:
				m.fi = [360, 216, 120, 400][rng.randi() % 4]
			m.fh += clampi(tx - m.fh, -6, 6)
			if t == 900 and seed == 2:
				d.clear()
			m.ev.clear()
			S.c = false
			d.tick(m)
			var sl: Array = []
			for c in d.coins:
				sl.append("%d,%d,%d,%d,%d,%d,%d,%d,%d,%d" % [c.age, c.x, c.y, c.vx, c.vy, c.value, c.size, c.spin, int(c.px), int(c.py)])
			for c in d.items:
				sl.append("%d,%d,%d,%d,%d,%d,%d,%d,%d,%d" % [c.age, c.x, c.y, c.vx, c.vy, c.value, c.size, c.kind, int(c.px), int(c.py)])
			f.store_line("s%d t%d P=%d,%d D=%s K=%d,%d,%d,%d S=%d,%d,%d,%s,%s E=%s" % [seed, t, m.fh, m.fi, ";".join(sl),
				d.scroll_state, d.scroll_x, d.scroll_y, int(d.scroll_py), S.dg, S.G, S.I, S.fD, S.c, "|".join(m.ev)])
	f.close()
	quit(0)
