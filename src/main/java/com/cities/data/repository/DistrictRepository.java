package com.cities.data.repository;

import com.cities.data.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
    Optional<DistrictEntity> getByDistrictNameAndCity_CityName(String districtName, String cityName);
}
