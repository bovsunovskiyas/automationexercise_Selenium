package com.automationexercise.components;

import com.automationexercise.helpers.Waiter;
import com.automationexercise.pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenu {

    private static final Logger logger = LoggerFactory.getLogger(MainMenu.class);
    private final Waiter waiter = new Waiter(BasePage.getDriver());

    // --- Locators ---
    private final By productsButtonLocator = By.xpath("//a[@href='/products']");
    private final By signupLoginButtonLocator = By.xpath("//a[contains(text(),'Signup / Login')]");
    private final By loggedInAsLocator = By.xpath("//a[contains(text(),'Logged in as')]");
    private final By logoutLocator = By.xpath("//a[@href='/logout']");
    private final By deleteAccountButtonLocator = By.xpath("//a[contains(text(),'Delete Account')]");
    private final By contactUsLocator = By.xpath("//a[@href='/contact_us']");




    @Step("Click 'Signup / Login' button")
    public SignUpPage clickLoginButton() {
        logger.info("Clicking 'Signup / Login' button");
        waiter.waitUntilClickable(signupLoginButtonLocator).click();
        return new SignUpPage();
    }

    @Step("Click 'Products' button")
    public ProductsPage clickProductsButton() {
        waiter.waitUntilClickable(productsButtonLocator).click();
        return new ProductsPage();
    }

    @Step("Click 'Logout' button")
    public SignUpPage clickLogoutButton() {
        waiter.waitUntilClickable(logoutLocator).click();
        return new SignUpPage();
    }

    @Step("Click 'Delete Account' button")
    public AccountDeletedPage deleteAccount() {
        waiter.waitUntilClickable(deleteAccountButtonLocator).click();
        return new AccountDeletedPage();
    }

    @Step("Click 'Contact us' button")
    public ContactUsPage clickContactUsButton() {
        waiter.waitUntilClickable(contactUsLocator).click();
        return new ContactUsPage();
    }

    @Step("Get logged in user text")
    public String getLoggedInAsText() {
        logger.info("Getting logged in user text");
        return waiter.waitUntilVisibleOfElementLocated(loggedInAsLocator).getText();
    }
}
