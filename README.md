# 🚀 Selenium Automation Framework

### End-to-End E-Commerce Test Automation Framework

Built with **Java 11, Selenium WebDriver, TestNG, Maven and Page Object Model (POM)**.

## 📋 Overview

This project is a Java-based Selenium automation framework designed to automate key e-commerce workflows on **SauceDemo**.

The framework focuses on maintainability, reusability and reliable test execution through:

- Page Object Model (POM)
- Page Factory
- ThreadLocal WebDriver
- Parallel test execution
- Centralized explicit waits
- Data-driven testing with Excel
- Cross-browser execution
- Failure screenshots
- ExtentReports
- Log4j2 logging
- Maven-based execution

## 🎯 What This Project Automates

The framework automates the following SauceDemo workflows:

- 🔐 User login
- ❌ Invalid login scenarios
- 🚫 Locked-out user validation
- 🛍️ Product browsing
- 🔃 Product sorting
- 🛒 Add-to-cart operations
- 🔢 Cart count validation
- 🚪 Logout
- 💳 Checkout workflow
- ❗ Checkout validation scenarios
- 📊 Data-driven login testing

**Application Under Test:** SauceDemo  
https://www.saucedemo.com

## 🏗️ Framework Architecture

The framework follows a layered Page Object Model architecture:

**Test Layer** → TestNG test cases and validations  
**Page Layer** → Page Objects and reusable UI actions  
**Base Layer** → Test lifecycle and WebDriver setup  
**Utility Layer** → Driver, waits, Excel, screenshots and reporting utilities  
**Configuration Layer** → Browser and execution configuration  

### Execution Flow

**TestNG → BaseTest → DriverManager → Page Objects → Test Scenario → WaitUtils → Test Result → ExtentReports**

Parallel execution is supported using `ThreadLocal<WebDriver>` so each test thread maintains its own WebDriver instance.

## 🧰 Technology Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 11 | Programming language |
| Selenium WebDriver | 4.18.1 | Browser automation |
| TestNG | 7.9.0 | Test execution and test organization |
| Maven | 3.x | Build and dependency management |
| WebDriverManager | 5.7.0 | Browser driver management |
| ExtentReports | 5.1.1 | HTML test reporting |
| Apache POI | 5.2.5 | Excel test data handling |
| Log4j2 | 2.23.1 | Logging |
| Commons IO | 2.15.1 | File and screenshot utilities |

## ⭐ Key Features

- **Page Object Model** for maintainable UI automation
- **Page Factory** for Page Object initialization
- **ThreadLocal WebDriver** for thread-safe parallel execution
- **Chrome, Firefox and Edge** browser support
- **Centralized explicit waits** using `WebDriverWait`
- **Data-driven testing** using Apache POI and TestNG DataProvider
- **Automatic failure screenshots**
- **ExtentReports** for execution reporting
- **Log4j2** for console and file logging
- **Smoke and Regression** test groups
- **Headless execution** for supported browser configurations
- **Maven** build and test execution

## 📂 Project Structure

| Component | Purpose |
|---|---|
| `BaseTest` | Test lifecycle, WebDriver setup and cleanup |
| `BasePage` | Common Page Object functionality |
| `DriverManager` | WebDriver creation and lifecycle management |
| `ConfigReader` | Framework configuration |
| `Page Objects` | Application locators and reusable UI actions |
| `Test Classes` | Test scenarios and validations |
| `WaitUtils` | Centralized explicit waits |
| `ExcelUtils` | Excel-based test data handling |
| `ScreenshotUtils` | Failure screenshot capture |
| `ExtentReportListener` | Test execution reporting |
| `test-data/` | Excel test data |
| `reports/` | Reports, screenshots and logs |
| `testng.xml` | TestNG suite configuration |
| `pom.xml` | Maven dependencies and build configuration |

## 🧪 Test Coverage

### 🔐 Login Tests

- Standard user login
- Invalid username and password
- Empty username and password
- Locked-out user validation
- Excel-based data-driven login scenarios

### 🏠 Home Page Tests

- Products page verification
- Product count validation
- Logout functionality

### 🛍️ Product Tests

- Sort products by price
- Sort products by name
- Add product to cart
- Add multiple products

### 💳 Checkout Tests

- Complete checkout workflow
- Missing first-name validation
- Missing postal-code validation

## 📊 Test Execution & Reporting

The framework uses **TestNG** to organize and execute tests through:

- Smoke testing
- Regression testing
- Parallel method execution
- Two parallel threads

Test results are generated using **ExtentReports**.

Failure handling includes:

1. TestNG detects the failure
2. `ScreenshotUtils` captures the browser screenshot
3. The screenshot is attached to the Extent report
4. The screenshot is stored under `reports/screenshots/`

Execution logs are generated using **Log4j2**.

## 🔄 CI/CD

The repository includes a GitHub Actions workflow configuration for automated test execution.

The CI configuration covers:

- Ubuntu runner
- Java 11 Temurin
- Chrome
- Headless Maven test execution
- ExtentReports artifact upload
- Failure screenshot artifact upload

The workflow file is maintained under the repository's GitHub Actions workflow directory.

## ▶️ How to Run

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- Chrome, Firefox or Edge

WebDriverManager handles browser driver setup.

### Clone the Repository

    git clone https://github.com/mahesh001-sys/selenium-automation-framework.git

### Navigate to the Project

    cd selenium-automation-framework

### Execute All Tests

    mvn clean test

### Execute Smoke Tests

    mvn clean test -Dgroups=smoke

### Execute Regression Tests

    mvn clean test -Dgroups=regression

### Run in Headless Mode

    mvn clean test -Dheadless=true

### Run on Firefox

    mvn clean test -Dbrowser=firefox

### Run on Edge

    mvn clean test -Dbrowser=edge

### Run a Specific Test Class

    mvn clean test -Dtest=LoginTest

## 🤖 AI-Assisted Development

AI tools were used as development assistants during the project for:

- Understanding Java and Selenium concepts
- Exploring implementation approaches
- Debugging errors
- Generating test-case ideas
- Reviewing edge cases
- Improving documentation
- Understanding framework concepts

AI-generated suggestions were **reviewed, adapted and validated** rather than blindly copied.

The final implementation was verified through Maven/TestNG execution and by checking the framework behavior.

## 💡 Engineering Practices Demonstrated

- Object-Oriented Programming
- Page Object Model
- Page Factory
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
- Logging
- Maven-based execution
- Git and GitHub
- CI/CD concepts
- AI-assisted development with human validation

## 🎓 Project Outcome

This project demonstrates the transformation of individual Selenium scripts into a **structured, reusable and maintainable automation framework**.

It brings together:

**Java + Selenium + TestNG + POM + Page Factory + Data-Driven Testing + Parallel Execution + Reporting + Logging + CI/CD**

into a single end-to-end automation solution.

## 👨‍💻 Author

### Banoth Mahesh Kumar

**B.Tech Information Technology — Anurag University, Hyderabad**

**Java | Selenium | QA Automation | Software Testing**

## 🔗 Repository

[View the complete source code on GitHub](https://github.com/mahesh001-sys/selenium-automation-framework)

⭐ Feel free to explore the repository and review the framework implementation.
