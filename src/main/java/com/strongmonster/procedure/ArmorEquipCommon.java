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

public class ArmorEquipCommon {
    public static void CommonEquip(Entity entity, boolean for_head, boolean for_chest, boolean for_leg, boolean for_boot){
        boolean isRain = entity.level().isRaining();

        if (!isRain && entity.getType().is(TheRiseOfHostileEntityTag.ARMOR_EQUIP_BUFF) && entity.level() instanceof ServerLevel serverLevel){
            ItemStack helmet = ItemStack.EMPTY;
            ItemStack chest = ItemStack.EMPTY;
            ItemStack leg = ItemStack.EMPTY;
            ItemStack boot = ItemStack.EMPTY;

            boolean allDrop = serverLevel.getGameRules().get(TheRiseOfHostileGameRule.ALL_DIAMOND_GEAR_DROP_GAMERULE);
            float helmet_drop = 0.085f;
            float chest_drop = 0.085f;
            float leg_drop = 0.085f;
            float boot_drop = 0.085f;

            double random = Math.random() * 160;

            // HELMET
            if (for_head && Math.random() < 0.35) {
                if (random < 10) {
                    helmet = new ItemStack(Items.LEATHER_HELMET);
                } else if (random < 30) {
                    helmet = new ItemStack(Items.CHAINMAIL_HELMET);
                } else if (random < 100) {
                    helmet = new ItemStack(Items.IRON_HELMET);
                } else if (random < 135) {
                    helmet = new ItemStack(Items.GOLDEN_HELMET);
                } else if (random < 153) {
                    helmet = new ItemStack(Items.DIAMOND_HELMET);
                    if (!allDrop) {
                        helmet_drop = 0f;
                    }
                } else {
                    helmet = new ItemStack(Items.NETHERITE_HELMET);
                    helmet_drop = 0f;
                }

                helmet.enchant(
                        entity.level().registryAccess()
                                .lookupOrThrow(Registries.ENCHANTMENT)
                                .getOrThrow(Enchantments.PROTECTION),
                        Mth.nextInt(entity.level().random, 1, 2)
                );
            }

            // CHEST
            random = Math.random() * 160;
            if (for_chest && Math.random() < 0.35) {
                if (random < 10) {
                    chest = new ItemStack(Items.LEATHER_CHESTPLATE);
                } else if (random < 30) {
                    chest = new ItemStack(Items.CHAINMAIL_CHESTPLATE);
                } else if (random < 100) {
                    chest = new ItemStack(Items.IRON_CHESTPLATE);
                } else if (random < 135) {
                    chest = new ItemStack(Items.GOLDEN_CHESTPLATE);
                } else if (random < 153) {
                    chest = new ItemStack(Items.DIAMOND_CHESTPLATE);
                    if (!allDrop) {
                        chest_drop = 0f;
                    }
                } else {
                    chest = new ItemStack(Items.NETHERITE_CHESTPLATE);
                    chest_drop = 0f;
                }

                chest.enchant(
                        entity.level().registryAccess()
                                .lookupOrThrow(Registries.ENCHANTMENT)
                                .getOrThrow(Enchantments.PROTECTION),
                        Mth.nextInt(entity.level().random, 1, 2)
                );
            }

            // LEG
            random = Math.random() * 160;
            if (for_leg && Math.random() < 0.35) {
                if (random < 10) {
                    leg = new ItemStack(Items.LEATHER_LEGGINGS);
                } else if (random < 30) {
                    leg = new ItemStack(Items.CHAINMAIL_LEGGINGS);
                } else if (random < 100) {
                    leg = new ItemStack(Items.IRON_LEGGINGS);
                } else if (random < 135) {
                    leg = new ItemStack(Items.GOLDEN_LEGGINGS);
                } else if (random < 153) {
                    leg = new ItemStack(Items.DIAMOND_LEGGINGS);
                    if (!allDrop) {
                        leg_drop = 0f;
                    }
                } else {
                    leg = new ItemStack(Items.NETHERITE_LEGGINGS);
                    leg_drop = 0f;
                }

                leg.enchant(
                        entity.level().registryAccess()
                                .lookupOrThrow(Registries.ENCHANTMENT)
                                .getOrThrow(Enchantments.PROTECTION),
                        Mth.nextInt(entity.level().random, 1, 2)
                );
            }

            // BOOT
            random = Math.random() * 160;
            if (for_boot && Math.random() < 0.35) {
                if (random < 10) {
                    boot = new ItemStack(Items.LEATHER_BOOTS);
                } else if (random < 30) {
                    boot = new ItemStack(Items.CHAINMAIL_BOOTS);
                } else if (random < 100) {
                    boot = new ItemStack(Items.IRON_BOOTS);
                } else if (random < 135) {
                    boot = new ItemStack(Items.GOLDEN_BOOTS);
                } else if (random < 153) {
                    boot = new ItemStack(Items.DIAMOND_BOOTS);
                    if (!allDrop) {
                        boot_drop = 0.0f;
                    }
                } else {
                    boot = new ItemStack(Items.NETHERITE_BOOTS);
                    boot_drop = 0.0f;
                }

                boot.enchant(
                        entity.level().registryAccess()
                                .lookupOrThrow(Registries.ENCHANTMENT)
                                .getOrThrow(Enchantments.PROTECTION),
                        Mth.nextInt(entity.level().random, 1, 2)
                );
            }

            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.setItemSlot(EquipmentSlot.HEAD, helmet);
                livingEntity.setItemSlot(EquipmentSlot.CHEST, chest);
                livingEntity.setItemSlot(EquipmentSlot.LEGS, leg);
                livingEntity.setItemSlot(EquipmentSlot.FEET, boot);
            }

            if (entity instanceof Mob mob) {
                mob.setDropChance(EquipmentSlot.HEAD, helmet_drop);
                mob.setDropChance(EquipmentSlot.CHEST, chest_drop);
                mob.setDropChance(EquipmentSlot.LEGS, leg_drop);
                mob.setDropChance(EquipmentSlot.FEET, boot_drop);
            }
        }
    }
}