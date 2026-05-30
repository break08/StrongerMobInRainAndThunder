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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public class ZombieRangedAttack {
    @Inject(method="tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci){
        Zombie entity = (Zombie)(Object)this;
        if (!(entity.getTarget() == null)
                && entity.getOffhandItem().is(TheRiseOfHostileItemTag.IS_THROWABLE)
                && entity instanceof LivingEntity livingEntity
                && !livingEntity.level().isClientSide()
                && ((CoolAccess) entity).getCool() == 0
                && ((SpecialBuffAccess) entity).getSBuff()
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
            ((CoolAccess) entity).setCool(20);
        } else if (!(((CoolAccess) entity).getCool() == 20) && ((SpecialBuffAccess) entity).getSBuff() && entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide() && entity.getOffhandItem().is(TheRiseOfHostileItemTag.IS_THROWABLE)) {
            ((CoolAccess) entity).setCool(((CoolAccess) entity).getCool() - 1);
        }
    }
}
