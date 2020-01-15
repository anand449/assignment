package com.periscope.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class RealmBadRequestException extends Exception{
    public RealmBadRequestException() {
        super();
    }

    public RealmBadRequestException(String message) {
        super(message);
    }
}