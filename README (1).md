<div align="center">

# Selenium Automation Framework

**End-to-end test automation for e-commerce flows**  
Built with Selenium WebDriver · Java 11 · TestNG · Maven · Page Object Model

[![Java](https://img.shields.io/badge/Java-11-orange?logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/11/)
[![Selenium](https://img.shields.io/badge/Selenium-4.18.1-43B02A?logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9.0-red)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey)](LICENSE)
[![GitHub last commit](https://img.shields.io/github/last-commit/mahesh001-sys/selenium-automation-framework)](https://github.com/mahesh001-sys/selenium-automation-framework/commits/main)

</div>

---

## What this project does

This framework automates the complete shopping flow on [SauceDemo](https://www.saucedemo.com) — a dedicated automation practice site. It covers login, browsing products, adding items to cart, and completing checkout, with both happy-path and negative test scenarios.

I built this while completing the GUVI Automation Testing with Selenium (Java) program. The focus was on writing maintainable, structured tests rather than just scripts that work once — so everything follows Page Object Model, waits are explicit, and the browser setup is thread-safe for parallel runs.

---

## Framework architecture

```
selenium-automation-framework/
│
├── src/
│   ├── main/java/com/automation/
│   │   ├── config/
│   │   │   └── ConfigReader.java          ← singleton, reads config.properties once
│   │   ├── pages/
│   │   │   ├── BasePage.java              ← shared driver, waits, JS helpers
│   │   │   ├── LoginPage.java
│   │   │   ├── HomePage.java
│   │   │   ├── CartPage.java
│   │   │   └── CheckoutPage.java
│   │   └── utils/
│   │       ├── DriverManager.java         ← ThreadLocal WebDriver factory
│   │       ├── WaitUtils.java             ← all explicit waits, no Thread.sleep()
│   │       ├── ScreenshotUtils.java       ← auto-capture on failure
│   │       ├── ExcelUtils.java            ← Apache POI data reader
│   │       └── ExtentReportListener.java  ← TestNG listener, attaches screenshots
│   │
│   └── test/
│       ├── java/com/automation/
│       │   ├── base/
│       │   │   └── BaseTest.java          ← @BeforeMethod / @AfterMethod lifecycle
│       │   └── tests/
│       │       ├── LoginTest.java
│       │       ├── HomePageTest.java
│       │       ├── ProductTest.java
│       │       └── CheckoutTest.java
│       └── resources/
│           ├── config.properties          ← browser, URL, timeouts
│           ├── log4j2.xml
│           └── testng.xml                 ← smoke + regression suites
│
├── test-data/
│   └── TestData.xlsx                      ← login credentials, product data
│
├── reports/                               ← generated at runtime (gitignored)
│   ├── ExtentReport.html
│   └── screenshots/
│
├── docs/
│   └── images/                            ← report screenshots for this README
│
├── .github/
│   ├── workflows/
│   │   └── selenium-ci.yml               ← runs tests on every push
│   └── ISSUE_TEMPLATE/
│       └── bug_report.md
│
├── pom.xml
├── .gitignore
└── README.md
```

---

## Tech stack

| Tool | Version | Why it's here |
|------|---------|---------------|
| Selenium WebDriver | 4.18.1 | Browser automation |
| Java | 11 | Core language |
| TestNG | 7.9.0 | Test runner, grouping, parallel execution |
| Maven | 3.x | Build and dependency management |
| WebDriverManager | 5.7.0 | Auto-downloads the right chromedriver/geckodriver |
| ExtentReports | 5.1.1 | HTML report with screenshots |
| Apache POI | 5.2.5 | Read test data from Excel sheets |
| Log4j2 | 2.23.1 | Logging to console and rolling file |
| Commons IO | 2.15.1 | File utilities for screenshot saving |

---

## Getting started

**You need:** Java 11+, Maven 3.6+, and Chrome installed. That's it — WebDriverManager handles chromedriver automatically.

```bash
# 1. clone
git clone https://github.com/mahesh001-sys/selenium-automation-framework.git
cd selenium-automation-framework

# 2. run all tests
mvn clean test
```

Open `reports/ExtentReport.html` when done to see full results.

---

## Run options

```bash
# full regression suite
mvn clean test

# smoke tests only (fast, ~5 tests)
mvn clean test -Dgroups=smoke

# headless — no browser window, good for CI
mvn clean test -Dheadless=true

# cross-browser
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge

# single test class
mvn clean test -Dtest=LoginTest

# single method
mvn clean test -Dtest=LoginTest#testValidLogin
```

To change the target URL or browser permanently, edit `src/test/resources/config.properties`:

```properties
browser=chrome
base.url=https://www.saucedemo.com
headless=false
implicit.wait=10
explicit.wait=20
```

---

## Test coverage

### LoginTest
| Scenario | Type |
|----------|------|
| Standard user logs in → lands on products page | Happy path |
| Locked-out user → error message shown | Negative |
| Empty username → validation error | Negative |
| Empty password → validation error | Negative |
| Wrong password → credential mismatch error | Negative |
| Multiple invalid combos from Excel | Data-driven |

### HomePageTest
| Scenario | Type |
|----------|------|
| Page title is "Products" after login | Smoke |
| Exactly 6 products are displayed | Functional |
| Logout from burger menu works | Functional |

### ProductTest
| Scenario | Type |
|----------|------|
| Sort by price low-to-high is correct | Functional |
| Sort by name Z to A is correct | Functional |
| Add product → cart badge updates | Functional |
| Add two products → cart count is 2 | Functional |

### CheckoutTest
| Scenario | Type |
|----------|------|
| Full flow: login → add → cart → checkout → confirmation | E2E |
| Missing first name → validation error | Negative |
| Missing postal code → validation error | Negative |

---

## Data-driven testing

Test credentials live in `test-data/TestData.xlsx` under the `LoginData` sheet:

| username | password | expected |
|----------|----------|----------|
| standard_user | secret_sauce | pass |
| locked_out_user | secret_sauce | fail |
| invalid_user | wrong_pass | fail |
| | secret_sauce | fail |
| standard_user | | fail |

Adding a new row = new test case. No code changes needed.

---

## Reports

After `mvn clean test`, open `reports/ExtentReport.html` in any browser.

The report shows:
- Pass / Fail / Skip per test with timestamps
- Automatic screenshots attached to every failed test
- Browser and OS info in the dashboard
- Test logs from Log4j2 embedded inline

Logs also write to `reports/logs/test-run.log` for post-run debugging.

---

## Key design decisions

**ThreadLocal WebDriver** — Each parallel test thread gets its own browser instance. No shared state, no flaky failures from test interference.

**Page Object Model + Page Factory** — Every page is its own class. Locators live in the page class, not in tests. If a locator changes, you fix it in one place.

**No Thread.sleep() anywhere** — All synchronisation goes through `WaitUtils`, which wraps `WebDriverWait` with `ExpectedConditions`. This keeps tests fast and reliable.

**ConfigReader singleton** — `config.properties` is read once at startup. Browser, URL, and timeouts are all configurable without touching test code.

**Listener-based reporting** — `ExtentReportListener` implements `ITestListener`. Tests don't manually log to the report — the listener handles it, so test classes stay clean.

---

## CI/CD

Every push to `main` or a pull request triggers the GitHub Actions workflow:

1. Ubuntu runner, Java 11, Chrome installed
2. `mvn clean test -Dheadless=true`
3. Allure/Extent results uploaded as artifacts
4. Screenshots uploaded separately on failure

Workflow file: [`.github/workflows/selenium-ci.yml`](.github/workflows/selenium-ci.yml)

---

## Project background

This was built as the capstone project for the **GUVI Automation Testing with Selenium (Java)** 8-week training program. The program covered testing fundamentals, Java, Git, Selenium WebDriver, framework design, and CI/CD integration — this repo is where all of it came together.

SauceDemo was chosen as the target because it's purpose-built for automation practice. It has different user types that produce different behaviours (`locked_out_user`, `problem_user`, `performance_glitch_user`), which makes writing meaningful negative tests straightforward without needing a real backend.

---

## Author

**Banoth Mahesh Kumar**  
B.Tech Information Technology — Anurag University, Hyderabad (2022–2026)

[LinkedIn](https://www.linkedin.com/in/mahesh001) · [GitHub](https://github.com/mahesh001-sys) · maheshbanoth057@gmail.com

---

## License

MIT — see [LICENSE](LICENSE) for details.
