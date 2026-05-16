package com.apoorv.newsprocessor.config.manager;

import com.apoorv.newsprocessor.config.model.ApplicationConfig;
import lombok.Getter;

public class ConfigManager {

    @Getter
    private static ApplicationConfig applicationConfig;

    private ConfigManager() {

    }

    public static void setApplicationConfig(ApplicationConfig applicationConfig) {
        ConfigManager.applicationConfig = applicationConfig;
    }
}
