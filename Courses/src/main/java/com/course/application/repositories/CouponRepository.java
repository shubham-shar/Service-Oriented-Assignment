package com.course.application.repositories;


import com.course.application.models.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
public interface CouponRepository extends JpaRepository<Coupon, Long> {}
