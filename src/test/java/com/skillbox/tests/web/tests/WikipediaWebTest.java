package com.skillbox.tests.web.tests;

import com.skillbox.tests.web.pages.WikipediaHomePage;
import com.skillbox.tests.web.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * Тесты для Wikipedia
 */
public class WikipediaWebTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = WebDriverFactory.createDriver();
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
    @Test(description = "Проверка загрузки главной страницы Wikipedia")
    public void testWikipediaHomePageLoads() {
        WikipediaHomePage homePage = new WikipediaHomePage(driver);
        homePage.openMainPage();

        String currentUrl = homePage.getCurrentUrl();
        assertTrue(currentUrl.contains("wikipedia.org"),
                "URL должен содержать wikipedia.org");

        String title = homePage.getPageTitle();
        assertFalse(title.isEmpty(), "Заголовок страницы не должен быть пустым");
    }

    /**
     * Тест 2: Проверка поля поиска присутствует на странице
     */
    @Test(description = "Проверка наличия поля поиска")
    public void testSearchInputIsPresent() {
        WikipediaHomePage homePage = new WikipediaHomePage(driver);
        homePage.openMainPage();

        boolean isSearchPresent = homePage.isSearchInputDisplayed();
        assertTrue(isSearchPresent, "Поле поиска должно быть видимым");
    }

    /**
     * Тест 3: Переключение на русскую версию
     */
    @Test(description = "Переключение на русскую версию Wikipedia")
    public void testSwitchToRussianVersion() {
        WikipediaHomePage homePage = new WikipediaHomePage(driver);
        homePage.openRussianVersion();

        String currentUrl = homePage.getCurrentUrl();
        assertTrue(currentUrl.contains("ru.wikipedia.org"),
                "URL должен содержать ru.wikipedia.org");
    }

    /**
     * Тест 4: Проверка что URL изменился при навигации
     */
    @Test(description = "Проверка навигации между версиями")
    public void testNavigationBetweenVersions() {
        WikipediaHomePage homePage = new WikipediaHomePage(driver);

        homePage.openMainPage();
        String enUrl = homePage.getCurrentUrl();
        assertTrue(enUrl.contains("wikipedia.org"), "Должна быть английская версия");

        homePage.openRussianVersion();
        String ruUrl = homePage.getCurrentUrl();
        assertTrue(ruUrl.contains("ru.wikipedia.org"), "Должна быть русская версия");

        assertNotEquals(enUrl, ruUrl, "URLs должны быть разными");
    }

    /**
     * Тест 5: Проверка текущего URL
     */
    @Test(description = "Проверка текущего URL главной страницы")
    public void testMainPageUrl() {
        WikipediaHomePage homePage = new WikipediaHomePage(driver);
        homePage.openMainPage();

        String url = homePage.getCurrentUrl();
        assertNotNull(url, "URL не должен быть null");
        assertFalse(url.isEmpty(), "URL не должен быть пустым");
        assertTrue(url.contains("wikipedia.org"), "URL должен содержать wikipedia.org");
    }

    /**
     * Тест 6: Проверка заголовка страницы
     */
    @Test(description = "Проверка заголовка главной страницы")
    public void testPageTitle() {
        WikipediaHomePage homePage = new WikipediaHomePage(driver);
        homePage.openMainPage();

        String title = homePage.getMainPageTitle();
        assertNotNull(title, "Заголовок не должен быть null");
        assertFalse(title.isEmpty(), "Заголовок не должен быть пустым");
        assertTrue(title.length() > 0, "Заголовок должен иметь содержимое");
    }
}
