package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.VapeProjectileEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class VapeProjectileModel extends EntityModel<VapeProjectileEntity> {
    public static final EntityModelLayer VAPE_RING = new EntityModelLayer(Identifier.of(EggsAndGrits.MOD_ID, "vape_ring"), "main");
    private final ModelPart Vape_ring_1;
    private final ModelPart bone;
    private final ModelPart vape_cloud_quarter4;
    private final ModelPart vape_cloud_quarter3;
    private final ModelPart vape_cloud_quarter2;
    private final ModelPart vape_cloud_quarter;
    private final ModelPart Vape_ring_2;
    private final ModelPart Vape_ring_half;
    private final ModelPart Vape_ring_3;
    public VapeProjectileModel(ModelPart root) {
        this.Vape_ring_1 = root.getChild("Vape_ring_1");
        this.bone = this.Vape_ring_1.getChild("bone");
        this.vape_cloud_quarter4 = this.bone.getChild("vape_cloud_quarter4");
        this.vape_cloud_quarter3 = this.bone.getChild("vape_cloud_quarter3");
        this.vape_cloud_quarter2 = this.Vape_ring_1.getChild("vape_cloud_quarter2");
        this.vape_cloud_quarter = this.Vape_ring_1.getChild("vape_cloud_quarter");
        this.Vape_ring_2 = root.getChild("Vape_ring_2");
        this.Vape_ring_half = this.Vape_ring_2.getChild("Vape_ring_half");
        this.Vape_ring_3 = root.getChild("Vape_ring_3");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Vape_ring_1 = modelPartData.addChild("Vape_ring_1", ModelPartBuilder.create(), ModelTransform.of(0.0F, 16.0F, -7.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData bone = Vape_ring_1.addChild("bone", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData vape_cloud_quarter4 = bone.addChild("vape_cloud_quarter4", ModelPartBuilder.create().uv(0, 14).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 14).cuboid(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 14).cuboid(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 16).cuboid(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 8).cuboid(-1.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 10).cuboid(-1.0F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 14).cuboid(1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 0).cuboid(2.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 2).cuboid(3.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 16).cuboid(3.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 4).cuboid(4.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 6).cuboid(5.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 0.0F, 5.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData vape_cloud_quarter3 = bone.addChild("vape_cloud_quarter3", ModelPartBuilder.create().uv(12, 2).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 12).cuboid(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 4).cuboid(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 6).cuboid(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 12).cuboid(1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 8).cuboid(1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 10).cuboid(2.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 16).cuboid(3.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 12).cuboid(3.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 14).cuboid(4.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 12).cuboid(5.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 0.0F, -3.0F));

        ModelPartData vape_cloud_quarter2 = Vape_ring_1.addChild("vape_cloud_quarter2", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 2).cuboid(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 8).cuboid(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 4).cuboid(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 6).cuboid(1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 8).cuboid(1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 10).cuboid(2.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 0).cuboid(3.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 10).cuboid(3.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 10).cuboid(4.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(5.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 0.0F, 5.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData vape_cloud_quarter = Vape_ring_1.addChild("vape_cloud_quarter", ModelPartBuilder.create().uv(4, 4).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 6).cuboid(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 6).cuboid(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 8).cuboid(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 2).cuboid(1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 0).cuboid(1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 2).cuboid(2.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(3.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 4).cuboid(3.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 0.0F, -3.0F));

        ModelPartData Vape_ring_2 = modelPartData.addChild("Vape_ring_2", ModelPartBuilder.create().uv(2, 1).cuboid(0.0F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(-1.0F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(1.0F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(1.0F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(2.0F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(2.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(3.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(3.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(3.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(3.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(2.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(2.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(0.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.0F, -1.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData Vape_ring_half = Vape_ring_2.addChild("Vape_ring_half", ModelPartBuilder.create().uv(2, 1).cuboid(1.0F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(1.0F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(2.0F, -1.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(2.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(3.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(3.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(3.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(3.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(2.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(2.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData Vape_ring_3 = modelPartData.addChild("Vape_ring_3", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.0F, 6.0F, -1.5708F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(VapeProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        Vape_ring_1.render(matrices, vertexConsumer, light, overlay, color);
        Vape_ring_2.render(matrices, vertexConsumer, light, overlay, color);
        Vape_ring_3.render(matrices, vertexConsumer, light, overlay, color);
    }
}
