package end_update.Item;



import java.util.Map;
import java.util.function.Function;

import end_update.EndUpdate;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.IdentifierPattern;
import net.minecraft.resources.ResourceKey;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.Block;

public class ModItems {
    public static final TagKey<Item> REPAIRS_ENDERITE_ARMOR = TagKey.create(
            Registries.ITEM,
            Identifier.fromNamespaceAndPath(EndUpdate.MOD_ID, "repairs_enderite_armor")
    );
    public static final Item RAW_ENDERITE = register("raw_enderite", Item::new, new Item.Properties());
    public static final Item ENDERITE_INGOT = register("enderite_ingot", Item::new, new Item.Properties());
    public static final ResourceKey<EquipmentAsset> ENDERITE_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(EndUpdate.MOD_ID, "enderite"));
    public static final int BASE_DURABILITY = 15;
    public static final ArmorMaterial ENDERITE_ARMOR_MATERIAL = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    ArmorType.HELMET, 4,
                    ArmorType.CHESTPLATE, 9,
                    ArmorType.LEGGINGS, 7,
                    ArmorType.BOOTS, 4
            ),
            5,
            SoundEvents.ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            REPAIRS_ENDERITE_ARMOR,
            ENDERITE_ARMOR_MATERIAL_KEY
    );
    public static final ToolMaterial ENDERITE_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            2500,
            10.0F,
            1.5F,
            22,
            REPAIRS_ENDERITE_ARMOR
    );
    public static final Item ENDERITE_PICKAXE = register("enderite_pickaxe", Item::new, new Item.Properties().pickaxe(ENDERITE_TOOL_MATERIAL, 1.0f, -3.0f)) ;
    public static final Item ENDERITE_SWORD = register("enderite_sword", Item::new, new Item.Properties().sword(ENDERITE_TOOL_MATERIAL, 8.0f, -2.2f));
    public static final Item ENDERITE_AXE = register("enderite_axe", Item::new, new Item.Properties().axe(ENDERITE_TOOL_MATERIAL, 9.0f, -3.0f));
    public static final Item ENDERITE_SHOVEL = register("enderite_shovel", Item::new, new Item.Properties().shovel(ENDERITE_TOOL_MATERIAL, 1.0f, -3.0f));
    public static final Item ENDERITE_HELMET = register(
            "enderite_helmet",
            Item::new,
            new Item.Properties().humanoidArmor(ENDERITE_ARMOR_MATERIAL, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(BASE_DURABILITY))
    );
    public static final Item ENDERITE_CHESTPLATE = register("enderite_chestplate",
            Item::new,
            new Item.Properties().humanoidArmor(ENDERITE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(BASE_DURABILITY))
    );

    public static final Item ENDERITE_LEGGINGS = register(
            "enderite_leggings",
            Item::new,
            new Item.Properties().humanoidArmor(ENDERITE_ARMOR_MATERIAL, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(BASE_DURABILITY))
    );

    public static final Item ENDERITE_BOOTS = register(
            "enderite_boots",
            Item::new,
            new Item.Properties().humanoidArmor(ENDERITE_ARMOR_MATERIAL, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(BASE_DURABILITY))
    );
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
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(fabricItemGroupEntries -> {

            fabricItemGroupEntries.accept(ENDERITE_SWORD);
            fabricItemGroupEntries.accept(ENDERITE_AXE);
            fabricItemGroupEntries.accept(ENDERITE_HELMET);
            fabricItemGroupEntries.accept(ENDERITE_CHESTPLATE);
            fabricItemGroupEntries.accept(ENDERITE_LEGGINGS);
            fabricItemGroupEntries.accept(ENDERITE_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.accept(ENDERITE_PICKAXE);
            fabricItemGroupEntries.accept(ENDERITE_SHOVEL);
        });

        return item;
    }

}