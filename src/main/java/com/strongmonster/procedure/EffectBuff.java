package com.strongmonster.procedure;

import com.strongmonster.mixin.nbt_mix.BuffAccess;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class EffectBuff {
    public static void run(Level level, Entity entity) {
        if (entity == null)
            return;

        boolean isRain = entity.level().isRaining();

        if (((BuffAccess) entity).getBuff()){
            if (isRain){
                if (entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide()){
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 28800, Mth.nextInt(RandomSource.create(), 2, 3)));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.SPEED, 28800, Mth.nextInt(RandomSource.create(), 2, 3)));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 28800, 0));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 28800, Mth.nextInt(RandomSource.create(), 1, 3)));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 28800, 0));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 28800, 0));
                }
            } else {
                if (Math.random() < 0.35) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 28800, Mth.nextInt(RandomSource.create(), 0, 1)));
                    }
                }
                if (Math.random() < 0.4) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 28800, 1));
                    }
                }
                if (Math.random() < 0.7) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 28800, 0));
                    }
                }
                if (Math.random() < 0.5) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 28800, 0));
                    }
                }
                if (Math.random() < 0.7) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 28800, 0));
                    }
                }
                if (Math.random() < 0.5) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.SPEED, 28800, 1));
                    }
                }
            }
        }
    }
}