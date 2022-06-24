package com.cities.dto;

import lombok.Data;

@Data
public abstract class CityBaseDTO {
    protected String cityName;
    protected String cityCode;
}
