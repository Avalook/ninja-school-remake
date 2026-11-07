# Ninja School 1 melee hit: source findings for the Mac restoration

Evidence is from the decompiled `analysis/NinjaSchool1/sources/a.java`; it is a narrow method trace, not a complete source audit.

- Basic attack input slot 5 starts state `A=5`, resets `fw/C/z`, and consumes the attack edge (`7441-7447`, `8859-8864`). A repeat while attacking sets the combo queue flag `fy` (`8969-8972`).
- The combo-free animation starts with `fw=0`; at `C=6`, it calls `u()` (`5161-5185`). The attack state advances through the `fw` animation branches and returns to idle (`5080-5388`). The game update interval is 40 ms (`42`).
- `A()` chooses the first mob whose state is not 5 and whose center lies in a facing-dependent horizontal span: right `fh-20..fh+40`, left `fh-40..fh+20`; vertical span is `fi-fk-15..fi+5` (`2642-2655`). `fk` is initialized to 32 (`3276`), so the implemented local vertical span is `player_y-47..player_y+5`.
- `u()` calculates damage as `E + w % (E >> 1)`, adds `(damage >> 1) * x` for the selected weapon index, and doubles the result when `fA` is enabled. It then chooses one target, applies knockback direction `-fp`, and requests a damage number (`2112-2144`).
- `k()` reduces target HP only for mob states 2, 3, 4, and 9; at zero HP it sets the dead state and calls the loot/death handler `L(n)`. Otherwise it enters a hit reaction (`11644-11670`).
- New-game map 0 resets base attack power `E=20` and begins at `(864,336)` (`4166-4182`). Weapon index `x` is selected from the weapon shop (`14060-14077`). `fA` is a timed doubled-damage state (`5734-5800`).
- Map 0 config defines two species-7 mobs, using an 80×19 map and source position arrays (`11696-11706`); helper `q(0,2)` initializes two mob slots with HP from `an[7]=1000` (`744-746`, `13443-13465`). The actual asset animation and spawn coordinate interpretation still need full validation.

## Implemented slice

`games/ninja1-original/player.gd` now applies the source hit bounds and damage formula at attack tick 6. It sends one hit to the first target in group `enemies` that implements `take_source_hit(damage, direction)`. The formula uses a local source tick counter in place of the game's global `w`, and map99 currently contains no original enemy instance. The smoke test uses a temporary fixture only to verify bounds, damage, and knockback.

## Remaining checks before source-faithful combat

- Verify how global `w` is initialized and persisted across map transitions.
- Decode the exact source map0 mob spawn positions, hit/death states, drop rewards and source enemy sprites.
- Restore the intro sequence from map99 into map0 before adding map0 enemies to the playable flow.

## New Game map99 to map4 exit trace

Verified directly in local decompilation `analysis/NinjaSchool1/sources/a.java`:

- `q(99)` sets `iP=70`, `iQ=15`, destination map `js=4`, and entry `(jx,jy)=(2*jh,5*jh)` (`a.java:11388-11397`; `jh=24` at line 1425), so map4 entry is `(48,120)`.
- The map update checks `fh >= iR - 24`; if `js != -1`, it assigns the destination entry coordinates, then invokes `x(js)` (`a.java:8197-8217`). For map99, the threshold is `70*24-24 = 1656`.
- `q(4)` gives map4 an 80×15 grid, but does not replace the entry coordinates prepared by the map99 exit (`a.java:11184-11193`). The New Game branch for map4 starts sequence `D(0)` (`a.java:4147-4156`); that sequence is not implemented yet.

The Mac project now loads the lossless map4 tile data and routes map99's right boundary to map4 at `(48,120)`, facing left. Headless smoke, dedicated transition test, Universal 2 export, and 120-frame bundled launch passed. Tile transforms, map4 story/cutscene and native visual QA remain unverified.
