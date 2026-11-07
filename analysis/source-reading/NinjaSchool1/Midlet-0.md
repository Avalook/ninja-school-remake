**Class Overview**
`Midlet` (L29) extends `MIDlet` and implements `Runnable`. It serves as the application entry point, managing static resources (images, audio, font metrics), persistence via `RecordStore`, and custom text rendering.

**Static Initialization (L53-80)**
*   **Font Metrics:** Three font systems are defined via static arrays:
    *   `k` (L55, len 41): Widths for font `j` (small, 8px height `l=8`).
    *   `n` (L57, len 100+): Widths for font `m` (medium, 12px height `o=12`).
    *   `q` (L59, len 100+): Widths for font `p` (large, 13px height `r=13`).
    *   Values are small integers (2-11), representing pixel widths.
*   **Images:** Loads `/fs0.png` to `/fs2.png` into `j` (L63-68), `/fr.png` into `m` (L72), and `/fb.png` into `p` (L76). Exceptions are silently caught.
*   **Audio:** `t` initialized to -1 (L79).

**Audio Management**
*   `b(int n)` (L82): Loads MIDI file `/s/{n}.mid`. Stops/deallocates previous player `s`. Sets volume to 100, loop count to 1000. On failure, sets `h=true` (L105).

**Text Rendering & Metrics**
*   **Rendering:**
    *   `a(Graphics, String, int, int, int)` (L110): Draws using font `p` (large). Supports alignment (0=left, 1=right, 2=center). Uses `q` for widths.
    *   `b(Graphics, String, int, int, int)` (L127): Draws using font `m` (medium). Center-aligned only. Uses `n` for widths.
    *   `a(Graphics, String, int, int, int, int)` (L144): Draws using font `j` (small). Supports alignment. Uses `k` for widths.
    *   All use `graphics.drawRegion` with a fixed height of `fontHeight - 1` and a constant offset of 20 (likely Y-offset or padding).
*   **Width Calculation:**
    *   `a(String)` (L161): Calculates width using `q`.
    *   `c(String)` (L175): Calculates width using `n`.
    *   `d(String)` (L189): Calculates width using `k` (adds 1 per char).
    *   Character sets include Vietnamese diacritics (e.g., `\u1ea3`, `\u0111`).

**Persistence (RecordStore)**
*   `b(String)` (L203): Opens store, reads record 1, closes. Returns `null` on error.
*   `a(String, byte[])` (L224): Opens store (create if missing). Updates record 1 if exists, else adds.
*   **Specific Keys:**
    *   `a()` (L216): Reads "bg" store, returns `byte[0]` (background ID).
    *   `a(int)` (L255): Writes int to "bg" store.
    *   `a(boolean)` (L240): Writes `1` to "saved" store.
    *   `b()` (L247): Checks if "saved" store record 1 is `1`.

**Text Wrapping**
*   `a(String, int)` (L262): Wraps text to fit width `n` using font `p` metrics (`a(String)`). Splits on spaces/newlines.
*   `b(String, int)` (L307): Wraps text to fit width `n` using font `m` metrics (`c(String)`).

**Lifecycle**
*   `Midlet()` (L348): Sets static `c` to instance. Calls `a.s()` (unresolved symbol `a`). Sets current displayable to static `b` (unresolved symbol `b`).
*   `destroyApp` (L354): Stops/deallocates audio player `s`.
*   `run()` (L372): Starts game loop. Sleeps 200ms. Sets `a.w` based on time, `a.R` to 30.

**Unresolved Symbols**
*   `a`: Class referenced in `a.s()`, `a.w`, `a.R`. Likely the main game engine/state class.
*   `b`: Static field of type `Displayable`, likely the main game screen.

READ_COMPLETE