package com.frikiteam.review.domain.service;

import com.frikiteam.review.domain.model.City;
import com.frikiteam.review.domain.model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DistrictService {
    District getDistrictById(Long districtId);
    District createDistrict(Long cityId, District district);

    District updateDistrict(Long districtId, District updateDistrict);

    ResponseEntity<?> deleteDistrict (Long districtId);

    Page<District> getAllDistricts(Pageable pageable);
}