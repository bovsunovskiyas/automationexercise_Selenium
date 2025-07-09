package com.automationexercise.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    // Locators
    protected final By logoMainLocator = By.xpath("//img[contains(@src, 'logo.png')]");

    @Getter
    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }




}
