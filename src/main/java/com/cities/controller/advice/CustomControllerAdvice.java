package com.cities.controller.advice;

import com.cities.model.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e, WebRequest webRequest) {
        var responseStatus = e.getClass().getAnnotation(ResponseStatus.class);

        var exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError(e.getMessage());
        exceptionResponse.setPath(webRequest.getDescription(false).substring(4));
        exceptionResponse.setStatus(responseStatus.value().value());
        exceptionResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(exceptionResponse, responseStatus.value());
    }
}