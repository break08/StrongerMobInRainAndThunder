package com.strongmonster.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/world/entity/monster/spider/Spider$SpiderTargetGoal")
public abstract class SpiderCanUseMixin<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    public SpiderCanUseMixin(Mob mob, Class<T> targetType, boolean mustSee) {
        super(mob, targetType, mustSee);
    }

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void canUse(CallbackInfoReturnable<Boolean> cir){
        if (!this.mob.isVehicle()) {
            cir.setReturnValue(super.canUse());
        }
    }
}