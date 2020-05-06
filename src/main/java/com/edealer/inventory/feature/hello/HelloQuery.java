package com.edealer.inventory.feature.hello;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class HelloQuery implements GraphQLQueryResolver {

    public String hello() {
        return "World!";
    }
}
