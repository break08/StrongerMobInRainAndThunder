package com.strongmonster.procedure;

import com.strongmonster.datagen.tag.TheRiseOfHostileEntityTag;
import com.strongmonster.datagen.tag.TheRiseOfHostileItemTag;

import com.strongmonster.item.TheRiseOfHostileItem;
import com.strongmonster.access.CoolAccess;
import com.strongmonster.access.SpecialBuffAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SpecialBuff {
    public static void run (Entity entity){
        if (((SpecialBuffAccess) entity).getSBuff() && entity instanceof LivingEntity living_entity) {
            if (entity.getType().is(TheRiseOfHostileEntityTag.ZOMBIE_BUFF)) {
                if (Math.random() < 0.2) {
                    living_entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(BuiltInRegistries.ITEM.getRandomElementOf(TheRiseOfHostileItemTag.IS_THROWABLE, RandomSource.create()).orElseGet(() -> BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)).value()));
                    ((CoolAccess) entity).setCool(20);
                    ArmorEquipCommon.CommonEquip(entity, true, true, true, true);
                } else if (Math.random() < 0.4) {
                    living_entity.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(BuiltInRegistries.ITEM.getRandomElementOf(TheRiseOfHostileItemTag.GIVE_TARGET_EFFECT, RandomSource.create()).orElseGet(() -> BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)).value()));
                    ArmorEquipCommon.CommonEquip(entity, true, true, true, true);
                } else if (Math.random() < 0.5) {
                    living_entity.setItemSlot(EquipmentSlot.CHEST, new ItemStack(TheRiseOfHostileItem.DYNAMITE_ROLL));
                    ArmorEquipCommon.CommonEquip(entity, true, false, true, true);
                }
            } else if(entity.getType().is(TheRiseOfHostileEntityTag.SKELETON_BUFF)){
                living_entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(TheRiseOfHostileItem.EFFECT_CURSE_HAT));
                ArmorEquipInRainWeather.ArmorEquipSpecialCase(entity, false, true, true, true);
            }
        }
    }
}