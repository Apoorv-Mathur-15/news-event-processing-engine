package com.apoorv.newsprocessor.service;

import com.apoorv.newsprocessor.config.ApiConfig;
import com.apoorv.newsprocessor.config.manager.ConfigManager;
import com.apoorv.newsprocessor.dto.news.NewsApiResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsApiService {

    private static final Logger logger = LoggerFactory.getLogger(NewsApiService.class);

    private final RestTemplate restTemplate;

    public NewsApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public NewsApiResponseDto fetchTopHeadlines() {
        try {
            logger.info("Fetching news articles from News API");

            ApiConfig apiConfig = ConfigManager.getApplicationConfig().getApiConfig();

            String apiUrl = apiConfig.getBaseUrl()
                    + "?country=us&pagesize="
                    + apiConfig.getPullLimit()
                    + "&apiKey="
                    + apiConfig.getApiKey();

            logger.info("Calling API URL: {}", apiUrl);

            NewsApiResponseDto  response = restTemplate.getForObject(apiUrl, NewsApiResponseDto.class);

            logger.info("Successfully fetched news articles");

            return response;
        }
        catch (Exception e) {
            logger.error("Error while fetching news articles from News API", e);

            throw new RuntimeException("News API could not be fetched");
        }
    }
}
