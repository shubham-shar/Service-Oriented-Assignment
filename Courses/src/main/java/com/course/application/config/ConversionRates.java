package com.course.application.config;

import com.course.application.exceptions.UnauthorizedException;
import com.course.application.models.enums.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
@Slf4j
@Configuration
@ConfigurationProperties(value = "conversion-rates")
public class ConversionRates {
    private Double usd;
    private Double euro;
    
    public Double getRate(String currency) {
        switch (Currency.valueOf(currency)) {
            case INR: {
                return 1.0;
            }
            case EUR: {
                return euro;
            }
            case USD: {
                return usd;
            }
            default: {
                log.error("Wrong currency passed in request : {}", currency);
                throw new UnauthorizedException("Wrong currency passed in request : " + currency);
            }
        }
    }
}
