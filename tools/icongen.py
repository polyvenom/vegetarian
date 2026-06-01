"""
Compose the Modrinth icon for The Vegetarian.

Usage:
    python tools/icongen.py

Reads textures from:
    - src/main/resources/assets/vegetarian/textures/item/ (user-drawn)
    - vanilla item textures expected at vanilla_textures/ (see VANILLA_DIR below)

Writes:
    icon.png (256x256, RGBA)
    src/main/resources/assets/vegetarian/icon.png (used by fabric.mod.json)
"""
from PIL import Image, ImageDraw
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
MOD_TEX = ROOT / "src/main/resources/assets/vegetarian/textures/item"
VANILLA_DIR = Path(__file__).resolve().parent / "vanilla_textures"
OUT = ROOT / "icon.png"
OUT_MOD = ROOT / "src/main/resources/assets/vegetarian/icon.png"

SIZE = 256
TILE = 64            # final size of each item in the grid (16x16 source * 4x)
GAP = 16             # gap between tiles AND outer margin (3*TILE + 4*GAP = 256)
BG = (54, 74, 38)    # deep forest green
FRAME = (28, 38, 20) # darker green for the border
BORDER_RADIUS = 24

# 3x3 grid: each slot is (relative_path, is_mod_texture)
# Center = brown_mushroom (the staple input). Corners + edges = outputs.
GRID = [
    ("feather.png",                    False), ("ink_bottle.png",         True),  ("leather.png",                False),
    ("fungal_mash.png",                True),  ("brown_mushroom.png",     False), ("cured_mycelium.png",         True),
    ("raw_improbable_steak.png",       True),  ("cooked_improbable_bacon.png", True), ("glow_ink_bottle.png",     True),
]


def load_texture(name: str, is_mod: bool) -> Image.Image:
    base = MOD_TEX if is_mod else VANILLA_DIR
    img = Image.open(base / name).convert("RGBA")
    return img.resize((TILE, TILE), Image.NEAREST)


def make_icon() -> Image.Image:
    icon = Image.new("RGBA", (SIZE, SIZE), (0, 0, 0, 0))
    draw = ImageDraw.Draw(icon)

    # Outer rounded background
    draw.rounded_rectangle((0, 0, SIZE - 1, SIZE - 1), radius=BORDER_RADIUS, fill=FRAME)
    # Inner panel
    pad = 8
    draw.rounded_rectangle(
        (pad, pad, SIZE - 1 - pad, SIZE - 1 - pad),
        radius=BORDER_RADIUS - 6,
        fill=BG,
    )

    # 3x3 grid
    for i, (name, is_mod) in enumerate(GRID):
        col, row = i % 3, i // 3
        x = GAP + col * (TILE + GAP)
        y = GAP + row * (TILE + GAP)
        try:
            tex = load_texture(name, is_mod)
            icon.paste(tex, (x, y), tex)
        except FileNotFoundError as e:
            print(f"!! Missing texture: {e.filename}")

    return icon


if __name__ == "__main__":
    img = make_icon()
    img.save(OUT)
    OUT_MOD.parent.mkdir(parents=True, exist_ok=True)
    img.save(OUT_MOD)
    print(f"Wrote {OUT} and {OUT_MOD}")
