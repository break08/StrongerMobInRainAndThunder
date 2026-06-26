package com.strongmonster.mixin;

import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public class ZombieNotBeAttractedByTurtleEgg {
    @Inject(method="registerGoals", at = @At("HEAD"), cancellable = true)
    private void registerGoals(CallbackInfo ci){
        Zombie entity = (Zombie)(Object)this;
        entity.goalSelector.addGoal(8, new LookAtPlayerGoal(entity, Player.class, 8.0F));
        entity.goalSelector.addGoal(8, new RandomLookAroundGoal(entity));
        entity.addBehaviourGoals();
        ci.cancel();
    }

    @Inject(method = "addBehaviourGoals", at = @At("HEAD"), cancellable = true)
    private void addBehaviourGoals(CallbackInfo ci){
        Zombie entity = (Zombie)(Object)this;
        entity.goalSelector.addGoal(2, new SpearUseGoal<>(entity, 1.0F, 1.0F, 10.0F, 2.0F));
        entity.goalSelector.addGoal(3, new ZombieAttackGoal(entity, 1.0F, false));
        entity.goalSelector.addGoal(6, new MoveThroughVillageGoal(entity, 1.0F, true, 4, () -> entity.canBreakDoors));
        entity.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(entity, 1.0F));
        entity.targetSelector.addGoal(1, (new HurtByTargetGoal(entity)).setAlertOthers(ZombifiedPiglin.class));
        entity.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(entity, Player.class, true));
        entity.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(entity, AbstractVillager.class, false));
        entity.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(entity, IronGolem.class, true));
        ci.cancel();
    }
}
