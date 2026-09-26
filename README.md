# 🚀 Selenium Automation Framework

### Java • Selenium • TestNG • Maven • POM

A reusable Selenium automation framework for testing key e-commerce workflows on [SauceDemo](https://www.saucedemo.com).

## 🎯 What This Project Automates

- User Login & Logout
- Invalid & Locked User Validation
- Product Browsing & Sorting
- Add to Cart & Cart Validation
- Checkout Workflow & Validations
- Data-Driven Login Testing

## 🏗️ Framework Architecture

**TestNG → BaseTest → ThreadLocal WebDriver → Page Objects → Utilities → Reports**

The framework follows the **Page Object Model (POM)** with Page Factory and uses `ThreadLocal<WebDriver>` for thread-safe parallel execution.

## 🧰 Technology Stack

| Technology | Purpose |
|---|---|
| Java 11 | Programming |
| Selenium 4.18.1 | UI Automation |
| TestNG 7.9.0 | Test Execution |
| Maven | Build & Dependency Management |
| Apache POI 5.2.5 | Excel Data Handling |
| ExtentReports 5.1.1 | HTML Reporting |
| Log4j2 2.23.1 | Logging |
| WebDriverManager 5.7.0 | Browser Driver Management |
| Git & GitHub | Version Control |
| GitHub Actions | CI/CD |

## ⭐ Key Features

- Page Object Model & Page Factory
- Chrome, Firefox & Edge Support
- ThreadLocal WebDriver
- Parallel Test Execution
- Explicit Waits
- Excel Data-Driven Testing
- Failure Screenshots
- ExtentReports
- Smoke & Regression Testing
- Headless Execution
- GitHub Actions CI/CD

## 📂 Project Structure

| Component | Purpose |
|---|---|
| `BaseTest` | Test setup & cleanup |
| `BasePage` | Common page functionality |
| `DriverManager` | ThreadLocal WebDriver management |
| `Page Objects` | UI locators & reusable actions |
| `Test Classes` | Test scenarios & validations |
| `WaitUtils` | Centralized explicit waits |
| `ConfigReader` | Configuration management |
| `ExcelUtils` | Excel test-data handling |
| `ScreenshotUtils` | Failure screenshot capture |
| `ExtentReportListener` | Test reporting |
| `test-data/` | Excel test data |
| `testng.xml` | Test suite & parallel execution |
| `pom.xml` | Maven configuration |

## 🧪 Test Execution

**Smoke Tests:** Login, Home, Product & Checkout flows

**Regression Tests:** Complete functional test suite

**Parallel Execution:** TestNG `parallel="methods"` with `thread-count="2"` using ThreadLocal WebDriver.

**Latest Execution:** 17 Tests | 17 Passed | 0 Failed | 0 Skipped | **BUILD SUCCESS**

**Reports:** ExtentReports HTML report with failure screenshots.

## 🔄 CI/CD

GitHub Actions automates Maven test execution using:

- Java 11
- Ubuntu
- Chrome
- Maven
- ExtentReports artifact upload

### CI/CD Flow

    Code Push / Pull Request
              ↓
         GitHub Actions
              ↓
            Java 11
              ↓
            Chrome
              ↓
         mvn clean test
              ↓
         Extent Report

Workflow file:

`.github/workflows/main.yml`

## ▶️ How to Run

### Clone Repository

    git clone https://github.com/mahesh001-sys/selenium-automation-framework.git
    cd selenium-automation-framework

### Run Complete Suite

    mvn clean test

### Smoke Tests

    mvn clean test -Dgroups=smoke

### Regression Tests

    mvn clean test -Dgroups=regression

### Firefox

    mvn clean test -Dbrowser=firefox

### Edge

    mvn clean test -Dbrowser=edge

### Headless Execution

    mvn clean test -Dheadless=true

## 🤖 AI-Assisted Development

AI tools were used as development assistance for understanding concepts, debugging, test-case ideas and documentation.

All suggestions were reviewed, adapted and validated through actual framework execution.

## 👨‍💻 Author

**Banoth Mahesh Kumar**  
B.Tech Information Technology  
**Java | Selenium | QA Automation | Software Testing**

## 🔗 Repository

https://github.com/mahesh001-sys/selenium-automation-framework
