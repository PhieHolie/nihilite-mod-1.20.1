package net.phie.nihilitemod.world;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.server.network.ServerPlayerEntity;
import net.phie.nihilitemod.block.ModBlocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class WantedLevelManager {
    private static final Random RANDOM = new Random();
    private static final Map<ServerPlayerEntity, Integer> wantedLevels = new HashMap<>();
    private static final int WANTED_LEVEL_DECAY_RATE = 20 * 60; // Decay every 60 seconds (1 minute)
    private static int tickCounter = 0;

    public static void register() {
        // Listen for block breaking events
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (state.getBlock() == ModBlocks.RAW_NIHILITE_BLOCK) {
                ServerPlayerEntity serverPlayer = Objects.requireNonNull(player.getServer()).getPlayerManager().getPlayer(player.getUuid());
                if (serverPlayer != null) {
                    increaseWantedLevel(serverPlayer);
                    triggerEndermanSpawn((ServerWorld) world, serverPlayer, pos);
                }
            }
        });

        // Periodically decay the wanted level
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickCounter++;
            if (tickCounter >= WANTED_LEVEL_DECAY_RATE) {
                tickCounter = 0;
                decayWantedLevels();
            }
        });
    }

    private static void increaseWantedLevel(ServerPlayerEntity player) {
        wantedLevels.put(player, wantedLevels.getOrDefault(player, 0) + 1);
        player.sendMessage(Text.literal("Your wanted level has increased! Current stars: " + wantedLevels.get(player)), true);
    }

    private static void decayWantedLevels() {
        for (Map.Entry<ServerPlayerEntity, Integer> entry : wantedLevels.entrySet()) {
            int newLevel = Math.max(0, entry.getValue() - 1); // Decay by 1 every 2 minutes
            if (newLevel == 0) {
                wantedLevels.remove(entry.getKey());
                entry.getKey().sendMessage(Text.literal("Your wanted level has decreased to 0."), true);
            } else {
                wantedLevels.put(entry.getKey(), newLevel);
                entry.getKey().sendMessage(Text.literal("Your wanted level has decreased! Current stars: " + newLevel), true);
            }
        }
    }

    private static void triggerEndermanSpawn(ServerWorld world, ServerPlayerEntity player, BlockPos pos) {
        int wantedLevel = wantedLevels.getOrDefault(player, 0);

        // Spawn more Endermen based on wanted level
        int mobCount = Math.min(5, wantedLevel); // Cap at 5 Endermen
        for (int i = 0; i < mobCount; i++) {
            BlockPos spawnPos = getRandomSpawnPosition(world, player.getBlockPos());
            if (spawnPos != null) {
                spawnEndermanWithLightning(world, spawnPos, player);
            }
        }
    }

    private static BlockPos getRandomSpawnPosition(ServerWorld world, BlockPos playerPos) {
        int minDistance = 10; // Minimum distance from player
        int maxDistance = 30; // Maximum distance from player

        // Calculate random spawn position around the player
        double angle = RANDOM.nextDouble() * 2 * Math.PI;
        int distance = RANDOM.nextInt(maxDistance - minDistance) + minDistance;

        int x = playerPos.getX() + (int) (distance * Math.cos(angle));
        int z = playerPos.getZ() + (int) (distance * Math.sin(angle));
        int y = world.getTopY(Heightmap.Type.WORLD_SURFACE, x, z);

        BlockPos pos = new BlockPos(x, y, z);
        if (world.getBlockState(pos.down()).isLiquid()) {
            return null;
        }
        return pos.up();
    }

    private static void spawnEndermanWithLightning(ServerWorld world, BlockPos pos, ServerPlayerEntity player) {
        // Spawn Enderman
        EndermanEntity enderman = EntityType.ENDERMAN.create(world);
        if (enderman != null) {
            enderman.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
            enderman.setPersistent();
            enderman.setTarget(player);
            world.spawnEntity(enderman);

            // Add lightning effect
            createLightningEffect(world, pos);
        }
    }

    private static void createLightningEffect(ServerWorld world, BlockPos pos) {
        LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
        if (lightning != null) {
            lightning.setCosmetic(true); // Cosmetic lightning doesn't cause damage or fire
            lightning.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
            world.spawnEntity(lightning);
        }
    }
}
