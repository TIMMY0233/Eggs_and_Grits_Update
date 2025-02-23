package net.dove.eggsandgrits.entity.custom;

import net.dove.eggsandgrits.entity.ModEntities;
import net.dove.eggsandgrits.entity.ai.DaBabyAttackGoal;
import net.dove.eggsandgrits.item.ModItems;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;


import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;


public class DaBabyEntity extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState(); // Add attack animation state
    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;


    public static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(DaBabyEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private final ServerBossBar bossBar = new ServerBossBar(Text.literal("DaBaby"),
            BossBar.Color.BLUE, BossBar.Style.NOTCHED_10);

    private boolean hasBeenAttacked = false;

    public DaBabyEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    // Goals for actions
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(10, new TemptGoal(this, 1.25D, Ingredient.ofItems(ModItems.BEANS), false));
        this.goalSelector.add(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(1, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));

        // Add melee attack goal
        this.goalSelector.add(1, new DaBabyAttackGoal(this, 1.2D, true));

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

            // Check if DaBaby is attacking and play attack animation
            if (this.hasBeenAttacked) {
                this.attackAnimationState.start(this.age);  // Start the attack animation
                this.hasBeenAttacked = false; // Reset after attack animation is triggered
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
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.IRON_BARS);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.DABABY.create(world);
    }

    public boolean isHasBeenAttacked() {
        return hasBeenAttacked;
    }

    public void setHasBeenAttacked(boolean hasBeenAttacked) {
        this.hasBeenAttacked = hasBeenAttacked;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.getAttacker() instanceof PlayerEntity) {
            this.hasBeenAttacked = true;  // Mark DaBaby as attacked
            this.setTarget(attackingPlayer);  // Set player as target
        }
        return super.damage(source, amount);
    }



    // SOUND //

    /*@Override
    protected @Nullable SoundEvent getAmbientSound() {
        if(random.nextFloat() < .3){
            return ModSounds.DO_NOT_EAT;
        }
        else if(random.nextFloat() <= .1){
            return ModSounds.HE_DID_WHAT;
        }
        else {
            return ModSounds.GIT_R_DONE;
        }
    }

    @Override
    public void playSound(SoundEvent sound, float volume, float pitch) {
        PlayerEntity player = this.getWorld().getClosestPlayer(this, 16);

        if (player != null && !hasLineOfSight(player)) {
            volume *= 0.4f; // Reduce volume if there's no line of sight
        }

        super.playSound(sound, volume, pitch);
    }


    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.TOOT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return ModSounds.COUGH;
    }

        public boolean hasLineOfSight(PlayerEntity player) {
            World world = this.getWorld();
            Vec3d mobPos = this.getEyePos(); // Get the eye position of the mob
            Vec3d playerPos = player.getEyePos(); // Get the eye position of the player

            BlockHitResult hit = world.raycast(new RaycastContext(
                    mobPos, playerPos,
                    RaycastContext.ShapeType.COLLIDER,
                    RaycastContext.FluidHandling.NONE,
                    this
            ));

            // If the raycast doesn't hit anything, the mob has a direct line of sight to the player
            return hit.getType() == HitResult.Type.MISS || hit.getBlockPos().equals(player.getBlockPos());
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

     */
    }
