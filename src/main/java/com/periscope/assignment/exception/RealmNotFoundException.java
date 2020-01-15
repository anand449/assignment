package com.periscope.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class RealmNotFoundException extends Exception {
    public RealmNotFoundException() {
        super();
    }

    public RealmNotFoundException(String message) {
        super(message);
    }
}
