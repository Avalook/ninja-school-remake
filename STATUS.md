# STATUS — Ninja School PC remake

Cập nhật: 2026-11-07, phiên M1 mục 1 (nhánh `m1-sim-view` tách từ `main` 3d04081).
Kế hoạch: `ROADMAP.md`. File này ghi **đã kiểm bằng chạy thật**. Phần nào chỉ lấy lại từ
`analysis/claude/progress.json` mà chưa chạy lại thì có ghi chú rõ.

## Mốc hiện tại
- **M0 (dọn repo)**: xong 5/5. Không dùng LFS (chủ dự án chốt). Oracle emulator dựng lại được từ repo.
- **M1 mục 1 xong**: `player.gd` đã tách thành `remake/sim/player_sim.gd` + `remake/view/player_view.gd`
  (+ `hero_anim.gd` sang `remake/view/`). Hành vi không đổi: trace 10 kịch bản khớp 100% từng dòng.
- Mục tiếp theo: M1 mục 2, tách enemies, story, world (va chạm), drops, projectiles.
- Đã merge `m1-sim-view` vào `main` (2026-11-07). Việc phát hiện trong phiên đã thêm vào ROADMAP:
  M1 (di chuyển native bước cố định 100 Hz), M2 Combat (sửa Xích Mao, cờ thật của Xích Mao, trace A = 18/19/23).
- Codex đang vẽ trong `art_src/` trên nhánh `claude/project-directory-codex-c71a67`.
  Nhánh này **không tạo, không sửa** gì trong `art_src/`.

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
- **Sim/View**: player đã tách (`sim/player_sim.gd`, `view/player_view.gd`). Enemies, story, world, drops,
  projectiles vẫn là Node vừa luật vừa vẽ (M1 mục 2).
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
  Lệnh pull ở thư mục gốc **chưa chạy**: session worktree bị chặn chạy git ra ngoài worktree.
  Chủ dự án tự chạy `git -C /Users/quangdang/Downloads/ninja-claude-parallel pull --ff-only`.

## Chưa rõ
- **Lỗi sẵn có (không sửa trong refactor): Xích Mao không bật được.** `_common_actions()` đặt `S.A = 23` nhưng
  cuối `_native()` gọi `_publish()` ghi đè `S.A` về 1/2, nên A = 23 không bao giờ tới tick. Đã thử:
  `--map=1,1416,216 --set=eH=false;eT=true;I=300` + nhấn phím biến hình → A luôn = 1. Sửa ở M2 (combat) kèm diff test.
- Cờ dùng cho Xích Mao trong remake là `S.eT`, nhưng `story.gd` dùng `eT` làm cờ cốt truyện "Toniku thành
  sát thủ" (đặt true ở map 4 prologue, false ở map 0). Cần đọc `a.java` xem cờ kỹ năng Xích Mao thật là biến nào.
- Xe máy: trace có A = 20/21 nhưng chưa có 18/19 (đứng/chạy trên xe). Cần kịch bản leo xe chắc chắn hơn.
- `remake/` trong worktree mới phải chạy `godot --headless --path remake --import` trước, nếu không mọi test
  in hàng trăm nghìn SCRIPT ERROR (texture null) dù luật vẫn chạy.
- **Điều tra sau:** fm94 (`game/tests/scripts/fm94.txt`) làm oracle `game/` in 3 lần
  `SCRIPT ERROR: Invalid access of index '7'` ở `game/core/orig.gd:5452` (`__g_o`). Trace vẫn khớp JAR
  4253/4253 tick, nên có thể chỉ là nhánh vẽ hoặc truy cập mảng mà bản Java bắt exception. Chưa xác định;
  `game/` là oracle nên không sửa khi chưa rõ.
- Có nên giữ `game/art/*` (HD cho bản hybrid) khi `game/` chỉ còn là oracle không.
