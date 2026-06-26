package com.strongmonster.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.illager.Illusioner;
import net.minecraft.world.entity.monster.illager.Pillager;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Pillager.class)
public class IllusionerSpawnWithPillager {
    @Inject(method = "finalizeSpawn", at = @At("HEAD"))
    public void finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, EntitySpawnReason entitySpawnReason, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir){
        Entity pillager = (Pillager)(Object)this;
        Illusioner illusioner = new Illusioner(EntityType.ILLUSIONER, pillager.level());
        illusioner.setPos(pillager.position());
        if (pillager.level() instanceof ServerLevel serverLevel && Math.random() < 0.15){
            serverLevel.addFreshEntity(illusioner);
        }
    }
}