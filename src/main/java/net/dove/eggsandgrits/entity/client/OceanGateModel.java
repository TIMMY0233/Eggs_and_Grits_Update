package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.entity.custom.OceanGateEntity;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.dove.eggsandgrits.EggsAndGrits;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class OceanGateModel<T extends OceanGateEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer OCEAN_GATE = new EntityModelLayer(Identifier.of(EggsAndGrits.MOD_ID, "ocean_gate"), "root");

    private final ModelPart root;
    private final ModelPart main;
    private final ModelPart jetthing3;
    private final ModelPart jetthing4;
    private final ModelPart jetthing2;
    private final ModelPart jetthing;
    private final ModelPart window;
    private final ModelPart tail;
    private final ModelPart leg1;
    private final ModelPart leg2;
    public OceanGateModel(ModelPart root) {
        this.root = root.getChild("root");
        this.main = this.root.getChild("main");
        this.jetthing3 = this.main.getChild("jetthing3");
        this.jetthing4 = this.main.getChild("jetthing4");
        this.jetthing2 = this.main.getChild("jetthing2");
        this.jetthing = this.main.getChild("jetthing");
        this.window = this.root.getChild("window");
        this.tail = this.root.getChild("tail");
        this.leg1 = this.root.getChild("leg1");
        this.leg2 = this.root.getChild("leg2");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData main = root.addChild("main", ModelPartBuilder.create().uv(116, 540).cuboid(-28.0F, 0.0F, -60.0F, 56.0F, 56.0F, 116.0F, new Dilation(0.0F))
                .uv(116, 712).cuboid(-20.0F, 56.0F, -56.0F, 40.0F, 4.0F, 112.0F, new Dilation(0.0F))
                .uv(116, 828).cuboid(-20.0F, -4.0F, -56.0F, 40.0F, 4.0F, 112.0F, new Dilation(0.0F))
                .uv(116, 1004).cuboid(-4.0F, -20.0F, 36.0F, 8.0F, 16.0F, 4.0F, new Dilation(0.0F))
                .uv(460, 572).cuboid(-4.0F, -8.0F, -36.0F, 8.0F, 4.0F, 56.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -76.0F, 0.0F));

        ModelPartData side_r1 = main.addChild("side_r1", ModelPartBuilder.create().uv(420, 820).cuboid(-16.0F, -4.0F, -52.0F, 40.0F, 4.0F, 104.0F, new Dilation(0.0F)), ModelTransform.of(28.0F, 24.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData side_r2 = main.addChild("side_r2", ModelPartBuilder.create().uv(420, 712).cuboid(-16.0F, -8.0F, -52.0F, 40.0F, 4.0F, 104.0F, new Dilation(0.0F)), ModelTransform.of(-36.0F, 24.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData jetthing3 = main.addChild("jetthing3", ModelPartBuilder.create(), ModelTransform.pivot(36.0F, 42.0F, -50.0F));

        ModelPartData side_r3 = jetthing3.addChild("side_r3", ModelPartBuilder.create().uv(364, 1004).cuboid(-2.0F, 0.0F, -2.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 1.5708F));

        ModelPartData side_r4 = jetthing3.addChild("side_r4", ModelPartBuilder.create().uv(276, 988).cuboid(-6.0F, -4.0F, -6.0F, 12.0F, 8.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 12.0F, 0.0F, -1.5708F, 1.5708F));

        ModelPartData jetthing4 = main.addChild("jetthing4", ModelPartBuilder.create(), ModelTransform.pivot(-32.0F, 42.0F, -50.0F));

        ModelPartData side_r5 = jetthing4.addChild("side_r5", ModelPartBuilder.create().uv(364, 1004).cuboid(-2.0F, 0.0F, -2.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 1.5708F));

        ModelPartData side_r6 = jetthing4.addChild("side_r6", ModelPartBuilder.create().uv(276, 988).cuboid(-6.0F, -4.0F, -6.0F, 12.0F, 8.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, 12.0F, 0.0F, -1.5708F, 1.5708F));

        ModelPartData jetthing2 = main.addChild("jetthing2", ModelPartBuilder.create(), ModelTransform.pivot(24.0F, 12.0F, 0.0F));

        ModelPartData side_r7 = jetthing2.addChild("side_r7", ModelPartBuilder.create().uv(140, 1004).cuboid(-16.0F, -12.0F, 4.0F, 12.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData side_r8 = jetthing2.addChild("side_r8", ModelPartBuilder.create().uv(276, 988).cuboid(-16.0F, -12.0F, 0.0F, 12.0F, 8.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 12.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData jetthing = main.addChild("jetthing", ModelPartBuilder.create(), ModelTransform.pivot(-44.0F, 24.0F, 0.0F));

        ModelPartData side_r9 = jetthing.addChild("side_r9", ModelPartBuilder.create().uv(276, 988).cuboid(-16.0F, -12.0F, 0.0F, 12.0F, 8.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData side_r10 = jetthing.addChild("side_r10", ModelPartBuilder.create().uv(140, 1004).cuboid(-16.0F, -12.0F, 4.0F, 12.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -12.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData window = root.addChild("window", ModelPartBuilder.create().uv(420, 928).cuboid(-24.0F, -44.0F, 12.0F, 48.0F, 48.0F, 4.0F, new Dilation(0.0F))
                .uv(524, 964).cuboid(-16.0F, -36.0F, 4.0F, 32.0F, 32.0F, 4.0F, new Dilation(0.0F))
                .uv(324, 944).cuboid(-20.0F, -40.0F, 8.0F, 40.0F, 40.0F, 4.0F, new Dilation(0.0F))
                .uv(220, 988).cuboid(-12.0F, -32.0F, 0.0F, 24.0F, 24.0F, 4.0F, new Dilation(0.0F))
                .uv(324, 988).cuboid(-8.0F, -28.0F, -4.0F, 16.0F, 16.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -28.0F, -76.0F));

        ModelPartData tail = root.addChild("tail", ModelPartBuilder.create().uv(116, 944).cuboid(-24.0F, -32.0F, -36.0F, 48.0F, 48.0F, 4.0F, new Dilation(0.0F))
                .uv(460, 656).cuboid(-24.0F, -32.0F, -40.0F, 48.0F, 52.0F, 4.0F, new Dilation(0.0F))
                .uv(220, 944).cuboid(-24.0F, -32.0F, -32.0F, 48.0F, 40.0F, 4.0F, new Dilation(0.0F))
                .uv(524, 928).cuboid(-20.0F, -32.0F, -28.0F, 40.0F, 32.0F, 4.0F, new Dilation(0.0F))
                .uv(412, 980).cuboid(-16.0F, -32.0F, -24.0F, 32.0F, 28.0F, 4.0F, new Dilation(0.0F))
                .uv(564, 656).cuboid(-12.0F, -32.0F, -20.0F, 24.0F, 24.0F, 4.0F, new Dilation(0.0F))
                .uv(564, 684).cuboid(-12.0F, -32.0F, -16.0F, 24.0F, 20.0F, 4.0F, new Dilation(0.0F))
                .uv(484, 980).cuboid(-8.0F, -32.0F, -12.0F, 16.0F, 16.0F, 4.0F, new Dilation(0.0F))
                .uv(364, 988).cuboid(-8.0F, -32.0F, -8.0F, 16.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(172, 1004).cuboid(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 4.0F, new Dilation(0.0F))
                .uv(196, 1004).cuboid(-4.0F, -32.0F, 0.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -44.0F, 96.0F));

        ModelPartData leg1 = root.addChild("leg1", ModelPartBuilder.create(), ModelTransform.pivot(40.0F, -8.0F, -8.0F));

        ModelPartData cube_r1 = leg1.addChild("cube_r1", ModelPartBuilder.create().uv(460, 540).cuboid(-40.0F, -4.0F, -4.0F, 96.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r2 = leg1.addChild("cube_r2", ModelPartBuilder.create().uv(164, 996).cuboid(-4.0F, -4.0F, 0.0F, 20.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(116, 996).cuboid(-4.0F, -4.0F, -84.0F, 20.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-12.0F, -12.0F, 48.0F, 0.0F, 0.0F, 0.8727F));

        ModelPartData leg2 = root.addChild("leg2", ModelPartBuilder.create(), ModelTransform.of(-40.0F, -8.0F, 8.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r3 = leg2.addChild("cube_r3", ModelPartBuilder.create().uv(460, 644).cuboid(-28.0F, -4.0F, -4.0F, 72.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-32.0F, 2.0F, 36.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r4 = leg2.addChild("cube_r4", ModelPartBuilder.create().uv(460, 632).cuboid(-28.0F, -4.0F, -4.0F, 72.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-32.0F, 2.0F, -20.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r5 = leg2.addChild("cube_r5", ModelPartBuilder.create().uv(460, 556).cuboid(-40.0F, -4.0F, -4.0F, 96.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r6 = leg2.addChild("cube_r6", ModelPartBuilder.create().uv(532, 1000).cuboid(-4.0F, -4.0F, 0.0F, 20.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(484, 1000).cuboid(-4.0F, -4.0F, -84.0F, 20.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-12.0F, -12.0F, 48.0F, 0.0F, 0.0F, 0.8727F));
        return TexturedModelData.of(modelData, 1024, 1024);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.window.yaw = headYaw * 0.017453292F;
        this.window.pitch = headPitch * 0.017453292F;
    }

    @Override
    public ModelPart getPart() {
        return root;
    }

    @Override
    public void setAngles(OceanGateEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.updateAnimation(entity.deathAnimationState, OceanGateAnimations.ANIM_OCEAN_GATE_DEATH, ageInTicks, 1f);
    }


}
