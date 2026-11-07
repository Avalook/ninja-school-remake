"""Tests for constrained local game-authoring tools."""

import json
from pathlib import Path
import subprocess
import sys
import tempfile
import unittest
from unittest.mock import patch

from scripts.local_game import ProjectTools, confined_path, run_bounded, verify_build, write_text


class LocalGameTest(unittest.TestCase):
    def setUp(self) -> None:
        self.temp = tempfile.TemporaryDirectory()
        self.addCleanup(self.temp.cleanup)
        self.base = Path(self.temp.name)
        self.project = self.base / "game"
        self.project.mkdir()
        self.reference = self.base / "reference"
        (self.reference / "resources").mkdir(parents=True)
        (self.reference / "sources").mkdir()
        (self.reference / "sources" / "a.java").write_text("1: jump\n2: draw\n")
        (self.reference / "resources" / "bg.png").write_bytes(b"image")
        (self.reference / "inventory.json").write_text(json.dumps({"entries": [
            {"path": "bg.png", "size": [10, 20]}, {"path": "other.txt"}
        ]}))
        workspace_patch = patch("scripts.local_game.WORKSPACE", self.base)
        workspace_patch.start()
        self.addCleanup(workspace_patch.stop)
        self.tools = ProjectTools(self.project, self.reference)

    def test_process_capture_is_bounded_and_times_out(self) -> None:
        result = run_bounded([sys.executable, "-c", "print('x'*20000)"], self.project, 3)
        self.assertEqual(result.returncode, 0)
        self.assertLessEqual(len(result.stdout), 6000)
        with self.assertRaisesRegex(RuntimeError, "output"):
            run_bounded([sys.executable, "-c", "print('x'*3000000)"], self.project, 3)
        with self.assertRaisesRegex(RuntimeError, "timed out"):
            run_bounded([sys.executable, "-c", "import time;time.sleep(3)"], self.project, 1)

    def test_completion_requires_fresh_checks(self) -> None:
        for ok in (False, True):
            with self.subTest(ok=ok), patch.object(ProjectTools, "__call__", return_value=json.dumps({"ok": ok})) as call:
                result = verify_build(self.tools)
                self.assertEqual(result["ok"], ok)
                self.assertEqual(call.call_count, 3 if ok else 1)

    def test_paths_reject_traversal_absolute_and_symlink(self) -> None:
        (self.project / "escape").symlink_to(self.base, target_is_directory=True)
        for name in ("../outside.gd", str(self.base / "x.gd"), "escape/x.gd", ".godot/x.gd"):
            with self.subTest(name=name), self.assertRaises(ValueError):
                confined_path(self.project, name)

    def test_write_is_atomic_and_validates_utf8_size_and_extension(self) -> None:
        target = self.project / "main.gd"
        write_text(target, "extends Node\n")
        with patch("scripts.local_game.os.replace", side_effect=OSError("disk")):
            with self.assertRaises(OSError):
                write_text(target, "replacement")
        self.assertEqual(target.read_text(), "extends Node\n")
        self.assertEqual(list(self.project.glob(".main.gd.*")), [])
        for path, content in (("x.py", "code"), ("large.gd", "ệ" * 50000), ("x.gd", 42)):
            with self.subTest(path=path), self.assertRaises(ValueError):
                self.tools("write_code", {"path": path, "content": content})

    def test_code_tools_use_actual_files(self) -> None:
        result = self.tools("write_code", {"path": "nested/main.gd", "content": "extends Node\n"})
        self.assertIn("nested/main.gd", result)
        self.assertIn("extends Node", self.tools("read_code", {"path": "nested/main.gd"}))
        self.assertIn("nested/main.gd", self.tools("list_code", {}))
        self.assertIn("jump", self.tools("read_reference", {"path": "sources/a.java"}))
        self.assertIn("a.java", self.tools("search_reference", {"query": "jump"}))

    def test_replace_code_requires_one_exact_match(self) -> None:
        self.tools("write_code", {"path": "main.gd", "content": "extends Node\n"})
        self.tools("replace_code", {"path": "main.gd", "find": "Node", "replace": "Node2D"})
        self.assertEqual((self.project / "main.gd").read_text(), "extends Node2D\n")
        for find in ("missing", "", 42):
            with self.subTest(find=find), self.assertRaises(ValueError):
                self.tools("replace_code", {"path": "main.gd", "find": find, "replace": "x"})
        self.tools("write_code", {"path": "main.gd", "content": "same same"})
        with self.assertRaisesRegex(ValueError, "one"):
            self.tools("replace_code", {"path": "main.gd", "find": "same", "replace": "x"})

    def test_asset_copy_stays_inside_project_assets(self) -> None:
        self.assertIn("bg.png", self.tools("list_assets", {"prefix": "bg"}))
        self.tools("copy_asset", {"path": "bg.png"})
        self.assertEqual((self.project / "assets/bg.png").read_bytes(), b"image")
        with self.assertRaises(ValueError):
            self.tools("copy_asset", {"path": "../sources/a.java"})
        (self.reference / "resources" / "bad.txt").write_text("not image")
        with self.assertRaises(ValueError):
            self.tools("copy_asset", {"path": "bad.txt"})

    def test_unknown_tools_and_extra_arguments_do_not_run_commands(self) -> None:
        for tool, args in (("shell", {"command": "anything"}),
                           ("check_project", {"command": "anything"}),
                           ("read_reference", {"path": "../outside"}),
                           ("read_code", {"path": "missing.gd", "count": True})):
            with self.subTest(tool=tool), patch("scripts.local_game.subprocess.run") as run:
                with self.assertRaises(ValueError):
                    self.tools(tool, args)
                run.assert_not_called()

    def test_godot_operations_have_fixed_argv_and_timeout(self) -> None:
        self.tools("write_code", {"path": "project.godot", "content": "config_version=5\n"})
        self.tools("write_code", {"path": "tests/smoke.gd", "content": "extends SceneTree\n"})
        response = subprocess.CompletedProcess([], 0, "SMOKE_PASS", "")
        for tool in ("check_project", "smoke_test", "run_game", "export_windows"):
            with self.subTest(tool=tool), patch(
                "scripts.local_game.run_bounded", return_value=response
            ) as run:
                result = json.loads(self.tools(tool, {}))
                self.assertLessEqual(run.call_args.args[2], 120)
                self.assertIn("--headless", run.call_args.args[0])
                self.assertTrue(result["ok"] if tool != "export_windows" else not result["ok"])

    def test_godot_parse_errors_fail_even_when_exit_code_is_zero(self) -> None:
        (self.project / "project.godot").write_text("config_version=5\n")
        response = subprocess.CompletedProcess([], 0, "x" * 10000, "SCRIPT ERROR: Parse Error")
        with patch("scripts.local_game.run_bounded", return_value=response):
            result = json.loads(self.tools("check_project", {}))
        self.assertFalse(result["ok"])
        self.assertLessEqual(len(result["output"]), 6000)
        with patch("scripts.local_game.run_bounded", side_effect=RuntimeError("Godot timed out")):
            with self.assertRaisesRegex(RuntimeError, "timed out"):
                self.tools("check_project", {})


if __name__ == "__main__":
    unittest.main()
