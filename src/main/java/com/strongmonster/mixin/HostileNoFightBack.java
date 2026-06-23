package com.strongmonster.mixin;

import com.strongmonster.datagen.tag.TheRiseOfHostileEntityTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/world/entity/ai/goal/target/HurtByTargetGoal")
public abstract class HostileNoFightBack extends TargetGoal {

    @Shadow
    private int timestamp;

    @Shadow
    @Final
    private Class<?>[] toIgnoreDamage;

    @Shadow
    @Final
    private static TargetingConditions HURT_BY_TARGETING;

    public HostileNoFightBack(Mob mob, boolean bl) {
        super(mob, bl);
    }

    @Inject(method="canUse", at = @At("HEAD"), cancellable = true)
    public void canUse(CallbackInfoReturnable<Boolean> cir){
        int i = this.mob.getLastHurtByMobTimestamp();
        LivingEntity livingEntity = this.mob.getLastHurtByMob();
        if (i != this.timestamp && livingEntity != null && !(livingEntity.getType().is(TheRiseOfHostileEntityTag.HOSTILE))) {
            if (livingEntity.getType() == EntityType.PLAYER && getServerLevel(this.mob).getGameRules().get(GameRules.UNIVERSAL_ANGER)) {
                cir.setReturnValue(false);
            } else {
                for(Class<?> class_ : this.toIgnoreDamage) {
                    if (class_.isAssignableFrom(livingEntity.getClass())) {
                        cir.setReturnValue(false);
                        return;
                    }
                }
                cir.setReturnValue(this.canAttack(livingEntity, HURT_BY_TARGETING));
            }
        } else {
            cir.setReturnValue(false);
        }
    }
}
