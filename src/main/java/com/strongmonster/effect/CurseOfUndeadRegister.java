package com.strongmonster.effect;

import com.strongmonster.TheRiseOfHostile;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

public class CurseOfUndeadRegister implements ModInitializer {
    public static final Holder<MobEffect> CURSE_OF_UNDEAD = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "curse_of_undead"), new CurseOfUndead());
    @Override
    public void onInitialize(){

    }
}
