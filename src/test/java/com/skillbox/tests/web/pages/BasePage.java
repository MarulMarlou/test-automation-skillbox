package com.skillbox.tests.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.skillbox.tests.config.ConfigManager;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final ConfigManager config = ConfigManager.getInstance();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(config.getExplicitWait()));
    }

    public void openUrl(String url) {
        driver.navigate().to(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
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

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
