package com.strongmonster.client.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.strongmonster.client.config.TheRiseOfHostileConfig;

public class AddToModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return TheRiseOfHostileConfig::create;
    }
}
