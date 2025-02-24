package net.dove.eggsandgrits.item.custom;


import net.dove.eggsandgrits.item.ModItems;
import net.minecraft.item.Items;
import net.minecraft.item.Item;

import java.util.List;


public class CrateLootTable {
    public static final List<Item> commonItems = List.of(Items.APPLE, Items.BEEF, Items.BONE, Items.COOKIE, ModItems.BEANS, ModItems.ONION_SEEDS, ModItems.RICE, ModItems.CORN_SEED, ModItems.SYRUP); // Define common items
    public static final List<Item> uncommonItems = List.of(Items.GOLDEN_HOE, Items.GOLDEN_APPLE, ModItems.WAFFLE, ModItems.PANCAKE, ModItems.COOKED_HOTDOG, ModItems.BACON, ModItems.MOUNTAIN_DEW, ModItems.MEATBALL, ModItems.TOAST_ONION, ModItems.TOAST_EGG, ModItems.REDBULL); // Define uncommon items
    public static final List<Item> rareItems = List.of(ModItems.CRATE, Items.DIAMOND, ModItems.QUAALUDE, ModItems.GRITS, ModItems.SWEET_BABY_RAYS, ModItems.TINYGUY_SPAWN_EGG); // Define rare items
    public static final List<Item> epicItems = List.of(ModItems.CS_KNIFE, ModItems.GRITS, ModItems.EGGS, ModItems.ECLIPSE, ModItems.CS_BUTTERFLY_KNIFE, ModItems.CS_GLOVES); // Define epic items

    public static Item getRandomLoot() {
        // Define weights for rarities (e.g., 50% common, 30% uncommon, 15% rare, 5% epic)
        double rarityRoll = Math.random();

        if (rarityRoll < 0.01) {
            return getRandomItemFromList(epicItems);
        } else if (rarityRoll < 0.20) {
            return getRandomItemFromList(rareItems);
        } else if (rarityRoll < 0.50) {
            return getRandomItemFromList(uncommonItems);
        } else {
            return getRandomItemFromList(commonItems);
        }
    }

    private static Item getRandomItemFromList(List<Item> items) {
        return items.get((int) (Math.random() * items.size()));
    }
}


