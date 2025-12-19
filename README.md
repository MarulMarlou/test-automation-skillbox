# Test Automation - Wikipedia 

Автоматизированное тестирование Wikipedia с использованием Selenium, TestNG и Page Object Model.

##  Что внутри

- **6 веб-тестов** для Wikipedia (главная страница, поиск, навигация)
- **Page Object Model** архитектура
- **WebDriver Manager** для управления браузерами
- **Параметризованная конфигурация** через properties
- **TestNG** для управления тестами

##  Быстрый старт
### Требования
- Java 25+
- Maven 3.9+
- Google Chrome

### Запуск тестов

```bash
# Клонируй репозиторий
git clone https://github.com/yourusername/test-automation-skillbox.git
cd test-automation-skillbox

# Установи зависимости
mvn clean install

# Запусти тесты
mvn clean test
```

### Тестовые сценарии

| # | Тест | Описание |
|---|------|---------|
| 1 | testWikipediaHomePageLoads | Проверка загрузки главной страницы |
| 2 | testSearchInputIsPresent | Проверка видимости поля поиска |
| 3 | testSwitchToRussianVersion | Переключение на русскую версию |
| 4 | testNavigationBetweenVersions | Навигация между версиями |
| 5 | testMainPageUrl | Проверка URL главной страницы |
| 6 | testPageTitle | Проверка заголовка страницы |

##  Структура проекта

```
src/test/
├── java/com/skillbox/tests/
│   ├── config/
│   │   └── ConfigManager.java
│   └── web/
│       ├── pages/
│       │   ├── BasePage.java
│       │   ├── WikipediaHomePage.java
│       │   └── WikipediaSearchResultsPage.java
│       ├── tests/
│       │   └── WikipediaWebTest.java
│       └── utils/
│           └── WebDriverFactory.java
└── resources/
    ├── config.properties
    └── testng.xml
```

##  Технологии

- **Selenium WebDriver** 4.13.0
- **TestNG** 7.7.1
- **WebDriverManager** 5.6.2
- **Maven** 3.9+

##  Конфигурация

Файл `src/test/resources/config.properties`:

```properties
# Website
wikipedia.base.url=https://www.wikipedia.org
wikipedia.ru.url=https://ru.wikipedia.org

# Browser
browser.name=chrome
browser.headless=false
browser.implicit.wait=10
browser.explicit.wait=15
```


## Мобильные тесты

Код для мобильных тестов с Appium готов в папке `src/test/java/com/skillbox/tests/mobile/`

Для запуска требуется:
- Appium Server 3.1.2
- UiAutomator2 драйвер
- Android SDK 33+

##  Команды

```bash
# Запуск всех тестов
mvn clean test

# Запуск только веб-тестов
mvn clean test -Dtest=WikipediaWebTest

# Генерация отчета
mvn surefire-report:report
# Результаты в: target/site/surefire-report.html

# Очистка проекта
mvn clean
```

