package net.mcreator.strongermobinrainthunder.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.strongermobinrainthunder.network.StrongermobinrainthunderModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class IncreaseChanceForRainProcedure {
	@SubscribeEvent
	public static void onWorldTick(TickEvent.LevelTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.level);
		}
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		if (Math.random() < 0.5 && StrongermobinrainthunderModVariables.MapVariables.get(world).rolled == false && world.getLevelData().isRaining()) {
			StrongermobinrainthunderModVariables.MapVariables.get(world).rolled = true;
			StrongermobinrainthunderModVariables.MapVariables.get(world).syncData(world);
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3((world.getLevelData().getXSpawn()), (world.getLevelData().getYSpawn()), (world.getLevelData().getZSpawn())), Vec2.ZERO,
						_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "weather thunder");
		}
		if (StrongermobinrainthunderModVariables.MapVariables.get(world).rolled == true && !world.getLevelData().isRaining()) {
			StrongermobinrainthunderModVariables.MapVariables.get(world).rolled = false;
			StrongermobinrainthunderModVariables.MapVariables.get(world).syncData(world);
		}
	}
}