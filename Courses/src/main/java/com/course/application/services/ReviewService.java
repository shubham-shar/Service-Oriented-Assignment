package com.course.application.services;

import com.course.application.config.ReviewConfig;
import com.course.application.exceptions.CustomException;
import com.course.application.models.dtos.Reviews;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ReviewService {

    @Autowired
    ReviewConfig reviewConfig;

    @Autowired
    RestTemplate restTemplate;

    public Set<Reviews> getCourseReviews(Long courseId) {
        String getEventsUrl = reviewConfig.getUrl().concat(reviewConfig.getReviewsByCourseId())
                .concat(courseId.toString());
        URI uri = null;
        try {
            uri = new URI(getEventsUrl);
            ResponseEntity<Reviews[]> reviews = restTemplate.getForEntity(uri, Reviews[].class);
            return Optional.ofNullable(reviews).map(HttpEntity::getBody).map(Set::of).orElse(new HashSet<>());
        } catch (URISyntaxException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            log.error("Unable to fetch reviews, ", e);
            return new HashSet<>();
        }
    }
}
