package net.phie.nihilitemod.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.phie.nihilitemod.item.ModItems;

public class TooltipHandler {
    public static void register() {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            if (stack.getItem() == ModItems.RAW_NIHILITE) {
                // Add the tooltip to Raw Nihilite
                lines.add(Text.translatable("tooltip.nihilitemod.raw_nihilite")
                        .formatted(Formatting.RED, Formatting.ITALIC));
            }
        });
    }
}
