package com.apoorv.newsprocessor.scheduler;

import com.apoorv.newsprocessor.service.NewsIngestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewsPollingScheduler {
    private static final Logger logger = LoggerFactory.getLogger(NewsPollingScheduler.class);

    private final NewsIngestionService newsIngestionService;

    public NewsPollingScheduler(NewsIngestionService newsIngestionService) {
        this.newsIngestionService = newsIngestionService;
    }

    @Scheduled(
            fixedDelayString =
                    "#{T(com.apoorv.newsprocessor.config.manager.ConfigManager)"
                            + ".getApplicationConfig()"
                            + ".getSchedulerConfig()"
                            + ".getPollingIntervalSeconds() * 1000}"
    )
    public void pollNewsApi() {
        try {
            logger.info("Starting scheduled polling of news event");
            newsIngestionService.ingestNewsEvent();
            logger.info("Successfully ingested scheduled news event");
        }
        catch (Exception exception) {
            logger.error("Error while polling news api", exception);
        }
    }
}
