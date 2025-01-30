package net.dove.eggsandgrits.block.custom;

import net.dove.eggsandgrits.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BeansCropBlock extends CropBlock {
    public static final int MAX_AGE = 6;
    public static final IntProperty AGE = IntProperty.of("age", 0, 6);

    public BeansCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        List<ItemStack> drops = new ArrayList<>();

        // Always drop at least one bean
        drops.add(new ItemStack(ModItems.BEANS, 1));

        // If fully grown, drop extra beans (like carrots/potatoes)
        if (isMature(state)) {
            Random random = new Random();
            int extraDrops = 1 + random.nextInt(3); // Randomly drop 1 to 3 extra beans
            drops.add(new ItemStack(ModItems.BEANS, extraDrops));
        }

        return drops;
    }


    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.BEANS;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
