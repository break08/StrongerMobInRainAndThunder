/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.strongermobinrainthunder.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class StrongermobinrainthunderModGameRules {
	public static final GameRules.Key<GameRules.IntegerValue> BONUS_EXPERIENCE = GameRules.register("bonusExperience", GameRules.Category.DROPS, GameRules.IntegerValue.create(7));
	public static final GameRules.Key<GameRules.BooleanValue> DROP_GEARS = GameRules.register("dropGears", GameRules.Category.MOBS, GameRules.BooleanValue.create(false));
	public static final GameRules.Key<GameRules.BooleanValue> BONUS_MOB_LOOT = GameRules.register("bonusMobLoot", GameRules.Category.MOBS, GameRules.BooleanValue.create(true));
	public static final GameRules.Key<GameRules.BooleanValue> ALLOW_SLEEP_IN_RAIN_AND_THUNDER = GameRules.register("allowSleepInRainAndThunder", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));
}