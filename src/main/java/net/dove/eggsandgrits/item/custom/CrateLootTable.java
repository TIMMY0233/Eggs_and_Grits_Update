package net.dove.eggsandgrits.item.custom;

import net.minecraft.item.Items;
import net.minecraft.item.Item;

import java.util.List;
import java.util.Random;

public class CrateLootTable {
    public static final List<Item> commonItems = List.of(Items.APPLE, Items.BEEF); // Define common items
    public static final List<Item> uncommonItems = List.of(Items.GOLDEN_HOE, Items.GOLDEN_APPLE); // Define uncommon items
    public static final List<Item> rareItems = List.of(Items.DIAMOND_HOE, Items.DIAMOND); // Define rare items
    public static final List<Item> epicItems = List.of(Items.NETHERITE_HOE, Items.NETHERITE_INGOT); // Define epic items

    public static Item getRandomLoot() {
        // Define weights for rarities (e.g., 50% common, 30% uncommon, 15% rare, 5% epic)
        double rarityRoll = Math.random();

        if (rarityRoll < 0.05) {
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


