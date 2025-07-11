package com.automationexercise.api.tests;

import com.automationexercise.api.endpoints.ApiEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ProductsApiTests extends ApiBaseTest {

    @Test(description = "API 1: Get All Products List")
    public void testGetAllProductsList() {
        Response response = ApiEndpoints.getAllProductsList();

        response.then()
                .statusCode(200) // Перевірка коду відповіді
                .body("responseCode", equalTo(200)) // Перевірка коду у тілі відповіді
                .body("products", not(empty())); // Перевірка, що список продуктів не порожній
    }

    @Test(description = "API 2: POST To All Products List - Method Not Supported")
    public void testPostToAllProductsList() {
        Response response = ApiEndpoints.postToAllProductsList();

        response.then()
                .statusCode(200) // API повертає 200, хоча логічно очікувати 405
                .body("responseCode", equalTo(405)) // Перевірка коду у тілі відповіді
                .body("message", equalTo("This request method is not supported.")); // Перевірка повідомлення
    }

    @Test(description = "API 3: Get All Brands List")
    public void testGetAllBrandsList() {
        Response response = ApiEndpoints.getAllBrandsList();

        response.then()
                .statusCode(200) // Перевірка коду відповіді
                .body("responseCode", equalTo(200)) // Перевірка коду у тілі відповіді
                .body("brands", not(empty())); // Перевірка, що список брендів не порожній
    }

    @Test(description = "API 4: PUT To All Brands List - Method Not Supported")
    public void testPutToAllBrandsList() {
        Response response = ApiEndpoints.putToAllBrandsList();

        response.then()
                .statusCode(200) // API повертає 200, хоча логічно очікувати 405
                .body("responseCode", equalTo(405)) // Перевірка коду у тілі відповіді
                .body("message", equalTo("This request method is not supported.")); // Перевірка повідомлення
    }
}