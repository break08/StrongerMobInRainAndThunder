package com.strongmonster.effect;

import java.lang.Math;

import com.strongmonster.datagen.TheRiseOfHostileItemTag;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class CurseOfUndead extends MobEffect {
    protected CurseOfUndead() {
        super(MobEffectCategory.HARMFUL, 0xe9b8b3);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        BlockPos entityPos = BlockPos.containing(entity.position());
        boolean setOnFire = level.canSeeSky(entityPos);
        if (entity instanceof Player
                && setOnFire
                && level.isBrightOutside()
                && !entity.getItemBySlot(EquipmentSlot.HEAD).is(TheRiseOfHostileItemTag.IS_HELMET)
        ) {
            entity.igniteForSeconds(1);
        } else if (level.isBrightOutside() && entity.getItemBySlot(EquipmentSlot.HEAD).is(TheRiseOfHostileItemTag.IS_HELMET) && Math.random() < 0.4) {
            entity.getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(
                    1,
                    entity,
                    EquipmentSlot.HEAD
            );
        }

        if (entity instanceof LivingEntity _livEnt9 && _livEnt9.hasEffect(MobEffects.INSTANT_HEALTH)) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                _entity.addEffect(new MobEffectInstance(MobEffects.INSTANT_DAMAGE, entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.INSTANT_HEALTH) ? _livEnt.getEffect(MobEffects.INSTANT_HEALTH).getDuration() : 0,
                        entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.INSTANT_HEALTH) ? _livEnt.getEffect(MobEffects.INSTANT_HEALTH).getAmplifier() : 0));
            }
            if (entity instanceof LivingEntity _entity) {
                _entity.removeEffect(MobEffects.INSTANT_HEALTH);
            }
        } else if (entity instanceof LivingEntity _livEnt14 && _livEnt14.hasEffect(MobEffects.INSTANT_DAMAGE)) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                _entity.addEffect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.INSTANT_DAMAGE) ? _livEnt.getEffect(MobEffects.INSTANT_DAMAGE).getDuration() : 0,
                        entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.INSTANT_DAMAGE) ? _livEnt.getEffect(MobEffects.INSTANT_DAMAGE).getAmplifier() : 0));
            }
            if (entity instanceof LivingEntity _entity) {
                _entity.removeEffect(MobEffects.INSTANT_DAMAGE);
            }
        }


        return super.applyEffectTick(level, entity, amplifier);
    }
}