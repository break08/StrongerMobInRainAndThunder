package com.strongmonster.mixin;

import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import com.strongmonster.mixin.CoolAccess;

@Mixin(Mob.class)
public class GiveCool implements CoolAccess{
    private int cool;

    @Override
    public int getCool() {
        return cool;
    }

    @Override
    public void setCool(int value) {
        this.cool = value;
    }
}
