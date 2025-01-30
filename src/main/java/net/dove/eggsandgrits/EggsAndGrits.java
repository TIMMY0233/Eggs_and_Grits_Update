package net.dove.eggsandgrits;

import net.fabricmc.api.ModInitializer;


import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.dove.eggsandgrits.block.ModBlocks;
import net.dove.eggsandgrits.component.ModDataComponentTypes;
import net.dove.eggsandgrits.effect.ModEffects;
import net.dove.eggsandgrits.enchantment.ModEnchantmentEffects;
import net.dove.eggsandgrits.item.ModItemGroups;
import net.dove.eggsandgrits.item.ModItems;
import net.dove.eggsandgrits.potion.ModPotions;
import net.dove.eggsandgrits.sound.ModSounds;
import net.dove.eggsandgrits.util.HammerUsageEvent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;

import net.minecraft.item.Items;

import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

// Very important comment
public class EggsAndGrits implements ModInitializer {
	public static final String MOD_ID = "eggsandgrits";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModSounds.registerSounds();

		ModEnchantmentEffects.registerEnchantmentEffects();

		ModPotions.registerPotions();

		ModEffects.registerEffects();

		ModDataComponentTypes.registerDataComponentTypes();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());



		/*AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (entity instanceof SheepEntity sheepEntity && !world.isClient()) {
				if (player.getMainHandStack().getItem() == Items.END_ROD) {
					player.sendMessage(Text.literal("The Player just hit a sheep with an END ROD!"));
					player.getMainHandStack().decrement(1);
					sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,600,6));

				}
				return ActionResult.PASS;
			}

			return ActionResult.SUCCESS;
		});*/


		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION);
		});
		CompostingChanceRegistry.INSTANCE.add(ModItems.CAULIFLOWER, 0.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CAULIFLOWER_SEEDS, 0.25f);
	}
}
