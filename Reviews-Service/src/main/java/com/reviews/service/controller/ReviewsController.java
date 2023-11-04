package com.reviews.service.controller;

import com.reviews.service.model.Review;
import com.reviews.service.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    @Autowired
    ReviewService reviewService;

    /**
     * Creates a review for a course.
     *
     * @param courseId The id of the course.
     * @return The created review or 404, if courseId is not found.
     */
    @RequestMapping(value = "/reviews/courses/{courseId}", method = RequestMethod.POST)
    public ResponseEntity<Review> createReviewForCourse(@PathVariable("courseId") Long courseId,
                                                        @RequestBody Review review) {
        return reviewService.saveReview(review, courseId);
    }

    /**
     * Lists reviews by course.
     *
     * @param courseId The id of the course.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/courses/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> listReviewsForCourse(@PathVariable("courseId") Long courseId) {
        return reviewService.getCourseReviews(courseId);
    }
}