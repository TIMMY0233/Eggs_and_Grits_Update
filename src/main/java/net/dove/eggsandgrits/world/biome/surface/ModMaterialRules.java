package net.dove.eggsandgrits.world.biome.surface;

import net.minecraft.block.Block;
import net.dove.eggsandgrits.block.ModBlocks;
import net.dove.eggsandgrits.world.biome.ModBiomes;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule SALT_BLOCK = makeStateRule(ModBlocks.SALT_BLOCK);
    private static final MaterialRules.MaterialRule PINK_GARNET_BLOCK = makeStateRule(ModBlocks.PINK_GARNET_BLOCK);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return MaterialRules.sequence(
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.TEST_BIOME),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, PINK_GARNET_BLOCK)),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, SALT_BLOCK)),

                // Default to a grass and dirt surface
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
