package com.reviews.service.repositories;

import com.reviews.service.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Shubham Sharma
 * @date 01/11/23
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findByCourseId( Long courseId );

  Review findByContentAndUsername( String content, String username );
}
