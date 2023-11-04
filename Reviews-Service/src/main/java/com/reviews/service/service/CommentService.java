package com.reviews.service.service;


import com.reviews.service.model.Comment;
import com.reviews.service.model.Review;
import com.reviews.service.repositories.CommentRepository;
import com.reviews.service.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Shubham Sharma
 * @date 4/9/19
 */
@Component
public class CommentService {

  @Autowired
  CommentRepository commentRepository;

  @Autowired
  ReviewRepository reviewRepository;

  public ResponseEntity<Comment> saveComment(Comment comment, Long reviewId ) {
    Optional<Review> optionalReview = reviewRepository.findById(reviewId);
    if ( optionalReview.isPresent() ){
      comment.setReview(optionalReview.get());
      return ResponseEntity.status(HttpStatus.OK).body(commentRepository.save(comment));
    } else {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

    }
  }

  public ResponseEntity<List<Comment>> getCommentsForReview(Long reviewId ) {
    Optional<Review> optionalReview = reviewRepository.findById(reviewId);
    if(optionalReview.isPresent()){
      return ResponseEntity.status(HttpStatus.OK).body(commentRepository.findByReviewId(reviewId));
    } else {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
    }
  }
}
