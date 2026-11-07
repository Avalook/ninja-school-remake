# PC settings / pause panel (F10 or F1): key remapping, window mode, music volume, help.
# The original game is paused (no ticks) while this panel is open.
extends CanvasLayer

signal closed

var panel: PanelContainer
var list: VBoxContainer
var waiting_action := ""
var waiting_slot := 0
var buttons := {}
var info: Label
var vol_slider: HSlider

func _ready() -> void:
	layer = 50
	visible = false
	process_mode = Node.PROCESS_MODE_ALWAYS
	var bg := ColorRect.new()
	bg.color = Color(0.02, 0.01, 0.05, 0.78)
	bg.set_anchors_preset(Control.PRESET_FULL_RECT)
	add_child(bg)
	panel = PanelContainer.new()
	var sb := StyleBoxFlat.new()
	sb.bg_color = Color(0.09, 0.06, 0.16, 0.97)
	sb.border_color = Color(0.95, 0.75, 0.3)
	sb.set_border_width_all(3)
	sb.set_corner_radius_all(14)
	sb.content_margin_left = 32
	sb.content_margin_right = 32
	sb.content_margin_top = 24
	sb.content_margin_bottom = 24
	panel.add_theme_stylebox_override("panel", sb)
	panel.set_anchors_preset(Control.PRESET_CENTER)
	add_child(panel)
	var v := VBoxContainer.new()
	v.add_theme_constant_override("separation", 10)
	panel.add_child(v)
	var title := Label.new()
	title.text = "Cài đặt  ·  Ninja School 1"
	title.add_theme_font_size_override("font_size", 34)
	title.add_theme_color_override("font_color", Color(1, 0.85, 0.3))
	v.add_child(title)
	var row := HBoxContainer.new()
	v.add_child(row)
	var fs := Button.new()
	fs.text = "Toàn màn hình / Cửa sổ (F11)"
	fs.pressed.connect(_toggle_fs)
	row.add_child(fs)
	var vl := Label.new()
	vl.text = "   Âm lượng nhạc"
	row.add_child(vl)
	vol_slider = HSlider.new()
	vol_slider.min_value = 0
	vol_slider.max_value = 1
	vol_slider.step = 0.05
	vol_slider.custom_minimum_size = Vector2(220, 0)
	vol_slider.value = db_to_linear(AudioServer.get_bus_volume_db(0))
	vol_slider.value_changed.connect(func(x): AudioServer.set_bus_volume_db(0, linear_to_db(max(x, 0.0001))); _save_audio())
	row.add_child(vol_slider)
	var hint := Label.new()
	hint.text = "Bấm vào ô phím rồi nhấn phím mới để đổi. Esc khi đang chờ = hủy."
	hint.add_theme_color_override("font_color", Color(0.8, 0.8, 0.95))
	v.add_child(hint)
	var scroll := ScrollContainer.new()
	scroll.custom_minimum_size = Vector2(900, 520)
	v.add_child(scroll)
	list = VBoxContainer.new()
	list.size_flags_horizontal = Control.SIZE_EXPAND_FILL
	scroll.add_child(list)
	for a in Controls.ACTIONS:
		var h := HBoxContainer.new()
		var l := Label.new()
		l.text = Controls.DEFS[a][0]
		l.custom_minimum_size = Vector2(380, 0)
		h.add_child(l)
		for slot in 2:
			var b := Button.new()
			b.custom_minimum_size = Vector2(200, 0)
			b.pressed.connect(_begin_rebind.bind(a, slot))
			h.add_child(b)
			buttons[a + str(slot)] = b
		list.add_child(h)
	info = Label.new()
	info.text = "Lưu game: tự lưu khi qua bản đồ (như bản gốc) và qua Menu → Lưu. F10: đóng bảng này."
	info.add_theme_color_override("font_color", Color(0.7, 1, 0.8))
	v.add_child(info)
	var close := Button.new()
	close.text = "Đóng (F10)"
	close.pressed.connect(close_panel)
	v.add_child(close)
	_load_audio()

func open_panel() -> void:
	_refresh()
	visible = true
	panel.reset_size()
	panel.position = (get_viewport().get_visible_rect().size - panel.size) / 2.0

func close_panel() -> void:
	visible = false
	waiting_action = ""
	closed.emit()

func _refresh() -> void:
	for a in Controls.ACTIONS:
		var keys := []
		for ev in InputMap.action_get_events(a):
			if ev is InputEventKey:
				keys.append(OS.get_keycode_string(ev.physical_keycode))
		for slot in 2:
			var b: Button = buttons[a + str(slot)]
			b.text = keys[slot] if slot < keys.size() else "—"

func _begin_rebind(a: String, slot: int) -> void:
	waiting_action = a
	waiting_slot = slot
	buttons[a + str(slot)].text = "... nhấn phím"

func _input(event: InputEvent) -> void:
	if not visible:
		return
	if event is InputEventKey and event.pressed and not event.echo:
		if waiting_action != "":
			if event.physical_keycode != KEY_ESCAPE:
				Controls.rebind(waiting_action, waiting_slot, event.physical_keycode)
			waiting_action = ""
			_refresh()
			get_viewport().set_input_as_handled()
		elif event.physical_keycode == KEY_F10 or event.physical_keycode == KEY_F1:
			close_panel()
			get_viewport().set_input_as_handled()

func _toggle_fs() -> void:
	var w := get_window()
	w.mode = Window.MODE_WINDOWED if w.mode == Window.MODE_FULLSCREEN else Window.MODE_FULLSCREEN

func _save_audio() -> void:
	var cf := ConfigFile.new()
	cf.load(Controls.CFG)
	cf.set_value("audio", "music", vol_slider.value)
	cf.save(Controls.CFG)

func _load_audio() -> void:
	var cf := ConfigFile.new()
	if cf.load(Controls.CFG) == OK:
		var x = cf.get_value("audio", "music", 0.8)
		vol_slider.value = x
		AudioServer.set_bus_volume_db(0, linear_to_db(max(float(x), 0.0001)))
