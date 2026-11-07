# STATUS — Ninja School PC remake

Cập nhật: 2026-11-07, phiên M0 (nhánh `m0-cleanup`, worktree `.claude/worktrees/m0-cleanup`).
Kế hoạch: `ROADMAP.md`. File này ghi **đã kiểm bằng chạy thật**. Phần nào chỉ lấy lại từ
`analysis/claude/progress.json` mà chưa chạy lại thì có ghi chú rõ.

## Mốc hiện tại
- **M0 (dọn repo)**: xong 5/5. Không dùng LFS (chủ dự án chốt). Oracle emulator dựng lại được từ repo.
- Mục tiếp theo: M1, tách `player.gd` thành `sim/player_sim.gd` + `view/player_view.gd`.
- Codex đang vẽ trong `art_src/` trên nhánh `claude/project-directory-codex-c71a67`.
  Nhánh này **không tạo, không sửa** gì trong `art_src/`.

## Đã chạy thật trong phiên này (Godot 4.7.2, Mac M4 Pro)
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
- **Chưa tách Sim/View**: luật vẫn nằm trong Node (`scripts/player/player.gd` vừa xử lý luật vừa vẽ).
  Đó là việc của M1.
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
- Có nên giữ `game/art/*` (HD cho bản hybrid) khi `game/` chỉ còn là oracle không.
