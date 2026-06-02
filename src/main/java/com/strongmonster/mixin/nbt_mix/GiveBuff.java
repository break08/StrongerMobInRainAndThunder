package com.strongmonster.mixin.nbt_mix;

import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Mob.class)
public class GiveBuff implements BuffAccess{
    private boolean buff;

    @Override
    public boolean getBuff() {
        return buff;
    }

    @Override
    public void setBuff(boolean value) {
        this.buff = value;
    }
}
