package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.DaleEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DaleRenderer extends MobEntityRenderer<DaleEntity, DaleModel<DaleEntity>> {
    public DaleRenderer(EntityRendererFactory.Context context) {
        super(context, new DaleModel<>(context.getPart(DaleModel.DALE)), 0.25f);
    }

    @Override
    public Identifier getTexture(DaleEntity entity) {
        return Identifier.of(EggsAndGrits.MOD_ID, "textures/entity/dale/dale_earnhardt.png");
    }

    @Override
    public void render(DaleEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
