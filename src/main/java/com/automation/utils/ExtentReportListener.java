package com.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

/**
 * TestNG listener that automatically generates an Extent HTML report.
 *
 * Screenshots are attached automatically when a test fails.
 *
 * Compatible with Java 11.
 *
 * @author Banoth Mahesh Kumar
 */
public class ExtentReportListener
        implements ITestListener, ISuiteListener {

    private static ExtentReports extent;

    private static final ThreadLocal<ExtentTest> test =
            new ThreadLocal<ExtentTest>();

    @Override
    public void onStart(ISuite suite) {

        File reportsDirectory =
                new File("reports");

        if (!reportsDirectory.exists()) {
            reportsDirectory.mkdirs();
        }

        ExtentSparkReporter spark =
                new ExtentSparkReporter(
                        "reports/ExtentReport.html"
                );

        spark.config().setTheme(Theme.DARK);

        spark.config().setDocumentTitle(
                "Selenium Automation Report"
        );

        spark.config().setReportName(
                "Test Execution Report - SauceDemo"
        );

        extent = new ExtentReports();

        extent.attachReporter(spark);

        extent.setSystemInfo(
                "Tester",
                "Banoth Mahesh Kumar"
        );

        extent.setSystemInfo(
                "Application",
                "SauceDemo"
        );

        extent.setSystemInfo(
                "Environment",
                "QA"
        );
    }

    @Override
    public void onFinish(ISuite suite) {

        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {

        String methodName =
                result.getMethod().getMethodName();

        String description =
                result.getMethod().getDescription();

        ExtentTest extentTest =
                extent.createTest(
                        methodName,
                        description
                );

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest extentTest =
                test.get();

        if (extentTest != null) {

            extentTest.log(
                    Status.PASS,
                    "Test Passed"
            );
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest extentTest =
                test.get();

        if (extentTest != null) {

            extentTest.log(
                    Status.FAIL,
                    result.getThrowable()
            );

            if (DriverManager.getDriver() != null) {

                String screenshot =
                        ScreenshotUtils.capture(
                                DriverManager.getDriver(),
                                result.getName()
                        );

                if (screenshot != null
                        && !screenshot.isEmpty()) {

                    extentTest.addScreenCaptureFromPath(
                            screenshot,
                            "Failure Screenshot"
                    );
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest extentTest =
                test.get();

        if (extentTest != null) {

            extentTest.log(
                    Status.SKIP,
                    "Test Skipped: "
                            + result.getThrowable()
            );
        }
    }
}
