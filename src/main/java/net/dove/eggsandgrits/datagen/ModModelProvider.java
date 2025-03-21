package net.dove.eggsandgrits.datagen;

import net.dove.eggsandgrits.block.custom.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.dove.eggsandgrits.block.ModBlocks;
import net.dove.eggsandgrits.item.ModItems;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool pinkGarnetPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PINK_GARNET_BLOCK);
        BlockStateModelGenerator.BlockTexturePool driftwoodPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DRIFTWOOD_PLANKS);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_PINK_GARNET_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_DEEPSLATE_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SALT_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_END_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_NETHER_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK);

        pinkGarnetPool.stairs(ModBlocks.PINK_GARNET_STAIRS);
        pinkGarnetPool.slab(ModBlocks.PINK_GARNET_SLAB);

        pinkGarnetPool.button(ModBlocks.PINK_GARNET_BUTTON);
        pinkGarnetPool.pressurePlate(ModBlocks.PINK_GARNET_PRESSURE_PLATE);

        pinkGarnetPool.fence(ModBlocks.PINK_GARNET_FENCE);
        pinkGarnetPool.fenceGate(ModBlocks.PINK_GARNET_FENCE_GATE);
        pinkGarnetPool.wall(ModBlocks.PINK_GARNET_WALL);

        blockStateModelGenerator.registerDoor(ModBlocks.PINK_GARNET_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.PINK_GARNET_TRAPDOOR);

        blockStateModelGenerator.registerDoor(ModBlocks.DRIFTWOOD_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.DRIFTWOOD_TRAPDOOR);
        driftwoodPool.slab(ModBlocks.DRIFTWOOD_SLAB);
        driftwoodPool.stairs(ModBlocks.DRIFTWOOD_STAIRS);

        driftwoodPool.button(ModBlocks.DRIFTWOOD_BUTTON);

        driftwoodPool.fence(ModBlocks.DRIFTWOOD_FENCE);
        driftwoodPool.fenceGate(ModBlocks.DRIFTWOOD_FENCE_GATE);


        Identifier lampOffIdentifier = TexturedModel.CUBE_ALL.upload(ModBlocks.PINK_GARNET_LAMP, blockStateModelGenerator.modelCollector);
        Identifier lampOnIdentifier = blockStateModelGenerator.createSubModel(ModBlocks.PINK_GARNET_LAMP, "_on", Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.PINK_GARNET_LAMP)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(PinkGarnetLampBlock.CLICKED, lampOnIdentifier, lampOffIdentifier)));


        blockStateModelGenerator.registerCrop(ModBlocks.CAULIFLOWER_CROP, CauliflowerCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6);

        blockStateModelGenerator.registerCrop(ModBlocks.ONION_CROP, OnionCropBlock.AGE, 0, 1, 2, 3);

        blockStateModelGenerator.registerCrop(ModBlocks.BEANS_CROP, BeansCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6);

        blockStateModelGenerator.registerCrop(ModBlocks.CORN_CROP, CornCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7, 8);

        blockStateModelGenerator.registerCrop(ModBlocks.RICE_CROP, RiceCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7, 8);


        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.PEPPERCORN_BUSH_BLOCK, BlockStateModelGenerator.TintType.NOT_TINTED,
                PeppercornBushBlock.AGE, 0,1,2,3);

        blockStateModelGenerator.registerLog(ModBlocks.DRIFTWOOD_LOG).log(ModBlocks.DRIFTWOOD_LOG).wood(ModBlocks.DRIFTWOOD_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_DRIFTWOOD_LOG).log(ModBlocks.STRIPPED_DRIFTWOOD_LOG).wood(ModBlocks.STRIPPED_DRIFTWOOD_WOOD);

        blockStateModelGenerator.registerSingleton(ModBlocks.DRIFTWOOD_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.DRIFTWOOD_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.CHAIR);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.TOILET);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.EVIL_CHAIR);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.STOOL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.HOT_DOG_ROLLER);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED);

        itemModelGenerator.register(ModItems.RITZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRITS, Models.GENERATED);
        itemModelGenerator.register(ModItems.JAK, Models.GENERATED);
        itemModelGenerator.register(ModItems.EGGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SYRUP, Models.GENERATED);

        itemModelGenerator.register(ModItems.CREAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHILI, Models.GENERATED);

        itemModelGenerator.register(ModItems.BUTTER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEPPER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALT, Models.GENERATED);
        itemModelGenerator.register(ModItems.GROUND_CORN, Models.GENERATED);

        itemModelGenerator.register(ModItems.EXTENSION_CORD, Models.GENERATED);

        itemModelGenerator.register(ModItems.WAFFLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIESTADA, Models.GENERATED);
        itemModelGenerator.register(ModItems.REDBULL, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOAST, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOAST_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOAST_ONION, Models.GENERATED);

        itemModelGenerator.register(ModItems.MOUNTAIN_DEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.QUAALUDE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_BABY_RAYS, Models.GENERATED);
        itemModelGenerator.register(ModItems.PANCAKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MEATBALL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAUSAGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BACON, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYSTERY_MEAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ONION, Models.GENERATED);

        itemModelGenerator.register(ModItems.CRATE, Models.GENERATED);

        itemModelGenerator.register(ModItems.RAW_HOTDOG, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_HOTDOG, Models.GENERATED);

        itemModelGenerator.register(ModItems.ECLIPSE, Models.GENERATED);

        //itemModelGenerator.register(ModItems.CORN_SEED, Models.GENERATED);

        //itemModelGenerator.register(ModItems.PEPPERCORN, Models.GENERATED);


        itemModelGenerator.register(ModItems.BAR_BRAWL_MUSIC_DISC, Models.GENERATED);

        itemModelGenerator.register(ModItems.CAULIFLOWER, Models.GENERATED);
        //itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.STARLIGHT_ASHES, Models.GENERATED);

        itemModelGenerator.register(ModItems.PINK_GARNET_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_HOE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.PINK_GARNET_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_HAMMER, Models.HANDHELD);


        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINK_GARNET_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINK_GARNET_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINK_GARNET_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PINK_GARNET_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NINE_ELEVEN_PANTS));

        itemModelGenerator.register(ModItems.PINK_GARNET_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.KAUPEN_SMITHING_TEMPLATE, Models.GENERATED);

        itemModelGenerator.register(ModBlocks.DRIFTWOOD_SAPLING.asItem(), Models.GENERATED);

        itemModelGenerator.register(ModItems.MANTIS_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));

        itemModelGenerator.register(ModItems.LARRY_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));

        itemModelGenerator.register(ModItems.DALE_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));

        itemModelGenerator.register(ModItems.TINYGUY_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}