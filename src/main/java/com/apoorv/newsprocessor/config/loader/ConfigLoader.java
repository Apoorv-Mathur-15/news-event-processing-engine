package com.apoorv.newsprocessor.config.loader;

import com.apoorv.newsprocessor.config.model.ApplicationConfig;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ConfigLoader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    public ApplicationConfig loadApplicationConfig() {

        try {
            logger.info("Loading configuration from config/config.xml");

            XmlMapper xmlMapper = new XmlMapper();

            File configFile = new File("config/config.xml");

            ApplicationConfig applicationConfig = xmlMapper.readValue(configFile, ApplicationConfig.class);

            logger.info("Successfully loaded configuration from config/config.xml");

            return applicationConfig;
        }
        catch (Exception error) {
            logger.error("Failed to load configuration from config/config.xml", error);

            throw new RuntimeException("Configuration loading failed");
        }
    }
}
