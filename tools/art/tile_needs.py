#!/usr/bin/env python3
"""tools/art/tile_needs.py <map id> <biome id>: list of tiles to redraw for one map of game 1.

Reads remake/data/maps/map_<id>.json (tile ids + per-cell collision flags, from the original q(int)) and the
original tile atlas analysis/NinjaSchool1/resources/t/t.png (24 x 2880: tile id n is the 24x24 block at
y = (n - 1) * 24, a.java ~L11495). Writes into art_src/env/<biome>/:
  TILE_NEEDS.md          every tile id used, count, flags per id
  tiles_orig_4x/tNNN.png each original tile, nearest-neighbour 4x (96 x 96)
  tiles_orig_4x.png      contact sheet of the same tiles with their id
  ref_map<id>.png        the whole map drawn from the original tiles at 4x (original layer rules, no sky)
Only reads original data; the images are the Nokia pixels scaled up, not new art.
"""
import collections, json, os, sys
from PIL import Image, ImageDraw

R = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
RES = os.path.join(R, "analysis/NinjaSchool1/resources/t")
K = 4

# bit -> (column name, meaning and where it is in the code)
FLAGS = [
    (0x2, "đứng", "sàn một chiều: chỉ đáp xuống khi đang rơi, nhảy xuyên từ dưới lên được (`player_sim.gd` ~L270, a.java g(fh, fi, 2))"),
    (0x4, "tường→", "chặn khi đi sang phải (`player_sim.gd` ~L225)"),
    (0x8, "←tường", "chặn khi đi sang trái (`player_sim.gd` ~L228)"),
    (0x10, "nền sâu", "tile lớp sau, vẽ có parallax 22/24 (`tile_layer.gd`, a.java k(Graphics))"),
    (0x20, "mặt nước", "mặt nước động `wtf.png` 4 frame (`tile_layer.gd`)"),
    (0x40, "nước", "thân nước: rơi vào là bơi A = 11 (a.java L5031), sóng `wts.png` vẽ ở lớp trước"),
    (0x100, "bụi trước", "bụi cây tiền cảnh `tb.png`, parallax 23/24, che nhân vật"),
    (0x200, "tile trước", "tile tiền cảnh vẽ lệch +2 px, che nhân vật"),
    (0x800, "ván lún", "ván nhún: đứng lên thì lún 1 px (cờ 0x400) (a.java S(), L5752)"),
    (0x1000, "vùng chìm", "rơi vào thì chìm A = 12, ngập đầu thì chết (a.java L5042, L5495); tile vẽ ở lớp trước"),
    (0x2000, "đất đào", "đất đào được bằng Độn thổ (A = 8, a.java L5456)"),
]
CLIMB_NOTE = ("**Thang/dây leo:** luật game 1 không có trạng thái leo và không có cờ riêng cho thang/dây leo "
              "(đã đọc `remake/sim/player_sim.gd` và các chỗ a.java gọi `g(.., cờ)`). Comment cũ trong "
              "`world.gd` gọi 0x1000 là \"vines\", nhưng luật dùng 0x1000 như vùng chìm.")


def main() -> None:
    mid, biome = int(sys.argv[1]), sys.argv[2]
    out = os.path.join(R, "art_src/env", biome)
    os.makedirs(os.path.join(out, "tiles_orig_4x"), exist_ok=True)
    m = json.load(open(os.path.join(R, "remake/data/maps/map_%d.json" % mid)))
    w, h, tiles, flags = m["w"], m["h"], m["tiles"], m["flags"]
    atlas = Image.open(os.path.join(RES, "t.png")).convert("RGBA")
    wtf = Image.open(os.path.join(RES, "wtf.png")).convert("RGBA")
    wts = Image.open(os.path.join(RES, "wts.png")).convert("RGBA")
    tb = Image.open(os.path.join(RES, "tb.png")).convert("RGBA")

    def tile(n: int) -> Image.Image:
        return atlas.crop((0, (n - 1) * 24, 24, n * 24))

    count = collections.Counter()
    fl = collections.defaultdict(collections.Counter)    # id -> bit -> cells
    combos = collections.defaultdict(collections.Counter)
    for i, n in enumerate(tiles):
        count[n] += 1
        f = int(flags[i])
        combos[n][f] += 1
        for b, _, _ in FLAGS:
            if f & b:
                fl[n][b] += 1
    ids = sorted(k for k in count if k > 0)

    # per-tile 4x images + contact sheet
    cols = 10
    sheet = Image.new("RGBA", (cols * 104, ((len(ids) + cols - 1) // cols) * 116), (40, 40, 40, 255))
    d = ImageDraw.Draw(sheet)
    for j, n in enumerate(ids):
        t = tile(n).resize((24 * K, 24 * K), Image.Resampling.NEAREST)
        t.save(os.path.join(out, "tiles_orig_4x", "t%03d.png" % n))
        x, y = (j % cols) * 104, (j // cols) * 116
        sheet.alpha_composite(t, (x + 4, y + 2))
        d.text((x + 4, y + 99), "t%03d x%d" % (n, count[n]), fill=(255, 255, 255, 255))
    sheet.save(os.path.join(out, "tiles_orig_4x.png"))

    # whole map from the original tiles (layer rules of tile_layer.gd / a.java k, l; no camera -> no parallax,
    # animated water at frame 0, sky / background images left out: transparent)
    img = Image.new("RGBA", (w * 24, h * 24), (0, 0, 0, 0))
    ft = lambda tx, ty: int(flags[ty * w + tx]) if 0 <= tx < w and 0 <= ty < h else 0
    for ty in range(h):            # back layer
        for tx in range(w):
            f, n, p = ft(tx, ty), tiles[ty * w + tx], (tx * 24, ty * 24)
            if f & 0x10:
                if n > 0:
                    img.alpha_composite(tile(n), p)
            elif not f & 0x100 and not f & 0x200:
                if f & 0x20:
                    img.alpha_composite(wtf.crop((0, 0, 24, 24)), p)
                elif f & 0x40 or f & 0x1000:
                    up = ft(tx, ty - 1)
                    if up & 0x20:
                        img.alpha_composite(wtf.crop((0, 0, 24, 24)), p)
                    elif up & 0x2000:
                        img.alpha_composite(tile(22), p)
                elif n > 0:
                    img.alpha_composite(tile(n), p)
    for ty in range(h):            # front layer
        for tx in range(w):
            f, n, p = ft(tx, ty), tiles[ty * w + tx], (tx * 24, ty * 24)
            if f & 0x40:
                img.alpha_composite(wts.crop((0, 0, 24, wts.height // 2)), p)
            elif f & 0x100:
                img.alpha_composite(tb, (p[0] - 2 + (tx + ty) % 5, p[1] - 3 + ty % 5))
            if f & 0x200 and n > 0:
                img.alpha_composite(tile(n), (p[0], p[1] + 2))
            if f & 0x1000 and n > 0:
                img.alpha_composite(tile(n), p)
    img.resize((w * 24 * K, h * 24 * K), Image.Resampling.NEAREST).save(os.path.join(out, "ref_map%d.png" % mid))

    L = []
    L.append("# Tile cần vẽ cho map %d (%s)" % (mid, biome))
    L.append("")
    L.append("Sinh tự động bởi `tools/art/tile_needs.py %d %s` từ `remake/data/maps/map_%d.json` (dữ liệu bản gốc) "
             "và atlas gốc `analysis/NinjaSchool1/resources/t/t.png`. Không sửa tay; muốn đổi thì chạy lại script." % (mid, biome, mid))
    L.append("")
    L.append("- Map %d: %d × %d ô (ô 24 px gốc → 96 px art). %d ô có tile, %d ô trống (id 0). **%d tile id khác nhau.**" %
             (mid, w, h, sum(count[k] for k in ids), count[0], len(ids)))
    L.append("- `tiles_orig_4x/tNNN.png`: tile gốc phóng 4× (nearest, 96×96). `tiles_orig_4x.png`: bảng tổng có số id.")
    L.append("- `ref_map%d.png`: toàn map %d ghép lại từ tile gốc theo luật lớp của bản gốc, phóng 4× (%d×%d). "
             "Không có camera nên không có parallax; nước lấy frame 0; **không có trời/nền xa** (nền trong suốt). "
             "Đây là ảnh ghép từ dữ liệu gốc, không phải ảnh chụp emulator." % (mid, mid, w * 24 * K, h * 24 * K))
    L.append("- Cờ va chạm nằm theo **ô**, không theo tile id: cùng một id có thể mang cờ khác nhau ở chỗ khác nhau. "
             "Bảng ghi số ô mang từng cờ. Hitbox giữ theo bản gốc dù art mới lớn hơn (ROADMAP, quy tắc 1).")
    L.append("")
    L.append("## Nghĩa các cờ")
    L.append("")
    L.append("| Bit | Cột | Nghĩa (nguồn) | Số ô trên map %d |" % mid)
    L.append("|---|---|---|---|")
    for b, name, mean in FLAGS:
        L.append("| `0x%x` | %s | %s | %d |" % (b, name, mean, sum(1 for v in flags if int(v) & b)))
    L.append("")
    L.append(CLIMB_NOTE)
    L.append("")
    mask = sum(b for b, _, _ in FLAGS)
    nf = sum(1 for i, n in enumerate(tiles) if n == 0 and int(flags[i]) & mask)
    L.append("Ô trống (id 0) nhưng vẫn mang cờ (va chạm tàng hình): %d ô." % nf)
    L.append("")
    L.append("## Danh sách tile")
    L.append("")
    L.append("| Tile | Ảnh gốc 4× | Số lần | " + " | ".join(n for _, n, _ in FLAGS) + " | Tổ hợp cờ (cờ: số ô) |")
    L.append("|" + "---|" * (4 + len(FLAGS)))
    for n in ids:
        cells = " | ".join(str(fl[n][b]) if fl[n][b] else "" for b, _, _ in FLAGS)
        comb = ", ".join("`0x%x`: %d" % (f, c) for f, c in sorted(combos[n].items()))
        L.append("| t%03d | ![](tiles_orig_4x/t%03d.png) | %d | %s | %s |" % (n, n, count[n], cells, comb))
    L.append("")
    open(os.path.join(out, "TILE_NEEDS.md"), "w").write("\n".join(L))
    print("map %d: %d tile ids -> %s" % (mid, len(ids), out))


if __name__ == "__main__":
    main()
