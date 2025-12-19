package com.skillbox.tests.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.skillbox.tests.config.ConfigManager;
import java.time.Duration;

public class BaseMobilePage {

    protected AndroidDriver driver;
    protected WebDriverWait wait;
    protected static final ConfigManager config = ConfigManager.getInstance();

    public BaseMobilePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(config.getExplicitWait()));
    }

    protected WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void click(WebElement element) {
        waitForElementClickable(element).click();
    }

    protected void sendKeys(WebElement element, String text) {
        waitForElementVisible(element).clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return waitForElementVisible(element).getText();
    }

    protected boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void closeApp() {
        if (driver != null) {
            driver.quit();
        }
    }
}
