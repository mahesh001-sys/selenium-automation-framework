package com.automation.base;

import com.automation.config.ConfigReader;
import com.automation.utils.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base test class.
 *
 * Handles WebDriver setup and cleanup
 * for every TestNG test method.
 *
 * Compatible with Java 11.
 *
 * @author Banoth Mahesh Kumar
 */
public class BaseTest {

    /**
     * Runs before every test method.
     */
    @BeforeMethod
    public void setUp() {

        DriverManager.initDriver();

        if (DriverManager.getDriver() == null) {
            throw new RuntimeException(
                    "WebDriver initialization failed"
            );
        }

        DriverManager.getDriver().get(
                ConfigReader.getInstance().getBaseUrl()
        );
    }

    /**
     * Runs after every test method.
     */
    @AfterMethod
    public void tearDown() {

        DriverManager.quitDriver();
    }
}
