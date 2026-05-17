package com.strongmonster.game_rule;

import com.strongmonster.TheRiseOfHostile;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.storage.LevelData;

public class TheRiseOfHostileGameRule implements ModInitializer {
    public static final GameRule<Boolean> ALLOW_SLEEP_GAMERULE = GameRuleBuilder
            .forBoolean(true)
            .category(GameRuleCategory.PLAYER)
            .buildAndRegister(Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "allow_sleep"));

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
                    }
                }
            }
            return InteractionResult.PASS;
        });
    }

    @Override
    public void onInitialize(){
        onAllowSleepDisable();
    }
}
