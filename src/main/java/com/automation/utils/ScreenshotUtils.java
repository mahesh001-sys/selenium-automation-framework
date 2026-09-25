package com.automation.utils;

import com.automation.config.ConfigReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for capturing screenshots.
 *
 * Screenshots are stored in reports/screenshots/.
 *
 * Java 11 compatible.
 *
 * @author Banoth Mahesh Kumar
 */
public final class ScreenshotUtils {

    private ScreenshotUtils() {
        // Prevent object creation
    }

    public static String capture(
            WebDriver driver,
            String testName) {

        if (driver == null) {
            return null;
        }

        try {

            String directory =
                    ConfigReader.getInstance()
                            .getScreenshotPath();

            File screenshotDirectory =
                    new File(directory);

            if (!screenshotDirectory.exists()) {
                screenshotDirectory.mkdirs();
            }

            String timestamp =
                    new SimpleDateFormat(
                            "yyyyMMdd_HHmmss"
                    ).format(new Date());

            String safeTestName =
                    testName.replaceAll(
                            "[^a-zA-Z0-9._-]",
                            "_"
                    );

            String fileName =
                    safeTestName + "_" + timestamp + ".png";

            File destination =
                    new File(
                            screenshotDirectory,
                            fileName
                    );

            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.FILE
                            );

            FileUtils.copyFile(
                    source,
                    destination
            );

            return destination.getPath();

        } catch (Exception e) {

            System.err.println(
                    "Unable to capture screenshot: "
                            + e.getMessage()
            );

            return null;
        }
    }
}
