# Map objects of the original: push blocks / rafts (it, iv, iw, ix, iy, iz, iA, iB; a.java d(n) G() c(),
# update in run() ~L9160) and motorbikes (il, in, io, ip, iq, ir, is; c(n) F() b(), update ~L9250).
# Stepped on the 25 Hz clock (original integer rules), drawn interpolated.
extends Node2D

const BLOCKS := {1: [[21, 9], [13, 8]], 11: [[33, 7], [44, 10]], 5: [[5, 46], [15, 46]], 97: [[3, 6], [7, 4], [12, 3], [22, 6]]}
const BIKES := {2: [18, 12], 3: [2, 9], 6: [12, 9], 8: [3, 12], 9: [2, 11], 10: [3, 12], 11: [5, 6], 12: [4, 8],
	13: [16, 11], 14: [18, 7], 15: [1, 11], 16: [7, 11], 17: [14, 12]}

var world
var tex_b: Texture2D
var tex_v: Array = []
var prev_b: Array = []
var prev_v: Array = []
var alpha := 0.0

func _ready() -> void:
	z_index = 4
	tex_b = load("res://art/obj/b.png")
	for i in 3:
		tex_v.append(load("res://art/obj/v%d.png" % i))
	Game.tick25.connect(_tick)

# called by World.load_map (original d(n) then c(n))
func setup(id: int) -> void:
	var bl: Array = BLOCKS.get(id, [])
	S.it = bl.size()
	S.iv = []; S.iw = []; S.ix = []; S.iy = []; S.iz = []; S.iA = []; S.iB = []
	for b in bl:
		S.iv.append(b[0] * 24 + 12)
		S.iw.append(b[1] * 24 + 24)
		S.ix.append(1)
		S.iy.append(0); S.iz.append(0); S.iA.append(0); S.iB.append(0)
	S.iu = -1
	# motorbikes: the one being ridden comes along (im != -1), plus the parked one of this map
	var riding: bool = int(S.im) != -1
	S.il = 0
	var xs := []; var ys := []
	if riding and (id in [0, 1, 7, 5, 18] or id >= 94):
		xs.append(S.fh); ys.append(S.fi)
	if BIKES.has(id):
		if riding:
			xs.push_front(BIKES[id][0] * 24 + 12)
			ys.push_front(BIKES[id][1] * 24)
			xs.append(S.fh); ys.append(S.fi)
		else:
			xs = [BIKES[id][0] * 24 + 12]
			ys = [BIKES[id][1] * 24]
	S.il = xs.size()
	_set_bikes(xs, ys)
	S.im = (S.il - 1) if riding and S.il > 0 else -1
	if S.im != -1:
		S.ip[S.im] = S.fp
	_snap()

func _set_bikes(xs: Array, ys: Array) -> void:
	S.in_ = xs.duplicate()
	S.io = ys.duplicate()
	S.ip = []; S.iq = []; S.ir = []; S.is_ = []
	for i in xs.size():
		S.ip.append(1); S.iq.append(0); S.ir.append(0); S.is_.append(0)

func bx() -> Array:
	return S.in_

func _snap() -> void:
	prev_b = []
	for i in int(S.it):
		prev_b.append(Vector2(S.iv[i], S.iw[i]))
	prev_v = []
	for i in int(S.il):
		prev_v.append(Vector2(bx()[i], S.io[i]))

func g(x: int, y: int, f: int) -> bool:
	return (world.flags_at(x, y) & f) == f

func _tick() -> void:
	if world == null or world.map_id < 0 or Game.mode != "play":
		return
	_snap()
	for n in int(S.it):
		match int(S.ix[n]):
			2:   # sliding after a push
				S.iv[n] += S.iy[n]
				if S.iy[n] > 0: S.iy[n] -= 1
				elif S.iy[n] < 0: S.iy[n] += 1
				else: S.ix[n] = 1
				if not g(S.iv[n] - 12, S.iw[n] + 1, 2) and not g(S.iv[n] + 12, S.iw[n] + 1, 2):
					S.ix[n] = 4
					S.iz[n] = 2
				if g(S.iv[n] + 12, S.iw[n] - 2, 4) or g(S.iv[n] - 12, S.iw[n] - 2, 8):
					S.iv[n] = int(S.iv[n] / 24) * 24 + 12
			4:   # falling
				S.iw[n] += S.iz[n]
				if S.iz[n] < 20: S.iz[n] += 1
				if g(S.iv[n] + 12, S.iw[n], 2) or g(S.iv[n] - 12, S.iw[n], 2):
					S.iw[n] = int(S.iw[n] / 24) * 24
					S.iz[n] = 0
					S.ix[n] = 1
				elif g(S.iv[n], S.iw[n] - 24, 64):
					S.iw[n] = int(S.iw[n] / 24) * 24 + 1
					world.spawn_fx("splash", Vector2(S.iv[n], S.iw[n] - 13), 1)
					S.iz[n] = 1
					S.iA[n] = -1
					S.iB[n] = 1
					S.ix[n] = 11
			11:  # floating raft: bobs and drifts, carries the player standing on it
				if S.w % 2 == 0:
					S.iw[n] += S.iA[n]
					S.iz[n] += S.iA[n]
					if S.iz[n] >= 1: S.iA[n] = -1
					if S.iz[n] <= -1: S.iA[n] = 1
					S.iv[n] += S.iB[n]
					if n == int(S.iu) and world.player:
						world.player.carry(Vector2(S.iB[n], 0), S.iw[n] - 24)
					if g(S.iv[n] + 12, S.iw[n], 4) or g(S.iv[n] - 12, S.iw[n], 8):
						S.iB[n] = -S.iB[n]
	for n in int(S.il):
		if n == int(S.im):
			S.iq[n] = 2 if S.C > 1 else (1 if S.w % 8 > 3 else 0)
		if int(S.ir[n]) == 1:
			S.io[n] += S.is_[n]
			if S.is_[n] < 20: S.is_[n] += 1
			if S.io[n] >= world.h * 24 or g(bx()[n], S.io[n], 2):
				S.is_[n] = 0
				S.ir[n] = 0
				S.io[n] = int(S.io[n] / 24) * 24

# player pushes / lands on blocks (original y(), x(), w(), u())
func block_at_side(px: float, py: float, dir: int) -> int:
	for n in int(S.it):
		if abs(px - S.iv[n]) < 20 and int(S.iw[n]) == int(py):
			return n
	return -1

func push(n: int, v: int) -> void:
	if int(S.ix[n]) != 4:
		S.ix[n] = 2
		S.iy[n] = v

func land_block(px: float, y0: float, y1: float) -> int:
	# a block whose top (iw - 24) is crossed this frame and that is under the feet
	for n in int(S.it):
		var top: float = S.iw[n] - 24
		if int(S.ix[n]) != 4 and abs(px - S.iv[n]) < 20 and top >= y0 - 0.01 and top <= y1:
			return n
	return -1

func hit_blocks(px: int, py: int, dir: int) -> void:
	for n in int(S.it):
		if abs(py - 16 - S.iw[n]) < 20 and abs(px + (dir << 4) - S.iv[n]) < 24:
			S.ix[n] = 2
			S.iy[n] = dir << 3
			world.spawn_fx("hit", Vector2(S.iv[n], S.iw[n] - 12), dir)
			return

func _process(_d: float) -> void:
	alpha = clampf(Game.acc / 0.04, 0.0, 1.0)
	queue_redraw()

func _draw() -> void:
	for n in int(S.it):
		var p := Vector2(S.iv[n], S.iw[n])
		if n < prev_b.size():
			p = prev_b[n].lerp(p, alpha)
		draw_texture_rect(tex_b, Rect2(p.x - 12, p.y - 24, 24, 24), false)
	for n in int(S.il):
		if n == int(S.im):
			continue            # the ridden bike is drawn with the player
		var p := Vector2(bx()[n], S.io[n])
		if n < prev_v.size():
			p = prev_v[n].lerp(p, alpha)
		draw_bike(self, p, int(S.ip[n]), int(S.iq[n]))

func draw_bike(ci: CanvasItem, p: Vector2, dir: int, frame: int) -> void:
	var t: Texture2D = tex_v[clampi(frame, 0, 2)]
	var sz := Vector2(t.get_width(), t.get_height()) / 4.0
	if dir == 1:
		ci.draw_texture_rect(t, Rect2(p.x - sz.x * 0.5, p.y - sz.y, sz.x, sz.y), false)
	else:
		ci.draw_set_transform(Vector2(p.x + sz.x * 0.5, p.y - sz.y), 0.0, Vector2(-1, 1))
		ci.draw_texture_rect(t, Rect2(Vector2.ZERO, sz), false)
		ci.draw_set_transform(Vector2.ZERO)
