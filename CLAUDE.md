# Ninja School PC remake — đọc trước khi làm

Dự án cá nhân: làm lại game Ninja School (J2ME/Nokia) để chơi trên laptop. Giữ nguyên luật và cốt truyện,
làm lại toàn bộ phần nhìn.

Thứ tự đọc: `STATUS.md` (đang ở đâu) → `ROADMAP.md` (kế hoạch, quy tắc, mục tiếp theo).

Ghi nhớ nhanh:
- Bản chính: `remake/` (Godot 4.7, GDScript có kiểu). Luật nằm trong `remake/sim/`, phần nhìn trong `remake/view/`.
- `game/` và `analysis/claude/emu/` là oracle để so sánh. Không sửa, không ship.
- JAR gốc, `analysis/*/sources`, `analysis/*/resources` là dữ liệu gốc: chỉ đọc.
- `archive/` là lịch sử, không làm việc trong đó.
- Mỗi phiên làm một mục checkbox trong `ROADMAP.md`. Thay đổi `sim/` phải có diff test xanh.
  Thay đổi hình ảnh phải có ảnh hoặc video trong `review/`. Cuối phiên cập nhật `STATUS.md`.
- Art nhân vật: Codex vẽ tách bộ phận → rig Skeleton2D. Không dùng thêm nguồn art khác.
- Không tạo project hay thư mục song song mới. Không đoán luật: đọc source gốc hoặc chạy oracle.
- Art mới do Codex vẽ, nằm trong `art_src/` (quy tắc ở `AGENTS.md`). Claude Code chỉ chuẩn hoá
  (cắt, scale 4×, anchor), rig, animation và ghép vào game. Không tự sinh art thay Codex.
