package net.phie.nihilitemod.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;

public class RawNihiliteBlock extends ExperienceDroppingBlock {
    public RawNihiliteBlock(Settings settings) {
        super(settings, UniformIntProvider.create(6, 12)); // XP given
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack itemStack) {
        super.afterBreak(world, player, pos, state, blockEntity, itemStack);
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            // spawnEndermites(serverWorld, pos, player, 0, 3); // Spawn 0 to 3 Endermites targeting the player
            // trySpawnEnderman(serverWorld, pos, player); // Rare chance to spawn an Enderman
        }
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


    private void spawnEndermites(ServerWorld world, BlockPos pos, PlayerEntity player, int min, int max) {
        Random random = world.getRandom();
        int endermiteCount = random.nextInt(max - min + 1) + min; // Random count between min and max

        for (int i = 0; i < endermiteCount; i++) {
            EndermiteEntity endermite = new EndermiteEntity(EntityType.ENDERMITE, world);
            endermite.refreshPositionAndAngles(
                    pos.getX() + 0.5 + random.nextDouble() - 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5 + random.nextDouble() - 0.5,
                    random.nextFloat() * 360.0F,
                    0.0F
            );
            endermite.setPersistent(); // Prevent despawning
            endermite.setTarget(player); // Make the Endermite attack the player
            world.spawnEntity(endermite);
            world.emitGameEvent(endermite, GameEvent.ENTITY_PLACE, pos); // Optional: Add a game event
        }
    }

    private void trySpawnEnderman(ServerWorld world, BlockPos pos, PlayerEntity player) {
        Random random = world.getRandom();

        // 5% chance to spawn an Enderman
        if (random.nextFloat() < 0.05) {
            EndermanEntity enderman = new EndermanEntity(EntityType.ENDERMAN, world);
            enderman.refreshPositionAndAngles(
                    pos.getX() + 0.5 + random.nextDouble() - 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5 + random.nextDouble() - 0.5,
                    random.nextFloat() * 360.0F,
                    0.0F
            );
            enderman.setPersistent(); // Prevent despawning
            enderman.setTarget(player); // Make the Enderman attack the player
            world.spawnEntity(enderman);
            world.emitGameEvent(enderman, GameEvent.ENTITY_PLACE, pos); // Optional: Add a game event
        }
    }
}
