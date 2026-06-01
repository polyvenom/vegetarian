package com.tom.vegetarian.interactions;

import com.tom.vegetarian.VegetarianAttachments;
import com.tom.vegetarian.items.VegetarianItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.squid.GlowSquid;
import net.minecraft.world.entity.animal.squid.Squid;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public final class SquidBottleHandler {

    private static final long COOLDOWN_TICKS = 6000L; // 5 minutes
    private static final int MAX_HARVEST = 3;

    private SquidBottleHandler() {}

    public static InteractionResult onUseEntity(Player player, Level level, InteractionHand hand, Entity entity, EntityHitResult hit) {
        boolean isGlow = entity instanceof GlowSquid;
        if (!(entity instanceof Squid squid) && !isGlow) return InteractionResult.PASS;
        if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;

        ItemStack held = player.getMainHandItem();
        if (!held.is(Items.GLASS_BOTTLE)) return InteractionResult.PASS;

        long now = level.getGameTime();
        Long nextAvailable = entity.getAttached(VegetarianAttachments.NEXT_BOTTLE_TIME);
        if (nextAvailable != null && now < nextAvailable) {
            return InteractionResult.PASS;
        }

        int available = held.getCount();
        int taken = 1 + level.getRandom().nextInt(Math.min(MAX_HARVEST, available));

        if (!level.isClientSide()) {
            Item resultItem = isGlow ? VegetarianItems.GLOW_INK_BOTTLE : VegetarianItems.INK_BOTTLE;
            ItemStack result = new ItemStack(resultItem, taken);

            if (!player.isCreative()) {
                held.shrink(taken);
            }

            if (!player.getInventory().add(result)) {
                ItemEntity drop = new ItemEntity(level, entity.getX(), entity.getY() + 0.5, entity.getZ(), result);
                level.addFreshEntity(drop);
            }

            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                    SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0f, 1.0f);

            entity.setAttached(VegetarianAttachments.NEXT_BOTTLE_TIME, now + COOLDOWN_TICKS);
        }

        player.swing(hand, true);
        return InteractionResult.SUCCESS;
    }
}
