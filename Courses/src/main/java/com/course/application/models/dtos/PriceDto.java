package com.course.application.models.dtos;

import com.course.application.models.enums.Currency;
import com.course.application.models.enums.PricingStrategy;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceDto {
    private Double totalAmount;
    
    private Double baseAmount;
    
    private TaxComponentDto taxes;
    
    private Double conversionMultiple;
    
    private Double discount;
    
    private boolean isCouponApplied = false;
    
    private String appliedCoupon = null;
    
    private Currency currency = Currency.INR;
    
    private PricingStrategy pricingStrategy;
    
    private int durationOfMonths;
    
}
