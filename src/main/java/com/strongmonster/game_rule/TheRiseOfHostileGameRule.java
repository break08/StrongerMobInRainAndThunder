package com.strongmonster.game_rule;

import com.strongmonster.TheRiseOfHostile;
import com.strongmonster.datagen.tag.TheRiseOfHostileEntityTag;

// Head lib
import com.strongmonster.head_lib.CustomGetScoreboard;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.illager.Evoker;
import net.minecraft.world.entity.monster.illager.Pillager;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.Vec3;

public class TheRiseOfHostileGameRule implements ModInitializer {
    public static final GameRule<Boolean> ALLOW_SLEEP_GAMERULE = GameRuleBuilder
            .forBoolean(true)
            .category(GameRuleCategory.PLAYER)
            .buildAndRegister(Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "allow_sleep"));

    public static final GameRule<Boolean> BONUS_DROP_GAMERULE = GameRuleBuilder
            .forBoolean(true)
            .category(GameRuleCategory.DROPS)
            .buildAndRegister(Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "bonus_drop"));

    public static final GameRule<Boolean> ALL_DIAMOND_GEAR_DROP_GAMERULE = GameRuleBuilder
            .forBoolean(false)
            .category(GameRuleCategory.DROPS)
            .buildAndRegister(Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "all_diamond_gear_drop"));
    public static final GameRule<Boolean> BONUS_EXP = GameRuleBuilder
            .forBoolean(false)
            .category(GameRuleCategory.DROPS)
            .buildAndRegister(Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "bonus_exp"));



    private static void onAllowSleepDisable(){
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world instanceof ServerLevel serverLevel) {
                boolean AllowSleepDisabled = serverLevel.getGameRules().get(TheRiseOfHostileGameRule.ALLOW_SLEEP_GAMERULE);
                BlockPos pos = hitResult.getBlockPos();
                if (!AllowSleepDisabled){
                    serverLevel.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                    if (player instanceof ServerPlayer _serverPlayer) {
                        LevelData.RespawnData respawnData =
                                LevelData.RespawnData.of(
                                        serverLevel.dimension(),
                                        pos,
                                        0.0F,
                                        0.0F
                                );

                        ServerPlayer.RespawnConfig config =
                                new ServerPlayer.RespawnConfig(
                                        respawnData,
                                        true
                                );

                        _serverPlayer.setRespawnPosition(config, true);

                        _serverPlayer.displayClientMessage(Component.literal("Game rule 'Allow Sleep' is disabled"), true);

                    }
                }
            }
            return InteractionResult.PASS;
        });
    }

    private static void onBonusDropOn() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(
                (world, entity, killedEntity, damageSource) -> {

                    boolean bonusDrop =
                            world.getGameRules().get(TheRiseOfHostileGameRule.BONUS_DROP_GAMERULE);
                    boolean bonusExp =
                            world.getGameRules().get(TheRiseOfHostileGameRule.BONUS_EXP);

                    BlockPos entityRipPos = killedEntity.blockPosition();
                    Entity attacker = damageSource.getEntity();

                    // Scoreboard Check
                    int isBuff = CustomGetScoreboard.getScoreBoard(entity, "buff");
                    int isSpecialBuff = CustomGetScoreboard.getScoreBoard(entity, "special_buff");

                    if (
                            attacker instanceof Player player
                                    && (isBuff == 1 || isSpecialBuff == 1)
                                    && bonusDrop
                    ) {

                        if (killedEntity instanceof Vindicator) {

                            for (
                                    int index0 = 0;
                                    index0 < Mth.nextInt(RandomSource.create(), 3, 7);
                                    index0++
                            ) {

                                if (world instanceof ServerLevel _level) {
                                    ItemEntity entityToSpawn_5 = new ItemEntity(
                                            _level,
                                            entityRipPos.getX(),
                                            entityRipPos.getY(),
                                            entityRipPos.getZ(),
                                            new ItemStack(Items.EMERALD)
                                    );

                                    entityToSpawn_5.setPickUpDelay(1);
                                    _level.addFreshEntity(entityToSpawn_5);
                                }
                            }

                        } else if (killedEntity instanceof Pillager) {

                            if (world instanceof ServerLevel _level) {
                                ItemEntity entityToSpawn_7 = new ItemEntity(
                                        _level,
                                        entityRipPos.getX(),
                                        entityRipPos.getY(),
                                        entityRipPos.getZ(),
                                        new ItemStack(Items.CROSSBOW)
                                );

                                entityToSpawn_7.setPickUpDelay(1);
                                _level.addFreshEntity(entityToSpawn_7);
                            }

                            for (
                                    int index1 = 0;
                                    index1 < Mth.nextInt(RandomSource.create(), 3, 10);
                                    index1++
                            ) {

                                if (world instanceof ServerLevel _level) {
                                    ItemEntity entityToSpawn_9 = new ItemEntity(
                                            _level,
                                            entityRipPos.getX(),
                                            entityRipPos.getY(),
                                            entityRipPos.getZ(),
                                            new ItemStack(Items.ARROW)
                                    );

                                    entityToSpawn_9.setPickUpDelay(1);
                                    _level.addFreshEntity(entityToSpawn_9);
                                }
                            }

                        } else if (killedEntity.getType().is(TheRiseOfHostileEntityTag.ZOMBIE_BUFF)) {

                            for (
                                    int index2 = 0;
                                    index2 < Mth.nextInt(RandomSource.create(), 2, 4);
                                    index2++
                            ) {

                                if (world instanceof ServerLevel _level) {
                                    ItemEntity entityToSpawn_12 = new ItemEntity(
                                            _level,
                                            entityRipPos.getX(),
                                            entityRipPos.getY(),
                                            entityRipPos.getZ(),
                                            new ItemStack(Items.IRON_INGOT)
                                    );

                                    entityToSpawn_12.setPickUpDelay(1);
                                    _level.addFreshEntity(entityToSpawn_12);
                                }
                            }

                            if (Math.random() < 0.1) {

                                if (world instanceof ServerLevel _level) {
                                    ItemEntity entityToSpawn_13 = new ItemEntity(
                                            _level,
                                            entityRipPos.getX(),
                                            entityRipPos.getY(),
                                            entityRipPos.getZ(),
                                            new ItemStack(Items.TOTEM_OF_UNDYING)
                                    );

                                    entityToSpawn_13.setPickUpDelay(1);
                                    _level.addFreshEntity(entityToSpawn_13);
                                }
                            }

                        } else if (killedEntity instanceof Creeper) {

                            for (
                                    int index3 = 0;
                                    index3 < Mth.nextInt(RandomSource.create(), 3, 6);
                                    index3++
                            ) {

                                if (world instanceof ServerLevel _level) {
                                    ItemEntity entityToSpawn_16 = new ItemEntity(
                                            _level,
                                            entityRipPos.getX(),
                                            entityRipPos.getY(),
                                            entityRipPos.getZ(),
                                            new ItemStack(Items.GUNPOWDER)
                                    );

                                    entityToSpawn_16.setPickUpDelay(1);
                                    _level.addFreshEntity(entityToSpawn_16);
                                }
                            }

                        } else if (killedEntity instanceof Evoker) {

                            if (world instanceof ServerLevel _level) {
                                ItemEntity entityToSpawn_18 = new ItemEntity(
                                        _level,
                                        entityRipPos.getX(),
                                        entityRipPos.getY(),
                                        entityRipPos.getZ(),
                                        new ItemStack(Items.TOTEM_OF_UNDYING)
                                );

                                entityToSpawn_18.setPickUpDelay(1);
                                _level.addFreshEntity(entityToSpawn_18);
                            }
                        }
                        if (world instanceof ServerLevel serverLevel) {
                            ExperienceOrb experienceOrb = new ExperienceOrb(killedEntity.level(), killedEntity.position(), Vec3.ZERO, 10);
                            serverLevel.addFreshEntity(experienceOrb);
                        }
                    }
                }
        );
    }

    @Override
    public void onInitialize(){
        onAllowSleepDisable();
        onBonusDropOn();
    }
}
