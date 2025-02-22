package net.dove.eggsandgrits.item.custom;

import net.dove.eggsandgrits.enchantment.ModEnchantments;
import net.dove.eggsandgrits.entity.custom.VapeProjectileEntity;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class VapeItem extends Item {
    public VapeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_LLAMA_SPIT,
                SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        if (!world.isClient) {
            VapeProjectileEntity vape_ring = new VapeProjectileEntity(world, user);
            vape_ring.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 4f, 0f);
            world.spawnEntity(vape_ring);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }


    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true; // Allows enchantments
    }

    @Override
    public int getEnchantability() {
        return 15; // Determines enchantability (same as Iron Sword)
    }

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context) {
        return enchantment == ModEnchantments.LIGHTNING_STRIKER || enchantment == Enchantments.LOOTING;
    }
}
