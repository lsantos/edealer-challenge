package com.edealer.inventory.repository;

import static org.jooq.generated.Tables.CAR_CUSTOMER;
import static org.jooq.generated.Tables.MAKE;
import static org.jooq.generated.Tables.MODEL;
import static org.jooq.generated.Tables.CAR;

import java.util.List;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import com.edealer.inventory.feature.car.Car;

@Repository
public class MetadataRepository {

    private final DSLContext jooq;

    public MetadataRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    public String getMakeNameById(String makeId) {
        return jooq.selectFrom(MAKE)
                .where(MAKE.ID.eq(makeId))
                .fetchOne(MAKE.NAME);
    }

    public String getModelNameById(String modelId) {
        return jooq.selectFrom(MODEL)
                .where(MODEL.ID.eq(modelId))
                .fetchOne(MODEL.NAME);
    }

    public List<Car> getInterestedCarsByCustomerId(String customerId) {
        return jooq.select(CAR.ID, 
                CAR.COLOR, CAR.MAKE_ID, CAR.MODEL_ID, CAR.YEAR)
                .from(CAR)
                .join(CAR_CUSTOMER)
                .on(CAR.ID.eq(CAR_CUSTOMER.CAR_ID))
                .where(CAR_CUSTOMER.CUSTOMER_ID.eq(customerId))
                .fetchInto(Car.class);
    }
}
