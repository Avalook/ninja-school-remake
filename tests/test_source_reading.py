"""Coverage bookkeeping tests; never load original game source or call a model."""
from pathlib import Path
import tempfile
import unittest
from unittest.mock import patch
from analysis import full_source_read_job as reader

class SourceReadingTest(unittest.TestCase):
    def test_requires_exact_range_and_complete_note(self):
        with tempfile.TemporaryDirectory() as directory:
            root=Path(directory); note=root/'analysis/source-reading/note.md'
            note.parent.mkdir(parents=True); note.write_text('Recorded implementation facts.\nREAD_COMPLETE')
            item={'file':'x.java','sha256':'abc','byte_start':0,'byte_end':100}
            record={**item,'note':'analysis/source-reading/note.md'}
            with patch.object(reader,'ROOT',root):
                self.assertTrue(reader.record_matches(item,record))
                self.assertFalse(reader.record_matches({**item,'byte_end':120},record))
                self.assertFalse(reader.record_matches({**item,'sha256':'changed'},record))
                self.assertFalse(reader.record_matches(item,{**record,'note':'../outside.md'}))
                note.write_text('incomplete note')
                self.assertFalse(reader.record_matches(item,record))
    def test_full_prompt_byte_budget(self):
        self.assertEqual(reader.validate_prompt('a'*27000),27000)
        with self.assertRaises(ValueError):reader.validate_prompt('a'*27001)
        with self.assertRaises(ValueError):reader.validate_prompt('ệ'*10000)

if __name__=='__main__':unittest.main()
