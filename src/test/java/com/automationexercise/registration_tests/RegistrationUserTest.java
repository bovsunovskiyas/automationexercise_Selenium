package com.automationexercise.registration_tests;

import com.automationexercise.helpers.DataRandomizer;
import com.automationexercise.models.UserRegistrationDetails;
import com.automationexercise.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automationexercise.BaseTest;

import static com.automationexercise.helpers.DataRandomizer.*;

public class RegistrationUserTest extends BaseTest {

    //Test Case 1: Register User
    @Test
    public void testRegisterUserFlow() {

        String[] randomBirtDate = DataRandomizer.getRandomBirthDate();
        String randomFirstName = DataRandomizer.getRandomFirstName();
        String randomLastName = DataRandomizer.getRandomLastName();

        UserRegistrationDetails user = UserRegistrationDetails.builder()
                .gender(getRandomTitle())
                .email(getRandomEmail())
                .password(getRandomPassword())
                .day(randomBirtDate[0])
                .month("May")
                .year(randomBirtDate[2])
                .firstName(randomFirstName)
                .lastName(randomLastName)
                .company(getRandomCompany())
                .address1(getRandomAddress1())
                .address2(getRandomAddress2())
                .country(getRandomCountry())
                .state(getRandomState())
                .city(getRandomCity())
                .zipcode(getRandomZipcode())
                .phone(getRandomPhone()).build();

        HomePage homePage = new HomePage();

        LoginPage loginPage = homePage
                .openMainPage()
                .assertMainPageSuccessfullyLoaded()
                .getMainMenu()
                .clickLoginButton();

        SignupEnterAccountInfoPage signupEnterAccountInfoPage = loginPage
                .assertLoginPageSuccessfullyLoaded()
                .enterSignupName(user.getFirstName() + " " + user.getLastName())
                .enterSignupEmail(user.getEmail())
                .clickSignUpButton();

        AccountCreatedPage accountCreatedPage = signupEnterAccountInfoPage
                .assertEnterAccountInfoPageSuccessfullyLoaded()
                .clickTitleRadioButton(user.getGender())
                .fillPassword(user.getPassword())
//        Assert.assertTrue(createAccountPage.isEnterAccountInfoVisible(), "'ENTER ACCOUNT INFORMATION' is not visible.");
//              ("15", "May", "1995")
                .selectDay(user.getDay())
                .selectMonth(user.getMonth())
                .selectYear(user.getYear())
                .clickNewsletterCheckbox()
                .clickSpecialOffersCheckbox()
                .fillFirstName(user.getFirstName())
                .fillLastName(user.getLastName())
                .fillCompany(user.getCompany())
                .fillAddress1(user.getAddress1())
                .fillAddress2(user.getAddress2())
                .selectCountry("Canada")
                .fillState(user.getState())
                .fillCity(user.getCity())
                .fillZipcode(user.getZipcode())
                .fillMobileNumber(user.getPhone())
                .clickCreateAccountButton();

        Assert.assertEquals(accountCreatedPage.getAccountCreatedText(), "ACCOUNT CREATED!");
        accountCreatedPage.clickContinueButton();
        Assert.assertTrue(homePage.getMainMenu().getLoggedInAsText().contains(user.getFirstName() + " " + user.getLastName()), "Logged in username does not match.");

        AccountDeletedPage accountDeletedPage =
                homePage
                        .getMainMenu()
                        .deleteAccount();
        Assert.assertEquals(accountDeletedPage.getAccountDeletedText(), "ACCOUNT DELETED!");

        accountDeletedPage.clickContinueButton();
        homePage.assertMainPageSuccessfullyLoaded();
    }
}