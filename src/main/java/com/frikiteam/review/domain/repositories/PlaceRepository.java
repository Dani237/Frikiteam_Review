package com.frikiteam.review.domain.repositories;

import com.frikiteam.review.domain.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
