package com.course.application.repositories;

import com.course.application.models.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
public interface PriceRepository extends JpaRepository<Price, Long> {
    Set<Price> findByCourseId(Long courseId);
}
