package com.strongmonster.procedure;

import com.strongmonster.access.BuffAccess;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EffectBuff {
    public static void run(Entity entity) {
        if (entity == null)
            return;

        boolean isRain = entity.level().isRaining();

        if (entity instanceof BuffAccess buff_access && buff_access.getBuff()){
            if (isRain){
                if (entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide()){
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 1728000, Mth.nextInt(RandomSource.create(), 2, 3)));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 1728000, 0));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1728000, Mth.nextInt(RandomSource.create(), 1, 3)));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1728000, 0));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1728000, 0));
                }
            } else {
                if (Math.random() < 0.35) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 1728000, Mth.nextInt(RandomSource.create(), 0, 1)));
                    }
                }
                if (Math.random() < 0.4) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1728000, 1));
                    }
                }
                if (Math.random() < 0.7) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1728000, 0));
                    }
                }
                if (Math.random() < 0.5) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 1728000, 0));
                    }
                }
                if (Math.random() < 0.7) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1728000, 0));
                    }
                }
            }
        }
    }
}