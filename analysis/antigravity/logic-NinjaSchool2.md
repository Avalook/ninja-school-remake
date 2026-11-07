# Ninja School 2: Báo Cáo Dịch Ngược Kiến Trúc & Logic Game

Tài liệu này ghi nhận kết quả phân tích kỹ thuật và dịch ngược hệ thống logic của tựa game **Ninja School 2** (J2ME) dựa trên 22 tệp mã nguồn CFR decompile và 578 tài nguyên hình ảnh gốc trong workspace `/Users/quangdang/Downloads/ninja/analysis/NinjaSchool2`.

---

## 1. Cấu Trúc Module & Entrypoint

Khác với Ninja School 1 vốn bị obfuscator dồn toàn bộ vào một lớp `a.java` khổng lồ, Ninja School 2 đã được module hóa thành các lớp chuyên trách:

| File nguồn | Số dòng / Kích thước | Vai trò trong hệ thống |
| :--- | :---: | :--- |
| `Midlet.java` | 793 dòng / 26,435 bytes | Khởi chạy ứng dụng J2ME, custom font, nạp thanh tiến trình khởi động. |
| `q.java` | 8,024 dòng / 280,060 bytes | **Core Canvas Game Engine** kế thừa `Canvas`, vòng lặp chính (`run()`), vẽ đồ họa (`paint()`), nhận phím (`keyPressed`), máy trạng thái nhân vật và HUD. |
| `j.java` | 1,004 dòng / 28,374 bytes | **Hệ thống Bản đồ (Map Engine)**, đọc dữ liệu lưới tile từ `/m/{id}`, tính toán cờ va chạm bitmask `x[]`. |
| `g.java` | 3,747 dòng / 136,665 bytes | **Quản lý Nhân vật & Sprite Parts**, nạp `/cp/`, tính toán hoạt ảnh và ghép nối cơ thể người chơi / nhân vật nữ / NPC. |
| `a.java` | 2,885 dòng / 102,836 bytes | **Hệ thống Quái vật & Bosses**, định nghĩa 29 loại quái vật và các thuộc tính chiến đấu. |
| `l.java` | 25 dòng / 50,326 bytes | **Bảng dữ liệu Kịch bản & Thoại (Script Data Tables)**, chứa toàn bộ chuỗi cutscenes, tiêu đề cảnh và đối thoại cốt truyện. |
| `b.java` | 674 dòng / 22,175 bytes | **Hệ thống Lưu trữ RMS**, tuần tự hóa dữ liệu game vào RecordStore. |

---

## 2. Dòng Thời Gian & Cốt Truyện (Setting & Storyline)

- **Mốc thời gian**: *"Mùa hè năm 1938 tại trường Hirosaki"* (diễn ra sau các sự kiện của Ninja School 1 đúng 1 năm).
- **Các phân cảnh chính (l.java)**:
  1. `[0]`: Mùa hè năm 1938 tại trường Hirosaki.
  2. `[1]`: Rừng trúc Ura 2 giờ sau..
  3. `[2]`: Chỉ dẫn: Dùng phím 5 tấn công.
  4. `[3-4]`: 3 hôm sau, tại cánh đồng Fuki..
  5. `[5]`: Sáng hôm sau.
  6. `[6]`: Trường Hirosaki 3 ngày sau....
- **Xung đột cốt truyện**:
  - Giao lưu và cạnh tranh với ngôi trường ninja thứ hai: **Trường Haruna** (Map 9).
  - Khám phá bí mật về cổ vật, cây sáo ma thuật và bức tượng phong ấn.
- **Đoạn kết (Ending Synopsis - l.java)**:
  *"Kỳ học tiếp theo đã kết thúc êm đềm, trải qua bao gian khổ và nguy hiểm để hoàn thành nhiệm vụ. Các bạn trẻ của chúng ta đã biết thế nào là sự đoàn kết. Nhưng liệu cuộc sống có luôn yên bình không? Kỳ học tiếp theo của các bạn sẽ xảy ra chuyện gì.... Mời các bạn tiếp tục theo dõi...."* $\to$ Dẫn trực tiếp sang phần 3.

---

## 3. Quy Tắc Ghép Nối Nhân Vật (Player Composite Contract)

Cơ chế ghép nối nhân vật trong Ninja School 2 tuân thủ **chính xác tuyệt đối** cấu trúc của Ninja School 1, khắc phục lỗi đảo ngược tên file:

- **Nguồn đối chiếu**: `g.java` L2601-L2625 (Image loader), L2750-L2760 (Render call).
- **Quy tắc nạp (g.java L2605-L2622)**:
  - `g.cd[n] = Image.createImage("/cp/cb" + n + ".png");` $\to$ Nạp vào `cd`: **THÂN (BODY)**.
  - `g.N[n] = Image.createImage("/cp/ch" + n + ".png");` $\to$ Nạp vào `N`: **ĐẦU (HEAD)**.
  - `g.ci[n] = Image.createImage("/cp/cl" + n + ".png");` $\to$ Nạp vào `ci`: **CHÂN (LEGS)**.
- **Quy tắc vẽ (g.java L2753-L2755)**:
  - `part_0`: `graphics.drawImage(N[E[ay][0][0]], at + E[ay][0][1], au - E[ay][0][2], 0);` $\to$ Vẽ `N` (**HEAD**) tại offset Y cao nhất (~31px).
  - `part_1`: `graphics.drawImage(ci[E[ay][1][0]], at + E[ay][1][1], au - E[ay][1][2], 0);` $\to$ Vẽ `ci` (**LEGS**) tại offset Y thấp nhất (~8px).
  - `part_2`: `graphics.drawImage(cd[E[ay][2][0]], at + E[ay][2][1], au - E[ay][2][2], 0);` $\to$ Vẽ `cd` (**BODY**) tại offset Y trung gian (~14px).
- **Kích thước nhân vật**: Rộng 22 px, cao 32 px (`az = 22; aA = 32;`). Bán kính hitbox `aB = 11, aC = 16`.

---

## 4. Hệ Thống Bản Đồ (Maps & World Topology)

Ninja School 2 mở rộng thế giới lên tới **21 địa danh chính thức** và hơn 50 cấu hình màn chơi trong `j.java`:

| Map ID | Tên Bản Đồ | Đặc điểm địa hình & Chức năng |
| :---: | :--- | :--- |
| **0** | Trường Hirosaki | Khuôn viên trường học trung tâm. |
| **1** | Khu luyện tập | Bãi tập đánh bù nhìn, quái sơ cấp. |
| **2** | Thác nước | Địa hình leo trèo, vách đá nước chảy. |
| **3** | Làng Suzuya | Ngôi làng dân cư dưới chân núi. |
| **4** | Làng Tone | Ngôi làng giáp ranh cánh đồng. |
| **5** | Vách đá Ichidai | Địa hình dốc đứng cheo leo. |
| **6** | Đỉnh Ichidai | Đỉnh núi cao tuyết phủ. |
| **7** | Đồi Kokoro | Vùng đồi thoai thoải nối các làng. |
| **8** | Cánh đồng Fuki | Cánh đồng rộng lớn, chiến trường cutscene phân cảnh 3. |
| **9** | Trường Haruna | Trường đào tạo ninja đối thủ / giao lưu. |
| **10** | Ký túc xá | Nơi nghỉ ngơi và mua sắm vật phẩm. |
| **11** | Hang Aka | Hang động tối có nhũ đá và quái dơi. |
| **12** | Suối NN Akagi | Suối nước nóng hồi phục thể lực. |
| **13** | Bờ biển | Vùng chuyển tiếp sang đảo. |
| **14** | Làng chài | Cảng thuyền đánh bắt cá. |
| **15** | Biển | Bản đồ mặt nước di chuyển bằng thuyền/đứng nước. |
| **16** | Đảo Sasayaku | Hòn đảo hoang biệt lập. |
| **17** | Hang Uno | Hang đá sâu có quái xương ma. |
| **18** | Đồng hoang | Vùng đất khô cằn nguy hiểm. |
| **19** | Đỉnh gió | Vùng núi gió bão, địa hình nhảy khó. |
| **20** | Miếu Chakumi | Ngôi miếu cổ linh thiêng chứa bí mật kết thúc. |

---

## 5. Hệ Thống Quái Vật (29 Loại Quái Vật)

- **Nguồn đối chiếu**: `analysis/NinjaSchool2/sources/a.java`:
  Danh sách 29 loài quái vật nguyên bản của Ninja School 2:
  1. `[0]`: Nhím nâu
  2. `[1]`: Thỏ trắng
  3. `[2]`: Cóc vàng
  4. `[3]`: Bọ cánh cứng
  5. `[4]`: Lật đật
  6. `[5]`: Ốc ma đen
  7. `[6]`: Bò cạp
  8. `[7]`: Bọ xít
  9. `[8]`: Khỉ lông nâu
  10. `[9]`: Bù nhìn
  11. `[10]`: Cào cào
  12. `[11]`: Ninja gió
  13. `[12]`: Ốc bươu vàng
  14. `[13]`: Dơi
  15. `[14]`: Ma xương
  16. `[15]`: Rùa
  17. `[16]`: Cua
  18. `[17]`: Ốc mượn hồn
  19. `[18]`: Ma dù
  20. `[19]`: Mực nhảy
  21. `[20]`: Cá nhám xanh
  22. `[21]`: Rắn cạp nong
  23. `[22]`: Heo rừng
  24. `[23-24]`: Hồn ma (2 biến thể)
  25. `[25]`: Quỷ đầu lâu
  26. `[26]`: Rùa già (Boss)
  27. `[27]`: Bọ núi
  28. `[28]`: Ốc tiền sử (Boss cổ đại)

---

## 6. Lưu Ý Dịch Ngược & Cảnh Báo CFR

- Trong `q.java`, CFR báo cảnh báo: `/* Illegal identifiers - consider using --renameillegalidents true */`. Một số tên hàm bị trùng định danh hoặc dùng từ khóa Java (`if`, `do`, `goto`). Cần đối chiếu bytecode `javap` khi biên dịch lại sang ngôn ngữ hiện đại (GDScript / C#).
- Mọi kết luận ở đây dựa trên phân tích mã nguồn tĩnh, cần được kiểm chứng qua các kịch bản chạy thực nghiệm.

