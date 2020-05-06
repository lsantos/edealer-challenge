package com.edealer.inventory.feature;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

import static graphql.ErrorType.DataFetchingException;

public class InputError implements GraphQLError {

    private final String message;

    public InputError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return DataFetchingException;
    }
}
