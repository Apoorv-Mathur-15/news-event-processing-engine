package com.apoorv.newsprocessor.mapper;

import com.apoorv.newsprocessor.contants.enums.EventStatus;
import com.apoorv.newsprocessor.dto.news.ArticleDto;
import com.apoorv.newsprocessor.entity.NewsEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Component
public class NewsEventMapper {

    public NewsEvent mapArticleToEvent(ArticleDto articleDto) {
        NewsEvent newsEvent = new NewsEvent();
        newsEvent.setArticleId(articleDto.getUrl());

        if(articleDto.getSource() != null) {
            newsEvent.setSourceName(articleDto.getSource().getName());
        }
        newsEvent.setStatus(EventStatus.NEW);
        newsEvent.setTitle(articleDto.getTitle());
        newsEvent.setDescription(articleDto.getDescription());
        newsEvent.setArticleUrl(articleDto.getUrl());
        newsEvent.setRetryCount(0);
        newsEvent.setCreatedAt(LocalDateTime.now());
        newsEvent.setUpdatedAt(LocalDateTime.now());

        if(articleDto.getPublishedAt() != null) {
            newsEvent.setPublishedAt(OffsetDateTime.parse(articleDto.getPublishedAt()).toLocalDateTime());
        }
        return newsEvent;
    }
}
