package com.strongmonster.datagen.lang;

import com.strongmonster.item.TheRiseOfHostileItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;

import com.strongmonster.TheRiseOfHostile;
import net.minecraft.util.Util;

public class TheRiseOfHostileEnUs extends FabricLanguageProvider {
    public TheRiseOfHostileEnUs(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        // Specifying en_us is optional, as it's the default language code
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {
        // Game Rule
        translationBuilder.add(Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "allow_sleep"), "Allow Sleep");
        translationBuilder.add(
                Util.makeDescriptionId("gamerule", Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "allow_sleep")),
                "Allow player to sleep at night or not"
        );
        translationBuilder.add(Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "bonus_drop"), "Bonus Drop");
        translationBuilder.add(
                Util.makeDescriptionId("gamerule", Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "bonus_drop")),
                "Stronger Entity will drop bonus item when be killed by player"
        );
        translationBuilder.add(Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "all_diamond_gear_drop"), "Drop Diamond Gear");
        translationBuilder.add(
                Util.makeDescriptionId("gamerule", Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "all_diamond_gear_drop")),
                "Entity with Diamond gear will drop their Diamond stuff, suggest to turn this off if you don't want an OP game"
        );

        // Item
        translationBuilder.add(TheRiseOfHostileItem.DYNAMITE_ROLL, "Dynamite Roll");

        // Effect
        translationBuilder.add("effect.the_rise_of_hostile.curse_of_undead", "Curse Of Undead");

        // Config
        translationBuilder.add("title.the_rise_of_hostile.setting", "The Rise Of Hostile Config");

    }
}