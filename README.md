# Selenium Automation Framework

A test automation framework I built while learning Selenium with Java. It covers end-to-end flows on [SauceDemo](https://www.saucedemo.com) — login, browsing products, adding to cart, and checkout. The goal was to build something structured enough to show in interviews, not just a bunch of test scripts thrown together.

---

## What's inside

The framework uses **Page Object Model** so each page of the app has its own class. Tests don't interact with the browser directly — they just call methods like `loginPage.loginAs("user", "pass")` and the page class handles the locators and actions. Makes it a lot easier to update when the UI changes.

**Tech used:**
- Selenium WebDriver 4.18
- TestNG 7.9 for running tests and grouping them
- Maven for build and dependency management
- ExtentReports for HTML test reports
- Apache POI to read test data from Excel
- WebDriverManager so I don't have to manually manage chromedriver
- Log4j2 for logging what's happening during a run

---

## Project layout

```
src/
├── main/java/com/automation/
│   ├── config/       → reads config.properties (browser, URL, timeouts)
│   ├── pages/        → one class per page (BasePage, LoginPage, HomePage, CartPage, CheckoutPage)
│   └── utils/        → DriverManager, WaitUtils, ScreenshotUtils, ExcelUtils, ExtentReportListener
│
└── test/
    ├── java/com/automation/
    │   ├── base/     → BaseTest handles @BeforeMethod and @AfterMethod
    │   └── tests/    → LoginTest, HomePageTest, ProductTest, CheckoutTest
    └── resources/
        ├── config.properties
        ├── log4j2.xml
        └── testng.xml

test-data/
└── TestData.xlsx     → login credentials and other data for data-driven tests
```

---

## How to run

You need Java 11+ and Maven installed. Chrome should already be on your machine — WebDriverManager handles the driver automatically.

```bash
# clone and enter the project
git clone https://github.com/mahesh001-sys/selenium-automation-framework.git
cd selenium-automation-framework

# run everything
mvn clean test

# run just smoke tests
mvn clean test -Dgroups=smoke

# run on Firefox
mvn clean test -Dbrowser=firefox

# run headless (useful for CI)
mvn clean test -Dheadless=true
```

After the run finishes, open `reports/ExtentReport.html` in a browser to see the full results with pass/fail status and screenshots for any failures.

---

## Test cases covered

**Login** — valid credentials, locked out user, empty fields, wrong password. These also run data-driven from the Excel sheet so I can add new scenarios without touching the code.

**Products page** — checks that items load, count is correct, sorting by price and name works properly.

**Cart** — adding items, verifying they show up, removing them.

**Checkout** — the full end-to-end flow from login all the way to order confirmation, plus validation when required fields are left empty.

---

## A few things worth noting

The `DriverManager` uses `ThreadLocal<WebDriver>` so tests can run in parallel without browser instances clashing. Waits are all explicit — no `Thread.sleep()` anywhere in the code. Screenshots are captured automatically on failure and attached to the Extent report.

Config like browser type, base URL, and timeouts all live in `config.properties` so nothing is hardcoded in the tests.

---

## About

Built this as part of the GUVI Automation Testing with Selenium (Java) program. SauceDemo is the test target because it's purpose-built for automation practice — it has different user types that produce different behaviours, which made writing meaningful negative tests much easier.

---

**Banoth Mahesh Kumar**  
[LinkedIn](https://www.linkedin.com/in/mahesh001) · [GitHub](https://github.com/mahesh001-sys) · maheshbanoth057@gmail.com
