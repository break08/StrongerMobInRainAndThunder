package com.strongmonster.procedure;

import com.strongmonster.datagen.TheRiseOfHostileEntityTag;
import com.strongmonster.datagen.TheRiseOfHostileItemTag;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.ScoreAccess;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;

public class SpecialBuff {
    public static void run (Entity entity, Level level){
        Scoreboard scoreboard = level.getScoreboard();

        Objective objective = scoreboard.getObjective("special_buff");
        int isSpecialBuffed =
                scoreboard
                        .getOrCreatePlayerScore(
                                entity,
                                objective
                        )
                        .get();
        if (isSpecialBuffed == 1) {
            if (entity.getType().is(TheRiseOfHostileEntityTag.ZOMBIE_BUFF)) {
                if (Math.random() < 0.4) {
                    if (entity instanceof LivingEntity living_entity) {
                        living_entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(BuiltInRegistries.ITEM.getRandomElementOf(TheRiseOfHostileItemTag.IS_THROWABLE, RandomSource.create()).orElseGet(() -> BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)).value()));
                        Scoreboard scoreboardbuff = level.getScoreboard();
                        Objective _objective = scoreboardbuff.getObjective("cool");

                        if (_objective == null) {
                            _objective = scoreboardbuff.addObjective(
                                    "cool",
                                    ObjectiveCriteria.DUMMY,
                                    Component.literal("cool"),
                                    ObjectiveCriteria.RenderType.INTEGER,
                                    false,
                                    null
                            );
                        }
                        ScoreAccess score_buff = scoreboardbuff.getOrCreatePlayerScore(ScoreHolder.forNameOnly(entity.getScoreboardName()), _objective);

                        score_buff.set(5);
                    }
                }
            }
        }
    }
}
