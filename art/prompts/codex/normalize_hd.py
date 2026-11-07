"""User-authorized sizing/alpha/layout normalization; keep untouched raw generations."""
import json
import shutil
from pathlib import Path

import numpy as np
from PIL import Image

ROOT = Path(__file__).resolve().parents[3]
MANIFEST = ROOT / 'art/prompts/codex/hd-production-manifest.json'


def clean_crop(image):
    image = image.convert('RGBA')
    alpha = image.getchannel('A')
    box = alpha.point(lambda v: 255 if v >= 128 else 0).getbbox()
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
    y = round(size[1]*baseline)-subject.height if baseline else (size[1]-subject.height)//2
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


def normalize(job):
    output = Path(job['path'])
    raw = output.parent / 'raw' / output.name
    raw.parent.mkdir(exist_ok=True)
    if not raw.exists():
        shutil.copy2(output, raw)
    image = Image.open(raw)
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
        frames = [clean_crop(image.crop((round(n*image.width/4), 0, round((n+1)*image.width/4), image.height))) for n in range(4)]
        scale = min(476/max(f.width for f in frames), 472/max(f.height for f in frames))
        result = Image.new('RGBA', (2048, 512))
        rects = []
        for n, frame in enumerate(frames):
            f = frame.resize((max(1, round(frame.width*scale)), max(1, round(frame.height*scale))), Image.Resampling.LANCZOS)
            x = n*512+(512-f.width)//2
            y = round(512*0.97)-f.height
            result.alpha_composite(f, (x, y))
            rects.append([n*512, 0, 512, 512])
        meta.update(frame_rects=rects, pose_order=['move_a', 'move_b', 'attack', 'hurt'], common_scale=scale)
    elif kind in ('parts', 'golem_parts'):
        result = place(image, (1254, 1254), margin=0.035)
        meta['joint_overlap_and_parts_count'] = 'requires rig integration verification'
    elif kind == 'prop':
        result = place(image, (1024, 1024), margin=0.05)
    else:
        result = place(image, (1254, 1254), baseline=0.97, margin=0.03)
        meta['baseline_y'] = round(1254*0.97)
    result.save(output)
    alpha = result.getchannel('A') if result.mode == 'RGBA' else None
    meta.update(size=list(result.size), mode=result.mode, alpha_extrema=list(alpha.getextrema()) if alpha else None)
    return meta


def main():
    manifest = json.loads(MANIFEST.read_text())
    for job in manifest['jobs']:
        if not Path(job['path']).is_file():
            continue
        try:
            job['normalization'] = normalize(job)
        except Exception as error:
            job['normalization_error'] = str(error)
    manifest['normalization_authorization'] = 'User explicitly approved Python normalization of game images.'
    MANIFEST.write_text(json.dumps(manifest, ensure_ascii=False, indent=2))
    print('Normalized', sum('normalization' in j for j in manifest['jobs']), 'files')


if __name__ == '__main__':
    main()
