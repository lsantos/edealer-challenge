package com.edealer.inventory.feature.customer;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.edealer.inventory.repository.CarCustomerRepository;
import com.edealer.inventory.repository.CustomerRepository;

import graphql.execution.DataFetcherResult;

@Component
public class CustomerCreateMutation implements GraphQLMutationResolver {

    private final CustomerRepository customerRepo;
    private final CarCustomerRepository carCustomerRepo;

    public CustomerCreateMutation(CustomerRepository customerRepo, CarCustomerRepository carCustomerRepo) {
        this.customerRepo = customerRepo;
        this.carCustomerRepo = carCustomerRepo;
    }

    public DataFetcherResult<Customer> customerCreate(
            String firstName,
            String lastName,
            String[] carInterestedIds
            ) {

        Customer customer = new Customer(
                UUID.randomUUID().toString(),
                firstName,
                lastName
                );

        customerRepo.create(customer);
        carCustomerRepo.create(customer, carInterestedIds);

        return DataFetcherResult.<Customer>newResult()
                .data(customer)
                .build();
    }
}
