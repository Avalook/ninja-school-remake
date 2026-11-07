# Ninja School 1 PC — how fidelity is established and what was actually run

Status date: 2026-11-07. Author: Claude (Opus 5.5) session, worktree `claude/ninja-pc-game-dev-f010c2`.

## Architecture (why the gameplay matches the original)

1. **Logic is not re-implemented by hand.** `tools/transpile/j2gd.py` translates every structured
   method of the CFR output (`a.java`, `Midlet.java`) into GDScript (`game/core/orig.gd`, ~12k lines),
   keeping the original variable names (fh/fi = player x/y, A = player state, B = frame, O = quest, ...).
2. `a.run()` (main loop) and `a.paint()` are unstructured in CFR, so `tools/transpile/bc2gd.py`
   translates their **javap bytecode** into basic-block state machines (`game/core/orig_run.gd`).
   `run()` becomes `run_tick()` that stops at `Canvas.repaint()` → exactly one original frame per tick (40 ms).
3. `game/core/orig_host.gd` implements the J2ME surface (images as size refs, resources, RecordStore
   as files in `user://rms`, time, the loader thread run synchronously, Graphics as a draw-command recorder).
4. The presentation (`game/render/*`, `game/ui/*`) only reads that state / draw-command list and draws
   new art. Hit boxes, damage, AI, timings, quests, cutscenes, shop, save format are the original code.

Rebuild translation: `tools/transpile/build.sh` (needs `.venv-tools` with `javalang`, JDK 21 javap).

## Ground truth: the original JAR running in a JVM

`analysis/claude/emu` is a small MIDP stub runtime (lcdui/rms/media over AWT). It runs the untouched
`NinjaSchool1.jar` bytecode; only *copies* of `a.class`/`Midlet.class` are patched (`patchcls.py`) to
route `getResourceAsStream`, `currentTimeMillis`, `Canvas.repaint` and loader `Thread.start` to the
harness for deterministic **lockstep** execution (virtual clock, synchronous loader — same as the port).

## Differential tests (same inputs → compare state every tick)

`tools/diff_run.sh <script> <name>` runs an input script on the original JAR and on the Godot port at the
original 240x320 screen and compares, per tick: d, v, A, B, fh, fi, fR, fS, fV, fX, gc, G, I, N, M, dg, O,
fF, w, the monster arrays jN/jO/kc/jS and boss state hu/hP/hS/hT. `tools/gen_fuzz.py` makes seeded random
input (move/jump/attack/talk/digit keys/menus, potions, skills).

Results run in this session (all after the transpiler fixes listed below):

| Test | Ticks compared | Mismatches |
|---|---:|---:|
| New game → map99 intro → map4 (scripted) | 797 | 0 |
| fuzz seed 1/2/3 (map99/map4 combat, menus, digits) | 3847 / 3879 / 2011 | 0 |
| teleport + fuzz on maps 0,1,2,3,5..18,94,95,96,97 (22 maps) | ~37,000 | 0 (2 lines differed only because the emulator trace file was truncated at exit; fixed) |
| map95 boss fight (scripted) | 4702 | 0 |
| ending: boss defeated → Sakura rescue → map94 D(10) graduation → map0 | 2827 | 0 |

Transpiler bugs found **by these tests** and fixed: unary `-`/`!` applied to parenthesised expressions
were dropped (28 sites: knockback, monster wander, parallax, conditions); selector on an assignment
expression (`(by = ...).length()`) was dropped.

## Other runtime checks

- Quest 0 end-to-end in the port with a test bot (`tests/script_run.gd` bot_npc/bot_walk/bot_kill):
  after the prologue, talk to Ayumi in map0 → quest O=0 accepted → walk to map1, snail kills
  increment fF (0→2 observed by the bot) → (counter then set to 10 to save time) → walk back, talk →
  reward granted: eL=true (khinh công), O=-1. Scripts: game/tests/scripts/ (q1c.txt quest0, end3*.txt ending, sv1/sv2 save-load, fm*.txt per-map fuzz, f1 fuzz).

- Save/load across two separate processes (`tests/script_run.gd`, menu → "Lưu và thoát", relaunch →
  "Tiếp tục"): position/HP/MP/potions restored.
- Ending chain executes without script errors and matches the JAR (table above).
- All 23 playable maps rendered in the windowed game with HD backgrounds (screenshots in session).
- Performance: game logic 0.48 ms/tick (headless). Windowed fullscreen 1920x1080 on Mac M4 Pro: 120 fps
  (display-capped), worst frame 8.3 ms after load.
- Mac export `builds/mac/Ninja School 1.app` launched and played scripted input.

## Not verified / honest gaps

- **Windows build exported but NOT run on Windows** (no Windows machine here). Acer GTX1650 target untested.
- No human full playthrough of all 18 quests in one save; quest logic is the original code and matched the
  JAR under fuzz, but story progress beyond the scripted segments was not played end-to-end by input.
  The map95 boss could not be damaged by my simple test bot (its hit test needs the player ~1 px higher
  than ground: jump attacks); the original behaves identically under the same inputs.
- SMS activation/payment and IMEI licensing are bypassed (always "Tiếp tục"); this is a deliberate PC deviation.
- Startup prompts (sound/quality) are auto-answered "Bật"/"Cao". Day/night still alternates per launch as in the original.
- Music: original MIDI rendered with Java Gervill + `~/.gervill/soundbank-emg.sf2` to MP3 (sound effects: the original has none beyond music).
- Art: hero = Blender cutout rig (46 frames). Backgrounds = Blender 14 biomes day/night. Tiles, NPCs and
  small effects are Scale4x of the original pixels; monsters HD set was in progress at the time of writing
  (see progress.json).
