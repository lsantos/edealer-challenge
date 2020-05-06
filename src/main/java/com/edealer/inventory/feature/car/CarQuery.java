package com.edealer.inventory.feature.car;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.edealer.inventory.repository.CarRepository;
import org.springframework.stereotype.Component;

@Component
public class CarQuery implements GraphQLQueryResolver {

    private final CarRepository carRepo;

    public CarQuery(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    public Car car(String id) {
        return carRepo.getById(id);
    }
}
