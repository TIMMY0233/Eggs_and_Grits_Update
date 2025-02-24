package net.dove.eggsandgrits.entity.client;


import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.MissileProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class MissileEntityRenderer extends EntityRenderer<MissileProjectileEntity> {
    protected MissileEntityModel model;

    public MissileEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new MissileEntityModel(ctx.getPart(MissileEntityModel.MISSILE));
    }

    @Override
    public void render(MissileProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

            //matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getYaw()));
            //matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getPitch()));
            matrices.translate(0,-1.2 , 0);


        VertexConsumer vertexconsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers,
                this.model.getLayer(Identifier.of(EggsAndGrits.MOD_ID, "textures/entity/missile/missile.png")), false, false);
        this.model.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(MissileProjectileEntity entity) {
        return Identifier.of(EggsAndGrits.MOD_ID, "textures/entity/missile/missile.png");
    }
}


