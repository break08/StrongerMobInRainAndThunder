package com.strongmonster.procedure;

import com.strongmonster.datagen.TheRiseOfHostileEntityTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class ArmorEquipInRainWeather {
    public static void ArmorEquipSpecialCase(Entity entity){
        boolean isRain = entity.level().isRaining();
        boolean isThunder = entity.level().isThundering();

        ItemStack helmet = ItemStack.EMPTY;
        ItemStack chest = ItemStack.EMPTY;
        ItemStack leg = ItemStack.EMPTY;
        ItemStack boot = ItemStack.EMPTY;

        if (isRain){
            if (entity.getType().is(TheRiseOfHostileEntityTag.ARMOR_EQUIP_BUFF)){

            }
        }
    }
}
