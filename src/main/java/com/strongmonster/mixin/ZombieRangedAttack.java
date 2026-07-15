package com.strongmonster.mixin;

import com.strongmonster.datagen.tag.TheRiseOfHostileItemTag;

import com.strongmonster.access.CoolAccess;
import com.strongmonster.access.SpecialBuffAccess;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.WitherSkull;
import net.minecraft.world.entity.projectile.hurtingprojectile.DragonFireball;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
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
                && entity instanceof SpecialBuffAccess
                && ((SpecialBuffAccess) entity).getSBuff()
        ) {
            for (int i = 0; i < 5; i++) {
                double e = entity.getTarget().getX() - livingEntity.getX();
                double f = entity.getTarget().getY(0.5F) - livingEntity.getY(0.5F);
                double g = entity.getTarget().getZ() - livingEntity.getZ();

                double h = Math.sqrt(Math.sqrt(entity.distanceToSqr(entity.getTarget()))) * 0.5F;
                Vec3 vec3 = new Vec3(
                        entity.getRandom().triangle(e, 2.297 * h),
                        f,
                        entity.getRandom().triangle(g, 2.297 * h)
                );
                if (entity.getOffhandItem().is(Items.FIRE_CHARGE)) {
                    SmallFireball projectile = new SmallFireball(entity.level(), entity, vec3.normalize());
                    projectile.setPos(entity.getX(), entity.getY(0.5F) + 0.5F, entity.getZ());

                    livingEntity.level().addFreshEntity(projectile);
                } else if (entity.getOffhandItem().is(Items.WITHER_SKELETON_SKULL)) {
                    WitherSkull projectile = new WitherSkull(entity.level(), entity, vec3.normalize());
                    projectile.setPos(entity.getX(), entity.getY(0.5F) + 0.5F, entity.getZ());

                    livingEntity.level().addFreshEntity(projectile);
                } else if (entity.getOffhandItem().is(Items.DRAGON_BREATH)) {
                    DragonFireball projectile = new DragonFireball(entity.level(), entity, vec3.normalize());
                    projectile.setPos(entity.getX(), entity.getY(0.5F) + 0.5F, entity.getZ());

                    livingEntity.level().addFreshEntity(projectile);
                }
            }
            ((CoolAccess) entity).setCool(20);
        } else if (((CoolAccess) entity).getCool() != 0 && ((SpecialBuffAccess) entity).getSBuff() && entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide() && entity.getOffhandItem().is(TheRiseOfHostileItemTag.IS_THROWABLE)) {
            ((CoolAccess) entity).setCool(((CoolAccess) entity).getCool() - 1);
        }
    }
}
