package com.reviews.service.service;

import com.reviews.service.model.Review;
import com.reviews.service.repositories.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shubham Sharma
 * @date 01/11/23
 */
@Slf4j
@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CourseService courseService;

    public ResponseEntity<Review> saveReview(Review review, Long courseId) {
        Boolean isValidCourse = courseService.isValidCourseId(courseId);
        if(isValidCourse) {
            review.setCourseId(courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(reviewRepository.save(review));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    public ResponseEntity<List<Review>> getCourseReviews(Long courseId) {
        Boolean isValidCourse = courseService.isValidCourseId(courseId);
        if(isValidCourse) {
            return ResponseEntity.status(HttpStatus.OK).body(reviewRepository.findByCourseId(courseId));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

}
