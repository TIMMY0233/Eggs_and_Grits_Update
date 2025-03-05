package net.dove.eggsandgrits.item;


import net.dove.eggsandgrits.effect.ModEffects;
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

    public static final FoodComponent CORN = new FoodComponent.Builder().nutrition(1).saturationModifier(0.25f)
            .build();

    public static final FoodComponent RAW_HOTDOG = new FoodComponent.Builder().nutrition(1).saturationModifier(0.25f)
            .build();

    public static final FoodComponent COOKED_HOTDOG = new FoodComponent.Builder().nutrition(5).saturationModifier(0.25f)
            .build();

    public static final FoodComponent CHILI = new FoodComponent.Builder().nutrition(1).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 1200,0), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40,2), 1f)
            .statusEffect(new StatusEffectInstance(ModEffects.DIARRHEA, 100,0), 0.25f)
            .alwaysEdible()
            .build();

    public static final FoodComponent BUTTER = new FoodComponent.Builder().nutrition(5).saturationModifier(0.75f)
            .build();

    public static final FoodComponent PEPPERCORN = new FoodComponent.Builder().nutrition(1).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(ModEffects.COUGH, 200,0),1f)
            .build();

    public static final FoodComponent PEPPER = new FoodComponent.Builder().nutrition(1).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(ModEffects.COUGH, 200,0),1f)
            .build();

    public static final FoodComponent WAFFLE = new FoodComponent.Builder().nutrition(10).saturationModifier(1.75f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 400,1), 0.5f)
            .build();

    public static final FoodComponent REDBULL = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 6), 1f)
            .alwaysEdible()
            .build();

    public static final FoodComponent TOAST = new FoodComponent.Builder().nutrition(7).saturationModifier(1f)
            .build();

    public static final FoodComponent TOAST_EGG = new FoodComponent.Builder().nutrition(9).saturationModifier(1.25f)
            .build();

    public static final FoodComponent TOAST_ONION = new FoodComponent.Builder().nutrition(8).saturationModifier(1.25f)
            .build();

    public static final FoodComponent FIESTADA = new FoodComponent.Builder().nutrition(12).saturationModifier(2.4f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 1200, 3), 1f)
            .alwaysEdible()
            .build();

    public static final FoodComponent MEATBALL = new FoodComponent.Builder().nutrition(4).saturationModifier(.5f)
            .build();

    public static final FoodComponent SAUSAGE = new FoodComponent.Builder().nutrition(5).saturationModifier(.5f)
            .build();

    public static final FoodComponent BACON = new FoodComponent.Builder().nutrition(5).saturationModifier(.15f)
            .build();

    public static final FoodComponent PANCAKE = new FoodComponent.Builder().nutrition(5).saturationModifier(.15f)
            .build();

    public static final FoodComponent QUAALUDE = new FoodComponent.Builder().nutrition(1).saturationModifier(2.4f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 1200, 3), 1f)
            .alwaysEdible()
            .build();

    public static final FoodComponent MYSTERY_MEAT = new FoodComponent.Builder().nutrition(2).saturationModifier(.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 1200, 1), .1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 400, 1), .1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 400, 1), .1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 400, 1), .1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 400, 1), .1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1), .1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 1), .1f)
            .alwaysEdible()
            .build();

    public static final FoodComponent SWEET_BABY_RAYS = new FoodComponent.Builder().nutrition(10).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100), 1f).build();

    public static final FoodComponent MOUNTAIN_DEW = new FoodComponent.Builder().nutrition(2).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100), 1f).build();

}

