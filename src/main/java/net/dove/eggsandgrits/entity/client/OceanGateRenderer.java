package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.OceanGateEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class OceanGateRenderer extends MobEntityRenderer<OceanGateEntity, OceanGateModel<OceanGateEntity>> {
    public final float size = 1;
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
                matrixStack.scale(this.size, this.size, this.size);

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
