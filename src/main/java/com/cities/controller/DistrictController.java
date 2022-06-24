package com.cities.controller;

import com.cities.dto.DistrictDTO;
import com.cities.dto.DistrictUpdateDTO;
import com.cities.model.RestResponse;
import com.cities.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/districts")
public class DistrictController {
    private final DistrictService districtService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/district")
    public void deleteDistrict(@RequestParam Long districtId) {
        districtService.deleteDistrict(districtId);
    }

    @PutMapping("/update/district")
    public RestResponse<DistrictDTO> updateDistrict(@RequestParam Long districtId,
                                                    @RequestBody DistrictUpdateDTO districtUpdateDTO) {
        return new RestResponse<>(districtService.updateDistrict(districtId, districtUpdateDTO));
    }
}
