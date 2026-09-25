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

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        System.out.println(
                "===== BaseTest.setUp() EXECUTED ====="
        );

        System.out.println(
                "===== Initializing WebDriver ====="
        );

        DriverManager.initDriver();

        if (DriverManager.getDriver() == null) {

            throw new IllegalStateException(
                    "WebDriver initialization failed."
            );
        }

        System.out.println(
                "===== WebDriver initialized successfully ====="
        );

        String baseUrl =
                ConfigReader.getInstance()
                        .getBaseUrl();

        System.out.println(
                "===== Opening URL: " + baseUrl + " ====="
        );

        DriverManager.getDriver().get(baseUrl);

        System.out.println(
                "===== BaseTest.setUp() COMPLETED ====="
        );
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        System.out.println(
                "===== BaseTest.tearDown() EXECUTED ====="
        );

        DriverManager.quitDriver();

        System.out.println(
                "===== WebDriver closed ====="
        );
    }
}
