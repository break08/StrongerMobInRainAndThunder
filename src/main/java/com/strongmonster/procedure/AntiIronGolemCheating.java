package com.strongmonster.procedure;

import com.strongmonster.datagen.TheRiseOfHostileEntityTag;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;

public class AntiIronGolemCheating {
    public static void register(){NoGolemCheat();}
    public static void NoGolemCheat(){
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(
                (world, entity, killedEntity, damageSource) -> {
                    Scoreboard scoreboard = entity.level().getScoreboard();

                    Objective objective = scoreboard.getObjective("buff");

                    int isBuffed =
                            scoreboard
                                    .getOrCreatePlayerScore(
                                            entity,
                                            objective
                                    )
                                    .get();
                    if (entity.getType().is(TheRiseOfHostileEntityTag.IS_GOLEM) && isBuffed == 1 && entity.level().isRaining()) {
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
