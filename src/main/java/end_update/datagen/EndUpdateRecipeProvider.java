package end_update.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import end_update.Item.ModItems;
import end_update.Block.ModBlocks;

public class EndUpdateRecipeProvider extends FabricRecipeProvider {
    public EndUpdateRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                // This is where we will add recipes later!
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
                shaped(RecipeCategory.MISC, ModBlocks.ENDERITE_BLOCK, 1)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .define('#', ModItems.ENDERITE_INGOT) // 'l' means "any log"
                        .group("multi_bench") // Put it in a group called "multi_bench" - groups are shown in one slot in the recipe book
                        .unlockedBy(getHasName(Items.CRAFTING_TABLE), has(Items.CRAFTING_TABLE))
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.ENDERITE_INGOT, 9) // You can also specify an int to produce more than one
                        .requires(ModBlocks.ENDERITE_BLOCK) // You can also specify an int to require more than one, or a tag to accept multiple things
                        // Create an advancement that gives you the recipe
                        .unlockedBy(getHasName(ModBlocks.ENDERITE_BLOCK), has(ModBlocks.ENDERITE_BLOCK))
                        .save(output);
                oreSmelting(
                        List.of(ModItems.RAW_ENDERITE), // Inputs
                        RecipeCategory.MISC, // Category
                        ModItems.ENDERITE_INGOT, // Output
                        0.1f, // Experience
                        300, // Cooking time
                        "Raw_Ore_To_Ingot" // group
                );
                shaped(RecipeCategory.COMBAT, ModItems.ENDERITE_SWORD)
                        .pattern(" e ")
                        .pattern("ene")
                        .pattern(" e ")
                        .define('e', ModItems.ENDERITE_INGOT)
                        .define('n', Items.NETHERITE_SWORD)
                        .unlockedBy(getHasName(ModItems.ENDERITE_INGOT), has(ModItems.ENDERITE_INGOT))
                        .save(output);
                shaped(RecipeCategory.COMBAT, ModItems.ENDERITE_AXE)
                        .pattern(" e ")
                        .pattern("ene")
                        .pattern(" e ")
                        .define('e', ModItems.ENDERITE_INGOT)
                        .define('n', Items.NETHERITE_AXE)
                        .unlockedBy(getHasName(ModItems.ENDERITE_INGOT), has(ModItems.ENDERITE_INGOT))
                        .save(output);
                shaped(RecipeCategory.TOOLS, ModItems.ENDERITE_PICKAXE)
                        .pattern(" e ")
                        .pattern("ene")
                        .pattern(" e ")
                        .define('e', ModItems.ENDERITE_INGOT)
                        .define('n', Items.NETHERITE_PICKAXE)
                        .unlockedBy(getHasName(ModItems.ENDERITE_INGOT), has(ModItems.ENDERITE_INGOT))
                        .save(output);
                shaped(RecipeCategory.COMBAT, ModItems.ENDERITE_SHOVEL)
                        .pattern(" e ")
                        .pattern("ene")
                        .pattern(" e ")
                        .define('e', ModItems.ENDERITE_INGOT)
                        .define('n', Items.NETHERITE_SHOVEL)
                        .unlockedBy(getHasName(ModItems.ENDERITE_INGOT), has(ModItems.ENDERITE_INGOT))
                        .save(output);

            }
        };
    }

    @Override
    public String getName() {
        return "End Update Recipes";
    }
}
