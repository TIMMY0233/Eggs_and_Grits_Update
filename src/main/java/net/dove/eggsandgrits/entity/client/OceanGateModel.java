package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.entity.custom.OceanGateEntity;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.dove.eggsandgrits.EggsAndGrits;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
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
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create().uv(70, 103).cuboid(-1.0F, -24.0F, 9.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(86, 8).cuboid(-1.0F, -21.0F, -9.0F, 2.0F, 1.0F, 14.0F, new Dilation(0.0F))
                .uv(-29, -26).cuboid(-4.0F, -3.0F, -14.0F, 8.0F, 1.0F, 28.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData main = root.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, 0.0F, -15.0F, 14.0F, 14.0F, 29.0F, new Dilation(0.0F))
                .uv(0, 43).cuboid(-5.0F, 14.0F, -14.0F, 10.0F, 1.0F, 28.0F, new Dilation(0.0F))
                .uv(0, 73).cuboid(-5.0F, -1.0F, -14.0F, 10.0F, 1.0F, 28.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -19.0F, 0.0F));

        ModelPartData side_r1 = main.addChild("side_r1", ModelPartBuilder.create().uv(76, 71).cuboid(-4.0F, -1.0F, -13.0F, 10.0F, 1.0F, 26.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 6.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData side_r2 = main.addChild("side_r2", ModelPartBuilder.create().uv(76, 43).cuboid(-4.0F, -2.0F, -13.0F, 10.0F, 1.0F, 26.0F, new Dilation(0.0F)), ModelTransform.of(-9.0F, 6.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData jetthing3 = main.addChild("jetthing3", ModelPartBuilder.create(), ModelTransform.pivot(9.0F, 10.5F, -12.5F));

        ModelPartData side_r3 = jetthing3.addChild("side_r3", ModelPartBuilder.create().uv(61, 97).cuboid(-0.5F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 1.5708F));

        ModelPartData side_r4 = jetthing3.addChild("side_r4", ModelPartBuilder.create().uv(58, 95).cuboid(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 3.0F, 0.0F, -1.5708F, 1.5708F));

        ModelPartData jetthing4 = main.addChild("jetthing4", ModelPartBuilder.create(), ModelTransform.pivot(-8.0F, 10.5F, -12.5F));

        ModelPartData side_r5 = jetthing4.addChild("side_r5", ModelPartBuilder.create().uv(61, 97).cuboid(-0.5F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 1.5708F));

        ModelPartData side_r6 = jetthing4.addChild("side_r6", ModelPartBuilder.create().uv(58, 95).cuboid(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 3.0F, 0.0F, -1.5708F, 1.5708F));

        ModelPartData jetthing2 = main.addChild("jetthing2", ModelPartBuilder.create(), ModelTransform.pivot(6.0F, 3.0F, 0.0F));

        ModelPartData side_r7 = jetthing2.addChild("side_r7", ModelPartBuilder.create().uv(49, 93).cuboid(-4.0F, -3.0F, 1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData side_r8 = jetthing2.addChild("side_r8", ModelPartBuilder.create().uv(58, 95).cuboid(-4.0F, -3.0F, 0.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 3.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData jetthing = main.addChild("jetthing", ModelPartBuilder.create(), ModelTransform.pivot(-11.0F, 6.0F, 0.0F));

        ModelPartData side_r9 = jetthing.addChild("side_r9", ModelPartBuilder.create().uv(58, 95).cuboid(-4.0F, -3.0F, 0.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData side_r10 = jetthing.addChild("side_r10", ModelPartBuilder.create().uv(49, 93).cuboid(-4.0F, -3.0F, 1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData window = root.addChild("window", ModelPartBuilder.create().uv(76, 99).cuboid(-6.0F, -11.0F, 3.0F, 12.0F, 12.0F, 1.0F, new Dilation(0.0F))
                .uv(112, 29).cuboid(-4.0F, -9.0F, 1.0F, 8.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(26, 103).cuboid(-5.0F, -10.0F, 2.0F, 10.0F, 10.0F, 1.0F, new Dilation(0.0F))
                .uv(66, 112).cuboid(-3.0F, -8.0F, 0.0F, 6.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 96).cuboid(-2.0F, -7.0F, -1.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -7.0F, -19.0F));

        ModelPartData tail = root.addChild("tail", ModelPartBuilder.create().uv(102, 99).cuboid(-6.0F, -8.0F, -9.0F, 12.0F, 12.0F, 1.0F, new Dilation(0.0F))
                .uv(86, 29).cuboid(-6.0F, -8.0F, -10.0F, 12.0F, 13.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 103).cuboid(-6.0F, -8.0F, -8.0F, 12.0F, 10.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 103).cuboid(-5.0F, -8.0F, -7.0F, 10.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 112).cuboid(-4.0F, -8.0F, -6.0F, 8.0F, 7.0F, 1.0F, new Dilation(0.0F))
                .uv(80, 112).cuboid(-3.0F, -8.0F, -5.0F, 6.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 112).cuboid(-3.0F, -8.0F, -4.0F, 6.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(112, 38).cuboid(-2.0F, -8.0F, -3.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(108, 112).cuboid(-2.0F, -8.0F, -2.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(70, 108).cuboid(-1.0F, -8.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 116).cuboid(-1.0F, -8.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.0F, 24.0F));

        ModelPartData leg1 = root.addChild("leg1", ModelPartBuilder.create(), ModelTransform.pivot(10.0F, -2.0F, -2.0F));

        ModelPartData cube_r1 = leg1.addChild("cube_r1", ModelPartBuilder.create().uv(86, 0).cuboid(-10.0F, -1.0F, -1.0F, 24.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r2 = leg1.addChild("cube_r2", ModelPartBuilder.create().uv(12, 114).cuboid(-1.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 114).cuboid(-1.0F, -1.0F, -21.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -3.0F, 12.0F, 0.0F, 0.0F, 0.8727F));

        ModelPartData leg2 = root.addChild("leg2", ModelPartBuilder.create(), ModelTransform.of(-10.0F, -2.0F, 2.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r3 = leg2.addChild("cube_r3", ModelPartBuilder.create().uv(86, 26).cuboid(-7.0F, -1.0F, -1.0F, 18.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 0.5F, 9.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r4 = leg2.addChild("cube_r4", ModelPartBuilder.create().uv(86, 23).cuboid(-7.0F, -1.0F, -1.0F, 18.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 0.5F, -5.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r5 = leg2.addChild("cube_r5", ModelPartBuilder.create().uv(86, 4).cuboid(-10.0F, -1.0F, -1.0F, 24.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r6 = leg2.addChild("cube_r6", ModelPartBuilder.create().uv(36, 114).cuboid(-1.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 114).cuboid(-1.0F, -1.0F, -21.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -3.0F, 12.0F, 0.0F, 0.0F, 0.8727F));
        return TexturedModelData.of(modelData, 256, 256);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
    }


    @Override
    public ModelPart getPart() {
        return root;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

    }
}
