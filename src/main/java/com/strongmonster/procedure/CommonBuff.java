package com.strongmonster.procedure;

import com.strongmonster.datagen.TheRiseOfHostileEntityTag;
import com.strongmonster.game_rule.TheRiseOfHostileGameRule;
import com.strongmonster.mixin.BuffAccess;
import com.strongmonster.mixin.SpecialBuffAccess;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
                    }
                } else {
                    ((SpecialBuffAccess) entity).setSBuff(true);
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
