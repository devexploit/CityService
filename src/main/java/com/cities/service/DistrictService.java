package com.cities.service;

import com.cities.data.entity.DistrictEntity;
import com.cities.data.repository.DistrictRepository;
import com.cities.dto.DistrictDTO;
import com.cities.dto.DistrictUpdateDTO;
import com.cities.exception.DistrictAlreadyExistsException;
import com.cities.exception.DistrictNotFoundException;
import com.cities.mapper.DistrictMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;
    @Lazy
    private final CityService cityService;

    public Optional<DistrictEntity> getDistrictByDistrictNameAndCityName(String districtName, String cityName) {
        return districtRepository.getByDistrictNameAndCity_CityName(districtName, cityName);
    }

    public void saveAll(Iterable<DistrictEntity> districtEntities) {
        districtRepository.saveAll(districtEntities);
    }

    public DistrictMapper getDistrictMapper() {
        return districtMapper;
    }

    public void deleteDistrict(Long districtId) {
        districtRepository.deleteById(districtId);
    }

    public DistrictDTO updateDistrict(Long districtId, DistrictUpdateDTO districtUpdateDTO) {
        var districtEntity = districtRepository.findById(districtId)
                .orElseThrow(() -> new DistrictNotFoundException(String.format("District not found id: %s", districtId)));

        var cityDTO = cityService.getCity(districtEntity.getCity().getCityId());

        cityDTO.getDistricts()
                .forEach(d -> {
                    if (StringUtils.equalsIgnoreCase(d.getDistrictName(), districtUpdateDTO.getDistrictName())) {
                        throw new DistrictAlreadyExistsException(String.format("District already exists id: %s, name: %s", d.getDistrictId(), d.getDistrictName()));
                    }
                });

        districtUpdateDTO.setDistrictName(cityService.capitaliseFirstLetter(districtUpdateDTO).getDistrictName());

        districtMapper.updateDistrictFromDTO(districtUpdateDTO, districtEntity);
        districtRepository.save(districtEntity);

        return districtMapper.toDistrictDTO(districtEntity);
    }
}
