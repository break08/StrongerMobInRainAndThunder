package com.strongmonster.procedure;

// Head lib
import com.strongmonster.head_lib.CustomGetScoreboard;

import com.strongmonster.datagen.TheRiseOfHostileEntityTag;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.zombie.Zombie;

public class AntiIronGolemCheating {
    public static void register(){NoGolemCheat();}
    public static void NoGolemCheat(){
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(
                (world, entity, killedEntity, damageSource) -> {

                    int isBuffed = CustomGetScoreboard.getScoreBoard(killedEntity, "buff");
                    int isSpecialBuff = CustomGetScoreboard.getScoreBoard(killedEntity, "special_buff");

                    if (entity.getType().is(TheRiseOfHostileEntityTag.IS_GOLEM) && isBuffed == 1 && killedEntity.level().isRaining() && !(isSpecialBuff == 1)) {
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
