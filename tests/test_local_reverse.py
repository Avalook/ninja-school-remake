"""Tests for the local-only reverse-engineering bridge."""

import json
from pathlib import Path
import tempfile
import unittest
from unittest.mock import patch

from scripts.local_reverse import (
    ENDPOINT,
    atomic_json,
    bounded_history,
    check_model,
    local_request,
    run_agent,
    run_tool,
    source_path,
)


class LocalReverseTest(unittest.TestCase):
    def setUp(self) -> None:
        log_patch = patch("scripts.local_reverse.LOG")
        log_patch.start()
        self.addCleanup(log_patch.stop)
        self.temp = tempfile.TemporaryDirectory()
        self.addCleanup(self.temp.cleanup)
        self.root = Path(self.temp.name) / "input"
        self.root.mkdir()
        (self.root / "Example.java").write_text(
            "class Example {\n  void jump() {}\n}\n", encoding="utf-8"
        )

    def test_read_and_search_use_real_files(self) -> None:
        self.assertIn("2:   void jump", run_tool(
            self.root, "read_lines", {"path": "Example.java", "start": 2, "count": 1}
        ))
        self.assertIn("Example.java:2", run_tool(
            self.root, "search_source", {"query": "jump"}
        ))
        self.assertIn("Example.java", run_tool(self.root, "list_files", {}))
        self.assertIn("636c617373", run_tool(
            self.root, "read_hex", {"path": "Example.java", "count": 5}
        ))

    def test_rejects_escape_and_symlink(self) -> None:
        outside = self.root.parent / "secret"
        outside.write_text("secret", encoding="utf-8")
        (self.root / "link").symlink_to(outside)
        for name in ("../secret", str(outside), "link"):
            with self.subTest(name=name), self.assertRaises(ValueError):
                source_path(self.root, name)

    def test_rejects_invalid_tool_arguments(self) -> None:
        cases = [
            ("exec", {}),
            ("read_lines", {"path": "Example.java", "start": 0}),
            ("read_lines", {"path": "Example.java", "count": 201}),
            ("read_lines", {"path": "Example.java", "count": True}),
            ("read_hex", {"path": "Example.java", "offset": -1}),
            ("read_hex", {"path": "Example.java", "count": 257}),
            ("search_source", {"query": ""}),
            ("search_source", {"query": 10}),
            ("list_files", {"unexpected": "value"}),
        ]
        for name, args in cases:
            with self.subTest(name=name, args=args), self.assertRaises(ValueError):
                run_tool(self.root, name, args)

    def test_tool_outputs_are_bounded(self) -> None:
        (self.root / "large.java").write_text("x" * 20000, encoding="utf-8")
        self.assertLessEqual(len(run_tool(
            self.root, "read_lines", {"path": "large.java"}
        )), 16000)
        self.assertEqual(run_tool(
            self.root, "search_source", {"query": "missing"}
        ), "No matches.")

    def test_remote_models_are_rejected(self) -> None:
        for item in (
            {"name": "bad:cloud"},
            {"name": "bad", "remote_model": "other"},
            {"name": "bad", "remote_host": "https://example.com"},
        ):
            with self.subTest(item=item), patch(
                "scripts.local_reverse.local_request",
                return_value={"models": [item]},
            ), self.assertRaises(ValueError):
                check_model(item["name"])
        with patch("scripts.local_reverse.local_request", return_value={
            "models": [{"name": "local", "size": 100}]
        }):
            self.assertEqual(check_model("local")["name"], "local")
            with self.assertRaises(ValueError):
                check_model("missing")

    def test_atomic_write_preserves_previous_file_on_failure(self) -> None:
        output = self.root.parent / "result.json"
        atomic_json(output, {"old": True})
        with patch("scripts.local_reverse.os.replace", side_effect=OSError("disk")):
            with self.assertRaises(OSError):
                atomic_json(output, {"new": True})
        self.assertEqual(json.loads(output.read_text()), {"old": True})
        self.assertEqual(list(output.parent.glob(".result.json.*")), [])

    def test_local_request_never_uses_a_proxy(self) -> None:
        with patch("scripts.local_reverse.urllib.request.build_opener") as build:
            build.return_value.open.return_value.__enter__.return_value.read.return_value = b"{}"
            self.assertEqual(local_request("/api/tags"), {})
            request = build.return_value.open.call_args.args[0]
            self.assertEqual(request.full_url, ENDPOINT + "/api/tags")
        with self.assertRaises(ValueError):
            local_request("https://other.example")

    def test_network_and_json_errors_are_reported(self) -> None:
        with patch("scripts.local_reverse.urllib.request.build_opener") as build:
            build.return_value.open.side_effect = TimeoutError("timeout")
            with self.assertRaisesRegex(RuntimeError, "Ollama"):
                local_request("/api/tags")
        with patch("scripts.local_reverse.urllib.request.build_opener") as build:
            build.return_value.open.return_value.__enter__.return_value.read.return_value = b"not json"
            with self.assertRaisesRegex(RuntimeError, "Ollama"):
                local_request("/api/tags")

    def test_agent_executes_tool_and_preserves_transcript(self) -> None:
        responses = [
            {"message": {"role": "assistant", "content": "", "tool_calls": [
                {"function": {"name": "read_lines", "arguments": {"path": "Example.java"}}}
            ]}},
            {"message": {"role": "assistant", "content": "Found jump at Example.java:2."}},
        ]
        output = self.root.parent / "run.json"
        with patch("scripts.local_reverse.check_model"), patch(
            "scripts.local_reverse.local_request", side_effect=responses
        ):
            result = run_agent(self.root, "local", "inspect", output, 3)
        self.assertEqual(result["status"], "complete")
        self.assertEqual(result["steps"], 2)
        self.assertIn("Found jump", result["answer"])
        self.assertEqual(json.loads(output.read_text())["status"], "complete")

    def test_step_limit_is_not_reported_as_complete(self) -> None:
        response = {"message": {"role": "assistant", "content": "", "tool_calls": [
            {"function": {"name": "list_files", "arguments": {}}}
        ]}}
        with patch("scripts.local_reverse.check_model"), patch(
            "scripts.local_reverse.local_request", return_value=response
        ) as request:
            result = run_agent(
                self.root, "local", "inspect", self.root.parent / "limit.json", 2
            )
        self.assertEqual(result["status"], "step_limit")
        self.assertEqual(request.call_count, 2)

    def test_malformed_model_responses_are_recorded_as_errors(self) -> None:
        replies = [
            {"message": None},
            {"message": {"role": "assistant", "content": 42}},
            {"message": {"role": "assistant", "tool_calls": "bad"}},
            {"message": {"role": "assistant", "tool_calls": [{"function": None}]}},
            {"message": {"role": "assistant", "tool_calls": [None]}},
            {"message": {"role": "assistant", "tool_calls": [{"function": {
                "name": "read_lines", "arguments": []
            }}]}},
        ]
        for reply in replies:
            output = self.root.parent / "malformed.json"
            with self.subTest(reply=reply), patch(
                "scripts.local_reverse.check_model"
            ), patch("scripts.local_reverse.local_request", return_value=reply):
                with self.assertRaises(ValueError):
                    run_agent(self.root, "local", "inspect", output, 1)
                self.assertEqual(json.loads(output.read_text())["status"], "error")

    def test_model_startup_failure_is_recorded(self) -> None:
        output = self.root.parent / "startup.json"
        with patch("scripts.local_reverse.check_model", side_effect=ValueError("missing")):
            with self.assertRaises(ValueError):
                run_agent(self.root, "local", "inspect", output, 1)
        self.assertEqual(json.loads(output.read_text())["status"], "error")

    def test_history_is_bounded_without_orphan_tool_messages(self) -> None:
        base = [{"role": "system", "content": "system"}, {"role": "user", "content": "task"}]
        old = {"role": "assistant", "content": "x" * 3000}
        call = {"role": "assistant", "tool_calls": [{"function": {"name": "read"}}]}
        messages = [*base, old, call, {"role": "tool", "content": "ok"}]
        compact = bounded_history(messages, 1000)
        self.assertLessEqual(len(json.dumps(compact).encode()), 1000)
        self.assertNotIn(old, compact)
        self.assertEqual(compact[-2:], messages[-2:])
        self.assertEqual(bounded_history(base, 1000), base)
        with self.assertRaises(ValueError):
            bounded_history(base, 5)

    def test_oversized_latest_group_keeps_compact_tool_results(self) -> None:
        base = [{"role": "system", "content": "system"}, {"role": "user", "content": "task"}]
        call = {"role": "assistant", "tool_calls": [{"function": {
            "name": "read_reference", "arguments": {"path": f"source{i}.java"}
        }} for i in range(8)]}
        messages = [*base, call, *[{"role": "tool", "tool_name": "read_reference", "content": "x" * 6000} for _ in range(8)]]
        compact = bounded_history(messages, 12000)
        self.assertLessEqual(len(json.dumps(compact).encode()), 12000)
        self.assertEqual(sum(x.get("role") == "tool" for x in compact), 8)
        self.assertEqual(len(messages[-1]["content"]), 6000)

    def test_coding_tools_are_injected_and_code_is_not_dumped_into_events(self) -> None:
        responses = [
            {"message": {"role": "assistant", "content": "", "tool_calls": [
                {"function": {"name": "write_code", "arguments": {
                    "path": "main.gd", "content": "extends Node"
                }}}
            ]}},
            {"message": {"role": "assistant", "content": "Written"}},
        ]
        def handler(name, args):
            return "written " + args["path"]
        with patch("scripts.local_reverse.check_model"), patch(
            "scripts.local_reverse.local_request", side_effect=responses
        ) as request:
            result = run_agent(self.root, "local", "build", self.root.parent / "build.json", 2,
                tool_specs=[], tool_runner=handler, system_prompt="Code locally",
                phase="coding", max_output_tokens=4500, request_timeout=600)
        self.assertEqual(result["phase"], "coding")
        self.assertEqual(result["status"], "complete")
        self.assertNotIn("extends Node", str(result["events"]))
        self.assertEqual(request.call_args_list[0].args[1]["options"]["num_predict"], 4500)
        self.assertLessEqual(request.call_args_list[0].kwargs["timeout"], 600)

    def test_agent_failure_is_recorded(self) -> None:
        output = self.root.parent / "failure.json"
        with patch("scripts.local_reverse.check_model"), patch(
            "scripts.local_reverse.local_request", side_effect=RuntimeError("offline")
        ), self.assertRaises(RuntimeError):
            run_agent(self.root, "local", "inspect", output, 1)
        self.assertEqual(json.loads(output.read_text())["status"], "error")


if __name__ == "__main__":
    unittest.main()
