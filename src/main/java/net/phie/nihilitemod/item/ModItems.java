package net.phie.nihilitemod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.phie.nihilitemod.NihiliteMod;

public class ModItems {
    public static final Item NIHILITE = registerItem("nihilite", new Item(new FabricItemSettings()));
    public static final Item RAW_NIHILITE = registerItem("raw_nihilite", new Item(new FabricItemSettings()));

    public static final Item NIHILITE_SWORD = registerItem("nihilite_sword", new NihiliteSwordItem(
            new NihiliteToolMaterial(),
            6, // Attack damage
            -2.4f, // Attack speed
            new Item.Settings().fireproof().maxCount(1) // Fireproof and unstackable
    ));

    public static final Item NIHILITE_PICKAXE = registerItem("nihilite_pickaxe", new NihilitePickaxeItem(new NihiliteToolMaterial(),
            4,
            -2.8f,
            new Item.Settings().fireproof().maxCount(1)
    ));

    public static final Item NIHILITE_SHOVEL = registerItem("nihilite_shovel", new NihiliteShovelItem(new NihiliteToolMaterial(),
            4,
            -2.8f,
            new Item.Settings().fireproof().maxCount(1)
    ));

    public static final Item NIHILITE_AXE = registerItem("nihilite_axe", new NihiliteAxeItem(new NihiliteToolMaterial(),
            8,
            -3.0f,
            new Item.Settings().fireproof().maxCount(1)
    ));

    public static final ArmorMaterial NIHILITE_ARMOR_MATERIAL = new NihiliteArmorMaterial();

    public static final Item NIHILITE_HELMET = registerItem("nihilite_helmet", new ArmorItem(
            NIHILITE_ARMOR_MATERIAL,
            ArmorItem.Type.HELMET,
            new Item.Settings().fireproof().maxCount(1)
    ));

    public static final Item NIHILITE_CHESTPLATE = registerItem("nihilite_chestplate", new ArmorItem(
            NIHILITE_ARMOR_MATERIAL,
            ArmorItem.Type.CHESTPLATE,
            new Item.Settings().fireproof().maxCount(1)
    ));

    public static final Item NIHILITE_LEGGINGS = registerItem("nihilite_leggings", new ArmorItem(
            NIHILITE_ARMOR_MATERIAL,
            ArmorItem.Type.LEGGINGS,
            new Item.Settings().fireproof().maxCount(1)
    ));

    public static final Item NIHILITE_BOOTS = registerItem("nihilite_boots", new ArmorItem(
            NIHILITE_ARMOR_MATERIAL,
            ArmorItem.Type.BOOTS,
            new Item.Settings().fireproof().maxCount(1)
    ));

    public static final Item RUNE_OF_SWIFTNESS = registerItem("rune_of_swiftness", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SLEEPING_RUNE_OF_SWIFTNESS = registerItem("sleeping_rune_of_swiftness", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item RUNE_OF_HASTE = registerItem("rune_of_haste", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SLEEPING_RUNE_OF_HASTE = registerItem("sleeping_rune_of_haste", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item RUNE_OF_STRENGTH = registerItem("rune_of_strength", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SLEEPING_RUNE_OF_STRENGTH = registerItem("sleeping_rune_of_strength", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item RUNE_OF_LEAPING = registerItem("rune_of_leaping", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SLEEPING_RUNE_OF_LEAPING = registerItem("sleeping_rune_of_leaping", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item RUNE_OF_LUCK = registerItem("rune_of_luck", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SLEEPING_RUNE_OF_LUCK = registerItem("sleeping_rune_of_luck", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item RUNE_OF_FEATHER = registerItem("rune_of_feather", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SLEEPING_RUNE_OF_FEATHER = registerItem("sleeping_rune_of_feather", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item RUNE_OF_ENDURANCE = registerItem("rune_of_endurance", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SLEEPING_RUNE_OF_ENDURANCE = registerItem("sleeping_rune_of_endurance", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item RUNE_OF_NIGHT_VISION = registerItem("rune_of_night_vision", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SLEEPING_RUNE_OF_NIGHT_VISION = registerItem("sleeping_rune_of_night_vision", new Item(new FabricItemSettings().maxCount(1)));


    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(NIHILITE);
    }

    private static void addItemsToCombatTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(NIHILITE_SWORD);
        entries.add(NIHILITE_HELMET);
        entries.add(NIHILITE_CHESTPLATE);
        entries.add(NIHILITE_LEGGINGS);
        entries.add(NIHILITE_BOOTS);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(NihiliteMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NihiliteMod.LOGGER.info("Registering Mod Items for " + NihiliteMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatTabItemGroup);
    }
}
