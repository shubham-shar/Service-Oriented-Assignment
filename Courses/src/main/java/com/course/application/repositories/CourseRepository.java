package com.course.application.repositories;

import com.course.application.models.entities.Course;
import com.course.application.models.enums.PricingStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findCourseByName(String name);
    @Query(value = Queries.FETCH_COURSE_BASED_ON_STRATEGY)
    Set<Course> findCoursesByStrategy(@Param("type") PricingStrategy strategy);
    
    class Queries {
    
        private Queries() {}
    
        public static final String FETCH_COURSE_BASED_ON_STRATEGY = "select c from Course c "
                + "join Price p on c.id = p.course.id "
                + "join PricingStrategy ps on ps.price.id = p.id "
                + "where ps.type = :type ";
    }
}
