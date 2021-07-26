package com.frikiteam.review.service;

import com.frikiteam.review.domain.model.City;
import com.frikiteam.review.domain.repositories.CityRepository;
import com.frikiteam.review.domain.repositories.CountryRepository;
import com.frikiteam.review.domain.service.CityService;
import com.frikiteam.review.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImplementation implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(()->new ResourceNotFoundException("City", "id", cityId));
    }

    @Override
    public City createCity(Long countryId, City city) {
        return countryRepository.findById(countryId)
                .map(country -> {
                    city.setCountry(country);
                    return cityRepository.save(city);
                }).orElseThrow(() -> new ResourceNotFoundException("Country", "Id", countryId));
    }

    @Override
    public City updateCity(Long cityId, City updateCity) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(()->new ResourceNotFoundException("City", "id", cityId));
        city.setName(updateCity.getName());
        city.setReference(updateCity.getReference());
        city.setCountry(updateCity.getCountry());
        return cityRepository.save(updateCity);
    }

    @Override
    public ResponseEntity<?> deleteCity(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "id", cityId));
        cityRepository.delete(city);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<City> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }
}
