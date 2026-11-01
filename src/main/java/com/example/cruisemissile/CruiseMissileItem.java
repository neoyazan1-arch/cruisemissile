package com.example.cruisemissile;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CruiseMissileItem extends Item {
    private static final int EXPLOSION_POWER = 6; // TNT is 4
    private static final double SPEED = 2.0;
    private static final int COOLDOWN_TICKS = 40;

    public CruiseMissileItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            Vec3 look = player.getLookAngle();

            LargeFireball missile = new LargeFireball(level, player, look, EXPLOSION_POWER);
            missile.setPos(player.getX() + look.x * 1.5,
                           player.getEyeY() - 0.2 + look.y * 1.5,
                           player.getZ() + look.z * 1.5);
            missile.setDeltaMovement(look.scale(SPEED));
            level.addFreshEntity(missile);

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.PLAYERS, 1.5F, 0.8F);

            stack.consume(1, player); // doesn't consume in creative
        }

        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
