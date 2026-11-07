"""Regression checks for authorized art normalization, using synthetic images."""
import importlib.util
import hashlib
import json
import os
from pathlib import Path
import shutil
import subprocess
import sys
import tempfile
import unittest
from unittest.mock import patch

import numpy as np
from PIL import Image, ImageDraw


SCRIPT = Path(os.environ.get("NORMALIZER_UNDER_TEST", Path(__file__).resolve().parents[1] / "normalize_hd.py"))
SPEC = importlib.util.spec_from_file_location("normalize_hd", SCRIPT)
NORMALIZER = importlib.util.module_from_spec(SPEC)
SPEC.loader.exec_module(NORMALIZER)


def visible_box(image):
    return image.getchannel("A").point(lambda value: 255 if value > 8 else 0).getbbox()


def subject(color=(255, 0, 0, 255)):
    image = Image.new("RGBA", (80, 100))
    ImageDraw.Draw(image).rectangle((20, 15, 59, 79), fill=color)
    return image


class NormalizeTests(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory(dir=Path(__file__).resolve().parent)
        self.addCleanup(self.temp.cleanup)
        self.root = Path(self.temp.name)
        self.root_patch = patch.object(NORMALIZER, "ROOT", self.root)
        self.root_patch.start()
        self.addCleanup(self.root_patch.stop)

    def job(self, kind="full", image=None):
        output = self.root / "output.png"
        (subject() if image is None else image).save(output)
        return {"path": str(output), "kind": kind}

    def test_crop_preserves_translucent_art_beyond_opaque_subject(self):
        image = subject()
        image.putpixel((2, 3), (50, 60, 70, 20))
        crop = NORMALIZER.clean_crop(image)
        self.assertEqual(crop.getpixel((2, 3)), (50, 60, 70, 20))
        self.assertEqual(crop.width, 64)

    def test_crop_accepts_all_translucent_subject_and_drops_only_noise(self):
        image = Image.new("RGBA", (20, 20))
        image.putpixel((10, 10), (50, 60, 70, 9))
        image.putpixel((9, 9), (20, 30, 40, 8))
        crop = NORMALIZER.clean_crop(image)
        self.assertEqual(crop.getchannel("A").getextrema(), (0, 9))
        with self.assertRaisesRegex(ValueError, "No visible subject"):
            NORMALIZER.clean_crop(Image.new("RGBA", (10, 10)))

    def test_place_aligns_retained_alpha_bottom_to_baseline(self):
        image = NORMALIZER.place(subject(), (1254, 1254), baseline=0.97)
        self.assertEqual(visible_box(image)[3], round(1254 * 0.97))
        self.assertGreater(visible_box(image)[1], 0)

    def test_prop_and_parts_are_centered_without_changing_input(self):
        source = subject()
        before = source.tobytes()
        for kind, expected in (("prop", 1024), ("parts", 1254), ("golem_parts", 1254)):
            with self.subTest(kind=kind):
                job = self.job(kind, source)
                NORMALIZER.normalize(job)
                with Image.open(job["path"]) as result:
                    box = visible_box(result)
                    self.assertEqual(result.size, (expected, expected))
                    self.assertLessEqual(abs((box[0] + box[2]) - expected), 2)
                    self.assertLessEqual(abs((box[1] + box[3]) - expected), 2)
        self.assertEqual(source.tobytes(), before)

    def strip(self):
        image = Image.new("RGBA", (2048, 512))
        draw = ImageDraw.Draw(image)
        for index in range(4):
            draw.rectangle((index * 512 + 160, 30, index * 512 + 350, 450), fill=(index * 50, 50, 100, 255))
        return image

    def test_strip_retains_all_four_poses_on_actual_baseline(self):
        job = self.job("strip", self.strip())
        meta = NORMALIZER.normalize(job)
        self.assertEqual(len(meta["frame_rects"]), 4)
        with Image.open(job["path"]) as result:
            self.assertEqual(result.size, (2048, 512))
            for index in range(4):
                frame = result.crop((index * 512, 0, (index + 1) * 512, 512))
                self.assertEqual(visible_box(frame)[3], round(512 * 0.97))

    def test_strip_accepts_shifted_gutter_without_cutting_attack_effect(self):
        image = self.strip()
        ImageDraw.Draw(image).rectangle((1350, 100, 1595, 300), fill=(100, 50, 100, 20))
        job = self.job("strip", image)
        original = Path(job["path"]).read_bytes()
        meta = NORMALIZER.normalize(job)
        self.assertGreater(meta["source_frame_rects"][2][0] + meta["source_frame_rects"][2][2], 1595)
        self.assertLessEqual(meta["source_frame_rects"][3][0], 1536 + 102)
        retained = 0
        for index, (x, y, width, height) in enumerate(meta["source_frame_rects"]):
            pixels = np.asarray(image.crop((x, y, x + width, y + height)))
            mask = pixels[:, :, 3] > 8
            retained += int(mask.sum())
            self.assertTrue(np.all(pixels[:, :, :3][mask] == (index * 50, 50, 100)))
        self.assertEqual(retained, int((np.asarray(image)[:, :, 3] > 8).sum()))
        self.assertEqual((self.root / meta["raw"]).read_bytes(), original)
        normalized = Path(job["path"]).read_bytes()
        self.assertEqual(NORMALIZER.normalize(job)["source_frame_rects"], meta["source_frame_rects"])
        self.assertEqual(Path(job["path"]).read_bytes(), normalized)

    def test_strip_rejects_no_gutter_within_bounded_window_and_preserves_output(self):
        for boundary in (512, 1024, 1536):
            with self.subTest(boundary=boundary):
                image = self.strip()
                ImageDraw.Draw(image).rectangle((boundary - 104, 40, boundary + 104, 80), fill=(100, 100, 100, 20))
                job = self.job("strip", image)
                before = Path(job["path"]).read_bytes()
                with self.assertRaisesRegex(ValueError, "[Gg]utter|[Ss]plit"):
                    NORMALIZER.normalize(job)
                self.assertEqual(Path(job["path"]).read_bytes(), before)

    def test_non_horizontal_strip_and_empty_frame_are_rejected(self):
        job = self.job("strip", subject())
        with self.assertRaisesRegex(ValueError, "not horizontal"):
            NORMALIZER.normalize(job)
        image = self.strip()
        ImageDraw.Draw(image).rectangle((512, 0, 1023, 511), fill=(0, 0, 0, 0))
        job = self.job("strip", image)
        with self.assertRaisesRegex(ValueError, "No visible subject"):
            NORMALIZER.normalize(job)

    def test_cached_raw_never_replaces_new_regenerated_source(self):
        job = self.job()
        first_bytes = Path(job["path"]).read_bytes()
        first = NORMALIZER.normalize(job)
        source = self.root / "new-generation.png"
        subject((0, 255, 0, 255)).save(source)
        job["regenerated_source"] = str(source)
        second = NORMALIZER.normalize(job)
        with Image.open(job["path"]) as result:
            self.assertEqual(result.getpixel((627, 700))[:3], (0, 255, 0))
        self.assertEqual((self.root / first["raw"]).read_bytes(), first_bytes)
        self.assertEqual((self.root / second["raw"]).read_bytes(), source.read_bytes())

    def test_replaced_output_without_source_identity_is_not_rolled_back(self):
        job = self.job()
        NORMALIZER.normalize(job)
        subject((0, 0, 255, 255)).save(job["path"])
        NORMALIZER.normalize(job)
        with Image.open(job["path"]) as result:
            self.assertEqual(result.getpixel((627, 700))[:3], (0, 0, 255))

    def test_same_source_path_with_new_bytes_creates_new_raw_version(self):
        job = self.job()
        source = self.root / "generated-source.png"
        shutil.copy2(job["path"], source)
        job["generated_source"] = str(source)
        first = NORMALIZER.normalize(job)
        original = (self.root / first["raw"]).read_bytes()
        subject((0, 255, 0, 255)).save(source)
        second = NORMALIZER.normalize(job)
        third = NORMALIZER.normalize(job)
        self.assertNotEqual(first["source_sha256"], second["source_sha256"])
        self.assertNotEqual(first["raw"], second["raw"])
        self.assertEqual(second["raw"], third["raw"])
        self.assertEqual((self.root / first["raw"]).read_bytes(), original)
        self.assertEqual((self.root / second["raw"]).read_bytes(), source.read_bytes())

    def test_corrupt_raw_cache_is_rejected_without_modifying_output(self):
        job = self.job()
        meta = NORMALIZER.normalize(job)
        normalized = Path(job["path"]).read_bytes()
        (self.root / meta["raw"]).write_bytes(b"corrupt")
        with self.assertRaisesRegex(ValueError, "fingerprint mismatch"):
            NORMALIZER.normalize(job)
        self.assertEqual(Path(job["path"]).read_bytes(), normalized)

    def test_normalization_is_idempotent_and_raw_is_original(self):
        job = self.job()
        original = Path(job["path"]).read_bytes()
        meta = NORMALIZER.normalize(job)
        normalized = Path(job["path"]).read_bytes()
        second = NORMALIZER.normalize(job)
        self.assertEqual(Path(job["path"]).read_bytes(), normalized)
        self.assertEqual((self.root / meta["raw"]).read_bytes(), original)
        self.assertEqual(meta["raw"], second["raw"])

    def test_texture_has_equal_edges_and_correct_size(self):
        image = Image.fromarray(np.random.default_rng(1).integers(0, 256, (40, 60, 3), dtype=np.uint8))
        job = self.job("texture", image)
        meta = NORMALIZER.normalize(job)
        self.assertEqual(meta["size"], [1024, 1024])
        self.assertTrue(meta["horizontal_edge_equal"])
        self.assertTrue(meta["vertical_edge_equal"])

    def test_script_end_to_end_updates_only_fixture_manifest(self):
        script = self.root / "art/prompts/codex/normalize_hd.py"
        script.parent.mkdir(parents=True)
        shutil.copy2(SCRIPT, script)
        job = self.job()
        job["normalization_error"] = "old failure"
        job["final_sha256"] = "stale hash"
        invalid_path = self.root / "invalid-strip.png"
        Image.new("RGBA", (2048, 512)).save(invalid_path)
        invalid = {"path": str(invalid_path), "kind": "strip", "normalization": {"stale": True}}
        manifest = script.parent / "hd-production-manifest.json"
        manifest.write_text(json.dumps({"jobs": [job, invalid, {"path": str(self.root / "missing.png"), "kind": "full"}]}))
        completed = subprocess.run([sys.executable, str(script)], capture_output=True, text=True, timeout=30, check=False)
        self.assertEqual(completed.returncode, 0, completed.stderr)
        result = json.loads(manifest.read_text())
        self.assertEqual(result["jobs"][0]["normalization"]["size"], [1254, 1254])
        self.assertNotIn("normalization_error", result["jobs"][0])
        self.assertEqual(result["jobs"][0]["final_sha256"], hashlib.sha256(Path(job["path"]).read_bytes()).hexdigest())
        self.assertNotIn("normalization", result["jobs"][1])
        self.assertIn("No visible subject", result["jobs"][1]["normalization_error"])
        self.assertNotIn("normalization", result["jobs"][2])
        self.assertIn("Normalized 1 files", completed.stdout)


if __name__ == "__main__":
    unittest.main()
