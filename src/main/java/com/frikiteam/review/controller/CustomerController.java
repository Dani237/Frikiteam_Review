package com.frikiteam.review.controller;

import com.frikiteam.review.domain.model.Customer;
import com.frikiteam.review.domain.model.User;
import com.frikiteam.review.domain.service.CustomerService;
import com.frikiteam.review.resource.CustomerResource;
import com.frikiteam.review.resource.SaveCustomerResource;
import com.frikiteam.review.resource.SaveUserResource;
import com.frikiteam.review.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ModelMapper mapper;


    @GetMapping("/customers")
    public Page<CustomerResource> getAllCustomers(Pageable pageable) {
        Page<Customer> customers = customerService.getAll(pageable);
        List<CustomerResource> resources = customers.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/customers/{id}")
    public CustomerResource getCustomerById(@PathVariable Long id) {
        return convertToResource(customerService.getById(id));
    }

    @PostMapping("/customers")
    public CustomerResource createCustomer(@RequestBody SaveCustomerResource resource) {
        return convertToResource(customerService.save(convertToEntity(resource)));
    }

    @PutMapping("/customers/{id}")
    public CustomerResource updateCustomer(@PathVariable Long id, @RequestBody SaveCustomerResource resource) {
        return convertToResource(customerService.update(id, convertToEntity(resource)));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        return customerService.delete(id);
    }

    private Customer convertToEntity(SaveCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }
    private CustomerResource convertToResource(Customer entity) {
        return mapper.map(entity, CustomerResource.class);
    }


}

