package com.ecommerce.testcases;

import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.LoginPage;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Test Case: Verify valid user login.
 * Steps:
 * 1. Verify homepage is visible
 * 2. Navigate to login page
 * 3. Verify login form is visible
 * 4. Enter valid credentials and login
 * 5. Verify login is successful with username display
 */

public class LoginPageTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(LoginPageTest.class);
    private LoginPage loginPage;

    @Test(groups = {"Smoke", "Regression"})
    public void verifyLogin() {
        log.info("===== Starting Test: verifyLogin =====");

        try {
            loginPage = new LoginPage();
            log.debug("LoginPage object instantiated.");

            // Step 1: Check homepage visibility
            Assert.assertTrue(loginPage.isHomePageVisible(), "Home page is not visible.");
            log.info("Step 1 passed: Homepage is visible.");

            // Step 2: Navigate to login page
            loginPage.goToLoginPage();
            log.info("Step 2 passed: Navigated to login page.");

            // Step 3: Verify login form visibility
            Assert.assertTrue(loginPage.isLoginTitleVisible(), "'Login to your account' title not visible.");
            log.info("Step 3 passed: Login form title is visible.");

            // Step 4: Perform login
            String email = prop.getProperty("email");
            String password = prop.getProperty("password");
            log.debug("Using email: " + email + " and password: [HIDDEN]");
            loginPage.doLogin(email, password);
            log.info("Step 4 passed: Login attempted with valid credentials.");

            // Step 5: Verify username after login
            String actualUsername = loginPage.getLoggedInUsername();
            String expectedUsername = prop.getProperty("name");
            log.debug("Expected username: " + expectedUsername + ", Actual: " + actualUsername);
            Assert.assertEquals(actualUsername, expectedUsername, "Logged-in username mismatch.");
            log.info("Step 5 passed: Logged-in username verified successfully.");

            log.info("===== Test Passed: verifyLogin =====");

        } catch (Exception e) {
            log.error("Test execution failed.", e);
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }
}
