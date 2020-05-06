package com.edealer.inventory.feature;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class GraphQLExecutor {

    private final GraphQL graphQL;

    public GraphQLExecutor(List<GraphQLResolver<?>> resolvers) {
        Resource schemaResource = new ClassPathResource("schema.graphqls");

        String schemaString;
        try (Reader reader = new InputStreamReader(schemaResource.getInputStream(), UTF_8)) {
            schemaString = FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        GraphQLSchema schema = SchemaParser.newParser()
            .schemaString(schemaString)
            .resolvers(resolvers)
            .build()
            .makeExecutableSchema();

        this.graphQL = GraphQL.newGraphQL(schema).build();
    }

    public ExecutionResult execute(String query, Map<String, Object> variables) {
        ExecutionInput.Builder executionInput = ExecutionInput.newExecutionInput().query(query);

        if (variables != null) {
            executionInput.variables(variables);
        }

        return graphQL.execute(executionInput);
    }
}
