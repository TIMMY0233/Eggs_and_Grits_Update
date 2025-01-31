package net.dove.eggsandgrits.effect;

import net.dove.eggsandgrits.EggsAndGrits;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> SLIMEY = registerStatusEffect("slimey",
            new SlimeyEffect(StatusEffectCategory.NEUTRAL, 0x36ebab)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Identifier.of(EggsAndGrits.MOD_ID, "slimey"), -0.25f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );


    public static final RegistryEntry<StatusEffect> DIARRHEA = registerStatusEffect("diarrhea",
            new DiarrheaEffect(StatusEffectCategory.NEUTRAL, 0x9d593a)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Identifier.of(EggsAndGrits.MOD_ID, "diarrhea"), -0.25f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static final RegistryEntry<StatusEffect> COUGH = registerStatusEffect("cough",
            new CoughEffect(StatusEffectCategory.NEUTRAL, 0x353935)
    );



    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(EggsAndGrits.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        EggsAndGrits.LOGGER.info("Registering Mod Effects for " + EggsAndGrits.MOD_ID);
    }
}