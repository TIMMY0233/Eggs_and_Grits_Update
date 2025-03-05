package net.dove.eggsandgrits.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.Vec3d;


public class BunnyHopEffect extends StatusEffect {
    private static final double MAX_SPEED = 2; // Max horizontal speed to prevent excessive acceleration

    public BunnyHopEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        Vec3d velocity = entity.getVelocity();

        if (entity.groundCollision) {
            // Small speed boost when jumping
            double speedBoost = 1.05 + (amplifier * 0.02);
            double newX = Math.min(velocity.x * speedBoost, MAX_SPEED);
            double newZ = Math.min(velocity.z * speedBoost, MAX_SPEED);
            double newY = Math.min(velocity.y * speedBoost, MAX_SPEED);

            // Set new velocity with preserved direction
            entity.setVelocity(newX, newY, newZ);
        } else {
            // Allow strafing mid-air by preserving velocity direction
            Vec3d movementInput = entity.getRotationVector();
            double airControl = 0.06 + (amplifier * 0.005); // Adjust air movement control
            Vec3d airVelocity = new Vec3d(
                    velocity.x + movementInput.x * airControl,
                    velocity.y,
                    velocity.z + movementInput.z * airControl
            );

            // Limit horizontal speed
            if (airVelocity.horizontalLength() > MAX_SPEED) {
                airVelocity = airVelocity.normalize().multiply(MAX_SPEED);
            }

            entity.setVelocity(airVelocity);
        }

        // Apply dynamic FOV scaling based on speed

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
