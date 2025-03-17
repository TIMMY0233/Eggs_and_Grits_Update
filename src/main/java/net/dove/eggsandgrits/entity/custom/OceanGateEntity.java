package net.dove.eggsandgrits.entity.custom;

import net.dove.eggsandgrits.entity.ai.FollowTargetGoal;
import net.dove.eggsandgrits.entity.ai.SubmarineMoveControl;
import net.dove.eggsandgrits.sound.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class OceanGateEntity extends HostileEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState(); // Add attack animation state
    public final AnimationState deathAnimationState = new AnimationState(); // Add attack animation state

    public int ticksSinceDeath;
    public boolean playingDeathAnim = false;
    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;
    private final float healthThreshold = 5;
    private boolean hasBeenAttacked = false;
    boolean targetingUnderwater;

    private int flameAttackCooldown = 0;// Cooldown between attacks
    private int sphereAttackCooldown = 0;// Cooldown between attacks

    private final List<Vec3d> perchPoints = new ArrayList<>();
    private int currentPerchPointIndex = 0;





    public static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(OceanGateEntity.class, TrackedDataHandlerRegistry.BOOLEAN);


    private final ServerBossBar bossBar = new ServerBossBar(Text.literal("Titan Submersible"),
            BossBar.Color.BLUE, BossBar.Style.PROGRESS);

    public OceanGateEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new SubmarineMoveControl(this);

        perchPoints.add(new Vec3d(47, 120, 80));
        perchPoints.add(new Vec3d(24, 120, 3));
        perchPoints.add(new Vec3d(-19, 120, 6));
        perchPoints.add(new Vec3d(-17, 123, 34));
        perchPoints.add(new Vec3d(-8, 127, 81));
        perchPoints.add(new Vec3d(35, 122, 81));
        this.calculateDimensions();
    }


    @Override
    protected void initGoals() {
        super.initGoals();
        //this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new FollowTargetGoal(this, 2, 30.0F, 0.5F)); // Make sure it follows the player
        this.goalSelector.add(0, new LookAtEntityGoal(this, PlayerEntity.class, 30.0F));


        this.targetSelector.add(1,new RevengeGoal(this));

        this.goalSelector.add(4, new SwimAroundGoal(this,  1.0F, 10));

    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 18)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100);
    }


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <=0){
            attackAnimationTimeout = 40;
            attackAnimationState.start(this.age);
        }
        else{
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()){
            attackAnimationState.stop();
        }
    }

    @Override
    public int getAir() {
        return Integer.MAX_VALUE;  // This makes the mob have infinite air supply
    }

    @Override
    public void setAir(int air) {
        // Do nothing to prevent air depletion
    }

    @Override
    public void tick() {
        super.tick();
        if(!dead) {
            this.updateBoundingBox();
        }

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();

            // Check if attacking and play attack animation
            if (this.hasBeenAttacked) {
                this.attackAnimationState.start(this.age);  // Start the attack animation
                this.hasBeenAttacked = false; // Reset after attack animation is triggered
            }
        }
        // Check if in water
        if (this.isTouchingWater()) {
            // Ensure it's swimming downwards slightly when not at sea level
            if (this.getY() > this.getWorld().getSeaLevel() - 2) {
                this.setVelocity(this.getVelocity().add(0.0F, -0.005F, 0.0F));
            }

            // Ensure it moves around if idle
            if (this.getTarget() == null && !this.isAttacking()) {
                if (this.getRandom().nextInt(100) == 0) {
                    // Randomly swim in a direction
                    this.getMoveControl().moveTo(this.getX() + (this.getRandom().nextFloat() * 2 - 1), this.getY(), this.getZ() + (this.getRandom().nextFloat() * 2 - 1), 1.0);
                }
            }
        }

        // Set the gravity to zero if in water
        this.setNoGravity(this.isTouchingWater());

        if (this.getTarget() == null || this.distanceTo(this.getTarget()) > 30) {
            // If not attacking a player or player is too far, follow perch points

            // Get the current perch point
            Vec3d currentPoint = perchPoints.get(currentPerchPointIndex);

            // Move towards the perch point
            moveToPoint(currentPoint);

            // Check if we reached the perch point
            if (this.squaredDistanceTo(currentPoint) < 1.0) {
                // If we reached the point, move to the next one
                currentPerchPointIndex = (currentPerchPointIndex + 1) % perchPoints.size(); // Loop through points
            }
        }

        if (!getWorld().isClient) {
            if (flameAttackCooldown > 0) {
                flameAttackCooldown--;
            }

            if (sphereAttackCooldown > 0) {
                sphereAttackCooldown--;
            }

            // Example: If a player is close, attack
            if (this.getTarget() != null && this.distanceTo(this.getTarget()) < 10) {
                performFlamethrowerAttack();
            }

            if (this.getTarget() != null && this.distanceTo(this.getTarget()) < 30) {
                performParticleSphereAttack();  // Call the particle sphere attack
            }

        }
    }




    // MISC AND MOVEMENT //

    public void checkDespawn() {
        if (this.getWorld().getDifficulty() == Difficulty.PEACEFUL && this.isDisallowedInPeaceful()) {
            this.discard();
        } else {
            this.despawnCounter = 0;
        }
    }

    private void moveToPoint(Vec3d targetPoint) {
        // Calculate direction vector to the target point
        double deltaX = targetPoint.x - this.getX();
        double deltaY = targetPoint.y - this.getY();
        double deltaZ = targetPoint.z - this.getZ();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

        // If we're close enough to the target, stop moving
        if (distance < 0.5) {
            // Stop the movement, we reached the target
            this.setVelocity(0, 0, 0);
            return;
        }

        // Normalize the direction vector to get a unit direction
        Vec3d direction = new Vec3d(deltaX / distance, deltaY / distance, deltaZ / distance);

        // Apply smooth movement by gradually adjusting velocity
        double movementSpeed = 0.1;  // Adjust for desired swimming speed
        Vec3d velocity = direction.multiply(movementSpeed);
        this.setVelocity(velocity);

        // Smooth rotation: Update yaw to gradually face the target
        float targetYaw = (float) (Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90);
        float targetPitch = (float) (Math.toDegrees(Math.atan2(direction.y, Math.sqrt(deltaX * deltaX + deltaZ * deltaZ))));

        // Apply gradual rotation by interpolating between current and target yaw/pitch
        float yawDifference = Math.abs(this.getYaw() - targetYaw);
        if (yawDifference > 180) yawDifference = 360 - yawDifference;

        if (yawDifference > 5) {  // Smooth out yaw change
            this.setYaw(this.getYaw() + Math.signum(targetYaw - this.getYaw()) * Math.min(yawDifference, 5));  // Smooth rotation speed
        }

        // Apply smooth pitch adjustment
        float pitchDifference = Math.abs(this.getPitch() - targetPitch);
        if (pitchDifference > 5) {  // Smooth out pitch change
            this.setPitch(this.getPitch() + Math.signum(targetPitch - this.getPitch()) * Math.min(pitchDifference, 5));  // Smooth pitch speed
        }
    }

    private void updateBoundingBox() {
        float yaw = (this.getYaw() % 360 + 360) % 360; // Normalize yaw to 0-360 range

        double width = 4.0;  // Default width (X-axis)
        double depth = 8.0;  // Default depth (Z-axis)
        double height = 5.0; // Fixed height (Y-axis)

        double halfWidth, halfDepth;

        // Determine whether to swap width and depth
        if ((yaw >= 45 && yaw < 135) || (yaw >= 225 && yaw < 315)) {
            // Facing EAST or WEST → Swap width and depth
            halfWidth = depth / 2.0;
            halfDepth = width / 2.0;
        } else {
            // Facing NORTH or SOUTH → Keep default width and depth
            halfWidth = width / 2.0;
            halfDepth = depth / 2.0;
        }

        // Update bounding box to match rotation
        this.setBoundingBox(new Box(
                this.getX() - halfWidth, this.getY(), this.getZ() - halfDepth, // Min X, Y, Z
                this.getX() + halfWidth, this.getY() + height, this.getZ() + halfDepth  // Max X, Y, Z
        ));
    }



    // ATTACKS //

    private void performFlamethrowerAttack() {
        if (flameAttackCooldown > 0) return; // Prevent spamming the attack

        flameAttackCooldown = 140; // Reset attack cooldown (2 seconds)

        // Play attack sound
        getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_BOAT_PADDLE_WATER, this.getSoundCategory(), 1.0F, 1.0F);

        if (getWorld() instanceof ServerWorld serverWorld) {
            // Get the player's position (assuming you have a reference to the player entity)
            LivingEntity target = this.getTarget(); // Assuming target is set to the player or an entity
            if (target == null) return; // No target, exit

            // Get the vector pointing towards the target (player)
            Vec3d directionToPlayer = new Vec3d(target.getX() - this.getX(), target.getY() - this.getY(), target.getZ() - this.getZ());
            directionToPlayer = directionToPlayer.normalize(); // Normalize to get a unit vector

            // Calculate yaw and pitch to face the player
            double horizontalDistance = Math.sqrt(directionToPlayer.x * directionToPlayer.x + directionToPlayer.z * directionToPlayer.z);
            float yaw = (float) (Math.toDegrees(Math.atan2(directionToPlayer.z, directionToPlayer.x)) - 90);
            float pitch = (float) (Math.toDegrees(Math.atan2(directionToPlayer.y, horizontalDistance)));

            // Apply the rotations to the mob
            this.setYaw(yaw);
            this.setPitch(pitch);

            // Now the mob faces the player, so proceed with the flamethrower effect
            Vec3d startPos = this.getPos().add(directionToPlayer.multiply(1.5)); // Start attack slightly in front

            double attackRange = 10.0; // Attack range (in blocks)

            // Loop through the range to create the flamethrower effect
            for (int i = 1; i <= attackRange; i++) {
                // Position of the particle at each step
                Vec3d particlePos = startPos.add(directionToPlayer.multiply(i));

                // Adjust the spread to make particles appear wider
                double spreadAmount = 1.0; // Change to adjust how wide the effect spreads
                serverWorld.spawnParticles(ParticleTypes.BUBBLE,
                        particlePos.x, particlePos.y, particlePos.z,
                        50, spreadAmount, spreadAmount, spreadAmount, 0.1);
                serverWorld.spawnParticles(ParticleTypes.FIREWORK,
                        particlePos.x + 0.3, particlePos.y + 0.3, particlePos.z + 0.3,
                        1, spreadAmount, spreadAmount, spreadAmount, 0.1);// More particles for effect

                // Check for entities within a wider attack range
                List<LivingEntity> entities = getWorld().getEntitiesByClass(LivingEntity.class,
                        new Box(particlePos.x - spreadAmount, particlePos.y - 0.5, particlePos.z - spreadAmount,
                                particlePos.x + spreadAmount, particlePos.y + 0.5, particlePos.z + spreadAmount),
                        entity -> entity != this); // Ensure not targeting itself

                for (LivingEntity entity : entities) {
                    double distance = this.squaredDistanceTo(entity); // Calculate distance to entity

                    if (distance <= attackRange * attackRange) { // Apply damage if within range
                        entity.damage(getWorld().getDamageSources().mobAttack(this), 2.5F); // Lower damage but hits multiple times
                        entity.addVelocity(directionToPlayer.x * 0.2, 0.05, directionToPlayer.z * 0.2); // Push effect
                    }
                }
            }
        }
    }

    private float maxRadius = 25.0F;  // Maximum radius for the sphere
    private float minRadius = 1.0F;   // Minimum starting radius
    private int expandDuration = 20;   // Duration for the sphere to expand (in ticks)
    private int currentDuration = 0;   // Tracks how long the sphere has been expanding

    private void performParticleSphereAttack() {
        if(sphereAttackCooldown > 0) return;
        if(currentDuration == 0){
            getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.RADAR_PING, this.getSoundCategory(), 4.0F, 1.0F);
        }

        // Gradually increase the radius over time
        float normalizedDuration = Math.min(1.0F, currentDuration / (float) expandDuration);
        double smoothRadius = minRadius + (maxRadius - minRadius) * normalizedDuration; // Smooth out the expansion

        if (getWorld() instanceof ServerWorld serverWorld) {
            // Get the position of the entity (e.g., OceanGateEntity)
            Vec3d position = this.getPos();

            // Loop through all angles to create particles in a sphere shape
            int particlesPerLayer = 100; // Number of particles to spawn in each layer
            for (int i = 0; i < particlesPerLayer; i++) {
                // Random angle around the sphere
                double angle = Math.random() * 2 * Math.PI;
                double yAngle = Math.random() * Math.PI - Math.PI / 2;

                // Calculate the x, y, z offsets based on the angle and current radius
                double xOffset = smoothRadius * Math.cos(angle) * Math.cos(yAngle);
                double yOffset = smoothRadius * Math.sin(yAngle);
                double zOffset = smoothRadius * Math.sin(angle) * Math.cos(yAngle);

                Vec3d particlePos = position.add(xOffset, yOffset, zOffset);

                // Spawn the particle (you can change ParticleTypes to something else)
                serverWorld.spawnParticles(ParticleTypes.POOF, // Change this to the desired particle type
                        particlePos.x, particlePos.y, particlePos.z,
                        10, 0, 0, 0, 0);  // No velocity, change as needed
                serverWorld.spawnParticles(ParticleTypes.FIREWORK, // Change this to the desired particle type
                        particlePos.x + 0.5, particlePos.y + 0.5, particlePos.z + .5,
                        1, 0, 0, 0, 0);  // No velocity, change as needed
            }

            // Get all entities in the radius of the expanding sphere
            List<LivingEntity> entitiesInRange = serverWorld.getEntitiesByClass(LivingEntity.class,
                    new Box(position.subtract(smoothRadius, smoothRadius, smoothRadius), position.add(smoothRadius, smoothRadius, smoothRadius)),
                    entity -> true); // Get all entities in the range of the sphere

            // Loop through all the entities and apply knockback if they are within the sphere's radius
            for (LivingEntity entity : entitiesInRange) {
                // Prevent the OceanGateEntity from being affected by the attack
                if (entity == this) {
                    continue; // Skip this entity if it's the OceanGateEntity
                }

                double distanceToEntity = position.distanceTo(entity.getPos()); // Calculate distance to the entity
                if (distanceToEntity <= smoothRadius) {
                    // Apply knockback to the entity
                    Vec3d knockbackDirection = entity.getPos().subtract(position).normalize(); // Direction from entity to center
                    double knockbackStrength = 0.5; // Adjust the strength of the knockback here

                    // Apply horizontal knockback (no vertical)
                    entity.addVelocity(knockbackDirection.x * knockbackStrength, 0.05, knockbackDirection.z * knockbackStrength);

                    // If you want to apply damage to the entity within range (optional)
                    // This will apply damage and push effect to all entities in the sphere
                    entity.damage(getWorld().getDamageSources().mobAttack(this), 2.5F); // Lower damage but hits multiple times
                }
            }

            // If the sphere has reached its maximum size, reset and start expanding again
            if (currentDuration >= expandDuration) {
                currentDuration = 0; // Reset the duration to start the expansion again
                sphereAttackCooldown = 400;
            } else {
                currentDuration++; // Continue expanding the sphere
            }
        }
    }





    public void setAttacking(boolean attacking){
        this.dataTracker.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ATTACKING, false);    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.getAttacker() instanceof PlayerEntity player) {
            this.hasBeenAttacked = true;  // Mark as attacked by a player
            this.setTarget(player);// Set the player as the target
        }
        return super.damage(source, amount);
    }


    // END OF ATTACKS //

    // BOSS BAR //

    @Override
    public void onStartedTrackingBy (ServerPlayerEntity player){
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy (ServerPlayerEntity player){
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    protected void mobTick () {
        super.mobTick();
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    // END OF BOSS BAR //

    // SOUNDS //

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return ModSounds.OCEAN_DEATH;
    }

    protected float getSoundVolume() {
        return 5.0F;
    }
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    // END OF SOUNDS //

    // DEATH ?? //

    @Override
    protected void updatePostDeath() {
        ++this.ticksSinceDeath;
        flameAttackCooldown = 1000;
        sphereAttackCooldown = 1000;

        if(!playingDeathAnim) {
            deathAnimationState.start(this.age);
            playingDeathAnim = true;

        }

        boolean bl = this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_LOOT);
        int i = 750;

        if (this.ticksSinceDeath >= 30 && this.ticksSinceDeath <= 55) {
            float f = (this.random.nextFloat() - 0.5F) * 8.0F;
            float g = (this.random.nextFloat() - 0.5F) * 4.0F;
            float h = (this.random.nextFloat() - 0.5F) * 8.0F;
            this.getWorld().addParticle(ParticleTypes.SONIC_BOOM, this.getX() + (double)f, this.getY() + (double)2.0F + (double)g, this.getZ() + (double)h, 0.0F, 0.0F, 0.0F);
        }
        if (this.ticksSinceDeath >= 30 && this.ticksSinceDeath <= 55) {
            float f = (this.random.nextFloat() - 0.5F) * 8.0F;
            float g = (this.random.nextFloat() - 0.5F) * 4.0F;
            float h = (this.random.nextFloat() - 0.5F) * 8.0F;
            this.getWorld().addParticle(ParticleTypes.BUBBLE, this.getX() + (double)f, this.getY() + (double)2.0F + (double)g, this.getZ() + (double)h, 0.0F, 0.0F, 0.0F);
        }

        if (this.ticksSinceDeath >= 50 && this.ticksSinceDeath <= 100) {
            float f = (this.random.nextFloat() - 0.5F) * 8.0F;
            float g = (this.random.nextFloat() - 0.5F) * 4.0F;
            float h = (this.random.nextFloat() - 0.5F) * 8.0F;
            this.getWorld().addParticle(ParticleTypes.BUBBLE_POP, this.getX() + (double)f, this.getY() + (double)2.0F + (double)g, this.getZ() + (double)h, 0.0F, 0.0F, 0.0F);

        }

        if (this.getWorld() instanceof ServerWorld) {
            if (this.ticksSinceDeath > 20 && this.ticksSinceDeath % 5 == 0 && bl) {
                CustomExperienceOrbEntity.spawn((ServerWorld)this.getWorld(), this.getPos(), MathHelper.floor((float)i * 0.08F));
            }

            if (this.ticksSinceDeath == 1 && !this.isSilent()) {
                this.getWorld();
            }
        }

        this.move(MovementType.SELF, new Vec3d(0.0F, 0.1F, 0.0F));
        if (this.ticksSinceDeath == 100 && this.getWorld() instanceof ServerWorld) {
            if (bl) {
                CustomExperienceOrbEntity.spawn((ServerWorld)this.getWorld(), this.getPos(), MathHelper.floor((float)i * 0.2F));
            }

            this.remove(RemovalReason.KILLED);
            //this.emitGameEvent(GameEvent.ENTITY_DIE);
        }

    }

    // END OF DEATH //


}
