package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.VapeProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class VapeProjectileRenderer extends EntityRenderer<VapeProjectileEntity> {
    protected VapeProjectileModel model;


    public VapeProjectileRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new VapeProjectileModel(ctx.getPart(VapeProjectileModel.VAPE_RING));
    }

    @Override
    public void render(VapeProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        if (!entity.isGrounded()) {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw())));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getPitch()));
            matrices.translate(0, -1f, 0);
        } else {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getYaw()));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getPitch()));
            matrices.translate(0, -1.0f, 0);
        }

        VertexConsumer vertexconsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers,
                this.model.getLayer(Identifier.of(EggsAndGrits.MOD_ID, "textures/entity/vape_ring/vape_ring.png")), false, false);
        this.model.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(VapeProjectileEntity entity) {
        return Identifier.of(EggsAndGrits.MOD_ID, "textures/entity/vape_ring/vape_ring.png");
    }
}
