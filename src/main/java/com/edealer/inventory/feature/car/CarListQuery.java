package com.edealer.inventory.feature.car;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.edealer.inventory.repository.CarRepository;

@Component
public class CarListQuery implements GraphQLQueryResolver {

    private final CarRepository carRepo;

    public CarListQuery(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    public List<Car> carList(Integer limit, String[] sortBy, Boolean ascending, Color color) {
        return this.carRepo.getCarList(limit, sortBy, ascending, color);
    }
}
