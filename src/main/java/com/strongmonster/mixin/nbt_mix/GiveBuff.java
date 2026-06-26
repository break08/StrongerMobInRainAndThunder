package com.strongmonster.mixin.nbt_mix;

import com.strongmonster.access.BuffAccess;
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
public class GiveBuff implements BuffAccess {
    @Unique
    private boolean buff;

    @Override
    public boolean getBuff() {
        return buff;
    }

    @Override
    public void setBuff(boolean value) {
        this.buff = value;
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends Mob> entityType, Level level, CallbackInfo ci){
        this.buff = false;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void addAdditionalSaveData (ValueOutput valueOutput, CallbackInfo ci){
        valueOutput.putBoolean("buff", this.buff);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readAdditionalSaveData(ValueInput valueInput, CallbackInfo ci){
        this.buff = valueInput.getBooleanOr("buff", false);
    }
}
