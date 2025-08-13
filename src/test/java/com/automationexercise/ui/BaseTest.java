package com.automationexercise.ui;

import com.automationexercise.BrowserFactory;
import com.automationexercise.helpers.Browser;
import com.automationexercise.pages.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void closeBrowser(ITestResult result) {
//        BasePage.getDriver().quit();
//        if (BasePage.getThreadLocalDriver() != null) {
//            BasePage.getDriver().quit();
//        }

//
//    }
//}

        String pattern = "dd.MM.yyyy_HH-mm-ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String timestampString = sdf.format(new Date());

        if (result.getStatus() == ITestResult.FAILURE && BasePage.getDriver() != null) {
            byte[] screenshot = ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                    "Allure_" + timestampString + "_" + result.getName(),
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );

            String pageSource = BasePage.getDriver().getPageSource();
            Allure.addAttachment("Page Source", "text/html", pageSource, ".html");

        }

        if (BasePage.getDriver() != null) {
            BasePage.getDriver().quit();
            BasePage.setDriver(null); // очищаємо ThreadLocal
        }
    }
}

