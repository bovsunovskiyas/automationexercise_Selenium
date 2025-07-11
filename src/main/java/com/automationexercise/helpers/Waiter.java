package com.automationexercise.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {

    private final WebDriver driver;
    private final Duration defaultTimeout = Duration.ofSeconds(5);

    // Конструктор тепер приймає тільки драйвер
    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilUrlToBe(String expectedUrl) {
        new WebDriverWait(driver, defaultTimeout)
                .until(ExpectedConditions.urlToBe(expectedUrl));
    }

    public void waitUntilUrlToBe(String expectedUrl, Duration duration) {
        new WebDriverWait(driver, duration)
                .until(ExpectedConditions.urlToBe(expectedUrl));
    }

    // --- Методи очікування ---
    /**
     * Очікує, поки елемент не стане видимим на сторінці.
     * @param locator The By locator of the element.
     * @return The WebElement once it is visible.
     */
    public WebElement waitUntilVisibleOfElementLocated(By locator) {
        // WebDriverWait створюється тут, використовуючи локальні налаштування
        return new WebDriverWait(driver, defaultTimeout)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilVisibleOfElementLocated(By locator, Duration duration) {
        // WebDriverWait створюється тут, використовуючи локальні налаштування
        return new WebDriverWait(driver, duration)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Очікує, поки елемент не стане клікабельним на сторінці.
     * @param locator The By locator of the element.
     * @return The WebElement once it is clickable.
     */
    public WebElement waitUntilClickable(By locator) {
        return new WebDriverWait(driver, defaultTimeout)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Очікує, поки URL сторінки не буде містити очікуваний рядок.
     *
     * @param expectedUrl The expected URL substring.
     */
    public Boolean waitUntilUrlContains(String expectedUrl) {
        return new WebDriverWait(driver, defaultTimeout)
                .until(ExpectedConditions.urlContains(expectedUrl));
    }



    /**
     * Очікує, поки текст елемента не буде містити очікуваний рядок.
     *
     * @param locator      The By locator of the element.
     * @param expectedText The text expected to be present in the element.
     */
    public Boolean waitUntilTextContains(By locator, String expectedText) {
        return new WebDriverWait(driver, defaultTimeout)
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    }


    public void waitUntilNumberOfElementsToBe(By locator, int expectedCount) {
        new WebDriverWait(driver, defaultTimeout)
                .until(ExpectedConditions.numberOfElementsToBe(locator, expectedCount));
    }

}
