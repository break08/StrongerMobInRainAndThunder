package net.mcreator.strongermobinrainthunder.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.strongermobinrainthunder.init.StrongermobinrainthunderModGameRules;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MobSpawnBuffProcedure {
	@SubscribeEvent
	public static void onEntitySpawned(EntityJoinLevelEvent event) {
		execute(event, event.getLevel(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double roll = 0;
		ItemStack main_hand = ItemStack.EMPTY;
		ItemStack chestplate = ItemStack.EMPTY;
		ItemStack helmet = ItemStack.EMPTY;
		ItemStack boots = ItemStack.EMPTY;
		ItemStack off_hand = ItemStack.EMPTY;
		ItemStack legging = ItemStack.EMPTY;
		if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:zombie_buff")))) {
			if (world.getLevelData().isThundering() && Math.random() < 0.7) {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("buff");
					if (_so == null)
						_so = _sc.addObjective("buff", ObjectiveCriteria.DUMMY, Component.literal("buff"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
				}
				if (Math.random() < 0.5) {
					off_hand = new ItemStack((ForgeRegistries.ITEMS.tags().getTag(ItemTags.create(ResourceLocation.parse("minecraft:effect_from_item"))).getRandomElement(RandomSource.create()).orElseGet(() -> Items.AIR))).copy();
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack4 = off_hand.copy();
						_setstack4.setCount(1);
						_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack4);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				}
				if (Math.random() < 0.6) {
					if (Math.random() < 0.9) {
						main_hand = new ItemStack(Items.DIAMOND_SWORD).copy();
					} else {
						main_hand = new ItemStack(Items.NETHERITE_SWORD).copy();
					}
					main_hand.enchant(Enchantments.FIRE_ASPECT, 1);
					main_hand.enchant(Enchantments.SHARPNESS, 5);
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack7 = main_hand.copy();
						_setstack7.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack7);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				}
				if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:killer_bunny_summon_with"))) && Math.random() < 0.5) {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"summon rabbit ~ ~ ~ {RabbitType:99}");
				}
			} else if (world.getLevelData().isRaining() && Math.random() < 0.6) {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("buff");
					if (_so == null)
						_so = _sc.addObjective("buff", ObjectiveCriteria.DUMMY, Component.literal("buff"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
				}
				if (Math.random() < 0.9) {
					main_hand = new ItemStack(Items.IRON_SWORD).copy();
				} else {
					main_hand = new ItemStack(Items.DIAMOND_SWORD).copy();
				}
				main_hand.enchant(Enchantments.SHARPNESS, 2);
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack13 = main_hand.copy();
					_setstack13.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack13);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
				if (Math.random() < 0.5) {
					off_hand = new ItemStack((ForgeRegistries.ITEMS.tags().getTag(ItemTags.create(ResourceLocation.parse("minecraft:effect_from_item"))).getRandomElement(RandomSource.create()).orElseGet(() -> Items.AIR))).copy();
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack15 = off_hand.copy();
						_setstack15.setCount(1);
						_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack15);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				}
			}
		} else if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:skeleton_buff")))) {
			if (world.getLevelData().isThundering()) {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("buff");
					if (_so == null)
						_so = _sc.addObjective("buff", ObjectiveCriteria.DUMMY, Component.literal("buff"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
				}
				main_hand = new ItemStack(Items.BOW).copy();
				main_hand.enchant(Enchantments.PUNCH_ARROWS, 4);
				main_hand.enchant(Enchantments.POWER_ARROWS, 5);
				main_hand.enchant(Enchantments.FLAMING_ARROWS, 2);
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack22 = main_hand.copy();
					_setstack22.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack22);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			} else if (world.getLevelData().isRaining() && Math.random() < 0.65) {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("buff");
					if (_so == null)
						_so = _sc.addObjective("buff", ObjectiveCriteria.DUMMY, Component.literal("buff"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
				}
				main_hand = new ItemStack(Items.BOW).copy();
				main_hand.enchant(Enchantments.PUNCH_ARROWS, 2);
				main_hand.enchant(Enchantments.POWER_ARROWS, 1);
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack27 = main_hand.copy();
					_setstack27.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack27);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			}
		} else if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:creeper_buff"))) && world.getLevelData().isThundering()) {
			if (Math.random() < 0.5) {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("buff");
					if (_so == null)
						_so = _sc.addObjective("buff", ObjectiveCriteria.DUMMY, Component.literal("buff"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
				}
				{
					Entity _ent = entity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "data merge entity @s {powered:1b}");
					}
				}
			}
		} else if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:pillager_buff")))
				&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem())) {
			if (world.getLevelData().isThundering()) {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("buff");
					if (_so == null)
						_so = _sc.addObjective("buff", ObjectiveCriteria.DUMMY, Component.literal("buff"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
				}
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).enchant(Enchantments.MULTISHOT, 1);
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).enchant(Enchantments.UNBREAKING, 5);
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).enchant(Enchantments.QUICK_CHARGE, 3);
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).enchant(Enchantments.PIERCING, 2);
			} else if (world.getLevelData().isRaining()) {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("buff");
					if (_so == null)
						_so = _sc.addObjective("buff", ObjectiveCriteria.DUMMY, Component.literal("buff"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
				}
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).enchant(Enchantments.MULTISHOT, 1);
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).enchant(Enchantments.UNBREAKING, 3);
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).enchant(Enchantments.QUICK_CHARGE, 2);
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).enchant(Enchantments.PIERCING, 1);
			}
		} else if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:vindicator_buff")))) {
			if (world.getLevelData().isThundering()) {
				main_hand = new ItemStack(Items.DIAMOND_AXE).copy();
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("buff");
					if (_so == null)
						_so = _sc.addObjective("buff", ObjectiveCriteria.DUMMY, Component.literal("buff"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
				}
				main_hand.enchant(Enchantments.SHARPNESS, 4);
				main_hand.enchant(Enchantments.UNBREAKING, 5);
			} else if (world.getLevelData().isRaining()) {
				main_hand = new ItemStack(Items.DIAMOND_AXE).copy();
				main_hand.enchant(Enchantments.SHARPNESS, 2);
				main_hand.enchant(Enchantments.UNBREAKING, 4);
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("buff");
					if (_so == null)
						_so = _sc.addObjective("buff", ObjectiveCriteria.DUMMY, Component.literal("buff"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
				}
			}
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack65 = main_hand.copy();
				_setstack65.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack65);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
		} else if (entity instanceof Drowned) {
			{
				Entity _ent = entity;
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective("buff");
				if (_so == null)
					_so = _sc.addObjective("buff", ObjectiveCriteria.DUMMY, Component.literal("buff"), ObjectiveCriteria.RenderType.INTEGER);
				_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
			}
			if (Math.random() < 0.45) {
				if (Math.random() < 0.9) {
					entity.startRiding((world instanceof ServerLevel _level68 ? EntityType.GUARDIAN.spawn(_level68, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED) : null));
				} else {
					entity.startRiding((world instanceof ServerLevel _level70 ? EntityType.ELDER_GUARDIAN.spawn(_level70, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED) : null));
				}
			}
		} else if (entity instanceof Vex && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()) {
			{
				Entity _ent = entity;
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective("buff");
				if (_so == null)
					_so = _sc.addObjective("buff", ObjectiveCriteria.DUMMY, Component.literal("buff"), ObjectiveCriteria.RenderType.INTEGER);
				_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
			}
			if (Math.random() < 0.1 && world.getLevelData().isThundering()) {
				main_hand = new ItemStack(Items.NETHERITE_SWORD).copy();
			} else {
				main_hand = new ItemStack(Items.DIAMOND_SWORD).copy();
			}
		} else if (entity instanceof Evoker) {
			{
				Entity _ent = entity;
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective("buff");
				if (_so == null)
					_so = _sc.addObjective("buff", ObjectiveCriteria.DUMMY, Component.literal("buff"), ObjectiveCriteria.RenderType.INTEGER);
				_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
			}
		}
		if (getEntityScore("buff", entity) == 1) {
			if (world.getLevelData().isThundering()) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 144000, 4));
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 144000, 1));
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 144000, 4));
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 144000, 1));
			} else if (world.getLevelData().isRaining()) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 144000, 2));
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 144000, 1));
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 144000, 2));
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 144000, 0));
			}
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 144000, 0));
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 144000, 0));
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 144000, 0));
			if (world.getLevelData().getGameRules().getBoolean(StrongermobinrainthunderModGameRules.DROP_GEARS) == false) {
				{
					Entity _ent = entity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "data merge entity @s {HandDropChances:[0.0f,0.0f],ArmorDropChances:[0.0f,0.0f,0.0f,0.0f]}");
					}
				}
			}
			if (Math.random() < 0.95) {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("shoot_remain");
					if (_so == null)
						_so = _sc.addObjective("shoot_remain", ObjectiveCriteria.DUMMY, Component.literal("shoot_remain"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(75);
				}
			} else {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("infinity_shoot");
					if (_so == null)
						_so = _sc.addObjective("infinity_shoot", ObjectiveCriteria.DUMMY, Component.literal("infinity_shoot"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
				}
				entity.setCustomName(Component.literal("Crazy Man"));
			}
			{
				Entity _ent = entity;
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective("cooldown");
				if (_so == null)
					_so = _sc.addObjective("cooldown", ObjectiveCriteria.DUMMY, Component.literal("cooldown"), ObjectiveCriteria.RenderType.INTEGER);
				_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(80);
			}
			if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:able_to_buff")))) {
				if (world.getLevelData().isThundering()) {
					if (Math.random() < 0.65) {
						if (Math.random() < 0.9) {
							chestplate = new ItemStack(Items.DIAMOND_CHESTPLATE).copy();
						} else {
							chestplate = new ItemStack(Items.NETHERITE_CHESTPLATE).copy();
						}
						chestplate.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
						chestplate.enchant(Enchantments.BLAST_PROTECTION, 4);
						if (entity instanceof LivingEntity _living) {
							_living.setItemSlot(EquipmentSlot.CHEST, chestplate);
						}
					}
					if (Math.random() < 0.45) {
						if (Math.random() < 0.9) {
							helmet = new ItemStack(Items.DIAMOND_HELMET).copy();
						} else {
							helmet = new ItemStack(Items.NETHERITE_HELMET).copy();
						}
						helmet.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
						if (entity instanceof LivingEntity _living) {
							_living.setItemSlot(EquipmentSlot.HEAD, helmet);
						}
					}
					if (Math.random() < 0.85) {
						if (Math.random() < 0.9) {
							legging = new ItemStack(Items.DIAMOND_LEGGINGS).copy();
						} else {
							legging = new ItemStack(Items.NETHERITE_LEGGINGS).copy();
						}
						legging.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
						if (entity instanceof LivingEntity _living) {
							_living.setItemSlot(EquipmentSlot.LEGS, legging);
						}
					}
					if (Math.random() < 0.85) {
						if (Math.random() < 0.9) {
							boots = new ItemStack(Items.DIAMOND_BOOTS).copy();
						} else {
							boots = new ItemStack(Items.NETHERITE_BOOTS).copy();
						}
						boots.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
						if (entity instanceof LivingEntity _living) {
							_living.setItemSlot(EquipmentSlot.FEET, boots);
						}
					}
				} else if (world.getLevelData().isRaining()) {
					if (Math.random() < 0.9) {
						chestplate = new ItemStack(Items.IRON_CHESTPLATE).copy();
					} else {
						chestplate = new ItemStack(Items.DIAMOND_CHESTPLATE).copy();
					}
					chestplate.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
					chestplate.enchant(Enchantments.BLAST_PROTECTION, 3);
					if (entity instanceof LivingEntity _living) {
						_living.setItemSlot(EquipmentSlot.CHEST, chestplate);
					}
				}
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
}