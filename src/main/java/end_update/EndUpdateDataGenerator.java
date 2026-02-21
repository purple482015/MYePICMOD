package end_update;


import end_update.datagen.EndUpdateRecipeProvider;
import end_update.world.ModConfiguredFeatures;
import end_update.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

import java.util.concurrent.CompletableFuture;

public class EndUpdateDataGenerator implements DataGeneratorEntrypoint
{
	@Override

	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(EndUpdateRecipeProvider::new);
		pack.addProvider(ModWorldGenerator::new);

	}

	@Override
	public void buildRegistry(RegistrySetBuilder registrySetBuilder) {
		registrySetBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registrySetBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}

	public static class ModWorldGenerator extends FabricDynamicRegistryProvider {
		public ModWorldGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		protected void configure(HolderLookup.Provider registries, Entries entries) {
			entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
			entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
		}

		@Override
		public String getName() {
			return "End Update World Gen";
		}
	}

}