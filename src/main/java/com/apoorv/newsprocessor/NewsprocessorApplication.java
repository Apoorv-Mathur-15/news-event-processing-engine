package com.apoorv.newsprocessor;

import com.apoorv.newsprocessor.config.loader.ConfigLoader;
import com.apoorv.newsprocessor.config.manager.ConfigManager;
import com.apoorv.newsprocessor.config.model.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class NewsprocessorApplication {

	private static final Logger logger = LoggerFactory.getLogger(NewsprocessorApplication.class);

	public static void main(String[] args) {
		logger.info("Starting News Event Processing Engine application");

		ConfigLoader configLoader = new ConfigLoader();

		ApplicationConfig applicationConfig = configLoader.loadApplicationConfig();

		ConfigManager.setApplicationConfig(applicationConfig);

		logger.info("Configuration initialized successfully");

		logger.info( "API Pull Limit: {}", applicationConfig.getApiConfig().getPullLimit() );

		logger.info( "Retry Limit: {}", applicationConfig.getRetryConfig().getRetryLimit() );

		SpringApplication.run(NewsprocessorApplication.class, args);

		logger.info("Application started successfully");
	}

}
