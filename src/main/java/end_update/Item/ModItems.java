package end_update.Item;



import java.util.function.Function;

import end_update.EndUpdate;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

import net.minecraft.resources.Identifier;
import net.minecraft.util.IdentifierPattern;
import net.minecraft.resources.ResourceKey;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class ModItems {
    public static final Item RAW_ENDERITE = register("raw_enderite", Item::new, new Item.Properties());
    public static final Item ENDERITE_INGOT = register("enderite_ingot", Item::new, new Item.Properties());
    public static void registerModItems() {
        System.out.println("Registering Mod Items for " + EndUpdate.MOD_ID);
    }
    public static <GenericItem extends Item> GenericItem register(String name, Function<Item.Properties, GenericItem> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(EndUpdate.MOD_ID, name));

        // Create the item instance.
        GenericItem item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.accept(ENDERITE_INGOT);
            fabricItemGroupEntries.accept(RAW_ENDERITE);
        });

        return item;
    }

}