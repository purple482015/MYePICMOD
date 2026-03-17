package end_update;

import end_update.Block.ModBlocks;
import end_update.Item.ModItems;
import end_update.world.gen.ModWorldGeneration;
import entities.EntityRegistry;
import entities.ModEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import renderer.ModRenderer;

public class EndUpdate implements ModInitializer {
	public static final String MOD_ID = "end-update";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution
		ModItems.registerModItems();
		ModBlocks.registerBlocks();
		ModWorldGeneration.GenerateModWorldGen();
		ModEntities.RegisterEntities();
		FabricDefaultAttributeRegistry.register(EntityRegistry.ENDER_WARP, ModEntities.createAttributes());
		// Only register renderer if we're on the client
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			EntityRendererRegistry.register(EntityRegistry.ENDER_WARP,
					context -> new ModRenderer<>(context, EntityRegistry.ENDER_WARP));
		}


		LOGGER.info("Hello Fabric world!");
	}
}