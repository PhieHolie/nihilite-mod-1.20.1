package net.phie.nihilitemod.world;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;

import java.util.Random;

public class PeriodicSpawner {
    private static final Random RANDOM = new Random();
    private static int tickCounter = 0;
    private static int randomTriggerTime = getRandomTriggerTime();

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickCounter++;

            if (tickCounter >= randomTriggerTime) { // Trigger when tickCounter meets random delay
                tickCounter = 0;
                randomTriggerTime = getRandomTriggerTime(); // Reset to a new random time
                server.getWorlds().forEach(world -> {
                    world.getPlayers().forEach(player -> {
                        spawnMobsAroundPlayer(world, player.getBlockPos());
                    });
                });
            }
        });
    }

    private static int getRandomTriggerTime() {
        // Generate a random delay between 200 and 600 ticks (10 to 30 seconds)
        return 400 + RANDOM.nextInt(800);
    }

    private static void spawnMobsAroundPlayer(ServerWorld world, BlockPos playerPos) {
        int mobCount = 1 + RANDOM.nextInt(3); // Random number between 1 and 4

        for (int i = 0; i < mobCount; i++) {
            BlockPos spawnPos = getRandomSpawnPosition(world, playerPos);

            if (spawnPos != null) { // Ensure we got a valid position
                spawnEnderman(world, spawnPos);
            }
        }
    }

    private static BlockPos getRandomSpawnPosition(ServerWorld world, BlockPos playerPos) {
        int minDistance = 10; // Minimum distance from player
        int maxDistance = 30; // Maximum distance from player

        // Calculate random spawn position around the player
        double angle = RANDOM.nextDouble() * 2 * Math.PI; // Random angle
        int distance = RANDOM.nextInt(maxDistance - minDistance) + minDistance;

        int x = playerPos.getX() + (int) (distance * Math.cos(angle));
        int z = playerPos.getZ() + (int) (distance * Math.sin(angle));

        // Find the highest solid block at the calculated position
        int y = world.getTopY(Heightmap.Type.WORLD_SURFACE, x, z);

        BlockPos pos = new BlockPos(x, y, z);

        // Ensure the block below isn't liquid
        if (world.getBlockState(pos.down()).isLiquid()) {
            return null; // Skip if the block below is water or lava
        }

        return pos.up(); // Spawn one block above the ground
    }

    private static void spawnEnderman(ServerWorld world, BlockPos pos) {
        EndermanEntity enderman = EntityType.ENDERMAN.create(world);
        if (enderman != null) {
            enderman.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
            enderman.setPersistent();
            enderman.setTarget(world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 50, false));

            // Add lightning strike effect
            createLightningEffect(world, pos);

            world.spawnEntity(enderman);

            enderman.setTarget(world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 50, false));
        }
    }

    private static void createLightningEffect(ServerWorld world, BlockPos pos) {
        LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
        if (lightning != null) {
            lightning.setCosmetic(true); // Cosmetic lightning doesn't deal damage
            lightning.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
            world.spawnEntity(lightning);
        }
    }
}
