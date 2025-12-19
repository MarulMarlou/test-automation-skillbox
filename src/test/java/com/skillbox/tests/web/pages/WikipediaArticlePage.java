package com.skillbox.tests.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object для страницы статьи Wikipedia
 */
public class WikipediaArticlePage extends BasePage {

    // Локаторы
    @FindBy(xpath = "//h1[@class='firstHeading']")
    private WebElement articleTitle;

    @FindBy(xpath = "//div[@id='mw-content-text']")
    private WebElement articleContent;

    @FindBy(xpath = "//div[@class='mw-page-container']")
    private WebElement pageContainer;

    @FindBy(xpath = "//table[@class='infobox']")
    private WebElement infoBox;

    @FindBy(xpath = "//div[@id='mw-panel']//a[@href]")
    private WebElement sidebarLink;

    public WikipediaArticlePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Получает заголовок статьи
     */
    public String getArticleTitle() {
        return getText(articleTitle);
    }

    /**
     * Проверяет видимость содержимого статьи
     */
    public boolean isArticleContentDisplayed() {
        try {
            return articleContent.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверяет наличие информационной панели (infobox)
     */
    public boolean hasInfoBox() {
        try {
            return infoBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверяет что статья загружена (проверка по заголовку)
     */
    public boolean isArticleLoaded() {
        try {
            return articleTitle.isDisplayed() && !getArticleTitle().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Получает текущий URL страницы
     */
    public String getArticleUrl() {
        return getCurrentUrl();
    }

    /**
     * Прокручивает страницу вниз
     */
    public void scrollDown(int pixels) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0, " + pixels + ");");
    }

    /**
     * Прокручивает страницу вверх
     */
    public void scrollUp(int pixels) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0, -" + pixels + ");");
    }
}
