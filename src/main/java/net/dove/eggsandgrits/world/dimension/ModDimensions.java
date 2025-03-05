package net.dove.eggsandgrits.world.dimension;

import net.dove.eggsandgrits.EggsAndGrits;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimensions {

    public static final RegistryKey<DimensionOptions> KAUPENDIM_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(EggsAndGrits.MOD_ID, "kaupendim"));
    public static final RegistryKey<World> KAUPENDIM_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(EggsAndGrits.MOD_ID, "kaupendim"));
    public static final RegistryKey<DimensionType> KAUPEN_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(EggsAndGrits.MOD_ID, "kaupendim_type"));


    public static final RegistryKey<DimensionOptions> OCEANDIM_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(EggsAndGrits.MOD_ID, "oceandim"));
    public static final RegistryKey<World> OCEANDIM_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(EggsAndGrits.MOD_ID, "oceandim"));
    public static final RegistryKey<DimensionType> OCEAN_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(EggsAndGrits.MOD_ID, "oceandim_type"));


    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(KAUPEN_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                320, // height
                320, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));

        context.register(OCEAN_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                true, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                false, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                320, // height
                320, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
    }

}
