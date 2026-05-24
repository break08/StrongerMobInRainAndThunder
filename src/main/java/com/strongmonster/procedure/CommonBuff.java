package com.strongmonster.procedure;

import com.strongmonster.datagen.TheRiseOfHostileEntityTag;
import com.strongmonster.game_rule.TheRiseOfHostileGameRule;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.ScoreAccess;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;

public class CommonBuff {
    public static void register() {
        onCommonEntitySpawn();
    }

    private static void onCommonEntitySpawn() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {

            ItemStack main_hand = ItemStack.EMPTY;

            if (!(entity.level().isRaining())){
                Level level = entity.level();
                boolean allDrop = world.getGameRules().get(TheRiseOfHostileGameRule.ALL_DIAMOND_GEAR_DROP_GAMERULE);
                float mainhand_drop = 0.085f;

                //BUFF
                Scoreboard scoreboardbuff = level.getScoreboard();
                Objective objective = scoreboardbuff.getObjective("buff");

                if (objective == null) {
                    objective = scoreboardbuff.addObjective(
                            "buff",
                            ObjectiveCriteria.DUMMY,
                            Component.literal("Buff"),
                            ObjectiveCriteria.RenderType.INTEGER,
                            false,
                            null
                    );
                }
                ScoreAccess score_buff = scoreboardbuff.getOrCreatePlayerScore(ScoreHolder.forNameOnly(entity.getScoreboardName()), objective);

                // SPECIAL BUFF
                Scoreboard scoreboardspecialbuff = level.getScoreboard();
                Objective objective_special = scoreboardspecialbuff.getObjective("special_buff");

                if (objective_special == null) {
                    objective_special = scoreboardbuff.addObjective(
                            "special_buff",
                            ObjectiveCriteria.DUMMY,
                            Component.literal("Special_Buff"),
                            ObjectiveCriteria.RenderType.INTEGER,
                            false,
                            null
                    );
                }
                ScoreAccess score_special_buff = scoreboardbuff.getOrCreatePlayerScore(ScoreHolder.forNameOnly(entity.getScoreboardName()), objective_special);

                if (Math.random() < 0.78){
                    score_buff.set(1);
                    if (entity.getType().is(TheRiseOfHostileEntityTag.ZOMBIE_BUFF)){
                        if (Math.random() < 0.8){
                            main_hand = new ItemStack(Items.IRON_SWORD);
                        } else if (Math.random() < 0.7){
                            main_hand = new ItemStack(Items.DIAMOND_SWORD);
                            if (!allDrop){mainhand_drop = -1.0f;}
                        } else {
                            main_hand = new ItemStack(Items.NETHERITE_SWORD);
                            mainhand_drop = -1.0f;
                        }
                    }
                } else {
                    score_special_buff.set(1);
                }
                ArmorEquipCommon.CommonEquip(entity);
                EffectBuff.run(level, entity);
            }
        });
    }
}
