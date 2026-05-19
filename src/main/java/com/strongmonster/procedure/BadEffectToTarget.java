package com.strongmonster.procedure;

import com.strongmonster.head_lib.CustomGetScoreboard;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
public class BadEffectToTarget {
    public static void register(){onBadEffectGive();}
    private static void onBadEffectGive(){
        ServerLivingEntityEvents.AFTER_DAMAGE.register(
                (entity, source, healthAmount, shieldBlocked, a) -> {
                    Entity attacker = source.getEntity();
                    if (!(attacker == null)) {
                        if (CustomGetScoreboard.getScoreBoard(attacker, "special_buff") == 1
                                && !entity.level().isClientSide()
                                && attacker instanceof LivingEntity livingAttacker
                                && !(livingAttacker.getOffhandItem().isEmpty())
                        ) {
                            if (livingAttacker.getOffhandItem().is(Items.IRON_SHOVEL)) {
                                entity.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 12000, 4));
                            } else if (livingAttacker.getOffhandItem().is(Items.SPIDER_EYE)){
                                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 12000, 1));
                            } else if (livingAttacker.getOffhandItem().is(Items.COBWEB)) {
                                entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 12000, 2));
                            }
                        }
                    }
                }
        );
    }
}
