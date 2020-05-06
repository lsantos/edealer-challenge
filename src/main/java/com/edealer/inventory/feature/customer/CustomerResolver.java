package com.edealer.inventory.feature.customer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.edealer.inventory.feature.car.Car;
import com.edealer.inventory.repository.MetadataRepository;

@Component
public class CustomerResolver implements GraphQLResolver<Customer> {

    private final MetadataRepository metadataRepo;

    public CustomerResolver(MetadataRepository metadataRepo) {
        this.metadataRepo = metadataRepo;
    }

    public List<Car> getCarsInterested(Customer customer) {
        return metadataRepo.getInterestedCarsByCustomerId(customer.getId());    
    }
}
