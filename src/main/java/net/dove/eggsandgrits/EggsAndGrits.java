package net.dove.eggsandgrits;

import net.dove.eggsandgrits.entity.ModEntities;
import net.dove.eggsandgrits.entity.custom.*;
import net.dove.eggsandgrits.villager.ModVillagers;
import net.dove.eggsandgrits.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.*;
import net.dove.eggsandgrits.block.ModBlocks;
import net.dove.eggsandgrits.component.ModDataComponentTypes;
import net.dove.eggsandgrits.effect.ModEffects;
import net.dove.eggsandgrits.enchantment.ModEnchantmentEffects;
import net.dove.eggsandgrits.item.ModItemGroups;
import net.dove.eggsandgrits.item.ModItems;
import net.dove.eggsandgrits.potion.ModPotions;
import net.dove.eggsandgrits.sound.ModSounds;
import net.dove.eggsandgrits.util.HammerUsageEvent;


import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import net.minecraft.potion.Potions;

import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



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

		ModEntities.registerModEntities();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

		ModWorldGeneration.generateModWorldGen();

		EggsAndGritsStructures.registerStructureFeatures();

		ModVillagers.registerVillagers();


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

		StrippableBlockRegistry.register(ModBlocks.DRIFTWOOD_LOG, ModBlocks.STRIPPED_DRIFTWOOD_LOG);
		StrippableBlockRegistry.register(ModBlocks.DRIFTWOOD_WOOD, ModBlocks.STRIPPED_DRIFTWOOD_WOOD);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DRIFTWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DRIFTWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_LEAVES, 30, 60);

		FabricDefaultAttributeRegistry.register(ModEntities.MANTIS, MantisEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.LARRY, LarryEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.TINYGUY, TinyGuyEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.ATTACK_HELICOPTER, AttackHelicopterEntity.createAttributes());


		FabricDefaultAttributeRegistry.register(ModEntities.DALE, DaleEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.DABABY, DaBabyEntity.createAttributes());


		TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 3),
					new ItemStack(ModItems.BEANS, 8), 7, 2, 0.04f));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.DIAMOND, 9),
					new ItemStack(ModItems.CHILI, 2), 3, 4, 0.04f));
		});
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 3),
					new ItemStack(ModItems.CAULIFLOWER, 8), 7, 2, 0.04f));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.DIAMOND, 9),
					new ItemStack(ModItems.CAULIFLOWER_SEEDS, 2), 3, 4, 0.04f));
		});

		TradeOfferHelper.registerVillagerOffers(ModVillagers.KAUPENGER, 1, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 3),
					new ItemStack(ModItems.CAULIFLOWER, 8), 7, 2, 0.04f));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.DIAMOND, 9),
					new ItemStack(ModItems.CAULIFLOWER_SEEDS, 2), 3, 4, 0.04f));
		});
		TradeOfferHelper.registerVillagerOffers(ModVillagers.KAUPENGER, 2, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 3),
					new ItemStack(ModItems.MOUNTAIN_DEW, 8), 7, 2, 0.04f));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.DIAMOND, 9),
					new ItemStack(ModItems.MEATBALL, 2), 3, 4, 0.04f));
		});

		CustomPortalBuilder.beginPortal()
				.frameBlock(Blocks.GLOWSTONE)
				.lightWithFluid(Fluids.WATER)
				.destDimID(Identifier.of(EggsAndGrits.MOD_ID, "kaupendim"))
				.tintColor(0xE6E6FA)
				.registerPortal();

	}
}
