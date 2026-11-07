# Toàn bộ dữ liệu hiện có — Ninja School 1, 2, 3

Gói này đầy đủ dữ liệu đã thu thập tại thời điểm đóng gói. KHÔNG có nghĩa toàn bộ logic đã được hiểu, đối chiếu hay triển khai. Không được báo “clone hoàn tất” từ các prototype hoặc ghi chú.

## Kiểm kê thực tế

| Game | File Java | Dòng Java | PNG gốc | Byte có ghi chú lượt đầu / toàn bộ | Logic đã kiểm chứng toàn bộ |
|---|---:|---:|---:|---:|---|
| NinjaSchool1 | 4 | 15,854 | 362 | 123,373/610,099 | Chưa |
| NinjaSchool2 | 22 | 20,951 | 578 | 0/764,133 | Chưa |
| NinjaSchool3_bypass | 19 | 25,307 | 622 | 0/915,976 | Chưa |

Tổng: 45 Java, 62.112 dòng, 1.562 PNG. 1.357 SHA ảnh khác nhau; ảnh trùng có thể dùng chung. Ghi chú lượt đầu bao phủ khoảng 5,4% tổng số byte Java ba game; không phải “5,4% logic đã đúng”. Xem SOURCE_INDEX.json/CSV, ASSET_INDEX.json/CSV và analysis/source-reading-progress.json cho từng file/hash/range.

## File gốc và dịch ngược

- Ba JAR gốc tại root; đối chiếu ORIGINAL_JAR_HASHES.json. Không sửa.
- Mã CFR tại analysis/<game>/sources/. Decompile status/log và REA inspections tại từng thư mục game.
- Tài nguyên thô tại analysis/<game>/resources/, inventory.json ghi nội dung JAR.
- analysis/png-assets-checks.json đã kiểm 362/578/622 PNG khớp từng file trong JAR, không mismatch tại lượt kiểm trước.
- Việc xuất mã Java đã làm cho cả ba game. Hiểu trọn luồng gọi, sửa decompiler, đối chiếu bytecode/runtime chưa hoàn tất. Nguồn có tên biến làm rối và có thể có lỗi CFR; không coi Java xuất ra là source chuẩn tuyệt đối.

## Những gì đã có ở Ninja School 1

- Giải mã 24 lưới bản đồ gốc, tổng 26.571 ô. MAP_INDEX.json liệt kê từng map/dimensions/source. Chưa phải 24 màn chơi đã hoàn thiện.
- Atlas 24×2880, tile 24×24; mapping tile cơ sở và danh sách tile ground đã đối chiếu vài đường gọi. Còn thiếu semantic collision đầy đủ, hazard, nước, runtime tile transforms, object/NPC/exit theo từng map.
- games/ninja1-original là project Godot phục dựng đang dùng. Main scene original_map.tscn bắt đầu map99, lưới 70×15, spawn(108,312), quay trái.
- Nhân vật ghép đầu/thân/chân bằng PNG gốc cho idle/walk frames0–6. analysis/ninja1-character-loading.json nêu resource patterns và evidence. Chưa đủ attack/skill/full character animations.
- Di chuyển, nhảy, collider nền và camera theo dõi đã có; một số tham số quy đổi từ nhịp 40ms/25Hz. Chưa chứng minh tương đương toàn bộ vật lý gốc.
- J/keypad5 có basic melee, cửa sổ animation tạm, hit tick6, bounds theo nguồn, base damage/weapon/buff formula và knockback. Chỉ được test với fixture, chưa có enemy actor thật. Vệt chém là tạm và global tick variance đang gần đúng.
- Lối phải map99: fh>=iR-24 tức x>=1656, js=4, entry map4=(48,120), quay trái. Test có ca x1655 chưa chuyển và x1656 chuyển. Chưa đi hết map bằng chuỗi input thực tế.
- Vào map4 gốc 80×15; n(4)&&eH trong nguồn gọi D(0). Mới hiển thị title aH[1] “2 ngày sau, tại rừng trúc...” sau0,2s. Đây là card tĩnh mới, chưa scroll/timeline synthetic inputs/dialogue/gates/camera của D(0). Card chưa kết thúc theo sequence nguồn.
- Mac preview 0.3.0 Universal2 đã export; bundled executable chạy120frame headless. Native GUI QA mới của route/card chưa có. Project tests: res://tests/smoke.gd và res://tests/map_transition.gd.
- games/ninja1 là arena kỹ thuật cũ hình khối, người dùng không chấp nhận đồ họa đó. Giữ làm lịch sử; không dùng thay gameplay gốc.
- Windows prototype cũ chỉ là arena, không phải game1/2/3 hoàn chỉnh. Chưa chứng minh chạy trên Acer.

## Những gì CHƯA có đầy đủ

Chưa có full enemy AI, sprite/spawn mapping, HP/hit/death/drop, NPC interaction, quest state machines, shops/items/equipment, skills, save/load, toàn bộ hội thoại/cốt truyện/điều kiện kết thúc. Game2/3 chưa dựng thành bản chơi. Chưa hiểu từng nhân vật, từng ải và chưa có contract ảnh cho mọi frame. Tên vai trò trong asset catalog chỉ được xem là verified khi có role_evidence; các nhóm theo đường dẫn chỉ là gợi ý.

## Đồ họa mới và Blender

- art/references: contact sheets cho toàn bộ ảnh gốc, reference ghép player idle frame0. Chưa manual review từng ảnh.
- art/generated/ninja1-hero-master-v1.png: mẫu nhân vật do FLUX.2 Klein4B local sinh; RGB512×512, chưa alpha/rig/animation.
- art/generated/ninja1-mountain-layer-v1.png: mẫu lớp núi local1024×512, chưa kiểm seamless/parallax trong game.
- art/generated/ninja1-hero-cloud-v1.png: mẫu nhân vật công cụ image_gen theo yêu cầu user, RGBA1254×1254, alpha thật. Giữ nhận diện nhưng là thiết kế mới; chưa chia multipart/rig/frames và chưa thay sprite trong Godot.
- art/prompts chứa brief/prompt và art/generation-jobs.json lưu local sample settings (seed4211,4steps). Không có full bộ ảnh mới.
- Blender5.2.3 chính thức đã cài trên Mac. scripts/run_blender_stage.py điều khiển Blender background/Python, tạo art/blender/ninja1-art-stage.blend và art-stage-preview.png, texture được pack vào blend. Hai planes ở các độ sâu là scene thử kết nối, chưa có rig/3D character/terrain/gameplay.
- art/blender/connection-check.json là bằng chứng run mới. Render path trong blend là //art-stage-preview.png cho portable. Runner ghi running/failed/passed để không dùng success cũ khi rerun lỗi.
- Hướng art đang đề xuất: nhân vật anime2D và môi trường nhiều lớp/2.5D, giữ logic và hitbox có chứng cứ riêng với phần hiển thị. Chưa chốt style toàn bộ hay duyệt thay từng ảnh.

## Phối hợp

Antigravity được người dùng trực tiếp giao phân tích full logic trong phiên Gemini3.8Flash High. Đã quan sát “Agent working” khi gửi. Kết quả chỉ ghi analysis/antigravity/, không sửa games/builds/art hoặc checkpoint chung. Thư mục có thể chưa có kết quả tại thời điểm snapshot. Xem analysis/ANTIGRAVITY_HANDOFF.md. Không tính dispatch là công việc đã hoàn tất.

Không có lịch tự động Codex hiện hành: lịch trước đã bị xóa khi user clear goal. Trạng thái app/worker phải kiểm lại PID thật trước khi chạy, không tạo trùng.

## Máy đích và acceptance

Mac mini M4 Pro48GB dùng phát triển/sinh ảnh; Mac chơi trước. Windows11Pro Acer Nitro5 i5-11400H,16GB,GTX1650 4GB,1080p là đích sau. Mục tiêu gameplay mượt, phím đổi được, UI màn rộng, đầy đủ nội dung ba game, art thống nhất. 1080p60 là mục tiêu, chưa có benchmark chứng minh.

Tiêu chí bàn giao toàn bộ ở analysis/PC_REMAKE_PLAN.md. Bắt buộc đối chiếu game/JAR gốc, không thay bằng quest tự bịa hoặc một arena giống phong cách ninja. Chưa yêu cầu nào của full cả3 game đã đạt trọn vẹn.

## Chạy tiếp

Mở games/ninja1-original/project.godot bằng Godot4.7.2. Dùng A/D hoặc mũi tên để đi, W/Space nhảy, J/keypad5 đánh, Esc thoát. Bộ test Godot dùng --headless --path games/ninja1-original --script res://tests/smoke.gd hoặc map_transition.gd. Blender harness: python3 scripts/run_blender_stage.py. MFLUX cài riêng trong .tools/imagegen-venv trên máy hiện tại; virtualenv/model weights không nằm trong ZIP. REA/CFR/Godot cần setup lại nếu chuyển máy; xem TOOLING.md.

## Cập nhật Antigravity trong snapshot

Antigravity đã xuất các file story-and-quests.json, asset-usage.json, progress.json. Chưa nghiệm thu và đã phát hiện mapping cơ thể bị đảo cùng nhãn thời gian cutscene cần sửa. Đọc REVIEW_FLAGS.md trước khi dùng các file này, kể cả khi progress của Antigravity tự ghi verified.
