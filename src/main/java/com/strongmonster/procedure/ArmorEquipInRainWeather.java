package com.strongmonster.procedure;

import com.strongmonster.datagen.tag.TheRiseOfHostileEntityTag;
import com.strongmonster.game_rule.TheRiseOfHostileGameRule;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;

public class ArmorEquipInRainWeather {
    public static void ArmorEquipSpecialCase(Entity entity, boolean for_head, boolean for_chest, boolean for_leg, boolean for_boot){
        boolean isRain = entity.level().isRaining();
        boolean isThunder = entity.level().isThundering();
        boolean allDrop = false;
        if (entity.level() instanceof ServerLevel serverLevel) {
            allDrop = serverLevel.getGameRules().get(TheRiseOfHostileGameRule.ALL_DIAMOND_GEAR_DROP_GAMERULE);
        }

        // DROP
        float helmet_drop = 0.085f;
        float chest_drop = 0.085f;
        float leg_drop = 0.085f;
        float boot_drop = 0.085f;

        ItemStack helmet = ItemStack.EMPTY;
        ItemStack chest = ItemStack.EMPTY;
        ItemStack leg = ItemStack.EMPTY;
        ItemStack boot = ItemStack.EMPTY;

        if (isRain){
            if (entity.getType().is(TheRiseOfHostileEntityTag.ARMOR_EQUIP_BUFF)){
                if (isThunder){
                    // HELMET
                    if (for_head) {
                        if (Math.random() < 0.25) {
                            helmet = new ItemStack(Items.NETHERITE_HELMET);
                            helmet_drop = 0f;
                        } else {
                            helmet = new ItemStack(Items.DIAMOND_HELMET);
                        }

                        helmet.enchant(
                                entity.level().registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.PROTECTION),
                                Mth.nextInt(
                                        entity.level().random,
                                        2,
                                        3
                                )
                        );
                    }

                    // CHEST
                    if (for_chest) {
                        if (Math.random() < 0.25) {
                            chest = new ItemStack(Items.NETHERITE_CHESTPLATE);
                            chest_drop = 0f;
                        } else {
                            chest = new ItemStack(Items.DIAMOND_CHESTPLATE);
                        }

                        chest.enchant(
                                entity.level().registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.PROTECTION),

                                Mth.nextInt(
                                        entity.level().random,
                                        2,
                                        3
                                )
                        );
                    }

                    // LEG
                    if (for_leg) {
                        if (Math.random() < 0.25) {
                            leg = new ItemStack(Items.NETHERITE_LEGGINGS);
                            leg_drop = 0f;
                        } else {
                            leg = new ItemStack(Items.DIAMOND_LEGGINGS);
                        }

                        leg.enchant(
                                entity.level().registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.PROTECTION),
                                Mth.nextInt(
                                        entity.level().random,
                                        2,
                                        3
                                )
                        );
                    }

                    // BOOT
                    if (for_boot) {
                        if (Math.random() < 0.25) {
                            boot = new ItemStack(Items.NETHERITE_BOOTS);
                            boot_drop = 0f;
                        } else {
                            boot = new ItemStack(Items.DIAMOND_BOOTS);
                        }

                        boot.enchant(
                                entity.level().registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.PROTECTION),
                                Mth.nextInt(
                                        entity.level().random,
                                        2,
                                        3
                                )
                        );
                    }

                    if (!allDrop) {
                        helmet_drop = 0f;
                        chest_drop = 0f;
                        leg_drop = 0f;
                        boot_drop = 0f;
                    }

                } else {
                    // HELMET
                    if (for_head) {
                        if (Math.random() < 0.35) {
                            helmet = new ItemStack(Items.DIAMOND_HELMET);
                            if (!allDrop) {
                                helmet_drop = 0f;
                            }
                        } else if (Math.random() < 0.85) {
                            helmet = new ItemStack(Items.IRON_HELMET);
                        } else {
                            helmet = new ItemStack(Items.NETHERITE_HELMET);
                            helmet_drop = 0f;
                        }

                        helmet.enchant(
                                entity.level().registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.PROTECTION),
                                Mth.nextInt(
                                        entity.level().random,
                                        2,
                                        3
                                )
                        );
                    }

                    // CHEST
                    if (for_chest) {
                        if (Math.random() < 0.35) {
                            chest = new ItemStack(Items.DIAMOND_CHESTPLATE);
                            if (!allDrop) {
                                chest_drop = 0f;
                            }
                        } else if (Math.random() < 0.85) {
                            chest = new ItemStack(Items.IRON_CHESTPLATE);
                        } else {
                            chest = new ItemStack(Items.NETHERITE_CHESTPLATE);
                            chest_drop = 0f;
                        }

                        chest.enchant(
                                entity.level().registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.PROTECTION),
                                Mth.nextInt(
                                        entity.level().random,
                                        1,
                                        2
                                )
                        );
                    }

                    // LEG
                    if (for_leg) {
                        if (Math.random() < 0.35) {
                            leg = new ItemStack(Items.DIAMOND_LEGGINGS);
                            if (!allDrop) {
                                leg_drop = 0f;
                            }
                        } else if (Math.random() < 0.85) {
                            leg = new ItemStack(Items.IRON_LEGGINGS);
                        } else {
                            leg = new ItemStack(Items.NETHERITE_LEGGINGS);
                            leg_drop = 0f;
                        }
                        leg.enchant(
                                entity.level().registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.PROTECTION),
                                Mth.nextInt(
                                        entity.level().random,
                                        1,
                                        2
                                )
                        );
                    }

                    // BOOT
                    if (for_boot) {
                        if (Math.random() < 0.35) {
                            boot = new ItemStack(Items.DIAMOND_BOOTS);
                            if (!allDrop) {
                                boot_drop = 0f;
                            }
                        } else if (Math.random() < 0.85) {
                            boot = new ItemStack(Items.IRON_BOOTS);
                        } else {
                            boot = new ItemStack(Items.NETHERITE_BOOTS);
                            boot_drop = 0f;
                        }

                        boot.enchant(
                                entity.level().registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.PROTECTION),
                                Mth.nextInt(
                                        entity.level().random,
                                        1,
                                        2
                                )
                        );
                    }
                }
                if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.setItemSlot(EquipmentSlot.HEAD, helmet);
                    livingEntity.setItemSlot(EquipmentSlot.CHEST, chest);
                    livingEntity.setItemSlot(EquipmentSlot.LEGS, leg);
                    livingEntity.setItemSlot(EquipmentSlot.FEET, boot);
                }
                if (entity instanceof Mob mob){
                    mob.setDropChance(EquipmentSlot.HEAD, helmet_drop);
                    mob.setDropChance(EquipmentSlot.CHEST, chest_drop);
                    mob.setDropChance(EquipmentSlot.LEGS, leg_drop);
                    mob.setDropChance(EquipmentSlot.FEET, boot_drop);
                }
            }
        }
    }
}
