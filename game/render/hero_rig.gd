# Runtime 2D skeletal rig for the HD hero (same cutout parts and pose library as the Blender rig,
# scripts/blender_hero_rig.py -> art/hero/poses.json). Draws every display frame (60-120 fps):
#  * pose targets come from the ORIGINAL frame index B each tick (gameplay/hit timing untouched)
#  * bones follow targets with damped springs -> continuous motion, follow-through/overshoot
#  * run cycle is phase-driven by travelled distance (no 12.5 fps stepping); skid on stopping
#  * squash/stretch on take-off/landing, lean from velocity, katana trail ribbon
# All of this is presentation only; the 22x32 hit box and positions are the original logic's.
extends RefCounted

const SRC_PER_OUT := 1178.0 / 200.0
const GROUND := Vector2(620, 1215)
const BONES := [
	# name, parent, pivot key, angle key
	["root", "", "ground", ""],
	["hips", "root", "hips", "hips"],
	["torso", "hips", "hips", "torso"],
	["head", "torso", "neck", "head"],
	["back_upper", "torso", "back_shoulder", "bu"],
	["back_fore", "back_upper", "back_elbow", "bf"],
	["front_upper", "torso", "front_shoulder", "fu"],
	["front_fore", "front_upper", "front_elbow", "ff"],
	["back_thigh", "hips", "back_hip", "bt"],
	["back_shin", "back_thigh", "back_knee", "bs"],
	["front_thigh", "hips", "front_hip", "ft"],
	["front_shin", "front_thigh", "front_knee", "fs"],
]
const PART_BONE := {"head": "head", "head_hurt": "head", "head_dig": "head", "eyes_open": "head",
	"eyes_half": "head", "torso": "torso", "back_upper": "back_upper", "back_fore": "back_fore",
	"front_upper": "front_upper", "front_fore": "front_fore", "back_thigh": "back_thigh",
	"back_shin": "back_shin", "front_thigh": "front_thigh", "front_shin": "front_shin"}
const BODY := ["head", "torso", "back_upper", "back_fore", "front_upper", "front_fore",
	"back_thigh", "back_shin", "front_thigh", "front_shin"]
const EXTRA_Z := {"head_hurt": 7.0, "head_dig": 7.0, "eyes_open": 7.2, "eyes_half": 7.2}
const KEYS := ["hips", "torso", "head", "bu", "bf", "fu", "ff", "bt", "bs", "ft", "fs", "kd"]
const BALL_POINTS := [["torso", "neck"], ["hips", "hips"], ["back_thigh", "back_knee"],
	["front_thigh", "front_knee"], ["back_shin", "back_ankle"], ["front_shin", "front_ankle"], ["head", "head_top"]]
const RUN_B := [2, 3, 4, 5, 6]
const ATTACK_B := [13, 14, 15, 16, 17, 18, 19, 20, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42]

var ok := false
var meta := {}
var poses := {}
var tex := {}
var piv := {}
var ang := {}       # displayed angles (deg, Blender CCW convention)
var vel := {}
var root_off := Vector2.ZERO
var root_vel := Vector2.ZERO
var squash := 1.0
var squash_v := 0.0
var run_phase := 0.0
var run_blend := 0.0
var skid := 0.0
var last_A := -1
var last_x := 0.0
var vx_s := 0.0
var trail: Array = []   # [[tip, mid, age]]
var katana_vis := 0.0
var kd_shown := 0.0

func load_assets() -> void:
	var mp := "res://art/hero/parts/parts.json"
	var pp := "res://art/hero/poses.json"
	if not FileAccess.file_exists(mp) or not FileAccess.file_exists(pp):
		return
	meta = JSON.parse_string(FileAccess.get_file_as_string(mp))
	var pj: Dictionary = JSON.parse_string(FileAccess.get_file_as_string(pp))
	for k in pj["poses"]:
		poses[int(k)] = pj["poses"][k]
	for k in meta["pivots"]:
		piv[k] = Vector2(meta["pivots"][k][0], meta["pivots"][k][1])
	for name in meta["parts"]:
		var p := "res://art/hero/parts/" + str(meta["parts"][name]["file"])
		if ResourceLoader.exists(p):
			tex[name] = load(p)
	for k in KEYS:
		ang[k] = 0.0
		vel[k] = 0.0
	ok = tex.size() >= 10

# ---------- kinematics (source pixel space, y down) ----------
func _bone_xforms(a: Dictionary, breathe: float) -> Dictionary:
	var xf := {}
	for b in BONES:
		var name: String = b[0]
		var parent: String = b[1]
		var pv: Vector2 = piv.get(b[2], GROUND)
		var deg: float = a.get(b[3], 0.0) if b[3] != "" else 0.0
		var local := Transform2D(0.0, pv) * Transform2D(-deg_to_rad(deg), Vector2.ZERO)
		if name == "torso" and breathe != 1.0:
			local = local * Transform2D(0.0, Vector2(1.0, breathe), 0.0, Vector2.ZERO)
		local = local * Transform2D(0.0, -pv)
		xf[name] = (xf[parent] * local) if parent != "" else local
	return xf

func _sole_bottom(xf: Dictionary) -> float:
	var m := -1e9
	for part in meta["sole_points"]:
		var bone: String = PART_BONE[part]
		for p in meta["sole_points"][part]:
			m = max(m, (xf[bone] * Vector2(p[0], p[1])).y)
	return m

func _placement(pose: Dictionary, xf: Dictionary) -> Vector2:
	var place: Array = pose.get("place", ["ground", 0.0])
	var lift: float = float(place[1]) * SRC_PER_OUT
	match str(place[0]):
		"ground":
			return Vector2(0, GROUND.y - lift - _sole_bottom(xf))
		"neck":
			var cur: Vector2 = xf["torso"] * piv["neck"]
			return Vector2(0, piv["neck"].y - lift - cur.y)
		_:
			var c := Vector2.ZERO
			for i in BALL_POINTS.size() - 1:
				c += xf[BALL_POINTS[i][0]] * piv[BALL_POINTS[i][1]]
			c /= float(BALL_POINTS.size() - 1)
			var top: Vector2 = xf["head"] * piv["head_top"]
			c = (c * 4.0 + top) / 5.0
			return Vector2(GROUND.x - c.x, GROUND.y - lift - c.y)

func _lerp_pose(p1: Dictionary, p2: Dictionary, t: float) -> Dictionary:
	var out := (p1 if t < 0.5 else p2).duplicate()
	for k in KEYS:
		if p1.has(k) or p2.has(k):
			out[k] = lerpf(float(p1.get(k, 0.0)), float(p2.get(k, 0.0)), t)
	return out

# Catmull-Rom over the 5 original run poses, phase in [0,1)
func _run_pose(phase: float) -> Dictionary:
	var n := RUN_B.size()
	var f := phase * n
	var i := int(floor(f)) % n
	var t = f - floor(f)
	var p0: Dictionary = poses[RUN_B[(i - 1 + n) % n]]
	var p1: Dictionary = poses[RUN_B[i]]
	var p2: Dictionary = poses[RUN_B[(i + 1) % n]]
	var p3: Dictionary = poses[RUN_B[(i + 2) % n]]
	var out := p1.duplicate()
	for k in KEYS:
		var a0 := float(p0.get(k, 0.0))
		var a1 := float(p1.get(k, 0.0))
		var a2 := float(p2.get(k, 0.0))
		var a3 := float(p3.get(k, 0.0))
		out[k] = 0.5 * ((2.0 * a1) + (-a0 + a2) * t + (2.0 * a0 - 5.0 * a1 + 4.0 * a2 - a3) * t * t + (-a0 + 3.0 * a1 - 3.0 * a2 + a3) * t * t * t)
	out["place"] = ["ground", 0.0]
	return out

# ---------- per display frame ----------
func update(core, B: int, prev_B: int, alpha: float, world_x: float, dt: float) -> void:
	if not ok or dt <= 0.0:
		return
	dt = min(dt, 0.05)
	var A: int = core.A
	# smoothed horizontal speed (logical px / s) from the interpolated position
	var vx := (world_x - last_x) / dt
	if abs(vx) > 600.0:
		vx = 0.0
	last_x = world_x
	vx_s = lerpf(vx_s, vx, 1.0 - exp(-dt / 0.08))
	var running := B in RUN_B and A == 2
	run_blend = move_toward(run_blend, 1.0 if running else 0.0, dt * 8.0)
	if running:
		run_phase = fposmod(run_phase + abs(vx_s) * dt / 44.0, 1.0)
	# skid when the run stops with momentum
	if last_A == 2 and A == 1 and abs(vx_s) > 30.0:
		skid = 1.0
	skid = max(0.0, skid - dt * 3.5)
	# take-off / landing squash
	if A == 3 and last_A in [1, 2]:
		squash_v = -2.2
	if A in [1, 2, 8] and last_A in [3, 4, 12]:
		squash_v = 3.2
	last_A = A
	squash_v += ((1.0 - squash) * 260.0 - squash_v * 18.0) * dt
	squash += squash_v * dt
	squash = clampf(squash, 0.82, 1.15)

	var target: Dictionary = poses.get(B, poses[0])
	if prev_B != B and poses.has(prev_B):
		# the display runs one tick behind: ease from the previous key pose
		target = _lerp_pose(poses[prev_B], target, smoothstep(0.0, 1.0, alpha))
	if run_blend > 0.0:
		target = _lerp_pose(target, _run_pose(run_phase), run_blend)
	if skid > 0.0:
		var s := skid * skid
		target = target.duplicate()
		target["torso"] = float(target.get("torso", 0.0)) + 14.0 * s
		target["ft"] = float(target.get("ft", 0.0)) - 30.0 * s
		target["fs"] = float(target.get("fs", 0.0)) - 10.0 * s
		target["bt"] = float(target.get("bt", 0.0)) + 22.0 * s
		target["bu"] = float(target.get("bu", 0.0)) - 30.0 * s
		target["fu"] = float(target.get("fu", 0.0)) + 25.0 * s
	# lean into the run
	target = target.duplicate()
	target["torso"] = float(target.get("torso", 0.0)) - clampf(abs(vx_s) / 100.0, 0.0, 1.0) * 6.0 * run_blend

	var attacking := B in ATTACK_B
	for k in KEYS:
		if not target.has(k) and k != "kd":
			target[k] = 0.0
		if not target.has(k):
			continue
		var tgt := float(target[k])
		# bones: stiff spring with slight under-damping during attacks (follow-through)
		var w := 34.0
		var z := 0.9
		if attacking and k in ["bu", "bf", "fu", "ff", "kd", "torso"]:
			w = 46.0
			z = 0.78
		if k in ["bt", "bs", "ft", "fs"] and run_blend > 0.5:
			w = 60.0
			z = 1.0
		if k == "head":
			w = 22.0
			z = 0.55
		# take the short way for whole-body spins
		var d := tgt - float(ang[k])
		if k == "hips":
			d = wrapf(d, -180.0, 180.0)
		vel[k] += (d * w * w - 2.0 * z * w * float(vel[k])) * dt
		ang[k] = float(ang[k]) + float(vel[k]) * dt
	katana_vis = move_toward(katana_vis, 1.0 if target.has("kd") else 0.0, dt * 10.0)

	var xf := _bone_xforms(ang, float(target.get("breathe", 1.0)))
	var want := _placement(target, xf)
	var place_kind := str(target.get("place", ["ground", 0])[0])
	if place_kind == "ground":
		root_off = want          # feet locked to the ground every frame
		root_vel = Vector2.ZERO
	else:
		root_vel += ((want - root_off) * 900.0 - root_vel * 50.0) * dt
		root_off += root_vel * dt
	_cur_target = target

var _cur_target := {}

# ---------- drawing ----------
var cam_now := Vector2.ZERO

func draw(ci: CanvasItem, ground_screen: Vector2, facing: int, scale_out: float, mod: Color, cam_dt: float, cam := Vector2.ZERO) -> void:
	cam_now = cam
	if not ok:
		return
	var target: Dictionary = _cur_target
	var k := scale_out / SRC_PER_OUT     # logical px per source px
	var xf := _bone_xforms(ang, float(target.get("breathe", 1.0)))
	var base := Transform2D(0.0, ground_screen)
	base = base * Transform2D(0.0, Vector2(k * facing, k * squash), 0.0, Vector2.ZERO)
	base = base * Transform2D(0.0, Vector2(0, 0)) * Transform2D(0.0, root_off - GROUND)
	# stretch around the feet: x widens slightly when squashed
	var sx := 1.0 + (1.0 - squash) * 0.6
	base = Transform2D(0.0, ground_screen) * Transform2D(0.0, Vector2(k * facing * sx, k * squash), 0.0, Vector2.ZERO) * Transform2D(0.0, root_off - GROUND)
	# visibility & z
	var only = target.get("only", null)
	var show: Array = target.get("show", [])
	var hide: Array = target.get("hide", [])
	var zover: Dictionary = target.get("z", {})
	var scl: Dictionary = target.get("scale", {})
	var items := []
	for name in tex:
		var vis := false
		if only != null:
			vis = name in only
		elif name in BODY:
			vis = not (name in hide)
		else:
			vis = name in show
		if not vis:
			continue
		var z: float = float(zover.get(name, meta["z"].get(name, EXTRA_Z.get(name, 5.0))))
		items.append([z, name])
	var kvis: bool = target.has("kd") and only == null
	if kvis or katana_vis > 0.05:
		items.append([float(target.get("kz", 8.5)), "@katana"])
	items.sort_custom(func(a, b): return a[0] < b[0])
	for it in items:
		var name: String = it[1]
		if name == "@katana":
			_draw_katana(ci, base, xf, k)
			continue
		var info: Dictionary = meta["parts"][name]
		var off := Vector2(info["offset"][0], info["offset"][1])
		var sz := Vector2(info["size"][0], info["size"][1])
		var bone: String = PART_BONE.get(name, "torso")
		var t: Transform2D = base * xf[bone]
		var s: float = float(scl.get(name, 1.0))
		if s != 1.0:
			var c := off + sz * 0.5
			t = t * Transform2D(0.0, c) * Transform2D(0.0, Vector2(s, s), 0.0, Vector2.ZERO) * Transform2D(0.0, -c)
		ci.draw_set_transform_matrix(t)
		ci.draw_texture_rect(tex[name], Rect2(off, sz), false, mod)
	ci.draw_set_transform_matrix(Transform2D.IDENTITY)
	_draw_trail(ci, cam_dt)
	if target.get("show", []).has("spin_ring"):
		var c2: Vector2 = base * (xf["hips"] * piv["hips"])
		ci.draw_arc(c2, 17.0, 0.0, TAU * 0.8, 28, Color(1, 1, 1, 0.55), 1.6, true)

func _draw_katana(ci: CanvasItem, base: Transform2D, xf: Dictionary, k: float) -> void:
	var hand: Vector2 = xf["front_fore"] * piv["front_hand"]
	var a := -deg_to_rad(float(ang.get("kd", 0.0)))
	var dir := Vector2(cos(a), sin(a))
	var nrm := Vector2(-dir.y, dir.x)
	var L := 330.0   # blade length in source px (~0.75 of the hero height ratio used by the Blender katana)
	var pts_blade := PackedVector2Array()
	for i in 9:
		var u := float(i) / 8.0
		var curve := nrm * (-18.0 * u * u)
		pts_blade.append(hand + dir * (75.0 + L * u) + curve + nrm * (16.0 * (1.0 - 0.6 * u)))
	pts_blade.append(hand + dir * (75.0 + L + 30.0) + nrm * (-22.0))
	for i in range(8, -1, -1):
		var u := float(i) / 8.0
		var curve := nrm * (-18.0 * u * u)
		pts_blade.append(hand + dir * (75.0 + L * u) + curve - nrm * (14.0 * (1.0 - 0.5 * u)))
	ci.draw_set_transform_matrix(base)
	var outline := PackedVector2Array(pts_blade)
	outline.append(pts_blade[0])
	ci.draw_colored_polygon(pts_blade, Color(0.80, 0.86, 0.95, katana_vis))
	ci.draw_polyline(outline, Color(0.07, 0.06, 0.1, katana_vis), 7.0, true)
	# edge highlight
	ci.draw_line(hand + dir * 80.0 - nrm * 8.0, hand + dir * (70.0 + L) - nrm * 14.0, Color(1, 1, 1, 0.8 * katana_vis), 5.0)
	# guard + handle
	ci.draw_line(hand + dir * 62.0 + nrm * 30.0, hand + dir * 62.0 - nrm * 30.0, Color(0.95, 0.72, 0.15, katana_vis), 16.0)
	ci.draw_line(hand - dir * 70.0, hand + dir * 55.0, Color(0.12, 0.09, 0.18, katana_vis), 26.0)
	for i in 4:
		ci.draw_circle(hand - dir * (50.0 - i * 30.0), 7.0, Color(0.9, 0.84, 0.62, katana_vis))
	ci.draw_set_transform_matrix(Transform2D.IDENTITY)
	# trail sample (screen space)
	var tip: Vector2 = base * (hand + dir * (75.0 + L))
	var mid: Vector2 = base * (hand + dir * (75.0 + L * 0.35))
	if abs(float(vel.get("kd", 0.0))) > 260.0 and katana_vis > 0.9:
		trail.push_front([tip + cam_now, mid + cam_now, 0.0])
	elif trail.size() > 0:
		trail[0][2] += 0.03

func _draw_trail(ci: CanvasItem, dt: float) -> void:
	for t in trail:
		t[2] += max(dt, 0.0)
	while trail.size() > 0 and (trail[-1][2] > 0.12 or trail.size() > 14):
		trail.pop_back()
	if trail.size() < 3:
		return
	# only show a ribbon when the blade actually sweeps fast
	var pts := PackedVector2Array()
	for i in trail.size() - 1:
		var a0: Array = trail[i]
		var a1: Array = trail[i + 1]
		var fade := 1.0 - float(i) / float(trail.size())
		var quad := PackedVector2Array([a0[0] - cam_now, a1[0] - cam_now, a1[1] - cam_now, a0[1] - cam_now])
		ci.draw_colored_polygon(quad, Color(0.75, 0.92, 1.0, 0.38 * fade * katana_vis))
	for e in trail:
		pts.append(e[0] - cam_now)
	ci.draw_polyline(pts, Color(1, 1, 1, 0.8 * katana_vis), 1.3, true)
