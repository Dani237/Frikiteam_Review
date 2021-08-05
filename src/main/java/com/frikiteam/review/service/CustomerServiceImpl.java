package com.frikiteam.review.service;

import com.frikiteam.review.domain.model.Customer;
import com.frikiteam.review.domain.repositories.CustomerRepository;
import com.frikiteam.review.domain.service.CustomerService;
import com.frikiteam.review.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        return customerRepository.findById(id).map(
                Cus -> {
                    Cus.setNickName(customer.getNickName());
                    Cus.setEmail(customer.getEmail());
                    Cus.setFirstName(customer.getFirstName());
                    Cus.setLastName(customer.getLastName());
                    Cus.setLogo(customer.getLogo());
                    Cus.setPassword(customer.getPassword());
                    return customerRepository.save(Cus);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return customerRepository.findById(id).map(
            customer -> {
                customerRepository.delete(customer);
                return ResponseEntity.ok().build();
            }
        ).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
    }
}
