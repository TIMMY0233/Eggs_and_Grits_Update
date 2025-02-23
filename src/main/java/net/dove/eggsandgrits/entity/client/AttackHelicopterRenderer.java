package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.AttackHelicopterEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class AttackHelicopterRenderer extends MobEntityRenderer<AttackHelicopterEntity, AttackHelicopterModel<AttackHelicopterEntity>> {
    public AttackHelicopterRenderer(EntityRendererFactory.Context context) {
        super(context, new AttackHelicopterModel<>(context.getPart(AttackHelicopterModel.ATTACKHELICOPTER)), 0.25f);
    }

    @Override
    public Identifier getTexture(AttackHelicopterEntity entity) {
        return Identifier.of(EggsAndGrits.MOD_ID, "textures/entity/attackhelicopter/attackhelicopter.png");
    }

    @Override
    public void render(AttackHelicopterEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(3f, 3f, 3f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
