# Ninja School 1 — PC Remake (Godot 4.7.2)

Bản PC của Ninja School 1 (J2ME). Toàn bộ logic game (di chuyển, chiến đấu, quái/AI, NPC, hội thoại,
18 nhiệm vụ, kỹ năng, vật phẩm/shop, lưu/tải, cutscene, kết thúc) là **mã gốc được dịch tự động** từ JAR
sang GDScript và được đối chiếu từng tick với JAR gốc chạy trong JVM (xem `analysis/claude/VERIFICATION.md`).
Phần hình ảnh là mới: nhân vật chính anime HD dựng bằng rig Blender, nền nhiều lớp parallax 14 biome
(ngày/đêm) render bằng Blender, UI màn rộng 16:9, hiệu ứng chém/bụi/tia lửa, nhạc gốc.

## Chạy
- Mac: mở `builds/mac/Ninja School 1.app` (lần đầu: chuột phải → Open vì app chưa ký Apple).
- Windows 11 x64: `builds/windows/NinjaSchool1.exe` (đã xuất, **chưa chạy thử trên Windows thật**).
- Từ mã nguồn: Godot 4.7.2 → mở `game/project.godot` → F5.

## Phím mặc định (đổi được: F10 → Cài đặt)
| Hành động | Phím |
|---|---|
| Đi trái / phải | A / D hoặc ← / → |
| Nhảy / lên | W, Space, ↑ |
| Ngồi / độn thổ / xuống | S, ↓ |
| Đánh / chọn / tiếp thoại | J, Enter |
| Giao tiếp NPC / quay lại | E, Backspace |
| Menu (dùng vật phẩm, bản đồ, lưu) | Esc, Tab |
| Đổi kiếm ↔ shuriken (phím 0 gốc) | K, 0 |
| Nhảy chéo trái/phải (phím 1/3 gốc) | U / O, 1 / 3 |
| Xích Mao (phím 7) · Ẩn thân (phím 9) | L, 7 · I, 9 |
| Bình HP (*) · Bình MP (#) | Q · R |
| Toàn màn hình | F11 hoặc Alt+Enter |
| Cài đặt / đổi phím / âm lượng | F10 (hoặc F1) |

Hướng dẫn trong game nhắc "phím 5/0/7/9" là phím điện thoại gốc — tương ứng J / K / L / I như bảng trên.

## Lưu game
Như bản gốc: tự lưu khi chuyển bản đồ, hoặc Menu → "Lưu và thoát". Ở màn tiêu đề chọn "Tiếp tục".
Dữ liệu ở `~/Library/Application Support/Godot/app_userdata/Ninja School 1/rms` (Mac) hoặc
`%APPDATA%\Godot\app_userdata\Ninja School 1\rms` (Windows).

## Khác biệt có chủ đích so với bản gốc
- Bỏ kích hoạt/SMS trả phí; bỏ 2 câu hỏi âm thanh/chất lượng (mặc định Bật/Cao).
- Màn chơi rộng 480×270 logic (gốc 240×270) → thấy rộng hơn theo chiều ngang; HUD vẽ lại dạng overlay.
- Ngày/đêm vẫn luân phiên mỗi lần mở game như bản gốc.

## Dựng lại asset / kiểm thử
- Dịch lại logic: `tools/transpile/build.sh`
- Hero Blender: `python3 scripts/run_hero_pipeline.py` · Nền Blender: `python3 scripts/run_env_pipeline.py`
- Kiểm vi sai với JAR gốc: `tools/diff_run.sh <script> <tên>` (script tạo bằng `tools/gen_fuzz.py`)
- Chụp màn hình tự động: `tools/shoot.sh <script> <thư mục>`
