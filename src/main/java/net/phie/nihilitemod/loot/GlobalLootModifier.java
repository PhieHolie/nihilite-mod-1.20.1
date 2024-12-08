package net.phie.nihilitemod.loot;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.phie.nihilitemod.item.ModItems;

public class GlobalLootModifier {
    private static final Identifier MONSTER_LOOT_TABLE_ID = new Identifier("minecraft", "entities");

    public static void register() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (id.getNamespace().equals(MONSTER_LOOT_TABLE_ID.getNamespace()) &&
                    id.getPath().startsWith("entities/")) {
                LootPool pool = LootPool.builder()
                        .with(createItemEntry(ModItems.SLEEPING_RUNE_OF_SWIFTNESS, 0.001f))
                        .with(createItemEntry(ModItems.SLEEPING_RUNE_OF_HASTE, 0.001f))
                        .with(createItemEntry(ModItems.SLEEPING_RUNE_OF_STRENGTH, 0.001f))
                        .with(createItemEntry(ModItems.SLEEPING_RUNE_OF_LEAPING, 0.001f))
                        .with(createItemEntry(ModItems.SLEEPING_RUNE_OF_LUCK, 0.001f))
                        .with(createItemEntry(ModItems.SLEEPING_RUNE_OF_FEATHER, 0.001f))
                        .with(createItemEntry(ModItems.SLEEPING_RUNE_OF_ENDURANCE, 0.001f))
                        .with(createItemEntry(ModItems.SLEEPING_RUNE_OF_NIGHT_VISION, 0.001f))
                        .build();
                supplier.pool(pool);
            }
        });
    }

    private static ItemEntry.Builder<?> createItemEntry(Item item, float dropChance) {
        return ItemEntry.builder(item)
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 1)))
                .conditionally(RandomChanceLootCondition.builder(dropChance));
    }
}
