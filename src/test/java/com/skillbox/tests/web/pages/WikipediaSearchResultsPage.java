package com.skillbox.tests.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * Page Object для страницы результатов поиска
 */
public class WikipediaSearchResultsPage extends BasePage {

    // Локаторы
    @FindBy(xpath = "//ul[@class='mw-search-results']/li")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[@class='mw-search-result']//a[1]")
    private List<WebElement> resultLinks;

    @FindBy(xpath = "//form[@id='searchform']//input[@type='search']")
    private WebElement searchInput;

    @FindBy(xpath = "//h1[@class='firstHeading']")
    private WebElement pageHeading;

    public WikipediaSearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Получает количество результатов поиска
     */
    public int getSearchResultsCount() {
        return searchResults.size();
    }

    /**
     * Кликает на первый результат поиска
     */
    public WikipediaArticlePage clickFirstResult() {
        if (!resultLinks.isEmpty()) {
            click(resultLinks.get(0));
        }
        return new WikipediaArticlePage(driver);
    }

    /**
     * Кликает на результат по индексу
     */
    public WikipediaArticlePage clickResultByIndex(int index) {
        if (index < resultLinks.size()) {
            click(resultLinks.get(index));
        }
        return new WikipediaArticlePage(driver);
    }

    /**
     * Получает текст первого результата
     */
    public String getFirstResultText() {
        if (!resultLinks.isEmpty()) {
            return getText(resultLinks.get(0));
        }
        return "";
    }

    /**
     * Проверяет наличие результатов поиска
     */
    public boolean hasSearchResults() {
        return getSearchResultsCount() > 0;
    }

    /**
     * Повторяет поиск с другим термином
     */
    public WikipediaSearchResultsPage searchAgain(String newSearchTerm) {
        sendKeys(searchInput, newSearchTerm);
        // Здесь нужно нажать Enter или кнопку поиска
        searchInput.sendKeys(org.openqa.selenium.Keys.ENTER);
        return new WikipediaSearchResultsPage(driver);
    }
}
