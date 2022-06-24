package com.cities.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class DistrictNotFoundException extends RuntimeException {
    public DistrictNotFoundException() {
        super();
    }

    public DistrictNotFoundException(String message) {
        super(message);
    }

    public DistrictNotFoundException(String message, Throwable e) {
        super(message, e);
    }
}
