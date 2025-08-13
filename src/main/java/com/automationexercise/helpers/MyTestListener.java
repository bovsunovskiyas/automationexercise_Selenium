package com.automationexercise.helpers;

import com.automationexercise.pages.BasePage;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyTestListener implements ITestListener {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    @Override
    public void onTestFailure(ITestResult result) {
        result.setStatus(ITestResult.FAILURE);
        if (BasePage.getDriver() != null){
            String testName = result.getName();

            String pattern = "dd.MM.yyyy_HH-mm-ss";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            String timestampString = sdf.format(new Date());
            String timestamp2 = timestampString.toLowerCase();

            String fileName = timestampString.replace(":", "-") + "_" + testName;
            ScreenshotHelper.takeScreenshot(BasePage.getDriver(), fileName);

            byte[] screenshot = ((TakesScreenshot) BasePage.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    "Screenshot - " + timestamp2 + " - " + testName,
                    new ByteArrayInputStream(screenshot)
            );


            String pageSource = BasePage.getDriver().getPageSource();
            Allure.addAttachment("Page Source", "text/html", pageSource, ".html");

        }


    }
}

