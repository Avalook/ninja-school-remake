# Ninja School 1 — Bản thử kỹ thuật: Ghi chú bằng chứng

## Đã xác minh từ nguồn gốc (verified original facts)
- `sources/a.java` là một lớp J2ME `Canvas`/`Runnable` (game chạy trên nền J2ME).
- Ánh xạ phím gốc nằm trong khối `F/G` quanh dòng 7421–7547 của `a.java`.
- Tài sản gốc gồm `bg00.png`, `bg01.png`, các phần `c/` (ar/pt/v/w) và nhiều PNG.
  Kích thước PNG gốc rất nhỏ (vd `bg00.png` = 24x59, `c/v0.png` = 26x17) — đây là
  sprite độ phân giải thấp, KHÔNG phải artwork HD.

## Cơ chế TẠM THỜI của bản thử (provisional prototype mechanics)
- Sảnh tập luyện (training arena) được vẽ bằng vector trong code: núi, trăng, nền,
  vài nền tảng. Đây KHÔNG phải bản đồ gốc đã phục hồi.
- Giá trị gameplay (tốc độ, sát thương, cooldown, số kẻ địch, vị trí cổng) là
  GIÁ TRỊ TẠM THỜI để kiểm tra runtime PC, chưa được xác minh từ nguồn gốc.
- Nhân vật ninja là hình bóng tạm thời (tóc + kiếm), không phải sprite gốc.
- Bản thử này KHÔNG phải game gốc hoàn chỉnh và KHÔNG phải bản đồ gốc đã phục hồi.

## Chưa phục hồi
- Cấp 1 gốc, công thức chiến đấu đầy đủ, layout arena gốc.
