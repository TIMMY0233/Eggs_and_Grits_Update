package net.dove.eggsandgrits.item;


import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent CAULIFLOWER = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200), 0.15f).build();

    public static final FoodComponent GRITS = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200), 0.15f).build();

    public static final FoodComponent RITZ = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200), 0.15f).build();

    public static final FoodComponent EGGS = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200), 0.15f).build();

    public static final FoodComponent SYRUP = new FoodComponent.Builder().nutrition(10).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100), 1f).build();

    public static final FoodComponent CREAM = new FoodComponent.Builder().nutrition(10).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 3), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 2),1f).build();

    public static final FoodComponent BEANS = new FoodComponent.Builder().nutrition(1).saturationModifier(0.25f)
           .build();

    public static final FoodComponent CHILI = new FoodComponent.Builder().nutrition(1).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 1200,0), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40,2), 1f)
            .alwaysEdible()
            .build();




}

