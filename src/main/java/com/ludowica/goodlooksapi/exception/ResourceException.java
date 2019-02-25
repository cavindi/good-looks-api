package com.ludowica.goodlooksapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceException  extends RuntimeException {

    public ResourceException() {
        super();
    }

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}

