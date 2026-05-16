package com.apoorv.newsprocessor.dto.news;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto {

    private SourceDto source;

    private String author;
    private String title;
    private String description;
    private String url;
    private String publishedAt;
}
