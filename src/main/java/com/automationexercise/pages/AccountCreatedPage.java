package com.automationexercise.pages;

import com.automationexercise.components.MainMenu;
import com.automationexercise.helpers.Waiter;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage {

    @Getter
    private final MainMenu mainMenu = new MainMenu();
    private final Waiter waiter = new Waiter(getDriver());

    // --- Locators ---
    private final By accountCreatedHeaderLocator = By.cssSelector("h2[data-qa='account-created']");
    private final By continueButtonLocator = By.cssSelector("a[data-qa='continue-button']");


    public String getAccountCreatedText() {
        return waiter.waitUntilVisibleOfElementLocated(accountCreatedHeaderLocator).getText();
    }

    public HomePage clickContinueButton() {
        waiter.waitUntilClickable(continueButtonLocator).click();
        return new HomePage();
    }

}
