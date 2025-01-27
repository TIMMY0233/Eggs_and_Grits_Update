package net.dove.eggsandgrits.enchantment;

import com.mojang.serialization.MapCodec;
import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.enchantment.custom.LightningStrikerEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> LIGHTNING_STRIKER =
            registerEntityEffect("lightning_striker", LightningStrikerEnchantmentEffect.CODEC);


    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name,
                                                                                    MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(EggsAndGrits.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        EggsAndGrits.LOGGER.info("Registering Mod Enchantment Effects for " + EggsAndGrits.MOD_ID);
    }
}