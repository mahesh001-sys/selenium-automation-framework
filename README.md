# 🚀 Selenium Automation Framework

### Enterprise-Style UI Test Automation Framework | Java • Selenium • TestNG • Maven

[![Java](https://img.shields.io/badge/Java-11-orange)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.18.1-green)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9.0-red)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-Build-blue)](https://maven.apache.org/)
[![CI](https://img.shields.io/badge/GitHub_Actions-CI%2FCD-success)](https://github.com/mahesh001-sys/selenium-automation-framework/actions)

A maintainable Selenium WebDriver automation framework built with **Java, TestNG, Maven and Page Object Model (POM)** for automating real-world e-commerce workflows on the SauceDemo application.

The framework demonstrates practical QA automation engineering concepts including **reusable page objects, parallel execution, data-driven testing, cross-browser testing, centralized waits, automatic failure screenshots, ExtentReports and CI/CD execution**.

---

## 🎯 What This Project Automates

The framework covers the complete user journey from authentication to checkout.

| Area | Automated Scenarios |
|---|---|
| 🔐 Authentication | Valid login, invalid login, negative login scenarios |
| 🏠 Home Page | Product page validation, product visibility, logout |
| 🛍️ Products | Product selection, sorting, add-to-cart |
| 🛒 Cart | Cart validation and product verification |
| 💳 Checkout | Checkout workflow and validation scenarios |
| 📊 Test Data | Excel-based data-driven login testing |

**Application Under Test:** [SauceDemo](https://www.saucedemo.com/)

---

## 🏗️ Framework Design

The framework follows a layered automation design:

**Test Layer**  
→ TestNG test cases and assertions

**Page Layer**  
→ Page Objects containing locators and reusable actions

**Utility Layer**  
→ Driver management, waits, Excel handling and screenshots

**Reporting Layer**  
→ ExtentReports and TestNG listener

**Configuration Layer**  
→ Browser, URL and execution settings

This separation keeps the automation code **reusable, maintainable and easier to scale**.

---

## 🧰 Technology Stack

| Technology | Usage |
|---|---|
| Java 11 | Automation programming |
| Selenium WebDriver 4.18.1 | Browser automation |
| TestNG 7.9.0 | Test execution, assertions and groups |
| Maven | Build and dependency management |
| WebDriverManager | Browser driver management |
| Apache POI | Excel test data |
| ExtentReports 5.1.1 | HTML reporting |
| Commons IO | File and screenshot handling |
| Git & GitHub | Version control |
| GitHub Actions | CI/CD automation |

---

## ⭐ Key Framework Features

### Page Object Model

Application pages are represented using dedicated Page Object classes.

This separates:

- UI locators
- Page actions
- Test scenarios
- Assertions

Result: cleaner and more maintainable test code.

### ThreadLocal WebDriver

The framework uses `ThreadLocal<WebDriver>` to maintain isolated browser instances during parallel execution.

This allows multiple test methods to execute without sharing the same WebDriver instance.

### Parallel Execution

TestNG is configured for method-level parallel execution with two threads.

This demonstrates the framework's ability to execute independent tests concurrently.

### Centralized Explicit Waits

Reusable wait utilities are used instead of hard-coded delays.

The framework supports synchronization such as:

- Element visibility
- Element clickability
- Element presence
- URL validation
- Page title validation

### Data-Driven Testing

Login scenarios use external Excel test data through **Apache POI and TestNG DataProvider**.

Test data is maintained separately from the automation logic.

### Cross-Browser Testing

Supported browsers:

- Chrome
- Firefox
- Edge

Browser execution can be configured without changing the test cases.

### Automatic Failure Screenshots

When a test fails:

1. TestNG listener detects the failure.
2. ScreenshotUtils captures the browser state.
3. The screenshot is attached to the Extent Report.
4. The failure can be investigated directly from the report.

---

## 📊 Test Execution

### Latest Successful Execution

| Result | Count |
|---|---:|
| Total Tests | **17** |
| Passed | **17** |
| Failed | **0** |
| Errors | **0** |
| Skipped | **0** |
| Build Status | **BUILD SUCCESS** |

The failure-screenshot mechanism was also validated using an intentional test failure before removing the temporary validation test.

---

## 📈 Test Reporting

The framework generates an **ExtentReports HTML report** containing:

- Test execution status
- Test names
- Failure details
- Test descriptions
- Execution information
- Failure screenshots

**Report:** `reports/ExtentReport.html`

The reporting process is handled automatically through `ExtentReportListener`, so individual test classes do not need to contain reporting code.

---

## 🧪 TestNG Organization

Tests are organized using TestNG groups:

**Smoke**

Focused on important application functionality.

**Regression**

Covers broader functional and negative scenarios.

The framework also supports parallel method execution through the TestNG suite configuration.

---

## 🔄 CI/CD

The project is integrated with **GitHub Actions** for automated test execution.

CI pipeline:

**Git Push**  
→ **Checkout Repository**  
→ **Setup Java**  
→ **Setup Browser**  
→ **Maven Test Execution**  
→ **Generate Test Report**  
→ **Publish Test Results**

This allows the automation suite to be executed automatically in a CI environment.

---

## 📂 Project Structure

```text
src/
└── main/
    └── java/
        └── com.automation/
            ├── base/
            ├── config/
            ├── pages/
            ├── tests/
            └── utils/

test-data/
└── TestData.xlsx

reports/
└── ExtentReport.html

testng.xml
pom.xml
README.md
## 📌 Important Components

| Component | Responsibility |
|---|---|
| `BaseTest` | Test setup and teardown |
| `DriverManager` | WebDriver lifecycle |
| `ConfigReader` | Framework configuration |
| `WaitUtils` | Explicit synchronization |
| `ExcelUtils` | Excel test data |
| `ScreenshotUtils` | Failure screenshot capture |
| `ExtentReportListener` | Test reporting |
| Page Objects | Application UI interactions |
| Test Classes | Test scenarios and validations |

---

## ▶️ How to Run

### Clone the Repository

`git clone https://github.com/mahesh001-sys/selenium-automation-framework.git`

### Navigate to the Project

`cd selenium-automation-framework`

### Execute All Tests

`mvn clean test`

### Execute Smoke Tests

`mvn clean test -Dgroups=smoke`

### Execute Regression Tests

`mvn clean test -Dgroups=regression`

### Run on Firefox

`mvn clean test -Dbrowser=firefox`

### Run on Edge

`mvn clean test -Dbrowser=edge`

---

## 🤖 AI-Assisted Development

AI tools were used as development assistance, not as a replacement for validation.

AI assistance was used for:

- Understanding Java and Selenium concepts
- Debugging and troubleshooting
- Improving automation code
- Identifying test scenarios
- Framework design discussions
- Documentation
- Test case ideas
- Interview preparation

All relevant suggestions were reviewed and validated by executing the framework and verifying the results.

---

## 💡 Engineering Practices Demonstrated

- Object-Oriented Programming
- Page Object Model
- Reusable automation components
- Thread-safe WebDriver management
- Explicit synchronization
- Data-driven testing
- Negative testing
- Smoke and regression testing
- Cross-browser automation
- Parallel execution
- Failure diagnostics
- HTML reporting
- Maven-based execution
- CI/CD automation
- Git version control
- AI-assisted development with human validation

---

## 🎓 Project Outcome

This project demonstrates how a Selenium test suite can be transformed into a structured, reusable and CI-ready automation framework rather than a collection of individual Selenium scripts.

The framework brings together:

**Java + Selenium + TestNG + POM + Data-Driven Testing + Parallel Execution + Reporting + CI/CD**

into a single automation solution.

---

## 👨‍💻 Author

### Banoth Mahesh Kumar

**B.Tech – Information Technology**

**Aspiring QA Automation Engineer**

Interested in Software Testing, Selenium Automation, Java and Quality Engineering.

---

## 🔗 Repository

[View the complete source code on GitHub](https://github.com/mahesh001-sys/selenium-automation-framework)

⭐ If you find this project useful, feel free to explore the repository and review the framework implementation.
