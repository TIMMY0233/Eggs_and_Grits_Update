package net.dove.eggsandgrits.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;

public class FollowTargetGoal extends Goal {
    private final MobEntity mob;
    private final double speed;
    private LivingEntity target;
    private final float maxDistance;
    private final float minDistance;

    public FollowTargetGoal(MobEntity mob, double speed, float maxDistance, float minDistance) {
        this.mob = mob;
        this.speed = speed;
        this.maxDistance = maxDistance;
        this.minDistance = minDistance;
    }

    @Override
    public boolean canStart() {
        this.target = this.mob.getTarget();
        return this.target != null && this.mob.squaredDistanceTo(target) > minDistance * minDistance;
    }

    @Override
    public void start() {
        this.mob.getNavigation().startMovingTo(this.target, this.speed);
    }

    @Override
    public void tick() {
        if (this.target != null && this.mob.squaredDistanceTo(target) > maxDistance * maxDistance) {
            this.mob.getNavigation().startMovingTo(this.target, this.speed);
        }
    }
}

