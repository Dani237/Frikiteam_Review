package com.frikiteam.review.domain.repositories;

import com.frikiteam.review.domain.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
