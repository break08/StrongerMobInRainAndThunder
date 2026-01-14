package net.mcreator.strongermobinrainthunder.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.strongermobinrainthunder.init.StrongermobinrainthunderModGameRules;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BonusDropProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		ItemStack itemdropbonus = ItemStack.EMPTY;
		if (world.getLevelData().getGameRules().getBoolean(StrongermobinrainthunderModGameRules.BONUS_MOB_LOOT) == true && sourceentity instanceof Player) {
			if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:vindicator_buff")))) {
				for (int index0 = 0; index0 < Mth.nextInt(RandomSource.create(), 3, 7); index0++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.EMERALD));
						entityToSpawn.setPickUpDelay(1);
						_level.addFreshEntity(entityToSpawn);
					}
				}
			} else if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:pillager_buff")))) {
				if (world instanceof ServerLevel _level) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.CROSSBOW));
					entityToSpawn.setPickUpDelay(1);
					_level.addFreshEntity(entityToSpawn);
				}
				for (int index1 = 0; index1 < Mth.nextInt(RandomSource.create(), 3, 10); index1++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.ARROW));
						entityToSpawn.setPickUpDelay(1);
						_level.addFreshEntity(entityToSpawn);
					}
				}
			} else if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:zombie_buff")))) {
				for (int index2 = 0; index2 < Mth.nextInt(RandomSource.create(), 2, 4); index2++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.IRON_INGOT));
						entityToSpawn.setPickUpDelay(1);
						_level.addFreshEntity(entityToSpawn);
					}
				}
				if (Math.random() < 0.1) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.TOTEM_OF_UNDYING));
						entityToSpawn.setPickUpDelay(1);
						_level.addFreshEntity(entityToSpawn);
					}
				}
			} else if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:creeper_buff")))) {
				for (int index3 = 0; index3 < Mth.nextInt(RandomSource.create(), 3, 6); index3++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.GUNPOWDER));
						entityToSpawn.setPickUpDelay(1);
						_level.addFreshEntity(entityToSpawn);
					}
				}
			} else if (entity instanceof Evoker) {
				if (world instanceof ServerLevel _level) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.TOTEM_OF_UNDYING));
					entityToSpawn.setPickUpDelay(1);
					_level.addFreshEntity(entityToSpawn);
				}
			} else if (entity instanceof Drowned) {
				if (Math.random() < 0.06) {
					itemdropbonus = new ItemStack(Items.TRIDENT).copy();
					if (Math.random() < 0.14) {
						itemdropbonus.enchant(Enchantments.LOYALTY, 1);
					}
					if (Math.random() < 0.05) {
						itemdropbonus.enchant(Enchantments.MENDING, 1);
					}
					if (Math.random() < 0.15) {
						itemdropbonus.enchant(Enchantments.IMPALING, 1);
					}
					if (Math.random() < 0.3) {
						itemdropbonus.enchant(Enchantments.CHANNELING, 1);
					}
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, itemdropbonus);
						entityToSpawn.setPickUpDelay(1);
						_level.addFreshEntity(entityToSpawn);
					}
				}
			}
		}
		if ((world.getLevelData().getGameRules().getInt(StrongermobinrainthunderModGameRules.BONUS_EXPERIENCE)) > 0) {
			if (world instanceof ServerLevel _level)
				_level.addFreshEntity(new ExperienceOrb(_level, x, y, z, (world.getLevelData().getGameRules().getInt(StrongermobinrainthunderModGameRules.BONUS_EXPERIENCE))));
		}
	}
}