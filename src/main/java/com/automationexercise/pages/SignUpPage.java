package com.automationexercise.pages;

import com.automationexercise.components.MainMenu;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import com.automationexercise.helpers.Waiter;

public class SignUpPage extends BasePage{

    @Getter
    private final MainMenu mainMenu = new MainMenu();
    private final Waiter waiter = new Waiter(getDriver());

    // --- Locators ---
    private final By newUserSignupHeaderLocator = By.xpath("//h2[text()='New User Signup!']");
    private final By signupNameInputLocator = By.cssSelector("input[data-qa='signup-name']");
    private final By signupEmailInputLocator = By.cssSelector("input[data-qa='signup-email']");
    private final By signupButtonLocator = By.cssSelector("button[data-qa='signup-button']");
    private final By loginEmailInputLocator = By.cssSelector("input[data-qa='login-email']");
    private final By loginPasswordInputLocator = By.cssSelector("input[data-qa='login-password']");
    private final By loginButtonLocator = By.cssSelector("button[data-qa='login-button']");
    private final By incorrectLoginErrorMessageLocator = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");
    private final By incorrectSignUpErrorMessageLocator = By.xpath("//p[contains(text(),'Email Address already exist!')]");



    @Step("Verify Login Page is successfully loaded")
    public SignUpPage assertLoginPageSuccessfullyLoaded(){
        waiter.waitUntilVisibleOfElementLocated(newUserSignupHeaderLocator);
        return this;
    }

    @Step("Enter login email: {email}")
    public SignUpPage enterLoginEmail(String email) {
        waiter.waitUntilVisibleOfElementLocated(loginEmailInputLocator).sendKeys(email);
        return this;
    }

    @Step("Enter login password")
    public SignUpPage enterLoginPassword(String password) {
        waiter.waitUntilVisibleOfElementLocated(loginPasswordInputLocator).sendKeys(password);
        return this;
    }

    @Step("Click on Login button")
    public HomePage clickLoginButton() {
        waiter.waitUntilClickable(loginButtonLocator).click();
        return new HomePage();
    }

    @Step("Click on Signup button")
    public SignupEnterAccountInfoPage clickSignUpButton() {
        waiter.waitUntilClickable(signupButtonLocator).click();
        return new SignupEnterAccountInfoPage();
    }

    @Step("Click on Signup button")
    public SignUpPage clickSignUpButtonIncorrect() {
        waiter.waitUntilClickable(signupButtonLocator).click();
        return this;
    }

    @Step("Check if 'New User Signup!' header is visible")
    public boolean isNewUserSignupVisible() {
        return waiter.waitUntilVisibleOfElementLocated(newUserSignupHeaderLocator).isDisplayed();
    }

    @Step("Enter signup name: {name}")
    public SignUpPage enterSignupName(String name) {
        waiter.waitUntilVisibleOfElementLocated(signupNameInputLocator).sendKeys(name);
        return this;
    }

    @Step("Enter signup email: {email}")
    public SignUpPage enterSignupEmail(String email) {
        waiter.waitUntilVisibleOfElementLocated(signupEmailInputLocator).sendKeys(email);
        return this;
    }

    @Step("Check that incorrect login error message is visible")
    public boolean isIncorrectLoginErrorVisible() {
        return waiter.waitUntilVisibleOfElementLocated(incorrectLoginErrorMessageLocator).isDisplayed();
    }

    @Step("Check that already exist signup error message is visible")
    public boolean isAlreadyExistSignUpErrorVisible() {
        return waiter.waitUntilVisibleOfElementLocated(incorrectSignUpErrorMessageLocator).isDisplayed();
//        return this;
    }

}