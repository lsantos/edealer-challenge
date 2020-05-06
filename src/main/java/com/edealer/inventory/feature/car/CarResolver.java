package com.edealer.inventory.feature.car;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.edealer.inventory.repository.MetadataRepository;
import org.springframework.stereotype.Component;

@Component
public class CarResolver implements GraphQLResolver<Car> {

    private final MetadataRepository metadataRepo;

    public CarResolver(MetadataRepository metadataRepo) {
        this.metadataRepo = metadataRepo;
    }

    public String makeName(Car car) {
        return metadataRepo.getMakeNameById(car.getMakeId());
    }

    public String modelName(Car car) {
        return metadataRepo.getModelNameById(car.getModelId());
    }
}
