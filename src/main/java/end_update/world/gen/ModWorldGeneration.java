package end_update.world.gen;

import end_update.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModWorldGeneration {
    public static void GenerateModWorldGen() {
        BiomeModifications.addFeature(
                // Using the TagKey approach with the updated Identifier/ResourceLocation syntax
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.ENDERITE_ORE_PLACED_KEY
        );
    }
}