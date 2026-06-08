package com.strongmonster.mixin;

import com.strongmonster.ModHelper;
import com.strongmonster.datagen.tag.TheRiseOfHostileEntityTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public class AttributeBuff {
    @Inject(method = "finalizeSpawn", at = @At("TAIL"))
    private void finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, EntitySpawnReason entitySpawnReason, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir){
        Mob entity = (Mob)(Object)this;
        if (entity.getType().is(TheRiseOfHostileEntityTag.HOSTILE) && entity instanceof LivingEntity livingEntity) {
            ModHelper.plusRandomValueToIntAttribute(3, 5, Attributes.MAX_HEALTH, livingEntity);
            ModHelper.randomAttributeDoubleTypePlus(0, 0.5, Attributes.KNOCKBACK_RESISTANCE, livingEntity);
            ModHelper.randomAttributeDoubleTypePlus(0, 0.2, Attributes.MOVEMENT_SPEED, livingEntity);
            ModHelper.plusRandomValueToIntAttribute(7, 12, Attributes.FOLLOW_RANGE, livingEntity);
            ModHelper.plusRandomValueToIntAttribute(1, 2, Attributes.ATTACK_DAMAGE, livingEntity);
        }
        if (entity instanceof PiglinBrute){
            ((PiglinBrute) entity).setImmuneToZombification(true);
        }
    }
}