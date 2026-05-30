package com.strongmonster.procedure;

import com.strongmonster.datagen.TheRiseOfHostileEntityTag;
import com.strongmonster.mixin.BuffAccess;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.zombie.Zombie;

public class AntiIronGolemCheating {
    public static void register(){NoGolemCheat();}
    public static void NoGolemCheat(){
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(
                (world, entity, killedEntity, damageSource) -> {

                    if (entity.getType().is(TheRiseOfHostileEntityTag.IS_GOLEM) && ((BuffAccess) killedEntity).getBuff() && killedEntity.level().isRaining()) {
                        Zombie zombie = EntityType.ZOMBIE.create(entity.level(), EntitySpawnReason.NATURAL);
                        if (zombie != null) {
                            zombie.setYRot(world.getRandom().nextFloat() * 360F);
                            entity.level().addFreshEntity(zombie);
                        }
                    }
                }
        );
    }
}
