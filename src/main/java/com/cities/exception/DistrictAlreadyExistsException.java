package com.cities.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DistrictAlreadyExistsException extends RuntimeException {
    public DistrictAlreadyExistsException() {
        super();
    }

    public DistrictAlreadyExistsException(String message) {
        super(message);
    }

    public DistrictAlreadyExistsException(String message, Throwable e) {
        super(message, e);
    }
}
