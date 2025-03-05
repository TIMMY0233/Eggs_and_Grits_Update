package net.dove.eggsandgrits.datagen;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementsProvider extends FabricAdvancementProvider {
    public ModAdvancementsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry rootAdvancement = Advancement.Builder.create()
                .display(
                        Items.DIRT, // The display icon
                        Text.literal("Your First Dirt Block"), // The title
                        Text.literal("Now make a three by three"), // The description
                        Identifier.of(EggsAndGrits.MOD_ID,"textures/gui/advancements/backgrounds/salt_block.png"), // Background image used
                        AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("got_dirt", InventoryChangedCriterion.Conditions.items(Items.DIRT))
                .build(consumer, EggsAndGrits.MOD_ID + "/root");

        AdvancementEntry gritsAdvancement = Advancement.Builder.create().parent(rootAdvancement)
                .display(
                        ModItems.GRITS, // The display icon
                        Text.literal("Baby's First Grits"), // The title
                        Text.literal("Now make more"), // The description
                        null,
                        AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                .rewards(AdvancementRewards.Builder.experience(1000))
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("got_grits", InventoryChangedCriterion.Conditions.items(ModItems.GRITS))
                .build(consumer, EggsAndGrits.MOD_ID + "/got_grits");

        AdvancementEntry eggsAdvancement = Advancement.Builder.create().parent(rootAdvancement)
                .display(
                        ModItems.EGGS, // The display icon
                        Text.literal("Baby's First Eggs"), // The title
                        Text.literal("Now make more"), // The description
                        null,
                        AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                .rewards(AdvancementRewards.Builder.experience(1000))
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("got_eggs", InventoryChangedCriterion.Conditions.items(ModItems.EGGS))
                .build(consumer, EggsAndGrits.MOD_ID + "/got_eggs");

        AdvancementEntry hammerAdvancement = Advancement.Builder.create().parent(eggsAdvancement)
                .display(
                        ModItems.IRON_HAMMER, // The display icon
                        Text.literal("You found a hammer!"), // The title
                        Text.literal("Now find more"), // The description
                        null,
                        AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                .rewards(AdvancementRewards.Builder.experience(1000))
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("got_hammer", InventoryChangedCriterion.Conditions.items(ModItems.IRON_HAMMER))
                .build(consumer, EggsAndGrits.MOD_ID + "/got_hammer");

        AdvancementEntry eclipseAdvancement = Advancement.Builder.create().parent(hammerAdvancement)
                .display(
                        ModItems.ECLIPSE, // The display icon
                        Text.literal("Oh No"), // The title
                        Text.literal("Please be careful with that"), // The description
                        null,
                        AdvancementFrame.CHALLENGE, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                .rewards(AdvancementRewards.Builder.experience(1000))
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("got_eclipse", InventoryChangedCriterion.Conditions.items(ModItems.ECLIPSE))
                .build(consumer, EggsAndGrits.MOD_ID + "/got_eclipse");

        AdvancementEntry rawhotdogAdvancement = Advancement.Builder.create().parent(eclipseAdvancement)
                .display(
                        ModItems.RAW_HOTDOG, // The display icon
                        Text.literal("A Raw Hot Dog?"), // The title
                        Text.literal("Can I Boil This?"), // The description
                        null,
                        AdvancementFrame.GOAL, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("got_hot_dog", InventoryChangedCriterion.Conditions.items(ModItems.RAW_HOTDOG))
                .build(consumer, EggsAndGrits.MOD_ID + "/got_hot_dog");

        AdvancementEntry cookedhotdogAdvancement = Advancement.Builder.create().parent(rawhotdogAdvancement)
                .display(
                        ModItems.COOKED_HOTDOG, // The display icon
                        Text.literal("This Probably Tastes Better"), // The title
                        Text.literal("Ketchup or Mustard"), // The description
                        null,
                        AdvancementFrame.GOAL, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("got_cooked_hot_dog", InventoryChangedCriterion.Conditions.items(ModItems.COOKED_HOTDOG))
                .build(consumer, EggsAndGrits.MOD_ID + "/got_cooked_hot_dog");

    }
}
