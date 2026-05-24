package com.strongmonster.client;

import com.strongmonster.TheRiseOfHostile;
import com.strongmonster.client.config.TheRiseOfHostileConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.EndRodParticle;

public class TheRiseOfHostileClient implements ClientModInitializer {

    public static TheRiseOfHostileConfig CONFIG;

    @Override
    public void onInitializeClient() {
        AutoConfig.register(
                TheRiseOfHostileConfig.class,
                GsonConfigSerializer::new
        );

        CONFIG = AutoConfig.getConfigHolder(TheRiseOfHostileConfig.class).getConfig();

        ParticleFactoryRegistry.getInstance().register(TheRiseOfHostile.EXPLODE_ZOMBIE, EndRodParticle.Provider::new);
    }
}