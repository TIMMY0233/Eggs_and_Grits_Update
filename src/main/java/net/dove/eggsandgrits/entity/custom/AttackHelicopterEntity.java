package net.dove.eggsandgrits.entity.custom;


import net.dove.eggsandgrits.entity.ai.FollowTargetGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;


public class AttackHelicopterEntity extends HostileEntity implements RangedAttackMob {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private boolean isAttacking = false;
    private int missileCount = 0;  // Counter to track how many missiles have been shot
    private int missileCooldown = 0;  // Delay counter for missile shots
    private int barrageTimeout = 0;


    public AttackHelicopterEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 10, true);
        this.setHealth(this.getMaxHealth());
        this.experiencePoints = 50;
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world) {
            @Override
            public boolean isValidPosition(BlockPos pos) {
                return !this.world.getBlockState(pos.down()).isAir();
            }

        };
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(false);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }



    //Immune to fall damage
    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    //goals for actions
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        //this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));

        this.goalSelector.add(3, new HeliWanderAroundGoal());
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));

        //this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, true));

        this.targetSelector.add(1,new RevengeGoal(this));
        this.goalSelector.add(3, new FollowTargetGoal(this, 1.2D, 40F, 10F));


        this.goalSelector.add(2, new ProjectileAttackGoal(this, 1, 1, 40F));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PhantomEntity.class, true));



    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 18)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.3f);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
        if (missileCooldown > 0) {
            missileCooldown--;  // Decrease missile cooldown for the next shot
        }

        if (missileCount > 0 && missileCount < 5 && missileCooldown <= 0) {
            shootMissile(this.getTarget());  // Shoot the next missile in the barrage
            missileCooldown = 5;  // Reset cooldown for the next missile
            missileCount++;  // Increment missile count
        }

        if (missileCount >= 5 && barrageTimeout <= 0) {
            barrageTimeout = 60;  // Set cooldown after all missiles are fired (60 ticks)
            missileCount = 0;  // Reset missile count for the next barrage
        }

        if (barrageTimeout > 0) {
            barrageTimeout--;  // Decrease barrage cooldown
        }

    }


    @Override
    public boolean damage(DamageSource source, float amount) {
        if(source.getAttacker() instanceof LivingEntity attacker) {
            this.isAttacking = true;
            this.setTarget(attacker);
        }
        return super.damage(source, amount);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();

        if (this.getTarget() == null || !this.getTarget().isAlive()) {
            this.isAttacking = false;
        }
    }


   /* //Shoots snowball?
   @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        SnowballEntity snowballEntity = new SnowballEntity(this.getWorld(), this);
        double d = target.getEyeY()- 1.1F;
        double e = target.getX() - this.getX();
        double f = d - snowballEntity.getY();
        double g = target.getZ() - this.getZ();
        double h = Math.sqrt(e * e + g * g) * 0.2F;
        snowballEntity.setVelocity(e, f + h, g, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(snowballEntity);
    }

    */
//shoots missile
   /* @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        // Create a new instance of your custom missile entity
        MissileProjectileEntity missileProjectileEntity = new MissileProjectileEntity(this.getWorld(), this);

        // Calculate the position to shoot from (adjust as needed)
        double d = target.getEyeY() - 1.1F;  // Adjust target height if needed
        double e = target.getX() - this.getX();
        double f = d - missileProjectileEntity.getY();
        double g = target.getZ() - this.getZ();

        // Calculate the distance and set velocity (adjust the factor for missile speed)
        double h = Math.sqrt(e * e + g * g) * 0.2F;

        // Set the missile's velocity
        missileProjectileEntity.setVelocity(e, f + h, g, 1.6F, 12.0F);

        // Play the shooting sound (you can change this to a custom sound if you have one)
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));

        // Spawn the missile entity in the world
        this.getWorld().spawnEntity(missileProjectileEntity);
    }

    */
//multishot missile
 /*  @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        if (barrageTimeout <= 0) {  // Check if the barrage is off cooldown
            // Fire a barrage of missiles
            for (int i = 0; i < 5; ++i) {  // Fire 5 missiles (you can adjust the number)
                // Create a new missile projectile entity
                    MissileProjectileEntity missileProjectileEntity = new MissileProjectileEntity(this.getWorld(), this);

                    // Calculate the position to shoot from
                    double d = target.getEyeY() - 1.1F;  // Adjust target height if needed
                    double e = target.getX() - this.getX();
                    double f = d - missileProjectileEntity.getY();
                    double g = target.getZ() - this.getZ();

                    // Calculate velocity for the missile
                    double h = Math.sqrt(e * e + g * g) * 0.2F;

                    // Set the missile's velocity
                    missileProjectileEntity.setVelocity(e, f + h, g, 1.6F, 12.0F);

                    // Play the shooting sound (you can change this to a custom sound if you have one)
                    this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));

                    // Spawn the missile entity in the world
                    this.getWorld().spawnEntity(missileProjectileEntity);
                }



            // Set the cooldown (in this case, 60 ticks = 3 seconds)
            barrageTimeout = 60;  // You can adjust this to control the cooldown duration
        }
    }

  */

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        if (target != null && barrageTimeout <= 0 && missileCount == 0) {  // Ensure target is not null
            shootMissile(target);  // Fire the first missile
            missileCooldown = 5;  // Set cooldown between missiles
            missileCount++;  // Increment missile count
        }
    }


    private void shootMissile(LivingEntity target) {
        if (target == null) return;  // Ensure target is not null before proceeding

        // Create a new missile projectile entity
        MissileProjectileEntity missileProjectileEntity = new MissileProjectileEntity(this.getWorld(), this);

        // Calculate the position to shoot from
        double d = target.getEyeY() - 1.1F;  // Adjust target height if needed
        double e = target.getX() - this.getX();
        double f = d - missileProjectileEntity.getY();
        double g = target.getZ() - this.getZ();

        // Calculate velocity for the missile
        double h = Math.sqrt(e * e + g * g) * 0.2F;

        // Set the missile's velocity
        missileProjectileEntity.setVelocity(e, f + h, g, 1.6F, 12.0F);

        // Play the shooting sound (you can change this to a custom sound if you have one)
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));

        // Spawn the missile entity in the world
        this.getWorld().spawnEntity(missileProjectileEntity);
        missileCooldown = 5;
    }


    class HeliWanderAroundGoal extends Goal {
        private static final int MAX_DISTANCE = 22;

        HeliWanderAroundGoal() {
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return AttackHelicopterEntity.this.navigation.isIdle() && AttackHelicopterEntity.this.random.nextInt(10) == 0;
        }

        @Override
        public boolean shouldContinue() {
            return AttackHelicopterEntity.this.navigation.isFollowingPath();
        }

        @Override
        public void start() {
            Vec3d vec3d = this.getRandomLocation();
            if (vec3d != null) {
                AttackHelicopterEntity.this.navigation.startMovingAlong(AttackHelicopterEntity.this.navigation.findPathTo(BlockPos.ofFloored(vec3d), 1), 1.0);
            }
        }

        @Nullable
        private Vec3d getRandomLocation() {
            Vec3d vec3d2;
                vec3d2 = AttackHelicopterEntity.this.getRotationVec(0.0F);


            int i = 8;
            Vec3d vec3d3 = AboveGroundTargeting.find(AttackHelicopterEntity.this, 8, 7, vec3d2.x, vec3d2.z, (float) (Math.PI / 2), 3, 1);
            return vec3d3 != null ? vec3d3 : NoPenaltySolidTargeting.find(AttackHelicopterEntity.this, 8, 4, -2, vec3d2.x, vec3d2.z, (float) (Math.PI / 2));
        }
    }


}