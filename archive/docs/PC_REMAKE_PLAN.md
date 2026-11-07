# Ninja School 1–3: mục tiêu bản PC và tiêu chí bàn giao

## Kết quả cần đạt

Ba game chơi được trên Windows 11 x64, giữ nội dung và hành vi đã xác minh
từ từng bản gốc, có điều khiển bàn phím, đổi phím, lưu tiến độ và đồ họa mới
đồng nhất. Mục tiêu hiển thị 1920×1080, 16:9 và 60 FPS trên Acer Nitro 5
i5-11400H / RAM 16 GB / GTX 1650 4 GB. Đây là mục tiêu cần kiểm thử, chưa
phải kết quả đã đạt.

AI phục vụ phát triển chạy trên Mac mini M4 Pro 48 GB. Bản game cuối không
cần Ollama, REA hoặc model AI trên máy Windows của người chơi.

## Bằng chứng và vai trò công cụ

- Ba JAR gốc được giữ nguyên; kiểm kê lưu SHA-256.
- REA 6.8.0 hiện được dùng cho kiểm kê archive và bằng chứng tài nguyên.
  Bước này không giải mã logic Java ME hoặc chứng minh game chạy đúng.
- CFR 0.152 đã xuất 4 / 22 / 19 file Java cho ba game. Mã xuất vẫn có thể
  thiếu hoặc sai do obfuscation, nên phải đối chiếu bằng chứng và runtime.
- Qwen local đã hỗ trợ điều tra, soạn code sân tập và cảnh gốc bằng các lượt
  có giới hạn, có kiểm thử độc lập; chưa khôi phục toàn bộ game.

## Thứ tự thực hiện và điều kiện hoàn thành

| Mốc | Đầu ra | Điều kiện qua mốc |
| --- | --- | --- |
| 1. Khảo sát | Mã xuất, kiểm kê, báo cáo có file và số dòng | Tách rõ phần đã xác nhận, giả thuyết và phần chưa đọc |
| 2. Đối chiếu bản gốc | Bảng hành vi, dữ liệu màn chơi và các ca kiểm tra | Có chuẩn để kiểm tra điều khiển, va chạm và chiến đấu |
| 3. Bản thử Ninja School 1 | Dự án nguồn và bản Mac chơi thử trước, rồi xuất Windows | Đi, nhảy, đánh, nhận sát thương, chết/chơi lại theo hành vi đã xác minh |
| 4. Mẫu đồ họa và UI | Một nhân vật đủ chuyển động, một cảnh nền, menu và HUD | Hình sắc nét ở Full HD; khung hình, điểm neo và vùng va chạm nhất quán |
| 5. Hoàn thiện game 1 | Bản PC đủ nội dung của game 1 | Màn chơi, tiến độ và các tính năng có trong bản gốc được đối chiếu; lưu/đọc và đổi phím hoạt động |
| 6. Game 2 và game 3 | Bản PC tương ứng cho từng game | Giữ khác biệt riêng; không lấy game 1 thay cho nội dung hai game còn lại |
| 7. Bàn giao | Bộ chạy Windows, mã nguồn, tài nguyên và hướng dẫn | Chạy trên Acer mục tiêu; kiểm thử xuyên suốt, không chỉ xuất được file exe |

## Quyết định triển khai ban đầu

Godot/GDScript là hướng dựng game PC ban đầu; công cụ xuất Windows được
chuẩn bị riêng trong `.tools/godot`. Game 1 là bản chứng minh đầu tiên trước
khi mở rộng hai game còn lại. Chỉ dùng chung phần điều khiển, giao diện hoặc
công cụ dữ liệu khi bằng chứng cho thấy việc dùng chung phù hợp.

Không tự đoán nhiệm vụ, map hoặc công thức chiến đấu khi chưa tìm thấy
bằng chứng. Một bản thử tự dựng theo phong cách ninja phải được ghi là bản
thử, không được gọi là khôi phục đầy đủ game gốc.

## Tiêu chí hình ảnh và giao diện

- Giữ nhận diện ninja và tinh thần hình ảnh của bản gốc; làm rõ nét cho PC.
- Dựng lại bố cục menu, HUD và màn hình thiết lập phù hợp chuột/bàn phím.
- Có chữ tiếng Việt dễ đọc; không kéo giãn giao diện điện thoại thành 16:9.
- Đồ họa nhân vật gồm cả bộ chuyển động, không chỉ một ảnh tĩnh đẹp.
- Nền, địa hình, hiệu ứng và nhân vật cùng hệ màu và tỷ lệ; ưu tiên khả năng
  đọc đường đi, đòn đánh và vật thể tương tác khi đang chơi.
- Dùng model sinh ảnh local riêng; công cụ và model đó chưa được chọn hoặc
  cài đặt. Chốt chất lượng bằng mẫu chạy trong game trước khi tạo hàng loạt.

## Ưu tiên mới: Mac trước

Người dùng đã đồng ý thử trên Mac trước Windows. Worker viết code local hiện
có trong `scripts/local_game.py`, giới hạn công cụ ghi vào `games/ninja1`,
kiểm tra Godot và khóa chống worker trùng. Lượt đầu dựng sân tập kỹ thuật
với hình tạm; chưa khôi phục màn đầu của game gốc. Tiến trình riêng ở
`analysis/runs/NinjaSchool1-build.json`. Lịch tiếp tục công việc được lưu
thực tế ở nhịp 240 phút; luôn kiểm tra worker trước khi khởi động thêm.

## Giới hạn và lịch

Đã xuất exe sân tập kỹ thuật; chưa kiểm thử Windows, chưa có đồ họa HD mới và chưa
đủ cơ sở hẹn ngày hoàn tất ba game. Ước lượng lại sau bản thử một màn dựa
trên lượng nội dung thực tế và tốc độ khôi phục đã đo. Hoàn tất một lượt AI
hoặc một báo cáo không đồng nghĩa hoàn tất bất kỳ game nào.

## Phản hồi hình ảnh và kết quả mới

Người dùng không chấp nhận hình khối tạm. Đã ghép được địa hình tĩnh từ atlas
và 24 lưới map gốc ở `analysis/original-map-preview.html`, có chọn map/phóng to.
Đã kiểm tra dữ liệu 24 map và hiển thị map0/map1 trên trình duyệt. Đây chưa phải
màn chơi: chưa có nhân vật, va chạm, hiệu ứng động hoặc đồ họa HD mới. Bước kế
tiếp phải đưa cảnh gốc vào Godot với nhân vật và va chạm, không tạo thêm sân tập
chung hoặc báo cáo thay cho game.

Map0 đã được nhập vào dự án Godot riêng `games/ninja1-original`, kiểm tra
ảnh hiển thị native, camera bằng bàn phím và thoát cửa sổ. Import, smoke
và 120 khung startup đều qua; bằng chứng ở `analysis/ninja1-original-scene-checks.json`.
Đây là cảnh xem địa hình, chưa có nhân vật/va chạm và chưa xác nhận map bắt đầu.
