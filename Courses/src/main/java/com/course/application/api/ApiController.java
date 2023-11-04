package com.course.application.api;

import com.course.application.models.dtos.CourseDto;
import com.course.application.models.enums.PricingStrategy;
import com.course.application.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
@Slf4j
@RestController
@RequestMapping("/courses")
public class ApiController {
    
    @Autowired
    private CourseService courseService;

    @PostMapping
    public CourseDto createCourse(@RequestBody CourseDto course) {
        log.info("Creating course with Name: {}", course.getName());
        return courseService.addCourse(course);
    }

    @GetMapping
    public Set<CourseDto> getAllCourses(@RequestParam String currency) {
        log.info("Fetching all the course prices");
        return courseService.getCourses(currency);
    }
    
    @GetMapping("/{id}")
    public CourseDto getCourseById(@PathVariable("id") Long courseId, @RequestParam String currency) {
        log.info("Fetching the course {} price", courseId);
        return courseService.fetchCourseById(courseId, currency);
    }
    
    @GetMapping("/strategy")
    public Set<CourseDto> getAllCourseBasedOnStrategy(@RequestParam("type") PricingStrategy strategy,
            @RequestParam String currency) {
        log.info("Fetching all the course based on strategy {} with currency {}", strategy, currency);
        return courseService.getCoursesByStrategy(strategy, currency);
    }

    @GetMapping("/{id}/validate")
    public ResponseEntity<Boolean> validateCourseId(@PathVariable("id") Long courseId) {
        log.info("validating the course with id {}", courseId);
        return ResponseEntity.ok(courseService.validateCourse(courseId));
    }
}
