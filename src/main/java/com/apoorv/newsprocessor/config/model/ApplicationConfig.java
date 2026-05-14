package com.apoorv.newsprocessor.config.model;

import com.apoorv.newsprocessor.config.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "applicationConfig")
public class ApplicationConfig {

    @JacksonXmlProperty(localName = "database")
    private DatabaseConfig databaseConfig;

    @JacksonXmlProperty(localName = "api")
    private ApiConfig apiConfig;

    @JacksonXmlProperty(localName = "retry")
    private RetryConfig retryConfig;

    @JacksonXmlProperty(localName = "threadPool")
    private ThreadPoolConfig threadPoolConfig;

    @JacksonXmlProperty(localName = "scheduler")
    private SchedulerConfig schedulerConfig;
}
