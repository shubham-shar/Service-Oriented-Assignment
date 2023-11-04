package com.course.application.services;

import com.course.application.config.ConversionRates;
import com.course.application.exceptions.EntityNotFoundException;
import com.course.application.exceptions.UnauthorizedException;
import com.course.application.models.dtos.CourseDto;
import com.course.application.models.dtos.PriceDto;
import com.course.application.models.dtos.TaxComponentDto;
import com.course.application.models.entities.Course;
import com.course.application.models.entities.Price;
import com.course.application.models.entities.TaxComponent;
import com.course.application.models.enums.Currency;
import com.course.application.models.enums.PricingStrategy;
import com.course.application.repositories.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
@Slf4j
@Service
public class CourseService {
    
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ReviewService reviewService;

    @Autowired
    ConversionRates conversionRates;
    
    public Set<CourseDto> getCourses(String currency) {
        Double conversionMultiple = conversionRates.getRate(currency.toUpperCase());
        
        return courseRepository.findAll().stream()
                               .map(course -> getCourseDtoFromCourseEntity(course, currency, conversionMultiple))
                               .collect(Collectors.toSet());
    }
    
    public CourseDto fetchCourseById(Long courseId, String currency) {
        Course course= courseRepository.findById(courseId).orElseThrow(() -> {
                                    log.error("Course with id {} not found", courseId);
                                    throw new EntityNotFoundException("Course with id " + courseId + " not found");
                                });
        Double conversionMultiple = conversionRates.getRate(currency.toUpperCase());
        return getCourseDtoFromCourseEntity(course, currency, conversionMultiple);
        
    }
    
    public Set<CourseDto> getCoursesByStrategy(PricingStrategy strategy, String currency) {
        Set<Course> courses = courseRepository.findCoursesByStrategy(strategy);
        Double conversionMultiple = conversionRates.getRate(currency.toUpperCase());
        return courses.stream()
                      .map(course -> getCourseDtoFromCourseEntityBasedOnStrategy(course, currency, conversionMultiple,
                                                                                 strategy))
                      .collect(Collectors.toSet());
    }
    
    private PriceDto convertPriceEntityToPriceDto(Price price, String currency,
                                                  Double conversionMultiple) {
        Double totalAmount = getTotalAmount(price, price.getTaxes());
        return PriceDto.builder()
                       .totalAmount(totalAmount)
                       .baseAmount(getBaseAmount(price, conversionMultiple))
                       .pricingStrategy(price.getStrategy().getType())
                       .durationOfMonths(price.getStrategy().getDurationInMonths())
                       .currency(Currency.valueOf(currency.toUpperCase()))
                       .conversionMultiple(conversionMultiple)
                       .taxes(getTaxDtoFromEntity(price.getTaxes(), conversionMultiple))
                       .build();
    }
    
    private double getBaseAmount(Price price, Double conversionMultiple) {
        PricingStrategy pricingStrategy = price.getStrategy().getType();
        /*
            Assumption - If Pricing Strategy is subscription based then base price should be per month bases
            UI should show prices as per month only.
         */
        if(PricingStrategy.SUBSCRIPTION.equals(pricingStrategy)) {
            return (price.getBaseAmount() / conversionMultiple) / price.getStrategy().getDurationInMonths();
        }
        return price.getBaseAmount() / conversionMultiple;
    }
    
    private CourseDto getCourseDtoFromCourseEntity(Course course, String currency, Double conversionMultiple) {
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .reviews(reviewService.getCourseReviews(course.getId()))
                .prices(getPricesDto(course, currency, conversionMultiple))
                .build();
    }
    
    private CourseDto getCourseDtoFromCourseEntityBasedOnStrategy(Course course, String currency,
            Double conversionMultiple, PricingStrategy strategy) {
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .reviews(reviewService.getCourseReviews(course.getId()))
                .prices(getPricesDtoByStrategy(course, currency, conversionMultiple, strategy))
                .build();
    }
    
    private Set<PriceDto> getPricesDto(Course course, String currency, Double conversionMultiple) {
        return course.getPrices().stream()
                     .map(price -> convertPriceEntityToPriceDto(price, currency, conversionMultiple))
                     .collect(Collectors.toSet());
    }
    
    private Set<PriceDto> getPricesDtoByStrategy(Course course, String currency, Double conversionMultiple,
            PricingStrategy pricingStrategy) {
        return course.getPrices().stream()
                     .filter(price -> pricingStrategy.equals(price.getStrategy().getType()))
                     .map(price -> convertPriceEntityToPriceDto(price, currency, conversionMultiple))
                     .collect(Collectors.toSet());
    }
    
    private Double getTotalAmount(Price price, TaxComponent taxes) {
        return Optional.ofNullable(price).map(Price::getBaseAmount).orElse(0.0)
                + Optional.ofNullable(taxes).map(TaxComponent::getGstAmount).orElse(0.0);
    }
    
    private TaxComponentDto getTaxDtoFromEntity(TaxComponent taxes, Double conversionMultiple) {
        return TaxComponentDto.builder()
                              .gstAmount(taxes.getGstAmount() / conversionMultiple)
                              .build();
    }

    public Boolean validateCourse(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        return course.isPresent();
    }

    public CourseDto addCourse(CourseDto course) {
        String name = course.getName();
        if(Objects.isNull(name)) {
            throw new UnauthorizedException("Name field should not be null");
        }
        Course courseByName = courseRepository.findCourseByName(name);
        if(Objects.isNull(courseByName)) {
            Course savedCourse = courseRepository.save(getCourseFromCourseDto(course));
            return getCourseDtoFromCourseEntity(savedCourse);
        }
        throw new UnauthorizedException("Course with name " + name + " already exists");
    }

    private Course getCourseFromCourseDto(CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        return course;
    }

    private CourseDto getCourseDtoFromCourseEntity(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .build();
    }
}
