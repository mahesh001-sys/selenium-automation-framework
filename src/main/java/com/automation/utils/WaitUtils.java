package com.automation.utils;

import com.automation.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Provides reusable explicit wait methods.
 *
 * Java 11 compatible.
 *
 * @author Banoth Mahesh Kumar
 */
public class WaitUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver) {

        if (driver == null) {
            throw new IllegalArgumentException(
                    "WebDriver cannot be null"
            );
        }

        this.driver = driver;

        int timeout =
                ConfigReader.getInstance().getTimeout();

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(timeout)
        );
    }

    public WebElement waitForVisible(
            WebElement element) {

        return wait.until(
                ExpectedConditions.visibilityOf(element)
        );
    }

    public WebElement waitForClickable(
            WebElement element) {

        return wait.until(
                ExpectedConditions.elementToBeClickable(element)
        );
    }

    public WebElement waitForPresence(
            By locator) {

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public boolean waitForInvisibility(
            WebElement element) {

        return wait.until(
                ExpectedConditions.invisibilityOf(element)
        );
    }

    public boolean waitForInvisibility(
            By locator) {

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }

    public boolean waitForUrl(String urlFragment) {

        return wait.until(
                ExpectedConditions.urlContains(urlFragment)
        );
    }

    public String waitForTitle(String title) {

        wait.until(
                ExpectedConditions.titleContains(title)
        );

        return driver.getTitle();
    }
}
