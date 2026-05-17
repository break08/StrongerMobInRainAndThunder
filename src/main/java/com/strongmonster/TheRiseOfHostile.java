package com.strongmonster;

import com.strongmonster.procedure.AntiIronGolemCheating;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strongmonster.procedure.MobSpawnBuff;
import com.strongmonster.procedure.IllagerBuff;

public class TheRiseOfHostile implements ModInitializer {
	public static final String MOD_ID = "the_rise_of_hostile";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MobSpawnBuff.register();
		IllagerBuff.register();
		AntiIronGolemCheating.register();
	}
}