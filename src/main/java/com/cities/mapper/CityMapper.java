package com.cities.mapper;

import com.cities.data.entity.CityEntity;
import com.cities.dto.CityDTO;
import com.cities.dto.CitySaveDTO;
import org.mapstruct.Mapper;

@Mapper(implementationName = "CityMapperImpl", componentModel = "spring")
public interface CityMapper {
    CityEntity toCity(CityDTO cityDTO);

    CityDTO toCityDTO(CityEntity cityEntity);

    CityEntity toCity(CitySaveDTO citySaveDTO);
}
