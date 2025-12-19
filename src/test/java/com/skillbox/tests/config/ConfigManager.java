package com.skillbox.tests.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();
    private static ConfigManager instance;

    static {
        try (InputStream input = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties не найден!");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки config.properties: " + e.getMessage());
        }
    }

    private ConfigManager() {
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getBaseUrl() {
        return properties.getProperty("wikipedia.base.url", "https://www.wikipedia.org");
    }

    public String getRuUrl() {
        return properties.getProperty("wikipedia.ru.url", "https://ru.wikipedia.org");
    }

    public String getBrowserName() {
        return properties.getProperty("browser.name", "chrome");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("browser.headless", "false"));
    }

    public long getImplicitWait() {
        return Long.parseLong(properties.getProperty("browser.implicit.wait", "10"));
    }

    public long getExplicitWait() {
        return Long.parseLong(properties.getProperty("browser.explicit.wait", "15"));
    }

    public String getAppiumServerUrl() {
        return properties.getProperty("appium.server.url", "http://127.0.0.1:4723");
    }

    public String getAppPackage() {
        return properties.getProperty("appium.app.package", "org.wikipedia");
    }

    public String getAppActivity() {
        return properties.getProperty("appium.app.activity", "org.wikipedia.main.MainActivity");
    }

    public String getPlatformName() {
        return properties.getProperty("appium.platform.name", "Android");
    }

    public String getAutomationName() {
        return properties.getProperty("appium.automation.name", "UiAutomator2");
    }

    public String getDeviceName() {
        return properties.getProperty("appium.device.name", "emulator-5554");
    }

    public String getOsVersion() {
        return properties.getProperty("appium.os.version", "34");
    }

    public String getLogLevel() {
        return properties.getProperty("log.level", "INFO");
    }
}
