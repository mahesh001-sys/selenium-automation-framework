# Selenium Automation Framework

A scalable and maintainable Selenium WebDriver automation framework built using Java, TestNG, Maven, Page Object Model, and industry-standard automation practices.

## 🚀 Project Overview

This project demonstrates a complete UI automation framework for the SauceDemo web application.

The framework includes:

- Selenium WebDriver
- Java
- TestNG
- Maven
- Page Object Model (POM)
- Data-Driven Testing
- Parallel Execution
- Explicit Waits
- Cross-Browser Testing
- ExtentReports
- Automatic Failure Screenshots
- Configuration Management
- GitHub Actions CI/CD
- Excel Test Data
- AI-Assisted Development

## 🏗️ Framework Architecture

selenium-automation-framework/
├── src/
│   └── main/
│       └── java/
│           └── com/automation/
│               ├── base/
│               │   └── BaseTest.java
│               ├── config/
│               │   └── ConfigReader.java
│               ├── pages/
│               │   ├── LoginPage.java
│               │   ├── HomePage.java
│               │   ├── CartPage.java
│               │   └── CheckoutPage.java
│               ├── tests/
│               │   ├── LoginTest.java
│               │   ├── HomePageTest.java
│               │   ├── ProductTest.java
│               │   └── CheckoutTest.java
│               └── utils/
│                   ├── DriverManager.java
│                   ├── ExcelUtils.java
│                   ├── WaitUtils.java
│                   ├── ScreenshotUtils.java
│                   └── ExtentReportListener.java
├── test-data/
│   └── TestData.xlsx
├── reports/
│   └── ExtentReport.html
├── testng.xml
├── pom.xml
└── README.md

## 🔄 Test Execution Flow

TestNG
  ↓
BaseTest
  ↓
DriverManager
  ↓
WebDriver Initialization
  ↓
Page Object
  ↓
Test Execution
  ↓
Assertions
  ↓
ExtentReportListener
  ↓
Pass / Fail Result
  ↓
Failure Screenshot

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java | Programming Language |
| Selenium WebDriver | Browser Automation |
| TestNG | Test Execution and Assertions |
| Maven | Build and Dependency Management |
| WebDriverManager | Browser Driver Management |
| ExtentReports | HTML Test Reporting |
| Apache POI | Excel Test Data Handling |
| Git | Version Control |
| GitHub | Source Code Management |
| GitHub Actions | CI/CD Automation |

## 🧩 Core Framework Components

### DriverManager

Responsible for:

- WebDriver initialization
- Chrome support
- Firefox support
- Edge support
- Headless execution
- Browser configuration
- ThreadLocal WebDriver management
- Driver cleanup

### BaseTest

Provides common test setup and teardown:

- WebDriver initialization
- Application URL launch
- Driver validation
- Browser cleanup after test execution

### Page Object Model

The framework separates page locators and page actions from test cases.

Current page objects include:

- LoginPage
- HomePage
- CartPage
- CheckoutPage

Benefits:

- Better maintainability
- Code reusability
- Improved readability
- Separation of test logic and page logic

### ConfigReader

Centralizes framework configuration such as:

- Browser
- Base URL
- Headless mode
- Page load timeout
- Screenshot location

### WaitUtils

Provides reusable Selenium wait functionality to improve synchronization and reduce flaky test execution.

### ExcelUtils

Supports reading test data from Excel files using Apache POI for data-driven testing.

### ScreenshotUtils

Captures screenshots automatically when a test fails and stores them in the configured screenshot directory.

### ExtentReportListener

Integrates TestNG with ExtentReports and records:

- Test execution
- Passed tests
- Failed tests
- Skipped tests
- Failure details
- Failure screenshots

## 🧪 Test Coverage

### Login

- Valid login
- Invalid login
- Login validation

### Home Page

- Page validation
- Product visibility
- Navigation validation

### Product

- Product selection
- Add-to-cart functionality
- Product validation

### Checkout

- Cart validation
- Checkout information
- Order flow validation

## 📊 TestNG Configuration

The framework uses testng.xml to organize test execution.

Test groups include:

- Smoke Tests
- Regression Tests

Parallel execution is configured using TestNG:

parallel="methods"

thread-count="2"

This helps reduce overall execution time.

## 🔢 Test Execution Result

Latest successful execution:

Tests run: 17
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS

## 📸 Failure Screenshot Handling

The framework automatically captures a screenshot whenever a test fails.

Failure flow:

Test Failure
  ↓
ExtentReportListener
  ↓
ScreenshotUtils
  ↓
Capture Browser Screenshot
  ↓
Save Screenshot
  ↓
Attach Screenshot to Extent Report

The failure screenshot functionality was validated using an intentional test failure and then the temporary test was removed from the framework.

## 📈 ExtentReports

The framework generates an HTML execution report containing:

- Test names
- Test status
- Failure details
- Execution information
- Failure screenshots

Report location:

reports/ExtentReport.html

## 🌐 Cross-Browser Testing

The framework supports:

- Chrome
- Firefox
- Edge

Browser selection is configurable.

The framework also supports headless browser execution, which is useful for CI/CD environments.

## 📂 Test Data

External test data is maintained in:

test-data/TestData.xlsx

Using external test data helps separate test data from automation logic and supports reusable data-driven testing.

## 🤖 AI Tools & Development Assistance

AI tools were used as development assistance during the project for:

- Understanding Selenium and Java concepts
- Generating and improving code ideas
- Debugging errors
- Identifying framework issues
- Creating test scenarios
- Improving automation logic
- Documentation and README preparation
- Interview preparation

AI-generated suggestions were reviewed, integrated where appropriate, and validated by executing the automation tests.

## 🔄 CI/CD

The project is integrated with GitHub Actions for automated test execution.

CI/CD flow:

GitHub Push
  ↓
GitHub Actions
  ↓
Checkout Repository
  ↓
Setup Java
  ↓
Setup Browser
  ↓
Maven Test Execution
  ↓
Generate Extent Report
  ↓
Upload Test Report

This allows the automation suite to be executed automatically when changes are pushed to the repository.

## ▶️ How to Run

### Clone the Repository

git clone https://github.com/mahesh001-sys/selenium-automation-framework.git

### Navigate to the Project

cd selenium-automation-framework

### Run Tests

mvn clean test

### View the Report

After execution, open:

reports/ExtentReport.html

## 💡 Key Skills Demonstrated

- Java Programming
- Selenium WebDriver
- TestNG
- Page Object Model
- Test Automation Framework Design
- Data-Driven Testing
- Explicit Waits
- Cross-Browser Testing
- Parallel Test Execution
- Screenshot Automation
- ExtentReports
- Maven
- Git and GitHub
- GitHub Actions
- CI/CD
- Debugging
- AI-Assisted Development

## ⭐ Project Highlights

- Modular Selenium automation framework
- Reusable Page Object Model
- Thread-safe WebDriver management using ThreadLocal
- Configurable browser execution
- Chrome, Firefox and Edge support
- Headless execution support
- Smoke and Regression test grouping
- Parallel test execution
- Excel-based test data support
- Automatic failure screenshots
- Extent HTML reporting
- GitHub Actions CI/CD integration
- 17 automated tests passing successfully

## 👨‍💻 Author

Banoth Mahesh Kumar

B.Tech – Information Technology

Aspiring QA Automation Engineer

## 🔗 GitHub Repository

https://github.com/mahesh001-sys/selenium-automation-framework

⭐ If you find this project useful, feel free to explore the repository and review the framework implementation.
