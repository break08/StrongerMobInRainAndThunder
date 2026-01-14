package net.mcreator.strongermobinrainthunder.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ThrowProjectileProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (getEntityScore("buff", entity) == 1 && !((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null) && getEntityScore("cooldown", entity) == 0
				&& (!(getEntityScore("shoot_remain", entity) == 0) || getEntityScore("infinity", entity) == 1)) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.FIRE_CHARGE) {
				if (world instanceof ServerLevel projectileLevel) {
					Projectile _entityToSpawn = initProjectileProperties(new SmallFireball(EntityType.SMALL_FIREBALL, projectileLevel), entity, new Vec3(0, (-0.03), 0));
					_entityToSpawn.setPos((entity.getX()), (entity.getY()), (entity.getZ()));
					_entityToSpawn.shoot((entity.getLookAngle().x), (entity.getLookAngle().y), (entity.getLookAngle().z), 1, 0);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Blocks.WITHER_SKELETON_SKULL.asItem()) {
				if (world instanceof ServerLevel projectileLevel) {
					Projectile _entityToSpawn = initProjectileProperties(new WitherSkull(EntityType.WITHER_SKULL, projectileLevel), entity, new Vec3(0, (-0.03), 0));
					_entityToSpawn.setPos((entity.getX()), (entity.getY()), (entity.getZ()));
					_entityToSpawn.shoot((entity.getLookAngle().x), (entity.getLookAngle().y), (entity.getLookAngle().z), 1, 0);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.SNOWBALL) {
				if (world instanceof ServerLevel projectileLevel) {
					Projectile _entityToSpawn = initProjectileProperties(new Snowball(EntityType.SNOWBALL, projectileLevel), entity, Vec3.ZERO);
					_entityToSpawn.setPos((entity.getX()), (entity.getY()), (entity.getZ()));
					_entityToSpawn.shoot((entity.getLookAngle().x), (entity.getLookAngle().y), (entity.getLookAngle().z), 2, 0);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
			}
			if (!(getEntityScore("infinity", entity) == 1)) {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("shoot_remain");
					if (_so == null)
						_so = _sc.addObjective("shoot_remain", ObjectiveCriteria.DUMMY, Component.literal("shoot_remain"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(getEntityScore("shoot_remain", entity) - 1);
				}
			}
			{
				Entity _ent = entity;
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective("cooldown");
				if (_so == null)
					_so = _sc.addObjective("cooldown", ObjectiveCriteria.DUMMY, Component.literal("cooldown"), ObjectiveCriteria.RenderType.INTEGER);
				_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(80);
			}
		} else if (getEntityScore("cooldown", entity) <= 80 && getEntityScore("cooldown", entity) > 0) {
			{
				Entity _ent = entity;
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective("cooldown");
				if (_so == null)
					_so = _sc.addObjective("cooldown", ObjectiveCriteria.DUMMY, Component.literal("cooldown"), ObjectiveCriteria.RenderType.INTEGER);
				_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(getEntityScore("cooldown", entity) - 1);
			}
		}
	}

	private static int getEntityScore(String score, Entity entity) {
		Scoreboard scoreboard = entity.level().getScoreboard();
		Objective scoreboardObjective = scoreboard.getObjective(score);
		if (scoreboardObjective != null)
			return scoreboard.getOrCreatePlayerScore(entity.getScoreboardName(), scoreboardObjective).getScore();
		return 0;
	}

	private static Projectile initProjectileProperties(Projectile entityToSpawn, Entity shooter, Vec3 acceleration) {
		entityToSpawn.setOwner(shooter);
		if (!Vec3.ZERO.equals(acceleration)) {
			entityToSpawn.setDeltaMovement(acceleration);
			entityToSpawn.hasImpulse = true;
		}
		return entityToSpawn;
	}
}