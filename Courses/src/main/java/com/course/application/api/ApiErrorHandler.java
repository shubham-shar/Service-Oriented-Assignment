package com.course.application.api;

import com.course.application.exceptions.CustomException;
import com.course.application.exceptions.EntityNotFoundException;
import com.course.application.exceptions.UnauthorizedException;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
@Slf4j
@RestControllerAdvice
public class ApiErrorHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ ConstraintViolationException.class, DataIntegrityViolationException.class })
    public ErrorResponse handleBadRequest(Throwable throwable) {
        log.error("Bad Request, Something went wrong, {}", throwable.getMessage(), throwable);
        return ErrorResponse.builder().message(throwable.getMessage()).build();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ UnauthorizedException.class })
    public ErrorResponse unauthorizedException(Throwable throwable) {
        log.error("unauthorizedException exception found, {}", throwable.getMessage(), throwable);
        return ErrorResponse.builder().message(throwable.getMessage()).build();
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ EntityNotFoundException.class })
    public ErrorResponse entityNotFound(Throwable throwable) {
        log.error("Entity not found exception, {}", throwable.getMessage(), throwable);
        return ErrorResponse.builder().message(throwable.getMessage()).build();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ErrorResponse illegalArugmentException(Throwable throwable) {
        log.error("Wrong param passed in request, {}", throwable.getMessage(), throwable);
        return ErrorResponse.builder().message("Wrong param passed in request").build();
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ NullPointerException.class })
    public ErrorResponse handleNotFound(Throwable throwable) {
        log.error("null pointer exception, {}", throwable.getMessage(), throwable);
        return ErrorResponse.builder().message("Something went wrong").build();
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ IOException.class })
    protected ErrorResponse ioException(Throwable throwable) {
        log.error("Some IO exception occured {}", throwable.getMessage(), throwable);
        return ErrorResponse.builder().message(throwable.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ CustomException.class})
    public ErrorResponse handleCustomException(Throwable throwable) {
        log.error("Bad Request, Something went wrong, {}", throwable.getMessage(), throwable);
        return ErrorResponse.builder().message(throwable.getMessage()).build();
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class})
    public ErrorResponse exception(Throwable throwable){
        log.error("Something went wrong {}", throwable.getMessage(), throwable);
        return ErrorResponse.builder().message(throwable.getMessage()).build();
    }
    
    
    @Builder
    @Getter
    private static class ErrorResponse {
        private final String message;
    }
}
