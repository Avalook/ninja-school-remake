# Remappable PC controls -> original J2ME key codes.
# Saved in user://controls.cfg. Defaults follow CLAUDE_TASK: A/D or arrows move, W/Space jump, J attack.
extends Node

const CFG := "user://controls.cfg"
# action -> [label, j2me code, default keys]
const DEFS := {
	"move_left": ["Đi trái", -3, [KEY_A, KEY_LEFT]],
	"move_right": ["Đi phải", -4, [KEY_D, KEY_RIGHT]],
	"jump": ["Nhảy / Lên", -1, [KEY_W, KEY_SPACE, KEY_UP]],
	"down": ["Ngồi / Độn thổ / Xuống", -2, [KEY_S, KEY_DOWN]],
	"attack": ["Đánh / Chọn", -5, [KEY_J, KEY_ENTER, KEY_KP_ENTER]],
	"talk": ["Giao tiếp / Quay lại", -7, [KEY_E, KEY_BACKSPACE]],
	"menu": ["Menu", -6, [KEY_ESCAPE, KEY_TAB]],
	"weapon": ["Đổi kiếm / shuriken (0)", 48, [KEY_K, KEY_0]],
	"jump_left": ["Nhảy chéo trái (1)", 49, [KEY_U, KEY_1]],
	"jump_right": ["Nhảy chéo phải (3)", 51, [KEY_O, KEY_3]],
	"transform": ["Xích Mao (7)", 55, [KEY_L, KEY_7]],
	"stealth": ["Ẩn thân (9)", 57, [KEY_I, KEY_9]],
	"hp_potion": ["Bình HP (*)", 42, [KEY_Q]],
	"mp_potion": ["Bình MP (#)", 35, [KEY_R]],
	"key2": ["Phím 2", 50, [KEY_2]],
	"key4": ["Phím 4", 52, [KEY_4]],
	"key5": ["Phím 5", 53, [KEY_5]],
	"key6": ["Phím 6", 54, [KEY_6]],
	"key8": ["Phím 8", 56, [KEY_8]],
}
const ACTIONS := ["move_left", "move_right", "jump", "down", "attack", "talk", "menu", "weapon", "jump_left", "jump_right", "transform", "stealth", "hp_potion", "mp_potion", "key2", "key4", "key5", "key6", "key8"]

func _ready() -> void:
	ensure_defaults()

func ensure_defaults() -> void:
	if InputMap.has_action("move_left") and InputMap.action_get_events("move_left").size() > 0:
		return
	for a in ACTIONS:
		if not InputMap.has_action(a):
			InputMap.add_action(a)
		InputMap.action_erase_events(a)
		for k in DEFS[a][2]:
			var ev := InputEventKey.new()
			ev.physical_keycode = k
			InputMap.action_add_event(a, ev)
	if not InputMap.has_action("toggle_fullscreen"):
		InputMap.add_action("toggle_fullscreen")
		var f := InputEventKey.new()
		f.physical_keycode = KEY_F11
		InputMap.action_add_event("toggle_fullscreen", f)
		var f2 := InputEventKey.new()
		f2.physical_keycode = KEY_ENTER
		f2.alt_pressed = true
		InputMap.action_add_event("toggle_fullscreen", f2)
	load_cfg()

func code_for(action: String, _core) -> int:
	return DEFS[action][1] if DEFS.has(action) else 0

func load_cfg() -> void:
	var cf := ConfigFile.new()
	if cf.load(CFG) != OK:
		return
	for a in ACTIONS:
		var keys = cf.get_value("keys", a, null)
		if keys is Array and not keys.is_empty():
			InputMap.action_erase_events(a)
			for k in keys:
				var ev := InputEventKey.new()
				ev.physical_keycode = int(k)
				InputMap.action_add_event(a, ev)

func save_cfg() -> void:
	var cf := ConfigFile.new()
	cf.load(CFG)
	for a in ACTIONS:
		var ks := []
		for ev in InputMap.action_get_events(a):
			if ev is InputEventKey:
				ks.append(ev.physical_keycode)
		cf.set_value("keys", a, ks)
	cf.save(CFG)

func rebind(action: String, slot: int, keycode: int) -> void:
	var evs := InputMap.action_get_events(action)
	var ks := []
	for ev in evs:
		if ev is InputEventKey:
			ks.append(ev.physical_keycode)
	# remove this key from all other actions to avoid conflicts
	for a in ACTIONS:
		for ev in InputMap.action_get_events(a):
			if ev is InputEventKey and ev.physical_keycode == keycode:
				InputMap.action_erase_event(a, ev)
	while ks.size() <= slot:
		ks.append(0)
	ks[slot] = keycode
	InputMap.action_erase_events(action)
	for k in ks:
		if k != 0:
			var e := InputEventKey.new()
			e.physical_keycode = k
			InputMap.action_add_event(action, e)
	save_cfg()

func keys_text(action: String) -> String:
	var parts := []
	for ev in InputMap.action_get_events(action):
		if ev is InputEventKey:
			parts.append(OS.get_keycode_string(ev.physical_keycode))
	return ", ".join(parts)
