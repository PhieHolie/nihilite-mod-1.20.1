package net.phie.nihilitemod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.phie.nihilitemod.NihiliteMod;

public class ModBlocks {
    public static final Block NIHILITE_BLOCK = registerBlock("nihilite_block",
            new NetheriteRequiredBlock(FabricBlockSettings
                    .create() // Creates default block settings
                    .strength(90.0f, 2250.0f)
                    .requiresTool() // Ensures the correct tool is required
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK) // Sets sound to stone
            ));

    public static final Block PURE_NIHILITE_BLOCK = registerBlock("pure_nihilite_block",
            new NetheriteRequiredBlock(FabricBlockSettings
                    .create() // Creates default block settings
                    .strength(120.0f, 3000.0f)
                    .requiresTool() // Ensures the correct tool is required
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK) // Sets sound to stone
            ));


    public static final Block RAW_NIHILITE_BLOCK = registerBlock("raw_nihilite_block",
            new RawNihiliteBlock(FabricBlockSettings
                    .create() // Creates default block settings
                    .strength(60.0f, 1500.0f)
                    .requiresTool() // Ensures the correct tool is required
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            ));

    public static final Block SPAWNABLE_OBSIDIAN_BLOCK = registerBlock("spawnable_obsidian_block",
            new SpawnableObsidianBlock(FabricBlockSettings
                    .create() // Creates default block settings
                    .strength(50.0f, 1200.0f)
                    .requiresTool() // Ensures the correct tool is required
                    .sounds(BlockSoundGroup.STONE)
            ));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(NihiliteMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(NihiliteMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        NihiliteMod.LOGGER.info("Registering ModBlocks for " + NihiliteMod.MOD_ID);
    }
}
