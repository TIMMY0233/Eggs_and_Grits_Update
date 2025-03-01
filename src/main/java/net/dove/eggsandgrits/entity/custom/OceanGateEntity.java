package net.dove.eggsandgrits.entity.custom;

import net.dove.eggsandgrits.entity.ai.OceanGateAttackGoal;
import net.dove.eggsandgrits.sound.ModSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.RevengeGoal;
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
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class OceanGateEntity extends HostileEntity implements RangedAttackMob {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState(); // Add attack animation state

    public final AnimationState deathAnimationState = new AnimationState(); // Add attack animation state

    public int ticksSinceDeath;
    public boolean playingDeathAnim = false;
    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;
    private final float healthThreshold = 5;




    public static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(OceanGateEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private boolean hasBeenAttacked = false;


    public OceanGateEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    private final ServerBossBar bossBar = new ServerBossBar(Text.literal("Titan Submersible"),
            BossBar.Color.BLUE, BossBar.Style.PROGRESS);


    @Override
    protected void initGoals() {
        super.initGoals();

        this.goalSelector.add(1, new OceanGateAttackGoal(this, 1.2D, true));
        this.targetSelector.add(1,new RevengeGoal(this));


    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 18)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20);
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


        this.setNoGravity(true);
        //this.setNoGravity(this.isTouchingWater());

    }

        // ATTACKS //
    @Override
    public void shootAt(LivingEntity target, float pullProgress) {

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
        if (source.getAttacker() instanceof PlayerEntity) {
            this.hasBeenAttacked = true;  // Mark as attacked
            this.setTarget(attackingPlayer);  // Set player as target
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
            this.getWorld().addParticle(ParticleTypes.BUBBLE_POP, this.getX() + (double)f, this.getY() + (double)2.0F + (double)g, this.getZ() + (double)h, 0.0F, 0.0F, 0.0F);
        }

        if (this.ticksSinceDeath >= 50 && this.ticksSinceDeath <= 55) {
            float f = (this.random.nextFloat() - 0.5F) * 8.0F;
            float g = (this.random.nextFloat() - 0.5F) * 4.0F;
            float h = (this.random.nextFloat() - 0.5F) * 8.0F;
            this.getWorld().addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX() + (double)f, this.getY() + (double)2.0F + (double)g, this.getZ() + (double)h, 0.0F, 0.0F, 0.0F);

        }

        if (this.getWorld() instanceof ServerWorld) {
            if (this.ticksSinceDeath > 20 && this.ticksSinceDeath % 5 == 0 && bl) {
                CustomExperienceOrbEntity.spawn((ServerWorld)this.getWorld(), this.getPos(), MathHelper.floor((float)i * 0.08F));
            }

            if (this.ticksSinceDeath == 1 && !this.isSilent()) {
                this.getWorld();
            }
        }

        this.move(MovementType.SELF, new Vec3d(0.0F, 0.4F, 0.0F));
        if (this.ticksSinceDeath == 70 && this.getWorld() instanceof ServerWorld) {
            if (bl) {
                CustomExperienceOrbEntity.spawn((ServerWorld)this.getWorld(), this.getPos(), MathHelper.floor((float)i * 0.2F));
            }

            this.remove(RemovalReason.KILLED);
            //this.emitGameEvent(GameEvent.ENTITY_DIE);
        }

    }

    // END OF DEATH //


}
