# Bộ prompt Veo 3 + Codex cho Ninja School 1 PC

Ảnh chuẩn phong cách: `art/generated/ninja1-hero-cloud-v1.png` (gọi là **HERO REF**).
Ảnh sprite gốc từng nhân vật/quái: `art/prompts/codex/refs/*.png` (đính kèm khi gen để giữ nhận diện).
Trả kết quả về:
- Video Veo 3 → `art/incoming/veo/<mã>.mp4`
- Ảnh Codex → `art/incoming/codex/<mã>.png`

Sau đó báo tôi; pipeline sẽ tách nền, dựng rig Blender, xuất sprite và đưa vào game. Hitbox và nhịp đòn vẫn giữ theo logic gốc.

---

## PHẦN A — Veo 3: video tham chiếu chuyển động cho nhân vật chính

**Mục đích:** cho tôi (và bạn) thấy "mượt" kiểu Shadow Fight 3 cụ thể trông thế nào. Tôi dùng video làm **tham chiếu rotoscope**: trích từng khung bằng ffmpeg, tách nền xanh, đo góc tay/chân/kiếm và nhịp anticipation/follow-through, rồi chỉnh lại các tư thế và lò xo của rig xương trong game cho khớp.

Tôi không đưa thẳng khung Veo vào game: Veo hay đổi chi tiết trang phục giữa các khung. Nếu clip nào giữ được nhân vật ổn định, tôi có thể cắt khung làm sprite thử.

### A0. Cài đặt chung cho mọi clip
- **Chế độ:** Image-to-Video, frame đầu = HERO REF. Nếu Veo cho chọn tỉ lệ, dùng 16:9.
- **Thời lượng:** 8 giây, 24 fps, không cắt cảnh.
- **Âm thanh:** tắt, hoặc chỉ tiếng whoosh/gió nhẹ.
- **Tiền tố — dán đầu mọi prompt:**

```
2D side-scrolling fighting-game animation reference of the exact same chibi anime ninja boy from the input image (spiky black hair, red headband with two long tails, sleeveless royal-blue gi with gold trim and gold belt, black wristbands, baggy blue pants, gold ankle wraps, black boots). Strict side view, character faces RIGHT, orthographic-like flat camera, LOCKED-OFF STATIC CAMERA (no pan, no zoom, no shake), full body always fully inside the frame, feet on one flat horizontal ground line at 80% of frame height, character horizontally centered. Plain solid chroma-key green background (#00B140), no floor texture, no props, no shadows on the background, no other characters, no text. Crisp anime cel shading with clean dark outlines exactly like the input image, same proportions (about 2.8 heads tall), same costume and colours in every frame, no morphing, no extra limbs, no costume or weapon changes. Weighty, fluid martial-arts motion in the style of Shadow Fight 3: clear anticipation before every action, strong follow-through and overshoot after it, momentum carried through the hips, secondary motion on the headband tails, hair and baggy pants, natural easing (slow-in/slow-out), no robotic snapping.
```

- **Hậu tố tiếng Anh — dán cuối mọi prompt (negative):**

```
Avoid: camera movement, cuts, zoom, perspective change, 3/4 or front view, realistic 3D rendering, motion blur smears that hide the limbs, flickering details, changing hairstyle, changing outfit colours, holding the sword in the wrong hand, extra swords, background scenery.
```

### A1. Danh sách clip — dán sau tiền tố
Mã = tên file trả về.

1. `veo_idle_breath`
```
He stands in a relaxed fighting stance, knees slightly bent, fists loose, breathing calmly: chest rises and falls slowly, shoulders lift a little, headband tails sway gently in a light breeze, he blinks once. Loop-friendly: the last pose matches the first.
```

2. `veo_run_start_stop`
```
From the stance he leans forward and pushes off into a fast anime run toward the right edge but stays centered (run in place like a treadmill): arms pumping, knees high, torso leaning forward 10 degrees, headband tails streaming behind. After 4 seconds he brakes hard: the front foot plants, the body leans back, boots skid with a small dust puff, the arms swing forward to balance, then he settles back into the stance with a small bounce.
```

3. `veo_run_cycle_loop`
```
A clean, perfectly looping side-view run cycle in place (treadmill), 8 frames per stride, contact-down-passing-up poses clearly readable, arms opposite to legs, slight vertical bob of the head, headband tails trailing and fluttering.
```

4. `veo_jump_land`
```
He crouches down to anticipate (squash), springs straight up with arms swinging upward (stretch), reaches the apex with knees tucked, falls with arms out for balance, lands with a deep knee bend absorbing the impact (squash) and a small dust ring, then recovers to the stance.
```

5. `veo_double_jump_flip`
```
He jumps up, then at the apex performs a fast forward somersault: tucks into a tight ball and rotates 360 degrees twice in the air, unfolds, and lands lightly on the toes, recovering to the stance. The headband tails whip around the spin.
```

6. `veo_katana_combo`
```
He draws a curved katana from the hip and performs a two-hit combo: hit 1 - wind-up raising the blade back over the right shoulder, then a powerful diagonal downward slash forward with the hips rotating into it, blade ending low in front, a brief hold; hit 2 - he steps forward, pulls the blade back low, then sweeps a rising horizontal slash forward and upward, overshooting above the head, then returns the blade to a guard position. Clean bright arc trails along the blade path.
```

7. `veo_katana_combo_fast`
```
Same character performs a rapid four-slash katana chain while stepping forward slightly each time: down-slash, up-slash, horizontal slash, thrust. Each slash has a tiny anticipation and a crisp follow-through. After the last thrust he spins the katana once and returns to guard. Bright white-blue slash arcs.
```

8. `veo_shuriken_throw`
```
He reaches back, cocks the right arm behind the head, steps forward and whips the arm forward to throw a spinning steel shuriken to the right, the body follows through with the shoulder rolling forward, then he resets to the stance and throws a second one.
```

9. `veo_air_slash`
```
He jumps up and, while airborne, delivers a spinning katana slash in mid-air (a horizontal 360 degree spin with the blade extended), then a downward aerial slash, landing in a low crouch with the blade held behind.
```

10. `veo_hit_knockback`
```
He gets hit from the right by an invisible blow: the head snaps back, the body recoils and slides back a little with the arms flung up, a dizzy stunned face with spiral eyes for a moment, then he shakes it off and returns to the stance.
```

11. `veo_knockdown_getup`
```
He is struck hard, flies back in a short arc, lands on his back, bounces once, then rolls backward and kicks up to his feet into the fighting stance.
```

12. `veo_crouch_burrow`
```
He crouches low, places both hands together in a ninja hand seal, and quickly sinks into the ground up to the head (earth-hiding technique) with a small burst of soil, waits with only the head visible, then pops back up out of the ground.
```

13. `veo_stealth_fade`
```
He performs a ninja hand seal and his body fades into transparency until only two glowing eyes remain visible in the air, the eyes blink, then the body fades back in.
```

14. `veo_power_red`
```
He clenches his fists and powers up: a red-orange fiery aura bursts around him, his hair lifts slightly and gains red highlights, then he drops into an aggressive low stance with the aura flickering.
```

15. `veo_swim`
```
He swims horizontally to the right in place, as if underwater: breaststroke-like arm sweeps and frog kicks, bubbles rising, a short bamboo breathing tube in his mouth.
```

16. `veo_walk_talk`
```
He walks calmly in place (relaxed walk cycle), then stops, turns his head slightly toward the camera and makes a friendly talking gesture with one hand, then returns to the stance.
```

**Mẹo dùng Veo:**
- Nếu nhân vật bị đổi trang phục, thêm `same exact character as the input image in every single frame`.
- Thử 2–3 seed và giữ clip ổn định nhất.
- Clip nào nhân vật trôi khỏi tâm, gen lại với `stays centered, running in place`.

---

## PHẦN B — Codex / Image gen: nhân vật và quái HD cùng phong cách hero

### B0. Quy chuẩn bắt buộc
**Tiền tố — dán đầu mọi prompt:**

```
Character concept art for a 2D side-scrolling ninja game, same art style as the attached reference hero image (crisp anime cel shading, clean dark 2-4 px outlines, saturated but soft colours, chibi proportions about 2.8 heads tall). Full body, standing in a neutral relaxed pose, strict side 3/4 view facing RIGHT, arms slightly away from the body (so they can be cut out and rigged), legs slightly apart, feet flat on the ground. Transparent background (real alpha PNG), no shadow, no ground, no text, no frame. 1254x1254 canvas, character centered, head near the top, feet at 97% of the height.
```

**Đính kèm khi gen:** HERO REF + ảnh sprite gốc tương ứng trong `art/prompts/codex/refs/`.

**Bản tách mảnh để rig** (khuyến nghị: gen thêm cho mỗi nhân vật chính) — thêm câu:

```
Also provide a second image: a RIG PARTS SHEET of the same character on transparent background, each body part separated with gaps: head (with hair and accessories), torso with belt, upper arm and forearm+hand for each arm, thigh and shin+foot for each leg, all in the same scale and orientation as the full-body image, each part slightly extended at the joints (overlap margin) so it can rotate without gaps.
```

**Đặt tên file trả về:**
- `<mã>.png` (full body)
- `<mã>_parts.png` (bảng tách mảnh)

### B1. NPC giáo viên & người trong trường
Mã sprite gốc: `refs/npc_parts.png`.

1. `npc0_old_master` (npc/00): ông thầy già hói, râu trắng dài, kính tròn đỏ-xanh lá như kính bơi, áo choàng tím, tay chống gậy nhỏ; dáng còng, hiền và hài hước.
```
an old bald kung-fu master, long white beard and moustache, round red-framed goggles with green lenses, purple robe with white collar, slightly hunched, holding a small wooden cane, cheerful wise expression
```

2. `npc1_ayumi` (npc/10): cô giáo trẻ Kagome Ayumi, tóc đen búi có hoa anh đào hồng, kimono đỏ hoa văn, obi vàng, dịu dàng.
```
a young female ninja teacher, glossy black hair in a bun with a pink cherry-blossom hairpin, red patterned kimono with a gold obi, gentle smile, graceful posture
```

3. `npc2_hiroyaki` (npc/20): thầy Hiroyaki, tóc búi củ hành samurai, mắt nhắm điềm tĩnh, haori xanh trắng sọc, đai đỏ.
```
a calm middle-aged samurai teacher with a topknot, eyes closed serenely, blue-and-white striped haori over a dark kimono, red sash, arms folded
```

4. `npc3_honda` (npc/30): thầy Honda già, hói, ria và râu trắng, áo gi xanh dương, đứng nghiêm.
```
an elderly bald martial-arts teacher, white bushy moustache and short white beard, royal-blue gi with dark trim, stern but kind face, standing upright
```

5. `npc4_principal` (npc/40, hiệu trưởng Okamesama): mặc đen, băng đô xám, mặt nghiêm, áo choàng ninja đen.
```
a strict ninja school principal, black ninja robes and a black cloak with red lining, grey headband, sharp stern eyes, short black hair, commanding pose with one hand behind his back
```

6. `npc5_takanashi` (npc/50, thầy chủ nhiệm Takanashi): băng đô bạc, áo xanh đậm, mặt ngầu có vết sẹo, cầm thanh kiếm gỗ.
```
a tough ninja homeroom teacher, silver headband, dark navy gi with light blue trim, scar on the cheek, holding a wooden practice sword, confident grin
```

7. `npc_dog` (npc/d0-d1): chó sói xám lông trắng, mắt xanh.
```
a grey and white wolf-dog (husky-like) with blue eyes, fluffy tail, side view walking to the right
```
- Gen thêm 2 tư thế: đứng và đang bước.

### B2. Bạn cùng lớp
Gốc là đầu khác nhau trên cùng thân áo xanh như hero: `refs/student_heads.png`. Mỗi bạn: thân trang phục giống hero (gi xanh viền vàng), chỉ khác đầu/tóc/phụ kiện.

- `stu0_beanie`: mũ len xanh ngọc, mắt đỏ, mặt lém lỉnh.
- `stu1_bald`: đầu cạo trọc, mặt hiền, má phúng phính.
- `stu2_redspike`: tóc dựng đỏ rực, mắt đỏ, mặt nóng tính.
- `stu3_dreads`: tóc đen tết dreadlock buộc cao kiểu mohawk, mắt tím.
- `stu4_glasses`: hói, đeo kính trắng to, mặt mọt sách.
- `stu5_sakura`: cô bé Sakura, tóc đen buộc đuôi ngựa nơ đỏ, mắt xanh to (nhân vật được giải cứu cuối game).
  - Gen thêm phiên bản áo kimono hồng.
- `stu6_bucket`: đội nón thúng gỗ che kín mặt (kiểu komusō).
- `stu7_toniku`: Toniku, kẻ phản đồ — tóc đỏ rối, mặt bầm, ánh mắt gian; gi xanh rách.
  - Gen thêm phiên bản thủ lĩnh băng cướp: áo da thú, dây đai.

### B3. Boss
Gốc: `refs/boss_parts.png`.

- `boss_boar`: heo rừng thành tinh khổng lồ, lông nâu đỏ sẫm dựng gai, nanh trắng cong, mắt vàng dữ.
  - Gen 3 tư thế: đứng, lao húc, bị trúng đòn.
- `boss_squid`: thuỷ quái sông Waramato — mực/bạch tuộc xanh ngọc khổng lồ, đầu nhọn, mắt giận, xúc tu cong.
  - Gen 3 tư thế: nổi lên, quất xúc tu, bị đánh.
- `boss_golem`: khổng lồ gỗ (giáp gỗ điêu khắc) đai tím, nắm đấm gỗ to, chân gỗ.
  - Gen tách mảnh: thân, 2 nắm tay, 2 chân, đầu.

### B4. Quái thường (20 loại)
Gốc: `refs/monster_XX.png`. Mỗi quái gen 1 ảnh full + **4 tư thế** trên cùng một sheet ngang, nền trong suốt: đi/bay 1, đi/bay 2, tấn công, bị trúng đòn. Mặt quay PHẢI, cùng tỉ lệ.

| Mã | Tên | Mô tả |
|---|---|---|
| mob00 | Ma một mắt | hồn ma trắng tím một mắt to, đuôi khói |
| mob01 | Ốc sên | ốc sên vỏ xoắn nâu cam, thân xanh lá, mắt lồi |
| mob02 | Dơi quỷ | dơi tím đen cánh rách, răng nanh |
| mob03 | Quỷ một chân | yêu quái một chân nhảy, da đỏ, sừng nhỏ, cầm chuỳ |
| mob04 | Cóc xanh | cóc xanh to mụn cóc, lưỡi dài |
| mob05 | Nhện | nhện đen sọc đỏ 8 chân xoè chữ X |
| mob06 | Ong bồ vẽ | ong vàng đen to, ngòi nhọn, cánh trong |
| mob07 | Bù nhìn rơm | bù nhìn rơm tập đánh trên cọc gỗ, đầu bao tải |
| mob08 | Cá cọp | cá vằn cọp cam đen, răng sắc, nhảy khỏi nước |
| mob09 | Ninja gió | ninja áo xám xanh che mặt, khăn bay, kunai |
| mob10 | Cá cọp đỏ | cá cọp đỏ lớn hơn, vây gai |
| mob11 | Ma một mắt (mạnh) | như mob00 nhưng đỏ thẫm, có hào quang |
| mob12 | Rết sa mạc | rết đốt dài đỏ nâu, càng to |
| mob13 | Dơi vàng | dơi lông vàng óng, cánh mạ vàng |
| mob14 | Chuột | chuột xám to răng cửa, đuôi dài |
| mob15 | Thổ dân | chiến binh thổ dân băng Toniku, mặt nạ, giáo |
| mob16 | Ong đỏ | ong đỏ thẫm hung dữ |
| mob17 | Bò cạp | bò cạp vàng đất, đuôi cong ngòi |
| mob18 | Quỷ hoa | hoa ăn thịt đỏ, miệng răng, rễ chân |
| mob19 | Quỷ tử hoa | hoa ăn thịt tím khổng lồ, boss nhỏ |

**Prompt mẫu quái (thay `<mô tả>`):**
```
<prefix B0> Monster: <mô tả in English>. Provide one horizontal sprite strip with 4 poses of the same monster at the same scale, evenly spaced: move pose A, move pose B, attack pose, hurt pose; facing right; transparent background.
```

### B5. Bối cảnh / tile
Hiện trạng:
- **Lớp xa** (núi, trời, làng, rừng xa): đã là ảnh nền Blender nhiều lớp parallax.
- **Lớp gần** (đất cỏ, tường, mái, cây, đèn đá, nước, tre…): vẫn là **tile gốc 24×24 phóng to**. Vì vậy nhìn như "khối ảnh".

Muốn đẹp hẳn thì phải vẽ lại tileset. Tôi giữ đúng lưới và va chạm gốc, chỉ thay hình.

**Cách 1 — khuyến nghị: vẽ lại từng tile.** Gửi cho Codex ảnh `game/orig_res/t/t.png` (120 tile, 24 px, xếp dọc). Prompt:
```
Redraw this 24x24 pixel-art game tileset at 4x resolution (96x96 per tile, output 96x11520 PNG with transparency), keeping EXACTLY the same tile grid, the same silhouette and the same edges of every tile so neighbouring tiles still connect seamlessly. Convert the pixel art into clean high-resolution anime-style painted art matching the attached hero reference: soft cel shading, subtle texture (grass blades, dirt grain, stone cracks, wood grain, roof tile highlights), consistent top-left lighting, no outlines on tile edges that touch other tiles. Keep transparent pixels transparent. Do not move, merge or reorder tiles.
```
- Nếu ảnh quá dài: chia thành 6 phần, mỗi phần 20 tile (96×1920). Đặt tên `t_part1.png` … `t_part6.png`.

**Cách 2: vật liệu liền mạch.** 1024×1024, tileable cả hai chiều. Tôi sẽ đắp vật liệu lên mặt nạ hình tile gốc.
- grass top edge
- dirt/earth
- grey stone brick wall
- wooden plank wall
- blue roof tiles
- red temple wall
- bamboo stalk
- tree bark
- foliage
- water

Prompt:
```
Seamless tileable 1024x1024 texture of <material>, anime painted game style matching the attached hero reference, soft cel shading, top-left light, no perspective, flat orthographic side view, tiles perfectly on all four edges.
```

**Đạo cụ riêng** (mỗi cái PNG trong suốt):
- đèn đá
- đèn lồng đỏ
- hàng rào tre
- bia mộ
- tượng đá
- chum sứ xanh
- xe máy cũ
- thùng gỗ

Prompt:
```
<prefix B0 adapted> Prop: <tên>, side view, transparent background, 1024x1024.
```

---

## PHẦN C — Tôi sẽ làm gì khi nhận file
1. **Video Veo:** trích khung, tách nền xanh, đo quỹ đạo và nhịp. Sau đó chỉnh tư thế/lò xo của rig xương runtime (`game/render/hero_rig.gd`) và thêm tư thế mới (ví dụ phanh trượt, theo đà chém). Khi nào chạy xong tôi gửi lại video so sánh game với Veo.
2. **Ảnh nhân vật/quái Codex:**
   - Tách mảnh (hoặc dùng sheet parts), dựng rig Blender và rig runtime như hero.
   - Xuất sprite đúng khung và hitbox gốc.
   - Thay vào `game/hd_art/`, kiểm vi sai để chắc logic không đổi.
3. **Tileset:** kiểm từng tile đúng kích thước 96 px và mép liền. Sau đó thay vào `game/hd_art/t/t.png`.
