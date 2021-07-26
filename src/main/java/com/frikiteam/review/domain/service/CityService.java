package com.frikiteam.review.domain.service;

import com.frikiteam.review.domain.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CityService {
    City getCityById(Long cityId);

    City createCity(Long countryId, City city);
    City updateCity(Long cityId, City requestCity);
    ResponseEntity<?> deleteCity (Long cityId);

    Page<City> getAllCities(Pageable pageable);
}
