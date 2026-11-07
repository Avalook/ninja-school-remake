# Tooling tái lập

- Godot4.7.2: engine hiện dùng; project Godot không phụ thuộc đường dẫn .tools cố định. Export preset Mac hiện chứa custom_template tuyệt đối của máy gốc: sửa sang template đúng máy mới. Windows templates/runtime chưa được chứng minh cho remake đầy đủ.
- CFR0.152: JAR đi kèm tools/cfr-0.152.jar. REA repo: https://github.com/morluto/rea ; inspections và input hashes đi kèm analysis. REA hỗ trợ kiểm tra truy ngược, không tự biến JAR thành full game.
- Java/JDK: cần để chạy CFR/javap. Bytecode gốc ở ba JAR.
- Python3.12 và Pillow để xử lý inventory/ref. Các worker local code dùng Ollama127.0.0.1:11434, qwen3.8-27b-mac:latest tại máy gốc. Không đóng gói modelweights/virtualenv/credential.
- MFLUX0.24.0 + MLX trên Apple Silicon: local model Runpod/FLUX.2-klein-4B-mflux-4bit revision7ee1b3aa8178a1240050490072196a57da2bf2a9. Cài riêng bằng uv/pip rồi dùng CLI chính thức; art/generation-jobs.json mô tả prompt/reference/settings. Local generation đã chạy HF_HUB_OFFLINE=1, không cloud inference.
- Blender5.2.3 LTS chính thức: https://www.blender.org/ . Trên Mac đã cài cask từ download.blender.org. Runner tìm blender trong PATH hoặc /Applications/Blender.app/Contents/MacOS/Blender, trên Windows thêm Blender vào PATH. Chạy python3 scripts/run_blender_stage.py, kiểm art/blender/connection-check.json status passed. Blend đã pack texture và dùng render path tương đối.
- Bộ công cụ binary Godot/Blender, weights, node_modules, virtualenv và cache không đóng trong ZIP để pack portable. Source và build preview hiện có vẫn được kèm.
