package net.phie.nihilitemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import net.phie.nihilitemod.block.ModBlocks;
import net.phie.nihilitemod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NIHILITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_NIHILITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_NIHILITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SPAWNABLE_OBSIDIAN_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.NIHILITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_NIHILITE, Models.GENERATED);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NIHILITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NIHILITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NIHILITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NIHILITE_BOOTS));

        itemModelGenerator.register(ModItems.NIHILITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NIHILITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NIHILITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NIHILITE_AXE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.RUNE_OF_SWIFTNESS, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SLEEPING_RUNE_OF_SWIFTNESS, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUNE_OF_HASTE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SLEEPING_RUNE_OF_HASTE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUNE_OF_STRENGTH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SLEEPING_RUNE_OF_STRENGTH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUNE_OF_LEAPING, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SLEEPING_RUNE_OF_LEAPING, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUNE_OF_LUCK, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SLEEPING_RUNE_OF_LUCK, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUNE_OF_FEATHER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SLEEPING_RUNE_OF_FEATHER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUNE_OF_ENDURANCE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SLEEPING_RUNE_OF_ENDURANCE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUNE_OF_NIGHT_VISION, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SLEEPING_RUNE_OF_NIGHT_VISION, Models.HANDHELD);
    }
}
