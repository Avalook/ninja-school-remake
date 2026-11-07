"""Build the hero 2D cutout rig and render one pose per original frame index B (0..45).

Run (normally via scripts/run_hero_pipeline.py):
  Blender --background --factory-startup --python-exit-code 1 \
      --python scripts/blender_hero_rig.py -- --project-root . [--frames 0,1,13] [--no-render]

Inputs : art/hero/parts/*.png + parts.json (from scripts/hero_split_parts.py)
Outputs: art/blender/hero_rig.blend          (armature + textured planes + katana, packed,
                                              one constant-interpolated keyframe per B at frame B)
         art/hero/build/raw/bXX.png          (RENDER_SCALE x (256+2*MARGIN) px, transparent;
                                              the 256 cell is the centred window)
         art/hero/build/render-report.json   (engine, timings, per-frame placement)

World units: 1 BU = 100 source px. Source ground point (620,1215) = world origin.
Screen convention for every pose angle below: degrees, counter-clockwise positive as
seen by the camera; for hanging limbs + = swing forward (character faces +X / right).
"""

import argparse
import json
import math
import sys
import time
from pathlib import Path

import bmesh
import bpy
from mathutils import Matrix, Vector

CELL = 256
RENDER_SCALE = 4                 # raw render is 4x, box-downsampled on the host
MARGIN = 64                      # raw canvas = cell + MARGIN out-px on every side, so the
                                 # host can detect/repair overflow instead of losing pixels
ANCHOR = (128, 240)              # output pixel of the ground contact point
SRC_PER_OUT = 1178 / 200.0       # source head-top..sole (37..1215) -> 200 output px
GROUND_SRC = (620, 1215)
DEPTH_STEP = 0.5                 # BU between z layers (toward camera = -Y)

ROOT = None


def W(sx, sy, depth=0.0):
    return Vector(((sx - GROUND_SRC[0]) / 100.0, depth, (GROUND_SRC[1] - sy) / 100.0))


def out_px_to_bu(px):
    return px * SRC_PER_OUT / 100.0


# --------------------------------------------------------------------------------------
# Pose library. Keys: bu/bf back upper/fore arm, fu/ff front arm, bt/bs back thigh/shin,
# ft/fs front thigh/shin, torso, head, hips (whole body spin), breathe (torso stretch),
# kd = katana world direction (deg, 0 = pointing forward/right), kz = katana z layer,
# place = ("ground", lift_out_px) | ("neck", lift_out_px) | ("hips", height_out_px),
# show = list of extra/only parts.
# Lifts for airborne frames come from the original sheet: (56 - orig_top_2x) * 3.03 px.
# --------------------------------------------------------------------------------------
RUN = {
    "r2": dict(bt=-32, bs=-62, ft=22, fs=-22, bu=38, bf=70, fu=-36, ff=78, torso=-8),
    "r3": dict(bt=-40, bs=-28, ft=46, fs=-30, bu=52, bf=62, fu=-48, ff=70, torso=-11),
    "r4": dict(bt=6, bs=-92, ft=-8, fs=-14, bu=10, bf=82, fu=-8, ff=92, torso=-8),
    "r5": dict(bt=46, bs=-32, ft=-42, fs=-42, bu=-46, bf=70, fu=46, ff=70, torso=-11),
    "r6": dict(bt=-10, bs=-22, ft=6, fs=-92, bu=-10, bf=82, fu=12, ff=92, torso=-8),
}
STANCE = dict(bt=-26, bs=-6, ft=30, fs=-30)
AIR = dict(bt=-12, bs=-52, ft=62, fs=-84)
ATTACK = {   # upper body of 13..20 (re-used by 27..34 air and 35..42 run variants)
    13: dict(bu=72, bf=92, fu=62, ff=108, torso=0, head=0, kd=128, kz=5.5),
    14: dict(bu=-100, bf=60, fu=58, ff=18, torso=-6, head=-4, kd=-18, kz=8.5),
    15: dict(bu=40, bf=85, fu=22, ff=100, torso=-12, head=-6, kd=152, kz=5.5),
    16: dict(bu=-72, bf=8, fu=72, ff=28, torso=-8, head=-3, kd=14, kz=8.5),
    17: dict(bu=-42, bf=12, fu=150, ff=45, torso=6, head=4),
    18: dict(bu=-135, bf=25, fu=82, ff=8, torso=-10, head=-5, z={"back_fore": 7.5}),
    19: dict(bu=22, bf=62, fu=162, ff=8, torso=4, head=6, kd=100, kz=8.5),
    20: dict(bu=-130, bf=30, z={"back_fore": 7.5}, fu=76, ff=4, torso=-8, head=-4, kd=20, kz=8.5),
}


def merge(*ds):
    out = {}
    for d in ds:
        out.update(d)
    return out


def orig_lift(top2x):
    return (56 - top2x) * 3.03


ORIG_TOP = {7: 48, 12: 50, 21: 52, 23: 50, 24: 54, 27: 56, 28: 52, 29: 54, 30: 54, 31: 56,
            32: 54, 33: 56, 34: 54, 43: 44, 44: 42, 45: 34}

POSES = {}
POSES[0] = dict(place=("ground", 0))
POSES[1] = dict(breathe=1.035, bu=-3, fu=3, head=1, place=("ground", 0))
for b, key, lift in ((2, "r2", 0), (3, "r3", 0), (4, "r4", 0), (5, "r5", 12), (6, "r6", 0)):
    POSES[b] = merge(RUN[key], dict(place=("ground", lift)))
POSES[7] = dict(ft=76, fs=-96, bt=-16, bs=-52, bu=80, bf=55, fu=-22, ff=100, torso=-5,
                place=("neck", orig_lift(48)))
TUCK = dict(bt=118, bs=-150, ft=112, fs=-150, torso=-48, head=-24, bu=105, bf=55,
            fu=100, ff=60)
for i, b in enumerate(range(8, 12)):
    POSES[b] = merge(TUCK, dict(hips=-90 * i, place=("ball", 82), show=["spin_ring"]))
POSES[12] = dict(bu=-150, bf=20, z={"back_fore": 7.5}, fu=40, ff=122, ft=70, fs=-90, bt=-6, bs=-22, torso=5,
                 place=("neck", orig_lift(50)))
for b in range(13, 21):
    POSES[b] = merge(STANCE, ATTACK[b], dict(place=("ground", 0)))
POSES[21] = dict(bu=-150, bf=30, z={"back_fore": 7.5}, fu=110, ff=60, ft=66, fs=-88, bt=-8, bs=-24, torso=10,
                 head=8, place=("neck", orig_lift(52)), show=["head_hurt"], hide=["head"])
POSES[22] = dict(bt=70, bs=-112, ft=74, fs=-114, torso=-20, head=8, bu=34, bf=44, fu=40,
                 ff=40, place=("ground", 0))
POSES[23] = dict(ft=66, fs=-86, bt=-10, bs=-42, bu=92, bf=70, fu=30, ff=112, torso=0,
                 place=("neck", orig_lift(50)))
POSES[24] = dict(place=("neck", orig_lift(54)), only=["head_dig"])
POSES[25] = dict(place=("neck", 0), only=["eyes_open"])
POSES[26] = dict(place=("neck", 0), only=["eyes_half"])
for i, b in enumerate(range(27, 35)):
    POSES[b] = merge(AIR, ATTACK[13 + i], dict(place=("neck", orig_lift(ORIG_TOP[b]))))
for i, (b, key, lift) in enumerate(((35, "r2", 0), (36, "r3", 0), (37, "r4", 0),
                                    (38, "r5", 12), (39, "r6", 0), (40, "r3", 0),
                                    (41, "r4", 0), (42, "r5", 12))):
    legs = {k: v for k, v in RUN[key].items() if k in ("bt", "bs", "ft", "fs")}
    POSES[b] = merge(ATTACK[13 + i], legs, dict(place=("ground", lift)))
POSES[43] = dict(bt=4, bs=-12, ft=-6, fs=-6, bu=62, bf=102, fu=52, ff=112, torso=-12,
                 head=-6, place=("neck", orig_lift(44)))
POSES[44] = dict(bt=-2, bs=-8, ft=-10, fs=-4, bu=58, bf=108, fu=48, ff=116, torso=-10,
                 head=-4, place=("neck", orig_lift(42)))
POSES[45] = dict(bt=8, bs=-22, ft=14, fs=-28, bu=66, bf=98, fu=56, ff=108, torso=-8,
                 head=-2, place=("neck", orig_lift(34)))

# Back forearm/fist drawn in front of the torso when the pose brings it across the body.
for _b in (13, 15, 23, 27, 29, 35, 37):
    POSES[_b]["z"] = dict(POSES[_b].get("z", {}), back_fore=6.5)
# Stealth eyes are drawn enlarged in the original (cartoon eyes floating in the dark).
POSES[25]["scale"] = {"eyes_open": 2.3}
POSES[26]["scale"] = {"eyes_half": 2.3}

ANGLE_BONE = {"bu": "back_upper", "bf": "back_fore", "fu": "front_upper", "ff": "front_fore",
              "bt": "back_thigh", "bs": "back_shin", "ft": "front_thigh", "fs": "front_shin",
              "torso": "torso", "head": "head", "hips": "hips"}

PART_BONE = {"head": "head", "head_hurt": "head", "head_dig": "head", "eyes_open": "head",
             "eyes_half": "head", "torso": "torso", "back_upper": "back_upper",
             "back_fore": "back_fore", "front_upper": "front_upper",
             "front_fore": "front_fore", "back_thigh": "back_thigh",
             "back_shin": "back_shin", "front_thigh": "front_thigh",
             "front_shin": "front_shin"}
BODY = ["head", "torso", "back_upper", "back_fore", "front_upper", "front_fore",
        "back_thigh", "back_shin", "front_thigh", "front_shin"]
EXTRA_Z = {"head_hurt": 7, "head_dig": 7, "eyes_open": 7.2, "eyes_half": 7.2}


# --------------------------------------------------------------------------------------
def reset_scene():
    bpy.ops.wm.read_factory_settings(use_empty=True)
    scene = bpy.context.scene
    scene.name = "HeroRig"
    return scene


def setup_render(scene):
    engine_used = None
    for engine in ("BLENDER_EEVEE", "BLENDER_EEVEE_NEXT", "BLENDER_WORKBENCH"):
        try:
            scene.render.engine = engine
            engine_used = engine
            break
        except TypeError:
            continue
    r = scene.render
    r.resolution_x = r.resolution_y = (CELL + 2 * MARGIN) * RENDER_SCALE
    r.resolution_percentage = 100
    r.film_transparent = True
    r.filter_size = 0.75
    r.use_motion_blur = False
    r.image_settings.file_format = "PNG"
    r.image_settings.color_mode = "RGBA"
    r.image_settings.color_depth = "8"
    scene.view_settings.view_transform = "Standard"
    scene.view_settings.look = "None"
    scene.view_settings.exposure = 0
    scene.view_settings.gamma = 1
    if hasattr(scene, "eevee"):
        ee = scene.eevee
        for attr, val in (("taa_render_samples", 16), ("use_gtao", False),
                          ("use_bloom", False), ("use_shadows", False),
                          ("use_raytracing", False)):
            if hasattr(ee, attr):
                try:
                    setattr(ee, attr, val)
                except Exception:
                    pass
    world = bpy.data.worlds.new("HeroWorld")
    world.use_nodes = True
    bg = world.node_tree.nodes.get("Background")
    if bg:
        bg.inputs[0].default_value = (0, 0, 0, 1)
        bg.inputs[1].default_value = 0
    scene.world = world
    return engine_used


def setup_camera(scene):
    cam_data = bpy.data.cameras.new("HeroCam")
    cam_data.type = "ORTHO"
    span_src = (CELL + 2 * MARGIN) * SRC_PER_OUT     # source px across the raw canvas
    cam_data.ortho_scale = span_src / 100.0
    cam_data.clip_start = 0.1
    cam_data.clip_end = 200
    cam = bpy.data.objects.new("HeroCam", cam_data)
    scene.collection.objects.link(cam)
    # ground (0,0) must land on ANCHOR; camera centre is the cell centre
    cx = (CELL / 2 - ANCHOR[0]) * SRC_PER_OUT / 100.0
    cz = (ANCHOR[1] - CELL / 2) * SRC_PER_OUT / 100.0
    cam.location = (cx, -50, cz)
    cam.rotation_euler = (math.radians(90), 0, 0)
    scene.camera = cam
    return cam


def image_material(name, path):
    img = bpy.data.images.load(str(path), check_existing=True)
    img.alpha_mode = "STRAIGHT"
    img.colorspace_settings.name = "sRGB"
    mat = bpy.data.materials.new(name)
    mat.use_nodes = True
    nt = mat.node_tree
    nt.nodes.clear()
    tex = nt.nodes.new("ShaderNodeTexImage")
    tex.image = img
    tex.interpolation = "Linear"
    tex.extension = "CLIP"
    em = nt.nodes.new("ShaderNodeEmission")
    tr = nt.nodes.new("ShaderNodeBsdfTransparent")
    mix = nt.nodes.new("ShaderNodeMixShader")
    out = nt.nodes.new("ShaderNodeOutputMaterial")
    nt.links.new(tex.outputs["Color"], em.inputs["Color"])
    nt.links.new(tex.outputs["Alpha"], mix.inputs[0])
    nt.links.new(tr.outputs[0], mix.inputs[1])
    nt.links.new(em.outputs[0], mix.inputs[2])
    nt.links.new(mix.outputs[0], out.inputs["Surface"])
    for attr, val in (("surface_render_method", "BLENDED"), ("blend_method", "BLEND"),
                      ("use_backface_culling", False)):
        if hasattr(mat, attr):
            try:
                setattr(mat, attr, val)
            except Exception:
                pass
    if hasattr(mat, "show_transparent_back"):
        mat.show_transparent_back = False
    return mat


def flat_material(name, rgba, cull=False):
    mat = bpy.data.materials.new(name)
    mat.use_nodes = True
    nt = mat.node_tree
    nt.nodes.clear()
    em = nt.nodes.new("ShaderNodeEmission")
    em.inputs["Color"].default_value = rgba
    out = nt.nodes.new("ShaderNodeOutputMaterial")
    if rgba[3] < 1:
        tr = nt.nodes.new("ShaderNodeBsdfTransparent")
        mix = nt.nodes.new("ShaderNodeMixShader")
        mix.inputs[0].default_value = rgba[3]
        nt.links.new(tr.outputs[0], mix.inputs[1])
        nt.links.new(em.outputs[0], mix.inputs[2])
        nt.links.new(mix.outputs[0], out.inputs["Surface"])
        if hasattr(mat, "surface_render_method"):
            mat.surface_render_method = "BLENDED"
    else:
        nt.links.new(em.outputs[0], out.inputs["Surface"])
    mat.use_backface_culling = cull
    mat.diffuse_color = rgba
    return mat


def build_armature(scene, piv):
    arm_data = bpy.data.armatures.new("HeroArmature")
    arm = bpy.data.objects.new("HeroRig", arm_data)
    scene.collection.objects.link(arm)
    bpy.context.view_layer.objects.active = arm
    bpy.ops.object.mode_set(mode="EDIT")
    spec = [
        ("root", (620, 1215), (620, 1115), None),
        ("hips", piv["hips"], (piv["hips"][0], piv["hips"][1] - 80), "root"),
        ("torso", piv["hips"], piv["neck"], "hips"),
        ("head", piv["neck"], piv["head_top"], "torso"),
        ("back_upper", piv["back_shoulder"], piv["back_elbow"], "torso"),
        ("back_fore", piv["back_elbow"], piv["back_hand"], "back_upper"),
        ("front_upper", piv["front_shoulder"], piv["front_elbow"], "torso"),
        ("front_fore", piv["front_elbow"], piv["front_hand"], "front_upper"),
        ("back_thigh", piv["back_hip"], piv["back_knee"], "hips"),
        ("back_shin", piv["back_knee"], piv["back_ankle"], "back_thigh"),
        ("front_thigh", piv["front_hip"], piv["front_knee"], "hips"),
        ("front_shin", piv["front_knee"], piv["front_ankle"], "front_thigh"),
    ]
    for name, h, t, parent in spec:
        eb = arm_data.edit_bones.new(name)
        eb.head = W(*h)
        eb.tail = W(*t)
        eb.align_roll(Vector((0, -1, 0)))      # local Z toward camera: Z-rot = screen rot
        if parent:
            eb.parent = arm_data.edit_bones[parent]
            eb.use_connect = False
    bpy.ops.object.mode_set(mode="POSE")
    for pb in arm.pose.bones:
        pb.rotation_mode = "XYZ"
    bpy.ops.object.mode_set(mode="OBJECT")
    arm.show_in_front = True
    return arm


def parent_to_bone(obj, arm, bone_name):
    bone = arm.data.bones[bone_name]
    obj.parent = arm
    obj.parent_type = "BONE"
    obj.parent_bone = bone_name
    rest = arm.matrix_world @ bone.matrix_local @ Matrix.Translation((0, bone.length, 0))
    obj.matrix_parent_inverse = rest.inverted()


def part_plane(scene, arm, name, info, parts_dir, z):
    ox, oy = info["offset"]
    w, h = info["size"]
    mesh = bpy.data.meshes.new(name)
    c = W(ox + w / 2, oy + h / 2)
    verts = [W(ox, oy + h) - c, W(ox + w, oy + h) - c, W(ox + w, oy) - c, W(ox, oy) - c]
    mesh.from_pydata([tuple(v) for v in verts], [], [(0, 1, 2, 3)])
    uv = mesh.uv_layers.new(name="UVMap")
    for loop, co in zip(mesh.loops, [(0, 0), (1, 0), (1, 1), (0, 1)]):
        uv.data[loop.index].uv = co
    mesh.materials.append(image_material("M_" + name, parts_dir / info["file"]))
    obj = bpy.data.objects.new("P_" + name, mesh)
    scene.collection.objects.link(obj)
    obj.location = (c.x, -z * DEPTH_STEP, c.z)
    obj["rest_z"] = z
    parent_to_bone(obj, arm, PART_BONE[name])
    return obj


def extrude_poly(bm, pts, y0, y1, mat_index):
    vs = [bm.verts.new((x, y0, z)) for x, z in pts]
    face = bm.faces.new(vs)
    face.material_index = mat_index
    ret = bmesh.ops.extrude_face_region(bm, geom=[face])
    top = [e for e in ret["geom"] if isinstance(e, bmesh.types.BMVert)]
    bmesh.ops.translate(bm, verts=top, vec=(0, y1 - y0, 0))
    for f in bm.faces:
        if f.material_index == 0 and mat_index:
            pass
    return ret


def build_katana(scene, arm, hand_src):
    """Low-poly katana in hand space: grip centre at origin, blade pointing +X."""
    mats = [flat_material("K_steel", (0.62, 0.68, 0.78, 1)),
            flat_material("K_edge", (0.93, 0.96, 1.0, 1)),
            flat_material("K_wrap", (0.10, 0.08, 0.16, 1)),
            flat_material("K_diamond", (0.93, 0.86, 0.66, 1)),
            flat_material("K_gold", (0.96, 0.70, 0.12, 1)),
            flat_material("K_outline", (0.05, 0.04, 0.07, 1), cull=True)]
    bm = bmesh.new()
    t = 0.03
    # blade: spine part and edge part, slight curve (sori) upward toward the tip
    n = 10
    x0, x1 = 0.66, 3.55
    spine, edge, mid = [], [], []
    for i in range(n + 1):
        u = i / n
        x = x0 + (x1 - x0) * u
        lift = 0.18 * u * u
        half = 0.15 * (1 - 0.3 * u)
        spine.append((x, lift + half))
        mid.append((x, lift + half * 0.05))
        edge.append((x, lift - half))
    tip = (x1 + 0.30, 0.18 + 0.15)
    for poly, mi in ((spine + [tip] + mid[::-1], 0), (mid + [tip] + edge[::-1], 1)):
        # ensure counter-clockwise order in XZ for consistent normals
        extrude_poly(bm, poly[::-1] if mi == 0 else poly, -t, t, mi)
    extrude_poly(bm, [(0.60, -0.17), (0.72, -0.17), (0.72, 0.17), (0.60, 0.17)], -t * 1.4, t * 1.4, 4)  # habaki
    extrude_poly(bm, [(0.50, -0.32), (0.60, -0.32), (0.60, 0.33), (0.50, 0.33)], -0.12, 0.12, 4)  # tsuba (edge-on)
    extrude_poly(bm, [(-0.85, -0.11), (0.50, -0.11), (0.50, 0.11), (-0.85, 0.11)], -0.06, 0.06, 2)  # tsuka
    extrude_poly(bm, [(-0.98, -0.12), (-0.85, -0.12), (-0.85, 0.12), (-0.98, 0.12)], -0.065, 0.065, 4)  # kashira
    for i in range(6):                                   # wrap diamonds on the visible side
        cx = -0.72 + i * 0.22
        extrude_poly(bm, [(cx - 0.085, 0), (cx, -0.085), (cx + 0.085, 0), (cx, 0.085)], -0.075, -0.062, 3)
    bmesh.ops.recalc_face_normals(bm, faces=bm.faces)
    mesh = bpy.data.meshes.new("Katana")
    bm.to_mesh(mesh)
    bm.free()
    for m in mats:
        mesh.materials.append(m)
    obj = bpy.data.objects.new("Katana", mesh)
    scene.collection.objects.link(obj)
    sol = obj.modifiers.new("Outline", "SOLIDIFY")
    sol.thickness = 0.07
    sol.offset = 1
    sol.use_flip_normals = True
    sol.use_rim = False
    sol.material_offset = 5
    obj.location = W(*hand_src)
    parent_to_bone(obj, arm, "front_fore")
    obj.rotation_mode = "XYZ"
    return obj


def build_spin_ring(scene, arm, hips_src):
    mat = flat_material("Ring", (0.96, 0.96, 1.0, 0.85))
    bm = bmesh.new()
    r0, r1, seg = 4.05, 4.35, 40
    a0, a1 = math.radians(20), math.radians(320)
    inner, outer = [], []
    for i in range(seg + 1):
        a = a0 + (a1 - a0) * i / seg
        w = 0.35 + 0.65 * math.sin(math.pi * i / seg)     # tapered swoosh
        rr = r0 + (r1 - r0) * w
        inner.append(bm.verts.new((r0 * math.cos(a), 0, r0 * math.sin(a))))
        outer.append(bm.verts.new((rr * math.cos(a), 0, rr * math.sin(a))))
    for i in range(seg):
        bm.faces.new((inner[i], inner[i + 1], outer[i + 1], outer[i]))
    mesh = bpy.data.meshes.new("SpinRing")
    bm.to_mesh(mesh)
    bm.free()
    mesh.materials.append(mat)
    obj = bpy.data.objects.new("P_spin_ring", mesh)
    scene.collection.objects.link(obj)
    obj.location = W(hips_src[0], hips_src[1], DEPTH_STEP)   # keyed per spin frame
    obj.rotation_mode = "XYZ"
    return obj


# --------------------------------------------------------------------------------------
def posed_point(arm, bone_name, p_world_rest):
    pb = arm.pose.bones[bone_name]
    m = pb.matrix @ arm.data.bones[bone_name].matrix_local.inverted()
    return m @ p_world_rest


BALL_POINTS = (("torso", "neck"), ("hips", "hips"), ("back_thigh", "back_knee"),
               ("front_thigh", "front_knee"), ("back_shin", "back_ankle"),
               ("front_shin", "front_ankle"), ("head", "head_top"))


def ball_centre(arm, piv):
    pts = [posed_point(arm, bone, W(*piv[p])) for bone, p in BALL_POINTS]
    # head top counts half: it sits on the ball surface
    c = sum(pts[:-1], Vector()) / (len(pts) - 1)
    return (c * 4 + pts[-1]) / 5


def chain_angle(pose, keys):
    return sum(pose.get(k, 0) for k in keys)


def apply_pose(scene, arm, objs, katana, b, pose, meta):
    for pb in arm.pose.bones:
        pb.location = (0, 0, 0)
        pb.rotation_euler = (0, 0, 0)
        pb.scale = (1, 1, 1)
    for key, bone in ANGLE_BONE.items():
        arm.pose.bones[bone].rotation_euler = (0, 0, math.radians(pose.get(key, 0)))
    if "breathe" in pose:
        arm.pose.bones["torso"].scale = (1, pose["breathe"], 1)
    bpy.context.view_layer.update()

    kind, value = pose["place"]
    piv = meta["pivots"]
    if kind == "ground":
        pts = []
        for part, plist in meta["sole_points"].items():
            pts += [posed_point(arm, part, W(*p)) for p in plist]
        dz = -min(p.z for p in pts) + out_px_to_bu(value)
    elif kind == "neck":
        rest = W(*piv["neck"])
        cur = posed_point(arm, "torso", rest)       # neck = head bone head, moves with torso
        dz = (rest.z + out_px_to_bu(value)) - cur.z
    elif kind == "fixed":  # in-between frames: root placement interpolated from the two key poses
        dz = value[1]
    else:  # "ball": centroid of the tucked body at a height above ground
        c = ball_centre(arm, piv)
        dz = out_px_to_bu(value) - c.z
    dx = 0.0
    if kind == "ball":
        dx = -ball_centre(arm, piv).x
    elif kind == "fixed":
        dx = value[0]
    arm.pose.bones["root"].location = (dx, dz, 0)   # root local X/Y = world X/Z
    bpy.context.view_layer.update()

    # visibility + per-pose depth
    only = pose.get("only")
    show = set(pose.get("show", []))
    hide = set(pose.get("hide", []))
    for name, obj in objs.items():
        if only is not None:
            vis = name in only
        elif name in BODY:
            vis = name not in hide
        else:
            vis = name in show
        obj.hide_render = not vis
        obj.hide_viewport = not vis
        obj.keyframe_insert("hide_render", frame=b)
        obj.keyframe_insert("hide_viewport", frame=b)
        if "rest_z" in obj:
            obj.location.y = -pose.get("z", {}).get(name, obj["rest_z"]) * DEPTH_STEP
            sc = pose.get("scale", {}).get(name, 1.0)
            obj.scale = (sc, sc, sc)
            obj.keyframe_insert("location", frame=b)
            obj.keyframe_insert("scale", frame=b)
    kvis = "kd" in pose and only is None
    katana.hide_render = not kvis
    katana.hide_viewport = not kvis
    if kvis:
        chain = chain_angle(pose, ("hips", "torso", "fu", "ff"))
        katana.rotation_euler = (0, -math.radians(pose["kd"] - chain), 0)
        katana.location.y = -pose.get("kz", 8.5) * DEPTH_STEP + W(0, 0).y
    ring = objs["spin_ring"]
    if not ring.hide_render:
        c = ball_centre(arm, piv)
        ring.location = (c.x, DEPTH_STEP, c.z)
        ring.rotation_euler = (0, math.radians(-pose.get("hips", 0)), 0)
    ring.keyframe_insert("location", frame=b)
    ring.keyframe_insert("rotation_euler", frame=b)
    katana.keyframe_insert("hide_render", frame=b)
    katana.keyframe_insert("hide_viewport", frame=b)
    katana.keyframe_insert("rotation_euler", frame=b)
    katana.keyframe_insert("location", frame=b)
    for pb in arm.pose.bones:
        pb.keyframe_insert("location", frame=b)
        pb.keyframe_insert("rotation_euler", frame=b)
        pb.keyframe_insert("scale", frame=b)
    apply_pose.last_dx = dx
    return dz


NUMERIC = set(ANGLE_BONE) | {"kd", "kz", "breathe"}


def tween(p1, p2, t):
    """Linear blend of two key poses (angles, katana direction); discrete things switch at t=0.5."""
    near = p1 if t < 0.5 else p2
    out = {k: v for k, v in near.items() if k not in NUMERIC and k != "place"}
    for k in NUMERIC:
        if k in p1 or k in p2:
            d = 1.0 if k == "breathe" else 0.0
            if k in ("kd", "kz") and (k not in p1 or k not in p2):
                if k in near:
                    out[k] = near[k]
                continue
            out[k] = p1.get(k, d) * (1 - t) + p2.get(k, d) * t
    return out


def constant_interpolation():
    for action in bpy.data.actions:
        fcurves = getattr(action, "fcurves", None)
        if fcurves is None:
            # Blender 5 layered actions
            fcurves = []
            for layer in getattr(action, "layers", []):
                for strip in layer.strips:
                    for bag in getattr(strip, "channelbags", []):
                        fcurves.extend(bag.fcurves)
        for fc in fcurves:
            for kp in fc.keyframe_points:
                kp.interpolation = "CONSTANT"


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--project-root", required=True, type=Path)
    ap.add_argument("--frames", default="")
    ap.add_argument("--no-render", action="store_true")
    ap.add_argument("--tweens", default="", help="comma list of b1-b2 pairs: render in-between frames at t=1/3,2/3")
    args = ap.parse_args(sys.argv[sys.argv.index("--") + 1:])
    root = args.project_root.resolve()
    parts_dir = root / "art/hero/parts"
    meta = json.loads((parts_dir / "parts.json").read_text())
    t0 = time.time()

    scene = reset_scene()
    engine = setup_render(scene)
    setup_camera(scene)
    arm = build_armature(scene, {k: tuple(v) for k, v in meta["pivots"].items()})
    objs = {}
    z = dict(meta["z"])
    z.update(EXTRA_Z)
    for name, info in meta["parts"].items():
        objs[name] = part_plane(scene, arm, name, info, parts_dir, z[name])
    objs["spin_ring"] = build_spin_ring(scene, arm, meta["pivots"]["hips"])
    katana = build_katana(scene, arm, meta["pivots"]["front_hand"])

    scene.frame_start, scene.frame_end = 0, 45
    report = {"engine": engine, "render_scale": RENDER_SCALE, "margin": MARGIN, "frames": {}}
    roots = {}
    for b in range(46):
        dz = apply_pose(scene, arm, objs, katana, b, POSES[b], meta)
        roots[b] = (apply_pose.last_dx, dz)
        report["frames"][b] = {"place": list(POSES[b]["place"]), "root_dz_bu": round(dz, 4)}
    tweens = []
    for pr in [x for x in args.tweens.split(",") if x.strip()]:
        b1, b2 = [int(v) for v in pr.split("-")]
        for k, t in ((1, 1.0 / 3.0), (2, 2.0 / 3.0)):
            f = 100 + len(tweens)
            r1, r2 = roots[b1], roots[b2]
            pose = tween(POSES[b1], POSES[b2], t)
            pose["place"] = ("fixed", (r1[0] * (1 - t) + r2[0] * t, r1[1] * (1 - t) + r2[1] * t))
            apply_pose(scene, arm, objs, katana, f, pose, meta)
            tweens.append({"frame": f, "b1": b1, "b2": b2, "k": k, "t": t})
    report["tweens"] = tweens
    scene.frame_end = 100 + len(tweens) if tweens else 45
    constant_interpolation()
    if arm.animation_data and arm.animation_data.action:
        arm.animation_data.action.name = "HeroPoses_B00_B45"
    build_s = time.time() - t0

    blend = root / "art/blender/hero_rig.blend"
    blend.parent.mkdir(parents=True, exist_ok=True)
    bpy.ops.file.pack_all()
    bpy.ops.wm.save_as_mainfile(filepath=str(blend), compress=True)

    raw = root / "art/hero/build/raw"
    raw.mkdir(parents=True, exist_ok=True)
    frames = [int(f) for f in args.frames.split(",") if f.strip()] or list(range(46))
    t1 = time.time()
    if not args.no_render:
        for b in frames:
            scene.frame_set(b)
            scene.render.filepath = str(raw / f"b{b:02d}.png")
            bpy.ops.render.render(write_still=True)
            report["frames"][b]["rendered"] = True
        for tw in tweens:
            scene.frame_set(tw["frame"])
            scene.render.filepath = str(raw / ("t_%d_%d_%d.png" % (tw["b1"], tw["b2"], tw["k"])))
            bpy.ops.render.render(write_still=True)
    report.update(build_seconds=round(build_s, 2), render_seconds=round(time.time() - t1, 2),
                  rendered=[] if args.no_render else frames, blend=str(blend.relative_to(root)))
    (root / "art/hero/build/render-report.json").write_text(json.dumps(report, indent=2))
    print("HERO_RIG_DONE", json.dumps({k: report[k] for k in ("engine", "build_seconds", "render_seconds")}))


if __name__ == "__main__":
    main()
