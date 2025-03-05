package net.dove.eggsandgrits.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;

import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.minecraft.server.MinecraftServer;


public class CrateItem extends Item {
    public CrateItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();

        if (!world.isClient && player != null) {

            // Play sound effect immediately
            world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_BASALT_HIT, SoundCategory.PLAYERS, 0.5f, 1.0f);

            if (world instanceof ServerWorld) {
                // Use Minecraft's server scheduler to run after 3 seconds (60 ticks)
                MinecraftServer server = ((ServerWorld) world).getServer();
                server.execute(() -> giveReward(player));  // Schedule task to run

                ((ServerWorld) world).spawnParticles(ParticleTypes.FIREWORK,
                        context.getBlockPos().getX() + 0.5,
                        context.getBlockPos().getY() + 1.2,
                        context.getBlockPos().getZ() + 0.5, 20, 0,0,0,0.3);
            }
            ItemStack stack = player.getStackInHand(context.getHand());
            stack.decrement(1);

        }
        return ActionResult.SUCCESS;
    }

    private void giveReward(PlayerEntity player) {
        // Pick a random item from the loot table
        Item reward = CrateLootTable.getRandomLoot();

        // Get the rarity of the item from CrateLootTable
        Rarity rarity = getRarityForItem(reward);

        // Create a new ItemStack for the reward
        ItemStack rewardStack = new ItemStack(reward);

        // Give the item to the player
        boolean addedToInventory = player.getInventory().insertStack(rewardStack);
        if(!addedToInventory){
            player.dropItem(rewardStack, false);
        }

        // Show reward message to the player
        player.sendMessage(Text.literal("You received: ")
                .append(Text.literal(reward.getName().getString())
                        .setStyle(Style.EMPTY.withColor(rarity.getColor()).withBold(false).withItalic(false))), true);

        // If the item is epic, announce it to the whole server
        if (rarity == Rarity.EPIC) {
            // Broadcast to all players
            if (player.getWorld() instanceof ServerWorld serverWorld) {
                serverWorld.getPlayers().forEach(p ->
                        p.sendMessage(Text.literal(player.getName().getString() + " received: ")
                                .append(Text.literal(reward.getName().getString()).setStyle(Style.EMPTY.withColor(Formatting.LIGHT_PURPLE).withBold(false).withItalic(true)))));

            }
        }
    }


    // Helper method to get the rarity of an item
    private Rarity getRarityForItem(Item item) {
        if (CrateLootTable.epicItems.contains(item)) {
            return Rarity.EPIC;
        } else if (CrateLootTable.rareItems.contains(item)) {
            return Rarity.RARE;
        } else if (CrateLootTable.uncommonItems.contains(item)) {
            return Rarity.UNCOMMON;
        } else {
            return Rarity.COMMON;
        }
    }


}



