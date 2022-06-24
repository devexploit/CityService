package com.cities.mapper;

import com.cities.data.entity.DistrictEntity;
import com.cities.dto.DistrictBaseDTO;
import com.cities.dto.DistrictDTO;
import com.cities.dto.DistrictUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(implementationName = "DistrictMapperImpl", componentModel = "spring")
public interface DistrictMapper {
    DistrictEntity toDistrict(DistrictBaseDTO districtDTO);

    DistrictDTO toDistrictDTO(DistrictEntity districtEntity);

    void updateDistrictFromDTO(DistrictUpdateDTO districtUpdateDTO, @MappingTarget DistrictEntity districtEntity);
}
