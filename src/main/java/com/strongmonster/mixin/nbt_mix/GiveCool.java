package com.strongmonster.mixin.nbt_mix;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class GiveCool implements CoolAccess{
    @Unique
    private int cool;

    @Override
    public int getCool() {
        return cool;
    }

    @Override
    public void setCool(int value) {
        this.cool = value;
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends Mob> entityType, Level level, CallbackInfo ci){
        this.cool = 20;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void addAdditionalSaveData (ValueOutput valueOutput, CallbackInfo ci){
        valueOutput.putInt("cool", this.cool);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readAdditionalSaveData(ValueInput valueInput, CallbackInfo ci){
        this.cool = valueInput.getIntOr("buff", 20);
    }
}
