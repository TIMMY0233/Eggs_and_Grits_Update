package net.dove.eggsandgrits.entity;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.*;
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
                    .dimensions(.5f, 2f).build());

    public static final EntityType<DaleEntity> DALE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EggsAndGrits.MOD_ID, "dale"),
            EntityType.Builder.create(DaleEntity::new, SpawnGroup.CREATURE)
                    .dimensions(.5f, 2f).build());

    public static final EntityType<TinyGuyEntity> TINYGUY = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EggsAndGrits.MOD_ID, "tinyguy"),
            EntityType.Builder.create(TinyGuyEntity::new, SpawnGroup.CREATURE)
                    .dimensions(.5f, 2f).build());

    public static final EntityType<DaBabyEntity> DABABY = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EggsAndGrits.MOD_ID, "dababy"),
            EntityType.Builder.create(DaBabyEntity::new, SpawnGroup.CREATURE)
                    .dimensions(.5f, 1.5f).build());


    public static final EntityType<VapeProjectileEntity> VAPE_RING = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EggsAndGrits.MOD_ID, "vape_ring"),
            EntityType.Builder.<VapeProjectileEntity>create(VapeProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 1.15f).build());

    public static final EntityType<ChairEntity> CHAIR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EggsAndGrits.MOD_ID, "chair_entity"),
            EntityType.Builder.create(ChairEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f).build());

    public static final EntityType<StoolEntity> STOOL = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EggsAndGrits.MOD_ID, "stool_entity"),
            EntityType.Builder.create(StoolEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, .85f).build());

    public static final EntityType<HotDogRollerEntity> HOT_DOG_ROLLER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(EggsAndGrits.MOD_ID, "hot_dog_roller_entity"),
            EntityType.Builder.create(HotDogRollerEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, .85f).build());




    public static void registerModEntities() {
        EggsAndGrits.LOGGER.info("Registering Mod Entities for " + EggsAndGrits.MOD_ID);
    }


}