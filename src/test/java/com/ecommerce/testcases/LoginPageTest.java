package com.ecommerce.testcases;

import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.LoginPage;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import com.ecommerce.utility.JsonDataReader;



public class LoginPageTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(LoginPageTest.class);
    private LoginPage loginPage;
    
    /**
    * Test Case: Verify valid user login.
    * Steps:
    * 1. Verify homepage is visible
    * 2. Navigate to login page
    * 3. Verify login form is visible
    * 4. Enter valid credentials and login
    * 5. Verify login is successful with username display
    */

    @Test(groups = {"Smoke", "Regression"})
    public void verifyLogin() {
        log.info("===== Starting Test: verifyLogin =====");

        try {
            loginPage = new LoginPage();
            JsonDataReader jsonReader = new JsonDataReader(System.getProperty("user.dir") + "/src/test/resources/TestData/LoginCredentials.json");
            JSONObject validCreds = jsonReader.getValidCredentials();

            String email = validCreds.get("email").toString();
            String password = validCreds.get("password").toString();
            String expectedUsername = validCreds.get("username").toString();
            log.debug("Using email: " + email + " and password: [HIDDEN]");

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
            loginPage.doLogin(email, password);
            log.info("Step 4 passed: Login attempted with valid credentials.");

            // Step 5: Verify username after login
            String actualUsername = loginPage.getLoggedInUsername();
            log.debug("Expected username: " + expectedUsername + ", Actual: " + actualUsername);
            Assert.assertEquals(actualUsername, expectedUsername, "Logged-in username mismatch.");
            log.info("Step 5 passed: Logged-in username verified successfully.");

            log.info("===== Test Passed: verifyLogin =====");

        } catch (Exception e) {
            log.error("Test execution failed.", e);
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }
    
    /**
     * Test Case: Verify invalid user login.
     * Steps:
     * 1. Navigate to login page
     * 2. Enter invalid credentials and attempt login
     * 3. Verify error message is displayed
     */

    @Test(groups = {"Regression"})
    public void verifyInvalidLogin() {
        log.info("===== Starting Test: verifyInvalidLogin =====");

        try {
            loginPage = new LoginPage();
            JsonDataReader jsonReader = new JsonDataReader(System.getProperty("user.dir") + "/src/test/resources/TestData/LoginCredentials.json");
            JSONArray invalidCredsArray = jsonReader.getInvalidCredentials();

            for (Object obj : invalidCredsArray) {
                JSONObject invalidCreds = (JSONObject) obj;
                String email = invalidCreds.get("email").toString();
                String password = invalidCreds.get("password").toString();

                loginPage.goToLoginPage();
                Assert.assertTrue(loginPage.isLoginTitleVisible(), "'Login to your account' title not visible.");

                loginPage.doLogin(email, password);
                String errorMsg = loginPage.getLoginErrorMessage();
                Assert.assertEquals(errorMsg, "Your email or password is incorrect!", "Error message not displayed or incorrect for invalid login.");
                log.info("Invalid login error message verified for email: " + email);
            }

            log.info("===== Test Passed: verifyInvalidLogin =====");

        } catch (Exception e) {
            log.error("Test execution failed.", e);
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }
}
