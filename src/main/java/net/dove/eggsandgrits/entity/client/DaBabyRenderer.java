package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.DaBabyEntity;
import net.dove.eggsandgrits.entity.custom.LarryEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DaBabyRenderer extends MobEntityRenderer<DaBabyEntity, DaBabyModel<DaBabyEntity>> {
    public DaBabyRenderer(EntityRendererFactory.Context context) {
        super(context, new DaBabyModel<>(context.getPart(DaBabyModel.DABABY)), 0.15f);
    }

    @Override
    public Identifier getTexture(DaBabyEntity entity) {
        return Identifier.of(EggsAndGrits.MOD_ID, "textures/entity/dababy/dababy.png");
    }

    @Override
    public void render(DaBabyEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(5, 5, 5);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
