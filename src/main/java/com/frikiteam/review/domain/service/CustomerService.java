package com.frikiteam.review.domain.service;

import com.frikiteam.review.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    Page<Customer> getAll(Pageable pageable);
    Customer getById(Long id);
    Customer save(Customer customer);
    Customer update(Long id, Customer customer);
    ResponseEntity<?> delete(Long id);
}
