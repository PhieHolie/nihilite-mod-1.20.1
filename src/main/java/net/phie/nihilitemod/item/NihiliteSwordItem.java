package net.phie.nihilitemod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class NihiliteSwordItem extends SwordItem {

    private static final int[] LEVEL_THRESHOLDS = {0, 25, 75, 150, 300, 500, 750, 1000, 1250, 1500}; // XP thresholds for levels
    private static final float[] DAMAGE_BONUSES = {0.0f, 0.5f, 1.0f, 1.5f, 2.5f, 4.0f, 5.5f, 7.0f, 8.5f, 10.0f}; // Bonus damage per level

    public NihiliteSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static int getLevel(ItemStack stack) {
        if (stack.getNbt() == null || !stack.getNbt().contains("MobXP")) {
            return 1; // Default level
        }
        int xp = stack.getNbt().getInt("MobXP");
        return calculateLevel(xp);
    }

    private static int calculateLevel(int xp) {
        for (int i = LEVEL_THRESHOLDS.length - 1; i >= 0; i--) {
            if (xp >= LEVEL_THRESHOLDS[i]) {
                return i + 1; // Levels are 1-indexed
            }
        }
        return 1; // Default level
    }

    private static float getBonusDamage(ItemStack stack) {
        int level = getLevel(stack);
        return level > 0 && level <= DAMAGE_BONUSES.length ? DAMAGE_BONUSES[level - 1] : 0.0f;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {
            // Only count XP for hostile mobs
            if (isHostileMob(target)) {
                NbtCompound nbt = stack.getOrCreateNbt();
                int xp = nbt.getInt("MobXP") + 1; // Increment XP for each mob killed
                nbt.putInt("MobXP", xp);

                // Calculate levels before and after the action
                int currentLevel = calculateLevel(xp - 1);
                int newLevel = calculateLevel(xp);

                if (newLevel > currentLevel) {
                    player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0f, 0.5f);
                    player.sendMessage(
                            Text.literal("Your Nihilite Sword is now Level " + newLevel + "!").formatted(Formatting.GREEN),
                            true
                    );
                }
            }

            // 10% chance to strike lightning
            if (player.getWorld().getRandom().nextFloat() < 0.10) {
                if (!target.getWorld().isClient()) {
                    LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(target.getWorld());
                    Objects.requireNonNull(lightning).refreshPositionAfterTeleport(target.getPos());
                    target.getWorld().spawnEntity(lightning);
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean damage(DamageSource source) {
        return super.damage(source);
    }

    private boolean isHostileMob(LivingEntity entity) {
        // Define a list of hostile mobs
        return entity.getType() == EntityType.ZOMBIE ||
                entity.getType() == EntityType.SKELETON ||
                entity.getType() == EntityType.CREEPER ||
                entity.getType() == EntityType.SPIDER ||
                entity.getType() == EntityType.ENDERMAN ||
                entity.getType() == EntityType.BLAZE ||
                entity.getType() == EntityType.WITHER_SKELETON ||
                entity.getType() == EntityType.ZOMBIFIED_PIGLIN ||
                entity.getType() == EntityType.PHANTOM ||
                entity.getType() == EntityType.SLIME ||
                entity.getType() == EntityType.MAGMA_CUBE ||
                entity.getType() == EntityType.DROWNED ||
                entity.getType() == EntityType.GHAST ||
                entity.getType() == EntityType.HUSK ||
                entity.getType() == EntityType.PILLAGER ||
                entity.getType() == EntityType.RAVAGER ||
                entity.getType() == EntityType.WITCH ||
                entity.getType() == EntityType.WITHER ||
                entity.getType() == EntityType.EVOKER ||
                entity.getType() == EntityType.VINDICATOR ||
                entity.getType() == EntityType.VEX ||
                entity.getType() == EntityType.GUARDIAN ||
                entity.getType() == EntityType.ENDER_DRAGON ||
                entity.getType() == EntityType.ELDER_GUARDIAN;
    }

    @Override
    public float getAttackDamage() {
        return super.getAttackDamage() + getBonusDamage(this.getDefaultStack()); // Base damage + bonus damage
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        int xp = stack.getNbt() != null ? stack.getNbt().getInt("MobXP") : 0;
        int currentLevel = calculateLevel(xp);

        // Calculate XP needed for the next level
        // Levels are 1-indexed
        String xpProgress = (currentLevel < LEVEL_THRESHOLDS.length)
                ? xp + " / " + LEVEL_THRESHOLDS[currentLevel]
                : xp + "";

        tooltip.add(Text.literal("Level: " + currentLevel).formatted(Formatting.AQUA));
        tooltip.add(Text.literal("Monsters hit : " + xpProgress).formatted(Formatting.GRAY));
        tooltip.add(Text.literal("Bonus Damage: +" + getBonusDamage(stack)).formatted(Formatting.RED));

        super.appendTooltip(stack, world, tooltip, context);
    }
}
