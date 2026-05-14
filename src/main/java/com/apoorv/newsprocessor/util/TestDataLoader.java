package com.apoorv.newsprocessor.util;

import com.apoorv.newsprocessor.contants.enums.EventStatus;
import com.apoorv.newsprocessor.entity.NewsEvent;
import com.apoorv.newsprocessor.repository.NewsEventRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class TestDataLoader {

    private static final Logger logger = LoggerFactory.getLogger(TestDataLoader.class);
    private final NewsEventRepository newsEventRepository;

    public TestDataLoader(NewsEventRepository newsEventRepository) {
        this.newsEventRepository = newsEventRepository;
    }

    @PostConstruct
    public void loadTestData() {
        logger.info("Starting TestData load...");

        if(newsEventRepository.findByArticleId("TEST-ARTICLE-001").isPresent()) {
            logger.info("Test article already exists");

            return;
        }

        NewsEvent newsEvent = new NewsEvent();

        newsEvent.setArticleId("TEST-ARTICLE-001");
        newsEvent.setSourceName("BBC News");
        newsEvent.setTitle("Spring Boot Event Processing Started");
        newsEvent.setDescription("Initial test event insertion");
        newsEvent.setArticleUrl("https://example.com/article");
        newsEvent.setStatus(EventStatus.NEW);
        newsEvent.setRetryCount(0);
        newsEvent.setPublishedAt(LocalDateTime.now());
        newsEvent.setCreatedAt(LocalDateTime.now());
        newsEvent.setUpdatedAt(LocalDateTime.now());

        newsEventRepository.save(newsEvent);

        logger.info("Test article has been inserted successfully");
    }
}
