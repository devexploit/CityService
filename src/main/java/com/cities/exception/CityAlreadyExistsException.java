package com.cities.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CityAlreadyExistsException extends RuntimeException {
    public CityAlreadyExistsException() {
        super();
    }

    public CityAlreadyExistsException(String message) {
        super(message);
    }

    public CityAlreadyExistsException(String message, Throwable e) {
        super(message, e);
    }
}
