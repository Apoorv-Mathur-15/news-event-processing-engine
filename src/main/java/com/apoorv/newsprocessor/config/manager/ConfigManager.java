package com.apoorv.newsprocessor.config.manager;

import com.apoorv.newsprocessor.config.model.ApplicationConfig;

public class ConfigManager {

    private static ApplicationConfig applicationConfig;

    private ConfigManager() {

    }

    private static ApplicationConfig getApplicationConfig() {
        return applicationConfig;
    }

    public static void setApplicationConfig(ApplicationConfig applicationConfig) {
        ConfigManager.applicationConfig = applicationConfig;
    }
}
