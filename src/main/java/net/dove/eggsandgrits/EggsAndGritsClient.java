package net.dove.eggsandgrits;

import net.dove.eggsandgrits.entity.ModEntities;
import net.dove.eggsandgrits.entity.client.*;
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
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ONION_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BEANS_CROP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CORN_CROP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RICE_CROP, RenderLayer.getCutout());


        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEPPERCORN_BUSH_BLOCK, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DRIFTWOOD_SAPLING, RenderLayer.getCutout());

        ModModelPredicates.registerModelPredicates();

        EntityModelLayerRegistry.registerModelLayer(MantisModel.MANTIS, MantisModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MANTIS, MantisRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(LarryModel.LARRY, LarryModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.LARRY, LarryRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(DaleModel.DALE, DaleModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DALE, DaleRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(DaBabyModel.DABABY, DaBabyModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DABABY, DaBabyRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(TinyGuyModel.TINYGUY, TinyGuyModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.TINYGUY, TinyGuyRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(VapeProjectileModel.VAPE_RING, VapeProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.VAPE_RING, VapeProjectileRenderer::new);

        EntityRendererRegistry.register(ModEntities.CHAIR, ChairRenderer::new);
        EntityRendererRegistry.register(ModEntities.STOOL, StoolRenderer::new);

        EntityRendererRegistry.register(ModEntities.HOT_DOG_ROLLER, HotDogRollerRenderer::new);
    }
}