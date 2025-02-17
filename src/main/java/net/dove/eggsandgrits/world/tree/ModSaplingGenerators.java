package net.dove.eggsandgrits.world.tree;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;


import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator DRIFTWOOD = new SaplingGenerator(EggsAndGrits.MOD_ID + ":driftwood",
    Optional.empty(), Optional.of(ModConfiguredFeatures.DRIFTWOOD_KEY), Optional.empty());

}
