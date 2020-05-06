package com.edealer.inventory.repository;

import static org.jooq.generated.Tables.CAR_CUSTOMER;

import org.jooq.DSLContext;
import org.jooq.InsertValuesStep2;
import org.jooq.generated.tables.records.CarCustomerRecord;
import org.springframework.stereotype.Repository;

import com.edealer.inventory.feature.customer.Customer;

@Repository
public class CarCustomerRepository {

    private final DSLContext jooq;

    public CarCustomerRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    public void create(Customer customer, String[] carInterestedIds) {      
        InsertValuesStep2<CarCustomerRecord, String, String> insertInto = this.jooq.insertInto(CAR_CUSTOMER, 
                CAR_CUSTOMER.CAR_ID, CAR_CUSTOMER.CUSTOMER_ID);

        for (int i = 0; i < carInterestedIds.length; i++) {
            insertInto.values(carInterestedIds[i], customer.getId());
        }

        insertInto.execute();
    }    
}
