package com.strongmonster.procedure;

import com.strongmonster.datagen.tag.TheRiseOfHostileEntityTag;
import com.strongmonster.game_rule.TheRiseOfHostileGameRule;
import com.strongmonster.mixin.nbt_mix.BuffAccess;
import com.strongmonster.mixin.nbt_mix.SpecialBuffAccess;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

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


                if (Math.random() < 0.78){
                    ((BuffAccess) entity).setBuff(true);
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
                    } else if (entity.getType().is(TheRiseOfHostileEntityTag.SKELETON_BUFF)) {
                        main_hand = new ItemStack(Items.BOWL);
                        main_hand.enchant(
                                level.registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.PUNCH),
                                Mth.nextInt(
                                        level.random,
                                        1,
                                        3
                                )
                        );

                        main_hand.enchant(
                                level.registryAccess()
                                        .lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.POWER),
                                Mth.nextInt(
                                        level.random,
                                        1,
                                        4
                                )
                        );
                        if (Math.random() < 0.5) {
                            main_hand.enchant(
                                    level.registryAccess()
                                            .lookupOrThrow(Registries.ENCHANTMENT)
                                            .getOrThrow(Enchantments.FLAME),
                                    1
                            );
                        }
                    }
                } else {
                    ((SpecialBuffAccess) entity).setSBuff(true);
                }

                if (entity instanceof Drowned){
                    ((BuffAccess) entity).setBuff(true);
                }

                ArmorEquipCommon.CommonEquip(entity);
                EffectBuff.run(level, entity);
                if (entity instanceof LivingEntity livingEntity){
                    livingEntity.setItemSlot(EquipmentSlot.MAINHAND, main_hand);
                }
                if (entity instanceof Mob mob){
                    mob.setDropChance(EquipmentSlot.MAINHAND, mainhand_drop);
                }
            }
        });
    }
}
