package net.dove.eggsandgrits;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.dove.eggsandgrits.block.ModBlocks;
import net.dove.eggsandgrits.util.ModModelPredicates;
import net.minecraft.client.render.RenderLayer;

public class EggsAndGritsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_GARNET_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_GARNET_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CAULIFLOWER_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BEANS_CROP, RenderLayer.getCutout());

        ModModelPredicates.registerModelPredicates();
    }
}