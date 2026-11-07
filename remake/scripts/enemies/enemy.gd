# One monster slot of the original monster arrays (a.java: jM jN jO jS ... indexed by n).
# Every field keeps the original integer semantics; the original array name is noted per field.
extends RefCounted

# --- original jS values -----------------------------------------------------------
const DYING := 1      # knocked out, flies off and falls out of the map (al() case 1)
const ACTIVE := 2     # normal behaviour / patrol (al() case 2)
const IDLE := 3       # short pause after an attack, still senses the player (case 3)
const ATTACK := 4     # attacking the player (case 4)
const WAITING := 5    # not spawned, counting down to respawn (case 5)
const HURT := 6       # hit reaction / knockback (case 6)
const REMOVED := 8    # killed on a map with T set: never respawns (case 1 -> 8)
const FALLING := 9    # dropped onto its spawn row (case 9)

var index := 0
var type := -1        # jM  monster kind 0..19 (names: static am[])
var group := 0        # kk  sprite set / spawn-point group (kp[group], km/kn/ko[group])
var x := 0            # jN  feet x (world px)
var y := 0            # jO  feet y
var height := 0       # jP  hit height
var width := 0        # jQ  hit half width / HP bar width
var bar_timer := 0    # jR  HP bar visible while > 0
var state := WAITING  # jS
var min_x := 0        # jT  patrol range (some kinds: home x)
var max_x := 0        # jU  patrol range (some kinds: home y)
var vx := 0           # jV  velocity x (flyers)
var vy := 0           # jW  velocity y (flyers)
var timer := 0        # jX  state timer / respawn counter / hop velocity
var jump := 0         # jY  vertical speed of hoppers / dying fall
var aux := 0          # jZ  bounce counter / hop x speed
var frame := 0        # ka  animation frame 0..3
var dir := 1          # kb  facing (-1, 0, 1); >= 0 uses the right-facing sprite set
var hp := 0           # kc
var max_hp := 0       # kd
var attack := 0       # ke  damage dealt to the player
var aggro_cd := 0     # kf  ticks since last aggro (senses only when > 30)
var sense_w := 0      # kg  sense box width
var sense_h := 0      # kh  sense box half height
var slot := 0         # ki  spawn point index in km/kn[group]
var coin := 0         # kj  coin value base
var hit_companion := false  # kl  the attack targets the companion instead of the player

# --- presentation only (never read by the AI) ---------------------------------------
var prev_x := 0.0
var prev_y := 0.0
var flash := 0.0      # white tint seconds
var squash := 0.0     # squash amount 0..1
var fade := 1.0       # alpha while dying

func load_from(st: Dictionary, n: int) -> void:
	index = n
	type = _i(st, "jM", n)
	group = _i(st, "kk", n)
	x = _i(st, "jN", n)
	y = _i(st, "jO", n)
	height = _i(st, "jP", n)
	width = _i(st, "jQ", n)
	bar_timer = _i(st, "jR", n)
	state = _i(st, "jS", n)
	min_x = _i(st, "jT", n)
	max_x = _i(st, "jU", n)
	vx = _i(st, "jV", n)
	vy = _i(st, "jW", n)
	timer = _i(st, "jX", n)
	jump = _i(st, "jY", n)
	aux = _i(st, "jZ", n)
	frame = _i(st, "ka", n)
	dir = _i(st, "kb", n)
	hp = _i(st, "kc", n)
	max_hp = _i(st, "kd", n)
	attack = _i(st, "ke", n)
	aggro_cd = _i(st, "kf", n)
	sense_w = _i(st, "kg", n)
	sense_h = _i(st, "kh", n)
	slot = _i(st, "ki", n)
	coin = _i(st, "kj", n)
	var kl = st.get("kl")
	hit_companion = bool(kl[n]) if kl is Array and n < kl.size() else false
	snap()

static func _i(st: Dictionary, k: String, n: int) -> int:
	var a = st.get(k)
	if a is Array and n < a.size() and a[n] != null:
		return int(a[n])
	return 0

func snap() -> void:
	prev_x = x
	prev_y = y

func visible() -> bool:
	return state != WAITING and state != REMOVED

func feet() -> Vector2:
	return Vector2(x, y)
