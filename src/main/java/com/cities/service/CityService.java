package com.cities.service;

import com.cities.data.entity.CityEntity;
import com.cities.data.entity.DistrictEntity;
import com.cities.data.repository.CityRepository;
import com.cities.dto.*;
import com.cities.exception.CityAlreadyExistsException;
import com.cities.exception.CityNotFoundException;
import com.cities.exception.DistrictAlreadyExistsException;
import com.cities.mapper.CityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final DistrictService districtService;

    private DistrictEntity addDistrictCallback(DistrictBaseDTO districtDTO, CityEntity city) {
        districtService.getDistrictByDistrictNameAndCityName(districtDTO.getDistrictName(), city.getCityName())
                .ifPresent(d -> {
                    throw new DistrictAlreadyExistsException(String.format("District already exists id: %s, name: %s", d.getDistrictId(), d.getDistrictName()));
                });

        var districtEntity = districtService.getDistrictMapper().toDistrict(districtDTO);
        districtEntity.setCity(city);
        return districtEntity;
    }

    public DistrictBaseDTO capitaliseFirstLetter(DistrictBaseDTO districtDTO) {
        districtDTO.setDistrictName(districtDTO.getDistrictName().substring(0, 1).toUpperCase() + districtDTO.getDistrictName().substring(1));
        return districtDTO;
    }

    public CityDTO saveCityWithDistricts(CitySaveDTO citySaveDTO) {
        citySaveDTO.setCityName(citySaveDTO.getCityName().substring(0, 1).toUpperCase() + citySaveDTO.getCityName().substring(1));

        cityRepository.getByCityName(citySaveDTO.getCityName())
                .ifPresent(c -> {
                    throw new CityAlreadyExistsException(String.format("City already exists id: %s, name: %s", c.getCityId(), c.getCityName()));
                });

        var cityEntity = cityMapper.toCity(citySaveDTO);
        cityRepository.save(cityEntity);

        List<DistrictEntity> districtEntities = new ArrayList<>();

        if (citySaveDTO.getDistrictList() != null && citySaveDTO.getDistrictList().size() > 0) {
            districtEntities = citySaveDTO.getDistrictList()
                    .stream()
                    .map(this::capitaliseFirstLetter)
                    .distinct()
                    .map(d -> {
                        var districtEntity = districtService.getDistrictMapper().toDistrict(d);
                        districtEntity.setCity(cityEntity);
                        return districtEntity;
                    })
                    .collect(Collectors.toList());

            districtService.saveAll(districtEntities);
        }
        cityEntity.setDistricts(new LinkedHashSet<>(districtEntities));
        return cityMapper.toCityDTO(cityEntity);
    }

    public CityDTO addDistrictsToCity(long cityId, List<DistrictUpdateDTO> districtsToCityDTO) {
        var cityEntity = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(String.format("City not found id: %s", cityId)));

        var districtEntities = districtsToCityDTO
                .stream()
                .map(this::capitaliseFirstLetter)
                .distinct()
                .map(d -> addDistrictCallback(d, cityEntity))
                .collect(Collectors.toList());

        districtService.saveAll(districtEntities);

        cityEntity.getDistricts().addAll(new LinkedHashSet<>(districtEntities));
        return cityMapper.toCityDTO(cityEntity);
    }

    public List<CityDTO> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(cityMapper::toCityDTO)
                .collect(Collectors.toList());
    }

    public CityDTO getCity(Long cityId) {
        return cityMapper.toCityDTO(cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(String.format("City not found id: %s", cityId))));
    }

    public void deleteCity(Long cityId) {
        cityRepository.deleteById(cityId);
    }
}