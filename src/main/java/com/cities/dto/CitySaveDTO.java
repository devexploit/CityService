package com.cities.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class CitySaveDTO extends CityBaseDTO {
    private List<DistrictUpdateDTO> districtList;
}
