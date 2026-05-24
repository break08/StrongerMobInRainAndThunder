package com.strongmonster.procedure;

import com.strongmonster.game_rule.TheRiseOfHostileGameRule;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.illager.Evoker;
import net.minecraft.world.entity.monster.illager.Pillager;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.ScoreAccess;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;

public class IllagerBuff {
    public static void register(){IllagerSpawn();}

    public static void IllagerSpawn(){
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            Level level = entity.level();


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

            boolean isRain = level.isRaining();

            boolean allDrop = world.getGameRules().get(TheRiseOfHostileGameRule.ALL_DIAMOND_GEAR_DROP_GAMERULE);
            float mainhand_drop = 0.085f;

            ItemStack main_hand = ItemStack.EMPTY;
            if (entity instanceof LivingEntity living_entity) {
                if (living_entity instanceof Pillager && !(living_entity.getMainHandItem().isEmpty())) {
                    score_buff.set(1);
                    if (isRain) {
                        living_entity.getMainHandItem().enchant(
                                level.registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.MULTISHOT),
                                2
                        );

                        living_entity.getMainHandItem().enchant(
                                level.registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.UNBREAKING),
                                3
                        );

                        living_entity.getMainHandItem().enchant(
                                level.registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.QUICK_CHARGE),
                                3
                        );

                        living_entity.getMainHandItem().enchant(
                                level.registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.PIERCING),
                                2
                        );

                        mainhand_drop = -1.0f;
                    } else {
                        living_entity.getMainHandItem().enchant(
                                level.registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.MULTISHOT),
                                1
                        );

                        living_entity.getMainHandItem().enchant(
                                level.registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.UNBREAKING),
                                3
                        );

                        living_entity.getMainHandItem().enchant(
                                level.registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.QUICK_CHARGE),
                                2
                        );

                        living_entity.getMainHandItem().enchant(
                                level.registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.PIERCING),
                                1
                        );
                    }
                } else if (living_entity instanceof Vindicator) {
                    score_buff.set(1);
                    if (Math.random() < 0.7) {
                        if (isRain){
                            if (Math.random() < 0.5){
                                main_hand = new ItemStack(Items.DIAMOND_AXE);
                            } else {
                                main_hand = new ItemStack(Items.NETHERITE_AXE);
                            }
                            living_entity.getMainHandItem().enchant(
                                    level.registryAccess()
                                            .lookupOrThrow(Registries.ENCHANTMENT)
                                            .getOrThrow(Enchantments.SHARPNESS),
                                    4
                            );
                        } else {
                            main_hand = new ItemStack(Items.DIAMOND_AXE);
                            living_entity.getMainHandItem().enchant(
                                    level.registryAccess()
                                            .lookupOrThrow(Registries.ENCHANTMENT)
                                            .getOrThrow(Enchantments.SHARPNESS),
                                    2
                            );
                        }
                        living_entity.setItemInHand(InteractionHand.MAIN_HAND, main_hand.copy());
                        mainhand_drop = -1.0f;
                    } else {
                        score_special_buff.set(1);
                    }
                } else if (living_entity instanceof Vex){
                    if (isRain) {
                        if (Math.random() < 0.15) {
                            main_hand = new ItemStack(Items.NETHERITE_SWORD);
                            mainhand_drop = -1.0f;
                        } else {
                            main_hand = new ItemStack(Items.DIAMOND_SWORD);
                            if (!allDrop) {mainhand_drop = -1.0f;}
                        }
                    } else {
                        main_hand = new ItemStack(Items.DIAMOND_SWORD);
                        if (!allDrop) {mainhand_drop = -1.0f;}
                    }
                } else if (living_entity instanceof Evoker) {
                    score_buff.set(1);
                }
                EffectBuff.run(level, entity);
                SpecialBuff.run(entity, level);
            }
        });
    }
}
