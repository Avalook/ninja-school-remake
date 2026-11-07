# Codex — quy tắc vẽ art cho Ninja School PC remake

Bạn (Codex) **chỉ phụ trách vẽ hình**. Code, rig, animation và ghép vào game là việc của Claude Code
(xem `CLAUDE.md`, `ROADMAP.md`). Không sửa code trong `remake/`, `game/`, `tools/`.

## Mục tiêu
Làm lại toàn bộ hình ảnh của Ninja School 1 (game Nokia J2ME) thành anime 2D HD, đẹp, đồng nhất:
nhân vật, quái, boss, nền, cây cỏ, đồ vật, UI. Giữ **nhận diện** của bản gốc: cùng nhân vật,
cùng màu áo chủ đạo, cùng loại quái, cùng địa điểm. Chỉ vẽ lại đẹp hơn, không tự thiết kế lại.

## Ảnh gốc để tham chiếu (chỉ đọc)
- `art/references/NinjaSchool1-contact-01..04.png`: toàn bộ ảnh gốc game 1.
- `art/references/ninja1-hero-idle-original-64.png`, `ninja1-hero-46-frames-original.png`: nhân vật chính.
- `art/references/ninja1-background-original-reference.png`: nền gốc.
- `analysis/NinjaSchool1/resources/`: từng PNG gốc.
- `art/generated/codex_hd/`: art Codex làm trước đây, dùng làm tham khảo style (Ayumi `npc1_ayumi.png`).
- Tên quái, NPC, địa điểm: `remake/data/static.json` (khoá `am` = quái, `kP` = địa điểm, `kx` = vật phẩm).

## Quy tắc đồng nhất (bắt buộc)
1. **Không bao giờ gen chỉ bằng chữ.** Mỗi lần gen phải đính kèm:
   (a) style frame đã duyệt `art_src/style/APPROVED.png` (sau khi đã chọn),
   (b) model sheet đã duyệt của đúng nhân vật đó (nếu có),
   (c) ảnh gốc Nokia của nhân vật hoặc nơi đó.
2. Bảng màu theo `art_src/style/palette.json` (sau khi có). Viền, độ dày nét, kiểu đổ bóng theo style frame.
3. Thấy sai lệch thì sửa từ model sheet, không vá từng ảnh lẻ.
4. Nhân vật, quái, đồ vật: **nền trong suốt (PNG RGBA)**, không bóng đổ dưới chân, không chữ, không khung.
5. Nhân vật quay **sang phải** (game lật hình cho hướng trái), ánh sáng chiếu từ trên-trái.
6. Mỗi bộ phận tách rời phải vẽ trọn phần bị che (vai, khớp tay chân vẽ dư ra) để rig xoay không lộ lỗ.

## Thư mục ra (chỉ ghi vào `art_src/`)
```
art_src/style/            style_A.png, style_B.png, style_C.png, APPROVED.png, palette.json
art_src/characters/<id>/  model_sheet.png, expressions.png, parts/<bộ_phận>.png, palette.json
art_src/monsters/<id>/    model_sheet.png, parts/ hoặc sheet.png (quái ít động tác)
art_src/bosses/<id>/      như monsters
art_src/env/<biome>/      layer_0_sky.png … layer_5_front.png, tiles.png, decor/<tên>.png
art_src/items/            <tên>.png (icon 1:1)
art_src/ui/               khung, nút, thanh HP/MP, hộp thoại (9-slice có lề trong suốt)
art_src/prompts/<id>.md   prompt đã dùng + ảnh đính kèm + seed/ghi chú
```
`<id>` dùng tên không dấu, ví dụ `hero`, `ayumi`, `takanashi`, `ma_mot_mat`, `rung_truc`.

## Tên bộ phận nhân vật người (dùng chung cho mọi nhân vật để rig dùng lại)
`head`, `hair_back`, `hair_front`, `face_<biểu_cảm>`, `torso`, `hip`, `upper_arm_front`, `lower_arm_front`,
`hand_front`, `upper_arm_back`, `lower_arm_back`, `hand_back`, `thigh_front`, `shin_front`, `foot_front`,
`thigh_back`, `shin_back`, `foot_back`, `weapon_<tên>`, `cloth_<tên>` (vạt áo, khăn, đai bay).

## Xong mỗi việc
Ghi prompt vào `art_src/prompts/`, cập nhật `art_src/ART_LOG.md` (đã làm gì, file nào, đang chờ duyệt gì).
Không tự coi là đã duyệt. Chỉ chủ dự án duyệt.
