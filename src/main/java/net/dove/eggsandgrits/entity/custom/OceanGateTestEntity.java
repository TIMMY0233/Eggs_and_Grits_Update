package net.dove.eggsandgrits.entity.custom;

import net.dove.eggsandgrits.entity.ai.OceanGateAttackGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.MobNavigation;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;


public class OceanGateTestEntity extends HostileEntity implements RangedAttackMob {
    private final ServerBossBar bossBar = new ServerBossBar(Text.literal("Ocean Gate"),
            BossBar.Color.BLUE, BossBar.Style.PROGRESS);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState(); // Add attack animation state
    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;

    private boolean hasBeenAttacked = false;


    public int ticksSinceDeath;
    public static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(OceanGateTestEntity.class, TrackedDataHandlerRegistry.BOOLEAN);


    public final AnimationState OceanGateDeath = new AnimationState(); // Add attack animation state


    protected final SwimNavigation waterNavigation;
    protected final MobNavigation landNavigation;
    boolean targetingUnderwater;


    public OceanGateTestEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.waterNavigation = new SwimNavigation(this, world);
        this.landNavigation = new MobNavigation(this, world);
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
    protected void initGoals() {
        this.goalSelector.add(3, new SwimAroundGoal(this, (double) 1.0F, 10));
        this.goalSelector.add(0, new RevengeGoal(this));
        this.goalSelector.add(1, new ProjectileAttackGoal(this, 1, 30, 10F));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.add(0, new OceanGateAttackGoal(this, 1D, true));


    }

    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
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

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();

            // Check if DaBaby is attacking and play attack animation
            if (this.hasBeenAttacked) {
                this.attackAnimationState.start(this.age);  // Start the attack animation
                this.hasBeenAttacked = false; // Reset after attack animation is triggered
            }
        }

        this.setNoGravity(this.isTouchingWater());
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
        builder.add(ATTACKING, false);
    }

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

    /// attacking..

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        SnowballEntity snowballEntity = new SnowballEntity(this.getWorld(), this);
        snowballEntity.setPosition(this.getX(), this.getY(), this.getZ()); // Adjust "1.0" to set the correct spawn height
        double d = target.getEyeY()- 1.1F;
        double e = target.getX() - this.getX();
        double f = d - snowballEntity.getY();
        double g = target.getZ() - this.getZ();
        double h = Math.sqrt(e * e + g * g) * 0.2F;
        snowballEntity.setVelocity(e, f + h, g, 3F, 0F);
        //this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(snowballEntity);

    }

    @Override
    public int getAir() {
        return Integer.MAX_VALUE;  // This makes the mob have infinite air supply
    }

    @Override
    public void setAir(int air) {
        // Do nothing to prevent air depletion
    }

    boolean isTargetingUnderwater() {
        if (this.targetingUnderwater) {
            return true;
        } else {
            LivingEntity livingEntity = this.getTarget();
            return livingEntity != null && livingEntity.isTouchingWater();
        }
    }

    public void updateSwimming() {
        if (!this.getWorld().isClient) {
            if (this.canMoveVoluntarily() && this.isTouchingWater() && this.isTargetingUnderwater()) {
                this.navigation = this.waterNavigation;
                this.setSwimming(true);
            } else {
                this.navigation = this.landNavigation;
                this.setSwimming(false);
            }
        }
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement() && this.isTouchingWater() && this.isTargetingUnderwater()) {
            this.updateVelocity(0.01F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
        } else {
            super.travel(movementInput);
        }

    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.getAttacker() instanceof PlayerEntity) {
            this.hasBeenAttacked = true;  // Mark DaBaby as attacked
            this.setTarget(attackingPlayer);  // Set player as target
        }
        return super.damage(source, amount);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        //playCustomDeathAnimation();
    }

    private void playCustomDeathAnimation() {
        // Play particle effects
        //for(int i =0; i < 5; ++i){
            float f = (this.random.nextFloat() - 0.5F) * 8.0F;
            float g = (this.random.nextFloat() - 0.5F) * 4.0F;
            float h = (this.random.nextFloat() - 0.5F) * 8.0F;
            this.getWorld().addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX() + (double)f, this.getY() + (double)2.0F + (double)g, this.getZ() + (double)h, 0.0F, 0.0F, 0.0F);
    //}
        this.getWorld().addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        this.getWorld().addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0, 0, 0);

        // Additional animation behavior like model adjustments could go here
    }
    protected void updatePostDeath() {
        ++this.ticksSinceDeath;
        if (this.ticksSinceDeath >= 180 && this.ticksSinceDeath <= 200) {
            float f = (this.random.nextFloat() - 0.5F) * 8.0F;
            float g = (this.random.nextFloat() - 0.5F) * 4.0F;
            float h = (this.random.nextFloat() - 0.5F) * 8.0F;
            this.getWorld().addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX() + (double)f, this.getY() + (double)2.0F + (double)g, this.getZ() + (double)h, 0.0F, 0.0F, 0.0F);
        }

        boolean bl = this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_LOOT);
        int i = 500;


        if (this.getWorld() instanceof ServerWorld) {
            if (this.ticksSinceDeath > 150 && this.ticksSinceDeath % 5 == 0 && bl) {
                ExperienceOrbEntity.spawn((ServerWorld)this.getWorld(), this.getPos(), MathHelper.floor((float)i * 0.08F));
            }

            if (this.ticksSinceDeath == 1 && !this.isSilent()) {
                this.getWorld();
            }
        }

        this.move(MovementType.SELF, new Vec3d(0.0F, 0.1F, 0.0F));
        if (this.ticksSinceDeath == 200 && this.getWorld() instanceof ServerWorld) {
            if (bl) {
                ExperienceOrbEntity.spawn((ServerWorld)this.getWorld(), this.getPos(), MathHelper.floor((float)i * 0.2F));
            }

            this.remove(RemovalReason.KILLED);
            //this.emitGameEvent(GameEvent.ENTITY_DIE);
        }

    }



}
