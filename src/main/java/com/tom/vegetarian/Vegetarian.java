package com.tom.vegetarian;

import com.tom.vegetarian.interactions.ChickenPluckHandler;
import com.tom.vegetarian.interactions.SquidBottleHandler;
import com.tom.vegetarian.items.VegetarianItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class Vegetarian implements ModInitializer {

    public static final String MOD_ID = "vegetarian";

    private static final ResourceKey<CreativeModeTab> INGREDIENTS_TAB = vanillaTab("ingredients");
    private static final ResourceKey<CreativeModeTab> FOOD_AND_DRINKS_TAB = vanillaTab("food_and_drinks");

    @Override
    public void onInitialize() {
        VegetarianAttachments.registerAll();
        VegetarianItems.registerAll();

        UseEntityCallback.EVENT.register(ChickenPluckHandler::onUseEntity);
        UseEntityCallback.EVENT.register(SquidBottleHandler::onUseEntity);

        CreativeModeTabEvents.modifyOutputEvent(INGREDIENTS_TAB).register(output -> {
            output.accept(VegetarianItems.INK_BOTTLE);
            output.accept(VegetarianItems.GLOW_INK_BOTTLE);
            output.accept(VegetarianItems.FUNGAL_MASH);
            output.accept(VegetarianItems.CURED_MYCELIUM);
        });

        CreativeModeTabEvents.modifyOutputEvent(FOOD_AND_DRINKS_TAB).register(output -> {
            output.accept(VegetarianItems.RAW_IMPROBABLE_NUGGETS);
            output.accept(VegetarianItems.COOKED_IMPROBABLE_NUGGETS);
            output.accept(VegetarianItems.RAW_IMPROBABLE_BACON);
            output.accept(VegetarianItems.COOKED_IMPROBABLE_BACON);
            output.accept(VegetarianItems.RAW_IMPROBABLE_STEAK);
            output.accept(VegetarianItems.COOKED_IMPROBABLE_STEAK);
        });
    }

    private static ResourceKey<CreativeModeTab> vanillaTab(String name) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath("minecraft", name));
    }
}
