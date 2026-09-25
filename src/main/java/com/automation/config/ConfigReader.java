package com.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads configuration values from config.properties.
 *
 * Java 11 compatible.
 *
 * @author Banoth Mahesh Kumar
 */
public class ConfigReader {

    private static ConfigReader instance;

    private final Properties properties = new Properties();

    private ConfigReader() {

        try (FileInputStream fis = new FileInputStream(
                "src/test/resources/config.properties")) {

            properties.load(fis);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load config.properties", e
            );
        }
    }

    public static ConfigReader getInstance() {

        if (instance == null) {

            synchronized (ConfigReader.class) {

                if (instance == null) {
                    instance = new ConfigReader();
                }
            }
        }

        return instance;
    }

    public String get(String key) {

        String systemProperty = System.getProperty(key);

        if (systemProperty != null
                && !systemProperty.trim().isEmpty()) {

            return systemProperty.trim();
        }

        String value = properties.getProperty(key);

        if (value == null) {

            throw new RuntimeException(
                    "Configuration key not found: " + key
            );
        }

        return value.trim();
    }

    public String getBrowser() {
        return get("browser");
    }

    public String getBaseUrl() {
        return get("base.url");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public int getTimeout() {
        return Integer.parseInt(get("explicit.wait"));
    }

    public int getPageLoadTimeout() {
        return Integer.parseInt(get("page.load.timeout"));
    }

    public String getTestDataPath() {
        return get("test.data.path");
    }

    public String getReportPath() {
        return get("report.path");
    }

    public String getScreenshotPath() {
        return get("screenshot.path");
    }
}
