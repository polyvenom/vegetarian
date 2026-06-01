package com.tom.vegetarian.interactions;

import com.tom.vegetarian.VegetarianAttachments;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public final class ChickenPluckHandler {

    private static final long COOLDOWN_TICKS = 6000L; // 5 minutes
    private static final int MIN_FEATHERS = 1;
    private static final int MAX_FEATHERS = 3;

    private ChickenPluckHandler() {}

    public static InteractionResult onUseEntity(Player player, Level level, InteractionHand hand, Entity entity, EntityHitResult hit) {
        if (!(entity instanceof Chicken chicken)) return InteractionResult.PASS;
        if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
        if (!player.getMainHandItem().isEmpty()) return InteractionResult.PASS;
        if (chicken.isBaby()) return InteractionResult.PASS;

        long now = level.getGameTime();
        Long nextAvailable = chicken.getAttached(VegetarianAttachments.NEXT_PLUCK_TIME);
        if (nextAvailable != null && now < nextAvailable) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {
            int count = MIN_FEATHERS + level.getRandom().nextInt(MAX_FEATHERS - MIN_FEATHERS + 1);
            ItemStack feathers = new ItemStack(Items.FEATHER, count);

            ItemEntity drop = new ItemEntity(level, chicken.getX(), chicken.getY() + 0.5, chicken.getZ(), feathers);
            drop.setDeltaMovement(0.0, 0.1, 0.0);
            level.addFreshEntity(drop);

            level.playSound(null, chicken.getX(), chicken.getY(), chicken.getZ(),
                    SoundEvents.SHEARS_SNIP, SoundSource.NEUTRAL, 1.0f, 1.0f);

            chicken.setAttached(VegetarianAttachments.NEXT_PLUCK_TIME, now + COOLDOWN_TICKS);
        }

        player.swing(hand, true);
        return InteractionResult.SUCCESS;
    }
}
