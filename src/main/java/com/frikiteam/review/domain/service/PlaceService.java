package com.frikiteam.review.domain.service;

import com.frikiteam.review.domain.model.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PlaceService {
    Place getPlaceById(Long placeId);
    Place createPlace(Long districtId, Place place);
    Place updatePlace(Long placeId, Place requestPlace);
    ResponseEntity<?> deletePlace (Long placeId);
    Page<Place> getAllPlaces(Pageable pageable);
}
