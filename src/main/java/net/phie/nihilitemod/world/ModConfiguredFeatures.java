package net.phie.nihilitemod.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.phie.nihilitemod.NihiliteMod;
import net.phie.nihilitemod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_NIHILITE_ORE_KEY = registerKey("end_nihilite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> VOID_NIHILITE_ORE_KEY = registerKey("void_nihilite_ore");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest endReplacables = new BlockMatchRuleTest(Blocks.END_STONE);
        RuleTest voidReplacables = new BlockMatchRuleTest(Blocks.CRYING_OBSIDIAN);

        List<OreFeatureConfig.Target> endNihiliteOres =
                List.of(OreFeatureConfig.createTarget(endReplacables, ModBlocks.RAW_NIHILITE_BLOCK.getDefaultState()));
        List<OreFeatureConfig.Target> voidNihiliteOres =
                List.of(OreFeatureConfig.createTarget(voidReplacables, ModBlocks.RAW_NIHILITE_BLOCK.getDefaultState()));

        register(context, END_NIHILITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(endNihiliteOres, 6));
        register(context, VOID_NIHILITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(voidNihiliteOres, 12));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NihiliteMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
