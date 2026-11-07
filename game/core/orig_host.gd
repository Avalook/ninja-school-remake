# Host layer for the transpiled original game logic (orig.gd / orig_run.gd).
# Implements the J2ME platform surface (images, resources, RecordStore, time, threads)
# that the original bytecode relied on.  Nothing here changes game rules; deliberate
# PC deviations are listed in PC_DEVIATIONS and documented in analysis/claude/.
extends RefCounted

const PC_DEVIATIONS := [
	"SMS/WAP activation & payment flow bypassed (unresolved NS1-UNRES-04): startup goes straight to 'Tiếp tục'.",
	"Logical screen is widened (aW x aX) for 16:9; camera/clamp code of the original uses these values.",
	"Thread-based map loader (Midlet.run) executes synchronously inside the tick.",
]

var _constructed := false
var _img_sizes: Dictionary = {}
var _rms_dir := "user://rms"
var logical_w := 480
var logical_h := 270
var frame_ended := false
var quit_requested := false
var loader_requests := 0
var music_track := -1
var events: Array = []   # host-visible events (music, sounds) for the presentation layer

func _init() -> void:
	var f := FileAccess.open("res://core/imgsizes.json", FileAccess.READ)
	if f:
		_img_sizes = JSON.parse_string(f.get_as_text())
	DirAccess.make_dir_recursive_absolute(_rms_dir)

# ---------- integer semantics (Java int) ----------
func _hx_byte(x) -> int:
	x = int(x) & 255
	return x - 256 if x > 127 else x

func _hx_short(x) -> int:
	x = int(x) & 65535
	return x - 65536 if x > 32767 else x

func _hx_int(x) -> int:
	x = int(x) & 0xFFFFFFFF
	return x - 0x100000000 if x > 0x7FFFFFFF else x

func _hx_div(a, b) -> int:
	if b == 0:
		push_warning("orig: integer division by zero")
		return 0
	# GDScript int division truncates toward zero like Java
	return int(a) / int(b)

func _hx_mod(a, b) -> int:
	if b == 0:
		push_warning("orig: modulo by zero")
		return 0
	return int(a) % int(b)

func _hx_ushr(a, b) -> int:
	return (int(a) & 0xFFFFFFFF) >> (int(b) & 31)

func _hx_lcmp(a, b) -> int:
	return 0 if a == b else (1 if a > b else -1)

func _hx_newarr(dims: Array, fill):
	return _mk(dims, 0, fill)

func _mk(dims: Array, i: int, fill):
	var n: int = int(dims[i])
	var arr := []
	arr.resize(n)
	if i == dims.size() - 1:
		arr.fill(fill)
	else:
		for k in n:
			arr[k] = _mk(dims, i + 1, fill)
	return arr

func _hx_arraycopy(src, sp, dst, dp, n) -> void:
	for k in n:
		dst[dp + k] = src[sp + k]

func _hx_newstring(bytes) -> String:
	if bytes is String:
		return bytes
	var p := PackedByteArray()
	for b in bytes:
		p.append(int(b) & 255)
	return p.get_string_from_utf8()

func _hx_noop():
	return null

func _hx_obj(_n):
	return {}

func _hx_skip(_n):
	return null

func _hx_self():
	if not _constructed:
		_constructed = true
		call("_ctor_host")
	return self

func _hx_now() -> int:
	return Time.get_ticks_msec()

func _hx_prop(_k) -> String:
	return "PC"

func _hx_player(_s, _t):
	return {}

func _hx_quit() -> void:
	quit_requested = true

func _hx_frame_end() -> void:
	frame_ended = true

func _hx_loading_done() -> void:
	pass

func _hx_textbox() -> String:
	return ""

func _hx_cmdlabel(_c) -> String:
	return ""

# ---------- images / fonts ----------
# Images are lightweight refs; the presentation layer maps paths to HD/original textures.
func _hx_img(path, _a = null, _b = null, _c = null, _d = null, _e = null):
	var p := str(path)
	if not _img_sizes.has(p):
		push_warning("orig: missing image " + p)
		return null
	var s: Array = _img_sizes[p]
	return {"p": p, "w": int(s[0]), "h": int(s[1])}

func _hx_iw(img) -> int:
	return 0 if img == null else int(img["w"])

func _hx_ih(img) -> int:
	return 0 if img == null else int(img["h"])

func _hx_font(_a, _b, _c):
	return {"h": 14}

func _hx_fh(_f) -> int:
	return 14

func _hx_sw(_f, s) -> int:
	return str(s).length() * 6

# ---------- resources ----------
func _hx_res(path):
	var f := FileAccess.open("res://orig_res" + str(path), FileAccess.READ)
	if f == null:
		push_error("orig: missing resource " + str(path))
		return {"d": PackedByteArray(), "i": 0}
	return {"d": f.get_buffer(f.get_length()), "i": 0}

func _hx_read(st) -> int:
	if st == null:
		return -1
	var i: int = st["i"]
	var d: PackedByteArray = st["d"]
	if i >= d.size():
		return -1
	st["i"] = i + 1
	return d[i]

func _hx_avail(st) -> int:
	return st["d"].size() - st["i"]

# ---------- RecordStore (original names kept, one file per store) ----------
func _rms_path(name: String) -> String:
	return _rms_dir + "/" + name.uri_encode() + ".rec"

func _rms_get(name: String):
	var f := FileAccess.open(_rms_path(name), FileAccess.READ)
	if f == null:
		return null
	var p := f.get_buffer(f.get_length())
	var out := []
	for b in p:
		out.append(b - 256 if b > 127 else b)
	return out

func _rms_put(name: String, bytes) -> void:
	var p := PackedByteArray()
	for b in bytes:
		p.append(int(b) & 255)
	var f := FileAccess.open(_rms_path(name), FileAccess.WRITE)
	if f:
		f.store_buffer(p)

# a.f(String): read record 1 of "NINJASCHOOL"+name
func f_s(_string):
	return _rms_get("NINJASCHOOL" + str(_string))

# a.a(String, byte[])
func a_sB(_string, _bytes) -> void:
	_rms_put("NINJASCHOOL" + str(_string), _bytes)

# Midlet.b(String) read / Midlet.a(String, byte[]) write (game save "ninjasave")
func M_b_s(_string):
	return _rms_get(str(_string))

func M_a_sB(_string, _bytes) -> void:
	_rms_put(str(_string), _bytes)

# ---------- licensing / platform (bypassed, see PC_DEVIATIONS) ----------
func ae_() -> void:
	pass

func af_() -> void:
	pass

func aA_() -> void:
	pass

func aD_() -> void:
	pass

func az_() -> void:
	pass

func aG_() -> void:
	pass

func ay_() -> void:
	pass

func aB_() -> void:
	pass

func aC_() -> void:
	pass

func aF_() -> void:
	pass

func aH_() -> void:
	pass

func commandAction_oo(_a, _b) -> void:
	pass

func M_c_() -> void:
	quit_requested = true

func M_startApp_() -> void:
	pass

# new Thread(Midlet.c).start(): the original map/asset loader thread (Midlet.run) — run synchronously.
func _hx_start_loader() -> void:
	loader_requests += 1
	call("M_run_")
