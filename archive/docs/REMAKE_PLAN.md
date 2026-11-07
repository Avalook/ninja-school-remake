# Ninja School 1 — native remake (modern Godot code)

Goal: a complete PC game (New Game → ending) written as modern, readable Godot 4.7 GDScript, every
system running per display frame (60–120 fps), with the ORIGINAL content and rules as data:
maps, collision flags, monsters & stats, NPCs, dialogue, 18 quests, cutscene scripts, shop, skills, save.

The transpiled original (`game/core/*`, runnable via `game/`) is NOT used at runtime here. It is:
- the **data source** (`remake/data/` was exported from it after boot and after loading each map), and
- the **oracle** for verification (`tools/diff_run.sh`, `game/tests/script_run.gd`): quest flags,
  rewards, damage, cutscene outcomes of the remake are compared against it.

Reference code: `analysis/NinjaSchool1/sources/a.java` (CFR) and its exact translation
`game/core/orig.gd` (same names, `_name` = locals) and `game/core/orig_run.gd` (`run()` main loop as a
state machine, CFR-structured version: `/tmp/claude-501/cfr3/a.java` may be regenerated with CFR).

## Units & time
- World unit = original pixel. Tiles are 24×24. Player feet position = (fh, fi) in the original.
- Screen shows 480×270 world px (camera zoom = window_height / 270). Original framing:
  camera target x = player_x − 240 + bI·facing (bI = 80), y = player_y − 202, clamped to
  [0, (w−1)·24 − 480] × [0, (h−1)·24 − 270].
- Original logic ran at 25 Hz (1 tick = 0.04 s). Convert: px/tick × 25 = px/s; px/tick² × 625 = px/s².
  Modules may run their AI decisions on an internal fixed 25 Hz step (to keep the original timing/random
  behaviour, e.g. `w % N` patterns) but MUST move/draw smoothly (interpolate or integrate per frame).
- `w` (original global tick counter, 0..9999) is used for pseudo-randomness. The remake keeps a global
  `S.w` incremented at 25 Hz by `Game` (scripts/core/game.gd) for any ported formula that needs it.

## Data (remake/data)
- `static.json` — every variable of the original after boot (tables + initial player state).
  Important tables: monster names/HP/atk/exp/coin `am an ao ap aq`; EXP to next level `ak`;
  cutscene groups `av aw ax ay` (trigger kind / sequence id / x / y), sequences `az` (opcode) `aA` (arg)
  `aB` (cumulative tick); camera targets `aC`; dialogue speaker index/kind `aD aE`, dialogue lines `aG`,
  title cards `aH`; NPC dialogues `lB lC lD lE lF lG`; quest target mob `lH` / count `lI`;
  potion heal `kJ`; shop names `kx`; world map `kP` names, `kR kS` icon pos, `kT kU` spawn tile;
  menu `aF`; companion lines `aI`; player composite frames `as`(f_as)/`aJ`.
- `maps/map_<id>.json` — `{id, w, h, tiles[w*h] (tile id 0 = empty, else atlas row id-1),
  flags[w*h] (original iU bits), exits {jr,js,jt,ju (target map left/right/up/down), jv,jw (spawn when
  leaving left), jx,jy (spawn when leaving right), jz,jA (alt right exit when y > jD), jB,jC (down)},
  state {every original variable whose value differs from static after loading this map}}`.
  Monster arrays (in `state`): count `jK`, type `jM`, x `jN`, y `jO`, patrol min/max `jT jU`,
  size `jP jQ`, state `jS` (5 = waiting to respawn, 1 = dying …), hp `kc`, max hp `kd`, dir `kb`, …
  NPCs: count `lx`, x `ly`, y `lz`, kind `lA`. Classmates/companions: `dx dC dD dG …`. Objects `it iv iw ix`.
  Vehicles `il in io`. Big decor `ja jb jc jd je jf` (images `/t/h0..2.png`).
- Tile flag bits (rendering + collision, from q(int) and j/k/l(Graphics)): 2 standable top (one-way floor), 4 wall on the right side, 8 wall on the left side, 0x10 background-depth tile (drawn with slight parallax: x scaled by 22/24 around the camera centre), 0x20 animated water body (/t/wtf.png, 4 frames), 0x40 water surface (/t/wts.png, 2 frames, swim), 0x100 foreground bush (/t/tb.png drawn in front, parallax 23/24), 0x200 foreground tile (drawn in front, +2 px), 0x1000 ladder/vine (front), 0x2000 earth (dig skill), 0x800 tile 7, 0x400 runtime marker (crumble, drawn +1 px).
  0x10 water body, 0x40 water surface, 0x1000 ladder/vine, 0x2000 diggable/"earth" (dig skill),
  0x100 spikes(32), 0x200 (33/34), 0x800 (7), 0x400 runtime marker. See `q(int)` in a.java ~L11396.

## Architecture (remake/)
```
project.godot          main scene res://scenes/main.tscn
scripts/core/state_base.gd   GENERATED: all original variables with boot values (autoload `S`)
scripts/core/state.gd        extends state_base: helpers (level up, add exp/money, save/load JSON)
scripts/core/data.gd         autoload `Data`: loads static.json / maps
scripts/core/game.gd         autoload `Game`: mode machine (title, play, dialogue, menu, shop, cutscene),
                             25 Hz `S.w` clock, map transitions
scripts/world/world.gd       World node: tile layers, collision queries, decor, exits, camera
scripts/player/player.gd     native player controller + combat + skills
scripts/enemies/*.gd         EnemyManager + per-type behaviours + bosses       (agent A)
scripts/story/*.gd           Cutscene player, NPC talk/quest logic, map-entry triggers (agent B)
scripts/ui/*.gd              HUD, dialogue box, menus, shop, world map, title
scripts/fx/*.gd              hit sparks, slash trails, dust, damage numbers
art/                         game-ready art (sheets + json); tools under ../tools/art
```

### Contracts between modules
World (`scripts/world/world.gd`, group "world"):
- `flags_at(x: float, y: float) -> int`, `is_floor(x, y) -> bool` (flag 2), `map_id`, `map` (json dict)
- `player` (Player node), `enemies` (EnemyManager), `camera_override(target: Vector2, active: bool)`
- `spawn_fx(kind: String, pos: Vector2, dir := 1)`, `float_text(text, pos, color)`
- signal `map_loaded(id)`

Player (`scripts/player/player.gd`):
- `pos` (feet, Vector2), `vel`, `facing` (±1), `state` (String: idle/run/jump/fall/attack/hurt/dead/...)
- `hitbox() -> Rect2` (22×32 centered on x, bottom at y), `hurt(damage: int, from_dir: int)`
- `script_keys: Dictionary` — cutscenes press keys: {"left","right","jump","attack","down"}: bool; when
  `scripted == true` player input comes only from script_keys.
- `attack_box() -> Rect2` original melee area: x∈[x−20, x+40] facing right (mirror left), y∈[y−47, y+5].

EnemyManager (`scripts/enemies/enemy_manager.gd`, agent A):
- `setup(map: Dictionary)`, `_process(dt)`, `draw` itself (child sprites)
- `hit(area: Rect2, damage: int, dir: int) -> int` (index of first enemy hit or −1), `boss_state()`
- emits `enemy_killed(type: int, pos: Vector2)`; calls `World.player.hurt(dmg, dir)` on contact/attacks;
  drops coins/potions via `S` per original L(n) rules (`w % 20`).

Story (`scripts/story/story.gd`, agent B):
- `on_map_enter(map_id)` (original n(n) triggers), `on_enemy_killed(type)` (quest counters O/fF),
  `talk(npc_index)` (original NPC talk switch in run(), case lA), cutscene sequences (original V()/ad()),
  `is_cutscene_active()`. Uses UI: `UI.say(lines: Array, speaker_pos: Vector2) -> await`,
  `UI.title_card(text)`, `UI.popup(text)`.

## Verification
- Per system, scripted scenarios run in both the remake (headless test scene) and the oracle; compare
  outcomes (flags, HP, money, quest O/fF, positions at checkpoints).
- Visual review: Movie Maker recordings (`--write-movie`), plus an in-game frame-time overlay (F3).
