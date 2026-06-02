package com.strongmonster.datagen.tag;

import com.strongmonster.TheRiseOfHostile;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class TheRiseOfHostileItemTag extends FabricTagProvider.ItemTagProvider{
    public static final TagKey<Item> IS_HELMET = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "is_helmet"));

    public static final TagKey<Item> IS_THROWABLE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "is_throwable"));

    public static final TagKey<Item> GIVE_TARGET_EFFECT = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "give_target_effect"));

    public TheRiseOfHostileItemTag(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }


    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(IS_HELMET)
                .add(Items.LEATHER_HELMET)
                .add(Items.IRON_HELMET)
                .add(Items.GOLDEN_HELMET)
                .add(Items.DIAMOND_HELMET)
                .add(Items.NETHERITE_HELMET)
                .add(Items.TURTLE_HELMET);
        valueLookupBuilder(IS_THROWABLE)
                .add(Items.FIRE_CHARGE)
                .add(Items.DRAGON_BREATH)
                .add(Items.WIND_CHARGE)
                .add(Items.WITHER_SKELETON_SKULL);
        valueLookupBuilder(GIVE_TARGET_EFFECT)
                .add(Items.IRON_SHOVEL)
                .add(Items.SPIDER_EYE)
                .add(Items.COBWEB);

    }
}
