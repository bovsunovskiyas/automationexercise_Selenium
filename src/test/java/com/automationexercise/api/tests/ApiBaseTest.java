package com.automationexercise.api.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class ApiBaseTest {

    @BeforeClass
    public void setup() {
        // Встановлюємо базовий URI для всіх API запитів у цьому класі
        RestAssured.baseURI = "https://automationexercise.com/api";
    }
}
