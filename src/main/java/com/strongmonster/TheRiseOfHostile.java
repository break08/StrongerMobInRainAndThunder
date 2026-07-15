package com.strongmonster;

import com.strongmonster.item.TheRiseOfHostileItem;
import com.strongmonster.procedure.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheRiseOfHostile implements ModInitializer {
	public static final String MOD_ID = "the_rise_of_hostile";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final SimpleParticleType EXPLODE_ZOMBIE = FabricParticleTypes.simple();

	@Override
	public void onInitialize() {
		// Item Register
		TheRiseOfHostileItem.initialize();

		// Procedure Register
		AntiIronGolemCheating.register();
		BadEffectToTarget.register();
		DynamiteExplode.register();;
		SkeletonShootBadEffect.register();

		// Particle
		Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "explode_zombie"), EXPLODE_ZOMBIE);
	}
}