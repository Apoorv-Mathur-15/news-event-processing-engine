package com.apoorv.newsprocessor.repository;

import com.apoorv.newsprocessor.contants.enums.EventStatus;
import com.apoorv.newsprocessor.entity.NewsEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsEventRepository extends JpaRepository<NewsEvent, Integer> {

    Optional<NewsEvent> findByArticleId(String articleId);

    List<NewsEvent> findByStatus(EventStatus status);

    //List<NewsEvent> findByStatusAndRetryCountLessThan(EventStatus status, Integer retryCount);
}
