package com.edealer.inventory;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.edealer.inventory.controller.GraphQLController;
import com.edealer.inventory.controller.GraphQLRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerIT {
    @Autowired
    private GraphQLController controller;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void customerQueryTest() {
        String query = "{ " +
                "  customer(id: \"4139ef44-c1ea-43f1-b2e9-706c8167d8a3\") { " +
                "    id        " +	
                "    firstName " +
                "    lastName "  +
                "    carsInterested { " +
                "      color          " +
                "      makeName       " +
                "      modelName      " +
                "      year		      " +
                "    } " +
                "  } " +
                "}";

        Map<String, Object> result = controller.graphql(new GraphQLRequest(query, null));
        JsonNode resultJson = mapper.convertValue(result, JsonNode.class);

        Assert.assertNull(resultJson.get("errors"));

        JsonNode data = resultJson.get("data");
        JsonNode customer = data.get("customer");
        ArrayNode carsInterested = (ArrayNode) customer.get("carsInterested");        

        Assert.assertEquals("4139ef44-c1ea-43f1-b2e9-706c8167d8a3", customer.get("id").textValue());
        Assert.assertEquals("Tabangi", customer.get("firstName").textValue());
        Assert.assertEquals("Motors", customer.get("lastName").textValue());
        Assert.assertEquals(3, carsInterested.size());
    }

    @Test
    public void customerMutationTest() {
        String query = "mutation { " +
                "  customerCreate(" +
                "    firstName: \"Alan\"" +
                "    lastName: \"White\"" +
                "    carsInterestedIds: [\"671ccc3f-3cda-4544-be66-a27545202a3c\"]" +
                "  ) { " +
                "      id 		 " +
                "      firstName " +
                "      lastName " +
                "      carsInterested { " +
                "        color          " +
                "        makeName       " +
                "        modelName      " +
                "        year		    " +     
                "    } " +
                "  } "   +
                "}";

        Map<String, Object> result = controller.graphql(new GraphQLRequest(query, null));
        JsonNode resultJson = mapper.convertValue(result, JsonNode.class);
        Assert.assertNull(resultJson.get("errors"));

        JsonNode data = resultJson.get("data");
        JsonNode customer = data.get("customerCreate");
        ArrayNode carsInterested = (ArrayNode) customer.get("carsInterested");
        JsonNode carInterested = carsInterested.get(0);

        Assert.assertFalse(customer.get("id").textValue().isEmpty());
        Assert.assertEquals("Alan", customer.get("firstName").textValue());
        Assert.assertEquals("White", customer.get("lastName").textValue());
        Assert.assertEquals("Chevrolet", carInterested.get("makeName").textValue());
        Assert.assertEquals("Cruze", carInterested.get("modelName").textValue());
        Assert.assertEquals(2020, carInterested.get("year").asInt());
    }

    @Test
    public void customerMutationCarDoesNotExistTest() {
        String query = "mutation { " +
                "  customerCreate(" +
                "    firstName: \"Max\"" +
                "    lastName: \"Ming\"" +
                "    carsInterestedIds: [\"35196405-427f-497d-b2a6-ce6f568a13e1\"]" +
                "  ) { 		  " +
                "      id 	  " +
                "    } 		  " +
                "  } 		  " +
                "}";

        Map<String, Object> result = controller.graphql(new GraphQLRequest(query, null));
        JsonNode resultJson = mapper.convertValue(result, JsonNode.class);
        Assert.assertNotNull(resultJson.get("errors"));

        JsonNode errors = resultJson.get("errors");
        Assert.assertEquals(1, errors.size());
    }
}
