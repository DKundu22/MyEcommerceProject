package com.ecommerce.testcases;

import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.RegisterPage;
import com.ecommerce.utility.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Test Case: Register a new user using data-driven approach.
 * Steps:
 * 1. Navigate to Signup/Login page
 * 2. Enter name and email to initiate signup
 * 3. Fill full registration form
 * 4. Verify account creation
 * 5. Click Continue to login
 * 6. Verify "Logged in as <username>" is visible
 * 7. Delete account and verify deletion
 */

public class RegisterUserTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(RegisterUserTest.class);
    private RegisterPage registerPage;

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        List<Map<String, String>> testData = ExcelUtil.getTestData("Sheet1");
        Object[][] data = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }
        return data;
    }

    @Test(dataProvider = "userData", groups = {"Regression"})
    public void registerUser(Map<String, String> userData) {
        log.info("===== Starting Test: registerUser =====");

        try {
            registerPage = new RegisterPage();
            log.debug("RegisterPage object instantiated.");

            // Step 1: Navigate to Signup/Login page
            registerPage.navigateToRegister();
            log.info("Step 1 passed: Navigated to Signup/Login page.");

            // Step 2: Enter name and email
            log.debug("Entering name: {} and email: {}", userData.get("Name"), userData.get("Email"));
            registerPage.enterSignupDetails(userData.get("Name"), userData.get("Email"));
            log.info("Step 2 passed: Signup details submitted.");

            // Step 3: Fill the complete account registration form
            registerPage.fillAccountDetails(userData);
            log.info("Step 3 passed: Registration form filled and submitted.");

            // Step 4: Verify "Account Created!" is visible
            Assert.assertTrue(registerPage.isAccountCreatedVisible(), "Account creation confirmation not displayed.");
            log.info("Step 4 passed: Account creation confirmed.");

            // Step 5: Click "Continue" after account creation
            registerPage.clickContinue();
            log.info("Step 5 passed: Clicked Continue button.");

            // Step 6: Verify "Logged in as <username>" is visible
            Assert.assertTrue(registerPage.isLoggedInAsVisible(), "'Logged in as' not visible after login.");
            log.info("Step 6 passed: Logged in as username verified.");

            // Step 7: Delete the created account and verify deletion
            registerPage.deleteAccount();
            Assert.assertTrue(registerPage.isAccountDeletedVisible(), "Account deletion message not visible.");
            log.info("Step 7 passed: Account deletion confirmed.");

            registerPage.clickContinue();
            log.info("===== Test Passed: registerUser =====");

        } catch (Exception e) {
            log.error("Test execution failed: ", e);
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        }
    }
}
