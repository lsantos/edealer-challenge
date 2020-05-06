package com.edealer.inventory.controller;

import com.edealer.inventory.feature.GraphQLExecutor;
import graphql.ExecutionResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GraphQLController {

    private final GraphQLExecutor graphQLExecutor;

    public GraphQLController(GraphQLExecutor graphQLExecutor) {
        this.graphQLExecutor = graphQLExecutor;
    }

    @PostMapping("/graphql")
    public Map<String, Object> graphql(@RequestBody GraphQLRequest request) {
        ExecutionResult executionResult = graphQLExecutor.execute(request.getQuery(), request.getVariables());

        return executionResult.toSpecification();
    }
}
