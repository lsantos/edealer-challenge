package com.edealer.inventory.repository;

import static org.jooq.generated.Tables.CAR;
import static org.jooq.impl.DSL.trueCondition;

import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SortField;
import org.jooq.SortOrder;
import org.springframework.stereotype.Repository;

import com.edealer.inventory.feature.car.Car;
import com.edealer.inventory.feature.car.Color;

@Repository
public class CarRepository {

    private final DSLContext jooq;

    public CarRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    public Car getById(String id) {
        return jooq.selectFrom(CAR)
                .where(CAR.ID.eq(id))
                .fetchOneInto(Car.class);
    }

    public void create(Car car) {
        jooq.newRecord(CAR, car)
        .insert();
    }

    public List<Car> getCarList(Integer limit, String[] sortBy, Boolean ascending, Color color) {
        Condition condition = trueCondition();
        SortField<?>[] orderBy = new SortField<?>[sortBy.length];

        for (int i = 0; i < sortBy.length; ++i) {
            Field<?> field = CAR.field(sortBy[i].toUpperCase());

            if (field != null) {
                orderBy[i] = field.sort(ascending == true ? SortOrder.ASC : SortOrder.DESC);	
            }
        }

        if (color != null) {
            condition = condition.and(CAR.COLOR.eq(color.name()));
        }

        return jooq.selectFrom(CAR)
                .where(condition)
                .orderBy(orderBy)
                .limit(limit)
                .fetchInto(Car.class);
    }   
}
