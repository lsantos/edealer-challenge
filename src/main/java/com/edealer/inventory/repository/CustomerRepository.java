package com.edealer.inventory.repository;

import static org.jooq.generated.Tables.CUSTOMER;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import com.edealer.inventory.feature.customer.Customer;

@Repository
public class CustomerRepository {

    private final DSLContext jooq;

    public CustomerRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    public Customer getById(String id) {
        return jooq.selectFrom(CUSTOMER)
                .where(CUSTOMER.ID.eq(id))
                .fetchOneInto(Customer.class);
    }

    public void create(Customer customer) {
        jooq.newRecord(CUSTOMER, customer)
        .insert();
    }    
}
