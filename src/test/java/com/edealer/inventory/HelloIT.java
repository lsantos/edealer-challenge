package com.edealer.inventory;

import com.edealer.inventory.controller.GraphQLController;
import com.edealer.inventory.controller.GraphQLRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloIT {

    @Autowired
    private GraphQLController controller;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void helloQueryTest() {
        String query = "{" +
            "  hello" +
            "}";

        Map<String, Object> result = controller.graphql(new GraphQLRequest(query, null));
        JsonNode resultJson = mapper.convertValue(result, JsonNode.class);

        Assert.assertNull(resultJson.get("errors"));

        JsonNode data = resultJson.get("data");
        Assert.assertEquals("World!", data.get("hello").textValue());
    }
}
