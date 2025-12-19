package com.skillbox.tests.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object для главной страницы мобильного приложения Wikipedia
 */
public class WikipediaMainPage extends BaseMobilePage {

    @FindBy(id = "org.wikipedia:id/search_container")
    private WebElement searchContainer;

    @FindBy(xpath = "//android.widget.EditText[@resource-id='org.wikipedia:id/search_src_text']")
    private WebElement searchField;

    @FindBy(id = "org.wikipedia:id/view_main_status_bar_fragment")
    private WebElement statusBar;

    public WikipediaMainPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверяет что приложение загружено
     */
    public boolean isMainPageLoaded() {
        return isElementPresent(searchContainer);
    }

    /**
     * Нажимает на поле поиска
     */
    public void clickSearchField() {
        if (isElementPresent(searchContainer)) {
            click(searchContainer);
        }
    }

    /**
     * Вводит текст в поле поиска
     */
    public void searchArticle(String searchTerm) {
        clickSearchField();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        sendKeys(searchField, searchTerm);
    }

    /**
     * Проверяет наличие поля поиска
     */
    public boolean isSearchFieldVisible() {
        return isElementPresent(searchContainer);
    }
}
