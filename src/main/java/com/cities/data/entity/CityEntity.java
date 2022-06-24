package com.cities.data.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@ToString
@Entity
@Table(name = "city")
public class CityEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "city_code")
    private String cityCode;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<DistrictEntity> districts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var city = (CityEntity) o;
        return Objects.equals(cityId, city.cityId) && Objects.equals(cityName, city.cityName);
    }

    @Override
    public int hashCode() {
        return cityId.hashCode();
    }
}
