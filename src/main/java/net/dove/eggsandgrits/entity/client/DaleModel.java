package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.DaleEntity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class DaleModel<T extends DaleEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer DALE = new EntityModelLayer(Identifier.of(EggsAndGrits.MOD_ID, "dale"), "main");
        private final ModelPart waist;
        private final ModelPart body;
        private final ModelPart head;
        private final ModelPart hat;
        private final ModelPart Baseballhat;
        private final ModelPart rightArm;
        private final ModelPart rightItem;
        private final ModelPart leftArm;
        private final ModelPart leftItem;
        private final ModelPart rightLeg;
        private final ModelPart leftLeg;
        public DaleModel(ModelPart root) {
            this.waist = root.getChild("waist");
            this.body = this.waist.getChild("body");
            this.head = this.body.getChild("head");
            this.hat = this.head.getChild("hat");
            this.Baseballhat = this.hat.getChild("Baseballhat");
            this.rightArm = this.body.getChild("rightArm");
            this.rightItem = this.rightArm.getChild("rightItem");
            this.leftArm = this.body.getChild("leftArm");
            this.leftItem = this.leftArm.getChild("leftItem");
            this.rightLeg = this.body.getChild("rightLeg");
            this.leftLeg = this.body.getChild("leftLeg");
        }
        public static TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData waist = modelPartData.addChild("waist", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

            ModelPartData body = waist.addChild("body", ModelPartBuilder.create().uv(44, 0).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                    .uv(16, 45).cuboid(-4.7F, 0.0F, -2.5F, 9.0F, 12.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

            ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 29).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

            ModelPartData hat = head.addChild("hat", ModelPartBuilder.create().uv(32, 29).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

            ModelPartData Baseballhat = hat.addChild("Baseballhat", ModelPartBuilder.create(), ModelTransform.of(-0.05F, -9.575F, 0.2F, 0.1309F, 0.0F, 0.0F));

            ModelPartData cube_r1 = Baseballhat.addChild("cube_r1", ModelPartBuilder.create().uv(0, 15).cuboid(-4.4F, -6.8F, -4.3F, 8.8F, 5.0F, 8.7F, new Dilation(0.0F)), ModelTransform.of(0.05F, 2.575F, -0.5F, 0.0F, -1.5708F, 0.0F));

            ModelPartData cube_r2 = Baseballhat.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-4.6F, -1.6F, -8.4F, 9.3F, 2.0F, 13.0F, new Dilation(0.0F)), ModelTransform.of(0.05F, 1.375F, -0.5F, 0.0F, 3.1416F, 0.0F));

            ModelPartData rightArm = body.addChild("rightArm", ModelPartBuilder.create().uv(0, 45).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, 6.0F, 0.0F));

            ModelPartData rightItem = rightArm.addChild("rightItem", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.0F));

            ModelPartData leftArm = body.addChild("leftArm", ModelPartBuilder.create().uv(0, 45).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

            ModelPartData leftItem = leftArm.addChild("leftItem", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 7.0F, 1.0F));

            ModelPartData rightLeg = body.addChild("rightLeg", ModelPartBuilder.create().uv(48, 45).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

            ModelPartData leftLeg = body.addChild("leftLeg", ModelPartBuilder.create().uv(48, 45).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
            return TexturedModelData.of(modelData, 128, 128);
        }

    @Override
    public void setAngles(DaleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(DaleAnimations.ANIM_DALE_DRIVE, limbSwing, limbSwingAmount, 2f, 2.5f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        waist.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return waist;
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

}
