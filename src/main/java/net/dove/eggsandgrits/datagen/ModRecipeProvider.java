package net.dove.eggsandgrits.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.block.ModBlocks;
import net.dove.eggsandgrits.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> PINK_GARNET_SMELTABLES = List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE,
                ModBlocks.PINK_GARNET_DEEPSLATE_ORE);


        offerSmelting(exporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 200, "pink_garnet");


        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PINK_GARNET, RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_PINK_GARNET_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_PINK_GARNET)
                .criterion(hasItem(ModItems.RAW_PINK_GARNET), conditionsFromItem(ModItems.RAW_PINK_GARNET))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 9)
                .input(ModBlocks.RAW_PINK_GARNET_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.RAW_PINK_GARNET_BLOCK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHILI, 1)
                .input(ModItems.BEANS,8)
                .input(Items.BOWL)
                .criterion(hasItem(ModItems.BEANS), conditionsFromItem(ModItems.BEANS))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GRITS, 1)
                .input(ModItems.CREAM)
                .input(ModItems.BUTTER)
                .input(Items.BOWL)
                .input(Items.WATER_BUCKET)
                .criterion(hasItem(ModItems.CREAM), conditionsFromItem(ModItems.CREAM))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 32)
                .input(ModBlocks.MAGIC_BLOCK)
                .criterion(hasItem(ModBlocks.MAGIC_BLOCK), conditionsFromItem(ModBlocks.MAGIC_BLOCK))
                .offerTo(exporter, Identifier.of(EggsAndGrits.MOD_ID, "raw_pink_garnet_from_magic_block"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CREAM, 1)
                .input(Items.MILK_BUCKET)
                .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                .offerTo(exporter, Identifier.of(EggsAndGrits.MOD_ID, "cream"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BUTTER, 2)
                .input(ModItems.CREAM)
                .criterion(hasItem(ModItems.CREAM), conditionsFromItem(ModItems.CREAM))
                .offerTo(exporter, Identifier.of(EggsAndGrits.MOD_ID, "butter"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DIAMOND_HAMMER)
                .pattern("DDD")
                .pattern("DSD")
                .pattern(" S ")
                .input('D', Items.DIAMOND)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        offerSmithingTrimRecipe(exporter, ModItems.KAUPEN_SMITHING_TEMPLATE, Identifier.of(EggsAndGrits.MOD_ID, "kaupen"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GROUND_CORN, 4)
                .input(ModItems.CORN)
                .criterion(hasItem(ModItems.CORN), conditionsFromItem(ModItems.CORN))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PEPPER, 4)
                .input(ModItems.PEPPERCORN)
                .criterion(hasItem(ModItems.PEPPERCORN), conditionsFromItem(ModItems.PEPPERCORN))
                .offerTo(exporter);

        /*ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRIFTWOOD_PLANKS, 4)
                .input(ModBlocks.DRIFTWOOD_LOG)
                .criterion(hasItem(ModBlocks.DRIFTWOOD_LOG), conditionsFromItem(ModBlocks.DRIFTWOOD_LOG))
                .offerTo(exporter);

         */

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.DRIFTWOOD_DOOR, 3)
                .pattern("DD")
                .pattern("DD")
                .pattern("DD")
                .input('D', ModBlocks.DRIFTWOOD_PLANKS)
                .criterion(hasItem(ModBlocks.DRIFTWOOD_PLANKS), conditionsFromItem(ModBlocks.DRIFTWOOD_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.DRIFTWOOD_TRAPDOOR, 2)
                .pattern("DDD")
                .pattern("DDD")
                .pattern("   ")
                .input('D', ModBlocks.DRIFTWOOD_PLANKS)
                .criterion(hasItem(ModBlocks.DRIFTWOOD_PLANKS), conditionsFromItem(ModBlocks.DRIFTWOOD_PLANKS))
                .offerTo(exporter);


      }
}