package com.reviews.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "course-service")
public class CourseConfig {
    private String url;
    private String validateCourse;
}
