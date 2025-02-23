package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.EggsAndGrits;

import net.dove.eggsandgrits.entity.custom.TinyGuyEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class TinyGuyModel< T extends TinyGuyEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer TINYGUY = new EntityModelLayer(Identifier.of(EggsAndGrits.MOD_ID, "tinyguy"), "main");
        private final ModelPart tinyguy;
        private final ModelPart left_arm;
        private final ModelPart right_arm;
        private final ModelPart Feet;
        private final ModelPart left_foot;
        private final ModelPart right_foot;
        private final ModelPart head;
        private final ModelPart body;
        public TinyGuyModel(ModelPart root) {
            this.tinyguy = root.getChild("tinyguy");
            this.left_arm = this.tinyguy.getChild("left_arm");
            this.right_arm = this.tinyguy.getChild("right_arm");
            this.Feet = this.tinyguy.getChild("Feet");
            this.left_foot = this.Feet.getChild("left_foot");
            this.right_foot = this.Feet.getChild("right_foot");
            this.head = this.tinyguy.getChild("head");
            this.body = this.tinyguy.getChild("body");
        }
        public static TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData tinyguy = modelPartData.addChild("tinyguy", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 20.0F, 0.0F));

            ModelPartData left_arm = tinyguy.addChild("left_arm", ModelPartBuilder.create().uv(8, 13).cuboid(-1.0F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -2.0F, 0.0F));

            ModelPartData right_arm = tinyguy.addChild("right_arm", ModelPartBuilder.create().uv(8, 8).cuboid(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -2.0F, 0.0F));

            ModelPartData Feet = tinyguy.addChild("Feet", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 0.0F));

            ModelPartData left_foot = Feet.addChild("left_foot", ModelPartBuilder.create().uv(14, 8).cuboid(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 0.0F, 0.0F));

            ModelPartData right_foot = Feet.addChild("right_foot", ModelPartBuilder.create().uv(14, 12).cuboid(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 0.0F, 0.0F));

            ModelPartData head = tinyguy.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.0F, 0.0F));

            ModelPartData body = tinyguy.addChild("body", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));
            return TexturedModelData.of(modelData, 32, 32);
        }


    @Override
    public void setAngles(TinyGuyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(TinyGuyAnimations.ANIM_TINYGUY_ROLL, limbSwing, limbSwingAmount, 2f, 2.5f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        tinyguy.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return tinyguy;
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }
}

