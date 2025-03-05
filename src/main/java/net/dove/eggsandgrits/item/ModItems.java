package net.dove.eggsandgrits.item;

import net.dove.eggsandgrits.block.ModBlocks;
import net.dove.eggsandgrits.entity.ModEntities;
import net.dove.eggsandgrits.item.custom.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.sound.ModSounds;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

public class ModItems {
    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", new Item(new Item.Settings()));

    public static final Item BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.BAR_BRAWL_KEY).maxCount(1))
    );

    public static final Item JAK = registerItem("jak", new Item(new Item.Settings()));

    public static final Item RITZ = registerItem("ritz", new Item(new Item.Settings().food(ModFoodComponents.RITZ)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.eggsandgrits.ritz.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });


    public static final Item EGGS = registerItem("eggs", new Item(new Item.Settings().food(ModFoodComponents.EGGS)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.eggsandgrits.eggs.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item GRITS = registerItem("grits", new Item(new Item.Settings().food(ModFoodComponents.GRITS)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.eggsandgrits.girts.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item SYRUP = registerItem(
            "syrup", new DrinkItem(new Item.Settings().food(ModFoodComponents.SYRUP).maxCount(16))
    );

    public static final Item BUTTER = registerItem(
            "butter", new Item(new Item.Settings().food(ModFoodComponents.BUTTER).maxCount(64))
    );

    public static final Item CREAM = registerItem(
            "cream", new DrinkItem(new Item.Settings().food(ModFoodComponents.CREAM).maxCount(1)){
                @Override
                public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
                    tooltip.add(Text.translatable("tooltip.eggsandgrits.cream.tooltip"));
                    super.appendTooltip(stack, context, tooltip, options);
                }
            }
    );

    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));
    public static final Item CAULIFLOWER = registerItem("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CAULIFLOWER)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.eggsandgrits.cauliflower.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item PEPPER = registerItem("pepper", new Item(new Item.Settings().food(ModFoodComponents.PEPPER)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.eggsandgrits.pepper.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item SALT = registerItem("salt", new Item(new Item.Settings()));
    public static final Item GROUND_CORN = registerItem("ground_corn", new Item(new Item.Settings()));



    public static final Item CHILI = registerItem("chili", new Item(new Item.Settings().food(ModFoodComponents.CHILI)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if(Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("tooltip.eggsandgrits.chili.shift_down"));
            } else {
                tooltip.add(Text.translatable("tooltip.eggsandgrits.chili.tooltip"));
            }
                 super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item KAUPEN_BOW = registerItem("kaupen_bow", new BowItem(new Item.Settings().maxDamage(100).rarity(Rarity.EPIC)));

    public static final Item SLINGSHOT = registerItem("slingshot", new BowItem(new Item.Settings().maxDamage(100).rarity(Rarity.EPIC)));



    public static final Item STARLIGHT_ASHES = registerItem("starlight_ashes", new Item(new Item.Settings()));

    public static final Item PINK_GARNET_SWORD = registerItem("pink_garnet_sword",
            new SwordItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 3, -2.4f))));
    public static final Item PINK_GARNET_PICKAXE = registerItem("pink_garnet_pickaxe",
            new PickaxeItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 1, -2.8f))));
    public static final Item PINK_GARNET_SHOVEL = registerItem("pink_garnet_shovel",
            new ShovelItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 1.5f, -3.0f))));
    public static final Item PINK_GARNET_AXE = registerItem("pink_garnet_axe",
            new AxeItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 6, -3.2f))));
    public static final Item PINK_GARNET_HOE = registerItem("pink_garnet_hoe",
            new HoeItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 0, -3f))));

    public static final Item PINK_GARNET_HAMMER = registerItem("pink_garnet_hammer",
            new HammerItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 7, -3.4f))));

    public static final Item PINK_GARNET_HELMET = registerItem("pink_garnet_helmet",
            new ModArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15))));
    public static final Item PINK_GARNET_CHESTPLATE = registerItem("pink_garnet_chestplate",
            new ArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(15))));
    public static final Item PINK_GARNET_LEGGINGS = registerItem("pink_garnet_leggings",
            new ArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15))));
    public static final Item PINK_GARNET_BOOTS = registerItem("pink_garnet_boots",
            new ArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15))));

    public static final Item PINK_GARNET_HORSE_ARMOR = registerItem("pink_garnet_horse_armor",
            new AnimalArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, false, new Item.Settings().maxCount(1)));
    public static final Item KAUPEN_SMITHING_TEMPLATE = registerItem("kaupen_armor_trim_smithing_template",
            SmithingTemplateItem.of(Identifier.of(EggsAndGrits.MOD_ID, "kaupen"), FeatureFlags.VANILLA));

    public static final Item NINE_ELEVEN_PANTS = registerItem("nine_eleven_pants",
            new ArmorItem(ModArmorMaterials.NINE_ELEVEN_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15))));


    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer",
            new HammerItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.NETHERITE, 8, -3.4f))));

    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer",
            new HammerItem(ModToolMaterials.PINK_GARNET, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.DIAMOND, 7, -3.4f))));

    public static final Item IRON_HAMMER = registerItem("iron_hammer",
            new HammerItem(ModToolMaterials.PINK_GARNET   , new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.IRON, 6, -3.4f))));

    public static final Item CAULIFLOWER_SEEDS = registerItem("cauliflower_seeds",
            new AliasedBlockItem(ModBlocks.CAULIFLOWER_CROP, new Item.Settings()));

    public static final Item ONION_SEEDS = registerItem("onion_seeds",
            new AliasedBlockItem(ModBlocks.ONION_CROP, new Item.Settings()));

    public static final Item ONION = registerItem("onion",
            new Item(new Item.Settings().food(ModFoodComponents.CORN)));

    public static final Item BEANS = registerItem("beans",
            new AliasedBlockItem(ModBlocks.BEANS_CROP, new Item.Settings().food(ModFoodComponents.BEANS)));

    public static final Item RICE = registerItem("rice",
            new AliasedBlockItem(ModBlocks.RICE_CROP, new Item.Settings()));


    public static final Item CORN = registerItem("corn",
            new Item(new Item.Settings().food(ModFoodComponents.CORN)));

    public static final Item CORN_SEED = registerItem("corn_seeds",
            new AliasedBlockItem(ModBlocks.CORN_CROP, new  Item.Settings()));

    public static final Item PEPPERCORN = registerItem("peppercorn",
            new AliasedBlockItem(ModBlocks.PEPPERCORN_BUSH_BLOCK, new Item.Settings().food(ModFoodComponents.PEPPERCORN)));

    public static final Item MANTIS_SPAWN_EGG = registerItem("mantis_spawn_egg",
            new SpawnEggItem(ModEntities.MANTIS, 0x9dc783, 0xbfa5f, new Item.Settings()));

    public static final Item LARRY_SPAWN_EGG = registerItem("larry_spawn_egg",
            new SpawnEggItem(ModEntities.LARRY, 0x9dc783, 0xbda5f, new Item.Settings()));

    public static final Item DALE_SPAWN_EGG = registerItem("dale_spawn_egg",
            new SpawnEggItem(ModEntities.DALE, 0x9dc383, 0xbf, new Item.Settings()));

    public static final Item TINYGUY_SPAWN_EGG = registerItem("tinyguy_spawn_egg",
            new SpawnEggItem(ModEntities.TINYGUY, 0x9dc383, 0xbf, new Item.Settings()));

    public static final Item VAPE = registerItem("vape",
            new VapeItem(new Item.Settings().maxCount(16)));

    public static final Item SPECTRE_STAFF = registerItem("spectre_staff",
            new Item(new Item.Settings().maxCount(1)));

    public static final Item EXTENSION_CORD = registerItem("extension_cord",
            new Item(new Item.Settings().maxCount(1)));


    public static final Item FIESTADA = registerItem("fiestada", new Item(new Item.Settings().food(ModFoodComponents.FIESTADA)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.eggsandgrits.fiestada.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item REDBULL = registerItem("redbull", new DrinkItem(new Item.Settings().food(ModFoodComponents.REDBULL)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.eggsandgrits.redbull.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item WAFFLE = registerItem("waffle", new Item(new Item.Settings().food(ModFoodComponents.WAFFLE)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.eggsandgrits.waffle.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item TOAST = registerItem("toast", new Item(new Item.Settings().food(ModFoodComponents.TOAST)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.eggsandgrits.toast.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item TOAST_EGG = registerItem("toast_egg", new Item(new Item.Settings().food(ModFoodComponents.TOAST_EGG)) {
    });

    public static final Item TOAST_ONION = registerItem("toast_onion", new Item(new Item.Settings().food(ModFoodComponents.TOAST_ONION)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.eggsandgrits.toast_onion.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item PANCAKE = registerItem("pancakes", new Item(new Item.Settings().food(ModFoodComponents.PANCAKE)) {
    });

    public static final Item SAUSAGE = registerItem("sausage", new Item(new Item.Settings().food(ModFoodComponents.SAUSAGE)) {
    });

    public static final Item BACON = registerItem("bacon", new Item(new Item.Settings().food(ModFoodComponents.BACON)) {
    });

    public static final Item QUAALUDE = registerItem("quaalude", new Item(new Item.Settings().food(ModFoodComponents.QUAALUDE)) {
    });

    public static final Item MEATBALL = registerItem("meatball", new Item(new Item.Settings().food(ModFoodComponents.MEATBALL)) {
    });

    public static final Item RAW_HOTDOG = registerItem("raw_hotdog", new Item(new Item.Settings().food(ModFoodComponents.RAW_HOTDOG)) {
    });

    public static final Item COOKED_HOTDOG = registerItem("cooked_hotdog", new Item(new Item.Settings().food(ModFoodComponents.COOKED_HOTDOG)) {
    });

    public static final Item MYSTERY_MEAT = registerItem("mystery_meat", new Item(new Item.Settings().food(ModFoodComponents.MYSTERY_MEAT)) {
    });

    public static final Item MOUNTAIN_DEW = registerItem(
            "mountain_dew", new DrinkItem(new Item.Settings().food(ModFoodComponents.MOUNTAIN_DEW).maxCount(16))
    );

    public static final Item SWEET_BABY_RAYS = registerItem(
            "sweet_baby_rays", new DrinkItem(new Item.Settings().food(ModFoodComponents.SWEET_BABY_RAYS).maxCount(16))
    );

    public static final Item CS_KNIFE = registerItem("cs_knife",
            new SwordItem(ToolMaterials.DIAMOND, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 3, -2.4f))));

    public static final Item CRATE = registerItem("crate",
            new CrateItem(new Item.Settings().maxCount(64)));

    public static final Item ECLIPSE = registerItem("eclipse",
            new EclipseItem(new Item.Settings().maxCount(1)));


    public static final Item CS_GLOVES = registerItem("cs_gloves", new Item(new Item.Settings()));

    public static final Item CS_BUTTERFLY_KNIFE = registerItem("cs_butterfly_knife", new Item(new Item.Settings()));

    public static final Item SPOON = registerItem("spoon",
            new ShovelItem(ToolMaterials.IRON, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.IRON,1.5f, -3.0f))));

    public static final Item KNIFE = registerItem("knife",
            new SwordItem(ToolMaterials.IRON, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON,3, -2.4f))));

    public static final Item FORK = registerItem("fork",
            new SwordItem(ToolMaterials.IRON, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON,5, -3.4f))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(EggsAndGrits.MOD_ID, name), item);
    }

    public static void registerModItems() {
        EggsAndGrits.LOGGER.info("Registering Mod Items for " + EggsAndGrits.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
            entries.add(RAW_PINK_GARNET);
        });
    }
}