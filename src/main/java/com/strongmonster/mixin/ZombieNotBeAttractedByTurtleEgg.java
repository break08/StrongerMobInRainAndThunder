package com.strongmonster.mixin;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RemoveBlockGoal.class)
public class ZombieNotBeAttractedByTurtleEgg {
    @Shadow @Final
    private Mob removerMob;

    @Shadow @Final
    private Block blockToRemove;

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    public void canUse(CallbackInfoReturnable<Boolean> cir){
        if (this.removerMob instanceof Zombie && this.blockToRemove == Blocks.TURTLE_EGG){
            cir.setReturnValue(false);
        }
    }
}
