package com.automationexercise.pages;

import com.automationexercise.helpers.Waiter;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ContactUsPage extends BasePage{

    private final Waiter waiter = new Waiter(getDriver());

    // --- Locators ---
    private final By contactUsHeaderLocator = By.xpath("//h2[contains(text(),'Contact')]");
    private final By getInTouchHeaderLocator = By.xpath("//h2[contains(text(),'Get In Touch')]");

    private final By nameInputLocator = By.cssSelector("input[data-qa='name']");
    private final By emailInputLocator = By.cssSelector("input[data-qa='email']");
    private final By subjectInputLocator = By.cssSelector("input[data-qa='subject']");
    private final By messageInputLocator = By.cssSelector("textarea[data-qa='message']");


    private final By submitButtonLocator = By.cssSelector("button[data-qa='submit-button']");




    @Step("Assert 'Contact us' page is loaded")
    public ContactUsPage assertContactUsPageSuccessfullyLoaded(){
        waiter.waitUntilVisibleOfElementLocated(contactUsHeaderLocator);
        return this;
    }

    @Step("Assert 'Get In Touch' header is visible")
    public void assertGetInTouchHeaderIsVisible(){
        waiter.waitUntilVisibleOfElementLocated(getInTouchHeaderLocator).isDisplayed();

    }

//    @Step("Check that incorrect login error message is visible")
//    public boolean isIncorrectLoginErrorVisible() {
//        return waiter.waitUntilVisibleOfElementLocated(incorrectLoginErrorMessageLocator).isDisplayed();
//    }

}
