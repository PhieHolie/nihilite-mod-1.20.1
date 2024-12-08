package net.phie.nihilitemod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.phie.nihilitemod.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity) (Object) this;

        if (entity instanceof PlayerEntity player) {
            World world = player.getWorld();

            // Get the damage type
            RegistryEntry<DamageType> damageType = source.getTypeRegistryEntry();

            // Check if the player has the full Nihilite armor set equipped
            if (isWearingFullNihiliteArmor(player)) {
                // Protect against lightning and fire damage
                if (damageType.matchesId(new Identifier("minecraft", "lightning_bolt"))
                        || damageType.matchesId(new Identifier("minecraft", "on_fire"))
                        || damageType.matchesId(new Identifier("minecraft", "in_fire"))) {

                    // Cancel the lightning or fire damage
                    cir.setReturnValue(false); // Cancel damage
                    player.getWorld().addParticle(
                            ParticleTypes.LARGE_SMOKE, // Lightning immunity effect
                            player.getX(),
                            player.getY() + 1,
                            player.getZ(),
                            0.0,
                            0.5,
                            0.0
                    );
                } else {
                    // 10% chance to summon an Endermite when hit
                    if (world.getRandom().nextFloat() < 0.1) { // Random float between 0.0 and 1.0
                        EndermiteEntity endermite = new EndermiteEntity(EntityType.ENDERMITE, world);
                        endermite.refreshPositionAndAngles(
                                player.getX(),
                                player.getY(),
                                player.getZ(),
                                world.random.nextFloat() * 360.0F,
                                0.0F
                        );
                        world.spawnEntity(endermite);
                        // Set the Endermite to target the player
                        endermite.setTarget(player);
                    }
                }
            }
        }
    }

    private boolean isWearingFullNihiliteArmor(PlayerEntity player) {
        return isNihiliteArmor(player.getEquippedStack(EquipmentSlot.HEAD)) &&
                isNihiliteArmor(player.getEquippedStack(EquipmentSlot.CHEST)) &&
                isNihiliteArmor(player.getEquippedStack(EquipmentSlot.LEGS)) &&
                isNihiliteArmor(player.getEquippedStack(EquipmentSlot.FEET));
    }

    private boolean isNihiliteArmor(ItemStack stack) {
        return stack.getItem() == ModItems.NIHILITE_HELMET ||
                stack.getItem() == ModItems.NIHILITE_CHESTPLATE ||
                stack.getItem() == ModItems.NIHILITE_LEGGINGS ||
                stack.getItem() == ModItems.NIHILITE_BOOTS;
    }
}
