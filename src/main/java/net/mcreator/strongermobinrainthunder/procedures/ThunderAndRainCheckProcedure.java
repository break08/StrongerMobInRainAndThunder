package net.mcreator.strongermobinrainthunder.procedures;

import net.minecraft.world.level.LevelAccessor;

public class ThunderAndRainCheckProcedure {
	public static boolean execute(LevelAccessor world) {
		if (world.getLevelData().isThundering() || world.getLevelData().isRaining()) {
			return true;
		}
		return false;
	}
}