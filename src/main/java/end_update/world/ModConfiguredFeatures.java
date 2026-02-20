package end_update.world;

import end_update.EndUpdate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ENDERITE_ORE_KEY = registerKey("enderite_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest end_stoneReplaceables = new BlockMatchTest(Blocks.END_STONE);
        List<OreConfiguration.TargetBlockState> endTargets = List.of(
                OreConfiguration.target(end_stoneReplaceables, Blocks.COAL_ORE.defaultBlockState())
        );
        register(context, ENDERITE_ORE_KEY, Feature.ORE, new OreConfiguration(endTargets, 9));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        // FIXED: Correct class name is ResourceLocation, and fixed the missing closing parenthesis
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                net.minecraft.resources.Identifier.fromNamespaceAndPath(EndUpdate.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
