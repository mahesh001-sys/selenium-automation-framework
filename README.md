<div align="center">

# 🚀 Selenium Automation Framework

### End-to-End E-Commerce Test Automation Framework

Built with **Selenium WebDriver · Java 11 · TestNG · Maven · Page Object Model**

[![Java](https://img.shields.io/badge/Java-11-orange?logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/11/)
[![Selenium](https://img.shields.io/badge/Selenium-4.18.1-43B02A?logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9.0-red)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![GitHub](https://img.shields.io/badge/GitHub-Repository-181717?logo=github&logoColor=white)](https://github.com/mahesh001-sys/selenium-automation-framework)

</div>

---

## 📋 Overview

**Selenium Automation Framework** is a Java-based end-to-end test automation framework built to automate key e-commerce workflows on **SauceDemo**.

The framework demonstrates practical automation concepts including:

- Selenium WebDriver
- Java 11
- TestNG
- Page Object Model (POM)
- Page Factory
- ThreadLocal WebDriver
- Parallel test execution
- Explicit waits
- Data-driven testing with Excel
- ExtentReports
- Failure screenshots
- Log4j2 logging
- Maven build and test execution
- Cross-browser support

The framework is designed with maintainability and reusability in mind, keeping page locators, browser management, synchronization, test data, reporting, and test cases separated into dedicated components.

---

## 🎯 What This Project Automates

The framework targets **SauceDemo**, an e-commerce practice application.

Automated workflows include:

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

Target application:

https://www.saucedemo.com

---

## 🏗️ Framework Architecture

<pre>
selenium-automation-framework/
│
├── src/
│   ├── main/
│   │   └── java/com/automation/
│   │       ├── config/
│   │       │   └── ConfigReader.java
│   │       │
│   │       ├── pages/
│   │       │   ├── BasePage.java
│   │       │   ├── LoginPage.java
│   │       │   ├── HomePage.java
│   │       │   ├── CartPage.java
│   │       │   └── CheckoutPage.java
│   │       │
│   │       └── utils/
│   │           ├── DriverManager.java
│   │           ├── WaitUtils.java
│   │           ├── ScreenshotUtils.java
│   │           ├── ExcelUtils.java
│   │           └── ExtentReportListener.java
│   │
│   └── test/
│       ├── java/com/automation/
│       │   ├── base/
│       │   │   └── BaseTest.java
│       │   │
│       │   └── tests/
│       │       ├── LoginTest.java
│       │       ├── HomePageTest.java
│       │       ├── ProductTest.java
│       │       └── CheckoutTest.java
│       │
│       └── resources/
│           ├── config.properties
│           ├── log4j2.xml
│           └── testng.xml
│
├── reports/
│   ├── ExtentReport.html
│   ├── screenshots/
│   └── logs/
│       └── automation.log
│
├── test-data/
│   └── TestData.xlsx
│
├── pom.xml
├── .gitignore
├── CONTRIBUTING.md
├── selenium-ci.yml
├── testng.xml
└── README.md
</pre>

---

## 🔄 Framework Execution Flow

<pre>
                 TestNG Test
                     │
                     ▼
                BaseTest
                     │
             @BeforeMethod
                     │
                     ▼
             DriverManager
                     │
             ThreadLocal Driver
                     │
                     ▼
              Open Browser
                     │
                     ▼
               BasePage
                     │
          Page Object / PageFactory
                     │
                     ▼
              Test Scenario
                     │
                     ▼
                WaitUtils
                     │
              Explicit Waits
                     │
                     ▼
             Test Execution
                     │
          ┌──────────┴──────────┐
          ▼                     ▼
       Success                Failure
          │                     │
          ▼                     ▼
       TestNG              ScreenshotUtils
          │                     │
          └──────────┬──────────┘
                     ▼
          ExtentReportListener
                     │
                     ▼
          ExtentReport.html
</pre>

---

## 🧰 Technology Stack

| Technology | Version | Purpose |
|---|---:|---|
| ☕ Java | 11 | Core programming language |
| 🌐 Selenium WebDriver | 4.18.1 | Browser automation |
| 🧪 TestNG | 7.9.0 | Test execution, groups and parallel execution |
| 📦 Maven | 3.x | Build and dependency management |
| 🚗 WebDriverManager | 5.7.0 | Browser driver setup |
| 📊 ExtentReports | 5.1.1 | HTML test reporting |
| 📑 Apache POI | 5.2.5 | Excel-based test data |
| 📝 Log4j2 | 2.23.1 | Application/test logging |
| 📁 Commons IO | 2.15.1 | File and screenshot utilities |

---

## 🧱 Core Framework Components

### `ConfigReader`

A singleton configuration reader responsible for loading:

`src/test/resources/config.properties`

It provides configuration values such as:

- Browser
- Application URL
- Headless mode
- Explicit wait timeout

---

### `DriverManager`

Manages WebDriver instances using:

`ThreadLocal<WebDriver>`

Supported browsers:

- Chrome
- Firefox
- Edge

Using `ThreadLocal` allows each parallel test thread to maintain its own WebDriver instance.

---

### `BasePage`

Common parent class for Page Objects.

Responsibilities include:

- Accessing WebDriver
- Creating `WaitUtils`
- Initializing Page Factory
- Reusable click operation
- Reusable text input
- Reading element text
- Checking element visibility
- Getting page title
- Getting current URL

---

### `WaitUtils`

Centralized explicit wait utility built using:

`WebDriverWait`

and:

`ExpectedConditions`

Supported wait operations include:

- Visibility
- Clickability
- Presence
- Invisibility
- Page title
- URL

The framework does not rely on `Thread.sleep()` for synchronization.

---

### `ExcelUtils`

Uses **Apache POI** to read Excel test data.

The utility returns data in a format suitable for a TestNG `@DataProvider`.

---

### `ExtentReportListener`

Implements:

- `ITestListener`
- `ISuiteListener`

It automatically:

- Creates the Extent report
- Logs test status
- Captures failure information
- Attaches failure screenshots
- Flushes the report after suite execution

---

### `BaseTest`

Provides the TestNG test lifecycle:

- `@BeforeMethod`
- WebDriver initialization
- Application launch
- `@AfterMethod`
- WebDriver cleanup

---

## 🧪 Test Coverage

### 🔐 LoginTest

| Scenario | Type |
|---|---|
| Standard user login | Smoke / Regression |
| Invalid username and password | Negative |
| Empty username and password | Negative |
| Locked-out user | Negative |
| Excel-based login scenarios | Data-driven |

---

### 🏠 HomePageTest

| Scenario | Type |
|---|---|
| Verify Products page | Smoke |
| Verify product count | Functional |
| Logout from application | Functional |

---

### 🛍️ ProductTest

| Scenario | Type |
|---|---|
| Sort products by price | Functional |
| Sort products by name | Functional |
| Add product to cart | Functional |
| Add multiple products | Functional |

---

### 💳 CheckoutTest

| Scenario | Type |
|---|---|
| Complete checkout workflow | End-to-End |
| Missing first name validation | Negative |
| Missing postal code validation | Negative |

---

## 📊 TestNG Organization

The framework uses TestNG groups for organizing tests.

### Smoke

Used for important functional checks such as:

- Login
- Home page
- Product functionality
- Checkout functionality

### Regression

Used for broader functional and negative scenarios.

The TestNG suite also configures:

- Method-level parallel execution
- 2 parallel threads
- ExtentReports listener

This allows the framework to demonstrate parallel test execution with isolated WebDriver instances.

---

## ⚡ Parallel Execution

The framework uses:

`parallel="methods"`

with:

`thread-count="2"`

in the TestNG suite.

To safely support parallel execution, WebDriver instances are stored using:

`ThreadLocal<WebDriver>`

### Execution Model

<pre>
Thread 1 ──► WebDriver Instance 1 ──► Test A
Thread 2 ──► WebDriver Instance 2 ──► Test B
</pre>

This avoids sharing a single browser instance between parallel test methods.

---

## ⏳ Synchronization Strategy

The framework uses centralized explicit waits instead of `Thread.sleep()`.

Example wait types include:

- `visibilityOf`
- `elementToBeClickable`
- `presenceOfElementLocated`
- `invisibilityOf`
- `titleContains`
- `urlContains`

This keeps synchronization logic reusable across Page Objects.

---

## 📑 Data-Driven Testing

Login scenarios use Excel-based test data through Apache POI and TestNG `@DataProvider`.

The data source is:

`test-data/TestData.xlsx`

Sheet:

`LoginData`

Example test data:

| Username | Password | Expected |
|---|---|---|
| standard_user | secret_sauce | pass |
| locked_out_user | secret_sauce | fail |
| invalid_user | wrong_pass | fail |
| empty username | secret_sauce | fail |
| standard_user | empty password | fail |

The test reads the rows dynamically and executes the same test logic against multiple datasets.

---

## 📸 Failure Screenshots

When a TestNG test fails:

1. The listener detects the failure.
2. `ScreenshotUtils` captures the browser screenshot.
3. The screenshot is attached to the Extent report.
4. The screenshot is stored under:

`reports/screenshots/`

This makes failed test investigation easier.

---

## 📊 Test Reporting

The framework generates:

`reports/ExtentReport.html`

The Extent report provides:

- Test name
- PASS / FAIL / SKIP status
- Test descriptions
- Failure information
- Failure screenshots
- Tester information
- Application information
- Environment information
- Execution details

The report is generated automatically through `ExtentReportListener`.

---

## 📝 Logging

The project uses **Log4j2** for logging.

Console logging is configured along with file logging.

The file log is generated at:

`reports/logs/automation.log`

The logging configuration is maintained in:

`src/test/resources/log4j2.xml`

---

## ⚙️ Configuration

The framework loads its configuration from:

`src/test/resources/config.properties`

Current configuration includes:

<pre><code>browser=chrome
base.url=https://www.saucedemo.com
headless=false
explicit.wait=10
page.load.timeout=30
test.data.path=test-data/TestData.xlsx
report.path=reports/ExtentReport.html
screenshot.path=reports/screenshots/</code></pre>

### Supported Browsers

- Chrome
- Firefox
- Edge

### Headless Execution

Chrome and Firefox support headless mode through the current driver configuration.

Edge is supported for browser execution, but the current `DriverManager` does not apply the headless option to Edge.

---

## 🚀 Getting Started

### Prerequisites

Install:

- Java 11 or higher
- Maven 3.6+
- A supported browser such as Chrome

WebDriverManager handles browser driver setup for the supported browsers.

---

## 📥 Clone the Repository

    git clone https://github.com/mahesh001-sys/selenium-automation-framework.git

    cd selenium-automation-framework

---

## ▶️ Run Tests

Run the complete Maven/TestNG suite:

    mvn clean test

---

## 🧪 Run Smoke Tests

    mvn clean test -Dgroups=smoke

---

## 🔬 Run Regression Tests

    mvn clean test -Dgroups=regression

---

## 🖥️ Run Headless Tests

    mvn clean test -Dheadless=true

This is useful for CI environments where a visible browser window is not required.

---

## 🌐 Run on Firefox

    mvn clean test -Dbrowser=firefox

---

## 🌐 Run on Edge

    mvn clean test -Dbrowser=edge

---

## 🎯 Run a Specific Test Class

    mvn clean test -Dtest=LoginTest

---

## 🎯 Run a Specific Test Method

    mvn clean test -Dtest=LoginTest#testValidLogin

---

## 🔧 Run with a Different Browser

The browser can also be changed in:

`src/test/resources/config.properties`

Example:

<pre><code>browser=firefox</code></pre>

---

## 🔄 CI/CD

A GitHub Actions workflow definition is included in:

`selenium-ci.yml`

The workflow is configured for:

- Push to `main`
- Push to `develop`
- Pull requests to `main`
- Manual workflow dispatch
- Ubuntu runner
- Java 11 Temurin
- Chrome
- Headless Maven test execution
- Extent report artifact upload
- Failure screenshot artifact upload

### Important Repository Configuration

For GitHub Actions to automatically discover and execute this workflow, the file should be placed at:

`.github/workflows/selenium-ci.yml`

The current repository contains the workflow definition at the project root as `selenium-ci.yml`. Moving it into `.github/workflows/` will activate it as a GitHub Actions workflow.

---

## 🧠 Key Design Decisions

### 1. Page Object Model

Each application page has its own Page Object class.

Benefits:

- Separates test logic from UI locators
- Improves maintainability
- Encourages reusable page actions
- Reduces duplication

---

### 2. Page Factory

Page Objects are initialized using Selenium's:

`PageFactory.initElements()`

This keeps element definitions organized inside their respective Page Object classes.

---

### 3. ThreadLocal WebDriver

Each test thread receives its own WebDriver instance.

This supports parallel TestNG execution while avoiding shared browser state.

---

### 4. Centralized Explicit Waits

Wait logic is centralized inside `WaitUtils`.

This avoids duplicating wait implementation across every Page Object.

---

### 5. Configuration Management

Browser, URL, headless mode and wait configuration are separated from test logic through `config.properties`.

---

### 6. Listener-Based Reporting

Tests do not need to manually create ExtentReports entries.

`ExtentReportListener` handles:

- Test start
- Test success
- Test failure
- Test skip
- Screenshot attachment
- Report generation

---

### 7. Data-Driven Testing

Apache POI + TestNG DataProvider allows multiple login datasets to be executed through the same test method.

---

## 🔁 End-to-End Example

A typical checkout test follows this structure:

<pre>
Start Test
    ↓
Initialize WebDriver
    ↓
Open SauceDemo
    ↓
Login
    ↓
Navigate to Products
    ↓
Select Product
    ↓
Add Product to Cart
    ↓
Open Cart
    ↓
Proceed to Checkout
    ↓
Enter Checkout Details
    ↓
Complete Order
    ↓
Validate Confirmation
    ↓
Generate Test Result
    ↓
Close WebDriver
</pre>

---

## 📁 Important Project Outputs

| Path | Purpose |
|---|---|
| `reports/ExtentReport.html` | HTML execution report |
| `reports/screenshots/` | Failure screenshots |
| `reports/logs/automation.log` | Execution logs |
| `test-data/TestData.xlsx` | Excel test data |
| `src/test/resources/config.properties` | Framework configuration |
| `src/test/resources/testng.xml` | TestNG suite configuration |
| `src/test/resources/log4j2.xml` | Logging configuration |

---

## 🛠️ Maven Dependencies

The project uses the following primary dependencies:

- Selenium Java 4.18.1
- TestNG 7.9.0
- WebDriverManager 5.7.0
- ExtentReports 5.1.1
- Apache POI 5.2.5
- Commons IO 2.15.1
- Log4j2 2.23.1

---

## 🎓 Project Background

This project was developed as part of the **GUVI Automation Testing with Selenium (Java)** training program.

The project brings together practical concepts including:

- Software testing fundamentals
- Java programming
- Selenium WebDriver
- TestNG
- Page Object Model
- Test data management
- Automation framework design
- Reporting
- Logging
- Parallel execution
- CI/CD concepts

The application under test is **SauceDemo**, a web application designed for automation practice.

---

## 🤖 AI-Assisted Development

AI tools may be used as development assistants for tasks such as:

- Understanding Selenium and Java concepts
- Exploring implementation approaches
- Debugging errors
- Generating test-case ideas
- Reviewing edge cases
- Improving documentation
- Understanding framework concepts

AI-generated suggestions should be reviewed, adapted, compiled, and tested rather than blindly copied.

The final implementation is validated through the project's Maven/TestNG execution and framework behavior.

---

## 📈 Future Improvements

Potential future enhancements include:

- [ ] Activate GitHub Actions workflow from `.github/workflows/`
- [ ] Add more e-commerce workflows
- [ ] Expand Excel-based data-driven scenarios
- [ ] Add more negative test cases
- [ ] Add additional browser configuration options
- [ ] Improve parallel execution scalability
- [ ] Add richer reporting information
- [ ] Add API testing integration
- [ ] Add database validation
- [ ] Add retry analyzer for selected transient failures
- [ ] Integrate Allure reporting
- [ ] Add Docker-based execution
- [ ] Expand CI/CD pipeline

---

## 📚 Skills Demonstrated

This project demonstrates practical knowledge of:

**Automation Testing**
- Selenium WebDriver
- TestNG
- Functional Testing
- Regression Testing
- Smoke Testing
- Negative Testing
- End-to-End Testing

**Framework Design**
- Page Object Model
- Page Factory
- ThreadLocal WebDriver
- Driver Factory
- Utility classes
- Test lifecycle management
- Listener-based reporting

**Test Engineering**
- Explicit waits
- Data-driven testing
- Excel test data
- Failure screenshots
- Logging
- Cross-browser execution
- Parallel execution

**Build & DevOps**
- Maven
- Git
- GitHub
- GitHub Actions configuration
- Headless browser execution

---

## 👤 Author

### **Banoth Mahesh Kumar**

**B.Tech Information Technology — Anurag University, Hyderabad**

Java | Selenium | QA Automation | Software Testing

[![GitHub](https://img.shields.io/badge/GitHub-mahesh001--sys-181717?style=for-the-badge&logo=github)](https://github.com/mahesh001-sys)

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Mahesh%20Kumar-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mahesh001)

📧 **maheshbanoth057@gmail.com**

---

## 🔗 Project Links

**GitHub Repository:**  
https://github.com/mahesh001-sys/selenium-automation-framework

**SauceDemo:**  
https://www.saucedemo.com

---

<div align="center">

### ⭐ Built with Selenium, Java, TestNG and a focus on maintainable test automation.

</div>
