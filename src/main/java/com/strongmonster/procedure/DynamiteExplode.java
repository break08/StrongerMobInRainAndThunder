package com.strongmonster.procedure;

import com.strongmonster.item.TheRiseOfHostileItem;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class DynamiteExplode {
    public static void register(){
        onEntityHurtExplode();
    }
    private static void onEntityHurtExplode(){
        ServerLivingEntityEvents.AFTER_DAMAGE.register(
                (living_entity, source, healthAmount, shieldBlocked, a) -> {
                    Entity attacker = source.getEntity();

                    if (attacker instanceof LivingEntity livingAttacker
                            && !(shieldBlocked == 0)
                            && livingAttacker.getItemBySlot(EquipmentSlot.CHEST).is(TheRiseOfHostileItem.DYNAMITE_ROLL)){
                        livingAttacker.level().explode(
                                livingAttacker,
                                livingAttacker.blockPosition().getX(),
                                livingAttacker.blockPosition().getY(),
                                livingAttacker.blockPosition().getZ(),
                                5.5F,
                                Level.ExplosionInteraction.MOB
                        );
                        if (livingAttacker.isAlive()){
                            livingAttacker.discard();
                        }
                    }
        });
    }
}
