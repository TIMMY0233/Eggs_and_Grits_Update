package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.LarryEntity;
import net.dove.eggsandgrits.entity.custom.MantisEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class LarryRenderer extends MobEntityRenderer<LarryEntity, LarryModel<LarryEntity>> {
    public LarryRenderer(EntityRendererFactory.Context context) {
        super(context, new LarryModel<>(context.getPart(LarryModel.LARRY)), 0.25f);
    }

    @Override
    public Identifier getTexture(LarryEntity entity) {
        return Identifier.of(EggsAndGrits.MOD_ID, "textures/entity/larry/larry.png");
    }

    @Override
    public void render(LarryEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
