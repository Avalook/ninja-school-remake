# Giao việc cho Claude — remake Ninja School đầy đủ

Đọc PROJECT_KNOWLEDGE.md trước. Gói này bao gồm toàn bộ dữ liệu hiện có, không có nghĩa đã hiểu full game. Xem GAME_STATUS.json, SOURCE_INDEX.json, ASSET_INDEX.json và MAP_INDEX.json. Đọc analysis/PC_REMAKE_PLAN.md để giữ nguyên acceptance đủ ba game.

Người dùng muốn phục dựng toàn bộ Ninja School1/2/3 thành game PC, gameplay và cốt truyện/quest giống bản gốc, đồ họa/UI mới đẹp, phím phù hợp PC và remap được. Ưu tiên app Mac chạy mượt trước, rồi Windows11x64 cho Acer GTX1650 4GB/16GB. Tận dụng phần đúng đã có. Không dựng toy arena mới, không gọi report hoặc viewer là game hoàn chỉnh.

## Làm bản song song độc lập

Giải nén vào THƯ MỤC RIÊNG, ví dụ NinjaSchool-Claude-Parallel. Không ghi trở lại workspace Codex /Users/quangdang/Downloads/ninja. Dùng project games/ninja1-original làm điểm khởi đầu; giữ raw JAR/resources/sources bất biến. Không mở worker local trùng nếu đang dùng cùng máy, kiểm PID trước. Lưu tiến độ của bạn trong analysis/claude/ và implementation riêng của thư mục clone này.

## Công việc cần thực hiện

1. Kiểm checksum và inventory. Đọc các ghi chú đã có cùng exact coverage, không tưởng 45 Java đã được đọc hết. Rà các phần thiếu và caller/callee; xác minh CFR bằng javap/REA hoặc runtime J2ME khi mã Java sai.
2. Khôi phục full luồng NewGame map99→map4 D(0), actor coordinates/animations, source enemy/NPC/story/quest/item/shop/save/skill, rồi hoàn thành tất cả content game1. Đừng thêm quái đoán vào map99. Dựng các behavior contracts với file+dòng+confidence. Tham khảo output Antigravity nếu có nhưng phải đối chiếu.
3. Hoàn thành game2/3 với đúng khác biệt nguồn, không dùng nội dung game1 thay thế.
4. Từ art/references và asset-usage đã xác minh, chuẩn hóa HD art: identity/scale/anchor/alpha/crop, đầy đủ frames và tile seams. Các ảnh AI hiện chỉ mẫu. Chưa gắn mẫu single-character làm toàn bộ animation. Có thể dùng rig2D/Blender để tạo asset và môi trường; không thay hitbox/nhịp hành vi chỉ vì art mới.
5. Build app thực, kiểm native keyboard/collision/combat/progression/save/endgame. Export Windows sau Mac ổn, thử trên Windows thật trước claim Acer đạt. Kiểm performance/render/readability ở1080p. Lưu artifact và hướng dẫn cài/chơi.

## Bằng chứng và kết thúc

Mỗi tiến triển cần file thật, runtime/test đúng phạm vi và checkpoint. Phân biệt đã đọc, đã suy luận, đã đối chiếu và đã chạy. Không tuyên bố full logic/quest/character/map khi còn unknown, không report đầy đủ giả. Nếu mất phiên, checkpoint phải đủ cho lần tiếp nối; không dừng sau một bản mô tả kế hoạch. Không bỏ qua toàn bộ game2/3 trong acceptance.

## Dữ liệu quan trọng

- PROJECT_KNOWLEDGE.md: sự thật hiện tại và thiếu gì.
- analysis/project-state.json: checkpoint Codex ở thời điểm snapshot, có thể cũ hơn file actual; tin artifact/command output hơn mô tả.
- analysis/source-reading-progress.json: exact byte-range ledger và ghi chú lần đọc đầu.
- analysis/<game>/sources và resources: source CFR và assets gốc.
- games/ninja1-original: project đang chơi được từng phần.
- art/blender/ninja1-art-stage.blend: texture stage dùng kiểm nối Blender, chưa rig/gameplay.
- analysis/antigravity/: output bên phân tích nếu đã phát sinh, kiểm coverage/confidence trước khi dùng.
- PACKAGE_MANIFEST.json: hashes của từng file thực nằm trong ZIP này.
