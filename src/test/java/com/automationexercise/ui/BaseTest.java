package com.automationexercise.ui;

import com.automationexercise.BrowserFactory;
import com.automationexercise.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
//    @Step("Start browser before each test")
    public synchronized void startBrowser(){
        BrowserFactory browserFactory = new BrowserFactory();
        WebDriver driver = browserFactory.getWebDriverInstance();
        BasePage.setDriver(driver);
    }

    @AfterMethod(alwaysRun = true)
//    @Step("Close browser after each test")
    public void closeBrowser() {
        BasePage.getDriver().quit();
//        if (BasePage.getThreadLocalDriver() != null) {
//            BasePage.getDriver().quit();
//        }

    }
}

