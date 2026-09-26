# 🚀 Selenium Automation Framework

### Java • Selenium • TestNG • Maven • POM

A reusable Selenium automation framework for testing key e-commerce workflows on **SauceDemo**.

## 🎯 What This Project Automates

- User Login & Logout
- Invalid & Locked User Validation
- Product Browsing & Sorting
- Add to Cart & Cart Validation
- Checkout Workflow & Validations
- Data-Driven Login Testing

## 🏗️ Framework Architecture

**TestNG → BaseTest → DriverManager → Page Objects → Utilities → Reports**

The framework follows **Page Object Model (POM)** with Page Factory and uses `ThreadLocal<WebDriver>` for thread-safe execution.

## 🧰 Technology Stack

| Technology | Purpose |
|---|---|
| Java 11 | Programming |
| Selenium 4.18.1 | UI Automation |
| TestNG 7.9.0 | Test Execution |
| Maven | Build & Dependencies |
| Apache POI | Excel Data |
| ExtentReports | HTML Reporting |
| Log4j2 | Logging |
| WebDriverManager | Browser Drivers |

## ⭐ Key Features

- Page Object Model & Page Factory
- Chrome, Firefox & Edge
- Parallel Execution
- Thread-safe WebDriver
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
| `BasePage` | Common Page functionality |
| `DriverManager` | WebDriver management |
| `Page Objects` | UI locators & actions |
| `Test Classes` | Test scenarios |
| `Utils` | Waits, Excel & screenshots |
| `ExtentReportListener` | Test reporting |
| `test-data/` | Excel test data |
| `testng.xml` | Test suite |
| `pom.xml` | Maven configuration |

## 🧪 Test Execution

**Smoke Tests:** Login, Home, Product & Checkout flows

**Regression Tests:** Complete functional test suite

**Latest Execution:** 17 Tests | 17 Passed | 0 Failed | BUILD SUCCESS

## 🔄 CI/CD

GitHub Actions is configured for automated Maven test execution with headless Chrome and test artifacts.

## ▶️ How to Run

    git clone https://github.com/mahesh001-sys/selenium-automation-framework.git
    cd selenium-automation-framework
    mvn clean test

### Smoke

    mvn clean test -Dgroups=smoke

### Regression

    mvn clean test -Dgroups=regression

### Firefox

    mvn clean test -Dbrowser=firefox

### Edge

    mvn clean test -Dbrowser=edge

## 🤖 AI-Assisted Development

AI tools were used as development assistance for understanding concepts, debugging, test-case ideas and documentation.

All generated suggestions were reviewed, adapted and validated through actual framework execution.

## 🎓 Project Outcome

A structured, reusable and CI-ready Selenium automation framework demonstrating:

**Java + Selenium + TestNG + POM + Data-Driven Testing + Parallel Execution + Reporting + CI/CD**

## 👨‍💻 Author

**Banoth Mahesh Kumar**  
B.Tech Information Technology  
**Java | Selenium | QA Automation | Software Testing**

## 🔗 Repository

https://github.com/mahesh001-sys/selenium-automation-framework
