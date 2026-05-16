package com.apoorv.newsprocessor.service;

import com.apoorv.newsprocessor.dto.news.ArticleDto;
import com.apoorv.newsprocessor.dto.news.NewsApiResponseDto;
import com.apoorv.newsprocessor.entity.NewsEvent;
import com.apoorv.newsprocessor.mapper.NewsEventMapper;
import com.apoorv.newsprocessor.repository.NewsEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsIngestionService {

    private static final Logger logger = LoggerFactory.getLogger(NewsIngestionService.class);

    private final NewsApiService newsApiService;

    private final NewsEventRepository newsEventRepository;

    private final NewsEventMapper newsEventMapper;

    public NewsIngestionService(NewsApiService newsApiService, NewsEventRepository newsEventRepository, NewsEventMapper newsEventMapper) {
        this.newsApiService = newsApiService;
        this.newsEventRepository = newsEventRepository;
        this.newsEventMapper = newsEventMapper;
    }

    public void ingestNewsEvent() {
        logger.debug("Starting new ingesting of news event");

        NewsApiResponseDto response = newsApiService.fetchTopHeadlines();

        if(response == null || response.getArticles() == null || response.getArticles().isEmpty()) {
            logger.warn("No articles found from the API");

            return;
        }

        List<NewsEvent> newsEvents = new ArrayList<>();

        for(ArticleDto articleDto : response.getArticles()) {
            NewsEvent newsEvent = newsEventMapper.mapArticleToEvent(articleDto);
            newsEvents.add(newsEvent);
        }
        newsEventRepository.saveAll(newsEvents);

        logger.info("Successfully ingested news event", newsEvents.size());
    }
}
