package com.automation.utils;

import com.automation.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Thread-safe WebDriver factory using ThreadLocal.
 * Supports Chrome, Firefox, and Edge.
 *
 * @author Banoth Mahesh Kumar
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static void initDriver() {
        String browser   = ConfigReader.getInstance().getBrowser().toLowerCase();
        boolean headless = ConfigReader.getInstance().isHeadless();
        WebDriver driver;

        switch (browser) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOpts = new FirefoxOptions();
                if (headless) ffOpts.addArguments("--headless");
                driver = new FirefoxDriver(ffOpts);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions opts = new ChromeOptions();
                if (headless) opts.addArguments("--headless", "--no-sandbox",
                        "--disable-dev-shm-usage");
                opts.addArguments("--start-maximized");
                driver = new ChromeDriver(opts);
            }
        }

        driver.manage().window().maximize();
        driverThread.set(driver);
    }

    public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}
