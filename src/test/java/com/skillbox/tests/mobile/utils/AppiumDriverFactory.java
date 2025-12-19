package com.skillbox.tests.mobile.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.skillbox.tests.config.ConfigManager;
import java.net.URL;
import java.net.MalformedURLException;
import java.time.Duration;

public class AppiumDriverFactory {

    private static final ConfigManager config = ConfigManager.getInstance();

    public static AndroidDriver createAndroidDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability("platformName", config.getPlatformName());
            capabilities.setCapability("automationName", config.getAutomationName());
            capabilities.setCapability("deviceName", config.getDeviceName());
            capabilities.setCapability("osVersion", config.getOsVersion());

            capabilities.setCapability("appPackage", config.getAppPackage());
            capabilities.setCapability("appActivity", config.getAppActivity());

            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("noReset", false);

            URL appiumServerUrl = new URL(config.getAppiumServerUrl());
            AndroidDriver driver = new AndroidDriver(appiumServerUrl, capabilities);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWait()));

            return driver;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Ошибка подключения к Appium Server: " + e.getMessage());
        }
    }

    public static void closeDriver(AndroidDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
