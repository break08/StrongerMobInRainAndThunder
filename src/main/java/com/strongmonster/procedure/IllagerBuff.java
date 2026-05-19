package com.strongmonster.procedure;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.registries.Registries;
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

public class IllagerBuff {
    public static void register(){IllagerSpawn();}

    public static void IllagerSpawn(){
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            Level level = entity.level();

            boolean isRain = level.isRaining();
            boolean isThunder = level.isThundering();

            ItemStack main_hand = ItemStack.EMPTY;
            if (entity instanceof LivingEntity living_entity) {
                if (living_entity instanceof Pillager && !(living_entity.getMainHandItem().isEmpty())) {
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
                    } else {

                    }
                } else if (living_entity instanceof Vex){
                    if (isRain) {
                        if (Math.random() < 0.15) {
                            main_hand = new ItemStack(Items.NETHERITE_SWORD);
                        } else {
                            main_hand = new ItemStack(Items.DIAMOND_SWORD);
                        }
                    } else {
                        main_hand = new ItemStack(Items.DIAMOND_SWORD);
                    }
                } else if (living_entity instanceof Evoker) {

                }
            }
        });
    }
}
