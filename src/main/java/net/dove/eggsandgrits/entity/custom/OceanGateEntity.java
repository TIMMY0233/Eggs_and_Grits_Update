package net.dove.eggsandgrits.entity.custom;

import net.dove.eggsandgrits.entity.ai.OceanGateAttackGoal;
import net.dove.eggsandgrits.sound.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeNavigator;
import net.minecraft.entity.ai.pathing.SwimNavigation;
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
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;


public class OceanGateEntity extends HostileEntity implements RangedAttackMob {
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




    public static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(OceanGateEntity.class, TrackedDataHandlerRegistry.BOOLEAN);


    private final ServerBossBar bossBar = new ServerBossBar(Text.literal("Titan Submersible"),
            BossBar.Color.BLUE, BossBar.Style.PROGRESS);

    public OceanGateEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new AquaticMoveControl(this, 85, 10, 0.02F, 0.1F, true);


    }


    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new MoveIntoWaterGoal(this));

        //this.goalSelector.add(1, new OceanGateAttackGoal(this, 1.2D, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, DolphinEntity.class, true));

        this.targetSelector.add(2,new RevengeGoal(this));

        this.goalSelector.add(2, new SwimAroundGoal(this,  1.0F, 10));


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
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();

            // Check if attacking and play attack animation
            if (this.hasBeenAttacked) {
                this.attackAnimationState.start(this.age);  // Start the attack animation
                this.hasBeenAttacked = false; // Reset after attack animation is triggered
            }
        }

        // Set the gravity to zero if in water
        //this.setNoGravity(this.isTouchingWater());
    }

    // MISC AND MOVEMENT //


    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.isTouchingWater()) {
            Vec3d vec3d = this.getRotationVec(0.0F);

            for(int i = 0; i < 2; ++i) {
                this.getWorld().addParticle(ParticleTypes.BUBBLE, this.getParticleX((double)0.5F) - vec3d.x * (double)1.5F, this.getRandomBodyY() - vec3d.y * (double)1.5F, this.getParticleZ((double)0.5F) - vec3d.z * (double)1.5F, (double)0.0F, (double)0.0F, (double)0.0F);
            }
        }
    }

    public boolean cannotDespawn() {
        return super.cannotDespawn();
    }

    @Override
    public int getAir() {
        return Integer.MAX_VALUE;  // This makes the mob have infinite air supply
    }

    @Override
    public void setAir(int air) {
        // Do nothing to prevent air depletion
    }

    protected EntityNavigation createNavigation(World world) {
        return new SwimNavigation(this, world);
    }

    public int getMaxLookPitchChange() {
        return 1;
    }

    public int getMaxHeadRotation() {
        return 1;
    }


    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() && this.isTouchingWater()) {
            this.updateVelocity(this.getMovementSpeed(), movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.4));
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add((double)0.0F, -0.005, (double)0.0F));
            }
        } else {
            super.travel(movementInput);
        }

    }




    // ATTACKS //

    boolean isTargetingUnderwater() {
        if (this.targetingUnderwater) {
            return true;
        } else {
            LivingEntity livingEntity = this.getTarget();
            return livingEntity != null && livingEntity.isTouchingWater();
        }
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        SnowballEntity snowballEntity = new SnowballEntity(this.getWorld(), this);
        double d = target.getEyeY()- 1.1F;
        double e = target.getX() - this.getX();
        double f = d - snowballEntity.getY();
        double g = target.getZ() - this.getZ();
        double h = Math.sqrt(e * e + g * g) * 0.2F;
        snowballEntity.setVelocity(e, f + h, g, 1.6F, 12.0F);
        this.getWorld().spawnEntity(snowballEntity);
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
