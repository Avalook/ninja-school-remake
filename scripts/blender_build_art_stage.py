"""Build a portable Blender texture stage; run with Blender --background --python."""

import argparse
import json
import sys
from pathlib import Path

import bpy


def image_plane(name, source, position, width, height):
    image = bpy.data.images.load(str(source), check_existing=True)
    bpy.ops.mesh.primitive_plane_add(size=2, location=position)
    plane = bpy.context.object
    plane.name = name
    plane.scale = (width / 2, height / 2, 1)
    material = bpy.data.materials.new(name + "Material")
    material.use_nodes = True
    nodes = material.node_tree.nodes
    nodes.clear()
    texture = nodes.new("ShaderNodeTexImage")
    texture.image = image
    emission = nodes.new("ShaderNodeEmission")
    transparent = nodes.new("ShaderNodeBsdfTransparent")
    mix = nodes.new("ShaderNodeMixShader")
    output = nodes.new("ShaderNodeOutputMaterial")
    links = material.node_tree.links
    links.new(texture.outputs["Color"], emission.inputs["Color"])
    links.new(texture.outputs["Alpha"], mix.inputs[0])
    links.new(transparent.outputs[0], mix.inputs[1])
    links.new(emission.outputs[0], mix.inputs[2])
    links.new(mix.outputs[0], output.inputs["Surface"])
    plane.data.materials.append(material)
    return plane


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--project-root", required=True, type=Path)
    args = parser.parse_args(sys.argv[sys.argv.index("--") + 1:])
    root = args.project_root.resolve()
    output = root / "art/blender"
    output.mkdir(parents=True, exist_ok=True)
    inputs = [root / "art/generated/ninja1-mountain-layer-v1.png",
              root / "art/generated/ninja1-hero-cloud-v1.png"]
    for source in inputs:
        if not source.is_file():
            raise FileNotFoundError(source)
    bpy.ops.object.select_all(action="SELECT")
    bpy.ops.object.delete(use_global=False)
    image_plane("DistantMountainTextureLayer", inputs[0], (0, 0, -1), 16, 8)
    image_plane("HeroIdentityTextureNotRigged", inputs[1], (-2.5, -0.5, 0), 6, 6)
    bpy.ops.object.camera_add(location=(0, 0, 12))
    camera = bpy.context.object
    camera.name = "StagePreviewCamera"
    camera.data.type = "ORTHO"
    camera.data.ortho_scale = 14
    scene = bpy.context.scene
    scene.camera = camera
    scene.render.engine = "CYCLES"
    scene.cycles.samples = 8
    scene.cycles.device = "CPU"
    scene.render.resolution_x = 1280
    scene.render.resolution_y = 720
    scene.render.resolution_percentage = 100
    scene.render.image_settings.file_format = "PNG"
    scene.render.filepath = "//art-stage-preview.png"
    scene.view_settings.view_transform = "Standard"
    scene["scope"] = "Texture-stage harness check only; no character rig, gameplay or collision."
    bpy.ops.file.pack_all()
    bpy.ops.wm.save_as_mainfile(filepath=str(output / "ninja1-art-stage.blend"))
    bpy.ops.render.render(write_still=True)
    (output / "connection-check.json").write_text(json.dumps({
        "status": "passed", "blender_version": bpy.app.version_string,
        "blend_saved": True, "render_completed": True,
        "render_size": [1280, 720], "inputs": [str(p.relative_to(root)) for p in inputs],
        "scope": "CLI/Python harness connected; texture planes only, not rigged 3D characters",
    }, indent=2))


if __name__ == "__main__":
    main()
