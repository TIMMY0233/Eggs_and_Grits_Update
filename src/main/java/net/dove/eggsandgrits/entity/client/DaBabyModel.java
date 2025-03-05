package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.entity.custom.DaBabyEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class DaBabyModel<T extends DaBabyEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer DABABY = new EntityModelLayer(Identifier.of(EggsAndGrits.MOD_ID, "dababy"), "main");
    private final ModelPart bone;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart right_leg;
    private final ModelPart left_leg;
    public DaBabyModel(ModelPart root) {
        this.bone = root.getChild("bone");
        this.head = this.bone.getChild("head");
        this.body = this.bone.getChild("body");
        this.left_arm = this.bone.getChild("left_arm");
        this.right_arm = this.bone.getChild("right_arm");
        this.right_leg = this.bone.getChild("right_leg");
        this.left_leg = this.bone.getChild("left_leg");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 20.0F, 0.0F));

        ModelPartData head = bone.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

        ModelPartData body = bone.addChild("body", ModelPartBuilder.create().uv(4, 4).cuboid(-1.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 0.0F));

        ModelPartData left_arm = bone.addChild("left_arm", ModelPartBuilder.create().uv(10, 4).cuboid(0.0F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -1.5F, 0.0F));

        ModelPartData right_arm = bone.addChild("right_arm", ModelPartBuilder.create().uv(8, 12).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -2.0F, 0.0F));

        ModelPartData right_leg = bone.addChild("right_leg", ModelPartBuilder.create().uv(4, 12).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 1.0F, -0.5F));

        ModelPartData left_leg = bone.addChild("left_leg", ModelPartBuilder.create().uv(4, 12).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 1.0F, -0.5F));
        return TexturedModelData.of(modelData, 16, 16);
    }


        @Override
        public ModelPart getPart() {
                return bone;
            }

        @Override
        public void setAngles(DaBabyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            this.getPart().traverse().forEach(ModelPart::resetTransform);
            this.setHeadAngles(netHeadYaw, headPitch);

            this.animateMovement(DaBabyAnimations.ANIM_DABABY_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
            this.updateAnimation(entity.attackAnimationState, DaBabyAnimations.ANIM_DABABY_ATTACK, ageInTicks, 1f);

        }



    @Override
        public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
            bone.render(matrices, vertexConsumer, light, overlay, color);
        }


        private void setHeadAngles(float headYaw, float headPitch) {
            headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
            headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

            this.head.yaw = headYaw * 0.017453292F;
            this.head.pitch = headPitch * 0.017453292F;
        }
    }
