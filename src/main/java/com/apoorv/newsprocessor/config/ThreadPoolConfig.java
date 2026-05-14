package com.apoorv.newsprocessor.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadPoolConfig {
    private Integer corePoolSize;

    private Integer maxPoolSize;

    private Integer queueCapacity;
}
