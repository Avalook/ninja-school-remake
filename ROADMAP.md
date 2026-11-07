# Ninja School PC — Lộ trình làm lại từ đầu đến cuối

Mục tiêu của chủ dự án: chơi lại Ninja School (bản Nokia) trên laptop, giữ nguyên luật chơi,
cốt truyện và quest, nhưng **làm lại toàn bộ phần nhìn**: từng nền, cây, cỏ, áo, mặt nhân vật,
chuyển động, hiệu ứng và giao diện. Thứ tự: game 1 xong hẳn rồi mới tới game 2, game 3.

Đây là kế hoạch duy nhất. Tiến độ thật ghi trong `STATUS.md`. Tài liệu cũ đã chuyển vào `archive/`.

Phân vai: **Claude Code** làm code, rig, animation, ghép art, test (`CLAUDE.md`).
**Codex** chỉ vẽ art vào `art_src/` (`AGENTS.md`). **Chủ dự án** duyệt mọi mục [DUYỆT].

---

## 1. Quyết định kiến trúc (đã chốt)

### Ngôn ngữ và engine
- **Godot 4.7 + GDScript có kiểu tĩnh** (`var x: int`, `func f() -> void`). Không cần C#:
  logic gốc chỉ tốn khoảng 0.5 ms/tick.
- Bản chính là `remake/` (viết lại theo kiểu native). `game/` (logic dịch tự động từ JAR) **không ship**.
  Nó chỉ còn là **oracle** để so kết quả, cùng với emulator JAR ở `analysis/claude/emu`.

### Tách Sim / View: luật bất biến nhất của dự án
Lý do: muốn vẽ lại mọi thứ thì phần nhìn phải thay được hoàn toàn mà không chạm vào luật chơi.

```
remake/sim/    Luật chơi. RefCounted thuần, không Node, không vẽ, không âm thanh.
               Tick cố định 25 Hz (1 tick = 40 ms, như bản gốc), tất định (dùng S.w như gốc).
               Mỗi tick nhận input -> cập nhật state -> phát danh sách event
               (hit, damage, spawn, die, drop, say, quest_update, sfx, camera...).
remake/view/   Phần nhìn. Đọc snapshot sim (tick trước / tick sau), nội suy ở 60–144 fps.
               Animation, FX, camera, ánh sáng, UI. Chỉ phản ứng theo event, không tự đổi luật.
remake/content/  Dữ liệu (map, quái, NPC, thoại, quest, shop) dạng JSON hoặc .tres, xuất từ bản gốc.
remake/art/      Asset đã qua pipeline. Nguồn gốc (blend, prompt, master) để ở art_src/.
```

Quy tắc bắt buộc:
1. **Hitbox và hurtbox lấy theo bản gốc** (player 22×32, vùng chém x∈[−20,+40], y∈[−47,+5]...).
   Art lớn hơn hay đẹp hơn cũng không được đổi vùng va chạm. Phím F3 bật overlay hitbox.
2. **Frame trúng đòn của animation phải khớp tick trúng đòn gốc.** Animation chạy nhanh hay chậm tuỳ ý,
   nhưng frame "chém trúng" phải hiện đúng tick sim phát event `hit`.
3. **Di chuyển** (state 1–4: đứng, chạy, nhảy, rơi) được làm hiện đại (gia tốc, coyote, buffer). Đây là
   chỗ duy nhất được lệch khỏi bản gốc, và phải bật/tắt được bằng cờ `--classic`.
   Combat, AI quái, sát thương, rơi đồ, EXP, quest, cutscene, shop, save thì **giống bản gốc 100%**.

### Kiểm chứng (cổng bắt buộc cho mọi thay đổi trong sim/)
- `tools/diff_run.sh` chạy cùng một script input trên oracle và trên `remake/sim`, rồi so từng tick:
  HP/MP/tiền/EXP/level, quest `O`/`fF`, mảng quái `jN jO kc jS`, boss, cờ cốt truyện.
  Vị trí người chơi chỉ so ở các mốc checkpoint (vì di chuyển đã hiện đại hoá).
- Sim phải chạy headless, không cần cảnh Godot: `godot --headless --script res://tests/sim_run.gd`.
- Test chạy trước mỗi commit đụng tới `sim/`. Đỏ thì không merge.

---

## 2. Tiêu chuẩn đồ hoạ (art bible)

Viết đầy đủ trong `art_src/ART_BIBLE.md` ở mốc M3. Các thông số khung đã chốt:

| Hạng mục | Thông số |
|---|---|
| Khung nhìn logic | 480×270 px gốc (rộng gấp đôi bản Nokia 240) |
| Hệ số art | **4×**: 1 px gốc = 4 px art, vừa khít 1920×1080. Master lưu ở 8× để sau này lên 1440p/4K |
| Tile | 24 px gốc → 96 px art, có kiểm tra đường nối tự động |
| Nhân vật | Cao khoảng 32–47 px gốc → 130–190 px art. Chân đặt tại (fh, fi) |
| Animation | Rig xương 2D chạy theo fps màn hình, chọn animation theo sim state (A, B) qua state machine của view |
| Hướng | Vẽ một hướng (phải), lật cho hướng trái. Vũ khí cầm tay có thể cần sửa riêng |

### Pipeline nhân vật và quái (một pipeline duy nhất)
**Codex vẽ tách bộ phận → rig xương 2D (cutout) trong Godot → animation.**
Lý do: art Codex HD trong `art/generated/codex_hd` đã đạt style anime đẹp. Rig xương thì giữ mặt và áo
nhất quán qua mọi frame, và chuyển động mượt ở bất kỳ fps nào.
- Mỗi nhân vật: 1 ảnh tổng (reference) + 1 sheet bộ phận tách rời (đầu, tóc, mặt và biểu cảm, thân, tay trên,
  tay dưới, chân trên, chân dưới, vũ khí), đều nền trong suốt, cùng tỷ lệ 4×.
  Mẫu đã có: `npc*_parts.png`.
- Rig bằng `Skeleton2D` + `Bone2D` (hoặc Polygon2D có skin để uốn mềm), animation bằng `AnimationPlayer`.
  Rig dùng chung cho cast người; quái và boss có rig riêng theo dạng.
- Quái ít động tác (ma, hồn lửa...) có thể dùng thẳng sprite sheet Codex (như `mob00_sheet.png`),
  nhưng phải qua kiểm tra anchor và tỷ lệ.
- Blender chỉ dùng cho nền và môi trường khi cần chiều sâu. Bỏ hero Blender, Veo và Scale4x.
  Hiện repo đang trộn 5 nguồn art khác nhau (Blender, Codex HD, Veo, Scale4x, FLUX) nên không đồng nhất.
  Từ M3 trở đi tất cả quy về pipeline này.
- Prompt Codex lưu trong `art_src/prompts/`, luôn kèm ảnh reference style đã duyệt và ảnh gốc của nhân vật
  đó, để giữ nhận diện và đồng nhất style.

### Nền và môi trường
- Mỗi biome gồm 4–6 lớp parallax vẽ mới, có biến thể ngày/đêm, cộng tileset 96 px vẽ lại
  và decor động (cỏ lay, lá rơi, sương, nước). Đường đi và vật tương tác phải nổi rõ hơn nền.
- Nguồn: concept AI theo style đã duyệt → vẽ lại, chỉnh trong Blender/2D → kiểm tra seam và parallax.

### UI
- Theme Godot riêng, 9-slice, font tiếng Việt rõ ở 1080p. Không kéo giãn layout điện thoại.
- Làm lại: màn tiêu đề, HUD, hộp thoại (có chân dung nhân vật), menu, túi đồ, shop, bản đồ thế giới,
  cài đặt và đổi phím, màn chết, màn kết.

---

## 3. Các mốc (game 1)

Mỗi mốc có điều kiện qua mốc. Mốc nào có **[DUYỆT]** thì phải gửi ảnh hoặc video cho chủ dự án,
và chỉ qua mốc khi chủ dự án đồng ý. Claude không tự đánh giá "đẹp".

### M0 — Dọn repo (1 phiên)
- [x] Chuyển `games/ninja1`, `games/ninja1-original` vào `archive/`.
- [x] Chuyển `CLAUDE_TASK.md`, `START_HERE_CLAUDE.md`, `PROJECT_KNOWLEDGE.md`, `REVIEW_FLAGS.md`,
      `analysis/PC_REMAKE_PLAN.md`, `remake/REMAKE_PLAN.md` vào `archive/docs/` (giữ lịch sử, không xoá).
- [x] Tạo `STATUS.md` từ `analysis/claude/progress.json` và kiểm tra thực tế.
- [x] Bỏ đường dẫn tuyệt đối `/Users/quangdang/...` khỏi `export_presets.cfg`.
- [x] Đưa file lớn (blend, mp4, png nguồn) sang Git LFS hoặc vào `.gitignore` nếu sinh lại được.
      _(Chủ dự án chốt 2026-11-07: KHÔNG dùng LFS, không viết lại lịch sử. `review/**/*.mp4` vào
      `.gitignore`: video review chỉ giữ local, ảnh chụp review vẫn commit.)_
- Qua mốc: `godot --path remake` chạy được, `game/` vẫn chạy làm oracle, test cũ vẫn xanh.

### M1 — Tách Sim / View trong remake/
- [x] Tách `player.gd` (đang vừa là luật vừa là Node2D vẽ) thành `sim/player_sim.gd` và `view/player_view.gd`.
      _(2026-11-07, nhánh `m1-sim-view`. Bằng chứng: `remake/tests/player_trace.sh` 10 kịch bản khớp 100%, xem STATUS.md.)_
- [ ] Làm tương tự cho enemies, story, world (va chạm), drops, projectiles. _(Quá lớn cho một phiên, chia 5 mục con:)_
  - [x] Enemies: quái thường, 3 boss, hội bạn cùng lớp, và projectiles (phi tiêu, ám khí, hạt) vì
        projectiles trúng quái/boss/bạn học trong cùng một vòng lặp → `sim/enemies_sim.gd` + `view/enemies_view.gd`.
        _(2026-11-07, nhánh `m1-enemies-split`. Trace 14 kịch bản + oracle 12 map + 16 ảnh khớp 100%, xem STATUS.md.)_
  - [ ] Story: `story.gd`, `npc_quests.gd`, `cutscene_player.gd`, `menus.gd` (luật quest/cutscene tách khỏi UI).
  - [ ] World (va chạm): `world.gd` (cờ ô `flags_at`, lối ra, camera mà luật đọc), `objects.gd`, `npcs.gd`.
  - [ ] Drops: `drops.gd` (xu, bình, bí kíp; luật `w % 20` đã ở sim enemies, phần nhặt và vẽ còn trộn).
  - [ ] Projectiles: phần còn lại sau mục Enemies (nếu có), ví dụ phi tiêu của bạn đồng hành `hf` (TODO trong code).
- [ ] Viết `sim/sim.gd`: hàm `step(input) -> events`, kèm snapshot và restore (phục vụ save và test).
- [ ] Viết `tests/sim_run.gd` chạy headless, đọc cùng định dạng script với `tools/diff_run.sh`.
- [ ] Di chuyển native (state 1–4) chạy theo bước cố định 100 Hz (4 bước mỗi tick 25 Hz), không theo dt của frame.
      View nội suy giữa hai bước khi vẽ. Mục tiêu: kết quả như nhau ở 60/120/144 Hz.
      Test: chạy cùng kịch bản ở `--fixed-fps 60` và `--fixed-fps 144` phải ra trace giống nhau
      (`tests/player_trace.sh`). Đổi bước là đổi hành vi di chuyển nên phải ghi mốc trace mới, ghi rõ lý do.
- [ ] Mọi thứ chạy theo thời gian trong view (mũi tên "tiếp" hộp thoại nhấp nháy, hiệu ứng, ngày/đêm, seed FX)
      dùng đồng hồ của game (tick/frame của game) thay vì giờ thật (`Time.get_ticks_msec`, `randf()` không seed),
      để ảnh test lặp lại được 100%. Test: chạy `tests/enemies_shots.sh` hai lần không cần bước cố định bộ đếm
      ngày/đêm, ảnh phải trùng từng pixel.
- Qua mốc: intro map99 → map4 và quest 0 có diff bằng 0 so với oracle trên các trường luật;
  view chạy giống trước khi tách.

### M2 — Hoàn thiện Sim game 1
Theo thứ tự, mỗi mục kèm diff test riêng:
- [ ] Combat: combo, phi tiêu, Xích Mao, ẩn thân, độn thổ, bị đánh, knockback, chết và hồi sinh.
  - [ ] Sửa Xích Mao: `_common_actions()` đặt `S.A = 23` nhưng `_publish()` cuối `_native()` ghi đè về 1/2,
        nên không bao giờ biến hình. Đối chiếu `T()` và khối input trong `a.java`, có diff test.
  - [ ] Tìm cờ kỹ năng Xích Mao thật trong `a.java`, tách khỏi `eT` (cờ cốt truyện "Toniku thành sát thủ"
        mà `story.gd` dùng).
  - [ ] Bổ sung kịch bản `tests/player_trace.sh` phủ xe máy đứng/chạy (A = 18/19) và Xích Mao (A = 23).
- [ ] Toàn bộ loại quái, AI, spawn và respawn, rơi đồ (luật `w % 20`).
- [ ] Các boss (hiện có `boss_boar`, `boss_golem`, `boss_river`; đối chiếu với nguồn gốc xem đã đủ chưa)
      và hội bạn cùng lớp.
  - [ ] Thêm kịch bản trace trận golem đầy đủ (có cutscene mở trận, `hu = 1`), không chỉ golem đứng chờ
        như kịch bản m95 hiện tại. Đối chiếu oracle (`ACT_95`).
- [ ] NPC, thoại, 18 quest, cutscene `D(n)`, title card.
- [ ] Vật phẩm, shop, bình HP/MP, kỹ năng, lên cấp.
- [ ] Bơi, thang/dây leo, đất đào được, xe, vật thể, lối ra của cả 24 map.
- [ ] Save/load (định dạng mới dạng JSON, có version) và màn kết.
- Qua mốc: script chơi trọn game 1 (bot cộng teleport), fuzz 22 map, trận boss và chuỗi kết thúc
  đều có diff bằng 0 trên các trường luật.

### M3 — Art bible + vertical slice map 4 rừng trúc (chạy song song với M1–M2) [DUYỆT]
- [ ] (Codex) 3 style frame khác nhau cho cùng một cảnh (nhân vật chính đứng trong rừng trúc) → chủ dự án chọn 1.
      Style Codex hiện tại (`npc1_ayumi.png`) là một ứng viên.
- [ ] Rig thử nhân vật chính bằng Skeleton2D: chạy, nhảy, combo chém. Duyệt độ mượt trước khi rig cả cast.
- [ ] Viết `ART_BIBLE.md`: bảng màu, độ dày nét, kiểu đổ bóng, tỷ lệ đầu/thân, ánh sáng ngày/đêm.
- [ ] Nhân vật chính đủ bộ animation: đứng, chạy, nhảy, rơi, combo chém, ném phi tiêu, bị đánh, chết,
      ngồi, bơi, leo, đào, Xích Mao, ẩn thân.
- [ ] Các loại quái xuất hiện ở map 4, 1 NPC, chân dung hội thoại.
- [ ] Map 4 hoàn chỉnh: parallax, tileset, decor động, sương, ánh sáng.
- [ ] HUD, hộp thoại và FX chém/bụi/tia lửa đúng style.
- Qua mốc: xuất bản build chơi được map 4 trên laptop của chủ dự án và được duyệt.
  **Chưa qua mốc này thì không sản xuất hàng loạt.**

### M4 — Sản xuất art theo manifest [DUYỆT theo đợt]
- [ ] `art_src/manifest.json` liệt kê mọi asset cần làm, lấy từ dữ liệu gốc (mọi NPC, quái, boss,
      vật phẩm, biome, màn UI), kèm trạng thái: todo / draft / duyệt.
- [ ] Quy trình cho mỗi nhân vật hoặc quái: **model sheet trước** (trước/nghiêng/sau, 4–6 biểu cảm, bảng màu
      có mã hex) → chủ dự án duyệt → mới vẽ tách bộ phận và rig. Mọi lần gen sau đều đính kèm model sheet
      làm ảnh reference, không gen chỉ bằng chữ. Có sai lệch thì sửa model sheet, không vá từng ảnh lẻ.
- [ ] Mỗi asset phải qua kiểm tra tự động: màu nằm trong palette, kích thước theo hệ số 4×, anchor chân, alpha sạch,
      đủ frame, hit frame khớp, tile không lộ seam.
- [ ] Mỗi đợt xuất contact sheet và video ngắn trong game để duyệt.
- Thứ tự đợt: cast chính và NPC → quái thường → boss → 14 biome ngày/đêm → icon vật phẩm → toàn bộ màn UI → FX.

### M5 — Đánh bóng
- [ ] Camera mượt, hitstop, rung màn hình nhẹ, hạt (lá, bụi, nước), ánh sáng 2D.
- [ ] Âm thanh: phối lại nhạc gốc (từ MIDI), SFX mới.
- [ ] Cài đặt: đổi phím, âm lượng, fullscreen, hỗ trợ tay cầm, tuỳ chọn `--classic`.
- [ ] Đo hiệu năng bằng overlay F3: 1080p ổn định 60 fps.

### M6 — Ship game 1 [DUYỆT]
- [ ] Export bản Windows và Mac, chạy trên laptop thật của chủ dự án.
- [ ] Chủ dự án chơi trọn game, bug ghi vào `BUGS.md`, sửa đến khi sạch.

## 4. Game 2 và game 3 (sau khi M6 xong)
1. Mở rộng transpiler và emulator cho JAR nhiều class (22 và 19 file) → có oracle cho từng game.
2. Lặp lại M1–M2 cho sim của game đó, dùng chung engine, chỉ khác content.
3. Tái dùng rig, art bible và UI. Chỉ làm thêm asset mới, không thay nội dung game 2/3 bằng của game 1.

---

## 5. Cách mỗi phiên Claude Code làm việc

1. Đầu phiên: đọc `CLAUDE.md` → `STATUS.md` → mục đang làm trong `ROADMAP.md`.
2. Mỗi phiên chỉ làm **một mục checkbox**, trên branch `m<số>-<tên-ngắn>`.
3. Thay đổi trong `sim/` phải có test diff mới hoặc cập nhật test, chạy xanh rồi mới commit.
4. Thay đổi về hình ảnh phải kèm ảnh chụp (`tools/shoot.sh`) hoặc video `--write-movie` trong `review/<ngày>/`.
5. Cuối phiên: cập nhật `STATUS.md` (đã xong gì, bằng chứng nằm ở đâu, việc tiếp theo, còn chưa rõ gì),
   tick checkbox, commit.
6. Không tạo project Godot mới, không tạo thư mục song song, không ghi đè `game/` (oracle).
7. Chạy song song nhiều agent chỉ khi mỗi agent làm thư mục riêng (ví dụ: một agent `sim/`, một agent `art_src/`).
8. Không đoán luật. Chỗ nào chưa rõ thì đọc `analysis/NinjaSchool1/sources/a.java`, chạy oracle để xem,
   ghi vào `STATUS.md` mục "chưa rõ".
