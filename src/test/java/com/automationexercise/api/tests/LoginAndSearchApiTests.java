package com.automationexercise.api.tests;

import com.automationexercise.api.endpoints.ApiEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class LoginAndSearchApiTests extends ApiBaseTest {

    // --- Search Product Tests ---
    @Test(description = "API 5: POST To Search Product")
    public void testSearchProductWithParameter() {
        Response response = ApiEndpoints.searchProduct("top");

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("products", not(equalTo(null)));
    }

    @Test(description = "API 6: POST To Search Product without search_product parameter")
    public void testSearchProductWithoutParameter() {
        Response response = ApiEndpoints.searchProductWithoutParam();

        response.then()
                .statusCode(200) // API повертає 200
                .body("responseCode", equalTo(400))
                .body("message", equalTo("Bad request, search_product parameter is missing in POST request."));
    }

    // --- Verify Login Tests ---

    @Test(description = "API 7: POST To Verify Login with valid details")
    public void testVerifyLoginWithValidDetails() {
        // Використовуємо існуючі валідні дані з сайту
        Response response = ApiEndpoints.verifyLogin("test@example.com", "password123");

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("message", equalTo("User exists!"));
    }

    @Test(description = "API 8: POST To Verify Login without email parameter")
    public void testVerifyLoginWithoutEmail() {
        Response response = ApiEndpoints.verifyLoginWithoutEmail("somepassword");

        response.then()
                .statusCode(200) // API повертає 200
                .body("responseCode", equalTo(400))
                .body("message", equalTo("Bad request, email or password parameter is missing in POST request."));
    }

    @Test(description = "API 9: DELETE To Verify Login")
    public void testDeleteToVerifyLogin() {
        Response response = ApiEndpoints.deleteVerifyLogin();

        response.then()
                .statusCode(200) // API повертає 200
                .body("responseCode", equalTo(405))
                .body("message", equalTo("This request method is not supported."));
    }

    @Test(description = "API 10: POST To Verify Login with invalid details")
    public void testVerifyLoginWithInvalidDetails() {
        Response response = ApiEndpoints.verifyLoginWithInvalidDetails("invalid@user.com", "invalidpass");

        response.then()
                .statusCode(200) // API повертає 200
                .body("responseCode", equalTo(404))
                .body("message", equalTo("User not found!"));
    }
}