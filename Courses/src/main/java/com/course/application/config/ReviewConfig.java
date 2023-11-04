package com.course.application.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "review-service")
public class ReviewConfig {
    private String url;
    private String reviewsByCourseId;
}
