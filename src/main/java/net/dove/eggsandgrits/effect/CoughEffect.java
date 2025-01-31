package net.dove.eggsandgrits.effect;

import net.dove.eggsandgrits.sound.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;

public class CoughEffect extends StatusEffect {
    public CoughEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
    public CoughEffect() {
        super(StatusEffectCategory.HARMFUL, 0x353935); // Black color for effect
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20, 1, false, true, true));
            // Randomly play fart sounds (1 in 5 chance every tick)
            if (player.getWorld().random.nextFloat() < 0.07f) {
                player.getWorld().playSound(
                        null,
                        player.getBlockPos(),
                        ModSounds.COUGH,
                        SoundCategory.PLAYERS,
                        2f,
                        1f
                );
            }
            else if (player.getWorld().random.nextFloat() <0.1f) {
                player.getWorld().playSound(
                        null,
                        player.getBlockPos(),
                        ModSounds.COUGH2,
                        SoundCategory.PLAYERS,
                        2f,
                        1f
                );
            }
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true; // Apply effect every tick
    }
}
