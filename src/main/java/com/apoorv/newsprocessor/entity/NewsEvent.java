package com.apoorv.newsprocessor.entity;

import com.apoorv.newsprocessor.contants.enums.EventStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "news_event")
public class NewsEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_id", unique = true)
    private String articleId;

    @Column(name = "source_name")
    private String sourceName;

    @Column(length = 1000)
    private String title;

    @Column(length = 5000)
    private String description;

    @Column(name = "article_url", length = 2000)
    private String articleUrl;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "failure_reason", length = 3000)
    private String failureReason;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
