package net.phie.nihilitemod.world.biome.surface;

import net.phie.nihilitemod.block.ModBlocks;
import net.phie.nihilitemod.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final MaterialRules.MaterialRule NIHILITE = makeStateRule(ModBlocks.NIHILITE_BLOCK);
    private static final MaterialRules.MaterialRule RAW_NIHILITE = makeStateRule(ModBlocks.RAW_NIHILITE_BLOCK);

    public static MaterialRules.MaterialRule makeRules() {
        return MaterialRules.sequence(
                // Rule: If in TEST_BIOME, generate Nihilite and Raw Nihilite under specific conditions
                MaterialRules.condition(MaterialRules.biome(ModBiomes.TEST_BIOME),
                        MaterialRules.sequence(
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, RAW_NIHILITE),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, NIHILITE)
                        )),

                // Default rule: Everything else is obsidian
                OBSIDIAN
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
