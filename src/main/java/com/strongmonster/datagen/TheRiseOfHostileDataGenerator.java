package com.strongmonster.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TheRiseOfHostileDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(TheRiseOfHostileEntityTag::new);
        pack.addProvider(TheRiseOfHostileEnUs::new);
        pack.addProvider(TheRiseOfHostileItemTag::new);
    }
}
