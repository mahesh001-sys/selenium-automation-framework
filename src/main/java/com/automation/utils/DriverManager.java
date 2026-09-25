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
 * Manages WebDriver creation and cleanup.
 *
 * Supports Chrome, Firefox and Edge.
 * Supports headless execution for CI.
 *
 * Java 11 compatible.
 *
 * @author Banoth Mahesh Kumar
 */
public class DriverManager {

    private static WebDriver driver;

    private DriverManager() {
        // Prevent object creation
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void initDriver() {

        if (driver != null) {
            return;
        }

        ConfigReader config = ConfigReader.getInstance();

        String browser = config.getBrowser().toLowerCase();
        boolean headless = config.isHeadless();

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

                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions =
                        new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments(
                            "--headless",
                            "--no-sandbox",
                            "--disable-dev-shm-usage",
                            "--disable-gpu"
                    );
                }

                driver = new ChromeDriver(chromeOptions);
                break;

            default:

                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                );
        }

        if (headless) {

            driver.manage()
                    .window()
                    .setSize(new Dimension(1920, 1080));

        } else {

            driver.manage()
                    .window()
                    .maximize();
        }

        driver.manage()
                .timeouts()
                .pageLoadTimeout(
                        java.time.Duration.ofSeconds(
                                config.getPageLoadTimeout()
                        )
                );
    }

    public static void quitDriver() {

        if (driver != null) {

            driver.quit();
            driver = null;
        }
    }
                             }
