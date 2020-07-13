package com.examples.springbootfleetactivity.advice;

import com.examples.springbootfleetactivity.utils.ErrorDetails;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Controller Advice that contains the exception handlers
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionAdvice {

    /**
     * Not generally a good practice to cover general exceptions
     * This serves as an example for future specific exception handling methods
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                                                    ex.getMessage(),
                                                    request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
