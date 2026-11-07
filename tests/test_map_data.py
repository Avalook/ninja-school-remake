"""Checks for lossless decoding of original byte-per-cell map data."""

from dataclasses import FrozenInstanceError
import unittest

from scripts.map_data import decode_grid, parse_map_dimensions


class MapDataTest(unittest.TestCase):
    def test_unsigned_cells_and_row_order_are_preserved(self) -> None:
        raw = bytes([0, 127, 128, 255, 3, 4])
        grid = decode_grid(raw, 3, 2)
        self.assertEqual(grid.cells, ((0, 127, 128), (255, 3, 4)))
        self.assertEqual(bytes(v for row in grid.cells for v in row), raw)
        self.assertEqual((grid.columns, grid.rows), (3, 2))

    def test_grid_is_immutable(self) -> None:
        grid = decode_grid(b"\x01", 1, 1)
        with self.assertRaises(FrozenInstanceError):
            grid.columns = 2

    def test_invalid_dimensions_fail_before_allocation(self) -> None:
        for width, height in [(0, 1), (1, -1), (True, 1), (1, False),
                              (1.0, 1), ("2", 1), (4097, 1),
                              (4096, 4096)]:
            with self.subTest(width=width, height=height), self.assertRaises(ValueError):
                decode_grid(b"", width, height)

    def test_missing_or_extra_cells_are_rejected(self) -> None:
        for raw in (b"", b"\x01", b"\x01\x02\x03"):
            with self.subTest(raw=raw), self.assertRaisesRegex(ValueError, "length"):
                decode_grid(raw, 2, 1)

    def test_literal_loader_branches_and_alias_without_dimensions(self) -> None:
        source = """if (n == 101) {
    n = 0;
}
if (n == 0) {
    a.iP = 76;
    a.iQ = 20;
} else if (n == 95) {
    a.iP = 25;
    a.iQ = 14;
}
"""
        self.assertEqual(parse_map_dimensions(source), {0: (76, 20), 95: (25, 14)})

    def test_ambiguous_missing_and_duplicate_branches_are_rejected(self) -> None:
        snippets = ["", "if(n==0) {\n iP=76;\n}",
                    "if(n==0) {\n iP=76;\n iP=77;\n iQ=20;\n}",
                    "if(n==0) {\n iP=1; iQ=1;\n}\nif(n==0) {\n iP=1; iQ=1;\n}"]
        for source in snippets:
            with self.subTest(source=source), self.assertRaises(ValueError):
                parse_map_dimensions(source)

    def test_only_binary_input_is_accepted(self) -> None:
        with self.assertRaises(ValueError):
            decode_grid("1", 1, 1)


if __name__ == "__main__":
    unittest.main()
