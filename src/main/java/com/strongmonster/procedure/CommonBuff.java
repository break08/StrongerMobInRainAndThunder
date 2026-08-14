package com.strongmonster.procedure;

import com.strongmonster.datagen.tag.TheRiseOfHostileEntityTag;
import com.strongmonster.game_rule.TheRiseOfHostileGameRule;
import com.strongmonster.access.BuffAccess;
import com.strongmonster.access.SpecialBuffAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CommonBuff {
    public static void normalBuff(Entity entity, ServerLevel world) {
        System.out.println("Entered CommonBuff");

        ItemStack main_hand = ItemStack.EMPTY;
        float mainhand_drop = 0.085f;

        if (!(entity.level().isRaining()) && entity instanceof Mob livingEntity) {
            Level level = entity.level();
            boolean allDrop = world.getGameRules().get(TheRiseOfHostileGameRule.ALL_DIAMOND_GEAR_DROP_GAMERULE);

            if (Math.random() < 0.78) {
                ((BuffAccess) entity).setBuff(true);
                if (entity.getType().is(TheRiseOfHostileEntityTag.ZOMBIE_BUFF)) {
                    if (Math.random() < 0.4) {
                        if (Math.random() < 0.8) {
                            if (Math.random() < 0.9) {
                                if (Math.random() < 0.65) {
                                    main_hand = new ItemStack(Items.IRON_SWORD);
                                } else {
                                    main_hand = new ItemStack(Items.IRON_AXE);
                                }
                            } else {
                                main_hand = new ItemStack(Items.IRON_SPEAR);
                            }
                        } else if (Math.random() < 0.7) {
                            if (Math.random() < 0.85) {
                                if (Math.random() < 0.65) {
                                    main_hand = new ItemStack(Items.DIAMOND_SWORD);
                                } else {
                                    main_hand = new ItemStack(Items.DIAMOND_AXE);
                                }
                            } else {
                                main_hand = new ItemStack(Items.DIAMOND_SPEAR);
                            }
                            if (!allDrop) {
                                mainhand_drop = 0f;
                            }
                        } else {
                            if (Math.random() < 0.85) {
                                if (Math.random() < 0.65) {
                                    main_hand = new ItemStack(Items.NETHERITE_SWORD);
                                } else {
                                    main_hand = new ItemStack(Items.NETHERITE_AXE);
                                }
                            } else {
                                main_hand = new ItemStack(Items.NETHERITE_SPEAR);
                            }
                            mainhand_drop = 0f;
                        }

                        main_hand.enchant(
                                level.registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.SHARPNESS),
                                Mth.nextInt(
                                        level.random,
                                        1,
                                        2
                                )
                        );

                        if (Math.random() < 0.1) {
                            main_hand.enchant(
                                    level.registryAccess()
                                            .lookupOrThrow(Registries.ENCHANTMENT)
                                            .getOrThrow(Enchantments.FIRE_ASPECT),
                                    Mth.nextInt(
                                            level.random,
                                            1,
                                            2
                                    )
                            );
                        }
                    }

                } else if (entity.getType().is(TheRiseOfHostileEntityTag.SKELETON_BUFF)) {
                    main_hand = new ItemStack(Items.BOWL);
                    main_hand.enchant(
                            level.registryAccess()
                                    .lookupOrThrow(Registries.ENCHANTMENT)
                                    .getOrThrow(Enchantments.PUNCH),
                            Mth.nextInt(
                                    level.random,
                                    1,
                                    2
                            )
                    );

                    main_hand.enchant(
                            level.registryAccess()
                                    .lookupOrThrow(Registries.ENCHANTMENT)
                                    .getOrThrow(Enchantments.POWER),
                            Mth.nextInt(
                                    level.random,
                                    1,
                                    3
                            )
                    );
                    if (Math.random() < 0.1) {
                        main_hand.enchant(
                                level.registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.FLAME),
                                1
                        );
                    }
                } else if (entity instanceof Spider && !(entity instanceof CaveSpider)){
                    ((BuffAccess) entity).setBuff(true);
                    if (Math.random() < 0.1){
                        Vec3 pos = entity.position();
                        entity.discard();
                        CaveSpider caveSpider = EntityType.CAVE_SPIDER.create(level, EntitySpawnReason.NATURAL);
                        if (!(caveSpider ==null)){
                            caveSpider.setPos(pos);
                            world.addFreshEntity(caveSpider);
                        }
                    } else {
                        if (Math.random() < 0.15) {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 1728000, 0));
                        }
                    }
                } else if (entity instanceof CaveSpider){
                    ((BuffAccess) entity).setBuff(true);
                    if (Math.random() < 0.09){
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 1728000, 0));
                    }
                }
            } else {
                ((SpecialBuffAccess) entity).setSBuff(true);
            }
            if (entity instanceof Drowned) {
                ((BuffAccess) entity).setBuff(true);
            }

            if (entity instanceof BuffAccess && ((BuffAccess) entity).getBuff()) {
                ArmorEquipCommon.CommonEquip(entity, true, true, true, true);
            }
            EffectBuff.run(entity);
            livingEntity.setItemSlot(EquipmentSlot.MAINHAND, main_hand);
            if (entity instanceof Mob mob) {
                mob.setDropChance(EquipmentSlot.MAINHAND, mainhand_drop);
            }
        }
    }
}
