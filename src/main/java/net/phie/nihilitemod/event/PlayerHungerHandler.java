package net.phie.nihilitemod.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.phie.nihilitemod.item.ModItems;

public class PlayerHungerHandler {

    private static int tickCounter = 0;

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (PlayerEntity player : server.getPlayerManager().getPlayerList()) {
                checkAndDrainHunger(player);
            }
        });
    }

    private static void checkAndDrainHunger(PlayerEntity player) {
        // Count the total amount of Raw Nihilite in the player's inventory
        int rawNihiliteCount = player.getInventory().main.stream()
                .filter(stack -> stack.getItem() == ModItems.RAW_NIHILITE)
                .mapToInt(ItemStack::getCount)
                .sum();

        if (rawNihiliteCount > 0) {
            tickCounter++;

            // Adjust the drain interval based on the amount of Nihilite
            int drainInterval = Math.max(60, 600 - rawNihiliteCount * 20); // Max 600 ticks, min 60 ticks
            if (tickCounter % drainInterval == 0) {
                // Drain hunger only if the player has enough food level
                if (player.getHungerManager().getFoodLevel() > 0) {
                    player.getHungerManager().add(-1, -0.5f); // Drain 0.5 hunger points and saturation
                    player.getWorld().addParticle(ParticleTypes.SMOKE, player.getX(), player.getY(), player.getZ(), 0, 0, 0); // Subtle effect
                }
            }
        } else {
            tickCounter = 0; // Reset the counter if no Raw Nihilite is present
        }
    }
}
