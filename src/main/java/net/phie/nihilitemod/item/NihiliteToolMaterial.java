package net.phie.nihilitemod.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class NihiliteToolMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return Integer.MAX_VALUE; // Effectively unbreakable
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 10.0f;
    }

    @Override
    public float getAttackDamage() {
        return 3.0f;
    }

    @Override
    public int getMiningLevel() {
        return 4; // Equivalent to Netherite
    }

    @Override
    public int getEnchantability() {
        return 30; // High enchantability
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}