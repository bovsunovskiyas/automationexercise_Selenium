package com.automationexercise.pages;

import com.automationexercise.components.MainMenu;
import com.automationexercise.helpers.Waiter;
import lombok.Getter;
import org.openqa.selenium.By;

public class AccountDeletedPage extends BasePage {

    @Getter
    private final MainMenu mainMenu = new MainMenu();
    private final Waiter waiter = new Waiter(getDriver());

    // --- Locators ---
    private final By accountDeletedHeaderLocator = By.cssSelector("h2[data-qa='account-deleted']");
    private final By continueButtonLocator = By.cssSelector("a[data-qa='continue-button']");


    public String getAccountDeletedText() {
        return waiter.waitUntilVisibleOfElementLocated(accountDeletedHeaderLocator).getText();
    }

    public HomePage clickContinueButton() {
        waiter.waitUntilClickable(continueButtonLocator).click();
        return new HomePage();
    }
}
