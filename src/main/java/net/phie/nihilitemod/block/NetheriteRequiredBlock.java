package net.phie.nihilitemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NetheriteRequiredBlock extends Block {

    public NetheriteRequiredBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        // Custom logic for breaking the block
        if (!world.isClient) {
            ItemStack stack = player.getMainHandStack(); // Get the tool the player is holding
            if (stack.getItem() instanceof ToolItem toolItem) {
                if (toolItem.getMaterial() != ToolMaterials.NETHERITE) {
                    // Prevent breaking and reset the block
                    world.setBlockState(pos, state);
                    return;
                }
            }
        }

        // Call the superclass method if conditions are met
        super.onBreak(world, pos, state, player);
    }

}