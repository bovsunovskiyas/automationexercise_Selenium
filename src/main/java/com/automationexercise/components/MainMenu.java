package com.automationexercise.components;

import com.automationexercise.helpers.Waiter;
import com.automationexercise.pages.AccountDeletedPage;
import com.automationexercise.pages.BasePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.pages.ProductsPage;
import org.openqa.selenium.By;

public class MainMenu {

    private final Waiter waiter = new Waiter(BasePage.getDriver());

    // --- Locators ---
    private final By productsButtonLocator = By.xpath("//a[@href='/products']");
    private final By signupLoginButtonLocator = By.xpath("//a[contains(text(),'Signup / Login')]");
    private final By loggedInAsLocator = By.xpath("//a[contains(text(),'Logged in as')]");
    private final By logoutLocator = By.xpath("//a[@href='/logout']");
    private final By deleteAccountButtonLocator = By.xpath("//a[contains(text(),'Delete Account')]");

    public LoginPage clickLoginButton() {
        waiter.waitUntilClickable(signupLoginButtonLocator).click();
        return new LoginPage();
    }

    public ProductsPage clickProductsButton() {
        waiter.waitUntilClickable(productsButtonLocator).click();
        return new ProductsPage();
    }

    public LoginPage clickLogoutButton() {
        waiter.waitUntilClickable(logoutLocator).click();
        return new LoginPage();
    }

    public AccountDeletedPage deleteAccount() {
        waiter.waitUntilClickable(deleteAccountButtonLocator).click();
        return new AccountDeletedPage();
    }

    public String getLoggedInAsText() {
        return waiter.waitUntilVisibleOfElementLocated(loggedInAsLocator).getText();
    }

}
