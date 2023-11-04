package com.course.application.exceptions;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
