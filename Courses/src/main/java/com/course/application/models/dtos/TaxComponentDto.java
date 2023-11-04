package com.course.application.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxComponentDto {
    private Double gstAmount;
}
