package com.automationexercise.ui;

import com.automationexercise.BrowserFactory;
import com.automationexercise.helpers.Browser;
import com.automationexercise.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
//    @Step("Start browser before each test")
    public synchronized void startBrowser(String browser){
        Browser browserValue = Browser.valueOf(browser);
        BrowserFactory browserFactory = new BrowserFactory();
        WebDriver driver = browserFactory.getWebDriverInstance(browserValue);
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

