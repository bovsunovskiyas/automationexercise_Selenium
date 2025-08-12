package com.automationexercise.helpers;

import com.automationexercise.pages.BasePage;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

public class MyTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Date timestamp = new Date();
//        "hh:mm:ss"
        String timestampString = String.format("%tT", timestamp);
        String testName = result.getName();
        String fileName = timestampString.replace(":","-") + "_" + testName;
        ScreenshotHelper.takeScreenshot(BasePage.getDriver(), fileName);
    }
}
