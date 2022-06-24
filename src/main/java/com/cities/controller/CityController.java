package com.cities.controller;

import com.cities.dto.CityDTO;
import com.cities.dto.CitySaveDTO;
import com.cities.dto.DistrictDTO;
import com.cities.dto.DistrictUpdateDTO;
import com.cities.model.RestResponse;
import com.cities.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/city/save")
    public RestResponse<CityDTO> saveCityWithDistricts(@RequestBody CitySaveDTO citySaveDTO) {
        return new RestResponse<>(cityService.saveCityWithDistricts(citySaveDTO));
    }

    @PutMapping("/add/district/{cityId}")
    public RestResponse<CityDTO> addDistrictsToCity(@PathVariable long cityId,
                                                    @RequestBody List<DistrictUpdateDTO> districtDTOList) {
        return new RestResponse<>(cityService.addDistrictsToCity(cityId, districtDTOList));
    }

    @GetMapping("/all")
    public RestResponse<List<CityDTO>> getAllCities() {
        return new RestResponse<>(cityService.getAllCities());
    }

    @GetMapping("/city")
    public RestResponse<CityDTO> getCity(@RequestParam Long cityId) {
        return new RestResponse<>(cityService.getCity(cityId));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/city")
    public void deleteCity(@RequestParam Long cityId){
        cityService.deleteCity(cityId);
    }
}
