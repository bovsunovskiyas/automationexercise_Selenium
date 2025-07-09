package com.automationexercise;

import com.automationexercise.pages.BasePage;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.automationexercise.helpers.Waiter;

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
