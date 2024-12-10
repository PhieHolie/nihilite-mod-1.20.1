package net.phie.nihilitemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public class SpawnableObsidianBlock extends Block {
    public SpawnableObsidianBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canMobSpawnInside(BlockState state) {
        return true; // Allow mobs to spawn on this block
    }
}
