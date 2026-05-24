package com.strongmonster.procedure;

import com.strongmonster.datagen.TheRiseOfHostileEntityTag;
import com.strongmonster.game_rule.TheRiseOfHostileGameRule;
import com.strongmonster.mixin.CreeperMixin;
import com.strongmonster.mixin.KillerBunnyMixin;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.ScoreAccess;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;

import java.lang.Math;


public class MobSpawnBuff {
    public static void register() {
        onEntitySpawn();
    }

    private static void onEntitySpawn() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            // DROP MANAGEMENT
            boolean allDrop = world.getGameRules().get(TheRiseOfHostileGameRule.ALL_DIAMOND_GEAR_DROP_GAMERULE);
            float mainhand_drop = 0.085f;

            // ENTITY LEVEL
            Level level = entity.level();
            // Weather check
            boolean isRain = level.isRaining();
            boolean isThunder = level.isThundering();

            // Local variable
            ItemStack main_hand = ItemStack.EMPTY;

            // Start if isRain true
            if (isRain) {
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


                if (entity.getType().is(TheRiseOfHostileEntityTag.ZOMBIE_BUFF)) {
                    if (Math.random() < 0.65) {
                        int value =
                                Mth.nextInt(
                                        level.random,
                                        1,
                                        6
                                );
                        if (isThunder) {
                            score_buff.set(1);
                            if (value == 1 || value == 2) {
                                if (Math.random() < 0.9) {
                                    main_hand = new ItemStack(Items.DIAMOND_SWORD);
                                } else {
                                    main_hand = new ItemStack(Items.NETHERITE_SWORD);
                                }
                            } else if (value == 3 || value == 4) {
                                if (Math.random() < 0.9) {
                                    main_hand = new ItemStack(Items.DIAMOND_AXE);
                                } else {
                                    main_hand = new ItemStack(Items.NETHERITE_AXE);
                                }
                            } else {
                                if (Math.random() < 0.9) {
                                    main_hand = new ItemStack(Items.DIAMOND_SPEAR);
                                } else {
                                    main_hand = new ItemStack(Items.NETHERITE_SPEAR);
                                }
                            }

                            if ((main_hand.is(Items.NETHERITE_SWORD) || main_hand.is(Items.NETHERITE_AXE) || main_hand.is(Items.NETHERITE_SPEAR))
                                    || (!allDrop && (main_hand.is(Items.DIAMOND_SWORD) || main_hand.is(Items.DIAMOND_AXE) || main_hand.is(Items.DIAMOND_SPEAR)))) {
                                mainhand_drop = -1.0f;
                            }

                            main_hand.enchant(
                                    level.registryAccess()
                                            .lookupOrThrow(Registries.ENCHANTMENT)
                                            .getOrThrow(Enchantments.FIRE_ASPECT),
                                    1
                            );
                            main_hand.enchant(
                                    level.registryAccess()
                                            .lookupOrThrow(Registries.ENCHANTMENT)
                                            .getOrThrow(Enchantments.SHARPNESS),
                                    5
                            );
                            if (entity instanceof LivingEntity living_entity) {
                                living_entity.setItemInHand(InteractionHand.MAIN_HAND, main_hand.copy());
                                if (entity instanceof Player player) {
                                    player.getInventory().setChanged();
                                }
                            }
                            if (entity instanceof Husk && Math.random() < 0.5) {
                                Rabbit killer_bunny = EntityType.RABBIT.create(level, EntitySpawnReason.NATURAL);

                                assert killer_bunny != null;
                                killer_bunny.getEntityData().set(
                                        KillerBunnyMixin.setVariant(),
                                        99
                                );

                                if (killer_bunny != null) {
                                    killer_bunny.setYRot(world.getRandom().nextFloat() * 360F);
                                    entity.level().addFreshEntity(killer_bunny);
                                }
                            }
                        } else {
                            if (value == 1 || value == 2 || value == 3) {
                                if (Math.random() < 0.75) {
                                    main_hand = new ItemStack(Items.IRON_SWORD);
                                } else {
                                    main_hand = new ItemStack(Items.DIAMOND_SWORD);
                                }
                            } else if (value == 4 || value == 5) {
                                if (Math.random() < 0.75) {
                                    main_hand = new ItemStack(Items.IRON_AXE);
                                } else {
                                    main_hand = new ItemStack(Items.DIAMOND_AXE);
                                }
                            } else if (value == 6) {
                                if (Math.random() < 0.75) {
                                    main_hand = new ItemStack(Items.IRON_SPEAR);
                                } else {
                                    main_hand = new ItemStack(Items.DIAMOND_SPEAR);
                                }
                            }
                            if (!allDrop && (main_hand.is(Items.DIAMOND_SWORD) || main_hand.is(Items.DIAMOND_AXE) || main_hand.is(Items.DIAMOND_SPEAR))) {
                                mainhand_drop = -1.0f;
                            }

                            main_hand.enchant(
                                    level.registryAccess()
                                            .lookupOrThrow(Registries.ENCHANTMENT)
                                            .getOrThrow(Enchantments.SHARPNESS),
                                    2
                            );

                            if (entity instanceof LivingEntity living_entity) {
                                living_entity.setItemInHand(InteractionHand.MAIN_HAND, main_hand.copy());
                                if (entity instanceof Player player) {
                                    player.getInventory().setChanged();
                                }
                            }

                        }
                    } else {
                        score_special_buff.set(1);
                        SpecialBuff.run(entity, level);
                    }
                } else if (entity.getType().is(TheRiseOfHostileEntityTag.SKELETON_BUFF)) {
                    if (Math.random() < 0.85) {
                        score_buff.set(1);
                        main_hand = new ItemStack(Items.BOW);
                        if (isThunder) {
                            main_hand.enchant(
                                    level.registryAccess()
                                            .lookupOrThrow(Registries.ENCHANTMENT)
                                            .getOrThrow(Enchantments.PUNCH),
                                    4
                            );

                            main_hand.enchant(
                                    level.registryAccess()
                                            .lookupOrThrow(Registries.ENCHANTMENT)
                                            .getOrThrow(Enchantments.POWER),
                                    5
                            );

                            main_hand.enchant(
                                    level.registryAccess()
                                            .lookupOrThrow(Registries.ENCHANTMENT)
                                            .getOrThrow(Enchantments.FLAME),
                                    2
                            );

                            mainhand_drop = -1.0f;

                        } else {
                            main_hand.enchant(
                                    level.registryAccess()
                                            .lookupOrThrow(Registries.ENCHANTMENT)
                                            .getOrThrow(Enchantments.PUNCH),
                                    2
                            );

                            main_hand.enchant(
                                    level.registryAccess()
                                            .lookupOrThrow(Registries.ENCHANTMENT)
                                            .getOrThrow(Enchantments.POWER),
                                    3
                            );
                        }
                        if (entity instanceof LivingEntity living_entity) {
                            living_entity.setItemInHand(InteractionHand.MAIN_HAND, main_hand.copy());
                            if (entity instanceof Player player) {
                                player.getInventory().setChanged();
                            }
                        }
                    } else {
                        score_special_buff.set(1);
                        SpecialBuff.run(entity, level);
                    }
                } else if (entity instanceof Creeper creeper && isThunder && Math.random() < 0.5) {
                    score_buff.set(1);
                    creeper.getEntityData().set(
                            CreeperMixin.getDataIsPowered(),
                            true
                    );
                } else if (entity instanceof Drowned) {
                    score_buff.set(1);
                }
                EffectBuff.run(level, entity);
                ArmorEquipInRainWeather.ArmorEquipSpecialCase(entity);
                if (entity instanceof Mob mob) {
                    mob.setDropChance(EquipmentSlot.MAINHAND, mainhand_drop);
                }
            }
        });
    }
}