package com.automationexercise.pages;

import com.automationexercise.helpers.Waiter;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public abstract class BasePage {


    protected final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Getter @Setter
    private final Waiter waiter = new Waiter(BasePage.getDriver());
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Locators
    protected final By logoMainLocator = By.xpath("//img[contains(@src, 'logo.png')]");
    protected final By scrollToFirstProductElement = By.xpath("//img[contains(@src, '/get_product_picture/1')]");

//    @Getter
//    private static WebDriver driver;

    public static ThreadLocal<WebDriver> getThreadLocalDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        BasePage.driver.set(driver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @Step("Scroll to the first product element")
    public void scrollToFirstProductElement(){
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", waiter.waitUntilVisibleOfElementLocated(scrollToFirstProductElement));
    }
}