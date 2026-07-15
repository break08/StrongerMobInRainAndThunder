package com.strongmonster;

import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Objects;
import java.util.Random;

public class ModHelper {
    public static void randomAttributeDoubleTypePlus (double start, double end, Holder<Attribute> attributes, LivingEntity livingEntity){
        if (livingEntity.getAttribute(attributes) != null) {
            Random random1 = new Random();
            double value = Math.round(random1.nextDouble(start, end) * 10.0) / 10.0;
            double after = Objects.requireNonNull(livingEntity.getAttribute(attributes)).getBaseValue() + value;
            Objects.requireNonNull(livingEntity.getAttribute(attributes)).setBaseValue(after);
        }
    }

    public static void plusRandomValueToIntAttribute(int start, int end, Holder<Attribute> attributes, LivingEntity livingEntity) {
        if (livingEntity.getAttribute(attributes) != null) {
            int bonus = Mth.nextInt(livingEntity.level().random, start, end);
            double after = Objects.requireNonNull(livingEntity.getAttribute(attributes)).getBaseValue() + bonus;
            Objects.requireNonNull(livingEntity.getAttribute(attributes)).setBaseValue(after);
            if (attributes.is(Attributes.MAX_HEALTH)) {
                livingEntity.setHealth((float) after);
            }
        }
    }
}
