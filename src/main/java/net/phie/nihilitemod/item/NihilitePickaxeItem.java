package net.phie.nihilitemod.item;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.phie.nihilitemod.block.ModBlocks;

import java.util.List;

import static net.phie.nihilitemod.NihiliteMod.ORES_TAG;

public class NihilitePickaxeItem extends PickaxeItem {

    private static final int[] LEVEL_THRESHOLDS = {0, 25, 60, 120, 200, 300, 450, 650, 900, 1200}; // XP thresholds for levels
    private static final float[] LEVEL_SPEEDS = {10.0f, 10.5f, 11.0f, 12.0f, 13.0f, 14.5f, 16.0f, 17.5f, 19.0f, 20.0f}; // Mining speeds per level

    public NihilitePickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static int getLevel(ItemStack stack) {
        if (stack.getNbt() == null || !stack.getNbt().contains("OresMined")) {
            return 1; // Default level
        }
        int oresMined = stack.getNbt().getInt("OresMined");
        return calculateLevel(oresMined);
    }

    private static int calculateLevel(int oresMined) {
        for (int i = LEVEL_THRESHOLDS.length - 1; i >= 0; i--) {
            if (oresMined >= LEVEL_THRESHOLDS[i]) {
                return i + 1; // Levels are 1-indexed
            }
        }
        return 1; // Default level
    }

    private boolean isOre(BlockState state) {
        return state.isIn(ORES_TAG) || state.getBlock() == ModBlocks.RAW_NIHILITE_BLOCK;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient && miner instanceof PlayerEntity player) {
            if (isOre(state)) {
                NbtCompound nbt = stack.getOrCreateNbt();
                int oresMined = nbt.getInt("OresMined") + 1; // Increment ores mined
                nbt.putInt("OresMined", oresMined);

                // Calculate levels before and after the action
                int currentLevel = calculateLevel(oresMined - 1);
                int newLevel = calculateLevel(oresMined);

                if (newLevel > currentLevel) {
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0f, 0.5f);
                    player.sendMessage(
                            Text.literal("Your Nihilite Pickaxe is now Level " + newLevel + "!").formatted(Formatting.GREEN),
                            true
                    );
                }
            }
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        int level = getLevel(stack);
        return level > 0 && level <= LEVEL_SPEEDS.length ? LEVEL_SPEEDS[level - 1] : 10.0f;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        int oresMined = stack.getNbt() != null ? stack.getNbt().getInt("OresMined") : 0;
        int currentLevel = calculateLevel(oresMined);

        // Calculate ores needed for the next level
        int nextLevelIndex = currentLevel; // Levels are 1-indexed
        String oresProgress = (nextLevelIndex < LEVEL_THRESHOLDS.length)
                ? oresMined + " / " + LEVEL_THRESHOLDS[nextLevelIndex]
                : oresMined + "";

        tooltip.add(Text.literal("Level: " + currentLevel).formatted(Formatting.AQUA));
        tooltip.add(Text.literal("Ores Mined: " + oresProgress).formatted(Formatting.GRAY));
        tooltip.add(Text.literal("Mining Speed: " + getMiningSpeedMultiplier(stack, null)).formatted(Formatting.GOLD));

        super.appendTooltip(stack, world, tooltip, context);
    }
}
