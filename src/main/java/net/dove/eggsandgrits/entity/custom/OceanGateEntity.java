package net.dove.eggsandgrits.entity.custom;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.world.World;

public class OceanGateEntity extends WaterCreatureEntity {


    public OceanGateEntity(EntityType<? extends WaterCreatureEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new AquaticMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new YawAdjustingLookControl(this, 10);
        this.setCanPickUpLoot(true);
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimAroundGoal(this, (double) 1.0F, 10));
    }

    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100);
    }

    protected EntityNavigation createNavigation(World world) {
        return new SwimNavigation(this, world);
    }


    @Override
    public void tick() {
        super.tick();
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), (double)0.5F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
            this.setYaw(this.random.nextFloat() * 360.0F);
            this.setOnGround(false);
            this.velocityDirty = true;
        }
    }
}
