"""Toon low-poly monsters for the HD sprite set (Blender 5.x, headless EEVEE).

    Blender --background --factory-startup --python scripts/blender_monsters.py -- \
        --out art/monsters/raw [--only m100,m101] [--blend art/blender/monsters.blend]

Units are ORIGINAL sprite pixels (x right, z up, ground at z=0, camera looks +Y, creature
faces +X = right like the anim-0 originals). Each original frame is one collection with its
own pose; renders are transparent RGBA "raw" images, later fitted/outlined by
scripts/monster_post.py onto the exact 4x canvas (registration comes from the original bbox).
Anim-1 frames are mirrors of anim-0 and are produced in post, not here.
"""
import math
import os
import sys

import bmesh
import bpy
from mathutils import Euler, Matrix, Vector

PPU = 12          # render pixels per original pixel (3x supersample of the 4x target)
OL = 0.22         # inverted-hull outline thickness (original px)
DARK = "1a0e16"

# ----------------------------------------------------------------------------- materials
_mats = {}


def _lin(c):
    return c / 12.92 if c <= 0.04045 else ((c + 0.055) / 1.055) ** 2.4


def col(h):
    return tuple(_lin(int(h[i:i + 2], 16) / 255) for i in (0, 2, 4)) + (1.0,)


def _mix(h, k, other=(255, 255, 255)):
    r = [int(h[i:i + 2], 16) for i in (0, 2, 4)]
    return "".join("%02x" % int(max(0, min(255, a + (b - a) * k))) for a, b in zip(r, other))


def toon(base, shade=None, light=None, alpha=1.0):
    shade = shade or _mix(base, 0.38, (40, 10, 60))
    light = light or _mix(base, 0.35)
    key = (base, shade, light, alpha)
    if key in _mats:
        return _mats[key]
    m = bpy.data.materials.new("toon_%s" % base)
    m.use_nodes = True
    nt = m.node_tree
    nt.nodes.clear()
    diff = nt.nodes.new("ShaderNodeBsdfDiffuse")
    s2r = nt.nodes.new("ShaderNodeShaderToRGB")
    ramp = nt.nodes.new("ShaderNodeValToRGB")
    cr = ramp.color_ramp
    cr.interpolation = "CONSTANT"
    cr.elements[0].position, cr.elements[0].color = 0.0, col(shade)
    cr.elements[1].position, cr.elements[1].color = 0.30, col(base)
    e = cr.elements.new(0.80)
    e.color = col(light)
    emit = nt.nodes.new("ShaderNodeEmission")
    out = nt.nodes.new("ShaderNodeOutputMaterial")
    nt.links.new(diff.outputs[0], s2r.inputs[0])
    nt.links.new(s2r.outputs[0], ramp.inputs[0])
    nt.links.new(ramp.outputs[0], emit.inputs[0])
    if alpha < 1.0:
        tr = nt.nodes.new("ShaderNodeBsdfTransparent")
        mix = nt.nodes.new("ShaderNodeMixShader")
        mix.inputs[0].default_value = alpha
        nt.links.new(tr.outputs[0], mix.inputs[1])
        nt.links.new(emit.outputs[0], mix.inputs[2])
        nt.links.new(mix.outputs[0], out.inputs[0])
        try:
            m.surface_render_method = "BLENDED"
        except Exception:
            pass
    else:
        nt.links.new(emit.outputs[0], out.inputs[0])
    m.diffuse_color = col(base)
    _mats[key] = m
    return m


def outline_mat():
    if "outline" in _mats:
        return _mats["outline"]
    m = bpy.data.materials.new("outline")
    m.use_nodes = True
    nt = m.node_tree
    nt.nodes.clear()
    emit = nt.nodes.new("ShaderNodeEmission")
    emit.inputs[0].default_value = col(DARK)
    out = nt.nodes.new("ShaderNodeOutputMaterial")
    nt.links.new(emit.outputs[0], out.inputs[0])
    m.use_backface_culling = True
    _mats["outline"] = m
    return m


# ----------------------------------------------------------------------------- geometry
_ctx = {"coll": None, "objs": [], "parent": None}


def _obj(name, bm, mat, smooth=True, outline=True, thick=0.0):
    me = bpy.data.meshes.new(name)
    bm.to_mesh(me)
    bm.free()
    for p in me.polygons:
        p.use_smooth = smooth
    ob = bpy.data.objects.new(name, me)
    _ctx["coll"].objects.link(ob)
    ob.data.materials.append(mat)
    if thick:
        s = ob.modifiers.new("thick", "SOLIDIFY")
        s.thickness = thick
        s.offset = 0
    if outline:
        ob.data.materials.append(outline_mat())
        s = ob.modifiers.new("outline", "SOLIDIFY")
        s.thickness = OL
        s.offset = 1
        s.use_flip_normals = True
        s.use_rim = False
        s.material_offset = 1
    if _ctx["parent"] is not None:
        ob.parent = _ctx["parent"]
    _ctx["objs"].append(ob)
    return ob


def S(loc, scl, mat, rot=(0, 0, 0), outline=True):
    """ellipsoid; rot in degrees (XYZ)."""
    if not isinstance(scl, (tuple, list)):
        scl = (scl, scl, scl)
    bm = bmesh.new()
    bmesh.ops.create_uvsphere(bm, u_segments=28, v_segments=16, radius=1.0)
    M = (Matrix.Translation(loc) @ Euler([math.radians(r) for r in rot]).to_matrix().to_4x4()
         @ Matrix.Diagonal((scl[0], scl[1], scl[2], 1)))
    bmesh.ops.transform(bm, matrix=M, verts=bm.verts)
    return _obj("sph", bm, mat, outline=outline)


def _smooth_path(pts, n=6):
    """Catmull-Rom resample."""
    P = [Vector(p) for p in pts]
    if len(P) < 3:
        return [P[0].lerp(P[-1], t / (n * 2)) for t in range(n * 2 + 1)], None
    out = []
    ext = [P[0] * 2 - P[1]] + P + [P[-1] * 2 - P[-2]]
    for i in range(1, len(ext) - 2):
        p0, p1, p2, p3 = ext[i - 1], ext[i], ext[i + 1], ext[i + 2]
        for k in range(n):
            t = k / n
            out.append(0.5 * ((2 * p1) + (-p0 + p2) * t + (2 * p0 - 5 * p1 + 4 * p2 - p3) * t * t
                              + (-p0 + 3 * p1 - 3 * p2 + p3) * t * t * t))
    out.append(P[-1])
    return out, None


def T(pts, rads, mat, outline=True, caps=True, ring=12):
    """tube through pts; rads is a list (same length as pts) or a (start, end) pair."""
    path, _ = _smooth_path(pts)
    if len(rads) == len(pts) and len(pts) > 2:
        # interpolate per-control radius along resampled path
        seg = (len(path) - 1) / (len(pts) - 1)
        R = []
        for i in range(len(path)):
            f = i / seg
            j = min(int(f), len(pts) - 2)
            t = f - j
            R.append(rads[j] * (1 - t) + rads[j + 1] * t)
    else:
        R = [rads[0] + (rads[-1] - rads[0]) * i / (len(path) - 1) for i in range(len(path))]
    bm = bmesh.new()
    rings = []
    up = Vector((0, 1, 0))
    for i, p in enumerate(path):
        tg = (path[min(i + 1, len(path) - 1)] - path[max(i - 1, 0)]).normalized()
        a = tg.cross(up)
        if a.length < 1e-3:
            a = tg.cross(Vector((1, 0, 0)))
        a.normalize()
        b = tg.cross(a).normalized()
        rings.append([bm.verts.new(p + R[i] * (math.cos(2 * math.pi * k / ring) * a
                                                   + math.sin(2 * math.pi * k / ring) * b))
                      for k in range(ring)])
    for r0, r1 in zip(rings, rings[1:]):
        for k in range(ring):
            bm.faces.new((r0[k], r0[(k + 1) % ring], r1[(k + 1) % ring], r1[k]))
    if caps:
        for r, rev in ((rings[0], True), (rings[-1], False)):
            bm.faces.new(list(reversed(r)) if rev else r)
    bmesh.ops.recalc_face_normals(bm, faces=bm.faces)
    return _obj("tube", bm, mat, outline=outline)


def F(pts2d, mat, y=0.0, thick=0.5, outline=True, smooth_n=0):
    """flat plate in the XZ plane (fins, wings, leaves); pts2d = [(x, z), ...]."""
    if smooth_n:
        pts, _ = _smooth_path([(x, 0, z) for x, z in pts2d] + [(pts2d[0][0], 0, pts2d[0][1])], smooth_n)
        pts2d = [(p.x, p.z) for p in pts[:-1]]
    bm = bmesh.new()
    vs = [bm.verts.new((x, y, z)) for x, z in pts2d]
    bm.faces.new(vs)
    bmesh.ops.triangulate(bm, faces=bm.faces[:])
    bmesh.ops.recalc_face_normals(bm, faces=bm.faces)
    return _obj("plate", bm, mat, smooth=False, outline=outline, thick=thick)


def ellipse_pts(cx, cz, rx, rz, ang=0, n=20):
    a = math.radians(ang)
    pts = []
    for k in range(n):
        t = 2 * math.pi * k / n
        x, z = rx * math.cos(t), rz * math.sin(t)
        pts.append((cx + x * math.cos(a) - z * math.sin(a), cz + x * math.sin(a) + z * math.cos(a)))
    return pts


WHITE = "f6f6ee"
BLACK = "141018"


def eye(loc, r, look=(0.45, -1.0, 0.1), white=WHITE, pupil=0.45):
    S(loc, r if isinstance(r, (tuple, list)) else (r, r * 0.75, r), toon(white, "b8bcc8", "ffffff"))
    lk = Vector(look).normalized()
    rr = r[0] if isinstance(r, (tuple, list)) else r
    S(Vector(loc) + lk * rr * 0.62, rr * pupil, toon(BLACK, "000000", "3a3444"), outline=False)


def group(loc=(0, 0, 0), rot=(0, 0, 0)):
    e = bpy.data.objects.new("pose_root", None)
    _ctx["coll"].objects.link(e)
    e.location = loc
    e.rotation_euler = [math.radians(r) for r in rot]
    _ctx["parent"] = e
    return e


# ----------------------------------------------------------------------------- creatures
def snail(f):
    shell = toon("804040", "5c1c1c", "a46464")
    groove = toon("4a1212", "330808", "6a2a2a")
    green = toon("26aa02", "1a7a02", "70fe39")
    if f == 2:   # hit: shell knocked back/up-left, body squashed, face hidden by gag overlay
        S((16.5, 0, 2.0), (8.5, 2.8, 1.9), green)
        S((17, 0, 4.6), (3.6, 2.4, 3.2), green)
        sc, srot, ssc = (8.0, 0.6, 10.5), (0, -28, 0), (6.6, 3.7, 6.3)
    else:
        st = 0.6 if f == 1 else 0.0
        S((8.2 + st * 0.4, 0, 2.0), (7.0 + st * 0.5, 2.6, 1.9), green)
        T([(11.5 + st, 0, 2.2), (13 + st, 0, 3.4), (13.8 + st, 0, 4.2)], [2.0, 1.9, 1.7], green)
        T([(13.0 + st, -0.8, 4.6), (12.8 + st, -1.0, 6.0)], [0.5, 0.45], green)
        T([(14.2 + st, 0.6, 4.6), (14.6 + st, 0.4, 6.0)], [0.5, 0.45], green)
        eye((12.7 + st, -1.1, 6.4), 1.15, look=(0.5, -1, 0))
        eye((14.7 + st, 0.2, 6.4), 1.15, look=(0.5, -1, 0))
        sc, srot, ssc = (6.8, 0.6, 8.3), (0, 0, 0), (6.3, 3.6, 6.0)
    S(sc, ssc, shell, rot=srot)
    # spiral groove on the visible side of the shell
    pts = []
    rotm = Euler([math.radians(r) for r in srot]).to_matrix()
    for k in range(36):
        th = k / 35 * 3.3 * math.pi
        r = 0.7 + 4.0 * k / 35
        dx, dz = r * math.cos(th + 2.6), r * math.sin(th + 2.6)
        q = 1 - (dx / ssc[0]) ** 2 - (dz / ssc[2]) ** 2
        dy = -math.sqrt(max(q, 0.0)) * ssc[1] - 0.12
        pts.append(Vector(sc) + rotm @ Vector((dx + 0.5, dy, dz)))
    T(pts[::2], [0.28, 0.42], groove, outline=False)


def toad(f):
    g = toon("26aa02", "028600", "70fe39")
    belly = toon("4ecf1d", "26aa02", "8cff60")
    dk = toon("0a4a00", "052a00", "116a00")
    if f == 2:   # hit: sits up facing the camera, legs splayed (gag covers the face)
        S((10.5, 0, 8.5), (6.0, 4.0, 6.2), g)
        S((10.5, -1.5, 7.5), (4.2, 3.0, 4.6), belly)
        S((10.5, 0, 14.5), (6.2, 4.2, 4.6), g)
        for s in (-1, 1):
            T([(10.5 + s * 4.5, -1, 6), (10.5 + s * 8.5, -2, 3.5), (10.5 + s * 7.5, -2, 0.6),
               (10.5 + s * 10.0, -2, 0.4)], [1.6, 1.2, 0.9, 0.7], g)
            T([(10.5 + s * 3.0, -2.5, 9), (10.5 + s * 5.0, -3.2, 4.0), (10.5 + s * 4.0, -3.2, 0.5)],
              [1.0, 0.8, 0.6], g)
        return
    if f == 0:
        root = group((0, 0, 0))
        S((7.4, 0, 4.8), (6.8, 4.2, 4.3), g, rot=(0, -12, 0))
        S((8.8, -1.6, 3.4), (4.4, 2.8, 2.6), belly)
        S((12.6, 0, 7.0), (4.5, 3.6, 3.2), g)
        S((10.4, 0, 9.9), (2.3, 2.0, 2.2), g)
        S((10.8, -1.3, 10.6), (0.95, 0.75, 1.5), toon("e2e2e2", "a8a8b8", "ffffff"))
        S((11.4, -1.95, 10.6), 0.42, toon(BLACK, "000000", "302838"), outline=False)
        T([(16.6, -1.2, 6.6), (14.2, -2.8, 6.1), (11.8, -3.0, 6.6)], [0.25, 0.25, 0.2], dk, outline=False)
        S((4.0, -2.6, 3.8), (3.7, 1.6, 2.7), g, rot=(0, -20, 0))
        T([(6.0, -3.0, 1.4), (2.5, -3.0, 0.7), (0.4, -3.0, 0.5)], [1.0, 0.75, 0.6], g)
        T([(12.0, -2.6, 4.5), (13.0, -2.8, 2.0), (13.6, -2.8, 0.7), (15.6, -2.8, 0.5)],
          [1.0, 0.85, 0.7, 0.55], g)
        T([(12.5, 2.4, 4.5), (14.0, 2.4, 0.6), (16.0, 2.4, 0.5)], [0.9, 0.7, 0.5], g)
    else:        # leap
        S((10.6, 0, 10.2), (6.6, 4.0, 4.1), g, rot=(0, -35, 0))
        S((11.5, -1.6, 9.0), (4.2, 2.8, 2.4), belly, rot=(0, -35, 0))
        S((15.4, 0, 14.0), (4.4, 3.5, 3.1), g, rot=(0, -25, 0))
        S((12.6, 0, 16.8), (2.2, 2.0, 2.1), g)
        S((13.0, -1.3, 17.5), (0.95, 0.75, 1.5), toon("e2e2e2", "a8a8b8", "ffffff"))
        S((13.6, -1.95, 17.5), 0.42, toon(BLACK, "000000", "302838"), outline=False)
        T([(19.6, -1.2, 13.4), (17.4, -2.7, 12.4), (15.2, -3.0, 12.6)], [0.25, 0.25, 0.2], dk, outline=False)
        T([(7.0, -2.6, 8.0), (4.6, -2.8, 5.0), (2.4, -2.8, 2.0), (0.6, -2.8, 0.6)],
          [2.0, 1.3, 0.9, 0.6], g)
        T([(8.6, 2.4, 7.5), (8.0, 2.4, 3.5), (7.6, 2.4, 0.6), (9.6, 2.4, 0.4)], [1.6, 1.1, 0.8, 0.6], g)
        T([(15.0, -2.4, 9.5), (16.4, -2.6, 6.5), (18.0, -2.6, 5.2)], [0.9, 0.75, 0.6], g)


def spider(f):
    leg = toon("cc64fa", "643075", "fc99fb")
    body = toon("3074cf", "1d4a8a", "6cadf1")
    S((8, 3.0, 9.0), (3.4, 3.4, 3.0), body)
    S((8, 0, 6.2), (3.0, 2.6, 2.6), body)
    for s in (-1, 1):
        eye((8 + s * 1.2, -2.3, 4.9), 0.85, look=(0, -1, -0.2), white="f0f4ee", pupil=0.35)
        T([(8 + s * 0.6, -2.0, 4.4), (8 + s * 0.9, -2.4, 2.6)], [0.45, 0.2], leg)
        for i in range(4):
            up = 1.2 if (i + (f if f in (0, 1) else 0)) % 2 == 0 else 0.0
            T([(8 + s * 2.0, -0.6 + i * 0.9, 6.6),
               (8 + s * (4.6 + i * 0.35), -1.2 + i * 1.2, 10.6 + up - i * 0.5),
               (8 + s * (6.6 + i * 0.15), -1.6 + i * 1.6, 4.0 + up * 0.5),
               (8 + s * (7.4 - i * 0.2), -1.8 + i * 1.8, 0.4 + up * 0.6)],
              [0.75, 0.6, 0.5, 0.35], leg)


def bat(f):
    body = toon("0188dc", "005577", "66ccff")
    wingm = toon("005577", "0a2c2b", "0188dc")
    bone = toon("33aaf0", "0188dc", "66ccff")
    tilt = 14 if f == 2 else 0
    group((8, 0, 7), (0, tilt, 0))
    c = (0, 0, 0)
    S((0, 0, -0.4), (2.8, 2.2, 3.6), body)
    S((0, -0.3, 3.8), (2.6, 2.2, 2.3), body)
    for s in (-1, 1):
        T([(s * 1.4, 0, 5.2), (s * 2.1, 0, 7.2)], [0.8, 0.12], body)
        S((s * 0.9, -2.1, 4.0), 0.48, toon("ffffff", "77ccbb", "ffffff"), outline=False)
        T([(s * 0.9, 0, -3.5), (s * 1.1, -0.4, -6.2)], [0.5, 0.3], body)
        if f == 1 or f == 2:     # spread (hit: half-spread, drooping)
            k = 1.0 if f == 1 else 0.68
            dz = 0.0 if f == 1 else -1.5 * s
            tips = [(15.6 * k, 4.0 + dz), (12.3 * k, -3.4 + dz), (6.8 * k, -6.0 + dz)]
            sc = [(13.4 * k, -1.2 + dz), (9.6 * k, -2.6 + dz), (4.0 * k, -3.6 + dz)]
            poly = [(2.2, 2.5), tips[0], sc[0], tips[1], sc[1], tips[2], sc[2], (2.0, -3.0)]
        else:                    # folded: hangs at the sides, wrapping forward
            tips = [(7.6, 5.0), (7.4, -4.8), (4.8, -6.8)]
            sc = [(7.9, 0.0), (6.2, -4.6), (3.3, -5.2)]
            poly = [(2.2, 3.2), (6.0, 5.6), tips[0], sc[0], tips[1], sc[1], tips[2], sc[2], (2.1, -3.2)]
        poly = [(s * x, z) for x, z in poly]
        if s < 0:
            poly = poly[::-1]
        w = F(poly, wingm, y=0.6, thick=0.35)
        if f == 0:
            w.rotation_euler = (0, 0, math.radians(-22 * s))
        for t in tips:
            T([(s * 2.2, 0.3, 2.5), (s * t[0] * 0.55, 0.2, (2.5 + t[1]) * 0.55 + 1.6), (s * t[0], 0.2, t[1])],
              [0.42, 0.32, 0.18], bone, outline=False).rotation_euler = w.rotation_euler
    _ctx["parent"] = None


def wasp(f, palette="yellow"):
    if palette == "yellow":
        yel = toon("ecb730", "c8930c", "ffdb54")
        brn = toon("6d340a", "491000", "91582e")
        wing_c = "e6ecf4"
    S((12.8, 0, 15.3), (2.6, 2.3, 2.4), brn)
    S((14.0, -1.7, 16.4), (1.5, 0.7, 1.15), toon(WHITE, "b8bcc8", "ffffff"))
    S((14.8, -2.1, 16.5), 0.55, toon(BLACK, "000000", "3a3444"), outline=False)
    S((15.4, -0.4, 16.4), (1.2, 0.7, 1.05), toon(WHITE, "b8bcc8", "ffffff"))
    S((16.0, -0.7, 16.5), 0.5, toon(BLACK, "000000", "3a3444"), outline=False)
    S((11.0, 0, 12.4), (2.4, 2.2, 2.3), brn)
    ab = [(10.0, 0, 10.0), (8.8, 0, 7.8), (9.0, 0, 5.3), (10.6, 0, 3.2), (12.4, 0, 1.7)]
    rr = [2.4, 2.6, 2.5, 2.1, 1.4]
    for i, (p, r) in enumerate(zip(ab, rr)):
        S(p, (r, r * 0.9, r * 0.85), yel if i % 2 == 0 else brn, rot=(0, -30 + i * 25, 0))
    T([(13.2, 0, 1.0), (15.4, 0, 0.3)], [0.6, 0.08], toon(WHITE, "b8bcc8", "ffffff"))
    for i in range(2):
        T([(11 + i, -1.4, 10.8), (12.8 + i, -2.0, 9.2), (14 + i, -2.0, 9.6)], [0.35, 0.3, 0.2],
          toon("2a1408", "100400", "4a2a10"), outline=False)
    wm = toon(wing_c, "aab4c8", "ffffff", alpha=0.62)
    if f == 1:
        F(ellipse_pts(5.5, 13.0, 5.2, 1.9, 12), wm, y=1.2, thick=0.2)
        F(ellipse_pts(6.5, 11.8, 4.2, 1.5, -8), wm, y=1.6, thick=0.2)
    else:
        F(ellipse_pts(6.2, 15.8, 5.4, 1.9, 38), wm, y=1.2, thick=0.2)
        F(ellipse_pts(5.0, 13.0, 4.4, 1.6, 18), wm, y=1.6, thick=0.2)


def fish(f, red=False, mirror=False, rot=0):
    if red:
        b = toon("fa3c47", "d61823", "ff8087")
        dk = toon("6e0000", "400000", "8a1010")
    else:
        b = toon("5da5da", "3981b6", "81c9fe")
        dk = toon("00396e", "002448", "104a80")
    sg = -1 if mirror else 1
    group((8, 0, 6), (0, rot, 0))
    X = lambda x: sg * (x - 8)   # noqa: E731

    S((X(8.6), 0, 0), (6.0, 3.0, 5.0), b)
    tail = [(3.4, 6), (0.2, 10.6), (1.4, 6.0), (0.2, 1.4)]
    fins = [[(6, 10.2), (8.6, 12.0), (10.6, 10.0)], [(7.2, 1.6), (8.0, 0.0), (10.2, 1.4)]]
    for poly in [tail] + fins:
        pts = [(X(x), z - 6) for x, z in poly]
        if mirror:
            pts = pts[::-1]
        F(pts, b, y=0.2, thick=0.5)
    eye((X(11.6), -2.4, 2.4), 1.3, look=(0.5 * sg, -1, 0.05), white="f3f3ea")
    T([(X(9.6), -2.85, 2.2), (X(9.0), -3.05, 0.0), (X(9.6), -2.6, -2.4)], [0.3, 0.32, 0.25], dk, outline=False)
    for i, z in enumerate((-0.6, 0.8)):
        T([(X(5.0 + i), -2.2, z + 1.2), (X(7.0 + i), -2.7, z), (X(5.2 + i), -2.3, z - 1.2)],
          [0.22, 0.25, 0.2], dk, outline=False)
    if f == 0:   # mouth open with teeth
        S((X(14.2), -1.0, -0.8), (1.4, 1.8, 1.5), toon("2a0a10", "100004", "4a1a20"))
        for z in (0.3, -1.9):
            T([(X(13.6), -1.6, z), (X(15.0), -1.6, z * 0.6 - 0.3)], [0.55, 0.08], toon("ffffff", "c8d8e8", "ffffff"))
    else:
        T([(X(14.6), -2.0, -0.6), (X(12.6), -2.7, -0.9)], [0.25, 0.2], dk, outline=False)
    _ctx["parent"] = None


def rat(f):
    fur = toon("544338", "301f14", "7a6656")
    yel = toon("bc8f07", "8a6604", "f5bb0a")
    pink = toon("ff8080", "c05050", "ffb0b0")
    if f == 2:   # hit: rears up facing camera, tail curling; the eye gag is overlaid
        S((11.0, 0, 7.5), (4.6, 3.2, 5.6), fur, rot=(0, 18, 0))
        S((10.5, -0.5, 13.0), (3.4, 2.8, 3.0), fur)
        for s in (-1, 1):
            S((10.5 + s * 2.6, 0, 16.0), (1.4, 0.5, 1.8), fur)
            T([(11 + s * 3, -1.5, 9), (11 + s * 6.5, -2.0, 7.0), (11 + s * 8.5, -2.0, 6.4)], [0.8, 0.5, 0.35], fur)
            T([(11 + s * 3, -1.0, 3), (11 + s * 5.5, -1.5, 1.0), (11 + s * 4.0, -1.5, 0.4)], [0.8, 0.5, 0.4], yel)
        T([(7.5, 0, 3.0), (3.5, 0, 2.6), (1.2, 0, 4.2), (2.2, 0, 6.0)], [0.5, 0.4, 0.35, 0.3], yel)
        return
    st = 0.8 if f == 1 else 0.0
    S((12.0 + st * 0.5, 0, 3.4), (7.0 + st, 2.6, 3.3), fur)
    S((19.3 + st, 0, 3.0), (3.3, 2.2, 2.4), fur)
    T([(20.5 + st, 0, 2.8), (24.0 + st, 0, 1.5)], [1.8, 0.45], fur)
    S((24.2 + st, -0.3, 1.6), 0.55, pink, outline=False)
    S((18.4 + st, -0.4, 6.0), (1.3, 0.45, 1.7), fur)
    S((18.4 + st, -0.85, 6.0), (0.7, 0.2, 1.0), pink, outline=False)
    S((21.0 + st, -1.85, 3.6), 0.4, toon(BLACK, "000000", "3a3444"), outline=False)
    tz = 1.2 if f == 1 else 0.6
    T([(5.6, 0, 2.2), (3.0, 0, tz), (0.3, 0, 0.5)], [0.55, 0.42, 0.3], yel)
    for x in ((8.5, 16.5) if f == 0 else (10.0, 15.0)):
        S((x + st * 0.5, -1.5, 0.45), (1.3, 0.6, 0.45), yel)


def flower(f):
    red = toon("db0000", "b60000", "ff3a3a")
    seam = toon("6e0000", "4a0000", "8a0000")
    g = toon("00b600", "006d00", "00ff00")
    bob = -0.5 if f == 1 else 0.0
    T([(10.0, 0, 9.0), (7.0, 0, 6.2), (8.0, 0, 3.0), (9.2, 0, 0.6)], [0.9, 0.85, 0.8, 0.8], g)
    F([(9.2, 0.6), (4.0, 4.6), (0.4, 5.2), (2.0, 2.4), (5.0, 0.4)], g, y=0.4, thick=0.8, smooth_n=3)
    F([(9.4, 0.6), (14.0, 4.0), (17.8, 4.6), (16.6, 1.8), (13.0, 0.2)], g, y=-0.6, thick=0.8, smooth_n=3)
    T([(4.6, 0.6, 9.6 + bob), (2.4, 0.6, 14.0 + bob), (3.8, 0.6, 19.6 + bob)], [0.55, 0.6, 0.45], g)
    if f == 3:   # bite: bulb opens to the right, teeth
        cx, cz = 10.6, 15.6
        S((cx, 0, cz), (7.8, 5.0, 7.4), red)
        S((cx + 6.8, -0.8, cz - 1.6), (3.2, 3.6, 3.8), toon("3a0006", "1a0002", "5a0010"))
        for i in range(3):
            T([(cx + 5.6 + i * 1.6, -2.6, cz + 1.8 - i * 0.2), (cx + 6.4 + i * 1.5, -2.8, cz - 0.2)],
              [0.55, 0.06], toon("ffffff", "c8c8d0", "ffffff"))
            T([(cx + 5.6 + i * 1.6, -2.6, cz - 4.4 + i * 0.3), (cx + 6.2 + i * 1.5, -2.8, cz - 2.6)],
              [0.55, 0.06], toon("ffffff", "c8c8d0", "ffffff"))
        S((cx - 2.5, -3.6, cz + 3.6), (1.8, 0.4, 1.0), toon("ff7070", "ff3a3a", "ffb0b0"), rot=(0, 20, 0), outline=False)
        return
    cx, cz = 11.4, 15.6 + bob
    S((cx, 0, cz), (7.6, 4.8, 7.2), red, rot=(0, 6 if f == 1 else 0, 0))
    T([(cx - 2.0, -4.3, cz + 1.0), (cx + 3.0, -3.9, cz - 1.2), (cx + 7.3, -1.6, cz - 2.6)], [0.35, 0.4, 0.3], seam, outline=False)
    T([(cx + 0.5, -4.6, cz - 2.6), (cx + 3.6, -4.0, cz - 2.4), (cx + 7.0, -1.8, cz - 3.4)], [0.3, 0.35, 0.3], seam, outline=False)
    S((cx - 2.6, -3.5, cz + 3.6), (1.8, 0.4, 1.0), toon("ff7070", "ff3a3a", "ffb0b0"), rot=(0, 20, 0), outline=False)


def pflower(f):
    pk = toon("ff5bff", "b624b6", "e0a1e0")
    g = toon("00b600", "006d00", "00ff00")
    F([(10, 1.4), (4.0, 3.6), (0.4, 3.4), (3.0, 1.0), (7, 0.2)], g, y=-1.0, thick=0.8, smooth_n=3)
    F([(10, 1.4), (16.0, 3.6), (19.6, 3.4), (17.0, 1.0), (13, 0.2)], g, y=-1.0, thick=0.8, smooth_n=3)
    if f == 2:   # hit: bent over to the left (debris overlaid from the original)
        S((12.0, 0, 6.0), (5.0, 4.2, 4.8), pk, rot=(0, 25, 0))
        T([(9.2, 0, 9.0), (7.6, 0, 12.5), (8.4, 0, 16.5), (8.6, 0, 19.0)], [1.9, 1.4, 1.5, 1.4], pk)
        S((8.6, 0, 20.6), (1.9, 1.8, 1.9), pk)
        return
    if f == 3:
        bz, bh, nz, top = 8.4, 6.2, 18.4, 21.0
    elif f == 1:
        bz, bh, nz, top = 6.2, 4.6, 12.6, 15.0
    else:
        bz, bh, nz, top = 6.8, 5.0, 14.2, 16.8
    S((10, 0, bz), (5.6 if f != 3 else 4.6, 4.4, bh), pk)
    T([(10, 0, bz + bh * 0.6), (10, 0, nz)], [1.5, 1.3], pk)
    S((10, 0, top - 1.2), (2.5, 2.1, 1.5), pk)
    S((8.2, -3.6, bz + 1.4), (1.0, 0.4, 1.8), toon("ffb0ff", "ff5bff", "ffe0ff"), outline=False)


# frame name -> (builder, args, camera elevation deg)
FRAMES = {
    "m100": (snail, (0,), 0), "m101": (snail, (1,), 0), "m102": (snail, (2,), 0),
    "m400": (toad, (0,), 0), "m401": (toad, (1,), 0), "m402": (toad, (2,), 0),
    "m500": (spider, (0,), 25), "m501": (spider, (1,), 25), "m502": (spider, (2,), 25),
    "m200": (bat, (0,), 0), "m201": (bat, (1,), 0), "m202": (bat, (2,), 0),
    "m600": (wasp, (0,), 0), "m601": (wasp, (1,), 0), "m602": (wasp, (0,), 0),
    "m800": (fish, (0,), 0), "m801": (fish, (1,), 0),
    "m1000": (fish, (0, True), 0), "m1001": (fish, (1, True), 0),
    "m1002": (fish, (0, True, True, -90), 0), "m1003": (fish, (0, True, True, 90), 0),
    "m1400": (rat, (0,), 0), "m1401": (rat, (1,), 0), "m1402": (rat, (2,), 0),
    "m1800": (flower, (0,), 0), "m1801": (flower, (1,), 0), "m1803": (flower, (3,), 0),
    "m1900": (pflower, (0,), 0), "m1901": (pflower, (1,), 0), "m1902": (pflower, (2,), 0),
    "m1903": (pflower, (3,), 0),
}


# ----------------------------------------------------------------------------- scene
def setup_scene():
    sc = bpy.context.scene
    for eng in ("BLENDER_EEVEE", "BLENDER_EEVEE_NEXT"):
        try:
            sc.render.engine = eng
            break
        except TypeError:
            continue
    sc.render.film_transparent = True
    sc.view_settings.view_transform = "Standard"
    sc.view_settings.look = "None"
    sc.render.image_settings.file_format = "PNG"
    sc.render.image_settings.color_mode = "RGBA"
    try:
        sc.eevee.taa_render_samples = 16
    except Exception:
        pass
    for ob in list(bpy.data.objects):
        bpy.data.objects.remove(ob)
    w = bpy.data.worlds.new("w")
    w.use_nodes = True
    w.node_tree.nodes["Background"].inputs[0].default_value = (0, 0, 0, 1)
    sc.world = w
    sun_d = bpy.data.lights.new("sun", "SUN")
    sun_d.energy = 3.2
    sun_d.use_shadow = False
    sun = bpy.data.objects.new("sun", sun_d)
    sc.collection.objects.link(sun)
    d = Vector((0.55, 1.0, -0.9)).normalized()          # light travels down-right-away
    sun.rotation_euler = d.to_track_quat("-Z", "Y").to_euler()
    cam_d = bpy.data.cameras.new("cam")
    cam_d.type = "ORTHO"
    cam = bpy.data.objects.new("cam", cam_d)
    sc.collection.objects.link(cam)
    sc.camera = cam
    return sc, cam


def frame_camera(sc, cam, objs, elev):
    R = Euler((math.radians(90 - elev), 0, 0)).to_matrix()
    Ri = R.inverted()
    dg = bpy.context.evaluated_depsgraph_get()
    xs, ys, zs = [], [], []
    for ob in objs:
        if ob.type != "MESH":
            continue
        ev = ob.evaluated_get(dg)
        me = ev.to_mesh()
        mw = ob.matrix_world
        for v in me.vertices:
            p = Ri @ (mw @ v.co)
            xs.append(p.x)
            ys.append(p.y)
            zs.append(p.z)
        ev.to_mesh_clear()
    x0, x1, y0, y1 = min(xs), max(xs), min(ys), max(ys)
    m = 0.6
    w, h = x1 - x0 + 2 * m, y1 - y0 + 2 * m
    sc.render.resolution_x = int(math.ceil(w * PPU))
    sc.render.resolution_y = int(math.ceil(h * PPU))
    sc.render.resolution_percentage = 100
    cam.data.ortho_scale = max(sc.render.resolution_x, sc.render.resolution_y) / PPU
    cam.data.clip_start, cam.data.clip_end = 0.1, 1000
    cam.rotation_euler = R.to_euler()
    cam.location = R @ Vector(((x0 + x1) / 2, (y0 + y1) / 2, max(zs) + 200))


def main():
    argv = sys.argv[sys.argv.index("--") + 1:] if "--" in sys.argv else []
    out, only, blend = "art/monsters/raw", None, None
    i = 0
    while i < len(argv):
        if argv[i] == "--out":
            out = argv[i + 1]; i += 2
        elif argv[i] == "--only":
            only = set(argv[i + 1].split(",")); i += 2
        elif argv[i] == "--blend":
            blend = argv[i + 1]; i += 2
        else:
            i += 1
    os.makedirs(out, exist_ok=True)
    sc, cam = setup_scene()
    names = [n for n in FRAMES if only is None or n in only]
    colls = {}
    for idx, n in enumerate(names):
        fn, args, elev = FRAMES[n]
        c = bpy.data.collections.new(n)
        sc.collection.children.link(c)
        colls[n] = c
        _ctx.update(coll=c, objs=[], parent=None)
        fn(*args)
        objs = list(c.objects)
        for other in colls.values():
            other.hide_render = other is not c
        bpy.context.view_layer.update()
        frame_camera(sc, cam, objs, elev)
        sc.render.filepath = os.path.abspath(os.path.join(out, n + ".png"))
        bpy.ops.render.render(write_still=True)
        print("RENDERED", n, sc.render.resolution_x, sc.render.resolution_y, flush=True)
    if blend:
        # lay the frames out in a grid for the editable file (camera framing is per frame
        # in this script; offsets are applied to pose roots / loose objects only)
        for idx, n in enumerate(names):
            c = colls[n]
            c.hide_render = False
            off = Vector(((idx % 8) * 40.0, 0, -(idx // 8) * 30.0))
            for ob in c.objects:
                if ob.parent is None:
                    ob.location += off
        os.makedirs(os.path.dirname(os.path.abspath(blend)), exist_ok=True)
        bpy.ops.wm.save_as_mainfile(filepath=os.path.abspath(blend))
        print("SAVED", blend)


main()
