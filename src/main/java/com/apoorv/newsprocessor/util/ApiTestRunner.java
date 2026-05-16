package com.apoorv.newsprocessor.util;

import com.apoorv.newsprocessor.dto.news.NewsApiResponseDto;
import com.apoorv.newsprocessor.service.NewsIngestionService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApiTestRunner {

    private static final Logger logger = LoggerFactory.getLogger(ApiTestRunner.class);

    private final NewsIngestionService newsIngestionService;

    public ApiTestRunner(NewsIngestionService newsIngestionService) {
        this.newsIngestionService = newsIngestionService;
    }

    @PostConstruct
    public void testApiIntegration() {

        logger.info("News API injestion tes is starting");

        newsIngestionService.ingestNewsEvent();

        logger.info("News API injestion tes is completed");
    }
}
