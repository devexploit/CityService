package com.cities.model;

import lombok.Data;

@Data
public class ExceptionResponse {
    private long timestamp;
    private int status;
    private String error;
    private String path;
}
