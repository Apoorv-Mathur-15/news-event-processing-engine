package com.apoorv.newsprocessor.service;

import com.apoorv.newsprocessor.config.RetryConfig;
import com.apoorv.newsprocessor.config.manager.ConfigManager;
import com.apoorv.newsprocessor.contants.enums.EventStatus;
import com.apoorv.newsprocessor.entity.NewsEvent;
import com.apoorv.newsprocessor.repository.NewsEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(EventProcessingService.class);

    private final NewsEventRepository newsEventRepository;

    private final ThreadPoolTaskExecutor newsEventTaskExecutor;

    public EventProcessingService(NewsEventRepository newsEventRepository, ThreadPoolTaskExecutor newsEventTaskExecutor) {
        this.newsEventRepository = newsEventRepository;
        this.newsEventTaskExecutor = newsEventTaskExecutor;
    }

    public void processNewEvents() {
        logger.info("Fetching new news event for processing");
        List<NewsEvent> newsEvents = newsEventRepository.findByStatus(EventStatus.NEW);
        logger.info("Found {} new news events for processing", newsEvents.size());

        for (NewsEvent newsEvent : newsEvents) {
            newsEventTaskExecutor.execute(() -> processEvent(newsEvent));
        }
    }

    private void processEvent(NewsEvent newsEvent) {
        try {
            logger.info("Processing news event {} on thread: {}", newsEvent.getArticleId(), Thread.currentThread().getName());

            newsEvent.setStatus(EventStatus.PROCESSING);
            newsEvent.setUpdatedAt(LocalDateTime.now());
            newsEventRepository.save(newsEvent);

            Thread.sleep(3000);

            if(Math.random() > 0.3) {
                throw  new RuntimeException("Stimulated processing failure");
            }
            newsEvent.setStatus(EventStatus.SUCCESS);
            newsEvent.setUpdatedAt(LocalDateTime.now());
            newsEventRepository.save(newsEvent);

            logger.info("Successfully processed event: {}", newsEvent.getArticleId());
        }
        catch (Exception exception) {
            logger.error("Error while processing news event: {}", newsEvent.getArticleId(), exception);
            int updatedRetryCount = newsEvent.getRetryCount() + 1;
            newsEvent.setRetryCount(updatedRetryCount);
            newsEvent.setFailureReason(exception.getMessage());
            RetryConfig retryConfig = ConfigManager.getApplicationConfig().getRetryConfig();
            if(updatedRetryCount >= retryConfig.getRetryLimit()) {
                newsEvent.setStatus(EventStatus.FAILED);
                logger.error("Retry limit exceeded for event: {}",newsEvent.getArticleId());
            }
            else {
                newsEvent.setStatus(EventStatus.RETRY_PENDING);
                LocalDateTime nextRetryTime = LocalDateTime.now().plusSeconds(retryConfig.getRetryIntervalSeconds());
                newsEvent.setNextRetryTime(nextRetryTime);
                logger.error("Event moved to RETRY_PENDING with nextRetryTime={} for event={}",nextRetryTime, newsEvent.getArticleId());
            }
            newsEvent.setUpdatedAt(LocalDateTime.now());
            newsEventRepository.save(newsEvent);

        }
    }

    public void processRetryEvents() {
        logger.info("Fetching retry events for processing");

        //List<NewsEvent> retryEvents = newsEventRepository.findByStatus(EventStatus.RETRY_PENDING);
        List<NewsEvent> retryEvents = newsEventRepository.findByStatusAndNextRetryTimeBefore(EventStatus.RETRY_PENDING, LocalDateTime.now());

        logger.info("Found {} retry events for processing", retryEvents.size());
        for (NewsEvent retryEvent : retryEvents) {
            newsEventTaskExecutor.execute(() -> processEvent(retryEvent));
        }
    }
}
