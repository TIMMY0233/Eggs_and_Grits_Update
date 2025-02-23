package net.dove.eggsandgrits.item.custom;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EclipseItem extends Item {
    public EclipseItem(Settings settings) {
        super(settings);
    }

    private static final int COOLDOWN_TIME = 200;  // 100 ticks, which is 5 seconds


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (player != null && player.getItemCooldownManager().isCoolingDown(this)) {
            // Return early if the item is on cooldown
            return TypedActionResult.fail(player.getStackInHand(hand));
        }

        if (!world.isClient && player != null) {
            // Move time forward to night
            ServerWorld serverWorld = (ServerWorld) world;
            serverWorld.setTimeOfDay(18000);


            // Call a method to increase mob spawns
            increaseMobSpawns(world, player);

            // Optionally, play a sound when the item is used
            }
        player.getItemCooldownManager().set(this, COOLDOWN_TIME);

        return TypedActionResult.success(player.getStackInHand(hand));

    }

    private void increaseMobSpawns(World world, PlayerEntity player) {
        // This function increases mob spawns by temporarily changing spawn settings
        // For simplicity, we'll just spawn a few mobs, but you can adjust spawn rates
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;

            // Spawn a few mobs at random locations (example: a zombie)
            for (int i = 0; i < 5; i++) {
                // Random location near the player
                BlockPos spawnPos = new BlockPos(
                        (int) (player.getX() + (Math.random() * 20 - 2)),
                        (int) player.getY(),
                        (int) (player.getZ() + (Math.random() * 20 - 2))
                );
                ZombieEntity zombie = EntityType.ZOMBIE.create(world);
                if (zombie != null) {
                    zombie.refreshPositionAndAngles(spawnPos, 0.0f, 0.0f);
                    serverWorld.spawnEntity(zombie);
                }
            }

            // You can also modify the mob spawn settings if you want to increase spawn rates globally
            // For example, using world.spawnEntities() or changing the spawn rules
        }
    }
}
