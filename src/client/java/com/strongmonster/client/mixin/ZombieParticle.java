package com.strongmonster.client.mixin;

import com.strongmonster.TheRiseOfHostile;
import com.strongmonster.client.config.TheRiseOfHostileConfig;
import com.strongmonster.item.TheRiseOfHostileItem;
import com.strongmonster.mixin.SpecialBuffAccess;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public class ZombieParticle {
    @Inject(method="tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci){
        Zombie zombie = (Zombie)(Object)this;
        Level world = zombie.level();
        if (((SpecialBuffAccess) zombie).getSBuff() && TheRiseOfHostileConfig.get().show_particle && world.getGameTime() % 10 == 0){
            if (zombie.getItemBySlot(EquipmentSlot.BODY).is(TheRiseOfHostileItem.DYNAMITE_ROLL)){
                for (int i = 0; i < 7; i++){
                    world.addParticle(
                            TheRiseOfHostile.EXPLODE_ZOMBIE,
                            zombie.blockPosition().getX(), zombie.blockPosition().getY(), zombie.blockPosition().getZ(),
                            0.0, 0.1, 0.0
                    );
                }
            }
        }
    }
}
