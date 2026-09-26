package com.automation.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = DriverManager.getDriver();

        String screenshotPath =
                ScreenshotUtils.capture(
                        driver,
                        result.getName()
                );

        if (screenshotPath != null) {
            System.out.println(
                    "Screenshot captured: " + screenshotPath
            );
        }
    }
}
