package com.strongmonster.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/world/entity/ai/goal/target/HurtByTargetGoal")
public class HostileNoFightBack {
    @Inject(method="canUse", at = @At("HEAD"), cancellable = true)
    public void canUse(CallbackInfoReturnable<Boolean> cir){
        cir.cancel();
    }
}
