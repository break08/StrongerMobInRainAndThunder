package com.strongmonster.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.creaking.Creaking;
import net.minecraft.world.entity.monster.illager.AbstractIllager;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Vindicator.class)
public abstract class VindicatorNoAvoidCreaking extends AbstractIllager {
    protected VindicatorNoAvoidCreaking(EntityType<? extends Vindicator> entityType, Level level) {
        super(entityType, level);
    }
    @Inject(method = "registerGoals", at = @At("HEAD"), cancellable = true)
    private void registerGoals (CallbackInfo ci){
        ci.cancel();
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new Vindicator.VindicatorBreakDoorGoal(this));
        this.goalSelector.addGoal(3, new AbstractIllager.RaiderOpenDoorGoal(this));
        this.goalSelector.addGoal(4, new Raider.HoldGroundAttackGoal(this, 10.0F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0F, false));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(4, new Vindicator.VindicatorJohnnyAttackGoal((Vindicator)(Object)this));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }
}
