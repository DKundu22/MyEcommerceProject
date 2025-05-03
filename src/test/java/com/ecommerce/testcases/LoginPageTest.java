package com.ecommerce.testcases;

import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.LoginPage;
import com.ecommerce.utility.Log;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LoginPageTest extends BaseClass {

    LoginPage loginPage;

    @Test
    public void verifyLogin() {
        Log.startTestCase("verifyLogin");

        loginPage = new LoginPage();
        Log.info("LoginPage object initialized.");

        // Step 3: Verify home page
        Log.info("Verifying home page visibility.");
        Assert.assertTrue(loginPage.isHomePageVisible(), "Home page is not visible.");

        // Step 4-5: Navigate to login and verify login title
        Log.info("Navigating to login page.");
        loginPage.goToLoginPage();

        Log.info("Checking login title visibility.");
        Assert.assertTrue(loginPage.isLoginTitleVisible(), "'Login to your account' is not visible.");

        // Step 6-7: Perform login
        Log.info("Entering email and password.");
        loginPage.doLogin(prop.getProperty("email"), prop.getProperty("password"));

        // Step 8: Verify successful login using dynamic username
        String actualUsername = loginPage.getLoggedInUsername();
        String expectedUsername = prop.getProperty("name");

        Log.info("Verifying logged in username. Expected: " + expectedUsername + ", Actual: " + actualUsername);
        Assert.assertEquals(actualUsername, expectedUsername, "Logged-in username mismatch.");

        Log.endTestCase("verifyLogin");
    }
}


