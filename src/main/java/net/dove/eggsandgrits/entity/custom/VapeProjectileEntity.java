package net.dove.eggsandgrits.entity.custom;

import net.dove.eggsandgrits.entity.ModEntities;
import net.dove.eggsandgrits.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.joml.Vector2f;

public class VapeProjectileEntity extends PersistentProjectileEntity {
    private float rotation;
    public Vector2f groundedOffset;


    public VapeProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public VapeProjectileEntity(World world, PlayerEntity player) {
        super(ModEntities.VAPE_RING, player, world, new ItemStack(ModItems.VAPE), null);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.VAPE);
    }

    public float getRenderingRotation() {
        rotation += 0f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public boolean isGrounded() {
        return inGround;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 40);

        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        super.onBlockHit(result);

        if(result.getSide() == Direction.SOUTH) {
            groundedOffset = new Vector2f(215f,180f);
        }
        if(result.getSide() == Direction.NORTH) {
            groundedOffset = new Vector2f(215f, 0f);
        }
        if(result.getSide() == Direction.EAST) {
            groundedOffset = new Vector2f(215f,-90f);
        }
        if(result.getSide() == Direction.WEST) {
            groundedOffset = new Vector2f(215f,90f);
        }

        if(result.getSide() == Direction.DOWN) {
            groundedOffset = new Vector2f(115f,180f);
        }
        if(result.getSide() == Direction.UP) {
            groundedOffset = new Vector2f(285f,180f);
        }
    }

}
