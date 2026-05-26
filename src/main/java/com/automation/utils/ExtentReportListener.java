package com.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;

/**
 * TestNG listener that auto-generates an Extent HTML report.
 * Screenshots are attached automatically on test failure.
 *
 * @author Banoth Mahesh Kumar
 */
public class ExtentReportListener implements ITestListener, ISuiteListener {

    private static ExtentReports         extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Selenium Automation Report");
        spark.config().setReportName("Test Execution Report — SauceDemo");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester",      "Banoth Mahesh Kumar");
        extent.setSystemInfo("Application", "SauceDemo");
        extent.setSystemInfo("Environment", "QA");
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed ✔");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, result.getThrowable());
        String screenshot = ScreenshotUtils.capture(
                DriverManager.getDriver(), result.getName());
        if (!screenshot.isEmpty()) {
            test.get().addScreenCaptureFromPath(screenshot, "Failure Screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }
}
