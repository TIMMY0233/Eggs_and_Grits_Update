package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.OceanGateEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class OceanGateRenderer extends MobEntityRenderer<OceanGateEntity, OceanGateModel<OceanGateEntity>> {
    public OceanGateRenderer(EntityRendererFactory.Context context) {
        super(context, new OceanGateModel<>(context.getPart(OceanGateModel.OCEAN_GATE)), 1f);
    }

    @Override
    public Identifier getTexture(OceanGateEntity entity) {
        return Identifier.of(EggsAndGrits.MOD_ID, "textures/entity/ocean_gate/ocean_gate.png");
    }

    @Override
    public void render(OceanGateEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(2f, 2f, 2f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
