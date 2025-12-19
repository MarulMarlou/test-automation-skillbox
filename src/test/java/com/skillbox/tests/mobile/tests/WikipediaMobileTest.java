package com.skillbox.tests.mobile.tests;

import com.skillbox.tests.mobile.pages.WikipediaMainPage;
import com.skillbox.tests.mobile.utils.AppiumDriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * Тесты для мобильного приложения Wikipedia
 */
public class WikipediaMobileTest {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() {
        try {
            driver = AppiumDriverFactory.createAndroidDriver();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Тест 1: Проверка загрузки главной страницы Wikipedia
     */
    @Test(description = "Проверка загрузки главной страницы приложения")
    public void testWikipediaAppLoads() {
        WikipediaMainPage mainPage = new WikipediaMainPage(driver);

        boolean isLoaded = mainPage.isMainPageLoaded();
        assertTrue(isLoaded, "Главная страница должна быть загружена");
    }

    /**
     * Тест 2: Проверка наличия поля поиска
     */
    @Test(description = "Проверка наличия поля поиска на главной странице")
    public void testSearchFieldIsVisible() {
        WikipediaMainPage mainPage = new WikipediaMainPage(driver);

        boolean isSearchVisible = mainPage.isSearchFieldVisible();
        assertTrue(isSearchVisible, "Поле поиска должно быть видимым");
    }

    /**
     * Тест 3: Открытие поля поиска
     */
    @Test(description = "Клик на поле поиска открывает клавиатуру")
    public void testClickSearchField() {
        WikipediaMainPage mainPage = new WikipediaMainPage(driver);

        try {
            mainPage.clickSearchField();
            Thread.sleep(1000);

            boolean isSearchVisible = mainPage.isSearchFieldVisible();
            assertTrue(isSearchVisible, "Поле поиска должно остаться видимым после клика");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Тест 4: Ввод текста в поле поиска
     */
    @Test(description = "Ввод текста в поле поиска")
    public void testSearchArticleInput() {
        WikipediaMainPage mainPage = new WikipediaMainPage(driver);

        try {
            mainPage.searchArticle("Java");
            Thread.sleep(2000);

            boolean isLoaded = mainPage.isMainPageLoaded();
            assertTrue(isLoaded, "Приложение должно остаться работающим");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Тест 5: Приложение запущено и работает
     */
    @Test(description = "Проверка что приложение запущено")
    public void testAppIsRunning() {
        assertNotNull(driver, "Driver не должен быть null");
        assertNotNull(driver.getCapabilities(), "Capabilities не должны быть null");
    }

    /**
     * Тест 6: Проверка пакета приложения
     */
    @Test(description = "Проверка пакета приложения")
    public void testAppPackage() {
        String appPackage = driver.getCapabilities().getCapability("appPackage").toString();
        assertTrue(appPackage.contains("wikipedia"), "Пакет должен содержать wikipedia");
    }
}
