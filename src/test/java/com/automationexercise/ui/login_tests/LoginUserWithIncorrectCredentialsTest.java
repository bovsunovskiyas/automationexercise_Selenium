package com.automationexercise.ui.login_tests;

import com.automationexercise.helpers.DataRandomizer;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.SignUpPage;
import com.automationexercise.ui.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginUserWithIncorrectCredentialsTest extends BaseTest {

    @Severity(SeverityLevel.MINOR)
    @Feature(value = "Auth")
    @Test(description = "Test Case 3: Login User with incorrect email and password")
    public void loginUserWithIncorrectCredentialsTest(){

        String incorrectEmail = DataRandomizer.getRandomEmail();
        String incorrectPassword = DataRandomizer.getRandomPassword();
//        Assert.assertTrue(homePage.isLogoVisible(), "Home page is not visible.");

        HomePage homePage = new HomePage();
        homePage
                .openMainPage()
                .assertMainPageSuccessfullyLoaded()
                .getMainMenu()
                .clickLoginButton()
                .assertLoginPageSuccessfullyLoaded()
                .enterLoginEmail(incorrectEmail)
                .enterLoginPassword(incorrectPassword)
                .clickLoginButton();

        SignUpPage signUpPage = new SignUpPage();
//        loginPage.isIncorrectLoginErrorVisible();
        Assert.assertTrue(signUpPage.isIncorrectLoginErrorVisible(), "Error message is not visible.");

    }
}

//    @Test
//    public void testLoginUserWithIncorrectCredentials() {
//
//        // Створюємо екземпляр HomePage, передаючи залежності з BaseTest
//        MainPage mainPage = new MainPage(driver, waiter);
//        Assert.assertTrue(mainPage.isLogoVisible(), "Home page is not visible.");
//
//        // Переходимо на сторінку логіну
//        LoginPage loginPage = mainPage.goToLoginPage();
//
//        // Генеруємо некоректні дані
//        String incorrectEmail = faker.internet().emailAddress();
//        String incorrectPassword = faker.internet().password();
//
//        // Виконуємо спробу входу
//        loginPage.login(incorrectEmail, incorrectPassword);
//
//        // Перевіряємо, чи з'явилося повідомлення про помилку
//        Assert.assertTrue(loginPage.isIncorrectLoginErrorVisible(), "Error message is not visible.");
//    }
//

//    }
