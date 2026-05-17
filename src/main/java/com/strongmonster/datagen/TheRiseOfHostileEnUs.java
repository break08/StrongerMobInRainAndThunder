package com.strongmonster.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;

import com.strongmonster.TheRiseOfHostile;
import net.minecraft.util.Util;

public class TheRiseOfHostileEnUs extends FabricLanguageProvider {
    protected TheRiseOfHostileEnUs(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        // Specifying en_us is optional, as it's the default language code
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "allow_sleep"), "Allow Sleep");
        translationBuilder.add(
                Util.makeDescriptionId("gamerule", Identifier.fromNamespaceAndPath(TheRiseOfHostile.MOD_ID, "allow_sleep")),
                "Allow player to sleep at night or not"
        );
    }
}