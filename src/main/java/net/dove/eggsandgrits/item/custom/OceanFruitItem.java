package net.dove.eggsandgrits.item.custom;

import net.dove.eggsandgrits.EggsAndGrits;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OceanFruitItem extends Item {
    public OceanFruitItem(Settings settings) {
        super(settings.food(new FoodComponent.Builder().saturationModifier(0.3f).alwaysEdible().build()));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof ServerPlayerEntity player) {
            ServerWorld oceanDim = player.getServer().getWorld(RegistryKey.of(RegistryKeys.WORLD, Identifier.of(EggsAndGrits.MOD_ID, "oceandim")));

            if (oceanDim != null) {
                BlockPos teleportPos = new BlockPos(20, 113, 26); // Fixed teleport location
                player.teleport(oceanDim, teleportPos.getX(), teleportPos.getY(), teleportPos.getZ(), player.getYaw(), player.getPitch());
            }
        }
        return super.finishUsing(stack, world, user);
    }
}
