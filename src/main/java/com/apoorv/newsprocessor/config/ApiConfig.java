package com.apoorv.newsprocessor.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiConfig {

    private String baseUrl;

    private String apiKey;

    private Integer pullLimit;

    private Integer connectionTimeout;
}
