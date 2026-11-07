# Ninja School 3: Báo Cáo Dịch Ngược Kiến Trúc & Logic Game

Tài liệu này ghi nhận kết quả phân tích kỹ thuật và dịch ngược hệ thống logic của tựa game **Ninja School 3** (Bản bypass) dựa trên 19 tệp mã nguồn CFR decompile và 622 tài nguyên hình ảnh gốc trong workspace `/Users/quangdang/Downloads/ninja/analysis/NinjaSchool3_bypass`.

---

## 1. Cấu Trúc Module & Lớp Nguồn

Ninja School 3 tiếp tục phát triển trên nền tảng kiến trúc module của phần 2, tối ưu hóa phân lớp và bổ sung thêm các cơ chế chiến đấu băng tuyết:

| File nguồn | Số dòng / Kích thước | Vai trò trong hệ thống |
| :--- | :---: | :--- |
| `Midlet.java` | 816 dòng / 27,041 bytes | Điểm khởi đầu ứng dụng J2ME, custom font, nạp thanh trạng thái khởi tạo. |
| `MrQuyet.java` | 95 dòng / 2,644 bytes | **Lớp xử lý Bypass Kích hoạt & SMS**, ghi đè kiểm tra bản quyền WAP/SMS cho phép chạy độc lập không tốn cước viễn thông. |
| `q.java` | 9,781 dòng / 346,821 bytes | **Core Game Engine Canvas**, chiếm tỷ trọng lớn nhất: máy trạng thái nhân vật, vòng lặp game, quản lý phím, render HUD, hiệu ứng thời tiết băng tuyết. |
| `k.java` | 849 dòng / 25,753 bytes | **Hệ thống Bản đồ (Map Engine)**, nạp tile map từ `/m/`, phân tích ma trận cờ va chạm và kết nối các vùng đất. |
| `h.java` | 4,056 dòng / 141,859 bytes | **Bộ tổng hợp Sprite & Hoạt ảnh nhân vật**, xử lý lắp ráp cơ thể, vũ khí, hiệu ứng chém băng / lửa. |
| `i.java` | 2,274 dòng / 73,180 bytes | **Actor Manager**, quản lý di chuyển của quái vật, NPC và các vật thể tương tác trên bản đồ. |
| `a.java` | 3,032 dòng / 124,107 bytes | **Dữ liệu Quái vật & Bosses**, định nghĩa 30 loài quái vật và các công thức chiến đấu mới. |
| `m.java` | 33 dòng / 39,236 bytes | **Bảng dữ liệu Kịch bản & Dữ liệu lớn**, lưu trữ các mảng dữ liệu nhị phân đã nén. |
| `b.java` | 651 dòng / 20,942 bytes | **Lưu trữ RMS**, ghi lưu dữ liệu người chơi vào bộ nhớ máy. |

---

## 2. Bối Cảnh, Thế Giới & 16 Địa Danh (World Topology)

Ninja School 3 chuyển dời bối cảnh sang vùng núi tuyết Yamato và các đền thờ cổ xưa mang đậm màu sắc thần thoại Nhật Bản:

- **Danh sách 16 Bản đồ chính thức (`q.java`)**:
  1. `Map 0`: **Khu đá đỏ Akai** - Vùng đất đá đỏ hiểm trở.
  2. `Map 1`: **Khu đá đỏ Aiko** - Khu vực ranh giới giữa thung lũng đá và đồng tuyết.
  3. `Map 2`: **Cánh đồng Hiya** - Cánh đồng băng lạnh giá, nơi xuất hiện quái chuồn chuồn băng.
  4. `Map 3`: **Làng Echigo** - Ngôi làng tuyết trung tâm, nơi người chơi nhận nhiệm vụ và mua bán trang bị.
  5. `Map 4`: **Sông băng Yamato** - Dòng sông đóng băng vĩnh cửu, yêu cầu kỹ năng lướt trên băng.
  6. `Map 5`: **Hồ trăng** - Vùng hồ thiêng phản chiếu ánh trăng, địa điểm diễn ra nghi lễ ninja.
  7. `Map 6`: **Hẻm núi Takana** - Khe núi dốc đứng với các bẫy rơi tự nhiên.
  8. `Map 7`: **Miệng núi lửa** - Vùng đối lập giữa băng và lửa ngầm dưới lòng đất.
  9. `Map 8`: **Hang núi Kurai** - Hang động bóng tối che chở các loài quái ăn thịt.
  10. `Map 9`: **Rừng chết Kanashii** - Khu rừng u ám đầy chướng khí.
  11. `Map 10`: **Động Tamatamo** - Nơi ẩn náu của các ninja nhện và bẫy độc.
  12. `Map 11`: **Làng Oshin** - Ngôi làng cổ lánh nạn.
  13. `Map 12`: **Đền Amaterasu** - Đền thờ Nữ thần Mặt Trời tối cao.
  14. `Map 13`: **Đền Harumoto** - Ngôi đền phụ bảo vệ phong ấn cổ đại.
  15. `Map 14`: **Phong ấn Ounio** - Nơi giam giữ tà ma cổ đại Ounio (Địa điểm đại chiến Boss).
  16. `Map 15`: **Rừng tối** - Bản đồ bí mật kết thúc hành trình.

---

## 3. Hệ Thống 30 Quái Vật & Thần Thú

- **Nguồn đối chiếu**: `analysis/NinjaSchool3_bypass/sources/a.java`:
  Danh sách 30 loài quái vật nguyên bản của Ninja School 3:
  1. `[0]`: Tê giác
  2. `[1]`: Nhện đá
  3. `[2]`: Thiên tê
  4. `[3]`: Chuồn chuồn băng
  5. `[4]`: Cánh cụt ma
  6. `[5]`: Sâu lông xanh
  7. `[6]`: Chuột nhắt
  8. `[7]`: Cá kiếm
  9. `[8]`: Muỗi vòi dài
  10. `[9]`: Kappa (Thủy quái Kappa truyền thuyết)
  11. `[10]`: Bọ cánh đen
  12. `[11]`: Dơi đầu lâu
  13. `[12]`: Bạch cốt tinh
  14. `[13]`: Dơi lửa
  15. `[14]`: Hồn ma
  16. `[15]`: Nấm độc
  17. `[16]`: Hoa ăn thịt
  18. `[17]`: Quỷ đầu lâu
  19. `[18]`: Ma trơi
  20. `[19]`: Ốc bươu vàng
  21. `[20]`: Đỉa xanh
  22. `[21]`: Trùng điện
  23. `[22]`: Bướm độc
  24. `[23]`: Cá phát sáng
  25. `[24]`: Khỉ già
  26. `[25]`: Rắn hổ mang
  27. `[26]`: Ashigaru (Võ sĩ bộ binh)
  28. `[27]`: Ninja Nhện
  29. `[28]`: Ninja Rơm
  30. `[29]`: Bù nhìn

---

## 4. Cơ Chế Bypass Kích Hoạt Bản Quyền (Bypass Mechanism)

- Trong bản gốc J2ME thương mại của TeamMobi, trò chơi yêu cầu gửi tin nhắn SMS tới đầu số viễn thông (`8722`) để mở khóa các chương sau.
- Bản `NinjaSchool3_bypass` trong repo đã được tích hợp lớp `MrQuyet.java` và can thiệp vào `b.java`:
  - Mọi hàm kiểm tra trạng thái kích hoạt (`isActivated()`) đều trả về giá trị `true` mà không thực sự mở kết nối `Connector.open("sms://...")`.
  - Trong dự án PC Remake (Godot), lớp này cung cấp tham chiếu chính xác để loại bỏ hoàn toàn hệ thống thanh toán SMS và thay thế bằng việc mở khóa nội dung tuyến tính thông qua tiến trình chơi game hoặc tiền xu trong game.

---

## 5. Quy Tắc Ghép Nối Hoạt Ảnh (Sprite Assembly)

- Kế thừa và mở rộng từ hệ thống của phần 1 và 2:
  - Khung hình cơ thể vẫn sử dụng mô hình 3 phần chính ghép trên ma trận offset tọa độ:
    - Đầu (`ch*.png` $\to$ Part 0, offset Y cao nhất)
    - Chân (`cl*.png` $\to$ Part 1, offset Y thấp nhất sát đất)
    - Thân (`cb*.png` $\to$ Part 2, offset Y trung gian ở giữa)
  - Bổ sung hiệu ứng hạt tuyết rơi, biến dạng khung hình khi trượt trên băng và hiệu ứng phát sáng cho vũ khí cấp cao trong `h.java`.

