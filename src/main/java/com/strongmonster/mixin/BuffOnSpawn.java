package com.strongmonster.mixin;

import com.strongmonster.datagen.tag.TheRiseOfHostileEntityTag;
import com.strongmonster.procedure.CommonBuff;
import com.strongmonster.procedure.IllagerBuff;
import com.strongmonster.procedure.MobSpawnBuff;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public class BuffOnSpawn {
    @Inject(method = "finalizeSpawn", at = @At("TAIL"))
    private void finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, EntitySpawnReason entitySpawnReason, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir){
        Mob entity = (Mob)(Object)this;
        System.out.println("Finalize: " + ((Mob)(Object)this).getType());
        System.out.println("HOSTILE = " + entity.getType().is(TheRiseOfHostileEntityTag.HOSTILE));
        if (entity.getType().is(TheRiseOfHostileEntityTag.HOSTILE) && entity.level() instanceof ServerLevel serverLevel){
            System.out.println("Rain = " + serverLevel.isRaining());
            if (entity.getType().is(TheRiseOfHostileEntityTag.ILLAGER)){
                IllagerBuff.IllagerSpawn(entity, serverLevel);
            } else {
                if (serverLevel.isRaining()) {
                    MobSpawnBuff.rainyBuff(entity, serverLevel);
                } else {
                    CommonBuff.normalBuff(entity, serverLevel);
                }
            }
        }
    }
}
