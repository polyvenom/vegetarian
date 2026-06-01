package com.tom.vegetarian;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;

public final class VegetarianAttachments {

    public static final AttachmentType<Long> NEXT_PLUCK_TIME = AttachmentRegistry.createPersistent(
            Identifier.fromNamespaceAndPath(Vegetarian.MOD_ID, "next_pluck_time"),
            Codec.LONG
    );

    public static final AttachmentType<Long> NEXT_BOTTLE_TIME = AttachmentRegistry.createPersistent(
            Identifier.fromNamespaceAndPath(Vegetarian.MOD_ID, "next_bottle_time"),
            Codec.LONG
    );

    private VegetarianAttachments() {}

    // Touch from the entrypoint so the static fields above get initialized at the right time.
    public static void registerAll() {}
}
