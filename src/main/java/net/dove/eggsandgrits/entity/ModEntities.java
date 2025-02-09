package net.dove.eggsandgrits.entity;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.LarryEntity;
import net.dove.eggsandgrits.entity.custom.MantisEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MantisEntity> MANTIS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EggsAndGrits.MOD_ID, "mantis"),
            EntityType.Builder.create(MantisEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1.5f, 2.5f).build());


    public static final EntityType<LarryEntity> LARRY = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EggsAndGrits.MOD_ID, "larry"),
            EntityType.Builder.create(LarryEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 2f).build());


    public static void registerModEntities() {
        EggsAndGrits.LOGGER.info("Registering Mod Entities for " + EggsAndGrits.MOD_ID);
    }


}