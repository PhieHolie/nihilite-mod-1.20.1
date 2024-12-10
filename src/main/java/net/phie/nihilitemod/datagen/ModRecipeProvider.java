package net.phie.nihilitemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.phie.nihilitemod.block.ModBlocks;
import net.phie.nihilitemod.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> NIHILITE_SMELTABLES = List.of(ModItems.RAW_NIHILITE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, NIHILITE_SMELTABLES, RecipeCategory.MISC, ModItems.NIHILITE,
                1.5f, 1600, "nihilite");

        offerBlasting(exporter, NIHILITE_SMELTABLES, RecipeCategory.MISC, ModItems.NIHILITE,
                1.5f, 800, "nihilite");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.NIHILITE, RecipeCategory.MISC,
                ModBlocks.NIHILITE_BLOCK);

        // Recipe for Block of Pure Nihilite (9 Nihilite Blocks)
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURE_NIHILITE_BLOCK)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .criterion(hasItem(ModBlocks.NIHILITE_BLOCK), conditionsFromItem(ModBlocks.NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Sword
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.NIHILITE_SWORD)
                .pattern(" N ")
                .pattern(" N ")
                .pattern(" S ")
                .input('N', ModBlocks.PURE_NIHILITE_BLOCK)
                .input('S', net.minecraft.item.Items.STICK)
                .criterion(hasItem(ModBlocks.PURE_NIHILITE_BLOCK), conditionsFromItem(ModBlocks.PURE_NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Pickaxe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.NIHILITE_PICKAXE)
                .pattern("NNN")
                .pattern(" S ")
                .pattern(" S ")
                .input('N', ModBlocks.PURE_NIHILITE_BLOCK)
                .input('S', net.minecraft.item.Items.STICK)
                .criterion(hasItem(ModBlocks.PURE_NIHILITE_BLOCK), conditionsFromItem(ModBlocks.PURE_NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Axe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.NIHILITE_AXE)
                .pattern("NN ")
                .pattern("NS ")
                .pattern(" S ")
                .input('N', ModBlocks.PURE_NIHILITE_BLOCK)
                .input('S', net.minecraft.item.Items.STICK)
                .criterion(hasItem(ModBlocks.PURE_NIHILITE_BLOCK), conditionsFromItem(ModBlocks.PURE_NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Shovel
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.NIHILITE_SHOVEL)
                .pattern(" N ")
                .pattern(" S ")
                .pattern(" S ")
                .input('N', ModBlocks.PURE_NIHILITE_BLOCK)
                .input('S', net.minecraft.item.Items.STICK)
                .criterion(hasItem(ModBlocks.PURE_NIHILITE_BLOCK), conditionsFromItem(ModBlocks.PURE_NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Helmet
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.NIHILITE_HELMET)
                .pattern("NNN")
                .pattern("N N")
                .input('N', ModBlocks.PURE_NIHILITE_BLOCK)
                .criterion(hasItem(ModBlocks.PURE_NIHILITE_BLOCK), conditionsFromItem(ModBlocks.PURE_NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Chestplate
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.NIHILITE_CHESTPLATE)
                .pattern("N N")
                .pattern("NNN")
                .pattern("NNN")
                .input('N', ModBlocks.PURE_NIHILITE_BLOCK)
                .criterion(hasItem(ModBlocks.PURE_NIHILITE_BLOCK), conditionsFromItem(ModBlocks.PURE_NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Leggings
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.NIHILITE_LEGGINGS)
                .pattern("NNN")
                .pattern("N N")
                .pattern("N N")
                .input('N', ModBlocks.PURE_NIHILITE_BLOCK)
                .criterion(hasItem(ModBlocks.PURE_NIHILITE_BLOCK), conditionsFromItem(ModBlocks.PURE_NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Boots
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.NIHILITE_BOOTS)
                .pattern("N N")
                .pattern("N N")
                .input('N', ModBlocks.PURE_NIHILITE_BLOCK)
                .criterion(hasItem(ModBlocks.PURE_NIHILITE_BLOCK), conditionsFromItem(ModBlocks.PURE_NIHILITE_BLOCK))
                .offerTo(exporter);

        // Runes Recipes
        createRuneRecipe(exporter, ModItems.RUNE_OF_SWIFTNESS, ModItems.SLEEPING_RUNE_OF_SWIFTNESS);
        createRuneRecipe(exporter, ModItems.RUNE_OF_HASTE, ModItems.SLEEPING_RUNE_OF_HASTE);
        createRuneRecipe(exporter, ModItems.RUNE_OF_STRENGTH, ModItems.SLEEPING_RUNE_OF_STRENGTH);
        createRuneRecipe(exporter, ModItems.RUNE_OF_LEAPING, ModItems.SLEEPING_RUNE_OF_LEAPING);
        createRuneRecipe(exporter, ModItems.RUNE_OF_LUCK, ModItems.SLEEPING_RUNE_OF_LUCK);
        createRuneRecipe(exporter, ModItems.RUNE_OF_FEATHER, ModItems.SLEEPING_RUNE_OF_FEATHER);
        createRuneRecipe(exporter, ModItems.RUNE_OF_ENDURANCE, ModItems.SLEEPING_RUNE_OF_ENDURANCE);
        createRuneRecipe(exporter, ModItems.RUNE_OF_NIGHT_VISION, ModItems.SLEEPING_RUNE_OF_NIGHT_VISION);
    }

    private void createRuneRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible rune, ItemConvertible sleepingRune) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, rune)
                .pattern("NNN")
                .pattern("NSN")
                .pattern("NNN")
                .input('N', ModBlocks.PURE_NIHILITE_BLOCK)
                .input('S', sleepingRune)
                .criterion(hasItem(sleepingRune), conditionsFromItem(sleepingRune))
                .offerTo(exporter);
    }
}
