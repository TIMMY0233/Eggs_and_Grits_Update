package net.dove.eggsandgrits;

import net.dove.eggsandgrits.entity.ModEntities;
import net.dove.eggsandgrits.entity.client.LarryModel;
import net.dove.eggsandgrits.entity.client.LarryRenderer;
import net.dove.eggsandgrits.entity.client.MantisModel;
import net.dove.eggsandgrits.entity.client.MantisRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.dove.eggsandgrits.block.ModBlocks;
import net.dove.eggsandgrits.util.ModModelPredicates;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class EggsAndGritsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_GARNET_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_GARNET_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CAULIFLOWER_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BEANS_CROP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CORN_CROP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEPPERCORN_BUSH_BLOCK, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DRIFTWOOD_SAPLING, RenderLayer.getCutout());

        ModModelPredicates.registerModelPredicates();

        EntityModelLayerRegistry.registerModelLayer(MantisModel.MANTIS, MantisModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MANTIS, MantisRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(LarryModel.LARRY, LarryModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.LARRY, LarryRenderer::new);
    }
}