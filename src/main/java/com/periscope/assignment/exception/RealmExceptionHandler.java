package com.periscope.assignment.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RealmExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        Error error =new Error(ex.getMessage());
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RealmNotFoundException.class)
    public final ResponseEntity<Object> handleUserException(RealmNotFoundException ex, WebRequest request) throws Exception {
        Error error = new Error(ex.getMessage());
        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public final ResponseEntity<Object> handleUserException(InvalidArgumentException ex, WebRequest request) throws Exception {
        Error error = new Error(ex.getMessage());
        return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RealmBadRequestException.class)
    public final ResponseEntity<Object> handleUserException(RealmBadRequestException ex, WebRequest request) throws Exception {
        Error error = new Error(ex.getMessage());
        return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
    }
}