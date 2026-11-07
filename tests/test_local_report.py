"""Regression tests for bounded streaming local reports."""

import json
from pathlib import Path
import tempfile
import unittest
from unittest.mock import patch

from scripts.local_report import evidence_batches, parse_stream, report_checkpoint, stream_report


class LocalReportTest(unittest.TestCase):
    def test_evidence_batches_keep_evidence_with_tool_labels(self) -> None:
        state = {"events": [{"tool": "read_lines", "arguments": {"path": "a.java"}}],
                 "messages": [{"role": "tool", "content": "x" * 25000}]}
        batches = evidence_batches(state)
        self.assertTrue(all(len(x.encode('utf-8')) <= 5000 for x in batches))
        self.assertEqual(sum(x.count("x") for x in batches), 25000)
        self.assertTrue(all("a.java" in x for x in batches))

    def test_unicode_evidence_stays_intact_within_byte_budget(self) -> None:
        state = {"messages": [{"role": "tool", "content": "ệ" * 6000}]}
        batches = evidence_batches(state)
        self.assertTrue(all(len(x.encode('utf-8')) <= 5000 for x in batches))
        self.assertEqual(sum(x.count("ệ") for x in batches), 6000)

    def test_empty_batch_is_never_complete(self) -> None:
        for text in ("", " ", "\n\t"):
            with self.subTest(text=text), tempfile.TemporaryDirectory() as directory:
                path = Path(directory) / "run.json"
                path.write_text(json.dumps({"model": "local", "messages": [
                    {"role": "tool", "content": "evidence"}
                ]}))
                def stream(model, prompt, receive):
                    receive(text)
                    return {"done": True}
                with patch("scripts.local_report.check_model"), patch(
                    "scripts.local_report.stream_report", side_effect=stream
                ), self.assertRaisesRegex(RuntimeError, "empty"):
                    report_checkpoint(path)
                self.assertEqual(json.loads(path.read_text())["status"], "error")

    def test_stream_request_is_loopback_and_bounded(self) -> None:
        with patch("scripts.local_report.urllib.request.build_opener") as build:
            build.return_value.open.return_value.__enter__.return_value = iter([
                b'{"message":{"content":"hello"}}', b'{"done":true}'
            ])
            self.assertTrue(stream_report("local", "evidence", lambda _: None)["done"])
            request = build.return_value.open.call_args.args[0]
            payload = json.loads(request.data)
            self.assertEqual(request.full_url, "http://127.0.0.1:11434/api/chat")
            self.assertTrue(payload["stream"])
            self.assertEqual(payload["options"]["num_ctx"], 8192)
            self.assertEqual(build.return_value.open.call_args.kwargs["timeout"], 60)

    def test_stream_joins_content_and_hides_thinking(self) -> None:
        chunks = [
            {"message": {"thinking": "private", "content": "Hello "}},
            {"message": {"content": "local"}},
            {"done": True, "eval_count": 2},
        ]
        visible = []
        result = parse_stream((json.dumps(x).encode() for x in chunks), visible.append)
        self.assertEqual("".join(visible), "Hello local")
        self.assertEqual(result["eval_count"], 2)

    def test_invalid_streams_fail_instead_of_reporting_success(self) -> None:
        for lines in ([b"bad"], [b"[]"], [b'{"message": null}'],
                      [b'{"message":{"content":42}}'],
                      [b'{"message":{"content":"partial"}}'],
                      [b'{"done":true,"done_reason":"length"}']):
            with self.subTest(lines=lines), self.assertRaises(RuntimeError):
                parse_stream(iter(lines), lambda _: None)

    def test_stream_wall_limit_and_idle_timeout(self) -> None:
        with patch("scripts.local_report.time.monotonic", side_effect=[0, 601]):
            with self.assertRaisesRegex(RuntimeError, "600"):
                parse_stream(iter([b'{"done":true}']), lambda _: None)

        def timed_out():
            yield b'{"message":{"content":"partial"}}'
            raise TimeoutError("idle")

        with self.assertRaises(TimeoutError):
            parse_stream(timed_out(), lambda _: None)

    def test_checkpoint_streams_and_preserves_error_or_completion(self) -> None:
        for fail in (False, True):
            with self.subTest(fail=fail), tempfile.TemporaryDirectory() as directory:
                path = Path(directory) / "run.json"
                path.write_text(json.dumps({"model": "local", "events": [],
                    "messages": [{"role": "tool", "content": "1: evidence"}],
                    "generated_tokens": 0}))

                def stream(model, prompt, receive):
                    receive("Verified partial output")
                    checkpoint = json.loads(path.read_text())
                    self.assertIn("Verified partial", checkpoint["answer"])
                    if fail:
                        raise TimeoutError("idle")
                    return {"done": True, "eval_count": 3}

                with patch("scripts.local_report.check_model"), patch(
                    "scripts.local_report.stream_report", side_effect=stream
                ), patch("scripts.local_report.time.monotonic", side_effect=range(100)):
                    if fail:
                        with self.assertRaises(TimeoutError):
                            report_checkpoint(path)
                    else:
                        report_checkpoint(path)
                result = json.loads(path.read_text())
                self.assertEqual(result["status"], "error" if fail else "complete")
                self.assertIn("Verified partial", result["answer"])


if __name__ == "__main__":
    unittest.main()
