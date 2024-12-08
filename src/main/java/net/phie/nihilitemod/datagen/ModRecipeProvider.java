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

        // Nihilite Sword
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.NIHILITE_SWORD)
                .pattern(" N ")
                .pattern(" N ")
                .pattern(" S ")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', net.minecraft.item.Items.STICK)
                .criterion(hasItem(ModBlocks.NIHILITE_BLOCK), conditionsFromItem(ModBlocks.NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Pickaxe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.NIHILITE_PICKAXE)
                .pattern("NNN")
                .pattern(" S ")
                .pattern(" S ")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', net.minecraft.item.Items.STICK)
                .criterion(hasItem(ModBlocks.NIHILITE_BLOCK), conditionsFromItem(ModBlocks.NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Axe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.NIHILITE_AXE)
                .pattern("NN ")
                .pattern("NS ")
                .pattern(" S ")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', net.minecraft.item.Items.STICK)
                .criterion(hasItem(ModBlocks.NIHILITE_BLOCK), conditionsFromItem(ModBlocks.NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Shovel
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.NIHILITE_SHOVEL)
                .pattern(" N ")
                .pattern(" S ")
                .pattern(" S ")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', net.minecraft.item.Items.STICK)
                .criterion(hasItem(ModBlocks.NIHILITE_BLOCK), conditionsFromItem(ModBlocks.NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Hoe
        /*ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.NIHILITE_HOE)
                .pattern("NN ")
                .pattern(" S ")
                .pattern(" S ")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', net.minecraft.item.Items.STICK)
                .criterion(hasItem(ModBlocks.NIHILITE_BLOCK), conditionsFromItem(ModBlocks.NIHILITE_BLOCK))
                .offerTo(exporter);*/

        // Nihilite Helmet
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.NIHILITE_HELMET)
                .pattern("NNN")
                .pattern("N N")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .criterion(hasItem(ModBlocks.NIHILITE_BLOCK), conditionsFromItem(ModBlocks.NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Chestplate
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.NIHILITE_CHESTPLATE)
                .pattern("N N")
                .pattern("NNN")
                .pattern("NNN")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .criterion(hasItem(ModBlocks.NIHILITE_BLOCK), conditionsFromItem(ModBlocks.NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Leggings
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.NIHILITE_LEGGINGS)
                .pattern("NNN")
                .pattern("N N")
                .pattern("N N")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .criterion(hasItem(ModBlocks.NIHILITE_BLOCK), conditionsFromItem(ModBlocks.NIHILITE_BLOCK))
                .offerTo(exporter);

        // Nihilite Boots
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.NIHILITE_BOOTS)
                .pattern("N N")
                .pattern("N N")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .criterion(hasItem(ModBlocks.NIHILITE_BLOCK), conditionsFromItem(ModBlocks.NIHILITE_BLOCK))
                .offerTo(exporter);

        // Recipe for Awakening the Sleeping Rune of Swiftness
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUNE_OF_SWIFTNESS)
                .pattern("NNN")
                .pattern("NSN")
                .pattern("NNN")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', ModItems.SLEEPING_RUNE_OF_SWIFTNESS)
                .criterion(hasItem(ModItems.SLEEPING_RUNE_OF_SWIFTNESS), conditionsFromItem(ModItems.SLEEPING_RUNE_OF_SWIFTNESS))
                .offerTo(exporter);


        // Recipe for Awakening the Sleeping Rune of Haste
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUNE_OF_HASTE)
                .pattern("NNN")
                .pattern("NSN")
                .pattern("NNN")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', ModItems.SLEEPING_RUNE_OF_HASTE)
                .criterion(hasItem(ModItems.SLEEPING_RUNE_OF_HASTE), conditionsFromItem(ModItems.SLEEPING_RUNE_OF_HASTE))
                .offerTo(exporter);

        // Recipe for Awakening the Sleeping Rune of Strength
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUNE_OF_STRENGTH)
                .pattern("NNN")
                .pattern("NSN")
                .pattern("NNN")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', ModItems.SLEEPING_RUNE_OF_STRENGTH)
                .criterion(hasItem(ModItems.SLEEPING_RUNE_OF_STRENGTH), conditionsFromItem(ModItems.SLEEPING_RUNE_OF_STRENGTH))
                .offerTo(exporter);

        // Recipe for Awakening the Sleeping Rune of Leaping
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUNE_OF_LEAPING)
                .pattern("NNN")
                .pattern("NSN")
                .pattern("NNN")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', ModItems.SLEEPING_RUNE_OF_LEAPING)
                .criterion(hasItem(ModItems.SLEEPING_RUNE_OF_LEAPING), conditionsFromItem(ModItems.SLEEPING_RUNE_OF_LEAPING))
                .offerTo(exporter);

        // Recipe for Awakening the Sleeping Rune of Luck
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUNE_OF_LUCK)
                .pattern("NNN")
                .pattern("NSN")
                .pattern("NNN")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', ModItems.SLEEPING_RUNE_OF_LUCK)
                .criterion(hasItem(ModItems.SLEEPING_RUNE_OF_LUCK), conditionsFromItem(ModItems.SLEEPING_RUNE_OF_LUCK))
                .offerTo(exporter);

        // Recipe for Awakening the Sleeping Rune of Feather (Slow Falling)
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUNE_OF_FEATHER)
                .pattern("NNN")
                .pattern("NSN")
                .pattern("NNN")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', ModItems.SLEEPING_RUNE_OF_FEATHER)
                .criterion(hasItem(ModItems.SLEEPING_RUNE_OF_FEATHER), conditionsFromItem(ModItems.SLEEPING_RUNE_OF_FEATHER))
                .offerTo(exporter);

        // Recipe for Awakening the Sleeping Rune of Endurance (Health Boost)
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUNE_OF_ENDURANCE)
                .pattern("NNN")
                .pattern("NSN")
                .pattern("NNN")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', ModItems.SLEEPING_RUNE_OF_ENDURANCE)
                .criterion(hasItem(ModItems.SLEEPING_RUNE_OF_ENDURANCE), conditionsFromItem(ModItems.SLEEPING_RUNE_OF_ENDURANCE))
                .offerTo(exporter);

        // Recipe for Awakening the Sleeping Rune of Night Vision
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUNE_OF_NIGHT_VISION)
                .pattern("NNN")
                .pattern("NSN")
                .pattern("NNN")
                .input('N', ModBlocks.NIHILITE_BLOCK)
                .input('S', ModItems.SLEEPING_RUNE_OF_NIGHT_VISION)
                .criterion(hasItem(ModItems.SLEEPING_RUNE_OF_NIGHT_VISION), conditionsFromItem(ModItems.SLEEPING_RUNE_OF_NIGHT_VISION))
                .offerTo(exporter);

    }
}
