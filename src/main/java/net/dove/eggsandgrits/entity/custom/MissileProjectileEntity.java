package net.dove.eggsandgrits.entity.custom;

import net.dove.eggsandgrits.entity.ModEntities;
import net.dove.eggsandgrits.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
// Change this if your item package is different

public class MissileProjectileEntity extends ThrownItemEntity {
    public MissileProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }


    public MissileProjectileEntity(World world, LivingEntity owner) {
        super(ModEntities.MISSILE, owner, world); // Register in ModEntities
    }


    // Called when it hits something
    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().createExplosion(null, this.getX(), this.getY(), this.getZ(), 1.0F, World.ExplosionSourceType.MOB);
            this.discard(); // Remove the entity after impact
        }
    }

    // Called when it hits an entity
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 1);

        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.discard();
        }
    }

}

