# STATUS — Ninja School PC remake

Cập nhật: 2026-11-08, phiên M1 `sim/sim.gd`: xong, test xanh (nhánh `m1-sim-step` tách từ `main` 81c3481).
Kế hoạch: `ROADMAP.md`. File này ghi **đã kiểm bằng chạy thật**. Phần nào chỉ lấy lại từ
`analysis/claude/progress.json` mà chưa chạy lại thì có ghi chú rõ.

## Mốc hiện tại
- **M0 (dọn repo)**: xong 5/5. Không dùng LFS (chủ dự án chốt). Oracle emulator dựng lại được từ repo.
- **M1 mục 1 xong**: `player.gd` đã tách thành `remake/sim/player_sim.gd` + `remake/view/player_view.gd`
  (+ `hero_anim.gd` sang `remake/view/`). Hành vi không đổi: trace 10 kịch bản khớp 100% từng dòng.
- **M1 mục 2a (enemies) xong**: `enemy_manager.gd` tách thành `remake/sim/enemies_sim.gd` (+ `sim/enemies/`: quái,
  3 boss, bạn học, projectiles) và `remake/view/enemies_view.gd` (+ `view/sprites.gd`). Hành vi không đổi:
  trace 14 kịch bản khớp 100%, oracle 12 map 500/500 tick, 16 ảnh trùng từng pixel. Kết quả ở bảng bên dưới.
- **M1 mục 2b (story) xong**: `scripts/story/` tách thành `remake/sim/story_sim.gd` (+ `sim/story/`: cutscene,
  quests, items) và `remake/view/story_view.gd` (+ `view/story_menus.gd`). Hộp thoại không còn `await` trong luật.
  Hành vi không đổi: story oracle 77 check, trace 23 kịch bản khớp 100%. Kết quả ở bảng bên dưới.
- **M1 mục 2c (world) xong**: `scripts/world/` tách thành `remake/sim/world_sim.gd` (+ `sim/world/objects.gd`: khối,
  bè, xe máy) và `remake/view/world_view.gd` (+ `view/world/`: `objects_view.gd`, `npcs_view.gd`, `tile_layer.gd`,
  `background.gd`). Hành vi không đổi: trace 27 kịch bản khớp 100%, story 77 check, oracle 12 map. Kết quả ở bảng bên dưới.
- **M1 mục 2d (drops) xong**: `scripts/enemies/drops.gd` → `remake/sim/enemies/drops.gd` (chỉ luật) +
  `remake/view/drops_view.gd` (vẽ). Hành vi không đổi: `drops_trace` mới (phủ nhặt xu/bình/bí kíp) khớp 100%,
  trace 27 kịch bản, story 77 check, oracle 7 map, 28/29 ảnh trùng từng pixel. Kết quả ở bảng bên dưới.
- **M1 mục 2e (projectiles) xong, không sửa code**: rà lại thì không còn gì để tách. Mục M1 "làm tương tự cho
  enemies, story, world, drops, projectiles" đã tick đủ 5 mục con. Hai TODO `hf` hoá ra là luật gốc chưa từng làm,
  đã chuyển thành 2 mục con mới trong M2 Boss. Chi tiết ở "M1 mục 2e" bên dưới.
- **M1 `sim/sim.gd` xong**: các sim nối thẳng qua `remake/sim/sim.gd` (`step(input)` một tick 25 Hz, `frame()`, `snapshot()/restore()`).
  Hành vi không đổi: trace 27, story 77, oracle 12 map, drops khớp 100%; `sim_snapshot.sh` mới 6/6. Kết quả ở bảng bên dưới.
  `sim/` không còn tham chiếu tới view (grep). Mục M1 tiếp theo: `tests/sim_run.gd`.
- **M3 việc nhỏ (2026-11-07, commit thẳng `main` 8451b32)**: `art_src/env/rung_truc/TILE_NEEDS.md` (32 tile id của map 4,
  số lần, cờ theo ô), `tiles_orig_4x/` (tile gốc phóng 4×), `ref_map4.png` (toàn map 4 ghép từ tile gốc, 4×, không có
  trời/nền xa, không phải ảnh chụp emulator). Sinh bằng `tools/art/tile_needs.py 4 rung_truc`. Không đụng `sim/`.
- Đã thêm vào ROADMAP (chưa làm): M1 đồng hồ game cho mọi thứ chạy theo thời gian trong view; M2 Boss kịch bản
  trace trận golem đầy đủ có cutscene mở trận.
- Đã merge `m1-sim-view` vào `main` (2026-11-07). Việc phát hiện trong phiên đã thêm vào ROADMAP:
  M1 (di chuyển native bước cố định 100 Hz), M2 Combat (sửa Xích Mao, cờ thật của Xích Mao, trace A = 18/19/23).
- Codex đang vẽ trong `art_src/` trên nhánh `claude/project-directory-codex-c71a67`.
  Nhánh này **không tạo, không sửa** gì trong `art_src/`.

## M1 `sim/sim.gd`: kế hoạch (viết trước khi sửa code, ĐÃ DUYỆT 2026-11-07 kèm 3 bổ sung; nhánh `m1-sim-step` tách từ `main` 81c3481)

### Hiện trạng (đã đọc code, chưa chạy gì)
- 4 sim đã tách (`player_sim`, `enemies_sim`, `story_sim`, `world_sim` + `world/objects.gd`) nhưng **nối với nhau qua view**:
  `world_sim.player/enemies/story` là các Node view; `player_sim` gọi `world.enemies.hit/launch/ram/spawn_projectile`
  (= `EnemiesView` chuyển tiếp sang sim); `enemies_sim` gọi `world.player.hurt` (= `PlayerView`, view xả event của player
  ngay sau đó); `story_sim.player` là `PlayerView`; `objects.gd` gọi `world.player.carry`.
- Không có hàm "một tick". Thứ tự tick 25 Hz hiện do **thứ tự connect `Game.tick25`** quyết định:
  `objects_view._tick` (connect lúc thêm world) → `story_view.tick` (lúc thêm story) → `main._tick25`
  (projectiles → `player.tick` → monsters/boss/bạn học). `S.w += 1` nằm trong `Game._process`, trước khi emit.
- Mỗi sim tự lọc `Game.mode` theo cách riêng: objects `play`; story `play|cutscene|dialogue`; enemies `play|cutscene`;
  player tick luôn chạy, `is_play()` đọc `Game.mode`.
- Input: `player_sim.raw_key` đọc `Input` trực tiếp, đúng lúc cần (trong tick và trong `frame`).
- Di chuyển native (A = 1–4) chạy **theo frame** (`player_sim.frame(dt, alpha)` trong `PlayerView._process`), không theo tick.
  Mục "100 Hz" trong ROADMAP sẽ đổi chuyện này; mục này **không** đổi.
- Event trình bày: enemies/story/world gửi ngay qua `on_event`; player gom vào `events`, view xả sau mỗi lần gọi.
  Thứ tự gọi `spawn_fx` quan trọng cho ảnh (FX lấy `randf()` theo thứ tự gọi).
- Trong `sim/` không có `randi/randf/Time`: luật tất định, snapshot không cần trạng thái RNG.
- Luật đọc camera (`world_sim.cam_pos/cam_shown/view` do view ghi mỗi frame). Lối ra kiểm mỗi frame trong `WorldView._process`
  và có `await` fade.

### Thiết kế
**`remake/sim/sim.gd`** (RefCounted, mới): giữ và nối trực tiếp 4 sim, không qua view.
- `var world, player, enemies, story` (các sim), `var on_event: Callable`, `var events: Array`.
- **Một bus event**: mọi sim gọi chung một `emit(ev)` của `sim.gd`. Có `on_event` thì giao ngay (giữ thứ tự FX như hiện
  tại), không có (test headless) thì gom vào `events`. `player_sim` đổi `events.append` sang bus, nên view không cần
  xả sau mỗi lần gọi nữa (thứ tự giữ nguyên vì trước nay view cũng xả ngay sau mỗi lần gọi).
- `step(input: Dictionary) -> Array`: **một tick 25 Hz** đúng thứ tự hiện có:
  `S.w = (S.w + 1) % 10000` → objects (nếu `play`) → story (nếu `play|cutscene|dialogue`) → projectiles (nếu `play|cutscene`)
  → `player.tick()` → monsters/boss/bạn học (nếu `play|cutscene`). Trả về event của tick này (cũng đã giao qua `on_event`).
- `input` = `{"mode": Game.mode, "keys": {left, right, jump, down, attack, jump_left, jump_right, weapon, stealth,
  transform, hp, mp}}`. `raw_key` đọc từ `input.keys`. View dựng `input` **đúng chỗ đang đọc `Input` bây giờ**
  (trong `Game._process` cho tick, trong `PlayerView._process` cho frame), nên lúc đọc phím không đổi.
  `Game.mode` vẫn do view/story_view sở hữu (sự kiện `"mode"` của story như cũ); sim chỉ nhận qua input.
- `frame(dt, alpha, input)`: chuyển tiếp `player_sim.frame` (di chuyển native theo frame), giữ tạm tới mục 100 Hz.
- `exit_check()` / `take_exit()`: vẫn gọi từ `WorldView` (vì có fade `await`). Không đưa vào `step` ở mục này:
  đổi từ "mỗi frame" sang "mỗi tick" là đổi hành vi.
- **`snapshot() -> Dictionary` / `restore(d)`**: chụp toàn bộ trạng thái luật dạng dữ liệu thuần (int/bool/String/float/
  Array/Dictionary/Vector2), JSON được:
  - `S`: mọi biến script (giống `S.save_game()` nhưng thêm float/Dictionary, copy sâu).
  - từng sim + object con (Enemy, Shot, Drops, classmates, 3 boss, Objects, cutscene/quests/items): duyệt biến script
    bằng `get_property_list()`, đệ quy vào RefCounted con, **bỏ qua** tham chiếu (`world`, `player`, `enemies`, `story`,
    `data`, `st`, `on_event`, Callable, `_static`/map json nạp lại từ `Data`). Danh sách bỏ qua ghi rõ trong `sim.gd`.
  - Không chụp: view (camera mượt, FX, hộp thoại đang hiện, nhạc), đồng hồ `Game.acc`. Camera mà luật đọc
    (`cam_pos/cam_shown/view` trong `world_sim`) **có** chụp.
  - Save game giữ nguyên `S.save_game()` (mục Save/load ở M2 sẽ dùng snapshot sau).
- **View**: `main.gd` tạo một `Sim`, đưa cho các view; `Game.tick25` chỉ còn `main._tick25` gọi `sim.step(input)`
  (bỏ 3 connect riêng của objects/story/player; `EnemiesView.autotick` bỏ). Các view giữ API cũ cho test/UI
  (`EnemiesView.hit`, `PlayerView.place`...) nhưng chỉ chuyển tiếp. `world_sim.player/enemies/story` và
  `story_sim.player` trỏ sang sim, không sang view. `objects_view` thôi tự tick.

### Các bước (mỗi bước chạy lại bộ test bên dưới, xanh mới sang bước sau)
1. Ghi mốc **bằng code trước refactor**: chạy toàn bộ test + ảnh, lưu vào `review/2026-11-07/m1_sim_step/before/`.
2. Bus event chung (player sang `emit`), chưa đổi thứ tự tick.
3. `sim.gd` + nối trực tiếp các sim; `main._tick25` → `sim.step(input)`; bỏ connect riêng.
4. Input qua `input.keys` / `input.mode`.
5. `snapshot()` / `restore()` + test mới.

### Test chứng minh không đổi hành vi
1. `player_trace.sh` 27 kịch bản khớp 100% từng dòng (trace đã ghi player, quái, boss, bạn học, projectiles, drops,
   world, story mỗi tick, nên bắt được đổi thứ tự tick).
2. `story_run_tests.sh` 77 check, `run_enemies_oracle.sh 500` (12 map), `drops_trace.sh` 4 seed: khớp như trước.
3. Ảnh có cửa sổ `enemies_shots.sh`, `world_shots.sh`, `story_shots.sh`, `drops_shots.sh` trước/sau trùng từng pixel
   (bắt đổi thứ tự FX). Ảnh nào vốn không lặp lại được giữa hai lần chạy cùng code (các phiên trước: 28/29, 41/43 ảnh trùng) thì ghi rõ, so với lần chạy lại của mốc.
4. **Mới `tests/sim_snapshot.sh`** (headless, chạy thẳng `sim.gd`, không view; phím ngẫu nhiên có seed, 3–4 map:
   4 có quái, 9 bạn học, 95 golem, 99 intro có cutscene): chạy N tick → `snapshot()` → chạy tiếp tới M ghi trace;
   `restore()` → chạy lại cùng input tới M → trace phải trùng 100%. Thêm: `snapshot` → JSON → parse → `restore` →
   `snapshot` lần 2 phải bằng lần 1.
5. Độ nhạy: cố ý đổi thứ tự (story chạy sau player) phải làm `player_trace` lệch; bỏ một biến khỏi snapshot phải làm
   test 4 lệch. Hoàn nguyên sau khi thử.

### Rủi ro đã thấy
- Thứ tự connect `tick25` là luật ngầm: gom vào `step` phải giữ đúng objects → story → projectiles → player → actors.
- Nếu các nơi trong view vẫn tự đọc `Input` hay `Game.mode` giữa tick thì sẽ lộ ra ở bước 4; gặp thì ghi lại, không đoán.
- `story_sim._dialogs` giữ trạng thái hộp thoại đang chờ view đóng (`close_dialog(id)`): có chụp, nhưng restore lúc đang
  mở hộp thoại thì view không tự hiện lại hộp. Test snapshot chọn điểm chụp khi đang không có hộp thoại; ghi rõ giới hạn.

### Ngoài phạm vi (để mục sau)
Di chuyển native theo bước cố định (mục 100 Hz), `tests/sim_run.gd` đọc định dạng `diff_run.sh` (mục kế tiếp),
đồng hồ game cho view, save/load mới (M2), lối ra kiểm theo tick.

### Chủ dự án đã duyệt (2026-11-07), thêm 3 yêu cầu
1. `snapshot()/restore()` gồm cả trạng thái di chuyển native của player (`vel`, `coyote`, `buffer`, `air_jumps` và mọi biến
   theo frame mà luật dùng). `sim_snapshot.sh` chạy ở fps cố định.
2. Luật nào đọc camera của view (quái thức dậy trên màn, chết rơi...) thì nhận camera qua `input` của `step()`, camera đó có
   trong snapshot. Hành vi không đổi. Xong mục này `sim/` không còn tham chiếu nào tới `view/` (kiểm bằng grep, ghi kết quả).
3. Sim muốn đổi mode (cutscene, hộp thoại, shop...) thì phát event, view đổi `Game.mode`. Sim không gọi `Game` trực tiếp.

### Câu hỏi đã hỏi (giữ để tra)
1. `step(input)` = **một tick 25 Hz**; di chuyển native vẫn ở `frame(dt, alpha, input)` cho tới mục 100 Hz. Được không?
2. `Game.mode` vẫn thuộc view, sim nhận qua `input.mode` (không chuyển quyền sở hữu mode vào sim ở mục này). Được không?
3. Snapshot chỉ gồm trạng thái luật (`S` + các sim + camera mà luật đọc), không gồm view; save game chưa đổi. Được không?

## M1 `sim/sim.gd`: kết quả (chạy 2026-11-07/08)
Mốc ghi **bằng code trước refactor** (`main` 81c3481, không sửa gì), rồi mới sửa code.

**Phát hiện khi làm (khác với lúc viết kế hoạch)**
- Worktree mới không có cache import của Godot (`.godot/` không nằm trong git): lần chạy mốc đầu, texture và nhạc là null,
  hàng trăm nghìn SCRIPT ERROR ở view (trace luật vẫn khớp). Đã chạy `godot --headless --import` rồi ghi lại mốc. Lần sau,
  worktree mới phải import trước khi chạy test có cửa sổ.
- `Game.mode` đổi **giữa tick** (story phát `"mode"`, `UI.say` đặt `"dialogue"`), và các bước sau trong cùng tick đọc giá trị
  mới. Vì vậy `input.mode` chỉ là giá trị đầu tick: `Game.mode` giờ có setter phát `mode_changed`, `main.gd` nối vào
  `sim.set_mode()`. Story phát `"mode"` thì `sim.gd` cũng tự cập nhật (chạy headless không cần view).
- `S.add_exp()` (trong `scripts/core/state.gd`, không phải `sim/`) gọi thẳng `world.float_text`, `world.spawn_fx`, `UI.message`.
  Đã chuyển thành `EnemiesSim.gain_exp()`, cùng luật, cùng thứ tự; hiệu ứng thành event `text` (có `color`), `fx`, `message`.
- Bản đầu của test snapshot chụp một điểm (tick 400): độ nhạy cho thấy bỏ `coyote/buffer/air_jumps` mà vẫn xanh (giá trị lúc đó
  bằng mặc định). Đã đổi sang chụp mỗi 25 tick. GDScript lambda chụp biến cục bộ theo giá trị (lỗi trong test, đã sửa). JSON làm mất bit cuối của số thực:
  snapshot ghi float/Vector2 kèm bit chính xác (`"#x"`, hex của `var_to_bytes`).
- `on_map_enter(id, frame)`: trước đây `frame` = `Engine.get_process_frames()` của view (chống chạy hai lần khi tín hiệu
  và lời gọi tay rơi vào cùng frame). Lời gọi tay chỉ còn ở nhánh không có world. `sim.gd` truyền số thứ tự lần nạp map.
  Khác duy nhất: nạp **cùng một map hai lần trong một frame** trước đây chạy story một lần, giờ hai lần. Trace 27 kịch bản
  không có trường hợp này.

**Đã làm**
- `remake/sim/sim.gd` (mới): giữ 4 sim, nối thẳng với nhau; `step(input)` = một tick 25 Hz theo đúng thứ tự cũ
  (`S.w`, objects, story, projectiles, player, quái/boss/bạn học/đồ rơi; `S.w` chuyển từ `Game._process` vào đây);
  `frame(dt, input)` cho di chuyển native; `snapshot()` / `restore()`.
- Event: mỗi sim gửi qua `sim.gd` (`ev.src` = world/player/enemies/story) tới handler của view, ngay lúc phát sinh như cũ.
  Player gom event và xả ở cuối mỗi lời gọi từ ngoài (`place/hurt/carry/tick/frame`), đúng như `PlayerView._drain` trước đây.
  `sim.gd` tự xử lý `warp` của story (nạp map), `map_loaded` (gọi `story.on_map_enter` trước, rồi tới view: nhạc), và
  `enemy_killed` → `story.on_enemy_killed` (trước đây đi qua EnemiesView → main → StoryView).
- Camera là input: `world_view` giữ `cam_pos`/`cam_shown` của mình, đưa vào `sim.step()`/`sim.frame()` qua `camera_input()`,
  và đẩy ngay (`_push_camera`) ở các lúc luật cần camera vừa đặt lại (nạp map, `camera_snap`, trước `take_exit`).
- `main.gd`: tạo `Sim` từ sim của các view, `Game.tick25` → `sim.step(input)` là listener duy nhất (objects_view, story_view
  thôi tự tick khi `external_tick`); `player_view` chạy `sim.frame()` mỗi frame.

**Kiểm**
| Kiểm tra | Trước refactor | Sau refactor |
|---|---|---|
| `remake/tests/player_trace.sh` (27 kịch bản) | 27/27 khớp mốc, 0 SCRIPT ERROR | **27/27 khớp 100%, 0 dòng lệch, 0 SCRIPT ERROR** |
| `remake/tests/story_run_tests.sh` | 77 check, 0 fail | **77 check, 0 fail** |
| `remake/tests/run_enemies_oracle.sh 500` (12 map) | 12 map 500/500 tick 100% | **12 map 500/500 tick 100%**, dòng MAP / sát thương giống hệt mốc |
| `remake/tests/drops_trace.sh` | 6000 tick, 0 lệch | **6000 tick, 0 lệch** |
| `remake/tests/sim_snapshot.sh` (mới; headless, 60 fps cố định, 6 kịch bản: map 4 boar, 6 di chuyển đủ phím, 9 bạn học, 95 golem, 1 khối đẩy, intro story 99 → 4; 900 tick, 29–31 điểm chụp mỗi kịch bản) | | **6/6: ở mọi điểm chụp, khôi phục từ JSON vào state và sim mới tinh, 50 tick replay khớp 100% (hash cả snapshot mỗi tick), JSON chụp lại trùng từng byte** (snapshot tới 236 KB) |
| Độ nhạy trace | | đổi thứ tự trong `step()` (story chạy sau player): `m4_combat` lệch 1766 dòng, `story_m0_start` lệch 1956 dòng. Đã hoàn nguyên |
| Độ nhạy snapshot (`--skip=<biến>`: bỏ một biến khỏi snapshot, đếm dòng replay lệch trên 6 kịch bản) | | `player.vel` 415–809, `player.grounded` 178–715, `player.prev_tick_pos` 768–1118, `player.buffer` 2–377 (3/6 kịch bản), `player.air_jumps` 2–27 (3/6), `enemies.enemies` 450–1550, `world.flags` 1420–1550. **Không bắt được**: `player.coyote` (bộ đếm vài phần trăm giây, gần như không bao giờ khác 0 ở điểm chụp cách nhau 25 tick), `story._dialogs` (test chỉ chụp lúc không có hộp thoại mở), `world.cam_pos` (camera là input, mỗi tick được nạp lại). Ba biến này vẫn có trong snapshot (JSON chụp lại trùng từng byte) |
| Ảnh có cửa sổ `enemies/world/story/drops_shots.sh` (56 ảnh, `--fixed-fps 60`) | chạy 2 lần cùng code: 49 ảnh trùng nhau, 7 ảnh vốn lệch (map2_houses 0.6, intro_m99 6.5/9.0, m0_ayumi cả 4) | **49/49 ảnh lặp lại được trùng từng pixel với mốc**; 7 ảnh vốn lệch vẫn lệch, không dùng để kết luận |

Bằng chứng: `review/2026-11-07/m1_sim_step/` (`before/`, `after/`: log từng test; `pixel_compare.txt` so đủ 56 ảnh; `after/shots/`
14 ảnh mẫu, một ảnh mỗi kịch bản; bộ đủ 56 ảnh chỉ giữ local trong `/tmp/claude-501/shots_*`, không commit vì nặng 67 MB).

**`sim/` không còn tham chiếu tới view** (grep 2026-11-08 trên `remake/sim/`):
`grep -rnE 'res://view|res://scripts/(ui|fx|main)|extends Node|get_tree|get_node|\bInput\.|\bUI\.|\bGame\.|\bEngine\.|DisplayServer|Camera2D|CanvasItem|queue_redraw|draw_'`
→ chỉ còn `draw_x/draw_y` (biến luật `hG/hH` của golem) và chữ trong comment (đã sửa comment cho đúng). `world.view` /
`view_size()` trong sim là kích thước camera nhận qua input. Sim vẫn dùng: autoload `S` (biến luật gốc), `Data` (dữ liệu map),
`OS.get_cmdline_user_args()` (cờ `--log` in debug trong `player_sim`). Không còn gọi `Game`, `UI`, `Input`, node nào.

**Còn lại / giới hạn (đã biết)**
- Di chuyển native vẫn theo frame (`frame(dt)`), lối ra vẫn kiểm mỗi frame trong `WorldView` (có fade `await`): đổi sang tick
  là đổi hành vi, để mục 100 Hz / mục sau.
- Restore lúc đang mở hộp thoại: sim có trạng thái hộp (`story._dialogs`), nhưng view không tự hiện lại hộp. Save game vẫn là
  `S.save_game()` cũ (mục Save/load M2 sẽ dùng snapshot).
- Các view vẫn giữ API cũ (chuyển tiếp) cho test và debug.

## M1 mục 2e: projectiles, rà soát (2026-11-07, nhánh `m1-projectiles-split`)
Không sửa `sim/` hay `view/`, nên không có diff test hay ảnh mới. Cách rà (chạy grep trên `remake/`, đọc `a.java` của NinjaSchool1):
- Mọi luật projectiles đã ở `sim/enemies/projectiles.gd` (sinh, bay, trúng quái/bạn học/người chơi/boar/quái sông) từ mục 2a.
  Mọi lời gọi `spawn_projectile`/`shoot_dart`/`lob_seed` đều từ `sim/` (player_sim, classmates, monster_types, 3 boss).
  `view/enemies_view.gd` chỉ chuyển tiếp API cũ và vẽ (xoay phi tiêu `shuriken_spin` là hình). `scripts/` không còn gì về projectiles.
- `TODO(companion hf)` (`sim/enemies_sim.gd:400`, `sim/enemies/projectiles.gd:187`) **không phải phần tách sót**: `hf` là cờ
  trận đấu với đối thủ ở map 96 (`l(n)` ~L4085: `hf = true` khi vào map 96 và chưa xong `eJ`, HP 15000, AI `dG[0]` ~L9991,
  thanh máu ~L7971, kết thúc qua opcode cutscene 25/26). Remake chưa có trận này (chỉ có `S.hf` trong lúc chết hồi máu đối thủ).
- Thấy thêm: AI bạn đồng hành gái (`D()` ~L2681, `v()` ~L2228: đi theo, chọn mục tiêu, ném phi tiêu mạnh `strong = true`) cũng
  chưa có. Trong remake không chỗ nào gọi `spawn_projectile(..., true)`, nhánh `strong` trong projectiles hiện là code chết.
- Cả hai đã thêm vào ROADMAP M2 (mục Boss) kèm số dòng `a.java`. Đây là luật mới, cần oracle/trace riêng, không làm trong M1.
- Còn ghép nối (để mục `sim/sim.gd`): `player_sim` gọi `world.enemies` = `EnemiesView` (Node) rồi view chuyển tiếp sang sim.

## M1 mục 2d: kế hoạch tách drops (viết trước khi sửa code, nhánh `m1-drops-split` tách từ `main` 88c85d0)
Nguồn: `remake/scripts/enemies/drops.gd` (RefCounted, 155 dòng). Luật đã gần như thuần: rơi xu `W()`, bình `ab()`,
bí kíp `fK`, nhặt (cộng `S.dg`/`S.G`/`S.I`, `S.fD`), chữ nổi/popup đã đi qua `m.text`/`m.popup` (event của sim enemies).
Phần còn trộn: `preload("res://view/sprites.gd")` và hàm `draw()` (vẽ xu, bình nhấp nhô theo `t_sec`, bí kíp).
- `git mv` → `remake/sim/enemies/drops.gd`: bỏ preload sprites và `draw()`, giữ nguyên luật và thứ tự. Giữ `px/py`,
  `scroll_py` (trạng thái tick trước để view nội suy, như các sim khác).
- `remake/view/drops_view.gd` (mới): `static func draw(ci, drops, alpha, t_sec)` y hệt `draw()` cũ.
  `view/enemies_view.gd` gọi `DropsView.draw(self, sim.drops, a, _time)` đúng chỗ cũ (giữ thứ tự vẽ).
- `sim/enemies_sim.gd` đổi đường dẫn preload. `main.gd` (trace), `enemies_oracle.gd` không đổi (đọc `enemies.drops`).

**Test chứng minh không đổi hành vi**
1. `remake/tests/drops_trace.sh` (mới): chạy luật drops headless với "người chơi" và nền giả có seed (rơi xu 3 cỡ,
   bình HP/MP, bí kíp; người chơi đi qua nhặt; nảy trên sàn cờ 2; rơi khỏi map; hết hạn 200 tick), ghi mỗi tick mọi
   slot + `S.dg G I fD c` + event. Lý do: 27 kịch bản `player_trace.sh` gần như không có đồ rơi (chỉ 8 tick ở
   `m3_bike`) và **không có lần nhặt nào**.
   Mốc ghi bằng code trước refactor, sau refactor phải khớp 100%.
2. `player_trace.sh` 27 kịch bản, `story_run_tests.sh` 77 check, `run_enemies_oracle.sh 500` vẫn xanh.
3. Ảnh có cửa sổ (`enemies_shots.sh` + kịch bản có xu/bình trên màn) trước/sau trùng từng pixel → `review/2026-11-07/m1_drops_split/`.

## M1 mục 2d: kết quả (chạy 2026-11-07)
Mốc ghi **bằng code trước refactor** (main 88c85d0 + chỉ thêm `drops_trace` và hook `--drops`), rồi mới sửa code.
| Kiểm tra | Trước refactor | Sau refactor |
|---|---|---|
| `remake/tests/drops_trace.sh` (mới): 4 seed × 1500 tick, 89 tick có event nhặt (23 xu nhỏ / 43 HP / 16 MP / 8 bí kíp; chạm trần H, J) | ghi mốc; chạy lại khớp 0 dòng lệch (tất định) | **6000/6000 tick khớp, 0 dòng lệch** |
| Độ nhạy | ngưỡng nhặt dọc `< 10` → `< 11`: 3020 dòng lệch | đã hoàn nguyên |
| `remake/tests/player_trace.sh` (27 kịch bản) | mốc cũ | **27/27 khớp 100%, 0 SCRIPT ERROR** |
| `remake/tests/story_run_tests.sh` | | **77 check, 0 fail** |
| `remake/tests/run_enemies_oracle.sh 500` | | **7 map (4, 1, 6, 7, 12, 95, 97) 500/500 tick**, các trường đồ rơi `dk cT di dj dg` khớp |
| Ảnh có cửa sổ: `drops_shots.sh` (mới, 13 ảnh: xu 3 cỡ, bình HP/MP, bí kíp, cảnh nhặt) + `enemies_shots.sh` 16 | chụp | **28/29 trùng từng pixel**. Lệch: `map4_boar/shot_2.6` vùng 22×23 px = mũi tên "tiếp" hộp thoại nhấp nháy theo giờ thật (đã biết, xem "Chưa rõ") |

Bằng chứng: `review/2026-11-07/m1_drops_split/` (4 ảnh sau refactor có đồ rơi và cảnh nhặt, 2 ảnh cắt vùng lệch,
`pixel_compare.txt`, `drops_trace_check.txt`, `player_trace_check.txt`, `story_tests_after.txt`, `enemies_oracle_after.txt`).
Bộ ảnh trước/sau đầy đủ (≈51 MB) không commit; sinh lại bằng `tests/drops_shots.sh <dir>` và `tests/enemies_shots.sh <dir>`.

Đã làm đúng kế hoạch. Ghi chú:
- Hook test mới `main.gd --drops` (chỉ chạy khi có cờ, cùng `--map`): rải 3 xu, 2 bình, 1 bí kíp cạnh người chơi.
- Vị trí `--map=6,5976,240` (dùng trong `enemies_shots.sh`) nằm trên lối ra: người chơi sang map 7 ngay tick đầu.
  Ảnh `map6_monsters` thật ra là map 7. Không sửa trong phiên này (đổi ảnh mốc của bộ cũ).
- Bình nhấp nhô (`sin(t_sec ...)`) vẫn theo `_time` của view (dt frame); thuộc mục M1 "đồng hồ game".

## M1 mục 2c: kế hoạch tách world (viết trước khi sửa code, nhánh `m1-world-split` tách từ `main` dc96ae7)
Nguồn: `remake/scripts/world/` — `world.gd` (Node2D, 308 dòng: nạp map, cờ ô, tấm ván lún 0x800/0x400, lối ra,
camera, lớp vẽ, FX), `objects.gd` (Node2D: khối đẩy/bè it..iB, xe máy il..is, vừa luật 25 Hz vừa vẽ),
`npcs.gd` (chỉ vẽ NPC và mũi tên mục tiêu, không có luật), `tile_layer.gd`, `background.gd` (chỉ vẽ).

**`remake/sim/world_sim.gd`** (`git mv` từ `world.gd`; RefCounted, không Node, không vẽ, không `await`):
- Luật: `load_map` (phần luật: Data map, `S.v/iP/iQ/iR/iS/W`, `_apply_map_state` + regex MAP_VAR, gọi
  `objects.setup`, `player.place`, `enemies.setup`), `flags_at`, `flag_tile`, `tile_id`, `is_floor`, `world_size`,
  `set_flag_tile`, `crumble_at`/`_mark`, và luật lối ra (`exit_check`: chọn lối jr/js/jt/ju, chặn map 95 khi
  chưa có ẩn thân `S.fc` kèm đẩy lùi + popup, `take_exit`: đặt `S.fh/fi`, nạp map, `S.save_game()`).
- `sim/world/objects.gd` (`git mv` từ `objects.gd`): luật khối/bè/xe (`setup`, `tick`, `block_at_side`, `push`,
  `land_block`, `hit_blocks`, snapshot `prev_b/prev_v` của tick trước để view nội suy). Bỏ `_draw`/texture.
- Thứ tự trong `load_map` giữ y nguyên. Chỗ cần view ở giữa (đặt lại camera sau `enemies.setup`, phát
  `map_loaded` cuối cùng) thành event gửi **ngay lúc phát sinh** qua `sim.on_event` (như sim enemies/story):
  `camera_reset`, `map_loaded`, `fx` (bè rơi xuống nước), `popup` (chặn map 95).
- Sim không đọc `Game`/`UI`/viewport: view truyền `Game.mode == "play"` vào `objects.tick`, truyền trạng thái chết
  của hình nhân vật vào `exit_check`, và ghi vào sim mỗi frame `cam_pos` (camera mượt), `cam_shown`
  (`camera.position`, có rung) và kích thước khung nhìn. Đây là đúng giá trị luật đang đọc hôm nay.
- Còn giữ (để mục `sim/sim.gd` xử lý): `world_sim.player` / `.enemies` vẫn là node view của player/enemies
  (các view đó xả event ngay sau mỗi lần gọi, đổi sang sim trực tiếp sẽ đổi thứ tự FX); `world_sim.story` thay
  `world.get_parent().story` trong `player_sim._u()`.

**`remake/view/world_view.gd`** (Node2D, thay `World` trong `main.gd`, giữ API cũ cho main/test/view khác:
`load_map`, `map_id`, `map`, `w`, `h`, `tiles`, `flags`, `exits`, `player`, `enemies`, `objects`, `camera`,
`cam_pos`, `layer_actors`, `flags_at`, `flag_tile`, `tile_id`, `is_floor`, `view_size`, `camera_snap`,
`camera_override`, `set_flag_tile`, `spawn_fx`, `float_text`, `screen_shake`, signal `map_loaded`, nhóm "world"):
- Camera (lead, lò xo `_damp`, rung, override), lớp vẽ, nhà trang trí map 2/16, FX, chuyển map có fade (gọi
  `exit_check` mỗi frame như cũ, fade, `take_exit`).
- `view/world/objects_view.gd`: vẽ khối/bè/xe (code vẽ cũ), nối `Game.tick25` đúng chỗ cũ (trong `_ready` của
  world) để thứ tự tick 25 Hz không đổi. `draw_bike` giữ trên node này cho `player_view`.
- `git mv`: `npcs.gd` → `view/world/npcs_view.gd`, `tile_layer.gd`, `background.gd` → `view/world/` (không đổi code).
- Các sim (`player_sim`, `enemies_sim`, `story_sim`) đọc `world_sim`; các view vẫn giữ node world view cho FX/vẽ.
  World giả của test (`tests/story_stubs.gd`) không có `sim` nên vẫn dùng thẳng như cũ.

**Test chứng minh không đổi hành vi** (ghi mốc bằng code trước refactor)
1. `remake/tests/player_trace.sh`: dòng trace thêm `W=` (map_id, w, h, hash mảng cờ ô, khối it/iv/iw/ix/iy/iz/iA/iB/iu,
   xe il/im/in/io/ip/iq/ir/is, `cam_pos`). Thêm kịch bản: map 1 đẩy khối, map 97 bè, map 2 có nhà trang trí, và
   kịch bản đi qua lối ra. Mốc 23 kịch bản cũ ghi lại vì thêm trường; phần trường cũ phải giống mốc cũ từng dòng.
2. `remake/tests/story_run_tests.sh` vẫn 77 check, 0 fail; `run_enemies_oracle.sh 500` 12 map như trước.
3. Ảnh có cửa sổ (`enemies_shots.sh`, `story_shots.sh`, thêm `world_shots.sh`: khối map 1, xe map 3, nhà map 2)
   trước/sau so từng pixel → `review/2026-11-07/m1_world_split/`.

## M1 mục 2c: kết quả (chạy 2026-11-07)
Mốc ghi **bằng code trước refactor** (main dc96ae7 + chỉ thêm trường trace `W=` và 4 kịch bản), rồi mới sửa code.
| Kiểm tra | Trước refactor | Sau refactor |
|---|---|---|
| `remake/tests/player_trace.sh`: 23 kịch bản cũ, dòng trace thêm `W=` (map, hash cờ ô, khối/bè, xe, `cam_pos`) | ghi lại mốc; phần trường cũ giống mốc cũ 23/23, 0 dòng lệch | **23/23 khớp 100%** |
| `remake/tests/player_trace.sh`: 4 kịch bản world mới (map 1 đẩy khối, map 1 ván lún, map 2 qua lối ra sang map 0, map 97 bè) | ghi mốc (khối 13 trạng thái, bè 391, ván 33 trạng thái cờ) | **4/4 khớp 100%, 0 SCRIPT ERROR** |
| Độ nhạy | bè `S.w % 2` → `% 3`: w_m97_rafts lệch 1478 dòng; ván lún `0x800` → `0x400`: w_m1_planks lệch 1740 dòng | đã hoàn nguyên |
| `remake/tests/story_run_tests.sh` | 77 check, 0 fail | **77 check, 0 fail** |
| `remake/tests/run_enemies_oracle.sh 500` (12 map) | | **12 map 500/500 tick**, giống hệt lần chạy phiên story |
| Ảnh có cửa sổ: `enemies_shots.sh` 16 + `story_shots.sh` 13 + `world_shots.sh` (mới) 14 | | **41/43 trùng từng pixel** lần đầu; chạy lại 2 lần: chỉ còn `map2_houses/shot_0.6` lệch đúng vùng NPC đứng thở (2 lần chạy code mới cũng lệch nhau ở đó) |

Bằng chứng: `review/2026-11-07/m1_world_split/` (4 ảnh sau refactor, ảnh trước/sau + mặt nạ lệch map 2, `pixel_compare.txt`,
`player_trace_check.txt`, `old_fields_vs_old_baseline.txt`, `enemies_oracle_after.txt`, `story_tests_after.txt`).

Đã làm đúng kế hoạch. Ghi chú:
- `world_sim.player` / `.enemies` vẫn là node view của player/enemies (view xả event ngay sau mỗi lần gọi). Bỏ khi làm `sim/sim.gd`.
- `player_sim._u()` đọc `world.story` (main gán) thay cho `world.get_parent().story`; `_st_dead` đọc `world.cam_shown`
  (đúng `camera.position` cũ, có rung).
- Worktree mới chưa có `remake/.godot/` (bị ignore) thì texture/nhạc là null: trace vẫn khớp nhưng log có hàng trăm nghìn
  SCRIPT ERROR ở hàm vẽ. Chạy `godot --headless --path remake --import` một lần trước khi chạy test.
- Ảnh đầu tiên sau khi mở cửa sổ đôi khi là khung đen (gặp 1 lần ở map 97, chạy lại không lặp).

## M1 mục 2b: kế hoạch tách story (viết trước khi sửa code, nhánh `m1-story-split` tách từ `main` 46572ae)
Nguồn: `remake/scripts/story/` — `story.gd` (Node, 551 dòng: map entry m/f/g/n, tick 25 Hz, dialog, warp, title
flow), `cutscene_player.gd` (D/C, ad(), V(), camera fL/fM/fR/fS), `npc_quests.gd` (talk switch, z(), L(n), u(),
nhặt bí kíp), `menus.gd` (vòng `UI.choose` + luật bình/bản đồ/shop). Hộp thoại đang chạy bằng `await ui.say()`
ngay trong luật.

**`remake/sim/story_sim.gd`** (`git mv` từ `story.gd`; RefCounted, không Node, không UI, không `await`):
- Luật: `on_map_enter` (+ `_load_map_story_state`, `_m`, `_f`, `_g_variants`, `_n`), `tick()` (ad() → V() →
  player control → quests → camera), `talk_target`, `talk(i)`, `talk_classmate`, `talk_girl`, `notice`,
  `set_marker`, `clear_barrier`, `reset_state`, `new_game`, `continue_game`.
- `sim/story/cutscene.gd` (`git mv` từ `cutscene_player.gd`) và `sim/story/quests.gd` (`git mv` từ `npc_quests.gd`).
- Luật trong `menus.gd` (dùng bình, bình nhanh, vị trí hiện tại, giá chuyển map, kiểm tra chuyển, chuyển map, mua)
  chuyển sang `sim/story/items.gd`. Phần còn lại của `menus.gd` (vòng chọn, nhãn, popup, màn tiêu đề, chỉ dẫn)
  thành `view/story_menus.gd` (`git mv`).
- **Hộp thoại không `await` trong sim nữa:** sim đặt `iC/iG` (hoặc `gd` cho cutscene), phát event `say` kèm mã
  hộp thoại; view chạy `UI.say`, xong thì gọi `sim.close_dialog(mã)` → sim chạy z()/kết thúc thoại cutscene.
  Hàm `close_dialog` chạy đúng chỗ `await` cũ trả về nên thứ tự không đổi. Mã cutscene giữ cơ chế token (`abort`).
- Mọi chỗ sim gọi UI/Game/World-camera/signal thành event gửi **ngay lúc phát sinh** qua `sim.on_event`
  (như sim enemies): `say`, `title`, `hide_title`, `notice`, `marker`, `scroll`, `weapon`, `changed`
  (story_state_changed + `S.changed`), `cutscene_started/finished`, `to_title`, `mode`, `warp`, `camera`,
  `camera_snap`. `warp` chạy đồng bộ trong view nên `on_map_enter` lồng vào vẫn đúng thời điểm cũ.
- Sim không gọi `get_tree()`/`Engine`: view truyền số frame cho chống gọi đôi `on_map_enter`, và truyền Data.
- Còn giữ (để mục World xử lý): sim vẫn đọc/ghi `world` (map json, `set_flag_tile` khi mở rào) và `player`
  (pos/facing/`is_standing`/`place`/`scripted`/`script_keys`) như `player_sim.gd`, `enemies_sim.gd`.

**`remake/view/story_view.gd`** (Node, thay `Story` trong `main.gd`, giữ nguyên API cũ cho main/test/player_sim:
`st`, `ui`, `world`, `attach`, `tick`, `events`, `notice_text`, `quests`, `cutscene`, `menus`, `is_busy`,
`is_cutscene_active`, `talk_label`, `interact`, `on_enemy_killed`, `on_player_attack`, `game_menu`, `open_shop`,
`title_menu`, `quick_potion`, `new_game`, `continue_game`, các signal):
- Nối `Game.tick25` và `world.map_loaded` như cũ (cùng thứ tự), đọc phím (`handle_input`), xử lý event
  (UI.say/title_card/notice, Game.mode, goto_map, camera_override), phát lại signal cùng tên.

**Test chứng minh không đổi hành vi** (ghi mốc bằng code trước refactor)
1. `remake/tests/player_trace.sh`: dòng trace thêm `ST=` (biến cutscene/camera/quest/marker/cô bé/cờ truyện,
   `Game.mode`) và `SEV=` (dòng log story mới trong tick). Thêm 7 kịch bản story có `--autoskip`: new game
   intro (map 99 → 4 → 0), map 0 ba giai đoạn quest (nói chuyện NPC), map 97 cutscene sông, map 9 quest 10,
   map 3 quest 3. Mốc 14 kịch bản cũ ghi lại vì thêm trường; phần trường cũ phải giống mốc cũ từng dòng.
2. `remake/tests/story_run_tests.sh` (so với log oracle game gốc) vẫn 77 check, 0 fail.
3. `remake/tests/run_enemies_oracle.sh 500 …` 12 map và `tests/enemies_shots.sh` 16 ảnh vẫn như trước.
4. Ảnh có cửa sổ cutscene intro (title card, hộp thoại) trước/sau → `review/2026-11-07/m1_story_split/`.

## M1 mục 2b: kết quả (chạy 2026-11-07)
Mốc ghi **bằng code trước refactor** (main 46572ae + chỉ thêm hook test), rồi mới sửa code.
| Kiểm tra | Trước refactor | Sau refactor |
|---|---|---|
| `remake/tests/story_run_tests.sh` (so với log oracle game gốc) | 77 check, 0 fail | **77 check, 0 fail** |
| `remake/tests/player_trace.sh`: 14 kịch bản cũ, dòng trace thêm `ST=`/`SEV=` | ghi lại mốc; phần trường cũ giống mốc cũ 14/14, 0 dòng lệch | **14/14 khớp 100%** |
| `remake/tests/player_trace.sh`: 9 kịch bản story mới (`--autoskip`; intro 99→4→0, map 0 ba giai đoạn, nói chuyện Ayumi/Takanashi nhận quest, cutscene sông 97, quest 10 map 9, quest 3 map 3) | ghi mốc; chạy lại lần 2 khớp 100% (tất định) | **9/9 khớp 100%, 0 SCRIPT ERROR** |
| Độ nhạy | đổi luật notice `cq >= 150` → `151`: story_m0_ayumi lệch 1024 dòng | đã hoàn nguyên |
| `remake/tests/run_enemies_oracle.sh 500` (12 map) | | **12 map 500/500 tick**, output giống hệt lần trước |
| `remake/tests/story_shots.sh` (mới; có cửa sổ, 3 cảnh × 4–5 ảnh, `--fixed-fps 60 --seed=1`) | 2 lần chạy code cũ: 6/13 ảnh lệch ở NPC/hero đứng thở | **7/13 ảnh trùng từng pixel; 6 ảnh lệch đúng vùng mà code cũ chạy 2 lần cũng lệch** (ngoài vùng đó: 1 pixel ở mép sprite) |

Bằng chứng: `review/2026-11-07/m1_story_split/` (3 ảnh sau refactor, ảnh trước/sau + mặt nạ lệch map 0,
`pixel_compare.txt`, `player_trace_check.txt`, `enemies_oracle_after.txt`). Mốc trace: `remake/tests/player_trace/expected/`.
`tools/diff_suite.sh` (JAR so với `game/`) không chạy lại: phiên này không đụng `game/` hay emulator.

Đã làm đúng kế hoạch. Ghi chú:
- Ảnh có cửa sổ lệch giữa các lần chạy vì `scripts/world/npcs.gd:51,79` (NPC đứng thở, dấu "!") chạy theo
  `Time.get_ticks_msec()`. Thuộc mục M1 mới "đồng hồ game cho view".
- Mã hộp thoại: `sim.say()` trả id, view gọi `sim.close_dialog(id)` khi `UI.say` xong. Cutscene giữ token nên
  hộp thoại cũ sau `abort()` (new game/title) bị bỏ qua như trước.
- Test gọi luật menu qua `story.items.*` (trước là `story.menus.*`).
- Kịch bản `story_m3_q3` có A = 18/19 (xe máy đứng/chạy), nên mục "trace phủ xe máy" ở M2 đã có một phần (chưa có A = 23).

## M1 mục 2a: kế hoạch tách enemies (viết trước khi sửa code, nhánh `m1-enemies-split` tách từ `main` 6629201)
Nguồn: `remake/scripts/enemies/` — `enemy_manager.gd` (Node2D, 535 dòng) điều phối `enemy.gd`, `monster_types.gd`,
`boss_boar.gd`, `boss_river.gd`, `boss_golem.gd`, `classmates.gd`, `projectiles.gd` (RefCounted nhưng có hàm
`draw` và biến chỉ để vẽ), `drops.gd`, `sprites.gd`. Projectiles đi chung vì vòng lặp phi tiêu trúng quái, bạn
học, boss và player trong cùng một tick (`_fly_shuriken`), không tách rời được.

**`remake/sim/enemies_sim.gd`** (`git mv` từ `enemy_manager.gd`; RefCounted, không Node, không vẽ):
- Toàn bộ luật: `setup`, `step_projectiles`/`step_actors` (đúng thứ tự gốc), `_update_monsters`, respawn,
  `_dying`, sense J/K, `hit` (u()), `damage_monster` (k()), `kill` (L(): EXP, `w % 20`, bí kíp), đồng bộ biến boss
  với S (`SYNC`), `boss_state`, `start_golem`, `alive_count`, `flags`.
- Các module con chuyển vào `remake/sim/enemies/` (`git mv`, giữ blame): `enemy.gd`, `monster_types.gd`,
  `boss_boar.gd`, `boss_river.gd`, `boss_golem.gd`, `classmates.gd`, `projectiles.gd`. Bỏ hàm `draw` và biến chỉ để
  vẽ (`flash`, `squash`, `fade`, `spin`).
- Hiệu ứng không gọi thẳng nữa: `fx`, `text`, `popup` và lúc bị đánh (`on_damaged`, trước đây đặt `flash`/`squash`)
  thành event trong `sim.events` (`fx`, `text`, `popup`, `flash`). View xả event ngay sau mỗi lần gọi sim.
- Signal `enemy_killed`, `classmate_killed`, `boss_killed`, `player_hit` giữ là signal của sim (gọi đồng bộ, story
  nhận đúng thời điểm như cũ); view phát lại cùng tên.
- Biến `prev_x/prev_y`, `px/py` (vị trí tick trước) vẫn ở sim: đó là snapshot tick trước mà view nội suy.
- Còn giữ (để mục World xử lý): sim vẫn đọc `world` cho `flags_at`, `cam_pos`, `view_size`, `player.hurt`,
  `companion.hurt`, như `player_sim.gd`.
- `drops.gd` để nguyên chỗ cũ (`scripts/enemies/drops.gd`, mục Drops riêng): sim vẫn gọi `drops.tick`, view gọi
  `drops.draw`. `drops.gd` gọi `m.text`/`m.popup` nên tự động thành event.

**`remake/view/enemies_view.gd`** (Node2D, thay `EnemyManager` trong `main.gd`; giữ nguyên API cũ cho main/world/
player_sim: `setup`, `step_projectiles`, `step_actors`, `hit`, `spawn_projectile`, `enemies`, `boss_state`,
`start_golem`, `alive_count`, `damage_monster`, `world`, `test_player`, `autotick`, các signal):
- Vẽ quái, 3 boss, bạn học, projectiles (chuyển từ các hàm `draw` cũ, code vẽ giữ nguyên), gọi `drops.draw`.
- Giữ thời gian nháy trắng/méo theo event `flash` (trước đây nằm trong record của sim).
- `sprites.gd` → `remake/view/sprites.gd` (chỉ là cache texture + vẽ kiểu J2ME).

**Test chứng minh không đổi hành vi** (ghi mốc trước khi sửa code)
1. `remake/tests/player_trace.sh`: mở rộng dòng trace (`main.gd --trace`) thêm boss (gQ/gV/gS/gT/hc, hM/ia/hS/hT/hP,
   hl/hu/hs/ht/hp), bạn học (cx cy cH cu), phi tiêu/ám khí/hạt, xu/bình. Thêm kịch bản map 9 (bạn học), map 97
   (quái sông), map 4 gần boar. Ghi lại mốc **bằng code trước refactor**; sau refactor phải khớp 100%.
2. `remake/tests/run_enemies_oracle.sh 500` thêm map 9, 10, 11, 16, 17 (bạn học) vào danh sách chạy trước/sau.
3. `remake/tests/story_run_tests.sh` vẫn 77 check, 0 fail. `enemies_oracle.gd` đổi sang chạy thẳng sim (không Node).
4. Ảnh có cửa sổ map 4 (boar), 97 (quái sông), 9 (bạn học), trước/sau phải trùng từng pixel → `review/2026-11-07/m1_enemies_split/`.

## M1 mục 2a: kết quả (chạy 2026-11-07)
Mốc ghi **bằng code trước refactor** (main 6629201 + chỉ thêm hook test), rồi mới sửa code.
| Kiểm tra | Trước refactor | Sau refactor |
|---|---|---|
| `remake/tests/player_trace.sh` (14 kịch bản, 12.643 tick; thêm m9 bạn học, m4 boar, m97 quái sông, m95 golem) | ghi mốc | **14/14 khớp 100%, 0 dòng lệch, 0 SCRIPT ERROR** |
| `remake/tests/run_enemies_oracle.sh 500 4 1 6 7 9 10 11 12 16 17 95 97` (thêm 5 map bạn học) | 12 map 500/500 tick 100% | **12 map 500/500 tick 100%**, output giống hệt mốc từng dòng, sát thương lên player khớp |
| `remake/tests/story_run_tests.sh` | | **77 check, 0 fail** |
| `remake/tests/enemies_shots.sh` (mới; có cửa sổ, 4 map × 4 ảnh, `--fixed-fps 60 --seed=1`) | 2 lần chạy trùng nhau 16/16 | **16/16 ảnh trùng từng pixel với trước** |
| Độ nhạy | đổi luật bạn học (`timer > 50` → `> 51`): trace m9 lệch 1926 dòng; đổi bóng boar `py+1` → `py+2`: 4/4 ảnh map 4 lệch | đã hoàn nguyên |
| Boot new game có cửa sổ (`--newgame --autoskip --quit=7`) | | 0 SCRIPT ERROR |

Bằng chứng: `review/2026-11-07/m1_enemies_split/` (4 ảnh sau refactor, `pixel_compare.txt`, `player_trace_check.txt`,
`enemies_oracle_before.txt` / `_after.txt`). Mốc trace mới: `remake/tests/player_trace/expected/*.txt.gz`.
`tools/diff_suite.sh` (JAR so với `game/`) không chạy lại: phiên này không đụng `game/` hay emulator.

Đã làm đúng kế hoạch, thêm hai điểm:
- Event của sim enemies đi qua `sim.on_event` (Callable) **ngay lúc phát sinh**, không gom cuối tick: fx_layer lấy
  `randf()` theo thứ tự gọi `spawn_fx`, gom cuối tick sẽ đổi thứ tự với FX của player (vd. quái trúng phi tiêu
  rồi phi tiêu khác trúng player trong cùng tick) → tia lửa vẽ khác. Không có view (test headless) thì vào `sim.events`.
- Hook test mới (chỉ chạy khi có cờ): `main.gd --trace` ghi thêm boss (`B=`), bạn học (`C=`), projectiles (`P=`),
  xu/bình (`D=`), bí kíp (`K=`), nên mốc trace 10 kịch bản cũ được ghi lại (bằng code trước refactor);
  `main.gd --seed=N` seed RNG cho ảnh lặp lại được; `player_trace.sh` nhận `ONLY=<regex>`.
- `tests/enemies_oracle.gd` giờ chạy thẳng `sim/enemies_sim.gd` (RefCounted, không Node): sim chạy headless được.

## M1 mục 1: kế hoạch tách `player.gd` (viết trước khi sửa code, nhánh `m1-sim-view`)
Nguồn: `remake/scripts/player/player.gd` (1065 dòng, Node2D vừa luật vừa vẽ).

**`remake/sim/player_sim.gd`** (RefCounted, không Node, không vẽ, không gọi Input/UI/Game):
- Toàn bộ luật: `place`, `hurt` (a(n,dir)), `hitbox`, `attack_box`, `is_standing`, `is_native`, `carry`,
  di chuyển native `_native`/`_jump`/`_land`/`_mount_bike`/`_common_actions`/`_publish`,
  bước 25 Hz `tick` (timer MP, fq, `_npc_check`, `_crumble_tick`) và mọi state gốc
  `_st_attack`, `_u`, `_st_dead`, `_st_hurt`, `_st_crouch`, `_st_swim`, `_st_sink`, `_st_underwater`,
  `_st_transform`, `_st_bike` (+ `_bike_*`), đào 13–15.
- Input không đọc trực tiếp: view tiêm `raw_key: Callable(k) -> bool` và `is_play: Callable() -> bool`.
  Gọi trực tiếp lúc cần (không chụp snapshot) để giữ đúng thời điểm đọc phím như cũ.
- Hiệu ứng không gọi thẳng: `spawn_fx`, `float_text`, `screen_shake`, `Game.hitstop`, `UI.message`,
  `S.changed.emit()` đổi thành event trong `sim.events` (`fx`, `text`, `shake`, `hitstop`, `message`,
  `changed`, `run_dust`). Bụi khi chạy (`randf() < 0.25`, chỉ là hình) chuyển sang view.
- Còn giữ (ghi rõ để các mục M1 sau xử lý): sim vẫn gọi `world` (Node) cho va chạm/flags, objects,
  enemies, story (`on_player_attack`), và camera trong `_st_dead` (luật gốc dùng mép màn hình).
  Di chuyển native vẫn chạy theo dt từng frame (`frame(dt, alpha)`), chưa về tick 25 Hz: đổi sang tick
  là đổi hành vi, để mục `sim/sim.gd step(input)`.

**`remake/view/player_view.gd`** (Node2D, thay `player.gd`, giữ nguyên API cũ cho main/story/world/objects/
enemies/cutscene: `pos`, `vel`, `facing`, `grounded`, `scripted`, `script_keys`, `near_npc`, `state`,
`place()`, `hurt()`, `tick()`, `carry()`, `is_standing()`... đều chuyển tiếp sang sim):
- `_process`: gọi `sim.frame(dt, Game.acc/0.04)`, xả event (gọi world/UI/Game ngay sau mỗi lần gọi sim
  để giữ thứ tự), tên state animation `_state_name`, `anim.update`, `_draw` (xe máy + hero).
- `hero_anim.gd` → `remake/view/hero_anim.gd` (chỉ là phần nhìn).

**Test chứng minh không đổi hành vi** (kết quả ở bảng "M1 mục 1: kết quả" bên dưới)
1. `remake/tests/player_trace.sh` (mới): 10 kịch bản headless `--fixed-fps 60`, phím ngẫu nhiên có seed,
   ghi mỗi tick toàn bộ biến luật của player (A B C D fh fi fp y z G I N M fw fv fy fu fq fz fA fg ff im iu
   V O fF), `pos`/`vel` float 4 số lẻ, `grounded`, `near_npc`, và mọi slot quái (type, x, y, jS, kc).
   Mốc ghi **trước** khi sửa: `remake/tests/player_trace/expected/`. Sau refactor phải khớp 100% từng dòng.
   Đã kiểm chạy 2 lần liên tiếp cho kết quả giống hệt (tất định).
2. `remake/tests/story_run_tests.sh` và `remake/tests/run_enemies_oracle.sh 500` vẫn xanh (2 bộ này dùng
   player giả, chỉ chứng minh không làm hỏng story/enemies).
3. `tools/diff_suite.sh` (JAR so với `game/`): mốc 28 bài, không đụng tới, chạy lại để chắc không lệch.
4. Chạy có cửa sổ, chụp ảnh `review/2026-11-07/` để xem view vẫn vẽ như cũ.

## M1 mục 1: kết quả (chạy 2026-11-07)
| Kiểm tra | Trước refactor | Sau refactor |
|---|---|---|
| `remake/tests/player_trace.sh` (10 kịch bản, ~9.500 tick) | ghi mốc; chạy lại lần 2 khớp 100% (tất định) | **10/10 khớp 100%, 0 dòng lệch, 0 SCRIPT ERROR** |
| Độ nhạy của trace test | đổi thử thời gian bị đánh 7 → 8 tick | 8/10 kịch bản báo lệch (đã hoàn nguyên) |
| `remake/tests/story_run_tests.sh` | 77 check, 0 fail | **77 check, 0 fail** |
| `remake/tests/run_enemies_oracle.sh 500` | 7 map 500/500 tick 100% | **7 map 500/500 tick 100%**, sát thương lên player khớp |
| Ảnh có cửa sổ map 4 (3 thời điểm, `--fixed-fps 60`) | chụp | **trùng từng pixel với trước** |
| Boot new game có cửa sổ (`--newgame --autoskip --quit=7`) | | 0 SCRIPT ERROR |
| `tools/diff_suite.sh` (JAR so với `game/`, 28 bài) | 0 mismatch / 55.612 tick | **28/28 bài 0 mismatch / 55.612 tick** |

Trạng thái các state player trong trace: phủ A = 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 16, 17, 20, 21.
**Chưa phủ: A = 18, 19 (xe máy đứng/chạy) và 23 (Xích Mao)**, xem "Chưa rõ".
Bằng chứng: `review/2026-11-07/m1_player_split/after_*.png`, mốc trace `remake/tests/player_trace/expected/*.txt.gz`,
log diff suite `review/2026-11-07/diff_suite_baseline.txt`.

Cách tách (đúng kế hoạch ở trên): `git mv` `player.gd` → `sim/player_sim.gd` (giữ lịch sử blame), bỏ phần
Node/vẽ/Input, đổi 18 lời gọi FX/UI + 12 `S.changed.emit()` + hitstop + message thành event. View giữ nguyên
API cũ bằng property chuyển tiếp, nên `main.gd` chỉ đổi đường dẫn preload. Thêm hook test `--trace=file` vào
`main.gd` (ghi trạng thái luật mỗi tick, chỉ chạy khi có cờ).

## Diff suite mở rộng (mốc trước refactor, chạy 2026-11-07)
`tools/diff_suite.sh [regex]` giờ có 28 bài: intro, fuzz1, **fuzz2, fuzz3** (sinh bằng `gen_fuzz.py 2 400` /
`3 400`, cùng tham số đã tái tạo đúng từng byte `f1.txt`, lưu ở `tools/diff_scripts/f2.txt`, `f3.txt`),
**22 map `fm*.txt`**, boss95, ending. Kết quả: **28/28 bài 0 mismatch, 55.612 tick** (22 map fm: 34.527 tick).
- fuzz2: 4072 tick, fuzz3: 4838 tick. VERIFICATION.md cũ ghi 3879 / 2011: script f2/f3 cũ không còn trong
  repo nên số tick khác, không so được với số cũ.
- fm94: oracle `game/` in 3 lần `SCRIPT ERROR: Invalid access of index '7'` ở `core/orig.gd:5452` (`__g_o`),
  nhưng trace vẫn khớp JAR 4253/4253 tick. Không sửa `game/` (oracle), ghi vào "Chưa rõ".
- Lưu ý: suite này kiểm `game/` khớp JAR, không chạy `remake/`. Refactor M1 mục 1 không đụng `game/`,
  nên nó chỉ là mốc cho các mục M1 sau. Bằng chứng cho `remake/` là `player_trace.sh`.

## Đã chạy thật trong phiên M0 (Godot 4.7.2, Mac M4 Pro)
| Kiểm tra | Lệnh | Kết quả |
|---|---|---|
| Import asset remake (worktree mới) | `godot --headless --path remake --import` | exit 0, 0 lỗi |
| Boot remake headless | `godot --headless --path remake -- --newgame --keys=... --quit=8` | exit 0, 0 SCRIPT ERROR |
| Chạy remake có cửa sổ | `godot --path remake -- --newgame --autoskip --shots=6 --quit=7` | exit 0, 0 SCRIPT ERROR, ảnh `review/2026-11-07/shot_6.png` (map99 intro, HUD, hộp thoại) |
| Import oracle `game/` | `godot --headless --path game --import` | exit 0 |
| Story tests (remake so với log oracle đã lưu) | `remake/tests/story_run_tests.sh` | **77 check, 0 fail** |
| Enemies oracle (chạy `game/` + remake, so từng tick) | `remake/tests/run_enemies_oracle.sh 500` | **map 4, 1, 6, 7, 12, 95, 97: 500/500 tick khớp 100%**, sát thương lên player khớp |
| Test Python cũ (`tests/`) | `python3 -m unittest discover -s tests` | 43 test OK (công cụ đời Codex cũ, không phải luật game) |

Ghi chú: lúc thoát Godot luôn in `1 resources still in use at exit` và `2 ObjectDB instances leaked`.
Không ảnh hưởng kết quả, nhưng nên dọn ở M1.

## Oracle emulator: dựng lại từ repo và diff (chạy 2026-11-07)
- `tools/build_emu.sh` dựng `analysis/claude/emu/classes/` (javac `src/` + `harnessR/`, 34 class) và `pcls/`
  (copy `a/b/Midlet.class` lấy từ `NinjaSchool1.jar`, vá bằng `patchcls.py`). JAR không bị sửa,
  file `.class` vẫn gitignore. Đối chiếu: `pcls/a.class`, `b.class`, `Midlet.class` **trùng từng byte**
  với bản cũ ở worktree `ninja-pc-game-dev-f010c2`.
- `tools/diff_suite.sh` chạy `tools/diff_run.sh` (emulator JAR gốc so với oracle `game/`, so từng tick
  các trường luật). Script trận boss trước đây chỉ có ở worktree cũ, đã chép vào
  `tools/diff_scripts/boss95.txt`.

| Bài | Script | Tick so sánh (theo map) | Mismatch |
|---|---|---|---:|
| Intro map99 → map4 | `game/tests/scripts/d1.txt` | 799 (map99 418, map4 381) | **0** |
| Fuzz seed 1 | `game/tests/scripts/f1.txt` | 3847 (map99 592, map4 3255) | **0** |
| Boss map95 | `tools/diff_scripts/boss95.txt` | 4702 (map99 80, map95 4622) | **0** |
| Chuỗi kết thúc | `game/tests/scripts/end3d.txt` | 2827 (map99 80, map95 702, map94 847, map0 1198) | **0** |

Mỗi đoạn map có số frame emu = godot. Intro ra 799 tick, VERIFICATION.md cũ ghi 797; ba bài còn lại khớp
đúng số cũ. Chưa chạy lại: fuzz seed 2/3 và 22 map `fm*.txt`.
Lưu ý: bộ này kiểm **oracle `game/` khớp JAR**, chưa kiểm `remake/`. Phần remake so với oracle hiện chỉ có
enemies/story (bảng trên). Diff remake trên đủ trường luật là việc của M1.

## Chưa chạy lại được trong phiên này
- Export Mac/Windows: chưa thử (sau khi bỏ đường dẫn template tuyệt đối, Godot sẽ dùng template cài sẵn
  ở `~/Library/Application Support/Godot/export_templates/4.7.2.stable/`).
- Các mục dưới đây lấy từ `progress.json`, phiên này **chưa kiểm lại**: bản hybrid `game/` đã ship Mac
  (Windows chỉ export, chưa chạy), hero Blender 46 frame, 14 biome ngày/đêm, nhạc MIDI → mp3,
  164 sprite quái HD, movement native giai đoạn 1.

## Trạng thái remake/ (theo code hiện có)
- Chơi được map 99 → map 4 native. Đã ghép story (cutscene, quest NPC, menu, bản đồ thế giới, shop)
  và enemies (24 map giống oracle theo commit `52a4a00`).
- **Sim/View**: player, enemies, story, world đã tách (`sim/*_sim.gd` + `view/*_view.gd`). Drops vẫn vừa luật
  vừa vẽ (M1 mục 2d). `drops.gd` còn ở `scripts/enemies/` (luật nhặt + `draw`), sim enemies gọi `tick`, view gọi `draw`.
- Art đang trộn 5 nguồn (Blender, Codex HD, Veo, Scale4x, FLUX). Từ M3 thống nhất theo pipeline Codex → rig.

## Đã làm trong M0
- `games/ninja1`, `games/ninja1-original` → `archive/games/` (git mv).
- `CLAUDE_TASK.md`, `START_HERE_CLAUDE.md`, `PROJECT_KNOWLEDGE.md`, `REVIEW_FLAGS.md`,
  `analysis/PC_REMAKE_PLAN.md`, `remake/REMAKE_PLAN.md` → `archive/docs/`. Các comment trong `remake/`
  trỏ tới REMAKE_PLAN đã đổi sang `archive/docs/REMAKE_PLAN.md`.
- `custom_template` tuyệt đối `/Users/quangdang/...` → `""` trong cả 4 `export_presets.cfg`
  (`game/`, `remake/`, 2 file trong archive). Không còn `/Users/quangdang` trong các file preset.
- `remake/tests/run_enemies_oracle.sh`: `GODOT` ghi đè được qua biến môi trường (mặc định giữ nguyên).
  Các script khác (`tools/diff_run.sh`, `tools/shoot.sh`) vẫn ghi cứng đường dẫn Godot
  `/Users/quangdang/Downloads/ninja/.tools/godot/...`. Chỉ đọc binary ở đó, không sửa thư mục `ninja`.
- Một số script và JSON cũ trong `analysis/` (đời Codex/Antigravity) vẫn nhắc `games/ninja1`.
  Đó là lịch sử, không sửa.

## File lớn
**Đã chốt: KHÔNG chuyển LFS, không viết lại lịch sử.** `review/**/*.mp4` đã vào `.gitignore`.
Phần dưới giữ làm tham khảo.

Tổng `.git` khoảng 427 MB (pack 254 MB). File > 20 MB đang track, cả trong lịch sử:
- `art/blender/env_backgrounds.blend` 25.7 MB. **Đề xuất: Git LFS** (`*.blend`). Đây là nguồn, không sinh lại được.

Không vượt 20 MB nhưng đáng xử lý:
- `hero_tweens.png` 6.9 MB × 3 bản (`art/hero`, `game/art/hero`, `remake/art/hero`); webp/mp4 Veo
  ×2 bản trong `game/art/veo` và `remake/art/veo`. **Đề xuất:** khi M3 bỏ hero Blender/Veo thì chuyển
  các bản sinh ra vào archive hoặc `.gitignore` (sinh lại được từ `scripts/run_hero_pipeline.py`,
  `tools/art/veo_extract.py`). Bản gốc `art/incoming/veo/*.mp4` → LFS.
- Thêm LFS cho `*.mp4`, `*.blend`, `*.tpz`. Chuyển file **trong lịch sử** sang LFS phải viết lại
  lịch sử (`git lfs migrate`), đụng tới mọi nhánh và worktree (cả nhánh Codex) nên cần chủ dự án quyết.

## Local chưa push
Kiểm `/Users/quangdang/Downloads/ninja-claude-parallel` (main local, `35a3512`) so với `origin/main`.
Chỉ đọc, không pull, không reset, không xoá gì:
- **Commit chỉ có ở local: không có.** `35a3512` là tổ tiên của `origin/main` (merge-base = `35a3512`).
  Local đang chậm hơn GitHub 24 commit (giờ thêm commit docs `1bcaafc` là 25).
- File chưa track: `.serena/` (20 KB, cấu hình công cụ Serena: `project.yml`, `memories/`, `cache/`).
  Không phải dữ liệu game.
- File bị ignore: `.claude/` (chứa các worktree) và `.serena/project.local.yml`.
- Kết luận: pull fast-forward lên `origin/main` an toàn, không mất gì.
- Chủ dự án duyệt (2026-11-07): pull ff-only và thêm `.serena/` vào `.gitignore` (đã thêm).
  Lệnh pull: đã chạy, main local khớp GitHub.

## Chưa rõ
- **Ảnh có cửa sổ không tất định giữa các lần chạy** (có từ trước, không phải do refactor): `world/background.gd`
  đổi ngày/đêm mỗi lần mở game (đếm trong `user://remake_cfg.ini`), `fx_layer.gd` seed FX bằng `randf()`.
  `tests/enemies_shots.sh` cố định bộ đếm về 0 rồi trả lại file cũ, và chạy `--seed=1`. Mũi tên "tiếp" của hộp
  thoại (`ui.gd:293`) và NPC đứng thở / dấu "!" (`world/npcs.gd:51,79`) chạy theo giờ thật (`Time.get_ticks_msec`), làm
  ảnh lệch ở các vùng đó.
  Nếu muốn ảnh review luôn so được: chuyển các chỗ này sang đồng hồ game (mục World / UI).
- **Luật đọc camera mượt theo frame**: sim quái (`fR/fS` thức quái theo màn hình) và luật chết rơi (`_st_dead`)
  đọc vị trí camera mà view tính bằng lò xo theo `delta` của frame (có rung `randf`). Bản gốc tính camera trong luật
  25 Hz (`h()` ~L3185). Refactor giữ nguyên cách cũ; muốn kết quả như nhau ở mọi fps thì phải chuyển camera luật vào
  sim (đổi hành vi, cần mốc trace mới), nên làm cùng mục M1 "di chuyển 100 Hz".
- Comment cũ gọi cờ ô `0x1000` là "vines", nhưng luật dùng nó như vùng chìm (rơi vào A = 12, ngập đầu thì chết).
  Game 1 không có trạng thái leo thang/dây.
- Trace m95: golem đứng chờ (`hu = 0`) vì không có cutscene mở trận; trận golem có trong oracle (`ACT_95`, `hu = 1`).
- Còn TODO trong sim enemies: phi tiêu và kiếm trúng bạn đồng hành đối thủ `hf` (dC/dD) chưa làm (mục 2e Projectiles).
- **Lỗi sẵn có (không sửa trong refactor): Xích Mao không bật được.** `_common_actions()` đặt `S.A = 23` nhưng
  cuối `_native()` gọi `_publish()` ghi đè `S.A` về 1/2, nên A = 23 không bao giờ tới tick. Đã thử:
  `--map=1,1416,216 --set=eH=false;eT=true;I=300` + nhấn phím biến hình → A luôn = 1. Sửa ở M2 (combat) kèm diff test.
- Cờ dùng cho Xích Mao trong remake là `S.eT`, nhưng `story.gd` dùng `eT` làm cờ cốt truyện "Toniku thành
  sát thủ" (đặt true ở map 4 prologue, false ở map 0). Cần đọc `a.java` xem cờ kỹ năng Xích Mao thật là biến nào.
- Xe máy: kịch bản `story_m3_q3` (2026-11-07) đã có A = 18/19/20/21. Xích Mao (A = 23) vẫn chưa phủ.
- `remake/` trong worktree mới phải chạy `godot --headless --path remake --import` trước, nếu không mọi test
  in hàng trăm nghìn SCRIPT ERROR (texture null) dù luật vẫn chạy.
- **Điều tra sau:** fm94 (`game/tests/scripts/fm94.txt`) làm oracle `game/` in 3 lần
  `SCRIPT ERROR: Invalid access of index '7'` ở `game/core/orig.gd:5452` (`__g_o`). Trace vẫn khớp JAR
  4253/4253 tick, nên có thể chỉ là nhánh vẽ hoặc truy cập mảng mà bản Java bắt exception. Chưa xác định;
  `game/` là oracle nên không sửa khi chưa rõ.
- Có nên giữ `game/art/*` (HD cho bản hybrid) khi `game/` chỉ còn là oracle không.
