package com.reviews.service.repositories;

import com.reviews.service.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Shubham Sharma
 * @date 01/11/23
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByReviewId( Long reviewId );

  Comment findByContentAndUsername( String content, String username );
}
