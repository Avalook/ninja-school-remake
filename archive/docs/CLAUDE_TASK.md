# Xây dựng game ninja PC mới — bản phát triển độc lập

Người dùng giao bạn triển khai một game ninja phiêu lưu hành động 2D HD/2.5D hoàn chỉnh từ bộ đặc tả chức năng, bản đồ, dữ liệu nhân vật, nhiệm vụ và tài nguyên cung cấp trong workspace này. Làm ngay thành sản phẩm chạy được; không chỉ viết kế hoạch.

## Phạm vi sản phẩm

Ưu tiên hoàn thành Ninja School 1: toàn bộ luồng mở đầu, từng màn/bản đồ, chuyển cảnh, nhân vật, quái và AI, chiến đấu, chỉ số/kỹ năng, NPC/hội thoại, nhiệm vụ/phần thưởng, đồ/vũ khí/shop, save/load, chết/chơi lại và kết thúc theo thiết kế. Dùng dữ liệu của phần2/3 làm tài liệu cho các giai đoạn sau, không trộn nội dung sai vào phần1. Tiếp tục phần1 đến khi người dùng chơi trọn game mượt. Không coi một arena/map viewer hoặc một ảnh đẹp là game hoàn chỉnh.

## Nguồn thiết kế và độ chắc chắn

Đọc analysis/antigravity/logic-NinjaSchool1.md, story-and-quests.json, behavior-contracts.json, asset-usage.json, unresolved.json và progress.json. Đây là đặc tả tham chiếu có trích dẫn, còn khoảng214679 byte NS1 chưa đọc trong ledger Antigravity, nhiều hành vi chưa kiểm runtime. Không tự coi tất cả đã xác minh. Dữ liệu Java đi kèm là implementation tham chiếu, chỉ sử dụng để giải quyết phần đặc tả thiếu/mâu thuẫn khi cần. Giữ nguyên raw JAR, sources và ảnh gốc; không sửa dữ liệu để che lỗi implementation mới.

Antigravity đã sửa vai trò sprite ch=head(er), cl=legs(et), cb=body(eg). Mốc aB[] là tick tích lũy trong sequence, không phải delay độc lập. Vẫn cần xác nhận khi đưa vào engine. Gặp unknown thì khảo sát đúng phần cần thiết và tiến hành phần độc lập; ghi rõ giả định, không bịa ra nhiệm vụ thay thế. Tài liệu/checkpoint cũ có thể chậm hơn artifact hiện có.

## Engine và hình ảnh

Tiếp tục từ games/ninja1-original/project.godot đã có map99→map4, nhân vật ghép từ ảnh gốc, điều khiển/collision/basic melee và thẻ mở đầu đầu tiên. Có thể refactor thành sản phẩm mới, giữ lại phần hoạt động đúng. Dùng Godot4.7.2 trên Mac; binary sẵn đọc được tại /Users/quangdang/Downloads/ninja/.tools/godot/Godot.app/Contents/MacOS/Godot. Đừng sửa workspace gốc.

Đồ họa mong muốn: anime2D nét, nhân vật và chuyển động nhất quán, môi trường nhiều lớp/2.5D, camera mượt, ánh sáng/bóng, hiệu ứng vệt kiếm/va chạm/bụi chân/lá/sương/nước phù hợp. art/generated có hai mẫu nhân vật và một lớp nền núi; nhân vật cloud có alpha thật. Chúng là mẫu thiết kế, chưa đủ sprite animation. Dùng art/asset-manifest.json và ảnh gốc để làm đủ frames/rig/anchors/crop/mirror/tiles; giữ vùng va chạm và thời điểm hit theo contract gameplay. Không kéo giãn một PNG thành mọi chuyển động.

Blender5.2.3 đã cài ở /Applications/Blender.app/Contents/MacOS/Blender hoặc PATH. scripts/run_blender_stage.py tạo art/blender/ninja1-art-stage.blend; đã render và pack texture, chỉ là stage2plane chưa rig. Dùng được để phát triển asset/animation/môi trường; không nhầm nó là game.

## Giao diện, điều khiển và máy đích

Mac chạy được trước; sau đó Windows11x64. Windows đích: i5-11400H,16GB RAM,GTX1650 4GB,1080p. Mục tiêu1080p60FPS cần đo thực tế. Mặc định A/D hoặc ←/→ đi, W/Space nhảy, J đánh; cho đổi phím, pause/settings, fullscreen/windowed, UI phù hợp màn16:9. Phát triển bằng Opus/session Claude hiện tại đã được người dùng giao.

## Cách thực hiện và bàn giao

Chỉ ghi thay đổi trong /Users/quangdang/Downloads/ninja-claude-parallel hoặc worktree của repo này. Không ghi /Users/quangdang/Downloads/ninja hay thư mục dự án khác. Lưu checkpoint riêng analysis/claude/progress.json, mô tả artifact thực và việc tiếp theo. Thực hiện code và chạy app/test phù hợp, kiểm input/native visuals/progression/save, export Mac .app thật. Windows phải kiểm trên Windows thật trước claim đã chơi được trên Acer. Không trả lời full game nếu còn thiếu hệ thống/nội dung. Khởi đầu bằng kiểm trạng thái và đặc tả NS1, rồi làm màn mở đầu có quái/đối thoại/chuyển cảnh đúng, tiếp tục nối đủ nội dung, polish và đóng gói. Không dừng ở bản kế hoạch nếu có thể tiến hành.
