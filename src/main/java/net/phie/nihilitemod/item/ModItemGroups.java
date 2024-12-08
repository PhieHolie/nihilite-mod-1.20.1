package net.phie.nihilitemod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.phie.nihilitemod.NihiliteMod;
import net.phie.nihilitemod.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup NIHILITE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(NihiliteMod.MOD_ID, "nihilite"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.nihilite"))
                    .icon(() -> new ItemStack(ModItems.NIHILITE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.NIHILITE);
                        entries.add(ModItems.RAW_NIHILITE);

                        entries.add(ModItems.NIHILITE_SWORD);
                        entries.add(ModItems.NIHILITE_PICKAXE);
                        entries.add(ModItems.NIHILITE_SHOVEL);
                        entries.add(ModItems.NIHILITE_AXE);

                        entries.add(ModItems.NIHILITE_HELMET);
                        entries.add(ModItems.NIHILITE_CHESTPLATE);
                        entries.add(ModItems.NIHILITE_LEGGINGS);
                        entries.add(ModItems.NIHILITE_BOOTS);

                        entries.add(ModItems.SLEEPING_RUNE_OF_SWIFTNESS);
                        entries.add(ModItems.RUNE_OF_SWIFTNESS);
                        entries.add(ModItems.SLEEPING_RUNE_OF_HASTE);
                        entries.add(ModItems.RUNE_OF_HASTE);
                        entries.add(ModItems.SLEEPING_RUNE_OF_STRENGTH);
                        entries.add(ModItems.RUNE_OF_STRENGTH);
                        entries.add(ModItems.SLEEPING_RUNE_OF_LEAPING);
                        entries.add(ModItems.RUNE_OF_LEAPING);
                        entries.add(ModItems.SLEEPING_RUNE_OF_LUCK);
                        entries.add(ModItems.RUNE_OF_LUCK);
                        entries.add(ModItems.SLEEPING_RUNE_OF_FEATHER);
                        entries.add(ModItems.RUNE_OF_FEATHER);
                        entries.add(ModItems.SLEEPING_RUNE_OF_ENDURANCE);
                        entries.add(ModItems.RUNE_OF_ENDURANCE);
                        entries.add(ModItems.SLEEPING_RUNE_OF_NIGHT_VISION);
                        entries.add(ModItems.RUNE_OF_NIGHT_VISION);

                        entries.add(ModBlocks.NIHILITE_BLOCK);
                        entries.add(ModBlocks.RAW_NIHILITE_BLOCK);
                    }).build());

    public static void registerItemGroups() {
        NihiliteMod.LOGGER.info("Registering Item Groups for " + NihiliteMod.MOD_ID);
    }
}
