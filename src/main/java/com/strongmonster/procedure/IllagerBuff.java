package com.strongmonster.procedure;

import com.strongmonster.game_rule.TheRiseOfHostileGameRule;
import com.strongmonster.access.BuffAccess;
import com.strongmonster.access.SpecialBuffAccess;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.illager.Evoker;
import net.minecraft.world.entity.monster.illager.Pillager;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class IllagerBuff {
    public static void IllagerSpawn(Entity entity, ServerLevel world){
        Level level = entity.level();


        //BUFF
        boolean isRain = level.isRaining();

        boolean allDrop = world.getGameRules().get(TheRiseOfHostileGameRule.ALL_DIAMOND_GEAR_DROP_GAMERULE);
        float mainhand_drop = 0.085f;

        ItemStack main_hand = ItemStack.EMPTY;
        if (entity instanceof LivingEntity living_entity) {
            if (living_entity instanceof Pillager && !(living_entity.getMainHandItem().isEmpty())) {
                ((BuffAccess) entity).setBuff(true);
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
                ((BuffAccess) entity).setBuff(true);
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
                    ((SpecialBuffAccess) entity).setSBuff(true);
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
                ((BuffAccess) entity).setBuff(true);
            }
            EffectBuff.run(entity);
            SpecialBuff.run(entity);
            if (entity instanceof LivingEntity livingEntity){
                livingEntity.setItemSlot(EquipmentSlot.MAINHAND, main_hand);
            }
            if (entity instanceof Mob mob){
                mob.setDropChance(EquipmentSlot.MAINHAND, mainhand_drop);
            }
        }
    }
}
