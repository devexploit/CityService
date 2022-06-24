package com.cities.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DistrictDTO extends DistrictBaseDTO{
    private long districtId;
}
