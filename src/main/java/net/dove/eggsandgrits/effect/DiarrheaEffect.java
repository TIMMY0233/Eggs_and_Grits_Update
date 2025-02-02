package net.dove.eggsandgrits.effect;

import net.dove.eggsandgrits.sound.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;

public class DiarrheaEffect extends StatusEffect {
    public DiarrheaEffect() {
        super(StatusEffectCategory.HARMFUL, 0x654321); // Brown color for effect
    }

    public DiarrheaEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            // Apply Slowness
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, amplifier, false, true, true));

            // Randomly play fart sounds (1 in 5 chance every tick)
            if (player.getWorld().random.nextFloat() < 0.07f) {
                player.getWorld().playSound(
                        null,
                        player.getBlockPos(),
                        ModSounds.TOOT2,
                        SoundCategory.PLAYERS,
                        0.5f,
                        1.3f
                );
            }
            else if (player.getWorld().random.nextFloat() <0.1f) {
                player.getWorld().playSound(
                        null,
                        player.getBlockPos(),
                        ModSounds.TOOT,
                        SoundCategory.PLAYERS,
                        0.5f,
                        1.3f
                );
            }
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true; // Apply effect every tick
    }
}