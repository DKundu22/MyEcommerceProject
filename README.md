
# 🛒 MyEcommerceProject – Selenium Automation Framework

This is a modular and scalable test automation framework built with **Selenium WebDriver**, **Java**, and **TestNG** for automating key functionalities of an e-commerce website.

## 🚀 Tech Stack

- **Language**: Java
- **Frameworks**: Selenium, TestNG
- **Build Tool**: Maven
- **Reporting**: ExtentReports
- **Logging**: Log4j2
- **CI/CD Support**: Jenkins (configurable)
- **Data Handling**: Apache POI (Excel), json 
- **Design Pattern**: Page Object Model (POM)

---

## 📁 Project Structure

```
MyEcommerceProject/
│
├── src/
│   ├── main/
│   │   ├── java/com/ecommerce/
│   │   │   ├── actiondriver/            # Reusable UI actions (click, type, wait, etc.)
│   │   │   ├── actioninterface/         # Action interfaces for driver abstraction
│   │   │   ├── base/                    # BaseClass: WebDriver setup, teardown
│   │   │   ├── pageobjects/             # Page classes for POM design
│   │   │   └── utility/                 # ConfigReader, Logger, ExtentReport setup, ExcelUtil, JsonDataReader
│   │   └── resources/                   # Log4j2 config
│
│   ├── test/
│   │   ├── java/com/ecommerce/testcases/  # TestNG test classes
│   │   └── resources/TestData/            # Excel & JSON data for DDT
│
├── Configuration/
│   └── Config.properties               # Environment config: browser, URL, timeouts
│
├── Screenshots/                        # Captured on test failure
├── logs/                               # Log files from Log4j2
├── test-output/                        # TestNG default report output, Extent Report 
├── pom.xml                             # Maven dependencies and build config
├── testng-smoke.xml                    # Smoke test suite
├── testng-regression.xml               # Regression test suite
└── .gitignore 
```

---

## ✅ Features

- 📦 **Modular Framework**: Built using Page Object Model for maintainability.
- 📊 **Advanced Reporting**: Integrated ExtentReports with screenshots on failure.
- 🔐 **External Config Support**: Manage browser & test environment through `Config.properties`.
- 📁 **Data-Driven Testing (DDT)**: Read data from Excel using Apache POI & also read additional data from JSON 
- 📄 **Grouped Test Suites**: Separate `testng-smoke.xml` and `testng-regression.xml`.
- 🪵 **Robust Logging**: Log4j2 for granular logging and test debugging.
- 📸 **Screenshots on Failure**: Automatically captured and stored in `Screenshots/`.

---

## 🧪 Test Scenarios Implemented

| Test Cases                    | Description                                           |
|-------------------------------|-------------------------------------------------------|
| **LoginPageTest**             | Validates login functionality(both valid and invalid data) |
| **RegisterUserTest**          | Automates user registration with data-driven input   |
| **ProductsPageTest**          | Validates 'All Products' view and product presence   |
| **SearchProductTest**         | Searches for products and verifies search results    |
| **CartTest**                  | Adds products to cart, updates quantity, validates total |
| **CheckoutTest**              | Checkout after login, Add product to cart > Register > checkout|

---

## 🧰 How to Run the Tests

1. **Clone the repo**
   ```bash
   git clone https://github.com/DKundu22/MyEcommerceProject.git
   cd MyEcommerceProject
   ```

2. **Update `Config.properties`**
   - Set browser, base URL, implicit waits, etc.

3. **Run via Maven**
   ```bash
   mvn clean test -DsuiteXmlFile=testng.xml
   ```

4. **Run specific suite**
   ```bash
   mvn test -DsuiteXmlFile=testng-smoke.xml
   ```

5. **View Reports**
   - Navigate to `/test-output/` for TestNG default reports
   - Navigate to `/ExtentReports/` (or custom folder) for advanced HTML reports

---

## 📊 Sample Reporting Output

Screenshots of failure are stored under `Screenshots/` and attached in ExtentReports.

---

##  Imporvements 

> _Note: This is an ongoing project. I am working on this project & will add more test cases. I am also working on the improvements of this framework._

---

## 👨‍💻 Author

**Debopriyo Kundu**  
- [LinkedIn](https://www.linkedin.com/in/debopriyo-kundu/)  
- [GitHub](https://github.com/DKundu22)

