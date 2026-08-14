package com.strongmonster.item;

import com.strongmonster.TheRiseOfHostile;

// Armor Material
import com.strongmonster.item.armor.DynamiteRoll;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Function;

public class TheRiseOfHostileItem {

    public static final Item DYNAMITE_ROLL = register("dynamite_roll",
            Item::new,
            new Item.Properties().humanoidArmor(DynamiteRoll.INSTANCE, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(DynamiteRoll.BASE_DURABILITY))
    );

    public static void initialize(){}

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, name));

        // Create the item instance.
        T item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
}
