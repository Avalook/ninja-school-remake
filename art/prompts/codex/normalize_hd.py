"""User-authorized sizing/alpha/layout normalization; keep untouched raw generations."""
import json
import hashlib
import shutil
from pathlib import Path

import numpy as np
from PIL import Image

ROOT = Path(__file__).resolve().parents[3]
MANIFEST = ROOT / 'art/prompts/codex/hd-production-manifest.json'


def clean_crop(image):
    image = image.convert('RGBA')
    alpha = image.getchannel('A')
    box = alpha.point(lambda v: 255 if v > 8 else 0).getbbox()
    if box is None:
        raise ValueError('No visible subject')
    x0, y0, x1, y1 = box
    box = (max(0, x0-4), max(0, y0-4), min(image.width, x1+4), min(image.height, y1+4))
    image = image.crop(box)
    image.putalpha(image.getchannel('A').point(lambda v: 0 if v <= 8 else v))
    return image


def place(image, size, baseline=None, margin=0.035):
    crop = clean_crop(image)
    max_w = size[0] * (1 - 2*margin)
    max_h = size[1] * ((baseline-margin) if baseline else (1-2*margin))
    scale = min(max_w/crop.width, max_h/crop.height)
    subject = crop.resize((max(1, round(crop.width*scale)), max(1, round(crop.height*scale))), Image.Resampling.LANCZOS)
    canvas = Image.new('RGBA', size)
    x = (size[0]-subject.width)//2
    visible_bottom = subject.getchannel('A').point(lambda v: 255 if v > 8 else 0).getbbox()[3]
    y = round(size[1]*baseline)-visible_bottom if baseline else (size[1]-subject.height)//2
    canvas.alpha_composite(subject, (x, y))
    return canvas


def seamless(image):
    data = np.asarray(image.convert('RGB').resize((1024, 1024), Image.Resampling.LANCZOS)).astype(np.float32)
    width = 40
    for axis in (1, 0):
        copy = data.copy()
        for n in range(width):
            weight = 0.5*(1-n/(width-1))
            if axis == 1:
                a, b = copy[:, n].copy(), copy[:, -n-1].copy()
                data[:, n] = a*(1-weight)+b*weight
                data[:, -n-1] = b*(1-weight)+a*weight
            else:
                a, b = copy[n].copy(), copy[-n-1].copy()
                data[n] = a*(1-weight)+b*weight
                data[-n-1] = b*(1-weight)+a*weight
    return Image.fromarray(np.clip(np.rint(data), 0, 255).astype('uint8'))


def source_raw(job: dict, output: Path) -> tuple[Path, str, str, Path]:
    """Keep raw generations immutable and identify each normalized output."""
    raw = output.parent / 'raw' / output.name
    raw.parent.mkdir(exist_ok=True)
    record_path = raw.with_suffix(raw.suffix + '.normalization.json')
    record = json.loads(record_path.read_text()) if record_path.exists() else {}
    identity = str(job.get('regenerated_source') or job.get('generated_source') or '')
    source = Path(identity) if identity else output
    if not source.is_file():
        raise ValueError(f'Generated source is missing: {source}')
    digest = hashlib.sha256(source.read_bytes()).hexdigest()
    if source == output and digest == record.get('normalized_sha256'):
        cached = output.parent / 'raw' / record['raw_name']
        if not cached.is_file() or hashlib.sha256(cached.read_bytes()).hexdigest() != record['source_sha256']:
            raise ValueError('Raw cache fingerprint mismatch')
        return cached, record['source_sha256'], identity, record_path
    if raw.exists() and hashlib.sha256(raw.read_bytes()).hexdigest() != digest:
        raw = raw.with_name(f'{raw.stem}-{digest[:16]}{raw.suffix}')
    if raw.exists():
        if hashlib.sha256(raw.read_bytes()).hexdigest() != digest:
            raise ValueError('Raw cache fingerprint mismatch')
    else:
        shutil.copy2(source, raw)
    return raw, digest, identity, record_path


def normalize(job):
    output = Path(job['path'])
    raw, source_digest, identity, record_path = source_raw(job, output)
    with Image.open(raw) as source:
        image = source.copy()
    kind = job['kind']
    meta = {'raw': str(raw.relative_to(ROOT)), 'operation': 'sizing and alpha padding only'}
    if kind == 'texture':
        result = seamless(image)
        a = np.asarray(result)
        meta.update(operation='1024 resize and 40px symmetric edge blend', horizontal_edge_equal=bool(np.array_equal(a[:, 0], a[:, -1])), vertical_edge_equal=bool(np.array_equal(a[0], a[-1])))
    elif kind == 'strip':
        # Keep the generated left-to-right order and common scale; do not invent poses.
        if image.width < image.height*2:
            raise ValueError('Strip is not horizontal; requires image-generation correction')
        opaque_columns = np.any(np.asarray(image.convert('RGBA').getchannel('A')) > 8, axis=0)
        radius = int((image.width/4)*0.20)
        cuts = [0]
        for n in range(1, 4):
            ideal = round(n*image.width/4)
            nearby = range(max(1, ideal-radius), min(image.width-1, ideal+radius)+1)
            safe = [cut for cut in nearby if not opaque_columns[cut-1] and not opaque_columns[cut]]
            if not safe:
                raise ValueError(f'Strip split {n} has no transparent gutter within 20% of frame width; requires image-generation correction')
            cuts.append(min(safe, key=lambda cut: (abs(cut-ideal), cut)))
        cuts.append(image.width)
        frames = [clean_crop(image.crop((cuts[n], 0, cuts[n+1], image.height))) for n in range(4)]
        scale = min(476/max(f.width for f in frames), 472/max(f.height for f in frames))
        result = Image.new('RGBA', (2048, 512))
        rects = []
        for n, frame in enumerate(frames):
            f = frame.resize((max(1, round(frame.width*scale)), max(1, round(frame.height*scale))), Image.Resampling.LANCZOS)
            x = n*512+(512-f.width)//2
            visible_bottom = f.getchannel('A').point(lambda v: 255 if v > 8 else 0).getbbox()[3]
            y = round(512*0.97)-visible_bottom
            result.alpha_composite(f, (x, y))
            rects.append([n*512, 0, 512, 512])
        meta.update(frame_rects=rects, source_frame_rects=[[cuts[n], 0, cuts[n+1]-cuts[n], image.height] for n in range(4)], pose_order=['move_a', 'move_b', 'attack', 'hurt'], common_scale=scale)
    elif kind in ('parts', 'golem_parts'):
        result = place(image, (1254, 1254), margin=0.035)
        meta['joint_overlap_and_parts_count'] = 'requires rig integration verification'
    elif kind == 'prop':
        result = place(image, (1024, 1024), margin=0.05)
    else:
        result = place(image, (1254, 1254), baseline=0.97, margin=0.03)
        meta['baseline_y'] = round(1254*0.97)
    result.save(output)
    normalized_digest = hashlib.sha256(output.read_bytes()).hexdigest()
    record = {'raw_name': raw.name, 'source_sha256': source_digest, 'source_identity': identity, 'normalized_sha256': normalized_digest}
    temporary = record_path.with_suffix(record_path.suffix + '.tmp')
    temporary.write_text(json.dumps(record, indent=2))
    temporary.replace(record_path)
    alpha = result.getchannel('A') if result.mode == 'RGBA' else None
    meta.update(size=list(result.size), mode=result.mode, alpha_extrema=list(alpha.getextrema()) if alpha else None, source_sha256=source_digest, normalized_sha256=normalized_digest)
    return meta


def main():
    manifest = json.loads(MANIFEST.read_text())
    for job in manifest['jobs']:
        if not Path(job['path']).is_file():
            continue
        try:
            job['normalization'] = normalize(job)
            job['final_sha256'] = job['normalization']['normalized_sha256']
            job.pop('normalization_error', None)
        except Exception as error:
            job.pop('normalization', None)
            job['normalization_error'] = str(error)
    manifest['normalization_authorization'] = 'User explicitly approved Python normalization of game images.'
    MANIFEST.write_text(json.dumps(manifest, ensure_ascii=False, indent=2))
    print('Normalized', sum('normalization' in j for j in manifest['jobs']), 'files')


if __name__ == '__main__':
    main()
