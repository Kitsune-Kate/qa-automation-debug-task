# QA Automation Debugging Task

This repository contains a small UI automation project built with:

- Java 17
- Selenide
- TestNG
- Maven

## Requirements

To run the project locally, make sure you have:

- JDK 17+
- Maven
- Google Chrome

## Running the tests

From the project root, run:

```bash
mvn clean test
```

## Debugging Analysis

**1. Какие проблемы были обнаружены?**
В ходе выполнения теста упали два метода:
* В `WebFormPage.fillForm` тест падал на проверке текста `Expected substring must not be null or empty`.
* В `WebFixture.teardown` тест падал после завершения с ошибкой `No webdriver is bound to current thread`.

**2. Как определены их причины?**
* В `fillForm` за ожидаемое значение (`expectedText`) бралось значение из поля до того, как в него ввели текст. Так как поле было пустым, проверка введенного значения падала.
* В `teardown` драйвер закрывался (`Selenide.closeWebDriver()`) до того, как из него пытались получить данные о сессии (`SessionId`).

**3. Почему предложенные изменения решают проблемы?**
* В `fillForm` я заменила `textInput.getValue()` на `text`. Ожидаемым значением должен быть текст, который мы передаем.
* В `teardown` я поменяла строки местами - перенесла `Selenide.closeWebDriver()` в конец метода. Теперь сначала берется `SessionId` у драйвера, а потом он закрывается.

**4. Есть ли в lifecycle другие потенциальные проблемы?**
Да, вот некоторые улучшения:
* **Защита от падения `teardown`:** Если тест упадет *до* вызова `open(url)`, драйвер не успеет создаться. В этом случае `teardown` тоже упадет. Чтобы сделать фреймворк надежнее, нужно обернуть логику закрытия драйвера в проверку `if (WebDriverRunner.hasWebDriverStarted())`.
* **Добавление `@BeforeMethod` (setUp):** Сейчас `open(url)` вызывается в теле теста. Если тестов будет много, придется дублировать вызов в каждом. Логичнее вынести открытие страницы в `@BeforeMethod` в базовом классе `WebFixture`.
