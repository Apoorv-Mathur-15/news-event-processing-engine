package com.apoorv.newsprocessor.config;

import com.apoorv.newsprocessor.config.manager.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {
    private static final Logger logger = LoggerFactory.getLogger(AsyncConfig.class);

    @Bean(name = "newsEventTaskExecutor")
    public ThreadPoolTaskExecutor newsEventTaskExecutor() {
        logger.info("Initializing thread pool task executor");

        ThreadPoolConfig threadPoolConfig = ConfigManager.getApplicationConfig().getThreadPoolConfig();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolConfig.getCorePoolSize());
        executor.setMaxPoolSize(threadPoolConfig.getMaxPoolSize());
        executor.setQueueCapacity(threadPoolConfig.getQueueCapacity());

        executor.setThreadNamePrefix("news-event-worker-");
        executor.initialize();
        logger.info("Thread pool initialized with corePoolSize={}, maxPoolSize={}, queueCapacity={}",
                threadPoolConfig.getCorePoolSize(), threadPoolConfig.getMaxPoolSize(), threadPoolConfig.getQueueCapacity());

        return executor;
    }
}
