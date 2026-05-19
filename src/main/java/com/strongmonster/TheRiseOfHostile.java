package com.strongmonster;

import com.strongmonster.item.TheRiseOfHostileItem;
import com.strongmonster.procedure.*;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheRiseOfHostile implements ModInitializer {
	public static final String MOD_ID = "the_rise_of_hostile";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Item Register

		TheRiseOfHostileItem.initialize();

		// Procedure Register
		MobSpawnBuff.register();
		IllagerBuff.register();
		AntiIronGolemCheating.register();
		BadEffectToTarget.register();
		DynamiteExplode.register();
	}
}