package com.strongmonster.procedure;

import com.strongmonster.TheRiseOfHostile;
import com.strongmonster.datagen.tag.TheRiseOfHostileEntityTag;
import com.strongmonster.effect.CurseOfUndead;
import com.strongmonster.effect.CurseOfUndeadRegister;
import com.strongmonster.item.TheRiseOfHostileItem;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class SkeletonShootBadEffect {
    public static void register(){badEffectGive();}

    private static final List<Holder<MobEffect>> BAD_EFFECTS = List.of(
            MobEffects.LEVITATION,
            MobEffects.POISON,
            MobEffects.SLOWNESS,
            MobEffects.WEAKNESS,
            MobEffects.WITHER,
            MobEffects.NAUSEA,
            MobEffects.INSTANT_DAMAGE,
            CurseOfUndeadRegister.CURSE_OF_UNDEAD
    );
    private static void badEffectGive(){
        ServerLivingEntityEvents.AFTER_DAMAGE.register(
                (entity, source, healthAmount, shieldBlocked, a) -> {
                    Entity attacker = source.getEntity();
                    if (attacker instanceof LivingEntity livingAttacker
                            && livingAttacker.getItemBySlot(EquipmentSlot.HEAD).is(TheRiseOfHostileItem.EFFECT_CURSE_HAT)
                            && livingAttacker.getType().is(TheRiseOfHostileEntityTag.SKELETON_BUFF)
                    ){
                        entity.addEffect(
                                new MobEffectInstance(
                                        BAD_EFFECTS.get(
                                                Mth.nextInt(
                                                        entity.level().random,
                                                        0,
                                                        7
                                                )
                                        ),
                                        Mth.nextInt(
                                                entity.level().random,
                                                200,
                                                300
                                        ),
                                        Mth.nextInt(
                                                entity.level().random,
                                                0,
                                                1
                                        )
                                )
                        );
                    }
                }
        );
    }
}
