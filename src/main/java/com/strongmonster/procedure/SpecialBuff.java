package com.strongmonster.procedure;

import com.strongmonster.datagen.TheRiseOfHostileEntityTag;
import com.strongmonster.datagen.TheRiseOfHostileItemTag;

import com.strongmonster.item.TheRiseOfHostileItem;
import com.strongmonster.mixin.CoolAccess;
import com.strongmonster.mixin.SpecialBuffAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class SpecialBuff {
    public static void run (Entity entity, Level level){
        if (((SpecialBuffAccess) entity).getSBuff() && entity instanceof LivingEntity living_entity) {
            if (entity.getType().is(TheRiseOfHostileEntityTag.ZOMBIE_BUFF)) {
                if (Math.random() < 0.2) {
                    living_entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(BuiltInRegistries.ITEM.getRandomElementOf(TheRiseOfHostileItemTag.IS_THROWABLE, RandomSource.create()).orElseGet(() -> BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)).value()));
                    ((CoolAccess) entity).setCool(20);
                } else if (Math.random() < 0.4) {
                    living_entity.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(BuiltInRegistries.ITEM.getRandomElementOf(TheRiseOfHostileItemTag.GIVE_TARGET_EFFECT, RandomSource.create()).orElseGet(() -> BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)).value()));
                } else if (Math.random() < 0.5) {
                    living_entity.setItemSlot(EquipmentSlot.CHEST, new ItemStack(TheRiseOfHostileItem.DYNAMITE_ROLL));
                }
            }
        }
    }
}
