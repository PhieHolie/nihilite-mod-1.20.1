package net.phie.nihilitemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.phie.nihilitemod.block.ModBlocks;
import net.phie.nihilitemod.event.PlayerHungerHandler;
import net.phie.nihilitemod.event.RunesHandler;
import net.phie.nihilitemod.event.TooltipHandler;
import net.phie.nihilitemod.item.ModItemGroups;
import net.phie.nihilitemod.item.ModItems;
import net.phie.nihilitemod.loot.GlobalLootModifier;
import net.phie.nihilitemod.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NihiliteMod implements ModInitializer {
    public static final String MOD_ID = "nihilitemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final TagKey<Block> ORES_TAG = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "ores"));
    // Tags for dynamic detection
    private static final TagKey<Block> LOG_TAG = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "logs"));
    private static final TagKey<Block> LEAF_TAG = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "leaves"));
    private static final TagKey<Block> DIGGABLE_TAG = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "mineable/shovel"));

    @Override
    public void onInitialize() {
        // Initialize mod components
        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModWorldGeneration.generateModWorldGen();
        GlobalLootModifier.register();
        RunesHandler.register();
        PlayerHungerHandler.register();
        TooltipHandler.register();

        LOGGER.info("Nihilite Mod Initialized!");

        // Register block break event
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (!world.isClient() && world instanceof ServerWorld serverWorld) {
                handleBlockBreak(serverWorld, player, pos, state);
            }
        });
    }

    private void handleBlockBreak(ServerWorld world, PlayerEntity player, BlockPos pos, BlockState state) {
        if (player.getMainHandStack().getItem() == ModItems.NIHILITE_PICKAXE) {
            handleNihilitePickaxe(world, player, pos);
        } else if (player.getMainHandStack().getItem() == ModItems.NIHILITE_SHOVEL) {
            handleNihiliteShovel(world, player, pos);
        } else if (player.getMainHandStack().getItem() == ModItems.NIHILITE_AXE && isLog(state)) {
            handleNihiliteAxe(world, player, pos);
        }
    }

    private void handleNihilitePickaxe(ServerWorld world, PlayerEntity player, BlockPos pos) {
        BlockPos nearestOre = findNearestBlock(world, pos, 2, this::isOre);
        if (nearestOre != null) {
            createParticlePath(world, pos, nearestOre);
        }
    }

    private void handleNihiliteShovel(ServerWorld world, PlayerEntity player, BlockPos center) {
        iterateBlockArea(center, 1, targetPos -> {
            BlockState targetState = world.getBlockState(targetPos);
            if (isDiggable(targetState) && !targetState.isAir()) {
                world.breakBlock(targetPos, true, player);
            }
        });
    }

    private void handleNihiliteAxe(ServerWorld world, PlayerEntity player, BlockPos center) {
        iterateBlockArea(center, 3, targetPos -> {
            BlockState targetState = world.getBlockState(targetPos);
            if (isLeaf(targetState)) {
                world.breakBlock(targetPos, true, player);
            }
        });
    }

    private BlockPos findNearestBlock(ServerWorld world, BlockPos start, int radius, java.util.function.Predicate<BlockState> condition) {
        BlockPos nearest = null;
        double nearestDistance = Double.MAX_VALUE;

        for (BlockPos currentPos : BlockPos.iterateOutwards(start, radius, radius, radius)) {
            BlockState currentState = world.getBlockState(currentPos);
            if (condition.test(currentState)) {
                double distance = start.getSquaredDistance(currentPos);
                if (distance < nearestDistance) {
                    nearest = currentPos;
                    nearestDistance = distance;
                }
            }
        }
        return nearest;
    }

    private void iterateBlockArea(BlockPos center, int radius, java.util.function.Consumer<BlockPos> action) {
        BlockPos.iterate(center.add(-radius, -radius, -radius), center.add(radius, radius, radius))
                .forEach(action);
    }

    private void createParticlePath(ServerWorld world, BlockPos start, BlockPos end) {
        Vec3d startVec = Vec3d.ofCenter(start);
        Vec3d endVec = Vec3d.ofCenter(end);
        Vec3d direction = endVec.subtract(startVec).normalize();
        double distance = startVec.distanceTo(endVec);

        for (double i = 0; i < distance; i += 0.5) {
            Vec3d particlePos = startVec.add(direction.multiply(i));
            world.spawnParticles(ParticleTypes.END_ROD, particlePos.x, particlePos.y, particlePos.z, 1, 0, 0, 0, 0);
        }
    }

    private boolean isOre(BlockState state) {
        return state.isIn(ORES_TAG) || state.getBlock() == ModBlocks.RAW_NIHILITE_BLOCK;
    }

    private boolean isLog(BlockState state) {
        return state.isIn(LOG_TAG);
    }

    private boolean isLeaf(BlockState state) {
        return state.isIn(LEAF_TAG);
    }

    private boolean isDiggable(BlockState state) {
        return state.isIn(DIGGABLE_TAG);
    }
}
