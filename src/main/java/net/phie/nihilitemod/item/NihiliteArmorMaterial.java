package net.phie.nihilitemod.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class NihiliteArmorMaterial implements ArmorMaterial {
    @Override
    public int getDurability(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> 13 * 56;
            case CHESTPLATE -> 15 * 56;
            case LEGGINGS -> 16 * 56;
            case BOOTS -> 11 * 56;
        };
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> 3;
            case CHESTPLATE -> 6;
            case LEGGINGS -> 8;
            case BOOTS -> 3;
        };
    }

    @Override
    public int getEnchantability() {
        return 30; // High enchantability
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.NIHILITE); // Nihilite item if available
    }

    @Override
    public String getName() {
        return "nihilite";
    }

    @Override
    public float getToughness() {
        return 3.0f; // Similar to Netherite
    }

    @Override
    public float getKnockbackResistance() {
        return 0.1f; // Slight knockback resistance
    }
}