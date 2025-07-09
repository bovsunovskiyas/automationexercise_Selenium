package com.automationexercise.login_tests;

import com.automationexercise.helpers.DataRandomizer;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import com.automationexercise.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginUserWithIncorrectCredentialsTest extends BaseTest {

//    //Test Case 3: Login User with incorrect email and password
    @Test
    public void testLoginUserWithIncorrectCredentialsTest(){

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

        LoginPage loginPage = new LoginPage();
//        loginPage.isIncorrectLoginErrorVisible();
        Assert.assertTrue(loginPage.isIncorrectLoginErrorVisible(), "Error message is not visible.");



    }



}
