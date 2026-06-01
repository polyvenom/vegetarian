# The Vegetarian — Modrinth Listing

Copy-paste content for the Modrinth project page. The description body below is the markdown that goes into the long-form project description.

## Form metadata

| Field | Value |
| --- | --- |
| Title | The Vegetarian |
| Slug | `vegetarian` |
| Summary (short, ≤256 chars) | Plant-based alternatives to every passive-mob drop. Pluck chickens, bottle squids, grow leather from mushrooms, cook Improbable Meats. Peaceful-friendly. |
| Categories | `food`, `game-mechanics`, `mobs` |
| Loaders | Fabric |
| Minecraft versions | 26.1.2 |
| Environment — Client side | Required |
| Environment — Server side | Required |
| License | CC0-1.0 (Public Domain) |
| Source link | https://github.com/polyvenom/vegetarian |
| Issue tracker | https://github.com/polyvenom/vegetarian/issues |
| Donation | Ko-fi (polyvenom) |

## Description body

---

A small Fabric mod for Minecraft 26.1.2 that gives you a plant-based alternative to every passive-mob drop. Pluck chickens for feathers, bottle squids for ink, grow leather from mushrooms, and cook **Improbable Meats** — all without killing a single cow, pig, or chicken.

## What it does

### 🐔 Pluck a chicken

Right-click a chicken with an empty main hand. It drops 1-3 feathers, plays a snip sound, and goes on a 5-minute cooldown. Repeat. No harm done.

### 🦑 Bottle a squid

Right-click any squid or glow squid while holding glass bottles. Consumes 1-3 bottles, fills them with **Ink Bottles** or **Glow Ink Bottles** — new items that act as drop-in replacements for ink sacs and glow ink sacs in every vanilla recipe (writable book, glow item frame, black dye). 5-minute cooldown per squid.

### 🍄 Mycelium leather

A two-step path to leather without touching a cow:

1. **Fungal Mash** — craft 2 brown mushroom + 1 dirt + 1 bone meal
2. **Cured Mycelium** — smelt or smoke the mash
3. **Leather** — 2 cured mycelium side-by-side

### 🥩 Improbable Meats

Three mushroom-based meat analogues, each with a raw and a cooked variant. Cooked stats match vanilla exactly.

| Analogue | Raw recipe | Cooked stats |
| --- | --- | --- |
| **Improbable Nuggets** | Brown mushroom + wheat seeds | 6 hunger / 7.2 saturation |
| **Improbable Bacon** | Brown mushroom + beetroot | 8 hunger / 12.8 saturation |
| **Improbable Steak** | Brown mushroom + potato | 8 hunger / 12.8 saturation |

Raw variants have a 70% chance of Hunger effect — eat them cooked.

### Bonus

- **Rotten flesh → leather** (smelt, smoke, or campfire)
- **Glow lichen → glow ink bottle** in a stonecutter

## Companion mod

Pair with **[The Pacifist](https://modrinth.com/mod/pacifist)** to also cover hostile-mob drops. The two mods are independent — use either or both.

## Compatibility

- Minecraft 26.1.2 + Fabric Loader 0.19.2+
- Requires Fabric API
- Works on peaceful difficulty
- Loot/recipe additions use Fabric's non-destructive injection APIs — plays nice with other content mods

## Source

[github.com/polyvenom/vegetarian](https://github.com/polyvenom/vegetarian) — CC0 public domain.
