"""Lossless byte-grid decoding; no collision or artwork semantics inferred."""

from __future__ import annotations

from dataclasses import dataclass
import re


@dataclass(frozen=True)
class MapGrid:
    """An immutable row-major grid of original unsigned byte values."""

    columns: int
    rows: int
    cells: tuple[tuple[int, ...], ...]


def decode_grid(raw: bytes, columns: int, rows: int) -> MapGrid:
    """Decode exactly one unsigned byte per cell, without altering tile IDs."""
    if not isinstance(raw, bytes):
        raise ValueError("Map input must be bytes")
    if any(type(value) is not int or not 1 <= value <= 4096
           for value in (columns, rows)):
        raise ValueError("Map dimensions must be integers between 1 and 4096")
    if columns * rows > 1_000_000:
        raise ValueError("Map exceeds the cell budget")
    if len(raw) != columns * rows:
        raise ValueError("Map length does not match the declared dimensions")
    return MapGrid(columns, rows, tuple(
        tuple(raw[row * columns:(row + 1) * columns])
        for row in range(rows)
    ))


def parse_map_dimensions(loader_prefix: str) -> dict[int, tuple[int, int]]:
    """Read literal iP/iQ branches from the locally identified map loader."""
    dimensions: dict[int, tuple[int, int]] = {}
    current: int | None = None
    widths: frozenset[int] = frozenset()
    heights: frozenset[int] = frozenset()
    # A sentinel flushes the final branch; aliases without dimensions are skipped.
    for line in (*loader_prefix.splitlines(), "if (n == -1)"):
        branch = re.search(r"\bif\s*\(\s*n\s*==\s*(-?\d+)\s*\)", line)
        if branch:
            if current is not None and (widths or heights):
                if len(widths) != 1 or len(heights) != 1:
                    raise ValueError("Ambiguous or missing literal map dimensions")
                if current in dimensions:
                    raise ValueError("Duplicate map branch")
                dimensions = {**dimensions, current: (next(iter(widths)), next(iter(heights)))}
            current = int(branch.group(1))
            widths, heights = frozenset(), frozenset()
        if current is None:
            continue
        for field, value in re.findall(r"\b(iP|iQ)\s*=\s*(\d+)\s*;", line):
            if field == "iP":
                widths = widths | {int(value)}
            else:
                heights = heights | {int(value)}
    if not dimensions:
        raise ValueError("No literal map dimensions found")
    return dimensions
