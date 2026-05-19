package com.strongmonster.item.armor;

import java.util.Map;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import com.strongmonster.TheRiseOfHostile;

public class DynamiteRoll {
    public static final int BASE_DURABILITY = 500;
    public static final ResourceKey<EquipmentAsset> DYNAMITE_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "dynamite_roll_key"));
    public static final TagKey<Item> REPAIRS_DYNAMITE = TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "repairs_dynamite_roll"));
    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    ArmorType.CHESTPLATE, 8
            ),
            5,
            SoundEvents.ARMOR_EQUIP_CHAIN,
            0.0F,
            0.0F,
            REPAIRS_DYNAMITE,
            DYNAMITE_MATERIAL_KEY
    );
}