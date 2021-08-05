package com.frikiteam.review.domain.repositories;

import com.frikiteam.review.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
