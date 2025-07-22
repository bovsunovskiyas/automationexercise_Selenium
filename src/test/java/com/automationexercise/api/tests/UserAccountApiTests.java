package com.automationexercise.api.tests;

import com.automationexercise.api.endpoints.ApiEndpoints;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class UserAccountApiTests extends ApiBaseTest {

    private Faker faker;
    private Map<String, Object> userData;

    @BeforeClass
    public void generateTestData() {
        faker = new Faker();
        userData = new HashMap<>();

        // Генеруємо унікальні дані для кожного запуску
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16);
        String firstName = faker.name().firstName();

        // Зберігаємо основні дані для майбутніх тестів
        userData.put("email", email);
        userData.put("password", password);
        userData.put("name", firstName);
        userData.put("title", "Mr");
        userData.put("birth_date", "10");
        userData.put("birth_month", "January");
        userData.put("birth_year", "1990");
        userData.put("firstname", firstName);
        userData.put("lastname", faker.name().lastName());
        userData.put("company", faker.company().name());
        userData.put("address1", faker.address().streetAddress());
        userData.put("address2", faker.address().secondaryAddress());
        userData.put("country", "United States");
        userData.put("zipcode", faker.address().zipCode());
        userData.put("state", faker.address().state());
        userData.put("city", faker.address().city());
        userData.put("mobile_number", faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1, description = "API 11: POST To Create/Register User Account")
    public void testCreateUserAccount() {
        Response response = ApiEndpoints.createAccount(userData);

        response.then()
                .statusCode(200) // API повертає 200
                .body("responseCode", equalTo(201))
                .body("message", equalTo("User created!"));
    }

    @Test(priority = 2, dependsOnMethods = "testCreateUserAccount", description = "API 14: GET user account detail by email")
    public void testGetUserDetailByEmail() {
        Response response = ApiEndpoints.getUserDetailByEmail((String) userData.get("email"));

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("user.name", equalTo(userData.get("name")))
                .body("user.email", equalTo(userData.get("email")));
    }

    @Test(priority = 3, dependsOnMethods = "testCreateUserAccount", description = "API 13: PUT METHOD To Update User Account")
    public void testUpdateUserAccount() {
        // Оновлюємо ім'я та адресу для перевірки
        userData.put("name", faker.name().fullName());
        userData.put("address1", faker.address().streetAddressNumber());

        Response response = ApiEndpoints.updateAccount(userData);

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("message", equalTo("User updated!"));
    }

    @Test(priority = 4, dependsOnMethods = "testCreateUserAccount", description = "API 12: DELETE METHOD To Delete User Account")
    public void testDeleteUserAccount() {
        String email = (String) userData.get("email");
        String password = (String) userData.get("password");

        Response response = ApiEndpoints.deleteAccount(email, password);

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("message", equalTo("Account deleted!"));

        // Фінальна перевірка: переконуємося, що користувача дійсно видалено
        ApiEndpoints.verifyLoginWithInvalidDetails(email, password).then().body("responseCode", equalTo(404));
    }
}