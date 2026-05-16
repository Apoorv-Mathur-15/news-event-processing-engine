package com.apoorv.newsprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewsprocessorApplication {

	private static final Logger logger =
			LoggerFactory.getLogger(NewsprocessorApplication.class);

	public static void main(String[] args) {

		logger.info("Starting News Event Processing Engine application");

		SpringApplication.run(NewsprocessorApplication.class, args);

		logger.info("Application started successfully");
	}
}