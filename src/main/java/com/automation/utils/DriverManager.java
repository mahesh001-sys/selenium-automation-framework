package com.automation.utils;

import com.automation.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * WebDriver factory for Selenium tests.
 *
 * Supports Chrome, Firefox, and Edge.
 *
 * Compatible with Java 11.
 *
 * @author Banoth Mahesh Kumar
 */
public class DriverManager {

    private static WebDriver driver;

    private DriverManager() {
        // Prevent object creation
    }

    /**
     * Returns the current WebDriver instance.
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Initializes WebDriver based on configuration.
     */
    public static void initDriver() {

        String browser = ConfigReader.getInstance()
                .getBrowser()
                .toLowerCase();

        boolean headless = ConfigReader.getInstance()
                .isHeadless();

        switch (browser) {

            case "firefox":

                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions =
                        new FirefoxOptions();

                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":

                WebDriverManager.edgedriver().setup();

                EdgeOptions edgeOptions =
                        new EdgeOptions();

                if (headless) {
                    edgeOptions.addArguments(
                            "--headless",
                            "--no-sandbox",
                            "--disable-dev-shm-usage"
                    );
                }

                driver = new EdgeDriver(edgeOptions);
                break;

            case "chrome":

            default:

                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions =
                        new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments(
                            "--headless",
                            "--no-sandbox",
                            "--disable-dev-shm-usage"
                    );
                }

                driver = new ChromeDriver(chromeOptions);
                break;
        }

        if (headless) {

            driver.manage()
                    .window()
                    .setSize(
                            new Dimension(1920, 1080)
                    );

        } else {

            driver.manage()
                    .window()
                    .maximize();
        }
    }

    /**
     * Quits WebDriver and clears the reference.
     */
    public static void quitDriver() {

        if (driver != null) {

            driver.quit();
            driver = null;
        }
    }
}
