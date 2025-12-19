package com.skillbox.tests.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object для главной страницы Wikipedia
 */
public class WikipediaHomePage extends BasePage {

    // Локаторы
    @FindBy(id = "searchInput")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//h1[@class='central-textlogo-image']")
    private WebElement wikipediaLogo;

    @FindBy(xpath = "//a[contains(@href, 'ru.wikipedia.org')]")
    private WebElement russianLanguageLink;

    @FindBy(xpath = "//a[contains(@href, 'en.wikipedia.org')]")
    private WebElement englishLanguageLink;

    @FindBy(xpath = "//div[@id='mw-page-base']")
    private WebElement pageContainer;

    public WikipediaHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Открывает главную страницу Wikipedia
     */
    public void openMainPage() {
        openUrl(config.getBaseUrl());
    }

    /**
     * Открывает русскую версию Wikipedia
     */
    public void openRussianVersion() {
        openUrl(config.getRuUrl());
    }

    /**
     * Выполняет поиск статьи
     */
    public WikipediaSearchResultsPage searchArticle(String articleName) {
        sendKeys(searchInput, articleName);
        click(searchButton);
        return new WikipediaSearchResultsPage(driver);
    }

    /**
     * Проверяет видимость логотипа Wikipedia
     */
    public boolean isWikipediaLogoDisplayed() {
        return isElementDisplayed(wikipediaLogo);
    }

    /**
     * Проверяет видимость поля поиска
     */
    public boolean isSearchInputDisplayed() {
        return isElementDisplayed(searchInput);
    }

    /**
     * Получает заголовок главной страницы
     */
    public String getMainPageTitle() {
        return getPageTitle();
    }

    /**
     * Нажимает на ссылку русского языка
     */
    public WikipediaHomePage clickRussianLanguage() {
        click(russianLanguageLink);
        return new WikipediaHomePage(driver);
    }

    /**
     * Нажимает на ссылку английского языка
     */
    public WikipediaHomePage clickEnglishLanguage() {
        click(englishLanguageLink);
        return new WikipediaHomePage(driver);
    }

    /**
     * Проверяет наличие элемента на странице
     */
    private boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
