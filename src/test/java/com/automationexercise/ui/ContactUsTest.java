package com.automationexercise.ui;

import com.automationexercise.helpers.DataRandomizer;
import com.automationexercise.pages.ContactUsPage;
import com.automationexercise.pages.HomePage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactUsTest extends BaseTest{





    @Severity(SeverityLevel.TRIVIAL)
    @Feature(value = "Contact us")
    @Test(description = "Test Case 6: Contact Us Form")
    @Step("Verify ")
    public void contactUsTest() {

        String randomFirstName = DataRandomizer.getRandomFirstName();
        String randomEmail = DataRandomizer.getRandomEmail();
        String randomSubject = DataRandomizer.getRandomSubject();
        String randomMessage = DataRandomizer.getRandomMessage();

        //    Test Case 6: Contact Us Form
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        //3. Verify that home page is visible successfully
        //4. Click on 'Contact Us' button

        HomePage homePage = new HomePage();

        ContactUsPage contactUsPage = homePage
                .openMainPage()
                .assertMainPageSuccessfullyLoaded()
                .getMainMenu()
                .clickContactUsButton()
                .assertContactUsPageSuccessfullyLoaded();

        contactUsPage
                .assertGetInTouchHeaderIsVisible()
        //6. Enter name, email, subject and message
        ;

//5. Verify 'GET IN TOUCH' is visible
//6. Enter name, email, subject and message
//7. Upload file
//8. Click 'Submit' button
//9. Click OK button
//10. Verify success message 'Success! Your details have been submitted successfully.' is visible
//11. Click 'Home' button and verify that landed to home page successfully

    }
}
