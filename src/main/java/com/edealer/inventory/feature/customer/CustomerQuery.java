package com.edealer.inventory.feature.customer;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.edealer.inventory.repository.CustomerRepository;

@Component
public class CustomerQuery implements GraphQLQueryResolver {

    private final CustomerRepository customerRepo;

    public CustomerQuery(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer customer(String id) {
        return customerRepo.getById(id);
    }
}
