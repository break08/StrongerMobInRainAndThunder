package com.strongmonster.mixin;

import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/world/entity/monster/spider/Spider$SpiderTargetGoal")
public class SpiderCanUseMixin {
    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void canUse(@NonNull CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
    }
}
