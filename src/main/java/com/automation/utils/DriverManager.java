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

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    private DriverManager() {
        // Prevent object creation
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver() {

        if (driver.get() != null) {
            return;
        }

        ConfigReader config =
                ConfigReader.getInstance();

        String browser =
                config.getBrowser().toLowerCase();

        boolean headless =
                config.isHeadless();

        WebDriver webDriver;

        switch (browser) {

            case "firefox":

                WebDriverManager
                        .firefoxdriver()
                        .setup();

                FirefoxOptions firefoxOptions =
                        new FirefoxOptions();

                if (headless) {
                    firefoxOptions.addArguments(
                            "--headless"
                    );
                }

                webDriver =
                        new FirefoxDriver(
                                firefoxOptions
                        );

                break;

            case "edge":

                WebDriverManager
                        .edgedriver()
                        .setup();

                EdgeOptions edgeOptions =
                        new EdgeOptions();

                if (headless) {
                    edgeOptions.addArguments(
                            "--headless",
                            "--no-sandbox",
                            "--disable-dev-shm-usage"
                    );
                }

                webDriver =
                        new EdgeDriver(
                                edgeOptions
                        );

                break;

            case "chrome":

                WebDriverManager
                        .chromedriver()
                        .setup();

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

                webDriver =
                        new ChromeDriver(
                                chromeOptions
                        );

                break;

            default:

                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                );
        }

        if (headless) {

            webDriver.manage()
                    .window()
                    .setSize(
                            new Dimension(
                                    1920,
                                    1080
                            )
                    );

        } else {

            webDriver.manage()
                    .window()
                    .maximize();
        }

        webDriver.manage()
                .timeouts()
                .pageLoadTimeout(
                        java.time.Duration.ofSeconds(
                                config.getPageLoadTimeout()
                        )
                );

        driver.set(webDriver);
    }

    public static void quitDriver() {

        WebDriver webDriver =
                driver.get();

        if (webDriver != null) {

            try {
                webDriver.quit();
            } finally {
                driver.remove();
            }
        }
    }
}
