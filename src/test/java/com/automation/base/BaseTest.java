package com.automation.base;

import com.automation.config.ConfigReader;
import com.automation.utils.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base test class.
 *
 * Initializes WebDriver before every test method
 * and closes WebDriver after every test method.
 *
 * Java 11 compatible.
 *
 * @author Banoth Mahesh Kumar
 */
public class BaseTest {

    @BeforeMethod
    public void setUp() {

        DriverManager.initDriver();

        if (DriverManager.getDriver() == null) {
            throw new IllegalStateException(
                    "WebDriver initialization failed."
            );
        }

        DriverManager.getDriver().get(
                ConfigReader.getInstance()
                        .getBaseUrl()
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        DriverManager.quitDriver();
    }
}
