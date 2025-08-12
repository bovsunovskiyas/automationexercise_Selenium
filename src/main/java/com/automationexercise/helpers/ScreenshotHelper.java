package com.automationexercise.helpers;

import com.automationexercise.BrowserFactory;
import com.automationexercise.pages.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotHelper {
    private static final String SCREENSHOT_PATH = "src/screenshots/";

    public static void takeScreenshot(WebDriver driver, String fileName) {
        File screenshot = ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(SCREENSHOT_PATH + fileName +".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
