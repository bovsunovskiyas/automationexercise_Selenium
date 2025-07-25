package com.automationexercise.pages;

import com.automationexercise.helpers.Waiter;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    private final Waiter waiter = new Waiter(BasePage.getDriver());

    // Locators
    protected final By logoMainLocator = By.xpath("//img[contains(@src, 'logo.png')]");
    protected final By scrollToFirstProductElement = By.xpath("//img[contains(@src, '/get_product_picture/1')]");

    @Getter
    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    @Step("Scroll to the first product element")
    public void scrollToFirstProductElement(){
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", waiter.waitUntilVisibleOfElementLocated(scrollToFirstProductElement));
    }


//    // ThreadLocal для безпечного зберігання драйвера в багатопотоковому середовищі
//    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
//
//    // Метод для встановлення драйвера
//    public static void setDriver(WebDriver driver) {
//        driverThreadLocal.set(driver);
//    }
//
//    // Метод для отримання драйвера
//    public static WebDriver getDriver() {
//        return driverThreadLocal.get();
//    }

}
