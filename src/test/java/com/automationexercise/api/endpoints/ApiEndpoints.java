package com.automationexercise.api.endpoints;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Цей клас інкапсулює логіку для взаємодії з API ендпоінтами.
 * Він діє як сервісний шар, аналогічний до Page Object для UI тестів.
 */
public class ApiEndpoints {

    private static final String PRODUCTS_LIST_ENDPOINT = "/productsList";
    private static final String BRANDS_LIST_ENDPOINT = "/brandsList";
    private static final String SEARCH_PRODUCT_ENDPOINT = "/searchProduct";
    private static final String VERIFY_LOGIN_ENDPOINT = "/verifyLogin";
    private static final String CREATE_ACCOUNT_ENDPOINT = "/createAccount";
    private static final String DELETE_ACCOUNT_ENDPOINT = "/deleteAccount";
    private static final String UPDATE_ACCOUNT_ENDPOINT = "/updateAccount";
    private static final String GET_USER_DETAIL_ENDPOINT = "/getUserDetailByEmail";

    /**
     * Надсилає GET-запит для отримання списку всіх продуктів.
     * @return Об'єкт Response від RestAssured.
     */
    public static Response getAllProductsList() {
        return given()
                .when()
                .get(PRODUCTS_LIST_ENDPOINT);
    }

    /**
     * Надсилає POST-запит до ендпоінту списку продуктів.
     * @return Об'єкт Response від RestAssured.
     */
    public static Response postToAllProductsList() {
        return given()
                .when()
                .post(PRODUCTS_LIST_ENDPOINT);
    }

    /**
     * Надсилає GET-запит для отримання списку всіх брендів.
     * @return Об'єкт Response від RestAssured.
     */
    public static Response getAllBrandsList() {
        return given()
                .when()
                .get(BRANDS_LIST_ENDPOINT);
    }

    /**
     * Надсилає PUT-запит до ендпоінту списку брендів.
     * @return Об'єкт Response від RestAssured.
     */
    public static Response putToAllBrandsList() {
        return given()
                .when()
                .put(BRANDS_LIST_ENDPOINT);
    }

    public static Response searchProduct(String searchTerm) {
        return given()
                .formParam("search_product", searchTerm)
                .when()
                .post(SEARCH_PRODUCT_ENDPOINT);
    }

    public static Response searchProductWithoutParam() {
        return given()
                .when()
                .post(SEARCH_PRODUCT_ENDPOINT);
    }

    public static Response verifyLogin(String email, String password) {
        return given()
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post(VERIFY_LOGIN_ENDPOINT);
    }

    public static Response verifyLoginWithInvalidDetails(String email, String password) {
        // Цей метод функціонально ідентичний до verifyLogin,
        // але назва відображає його використання у тесті
        return verifyLogin(email, password);
    }

    public static Response verifyLoginWithoutEmail(String password) {
        return given()
                .formParam("password", password)
                .when()
                .post(VERIFY_LOGIN_ENDPOINT);
    }

    public static Response deleteVerifyLogin() {
        return given()
                .when()
                .delete(VERIFY_LOGIN_ENDPOINT);
    }

    public static Response createAccount(Map<String, Object> userData) {
        return given()
                .formParams(userData)
                .when()
                .post(CREATE_ACCOUNT_ENDPOINT);
    }

    public static Response deleteAccount(String email, String password) {
        return given()
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .delete(DELETE_ACCOUNT_ENDPOINT);
    }

    public static Response updateAccount(Map<String, Object> userData) {
        return given()
                .formParams(userData)
                .when()
                .put(UPDATE_ACCOUNT_ENDPOINT);
    }

    public static Response getUserDetailByEmail(String email) {
        return given()
                .queryParam("email", email)
                .when()
                .get(GET_USER_DETAIL_ENDPOINT);
    }
}