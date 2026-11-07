# Theo dõi AI local

Mở **http://127.0.0.1:8765/** trên chính Mac mini. Trang tự đọc
checkpoint mới mỗi 2 giây, không gọi model hoặc dịch vụ Internet.

## Các kết quả hiện có

- `NinjaSchool*/resources/`: tài nguyên gốc trích từ JAR.
- `NinjaSchool*/sources/`: mã Java do CFR xuất; chưa xác minh runtime.
- `NinjaSchool*/inventory.json`: kiểm kê và SHA-256 JAR gốc.
- `NinjaSchool*/rea-inspection.json`: bằng chứng kiểm kê từ REA 6.8.0.
- `runs/*.json`: hành động công cụ, báo cáo và trạng thái của AI local.
- `local-agent.log`: log hoạt động của cầu nối.

`complete` chỉ có nghĩa là model đã trả một báo cáo cho lượt điều tra.
Nó không chứng minh báo cáo đúng, toàn bộ game đã được phục hồi, hoặc đã
có bản Windows. `step_limit`, `truncated` và `error` cần được xử lý tiếp.

## Khởi động lại khi cần

Chạy từ `/Users/quangdang/Downloads/ninja` trong các terminal riêng:

```sh
OLLAMA_NO_CLOUD=1 OLLAMA_HOST=127.0.0.1:11434 ollama serve
```

Nếu dịch vụ đã chạy ở cổng này thì không khởi động thêm một bản.

```sh
python3 -m http.server 8765 --bind 127.0.0.1 --directory analysis
```

Để chạy lại lượt phân tích Ninja School 1, giữ báo cáo cũ bằng tên output mới:

```sh
python3 scripts/local_reverse.py --game analysis/NinjaSchool1 --task-file analysis/task-ninja1.txt --output analysis/runs/NinjaSchool1-rerun.json --max-steps 12
```

Trang hiện theo dõi các tên `NinjaSchool1.json`, `NinjaSchool2.json` và
`NinjaSchool3_bypass.json`; báo cáo có tên khác vẫn đọc trực tiếp được.
Tránh ghi đè checkpoint của một lượt đang chạy.

## Phạm vi

Model: `qwen3.8-27b-mac:latest`, trọng số Q4_K_M khoảng 19,6 GB.
Cloud được tắt khi khởi động Ollama; cầu nối từ chối model remote và chỉ
gọi loopback, không dùng HTTP proxy. Model chỉ có các công cụ đọc file đã
giới hạn trong thư mục game được chọn; không có shell hoặc công cụ ghi mã.

Mục tiêu sản phẩm: ba game Windows 11 x64, Full HD 16:9, ưu tiên 60 FPS
trên Acer Nitro 5 i5-11400H / RAM 16 GB / GTX 1650 4 GB. Đồ họa mới cần
một pipeline sinh ảnh local riêng; hiện chưa tạo hình HD hoặc bản game.

Kiểm thử cầu nối: `python3 -m unittest discover -s tests -v`.
