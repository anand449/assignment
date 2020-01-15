package com.periscope.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends Exception {

    public InvalidArgumentException() {}
    public InvalidArgumentException(String message) {
        super(message);
    }

}