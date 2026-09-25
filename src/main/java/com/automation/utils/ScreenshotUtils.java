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
 * Utility class for capturing screenshots.
 *
 * Screenshots are saved to reports/screenshots/.
 *
 * Compatible with Java 11.
 *
 * @author Banoth Mahesh Kumar
 */
public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR =
            "reports/screenshots/";

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private ScreenshotUtils() {
        // Prevent object creation
    }

    /**
     * Captures a screenshot and returns its absolute file path.
     *
     * @param driver   active WebDriver
     * @param testName name used in the screenshot filename
     * @return absolute path of the screenshot,
     *         or an empty string if capture fails
     */
    public static String capture(
            WebDriver driver,
            String testName) {

        try {

            // Create screenshot directory if it does not exist
            File screenshotDirectory =
                    new File(SCREENSHOT_DIR);

            if (!screenshotDirectory.exists()) {
                screenshotDirectory.mkdirs();
            }

            // Generate unique timestamp
            String timestamp =
                    LocalDateTime.now().format(FMT);

            // Replace characters that are unsafe in filenames
            String safeTestName =
                    testName.replaceAll(
                            "[^a-zA-Z0-9._-]",
                            "_"
                    );

            String fileName =
                    safeTestName
                            + "_"
                            + timestamp
                            + ".png";

            File destination =
                    new File(
                            screenshotDirectory,
                            fileName
                    );

            // Capture screenshot
            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(
                    source,
                    destination
            );

            return destination.getAbsolutePath();

        } catch (IOException e) {

            System.err.println(
                    "[ScreenshotUtils] Failed to capture screenshot: "
                            + e.getMessage()
            );

            return "";

        } catch (RuntimeException e) {

            System.err.println(
                    "[ScreenshotUtils] WebDriver screenshot error: "
                            + e.getMessage()
            );

            return "";
        }
    }
}
