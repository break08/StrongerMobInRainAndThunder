package com.strongmonster.mixin;

import com.strongmonster.datagen.TheRiseOfHostileItemTag;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.WitherSkull;
import net.minecraft.world.entity.projectile.hurtingprojectile.DragonFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public class ZombieRangedAttack {
    @Inject(method="tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci){
        Zombie entity = (Zombie)(Object)this;
        Scoreboard scoreboard = entity.level().getScoreboard();

        Objective objective = scoreboard.getObjective("special_buff");

        int isSpecialBuffed =
                scoreboard
                        .getOrCreatePlayerScore(
                                entity,
                                objective
                        )
                        .get();

        Objective cooldown_obj = scoreboard.getObjective("cool");

        int getCooldown =
                scoreboard
                        .getOrCreatePlayerScore(
                                entity,
                                cooldown_obj
                        )
                        .get();

        if (!(entity.getTarget() == null)
                && entity.getOffhandItem().is(TheRiseOfHostileItemTag.IS_THROWABLE)
                && entity instanceof LivingEntity livingEntity
                && !livingEntity.level().isClientSide()
                && isSpecialBuffed == 1
                && getCooldown == 0
        ) {
            for (int i = 0; i < 5; i++) {
                if (entity.getOffhandItem().equals(new ItemStack(Items.FIRE_CHARGE))) {
                    SmallFireball projectile = new SmallFireball(EntityType.SMALL_FIREBALL, livingEntity.level());
                    projectile.setPos(livingEntity.getX(), livingEntity.getEyeY() - 0.1, livingEntity.getZ());
                    projectile.shoot(livingEntity.getLookAngle().x, livingEntity.getLookAngle().y, livingEntity.getLookAngle().z, 2, 0.5f);

                    livingEntity.level().addFreshEntity(projectile);
                } else if (entity.getOffhandItem().equals(new ItemStack(Items.WITHER_SKELETON_SKULL))) {
                    WitherSkull projectile = new WitherSkull(EntityType.WITHER_SKULL, livingEntity.level());
                    projectile.setPos(livingEntity.getX(), livingEntity.getEyeY() - 0.1, livingEntity.getZ());
                    projectile.shoot(livingEntity.getLookAngle().x, livingEntity.getLookAngle().y, livingEntity.getLookAngle().z, 2, 0.5f);

                    livingEntity.level().addFreshEntity(projectile);
                } else if (entity.getOffhandItem().equals(new ItemStack(Items.DRAGON_BREATH))) {
                    DragonFireball projectile = new DragonFireball(EntityType.DRAGON_FIREBALL, livingEntity.level());
                    projectile.setPos(livingEntity.getX(), livingEntity.getEyeY() - 0.1, livingEntity.getZ());
                    projectile.shoot(livingEntity.getLookAngle().x, livingEntity.getLookAngle().y, livingEntity.getLookAngle().z, 2, 0.5f);
                    livingEntity.level().addFreshEntity(projectile);
                }
            }
            scoreboard
                    .getOrCreatePlayerScore(
                            entity,
                            cooldown_obj
                    )
                    .set(20);
        } else if (!(getCooldown == 0) && isSpecialBuffed == 1 && entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide() && entity.getOffhandItem().is(TheRiseOfHostileItemTag.IS_THROWABLE)) {
            scoreboard
                    .getOrCreatePlayerScore(
                            entity,
                            cooldown_obj
                    )
                    .set(getCooldown - 1);
        }
    }
}
