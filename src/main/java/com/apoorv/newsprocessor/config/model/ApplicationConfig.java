package com.apoorv.newsprocessor.config.model;

import com.apoorv.newsprocessor.config.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationConfig {
    private DatabaseConfig databaseConfig;

    private ApiConfig apiConfig;

    private RetryConfig retryConfig;

    private ThreadPoolConfig threadPoolConfig;

    private SchedulerConfig schedulerConfig;
}
