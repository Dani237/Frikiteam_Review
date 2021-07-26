package com.frikiteam.review.domain.repositories;

import com.frikiteam.review.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
