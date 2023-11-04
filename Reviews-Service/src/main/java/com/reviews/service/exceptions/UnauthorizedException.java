package com.reviews.service.exceptions;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
