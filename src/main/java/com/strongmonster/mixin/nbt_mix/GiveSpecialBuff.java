package com.strongmonster.mixin.nbt_mix;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class GiveSpecialBuff implements SpecialBuffAccess{
    private boolean sbuff;

    @Override
    public boolean getSBuff() {
        return sbuff;
    }

    @Override
    public void setSBuff(boolean value) {
        this.sbuff = value;
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends Mob> entityType, Level level, CallbackInfo ci){
        this.sbuff = false;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void addAdditionalSaveData (ValueOutput valueOutput, CallbackInfo ci){
        valueOutput.putBoolean("sbuff", this.sbuff);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readAdditionalSaveData(ValueInput valueInput, CallbackInfo ci){
        this.sbuff = valueInput.getBooleanOr("sbuff", false);
    }
}
