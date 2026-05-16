package com.apoorv.newsprocessor.util;

import com.apoorv.newsprocessor.dto.news.NewsApiResponseDto;
import com.apoorv.newsprocessor.service.NewsApiService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApiTestRunner {

    private static final Logger logger = LoggerFactory.getLogger(ApiTestRunner.class);

    private final NewsApiService newsApiService;

    public ApiTestRunner(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }

    @PostConstruct
    public void testApiIntegration() {

        logger.info("Starting News API Test Runner");

        NewsApiResponseDto response = newsApiService.fetchTopHeadlines();

        if(response != null && response.getArticles() != null) {
            logger.info("Total articles fetched: {}", response.getArticles().size());
        }
    }
}
