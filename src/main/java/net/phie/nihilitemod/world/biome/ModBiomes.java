package net.phie.nihilitemod.world.biome;

import net.phie.nihilitemod.NihiliteMod;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.phie.nihilitemod.world.ModPlacedFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> TEST_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(NihiliteMod.MOD_ID, "test_biome"));

    public static void bootstrap(Registerable<Biome> context) {
        context.register(TEST_BIOME, testBiome(context));
    }

    public static Biome testBiome(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        // Add Endermites
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.ENDERMITE, 5, 2, 6));

        // Add Endermen
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES,
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.VOID_NIHILITE_ORE_PLACED_KEY));

        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);

        return new Biome.Builder()
                .precipitation(false) // Allow precipitation
                .temperature(0.7f) // Biome temperature
                .downfall(0.4f) // Biome rainfall level
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x4B0082) // Set water color to dark purple (Indigo)
                        .waterFogColor(0x2E0854) // Set water fog color to an even darker purple
                        .skyColor(0x38003C) // Deep End-themed sky color
                        .fogColor(0x1A001E) // End-like fog
                        .grassColor(0x2E013F) // Purple with black hue
                        .foliageColor(0x1E0130) // Dark purple
                        .moodSound(BiomeMoodSound.CAVE) // Cave sound for ambiance
                        .build())
                .build();
    }
}