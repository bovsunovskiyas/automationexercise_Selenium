package com.automationexercise.pages;

import com.automationexercise.helpers.Waiter;
import com.automationexercise.models.ProductDetails;
import org.openqa.selenium.By;

public class ProductDetailsPage extends BasePage {

    private final Waiter waiter = new Waiter(getDriver());

    // --- Locators ---
    private final By productInformationLocator = By.className("product-information");
    private final By productNameLocator = By.xpath("//div[@class='product-information']/h2");
    private final By categoryLocator = By.xpath("//div[@class='product-information']/p[1]");
    private final By priceLocator = By.xpath("//div[@class='product-information']//span/span");
    private final By availabilityLocator = By.xpath("//div[@class='product-information']/p[2]");
    private final By conditionLocator = By.xpath("//div[@class='product-information']/p[3]");
    private final By brandLocator = By.xpath("//div[@class='product-information']/p[4]");

    public ProductDetails getProductDetails() {
        return ProductDetails.builder()
                .name(waiter.waitUntilVisibleOfElementLocated(productNameLocator).getText())
                .category(waiter.waitUntilVisibleOfElementLocated(categoryLocator).getText())
                .price(waiter.waitUntilVisibleOfElementLocated(priceLocator).getText())
                .availability(waiter.waitUntilVisibleOfElementLocated(availabilityLocator).getText())
                .condition(waiter.waitUntilVisibleOfElementLocated(conditionLocator).getText())
                .brand(waiter.waitUntilVisibleOfElementLocated(brandLocator).getText())
                .build();
    }

    public ProductDetailsPage assertProductDetailPageLoaded() {
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