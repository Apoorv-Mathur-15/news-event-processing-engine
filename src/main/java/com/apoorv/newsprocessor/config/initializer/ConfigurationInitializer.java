package com.apoorv.newsprocessor.config.initializer;

import com.apoorv.newsprocessor.config.loader.ConfigLoader;
import com.apoorv.newsprocessor.config.manager.ConfigManager;
import com.apoorv.newsprocessor.config.model.ApplicationConfig;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationInitializer.class);

    @PostConstruct
    public void initializeConfiguration() {
        logger.info("Initializing configuration...");
        ConfigLoader configLoader = new ConfigLoader();

        ApplicationConfig applicationConfig = configLoader.loadApplicationConfig();
        ConfigManager.setApplicationConfig(applicationConfig);

        logger.info("Configuration loaded!");

        logger.info("Configured API Pull Limit: {}", applicationConfig.getApiConfig().getPullLimit());

        logger.info("Configured Retry Limit: {}",applicationConfig.getRetryConfig().getRetryLimit());

    }
}
