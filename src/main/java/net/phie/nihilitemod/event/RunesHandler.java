package net.phie.nihilitemod.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.phie.nihilitemod.item.ModItems;

import java.util.Map;

public class RunesHandler {

    // Map to associate rune items with their respective status effects
    private static final Map<Item, StatusEffect> RUNE_EFFECTS = Map.of(
            ModItems.RUNE_OF_SWIFTNESS, StatusEffects.SPEED,
            ModItems.RUNE_OF_HASTE, StatusEffects.HASTE,
            ModItems.RUNE_OF_STRENGTH, StatusEffects.STRENGTH,
            ModItems.RUNE_OF_LEAPING, StatusEffects.JUMP_BOOST,
            ModItems.RUNE_OF_LUCK, StatusEffects.LUCK,
            ModItems.RUNE_OF_FEATHER, StatusEffects.SLOW_FALLING,
            ModItems.RUNE_OF_ENDURANCE, StatusEffects.HEALTH_BOOST,
            ModItems.RUNE_OF_NIGHT_VISION, StatusEffects.NIGHT_VISION
    );

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (PlayerEntity player : server.getPlayerManager().getPlayerList()) {
                handleRunes(player);
            }
        });
    }

    private static void handleRunes(PlayerEntity player) {
        RUNE_EFFECTS.forEach((rune, effect) -> {
            if (player.getOffHandStack().getItem() == rune) {
                // Apply the effect if not already active
                if (!player.hasStatusEffect(effect)) {
                    player.addStatusEffect(new StatusEffectInstance(effect, 600, 1, true, true, true));
                }
            } else {
                // Remove the effect if the rune is no longer in the off-hand
                if (player.hasStatusEffect(effect)) {
                    player.removeStatusEffect(effect);
                }
            }
        });
    }
}
