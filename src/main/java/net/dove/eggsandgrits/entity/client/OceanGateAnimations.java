package net.dove.eggsandgrits.entity.client;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class OceanGateAnimations {

    public static final Animation ANIM_OCEAN_GATE_DEATH = Animation.Builder.create(2f)//.looping()
            .addBoneAnimation("root",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("root",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 0f, 12.5f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.375f, AnimationHelper.createRotationalVector(-2.41f, -0.65f, -12.49f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(-5f, 0f, -7.5f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625f, AnimationHelper.createRotationalVector(-9.88f, 0.75f, 1.22f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(10.23f, 1.5f, 9.94f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.8343334f, AnimationHelper.createRotationalVector(-5.72f, 1.53f, -7.37f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(-1.71f, 18.39f, -17.07f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.125f, AnimationHelper.createRotationalVector(-12.13f, -9.98f, -25.68f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.2916767f, AnimationHelper.createRotationalVector(-9.06f, 1.6f, -44.34f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.4583433f, AnimationHelper.createRotationalVector(-11.77f, -43.39f, -57.28f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.7083433f, AnimationHelper.createRotationalVector(-19.93f, -14.82f, -12.2f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.875f, AnimationHelper.createRotationalVector(-23.88f, -6.33f, 9.02f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.9583433f, AnimationHelper.createRotationalVector(-9.59f, -11.23f, -2.62f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("root",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.7083433f, AnimationHelper.createScalingVector(1f, 1f, 0.96f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.7916767f, AnimationHelper.createScalingVector(1f, 1f, 0.72f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.875f, AnimationHelper.createScalingVector(0.22f, 0.25f, 0.11f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.9583433f, AnimationHelper.createScalingVector(-0.08f, 0.05f, 0.01f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(2f, AnimationHelper.createScalingVector(0f, 0.05f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("window",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, 0f, 19f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("tail",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, 0f, -37f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createTranslationalVector(0f, 0f, -46f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("tail",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("leg1",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(-7f, 4f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(-16f, 7f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("leg1",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("leg2",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(3f, 9f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(12f, 9f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("leg2",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createScalingVector(0.4f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR))).build();
}
