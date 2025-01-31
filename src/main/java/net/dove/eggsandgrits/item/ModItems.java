package net.dove.eggsandgrits.item;

import net.dove.eggsandgrits.block.ModBlocks;
import net.dove.eggsandgrits.item.custom.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.sound.ModSounds;

import net.minecraft.client.gui.screen.Screen;

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

    public static final Item KAUPEN_BOW = registerItem("kaupen_bow", new BowItem(new Item.Settings().maxDamage(500).rarity(Rarity.EPIC)));


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

    public static final Item CAULIFLOWER_SEEDS = registerItem("cauliflower_seeds",
            new AliasedBlockItem(ModBlocks.CAULIFLOWER_CROP, new Item.Settings()));

    public static final Item BEANS = registerItem("beans",
            new AliasedBlockItem(ModBlocks.BEANS_CROP, new Item.Settings().food(ModFoodComponents.BEANS)));


    public static final Item CORN = registerItem("corn",
            new Item(new Item.Settings().food(ModFoodComponents.CORN)));

    public static final Item CORN_SEED = registerItem("corn_seeds",
            new AliasedBlockItem(ModBlocks.CORN_CROP, new  Item.Settings()));

    public static final Item PEPPERCORN = registerItem("peppercorn",
            new AliasedBlockItem(ModBlocks.PEPPERCORN_BUSH_BLOCK, new Item.Settings().food(ModFoodComponents.PEPPERCORN)));


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