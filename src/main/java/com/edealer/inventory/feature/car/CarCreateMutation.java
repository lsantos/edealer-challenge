package com.edealer.inventory.feature.car;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.edealer.inventory.feature.InputError;
import com.edealer.inventory.repository.CarRepository;
import com.edealer.inventory.repository.MakeModelRepository;
import com.edealer.inventory.repository.MetadataRepository;

import graphql.execution.DataFetcherResult;

@Component
public class CarCreateMutation implements GraphQLMutationResolver {

    private static final int STARTING_YEAR = 1900;
    private final CarRepository carRepo;
    private final MetadataRepository metadataRepo;
    private final MakeModelRepository makeModelRepository;

    public CarCreateMutation(CarRepository carRepo, MetadataRepository metadataRepo, MakeModelRepository makeModelRepository) {
        this.carRepo = carRepo;
        this.metadataRepo = metadataRepo;
        this.makeModelRepository = makeModelRepository;
    }

    public DataFetcherResult<Car> carCreate(
            Color color,
            String makeId,
            String modelId,
            Integer year
            ) {
        if (metadataRepo.getMakeNameById(makeId) == null) {
            return DataFetcherResult.<Car>newResult()
                    .error(new InputError("Invalid make ID"))
                    .build();
        }

        if (metadataRepo.getModelNameById(modelId) == null) {
            return DataFetcherResult.<Car>newResult()
                    .error(new InputError("Invalid model ID"))
                    .build();
        }

        if (year < STARTING_YEAR || year > LocalDate.now().getYear()) {
            return DataFetcherResult.<Car>newResult()
                    .error(new InputError("Year out of range"))
                    .build();
        }

        if (makeModelRepository.isModelNotFromMake(modelId, makeId)) {
            return DataFetcherResult.<Car>newResult()
                    .error(new InputError("Model does not belong to make"))
                    .build();
        }

        Car car = new Car(
                UUID.randomUUID().toString(),
                color,
                makeId,
                modelId,
                year
                );

        carRepo.create(car);

        return DataFetcherResult.<Car>newResult()
                .data(car)
                .build();
    }
}
