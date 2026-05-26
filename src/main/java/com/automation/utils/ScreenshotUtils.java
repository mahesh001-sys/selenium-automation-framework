package com.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Captures screenshots on test failure and saves them to reports/screenshots/.
 *
 * @author Banoth Mahesh Kumar
 */
public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "reports/screenshots/";
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtils() {}

    /**
     * Takes a screenshot and returns the absolute file path.
     *
     * @param driver   active WebDriver
     * @param testName name used in the filename
     * @return absolute path to saved screenshot, or empty string on failure
     */
    public static String capture(WebDriver driver, String testName) {
        try {
            new File(SCREENSHOT_DIR).mkdirs();
            String timestamp = LocalDateTime.now().format(FMT);
            String fileName  = SCREENSHOT_DIR + testName + "_" + timestamp + ".png";

            File src  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(fileName);
            FileUtils.copyFile(src, dest);

            return dest.getAbsolutePath();
        } catch (IOException e) {
            System.err.println("[ScreenshotUtils] Failed to capture screenshot: " + e.getMessage());
            return "";
        }
    }
}
