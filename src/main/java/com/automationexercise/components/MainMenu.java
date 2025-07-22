package com.automationexercise.components;

import com.automationexercise.helpers.Waiter;
import com.automationexercise.pages.AccountDeletedPage;
import com.automationexercise.pages.BasePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.pages.ProductsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MainMenu {

    private final Waiter waiter = new Waiter(BasePage.getDriver());

    // --- Locators ---
    private final By productsButtonLocator = By.xpath("//a[@href='/products']");
    private final By signupLoginButtonLocator = By.xpath("//a[contains(text(),'Signup / Login')]");
    private final By loggedInAsLocator = By.xpath("//a[contains(text(),'Logged in as')]");
    private final By logoutLocator = By.xpath("//a[@href='/logout']");
    private final By deleteAccountButtonLocator = By.xpath("//a[contains(text(),'Delete Account')]");


    @Step("Click 'Signup / Login' button")
    public LoginPage clickLoginButton() {
        waiter.waitUntilClickable(signupLoginButtonLocator).click();
        return new LoginPage();
    }

    @Step("Click 'Products' button")
    public ProductsPage clickProductsButton() {
        waiter.waitUntilClickable(productsButtonLocator).click();
        return new ProductsPage();
    }

    @Step("Click 'Logout' button")
    public LoginPage clickLogoutButton() {
        waiter.waitUntilClickable(logoutLocator).click();
        return new LoginPage();
    }

    @Step("Click 'Delete Account' button")
    public AccountDeletedPage deleteAccount() {
        waiter.waitUntilClickable(deleteAccountButtonLocator).click();
        return new AccountDeletedPage();
    }

    @Step("Get logged in user text")
    public String getLoggedInAsText() {
        return waiter.waitUntilVisibleOfElementLocated(loggedInAsLocator).getText();
    }
}
