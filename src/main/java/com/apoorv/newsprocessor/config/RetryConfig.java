package com.apoorv.newsprocessor.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetryConfig {
    private Integer retryLimit;

    private Integer retryIntervalSeconds;
}
