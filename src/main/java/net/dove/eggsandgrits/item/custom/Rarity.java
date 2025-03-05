package net.dove.eggsandgrits.item.custom;

import net.minecraft.util.Formatting;

public enum Rarity {
    COMMON(Formatting.GRAY),
    UNCOMMON(Formatting.GREEN),
    RARE(Formatting.BLUE),
    EPIC(Formatting.LIGHT_PURPLE);

    private final Formatting color;

    Rarity(Formatting color) {
        this.color = color;
    }

    public Formatting getColor() {
        return color;
    }
}
