package com.reviews.service.service;

import com.reviews.service.config.CourseConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Shubham Sharma
 * @date 01/11/23
 */
@Slf4j
@Service
public class CourseService {

    @Autowired
    CourseConfig courseConfig;

    @Autowired
    RestTemplate restTemplate;

    public Boolean isValidCourseId(Long courseId) {
        String getEventsUrl = courseConfig.getUrl().concat(courseConfig.getValidateCourse())
                .concat(courseId.toString()).concat("/validate");
        URI uri = null;
        try {
            uri = new URI(getEventsUrl);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
        ResponseEntity<Boolean> isValidCourse = restTemplate.getForEntity(uri, Boolean.class);
        return isValidCourse.getBody();
    }
}
