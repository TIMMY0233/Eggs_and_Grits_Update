package net.dove.eggsandgrits.mixin;

import net.dove.eggsandgrits.effect.ModEffects;
import net.dove.eggsandgrits.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

    @Mixin(LivingEntity.class)
    public abstract class HoldingItemEffectMixin {

        @Inject(method = "tick", at = @At("HEAD"))
        private void applyHoldingEffect(CallbackInfo info) {
            LivingEntity entity = (LivingEntity) (Object) this;

            if (entity instanceof PlayerEntity player) {
                ItemStack heldItem = player.getMainHandStack();

                if (heldItem.getItem() == Items.NETHER_STAR) { // Change to your desired item
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 10, 1, true, false));
                }
                if (heldItem.getItem() == ModItems.CS_KNIFE) { // Change to your desired item
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.B_HOP, 10, 1, true, false));
                }
            }
        }
    }


