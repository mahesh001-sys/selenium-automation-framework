package com.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton ConfigReader — loads config.properties once and provides key lookups.
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
            throw new RuntimeException("config.properties not found!", e);
        }
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            synchronized (ConfigReader.class) {
                if (instance == null) instance = new ConfigReader();
            }
        }
        return instance;
    }

    public String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) throw new RuntimeException("Key not found in config: " + key);
        return value.trim();
    }

    public String getBrowser()   { return get("browser"); }
    public String getBaseUrl()   { return get("base.url"); }
    public int    getTimeout()   { return Integer.parseInt(get("explicit.wait")); }
    public boolean isHeadless()  { return Boolean.parseBoolean(get("headless")); }
}
