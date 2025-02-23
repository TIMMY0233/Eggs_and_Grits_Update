package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.AttackHelicopterEntity;
import net.dove.eggsandgrits.entity.custom.TinyGuyEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class AttackHelicopterModel< T extends AttackHelicopterEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer ATTACKHELICOPTER = new EntityModelLayer(Identifier.of(EggsAndGrits.MOD_ID, "attackhelicopter"), "main");
    private final ModelPart root;
    private final ModelPart propellers;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart tail_propellers;
    private final ModelPart left_tail;
    private final ModelPart right_tail;
    public AttackHelicopterModel(ModelPart root) {
        this.root = root.getChild("root");
        this.propellers = this.root.getChild("propellers");
        this.body = this.root.getChild("body");
        this.tail = this.root.getChild("tail");
        this.tail_propellers = this.root.getChild("tail_propellers");
        this.left_tail = this.tail_propellers.getChild("left_tail");
        this.right_tail = this.tail_propellers.getChild("right_tail");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 22.0F, -2.0F));

        ModelPartData propellers = root.addChild("propellers", ModelPartBuilder.create().uv(0, 19).cuboid(0.0F, -0.7F, -7.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 19).cuboid(-1.0F, -0.7F, 1.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(20, 26).cuboid(-1.0F, -0.7F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.3F, -1.0F));

        ModelPartData propeller1_r1 = propellers.addChild("propeller1_r1", ModelPartBuilder.create().uv(0, 19).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.3F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData propeller4_r1 = propellers.addChild("propeller4_r1", ModelPartBuilder.create().uv(0, 19).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.3F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -5.0F, 4.0F, 2.0F, 10.0F, new Dilation(0.0F))
                .uv(3, 2).cuboid(-2.0F, 0.0F, -4.0F, 4.0F, 1.0F, 7.0F, new Dilation(0.0F))
                .uv(28, 12).cuboid(-0.5F, 1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(28, 16).cuboid(1.0F, 1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(28, 14).cuboid(-2.0F, 1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData tail = root.addChild("tail", ModelPartBuilder.create().uv(28, 0).cuboid(-1.0F, 0.0F, -5.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(28, 3).cuboid(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(28, 6).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(29, 10).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 10.0F));

        ModelPartData tail_propellers = root.addChild("tail_propellers", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -3.5F, 10.0F));

        ModelPartData left_tail = tail_propellers.addChild("left_tail", ModelPartBuilder.create(), ModelTransform.pivot(1.5F, 0.0F, 0.0F));

        ModelPartData propeller3_r1 = left_tail.addChild("propeller3_r1", ModelPartBuilder.create().uv(0, 26).cuboid(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.8727F, 0.0F, 0.0F));

        ModelPartData right_tail = tail_propellers.addChild("right_tail", ModelPartBuilder.create(), ModelTransform.pivot(-1.5F, 0.0F, 0.0F));

        ModelPartData propeller4_r2 = right_tail.addChild("propeller4_r2", ModelPartBuilder.create().uv(10, 26).cuboid(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }



    @Override
    public void setAngles(AttackHelicopterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(AttackHelicopterAnimations.ANIM_HELI_FLYING, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, AttackHelicopterAnimations.ANIM_HELI_IDLE, ageInTicks, 1f);

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.root.yaw = headYaw * 0.017453292F;
        this.root.pitch = headPitch * 0.017453292F;
    }

}

