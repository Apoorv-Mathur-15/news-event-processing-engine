package com.apoorv.newsprocessor.dto.news;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewsApiResponseDto {

    private String status;
    private Integer totalResults;
    private List<ArticleDto> articles;
}
