package net.dove.eggsandgrits.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup PINK_GARNET_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EggsAndGrits.MOD_ID, "pink_garnet_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PINK_GARNET))
                    .displayName(Text.translatable("itemgroup.eggsandgrits.pink_garnet_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.RAW_PINK_GARNET);

                        entries.add(ModItems.RITZ);
                        entries.add(ModItems.EGGS);
                        entries.add(ModItems.GRITS);
                        entries.add(ModItems.JAK);
                        entries.add(ModItems.SYRUP);
                        entries.add(ModItems.CREAM);
                        entries.add(ModItems.CAULIFLOWER_SEEDS);

                        entries.add(ModItems.ONION_SEEDS);

                        entries.add(ModItems.BEANS);
                        entries.add(ModItems.CHILI);
                        entries.add(ModItems.BUTTER);

                        entries.add(ModItems.CORN);
                        entries.add(ModItems.CORN_SEED);
                        entries.add(ModItems.GROUND_CORN);

                        entries.add(ModItems.SALT);
                        entries.add(ModItems.PEPPER);
                        entries.add(ModItems.CS_KNIFE);
                        entries.add(ModItems.CRATE);


                        entries.add(ModItems.PEPPERCORN);

                        entries.add(ModItems.KAUPEN_BOW);
                        entries.add(ModItems.BAR_BRAWL_MUSIC_DISC);


                        entries.add(ModItems.CHISEL);
                        entries.add(ModItems.CAULIFLOWER);
                        entries.add(ModItems.ONION);
                        entries.add(ModItems.IRON_HAMMER);
                        entries.add(ModItems.DIAMOND_HAMMER);
                        entries.add(ModItems.NETHERITE_HAMMER);

                        entries.add(ModItems.STARLIGHT_ASHES);

                        entries.add(ModItems.PINK_GARNET_SWORD);
                        entries.add(ModItems.PINK_GARNET_PICKAXE);
                        entries.add(ModItems.PINK_GARNET_SHOVEL);
                        entries.add(ModItems.PINK_GARNET_AXE);
                        entries.add(ModItems.PINK_GARNET_HOE);


                        entries.add(ModItems.PINK_GARNET_HAMMER);

                        entries.add(ModItems.PINK_GARNET_HELMET);
                        entries.add(ModItems.PINK_GARNET_CHESTPLATE);
                        entries.add(ModItems.PINK_GARNET_LEGGINGS);
                        entries.add(ModItems.PINK_GARNET_BOOTS);

                        entries.add(ModItems.PINK_GARNET_HORSE_ARMOR);
                        entries.add(ModItems.KAUPEN_SMITHING_TEMPLATE);

                        entries.add(ModItems.MANTIS_SPAWN_EGG);
                        entries.add(ModItems.LARRY_SPAWN_EGG);
                        entries.add(ModItems.DALE_SPAWN_EGG);

                        entries.add(ModItems.VAPE);

                        entries.add(ModItems.SPECTRE_STAFF);
                        entries.add(ModItems.EXTENSION_CORD);

                        entries.add(ModItems.RICE);

                        entries.add(ModItems.WAFFLE);
                        entries.add(ModItems.FIESTADA);
                        entries.add(ModItems.REDBULL);
                        entries.add(ModItems.TOAST);
                        entries.add(ModItems.TOAST_EGG);
                        entries.add(ModItems.TOAST_ONION);

                        entries.add(ModItems.SAUSAGE);
                        entries.add(ModItems.BACON);
                        entries.add(ModItems.SWEET_BABY_RAYS);
                        entries.add(ModItems.MEATBALL);
                        entries.add(ModItems.MYSTERY_MEAT);
                        entries.add(ModItems.PANCAKE);

                        entries.add(ModItems.QUAALUDE);
                        entries.add(ModItems.MOUNTAIN_DEW);

                        entries.add(ModItems.COOKED_HOTDOG);
                        entries.add(ModItems.RAW_HOTDOG);

                        entries.add(ModItems.ECLIPSE);

                        entries.add(ModItems.SLINGSHOT);
                        entries.add(ModItems.NINE_ELEVEN_PANTS);

                        entries.add(ModItems.SPOON);
                        entries.add(ModItems.KNIFE);
                        entries.add(ModItems.FORK);

                        entries.add(ModItems.OCEAN_FRUIT);




                    }).build());

    public static final ItemGroup PINK_GARNET_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EggsAndGrits.MOD_ID, "pink_garnet_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.PINK_GARNET_BLOCK))
                    .displayName(Text.translatable("itemgroup.eggsandgrits.pink_garnet_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);

                        entries.add(ModBlocks.PINK_GARNET_ORE);
                        entries.add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE);


                        entries.add(ModBlocks.MAGIC_BLOCK);
                        entries.add(ModBlocks.SALT_BLOCK);

                        entries.add(ModBlocks.PINK_GARNET_STAIRS);
                        entries.add(ModBlocks.PINK_GARNET_SLAB);

                        entries.add(ModBlocks.PINK_GARNET_BUTTON);
                        entries.add(ModBlocks.PINK_GARNET_PRESSURE_PLATE);

                        entries.add(ModBlocks.PINK_GARNET_FENCE);
                        entries.add(ModBlocks.PINK_GARNET_FENCE_GATE);
                        entries.add(ModBlocks.PINK_GARNET_WALL);

                        entries.add(ModBlocks.PINK_GARNET_DOOR);
                        entries.add(ModBlocks.PINK_GARNET_TRAPDOOR);

                        entries.add(ModBlocks.PINK_GARNET_LAMP);

                        entries.add(ModBlocks.DRIFTWOOD_LOG);
                        entries.add(ModBlocks.STRIPPED_DRIFTWOOD_LOG);
                        entries.add(ModBlocks.STRIPPED_DRIFTWOOD_WOOD);
                        entries.add(ModBlocks.DRIFTWOOD_LEAVES);
                        entries.add(ModBlocks.DRIFTWOOD_PLANKS);
                        entries.add(ModBlocks.DRIFTWOOD_SAPLING);
                        entries.add(ModBlocks.DRIFTWOOD_WOOD);
                        entries.add(ModBlocks.DRIFTWOOD_DOOR);
                        entries.add(ModBlocks.DRIFTWOOD_TRAPDOOR);
                        entries.add(ModBlocks.DRIFTWOOD_SLAB);
                        entries.add(ModBlocks.DRIFTWOOD_STAIRS);
                        entries.add(ModBlocks.DRIFTWOOD_BUTTON);
                        entries.add(ModBlocks.DRIFTWOOD_FENCE);
                        entries.add(ModBlocks.DRIFTWOOD_FENCE_GATE);


                        entries.add(ModBlocks.CHAIR);

                        entries.add(ModBlocks.TOILET);

                        entries.add(ModBlocks.EVIL_CHAIR);

                        entries.add(ModBlocks.STOOL);

                        entries.add(ModBlocks.HOT_DOG_ROLLER);



                    }).build());


    public static void registerItemGroups() {
        EggsAndGrits.LOGGER.info("Registering Item Groups for " + EggsAndGrits.MOD_ID);
    }
}