package net.dove.eggsandgrits.block.custom;

import com.mojang.serialization.MapCodec;
import net.dove.eggsandgrits.item.ModItems;
import net.dove.eggsandgrits.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;

import net.minecraft.entity.ItemEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;

import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class HotDogRollerBlock extends HorizontalFacingBlock {
    public static final MapCodec<HotDogRollerBlock> CODEC = createCodec(HotDogRollerBlock::new);
    private static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15, 16.0, 15.0);

    public HotDogRollerBlock(Settings settings) {
        super(settings);
    }

    /*
    Really cool!! (transofrms items within a radius of the block when clicked)
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        // Play sound on block click
        world.playSound(player, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 1f, 1f);

        // Check if we are on the server side
        if (!world.isClient) {
            // Find item entities at the block position
            List<ItemEntity> entities = world.getEntitiesByClass(ItemEntity.class, new Box(pos).expand(1.0f), entity -> true);

            for (ItemEntity itemEntity : entities) {
                if (isValidItem(itemEntity.getStack())) {
                    // Convert the item into a diamond, preserving its count
                    itemEntity.setStack(new ItemStack(Items.DIAMOND, itemEntity.getStack().getCount()));
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    // Define valid items that can be transformed
    private boolean isValidItem(ItemStack stack) {
        return stack.getItem() == Items.COAL || stack.getItem() == Items.IRON_INGOT; // Add more if needed
    }

*/

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Check if the held item is valid for transformation
        if (!stack.isEmpty() && canTransform(stack)) {
            // Play a sound effect when the transformation happens
            world.playSound(null, pos, ModSounds.SIZZLE, SoundCategory.BLOCKS, 1f, 1f);

            if (!world.isClient) {
                // Get the transformed item
                ItemStack newItem = getTransformedItem(stack);

                // Set the new item in the player's hand, preserving stack size
                //newItem.setCount(stack.getCount());
                //player.setStackInHand(hand, newItem);

                //decreases stack size by 1
                stack.decrement(1);

                //gives player 1 item
                //if (!player.getInventory().insertStack(newItem)) {
                    // If inventory is full, drop the item in the world
                  //  player.dropItem(newItem, false);
                //}

                //Item drops at block POS
                ItemEntity droppedItem = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, newItem);
                droppedItem.setToDefaultPickupDelay(); // Makes it behave like a natural dropped item
                world.spawnEntity(droppedItem);

            }
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;
    }

    // Define which items can be transformed
    private boolean canTransform(ItemStack stack) {
        return stack.getItem() == ModItems.RAW_HOTDOG || stack.getItem() == Items.IRON_INGOT; // Add more as needed
    }

    // Define transformation rules
    private ItemStack getTransformedItem(ItemStack stack) {
        if (stack.getItem() == ModItems.RAW_HOTDOG) {
            return new ItemStack(ModItems.COOKED_HOTDOG,1); // Coal → Diamond
        } else if (stack.getItem() == Items.IRON_INGOT) {
            return new ItemStack(Items.GOLD_INGOT,1); // Iron → Gold
        }
        return stack; // Default: return unchanged (failsafe)
    }


    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }


}