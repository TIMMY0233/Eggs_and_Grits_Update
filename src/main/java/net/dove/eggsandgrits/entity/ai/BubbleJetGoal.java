package net.dove.eggsandgrits.entity.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class BubbleJetGoal extends Goal {
    private final MobEntity mob;
    private int cooldown = 0;

    public BubbleJetGoal(MobEntity mob) {
        this.mob = mob;
    }

    @Override
    public boolean canStart() {
        return mob.getVelocity().lengthSquared() > 0.01; // Only activate if moving
    }

    @Override
    public void tick() {
        if (cooldown-- <= 0) {
            spawnBubbles();
            cooldown = 5; // Adjust rate of bubbles
        }
    }

    private void spawnBubbles() {
        ServerWorld world = (ServerWorld) mob.getWorld();
        Vec3d pos = mob.getPos();
        world.spawnParticles(ParticleTypes.BUBBLE, pos.x, pos.y, pos.z, 10, 0.2, 0.2, 0.2, 0.1);
    }
}