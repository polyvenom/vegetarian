# The Vegetarian

A small Fabric mod for Minecraft 26.1.2 that gives you a plant-based alternative to every passive-mob drop. Pluck chickens for feathers, bottle squids for ink, grow leather from mushrooms, and cook Improbable Meats — all without killing a single cow, pig, or chicken.

Companion mod: [**The Pacifist**](https://github.com/polyvenom/pacifist), which handles hostile-mob drops the same way. Use either alone or together.

## What it does

### Pluck a chicken

Right-click a chicken with an empty main hand. It drops 1-3 feathers, plays a snip sound, and goes on a 5-minute cooldown. Repeat. No harm done.

### Bottle a squid

Right-click any squid or glow squid while holding glass bottles. Consumes 1-3 bottles based on RNG and your stack size, fills them with **Ink Bottles** or **Glow Ink Bottles** — new items that act as drop-in replacements for ink sacs and glow ink sacs in every vanilla recipe (writable book, glow item frame, black dye). 5-minute cooldown per squid.

### Mycelium leather

A two-step path to leather without touching a cow:

1. **Fungal Mash** — craft 2 brown mushroom + 1 dirt + 1 bone meal in any pattern
2. **Cured Mycelium** — smelt or smoke the mash
3. **Leather** — 2 cured mycelium side-by-side in a crafting grid

### Improbable Meats

Three mushroom-based meat analogues, each with a raw and a cooked variant. Cooked stats match vanilla equivalents exactly.

| Meat | Raw recipe | Cooked stats |
| --- | --- | --- |
| **Improbable Nuggets** | Brown mushroom + wheat seeds | 6 hunger / 7.2 saturation (= cooked chicken) |
| **Improbable Bacon** | Brown mushroom + beetroot | 8 hunger / 12.8 saturation (= cooked porkchop) |
| **Improbable Steak** | Brown mushroom + potato | 8 hunger / 12.8 saturation (= cooked beef) |

Raw variants have a 70% chance of Hunger effect on consume — eat them cooked.

### Bonus recipes

- **Rotten flesh → leather** — smelt, smoke, or campfire-cook rotten flesh you fish up
- **Glow lichen → glow ink bottle** in a stonecutter, 1:1

## Install

1. Install [Fabric Loader](https://fabricmc.net/use/installer/) for Minecraft 26.1.2.
2. Install [Fabric API](https://modrinth.com/mod/fabric-api).
3. Drop `vegetarian-1.0.0.jar` into your `mods` folder.
4. Launch.

Server-side: the mod must be installed on the server as well. Entity interactions and recipes are server-side; item rendering is client-side.

## Compatibility

- **Minecraft**: 26.1.2
- **Fabric Loader**: 0.19.2+
- **Fabric API**: required
- Works on peaceful difficulty (passive mobs spawn fine)

## Building

```sh
./gradlew build
```

Output jar lands in `build/libs/`.

## License

CC0 1.0 Universal — public domain.
