package com.edealer.inventory.repository;

import static org.jooq.generated.Tables.MAKE_MODEL;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class MakeModelRepository {

    private final DSLContext jooq;

    public MakeModelRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    public boolean isModelNotFromMake(String modelId, String makeId) {
        return jooq.selectCount()
                .from(MAKE_MODEL)
                .where(MAKE_MODEL.MODEL_ID.eq(modelId), MAKE_MODEL.MAKE_ID.eq(makeId))
                .fetchOne(0, int.class) == 0;
    }
}
