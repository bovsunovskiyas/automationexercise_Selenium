package com.automationexercise.ui;

import com.automationexercise.BrowserFactory;
import com.automationexercise.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void startBrowser(){
        BrowserFactory browserFactory = new BrowserFactory();
        WebDriver driver = browserFactory.getWebDriverInstance();
        BasePage.setDriver(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        BasePage.getDriver().quit();
    }

}
