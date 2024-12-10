package net.phie.nihilitemod.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.phie.nihilitemod.NihiliteMod;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> END_NIHILITE_ORE_PLACED_KEY = registerKey("end_nihilite_ore_placed");
    public static final RegistryKey<PlacedFeature> VOID_NIHILITE_ORE_PLACED_KEY = registerKey("void_nihilite_ore_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistry = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // Register End Nihilite Ore Placement
        register(
                context,
                END_NIHILITE_ORE_PLACED_KEY,
                configuredFeatureRegistry.getOrThrow(ModConfiguredFeatures.END_NIHILITE_ORE_KEY),
                List.of(
                        CountPlacementModifier.of(6), // 8 veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))
                )
        );

        // Register Void Nihilite Ore Placement
        register(
                context,
                VOID_NIHILITE_ORE_PLACED_KEY,
                configuredFeatureRegistry.getOrThrow(ModConfiguredFeatures.VOID_NIHILITE_ORE_KEY),
                List.of(
                        CountPlacementModifier.of(12), // 12 veins per chunk
                                HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))
                )
        );
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(NihiliteMod.MOD_ID, name));
    }

    private static void register(
            Registerable<PlacedFeature> context,
            RegistryKey<PlacedFeature> key,
            RegistryEntry<ConfiguredFeature<?, ?>> configuration,
            List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
