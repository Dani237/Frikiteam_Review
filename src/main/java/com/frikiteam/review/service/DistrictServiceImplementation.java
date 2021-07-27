package com.frikiteam.review.service;

import com.frikiteam.review.domain.model.City;
import com.frikiteam.review.domain.model.District;
import com.frikiteam.review.domain.repositories.CityRepository;
import com.frikiteam.review.domain.repositories.DistrictRepository;
import com.frikiteam.review.domain.service.DistrictService;
import com.frikiteam.review.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImplementation implements DistrictService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;


    @Override
    public District getDistrictById(Long districtId) {
        return districtRepository.findById(districtId)
                .orElseThrow(()->new ResourceNotFoundException("District", "id", districtId));
    }

    @Override
    public District createDistrict(Long cityId, District district) {
        return cityRepository.findById(cityId)
                .map(city -> {
                    district.setCity(city);
                    return districtRepository.save(district);
                })
                .orElseThrow(()-> new ResourceNotFoundException("City", "Id", cityId));
    }

    @Override
    public District updateDistrict(Long districtId, District requestDistrict) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(()->new ResourceNotFoundException("District", "id", districtId));
        district.setName(requestDistrict.getName());
        district.setReference(requestDistrict.getReference());
        return districtRepository.save(district);
    }

    @Override
    public ResponseEntity<?> deleteDistrict(Long districtId) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(()->new ResourceNotFoundException("District", "id", districtId));
        districtRepository.delete(district);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<District> getAllDistricts(Pageable pageable) {
        return districtRepository.findAll(pageable);
    }
}
