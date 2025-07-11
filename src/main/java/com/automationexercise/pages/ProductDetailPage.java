package com.automationexercise.pages;

import com.automationexercise.helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDetailPage extends BasePage {

    private final Waiter waiter = new Waiter(getDriver());

    // --- Locators ---
    private final By productInformationLocator = By.className("product-information");
    private final By productNameLocator = By.xpath("//div[@class='product-information']//h2");
    private final By categoryLocator = By.xpath("//div[@class='product-information']//p[contains(text(), 'Category:')]");
    private final By priceLocator = By.xpath("//div[@class='product-information']//span/span");
    private final By availabilityLocator = By.xpath("//div[@class='product-information']//b[text()='Availability:']");
    private final By conditionLocator = By.xpath("//div[@class='product-information']//b[text()='Condition:']");
    private final By brandLocator = By.xpath("//div[@class='product-information']//b[text()='Brand:']");


    public ProductDetailPage assertProductDetailPageLoaded() {
        waiter.waitUntilVisibleOfElementLocated(productInformationLocator);
        return this;
    }

    public boolean isProductNameVisible() {
        return waiter.waitUntilVisibleOfElementLocated(productNameLocator).isDisplayed();
    }

    public boolean isCategoryVisible() {
        return waiter.waitUntilVisibleOfElementLocated(categoryLocator).isDisplayed();
    }

    public boolean isPriceVisible() {
        return waiter.waitUntilVisibleOfElementLocated(priceLocator).isDisplayed();
    }

    public boolean isAvailabilityVisible() {
        return waiter.waitUntilVisibleOfElementLocated(availabilityLocator).isDisplayed();
    }

    public boolean isConditionVisible() {
        return waiter.waitUntilVisibleOfElementLocated(conditionLocator).isDisplayed();
    }

    public boolean isBrandVisible() {
        return waiter.waitUntilVisibleOfElementLocated(brandLocator).isDisplayed();
    }
}