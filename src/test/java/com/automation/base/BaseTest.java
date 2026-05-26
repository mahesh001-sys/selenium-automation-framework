package com.automation.base;

import com.automation.config.ConfigReader;
import com.automation.utils.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base test class — handles WebDriver lifecycle for every test method.
 *
 * @author Banoth Mahesh Kumar
 */
public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver();
        DriverManager.getDriver().get(ConfigReader.getInstance().getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
