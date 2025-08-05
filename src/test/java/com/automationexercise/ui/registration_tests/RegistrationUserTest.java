package com.automationexercise.ui.registration_tests;

import com.automationexercise.helpers.DataRandomizer;
import com.automationexercise.models.UserRegistrationDetails;
import com.automationexercise.pages.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automationexercise.ui.BaseTest;

import static com.automationexercise.helpers.DataRandomizer.*;

public class RegistrationUserTest extends BaseTest {

    @Severity(SeverityLevel.BLOCKER)
    @Feature(value = "SignUp")
    @Test(description = "Test Case 1: Register User with valid details and verify account is created and deleted successfully")
    public void registerUserFlowTest() {

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

        SignUpPage signUpPage = homePage
                .openMainPage()
                .assertMainPageSuccessfullyLoaded()
                .getMainMenu()
                .clickLoginButton();

        SignupEnterAccountInfoPage signupEnterAccountInfoPage = signUpPage
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

    @Severity(SeverityLevel.CRITICAL)
    @Feature(value = "SignUp")
    @Test(description = "Test Case 5: Register User with existing email")
    public void RegisterWithExistingEmailTest() {

        SignUpPage signUpPage = new HomePage()
                .openMainPage()
                .assertMainPageSuccessfullyLoaded()
                .getMainMenu()
                .clickLoginButton()
                .assertLoginPageSuccessfullyLoaded()
//                .enterLoginEmail(SecretsManager.get("USER_EMAIL"))
//                .enterLoginPassword(SecretsManager.get("USER_PASSWORD"))
                .enterSignupName("burt.abernathy")
                .enterSignupEmail("780a_burt.abernathy@gmail.com")
                .clickSignUpButtonIncorrect();

//        .isAlreadyExistSignUpErrorVisible();

        Assert.assertTrue(signUpPage.isAlreadyExistSignUpErrorVisible(), "Error message is not visible.");


    }
}