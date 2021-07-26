package com.frikiteam.review.domain.service;

import com.frikiteam.review.domain.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CountryService {
    Country getCountryById(Long countryId);
    Country createCountry(Country country);
    Country updateCountry(Long countryId, Country updateCountry);
    ResponseEntity<?> deleteCountry(Long countryId);
    Page<Country> getAllCountries(Pageable pageable);
}
