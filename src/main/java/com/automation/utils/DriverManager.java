package com.automation.utils;

import com.automation.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Thread-safe WebDriver factory using ThreadLocal.
 * Supports Chrome, Firefox, and Edge.
 *
 * Compatible with Java 11.
 *
 * @author Banoth Mahesh Kumar
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThread =
            new ThreadLocal<>();

    private DriverManager() {
        // Prevent object creation
    }

    /**
     * Returns the WebDriver instance for the current thread.
     */
    public static WebDriver getDriver() {
        return driverThread.get();
    }

    /**
     * Initializes the WebDriver based on the browser
     * and headless configuration.
     */
    public static void initDriver() {

        String browser = ConfigReader.getInstance()
                .getBrowser()
                .toLowerCase();

        boolean headless = ConfigReader.getInstance()
                .isHeadless();

        WebDriver driver;

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

        /*
         * Maximize only when running in normal mode.
         * In headless mode, use a fixed window size.
         */
        if (headless) {

            driver.manage()
                    .window()
                    .setSize(
                            new org.openqa.selenium.Dimension(
                                    1920,
                                    1080
                            )
                    );

        } else {

            driver.manage()
                    .window()
                    .maximize();
        }

        driverThread.set(driver);
    }

    /**
     * Quits the WebDriver and removes it from ThreadLocal.
     */
    public static void quitDriver() {

        WebDriver driver = driverThread.get();

        if (driver != null) {

            driver.quit();

            driverThread.remove();
        }
    }
}
