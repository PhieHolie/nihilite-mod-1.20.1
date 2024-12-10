package net.phie.nihilitemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.phie.nihilitemod.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.RAW_NIHILITE_BLOCK)
                .add(ModBlocks.NIHILITE_BLOCK)
                .add(ModBlocks.PURE_NIHILITE_BLOCK);

        // Use RegistryKeys.BLOCK to define a custom tag
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("nihilitemod", "needs_netherite_tool")))
                .add(ModBlocks.RAW_NIHILITE_BLOCK)
                .add(ModBlocks.NIHILITE_BLOCK)
                .add(ModBlocks.PURE_NIHILITE_BLOCK);
    }
}
