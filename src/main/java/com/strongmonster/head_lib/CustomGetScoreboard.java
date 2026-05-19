package com.strongmonster.head_lib;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;

public class CustomGetScoreboard {
    public static int getScoreBoard(Entity entity, String score){
        Scoreboard scoreboard = entity.level().getScoreboard();

        Objective objective = scoreboard.getObjective(score);

        return scoreboard
                .getOrCreatePlayerScore(
                        entity,
                        objective
                )
                .get();

    }
}
