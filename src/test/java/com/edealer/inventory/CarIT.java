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

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CarIT {
    // DO NOT MODIFY ANY TEST IN THIS CLASS

    @Autowired
    private GraphQLController controller;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void carQueryTest() {
        String query = "{ " +
            "  car(id: \"5ed6092b-6924-4d31-92d0-b77d4d777b46\") { " +
            "    id " +
            "    color " +
            "    makeName " +
            "    modelName " +
            "    year " +
            "  } " +
            "}";

        Map<String, Object> result = controller.graphql(new GraphQLRequest(query, null));
        JsonNode resultJson = mapper.convertValue(result, JsonNode.class);

        Assert.assertNull(resultJson.get("errors"));

        JsonNode data = resultJson.get("data");
        JsonNode car = data.get("car");

        Assert.assertEquals("5ed6092b-6924-4d31-92d0-b77d4d777b46", car.get("id").textValue());
        Assert.assertEquals("RED", car.get("color").textValue());
        Assert.assertEquals("Ford", car.get("makeName").textValue());
        Assert.assertEquals("F150", car.get("modelName").textValue());
        Assert.assertEquals(2020, car.get("year").intValue());
    }

    @Test
    public void carMutationTest() {
        String query = "mutation { " +
            "  carCreate(" +
            "    color: WHITE" +
            "    makeId: \"a64c44da-412a-4611-a44e-5b203dcd026f\"" +
            "    modelId: \"0f157c3f-3125-4594-8f9f-40c1b2f35827\"" +
            "    year: 2019" +
            "  ) { " +
            "      id " +
            "      color " +
            "      makeName " +
            "      modelName " +
            "      year " +
            "  } " +
            "}";

        Map<String, Object> result = controller.graphql(new GraphQLRequest(query, null));
        JsonNode resultJson = mapper.convertValue(result, JsonNode.class);

        Assert.assertNull(resultJson.get("errors"));

        JsonNode data = resultJson.get("data");
        JsonNode car = data.get("carCreate");

        Assert.assertFalse(car.get("id").textValue().isEmpty());
        Assert.assertEquals("WHITE", car.get("color").textValue());
        Assert.assertEquals("Honda", car.get("makeName").textValue());
        Assert.assertEquals("Accord", car.get("modelName").textValue());
        Assert.assertEquals(2019, car.get("year").intValue());
    }

    @Test
    public void carMutationYearBelowMinTest() {
        String query = "mutation { " +
            "  carCreate(" +
            "    color: BLUE" +
            "    makeId: \"a64c44da-412a-4611-a44e-5b203dcd026f\"" +
            "    modelId: \"0f157c3f-3125-4594-8f9f-40c1b2f35827\"" +
            "    year: 1899" +
            "  ) { " +
            "      id " +
            "  } " +
            "}";

        Map<String, Object> result = controller.graphql(new GraphQLRequest(query, null));
        JsonNode resultJson = mapper.convertValue(result, JsonNode.class);

        Assert.assertNotNull(resultJson.get("errors"));

        JsonNode errors = resultJson.get("errors");
        Assert.assertEquals(1, errors.size());

        JsonNode error = errors.get(0);
        Assert.assertEquals("Year out of range", error.get("message").textValue());
    }

    @Test
    public void carMutationYearAboveMaxTest() {
        String query = "mutation { " +
            "  carCreate(" +
            "    color: BLUE" +
            "    makeId: \"a64c44da-412a-4611-a44e-5b203dcd026f\"" +
            "    modelId: \"0f157c3f-3125-4594-8f9f-40c1b2f35827\"" +
            "    year: 2021" +
            "  ) { " +
            "      id " +
            "  } " +
            "}";

        Map<String, Object> result = controller.graphql(new GraphQLRequest(query, null));
        JsonNode resultJson = mapper.convertValue(result, JsonNode.class);

        Assert.assertNotNull(resultJson.get("errors"));

        JsonNode errors = resultJson.get("errors");
        Assert.assertEquals(1, errors.size());

        JsonNode error = errors.get(0);
        Assert.assertEquals("Year out of range", error.get("message").textValue());
    }

    @Test
    public void carMutationInvalidModelIdTest() {
        String query = "mutation { " +
            "  carCreate(" +
            "    color: BLUE" +
            "    makeId: \"a64c44da-412a-4611-a44e-5b203dcd026f\"" +
            "    modelId: \"<invalid-model-id>\"" +
            "    year: 2019" +
            "  ) { " +
            "      id " +
            "  } " +
            "}";

        Map<String, Object> result = controller.graphql(new GraphQLRequest(query, null));
        JsonNode resultJson = mapper.convertValue(result, JsonNode.class);

        Assert.assertNotNull(resultJson.get("errors"));

        JsonNode errors = resultJson.get("errors");
        Assert.assertEquals(1, errors.size());

        JsonNode error = errors.get(0);
        Assert.assertEquals("Invalid model ID", error.get("message").textValue());
    }
    
    @Test
    public void carMutationCarDoesNotBelongToMakeTest() {
        String query = "mutation { " +
            "  carCreate(" +
            "    color: BLUE" +
            "    makeId: \"71431b55-e504-41fd-918c-80aa42c86c54\"" +
            "    modelId: \"82d957db-fa91-41f1-b354-f522c7e0257d\"" +
            "    year: 2020" +
            "  ) { " +
            "      id " +
            "  } " +
            "}";

        Map<String, Object> result = controller.graphql(new GraphQLRequest(query, null));
        JsonNode resultJson = mapper.convertValue(result, JsonNode.class);

        Assert.assertNotNull(resultJson.get("errors"));

        JsonNode errors = resultJson.get("errors");
        Assert.assertEquals(1, errors.size());

        JsonNode error = errors.get(0);
        Assert.assertEquals("Model does not belong to make", error.get("message").textValue());
    }

    @Test
    public void carListQueryFilterByColor() {
        String query = "{ " +
            "  carList(" +
            "    limit: 10 " +
            "    color: BLUE " +
            "    sortBy: [\"color\"] " +
            "    ascending: true " +
            ") { " +
            "    id " +
            "    color " +
            "  } " +
            "}";

        Map<String, Object> result = controller.graphql(new GraphQLRequest(query, null));
        JsonNode resultJson = mapper.convertValue(result, JsonNode.class);

        Assert.assertNull(resultJson.get("errors"));

        JsonNode data = resultJson.get("data");
        JsonNode carList = data.get("carList");

        Assert.assertEquals(1, carList.size());
        Assert.assertEquals("bcc1da6d-393a-4232-870f-80a759ac4fe1", carList.get(0).get("id").textValue());
        Assert.assertEquals("BLUE", carList.get(0).get("color").textValue());
    }

    @Test
    public void carListQuerySortByColor() {
        String query = "{ " +
            "  carList(" +
            "    limit: 2 " +
            "    sortBy: [\"color\"] " +
            "    ascending: false " +
            ") { " +
            "    id " +
            "    color " +
            "  } " +
            "}";

        Map<String, Object> result = controller.graphql(new GraphQLRequest(query, null));
        JsonNode resultJson = mapper.convertValue(result, JsonNode.class);

        Assert.assertNull(resultJson.get("errors"));

        JsonNode data = resultJson.get("data");
        JsonNode carList = data.get("carList");

        Assert.assertEquals(2, carList.size());
        Assert.assertEquals("WHITE", carList.get(0).get("color").textValue());
        Assert.assertEquals("RED", carList.get(1).get("color").textValue());
    }
}
