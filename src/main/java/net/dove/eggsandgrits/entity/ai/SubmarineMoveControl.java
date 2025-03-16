package net.dove.eggsandgrits.entity.ai;

import net.dove.eggsandgrits.entity.custom.OceanGateEntity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class SubmarineMoveControl extends MoveControl {
    private final OceanGateEntity entity;

    public SubmarineMoveControl(OceanGateEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public void tick() {
        if (this.entity.isTouchingWater()) {

            // Check if the entity has a target
            if (this.entity.getTarget() != null) {
                double targetX = this.entity.getTarget().getX();
                double targetY = this.entity.getTarget().getY();
                double targetZ = this.entity.getTarget().getZ();

                double distance = this.entity.squaredDistanceTo(targetX, targetY, targetZ);
                double minDistance = 140.0; // Squared value of the desired minimum distance (7 blocks away)

                if (distance < minDistance) {
                    // Move AWAY from the player if too close
                    Vec3d awayVec = new Vec3d(
                            this.entity.getX() - targetX,
                            this.entity.getY() - targetY,
                            this.entity.getZ() - targetZ
                    ).normalize().multiply(0.05); // Adjust speed as needed

                    this.entity.setVelocity(awayVec);
                } else {
                    // Normal movement towards the player
                    Vec3d movementVec = new Vec3d(
                            (targetX - this.entity.getX()),
                            (targetY - this.entity.getY()),
                            (targetZ - this.entity.getZ())
                    ).normalize().multiply(0.05); // Adjust speed as needed

                    this.entity.setVelocity(movementVec);
                }

                // --- Rotate entity to face the player ---
                double dx = targetX - this.entity.getX();
                double dz = targetZ - this.entity.getZ();
                double dy = targetY - this.entity.getY();

                // Horizontal rotation (yaw)
                float targetYaw = (float) (Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
                this.entity.setYaw(this.entity.getYaw() + MathHelper.wrapDegrees(targetYaw - this.entity.getYaw()) * 0.1F);
                this.entity.setBodyYaw(this.entity.getYaw()); // Sync body rotation with head rotation

                // Vertical tilt (pitch)
                float targetPitch = (float) (-Math.toDegrees(Math.atan2(dy, Math.sqrt(dx * dx + dz * dz))));
                this.entity.setPitch(this.entity.getPitch() + MathHelper.wrapDegrees(targetPitch - this.entity.getPitch()) * 0.1F);
            }
        }
    }
}
