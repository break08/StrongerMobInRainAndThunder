package com.strongmonster.datagen.tag;

import com.strongmonster.TheRiseOfHostile;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import java.util.concurrent.CompletableFuture;

public class TheRiseOfHostileEntityTag extends FabricTagProvider.EntityTypeTagProvider {

    public static final TagKey<EntityType<?>> ZOMBIE_BUFF = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "zombie_buff"));
    public static final TagKey<EntityType<?>> SKELETON_BUFF = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "skeleton_buff"));

    public static final TagKey<EntityType<?>> IS_GOLEM = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "is_golem"));

    public static final TagKey<EntityType<?>> ARMOR_EQUIP_BUFF = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "armor_buff"));

    public static final TagKey<EntityType<?>> HOSTILE = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "hostile_all"));

    public static final TagKey<EntityType<?>> TWENTY_HEALTH = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "twenty_health"));

    public TheRiseOfHostileEntityTag(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(ZOMBIE_BUFF)
                .add(EntityType.ZOMBIE)
                .add(EntityType.HUSK)
                .add(EntityType.ZOMBIE_VILLAGER);
        valueLookupBuilder(SKELETON_BUFF)
                .add(EntityType.SKELETON)
                .add(EntityType.STRAY)
                .add(EntityType.BOGGED)
                .add(EntityType.PARCHED);
        valueLookupBuilder(IS_GOLEM)
                .add(EntityType.IRON_GOLEM)
                .add(EntityType.SNOW_GOLEM);
        valueLookupBuilder(ARMOR_EQUIP_BUFF)
                .addTag(SKELETON_BUFF)
                .addTag(ZOMBIE_BUFF)
                .add(EntityType.DROWNED);
        valueLookupBuilder(HOSTILE)
                .addTag(ARMOR_EQUIP_BUFF)
                .add(EntityType.PILLAGER)
                .add(EntityType.VINDICATOR)
                .add(EntityType.EVOKER)
                .add(EntityType.VEX)
                .add(EntityType.RAVAGER)
                .add(EntityType.WITCH)
                .add(EntityType.SPIDER)
                .add(EntityType.CAVE_SPIDER);
        valueLookupBuilder(TWENTY_HEALTH)
                .add(EntityType.PIGLIN_BRUTE)
                .addTag(ZOMBIE_BUFF)
                .addTag(SKELETON_BUFF)
                .add(EntityType.DROWNED)
                .add(EntityType.WITHER_SKELETON)
                .add(EntityType.CREEPER)
                .add(EntityType.BLAZE)
                .add(EntityType.PHANTOM);
    }
}