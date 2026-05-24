package com.strongmonster.client.config;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.network.chat.Component;

@Config(name = "the_rise_of_hostile")
public class TheRiseOfHostileConfig implements ConfigData {
    public static TheRiseOfHostileConfig get() {
        return AutoConfig.getConfigHolder(TheRiseOfHostileConfig.class).getConfig();
    }

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("The Rise Of Hostile Config"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        //General
        ConfigCategory general = builder.getOrCreateCategory(
                Component.literal("Render")
        );

        return builder.build();
    }
}
