package com.tom.vegetarian.items;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.function.Function;

public final class VegetarianItems {

    private VegetarianItems() {}

    // Ink dye-bottle items — work as black/glow dye via #c:dyes tags.
    public static final Item INK_BOTTLE = registerItem("ink_bottle", Item::new, new Item.Properties().stacksTo(16));
    public static final Item GLOW_INK_BOTTLE = registerItem("glow_ink_bottle", Item::new, new Item.Properties().stacksTo(16));

    // Mycelium leather chain.
    public static final Item FUNGAL_MASH = registerItem("fungal_mash", Item::new, new Item.Properties());
    public static final Item CURED_MYCELIUM = registerItem("cured_mycelium", Item::new, new Item.Properties());

    // Improbable Meats — chicken analogue.
    public static final Item RAW_IMPROBABLE_NUGGETS = registerItem(
            "raw_improbable_nuggets", Item::new,
            new Item.Properties().food(
                    new FoodProperties(2, 1.2f, false),
                    consumableWithPoisoning(0.7f)));
    public static final Item COOKED_IMPROBABLE_NUGGETS = registerItem(
            "cooked_improbable_nuggets", Item::new,
            new Item.Properties().food(new FoodProperties(6, 7.2f, false)));

    // Improbable Meats — porkchop analogue.
    public static final Item RAW_IMPROBABLE_BACON = registerItem(
            "raw_improbable_bacon", Item::new,
            new Item.Properties().food(
                    new FoodProperties(3, 1.8f, false),
                    consumableWithPoisoning(0.7f)));
    public static final Item COOKED_IMPROBABLE_BACON = registerItem(
            "cooked_improbable_bacon", Item::new,
            new Item.Properties().food(new FoodProperties(8, 12.8f, false)));

    // Improbable Meats — beef analogue.
    public static final Item RAW_IMPROBABLE_STEAK = registerItem(
            "raw_improbable_steak", Item::new,
            new Item.Properties().food(
                    new FoodProperties(3, 1.8f, false),
                    consumableWithPoisoning(0.7f)));
    public static final Item COOKED_IMPROBABLE_STEAK = registerItem(
            "cooked_improbable_steak", Item::new,
            new Item.Properties().food(new FoodProperties(8, 12.8f, false)));

    // Touch this method from the main entrypoint so the static fields above
    // are initialized (and therefore registered) before world load.
    public static void registerAll() {}

    // Hunger effect, 30 seconds (600 ticks), like vanilla raw chicken.
    private static Consumable consumableWithPoisoning(float probability) {
        return Consumable.builder()
                .consumeSeconds(1.6f)
                .animation(ItemUseAnimation.EAT)
                .sound(SoundEvents.GENERIC_EAT)
                .hasConsumeParticles(true)
                .onConsume(new ApplyStatusEffectsConsumeEffect(
                        new MobEffectInstance(MobEffects.HUNGER, 600, 0),
                        probability))
                .build();
    }

    private static Item registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties props) {
        Identifier id = Identifier.fromNamespaceAndPath("vegetarian", name);
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
        Item item = factory.apply(props.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }
}
