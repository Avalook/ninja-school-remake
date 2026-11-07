# Headless oracle comparison for the enemy module.
#   godot --headless --path remake res://tests/enemies_oracle.tscn -- <map_id> <oracle_trace> [ticks]
# The oracle trace comes from the original game (game/tests/script_run.gd, see run_enemies_oracle.sh):
# it loads the same exported map state (remake/data/maps/map_<id>.json) and runs N ticks with no input.
# Here the EnemyManager is set up from the same map state; each tick the original globals the AI reads
# (w, player fh/fi/fp/A/fz, companion gq/gr/gu, camera fR/fS) are copied from the oracle row, one
# 25 Hz step is run, and every monster's jN/jO/jS/kc (+ boss positions/states) is compared.
extends Node

const EM := preload("res://scripts/enemies/enemy_manager.gd")

class FakePlayer:
	var hits := []
	func hurt(dmg: int, d: int) -> void:
		hits.append([dmg, d])

static func parse_row(line: String) -> Dictionary:
	var out := {}
	var parts := line.split(" ", false)
	out["tick"] = int(parts[0])
	for i in range(1, parts.size()):
		var kv := parts[i].split("=", true, 1)
		if kv.size() < 2:
			continue
		var v: String = kv[1]
		if v == "<null>":
			out[kv[0]] = null
		elif v.begins_with("["):
			out[kv[0]] = JSON.parse_string(v)
		elif v == "true" or v == "false":
			out[kv[0]] = v == "true"
		else:
			out[kv[0]] = int(v)
	return out

func _ready() -> void:
	var root := get_tree().root
	var args := OS.get_cmdline_user_args()
	var map_id := int(args[0])
	var rows := []
	for line in FileAccess.get_file_as_string(args[1]).split("\n", false):
		var row := parse_row(line)
		if row["tick"] >= 304:      # rows after "loadstate" (300 boot + 3 map ticks)
			rows.append(row)
	var limit := int(args[2]) if args.size() > 2 else rows.size()
	# scenario actions, applied between oracle ticks exactly like the oracle script does:
	#   k@<after_tick>:<idx>:<dmg>:<dir>   original k(idx, dmg, dir) (sword damage incl. death + loot)
	#   set@<after_tick>:<var>:<value>     set an original global in S (e.g. hu=1 starts the golem)
	var actions := []
	for i in range(3, args.size()):
		var a: String = args[i]
		var at := a.split("@")
		var p := at[1].split(":")
		actions.append({"kind": at[0], "after": int(p[0]), "args": p.slice(1)})
	var S = root.get_node("S")
	var Data = root.get_node("Data")
	var mp: Dictionary = Data.map(map_id)
	# S starts from the same exported state (static + map), like the oracle
	for k in mp["state"]:
		var cur = S.get(k)
		if cur == null or cur is Object:
			continue
		var v = mp["state"][k]
		if cur is bool:
			S.set(k, bool(v))
		elif cur is int and (v is float or v is int):
			S.set(k, int(v))
	var em = EM.new()
	em.autotick = false
	var fp := FakePlayer.new()
	em.test_player = fp
	add_child(em)
	em.setup(mp)
	var fields_total := 0
	var fields_ok := 0
	var ticks_exact := 0
	var first_bad := -1
	var first_bad_msg := ""
	var per_field := {}
	var oracle_hp_loss := 0
	var oracle_hits := 0
	var prevG := -1
	var n := mini(limit, rows.size())
	for t in n:
		var row: Dictionary = rows[t]
		# actions happen between ticks: S still holds the previous oracle row (w, player position)
		for a in actions:
			if a["after"] == row["tick"] - 1:
				if a["kind"] == "k":
					em.damage_monster(em.enemies[int(a["args"][0])], int(a["args"][1]), int(a["args"][2]))
				elif a["kind"] == "u":
					# player sword swing, original u(): damage roll + attack box, routed through hit()
					var dmg: int = S.E + S.w % (S.E >> 1)
					dmg += (dmg >> 1) * S.x
					if S.fA:
						dmg <<= 1
					var x0: int = S.fh - 20 if S.fp > 0 else S.fh - 40
					em.hit(Rect2(x0, S.fi - S.fk - 15, 60, S.fk + 20), dmg, -S.fp)
				elif a["kind"] == "s":
					# player shuriken, original u() with fg == 1
					em.spawn_projectile(S.fh + (S.fp << 3), S.fi - 12, (S.fp << 3) + S.y, S.z << 1, false)
				elif a["kind"] == "set":
					S.set(a["args"][0], int(a["args"][1]))
		S.w = row["w"]
		em.step_projectiles()      # sees the player where the previous tick left him (original order)
		for k in ["fh", "fi", "fp", "A", "fR", "fS", "gq", "gr", "gu", "y", "z", "fg"]:
			if row.has(k):
				S.set(k, row[k])
		if row.has("fz"):
			S.fz = row["fz"]
		em.step_actors()
		if prevG >= 0 and row["G"] < prevG:
			oracle_hits += 1
			oracle_hp_loss += prevG - row["G"]
		prevG = row["G"]
		var ok_tick := true
		var arrays := {"jN": "x", "jO": "y", "jS": "state", "kc": "hp", "jX": "timer", "ka": "frame", "kb": "dir"}
		for k in arrays:
			if not row.has(k) or row[k] == null:
				continue
			var ref: Array = row[k]
			for i in mini(ref.size(), em.enemies.size()):
				var mine: int = em.enemies[i].get(arrays[k])
				fields_total += 1
				var pf: Array = per_field.get(k, [0, 0])
				pf[1] += 1
				if mine == int(ref[i]):
					fields_ok += 1
					pf[0] += 1
				else:
					ok_tick = false
					if first_bad < 0:
						first_bad = row["tick"]
						first_bad_msg = "%s[%d] (type %d) mine=%d oracle=%d" % [k, i, em.enemies[i].type, mine, int(ref[i])]
				per_field[k] = pf
		# rival students (aa())
		if em.classmates:
			for k in {"cx": "x", "cy": "y", "cH": "state", "cu": "hp"}:
				if not row.has(k) or row[k] == null:
					continue
				var arr: Array = em.classmates.get({"cx": "x", "cy": "y", "cH": "state", "cu": "hp"}[k])
				for i in mini(row[k].size(), arr.size()):
					fields_total += 1
					var pf: Array = per_field.get(k, [0, 0])
					pf[1] += 1
					if int(arr[i]) == int(row[k][i]):
						fields_ok += 1
						pf[0] += 1
					else:
						ok_tick = false
						if first_bad < 0:
							first_bad = row["tick"]
							first_bad_msg = "%s[%d] mine=%d oracle=%d" % [k, i, arr[i], row[k][i]]
					per_field[k] = pf
		# loot: coins (dk do di dj) and potions (cT cX cQ cR cS), money dg, exp N
		var loot := {"dk": [em.drops.coins, "age"], "do": [em.drops.coins, "value"], "di": [em.drops.coins, "x"],
			"dj": [em.drops.coins, "y"], "cT": [em.drops.items, "age"], "cX": [em.drops.items, "value"],
			"cQ": [em.drops.items, "kind"], "cR": [em.drops.items, "x"], "cS": [em.drops.items, "y"],
			"db": [em.shots.shurikens, "state"], "cZ": [em.shots.shurikens, "x"], "da": [em.shots.shurikens, "y"],
			"bS": [em.shots.darts, "state"], "bQ": [em.shots.darts, "x"], "bZ": [em.shots.seeds, "state"], "bX": [em.shots.seeds, "x"], "bY": [em.shots.seeds, "y"]}
		for k in loot:
			if not row.has(k) or row[k] == null:
				continue
			for i in mini(row[k].size(), loot[k][0].size()):
				var d = loot[k][0][i]
				var gate := {"di": "dk", "dj": "dk", "do": "dk", "cX": "cT", "cQ": "cT", "cR": "cT", "cS": "cT",
					"cZ": "db", "da": "db", "bQ": "bS", "bX": "bZ", "bY": "bZ"}
				if gate.has(k):
					if not row.has(gate[k]) or row[gate[k]] == null:
						continue
					var gv: int = int(row[gate[k]][i])
					if gv == 0 or (gv == -1 and gate[k] != "bZ"):
						continue
				fields_total += 1
				var pf: Array = per_field.get(k, [0, 0])
				pf[1] += 1
				if int(d.get(loot[k][1])) == int(row[k][i]):
					fields_ok += 1
					pf[0] += 1
				else:
					ok_tick = false
					if first_bad < 0:
						first_bad = row["tick"]
						first_bad_msg = "%s[%d] mine=%d oracle=%d" % [k, i, d.get(loot[k][1]), row[k][i]]
				per_field[k] = pf
		for k in ["dg", "N"]:
			if row.has(k):
				fields_total += 1
				var pf: Array = per_field.get(k, [0, 0])
				pf[1] += 1
				if int(S.get(k)) == int(row[k]):
					fields_ok += 1
					pf[0] += 1
				else:
					ok_tick = false
					if first_bad < 0:
						first_bad = row["tick"]
						first_bad_msg = "%s mine=%d oracle=%d" % [k, S.get(k), row[k]]
				per_field[k] = pf
		var bosses := {"gS": [em.boar, "x"], "gT": [em.boar, "y"], "gV": [em.boar, "state"],
			"hS": [em.river, "x"], "hT": [em.river, "y"], "ia": [em.river, "state"], "hP": [em.river, "hp"],
			"hs": [em.golem, "x"], "ht": [em.golem, "y"], "hu": [em.golem, "state"]}
		for k in bosses:
			if not row.has(k) or not bosses[k][0].active:
				continue
			fields_total += 1
			var pf: Array = per_field.get(k, [0, 0])
			pf[1] += 1
			if int(bosses[k][0].get(bosses[k][1])) == int(row[k]):
				fields_ok += 1
				pf[0] += 1
			else:
				ok_tick = false
				if first_bad < 0:
					first_bad = row["tick"]
					first_bad_msg = "%s mine=%d oracle=%d" % [k, bosses[k][0].get(bosses[k][1]), row[k]]
			per_field[k] = pf
		if ok_tick:
			ticks_exact += 1
	var mine_loss := 0
	for h in fp.hits:
		mine_loss += h[0]
	print("MAP %d: ticks=%d exact_ticks=%d (%.1f%%) fields=%d/%d (%.2f%%) first_mismatch=%s %s" % [
		map_id, n, ticks_exact, 100.0 * ticks_exact / max(1, n), fields_ok, fields_total,
		100.0 * fields_ok / max(1, fields_total), str(first_bad), first_bad_msg])
	var pfs := ""
	for k in per_field:
		pfs += "%s %d/%d  " % [k, per_field[k][0], per_field[k][1]]
	print("  per field: ", pfs)
	print("  player damage: oracle %d hits / %d HP lost, remake %d hurt() calls / %d dmg" % [oracle_hits, oracle_hp_loss, fp.hits.size(), mine_loss])
	get_tree().quit()
