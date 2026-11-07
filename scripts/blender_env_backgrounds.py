"""Procedural HD parallax backgrounds for the Ninja School 1 PC remake.

Run (one biome, both lightings):
  /Applications/Blender.app/Contents/MacOS/Blender --background --factory-startup \
      --python scripts/blender_env_backgrounds.py -- --biome school --lighting day night

Options after "--":
  --biome NAME|all        biome(s) to build (see BIOMES)
  --lighting day night    lightings to build
  --out art/env           output root (art/env/<biome>/<lighting>/layer0..3.png + meta.json)
  --samples 24            EEVEE TAA samples
  --scale 1.0             resolution scale (0.25 for quick look-dev renders)
  --blend PATH            also save the built scenes into a .blend (generator text embedded)
  --no-render             build scenes only (use with --blend)

How it works
  * Orthographic camera pitched 12 deg down. World X maps 1:1 to screen X; 1 unit = 100 px at
    full res, so the frame is exactly W = 20.48 units wide.
  * Everything is periodic in X with period W: ridge/plane meshes are evaluated from periodic
    functions (integer frequencies only) and every scattered object that crosses x=0 or x=W gets
    a linked duplicate at -/+W.  Shader noise is sampled on a cylinder (cos/sin of 2*pi*x/W) so it
    is periodic too.  Result: every layer tiles horizontally; meta.json records an edge check.
  * Materials are flat/cel "anime backdrop" shaders: Diffuse -> Shader-to-RGB -> soft 2-tone ramp
    between a lit and a shade colour, periodic paint noise, per-object value jitter, bottom-of-
    object darkening, and screen-height mist + per-layer haze mixed toward the fog colour.
  * Night is derived from the day palette (moonlit blue transform) plus warm emissive lights.
  * 4 layers are rendered separately by toggling collections: L0 sky (opaque, 1024x576),
    L1 far ridges, L2 mid scenery, L3 near silhouettes (transparent film, 2048x1152).
"""
import bpy
import bmesh
import math
import random
import json
import sys
import os
import time
import colorsys
import numpy as np
from mathutils import Vector, Matrix, Euler

# ----------------------------------------------------------------------------- constants
W = 20.48            # tile width in world units (2048 px)
H = 11.52            # frame height (1152 px)
PITCH = math.radians(12.0)
SP, CP = math.sin(PITCH), math.cos(PITCH)
TAU = 2.0 * math.pi
LAYERS = ("L0", "L1", "L2", "L3")
PARALLAX = {"L0": 0.05, "L1": 0.15, "L2": 0.35, "L3": 0.6}


def z_at(V, y):
    """World z so that a point at depth y appears at screen height V (0 = bottom, H = top)."""
    return (V - H / 2.0 - y * SP) / CP


# ----------------------------------------------------------------------------- colour utils
def hexc(h):
    h = h.lstrip("#")
    return tuple(int(h[i:i + 2], 16) / 255.0 for i in (0, 2, 4))


def to_hex(c):
    return "#%02x%02x%02x" % tuple(max(0, min(255, int(round(v * 255)))) for v in c[:3])


def s2l(c):
    return c / 12.92 if c <= 0.04045 else ((c + 0.055) / 1.055) ** 2.4


def lin(c, a=1.0):
    return (s2l(c[0]), s2l(c[1]), s2l(c[2]), a)


def mixc(a, b, t):
    return tuple(a[i] * (1 - t) + b[i] * t for i in range(3))


def shade_of(c, k=0.6, hue_to=0.68, hue_amt=0.10):
    h, s, v = colorsys.rgb_to_hsv(*c)
    dh = ((hue_to - h + 0.5) % 1.0) - 0.5
    return colorsys.hsv_to_rgb((h + dh * hue_amt) % 1.0, min(1, s * 1.08 + 0.04), v * k)


def as_rgb(c):
    return hexc(c) if isinstance(c, str) else tuple(c)


# ----------------------------------------------------------------------------- context
class Ctx:
    def __init__(self, biome, lighting, scene, seed):
        self.biome = biome
        self.lighting = lighting
        self.night = lighting == "night"
        self.scene = scene
        self.rng = random.Random(seed)
        self.seed = seed
        self.mats = {}
        self.meshes = {}
        self.colls = {}
        self.prefix = scene.name
        # night look (per biome overridable)
        self.night_lit = (0.58, 0.70, 1.00)
        self.night_shade = (0.30, 0.36, 0.70)
        self.night_k_lit = 0.50
        self.night_k_shade = 0.40
        self.night_desat = 0.45
        # fog per layer: colour, haze, mist band (v0 -> v1 fades fmax -> 0)
        self.fog = {}
        self.meta = {}

    def night_col(self, c, lit=True):
        lum = 0.3 * c[0] + 0.59 * c[1] + 0.11 * c[2]
        base = mixc(c, (lum, lum, lum), self.night_desat)
        tint = self.night_lit if lit else self.night_shade
        k = self.night_k_lit if lit else self.night_k_shade
        return tuple(min(1.0, base[i] * tint[i] * k * 1.6) for i in range(3))

    def col(self, c, lit=True):
        c = as_rgb(c)
        return self.night_col(c, lit) if self.night else c


# ----------------------------------------------------------------------------- node helpers
def _link(nt, src, dst):
    if isinstance(src, bpy.types.NodeSocket):
        nt.links.new(src, dst)
    else:
        dst.default_value = src


def mnode(nt, op, a, b=None, c=None, clamp=False):
    n = nt.nodes.new("ShaderNodeMath")
    n.operation = op
    n.use_clamp = clamp
    _link(nt, a, n.inputs[0])
    if b is not None:
        _link(nt, b, n.inputs[1])
    if c is not None:
        _link(nt, c, n.inputs[2])
    return n.outputs[0]


def mix_col(nt, fac, a, b, blend="MIX"):
    n = nt.nodes.new("ShaderNodeMix")
    n.data_type = "RGBA"
    n.blend_type = blend
    _link(nt, fac, n.inputs["Factor_Float"] if "Factor_Float" in n.inputs else n.inputs[0])
    _link(nt, a, [i for i in n.inputs if i.identifier == "A_Color"][0])
    _link(nt, b, [i for i in n.inputs if i.identifier == "B_Color"][0])
    return [o for o in n.outputs if o.identifier == "Result_Color"][0]


def maprange(nt, v, a0, a1, b0, b1, clamp=True, interp="LINEAR"):
    n = nt.nodes.new("ShaderNodeMapRange")
    n.clamp = clamp
    n.interpolation_type = interp
    _link(nt, v, n.inputs["Value"])
    n.inputs["From Min"].default_value = a0
    n.inputs["From Max"].default_value = a1
    n.inputs["To Min"].default_value = b0
    n.inputs["To Max"].default_value = b1
    return n.outputs["Result"]


def ramp(nt, fac, stops, interp="LINEAR"):
    """stops: list of (pos, (r,g,b,a)) linear colours."""
    n = nt.nodes.new("ShaderNodeValToRGB")
    cr = n.color_ramp
    cr.interpolation = interp
    while len(cr.elements) > 1:
        cr.elements.remove(cr.elements[-1])
    cr.elements[0].position = stops[0][0]
    cr.elements[0].color = stops[0][1]
    for p, c in stops[1:]:
        e = cr.elements.new(p)
        e.color = c
    _link(nt, fac, n.inputs["Fac"])
    return n.outputs["Color"], n.outputs["Alpha"]


def geo_xyz(nt):
    g = nt.nodes.new("ShaderNodeNewGeometry")
    s = nt.nodes.new("ShaderNodeSeparateXYZ")
    nt.links.new(g.outputs["Position"], s.inputs[0])
    return s.outputs[0], s.outputs[1], s.outputs[2]


def screen_v(nt, Y, Z):
    return mnode(nt, "ADD", mnode(nt, "MULTIPLY_ADD", Y, SP, H / 2.0), mnode(nt, "MULTIPLY", Z, CP))


def periodic_noise(nt, X, Y, Z, scale=1.0, detail=3.0, rough=0.55, wmul=0.31, zmul=1.0, xstretch=1.0):
    """4D noise sampled on a cylinder -> exactly periodic in X with period W."""
    th = mnode(nt, "MULTIPLY", X, TAU / W)
    R = W / TAU / xstretch
    cx = mnode(nt, "MULTIPLY", mnode(nt, "COSINE", th), R)
    cy = mnode(nt, "MULTIPLY", mnode(nt, "SINE", th), R)
    cz = mnode(nt, "MULTIPLY", Z, zmul)
    comb = nt.nodes.new("ShaderNodeCombineXYZ")
    nt.links.new(cx, comb.inputs[0])
    nt.links.new(cy, comb.inputs[1])
    nt.links.new(cz, comb.inputs[2])
    nz = nt.nodes.new("ShaderNodeTexNoise")
    nz.noise_dimensions = "4D"
    nt.links.new(comb.outputs[0], nz.inputs["Vector"])
    _link(nt, mnode(nt, "MULTIPLY", Y, wmul), nz.inputs["W"])
    nz.inputs["Scale"].default_value = scale
    nz.inputs["Detail"].default_value = detail
    nz.inputs["Roughness"].default_value = rough
    return nz.outputs["Fac"]


def finish(nt, color, alpha=1.0, strength=1.0):
    em = nt.nodes.new("ShaderNodeEmission")
    _link(nt, color, em.inputs["Color"])
    em.inputs["Strength"].default_value = strength
    out = nt.nodes.new("ShaderNodeOutputMaterial")
    if isinstance(alpha, (int, float)) and alpha >= 0.999:
        nt.links.new(em.outputs[0], out.inputs["Surface"])
        return
    tr = nt.nodes.new("ShaderNodeBsdfTransparent")
    mx = nt.nodes.new("ShaderNodeMixShader")
    _link(nt, alpha, mx.inputs[0])
    nt.links.new(tr.outputs[0], mx.inputs[1])
    nt.links.new(em.outputs[0], mx.inputs[2])
    nt.links.new(mx.outputs[0], out.inputs["Surface"])


def new_mat(ctx, name, blended=False):
    m = bpy.data.materials.new(ctx.prefix + "_" + name)
    nt = m.node_tree
    nt.nodes.clear()
    if blended:
        m.surface_render_method = "BLENDED"
    return m, nt


def apply_fog(ctx, nt, color, layer, Y, Z, haze=None, fogmul=1.0):
    f = ctx.fog[layer]
    hz = f["haze"] if haze is None else haze
    V = screen_v(nt, Y, Z)
    band = maprange(nt, V, f["v0"], f["v1"], f["fmax"] * fogmul, 0.0, interp="SMOOTHSTEP")
    tot = mnode(nt, "MULTIPLY_ADD", band, 1.0 - hz, hz, clamp=True)
    return mix_col(nt, tot, color, lin(f["color"]))


# ----------------------------------------------------------------------------- materials
def toon(ctx, name, lit, shade=None, layer="L2", haze=None, noise=0.10, nscale=1.4, grad=0.25,
         thr=0.45, soft=0.07, fogmul=1.0, flat=False, alpha=1.0, glow=0.0):
    key = (name, layer, haze, fogmul)
    if key in ctx.mats:
        return ctx.mats[key]
    lit_c = as_rgb(lit)
    shade_c = as_rgb(shade) if shade is not None else shade_of(lit_c)
    lit_c, shade_c = ctx.col(lit_c, True), ctx.col(shade_c, False)
    m, nt = new_mat(ctx, "%s_%s" % (name, layer), blended=(alpha < 1.0))
    X, Y, Z = geo_xyz(nt)
    if flat:
        tc = nt.nodes.new("ShaderNodeTexCoord")
        sz = nt.nodes.new("ShaderNodeSeparateXYZ")
        nt.links.new(tc.outputs["Generated"], sz.inputs[0])
        fac = maprange(nt, sz.outputs[1], 0.0, 1.0, 0.0, 1.0)
    else:
        d = nt.nodes.new("ShaderNodeBsdfDiffuse")
        d.inputs["Color"].default_value = (1, 1, 1, 1)
        s2r = nt.nodes.new("ShaderNodeShaderToRGB")
        nt.links.new(d.outputs[0], s2r.inputs[0])
        bw = nt.nodes.new("ShaderNodeRGBToBW")
        nt.links.new(s2r.outputs["Color"], bw.inputs[0])
        fac = maprange(nt, bw.outputs[0], thr - soft, thr + soft, 0.0, 1.0, interp="SMOOTHSTEP")
    base = mix_col(nt, fac, lin(shade_c), lin(lit_c))
    # value modulation: paint noise, object jitter (Object Info colour .r), bottom darkening
    n = periodic_noise(nt, X, Y, Z, scale=nscale, detail=4.0)
    val = mnode(nt, "MULTIPLY_ADD", n, noise * 2.0, 1.0 - noise)
    oi = nt.nodes.new("ShaderNodeObjectInfo")
    so = nt.nodes.new("ShaderNodeSeparateColor")
    nt.links.new(oi.outputs["Color"], so.inputs[0])
    val = mnode(nt, "MULTIPLY", val, so.outputs[0])
    if grad > 0:
        tc = nt.nodes.new("ShaderNodeTexCoord")
        sg = nt.nodes.new("ShaderNodeSeparateXYZ")
        nt.links.new(tc.outputs["Generated"], sg.inputs[0])
        gz = maprange(nt, sg.outputs[2], 0.0, 0.75, 1.0 - grad, 1.0, interp="SMOOTHSTEP")
        val = mnode(nt, "MULTIPLY", val, gz)
    hsv = nt.nodes.new("ShaderNodeHueSaturation")
    _link(nt, val, hsv.inputs["Value"])
    _link(nt, base, hsv.inputs["Color"])
    col = apply_fog(ctx, nt, hsv.outputs[0], layer, Y, Z, haze, fogmul)
    finish(nt, col, alpha, 1.0 + glow)
    ctx.mats[key] = m
    return m


def glow_mat(ctx, name, color, strength=1.6, layer="L2", alpha=1.0):
    key = ("glow", name, layer)
    if key in ctx.mats:
        return ctx.mats[key]
    m, nt = new_mat(ctx, "glow_%s_%s" % (name, layer), blended=alpha < 1)
    finish(nt, lin(as_rgb(color)), alpha, strength)
    ctx.mats[key] = m
    return m


def radial_mat(ctx, name, color, inner=0.0, outer=1.0, peak=1.0, strength=1.0, power_stops=None):
    """Card material: radial alpha falloff (halo / disc / glow)."""
    key = ("radial", name)
    if key in ctx.mats:
        return ctx.mats[key]
    m, nt = new_mat(ctx, "rad_" + name, blended=True)
    tc = nt.nodes.new("ShaderNodeTexCoord")
    vm = nt.nodes.new("ShaderNodeVectorMath")
    vm.operation = "SUBTRACT"
    nt.links.new(tc.outputs["Generated"], vm.inputs[0])
    vm.inputs[1].default_value = (0.5, 0.5, 0.5)
    ln = nt.nodes.new("ShaderNodeVectorMath")
    ln.operation = "LENGTH"
    nt.links.new(vm.outputs[0], ln.inputs[0])
    d = mnode(nt, "MULTIPLY", ln.outputs["Value"], 2.0)
    stops = power_stops or [(inner, peak), (outer, 0.0)]
    a = None
    _, a = ramp(nt, d, [(p, (1, 1, 1, v)) for p, v in stops], "EASE")
    finish(nt, lin(as_rgb(color)), a, strength)
    ctx.mats[key] = m
    return m


def fog_card_mat(ctx, name, layer, color=None, strength=0.75, top=1.0, nscale=0.9, wisp=0.55):
    """Translucent mist strip: dense at bottom, fades upward, wispy periodic noise."""
    key = ("fogcard", name, layer)
    if key in ctx.mats:
        return ctx.mats[key]
    m, nt = new_mat(ctx, "fog_%s_%s" % (name, layer), blended=True)
    X, Y, Z = geo_xyz(nt)
    tc = nt.nodes.new("ShaderNodeTexCoord")
    sg = nt.nodes.new("ShaderNodeSeparateXYZ")
    nt.links.new(tc.outputs["Generated"], sg.inputs[0])
    v = sg.outputs[1]
    prof = maprange(nt, v, 0.0, top, 1.0, 0.0, interp="SMOOTHSTEP")
    bot = maprange(nt, v, 0.0, 0.12, 0.0, 1.0, interp="SMOOTHSTEP")
    n = periodic_noise(nt, X, Y, Z, scale=nscale, detail=3.0, xstretch=1.0, zmul=2.2)
    nn = maprange(nt, n, 0.35, 0.65, 1.0 - wisp, 1.0)
    a = mnode(nt, "MULTIPLY", mnode(nt, "MULTIPLY", prof, nn), mnode(nt, "MULTIPLY", bot, strength))
    c = as_rgb(color) if color is not None else ctx.fog[layer]["color"]
    finish(nt, lin(c), a)
    ctx.mats[key] = m
    return m


def sky_mat(ctx, stops):
    """stops: [(V, hex)] screen-height gradient."""
    m, nt = new_mat(ctx, "sky")
    X, Y, Z = geo_xyz(nt)
    V = screen_v(nt, Y, Z)
    t = maprange(nt, V, 0.0, H, 0.0, 1.0)
    col, _ = ramp(nt, t, [(p / H, lin(as_rgb(c))) for p, c in stops], "EASE")
    n = periodic_noise(nt, X, Y, Z, scale=0.5, detail=2.0)
    col = mix_col(nt, maprange(nt, n, 0.3, 0.7, 0.0, 0.012 if ctx.night else 0.05), col, (1, 1, 1, 1), "SCREEN")
    finish(nt, col)
    return m


def water_mat(ctx, name, layer, deep, light, hi, v_far, v_near, streak=0.5, yfreq=9.0):
    key = ("water", name, layer)
    if key in ctx.mats:
        return ctx.mats[key]
    deep, light, hi = ctx.col(deep, False), ctx.col(light, True), (ctx.col(hi, True) if not ctx.night else mixc(ctx.col(hi, True), (0.9, 0.92, 1.0), 0.4))
    m, nt = new_mat(ctx, "water_%s_%s" % (name, layer))
    X, Y, Z = geo_xyz(nt)
    V = screen_v(nt, Y, Z)
    t = maprange(nt, V, v_near, v_far, 0.0, 1.0, interp="SMOOTHSTEP")
    base = mix_col(nt, t, lin(deep), lin(light))
    n1 = periodic_noise(nt, X, Y, Z, scale=1.3, detail=2.0, wmul=yfreq, zmul=0.0)
    n2 = periodic_noise(nt, X, mnode(nt, "ADD", Y, 37.0), Z, scale=0.6, detail=1.0, wmul=0.6)
    s = mnode(nt, "MULTIPLY", maprange(nt, n1, 0.60, 0.66, 0.0, 1.0), maprange(nt, n2, 0.45, 0.6, 0.0, streak))
    col = mix_col(nt, s, base, lin(hi))
    col = apply_fog(ctx, nt, col, layer, Y, Z)
    finish(nt, col)
    ctx.mats[key] = m
    return m


def fall_mat(ctx, name, layer, c1, c2, alpha=0.92):
    """Waterfall card: vertical streaks (object generated coords), soft side edges."""
    key = ("fall", name, layer)
    if key in ctx.mats:
        return ctx.mats[key]
    c1, c2 = ctx.col(c1, True), ctx.col(c2, False)
    m, nt = new_mat(ctx, "fall_%s_%s" % (name, layer), blended=True)
    tc = nt.nodes.new("ShaderNodeTexCoord")
    sg = nt.nodes.new("ShaderNodeSeparateXYZ")
    nt.links.new(tc.outputs["Generated"], sg.inputs[0])
    u, v = sg.outputs[0], sg.outputs[1]
    comb = nt.nodes.new("ShaderNodeCombineXYZ")
    _link(nt, mnode(nt, "MULTIPLY", u, 14.0), comb.inputs[0])
    _link(nt, mnode(nt, "MULTIPLY", v, 0.8), comb.inputs[1])
    nz = nt.nodes.new("ShaderNodeTexNoise")
    nt.links.new(comb.outputs[0], nz.inputs["Vector"])
    nz.inputs["Scale"].default_value = 2.0
    nz.inputs["Detail"].default_value = 3.0
    s = maprange(nt, nz.outputs["Fac"], 0.42, 0.62, 0.0, 1.0)
    col = mix_col(nt, s, lin(c2), lin(c1))
    edge = mnode(nt, "MULTIPLY", maprange(nt, u, 0.0, 0.18, 0.0, 1.0, interp="SMOOTHSTEP"),
                 maprange(nt, u, 0.82, 1.0, 1.0, 0.0, interp="SMOOTHSTEP"))
    a = mnode(nt, "MULTIPLY", edge, mnode(nt, "MULTIPLY_ADD", s, 0.25, alpha - 0.2))
    X, Y, Z = geo_xyz(nt)
    col = apply_fog(ctx, nt, col, layer, Y, Z, fogmul=0.6)
    finish(nt, col, a)
    ctx.mats[key] = m
    return m


def field_mat(ctx, name, layer, c1, c2, dike, ncols=34, yfreq=2.2):
    """Rice paddies on a ground plane: periodic plots separated by dikes."""
    key = ("field", name, layer)
    if key in ctx.mats:
        return ctx.mats[key]
    c1, c2, dike = ctx.col(c1, True), ctx.col(c2, True), ctx.col(dike, False)
    m, nt = new_mat(ctx, "field_%s_%s" % (name, layer))
    X, Y, Z = geo_xyz(nt)
    th = mnode(nt, "MULTIPLY", X, TAU / W * ncols)
    wob = periodic_noise(nt, X, Y, Z, scale=0.5, detail=1.0, wmul=0.4)
    u = mnode(nt, "ADD", th, mnode(nt, "MULTIPLY", wob, 3.0))
    sx = mnode(nt, "ABSOLUTE", mnode(nt, "SINE", u))
    sy = mnode(nt, "ABSOLUTE", mnode(nt, "SINE", mnode(nt, "MULTIPLY", Y, yfreq)))
    dk = mnode(nt, "MAXIMUM", maprange(nt, sx, 0.10, 0.03, 0.0, 1.0), maprange(nt, sy, 0.10, 0.03, 0.0, 1.0))
    # plot cells follow the (warped) dike lines; index -> world units keeps the noise periodic in X
    cellx = mnode(nt, "MULTIPLY", mnode(nt, "SNAP", mnode(nt, "DIVIDE", u, math.pi), 1.0), W / (2 * ncols))
    n = periodic_noise(nt, cellx, mnode(nt, "SNAP", mnode(nt, "MULTIPLY", Y, yfreq / math.pi), 1.0), Z, scale=3.0, detail=0.0)
    plot = mix_col(nt, maprange(nt, n, 0.35, 0.65, 0.0, 1.0), lin(c1), lin(c2))
    n2 = periodic_noise(nt, X, Y, Z, scale=6.0, detail=2.0)
    plot = mix_col(nt, maprange(nt, n2, 0.3, 0.7, 0.0, 0.18), plot, (0, 0, 0, 1), "MULTIPLY")
    col = mix_col(nt, dk, plot, lin(dike))
    col = apply_fog(ctx, nt, col, layer, Y, Z)
    finish(nt, col)
    ctx.mats[key] = m
    return m


# ----------------------------------------------------------------------------- scene setup
def new_scene(name, res_scale, samples):
    sc = bpy.data.scenes.new(name)
    sc.render.engine = "BLENDER_EEVEE"
    sc.view_settings.view_transform = "Standard"
    sc.view_settings.look = "None"
    sc.render.resolution_x = int(2048 * res_scale)
    sc.render.resolution_y = int(1152 * res_scale)
    sc.render.resolution_percentage = 100
    sc.render.image_settings.file_format = "PNG"
    sc.render.image_settings.color_mode = "RGBA"
    sc.render.image_settings.color_depth = "8"
    sc.render.image_settings.compression = 100
    sc.eevee.taa_render_samples = samples
    sc.render.film_transparent = True
    sc.render.filter_size = 1.2
    w = bpy.data.worlds.new(name + "_world")
    w.color = (0, 0, 0)
    bg = w.node_tree.nodes.get("Background")
    if bg:
        bg.inputs["Color"].default_value = (0, 0, 0, 1)
        bg.inputs["Strength"].default_value = 0.0
    sc.world = w
    cam_d = bpy.data.cameras.new(name + "_cam")
    cam_d.type = "ORTHO"
    cam_d.ortho_scale = W
    cam_d.clip_start = 0.1
    cam_d.clip_end = 20000.0
    cam_d.sensor_fit = "HORIZONTAL"
    cam = bpy.data.objects.new(name + "_cam", cam_d)
    sc.collection.objects.link(cam)
    fwd = Vector((0.0, CP, -SP))
    cam.location = Vector((W / 2.0, 0.0, 0.0)) - fwd * 600.0
    cam.rotation_euler = Euler((math.pi / 2 - PITCH, 0.0, 0.0))
    sc.camera = cam
    return sc


def add_sun(ctx, d):
    ld = bpy.data.lights.new(ctx.prefix + "_sun", "SUN")
    ld.energy = math.pi
    ld.use_shadow = False
    ld.angle = 0.0
    ob = bpy.data.objects.new(ctx.prefix + "_sun", ld)
    ctx.scene.collection.objects.link(ob)
    v = Vector(d).normalized()
    ob.rotation_euler = v.to_track_quat("-Z", "Y").to_euler()


def coll(ctx, layer):
    if layer not in ctx.colls:
        c = bpy.data.collections.new("%s_%s" % (ctx.prefix, layer))
        ctx.scene.collection.children.link(c)
        ctx.colls[layer] = c
    return ctx.colls[layer]


def add_obj(ctx, layer, name, mesh, loc=(0, 0, 0), rot=(0, 0, 0), scale=(1, 1, 1), wrap=True, var=1.0):
    ob = bpy.data.objects.new("%s_%s" % (ctx.prefix, name), mesh)
    ob.location = loc
    ob.rotation_euler = rot
    ob.scale = scale if isinstance(scale, (tuple, list)) else (scale, scale, scale)
    ob.color = (var, 1.0, 1.0, 1.0)
    ob["wrap"] = bool(wrap)
    coll(ctx, layer).objects.link(ob)
    return ob


def wrap_duplicates(ctx):
    """Linked duplicates at +/-W for every object crossing the tile edges -> seamless tiling."""
    n = 0
    for layer, c in list(ctx.colls.items()):
        for ob in list(c.objects):
            if not ob.get("wrap") or ob.type != "MESH":
                continue
            ob.location.x = ob.location.x % W
            mw = ob.matrix_world.copy()
            mw = Matrix.Translation(ob.location) @ ob.rotation_euler.to_matrix().to_4x4() @ Matrix.Diagonal(ob.scale.to_4d())
            xs = [(mw @ Vector(b)).x for b in ob.bound_box]
            x0, x1 = min(xs), max(xs)
            for sh in (-W, W):
                if (sh > 0 and x0 < 0.0) or (sh < 0 and x1 > W):
                    cp = ob.copy()
                    cp.location.x += sh
                    cp["wrap"] = False
                    c.objects.link(cp)
                    n += 1
    return n


# ----------------------------------------------------------------------------- periodic fns
class PFn:
    """Random periodic 1D function on [0,W) built from integer-frequency sines."""

    def __init__(self, seed, kmin=1, kmax=8, n=7, falloff=1.1):
        r = np.random.default_rng(seed)
        self.k = r.integers(kmin, kmax + 1, size=n)
        self.ph = r.uniform(0, TAU, size=n)
        self.a = r.uniform(0.5, 1.0, size=n) / (self.k.astype(float) ** falloff)
        x = np.linspace(0, W, 2048, endpoint=False)
        raw = self._raw(x)
        self.lo, self.hi = raw.min(), raw.max()

    def _raw(self, x):
        x = np.asarray(x, dtype=float)
        return sum(a * np.sin(TAU * k * x / W + p) for a, k, p in zip(self.a, self.k, self.ph))

    def __call__(self, x):
        return (self._raw(x) - self.lo) / (self.hi - self.lo + 1e-9)


def pdist(x, cx):
    return np.abs(((np.asarray(x) - cx + W / 2) % W) - W / 2)


def crest(seed, base, amp, kmin=1, kmax=7, sharp=1.4, ridged=0.0, n=7):
    f = PFn(seed, kmin, kmax, n)
    g = PFn(seed + 999, kmin + 1, kmax + 4, n) if ridged > 0 else None

    def fn(x):
        s = np.clip(f(x), 0, 1) ** sharp
        if g is not None:
            s = s * (1 - ridged) + ridged * s * (1 - np.abs(g(x) * 2 - 1))
        return base + amp * s
    return fn


def volcano(cx, base, amp, r, top=0.94, expo=1.8):
    def fn(x):
        d = np.clip(1 - pdist(x, cx) / r, 0, 1)
        return base + amp * np.minimum(d ** expo, top)
    return fn


def fmax_(*fns):
    return lambda x: np.max(np.stack([f(x) for f in fns]), axis=0)


def pnoise2(x, t, seed, kx=(6, 40), kt=(1, 4), n=10):
    r = np.random.default_rng(seed)
    out = 0
    for _ in range(n):
        k = r.integers(kx[0], kx[1] + 1)
        m = r.uniform(kt[0], kt[1])
        out = out + np.sin(TAU * k * x / W + r.uniform(0, TAU) + m * TAU * t) / (k ** 0.6)
    return out / n


# ----------------------------------------------------------------------------- mesh helpers
def mesh_from(name, verts, faces, mats, smooth=True, mat_idx=None):
    me = bpy.data.meshes.new(name)
    me.from_pydata([tuple(v) for v in verts], [], [tuple(f) for f in faces])
    for m in mats:
        me.materials.append(m)
    if mat_idx is not None:
        me.polygons.foreach_set("material_index", mat_idx)
    if smooth:
        me.shade_smooth()
    me.update()
    return me


def ridge(ctx, layer, name, mat, y0, D, crest_fn, v_low=-1.0, detail=0.25, stri=0.0, nx=640, nt=26,
          gexp=1.6, seed=1, back=0.0):
    """Mountain/hill sheet rising from v_low at depth y0 to crest_fn(x) at depth y0+D (periodic in X)."""
    dx = W / nx
    pad = 24
    xs = (np.arange(-pad, nx + pad + 1) * dx)
    ts = np.linspace(0, 1, nt)
    Xg, Tg = np.meshgrid(xs, ts)
    C = crest_fn(Xg)
    g = 1 - (1 - Tg) ** gexp
    V = v_low + (C - v_low) * g
    amp = (C - v_low)
    V = V + detail * amp * pnoise2(Xg, Tg, seed) * np.sin(np.pi * np.clip(Tg, 0, 1) ** 0.7) * 0.6
    Yg = y0 + D * Tg
    if stri > 0:
        Yg = Yg + stri * D * pnoise2(Xg, Tg * 0.2, seed + 7, kx=(10, 60), kt=(0, 0.5), n=14) * (0.3 + g)
    Zg = (V - H / 2.0 - Yg * SP) / CP
    nxx = len(xs)
    verts = np.stack([Xg, Yg, Zg], -1).reshape(-1, 3)
    idx = np.arange(nt * nxx).reshape(nt, nxx)
    a = idx[:-1, :-1].ravel()
    faces = np.stack([a, a + 1, a + 1 + nxx, a + nxx], -1)
    me = bpy.data.meshes.new("%s_%s" % (ctx.prefix, name))
    me.from_pydata(verts.tolist(), [], faces.tolist())
    me.update()
    me.materials.append(mat)
    me.shade_smooth()
    add_obj(ctx, layer, name, me, wrap=False)
    return crest_fn


def ridge_v(crest_fn, x, t, v_low=-1.0, gexp=1.6):
    g = 1 - (1 - t) ** gexp
    return float(v_low + (crest_fn(np.array([x]))[0] - v_low) * g)


def card(ctx, layer, name, mat, x, y, Vc, w, h, wrap=True, var=1.0):
    key = ("card", mat.name, round(w, 3), round(h, 3))
    me = ctx.meshes.get(key)
    if me is None:
        me = mesh_from("%s_card" % ctx.prefix, [(-w / 2, -h / 2, 0), (w / 2, -h / 2, 0), (w / 2, h / 2, 0), (-w / 2, h / 2, 0)],
                       [(0, 1, 2, 3)], [mat], smooth=False)
        ctx.meshes[key] = me
    zc = z_at(Vc, y)
    return add_obj(ctx, layer, name, me, (x, y, zc), (math.pi / 2 - PITCH, 0, 0), wrap=wrap, var=var)


def fog_band(ctx, layer, y, v_bottom, height, strength=0.7, color=None, name="mist", nscale=0.9, wisp=0.55):
    m = fog_card_mat(ctx, "%s_%d" % (name, int(y)), layer, color, strength, nscale=nscale, wisp=wisp)
    card(ctx, layer, "%s_%d" % (name, int(y)), m, W / 2, y, v_bottom + height / 2, W + 6, height, wrap=False)


def ground_plane(ctx, layer, name, mat, y_near, y_far, V_near):
    """Horizontal plane (spans whole width) whose near edge appears at screen height V_near."""
    z = z_at(V_near, y_near)
    verts = [(-2, y_near, z), (W + 2, y_near, z), (W + 2, y_far, z), (-2, y_far, z)]
    me = mesh_from("%s_%s" % (ctx.prefix, name), verts, [(0, 1, 2, 3)], [mat], smooth=False)
    add_obj(ctx, layer, name, me, wrap=False)
    return z, y_near * SP + z * CP + H / 2, y_far * SP + z * CP + H / 2


class MB:
    """bmesh builder with material slots."""

    def __init__(self):
        self.bm = bmesh.new()
        self.mats = []
        self.fm = []

    def slot(self, m):
        if m not in self.mats:
            self.mats.append(m)
        return self.mats.index(m)

    def _tag(self, verts, m, smooth):
        s = self.slot(m)
        for f in {f for v in verts for f in v.link_faces}:
            f.material_index = s
            f.smooth = smooth

    def ico(self, m, c, r, sc=(1, 1, 1), sub=2, smooth=True, flatten_below=None):
        mat = Matrix.Translation(c) @ Matrix.Diagonal((sc[0] * r, sc[1] * r, sc[2] * r, 1))
        res = bmesh.ops.create_icosphere(self.bm, subdivisions=sub, radius=1.0, matrix=mat)
        if flatten_below is not None:
            for v in res["verts"]:
                if v.co.z < flatten_below:
                    v.co.z = flatten_below + (v.co.z - flatten_below) * 0.15
        self._tag(res["verts"], m, smooth)

    def cone(self, m, p0, p1, r0, r1, segs=8, smooth=True, cap=True):
        p0, p1 = Vector(p0), Vector(p1)
        d = p1 - p0
        L = d.length
        q = d.normalized().to_track_quat("Z", "Y")
        mat = Matrix.Translation((p0 + p1) / 2) @ q.to_matrix().to_4x4()
        res = bmesh.ops.create_cone(self.bm, cap_ends=cap, cap_tris=False, segments=segs, radius1=r0, radius2=max(r1, 0.0001), depth=L, matrix=mat)
        self._tag(res["verts"], m, smooth)

    def box(self, m, c, size, rotz=0.0, smooth=False):
        mat = Matrix.Translation(c) @ Matrix.Rotation(rotz, 4, "Z") @ Matrix.Diagonal((size[0], size[1], size[2], 1))
        res = bmesh.ops.create_cube(self.bm, size=1.0, matrix=mat)
        self._tag(res["verts"], m, smooth)

    def quad(self, m, pts, smooth=False):
        vs = [self.bm.verts.new(p) for p in pts]
        self.bm.faces.new(vs)
        self._tag(vs, m, smooth)

    def grid(self, m, P, smooth=True):
        """P: 2D list rows x cols of points -> quad grid."""
        V = [[self.bm.verts.new(p) for p in row] for row in P]
        for i in range(len(V) - 1):
            for j in range(len(V[0]) - 1):
                try:
                    self.bm.faces.new((V[i][j], V[i][j + 1], V[i + 1][j + 1], V[i + 1][j]))
                except ValueError:
                    pass
        self._tag([v for row in V for v in row], m, smooth)

    def mesh(self, name):
        me = bpy.data.meshes.new(name)
        self.bm.to_mesh(me)
        self.bm.free()
        for m in self.mats:
            me.materials.append(m)
        return me


def roof(mb, m, cx, cy, z0, w, d, h, over=0.25, lift=0.18, rows=6, segs=8, expo=1.7, ridge_frac=None):
    """Concave hip roof with up-turned eave corners (Japanese irimoya-ish silhouette)."""
    W2, D2 = w / 2 + over, d / 2 + over
    rl = (w - d) / 2 if ridge_frac is None else w * ridge_frac / 2
    rl = max(rl, 0.02)
    rows_pts = []
    for i in range(rows + 1):
        s = i / rows
        hw = W2 * (1 - s) + rl * s
        hd = D2 * (1 - s) + 0.001 * s
        zz = z0 + h * (s ** expo)
        ring = []
        # rectangle perimeter, counter-clockwise, segs points per side
        corners = [(-hw, -hd), (hw, -hd), (hw, hd), (-hw, hd)]
        for k in range(4):
            a, b = corners[k], corners[(k + 1) % 4]
            for j in range(segs):
                u = j / segs
                px, py = a[0] + (b[0] - a[0]) * u, a[1] + (b[1] - a[1]) * u
                # corner lift: strongest at corners, only near eaves
                cu = abs(u - 0.5) * 2
                lz = lift * (cu ** 4) * (1 - s) ** 2
                ring.append(Vector((cx + px, cy + py, zz + lz)))
        ring.append(ring[0])
        rows_pts.append(ring)
    mb.grid(m, rows_pts, smooth=True)
    # ridge cap
    mb.box(m, (cx, cy, z0 + h + 0.03), (2 * rl + 0.12, 0.10, 0.08))


# ----------------------------------------------------------------------------- props (meshes)
def pal_mat(ctx, P, role, layer, **kw):
    c = P[role]
    if isinstance(c, (list, tuple)) and len(c) == 2 and isinstance(c[0], str):
        return toon(ctx, role, c[0], c[1], layer, **kw)
    return toon(ctx, role, c, None, layer, **kw)


def m_round_tree(ctx, P, layer, seed, fol="foliage", h=1.4, crown=1.2, blobs=9, trunk="trunk"):
    r = random.Random(seed)
    mb = MB()
    fm = pal_mat(ctx, P, fol, layer)
    lean = r.uniform(-0.15, 0.15)
    if trunk:
        tm = pal_mat(ctx, P, trunk, layer)
        mb.cone(tm, (0, 0, -0.6), (lean, 0, h), 0.13 * crown, 0.07 * crown, segs=7)
        for s in (-1, 1):
            mb.cone(tm, (lean * 0.6, 0, h * 0.6), (lean + s * 0.45 * crown, 0, h + 0.35 * crown), 0.05 * crown, 0.03 * crown, segs=5)
    c = Vector((lean, 0, h + 0.55 * crown))
    for i in range(blobs):
        a = r.uniform(0, TAU)
        rr = r.uniform(0.2, 1.0)
        off = Vector((math.cos(a) * rr * 0.75 * crown, r.uniform(-0.3, 0.3) * crown, math.sin(a) * rr * 0.45 * crown + 0.1 * crown))
        mb.ico(fm, c + off, r.uniform(0.38, 0.6) * crown, sub=3)
    mb.ico(fm, c + Vector((0, 0, 0.35 * crown)), 0.6 * crown, sub=3)
    return mb.mesh("%s_tree%d" % (ctx.prefix, seed))


def m_pine(ctx, P, layer, seed, h=3.0, s=1.0, fol="pine"):
    r = random.Random(seed)
    mb = MB()
    tm = pal_mat(ctx, P, "trunk", layer)
    fm = pal_mat(ctx, P, fol, layer)
    pts = [Vector((0, 0, -0.5))]
    x = 0.0
    for i in range(1, 6):
        x += r.uniform(-0.28, 0.28) * s
        pts.append(Vector((x, 0, h * s * i / 5)))
    for i in range(5):
        mb.cone(tm, pts[i], pts[i + 1], 0.16 * s * (1 - i * 0.15), 0.16 * s * (1 - (i + 1) * 0.15), segs=7)
    pads = r.randint(4, 6)
    side = r.choice((-1, 1))
    for i in range(pads):
        t = 0.45 + 0.55 * i / (pads - 1)
        base = pts[0].lerp(pts[5], t)
        base.x = np.interp(t * 5, range(6), [p.x for p in pts])
        side = -side
        reach = r.uniform(0.4, 1.0) * s * (1.1 - t * 0.5)
        tip = base + Vector((side * reach, 0, r.uniform(0.05, 0.25) * s))
        mb.cone(tm, base, tip, 0.06 * s, 0.035 * s, segs=5)
        pw = r.uniform(0.55, 0.9) * s * (1.15 - t * 0.45)
        for k in range(3):
            mb.ico(fm, tip + Vector((r.uniform(-0.35, 0.35) * pw, r.uniform(-0.2, 0.2), r.uniform(0.0, 0.12) * s)),
                   pw * r.uniform(0.55, 0.8), sc=(1.25, 0.9, 0.42), sub=2)
    top = pts[5]
    for k in range(3):
        mb.ico(fm, top + Vector((r.uniform(-0.3, 0.3) * s, 0, r.uniform(0.0, 0.2) * s)), 0.6 * s, sc=(1.3, 0.9, 0.5), sub=2)
    return mb.mesh("%s_pine%d" % (ctx.prefix, seed))


def m_cedar(ctx, P, layer, seed, h=5.0, s=1.0, fol="cedar"):
    r = random.Random(seed)
    mb = MB()
    tm = pal_mat(ctx, P, "trunk", layer)
    fm = pal_mat(ctx, P, fol, layer)
    mb.cone(tm, (0, 0, -0.8), (0, 0, h * s), 0.16 * s, 0.05 * s, segs=7)
    tiers = 7
    for i in range(tiers):
        t = i / tiers
        z0 = h * s * (0.22 + 0.72 * t)
        rad = (0.95 - 0.8 * t) * s * r.uniform(0.9, 1.1)
        mb.cone(fm, (r.uniform(-0.05, 0.05), 0, z0), (0, 0, z0 + 1.15 * s), rad, 0.05 * s, segs=9)
    return mb.mesh("%s_cedar%d" % (ctx.prefix, seed))


def m_dead_tree(ctx, P, layer, seed, h=2.6, s=1.0, role="deadwood"):
    r = random.Random(seed)
    mb = MB()
    tm = pal_mat(ctx, P, role, layer, grad=0.1)

    def branch(p, d, L, rad, depth):
        q = p + d * L
        mb.cone(tm, p, q, rad, rad * 0.62, segs=6)
        if depth == 0:
            return
        for k in range(r.choice((2, 2, 3))):
            nd = (d + Vector((r.uniform(-0.9, 0.9), r.uniform(-0.3, 0.3), r.uniform(-0.1, 0.5)))).normalized()
            branch(q, nd, L * r.uniform(0.55, 0.75), rad * 0.6, depth - 1)
    branch(Vector((0, 0, -0.5)), Vector((r.uniform(-0.15, 0.15), 0, 1)).normalized(), h * 0.45 * s, 0.17 * s, 4)
    return mb.mesh("%s_dead%d" % (ctx.prefix, seed))


def m_bamboo(ctx, P, layer, seed, h=12.0, rad=0.09, leaves=14, leaf_z=(0.55, 1.0), lean=0.0):
    r = random.Random(seed)
    mb = MB()
    sm = pal_mat(ctx, P, "bamboo", layer, grad=0.15, thr=0.40, soft=0.10)
    nm = pal_mat(ctx, P, "bamboo_node", layer, grad=0.0)
    lm = pal_mat(ctx, P, "bamboo_leaf", layer, grad=0.0, thr=0.40, soft=0.12)
    seg = r.uniform(0.7, 1.0)
    z = -1.0
    top = Vector((lean * h, 0, h))
    while z < h:
        z1 = min(z + seg, h)
        p0 = Vector((lean * z, 0, z))
        p1 = Vector((lean * z1, 0, z1))
        mb.cone(sm, p0, p1 - Vector((0, 0, 0.02)), rad, rad * 0.97, segs=10, cap=False)
        mb.cone(nm, p1 - Vector((0, 0, 0.04)), p1 + Vector((0, 0, 0.03)), rad * 1.1, rad * 1.1, segs=10)
        z = z1
    for i in range(leaves):
        zz = h * r.uniform(*leaf_z)
        base = Vector((lean * zz, 0, zz))
        n = r.randint(3, 6)
        side = r.choice((-1, 1))
        twig_end = base + Vector((side * r.uniform(0.25, 0.6), r.uniform(-0.2, 0.2), r.uniform(-0.05, 0.15)))
        mb.cone(sm, base, twig_end, 0.015, 0.01, segs=4, smooth=False)
        for k in range(n):
            a = r.uniform(-1.2, 0.3) if side > 0 else math.pi - r.uniform(-1.2, 0.3)
            L = r.uniform(0.35, 0.6)
            wl = L * 0.13
            d = Vector((math.cos(a), r.uniform(-0.4, 0.4), math.sin(a))).normalized()
            perp = d.cross(Vector((0, 1, 0))).normalized() * wl
            p0 = twig_end
            mb.quad(lm, [p0, p0 + d * L * 0.4 + perp, p0 + d * L, p0 + d * L * 0.4 - perp])
    return mb.mesh("%s_bamboo%d" % (ctx.prefix, seed))


def m_grass(ctx, P, layer, seed, n=18, h=0.7, spread=0.6, role="grass", plume=None):
    r = random.Random(seed)
    mb = MB()
    gm = pal_mat(ctx, P, role, layer, grad=0.35, thr=0.4, soft=0.12)
    pm = pal_mat(ctx, P, plume, layer, grad=0.0) if plume else None
    for i in range(n):
        x0 = r.uniform(-spread, spread)
        hh = h * r.uniform(0.6, 1.2)
        tipx = x0 + r.uniform(-0.45, 0.45) * hh
        wdt = 0.035
        y0 = r.uniform(-0.1, 0.1)
        mb.quad(gm, [(x0 - wdt, y0, -0.3), (x0 + wdt, y0, -0.3), (tipx + 0.005, y0 + 0.02, hh), (tipx - 0.005, y0 + 0.02, hh)])
        if pm and r.random() < 0.6:
            mb.ico(pm, (tipx, y0, hh), 0.06, sc=(0.6, 0.6, 2.2), sub=1)
    return mb.mesh("%s_grass%d" % (ctx.prefix, seed))


def m_house(ctx, P, layer, seed, w=3.0, d=1.6, h=1.0, rh=0.9, style="tile", windows=2, stories=1):
    r = random.Random(seed)
    mb = MB()
    wall = pal_mat(ctx, P, "plaster" if style == "tile" else "wood_wall", layer, grad=0.2)
    wood = pal_mat(ctx, P, "wood", layer, grad=0.0)
    rm = pal_mat(ctx, P, "roof" if style == "tile" else "thatch", layer, grad=0.0, thr=0.42, soft=0.04)
    stone = pal_mat(ctx, P, "stone", layer, grad=0.0)
    win = (glow_mat(ctx, "window", "#ffc46a", 1.4, layer) if ctx.night and r.random() < 0.85
           else pal_mat(ctx, P, "shoji", layer, grad=0.0))
    mb.box(stone, (0, 0, -0.25), (w + 0.1, d + 0.1, 0.5))
    z = 0.0
    for st in range(stories):
        sw = w * (1 - 0.18 * st)
        sd = d * (1 - 0.18 * st)
        mb.box(wall, (0, 0, z + h / 2), (sw, sd, h))
        for px in np.linspace(-sw / 2, sw / 2, 4):
            mb.box(wood, (px, -sd / 2 - 0.01, z + h / 2), (0.07, 0.06, h))
        mb.box(wood, (0, -sd / 2 - 0.02, z + h * 0.92), (sw, 0.05, 0.08))
        for k in range(windows):
            wx = (k + 0.5) / windows * sw - sw / 2
            mb.box(win, (wx, -sd / 2 - 0.03, z + h * 0.52), (sw / windows * 0.42, 0.04, h * 0.38))
        top = z + h
        if style == "tile":
            roof(mb, rm, 0, 0, top, sw, sd, rh * (0.8 if st < stories - 1 else 1.0), over=0.3, lift=0.22)
        else:
            roof(mb, rm, 0, 0, top - 0.1, sw, sd, rh * 1.5, over=0.35, lift=0.0, expo=1.0, rows=4)
            mb.box(rm, (0, 0, top - 0.05), (sw + 0.75, sd + 0.75, 0.18))
        z = top + (rh * 0.55 if st < stories - 1 else 0)
    return mb.mesh("%s_house%d" % (ctx.prefix, seed))


def m_pagoda(ctx, P, layer, seed, tiers=3, w=1.6):
    mb = MB()
    wall = pal_mat(ctx, P, "pagoda_wall", layer, grad=0.2)
    rm = pal_mat(ctx, P, "roof", layer, grad=0.0, thr=0.42, soft=0.04)
    gold = pal_mat(ctx, P, "gold", layer, grad=0.0)
    z = 0
    for t in range(tiers):
        ww = w * (1 - 0.14 * t)
        mb.box(wall, (0, 0, z + 0.4), (ww, ww, 0.8))
        roof(mb, rm, 0, 0, z + 0.8, ww, ww, 0.35, over=0.45, lift=0.25, ridge_frac=0.02)
        z += 1.1
    mb.cone(gold, (0, 0, z - 0.2), (0, 0, z + 1.3), 0.06, 0.03, segs=6)
    for k in range(5):
        mb.ico(gold, (0, 0, z + k * 0.22), 0.1, sc=(1, 1, 0.3), sub=1)
    return mb.mesh("%s_pagoda%d" % (ctx.prefix, seed))


def m_torii(ctx, P, layer, seed, w=2.4, h=2.4, broken=False):
    mb = MB()
    red = pal_mat(ctx, P, "vermilion", layer, grad=0.15)
    blk = pal_mat(ctx, P, "torii_top", layer, grad=0.0)
    for s in (-1, 1):
        mb.cone(red, (s * w / 2, 0, -0.4), (s * w / 2 * 0.93, 0, h), 0.11, 0.09, segs=10)
        mb.cone(blk, (s * w / 2, 0, -0.4), (s * w / 2, 0, 0.1), 0.14, 0.14, segs=10)
    mb.box(red, (0, 0, h * 0.78), (w + 0.4, 0.12, 0.13))
    # curved kasagi
    pts_lo, pts_hi = [], []
    n = 12
    for i in range(n + 1):
        u = i / n * 2 - 1
        x = u * (w / 2 + 0.6)
        zl = h + 0.06 + 0.18 * u ** 4
        pts_lo.append((x, -0.11, zl))
        pts_hi.append((x, -0.11, zl + 0.18))
    back_lo = [(p[0], 0.11, p[2]) for p in pts_lo]
    back_hi = [(p[0], 0.11, p[2]) for p in pts_hi]
    mb.grid(blk, [pts_lo, pts_hi], smooth=False)
    mb.grid(blk, [pts_hi, back_hi], smooth=False)
    mb.grid(blk, [back_hi, back_lo], smooth=False)
    mb.box(red, (0, 0, h + 0.03), (w + 0.9, 0.18, 0.1))
    mb.box(blk, (0, -0.02, h * 0.88), (0.3, 0.06, 0.35))
    me = mb.mesh("%s_torii%d" % (ctx.prefix, seed))
    return me


def m_lantern(ctx, P, layer, seed, s=1.0):
    mb = MB()
    st = pal_mat(ctx, P, "stone", layer, grad=0.2)
    light = glow_mat(ctx, "lantern_fire", "#ffb75a", 2.0, layer) if ctx.night else pal_mat(ctx, P, "stone_dark", layer, grad=0.0)
    mb.cone(st, (0, 0, -0.2), (0, 0, 0.12 * s), 0.32 * s, 0.26 * s, segs=6, smooth=False)
    mb.cone(st, (0, 0, 0.1 * s), (0, 0, 0.85 * s), 0.09 * s, 0.08 * s, segs=8)
    mb.box(st, (0, 0, 0.9 * s), (0.36 * s, 0.36 * s, 0.1 * s))
    mb.box(light, (0, 0, 1.08 * s), (0.24 * s, 0.24 * s, 0.26 * s))
    mb.cone(st, (0, 0, 1.2 * s), (0, 0, 1.45 * s), 0.38 * s, 0.05 * s, segs=6, smooth=False)
    mb.ico(st, (0, 0, 1.5 * s), 0.07 * s, sub=1)
    return mb.mesh("%s_lantern%d" % (ctx.prefix, seed))


def m_grave(ctx, P, layer, seed):
    r = random.Random(seed)
    mb = MB()
    st = pal_mat(ctx, P, "stone", layer, grad=0.3)
    moss = pal_mat(ctx, P, "stone_dark", layer, grad=0.2)
    mb.box(moss, (0, 0, -0.1), (0.7, 0.55, 0.3))
    mb.box(st, (0, 0, 0.12), (0.5, 0.42, 0.16))
    hh = r.uniform(0.6, 1.0)
    kind = r.random()
    if kind < 0.6:
        mb.box(st, (0, 0, 0.2 + hh / 2), (0.3, 0.26, hh), rotz=r.uniform(-0.1, 0.1))
        mb.cone(st, (0, 0, 0.2 + hh), (0, 0, 0.32 + hh), 0.21, 0.02, segs=4, smooth=False)
    elif kind < 0.85:
        mb.cone(st, (0, 0, 0.2), (0, 0, 0.2 + hh * 0.7), 0.2, 0.17, segs=8)
        mb.ico(st, (0, 0, 0.3 + hh * 0.7), 0.17, sub=1)
    else:  # wooden sotoba stakes
        wd = pal_mat(ctx, P, "deadwood", layer, grad=0.1)
        for k in range(3):
            mb.box(wd, (-0.2 + k * 0.2, 0.25, 0.4 + k * 0.05), (0.06, 0.02, 1.2 + k * 0.1), rotz=0.05 * k)
        mb.box(st, (0, 0, 0.45), (0.32, 0.26, 0.5))
    return mb.mesh("%s_grave%d" % (ctx.prefix, seed))


def m_spikes(ctx, P, layer, seed, n=9, h=1.4, spread=1.0):
    r = random.Random(seed)
    mb = MB()
    sm = pal_mat(ctx, P, "stake", layer, grad=0.3, thr=0.4)
    tip = pal_mat(ctx, P, "stake_tip", layer, grad=0.0)
    rope = pal_mat(ctx, P, "rope", layer, grad=0.0)
    for i in range(n):
        x = (i / max(n - 1, 1) - 0.5) * spread * 2 + r.uniform(-0.08, 0.08)
        hh = h * r.uniform(0.7, 1.2)
        lean = r.uniform(-0.35, 0.35)
        p0 = Vector((x, r.uniform(-0.1, 0.1), -0.5))
        p1 = Vector((x + lean * hh, 0, hh * 0.86))
        p2 = Vector((x + lean * hh * 1.16, 0, hh))
        rad = r.uniform(0.05, 0.08)
        mb.cone(sm, p0, p1, rad, rad, segs=7)
        mb.cone(tip, p1, p2 + (p2 - p1) * 0.3, rad, 0.002, segs=7)
    mb.box(rope, (0, -0.05, h * 0.35), (spread * 2.2, 0.05, 0.05))
    return mb.mesh("%s_spikes%d" % (ctx.prefix, seed))


def m_cloud(ctx, layer, seed, w=3.0, h=1.0, lit="#ffffff", shade="#b8c6e6", name="cloud"):
    r = random.Random(seed)
    mb = MB()
    cm = toon(ctx, name, lit, shade, layer, haze=0.0, noise=0.03, grad=0.0, thr=0.42, soft=0.06, fogmul=0.0)
    n = r.randint(7, 12)
    for i in range(n):
        u = i / (n - 1) * 2 - 1
        x = u * w / 2 * 0.85 + r.uniform(-0.2, 0.2)
        rad = h * (0.55 + 0.6 * (1 - abs(u)) ** 1.2) * r.uniform(0.75, 1.1)
        mb.ico(cm, (x, r.uniform(-0.2, 0.2), rad * 0.45 + r.uniform(0, 0.15) * h), rad, sc=(1.25, 0.8, 0.9), sub=3, flatten_below=0.0)
    return mb.mesh("%s_%s%d" % (ctx.prefix, name, seed))


def m_rock(ctx, P, layer, seed, s=1.0, role="rock"):
    r = random.Random(seed)
    mb = MB()
    rm = pal_mat(ctx, P, role, layer, grad=0.25, thr=0.44, soft=0.03)
    for i in range(r.randint(2, 4)):
        mb.ico(rm, (r.uniform(-0.4, 0.4) * s, r.uniform(-0.2, 0.2), r.uniform(0, 0.3) * s), r.uniform(0.4, 0.75) * s,
               sc=(r.uniform(1.0, 1.6), 0.9, r.uniform(0.7, 1.1)), sub=1, smooth=False, flatten_below=-0.1)
    return mb.mesh("%s_rock%d" % (ctx.prefix, seed))


# ----------------------------------------------------------------------------- composition helpers
def jitter_xs(rng, n, jit=0.35, offset=None):
    off = rng.uniform(0, W) if offset is None else offset
    return [((i + 0.5 + rng.uniform(-jit, jit)) * W / n + off) % W for i in range(n)]


def scatter(ctx, layer, name, meshes, xs, y, vbase_fn, scale=(0.8, 1.2), dy=0.0, var=(0.9, 1.08), sink=0.12):
    r = ctx.rng
    out = []
    for i, x in enumerate(xs):
        me = meshes[i % len(meshes)] if not callable(meshes) else meshes(i)
        yy = y + r.uniform(-dy, dy)
        V = vbase_fn(x) - sink
        s = r.uniform(*scale)
        ob = add_obj(ctx, layer, "%s%d" % (name, i), me, (x, yy, z_at(V, yy)), (0, 0, r.uniform(-0.25, 0.25)), s,
                     var=r.uniform(*var))
        out.append(ob)
    return out


def sky_layer(ctx, S):
    """L0: gradient sky card, sun/moon with halo, clouds, stars."""
    night = ctx.night
    stops = S["sky_night"] if night else S["sky_day"]
    ctx.meta["horizon"] = stops[0][1]
    ctx.meta["zenith"] = stops[-1][1]
    m = sky_mat(ctx, stops)
    card(ctx, "L0", "sky", m, W / 2, 6000, H / 2, W + 8, H + 8, wrap=False)
    r = ctx.rng
    if night:
        mx, mv = S.get("moon_pos", (W * 0.72, H * 0.78))
        mcol = S.get("moon", "#fff4d6")
        card(ctx, "L0", "moonhalo", radial_mat(ctx, "moonhalo", S.get("moon_halo", "#9fb6ff"), 0.0, 1.0, 0.45), mx, 5000, mv, 6.0, 6.0)
        card(ctx, "L0", "moon", radial_mat(ctx, "moon", mcol, power_stops=[(0.0, 1.0), (0.9, 1.0), (1.0, 0.0)], strength=1.0), mx, 4990, mv, 1.1, 1.1)
        # stars: 3 size classes, radial cards (wrap duplicates keep them periodic)
        for cls, (cnt, sz, col) in enumerate([(90, 0.07, "#c9d6ff"), (34, 0.10, "#ffffff"), (8, 0.16, "#fff2cf")]):
            mat = radial_mat(ctx, "star%d" % cls, col, power_stops=[(0.0, 1.0), (0.25, 0.7), (1.0, 0.0)], strength=1.2)
            for i in range(cnt):
                x, v = r.uniform(0, W), r.uniform(H * 0.45, H)
                if r.random() < 0.5 and v < H * 0.62:
                    v = r.uniform(H * 0.62, H)
                card(ctx, "L0", "star", mat, x, 5500 - cls, v, sz, sz)
    else:
        sx, sv = S.get("sun_pos", (W * 0.22, H * 0.80))
        card(ctx, "L0", "sunhalo", radial_mat(ctx, "sunhalo", S.get("sun_halo", "#fff4d2"), 0.0, 1.0, 0.55), sx, 5000, sv, 7.0, 7.0)
        card(ctx, "L0", "sun", radial_mat(ctx, "sun", S.get("sun", "#fffbea"), power_stops=[(0.0, 1.0), (0.85, 1.0), (1.0, 0.0)]), sx, 4990, sv, 1.0, 1.0)
    # clouds
    cl = S.get("clouds_night" if night else "clouds_day", ("#5e6a8e", "#262e52") if night else ("#ffffff", "#b9c7e8"))
    ncl = S.get("n_clouds", 6)
    for i, x in enumerate(jitter_xs(r, ncl, 0.3)):
        w = r.uniform(2.2, 4.8)
        hh = w * r.uniform(0.2, 0.3)
        me = m_cloud(ctx, "L0", ctx.seed * 31 + i, w, hh, cl[0], cl[1])
        v = r.uniform(*S.get("cloud_v", (H * 0.55, H * 0.88)))
        y = 4000 - i * 10
        add_obj(ctx, "L0", "cloud%d" % i, me, (x, y, z_at(v, y)), (0, 0, 0), 1.0)
    # thin stratus streaks
    st = S.get("streak_night" if night else "streak_day", "#ffffff")
    for i, x in enumerate(jitter_xs(r, S.get("n_streaks", 5), 0.4)):
        m = radial_mat(ctx, "streak", st, 0.0, 1.0, 0.5 if not night else 0.3)
        card(ctx, "L0", "streak%d" % i, m, x, 4500 - i, r.uniform(H * 0.45, H * 0.75), r.uniform(4, 8), r.uniform(0.18, 0.35))


def set_fog(ctx, S):
    fd = S["fog_night"] if ctx.night else S["fog_day"]
    base = {"L0": dict(haze=0.0, v0=0.0, v1=0.1, fmax=0.0),
            "L1": dict(haze=0.42, v0=1.5, v1=5.2, fmax=0.75),
            "L2": dict(haze=0.18, v0=0.5, v1=3.6, fmax=0.55),
            "L3": dict(haze=0.05, v0=0.0, v1=2.6, fmax=0.45)}
    for L in LAYERS:
        d = dict(base[L])
        d.update(S.get("fog_%s" % L, {}))
        d["color"] = as_rgb(fd[L] if isinstance(fd, dict) else fd)
        ctx.fog[L] = d


def far_ridges(ctx, S, rows, seed0=10):
    """rows: list of dict(y, D, crest, lit, shade, detail, stri)."""
    for i, rw in enumerate(rows):
        m = toon(ctx, "ridge%d" % i, rw["lit"], rw.get("shade"), rw.get("layer", "L1"), haze=rw.get("haze"),
                 noise=rw.get("noise", 0.06), nscale=rw.get("nscale", 0.8), grad=0.0, thr=rw.get("thr", 0.45), soft=rw.get("soft", 0.05),
                 fogmul=rw.get("fogmul", 1.0))
        ridge(ctx, rw.get("layer", "L1"), "ridge%d" % i, m, rw["y"], rw.get("D", 8), rw["crest"], v_low=rw.get("v_low", -1.0),
              detail=rw.get("detail", 0.25), stri=rw.get("stri", 0.0), seed=seed0 + i, gexp=rw.get("gexp", 1.6))


# ----------------------------------------------------------------------------- palettes
BASE_P = {
    "trunk": ("#7a5a4a", "#3c2c36"),
    "foliage": ("#79b45e", "#3f7a5e"),
    "pine": ("#5f9c62", "#2e5c56"),
    "cedar": ("#4e8a64", "#244c4c"),
    "maple": ("#ef7a3c", "#a83a3e"),
    "ginkgo": ("#f2c04c", "#b97a3c"),
    "sakura": ("#ffd3df", "#d98aac"),
    "persimmon": ("#7da35a", "#466e50"),
    "plaster": ("#f6f0e2", "#a7abc2"),
    "wood_wall": ("#9a6e4e", "#56404a"),
    "wood": ("#5e4034", "#2e2430"),
    "roof": ("#5f6b86", "#2b3350"),
    "thatch": ("#caa262", "#7c5a44"),
    "stone": ("#bdbab0", "#6f7388"),
    "stone_dark": ("#7f8076", "#454a5c"),
    "shoji": ("#efe6cc", "#b9ae9a"),
    "vermilion": ("#e4492e", "#922c3c"),
    "torii_top": ("#3a3038", "#1c1824"),
    "gold": ("#e8c060", "#9a6a3a"),
    "pagoda_wall": ("#d65a3a", "#7d3040"),
    "deadwood": ("#8a7a76", "#4a4252"),
    "grass": ("#a6b866", "#5e7a54"),
    "susuki": ("#d8c690", "#8f7c62"),
    "bamboo": ("#a8cc62", "#4f8a58"),
    "bamboo_node": ("#7ea24e", "#3c6a48"),
    "bamboo_leaf": ("#78b450", "#356a4a"),
    "stake": ("#c6b870", "#6e7050"),
    "stake_tip": ("#e8dcb0", "#8a8060"),
    "rope": ("#8a6a4a", "#4a3a3a"),
    "rock": ("#a89c8e", "#5c5868"),
    "hill": ("#9cc06a", "#5a8a5e"),
    "straw": ("#e4c46a", "#a07a4e"),
    "fruit": ("#f2803a", "#a84a3a"),
}


def P_with(**kw):
    p = dict(BASE_P)
    p.update(kw)
    return p


# ============================================================================= biomes
def b_school(ctx):
    S = dict(sky_day=[(0, "#f4f1dc"), (4.5, "#dff0ec"), (7.5, "#9fd0ef"), (H, "#5ea6e4")],
             sky_night=[(0, "#2c3f6e"), (4.5, "#1d2d5c"), (8, "#111c40"), (H, "#070c22")],
             fog_day={"L1": "#cfe4ef", "L2": "#dcebe8", "L3": "#e4eadc", "L0": "#ffffff"},
             fog_night={"L1": "#2a3a68", "L2": "#22305a", "L3": "#1b2548", "L0": "#000000"},
             n_clouds=6)
    set_fog(ctx, S)
    P = P_with()
    sky_layer(ctx, S)
    # L1: Mt Iwaki-like cone + ridges
    far_ridges(ctx, S, [
        dict(y=900, D=9, crest=fmax_(volcano(W * 0.62, 5.4, 3.8, 6.5, expo=1.6), crest(11, 5.6, 1.2, 2, 14, ridged=0.4)), lit="#b4d0ea", shade="#7c98c8", detail=0.35, haze=0.42),
        dict(y=700, D=7, crest=crest(12, 4.9, 1.6, 2, 16, 1.6, ridged=0.35), lit="#98bcd8", shade="#6582b4", detail=0.35, haze=0.34),
        dict(y=520, D=6, crest=crest(13, 4.3, 1.2, 3, 18, 1.3, ridged=0.3), lit="#8cb49c", shade="#56788c", detail=0.35, haze=0.26),
    ])
    fog_band(ctx, "L1", 600, 2.6, 2.6, 0.75)
    # L2: distant town on a terrace, school wall, halls, pines & maples
    hill = crest(21, 3.9, 0.6, 1, 5, 1.0)
    ridge(ctx, "L2", "terrace", pal_mat(ctx, P, "hill", "L2"), 330, 6, hill, detail=0.15, seed=21)
    r = ctx.rng
    houses = [m_house(ctx, P, "L2", 100 + i, w=r.uniform(1.2, 2.2), d=1.0, h=0.55, rh=0.5, windows=2) for i in range(5)]
    scatter(ctx, "L2", "town", houses, jitter_xs(r, 16, 0.3), 335, lambda x: hill(np.array([x]))[0] + 0.05, (0.55, 0.75), dy=3)
    trees_far = [m_round_tree(ctx, P, "L2", 200 + i, fol=("pine" if i % 2 else "foliage"), crown=0.9) for i in range(4)]
    scatter(ctx, "L2", "ftree", trees_far, jitter_xs(r, 14, 0.4), 334, lambda x: hill(np.array([x]))[0], (0.45, 0.7), dy=4)
    # school compound: long wall (spans width) + gate + main hall + dojo + tower
    yw = 240
    wall_v = 2.35
    mb = MB()
    wall = pal_mat(ctx, P, "plaster", "L2", grad=0.15)
    wood = pal_mat(ctx, P, "wood", "L2", grad=0.0)
    rm = pal_mat(ctx, P, "roof", "L2", grad=0.0, thr=0.42, soft=0.04)
    zb = z_at(wall_v, yw)
    mb.box(wall, (W / 2, 0, zb + 0.5), (W + 4, 0.3, 1.0))
    mb.box(wood, (W / 2, -0.16, zb + 0.15), (W + 4, 0.02, 0.3))
    roof(mb, rm, W / 2, 0, zb + 1.0, W + 4, 0.3, 0.22, over=0.16, lift=0.0, rows=3, segs=40, expo=1.0, ridge_frac=1.0)
    add_obj(ctx, "L2", "schoolwall", mb.mesh(ctx.prefix + "_wall"), (0, yw, 0), wrap=False)
    hall = m_house(ctx, P, "L2", 300, w=4.6, d=2.4, h=1.3, rh=1.4, windows=4, stories=2)
    add_obj(ctx, "L2", "hall", hall, (W * 0.30, yw + 14, z_at(wall_v + 0.25, yw + 14)), (0, 0, 0.0), 0.78)
    dojo = m_house(ctx, P, "L2", 301, w=3.4, d=2.0, h=1.0, rh=1.1, windows=3)
    add_obj(ctx, "L2", "dojo", dojo, (W * 0.74, yw + 10, z_at(wall_v + 0.15, yw + 10)), (0, 0, 0), 0.8)
    gate = m_house(ctx, P, "L2", 302, w=1.6, d=0.8, h=1.0, rh=0.7, windows=0)
    add_obj(ctx, "L2", "gate", gate, (W * 0.52, yw - 1.5, z_at(wall_v - 0.05, yw - 1.5)), (0, 0, 0), 1.0)
    pag = m_pagoda(ctx, P, "L2", 303, tiers=3, w=1.3)
    add_obj(ctx, "L2", "tower", pag, (W * 0.92, yw + 20, z_at(wall_v + 0.4, yw + 20)), (0, 0, 0.0), 0.8)
    pines = [m_pine(ctx, P, "L2", 400 + i, h=2.6) for i in range(4)]
    scatter(ctx, "L2", "pine", pines, [W * 0.12, W * 0.45, W * 0.62, W * 0.84], yw + 6, lambda x: wall_v + 0.4, (0.6, 0.8), dy=3)
    maples = [m_round_tree(ctx, P, "L2", 500 + i, fol=("maple" if i % 2 == 0 else "ginkgo"), crown=1.0, h=1.2) for i in range(4)]
    scatter(ctx, "L2", "maple", maples, [W * 0.04, W * 0.2, W * 0.4, W * 0.58, W * 0.68, W * 0.98], yw + 4, lambda x: wall_v + 0.2, (0.6, 0.85), dy=2)
    if ctx.night:
        lmat = radial_mat(ctx, "lanternhalo", "#ffb05a", 0.0, 1.0, 0.5)
        for x in (W * 0.30 - 1.2, W * 0.30 + 1.2, W * 0.52, W * 0.74):
            card(ctx, "L2", "halo", lmat, x, yw - 3, wall_v + 0.8, 1.6, 1.6)
    lawn = lambda x: np.full_like(np.asarray(x, dtype=float), wall_v + 0.05)
    ridge(ctx, "L2", "lawn", pal_mat(ctx, P, "hill", "L2"), yw - 12, 10, lawn, detail=0.0, seed=22, gexp=3.0)
    fog_band(ctx, "L2", 225, 0.0, 2.4, 0.55)
    # L3: near pine & maple framing, hedges
    nm = [m_pine(ctx, P, "L3", 600, h=5.2, s=1.6)]
    scatter(ctx, "L3", "npine", nm, [W * 0.10], 60, lambda x: 0.3, (1.0, 1.0), sink=0)
    nmap = [m_round_tree(ctx, P, "L3", 700, fol="maple", crown=2.0, h=4.6, blobs=14)]
    scatter(ctx, "L3", "nmaple", nmap, [W * 0.66], 55, lambda x: 0.2, (1.0, 1.0), sink=0)
    bush = [m_round_tree(ctx, P, "L3", 800 + i, fol="foliage", crown=0.8, h=0.0, blobs=7, trunk=None) for i in range(3)]
    scatter(ctx, "L3", "bush", bush, [W * 0.03, W * 0.17, W * 0.58, W * 0.74, W * 0.80], 70, lambda x: 0.0, (0.9, 1.2), sink=0)
    return dict(particles="leaves" if not ctx.night else "fireflies")


# ----------------------------------------------------------------------------- shared biome helpers
def mesa(seed, base, amp, kmin=2, kmax=9, cut=0.42, k=3.5, n=7):
    f = PFn(seed, kmin, kmax, n)
    g = PFn(seed + 5, 8, 24, 8)

    def fn(x):
        s = np.clip((f(x) - cut) * k, 0, 1) ** 0.6
        return base + amp * s + 0.12 * amp * (g(x) - 0.5) * (0.3 + s)
    return fn


def const(v):
    return lambda x: np.full_like(np.asarray(x, dtype=float), v)


def hill_trees(ctx, P, layer, name, y, crest_fn, meshes, n, scale, role="hill", D=6, detail=0.15, seed=0, jit=0.4, var=(0.88, 1.08), t=0.85):
    ridge(ctx, layer, name, pal_mat(ctx, P, role, layer), y, D, crest_fn, detail=detail, seed=seed)
    if meshes and n:
        xs = jitter_xs(ctx.rng, n, jit)
        scatter(ctx, layer, name + "_t", meshes, xs, y + D * t, lambda x: ridge_v(crest_fn, x, t), scale, dy=0.5, var=var)


def halo(ctx, layer, x, y, V, size=1.4, color="#ffb05a", name="halo", peak=0.5):
    if ctx.night:
        card(ctx, layer, name, radial_mat(ctx, "halo_" + color, color, 0.0, 1.0, peak), x, y, V, size, size)


def m_tube(ctx, name, pts, radii, mat, segs=12, mat2=None):
    """Tube along a polyline (serpent bodies). pts: list of Vector, radii: list."""
    mb = MB()
    rings = []
    for i, p in enumerate(pts):
        d = (pts[min(i + 1, len(pts) - 1)] - pts[max(i - 1, 0)]).normalized()
        a = d.cross(Vector((0, 1, 0)))
        if a.length < 1e-4:
            a = Vector((1, 0, 0))
        a.normalize()
        b = d.cross(a).normalized()
        ring = []
        for k in range(segs + 1):
            th = TAU * k / segs
            ring.append(p + (a * math.cos(th) + b * math.sin(th)) * radii[i])
        rings.append(ring)
    mb.grid(mat, rings, smooth=True)
    return mb.mesh(name)


def m_persimmon(ctx, P, layer, seed, crown=1.1):
    r = random.Random(seed)
    mb = MB()
    tm = pal_mat(ctx, P, "trunk", layer)
    fm = pal_mat(ctx, P, "persimmon", layer)
    om = pal_mat(ctx, P, "fruit", layer, grad=0.0)
    mb.cone(tm, (0, 0, -0.6), (0.1, 0, 1.3), 0.12 * crown, 0.07 * crown, segs=7)
    c = Vector((0.1, 0, 1.3 + 0.5 * crown))
    for i in range(8):
        a = r.uniform(0, TAU)
        off = Vector((math.cos(a) * 0.7 * crown * r.random(), r.uniform(-0.3, 0.3), math.sin(a) * 0.4 * crown))
        mb.ico(fm, c + off, r.uniform(0.35, 0.55) * crown, sub=3)
    for i in range(22):
        a = r.uniform(0, TAU)
        mb.ico(om, c + Vector((math.cos(a) * 0.95 * crown * r.uniform(0.5, 1), -0.55 * crown, math.sin(a) * 0.65 * crown * r.uniform(0.4, 1))), 0.07 * crown, sub=1)
    return mb.mesh("%s_persimmon%d" % (ctx.prefix, seed))


def m_hasa(ctx, P, layer, seed, w=2.4):
    mb = MB()
    wd = pal_mat(ctx, P, "wood", layer, grad=0.0)
    st = pal_mat(ctx, P, "straw", layer, grad=0.2)
    for x in np.linspace(-w / 2, w / 2, 4):
        mb.cone(wd, (x, 0, -0.3), (x, 0, 1.0), 0.03, 0.03, segs=5)
    mb.box(st, (0, 0, 0.7), (w, 0.16, 0.42))
    mb.box(st, (0, 0, 0.3), (w, 0.14, 0.3))
    return mb.mesh("%s_hasa%d" % (ctx.prefix, seed))


def m_stack(ctx, P, layer, seed):
    mb = MB()
    st = pal_mat(ctx, P, "straw", layer, grad=0.25)
    mb.cone(st, (0, 0, -0.2), (0, 0, 0.9), 0.35, 0.02, segs=10)
    mb.cone(st, (0, 0, -0.2), (0, 0, 0.35), 0.38, 0.33, segs=10)
    return mb.mesh("%s_stack%d" % (ctx.prefix, seed))


def m_fence(ctx, P, layer, h=0.6, role="wood"):
    mb = MB()
    wd = pal_mat(ctx, P, role, layer, grad=0.0)
    n = 64
    for i in range(n):
        x = (i + 0.5) * W / n
        mb.box(wd, (x, 0, h / 2 - 0.1), (0.05, 0.05, h + 0.2))
    for zz in (h * 0.35, h * 0.8):
        mb.box(wd, (W / 2, 0, zz), (W + 4, 0.04, 0.05))
    return mb.mesh("%s_fence" % ctx.prefix)


def m_stairs(ctx, P, layer, n=18, w=1.4, rise=0.16, run=0.42, role="stone"):
    mb = MB()
    st = pal_mat(ctx, P, role, layer, grad=0.0)
    for i in range(n):
        mb.box(st, (0, i * run, i * rise + rise / 2), (w, run + 0.02, rise))
    for s in (-1, 1):
        mb.box(st, (s * (w / 2 + 0.06), n * run / 2, n * rise / 2), (0.12, n * run, 0.1))
    return mb.mesh("%s_stairs" % ctx.prefix)


def m_boat(ctx, P, layer, seed):
    mb = MB()
    wd = pal_mat(ctx, P, "wood_wall", layer, grad=0.0)
    mb.box(wd, (0, 0, 0.05), (1.6, 0.35, 0.16))
    mb.cone(wd, (0.8, 0, 0.05), (1.15, 0, 0.22), 0.1, 0.02, segs=4, smooth=False)
    mb.cone(pal_mat(ctx, P, "wood", layer), (-0.2, 0, 0.1), (-0.5, 0, 1.0), 0.015, 0.012, segs=4)
    return mb.mesh("%s_boat%d" % (ctx.prefix, seed))


def std_sky(ctx, S):
    set_fog(ctx, S)
    sky_layer(ctx, S)


# ============================================================================= more biomes
def b_sakura(ctx):
    S = dict(sky_day=[(0, "#fde9ea"), (4.5, "#f6e6ef"), (7.5, "#b4d6f2"), (H, "#78b2e8")],
             sky_night=[(0, "#3a3a70"), (4.5, "#262a5e"), (8, "#141a42"), (H, "#080b24")],
             fog_day={"L1": "#ead8e8", "L2": "#f4e2ea", "L3": "#f6e8ea", "L0": "#ffffff"},
             fog_night={"L1": "#30346a", "L2": "#2a2c5c", "L3": "#22244a", "L0": "#000000"},
             clouds_day=("#ffffff", "#d8c4e4"), n_clouds=5)
    std_sky(ctx, S)
    P = P_with(hill=("#b2cc7e", "#6e9a72"), path=("#ead6b4", "#a89290"))
    far_ridges(ctx, S, [
        dict(y=900, D=9, crest=crest(31, 5.4, 2.2, 1, 9, 1.5, ridged=0.3), lit="#d4c8e6", shade="#a29ad0", detail=0.3, haze=0.45),
        dict(y=650, D=7, crest=crest(32, 4.8, 1.4, 2, 12, 1.3), lit="#c4b8dc", shade="#8f88c2", detail=0.3, haze=0.35),
    ])
    fog_band(ctx, "L1", 600, 3.4, 2.4, 0.7)
    r = ctx.rng
    sak = [m_round_tree(ctx, P, "L2", 1000 + i, fol="sakura", crown=1.25, h=1.3, blobs=12) for i in range(4)]
    h1 = crest(33, 3.9, 0.5, 1, 4)
    hill_trees(ctx, P, "L2", "hillA", 320, h1, sak, 13, (0.55, 0.75), seed=33)
    fog_band(ctx, "L2", 300, 3.0, 1.6, 0.6)
    h2 = crest(34, 2.9, 0.35, 1, 3)
    hill_trees(ctx, P, "L2", "hillB", 250, h2, sak, 8, (0.8, 1.05), seed=34, jit=0.3)
    lan = [m_lantern(ctx, P, "L2", 1100)]
    xs = [(i + 0.5) * W / 6 for i in range(6)]
    scatter(ctx, "L2", "lan", lan, xs, 245, lambda x: 2.55, (0.6, 0.6), var=(1, 1))
    for x in xs:
        halo(ctx, "L2", x, 243, 3.25, 1.2)
    torii = m_torii(ctx, P, "L2", 1101, w=2.0, h=2.0)
    add_obj(ctx, "L2", "torii", torii, (W * 0.42, 247, z_at(2.5, 247)), (0, 0, 0), 0.75)
    path = toon(ctx, "path", "#ecd8b8", "#b09aa0", "L2", flat=True, grad=0.0)
    ridge(ctx, "L2", "path", path, 236, 6, const(2.55), detail=0.0, gexp=3.0, seed=35)
    fog_band(ctx, "L2", 232, 0.0, 2.0, 0.5)
    big = m_round_tree(ctx, P, "L3", 1200, fol="sakura", crown=2.0, h=6.6, blobs=18)
    add_obj(ctx, "L3", "bigsakura", big, (W * 0.18, 60, z_at(-0.3, 60)), (0, 0, 0), 1.0)
    big2 = m_round_tree(ctx, P, "L3", 1201, fol="sakura", crown=1.6, h=7.8, blobs=14)
    add_obj(ctx, "L3", "bigsakura2", big2, (W * 0.72, 62, z_at(-0.3, 62)), (0, 0, 0), 1.0)
    return dict(particles="petals", notes="cherry trees are blob clusters; petals expected as runtime particles")


def b_bamboo(ctx):
    S = dict(sky_day=[(0, "#eef3dc"), (5, "#e2efd8"), (8, "#c2e0e2"), (H, "#8cc4e0")],
             sky_night=[(0, "#22385a"), (5, "#1a2c50"), (8, "#101c3a"), (H, "#060b20")],
             fog_day={"L1": "#dbe9d4", "L2": "#dfecd6", "L3": "#e2eed8", "L0": "#ffffff"},
             fog_night={"L1": "#22365a", "L2": "#1e3052", "L3": "#1a2846", "L0": "#000000"},
             n_clouds=3, fog_L2=dict(haze=0.22, v0=0.0, v1=5.0, fmax=0.7))
    std_sky(ctx, S)
    P = P_with(hill=("#9cbc74", "#5e8a62"))
    far_ridges(ctx, S, [
        dict(y=900, D=9, crest=crest(41, 5.8, 2.0, 1, 10, 1.4, ridged=0.3), lit="#c4dcc8", shade="#96b4b4", detail=0.3, haze=0.55),
        dict(y=650, D=7, crest=crest(42, 4.9, 1.4, 2, 12, 1.2), lit="#b0cfb0", shade="#7ea4a0", detail=0.3, haze=0.45),
    ])
    fog_band(ctx, "L1", 600, 3.2, 3.0, 0.8)
    r = ctx.rng
    rows = [(340, 18, 0.06, 0.5), (280, 14, 0.075, 0.32), (220, 11, 0.09, 0.15)]
    for ri, (y, n, rad, hz) in enumerate(rows):
        ms = [m_bamboo(ctx, P, "L2", 1300 + ri * 10 + k, h=15, rad=rad, leaves=10, leaf_z=(0.45, 1.0), lean=r.uniform(-0.03, 0.03)) for k in range(4)]
        xs = jitter_xs(r, n, 0.45)
        for i, x in enumerate(xs):
            yy = y + r.uniform(-3, 3)
            add_obj(ctx, "L2", "bam%d_%d" % (ri, i), ms[i % 4], (x, yy, z_at(1.0 + ri * -0.4, yy)), (0, 0, r.uniform(0, TAU)), 1.0, var=r.uniform(0.9, 1.05))
        fog_band(ctx, "L2", y - 8, 0.0, 5.5 - ri * 0.8, 0.55, nscale=0.7)
    ms = [m_bamboo(ctx, P, "L3", 1400 + k, h=16, rad=0.2, leaves=12, leaf_z=(0.35, 1.0), lean=r.uniform(-0.04, 0.04)) for k in range(3)]
    for i, x in enumerate([W * 0.06, W * 0.13, W * 0.47, W * 0.78, W * 0.83]):
        add_obj(ctx, "L3", "nb%d" % i, ms[i % 3], (x, 60 + i, z_at(-1.0, 60 + i)), (0, 0, r.uniform(0, TAU)), 1.0)
    return dict(particles="mist", notes="bamboo rows are separate stalk meshes with leaf quads; mist between rows")


def b_waterfall(ctx):
    S = dict(sky_day=[(0, "#eaf4f0"), (5, "#d6ecf2"), (8, "#9ed0ee"), (H, "#5ea4e0")],
             sky_night=[(0, "#26406a"), (5, "#1c305a"), (8, "#101c40"), (H, "#060b22")],
             fog_day={"L1": "#d2e4ee", "L2": "#e0eef2", "L3": "#e6f0f0", "L0": "#ffffff"},
             fog_night={"L1": "#263c66", "L2": "#22365e", "L3": "#1c2c50", "L0": "#000000"}, n_clouds=5)
    std_sky(ctx, S)
    P = P_with(rock=("#a6a8a6", "#5c6276"), hill=("#8ab46e", "#4f7e62"))
    far_ridges(ctx, S, [
        dict(y=900, D=10, crest=mesa(51, 6.0, 2.6, 2, 8), lit="#b2c8de", shade="#7a92bc", detail=0.2, stri=0.4, haze=0.45),
        dict(y=650, D=8, crest=mesa(52, 5.0, 2.2, 2, 9, cut=0.38), lit="#9cb4cc", shade="#6a80a8", detail=0.2, stri=0.5, haze=0.35),
    ])
    fog_band(ctx, "L1", 600, 3.6, 2.4, 0.8)
    r = ctx.rng
    # main cliff wall with notches for falls
    falls = [W * 0.28, W * 0.71]
    base = mesa(53, 4.2, 3.4, 1, 5, cut=0.25, k=2.5)

    def cliffc(x):
        v = base(x)
        for fx in falls:
            v = v - 0.35 * np.exp(-(pdist(x, fx) / 0.5) ** 2)
        return v
    rock = pal_mat(ctx, P, "rock", "L2", thr=0.46, soft=0.08)
    ridge(ctx, "L2", "cliff", rock, 260, 7, cliffc, detail=0.25, stri=0.22, seed=53, gexp=1.2)
    pines = [m_pine(ctx, P, "L2", 1500 + i, h=2.2) for i in range(3)]
    xs = [x for x in jitter_xs(r, 10, 0.4) if min(pdist(x, falls[0]), pdist(x, falls[1])) > 1.2]
    scatter(ctx, "L2", "cpine", pines, xs, 266.5, lambda x: cliffc(np.array([x]))[0], (0.45, 0.7))
    for i, fx in enumerate(falls):
        top = float(cliffc(np.array([fx]))[0]) - 0.05
        w = 1.0 if i == 0 else 0.7
        fm = fall_mat(ctx, "fall%d" % i, "L2", "#f4fbff", "#9cc8e6")
        card(ctx, "L2", "fall%d" % i, fm, fx, 258, (top + 1.4) / 2, w, top - 1.4)
        mist = radial_mat(ctx, "spray", "#f2f8fa" if not ctx.night else "#8ea6cc", 0.0, 1.0, 0.85)
        for k in range(4):
            card(ctx, "L2", "spray%d_%d" % (i, k), mist, fx + r.uniform(-1.0, 1.0), 252 - k, 1.7 + r.uniform(-0.2, 0.5), r.uniform(1.8, 3.2), r.uniform(1.2, 1.8))
    wm = water_mat(ctx, "pool", "L2", "#5c9cc8", "#9cd0e8", "#ffffff", 2.0, 0.0)
    ground_plane(ctx, "L2", "pool", wm, 240, 250, 0.0)
    fog_band(ctx, "L2", 238, 0.0, 2.2, 0.55)
    rocks = [m_rock(ctx, P, "L3", 1600 + i, 1.6) for i in range(3)]
    scatter(ctx, "L3", "nrock", rocks, [W * 0.05, W * 0.52, W * 0.9], 60, lambda x: 0.4, (0.9, 1.3), sink=0)
    np_ = m_pine(ctx, P, "L3", 1650, h=5.4, s=1.5)
    add_obj(ctx, "L3", "npine", np_, (W * 0.55, 62, z_at(0.6, 62)), (0, 0, 0), 1.0)
    return dict(particles="mist", notes="falls are animated-ready cards (static here); spray = radial mist cards")


def b_fields(ctx):
    S = dict(sky_day=[(0, "#fbf0d6"), (4.5, "#f2ecd6"), (7.5, "#a8d2ee"), (H, "#62a6e2")],
             sky_night=[(0, "#34406c"), (4.5, "#232f5c"), (8, "#121c40"), (H, "#070b22")],
             fog_day={"L1": "#dfe6e4", "L2": "#efe8d2", "L3": "#f0e6cc", "L0": "#ffffff"},
             fog_night={"L1": "#2a386a", "L2": "#26305a", "L3": "#1e284c", "L0": "#000000"}, n_clouds=7,
             fog_L2=dict(haze=0.12, v0=0.0, v1=2.6, fmax=0.5))
    std_sky(ctx, S)
    P = P_with(straw=("#e4c46a", "#a07a4e"), hill=("#a2ba68", "#62865a"))
    far_ridges(ctx, S, [
        dict(y=900, D=9, crest=fmax_(volcano(W * 0.3, 4.9, 3.4, 7.0), crest(61, 4.8, 0.8, 2, 12)), lit="#b6cee6", shade="#7e98c6", detail=0.25, haze=0.45),
        dict(y=650, D=7, crest=crest(62, 4.3, 1.0, 2, 10, 1.2), lit="#98b8c8", shade="#6884a8", detail=0.3, haze=0.35),
    ])
    fog_band(ctx, "L1", 600, 3.4, 1.8, 0.65)
    r = ctx.rng
    tl = crest(63, 4.05, 0.25, 1, 4)
    trees = [m_round_tree(ctx, P, "L2", 1700 + i, fol=("foliage" if i % 3 else "ginkgo"), crown=0.9, h=1.0) for i in range(4)]
    hill_trees(ctx, P, "L2", "treeline", 320, tl, trees, 22, (0.4, 0.6), seed=63, jit=0.45)
    houses = [m_house(ctx, P, "L2", 1750 + i, w=2.0, d=1.4, h=0.7, rh=0.6, style="thatch", windows=1) for i in range(2)]
    scatter(ctx, "L2", "farm", houses, [W * 0.18, W * 0.64], 326, lambda x: ridge_v(tl, x, 0.9), (0.5, 0.6))
    for x in (W * 0.18, W * 0.64):
        halo(ctx, "L2", x, 324, ridge_v(tl, x, 0.9) + 0.3, 0.9)
    fm = field_mat(ctx, "paddy", "L2", "#e6c25c", "#d2b456", "#c0a060", ncols=22, yfreq=0.7)
    ground_plane(ctx, "L2", "paddies", fm, 302, 320.5, 0.0)
    hs = [m_hasa(ctx, P, "L2", 1800 + i) for i in range(2)]
    scatter(ctx, "L2", "hasa", hs, jitter_xs(r, 4, 0.3), 312, lambda x: 0.0 + (312 - 300) * SP + 0.0, (0.55, 0.7), sink=0.0)
    st = [m_stack(ctx, P, "L2", 1850)]
    scatter(ctx, "L2", "stack", st, jitter_xs(r, 7, 0.4), 306, lambda x: (306 - 300) * SP, (0.6, 0.9), sink=0.0)
    fog_band(ctx, "L2", 298, 0.0, 2.2, 0.45)
    pt = [m_persimmon(ctx, P, "L3", 1900 + i, crown=1.8) for i in range(2)]
    scatter(ctx, "L3", "pers", pt, [W * 0.12, W * 0.62], 60, lambda x: 0.2, (1.0, 1.15), sink=0)
    gr = [m_grass(ctx, P, "L3", 1950 + i, n=40, h=1.1, spread=1.6, role="susuki", plume="susuki") for i in range(2)]
    scatter(ctx, "L3", "grass", gr, [W * 0.3, W * 0.85], 62, lambda x: 0.0, (1.0, 1.2), sink=0)
    return dict(particles="leaves" if not ctx.night else "fireflies", notes="golden harvest paddies (autumn 1937)")


def b_river(ctx):
    S = dict(sky_day=[(0, "#f2f2e0"), (4.5, "#e2f0ec"), (7.5, "#a2d0f0"), (H, "#5aa2e2")],
             sky_night=[(0, "#2c4070"), (4.5, "#1e2e5c"), (8, "#101c40"), (H, "#060b22")],
             fog_day={"L1": "#d6e6ee", "L2": "#e2eeee", "L3": "#e6eee6", "L0": "#ffffff"},
             fog_night={"L1": "#283c6a", "L2": "#23345e", "L3": "#1c2a50", "L0": "#000000"}, n_clouds=6)
    std_sky(ctx, S)
    P = P_with(reed=("#b8b46a", "#6e7656"), willow=("#9ec46a", "#5a8a5a"))
    far_ridges(ctx, S, [
        dict(y=900, D=9, crest=crest(71, 5.0, 2.0, 1, 10, 1.6, ridged=0.3), lit="#b2cce6", shade="#7c96c4", detail=0.3, haze=0.45),
        dict(y=650, D=7, crest=crest(72, 4.6, 1.0, 2, 12, 1.2), lit="#9cbcd0", shade="#6a86ac", detail=0.3, haze=0.35),
    ])
    fog_band(ctx, "L1", 600, 3.6, 1.6, 0.6)
    r = ctx.rng
    bank = crest(73, 4.25, 0.35, 1, 5)
    trees = [m_round_tree(ctx, P, "L2", 2000 + i, fol=("foliage" if i % 2 else "maple"), crown=0.85, h=0.9) for i in range(4)]
    hill_trees(ctx, P, "L2", "farbank", 252, bank, trees, 18, (0.45, 0.6), seed=73)
    hs = [m_house(ctx, P, "L2", 2050 + i, w=1.6, d=1.0, h=0.6, rh=0.5, windows=2) for i in range(2)]
    scatter(ctx, "L2", "bankh", hs, [W * 0.4, W * 0.47, W * 0.86], 257, lambda x: ridge_v(bank, x, 0.85), (0.45, 0.55))
    wm = water_mat(ctx, "river", "L2", "#4f8fc0", "#a8d6ec", "#f4fcff", 4.3, 0.0, streak=0.8, yfreq=6.0)
    ground_plane(ctx, "L2", "river", wm, 232, 252.8, 0.0)
    boat = m_boat(ctx, P, "L2", 2060)
    add_obj(ctx, "L2", "boat", boat, (W * 0.62, 246, z_at(3.0, 246)), (0, 0, 0), 0.7)
    halo(ctx, "L2", W * 0.62, 245, 3.25, 0.8)
    fog_band(ctx, "L2", 236, 2.6, 1.4, 0.5)
    fog_band(ctx, "L2", 233, 0.0, 2.0, 0.4)
    reeds = [m_grass(ctx, P, "L3", 2100 + i, n=50, h=1.6, spread=1.4, role="reed", plume="susuki") for i in range(3)]
    scatter(ctx, "L3", "reeds", reeds, jitter_xs(r, 6, 0.3), 60, lambda x: 0.0, (0.9, 1.3), sink=0)
    wl = m_round_tree(ctx, P, "L3", 2150, fol="willow", crown=2.0, h=5.5, blobs=12)
    add_obj(ctx, "L3", "willow", wl, (W * 0.8, 62, z_at(0.0, 62)), (0, 0, 0), 1.0)
    return dict(particles="mist" if not ctx.night else "fireflies", notes="water is a flat plane with periodic streak highlights, no reflections")


def b_graveyard(ctx):
    S = dict(sky_day=[(0, "#e4e2dc"), (5, "#d4d6dc"), (8, "#b4bccc"), (H, "#8a96b0")],
             sky_night=[(0, "#262c46"), (5, "#1a2038"), (8, "#10142a"), (H, "#06081a")],
             fog_day={"L1": "#cfd2d8", "L2": "#d6d8da", "L3": "#d8d8d4", "L0": "#ffffff"},
             fog_night={"L1": "#262c48", "L2": "#232842", "L3": "#1c2038", "L0": "#000000"},
             clouds_day=("#eceef2", "#a6aec4"), n_clouds=8, cloud_v=(H * 0.6, H * 0.95),
             fog_L2=dict(haze=0.25, v0=0.0, v1=4.2, fmax=0.65))
    std_sky(ctx, S)
    ctx.night_lit = (0.62, 0.72, 0.95)
    P = P_with(hill=("#8e9a78", "#58646a"), stone=("#b6b4ac", "#6a6c7e"), stone_dark=("#7c8070", "#44485a"),
               cedar=("#4a6a5e", "#24383e"), deadwood=("#7a6e6c", "#3e3646"))
    far_ridges(ctx, S, [
        dict(y=900, D=9, crest=crest(81, 4.8, 1.6, 1, 8, 1.3), lit="#a8acbc", shade="#7c80a0", detail=0.3, haze=0.5),
        dict(y=650, D=7, crest=crest(82, 4.4, 1.0, 2, 10, 1.1), lit="#8c96a0", shade="#5e6880", detail=0.3, haze=0.4),
    ])
    fog_band(ctx, "L1", 600, 3.0, 3.0, 0.85)
    r = ctx.rng
    ced = [m_cedar(ctx, P, "L2", 2200 + i, h=4.2) for i in range(3)]
    back = crest(83, 4.0, 0.4, 1, 4)
    hill_trees(ctx, P, "L2", "back", 330, back, ced, 12, (0.5, 0.8), seed=83, jit=0.45)
    fog_band(ctx, "L2", 310, 2.8, 2.6, 0.7)
    gv = [m_grave(ctx, P, "L2", 2250 + i) for i in range(6)]
    for row, (y, V, n, sc) in enumerate([(290, 3.2, 22, 0.75), (260, 2.6, 14, 1.0)]):
        tr = const(V)
        ridge(ctx, "L2", "terr%d" % row, pal_mat(ctx, P, "hill", "L2"), y - 6, 6, tr, detail=0.0, gexp=3.0, seed=84 + row)
        scatter(ctx, "L2", "grave%d" % row, gv, jitter_xs(r, n, 0.3), y - 0.5, lambda x, V=V: V, (sc * 0.85, sc * 1.1), sink=0.05)
    dead = [m_dead_tree(ctx, P, "L2", 2300 + i, h=3.0) for i in range(3)]
    scatter(ctx, "L2", "dead", dead, jitter_xs(r, 4, 0.3), 280, lambda x: 3.0, (0.6, 0.9))
    lan = [m_lantern(ctx, P, "L2", 2350)]
    lx = [W * 0.15, W * 0.55, W * 0.82]
    scatter(ctx, "L2", "lan", lan, lx, 257, lambda x: 2.6, (0.7, 0.7), var=(1, 1))
    for x in lx:
        halo(ctx, "L2", x, 255, 3.35, 1.1, "#ffa65a")
    fog_band(ctx, "L2", 250, 0.0, 3.0, 0.65)
    bd = m_dead_tree(ctx, P, "L3", 2400, h=7.0, s=1.0)
    add_obj(ctx, "L3", "bigdead", bd, (W * 0.2, 60, z_at(-0.5, 60)), (0, 0, 0.3), 1.0)
    bd2 = m_dead_tree(ctx, P, "L3", 2401, h=6.0, s=1.0)
    add_obj(ctx, "L3", "bigdead2", bd2, (W * 0.74, 61, z_at(-0.5, 61)), (0, 0, -0.4), 1.0)
    gr = [m_grass(ctx, P, "L3", 2450 + i, n=36, h=1.2, spread=1.4, role="susuki", plume="susuki") for i in range(2)]
    scatter(ctx, "L3", "susuki", gr, jitter_xs(r, 4, 0.3), 62, lambda x: -0.1, (1.0, 1.3), sink=0)
    return dict(particles="mist", notes="overcast day; fog bands between grave rows")


def b_temple(ctx):
    S = dict(sky_day=[(0, "#f6eedc"), (4.5, "#e6efe6"), (7.5, "#a6d0ee"), (H, "#5ea2e0")],
             sky_night=[(0, "#2e3c6a"), (4.5, "#202c5a"), (8, "#121a3e"), (H, "#070a20")],
             fog_day={"L1": "#d4e2ea", "L2": "#e2eae4", "L3": "#e6ead8", "L0": "#ffffff"},
             fog_night={"L1": "#283868", "L2": "#22305a", "L3": "#1c264a", "L0": "#000000"}, n_clouds=5)
    std_sky(ctx, S)
    P = P_with(hill=("#8eb26a", "#527c5c"), plaster=("#f2ead8", "#a8a4bc"))
    far_ridges(ctx, S, [
        dict(y=900, D=9, crest=crest(91, 5.6, 2.2, 1, 9, 1.5, ridged=0.35), lit="#b0cae4", shade="#7a94c2", detail=0.3, haze=0.45),
        dict(y=650, D=7, crest=crest(92, 4.8, 1.2, 2, 12, 1.2), lit="#98b8c4", shade="#6684a6", detail=0.3, haze=0.35),
    ])
    fog_band(ctx, "L1", 600, 3.4, 2.0, 0.7)
    r = ctx.rng
    cx = W * 0.5
    hillc = lambda x: 3.0 + 2.2 * np.exp(-(pdist(x, cx) / 3.8) ** 2) + 0.25 * crest(93, 0, 1, 2, 6)(x)
    ced = [m_cedar(ctx, P, "L2", 2500 + i, h=4.6) for i in range(4)]
    ridge(ctx, "L2", "shrinehill", pal_mat(ctx, P, "hill", "L2"), 300, 8, hillc, detail=0.12, seed=93)
    xs = [x for x in jitter_xs(r, 20, 0.4) if pdist(x, cx) > 1.3]
    scatter(ctx, "L2", "ced", ced, xs, 306.5, lambda x: ridge_v(hillc, x, 0.8), (0.45, 0.75))
    top = float(hillc(np.array([cx]))[0])
    hall = m_house(ctx, P, "L2", 2550, w=3.2, d=2.0, h=1.0, rh=1.2, windows=3)
    add_obj(ctx, "L2", "shrine", hall, (cx, 308.5, z_at(top - 0.05, 308.5)), (0, 0, 0), 0.75)
    # stairs from foot to the hall, torii along them
    y0, y1 = 296.0, 307.0
    v0, v1 = 2.4, top - 0.15
    n = 22
    st = m_stairs(ctx, P, "L2", n=n, w=1.3, rise=((v1 - v0) / CP - (y1 - y0) * SP / CP) / n, run=(y1 - y0) / n)
    add_obj(ctx, "L2", "stairs", st, (cx, y0, z_at(v0, y0)), (0, 0, 0), 1.0, wrap=True)
    tor = m_torii(ctx, P, "L2", 2560, w=1.7, h=1.9)
    for k, f in enumerate((0.04, 0.5)):
        yy = y0 + (y1 - y0) * f
        vv = v0 + (v1 - v0) * f
        add_obj(ctx, "L2", "torii%d" % k, tor, (cx, yy, z_at(vv, yy)), (0, 0, 0), 0.8 - 0.12 * k)
    lan = [m_lantern(ctx, P, "L2", 2570)]
    for k, s in enumerate((-1, 1)):
        for j in range(3):
            f = 0.1 + j * 0.3
            yy, vv = y0 + (y1 - y0) * f, v0 + (v1 - v0) * f
            add_obj(ctx, "L2", "lan%d%d" % (k, j), lan[0], (cx + s * 1.0, yy, z_at(vv, yy)), (0, 0, 0), 0.5)
            halo(ctx, "L2", cx + s * 1.0, yy - 1, vv + 0.65, 0.8)
    fog_band(ctx, "L2", 290, 0.0, 2.6, 0.5)
    nc = [m_cedar(ctx, P, "L3", 2600 + i, h=10, s=1.3) for i in range(2)]
    scatter(ctx, "L3", "nced", nc, [W * 0.08, W * 0.9], 60, lambda x: -0.5, (1.0, 1.1), sink=0)
    nl = m_lantern(ctx, P, "L3", 2650, s=1.0)
    add_obj(ctx, "L3", "nlan", nl, (W * 0.2, 61, z_at(0.2, 61)), (0, 0, 0), 1.4)
    halo(ctx, "L3", W * 0.2, 60, 1.75, 2.2)
    return dict(particles="leaves" if not ctx.night else "fireflies", notes="stairs + 3 torii climbing the shrine hill")


def b_woods(ctx):
    S = dict(sky_day=[(0, "#e6efd6"), (5, "#d8e8d2"), (8, "#a8cce0"), (H, "#6aa4d4")],
             sky_night=[(0, "#1c3048"), (5, "#142440"), (8, "#0c1630"), (H, "#05091a")],
             fog_day={"L1": "#b4ccb8", "L2": "#9cb8a0", "L3": "#86a48c", "L0": "#ffffff"},
             fog_night={"L1": "#1c2e48", "L2": "#182840", "L3": "#132036", "L0": "#000000"}, n_clouds=4,
             fog_L2=dict(haze=0.2, v0=0.0, v1=4.0, fmax=0.6))
    std_sky(ctx, S)
    P = P_with(foliage=("#3e7448", "#183a34"), foliage2=("#548a4a", "#20463a"), trunk=("#4e3e38", "#221a22"),
               hill=("#3e6640", "#1e3a30"))
    far_ridges(ctx, S, [
        dict(y=900, D=9, crest=crest(101, 5.4, 1.8, 1, 9, 1.3), lit="#8ab0a2", shade="#5c8088", detail=0.3, haze=0.45),
        dict(y=650, D=7, crest=crest(102, 4.9, 1.2, 2, 14, 1.0), lit="#6c9a7e", shade="#3e6a68", detail=0.4, haze=0.35),
    ])
    fog_band(ctx, "L1", 600, 3.6, 2.0, 0.7)
    r = ctx.rng
    for row, (y, V, n, sc) in enumerate([(330, 4.0, 18, (0.75, 1.0)), (280, 3.0, 14, (0.95, 1.25)), (240, 1.6, 10, (1.2, 1.5))]):
        tm = [m_round_tree(ctx, P, "L2", 2700 + row * 10 + i, fol=("foliage" if (i + row) % 2 else "foliage2"), crown=1.3, h=2.2, blobs=13) for i in range(3)]
        cr = crest(103 + row, V, 0.3, 1, 5)
        hill_trees(ctx, P, "L2", "wrow%d" % row, y, cr, tm, n, sc, seed=103 + row, jit=0.45)
        fog_band(ctx, "L2", y - 6, 0.0, V + 1.6, 0.55)
    trunks = [m_round_tree(ctx, P, "L3", 2800 + i, fol="foliage", crown=2.8, h=8.6, blobs=16) for i in range(2)]
    scatter(ctx, "L3", "ntree", trunks, [W * 0.15, W * 0.62], 60, lambda x: -0.6, (1.0, 1.1), sink=0)
    bush = [m_round_tree(ctx, P, "L3", 2850 + i, fol="foliage2", crown=0.9, h=0, blobs=7, trunk=None) for i in range(2)]
    scatter(ctx, "L3", "bush", bush, jitter_xs(r, 7, 0.35), 64, lambda x: -0.1, (0.9, 1.3), sink=0)
    return dict(particles="leaves" if not ctx.night else "fireflies", notes="three canopy rows separated by mist")


def b_spikes(ctx):
    S = dict(sky_day=[(0, "#f4c890"), (4, "#eab48a"), (7, "#a0a4c0"), (H, "#5c6c9e")],
             sky_night=[(0, "#3a2a4a"), (4, "#2a2046"), (8, "#141432"), (H, "#07071a")],
             fog_day={"L1": "#d8b8a4", "L2": "#d6c0a6", "L3": "#d2c0a0", "L0": "#ffffff"},
             fog_night={"L1": "#2e2844", "L2": "#2a2640", "L3": "#221e36", "L0": "#000000"},
             clouds_day=("#fff0dc", "#b49ab4"), sun="#fff0c8", sun_halo="#ffd8a0", sun_pos=(W * 0.3, H * 0.55),
             streak_day="#ffe2c0", n_clouds=5)
    std_sky(ctx, S)
    ctx.night_lit = (0.72, 0.62, 0.95)
    P = P_with(bamboo=("#a8b860", "#5a6c4c"), bamboo_leaf=("#7a9a4c", "#3e5a40"), hill=("#9a9a62", "#5e5a50"))
    far_ridges(ctx, S, [
        dict(y=900, D=9, crest=crest(111, 5.0, 2.2, 2, 14, 1.8, ridged=0.5), lit="#b49cb0", shade="#76668e", detail=0.35, haze=0.45),
        dict(y=650, D=7, crest=crest(112, 4.4, 1.4, 3, 16, 1.5, ridged=0.4), lit="#9a8a98", shade="#5c5272", detail=0.35, haze=0.35),
    ])
    fog_band(ctx, "L1", 600, 3.4, 1.8, 0.65)
    r = ctx.rng
    for ri, (y, n, rad) in enumerate([(320, 16, 0.06), (270, 11, 0.08)]):
        ms = [m_bamboo(ctx, P, "L2", 2900 + ri * 10 + k, h=15, rad=rad, leaves=8, leaf_z=(0.5, 1.0), lean=r.uniform(-0.06, 0.06)) for k in range(3)]
        for i, x in enumerate(jitter_xs(r, n, 0.45)):
            yy = y + r.uniform(-3, 3)
            add_obj(ctx, "L2", "sb%d_%d" % (ri, i), ms[i % 3], (x, yy, z_at(1.5, yy)), (0, 0, r.uniform(0, TAU)), 1.0)
        fog_band(ctx, "L2", y - 8, 0.0, 5.0, 0.55)
    sp = [m_spikes(ctx, P, "L2", 2950 + i, n=11, h=1.4, spread=1.2) for i in range(3)]
    ridge(ctx, "L2", "ground", pal_mat(ctx, P, "hill", "L2"), 246, 6, const(2.6), detail=0.0, gexp=3.0, seed=113)
    scatter(ctx, "L2", "spk", sp, jitter_xs(r, 9, 0.2), 250, lambda x: 2.6, (0.7, 0.9), sink=0.05)
    fog_band(ctx, "L2", 244, 0.0, 2.4, 0.5)
    sp3 = [m_spikes(ctx, P, "L3", 3000 + i, n=8, h=2.6, spread=1.4) for i in range(2)]
    scatter(ctx, "L3", "nspk", sp3, [W * 0.1, W * 0.45, W * 0.75], 60, lambda x: 0.0, (1.0, 1.2), sink=0)
    ms = [m_bamboo(ctx, P, "L3", 3050 + k, h=16, rad=0.18, leaves=10, lean=r.uniform(-0.05, 0.05)) for k in range(2)]
    for i, x in enumerate([W * 0.3, W * 0.92]):
        add_obj(ctx, "L3", "nbam%d" % i, ms[i % 2], (x, 62, z_at(-1.0, 62)), (0, 0, r.uniform(0, TAU)), 1.0)
    return dict(particles="leaves", notes="dusk-tinted day for tension; takeyari stake fences")


def b_cliffs(ctx):
    S = dict(sky_day=[(0, "#f6e8d0"), (4.5, "#eee6d6"), (7.5, "#a6cceb"), (H, "#5e9ede")],
             sky_night=[(0, "#34385e"), (4.5, "#242a56"), (8, "#131a3e"), (H, "#070a20")],
             fog_day={"L1": "#e2d6cc", "L2": "#e6dace", "L3": "#e6d8c8", "L0": "#ffffff"},
             fog_night={"L1": "#2c3060", "L2": "#282c58", "L3": "#20244a", "L0": "#000000"}, n_clouds=5)
    std_sky(ctx, S)
    P = P_with(rock=("#c8a486", "#7a5c6a"), rock2=("#b89478", "#6a5068"), hill=("#a2a86a", "#64705a"))
    far_ridges(ctx, S, [
        dict(y=900, D=10, crest=mesa(121, 5.6, 2.8, 2, 8, cut=0.4), lit="#d8c0b4", shade="#9c8aa6", detail=0.2, stri=0.5, haze=0.45),
        dict(y=650, D=9, crest=mesa(122, 4.6, 2.6, 2, 9, cut=0.45, k=4), lit="#c8a894", shade="#88708e", detail=0.2, stri=0.6, haze=0.35),
    ])
    fog_band(ctx, "L1", 600, 3.0, 2.2, 0.7)
    r = ctx.rng
    pil = mesa(123, 2.6, 4.2, 2, 7, cut=0.5, k=5)
    ridge(ctx, "L2", "pillars", pal_mat(ctx, P, "rock", "L2", thr=0.46, soft=0.03), 280, 6, pil, detail=0.12, stri=0.7, seed=123, gexp=1.1)
    pines = [m_pine(ctx, P, "L2", 3100 + i, h=2.0) for i in range(3)]
    xs = np.linspace(0, W, 400, endpoint=False)
    vv = pil(xs)
    tops = [float(x) for x, v in zip(xs, vv) if v > 6.0][::25]
    scatter(ctx, "L2", "tpine", pines, tops, 285.5, lambda x: pil(np.array([x]))[0], (0.4, 0.6))
    fog_band(ctx, "L2", 274, 0.0, 4.0, 0.7)
    low = crest(124, 2.2, 0.7, 2, 10, 1.4, ridged=0.4)
    ridge(ctx, "L2", "talus", pal_mat(ctx, P, "rock2", "L2", thr=0.46, soft=0.03), 250, 5, low, detail=0.2, stri=0.3, seed=124)
    fog_band(ctx, "L2", 246, 0.0, 2.0, 0.45)
    sp = m_rock(ctx, P, "L3", 3150, 1.0)
    c = lambda x: -0.5 + 7.5 * np.clip(1 - pdist(x, W * 0.08) / 2.2, 0, 1) ** 0.5
    ridge(ctx, "L3", "spire", pal_mat(ctx, P, "rock", "L3", thr=0.46, soft=0.03, grad=0.0), 55, 4, c, detail=0.15, stri=0.5, seed=125, gexp=1.1)
    add_obj(ctx, "L3", "rock", sp, (W * 0.6, 61, z_at(0.3, 61)), (0, 0, 0), 1.6)
    npn = m_pine(ctx, P, "L3", 3160, h=3.5, s=1.2)
    add_obj(ctx, "L3", "npine", npn, (W * 0.08, 58.6, z_at(ridge_v(c, W * 0.08, 0.9) - 0.3, 58.6)), (0, 0, 0), 0.9)
    return dict(particles="mist", notes="mesa/pillar crests with striation displacement for rock faces")


def b_valley(ctx):
    S = dict(sky_day=[(0, "#f8eed8"), (4.5, "#f0ecdc"), (7.5, "#a8d0ee"), (H, "#62a2e0")],
             sky_night=[(0, "#323e6c"), (4.5, "#222e5c"), (8, "#121a40"), (H, "#060a20")],
             fog_day={"L1": "#dee4e4", "L2": "#e2e8de", "L3": "#e4e8d6", "L0": "#ffffff"},
             fog_night={"L1": "#2a386a", "L2": "#26325e", "L3": "#1e284e", "L0": "#000000"}, n_clouds=6)
    std_sky(ctx, S)
    P = P_with(hill=("#86b06c", "#4c7a62"), hill2=("#6e9e66", "#3c6a5c"))
    rows = []
    cols = [("#c4d4e6", "#94a8cc"), ("#a8c2da", "#7690bc"), ("#94b4c4", "#6382a6"), ("#86aab0", "#557a92")]
    for i in range(4):
        rows.append(dict(y=1000 - i * 150, D=8, crest=crest(131 + i, 6.6 - i * 0.65, 1.5, 1, 9 + i * 2, 1.3, ridged=0.2),
                         lit=cols[i][0], shade=cols[i][1], detail=0.3, haze=0.5 - i * 0.07))
    far_ridges(ctx, S, rows)
    for i in range(3):
        fog_band(ctx, "L1", 920 - i * 150, 4.2 - i * 0.5, 1.6, 0.6)
    r = ctx.rng
    ced = [m_cedar(ctx, P, "L2", 3200 + i, h=3.2) for i in range(3)]
    rt = [m_round_tree(ctx, P, "L2", 3220 + i, fol=("maple" if i == 0 else ("ginkgo" if i == 1 else "foliage")), crown=0.8, h=0.8) for i in range(3)]
    a = lambda x: 3.6 + 1.6 * np.clip(1 - pdist(x, W * 0.2) / 7, 0, 1) ** 1.3 + 0.3 * crest(135, 0, 1, 3, 12)(x)
    hill_trees(ctx, P, "L2", "vA", 300, a, ced + rt, 30, (0.35, 0.55), role="hill2", seed=135, t=0.9)
    fog_band(ctx, "L2", 292, 1.6, 2.6, 0.6)
    b = lambda x: 2.4 + 1.4 * np.clip(1 - pdist(x, W * 0.7) / 6, 0, 1) ** 1.3 + 0.2 * crest(136, 0, 1, 3, 10)(x)
    hill_trees(ctx, P, "L2", "vB", 250, b, rt + ced, 22, (0.5, 0.7), seed=136, t=0.9)
    fog_band(ctx, "L2", 244, 0.0, 2.4, 0.5)
    c = lambda x: -0.5 + 3.4 * np.clip(1 - pdist(x, W * 0.05) / 4.0, 0, 1) ** 1.2
    tm = [m_cedar(ctx, P, "L3", 3250 + i, h=5.0, s=1.0) for i in range(2)]
    ridge(ctx, "L3", "slope", pal_mat(ctx, P, "hill", "L3"), 55, 5, c, detail=0.1, seed=137)
    xs = [W * 0.92, W * 0.97, W * 0.02, W * 0.07, W * 0.12]
    scatter(ctx, "L3", "sced", tm, xs, 59, lambda x: ridge_v(c, x, 0.8), (0.9, 1.1))
    return dict(particles="mist", notes="four atmospheric ridge rows (classic layered-valley look)")


def b_village(ctx):
    S = dict(sky_day=[(0, "#f8ecd4"), (4.5, "#eeebd8"), (7.5, "#a6cfee"), (H, "#60a2e0")],
             sky_night=[(0, "#323c68"), (4.5, "#222c5a"), (8, "#121a3e"), (H, "#060a20")],
             fog_day={"L1": "#dae2e4", "L2": "#e6e6d6", "L3": "#e8e4d0", "L0": "#ffffff"},
             fog_night={"L1": "#283666", "L2": "#24305a", "L3": "#1e284c", "L0": "#000000"}, n_clouds=6)
    std_sky(ctx, S)
    P = P_with(hill=("#9ab86a", "#5a845c"), fruit=("#f2803a", "#a84a3a"), wood_wall=("#a27a56", "#5a4448"))
    far_ridges(ctx, S, [
        dict(y=900, D=9, crest=crest(141, 5.4, 1.8, 1, 9, 1.4, ridged=0.25), lit="#b2cce4", shade="#7c96c2", detail=0.3, haze=0.45),
        dict(y=650, D=7, crest=crest(142, 4.8, 1.2, 2, 12, 1.2), lit="#9abcb8", shade="#6886a2", detail=0.3, haze=0.35),
    ])
    fog_band(ctx, "L1", 600, 3.4, 1.8, 0.65)
    r = ctx.rng
    bk = crest(143, 4.2, 0.7, 1, 5)
    trees = [m_round_tree(ctx, P, "L2", 3300 + i, fol=("foliage" if i % 2 else "maple"), crown=0.9, h=1.0) for i in range(3)]
    hill_trees(ctx, P, "L2", "vhill", 320, bk, trees, 16, (0.5, 0.7), seed=143)
    ground = const(2.4)
    ridge(ctx, "L2", "vground", pal_mat(ctx, P, "hill", "L2"), 262, 14, ground, detail=0.0, gexp=3.0, seed=144)
    hs = [m_house(ctx, P, "L2", 3350 + i, w=r.uniform(2.4, 3.0), d=1.8, h=0.85, rh=0.85, style="thatch", windows=2) for i in range(3)]
    hx = [W * 0.08, W * 0.36, W * 0.6, W * 0.84]
    for i, x in enumerate(hx):
        yy = 272 + (i % 2) * 2
        add_obj(ctx, "L2", "vh%d" % i, hs[i % 3], (x, yy, z_at(2.45, yy)), (0, 0, r.uniform(-0.08, 0.08)), r.uniform(0.7, 0.8))
        halo(ctx, "L2", x, yy - 2, 3.0, 1.3)
    pm = [m_persimmon(ctx, P, "L2", 3380 + i, crown=1.0) for i in range(2)]
    scatter(ctx, "L2", "vpers", pm, [W * 0.22, W * 0.48, W * 0.73, W * 0.95], 268, lambda x: 2.45, (0.7, 0.9))
    fence = m_fence(ctx, P, "L2", 0.5)
    add_obj(ctx, "L2", "fence", fence, (0, 263, z_at(2.42, 263)), wrap=False)
    fog_band(ctx, "L2", 260, 0.0, 2.4, 0.5)
    big = m_persimmon(ctx, P, "L3", 3400, crown=2.2)
    add_obj(ctx, "L3", "bigpers", big, (W * 0.86, 60, z_at(0.2, 60)), (0, 0, 0), 0.95)
    mb = MB()
    st = pal_mat(ctx, P, "stone", "L3", grad=0.3, thr=0.44, soft=0.04)
    for i in range(30):
        mb.ico(st, (W * 0.05 + i * 0.22 + r.uniform(-0.05, 0.05), 0, r.uniform(0.0, 0.5)), r.uniform(0.18, 0.28), sc=(1.3, 1, 0.9), sub=1, smooth=False)
    add_obj(ctx, "L3", "stonewall", mb.mesh(ctx.prefix + "_swall"), (0, 62, z_at(0.4, 62)), wrap=True)
    return dict(particles="leaves" if not ctx.night else "fireflies", notes="thatched minka with persimmon trees")


def b_orochi(ctx):
    S = dict(sky_day=[(0, "#e88a5a"), (3.5, "#c0566a"), (7, "#6a3a6a"), (H, "#2e1e44")],
             sky_night=[(0, "#4a1428"), (3.5, "#2e0c26"), (7, "#16081c"), (H, "#07030c")],
             fog_day={"L1": "#a8607a", "L2": "#9a5470", "L3": "#86486a", "L0": "#ffffff"},
             fog_night={"L1": "#3a1630", "L2": "#32122a", "L3": "#280e24", "L0": "#000000"},
             clouds_day=("#e6a0a0", "#6a3a62"), clouds_night=("#a06070", "#401830"),
             sun="#ffe0b0", sun_halo="#ff9a6a", sun_pos=(W * 0.66, H * 0.42),
             moon="#ff8a6a", moon_halo="#c03a4a", moon_pos=(W * 0.3, H * 0.74),
             streak_day="#ffb4a0", streak_night="#a04050", n_clouds=7)
    std_sky(ctx, S)
    ctx.night_lit = (0.95, 0.55, 0.85)
    ctx.night_shade = (0.45, 0.22, 0.55)
    P = P_with(hill=("#6a4a5e", "#33223e"), deadwood=("#6a5058", "#2e2030"), vermilion=("#c03a32", "#5e1a2e"),
               torii_top=("#2a1e26", "#140c16"), stone=("#8a7e86", "#4a3e52"), stone_dark=("#5e5260", "#2e2638"),
               rock=("#7a5e6e", "#3a2840"))
    far_ridges(ctx, S, [
        dict(y=900, D=9, crest=crest(151, 4.8, 2.8, 2, 16, 2.0, ridged=0.6), lit="#8a5a7e", shade="#4e3058", detail=0.4, haze=0.4),
        dict(y=650, D=7, crest=crest(152, 4.2, 1.8, 3, 18, 1.8, ridged=0.6), lit="#6e4466", shade="#3a2246", detail=0.4, haze=0.3),
    ])
    fog_band(ctx, "L1", 600, 3.0, 2.2, 0.75)
    r = ctx.rng
    gnd = crest(153, 2.8, 0.5, 1, 6)
    ridge(ctx, "L2", "ground", pal_mat(ctx, P, "hill", "L2"), 270, 8, gnd, detail=0.15, seed=153)
    # serpent: arching coils emerging from the ground + raised head
    sm = toon(ctx, "serpent", "#7a4a7e", "#2a1430", "L2", noise=0.18, nscale=6.0, grad=0.0, thr=0.42, soft=0.04)
    pts, rad = [], []
    x0, x1 = W * 0.05, W * 0.82
    yy = 276.0
    for i in range(120):
        u = i / 119
        x = x0 + (x1 - x0) * u
        arch = math.sin(u * math.pi * 3.0)
        V = 2.6 + 2.0 * arch
        pts.append(Vector((x, yy + 1.0 * math.cos(u * 9), z_at(V, yy))))
        rad.append(0.42 * (0.55 + 0.45 * math.sin(math.pi * min(1, u * 1.2))))
    body = m_tube(ctx, ctx.prefix + "_serpent", pts, rad, sm, segs=14)
    add_obj(ctx, "L2", "serpent", body, wrap=True)
    hx = W * 0.88
    neck = [Vector((x1, yy, z_at(1.8, yy))), Vector((hx - 0.3, yy, z_at(3.6, yy))), Vector((hx, yy, z_at(5.4, yy))), Vector((hx - 0.4, yy, z_at(6.4, yy)))]
    fine = []
    for i in range(30):
        t = i / 29 * 3
        k = min(int(t), 2)
        fine.append(neck[k].lerp(neck[k + 1], t - k))
    nk = m_tube(ctx, ctx.prefix + "_neck", fine, [0.42 - 0.08 * i / 29 for i in range(30)], sm, segs=14)
    add_obj(ctx, "L2", "neck", nk, wrap=True)
    mb = MB()
    mb.cone(sm, (hx - 0.4, yy, z_at(6.3, yy)), (hx - 1.6, yy, z_at(6.0, yy)), 0.42, 0.12, segs=12)
    eye = glow_mat(ctx, "eye", "#ff3a2a", 2.5, "L2") if ctx.night else toon(ctx, "eye_d", "#ffd040", "#c06020", "L2", grad=0.0)
    mb.ico(eye, (hx - 0.75, yy - 0.38, z_at(6.42, yy)), 0.09, sub=2)
    add_obj(ctx, "L2", "head", mb.mesh(ctx.prefix + "_head"), wrap=True)
    halo(ctx, "L2", hx - 0.75, yy - 1, 6.42, 0.9, "#ff4a3a", peak=0.8)
    tor = m_torii(ctx, P, "L2", 3500, w=2.0, h=2.2)
    for k, (x, tilt) in enumerate(((W * 0.3, 0.0), (W * 0.52, 0.14), (W * 0.66, -0.08))):
        add_obj(ctx, "L2", "torii%d" % k, tor, (x, 268 + k, z_at(2.5, 268 + k)), (0, tilt, 0), 0.7 - 0.05 * k)
    dead = [m_dead_tree(ctx, P, "L2", 3550 + i, h=3.2) for i in range(3)]
    scatter(ctx, "L2", "dead", dead, jitter_xs(r, 6, 0.3), 280, lambda x: gnd(np.array([x]))[0] + 0.6, (0.5, 0.8))
    lan = [m_lantern(ctx, P, "L2", 3560)]
    for x in (W * 0.22, W * 0.44, W * 0.95):
        add_obj(ctx, "L2", "lan", lan[0], (x, 264, z_at(2.45, 264)), (0, 0, 0), 0.6)
        halo(ctx, "L2", x, 262, 3.1, 1.0, "#ff7a4a")
    fog_band(ctx, "L2", 262, 0.0, 2.8, 0.65)
    bd = m_dead_tree(ctx, P, "L3", 3600, h=7.5)
    add_obj(ctx, "L3", "bigdead", bd, (W * 0.3, 60, z_at(-0.5, 60)), (0, 0, 0.5), 1.0)
    rk = [m_rock(ctx, P, "L3", 3610 + i, 1.4) for i in range(2)]
    scatter(ctx, "L3", "nrock", rk, [W * 0.08, W * 0.7], 61, lambda x: 0.2, (1.0, 1.3), sink=0)
    sp = m_spikes(ctx, P, "L3", 3620, n=6, h=2.2, spread=0.9)
    add_obj(ctx, "L3", "nspk", sp, (W * 0.84, 62, z_at(0.0, 62)), (0, 0, 0), 1.0)
    return dict(particles="mist", notes="serpent body is a tube mesh arching out of the ground; eyes glow at night")


BIOMES = {
    "school": b_school, "sakura": b_sakura, "bamboo": b_bamboo, "waterfall": b_waterfall, "fields": b_fields,
    "river": b_river, "graveyard": b_graveyard, "temple": b_temple, "woods": b_woods, "spikes": b_spikes,
    "cliffs": b_cliffs, "valley": b_valley, "village": b_village, "orochi": b_orochi,
}


# ----------------------------------------------------------------------------- render
def seam_check(path):
    img = bpy.data.images.load(path)
    w, h = img.size
    px = np.empty(w * h * 4, dtype=np.float32)
    img.pixels.foreach_get(px)
    a = px.reshape(h, w, 4)
    rgb = a[..., :3] * a[..., 3:4]
    rgba = np.concatenate([rgb, a[..., 3:4]], -1)
    edge = float(np.abs(rgba[:, 0] - rgba[:, -1]).mean())
    # neighbouring column steps on both sides of the wrap (what a real seam must be compared with)
    band = np.concatenate([rgba[:, -9:], rgba[:, :9]], 1)
    steps = np.abs(np.diff(band, axis=1)).mean(axis=(0, 2))
    local = float(np.delete(steps, 8).max())
    bpy.data.images.remove(img)
    return dict(edge=round(edge, 5), local_max=round(local, 5), ok=bool(edge <= 1.5 * local + 0.002))


def render_layers(ctx, outdir, res_scale):
    os.makedirs(outdir, exist_ok=True)
    sc = ctx.scene
    results = {}
    for i, L in enumerate(LAYERS):
        for L2, c in ctx.colls.items():
            c.hide_render = (L2 != L)
        if L == "L0":
            sc.render.film_transparent = False
            sc.render.resolution_x, sc.render.resolution_y = int(1024 * res_scale), int(576 * res_scale)
        else:
            sc.render.film_transparent = True
            sc.render.resolution_x, sc.render.resolution_y = int(2048 * res_scale), int(1152 * res_scale)
        path = os.path.join(outdir, "layer%d.png" % i)
        sc.render.filepath = path
        t = time.time()
        bpy.ops.render.render(write_still=True, scene=sc.name)
        results["layer%d" % i] = dict(seconds=round(time.time() - t, 2), seam=seam_check(path),
                                      size=[sc.render.resolution_x, sc.render.resolution_y])
    for c in ctx.colls.values():
        c.hide_render = False
    return results


def build(biome, lighting, res_scale, samples, seed):
    sc = new_scene("%s_%s" % (biome, lighting), res_scale, samples)
    ctx = Ctx(biome, lighting, sc, seed)
    add_sun(ctx, (0.55, 0.6, -0.6) if lighting == "day" else (-0.55, 0.6, -0.6))
    for L in LAYERS:
        coll(ctx, L)
    info = BIOMES[biome](ctx) or {}
    nd = wrap_duplicates(ctx)
    return ctx, info, nd


def main():
    argv = sys.argv[sys.argv.index("--") + 1:] if "--" in sys.argv else []
    import argparse
    ap = argparse.ArgumentParser()
    ap.add_argument("--biome", nargs="+", default=["school"])
    ap.add_argument("--lighting", nargs="+", default=["day", "night"])
    ap.add_argument("--out", default="art/env")
    ap.add_argument("--samples", type=int, default=48)
    ap.add_argument("--scale", type=float, default=1.0)
    ap.add_argument("--blend", default=None)
    ap.add_argument("--no-render", action="store_true")
    ap.add_argument("--seed", type=int, default=7)
    a = ap.parse_args(argv)
    biomes = list(BIOMES) if a.biome == ["all"] else a.biome
    root = os.path.abspath(a.out)
    for b in biomes:
        for li in a.lighting:
            t0 = time.time()
            ctx, info, nd = build(b, li, a.scale, a.samples, a.seed + hash(b) % 1000)
            tb = time.time() - t0
            print("[env] built %s/%s in %.1fs (%d objects, %d wrap dups)" % (b, li, tb, len(ctx.scene.objects), nd))
            if a.no_render:
                continue
            outdir = os.path.join(root, b, li)
            res = render_layers(ctx, outdir, a.scale)
            fogs = {L: to_hex(ctx.fog[L]["color"]) for L in LAYERS}
            fogs["L0"] = ctx.meta.get("horizon", fogs["L0"])
            meta = dict(
                biome=b, lighting=li, generator="scripts/blender_env_backgrounds.py", blender=bpy.app.version_string,
                engine=ctx.scene.render.engine, samples=a.samples, res_scale=a.scale,
                layers=[dict(file="layer%d.png" % i, parallax=PARALLAX[L], size=res["layer%d" % i]["size"],
                             transparent=(L != "L0"), tileable_x=True, seam_check=res["layer%d" % i]["seam"],
                             render_seconds=res["layer%d" % i]["seconds"]) for i, L in enumerate(LAYERS)],
                fog_colors=fogs, tint=info.get("tint_hex", fogs["L2"]), sky=dict(horizon=ctx.meta.get("horizon"), zenith=ctx.meta.get("zenith")),
                ambient_particles=info.get("particles", "none"),
                calm_bottom_fraction=0.25, build_seconds=round(tb, 2),
                notes=info.get("notes", ""),
            )
            with open(os.path.join(outdir, "meta.json"), "w") as f:
                json.dump(meta, f, indent=2)
            print("[env] rendered %s/%s %s" % (b, li, json.dumps({k: (v["seconds"], v["seam"]["ok"]) for k, v in res.items()})))
    if a.blend:
        for s in list(bpy.data.scenes):
            if s.name == "Scene" and len(bpy.data.scenes) > 1:
                bpy.data.scenes.remove(s)
        txt = bpy.data.texts.new("blender_env_backgrounds.py")
        txt.from_string(open(os.path.abspath(__file__) if os.path.exists(__file__) else sys.argv[sys.argv.index("--python") + 1]).read())
        os.makedirs(os.path.dirname(os.path.abspath(a.blend)), exist_ok=True)
        bpy.ops.file.pack_all()
        bpy.ops.wm.save_as_mainfile(filepath=os.path.abspath(a.blend), compress=True)
        print("[env] saved", a.blend)


if __name__ == "__main__":
    main()
