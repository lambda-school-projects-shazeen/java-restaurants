package com.lambdaschool.restaurants.controllers;

import com.lambdaschool.restaurants.RestaurantsApplication;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = RestaurantsApplication.class)
@AutoConfigureMockMvc
public class RestaurantControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .build();
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void whenMeasuredReponseTime()
    {
        given().when()
            .get("/restaurants/restaurants")
            .then()
            .time(lessThan(5000L));
    }

    @Test
    public void listAllRestaurants()
    {
        given().when()
            .get("/restaurants/restaurants")
            .then()
            .statusCode(200)
            .and()
            .body(containsString("Apple"));
    }

    @Test
    public void getRestaurantById()
    {
        long aRestaurant = 4;

        given().when()
            .get("/restaurants/restaurant/" + aRestaurant)
            .then()
            .statusCode(200)
            .and()
            .body(containsString("Test"));
    }

    @Test
    public void getRestaurantByIdNotFound()
    {
        long aRestaurant = 7777;

        given().when()
            .get("/restaurants/restaurant/" + aRestaurant)
            .then()
            .statusCode(404)
            .and()
            .body(containsString("Resource"));
    }

    @Test
    public void getRestaurantByName()
    {
        String aRestaurant = "Test Apple";

        given().when()
            .get("/restaurants/restaurant/name/" + aRestaurant)
            .then()
            .statusCode(200)
            .and()
            .body(containsString("Apple"));
    }

    @Test
    public void getRestaurantByNameNotFound()
    {
        String aRestaurant = "Turtle";

        given().when()
            .get("/restaurants/restaurant/name/" + aRestaurant)
            .then()
            .statusCode(404)
            .and()
            .body(containsString("Resource"));
    }

    @Test
    public void listRestaurantNameCity()
    {
        given().when()
            .get("/restaurants/restaurants/name/apple/city/city")
            .then()
            .statusCode(200)
            .and()
            .body(containsString("Apple"));
    }

    @Test
    public void listRestaurantNameLike()
    {
        given().when()
            .get("/restaurants/restaurants/namelike/test")
            .then()
            .statusCode(200)
            .and()
            .body(containsString("Test"));
    }

    @Test
    public void addNewRestaurant() throws Exception
    {
        String jsonInput = "{\n" +
            "    \"name\": \"Good Eats\",\n" +
            "    \"address\": \"123 Main Avenue\",\n" +
            "    \"city\": \"Uptown\",\n" +
            "    \"state\": \"ST\",\n" +
            "    \"telephone\": \"555-777-7777\",\n" +
            "    \"menus\": [\n" +
            "        {\n" +
            "            \"dish\": \"Soda\",\n" +
            "            \"price\": 3.50\n" +
            "        },\n" +
            "        {\n" +
            "            \"dish\": \"Latte\",\n" +
            "            \"price\": 5.00\n" +
            "        }\n" +
            "    ],\n" +
            "    \"payments\": [\n" +
            "        {\n" +
            "            \"payment\" : {\n" +
            "                \"paymentid\":2\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"payment\" : {\n" +
            "                \"paymentid\":3\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/restaurants/restaurant")
            .content(jsonInput)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.header()
                .exists("location"));
    }

    @Test
    public void updateRestaurant() throws Exception
    {
        String jsonInput = "{\n" +
            "    \"name\": \"Stumps Eatery\",\n" +
            "    \"address\": \"123 Main Avenue\",\n" +
            "    \"city\": \"Uptown\",\n" +
            "    \"state\": \"ST\",\n" +
            "    \"telephone\": \"555-777-7777\",\n" +
            "    \"menus\": [\n" +
            "        {\n" +
            "            \"dish\": \"Soda\",\n" +
            "            \"price\": 3.50\n" +
            "        },\n" +
            "        {\n" +
            "            \"dish\": \"Latte\",\n" +
            "            \"price\": 5.00\n" +
            "        }\n" +
            "    ],\n" +
            "    \"payments\": [\n" +
            "        {\n" +
            "            \"payment\" : {\n" +
            "                \"paymentid\":2\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"payment\" : {\n" +
            "                \"paymentid\":3\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/restaurants/restaurant/{userid}",
            13)
            .content(jsonInput)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void deleteRestaurantById()
    {
        long aRestaurant = 10;
        given().when()
            .delete("/restaurants/restaurant/" + aRestaurant)
            .then()
            .statusCode(200);
    }

    @Test
    public void deleteRestaurantByIdNotFound()
    {
        long aRestaurant = 7777;
        given().when()
            .delete("/restaurants/restaurant/" + aRestaurant)
            .then()
            .statusCode(404);
    }

}