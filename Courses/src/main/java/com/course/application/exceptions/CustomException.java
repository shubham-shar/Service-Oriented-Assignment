package com.course.application.exceptions;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
