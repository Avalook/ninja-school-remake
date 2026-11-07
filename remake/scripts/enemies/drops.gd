# Loot dropped by monsters/bosses: coins (original slots dh=5: di dj dk dl dm dn do dp, W() ~L6265),
# potions (cP=5: cR cS cT cU cV cW cX cQ, ab() ~L6759) and the quest scroll (fI fJ fK, run() ~L10468).
@warning_ignore_start("integer_division")
extends RefCounted

const SP := preload("res://scripts/enemies/sprites.gd")
const COIN_SLOTS := 5   # dh
const ITEM_SLOTS := 5   # cP

class Drop:
	var x := 0
	var y := 0
	var vx := 0
	var vy := 0
	var age := 0          # dk / cT: 0 = free slot, counts up, 301 = just picked up
	var value := 0        # do / cX
	var size := 0         # dp / cW sprite row
	var kind := 0         # cQ: 0 HP potion, 1 MP potion
	var spin := 0         # dn coin frame
	var px := 0.0
	var py := 0.0

var coins: Array = []
var items: Array = []
var scroll_state := 0   # fK: 0 none, 1 lying, 2.. floating up after pickup
var scroll_x := 0       # fI
var scroll_y := 0       # fJ
var scroll_py := 0.0

func _init() -> void:
	for i in COIN_SLOTS:
		coins.append(Drop.new())
	for i in ITEM_SLOTS:
		items.append(Drop.new())

# original a(int x, int y, int vx, int vy, int value) ~L2036
func spawn_coin(x: int, y: int, vx: int, vy: int, value: int) -> void:
	for c in coins:
		if c.age == 0:
			c.x = x; c.y = y; c.vx = vx; c.vy = vy; c.age = 1; c.value = value
			c.size = 0 if value < 160 else (1 if value < 600 else 2)
			c.px = x; c.py = y
			return

# original a(int x, int y, int vx, int vy, int value, int kind) ~L2062 (vy is always -5)
func spawn_item(x: int, y: int, vx: int, value: int, kind: int) -> void:
	for c in items:
		if c.age == 0:
			c.x = x; c.y = y; c.vx = vx; c.vy = -5; c.age = 1; c.value = value; c.kind = kind
			c.size = 0 if value < 100 else (1 if value < 200 else 2)
			c.px = x; c.py = y
			return

# original e(int x, int y) ~L4654
func spawn_scroll(x: int, y: int) -> void:
	scroll_x = x
	scroll_y = y
	scroll_state = 1
	scroll_py = y

func clear() -> void:
	for c in coins + items:
		c.age = 0
	scroll_state = 0

static func _abs(v: int) -> int:
	return v if v > 0 else -v

func tick(m) -> void:
	_tick_list(m, coins, true)    # W()
	_tick_list(m, items, false)   # ab()
	_tick_scroll(m)

func _picked(m, d) -> bool:
	return _abs(m.fi - (m.fk >> 1) - (d.y - 7)) < 10 and _abs(m.fh - d.x) < 10

func _tick_list(m, list: Array, is_coin: bool) -> void:
	for d in list:
		d.px = d.x
		d.py = d.y
		if d.age == 0:
			continue
		if _picked(m, d):
			if is_coin:
				S.add_money(d.value)
				m.text("+%d" % d.value, d.x, d.y - 15, 1)
			elif d.kind == 0:
				S.G = mini(S.G + d.value, S.H)
				m.text("+%d" % d.value, d.x, d.y - 15, 0)
			else:
				S.I = mini(S.I + d.value, S.J)
				m.text("+%d" % d.value, d.x, d.y - 15, 2)
			S.c = true
			d.age = 301
		d.x += d.vx
		d.y += d.vy
		if d.vx > 1:
			d.vx -= 1
		if d.vx < -1:
			d.vx += 1
		if d.vy < 20:
			d.vy += 1
		d.age += 1
		if d.age > 200:
			d.age = 0
		if is_coin:
			d.spin = 0 if d.age % 20 > 18 else (1 if d.age % 20 > 16 else 2)
		if d.y < 0 or d.y >= m.iS or d.x >= m.iR or d.x < 0:
			d.age = 0
		elif d.vy > 2 and (m.flags(d.x, d.y) & 2) == 2:
			d.y = d.y / 24 * 24
			d.vy = -(d.vy >> 1)

func _tick_scroll(m) -> void:
	scroll_py = scroll_y
	if scroll_state == 0:
		return
	if scroll_state == 1:
		if _abs(m.fi - (m.fk >> 1) - (scroll_y - 7)) < 10 and _abs(m.fh - scroll_x) < 10:
			scroll_state = 2
			S.fD = true
			m.popup("đã lấy được bí kíp")
	else:
		scroll_y -= 3
		scroll_state += 1
		if scroll_state > 40:
			scroll_state = 0

# draw: coins ej[dp] region (0, dn*15, 8, 15) anchor 33 (~L7838); potions ez[cQ] region (0, cW*15, 13, 15)
func draw(ci: CanvasItem, alpha: float, t_sec: float) -> void:
	for d in coins:
		if d.age != 0 and d.age != 301:
			var p := Vector2(lerpf(d.px, d.x, alpha), lerpf(d.py, d.y, alpha))
			SP.draw(ci, "/i/dm%d.png" % d.size, p.x, p.y, 33, false, Color.WHITE, Rect2(0, d.spin * 15, 8, 15))
	for d in items:
		if d.age != 0 and d.age != 301:
			var p := Vector2(lerpf(d.px, d.x, alpha), lerpf(d.py, d.y, alpha))
			var bob := 0.0 if d.vy != 0 else sin(t_sec * 4.0 + d.x) * 0.6
			SP.draw(ci, "/i/pt%d.png" % d.kind, p.x, p.y + bob, 33, false, Color.WHITE, Rect2(0, d.size * 15, 13, 15))
	if scroll_state != 0:
		SP.draw(ci, "/i/bk.png", scroll_x, lerpf(scroll_py, scroll_y, alpha), 33)
